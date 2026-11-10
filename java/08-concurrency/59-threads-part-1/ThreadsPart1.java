// Hilos (threads), primera parte: crear un hilo, iniciarlo y esperar a que termine.
// Para ejecutarlo:  java ThreadsPart1.java

public class ThreadsPart1 {

    // ===== 5. El programa principal =====

    public static void main(String[] args) {
        Hilos ejecuta = new Hilos();
        ejecuta.conteo();
    }
}


// ===== 1. La clase Hilos =====

class Hilos {

    // conteo: el método que crea, inicia y espera al hilo
    public void conteo() {
        // ===== 2. Crear un hilo =====

        // Thread es la clase de los hilos. Al crearlo se le pasa el método que va a ejecutar
        // (Hilos::ejecucionHilo es una referencia al método: no se llama, se le entrega)
        Thread hilo1 = new Thread(Hilos::ejecucionHilo);

        // ===== 3. Iniciar el hilo =====

        // start() arranca el hilo: a partir de aquí ejecucionHilo corre en paralelo al resto del programa
        hilo1.start();
        System.out.println("Hilo iniciado");
        System.out.println("(esta línea sale antes que 'Pasaron 3 segundos': el programa principal no espera al hilo)");

        // ===== 4. Esperar al hilo: join =====

        // join() detiene el programa principal hasta que el hilo termine.
        // Sin esta línea, "Fin del programa" saldría enseguida, sin esperar los 3 segundos del hilo.
        // Puede lanzar InterruptedException (si alguien interrumpe la espera): hay que capturarla
        try {
            hilo1.join();
        } catch (InterruptedException e) {
            System.out.println("Espera interrumpida");
        }

        System.out.println("Fin del programa");
    }

    // El método que ejecuta el hilo
    static void ejecucionHilo() {
        // Thread.sleep(milisegundos): suspende el hilo actual ("lo duerme") ese tiempo. 3000 ms = 3 segundos
        // También lanza InterruptedException
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            System.out.println("Hilo interrumpido");
            return;
        }

        System.out.println("Pasaron 3 segundos");
    }
}
