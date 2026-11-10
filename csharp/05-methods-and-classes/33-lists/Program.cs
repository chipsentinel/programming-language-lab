// Una lista es una colección que guarda elementos en orden, como un array,
// pero con tamaño dinámico: se pueden añadir, insertar y quitar elementos.
// List<T> viene de System.Collections.Generic, que este proyecto ya incluye.

class Program
{
    static void Main()
    {
        // ===== 1. Crear una lista =====

        // List<tipo> nombre = new List<tipo>();
        // El tipo de los elementos va entre ángulos < >
        List<string> simpsons = new List<string>();

        Console.WriteLine("Elementos al crearla: " + simpsons.Count);   // 0: empieza vacía


        // ===== 2. Añadir elementos: Add =====

        simpsons.Add("Homero");
        simpsons.Add("Marge");
        simpsons.Add("Lisa");
        simpsons.Add("Bart");
        simpsons.Add("Maggie");

        Console.WriteLine("Cantidad: " + simpsons.Count);   // 5


        // ===== 3. Recorrer una lista: foreach =====

        // foreach recorre cada elemento sin usar índices
        foreach (string nombre in simpsons)
        {
            Console.WriteLine(nombre);
        }

        // También se puede con for, accediendo por posición como en un array
        for (int i = 0; i < simpsons.Count; i++)
        {
            Console.WriteLine($"Posición {i}: {simpsons[i]}");
        }


        // ===== 4. Las listas admiten duplicados =====

        simpsons.Add("Homero");
        Console.WriteLine("Con duplicado: " + string.Join(", ", simpsons));


        // ===== 5. Quitar por valor: Remove =====

        // Quita la PRIMERA aparición del valor
        simpsons.Remove("Homero");
        Console.WriteLine("Tras Remove(Homero): " + string.Join(", ", simpsons));


        // ===== 6. Insertar en una posición: Insert =====

        simpsons.Insert(0, "Apu");
        Console.WriteLine("Tras Insert(0, Apu): " + string.Join(", ", simpsons));


        // ===== 7. Quitar por posición: RemoveAt =====

        simpsons.RemoveAt(5);   // la posición 5 es el segundo Homero
        Console.WriteLine("Tras RemoveAt(5): " + string.Join(", ", simpsons));


        // ===== 8. Buscar: Contains e IndexOf =====

        Console.WriteLine("¿Contiene Lisa? " + simpsons.Contains("Lisa"));   // True
        Console.WriteLine("¿Contiene Moe? " + simpsons.Contains("Moe"));     // False

        Console.WriteLine("Posición de Bart: " + simpsons.IndexOf("Bart"));       // 3
        Console.WriteLine("Posición de Nelson: " + simpsons.IndexOf("Nelson"));   // -1: no está


        // ===== 9. Ordenar e invertir: Sort y Reverse =====

        simpsons.Sort();
        Console.WriteLine("Ordenada: " + string.Join(", ", simpsons));

        simpsons.Reverse();
        Console.WriteLine("Al revés: " + string.Join(", ", simpsons));


        // ===== 10. Vaciar: Clear =====

        simpsons.Clear();
        Console.WriteLine("Tras Clear: " + simpsons.Count);   // 0


        // ===== 11. Una lista de enteros =====

        List<int> valores = new List<int> { 21, 8, 235, 100, 120 };   // se puede crear ya con valores
        Console.WriteLine("Valores: " + string.Join(", ", valores));

        valores.Remove(235);     // quita el VALOR 235
        Console.WriteLine("Tras Remove(235): " + string.Join(", ", valores));

        valores.RemoveAt(1);     // quita lo que hay en la POSICIÓN 1 (el 8)
        Console.WriteLine("Tras RemoveAt(1): " + string.Join(", ", valores));

        valores.Sort();
        valores.Reverse();
        Console.WriteLine("De mayor a menor: " + string.Join(", ", valores));

        // Una lista de int solo admite int
        // valores.Add("hola");   // error: un texto no es un int


        // ===== 12. Convertir entre array y lista =====

        int[] array = valores.ToArray();
        List<int> deNuevoLista = new List<int>(array);
        Console.WriteLine("Array: " + array.Length + ", lista: " + deNuevoLista.Count);


        // Espera a que se pulse una tecla antes de cerrar la consola
        Console.ReadKey();
    }
}
