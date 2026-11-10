using System.Data;
using Oracle.ManagedDataAccess.Client;

// Consultas (SELECT) a una base de datos Oracle con ADO.NET.
// Requiere el usuario LIBRERIA y sus tablas (script libreria.sql, en esta carpeta).
// Si la clave de libreria no es "oracle", definir la variable de entorno ORACLE_PASSWORD;
// en Oracle 18c/21c XE, definir ORACLE_SERVICE=XEPDB1.

// ===== 1. La clase Conexion (la de la lección 55, con otro usuario) =====

class Conexion
{
    private string cadenaConexion;

    // public (antes private): ahora la usa otra clase, Consultas
    public OracleConnection conexionOracle;

    public Conexion()
    {
        string password = Environment.GetEnvironmentVariable("ORACLE_PASSWORD") ?? "oracle";
        string servicio = Environment.GetEnvironmentVariable("ORACLE_SERVICE") ?? "XE";

        // El usuario es LIBRERIA (el esquema donde están las tablas), no SYSTEM:
        // con SYSTEM la consulta falla con "ORA-00942: la tabla o vista no existe", porque las tablas no están en su esquema
        cadenaConexion = "User Id=libreria;" +
                         "Password=" + password + ";" +
                         "Data Source=localhost:1521/" + servicio + ";";

        conexionOracle = new OracleConnection(cadenaConexion);
    }

    public void AbrirConexion()
    {
        try
        {
            conexionOracle.Open();
            Console.WriteLine("Sistema conectado a Oracle Database");
        }
        catch (Exception e)
        {
            Console.WriteLine("Error conectando a la base: " + e.Message);
        }
    }

    public void CerrarConexion()
    {
        try
        {
            if (conexionOracle.State == ConnectionState.Open)
            {
                conexionOracle.Close();
                Console.WriteLine("Sistema desconectado de la base");
            }
        }
        catch (Exception e)
        {
            Console.WriteLine("Error al desconectar de la base: " + e.Message);
        }
    }
}


// ===== 2. La clase Consultas: las consultas a la base =====

class Consultas
{
    public void ConsultaLibros()
    {
        // Un objeto de la clase Conexion: ya sabe cómo conectarse
        Conexion conectar = new Conexion();

        try
        {
            conectar.AbrirConexion();

            string consulta = "SELECT * FROM libros";

            // OracleCommand: lleva la consulta y la conexión por la que se envía
            using (OracleCommand comando = new OracleCommand(consulta, conectar.conexionOracle))

            // OracleDataReader: lee las filas del resultado, de una en una
            // ExecuteReader() ejecuta la consulta y devuelve el lector
            using (OracleDataReader lectura = comando.ExecuteReader())
            {
                Console.WriteLine();
                Console.WriteLine("{0,-4}{1,-50}{2,-7}{3,-12}{4}", "ID", "Título", "Autor", "Fecha", "Género");
                Console.WriteLine(new string('-', 80));

                // Read() avanza a la siguiente fila y devuelve false cuando ya no quedan
                while (lectura.Read())
                {
                    // Los campos se piden por su POSICIÓN en la tabla, empezando en 0:
                    // 0 id_libro, 1 titulo, 2 id_autor, 3 fecha_publicacion, 4 genero
                    // Y con el método que corresponde al TIPO: GetInt32, GetString, GetDateTime
                    int id = lectura.GetInt32(0);
                    string titulo = lectura.GetString(1);
                    int autor = lectura.GetInt32(2);
                    DateTime fecha = lectura.GetDateTime(3);
                    string genero = lectura.GetString(4);

                    // fecha.ToString("yyyy-MM-dd"): la fecha formateada (año-mes-día)
                    Console.WriteLine("{0,-4}{1,-50}{2,-7}{3,-12}{4}", id, titulo, autor, fecha.ToString("yyyy-MM-dd"), genero);
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
        consultar.ConsultaLibros();


        // Espera a que se pulse una tecla antes de cerrar la consola
        Console.ReadKey();
    }
}
