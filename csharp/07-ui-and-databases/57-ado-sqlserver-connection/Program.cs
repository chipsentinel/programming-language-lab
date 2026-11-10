using System.Data;
using Microsoft.Data.SqlClient;

// Conexión a una base de datos SQL Server con ADO.NET.
// Antes de ejecutar: tener SQL Server en marcha y la base "farmacia" creada (script farmacia.sql, en esta carpeta).
// Por defecto se conecta a "localhost" con la autenticación de Windows (como en el vídeo). Variables de entorno opcionales:
//   SQLSERVER_SERVER    el servidor (por ejemplo localhost\SQLEXPRESS)
//   SQLSERVER_USER      si se define, se usa autenticación de SQL Server con este usuario...
//   SQLSERVER_PASSWORD  ...y esta contraseña

// ===== 1. La clase Conexion: guarda los parámetros y abre / cierra la conexión =====

class Conexion
{
    // La cadena de conexión: toda la información necesaria para llegar a la base
    private readonly string cadenaConexion;     // readonly: solo se asigna en el constructor

    // SqlConnection representa la conexión activa con la base de datos
    private SqlConnection conectar;

    // Constructor: prepara la cadena de conexión y el objeto SqlConnection (todavía sin conectar)
    public Conexion()
    {
        string servidor = Environment.GetEnvironmentVariable("SQLSERVER_SERVER") ?? "localhost";
        string? usuario = Environment.GetEnvironmentVariable("SQLSERVER_USER");

        // Sin usuario ni clave: autenticación de Windows (Trusted_Connection=True, el usuario de Windows entra solo).
        // Con usuario y clave: User Id=...;Password=...
        string seguridad = (usuario == null)
            ? "Trusted_Connection=True;"
            : "User Id=" + usuario + ";Password=" + Environment.GetEnvironmentVariable("SQLSERVER_PASSWORD") + ";";

        cadenaConexion = "Server=" + servidor + ";" +      // el servidor: localhost, el propio equipo
                         "Database=farmacia;" +            // nombre de la base de datos
                         seguridad +
                         "TrustServerCertificate=True;";   // confiar en el certificado del servidor (si no, falla en local)

        conectar = new SqlConnection(cadenaConexion);
    }

    // Abre la conexión y devuelve el objeto de conexión
    public SqlConnection AbrirConexion()
    {
        try
        {
            // Solo se abre si está cerrada
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

    // Cierra la conexión
    public void CerrarConexion()
    {
        try
        {
            // Solo si está abierta
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


// ===== 2. El programa principal =====

class Program
{
    static void Main()
    {
        Console.WriteLine("Iniciando conexión a la base...");

        Conexion conexion = new Conexion();

        conexion.AbrirConexion();
        conexion.CerrarConexion();


        // Espera a que se pulse una tecla antes de cerrar la consola
        Console.ReadKey();
    }
}
