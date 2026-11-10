import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// Conexión a una base de datos MySQL con JDBC: el equivalente de la lección 53 (ADO.NET).
// Antes de ejecutar: instalar MySQL, ejecutar colegio_amanecer.sql y descargar el conector (mysql-connector-j-8.4.0.jar).
// Para ejecutarlo (ver el .md):
//   java -cp mysql-connector-j-8.4.0.jar JdbcMysqlConnection.java
// Si la clave de root no es abc123, definir la variable de entorno MYSQL_PASSWORD.

public class JdbcMysqlConnection {

    // ===== 2. El programa principal =====

    public static void main(String[] args) {
        System.out.println("Iniciando conexión...");

        Conexion conectarBD = new Conexion();

        // Abrir la conexión (tiene que ser un paso aparte de crear el objeto)
        Connection conectar = conectarBD.abrirConexion();

        try {
            if (conectar != null && !conectar.isClosed()) {
                System.out.println("CONEXIÓN EXITOSA");
            } else {
                System.out.println("ERROR CONECTÁNDOSE A LA BASE");
            }
        } catch (SQLException e) {
            System.out.println("ERROR CONECTÁNDOSE A LA BASE");
        }

        // Al terminar, cerrar la conexión
        conectarBD.cerrarConexion();
    }
}


// ===== 1. La clase Conexion: guarda los parámetros y abre la conexión =====

class Conexion {

    // Connection representa la conexión activa con la base de datos (null mientras no se ha abierto)
    public Connection conectar;

    // Los parámetros de conexión: servidor, puerto y nombre de la base forman la URL JDBC
    private final String url = "jdbc:mysql://localhost:3306/colegio_amanecer";   // servidor, puerto y base
    private final String usuario = "root";
    private final String password;

    // Constructor: prepara los parámetros (todavía sin conectar)
    public Conexion() {
        // La clave no se escribe en el código: se lee de una variable de entorno (por defecto, abc123 como en el vídeo)
        String clave = System.getenv("MYSQL_PASSWORD");
        password = (clave != null) ? clave : "abc123";
    }

    // Abre la conexión y devuelve el objeto de conexión (abierto, o null si hubo un error)
    public Connection abrirConexion() {
        try {
            // Solo se abre si no hay una conexión abierta
            if (conectar == null || conectar.isClosed()) {
                conectar = DriverManager.getConnection(url, usuario, password);
                System.out.println("Conexión abierta");
            }
        } catch (SQLException e) {
            System.out.println("Error al abrir la conexión a la base: " + e.getMessage());
        }

        return conectar;
    }

    // Cierra la conexión: siempre hay que cerrarla para liberar recursos
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
}
