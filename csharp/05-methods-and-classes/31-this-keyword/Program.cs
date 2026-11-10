// this es una referencia al objeto actual: el objeto sobre el que se está ejecutando el método.
// Se usa para distinguir atributos de parámetros con el mismo nombre,
// para pasar el propio objeto como valor y para encadenar constructores.


// ===== 1. El problema: parámetro y atributo con el mismo nombre =====

class SinThis
{
    public string nombre = "";
    public int edad = 0;

    public SinThis(string nombre, int edad)
    {
        // Dentro del constructor, "nombre" es el PARÁMETRO (oculta al atributo):
        // se asigna el parámetro a sí mismo y el atributo no cambia (aviso CS1717)
        nombre = nombre;
        edad = edad;
    }
}


// ===== 2. La solución: this =====

class Persona
{
    public string nombre = "";
    public int edad = 0;

    // this.nombre es el atributo del objeto; nombre es el parámetro
    public Persona(string nombre, int edad)
    {
        this.nombre = nombre;
        this.edad = edad;
    }

    // ===== 5. Encadenar constructores: : this(...) =====

    // Este constructor reutiliza el de dos parámetros en lugar de repetir su código
    public Persona() : this("Sol", 34)
    {
    }

    public void Mostrar()
    {
        // En un método sin parámetros homónimos this es opcional, pero deja claro que es un atributo
        Console.WriteLine($"Nombre: {this.nombre}, edad: {this.edad}");
    }

    // ===== 3. this como valor: el propio objeto =====

    // this es el objeto que recibe la llamada; otra es el que se pasa como argumento
    public bool EsMayorQue(Persona otra)
    {
        return this.edad > otra.edad;
    }

    // ===== 4. Devolver this para encadenar llamadas =====

    public Persona CumplirAnios()
    {
        this.edad++;
        return this;   // devuelve el mismo objeto, y se puede llamar a otro método sobre él
    }
}


class Program
{
    static void Main()
    {
        // Sin this no se asigna nada
        SinThis malo = new SinThis("Bart", 10);
        Console.WriteLine($"Sin this: nombre = '{malo.nombre}', edad = {malo.edad}");

        // Con this sí
        Persona persona1 = new Persona("Bart", 10);
        persona1.Mostrar();

        // this como valor
        Persona persona2 = new Persona("Lisa", 8);
        Console.WriteLine($"¿Bart es mayor que Lisa? {persona1.EsMayorQue(persona2)}");   // True
        Console.WriteLine($"¿Lisa es mayor que Bart? {persona2.EsMayorQue(persona1)}");   // False

        // Constructor encadenado
        Persona persona3 = new Persona();
        persona3.Mostrar();

        // Llamadas encadenadas
        persona1.CumplirAnios().CumplirAnios();
        persona1.Mostrar();   // edad 12

        // Cuando los nombres son distintos, this no hace falta:
        // public Persona(string elNombre, int laEdad) { nombre = elNombre; edad = laEdad; }

        // Espera a que se pulse una tecla antes de cerrar la consola
        Console.ReadKey();
    }
}
