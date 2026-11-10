using System.Data;
using MySql.Data.MySqlClient;

// Conexión a una base de datos MySQL con ADO.NET.
// Antes de ejecutar: instalar MySQL, ejecutar colegio_amanecer.sql y, si la clave de root no es abc123,
// definir la variable de entorno MYSQL_PASSWORD (ver el .md).

// ===== 1. La clase Conexion: guarda los parámetros y abre la conexión =====

class Conexion
{
    // MySqlConnection representa la conexión activa con la base de datos
    public MySqlConnection conectar;

    // La cadena de conexión: toda la información necesaria para llegar a la base
    private string cadenaConexion;

    // Constructor: prepara la cadena de conexión y el objeto MySqlConnection (todavía sin conectar)
    public Conexion()
    {
        // La clave no se escribe en el código: se lee de una variable de entorno (por defecto, abc123 como en el vídeo)
        string password = Environment.GetEnvironmentVariable("MYSQL_PASSWORD") ?? "abc123";

        cadenaConexion = "server=localhost;" +          // servidor: el propio equipo
                         "port=3306;" +                 // puerto por defecto de MySQL
                         "database=colegio_amanecer;" + // nombre de la base de datos
                         "user=root;" +                 // usuario
                         "password=" + password + ";";  // contraseña

        conectar = new MySqlConnection(cadenaConexion);
    }

    // Abre la conexión y devuelve el objeto de conexión (abierto, o cerrado si hubo un error)
    public MySqlConnection AbrirConexion()
    {
        try
        {
            // Solo se abre si está cerrada
            if (conectar.State == ConnectionState.Closed)
            {
                conectar.Open();
                Console.WriteLine("Conexión abierta");
            }
        }
        catch (Exception e)
        {
            Console.WriteLine("Error al abrir la conexión a la base: " + e.Message);
        }

        return conectar;
    }

    // Cierra la conexión: siempre hay que cerrarla para liberar recursos
    public void CerrarConexion()
    {
        if (conectar.State == ConnectionState.Open)
        {
            conectar.Close();
            Console.WriteLine("Conexión cerrada");
        }
    }
}


// ===== 2. El programa principal =====

class Program
{
    static void Main()
    {
        Console.WriteLine("Iniciando conexión...");

        Conexion conectarBD = new Conexion();

        // Abrir la conexión (tiene que ser un paso aparte de crear el objeto)
        MySqlConnection conectar = conectarBD.AbrirConexion();

        if (conectar != null && conectar.State == ConnectionState.Open)
        {
            Console.WriteLine("CONEXIÓN EXITOSA");
        }
        else
        {
            Console.WriteLine("ERROR CONECTÁNDOSE A LA BASE");
        }

        // Al terminar, cerrar la conexión
        conectarBD.CerrarConexion();


        // Espera a que se pulse una tecla antes de cerrar la consola
        Console.ReadKey();
    }
}
