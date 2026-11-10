# Conexión a MySQL con JDBC

## Qué se aprende

Conectarse a una **base de datos** para **guardar**, **recuperar**, **actualizar** y **eliminar** datos. En Java se hace con **JDBC** (*Java Database Connectivity*): el paquete `java.sql`, que es el equivalente de **ADO.NET** en C# (lección 53).

JDBC es una interfaz **común para cualquier gestor** (MySQL, Oracle, SQL Server, PostgreSQL...). Lo que cambia entre gestores es el **controlador** (*driver*) y la **URL** de conexión; las clases del programa (`Connection`, `Statement`, `ResultSet`) son siempre las mismas.

> No es un curso de bases de datos: solo se prepara un entorno para aprender a **conectarse y trabajar desde Java**.

## El flujo típico

```text
1. Preparar los parámetros       →  servidor, puerto, base de datos, usuario y contraseña
2. Abrir la conexión             →  DriverManager.getConnection → Connection
3. Ejecutar comandos SQL         →  Statement / PreparedStatement: SELECT, INSERT, UPDATE, DELETE
4. Leer los resultados           →  ResultSet
5. Cerrar la conexión            →  siempre, para liberar recursos
```

| Paso | JDBC | ADO.NET (MySQL) |
|---|---|---|
| Conexión | `Connection` | `MySqlConnection` |
| Comando | `Statement` | `MySqlCommand` |
| Lector | `ResultSet` | `MySqlDataReader` |

## Preparar MySQL

Si ya hay un MySQL instalado solo hace falta conocer los **parámetros de conexión**.

### Instalarlo (Windows)

1. Descargar **MySQL Installer for Windows** (la versión grande, de unos 350 MB) desde la sección *Downloads → MySQL Community (GPL) Downloads* de mysql.com.
2. Ejecutarlo y elegir el tipo **Custom**.
3. Instalar **MySQL Server 8.0** (el motor de bases de datos, donde se guardan los datos) y **MySQL Workbench 8.0** (el editor con el que se administran).
4. En la configuración, dejar todo por defecto: *Development Computer* y el puerto **TCP/IP 3306**.
5. Fijar la **clave del administrador** (`root`). El instalador avisa de que es *weak* si es sencilla: en un entorno de pruebas no importa, pero en **producción** hay que usar una clave **fuerte**.
6. No crear más usuarios; terminar.

### MySQL Workbench y la base del ejemplo

Workbench se abre con la conexión **instancia local**: usuario **`root`** y dirección **`localhost:3306`**.

Con el script **[colegio_amanecer.sql](colegio_amanecer.sql)** (en esta carpeta) se crea la base **`colegio_amanecer`**:

1. Copiar todo su contenido en el escritorio de Workbench y ejecutarlo (el rayo). **Todas las líneas deben salir en verde**.
2. Comprobar con `SELECT * FROM alumnos;`.

| Tabla | Campos principales |
|---|---|
| `alumnos` | `id_alumno`, `nombre`, `apellido`, `edad`, `direccion`, `telefono`, `grado`, `id_profesor` |
| `profesores` | `id_profesor`, `nombre`, `grado` |
| `materias` | `id_materia`, `materia`, `nivel`, `horario`, `id_profesor` |
| `alumno_materia` | `id_alumno`, `id_materia` |

> El nombre es `colegio_amanecer` (minúscula y guion bajo): **hay que escribirlo exactamente igual** en la URL.

## El controlador (driver) de MySQL

`java.sql` define las clases, pero **el controlador de cada gestor es una biblioteca externa**: un archivo `.jar`.

