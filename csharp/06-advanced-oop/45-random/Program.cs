// La clase Random genera números PSEUDOALEATORIOS: parecen impredecibles, pero salen de una fórmula.
// Sirve para juegos, simulaciones, repartir datos al azar, elegir elementos...
// NO sirve para contraseñas ni claves de seguridad (ver el apartado final).
//
// Las líneas que empiezan por "Ejemplo:" cambian en cada ejecución.

class Program
{
    static void Main()
    {
        // ===== 1. Crear un objeto Random =====

        Random aleatorio = new Random();

        // Next() sin argumentos: un entero entre 0 y 2 147 483 646 (int.MaxValue - 1)
        int numero1 = aleatorio.Next();
        Console.WriteLine($"Ejemplo: número aleatorio 1: {numero1}");
        Console.WriteLine("Next() es mayor o igual que 0: " + (numero1 >= 0));


        // ===== 2. Entre 0 y un máximo: Next(max) =====

        // El máximo NO se incluye: Next(50) da de 0 a 49
        int numero2 = aleatorio.Next(50);
        Console.WriteLine($"Ejemplo: número aleatorio entre 0 y 49: {numero2}");

        // Para comprobarlo se piden muchos valores y se mira si TODOS caen en el rango
        bool todosEnRango = true;
        for (int i = 0; i < 1000; i++)
        {
            int valor = aleatorio.Next(50);
            if (valor < 0 || valor >= 50) todosEnRango = false;
        }
        Console.WriteLine("1000 valores de Next(50) están entre 0 y 49: " + todosEnRango);


        // ===== 3. Entre un mínimo y un máximo: Next(min, max) =====

        // El mínimo SÍ se incluye y el máximo NO: Next(20, 25) da 20, 21, 22, 23 o 24
        int numero3 = aleatorio.Next(20, 25);
        Console.WriteLine($"Ejemplo: número aleatorio entre 20 y 24: {numero3}");

        bool todosEn20a24 = true;
        bool salio20 = false;
        bool salio25 = false;
        for (int i = 0; i < 1000; i++)
        {
            int valor = aleatorio.Next(20, 25);
            if (valor < 20 || valor > 24) todosEn20a24 = false;
            if (valor == 20) salio20 = true;
            if (valor == 25) salio25 = true;
        }
        Console.WriteLine("1000 valores de Next(20, 25) están entre 20 y 24: " + todosEn20a24);
        Console.WriteLine("El mínimo (20) puede salir: " + salio20);
        Console.WriteLine("El máximo (25) nunca sale: " + !salio25);


        // ===== 4. Decimales: NextDouble =====

        // NextDouble() da un decimal entre 0.0 (incluido) y 1.0 (excluido)
        double decimal1 = aleatorio.NextDouble();
        Console.WriteLine("NextDouble() está entre 0 y 1: " + (decimal1 >= 0 && decimal1 < 1));

        // Para otro rango se multiplica: entre 0.0 y 10.0
        double decimal2 = aleatorio.NextDouble() * 10.0;
        Console.WriteLine($"Ejemplo: decimal entre 0 y 10: {decimal2}");
        Console.WriteLine("Está entre 0 y 10: " + (decimal2 >= 0 && decimal2 < 10));


        // ===== 5. Semilla: la misma semilla repite la secuencia =====

        // Un Random es una fórmula que parte de una "semilla". Con la misma semilla da SIEMPRE los mismos números.
        // Sin semilla, usa una que depende del reloj y por eso cambia en cada ejecución.
        Random a = new Random(42);
        Random b = new Random(42);

        bool iguales = true;
        for (int i = 0; i < 5; i++)
        {
            if (a.Next(100) != b.Next(100)) iguales = false;
        }
        Console.WriteLine("Con la misma semilla se repite la secuencia: " + iguales);   // útil para pruebas reproducibles


        // ===== 6. Usos habituales =====

        // Tirar un dado: del 1 al 6 (el máximo 7 no se incluye)
        int dado = aleatorio.Next(1, 7);
        Console.WriteLine($"Ejemplo: dado: {dado}");
        Console.WriteLine("Dado válido (1-6): " + (dado >= 1 && dado <= 6));

        // Cara o cruz
        string moneda = aleatorio.Next(2) == 0 ? "cara" : "cruz";
        Console.WriteLine($"Ejemplo: moneda: {moneda}");

        // Elegir un elemento al azar de un array: una posición aleatoria
        string[] colores = { "rojo", "verde", "azul", "amarillo" };
        string elegido = colores[aleatorio.Next(colores.Length)];
        Console.WriteLine($"Ejemplo: color elegido: {elegido}");
        Console.WriteLine("El color existe en el array: " + colores.Contains(elegido));


        // ===== 7. Random.Shared =====

        // Desde .NET 6 hay un Random compartido, listo para usar sin crear objetos
        int compartido = Random.Shared.Next(1, 101);
        Console.WriteLine("Random.Shared.Next(1, 101) está entre 1 y 100: " + (compartido >= 1 && compartido <= 100));


        // ===== 8. Random NO es seguro para contraseñas =====

        // Con la semilla se pueden predecir los números: para seguridad se usa RandomNumberGenerator
        int seguro = System.Security.Cryptography.RandomNumberGenerator.GetInt32(1, 101);
        Console.WriteLine("RandomNumberGenerator.GetInt32(1, 101) está entre 1 y 100: " + (seguro >= 1 && seguro <= 100));


        // Espera a que se pulse una tecla antes de cerrar la consola
        Console.ReadKey();
    }
}
