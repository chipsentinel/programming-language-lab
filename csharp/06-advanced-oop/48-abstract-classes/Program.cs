// Una clase abstracta es una clase que NO se puede instanciar: no se pueden crear objetos directamente de ella.
// Sirve como BASE para otras clases (herencia): define lo que tienen en común y OBLIGA a las hijas
// a implementar ciertas partes.
//
// Puede contener:
//   - miembros normales (atributos, propiedades, métodos con código)
//   - métodos abstractos: solo la firma, sin cuerpo; las hijas DEBEN implementarlos (override)
//   - métodos virtuales: con código por defecto; las hijas PUEDEN cambiarlos (override)


// ===== 1. La clase abstracta =====

// public es obligatorio para que otras clases hereden de ella
public abstract class Animal
{
    // Una clase abstracta puede tener atributos, propiedades y constructor normales
    public string Nombre { get; set; }

    public Animal(string nombre)
    {
        Nombre = nombre;
    }

    // Método normal: lo heredan las hijas tal cual
    public void Comer()
    {
        Console.WriteLine($"{Nombre} está comiendo");
    }

    // Método abstracto: sin cuerpo (solo ;). Cada hija DEBE implementarlo
    public abstract void HacerSonido();

    // Método virtual: con una versión por defecto que las hijas pueden cambiar o dejar
    public virtual void Dormir()
    {
        Console.WriteLine($"{Nombre} está durmiendo");
    }
}


// ===== 2. Las hijas implementan lo abstracto con override =====

public class Perro : Animal
{
    public Perro(string nombre) : base(nombre) { }

    // Obligatorio: si no se implementa, el compilador da error
    public override void HacerSonido()
    {
        Console.WriteLine($"{Nombre} está ladrando");
    }
}

public class Gato : Animal
{
    public Gato(string nombre) : base(nombre) { }

    public override void HacerSonido()
    {
        Console.WriteLine($"{Nombre} está maullando");
    }

    // Opcional: cambia la versión por defecto
    public override void Dormir()
    {
        Console.WriteLine($"{Nombre} duerme 16 horas");
    }
}

public class Vaca : Animal
{
    public Vaca(string nombre) : base(nombre) { }

    public override void HacerSonido()
    {
        Console.WriteLine($"{Nombre} está mugiendo");
    }
    // No redefine Dormir: usa el de Animal
}


public class Program
{
    public static void Main()
    {
        // ===== 3. No se puede instanciar una clase abstracta =====

        // Animal animal = new Animal("Genérico");   // error: no se puede crear una instancia de una clase abstracta

        Perro miPerro = new Perro("Snoopy");
        Gato miGato = new Gato("Garfield");


        // ===== 4. Usar las clases hijas =====

        miPerro.Comer();          // heredado de Animal
        miPerro.HacerSonido();    // implementado en Perro

        miGato.Comer();
        miGato.HacerSonido();     // implementado en Gato: es distinto


        // ===== 5. Polimorfismo =====

        // Todos son Animal: se guardan juntos en una lista del tipo base.
        // Al llamar a HacerSonido, se ejecuta la versión de la clase REAL de cada objeto.
        List<Animal> animales = new List<Animal>
        {
            miPerro,
            miGato,
            new Vaca("Lola")
        };

        Console.WriteLine("Animales: " + animales.Count);

        foreach (Animal animal in animales)
        {
            animal.HacerSonido();
        }

        foreach (Animal animal in animales)
        {
            animal.Dormir();      // virtual: Gato lo cambia, los demás usan el de Animal
        }


        // Espera a que se pulse una tecla antes de cerrar la consola
        Console.ReadKey();
    }
}
