import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

// Consultas (SELECT) a una base de datos MySQL con JDBC: el equivalente de la lección 54 (ADO.NET).
// Requiere la base colegio_amanecer de la lección 53 (script colegio_amanecer.sql en esa carpeta).
// Para ejecutarlo (ver el .md):
//   java -cp mysql-connector-j-8.4.0.jar JdbcMysqlQueries.java
// Si la clave de root no es abc123, definir la variable de entorno MYSQL_PASSWORD.

public class JdbcMysqlQueries {

    // ===== 3. El programa principal =====

    public static void main(String[] args) {
        System.out.println("Iniciando conexión...");

        // La conexión se abre dentro de cada consulta: aquí solo se invocan
        Consulta consulta = new Consulta();
        consulta.consultaAlumnos();
        consulta.consultaNombreGrado();
    }
}


// ===== 1. La clase Conexion (la de la lección 53, con el método para cerrar) =====

class Conexion {
    public Connection conectar;

    private final String url = "jdbc:mysql://localhost:3306/colegio_amanecer";
    private final String usuario = "root";
    private final String password;

    public Conexion() {
        String clave = System.getenv("MYSQL_PASSWORD");
        password = (clave != null) ? clave : "abc123";
    }

    // Abre la conexión (el propio método comprueba el estado, así que no hace falta comprobarlo fuera)
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

    // Cierra la conexión si está abierta: liberar recursos (memoria, procesador, conexiones del servidor)
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


// ===== 2. La clase Consulta: las consultas a la base =====

class Consulta {

    // Consulta 1: todos los campos de la tabla alumnos
    public void consultaAlumnos() {
        // Se crea un objeto de la clase Conexion: es la que sabe cómo conectarse
        Conexion conexionConsulta = new Conexion();

        // La consulta SQL, en un String
        String consulta = "SELECT * FROM alumnos";

        try {
            // Si no se pudo abrir (devuelve null), no se puede consultar: se avisa con una excepción
            if (conexionConsulta.abrirConexion() == null) {
                throw new SQLException("La conexión no está abierta");
            }

            // Statement: lleva la consulta por la conexión. executeQuery() la ejecuta y devuelve el ResultSet
            // ResultSet: las filas del resultado, que se leen de una en una
            // try-with-resources: cierra el Statement y el ResultSet automáticamente al salir
            try (Statement consultar = conexionConsulta.conectar.createStatement();
                 ResultSet datos = consultar.executeQuery(consulta)) {

                System.out.println();
                System.out.printf("%-4s%-9s%-10s%-5s%-19s%-11s%-9s%s%n",
                                  "ID", "Nombre", "Apellido", "Edad", "Dirección", "Teléfono", "Grado", "Prof.");

                // next() avanza a la siguiente fila y devuelve false cuando ya no quedan
                while (datos.next()) {
                    // Cada campo se pide por su nombre EXACTO en la tabla (getInt, getString según el tipo).
                    // %-9s: alinea el valor a la izquierda en 9 caracteres (para que se vea como una tabla)
                    System.out.printf("%-4d%-9s%-10s%-5d%-19s%-11s%-9s%d%n",
                                      datos.getInt("id_alumno"), datos.getString("nombre"),
                                      datos.getString("apellido"), datos.getInt("edad"),
                                      datos.getString("direccion"), datos.getString("telefono"),
                                      datos.getString("grado"), datos.getInt("id_profesor"));
                }
            }
        } catch (SQLException e) {
            System.out.println("Error consultando datos: " + e.getMessage());
        } finally {
            // finally se ejecuta SIEMPRE (haya error o no): la conexión se cierra sí o sí
            conexionConsulta.cerrarConexion();
        }
    }

    // Consulta 2: solo el nombre y el grado (se piden solo los campos necesarios)
    public void consultaNombreGrado() {
        Conexion conexionConsulta = new Conexion();
        String consulta = "SELECT nombre, grado FROM alumnos";

        try {
            // Si no se pudo abrir (devuelve null), no se puede consultar: se avisa con una excepción
            if (conexionConsulta.abrirConexion() == null) {
                throw new SQLException("La conexión no está abierta");
            }

            try (Statement consultar = conexionConsulta.conectar.createStatement();
                 ResultSet datos = consultar.executeQuery(consulta)) {

                System.out.println();
                System.out.printf("%-9s%s%n", "Nombre", "Grado");

                while (datos.next()) {
                    System.out.printf("%-9s%s%n", datos.getString("nombre"), datos.getString("grado"));
                }
            }
        } catch (SQLException e) {
            System.out.println("Error consultando datos: " + e.getMessage());
        } finally {
            conexionConsulta.cerrarConexion();
        }
    }
}
