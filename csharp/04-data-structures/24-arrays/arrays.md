# Arrays en C#

## Qué es un array

Una estructura de datos que **guarda varios valores del mismo tipo en una sola variable**. En lugar de crear `precio1`, `precio2`, `precio3`..., se crea un único array `precios` con todos.

- Todos los elementos son del **mismo tipo**: `int[]` solo guarda enteros.
- Cada elemento tiene una **posición** (índice), y **la primera es la 0**.
- El **tamaño es fijo**: se decide al crearlo y no cambia.

## Declarar un array

```csharp
int[] enteros = new int[5];          // 5 enteros
string[] nombres = new string[5];    // 5 textos
double[] precios = new double[5];    // 5 decimales
```

```text
tipo[]  nombre  =  new  tipo[cantidad];
```

- Los **corchetes `[]` van junto al tipo**: `int[]` se lee "array de enteros".
- `new` reserva la memoria; se explicará con las clases.
- El número entre corchetes es la **cantidad de elementos**.

Un array recién creado se rellena con el **valor por defecto** del tipo:

| Tipo | Valor por defecto |
|---|---|
| `int` | `0` |
| `double` | `0` |
| `bool` | `false` |
| `string` | `null` (vacío) |

## Guardar y leer por posición

```csharp
enteros[0] = 30;
enteros[1] = 6;
enteros[2] = 112;
enteros[3] = 20000;
enteros[4] = 60;

Console.WriteLine(enteros[2]);   // 112
```

```text
posición:  0     1    2     3       4
valor:     30    6    112   20000   60
```

- Sin la posición, `Console.WriteLine(enteros)` no muestra los valores: hay que decir **cuál** se quiere ver.
- Con 5 elementos, las posiciones válidas son **0 a 4**. La 5 **no existe**.

### Fuera de rango

Usar una posición que no existe da un error en ejecución:

```csharp
enteros[5] = 1;   // IndexOutOfRangeException
```

La causa más habitual es contar desde 1. Un array de `n` elementos llega hasta la posición `n - 1`.

### Un solo tipo

```csharp
enteros[0] = 4.8;      // error: un decimal no cabe en un int
enteros[0] = "hola";   // error: un texto no cabe en un int
```

## Crear un array con sus valores

Si ya se conocen los valores, se escriben entre llaves. El tamaño se calcula solo y las posiciones empiezan en 0:

```csharp
int[] numeros = { 20, 15, 18, 93, 36, 50, 1, 2, 7, 4 };
string[] personas = { "Juan", "Ana", "Antonio", "Samuel", "Elizabeth" };

Console.WriteLine(numeros[0]);   // 20
Console.WriteLine(numeros[3]);   // 93
```

Ya no hay que escribir `new int[...]`, ni las posiciones una a una. Todos los valores deben ser del tipo del array.

## Longitud: `Length`

El número de elementos:

```csharp
Console.WriteLine(numeros.Length);   // 10
```

La última posición es siempre `Length - 1`.

## Recorrer un array

Con un `for`, usando `Length` como límite:

```csharp
for (int i = 0; i < numeros.Length; i++)
{
    Console.WriteLine($"numeros[{i}] = {numeros[i]}");
}
```

- `i` empieza en 0 y llega a `Length - 1`: `i < numeros.Length`.
- Es la mejor forma de **hacer algo con cada elemento**.

## Mostrar todo en una línea: `string.Join`

Une los elementos con el separador indicado:

```csharp
Console.WriteLine(string.Join(", ", numeros));
// 20, 15, 18, 93, 36, 50, 1, 2, 7, 4
```

Es más corto que un `for`, pero **solo sirve para mostrar**. Si hay que operar con cada elemento, se usa el `for`.

## Funciones de la clase `Array`

| Función | Qué hace |
|---|---|
| `Array.Sort(a)` | Ordena de menor a mayor (alfabético en textos) |
| `Array.Reverse(a)` | Invierte el orden actual |
| `Array.IndexOf(a, valor)` | Posición del valor, o `-1` |
| `Array.Fill(a, valor)` | Rellena todo con un valor |
| `Array.Clear(a)` | Deja cada elemento en su valor por defecto |

```csharp
Array.Sort(numeros);
Console.WriteLine(string.Join(", ", numeros));   // 1, 2, 4, 7, 15, 18, 20, 36, 50, 93

Array.Reverse(numeros);
Console.WriteLine(string.Join(", ", numeros));   // 93, 50, 36, 20, 18, 15, 7, 4, 2, 1
```

- **Cambian el propio array**: después de `Sort`, el orden original se pierde.
- Para tener de mayor a menor: `Sort` y luego `Reverse`. `Reverse` solo invierte lo que haya, **no ordena**.
- Con textos, `Sort` ordena por la primera letra y después las siguientes: `Ana, Antonio, Elizabeth, Juan, Samuel`.

### Buscar

```csharp
Console.WriteLine(Array.IndexOf(personas, "Ana"));    // posición donde está
Console.WriteLine(Array.IndexOf(personas, "Luis"));   // -1: no existe
```

El `-1` significa "no encontrado", igual que en `string.IndexOf`.

### Rellenar y vaciar

```csharp
Array.Fill(numeros, 99);       // 99, 99, 99, ... en todas las posiciones
Array.Clear(personas);         // todos a null
```

Tras `Clear`, `string.Join(", ", personas)` solo muestra las comas, porque los elementos están vacíos. El array **conserva su tamaño**: no se pueden quitar posiciones.

## Tamaño fijo

Un array no puede crecer ni encogerse. Para colecciones a las que se añaden o quitan elementos existen las **listas** (lección 33).

## Ejemplo

```csharp
int[] numeros = { 20, 15, 18, 93, 36 };

Console.WriteLine("Elementos: " + numeros.Length);

Array.Sort(numeros);
Console.WriteLine("Ordenado: " + string.Join(", ", numeros));

for (int i = 0; i < numeros.Length; i++)
{
    Console.WriteLine($"Posición {i}: {numeros[i]}");
}

Console.ReadKey();
```

## Comandos

```bash
cd csharp/04-data-structures
dotnet new console -n Arrays -o 24-arrays
cd 24-arrays
dotnet run
```

Fuente: [curso de C# de INFORMATICONFIG](https://www.youtube.com/watch?v=MiOWZ0s3Wx8&list=PL2Z95CSZ1N4F7mym8Ca16RoTDVAlIOPlT&index=24), vídeo 24.
