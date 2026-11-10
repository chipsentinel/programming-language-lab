using System.Data;
using MySql.Data.MySqlClient;

// Consultas (SELECT) a una base de datos MySQL con ADO.NET.
// Requiere la base colegio_amanecer de la lección 53 (script colegio_amanecer.sql en esa carpeta).
// Si la clave de root no es abc123, definir la variable de entorno MYSQL_PASSWORD.

// ===== 1. La clase Conexion (la de la lección 53, con el método para cerrar) =====

class Conexion
{
    public MySqlConnection conectar;
    private string cadenaConexion;

    public Conexion()
    {
        string password = Environment.GetEnvironmentVariable("MYSQL_PASSWORD") ?? "abc123";

        cadenaConexion = "server=localhost;" +
                         "port=3306;" +
                         "database=colegio_amanecer;" +
                         "user=root;" +
                         "password=" + password + ";";

        conectar = new MySqlConnection(cadenaConexion);
    }

    // Abre la conexión (el propio método comprueba el estado, así que no hace falta comprobarlo fuera)
    public MySqlConnection AbrirConexion()
    {
        try
        {
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

    // Cierra la conexión si está abierta: liberar recursos (memoria, procesador, conexiones del servidor)
    public void CerrarConexion()
    {
        if (conectar.State == ConnectionState.Open)
        {
            conectar.Close();
            Console.WriteLine("Conexión cerrada");
        }
    }
}


// ===== 2. La clase Consulta: las consultas a la base =====

class Consulta
{
    // Consulta 1: todos los campos de la tabla alumnos
    public void ConsultaAlumnos()
    {
        // Se crea un objeto de la clase Conexion: es la que sabe cómo conectarse
        Conexion conexionConsulta = new Conexion();

        // La consulta SQL, en un string
        string consulta = "SELECT * FROM alumnos";

        try
        {
            conexionConsulta.AbrirConexion();

            // MySqlCommand: lleva la consulta y la conexión por la que se envía
            using (MySqlCommand consultar = new MySqlCommand(consulta, conexionConsulta.conectar))

            // MySqlDataReader: lee las filas del resultado, de una en una
            // ExecuteReader() ejecuta la consulta y devuelve el lector
            using (MySqlDataReader datos = consultar.ExecuteReader())
            {
                Console.WriteLine();
                Console.WriteLine("{0,-4}{1,-9}{2,-10}{3,-5}{4,-19}{5,-11}{6,-9}{7}",
                                  "ID", "Nombre", "Apellido", "Edad", "Dirección", "Teléfono", "Grado", "Prof.");

                // Read() avanza a la siguiente fila y devuelve false cuando ya no quedan
                while (datos.Read())
                {
                    // Cada campo se pide por su nombre EXACTO en la tabla.
                    // {valor,-9}: alinea el valor a la izquierda en 9 caracteres (para que se vea como una tabla)
                    Console.WriteLine("{0,-4}{1,-9}{2,-10}{3,-5}{4,-19}{5,-11}{6,-9}{7}",
                                      datos["id_alumno"], datos["nombre"], datos["apellido"], datos["edad"],
                                      datos["direccion"], datos["telefono"], datos["grado"], datos["id_profesor"]);
                }
            }
        }
        catch (Exception e)
        {
            Console.WriteLine("Error consultando datos: " + e.Message);
        }
        finally
        {
            // finally se ejecuta SIEMPRE (haya error o no): la conexión se cierra sí o sí
            conexionConsulta.CerrarConexion();
        }
    }

    // Consulta 2: solo el nombre y el grado (se piden solo los campos necesarios)
    public void ConsultaNombreGrado()
    {
        Conexion conexionConsulta = new Conexion();
        string consulta = "SELECT nombre, grado FROM alumnos";

        try
        {
            conexionConsulta.AbrirConexion();

            using (MySqlCommand consultar = new MySqlCommand(consulta, conexionConsulta.conectar))
            using (MySqlDataReader datos = consultar.ExecuteReader())
            {
                Console.WriteLine();
                Console.WriteLine("{0,-9}{1}", "Nombre", "Grado");

                while (datos.Read())
                {
                    Console.WriteLine("{0,-9}{1}", datos["nombre"], datos["grado"]);
                }
            }
        }
        catch (Exception e)
        {
            Console.WriteLine("Error consultando datos: " + e.Message);
        }
        finally
        {
            conexionConsulta.CerrarConexion();
        }
    }
}


// ===== 3. El programa principal =====

class Program
{
    static void Main()
    {
        Console.WriteLine("Iniciando conexión...");

        // La conexión se abre dentro de cada consulta: aquí solo se invocan
        Consulta consulta = new Consulta();
        consulta.ConsultaAlumnos();
        consulta.ConsultaNombreGrado();


        // Espera a que se pulse una tecla antes de cerrar la consola
        Console.ReadKey();
    }
}
