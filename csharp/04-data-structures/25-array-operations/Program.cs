// Operaciones habituales con arrays: sumar, filtrar, copiar y combinar dos arrays.
// Sum, Where, Max... vienen de LINQ (using System.Linq, que este proyecto ya incluye).


int[] numeros = { 12, 233, 5, 658, 45555 };


// ===== 1. Sumar todos los valores: Sum =====

int total = numeros.Sum();
Console.WriteLine("Total de la suma de valores: " + total);   // 46463


// ===== 2. Máximo, mínimo y media =====

Console.WriteLine("Máximo: " + numeros.Max());          // 45555
Console.WriteLine("Mínimo: " + numeros.Min());          // 5
Console.WriteLine("Media: " + numeros.Average());       // 9292,6


// ===== 3. Filtrar: Where =====

// Where recibe una condición y se queda con los elementos que la cumplen.
// x => x > 12 es una expresión lambda ("para cada x, ¿es x mayor que 12?"): se verá más adelante.
int[] mayoresQue12 = numeros.Where(x => x > 12).ToArray();

Console.WriteLine("Mayores que 12: " + string.Join(", ", mayoresQue12));   // 233, 658, 45555

// Where devuelve una secuencia: ToArray() la convierte de nuevo en un array


// ===== 4. Copiar un array =====

// Asignar un array a otra variable NO lo copia: las dos apuntan al mismo array
int[] alias = numeros;
alias[0] = 999;
Console.WriteLine("numeros[0] tras cambiar alias[0]: " + numeros[0]);   // 999

numeros[0] = 12;   // se restaura el valor original

// Clone crea una copia independiente
int[] cantidades = (int[])numeros.Clone();
cantidades[0] = 1;

Console.WriteLine("Array numeros: " + string.Join(", ", numeros));         // 12, 233, 5, 658, 45555
Console.WriteLine("Array cantidades: " + string.Join(", ", cantidades));   // 1, 233, 5, 658, 45555


// ===== 5. Operar dos arrays elemento a elemento =====

int[] array1 = { 10, 20, 30, 40 };
int[] array2 = { 1, 2, 3, 4 };

// Los dos arrays deben tener la misma cantidad de elementos
int[] suma = new int[array1.Length];
int[] resta = new int[array1.Length];
int[] producto = new int[array1.Length];
int[] division = new int[array1.Length];

for (int i = 0; i < array1.Length; i++)
{
    suma[i] = array1[i] + array2[i];          // 10 + 1, 20 + 2, ...
    resta[i] = array1[i] - array2[i];
    producto[i] = array1[i] * array2[i];
    division[i] = array1[i] / array2[i];      // división entera
}

Console.WriteLine("Suma: " + string.Join(", ", suma));             // 11, 22, 33, 44
Console.WriteLine("Resta: " + string.Join(", ", resta));           // 9, 18, 27, 36
Console.WriteLine("Producto: " + string.Join(", ", producto));     // 10, 40, 90, 160
Console.WriteLine("División: " + string.Join(", ", division));     // 10, 10, 10, 10


// ===== 6. Un array no se puede sumar con + =====

// array1 + array2 no suma los elementos: no compila.
// Hay que recorrer las posiciones con un for, como en el apartado anterior.


// Espera a que se pulse una tecla antes de cerrar la consola
Console.ReadKey();
