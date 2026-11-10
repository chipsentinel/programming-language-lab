// Hilos (threads), segunda parte: dos hilos con tareas distintas.
// Se simula analizar un programa (buscar amenazas) antes de instalarlo.
// Para ejecutarlo:  java ThreadsPart2.java

public class ThreadsPart2 {

    // ===== 2. El programa principal =====

    public static void main(String[] args) throws InterruptedException {
        // ----- A) Un hilo detrás de otro (como en el vídeo): primero el análisis y después la instalación -----

        System.out.println("=== A) Un hilo detrás de otro ===");
        long inicio = System.nanoTime();       // para medir el tiempo total

        Instalador ejecucion = new Instalador();

        ejecucion.hiloAnalisis.start();        // arranca el hilo de análisis
        ejecucion.hiloAnalisis.join();         // el programa ESPERA a que termine (5 s) antes de seguir

        ejecucion.hiloInstalador.start();      // solo entonces arranca el de instalación
        ejecucion.hiloInstalador.join();       // y se espera a que termine (7 s)

        System.out.println("Tiempo total: " + Math.round((System.nanoTime() - inicio) / 1e9) + " s");   // 5 + 7 = 12 s


        // ----- B) Los dos hilos A LA VEZ: se arrancan los dos y después se espera a los dos -----

        System.out.println();
        System.out.println("=== B) Los dos hilos a la vez ===");
        inicio = System.nanoTime();

        // Un hilo solo se puede iniciar UNA vez: hace falta un objeto nuevo con hilos nuevos
        Instalador ejecucionParalela = new Instalador();

        ejecucionParalela.hiloAnalisis.start();
        ejecucionParalela.hiloInstalador.start();      // arranca sin esperar a que acabe el análisis

        ejecucionParalela.hiloAnalisis.join();
        ejecucionParalela.hiloInstalador.join();

        System.out.println("Tiempo total: " + Math.round((System.nanoTime() - inicio) / 1e9) + " s");   // solo 7 s: el más largo
    }
}


// ===== 1. La clase Instalador: dos hilos, cada uno con su método =====

class Instalador {

    // Dos hilos, cada uno con el método que va a ejecutar (referencia al método, sin paréntesis)
    public Thread hiloAnalisis = new Thread(Instalador::analizarArchivo);
    public Thread hiloInstalador = new Thread(Instalador::instalacion);

    // Lo que hace el hilo de análisis: tarda 5 segundos
    static void analizarArchivo() {
        System.out.println("Analizando archivo...");

        // sleep indica cuánto TARDA esta tarea (aquí, 5 segundos). Es el tiempo que el sistema
        // espera antes de pasar a la siguiente acción. NO es lo que hace esperar al programa principal:
        // eso lo hace join, más arriba.
        dormir(5000);

        System.out.println("Archivo analizado, no hay amenazas");
    }

    // Lo que hace el hilo de instalación: tarda 7 segundos
    static void instalacion() {
        System.out.println("Instalando programa...");

        dormir(7000);

        System.out.println("Programa instalado correctamente");
    }

    // Thread.sleep lanza InterruptedException (comprobada): se captura en un único sitio
    static void dormir(long milisegundos) {
        try {
            Thread.sleep(milisegundos);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();    // se vuelve a marcar el hilo como interrumpido
        }
    }
}
