// Herencia: una clase (hija) puede derivar de otra (padre) y reutilizar sus miembros.
// La hija recibe los atributos, propiedades y métodos del padre, y puede añadir los suyos.
// Se escribe con dos puntos: class Hija : Padre
//
// Reglas en C#:
//  - Una clase solo puede heredar de UNA clase (herencia simple).
//  - Todas las clases derivan, directa o indirectamente, de la clase object (se hace solo).


// ===== 1. La clase padre: lo que tienen en común todos los animales =====

public class Animal
{
    // Propiedad automática: C# crea el atributo oculto y los métodos get y set (lección 37)
    public string Nombre { get; set; }

    public Animal(string nombre)
    {
        Nombre = nombre;
    }

    // Métodos comunes a cualquier animal
    public void Comer()
    {
        Console.WriteLine($"{Nombre} está comiendo");
    }

    public void Dormir()
    {
        Console.WriteLine($"{Nombre} está durmiendo");
    }
}


// ===== 2. Las clases hijas: heredan de Animal y añaden lo suyo =====

public class Perro : Animal
{
    // : base(nombre) llama al constructor del padre, que es quien guarda el nombre
    public Perro(string nombre) : base(nombre)
    {
    }

    // Método propio de los perros
    public void Ladrar()
    {
        Console.WriteLine($"{Nombre} está ladrando");
    }
}

public class Gato : Animal
{
    public Gato(string nombre) : base(nombre)
    {
    }

    public void Maullar()
    {
        Console.WriteLine($"{Nombre} está maullando");
    }
}


public class Program
{
    public static void Main()
    {
        // ===== 3. La hija usa los miembros del padre =====

        Perro miPerro = new Perro("Snoopy");

        miPerro.Dormir();    // heredado de Animal
        miPerro.Comer();     // heredado de Animal
        miPerro.Ladrar();    // propio de Perro

        // También la propiedad heredada
        miPerro.Nombre = "Rex";
        miPerro.Comer();     // Rex está comiendo


        // ===== 4. Cada hija tiene lo suyo =====

        Gato miGato = new Gato("Michi");

        miGato.Dormir();
        miGato.Comer();
        miGato.Maullar();

        // Lo propio de una hija no existe en la otra:
        // miGato.Ladrar();   // error: Gato no tiene el método Ladrar


        // ===== 5. Relación "es un" =====

        // Un Perro ES UN Animal: se puede guardar en una variable de tipo Animal
        Animal animal = miPerro;
        animal.Comer();      // Rex está comiendo
        Console.WriteLine("¿Perro es un Animal? " + (miPerro is Animal));   // True
        Console.WriteLine("¿Perro es un Gato? " + (miPerro is Gato));       // False

        // Pero a través de la variable Animal solo se ven los miembros de Animal:
        // animal.Ladrar();   // error: Animal no tiene Ladrar


        // ===== 6. Un miembro private del padre no se hereda para uso directo =====

        // Si Nombre fuera private en Animal, Perro no podría usarlo:
        // por eso se necesita protected (lección 39).


        // Espera a que se pulse una tecla antes de cerrar la consola
        Console.ReadKey();
    }
}
