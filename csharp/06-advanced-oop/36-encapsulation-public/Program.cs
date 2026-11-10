// Encapsulamiento: ocultar los detalles internos de una clase y controlar quién puede acceder a ella.
// Los modificadores de acceso indican desde dónde se puede usar una clase o uno de sus miembros.
//
// Modificadores de acceso en C#:
//   public              accesible desde cualquier parte del programa
//   private             solo desde dentro de la misma clase (lección siguiente)
//   protected           desde la clase y sus clases derivadas (lección 39)
//   internal            desde cualquier parte del mismo ensamblado (proyecto)
//   protected internal  desde el mismo ensamblado o desde clases derivadas
//   private protected   solo desde la clase y sus derivadas dentro del mismo ensamblado
//
// Si no se escribe ninguno: los miembros de una clase son private y las clases son internal.
// Esta lección se centra en public.


// ===== 1. Una clase con miembros públicos =====

public class Camion
{
    // public: se pueden leer y cambiar desde cualquier otra clase
    public string marca;
    public string modelo;
    public string color;
    public int cargaMaxima;

    // El constructor también es public: si no, otra clase no podría crear camiones
    public Camion(string marca, string modelo, string color, int cargaMaxima)
    {
        this.marca = marca;
        this.modelo = modelo;
        this.color = color;
        this.cargaMaxima = cargaMaxima;
    }

    // Un método public se puede llamar desde otra clase
    public void DatosCamion(int velocidad, int acelera)
    {
        Console.WriteLine($"Marca: {marca}");
        Console.WriteLine($"Modelo: {modelo}");
        Console.WriteLine($"Color: {color}");
        Console.WriteLine($"Velocidad: {velocidad} km/h");
        Console.WriteLine($"Aceleración: {acelera} m/s²");
    }
}


public class Program
{
    public static void Main()
    {
        // ===== 2. Usar una clase pública desde otra =====

        // Program es una clase distinta a Camion, pero puede crear un Camion y usar sus miembros públicos
        Camion camion1 = new Camion("Mack", "MK12", "rojo", 18000);
        camion1.DatosCamion(230, 40);


        // ===== 3. Cambiar un atributo público directamente =====

        camion1.color = "azul";
        Console.WriteLine("Color tras cambiarlo: " + camion1.color);


        // ===== 4. El inconveniente: nadie controla los valores =====

        // Al ser public, cualquier clase puede dejar el objeto en un estado absurdo
        camion1.cargaMaxima = -500;
        Console.WriteLine("Carga máxima: " + camion1.cargaMaxima + " (nadie lo impide)");

        // Para poder validar los datos hay que ocultar los atributos y dar acceso controlado:
        // eso es lo que permite private, en la lección siguiente.


        // Espera a que se pulse una tecla antes de cerrar la consola
        Console.ReadKey();
    }
}
