// Un parámetro es un dato que un método recibe al ser llamado.
// Así el mismo método puede trabajar con valores distintos sin cambiar su código.
// Los valores concretos que se pasan al llamarlo se llaman argumentos.


class Prueba
{
    // ===== 1. Un atributo de la clase se puede usar en todos sus métodos =====

    public string nombre = "Sol";

    public void SaludoConAtributo()
    {
        Console.WriteLine("Hola, " + nombre);   // usa el atributo de la clase
    }

    // Problema: el saludo siempre es para "Sol". Con un parámetro cada llamada puede usar otro nombre.


    // ===== 2. Método con un parámetro =====

    // Entre paréntesis se declara cada parámetro: tipo y nombre
    public void Saludar(string nombre)
    {
        Console.WriteLine("Hola, " + nombre);
    }


    // ===== 3. Método con varios parámetros =====

    // Se separan con comas, cada uno con su tipo
    public void Presentar(string nombre, int edad)
    {
        Console.WriteLine($"Nombre: {nombre}, edad: {edad}");
    }


    // ===== 4. Parámetros y retorno =====

    public int Sumar(int a, int b)
    {
        return a + b;
    }

    public bool EsMayorDeEdad(int edad)
    {
        return edad >= 18;
    }


    // ===== 5. Parámetro opcional (valor por defecto) =====

    // Si al llamar no se pasa el argumento, se usa el valor por defecto
    public void Despedir(string nombre, string mensaje = "Adiós")
    {
        Console.WriteLine($"{mensaje}, {nombre}");
    }
}


class Program
{
    static void Main()
    {
        Prueba objeto1 = new Prueba();

        objeto1.SaludoConAtributo();           // Hola, Sol

        // Se pasa un argumento por cada parámetro, en el mismo orden y con el mismo tipo
        objeto1.Saludar("Sol");                // Hola, Sol
        objeto1.Saludar("Marcos");             // Hola, Marcos

        objeto1.Presentar("Sol", 34);          // Nombre: Sol, edad: 34

        // Si faltan argumentos, o son de otro tipo, no compila:
        // objeto1.Presentar("Sol");           // error: falta la edad
        // objeto1.Presentar(34, "Sol");       // error: el orden y los tipos importan


        // ===== 6. Usar el valor devuelto =====

        int resultado = objeto1.Sumar(34, 22);
        Console.WriteLine("Suma: " + resultado);                      // 56

        // Los argumentos pueden ser variables o expresiones
        Console.WriteLine("Suma: " + objeto1.Sumar(resultado, 4));    // 60
        Console.WriteLine("Mayor de edad (20): " + objeto1.EsMayorDeEdad(20));   // True
        Console.WriteLine("Mayor de edad (15): " + objeto1.EsMayorDeEdad(15));   // False


        // ===== 7. Parámetro opcional =====

        objeto1.Despedir("Sol");                    // Adiós, Sol
        objeto1.Despedir("Sol", "Hasta pronto");    // Hasta pronto, Sol

        // También se pueden indicar los argumentos por nombre, en cualquier orden
        objeto1.Presentar(edad: 34, nombre: "Sol");   // Nombre: Sol, edad: 34


        // ===== 8. Un parámetro solo existe dentro de su método =====

        // El parámetro nombre de Saludar no se puede usar desde Main:
        // Console.WriteLine(nombre);   // error: nombre no existe aquí


        // ===== 9. Argumentos que escribe el usuario =====

        Console.WriteLine("Ingrese su nombre...");
        string nombreUsuario = Console.ReadLine() ?? "";

        Console.WriteLine("Ingrese su edad...");
        int edadUsuario = Convert.ToInt32(Console.ReadLine());

        objeto1.Presentar(nombreUsuario, edadUsuario);
        Console.WriteLine("Suma de la edad y 10: " + objeto1.Sumar(edadUsuario, 10));


        // Espera a que se pulse una tecla antes de cerrar la consola
        Console.ReadKey();
    }
}
