# Conexión a Oracle con JDBC

## Qué se aprende

La misma operación de la lección 53 (conectarse a una base de datos), pero con **Oracle**. Es importante saber hacerlo: muchos proyectos reales usan Oracle.

JDBC es **común a todos los gestores**: las clases (`Connection`, `DriverManager`) son las mismas. Cambian el **controlador** (*driver*) y la **URL**:

| | MySQL | Oracle |
|---|---|---|
| Controlador | `mysql-connector-j` | **`ojdbc11`** |
| URL | `jdbc:mysql://localhost:3306/colegio_amanecer` | **`jdbc:oracle:thin:@//localhost:1521/XE`** |
| Usuario | `root` | `system` |

## Preparar Oracle

Quien ya tenga Oracle instalado se puede saltar esto; solo necesita conocer los **parámetros** (usuario, clave, servidor, puerto y servicio).

### Oracle Database Express Edition (XE)

La versión **gratuita y ligera** de Oracle: más fácil de instalar y configurar que un Oracle profesional. El vídeo usa la **11g XE**, una versión antigua, pero para lo que se hace aquí la tecnología es la misma. Hoy se descarga la versión actual (**21c XE**) de oracle.com.

Pasos del instalador (*wizard*):

1. Ejecutar `setup` y **Next**.
2. Aceptar los términos de la licencia → **Next**.
3. Elegir la carpeta de instalación → **Next**.
4. Escribir la **clave del administrador** (la de los usuarios `SYS` y `SYSTEM`) → **Next**.
5. **Install**, y al terminar, **Finish**.

### SQL Developer

La herramienta para **administrar los datos** de Oracle (el equivalente de MySQL Workbench). Se descarga de oracle.com (hay una versión que ya **incluye el JDK**; como aquí ya se tiene Java, vale la normal). Se descomprime y se ejecuta `sqldeveloper.exe`.

Para conectarse: nueva conexión (flecha verde) → usuario **`system`** y la **clave** del instalador → **Test** (debe decir **Success**) → **Connect**.

Con clic derecho en la conexión → *Propiedades* se ve el **puerto** (**1521**, el de Oracle por defecto) y el servicio (**XE**).

## El controlador (driver) de Oracle

El controlador es un **`.jar`** que hay que añadir al classpath:

- Con **Maven** (como gestor de dependencias, equivalente de NuGet en C#):

```xml
<dependency>
    <groupId>com.oracle.database.jdbc</groupId>
    <artifactId>ojdbc11</artifactId>
    <version>23.26.4.0.0</version>
</dependency>
```

- A mano: descargar `ojdbc11-23.26.4.0.0.jar` (de Maven Central o de oracle.com) y ponerlo en el **classpath**:

```bash
java -cp ojdbc11-23.26.4.0.0.jar JdbcOracleConnection.java
```

`ojdbc11` es para **Java 11 o superior**. El controlador *thin* está escrito en Java puro: **no hace falta instalar el cliente de Oracle**. Y no hace falta `Class.forName(...)`: el controlador se registra solo.

## La clase `Conexion`

```java
class Conexion {
    private final String url = "jdbc:oracle:thin:@//localhost:1521/XE";
    private final String usuario = "system";
    private final String password;

    private Connection conexionOracle;     // null mientras no se ha abierto
}
```

### La URL de conexión

```text
jdbc:oracle:thin:@//localhost:1521/XE
│    │      │    │  │         │    └── el servicio (XE: Express Edition)
│    │      │    │  │         └─────── el puerto de Oracle
│    │      │    │  └───────────────── el servidor: este mismo equipo
│    │      │    └──────────────────── @// introduce servidor, puerto y servicio
│    │      └───────────────────────── "thin": el controlador que no necesita cliente instalado
│    └──────────────────────────────── el gestor (oracle)
└───────────────────────────────────── protocolo JDBC
```

El usuario y la clave se pasan **aparte** de la URL. A diferencia de MySQL, donde se indicaba **la base de datos**, en Oracle se indica **el servicio** y el usuario determina **su esquema** (donde están sus tablas). Con 11g XE el servicio es `XE`; con 18c o 21c XE la base "enchufable" donde se trabaja es **`XEPDB1`**: en ese caso se define la variable de entorno `ORACLE_SERVICE=XEPDB1` antes de ejecutar (por defecto se usa `XE`, como en el vídeo).

> `system` es el administrador: para pruebas está bien, pero en un proyecto real se crea **un usuario propio con los permisos justos**.

### La contraseña no va en el código

El vídeo la escribe en el código porque es un laboratorio. En el ejemplo se lee de una **variable de entorno**, con `oracle` por defecto:

```java
String clave = System.getenv("ORACLE_PASSWORD");
password = (clave != null) ? clave : "oracle";
```

Para usar otra clave, antes de ejecutar:

```powershell
$env:ORACLE_PASSWORD = "mi_clave"      # PowerShell
```

Una clave escrita en el código acaba en el repositorio y la ve cualquiera.

## Abrir y cerrar la conexión

```java
public void abrirConexion() {
    try {
        conexionOracle = DriverManager.getConnection(url, usuario, password);
        System.out.println("Sistema conectado a Oracle Database");
    } catch (SQLException e) {
        System.out.println("Error conectando a la base: " + e.getMessage());
    }
}

public void cerrarConexion() {
    try {
        if (conexionOracle != null) {
            conexionOracle.close();
            System.out.println("Sistema desconectado de la base");
        }
    } catch (SQLException e) {
        System.out.println("Error al desconectar de la base: " + e.getMessage());
    }
}
```

- **`DriverManager.getConnection(url, usuario, password)`** es lo que **realmente se conecta**. (En C# eran dos pasos: crear el `OracleConnection` y llamar a `Open()`.)
- **`close()`** cierra el canal. **Siempre hay que cerrar la conexión** para liberar recursos.
- Los dos van en un **`try/catch`**: la `SQLException` es una excepción comprobada y el servidor puede estar apagado, la clave puede ser incorrecta...
- En `cerrarConexion` se comprueba que **haya conexión** (`!= null`) para no avisar de "desconectado" si nunca se llegó a conectar (el vídeo no lo hace).

## El programa principal

```java
Conexion conectar = new Conexion();

conectar.abrirConexion();
conectar.cerrarConexion();
```

### Salida esperada

Con Oracle funcionando y la clave correcta:

```text
Iniciando conexión a la base...
Sistema conectado a Oracle Database
Sistema desconectado de la base
```

Sin Oracle (o con la clave mal), sale el motivo:

```text
Iniciando conexión a la base...
Error conectando a la base: ORA-12541: ... No hay ningún listener en host localhost port 1521.
```

(Los errores de Oracle empiezan con su **código `ORA-xxxxx`**, útil para buscarlos.)

## Ejecutar

```bash
java -cp ojdbc11-23.26.4.0.0.jar JdbcOracleConnection.java
```

## Comandos

```bash
cd java/07-ui-and-databases/55-jdbc-oracle-connection
# descargar ojdbc11-23.26.4.0.jar en esta carpeta y:
java -cp ojdbc11-23.26.4.0.0.jar JdbcOracleConnection.java
```

Fuente: [curso de C# de INFORMATICONFIG](https://www.youtube.com/watch?v=oY1LKRXDVE0&list=PL2Z95CSZ1N4F7mym8Ca16RoTDVAlIOPlT&index=55), vídeo 55.
