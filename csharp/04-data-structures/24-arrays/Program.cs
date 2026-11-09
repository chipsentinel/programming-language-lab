// Un array es una estructura que guarda varios valores DEL MISMO TIPO en una sola variable.
// Cada valor (elemento) tiene una posición, y la primera posición es la 0.


// ===== 1. Declarar un array =====

// tipo[] nombre = new tipo[cantidad];
int[] enteros = new int[5];          // 5 enteros
string[] nombres = new string[5];    // 5 textos
double[] precios = new double[5];    // 5 decimales

// Un array nuevo se rellena con el valor por defecto de su tipo
Console.WriteLine("Posición 2 de enteros: " + enteros[2]);   // 0
Console.WriteLine("Posición 2 de precios: " + precios[2]);   // 0
Console.WriteLine("Posición 2 de nombres está vacía: " + (nombres[2] == null));   // True


// ===== 2. Guardar y leer valores por posición =====

enteros[0] = 30;
enteros[1] = 6;
enteros[2] = 112;
enteros[3] = 20000;
enteros[4] = 60;

Console.WriteLine("enteros[2] = " + enteros[2]);   // 112
Console.WriteLine("enteros[4] = " + enteros[4]);   // 60

// Con 5 elementos las posiciones válidas son 0, 1, 2, 3 y 4
// enteros[5] = 1;   // error en ejecución: IndexOutOfRangeException

// Un array solo guarda el tipo declarado
// enteros[0] = 4.8;      // error: un decimal no cabe en un int
// enteros[0] = "hola";   // error: un texto no cabe en un int


// ===== 3. Crear un array con sus valores: inicializador =====

// Sin indicar el tamaño ni las posiciones: se calculan solas, empezando en 0
int[] numeros = { 20, 15, 18, 93, 36, 50, 1, 2, 7, 4 };

Console.WriteLine("numeros[0] = " + numeros[0]);   // 20
Console.WriteLine("numeros[3] = " + numeros[3]);   // 93

string[] personas = { "Juan", "Ana", "Antonio", "Samuel", "Elizabeth" };


// ===== 4. Longitud: Length =====

Console.WriteLine("Cantidad de elementos: " + numeros.Length);   // 10


// ===== 5. Recorrer un array con for =====

for (int i = 0; i < numeros.Length; i++)
{
    Console.WriteLine($"numeros[{i}] = {numeros[i]}");
}


// ===== 6. Mostrar todo el array: string.Join =====

Console.WriteLine("Array original: " + string.Join(", ", numeros));


// ===== 7. Ordenar: Array.Sort =====

Array.Sort(numeros);   // cambia el propio array, de menor a mayor
Console.WriteLine("Array organizado: " + string.Join(", ", numeros));

Array.Sort(personas);  // con textos, orden alfabético
Console.WriteLine("Nombres organizados: " + string.Join(", ", personas));


// ===== 8. Invertir: Array.Reverse =====

Array.Reverse(numeros);   // da la vuelta al orden actual
Console.WriteLine("Array al revés: " + string.Join(", ", numeros));


// ===== 9. Buscar: Array.IndexOf =====

// Devuelve la posición, o -1 si no está (igual que en string)
Console.WriteLine("Posición de Luis: " + Array.IndexOf(personas, "Luis"));       // -1
Console.WriteLine("Posición de Ana: " + Array.IndexOf(personas, "Ana"));         // 0
Console.WriteLine("Posición de Samuel: " + Array.IndexOf(personas, "Samuel"));   // 4


// ===== 10. Rellenar con un valor: Array.Fill =====

Array.Fill(numeros, 99);
Console.WriteLine("Array llenado con 99: " + string.Join(", ", numeros));


// ===== 11. Vaciar: Array.Clear =====

// Deja cada elemento en su valor por defecto (en un string[], vacío)
Array.Clear(personas);
Console.WriteLine("Array limpio: " + string.Join(", ", personas));   // solo quedan las comas


// ===== 12. El tamaño no cambia =====

// Un array tiene un tamaño fijo desde que se crea: no se pueden añadir elementos.
// Para colecciones que crecen se usan las listas (lección 33).


// Espera a que se pulse una tecla antes de cerrar la consola
Console.ReadKey();
