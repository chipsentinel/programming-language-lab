// Un método es un bloque de código con nombre, definido dentro de una clase.
// Sirve para no repetir código, dividir tareas en partes pequeñas y poder reutilizarlas.
// Puede recibir datos (parámetros, lección siguiente) y puede devolver un resultado.


// ===== 1. Definir métodos en una clase =====

class Prueba
{
    // Método SIN retorno: void significa "no devuelve nada"
    public void Saludar()
    {
        Console.WriteLine("Hola a todos");
    }

    public void Despedir()
    {
        Console.WriteLine("Adiós a todos");
    }

    // Método CON retorno: en lugar de void se escribe el tipo del valor que devuelve,
    // y termina con return
    public int Sumar()
    {
        int valor1 = 23, valor2 = 45;
        int total = valor1 + valor2;

        return total;   // el valor que recibe quien llame al método
    }

    public string Despedida()
    {
        string mensaje = "Adiós a todos";

        return mensaje;
    }

    // Un método puede llamar a otros métodos de su misma clase
    public void Presentar()
    {
        Saludar();
        Despedir();
    }
}


class Program
{
    // Un método static se puede llamar sin crear ningún objeto.
    // Main es un método static: por eso es el punto de entrada del programa.
    static void Separador()
    {
        Console.WriteLine("----------");
    }

    static void Main()
    {
        // ===== 2. Invocar (llamar) un método =====

        // Primero hay que instanciar la clase; después objeto.Metodo()
        Prueba saludo1 = new Prueba();
        saludo1.Saludar();   // Hola a todos

        Prueba despedida = new Prueba();
        despedida.Despedir();   // Adiós a todos

        // Un método se puede llamar tantas veces como se quiera
        saludo1.Saludar();


        // ===== 3. Métodos con retorno =====

        Separador();

        // El valor devuelto se puede guardar en una variable...
        int resultado = saludo1.Sumar();
        Console.WriteLine("Total de la suma: " + resultado);   // 68

        // ...o usar directamente
        Console.WriteLine("Mensaje: " + despedida.Despedida());   // Adiós a todos
        Console.WriteLine("Sumar() + 2 = " + (saludo1.Sumar() + 2));   // 70


        // ===== 4. Un método que llama a otros =====

        Separador();
        saludo1.Presentar();   // Hola a todos / Adiós a todos


        // ===== 5. Sin return no hay resultado =====

        // Un método void no se puede guardar en una variable:
        // int x = saludo1.Saludar();   // error: Saludar no devuelve nada


        // Espera a que se pulse una tecla antes de cerrar la consola
        Console.ReadKey();
    }
}
