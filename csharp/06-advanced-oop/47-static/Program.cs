// static: el miembro (método, variable, propiedad) pertenece a la CLASE en sí, no a un objeto concreto.
// Por eso se usa con el nombre de la clase, SIN crear ningún objeto: Clase.Miembro
//
// Sirve para:
//   - Llamar a algo sin instanciar la clase (utilidades como Math).
//   - Compartir un dato entre TODOS los objetos de una clase (un contador).
//   - Crear herramientas que no dependen del estado de un objeto.


// ===== 1. Miembros static de una clase normal =====

class Utilidad
{
    // Método static: se llama con Utilidad.Saludar(), sin new
    public static void Saludar()
    {
        Console.WriteLine("Hola a todos");
    }

    // Variable static: un único valor, perteneciente a la clase
    public static int Total = 5000;

    // Método normal (de instancia): necesita un objeto
    public void SaludarDesdeObjeto()
    {
        Console.WriteLine("Hola desde un objeto");
    }
}


// ===== 2. Clase static =====

// Una clase static no se puede instanciar y TODO lo que contiene debe ser static
static class Matematica
{
    public static int Sumar(int a, int b)
    {
        return a + b;
    }

    public static int Doble(int numero)
    {
        return numero * 2;
    }

    // Una variable static dentro de una clase static
    public static int Contador = 0;

    // int sinStatic;         // error: una clase static no puede tener miembros de instancia
    // public void Metodo() { }   // error: debe ser static
}


// ===== 3. Un dato compartido entre todos los objetos =====

class Persona
{
    // static: UNA sola copia para toda la clase
    public static int Creadas = 0;

    // Sin static: cada objeto tiene la suya
    public string Nombre;
    public int Numero;

    public Persona(string nombre)
    {
        Creadas++;               // cada objeto nuevo suma 1 al contador compartido
        Nombre = nombre;
        Numero = Creadas;
    }

    public void Presentar()
    {
        Console.WriteLine($"Soy {Nombre} (n.º {Numero})");
    }

    // Un método static solo puede usar miembros static
    public static void MostrarTotal()
    {
        Console.WriteLine("Personas creadas: " + Creadas);
        // Console.WriteLine(Nombre);   // error: un método static no tiene "this" ni acceso a miembros de instancia
        // Console.WriteLine(this.Numero);   // error: this no existe en un contexto static
    }
}


// ===== 4. Constructor static =====

class Configuracion
{
    public static string Idioma;

    // Se ejecuta UNA sola vez, automáticamente, antes del primer uso de la clase. No lleva public ni parámetros.
    static Configuracion()
    {
        Console.WriteLine("Inicializando configuración (solo una vez)");
        Idioma = "es";
    }
}


class Program
{
    // Main es static: por eso el programa puede arrancar sin crear ningún objeto
    static void Main()
    {
        // ===== 1. Llamar a un miembro static: Clase.Miembro =====

        Utilidad.Saludar();
        Console.WriteLine("Total: " + Utilidad.Total);

        // Sobre un objeto no se pueden llamar los miembros static:
        // Utilidad u = new Utilidad();
        // u.Saludar();           // error: se accede a través del tipo, no de una instancia
        // Y los de instancia sí necesitan el objeto:
        new Utilidad().SaludarDesdeObjeto();

        Utilidad.Total = 8000;    // una variable static se puede modificar
        Console.WriteLine("Total tras cambiarlo: " + Utilidad.Total);


        // ===== 2. Clase static =====

        // Matematica m = new Matematica();   // error: no se puede crear una instancia de una clase static
        Console.WriteLine("Suma: " + Matematica.Sumar(50, 20));
        Console.WriteLine("Doble de 4: " + Matematica.Doble(4));

        // Math (de .NET) es una clase static: Math.Sqrt(16), Math.PI...
        Console.WriteLine("Raíz de 16: " + Math.Sqrt(16));


        // ===== 3. Dato compartido =====

        Persona.MostrarTotal();

        Persona sol = new Persona("Sol");
        Persona marcos = new Persona("Marcos");
        Persona lucia = new Persona("Lucía");

        sol.Presentar();
        marcos.Presentar();
        lucia.Presentar();

        Persona.MostrarTotal();     // el contador es de la clase: lo ven y lo cambian todos los objetos


        // ===== 4. Constructor static =====

        Console.WriteLine("Antes de usar Configuracion");
        Console.WriteLine("Idioma: " + Configuracion.Idioma);     // aquí se ejecuta el constructor static
        Console.WriteLine("Idioma: " + Configuracion.Idioma);     // la segunda vez ya no


        // Espera a que se pulse una tecla antes de cerrar la consola
        Console.ReadKey();
    }
}
