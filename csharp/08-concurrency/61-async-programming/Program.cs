using System.Diagnostics;

// Programación asíncrona (async / await): varias tareas a la vez sin esperar a que cada una termine.

// ===== 1. La clase Asincrona =====

class Asincrona
{
    // Un método asíncrono:
    //   async   marca que el método usa await y puede trabajar de forma asíncrona
    //   Task    es lo que devuelve: "una tarea" que terminará en el futuro (sin valor, como void)
    static async Task Conteo(int tiempo, string mensaje)
    {
        // Task.Delay(ms) es una tarea que se completa pasado ese tiempo SIN BLOQUEAR el hilo
        // (con Thread.Sleep el hilo se quedaría parado). await espera a que la tarea termine,
        // pero mientras tanto el programa puede seguir haciendo otras cosas.
        await Task.Delay(tiempo);

        Console.WriteLine(mensaje);
    }

    // ===== 2. El programa principal =====

    // Un Main que usa await tiene que ser "async Task"
    static async Task Main()
    {
        // ----- A) Las dos tareas a la vez -----

        Console.WriteLine("=== A) Las dos tareas a la vez ===");
        Console.WriteLine("Iniciando conteo...");
        Stopwatch reloj = Stopwatch.StartNew();       // cronómetro para medir el tiempo total

        // Llamar al método NO espera: arranca la tarea y devuelve el objeto Task enseguida.
        // Las dos cuentas empiezan en el mismo momento
        Task proceso1 = Conteo(2000, "Han pasado 2 segundos");
        Task proceso2 = Conteo(5000, "Han pasado 5 segundos");

        // Task.WhenAll recibe las tareas (podrían ser 50) y devuelve una tarea nueva que se completa
        // cuando TODAS han terminado. Con await se espera a que eso ocurra
        await Task.WhenAll(proceso1, proceso2);

        // El segundo mensaje sale 3 segundos después del primero (no 5): las tareas corrían a la vez
        Console.WriteLine("Tiempo total: " + Math.Round(reloj.Elapsed.TotalSeconds) + " s");   // 5 s


        // ----- B) Una tarea detrás de otra (con await en cada una) -----

        Console.WriteLine();
        Console.WriteLine("=== B) Una tarea detrás de otra ===");
        reloj.Restart();

        // Aquí cada await espera a que su tarea termine ANTES de arrancar la siguiente
        await Conteo(2000, "Han pasado 2 segundos");
        await Conteo(5000, "Han pasado 5 segundos");

        Console.WriteLine("Tiempo total: " + Math.Round(reloj.Elapsed.TotalSeconds) + " s");   // 2 + 5 = 7 s


        // Espera a que se pulse una tecla antes de cerrar la consola
        Console.ReadKey();
    }
}
