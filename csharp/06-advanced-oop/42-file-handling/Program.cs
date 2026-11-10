// Manejo de archivos: crear, leer, modificar, copiar y borrar archivos del sistema.
// La clase File y la clase Directory están en el espacio de nombres System.IO (ya incluido en el proyecto).
//
// Para no ensuciar el equipo, esta lección trabaja dentro de una carpeta temporal
// que se crea al empezar y se borra al terminar.

class Program
{
    static void Main()
    {
        // ===== 0. La ruta del archivo =====

        // Una ruta puede ser relativa (archivo1.txt: junto al programa) o absoluta (C:\Carpeta\archivo1.txt).
        // Path.Combine une las partes con el separador correcto del sistema operativo.
        string carpeta = Path.Combine(Path.GetTempPath(), "leccion42-csharp");
        Directory.CreateDirectory(carpeta);

        string ruta = Path.Combine(carpeta, "archivo1.txt");

        // En una ruta escrita a mano, la barra invertida \ es especial en los textos:
        // se escribe doble (\\) o se antepone @ para que sea literal.
        //   string rutaManual = "C:\\Datos\\archivo1.txt";
        //   string rutaManual = @"C:\Datos\archivo1.txt";


        // ===== 1. Crear un archivo y escribir en él: WriteAllText =====

        // Si el archivo no existe lo crea; si existe, LO SOBRESCRIBE
        File.WriteAllText(ruta, "Hola a todos");
        Console.WriteLine("Archivo creado");


        // ===== 2. Leer todo el contenido: ReadAllText =====

        string contenido = File.ReadAllText(ruta);
        Console.WriteLine("Contenido: " + contenido);


        // ===== 3. Añadir texto al final: AppendAllText =====

        File.AppendAllText(ruta, "\nEsta es una nueva línea de texto");

        Console.WriteLine("Tras añadir una línea:");
        Console.WriteLine(File.ReadAllText(ruta));


        // ===== 4. Leer línea a línea: ReadAllLines =====

        string[] lineas = File.ReadAllLines(ruta);

        Console.WriteLine("Número de líneas: " + lineas.Length);
        for (int i = 0; i < lineas.Length; i++)
        {
            Console.WriteLine($"Línea {i + 1}: {lineas[i]}");
        }


        // ===== 5. Escribir varias líneas: WriteAllLines =====

        File.WriteAllLines(ruta, new string[] { "primera", "segunda", "tercera" });
        Console.WriteLine("Reescrito con 3 líneas");
        Console.WriteLine("Número de líneas: " + File.ReadAllLines(ruta).Length);


        // ===== 6. Comprobar si existe y copiar =====

        Console.WriteLine("¿Existe el archivo? " + File.Exists(ruta));

        string copia = Path.Combine(carpeta, "copia.txt");
        File.Copy(ruta, copia, true);   // true: sobrescribe si la copia ya existe
        Console.WriteLine("Copia creada: contenido igual = " + (File.ReadAllText(ruta) == File.ReadAllText(copia)));


        // ===== 7. Borrar un archivo: File.Delete =====

        if (File.Exists(ruta))
        {
            try
            {
                File.Delete(ruta);
                Console.WriteLine("Archivo borrado correctamente");
            }
            catch (IOException error)
            {
                Console.WriteLine("No se pudo borrar: " + error.Message);
            }
        }
        else
        {
            Console.WriteLine("El archivo no existe");
        }

        Console.WriteLine("¿Existe el archivo? " + File.Exists(ruta));

        // File.Delete NO da error si el archivo no existe (por eso conviene comprobarlo con File.Exists)
        File.Delete(ruta);


        // ===== 8. Leer un archivo que no existe: excepción =====

        try
        {
            File.ReadAllText(ruta);
        }
        catch (FileNotFoundException)
        {
            Console.WriteLine("Error: el archivo no existe");
        }


        // ===== 9. Cerrar los recursos con using: StreamWriter y StreamReader =====

        // using cierra el archivo al terminar el bloque, haya error o no (equivale a un finally)
        using (StreamWriter escritor = new StreamWriter(ruta))
        {
            escritor.WriteLine("línea escrita con StreamWriter");
            escritor.WriteLine("segunda línea");
        }

        using (StreamReader lector = new StreamReader(ruta))
        {
            string? linea;
            while ((linea = lector.ReadLine()) != null)
            {
                Console.WriteLine("Leída: " + linea);
            }
        }


        // ===== Limpieza: se borra la carpeta temporal con todo su contenido =====

        Directory.Delete(carpeta, true);
        Console.WriteLine("¿Existe la carpeta? " + Directory.Exists(carpeta));


        // Espera a que se pulse una tecla antes de cerrar la consola
        Console.ReadKey();
    }
}
