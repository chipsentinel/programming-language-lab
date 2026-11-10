// Un mapa (diccionario) guarda pares clave-valor: cada valor se asocia a una clave ÚNICA,
// y se accede al valor indicando su clave.
// En C# se llama Dictionary<TClave, TValor> (System.Collections.Generic, ya incluido en el proyecto).

class Program
{
    static void Main()
    {
        // ===== 1. Crear un diccionario =====

        // Dictionary<tipoClave, tipoValor>: aquí claves enteras y valores de texto
        Dictionary<int, string> frutas = new Dictionary<int, string>();


        // ===== 2. Añadir pares: Add(clave, valor) =====

        frutas.Add(1, "manzanas");
        frutas.Add(2, "peras");
        frutas.Add(3, "bananas");
        frutas.Add(4, "uvas");
        frutas.Add(5, "fresas");

        Console.WriteLine("Cantidad: " + frutas.Count);   // 5


        // ===== 3. Leer por clave: diccionario[clave] =====

        Console.WriteLine("Clave 2: " + frutas[2]);   // peras

        // Pedir una clave que no existe lanza KeyNotFoundException:
        // Console.WriteLine(frutas[9]);


        // ===== 4. Recorrer: foreach con pares clave-valor =====

        foreach (var par in frutas)
        {
            Console.WriteLine($"Clave: {par.Key}, Valor: {par.Value}");
        }


        // ===== 5. Comprobar si existe: ContainsKey y ContainsValue =====

        Console.WriteLine("¿Existe la clave 3? " + frutas.ContainsKey(3));            // True
        Console.WriteLine("¿Existe la clave 9? " + frutas.ContainsKey(9));            // False
        Console.WriteLine("¿Existe el valor uvas? " + frutas.ContainsValue("uvas"));  // True


        // ===== 6. Las claves son únicas =====

        // Add con una clave que ya existe lanza ArgumentException:
        // frutas.Add(2, "mangos");

        // El indexador [ ] reemplaza el valor si la clave existe...
        frutas[2] = "peras verdes";
        Console.WriteLine("Clave 2 tras cambiarla: " + frutas[2]);

        // ...y crea el par si no existe
        frutas[6] = "kiwis";
        Console.WriteLine("Cantidad: " + frutas.Count);   // 6


        // ===== 7. Leer sin riesgo: TryGetValue =====

        if (frutas.TryGetValue(9, out string? fruta))
        {
            Console.WriteLine("Clave 9: " + fruta);
        }
        else
        {
            Console.WriteLine("La clave 9 no existe");
        }


        // ===== 8. Quitar un par: Remove(clave) =====

        frutas.Remove(1);
        Console.WriteLine("Cantidad tras Remove(1): " + frutas.Count);   // 5

        // Las claves y los valores por separado
        Console.WriteLine("Claves: " + string.Join(", ", frutas.Keys));
        Console.WriteLine("Valores: " + string.Join(", ", frutas.Values));


        // ===== 9. Vaciar: Clear =====

        frutas.Clear();
        Console.WriteLine("Mapa limpio. Cantidad: " + frutas.Count);   // 0


        // ===== 10. Claves de texto: el caso más habitual =====

        Dictionary<string, int> stock = new Dictionary<string, int>
        {
            { "pan", 20 },
            { "leche", 12 },
            { "huevos", 30 }
        };

        Console.WriteLine("Stock de leche: " + stock["leche"]);   // 12

        stock["leche"] -= 2;   // se puede modificar el valor de una clave
        Console.WriteLine("Stock de leche tras vender 2: " + stock["leche"]);   // 10

        // Un valor por defecto cuando la clave no existe
        Console.WriteLine("Stock de sal: " + stock.GetValueOrDefault("sal", 0));   // 0


        // Espera a que se pulse una tecla antes de cerrar la consola
        Console.ReadKey();
    }
}
