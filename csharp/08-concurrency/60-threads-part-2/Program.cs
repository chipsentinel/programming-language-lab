using System.Diagnostics;

// Hilos (threads), segunda parte: dos hilos con tareas distintas.
// Se simula analizar un programa (buscar amenazas) antes de instalarlo.

// ===== 1. La clase Instalador: dos hilos, cada uno con su método =====

class Instalador
{
    // Dos hilos, cada uno con el método que va a ejecutar (solo el nombre, sin paréntesis)
    public Thread hiloAnalisis = new Thread(AnalizarArchivo);
    public Thread hiloInstalador = new Thread(Instalacion);

    // Lo que hace el hilo de análisis: tarda 5 segundos
    static void AnalizarArchivo()
    {
        Console.WriteLine("Analizando archivo...");

        // Sleep indica cuánto TARDA esta tarea (aquí, 5 segundos). Es el tiempo que el sistema
        // espera antes de pasar a la siguiente acción. NO es lo que hace esperar al programa principal:
        // eso lo hace Join, más abajo.
        Thread.Sleep(5000);

        Console.WriteLine("Archivo analizado, no hay amenazas");
    }

    // Lo que hace el hilo de instalación: tarda 7 segundos
    static void Instalacion()
    {
        Console.WriteLine("Instalando programa...");

        Thread.Sleep(7000);

        Console.WriteLine("Programa instalado correctamente");
    }
}


// ===== 2. El programa principal =====

class Program
{
    static void Main()
    {
        // ----- A) Un hilo detrás de otro (como en el vídeo): primero el análisis y después la instalación -----

        Console.WriteLine("=== A) Un hilo detrás de otro ===");
        Stopwatch reloj = Stopwatch.StartNew();       // cronómetro para medir el tiempo total

        Instalador ejecucion = new Instalador();

        ejecucion.hiloAnalisis.Start();     // arranca el hilo de análisis
        ejecucion.hiloAnalisis.Join();      // el programa ESPERA a que termine (5 s) antes de seguir

        ejecucion.hiloInstalador.Start();   // solo entonces arranca el de instalación
        ejecucion.hiloInstalador.Join();    // y se espera a que termine (7 s)

        Console.WriteLine("Tiempo total: " + Math.Round(reloj.Elapsed.TotalSeconds) + " s");   // 5 + 7 = 12 s


        // ----- B) Los dos hilos A LA VEZ: se arrancan los dos y después se espera a los dos -----

        Console.WriteLine();
        Console.WriteLine("=== B) Los dos hilos a la vez ===");
        reloj.Restart();

        // Un hilo solo se puede iniciar UNA vez: hace falta un objeto nuevo con hilos nuevos
        Instalador ejecucionParalela = new Instalador();

        ejecucionParalela.hiloAnalisis.Start();
        ejecucionParalela.hiloInstalador.Start();       // arranca sin esperar a que acabe el análisis

        ejecucionParalela.hiloAnalisis.Join();
        ejecucionParalela.hiloInstalador.Join();

        Console.WriteLine("Tiempo total: " + Math.Round(reloj.Elapsed.TotalSeconds) + " s");   // solo 7 s: el más largo


        // Espera a que se pulse una tecla antes de cerrar la consola
        Console.ReadKey();
    }
}
