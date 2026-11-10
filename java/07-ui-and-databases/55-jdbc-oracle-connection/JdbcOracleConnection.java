import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// Conexión a una base de datos Oracle con JDBC: el equivalente de la lección 55 (ADO.NET).
// Antes de ejecutar: tener Oracle (por ejemplo Oracle Database Express Edition) en marcha
// y descargar el controlador (ojdbc11.jar).
// Para ejecutarlo (ver el .md):
//   java -cp ojdbc11-23.26.4.0.0.jar JdbcOracleConnection.java
// Si la clave de system no es "oracle", definir la variable de entorno ORACLE_PASSWORD;
// en Oracle 18c/21c XE, definir ORACLE_SERVICE=XEPDB1.

public class JdbcOracleConnection {

    // ===== 2. El programa principal =====

    public static void main(String[] args) {
        System.out.println("Iniciando conexión a la base...");

        Conexion conectar = new Conexion();

        conectar.abrirConexion();
        conectar.cerrarConexion();
    }
}


// ===== 1. La clase Conexion: guarda los parámetros y abre / cierra la conexión =====

class Conexion {

    // Los parámetros de conexión: servidor, puerto y servicio forman la URL JDBC
    private final String url;                                  // se arma en el constructor: servidor:puerto/servicio
    private final String usuario = "system";
    private final String password;

    // Connection representa la conexión activa con la base de datos (null mientras no se ha abierto)
    private Connection conexionOracle;

    // Constructor: prepara los parámetros (todavía sin conectar)
    public Conexion() {
        // La clave no se escribe en el código: se lee de una variable de entorno (por defecto, oracle como en el vídeo)
        String clave = System.getenv("ORACLE_PASSWORD");
        password = (clave != null) ? clave : "oracle";

        // El servicio: XE en Oracle 11g XE (como en el vídeo). En 18c/21c XE, la base "enchufable" es XEPDB1
        String servicio = System.getenv("ORACLE_SERVICE");
        url = "jdbc:oracle:thin:@//localhost:1521/" + ((servicio != null) ? servicio : "XE");
    }

    // Abre la conexión
    public void abrirConexion() {
        try {
            // aquí es donde realmente se conecta
            conexionOracle = DriverManager.getConnection(url, usuario, password);
            System.out.println("Sistema conectado a Oracle Database");
        } catch (SQLException e) {
            System.out.println("Error conectando a la base: " + e.getMessage());
        }
    }

    // Cierra la conexión
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
}
