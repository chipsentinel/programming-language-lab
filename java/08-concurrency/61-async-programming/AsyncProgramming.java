import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

// Programación asíncrona (CompletableFuture): varias tareas a la vez sin esperar a que cada una termine.
// Es el equivalente de async / await con Task de C#.
// Para ejecutarlo:  java AsyncProgramming.java

public class AsyncProgramming {

    // ===== 1. Un método asíncrono =====

    // CompletableFuture<Void> es lo que devuelve: "una tarea" que terminará en el futuro (sin valor).
    // Equivale al Task de C#.
    static CompletableFuture<Void> conteo(int tiempo, String mensaje) {
        // delayedExecutor(ms) es un ejecutor que lanza la tarea pasado ese tiempo SIN BLOQUEAR ningún hilo
        // mientras espera (con Thread.sleep el hilo se quedaría parado). Equivale a Task.Delay de C#.
        // runAsync ejecuta el mensaje cuando llega el momento y devuelve la tarea
        return CompletableFuture.runAsync(
                () -> System.out.println(mensaje),
                CompletableFuture.delayedExecutor(tiempo, TimeUnit.MILLISECONDS));
    }

    // ===== 2. El programa principal =====

    public static void main(String[] args) {
        // ----- A) Las dos tareas a la vez -----

        System.out.println("=== A) Las dos tareas a la vez ===");
        System.out.println("Iniciando conteo...");
        long inicio = System.nanoTime();      // para medir el tiempo total

        // Llamar al método NO espera: arranca la tarea y devuelve el objeto enseguida.
        // Las dos cuentas empiezan en el mismo momento
        CompletableFuture<Void> proceso1 = conteo(2000, "Han pasado 2 segundos");
        CompletableFuture<Void> proceso2 = conteo(5000, "Han pasado 5 segundos");

        // allOf recibe las tareas (podrían ser 50) y devuelve una tarea nueva que se completa
        // cuando TODAS han terminado. join() espera a que eso ocurra (equivale al await de C#)
        CompletableFuture.allOf(proceso1, proceso2).join();

        // El segundo mensaje sale 3 segundos después del primero (no 5): las tareas corrían a la vez
        System.out.println("Tiempo total: " + Math.round((System.nanoTime() - inicio) / 1e9) + " s");   // 5 s


        // ----- B) Una tarea detrás de otra (con join en cada una) -----

        System.out.println();
        System.out.println("=== B) Una tarea detrás de otra ===");
        inicio = System.nanoTime();

        // Aquí cada join espera a que su tarea termine ANTES de arrancar la siguiente
        conteo(2000, "Han pasado 2 segundos").join();
        conteo(5000, "Han pasado 5 segundos").join();

        System.out.println("Tiempo total: " + Math.round((System.nanoTime() - inicio) / 1e9) + " s");   // 2 + 5 = 7 s
    }
}
