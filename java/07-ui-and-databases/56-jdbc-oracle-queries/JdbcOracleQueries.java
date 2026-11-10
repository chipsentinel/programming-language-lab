import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;

// Consultas (SELECT) a una base de datos Oracle con JDBC: el equivalente de la lección 56 (ADO.NET).
// Requiere el usuario LIBRERIA y sus tablas (script libreria.sql, en esta carpeta).
// Para ejecutarlo (ver el .md):
//   java -cp ojdbc11-23.26.4.0.0.jar JdbcOracleQueries.java
// Si la clave de libreria no es "oracle", definir la variable de entorno ORACLE_PASSWORD;
// en Oracle 18c/21c XE, definir ORACLE_SERVICE=XEPDB1.

public class JdbcOracleQueries {

    // ===== 3. El programa principal =====

    public static void main(String[] args) {
        System.out.println("Iniciando conexión a la base...");

        // La consulta abre y cierra la conexión por su cuenta
        Consultas consultar = new Consultas();
        consultar.consultaLibros();
    }
}


// ===== 1. La clase Conexion (la de la lección 55, con otro usuario) =====

class Conexion {
    private final String url;
    // El usuario es LIBRERIA (el esquema donde están las tablas), no SYSTEM:
    // con SYSTEM la consulta falla con "ORA-00942: la tabla o vista no existe", porque las tablas no están en su esquema
    private final String usuario = "libreria";
    private final String password;

    // public (antes private): ahora la usa otra clase, Consultas
    public Connection conexionOracle;

    public Conexion() {
        String clave = System.getenv("ORACLE_PASSWORD");
        password = (clave != null) ? clave : "oracle";

        String servicio = System.getenv("ORACLE_SERVICE");
        url = "jdbc:oracle:thin:@//localhost:1521/" + ((servicio != null) ? servicio : "XE");
    }

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
}


// ===== 2. La clase Consultas: las consultas a la base =====

class Consultas {

    public void consultaLibros() {
        // Un objeto de la clase Conexion: ya sabe cómo conectarse
        Conexion conectar = new Conexion();

        try {
            conectar.abrirConexion();

            // Si no se pudo abrir (queda null), no se puede consultar: se avisa con una excepción
            if (conectar.conexionOracle == null) {
                throw new SQLException("La conexión no está abierta");
            }

            String consulta = "SELECT * FROM libros";

            // Statement: lleva la consulta por la conexión. executeQuery() la ejecuta y devuelve el ResultSet
            // ResultSet: las filas del resultado, que se leen de una en una
            // try-with-resources: cierra el Statement y el ResultSet automáticamente al salir
            try (Statement comando = conectar.conexionOracle.createStatement();
                 ResultSet lectura = comando.executeQuery(consulta)) {

                System.out.println();
                System.out.printf("%-4s%-50s%-7s%-12s%s%n", "ID", "Título", "Autor", "Fecha", "Género");
                System.out.println("-".repeat(80));

                // next() avanza a la siguiente fila y devuelve false cuando ya no quedan
                while (lectura.next()) {
                    // Los campos se piden por su POSICIÓN en la tabla, pero en JDBC se empieza en 1 (no en 0):
                    // 1 id_libro, 2 titulo, 3 id_autor, 4 fecha_publicacion, 5 genero
                    // Y con el método que corresponde al TIPO: getInt, getString, getDate
                    int id = lectura.getInt(1);
                    String titulo = lectura.getString(2);
                    int autor = lectura.getInt(3);
                    LocalDate fecha = lectura.getDate(4).toLocalDate();   // java.sql.Date -> LocalDate (año-mes-día)
                    String genero = lectura.getString(5);

                    System.out.printf("%-4d%-50s%-7d%-12s%s%n", id, titulo, autor, fecha, genero);
                }
            }
        } catch (SQLException error) {
            System.out.println("Error consultando datos: " + error.getMessage());
        } finally {
            // finally se ejecuta SIEMPRE (haya error o no): la conexión se cierra sí o sí
            conectar.cerrarConexion();
        }
    }
}
