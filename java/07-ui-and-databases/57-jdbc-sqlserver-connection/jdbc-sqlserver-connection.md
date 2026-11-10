# Conexión a SQL Server con JDBC

## Qué se aprende

La misma operación de las lecciones 53 (MySQL) y 55 (Oracle), ahora con **SQL Server**, el gestor de Microsoft. JDBC es común a todos los gestores: lo que cambia es el **controlador** y la **URL**.

| | MySQL | Oracle | SQL Server |
|---|---|---|---|
| Controlador | `mysql-connector-j` | `ojdbc11` | **`mssql-jdbc`** |
| URL | `jdbc:mysql://localhost:3306/colegio_amanecer` | `jdbc:oracle:thin:@//localhost:1521/XE` | **`jdbc:sqlserver://localhost;databaseName=farmacia;...`** |

## Preparar SQL Server

Quien ya tenga una instancia de SQL Server solo necesita conocer los **parámetros**.

### El motor: SQL Server 2022 (Express)

1. En la página de **SQL Server 2022** de Microsoft → **Descargas** → edición **Express** (gratuita, para instalar en el propio equipo). Microsoft pide rellenar un formulario: es un requisito, no es relevante.
2. Ejecutar el instalador y elegir el tipo **Básica**.
3. Aceptar la licencia y la carpeta de instalación.
4. Al terminar muestra el **nombre de la instancia** (`SQLEXPRESS` en Express), las carpetas y la versión.

### La herramienta: SQL Server Management Studio (SSMS)

El equivalente de MySQL Workbench y de SQL Developer. En la misma pantalla final: **Install SSMS** → descargar **SSMS 21** → instalarlo **sin cargas de trabajo adicionales**.

### Conectarse con SSMS

1. Abrirlo desde el menú Inicio. Si pide iniciar sesión con una cuenta: **omitir**.
2. En la ventana de conexión: **Server name** (`localhost`, o `localhost\SQLEXPRESS`), **Authentication: Windows Authentication** y **Encryption: Optional** (si se deja *Mandatory* da un error y no se puede entrar).
3. **Connect**. Si **SQL Server Agent** aparece con una cruz roja: clic derecho → **Start**.

### Crear la base de datos `farmacia`

1. Clic derecho en **Databases** → **New Database** → nombre **`farmacia`** → **OK**.
2. Clic derecho sobre `farmacia` → **New Query**.
3. El script **[farmacia.sql](farmacia.sql)** (en esta carpeta) tiene **dos partes**: la **1** crea tres tablas (debe decir *Commands completed successfully*) y la **2** las llena (20 clientes, 30 productos y 30 ventas). Se pegan y ejecutan por separado.
4. Comprobar: `SELECT * FROM clientes;`.

| Tabla | Campos principales |
|---|---|
| `clientes` | `id_cliente`, `nombre`, `direccion`, `telefono` |
| `productos` | `id_producto`, `nombre`, `descripcion`, `precio`, `stock` |
| `ventas` | `id_venta`, `id_cliente`, `id_producto`, `cantidad`, `fecha` |

### Habilitar TCP/IP (necesario en Java)

