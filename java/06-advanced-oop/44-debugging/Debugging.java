// Depurar (debug) es el proceso de encontrar, analizar y corregir errores en un programa.
// Se hace con el DEPURADOR del IDE: se ejecuta el programa paso a paso, se detiene en puntos
// concretos (puntos de interrupción) y se examina el valor de las variables.
//
// Este archivo es un programa PARA DEPURAR: pon puntos de interrupción (clic en el margen izquierdo)
// en las líneas marcadas con "// <-- punto de interrupción" y ejecútalo con "Debug" (ver el .md).
//
// Tipos de errores:
//   - De sintaxis: el código está mal escrito. Lo detecta el compilador y no deja ejecutar.
//   - En tiempo de ejecución: ocurren al ejecutar (excepciones, lección 41).
//   - Lógicos: el programa funciona, pero NO hace lo que se quería. Son los más difíciles de encontrar.

public class Debugging {

    // ===== 1. Un error lógico =====

    // El programa funciona sin errores, pero uno de los resultados está mal.
    static void operaciones() {
        int a = 30;
        int b = 20;

        int suma = a + b;                    // <-- punto de interrupción
        int resta = a - b;
        int multiplicacion = a * b;

        System.out.println("Suma: " + suma);
        System.out.println("Resta: " + resta);

        // Versión con el error lógico: se muestra la variable equivocada.
        // System.out.println("Multiplicación: " + suma);          // muestra 50 en lugar de 600
        System.out.println("Multiplicación: " + multiplicacion);   // corregido: 600
    }


    // ===== 2. Un error clásico: uno de menos =====

    // Debería sumar de 1 a n, pero el bucle termina antes: < n en lugar de <= n
    static int sumaHastaConError(int n) {
        int total = 0;

        for (int i = 1; i < n; i++) {        // <-- punto de interrupción: ¿cuánto vale i en la última vuelta?
            total += i;
        }

        return total;
    }

    static int sumaHasta(int n) {
        int total = 0;

        for (int i = 1; i <= n; i++) {       // corregido: <=
            total += i;
        }

        return total;
    }


    // ===== 3. Depurar con trazas: mostrar valores mientras corre =====

    static int sumaHastaConTraza(int n) {
        int total = 0;

        for (int i = 1; i <= n; i++) {
            total += i;
            System.out.println("  i=" + i + ", total=" + total);   // la "traza": ver cómo evolucionan las variables
        }

        return total;
    }


    public static void main(String[] args) {
        operaciones();

        System.out.println("SumaHastaConError(5) = " + sumaHastaConError(5) + " (esperado 15)");
        System.out.println("SumaHasta(5) = " + sumaHasta(5));

        System.out.println("Traza de SumaHastaConTraza(3):");
        int resultado = sumaHastaConTraza(3);
        System.out.println("Resultado: " + resultado);


        // ===== 4. Herramientas de Java para depurar =====

        // La palabra clave assert comprueba una condición que SIEMPRE debería cumplirse.
        // Está DESACTIVADA por defecto: se activa con la opción -ea (java -ea Debugging.java)
        assert resultado == 6 : "El resultado debería ser 6";

        // System.err escribe en la salida de errores (se ve distinto en el IDE, normalmente en rojo)
        System.err.println("[traza] esto sale por la salida de errores");

        // Java no tiene un equivalente directo a Debugger.Break(): se usan los puntos de interrupción del IDE
    }
}
