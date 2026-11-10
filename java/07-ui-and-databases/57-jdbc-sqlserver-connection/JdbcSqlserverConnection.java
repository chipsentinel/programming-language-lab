import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// Conexión a una base de datos SQL Server con JDBC: el equivalente de la lección 57 (ADO.NET).
// Antes de ejecutar: tener SQL Server en marcha, la base "farmacia" creada (script farmacia.sql, en esta carpeta)
// y descargar el controlador (mssql-jdbc-12.10.2.jre11.jar).
// Para ejecutarlo (ver el .md):
//   java -cp mssql-jdbc-12.10.2.jre11.jar JdbcSqlserverConnection.java
// Por defecto se conecta a "localhost" con la autenticación de Windows (como en el vídeo; en Java necesita una DLL, ver el .md).
// Variables de entorno opcionales:
//   SQLSERVER_SERVER    el servidor (por ejemplo localhost\SQLEXPRESS)
//   SQLSERVER_USER      si se define, se usa autenticación de SQL Server con este usuario...
//   SQLSERVER_PASSWORD  ...y esta contraseña

public class JdbcSqlserverConnection {

    // ===== 2. El programa principal =====

    public static void main(String[] args) {
        System.out.println("Iniciando conexión a la base...");

        Conexion conexion = new Conexion();

        conexion.abrirConexion();
        conexion.cerrarConexion();
    }
}


// ===== 1. La clase Conexion: guarda los parámetros y abre / cierra la conexión =====

class Conexion {

    // La URL JDBC: toda la información necesaria para llegar a la base
    private final String url;
    private final String usuario;       // null = autenticación de Windows
    private final String password;

    // Connection representa la conexión activa con la base de datos (null mientras no se ha abierto)
    private Connection conectar;

    // Constructor: prepara los parámetros (todavía sin conectar)
    public Conexion() {
        String servidor = System.getenv("SQLSERVER_SERVER");
        if (servidor == null) servidor = "localhost";       // el propio equipo

        usuario = System.getenv("SQLSERVER_USER");
        password = System.getenv("SQLSERVER_PASSWORD");

        // Parámetros separados por punto y coma, después del servidor:
        //   databaseName          nombre de la base de datos
        //   trustServerCertificate  confiar en el certificado del servidor (si no, falla en local)
        //   integratedSecurity    autenticación de Windows (sin usuario ni clave)
        String parametros = "databaseName=farmacia;trustServerCertificate=true;";
        if (usuario == null) {
            parametros += "integratedSecurity=true;";
        }

        url = "jdbc:sqlserver://" + servidor + ";" + parametros;
    }

    // Abre la conexión y devuelve el objeto de conexión (abierto, o null si hubo un error)
    public Connection abrirConexion() {
        try {
            // Solo se abre si no hay una conexión abierta
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

    // Cierra la conexión
    public void cerrarConexion() {
        try {
            // Solo si está abierta
            if (conectar != null && !conectar.isClosed()) {
                conectar.close();
                System.out.println("Sistema desconectado de la base");
            }
        } catch (SQLException error) {
            System.out.println("Error al desconectar de la base: " + error.getMessage());
        }
    }
}
