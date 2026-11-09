// Hasta ahora el código se escribía suelto (instrucciones de nivel superior).
// A partir de aquí se organiza en CLASES, y el programa empieza en el método Main.


// ===== 1. Definir una clase =====

// Una clase es una plantilla (un molde) para crear objetos.
// Sus atributos son variables que pertenecen a la clase: describen cómo es cada objeto.
// (public significa que se pueden usar desde fuera de la clase; se explicará con el encapsulamiento)

class Persona
{
    public string nombre = "Sol";
    public int edad = 34;
    public string direccion = "Calle Mayor 12";
    public double salario = 2500;
    public bool activo = true;
}

// Otra clase, con atributos sin valor inicial
class Venta
{
    public string vendedor = "";
    public int cantidades;
}


// ===== 2. El método principal: Main =====

// Todo programa de consola empieza en Main: es el primer método que se ejecuta.
// (static, void y los paréntesis se explicarán en la lección de métodos)

class Program
{
    static void Main()
    {
        // ===== 3. Instanciar: crear un objeto de la clase =====

        // clase  nombreDelObjeto = new Clase();
        Persona persona1 = new Persona();

        // ===== 4. Leer los atributos: objeto.atributo =====

        Console.WriteLine(persona1.nombre);   // Sol

        Console.WriteLine($"Nombre: {persona1.nombre}");
        Console.WriteLine($"Edad: {persona1.edad}");
        Console.WriteLine($"Dirección: {persona1.direccion}");
        Console.WriteLine($"Salario: {persona1.salario}");
        Console.WriteLine($"Activo: {persona1.activo}");


        // ===== 5. Cambiar los atributos de un objeto =====

        persona1.edad = 35;
        Console.WriteLine($"Edad tras el cambio: {persona1.edad}");   // 35


        // ===== 6. Varios objetos de la misma clase =====

        // Cada objeto tiene sus propios valores
        Persona persona2 = new Persona();
        persona2.nombre = "Marcos";
        persona2.edad = 28;

        Console.WriteLine($"{persona1.nombre} tiene {persona1.edad} años");     // Sol tiene 35 años
        Console.WriteLine($"{persona2.nombre} tiene {persona2.edad} años");     // Marcos tiene 28 años


        // ===== 7. Otra clase =====

        Venta venta1 = new Venta();
        Console.WriteLine($"Cantidades por defecto: {venta1.cantidades}");   // 0

        venta1.vendedor = "Larry";
        venta1.cantidades = 500;
        Console.WriteLine($"Vendedor: {venta1.vendedor}, cantidades: {venta1.cantidades}");


        // Espera a que se pulse una tecla antes de cerrar la consola
        Console.ReadKey();
    }
}
