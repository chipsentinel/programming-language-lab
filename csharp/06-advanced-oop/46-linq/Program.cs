// LINQ (Language Integrated Query, "lenguaje de consulta integrado") permite hacer CONSULTAS sobre
// colecciones de datos (listas, arrays, bases de datos, XML...) escribiéndolas en el propio lenguaje:
// filtrar, ordenar, transformar, agrupar y calcular totales. Es muy parecido a SQL.
// Necesita System.Linq, que este proyecto ya incluye (ImplicitUsings).

// Un record: una clase sencilla para guardar datos (se verá más adelante)
record Persona(string Nombre, int Edad);

class Program
{
    static void Main()
    {
        List<string> nombres = new List<string> { "Ana", "Carlos", "Pedro", "Clara", "Andrés" };


        // ===== 1. Sintaxis de consulta: from ... where ... orderby ... select =====

        // Para cada elemento "nombre" de la lista "nombres", que empiece por C, ordenado, seleccionar
        var resultado =
            from nombre in nombres
            where nombre.StartsWith("C")
            orderby nombre
            select nombre;

        // La variable "nombre" es solo un nombre temporal para cada elemento: podría llamarse n, x...
        Console.WriteLine("Empiezan por C: " + string.Join(", ", resultado));      // Carlos, Clara

        var conA =
            from nombre in nombres
            where nombre.StartsWith("A")
            orderby nombre
            select nombre;
        Console.WriteLine("Empiezan por A: " + string.Join(", ", conA));           // Ana, Andrés


        // ===== 2. Sintaxis de métodos: Where, OrderBy, Select =====

        // Hace lo mismo con llamadas encadenadas y expresiones lambda (x => ...). Es la más usada.
        var mismoResultado = nombres.Where(n => n.StartsWith("C")).OrderBy(n => n);
        Console.WriteLine("Con métodos: " + string.Join(", ", mismoResultado));    // Carlos, Clara


        // ===== 3. Con números =====

        List<int> numeros = new List<int> { 10, 20, 30, 40, 50, 60, 1 };

        var pares = from n in numeros where n % 2 == 0 select n;
        Console.WriteLine("Pares: " + string.Join(", ", pares));                   // 10, 20, 30, 40, 50, 60

        Console.WriteLine("Mayores que 25: " + string.Join(", ", numeros.Where(n => n > 25)));           // 30, 40, 50, 60


        // ===== 4. Transformar (proyectar): Select =====

        Console.WriteLine("Doble de cada uno: " + string.Join(", ", numeros.Select(n => n * 2)));        // 20, 40, ..., 2


        // ===== 5. Ordenar: OrderBy y OrderByDescending =====

        Console.WriteLine("De mayor a menor: " + string.Join(", ", numeros.OrderByDescending(n => n)));   // 60, 50, ..., 1


        // ===== 6. Cálculos: Count, Sum, Average, Max, Min =====

        Console.WriteLine("Cantidad: " + numeros.Count());                         // 7
        Console.WriteLine("Suma: " + numeros.Sum());                                // 211
        Console.WriteLine("Media: " + numeros.Average().ToString("F2"));            // 30,14
        Console.WriteLine("Máximo: " + numeros.Max());                              // 60
        Console.WriteLine("Mínimo: " + numeros.Min());                              // 1


        // ===== 7. Preguntas: Any, All, First =====

        Console.WriteLine("¿Alguno mayor que 50? " + numeros.Any(n => n > 50));    // True
        Console.WriteLine("¿Todos mayores que 0? " + numeros.All(n => n > 0));      // True
        Console.WriteLine("Primero mayor que 25: " + numeros.First(n => n > 25));   // 30
        Console.WriteLine("¿Alguno mayor que 100? " + numeros.Any(n => n > 100));   // False


        // ===== 8. Seleccionar partes: Take, Skip, Distinct =====

        Console.WriteLine("Los 3 primeros: " + string.Join(", ", numeros.Take(3)));          // 10, 20, 30
        Console.WriteLine("Sin los 5 primeros: " + string.Join(", ", numeros.Skip(5)));      // 60, 1

        List<int> repetidos = new List<int> { 1, 2, 2, 3, 3, 3 };
        Console.WriteLine("Sin repetidos: " + string.Join(", ", repetidos.Distinct()));      // 1, 2, 3


        // ===== 9. Agrupar: GroupBy =====

        foreach (var grupo in nombres.GroupBy(n => n[0]))
        {
            Console.WriteLine($"{grupo.Key}: {string.Join(", ", grupo)}");
        }


        // ===== 10. Con objetos =====

        List<Persona> personas = new List<Persona>
        {
            new Persona("Sol", 34),
            new Persona("Marcos", 28),
            new Persona("Lucía", 8),
            new Persona("Pedro", 17),
            new Persona("Marta", 45)
        };

        var mayoresDeEdad = personas.Where(p => p.Edad >= 18).OrderBy(p => p.Edad).Select(p => p.Nombre);
        Console.WriteLine("Mayores de edad (por edad): " + string.Join(", ", mayoresDeEdad));    // Marcos, Sol, Marta
        Console.WriteLine("Edad media: " + personas.Average(p => p.Edad).ToString("F1"));       // 26,4


        // ===== 11. Ejecución diferida =====

        // La consulta NO se ejecuta al definirla, sino cuando se recorre.
        var empiezanPorC = nombres.Where(n => n.StartsWith("C"));
        nombres.Add("Cecilia");                          // se añade DESPUÉS de definir la consulta...
        Console.WriteLine("Diferida: " + string.Join(", ", empiezanPorC));   // ...y aparece: Carlos, Clara, Cecilia

        // ToList() o ToArray() ejecutan la consulta en el momento y guardan el resultado
        List<string> fijada = nombres.Where(n => n.StartsWith("C")).ToList();
        nombres.Add("Camila");
        Console.WriteLine("Fijada: " + string.Join(", ", fijada));      // Carlos, Clara, Cecilia (Camila no aparece)


        // Espera a que se pulse una tecla antes de cerrar la consola
        Console.ReadKey();
    }
}
