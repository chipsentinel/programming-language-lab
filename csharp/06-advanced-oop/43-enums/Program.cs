// Una enumeración (enum) es un tipo de dato formado por un conjunto FIJO de constantes con nombre.
// Hace el código más legible y seguro: en lugar de usar números o textos "mágicos" (0, 1, "enviado"),
// se usan nombres con significado (EstadoPedido.Enviado) y el compilador impide valores inventados.

// ===== 1. Definir una enumeración =====

// Se declara FUERA de los métodos: a nivel de espacio de nombres (visible para todas las clases)
// o dentro de una clase. No lleva punto y coma al final.
// Los valores se numeran automáticamente desde 0: Pendiente = 0, Procesado = 1, Enviado = 2, Entregado = 3
public enum EstadoPedido
{
    Pendiente,
    Procesado,
    Enviado,
    Entregado
}

// Se pueden dar valores propios a cada constante
public enum Prioridad
{
    Baja = 1,
    Media = 5,
    Alta = 10
}


class Program
{
    static void Main()
    {
        // ===== 2. Usar una enumeración =====

        // Se declara una variable del tipo enum y se le da una de sus constantes: Tipo.Valor
        EstadoPedido status = EstadoPedido.Pendiente;
        Console.WriteLine($"Estado del pedido: {status}");            // Pendiente (muestra el nombre)
        Console.WriteLine($"ID del estado: {(int)status}");           // 0 (casting a int: el número)

        status = EstadoPedido.Enviado;
        Console.WriteLine($"Estado del pedido: {status}");            // Enviado
        Console.WriteLine($"ID del estado: {(int)status}");           // 2

        // Una variable enum no admite valores inventados:
        // status = EstadoPedido.Perdido;   // error: no existe esa constante


        // ===== 3. Comprobar si un valor existe: Enum.IsDefined =====

        Console.WriteLine("¿Existe el valor 3? " + Enum.IsDefined(typeof(EstadoPedido), 3));   // True
        Console.WriteLine("¿Existe el valor 8? " + Enum.IsDefined(typeof(EstadoPedido), 8));   // False


        // ===== 4. Comparar =====

        Console.WriteLine("¿Está enviado? " + (status == EstadoPedido.Enviado));                  // True
        Console.WriteLine("¿Va más allá de pendiente? " + (status > EstadoPedido.Pendiente));    // True: se compara por su número


        // ===== 5. Enum con switch =====

        switch (status)
        {
            case EstadoPedido.Pendiente:
                Console.WriteLine("El pedido está esperando");
                break;
            case EstadoPedido.Procesado:
                Console.WriteLine("El pedido se está preparando");
                break;
            case EstadoPedido.Enviado:
                Console.WriteLine("El pedido va de camino");
                break;
            case EstadoPedido.Entregado:
                Console.WriteLine("El pedido ya llegó");
                break;
        }


        // ===== 6. Recorrer todos los valores: Enum.GetValues =====

        foreach (EstadoPedido estado in Enum.GetValues(typeof(EstadoPedido)))
        {
            Console.WriteLine($"{(int)estado} = {estado}");
        }


        // ===== 7. Texto a enum: Enum.TryParse =====

        // true: ignora mayúsculas y minúsculas
        if (Enum.TryParse("enviado", true, out EstadoPedido leido))
        {
            Console.WriteLine("Texto convertido: " + leido);
        }

        if (!Enum.TryParse("Perdido", true, out EstadoPedido noExiste))
        {
            Console.WriteLine("No existe el estado Perdido");
        }


        // ===== 8. Valores propios =====

        Prioridad prioridad = Prioridad.Alta;
        Console.WriteLine($"Prioridad {prioridad} = {(int)prioridad}");   // Alta = 10


        // Espera a que se pulse una tecla antes de cerrar la consola
        Console.ReadKey();
    }
}
