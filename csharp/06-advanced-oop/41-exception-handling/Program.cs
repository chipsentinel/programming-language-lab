// Una excepción es un error que ocurre MIENTRAS el programa se ejecuta (dividir entre cero,
// convertir un texto que no es un número, usar una posición que no existe...).
// Si no se controla, interrumpe el programa. Con try / catch se captura y se decide qué hacer.
//
// try      { código que puede fallar }
// catch    { qué hacer si falla }
// finally  { código que se ejecuta SIEMPRE, haya error o no }


class ManejoExcepciones
{
    // ===== 1. try / catch con una excepción concreta =====

    public void Dividir(int valor1, int valor2)
    {
        try
        {
            int resultado = valor1 / valor2;      // con valor2 = 0 lanza DivideByZeroException
            Console.WriteLine("Resultado: " + resultado);
        }
        catch (DivideByZeroException error)
        {
            Console.WriteLine("Error: no se puede dividir entre cero");
            // error contiene los detalles: tipo, mensaje y pila de llamadas
            Console.WriteLine("[técnico] " + error.GetType().Name + ": " + error.Message);
        }
    }


    // ===== 2. Error de formato =====

    public void Formato(string texto)
    {
        try
        {
            int numero = int.Parse(texto);        // con un texto que no es un número lanza FormatException
            Console.WriteLine("Número ingresado: " + numero);
        }
        catch (FormatException error)
        {
            Console.WriteLine($"Error: '{texto}' no es un número entero");
            Console.WriteLine("[técnico] " + error.GetType().Name);
        }
    }


    // ===== 3. Posición fuera del array =====

    public void ArrayError()
    {
        int[] numeros = { 10, 20, 30, 40 };       // posiciones válidas: 0 a 3

        try
        {
            Console.WriteLine(string.Join(", ", numeros));
            Console.WriteLine(numeros[5]);        // no existe: lanza IndexOutOfRangeException
        }
        catch (IndexOutOfRangeException error)
        {
            Console.WriteLine("Error: posición fuera del rango del array");
            Console.WriteLine("[técnico] " + error.GetType().Name);
        }
    }


    // ===== 4. Varios catch y finally =====

    public void Procesar(string texto, int divisor)
    {
        try
        {
            int numero = int.Parse(texto);
            Console.WriteLine($"{numero} / {divisor} = {numero / divisor}");
        }
        // Se evalúan en orden: el primero que coincide se ejecuta.
        // Lo más específico va primero y Exception (la general) al final.
        catch (FormatException)
        {
            Console.WriteLine("Error de formato");
        }
        catch (DivideByZeroException)
        {
            Console.WriteLine("Error: división entre cero");
        }
        catch (Exception error)
        {
            Console.WriteLine("Error inesperado: " + error.Message);
        }
        finally
        {
            // Se ejecuta siempre: ideal para cerrar archivos, conexiones...
            Console.WriteLine("Fin de la operación");
        }
    }


    // ===== 5. Lanzar una excepción propia: throw =====

    public void ValidarEdad(int edad)
    {
        if (edad < 0)
        {
            throw new ArgumentException("La edad no puede ser negativa");
        }

        Console.WriteLine("Edad válida: " + edad);
    }
}


class Program
{
    static void Main()
    {
        ManejoExcepciones log = new ManejoExcepciones();

        log.Dividir(20, 10);
        log.Dividir(20, 0);

        log.Formato("8");
        log.Formato("T");

        log.ArrayError();

        log.Procesar("100", 5);
        log.Procesar("abc", 5);
        log.Procesar("100", 0);

        try
        {
            log.ValidarEdad(30);
            log.ValidarEdad(-5);
        }
        catch (ArgumentException error)
        {
            Console.WriteLine("Error: " + error.Message);
        }

        // Una excepción sin controlar detiene el programa:
        // int cero = 0;
        // Console.WriteLine(10 / cero);   // Unhandled exception. System.DivideByZeroException


        // Espera a que se pulse una tecla antes de cerrar la consola
        Console.ReadKey();
    }
}
