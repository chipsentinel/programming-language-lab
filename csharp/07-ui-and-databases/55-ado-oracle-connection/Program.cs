using System.Data;
using Oracle.ManagedDataAccess.Client;

// Conexión a una base de datos Oracle con ADO.NET.
// Antes de ejecutar: tener Oracle (por ejemplo Oracle Database Express Edition) en marcha.
// Si la clave de system no es "oracle", definir la variable de entorno ORACLE_PASSWORD (ver el .md);
// en Oracle 18c/21c XE, definir ORACLE_SERVICE=XEPDB1.

// ===== 1. La clase Conexion: guarda los parámetros y abre / cierra la conexión =====

class Conexion
{
    // La cadena de conexión: toda la información necesaria para llegar a la base
    private string cadenaConexion;

    // OracleConnection representa la conexión activa con la base de datos
    private OracleConnection conexionOracle;

    // Constructor: prepara la cadena de conexión y el objeto OracleConnection (todavía sin conectar)
    public Conexion()
    {
        // La clave no se escribe en el código: se lee de una variable de entorno (por defecto, oracle como en el vídeo)
        string password = Environment.GetEnvironmentVariable("ORACLE_PASSWORD") ?? "oracle";

        // El servicio: XE en Oracle 11g XE (como en el vídeo). En 18c/21c XE, la base "enchufable" es XEPDB1
        string servicio = Environment.GetEnvironmentVariable("ORACLE_SERVICE") ?? "XE";

        cadenaConexion = "User Id=system;" +                      // usuario
                         "Password=" + password + ";" +           // contraseña
                         "Data Source=localhost:1521/" + servicio + ";";   // servidor:puerto/servicio

        conexionOracle = new OracleConnection(cadenaConexion);
    }

    // Abre la conexión
    public void AbrirConexion()
    {
        try
        {
            conexionOracle.Open();      // aquí es donde realmente se conecta
            Console.WriteLine("Sistema conectado a Oracle Database");
        }
        catch (Exception e)
        {
            Console.WriteLine("Error conectando a la base: " + e.Message);
        }
    }

    // Cierra la conexión
    public void CerrarConexion()
    {
        try
        {
            // Solo si está abierta (si no se pudo conectar, no hay nada que cerrar)
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


// ===== 2. El programa principal =====

class Program
{
    static void Main()
    {
        Console.WriteLine("Iniciando conexión a la base...");

        Conexion conectar = new Conexion();

        conectar.AbrirConexion();
        conectar.CerrarConexion();


        // Espera a que se pulse una tecla antes de cerrar la consola
        Console.ReadKey();
    }
}
