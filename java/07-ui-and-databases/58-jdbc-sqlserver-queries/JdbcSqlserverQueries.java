import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

// Consultas (SELECT) a una base de datos SQL Server con JDBC: el equivalente de la lección 58 (ADO.NET).
// Requiere la base "farmacia" de la lección 57 (script farmacia.sql en esa carpeta).
// Para ejecutarlo (ver el .md):
//   java -cp mssql-jdbc-12.10.2.jre11.jar JdbcSqlserverQueries.java
// Por defecto se conecta a "localhost" con la autenticación de Windows. Variables de entorno opcionales:
//   SQLSERVER_SERVER, SQLSERVER_USER y SQLSERVER_PASSWORD (ver la lección 57).

public class JdbcSqlserverQueries {

    // ===== 3. El programa principal =====

    public static void main(String[] args) {
        System.out.println("Iniciando conexión a la base...");

        // La consulta abre y cierra la conexión por su cuenta
        Consultas consultar = new Consultas();
        consultar.consultaClientes();
    }
}


// ===== 1. La clase Conexion (la de la lección 57) =====

class Conexion {
    private final String url;
    private final String usuario;       // null = autenticación de Windows
    private final String password;

    private Connection conectar;

    public Conexion() {
        String servidor = System.getenv("SQLSERVER_SERVER");
        if (servidor == null) servidor = "localhost";

        usuario = System.getenv("SQLSERVER_USER");
        password = System.getenv("SQLSERVER_PASSWORD");

        String parametros = "databaseName=farmacia;trustServerCertificate=true;";
        if (usuario == null) {
            parametros += "integratedSecurity=true;";
        }

        url = "jdbc:sqlserver://" + servidor + ";" + parametros;
    }

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
}


// ===== 2. La clase Consultas: las consultas a la base =====

class Consultas {

    public void consultaClientes() {
        // Un objeto de la clase Conexion: ya sabe cómo conectarse
        Conexion conectar = new Conexion();

        try {
            // abrirConexion devuelve la conexión abierta (o null, si hubo un error)
            Connection con = conectar.abrirConexion();
            if (con == null) {
                throw new SQLException("La conexión no está abierta");
            }

            // Solo se piden los campos necesarios: el ID casi nunca se muestra en las aplicaciones
            String consulta = "SELECT nombre, direccion, telefono FROM clientes";

            // Statement: lleva la consulta por la conexión. executeQuery() la ejecuta y devuelve el ResultSet
            // ResultSet: las filas del resultado, que se leen de una en una
            // try-with-resources: cierra el Statement y el ResultSet automáticamente al salir
            try (Statement comando = con.createStatement();
                 ResultSet lectura = comando.executeQuery(consulta)) {

                // Encabezados con formato de tabla: %-20s es texto alineado a la izquierda, en 20 caracteres
                System.out.println();
                System.out.printf("%-20s | %-30s | %-15s%n", "Nombre", "Dirección", "Teléfono");
                System.out.println("-".repeat(70));         // línea de 70 guiones

                // next() avanza a la siguiente fila y devuelve false cuando ya no quedan
                while (lectura.next()) {
                    // Cada campo se pide por su nombre EXACTO en la tabla
                    String nombre = lectura.getString("nombre");
                    String direccion = lectura.getString("direccion");
                    String telefono = lectura.getString("telefono");

                    System.out.printf("%-20s | %-30s | %-15s%n", nombre, direccion, telefono);
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
