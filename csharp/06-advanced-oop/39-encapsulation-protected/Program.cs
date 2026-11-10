// protected: el miembro es accesible dentro de su propia clase Y en las clases que heredan de ella.
// Pero NO es accesible desde el resto del programa (por ejemplo, desde Main sobre un objeto).
//
//             propia clase   clase hija   resto del programa
//   public         sí            sí              sí
//   protected      sí            sí              NO
//   private        sí            NO              NO


// ===== 1. La clase padre con miembros protected =====

public class Animal
{
    public string Nombre { get; set; }

    // protected: lo ven Animal y sus hijas (Perro, Gato), pero no el código externo
    protected int energia = 100;

    public Animal(string nombre)
    {
        Nombre = nombre;
    }

    // Método protected: es un "detalle interno" que las hijas pueden reutilizar
    protected void Respirar()
    {
        Console.WriteLine($"{Nombre} está respirando");
    }

    // Un método public de la propia clase puede usar sus miembros protected
    public void Dormir()
    {
        Console.WriteLine($"{Nombre} está durmiendo");
        Respirar();
    }

    public void Descansar()
    {
        energia = 100;
        Console.WriteLine($"{Nombre} descansa. Energía: {energia}");
    }
}


// ===== 2. Las hijas usan los miembros protected del padre =====

public class Perro : Animal
{
    public Perro(string nombre) : base(nombre)
    {
    }

    public void Ladrar()
    {
        Respirar();                                   // protected heredado: permitido
        Console.WriteLine($"{Nombre} está ladrando");
    }

    public void Correr()
    {
        energia -= 30;                                // atributo protected heredado: permitido
        Console.WriteLine($"{Nombre} corre. Energía: {energia}");
    }
}

public class Gato : Animal
{
    public Gato(string nombre) : base(nombre)
    {
    }

    public void Maullar()
    {
        Respirar();
        Console.WriteLine($"{Nombre} está maullando");
    }
}


public class Program
{
    public static void Main()
    {
        Perro miPerro = new Perro("Rex");

        miPerro.Dormir();       // el método public usa Respirar() internamente
        miPerro.Ladrar();       // la hija usa Respirar()
        miPerro.Correr();       // la hija usa energia
        miPerro.Descansar();

        Gato miGato = new Gato("Michi");
        miGato.Maullar();


        // ===== 3. Desde fuera, protected no se ve =====

        // Main no es una clase hija de Animal, así que no puede usar los miembros protected:
        // miPerro.Respirar();      // error: 'Animal.Respirar()' es inaccesible por su nivel de protección
        // Console.WriteLine(miPerro.energia);   // error
        // Y tampoco sobre un objeto Animal creado desde fuera:
        // Animal conejo = new Animal("Boxy");
        // conejo.Respirar();       // error


        // Espera a que se pulse una tecla antes de cerrar la consola
        Console.ReadKey();
    }
}