JDBC se conecta por **TCP/IP, puerto 1433**. En **SQL Server Express** esa opción suele estar **desactivada** (C# usa por defecto otro canal, las *named pipes*, y no lo nota):

1. Abrir **SQL Server Configuration Manager** → *Configuración de red de SQL Server* → *Protocolos de SQLEXPRESS* → **TCP/IP** → **Habilitar**.
2. En las propiedades de TCP/IP, pestaña *Direcciones IP*, sección **IPAll**: **Puerto TCP = 1433** (y **Puertos dinámicos** en blanco).
3. **Reiniciar** el servicio *SQL Server (SQLEXPRESS)*.

Si no, el error es *The TCP/IP connection to the host localhost, port 1433 has failed* (el mismo que se ve sin SQL Server).

## El controlador (driver) de SQL Server

Es un **`.jar`** que hay que añadir al classpath:

- Con **Maven** (como gestor de dependencias, equivalente de NuGet en C#):

```xml
<dependency>
    <groupId>com.microsoft.sqlserver</groupId>
    <artifactId>mssql-jdbc</artifactId>
    <version>12.10.2.jre11</version>
</dependency>
```

- A mano: descargar `mssql-jdbc-12.10.2.jre11.jar` (de Maven Central) y ponerlo en el **classpath**:

```bash
java -cp mssql-jdbc-12.10.2.jre11.jar JdbcSqlserverConnection.java
```

El controlador está escrito en Java puro. Y no hace falta `Class.forName(...)`: se registra solo.

## La clase `Conexion`

```java
class Conexion {
    private final String url;
    private final String usuario;       // null = autenticación de Windows
    private final String password;

    private Connection conectar;        // null mientras no se ha abierto
}
```

### La URL de conexión

```text
jdbc:sqlserver://localhost;databaseName=farmacia;trustServerCertificate=true;integratedSecurity=true;
│    │           │         └── parámetros, separados por punto y coma
│    │           └──────────── el servidor: el propio equipo (con instancia con nombre: localhost\SQLEXPRESS)
│    └──────────────────────── el gestor (sqlserver)
└───────────────────────────── protocolo JDBC
```

| Parámetro | Significado |
|---|---|
| `databaseName=farmacia` | La base de datos |
| `trustServerCertificate=true` | **Confiar en el certificado** del servidor (si no, falla con *PKIX path building failed* o *certificate not trusted*; en local no hay problema) |
| `integratedSecurity=true` | **Autenticación de Windows**: sin usuario ni clave (equivale a `Trusted_Connection=True` de C#) |

### Autenticación de Windows en Java

`integratedSecurity=true` necesita en Windows una **biblioteca nativa** (`mssql-jdbc_auth-12.10.2.x64.dll`, que viene en el ZIP del controlador en el sitio de Microsoft) dentro de una carpeta del **`java.library.path`**:

```bash
java -Djava.library.path="C:\ruta\a\dll" -cp mssql-jdbc-12.10.2.jre11.jar JdbcSqlserverConnection.java
```

Sin ella: *This driver is not configured for integrated authentication*. La alternativa más simple es la **autenticación de SQL Server** (usuario y clave), que no necesita ninguna DLL:

| Variable | Para qué |
|---|---|
| `SQLSERVER_SERVER` | El servidor, por ejemplo `localhost\SQLEXPRESS` |
| `SQLSERVER_USER` + `SQLSERVER_PASSWORD` | Si se definen, se usa **autenticación de SQL Server** (y no la de Windows) |

Para poder usarla hay que tener el servidor en **modo de autenticación mixta** y un *login* creado (propiedades del servidor en SSMS → *Security*).

## Abrir y cerrar la conexión

```java
public Connection abrirConexion() {
    try {
        if (conectar == null || conectar.isClosed()) {
            conectar = (usuario == null)
                    ? DriverManager.getConnection(url)
                    : DriverManager.getConnection(url, usuario, password);
            System.out.println("Sistema conectado a SQL Server");
        }
    } catch (SQLException error) {
        System.out.println("Error conectando a la base: " + error.getMessage());
    }

    return conectar;
}

public void cerrarConexion() {
    try {
        if (conectar != null && !conectar.isClosed()) {
            conectar.close();
            System.out.println("Sistema desconectado de la base");
        }
    } catch (SQLException error) {
        System.out.println("Error al desconectar de la base: " + error.getMessage());
    }
}
```

- **`DriverManager.getConnection(...)`** es lo que **realmente se conecta**. (En C# eran dos pasos: crear el `SqlConnection` y llamar a `Open()`.)
- Se abre solo si **no hay conexión abierta** y se cierra solo si **la hay** (`isClosed()`).
- Todo va en un **`try/catch`**: la `SQLException` es una excepción comprobada.
- `abrirConexion` **devuelve** la conexión (o `null` si falló): quien la llame podrá usarla para consultar (lección siguiente).

## Errores típicos (los del vídeo)

Con cada ejecución falla algo distinto, y el `catch` muestra el mensaje:

| Error | Causa |
|---|---|
| *The TCP/IP connection to the host ... has failed* | No hay SQL Server en marcha, TCP/IP está deshabilitado o el **servidor está mal escrito** (como la coma en vez de punto y coma del vídeo) |
| Certificado no confiable | Falta **`trustServerCertificate=true`** |
| *This driver is not configured for integrated authentication* | Falta la DLL de `integratedSecurity` |

> Y si el programa **conecta pero no dice nada**, es porque falta el `println` de éxito: hay que avisar **siempre** al usuario del resultado.

## El programa principal

```java
Conexion conexion = new Conexion();

conexion.abrirConexion();
conexion.cerrarConexion();
```

### Salida esperada

Con SQL Server funcionando y la base creada:

```text
Iniciando conexión a la base...
Sistema conectado a SQL Server
Sistema desconectado de la base
```

## Ejecutar

```bash
java -cp mssql-jdbc-12.10.2.jre11.jar JdbcSqlserverConnection.java
```

## Comandos

```bash
cd java/07-ui-and-databases/57-jdbc-sqlserver-connection
# descargar mssql-jdbc-12.10.2.jre11.jar en esta carpeta y:
java -cp mssql-jdbc-12.10.2.jre11.jar JdbcSqlserverConnection.java
```

Fuente: [curso de C# de INFORMATICONFIG](https://www.youtube.com/watch?v=y0OPYm63iZ0&list=PL2Z95CSZ1N4F7mym8Ca16RoTDVAlIOPlT&index=57), vídeo 57.
