// Programación orientada a objetos (POO): organizar el programa en torno a OBJETOS
// que combinan datos (atributos) y comportamientos (métodos), igual que los objetos del mundo real.
//
// Ejemplo: un auto tiene marca, modelo, color... (atributos) y puede encenderse, acelerar, frenar... (métodos).


// ===== 1. La clase: la plantilla del objeto =====

class Auto
{
    // Atributos: las características del auto
    public string marca;
    public string modelo;
    public string color;
    public int anio;                 // sin ñ en los nombres: anio
    public int velocidadMaxima;

    // Atributos de estado: cambian mientras el auto se usa
    public bool encendido = false;
    public int velocidadActual = 0;

    // ===== 2. El constructor: inicializa el objeto =====

    public Auto(string marca, string modelo, string color, int anio, int velocidadMaxima)
    {
        this.marca = marca;
        this.modelo = modelo;
        this.color = color;
        this.anio = anio;
        this.velocidadMaxima = velocidadMaxima;
    }

    // ===== 3. Métodos: lo que el auto puede hacer =====

    // Método sin retorno que muestra la información del objeto
    public void MostrarInfo()
    {
        Console.WriteLine("=== Información del auto ===");
        Console.WriteLine($"Marca: {marca}");
        Console.WriteLine($"Modelo: {modelo}");
        Console.WriteLine($"Color: {color}");
        Console.WriteLine($"Año: {anio}");
        Console.WriteLine($"Velocidad máxima: {velocidadMaxima} km/h");
    }

    // Métodos que cambian el estado del objeto
    public void Encender()
    {
        encendido = true;
        Console.WriteLine("Auto encendido");
    }

    public void Apagar()
    {
        encendido = false;
        velocidadActual = 0;
        Console.WriteLine("Auto apagado");
    }

    public void Acelerar()
    {
        // El comportamiento depende del estado actual del objeto
        if (!encendido)
        {
            Console.WriteLine("Auto apagado, no se puede acelerar");
            return;
        }

        velocidadActual = Math.Min(velocidadActual + 10, velocidadMaxima);
        Console.WriteLine($"Auto acelerando. Velocidad actual: {velocidadActual} km/h");
    }

    public void Frenar()
    {
        velocidadActual = 0;
        Console.WriteLine("Auto detenido");
    }

    // Métodos con retorno: devuelven información sobre el objeto
    public bool EstaEncendido()
    {
        return encendido;
    }

    public string Describir()
    {
        return $"{marca} {modelo} ({color}, {anio})";
    }
}


class Program
{
    static void Main()
    {
        // ===== 4. Crear objetos: instanciar la clase =====

        Auto auto1 = new Auto("Toyota", "RAV4", "negro", 2025, 250);
        auto1.MostrarInfo();


        // ===== 5. Usar los métodos del objeto =====

        auto1.Acelerar();    // apagado: no se puede
        auto1.Encender();
        auto1.Acelerar();
        auto1.Acelerar();
        Console.WriteLine("Velocidad: " + auto1.velocidadActual + " km/h");   // 20

        auto1.Frenar();
        auto1.Apagar();
        Console.WriteLine(auto1.Describir());


        // ===== 6. Cada objeto tiene su propio estado =====

        Auto auto2 = new Auto("Ford", "Fiesta", "rojo", 2018, 180);
        auto2.Encender();

        Console.WriteLine(auto2.Describir());
        Console.WriteLine("¿Auto 1 encendido? " + auto1.EstaEncendido());   // False
        Console.WriteLine("¿Auto 2 encendido? " + auto2.EstaEncendido());   // True


        // Espera a que se pulse una tecla antes de cerrar la consola
        Console.ReadKey();
    }
}
