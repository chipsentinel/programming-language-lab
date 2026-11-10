// private: el miembro solo es accesible DENTRO de la clase donde se declara.
// Ninguna otra clase (ni siquiera una derivada) puede usarlo directamente.
// Es lo habitual para los atributos: se protegen y se ofrece acceso controlado mediante métodos.


// ===== 1. Atributos privados con métodos de acceso =====

public class Camion
{
    // private: solo se pueden usar dentro de la clase Camion
    private string marca;
    private string modelo;
    private string color;
    private int cargaMaxima;

    public Camion(string marca, string modelo, string color, int cargaMaxima)
    {
        this.marca = marca;
        this.modelo = modelo;
        this.color = color;
        this.cargaMaxima = cargaMaxima;
    }

    // Método "getter": permite LEER el atributo sin dejar modificarlo
    public string ObtenerMarca()
    {
        return marca;
    }

    public int ObtenerCargaMaxima()
    {
        return cargaMaxima;
    }

    // Método "setter": permite MODIFICAR el atributo, pero validando el valor
    public void CambiarCargaMaxima(int nuevaCarga)
    {
        if (nuevaCarga <= 0)
        {
            Console.WriteLine("Carga inválida: debe ser mayor que cero");
            return;
        }

        cargaMaxima = nuevaCarga;
        Console.WriteLine($"Carga máxima cambiada a {cargaMaxima}");
    }

    // ===== 2. Métodos privados: ayudantes internos =====

    // Un método private solo se puede llamar desde otros métodos de la misma clase
    private string Descripcion()
    {
        return $"{marca} {modelo} ({color})";
    }

    public void Mostrar()
    {
        Console.WriteLine($"{Descripcion()}: carga máxima {cargaMaxima} kg");
    }
}


// ===== 3. Propiedades: la forma habitual en C# =====

public class Furgoneta
{
    private int carga = 500;    // atributo privado (campo de respaldo)

    // Una propiedad se usa como un atributo, pero ejecuta código al leer (get) y al escribir (set)
    public int Carga
    {
        get { return carga; }
        set
        {
            // value es el valor que se intenta asignar
            if (value <= 0)
            {
                Console.WriteLine("Carga inválida: debe ser mayor que cero");
                return;
            }

            carga = value;
        }
    }
}


// ===== 4. Constructor privado =====

public class Utilidades
{
    // Con un constructor private nadie puede crear objetos con new desde fuera
    private Utilidades() { }
}


public class Program
{
    public static void Main()
    {
        Camion camion1 = new Camion("Mack", "MK12", "rojo", 18000);

        // Los miembros privados no se ven desde otra clase:
        // Console.WriteLine(camion1.marca);        // error: 'marca' es inaccesible
        // camion1.cargaMaxima = -500;              // error: ya no se puede dejar un valor absurdo
        // Console.WriteLine(camion1.Descripcion()); // error: el método es private

        Console.WriteLine("Marca: " + camion1.ObtenerMarca());

        camion1.CambiarCargaMaxima(-500);     // rechazado por la validación
        camion1.CambiarCargaMaxima(20000);    // aceptado

        camion1.Mostrar();


        // ===== Propiedad =====

        Furgoneta furgoneta1 = new Furgoneta();
        furgoneta1.Carga = -5;        // rechazado
        furgoneta1.Carga = 1000;      // aceptado
        Console.WriteLine("Carga de la furgoneta: " + furgoneta1.Carga);


        // ===== Constructor privado =====

        // Utilidades u = new Utilidades();   // error: el constructor es inaccesible


        // Espera a que se pulse una tecla antes de cerrar la consola
        Console.ReadKey();
    }
}
