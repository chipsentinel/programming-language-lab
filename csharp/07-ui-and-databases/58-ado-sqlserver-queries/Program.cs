using System.Data;
using Microsoft.Data.SqlClient;

// Consultas (SELECT) a una base de datos SQL Server con ADO.NET.
// Requiere la base "farmacia" de la lección 57 (script farmacia.sql en esa carpeta).
// Por defecto se conecta a "localhost" con la autenticación de Windows. Variables de entorno opcionales:
//   SQLSERVER_SERVER, SQLSERVER_USER y SQLSERVER_PASSWORD (ver la lección 57).

// ===== 1. La clase Conexion (la de la lección 57) =====

class Conexion
{
    private readonly string cadenaConexion;
    private SqlConnection conectar;

    public Conexion()
    {
        string servidor = Environment.GetEnvironmentVariable("SQLSERVER_SERVER") ?? "localhost";
        string? usuario = Environment.GetEnvironmentVariable("SQLSERVER_USER");

        string seguridad = (usuario == null)
            ? "Trusted_Connection=True;"
            : "User Id=" + usuario + ";Password=" + Environment.GetEnvironmentVariable("SQLSERVER_PASSWORD") + ";";

        cadenaConexion = "Server=" + servidor + ";" +
                         "Database=farmacia;" +
                         seguridad +
                         "TrustServerCertificate=True;";

        conectar = new SqlConnection(cadenaConexion);
    }

    public SqlConnection AbrirConexion()
    {
        try
        {
            if (conectar.State == ConnectionState.Closed)
            {
                conectar.Open();
                Console.WriteLine("Sistema conectado a SQL Server");
            }
        }
        catch (Exception error)
        {
            Console.WriteLine("Error conectando a la base: " + error.Message);
        }

        return conectar;
    }

    public void CerrarConexion()
    {
        try
        {
            if (conectar.State == ConnectionState.Open)
            {
                conectar.Close();
                Console.WriteLine("Sistema desconectado de la base");
            }
        }
        catch (Exception error)
        {
            Console.WriteLine("Error al desconectar de la base: " + error.Message);
        }
    }
}


// ===== 2. La clase Consultas: las consultas a la base =====

class Consultas
{
    public void ConsultaClientes()
    {
        // Un objeto de la clase Conexion: ya sabe cómo conectarse
        Conexion conectar = new Conexion();

        try
        {
            // AbrirConexion devuelve la conexión ya abierta (o cerrada, si hubo un error)
            SqlConnection con = conectar.AbrirConexion();

            // Solo se piden los campos necesarios: el ID casi nunca se muestra en las aplicaciones
            string consulta = "SELECT nombre, direccion, telefono FROM clientes";

            // SqlCommand: lleva la consulta y la conexión por la que se envía
            using (SqlCommand comando = new SqlCommand(consulta, con))

            // SqlDataReader: lee las filas del resultado, de una en una
            // ExecuteReader() ejecuta la consulta y devuelve el lector
            using (SqlDataReader lectura = comando.ExecuteReader())
            {
                // Encabezados con formato de tabla: {0,-20} es la columna 0, alineada a la izquierda, en 20 caracteres
                Console.WriteLine();
                Console.WriteLine("{0,-20} | {1,-30} | {2,-15}", "Nombre", "Dirección", "Teléfono");
                Console.WriteLine(new string('-', 70));         // línea de 70 guiones

                // Read() avanza a la siguiente fila y devuelve false cuando ya no quedan
                while (lectura.Read())
                {
                    // Cada campo se pide por su nombre EXACTO en la tabla; ToString() lo pasa a texto
                    string nombre = lectura["nombre"].ToString() ?? "";
                    string direccion = lectura["direccion"].ToString() ?? "";
                    string telefono = lectura["telefono"].ToString() ?? "";

                    Console.WriteLine("{0,-20} | {1,-30} | {2,-15}", nombre, direccion, telefono);
                }
            }
        }
        catch (Exception error)
        {
            Console.WriteLine("Error consultando datos: " + error.Message);
        }
        finally
        {
            // finally se ejecuta SIEMPRE (haya error o no): la conexión se cierra sí o sí
            conectar.CerrarConexion();
        }
    }
}


// ===== 3. El programa principal =====

class Program
{
    static void Main()
    {
        Console.WriteLine("Iniciando conexión a la base...");

        // La consulta abre y cierra la conexión por su cuenta
        Consultas consultar = new Consultas();
        consultar.ConsultaClientes();


        // Espera a que se pulse una tecla antes de cerrar la consola
        Console.ReadKey();
    }
}
