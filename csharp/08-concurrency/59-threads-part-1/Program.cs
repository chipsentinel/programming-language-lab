// Hilos (threads), primera parte: crear un hilo, iniciarlo y esperar a que termine.

// ===== 1. La clase Hilos =====

class Hilos
{
    // Conteo: el método que crea, inicia y espera al hilo
    public void Conteo()
    {
        // ===== 2. Crear un hilo =====

        // Thread es la clase de los hilos. Al crearlo se le pasa el método que va a ejecutar
        // (solo el NOMBRE del método, sin paréntesis: no se llama, se le entrega)
        Thread hilo1 = new Thread(EjecucionHilo);

        // ===== 3. Iniciar el hilo =====

        // Start() arranca el hilo: a partir de aquí EjecucionHilo corre en paralelo al resto del programa
        hilo1.Start();
        Console.WriteLine("Hilo iniciado");
        Console.WriteLine("(esta línea sale antes que 'Pasaron 3 segundos': el programa principal no espera al hilo)");

        // ===== 4. Esperar al hilo: Join =====

        // Join() detiene el programa principal hasta que el hilo termine.
        // Sin esta línea, "Fin del programa" saldría enseguida, sin esperar los 3 segundos del hilo
        hilo1.Join();

        Console.WriteLine("Fin del programa");
    }

    // El método que ejecuta el hilo
    static void EjecucionHilo()
    {
        // Thread.Sleep(milisegundos): suspende el hilo actual ("lo duerme") ese tiempo. 3000 ms = 3 segundos
        Thread.Sleep(3000);

        Console.WriteLine("Pasaron 3 segundos");
    }
}


// ===== 5. El programa principal =====

class Program
{
    static void Main()
    {
        Hilos ejecuta = new Hilos();
        ejecuta.Conteo();


        // Espera a que se pulse una tecla antes de cerrar la consola
        Console.ReadKey();
    }
}
