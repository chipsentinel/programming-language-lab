// Depurar (debug) es el proceso de encontrar, analizar y corregir errores en un programa.
// Se hace con el DEPURADOR del editor: se ejecuta el programa paso a paso, se detiene en puntos
// concretos (puntos de interrupción) y se examina el valor de las variables.
//
// Este archivo es un programa PARA DEPURAR: pon puntos de interrupción (F9) en las líneas marcadas
// con "// <-- punto de interrupción" y ejecútalo con F5 (ver el .md).
//
// Tipos de errores:
//   - De sintaxis: el código está mal escrito. Lo detecta el compilador y no deja ejecutar.
//   - En tiempo de ejecución: ocurren al ejecutar (excepciones, lección 41).
//   - Lógicos: el programa funciona, pero NO hace lo que se quería. Son los más difíciles de encontrar.

using System.Diagnostics;

class Program
{
    // ===== 1. Un error lógico =====

    // El programa funciona sin errores, pero uno de los resultados está mal.
    static void Operaciones()
    {
        int a = 30;
        int b = 20;

        int suma = a + b;                    // <-- punto de interrupción
        int resta = a - b;
        int multiplicacion = a * b;

        Console.WriteLine("Suma: " + suma);
        Console.WriteLine("Resta: " + resta);

        // Versión con el error lógico: se muestra la variable equivocada.
        // Console.WriteLine("Multiplicación: " + suma);          // muestra 50 en lugar de 600
        Console.WriteLine("Multiplicación: " + multiplicacion);   // corregido: 600
    }


    // ===== 2. Un error clásico: uno de menos =====

    // Debería sumar de 1 a n, pero el bucle termina antes: < n en lugar de <= n
    static int SumaHastaConError(int n)
    {
        int total = 0;

        for (int i = 1; i < n; i++)          // <-- punto de interrupción: ¿cuánto vale i en la última vuelta?
        {
            total += i;
        }

        return total;
    }

    static int SumaHasta(int n)
    {
        int total = 0;

        for (int i = 1; i <= n; i++)         // corregido: <=
        {
            total += i;
        }

        return total;
    }


    // ===== 3. Depurar con trazas: mostrar valores mientras corre =====

    static int SumaHastaConTraza(int n)
    {
        int total = 0;

        for (int i = 1; i <= n; i++)
        {
            total += i;
            Console.WriteLine($"  i={i}, total={total}");   // la "traza": ver cómo evolucionan las variables
        }

        return total;
    }


    static void Main()
    {
        Operaciones();

        Console.WriteLine($"SumaHastaConError(5) = {SumaHastaConError(5)} (esperado 15)");
        Console.WriteLine($"SumaHasta(5) = {SumaHasta(5)}");

        Console.WriteLine("Traza de SumaHastaConTraza(3):");
        int resultado = SumaHastaConTraza(3);
        Console.WriteLine("Resultado: " + resultado);


        // ===== 4. Herramientas de C# para depurar =====

        // Debug.WriteLine escribe en la ventana "Salida" del depurador, NO en la consola.
        // Solo existe en compilaciones Debug: en Release se elimina.
        Debug.WriteLine("Esto solo se ve en la ventana de salida del depurador");

        // Debug.Assert comprueba una condición que SIEMPRE debería cumplirse; si no, avisa en Debug
        Debug.Assert(resultado == 6, "El resultado debería ser 6");

        // Debugger.IsAttached indica si hay un depurador conectado
        Console.WriteLine("¿Hay un depurador conectado? " + Debugger.IsAttached);

        // Debugger.Break() detiene la ejecución como si fuera un punto de interrupción (solo con depurador)
        // Debugger.Break();


        // Espera a que se pulse una tecla antes de cerrar la consola
        Console.ReadKey();
    }
}
