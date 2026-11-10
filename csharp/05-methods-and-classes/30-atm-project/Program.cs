// Proyecto: cajero automático.
// Pone en práctica lo visto hasta ahora: clases, atributos, métodos con y sin retorno,
// entrada de datos, conversión de tipos, if/else, switch y bucles do-while.


class CajeroAutomatico
{
    // Atributos del cajero
    private double saldo = 10000;           // saldo inicial de la cuenta
    private int operaciones = 0;            // operaciones realizadas en esta sesión
    private const int LimiteOperaciones = 3;


    // ===== Menú principal =====

    public void Menu()
    {
        string opcion;

        // do-while: el menú se muestra al menos una vez y se repite hasta elegir "4"
        do
        {
            LimpiarPantalla();

            Console.WriteLine(@"==== SELECCIONE LA OPERACIÓN ====
1. Consultar saldo
2. Depositar
3. Retirar
4. Salir
=================================");
            Console.WriteLine("Seleccione una opción:");

            // ReadLine devuelve texto, y la opción se compara con textos ("1", "2"...)
            opcion = Console.ReadLine() ?? "";

            switch (opcion)
            {
                case "1":
                    ConsultarSaldo();
                    break;
                case "2":
                    Depositar();
                    break;
                case "3":
                    Retirar();
                    break;
                case "4":
                    Salir();
                    break;
                default:
                    Console.WriteLine("OPCIÓN NO VÁLIDA. Presione Enter para continuar...");
                    Console.ReadLine();
                    break;
            }
        }
        while (opcion != "4");
    }


    // ===== Operaciones =====

    private void ConsultarSaldo()
    {
        LimpiarPantalla();
        Console.WriteLine($"Saldo actual: {saldo:F2}");

        FinalizarOperacion();
    }

    private void Depositar()
    {
        double monto;

        // Se vuelve a pedir el monto mientras no sea válido
        do
        {
            Console.WriteLine("Ingrese el monto a depositar:");
            monto = LeerNumero();

            if (monto <= 0)
            {
                Console.WriteLine("Monto inválido: debe ser mayor que cero. Intente de nuevo.");
            }
        }
        while (monto <= 0);

        saldo += monto;
        Console.WriteLine($"Depósito exitoso. Nuevo saldo: {saldo:F2}");

        FinalizarOperacion();
    }

    private void Retirar()
    {
        double retiro;
        bool valido;

        do
        {
            Console.WriteLine("Ingrese el monto a retirar:");
            retiro = LeerNumero();
            valido = false;

            if (retiro <= 0)
            {
                Console.WriteLine("Monto inválido: debe ser mayor que cero. Intente de nuevo.");
            }
            else if (retiro > saldo)
            {
                Console.WriteLine("Fondos insuficientes. Intente de nuevo.");
            }
            else
            {
                valido = true;
            }
        }
        while (!valido);

        saldo -= retiro;
        Console.WriteLine($"Retiro completo. Nuevo saldo: {saldo:F2}");

        FinalizarOperacion();
    }

    private void Salir()
    {
        Console.WriteLine("==============================");
        Console.WriteLine("Gracias por usar nuestros servicios");
        Console.WriteLine("==============================");

        Environment.Exit(0);   // cierra el programa
    }


    // ===== Métodos auxiliares =====

    // Cuenta la operación y pregunta si se quiere hacer otra
    private void FinalizarOperacion()
    {
        operaciones++;

        // Ejercicio propuesto: el cliente solo puede hacer un número limitado de operaciones
        if (operaciones >= LimiteOperaciones)
        {
            Console.WriteLine("LÍMITE DE OPERACIONES PERMITIDAS ALCANZADO");
            Salir();
        }

        Console.WriteLine("¿Desea realizar otra operación? (1 = Sí / 2 = No)");
        string respuesta = Console.ReadLine() ?? "";

        if (respuesta != "1")
        {
            Salir();
        }
        // Si responde 1, el método termina y el menú vuelve a mostrarse
    }

    // Convierte lo que escribe el usuario en número; si no es un número, devuelve 0 (monto inválido)
    private double LeerNumero()
    {
        if (double.TryParse(Console.ReadLine(), out double numero))
        {
            return numero;
        }

        return 0;
    }

    private void LimpiarPantalla()
    {
        // Console.Clear falla si la salida está redirigida a un archivo
        if (!Console.IsOutputRedirected)
        {
            Console.Clear();
        }
    }
}


class Program
{
    static void Main()
    {
        CajeroAutomatico cajero = new CajeroAutomatico();
        cajero.Menu();
    }
}
