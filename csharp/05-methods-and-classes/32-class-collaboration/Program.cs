// Un programa real tiene varias clases, y colaboran entre sí:
// una clase crea objetos de otra, los guarda como atributos y llama a sus métodos.


// ===== 1. Una clase con sus datos y su método =====

class Cliente
{
    public string nombre;
    public string email;

    public Cliente(string nombre, string email)
    {
        this.nombre = nombre;
        this.email = email;
    }

    // public: sin él, otras clases no podrían llamar al método
    public void Datos()
    {
        Console.WriteLine($"Nombre: {nombre}");
        Console.WriteLine($"Email: {email}");
    }
}


// ===== 2. Una clase que necesita a otra =====

// Una orden de compra pertenece a un cliente: Orden tiene un atributo de tipo Cliente
class Orden
{
    private int numero;
    private Cliente cliente;      // un objeto de otra clase como atributo
    private double total;

    public Orden(int numero, Cliente cliente, double total)
    {
        this.numero = numero;
        this.cliente = cliente;
        this.total = total;
    }

    public void Resumen()
    {
        Console.WriteLine($"Orden #{numero}");
        cliente.Datos();           // Orden usa un método de Cliente
        Console.WriteLine($"Total: {total:F2}");
    }

    public string NombreDelCliente()
    {
        return cliente.nombre;     // y también sus atributos
    }
}


class Program
{
    static void Main()
    {
        // ===== 3. Crear y usar un objeto de otra clase desde Main =====

        // Main está en la clase Program, pero crea un Cliente y llama a su método
        Cliente cliente1 = new Cliente("Sol", "sol@correo.com");
        cliente1.Datos();


        // ===== 4. Colaboración: una clase recibe un objeto de otra =====

        Orden orden1 = new Orden(1, cliente1, 120.5);
        orden1.Resumen();

        Console.WriteLine("Cliente de la orden: " + orden1.NombreDelCliente());


        // ===== 5. Los objetos se pasan por referencia =====

        // La orden no guarda una copia del cliente, sino el mismo objeto
        Orden orden2 = new Orden(2, cliente1, 80);

        cliente1.email = "nuevo@correo.com";   // se cambia el cliente...

        orden1.Resumen();   // ...y las dos órdenes lo ven
        orden2.Resumen();


        // Espera a que se pulse una tecla antes de cerrar la consola
        Console.ReadKey();
    }
}