- Con **Maven** (como gestor de dependencias, equivalente de NuGet en C#):

```xml
<dependency>
    <groupId>com.mysql</groupId>
    <artifactId>mysql-connector-j</artifactId>
    <version>8.4.0</version>
</dependency>
```

- A mano: descargar `mysql-connector-j-8.4.0.jar` (de Maven Central o de mysql.com) y ponerlo en el **classpath** al ejecutar:

```bash
java -cp mysql-connector-j-8.4.0.jar JdbcMysqlConnection.java
```

No hace falta `Class.forName(...)`: desde JDBC 4 el controlador se **registra solo** al estar en el classpath.

## La clase `Conexion`

Se separa en una clase la parte de **conectarse**, para que el programa sea más ordenado y se pueda reutilizar.

```java
class Conexion {
    public Connection conectar;                                                // la conexión activa (null mientras no se abre)

    private final String url = "jdbc:mysql://localhost:3306/colegio_amanecer";  // servidor, puerto y base
    private final String usuario = "root";
    private final String password;
}
```

### La URL de conexión

```text
jdbc:mysql://localhost:3306/colegio_amanecer
│    │      │         │    └── nombre de la base de datos
│    │      │         └─────── puerto (3306, el de MySQL por defecto)
│    │      └───────────────── servidor (localhost: este equipo)
│    └──────────────────────── el gestor (mysql, oracle, sqlserver...)
└───────────────────────────── protocolo JDBC
```

Frente a ADO.NET, donde todo iba en una cadena `server=...;port=...;`, en JDBC la **URL** lleva servidor, puerto y base, y el **usuario y la clave** se pasan aparte.

### La contraseña no va en el código

El vídeo la escribe en el código (`abc123`) porque es un laboratorio. En el ejemplo se lee de una **variable de entorno**, con `abc123` por defecto:

```java
String clave = System.getenv("MYSQL_PASSWORD");
password = (clave != null) ? clave : "abc123";
```

Para usar otra clave, antes de ejecutar:

```powershell
$env:MYSQL_PASSWORD = "mi_clave"      # PowerShell
```

Una clave escrita en el código acaba en el repositorio y la ve cualquiera.

## Abrir la conexión: `abrirConexion`

```java
public Connection abrirConexion() {
    try {
        if (conectar == null || conectar.isClosed()) {
            conectar = DriverManager.getConnection(url, usuario, password);
            System.out.println("Conexión abierta");
        }
    } catch (SQLException e) {
        System.out.println("Error al abrir la conexión a la base: " + e.getMessage());
    }

    return conectar;
}
```

- **`DriverManager.getConnection(url, usuario, password)`** es lo que **realmente se conecta**: abre un canal con el servidor y se autentica. (En C# eran dos pasos: crear el `MySqlConnection` y llamar a `Open()`.)
- **`isClosed()`** dice si la conexión está cerrada (en C# se consultaba `State == ConnectionState.Closed`).
- Si algo falla (servidor apagado, clave incorrecta, base inexistente...) salta una **`SQLException`** (excepción comprobada: hay que capturarla o declararla con `throws`). El **`try/catch`** muestra el motivo en vez de cerrar el programa.

> Un `try/catch` es **imprescindible** en todo el trabajo con bases de datos: el servidor puede estar apagado, la red puede fallar, la clave puede ser incorrecta.

## Cerrar la conexión: `cerrarConexion`

```java
public void cerrarConexion() {
    try {
        if (conectar != null && !conectar.isClosed()) {
            conectar.close();
            System.out.println("Conexión cerrada");
        }
    } catch (SQLException e) {
        System.out.println("Error al cerrar la conexión: " + e.getMessage());
    }
}
```

**Siempre hay que cerrar la conexión**: una conexión abierta ocupa **memoria, procesador y una de las conexiones limitadas del servidor**. (Más adelante se verá `try-with-resources`, que la cierra automáticamente.)

## El programa principal

```java
Conexion conectarBD = new Conexion();
Connection conectar = conectarBD.abrirConexion();

if (conectar != null && !conectar.isClosed()) {
    System.out.println("CONEXIÓN EXITOSA");
} else {
    System.out.println("ERROR CONECTÁNDOSE A LA BASE");
}

conectarBD.cerrarConexion();
```

### Salida esperada

Con MySQL funcionando y la base creada:

```text
Iniciando conexión...
Conexión abierta
CONEXIÓN EXITOSA
Conexión cerrada
```

Sin MySQL (o con la clave mal), sale el motivo y el aviso de error:

```text
Iniciando conexión...
Error al abrir la conexión a la base: Communications link failure ...
ERROR CONECTÁNDOSE A LA BASE
```

### "Abrir conexión" y "conectarse"

El vídeo distingue dos mensajes: **"conexión abierta"** lo da el método `abrirConexion` (se ha establecido la comunicación con el servidor) y **"conexión exitosa"** lo da el programa principal (la conexión está abierta y ya se pueden ejecutar comandos SQL, lo que se hace en la lección siguiente).

## Ejecutar

```bash
java -cp mysql-connector-j-8.4.0.jar JdbcMysqlConnection.java
```

## Comandos

```bash
cd java/07-ui-and-databases/53-jdbc-mysql-connection
# descargar mysql-connector-j-8.4.0.jar en esta carpeta y:
java -cp mysql-connector-j-8.4.0.jar JdbcMysqlConnection.java
```

Fuente: [curso de C# de INFORMATICONFIG](https://www.youtube.com/watch?v=7Vy8_RblBAA&list=PL2Z95CSZ1N4F7mym8Ca16RoTDVAlIOPlT&index=53), vídeo 53.
