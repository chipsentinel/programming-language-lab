# Operaciones con arrays en C#

## Qué se ve en esta lección

Operaciones habituales con arrays: **sumar** sus valores, **filtrar** los que cumplen una condición, **copiar** un array y **operar dos arrays** posición a posición.

```csharp
int[] numeros = { 12, 233, 5, 658, 45555 };
```

Las funciones `Sum`, `Max`, `Where`... pertenecen a **LINQ**, un conjunto de herramientas para trabajar con colecciones. Se activan con `using System.Linq;`, que los proyectos de este curso ya incluyen por defecto (`ImplicitUsings`). LINQ se verá en la lección 46.

## Sumar: `Sum`

Suma todos los elementos:

```csharp
int total = numeros.Sum();
Console.WriteLine("Total de la suma de valores: " + total);   // 46463
```

Es lo mismo que `12 + 233 + 5 + 658 + 45555`, sin escribir un bucle.

## Máximo, mínimo y media

```csharp
Console.WriteLine(numeros.Max());       // 45555
Console.WriteLine(numeros.Min());       // 5
Console.WriteLine(numeros.Average());   // 9292,6
```

`Average` devuelve un `double` aunque el array sea de enteros.

## Filtrar: `Where`

Se queda con los elementos que **cumplen una condición**:

```csharp
int[] mayoresQue12 = numeros.Where(x => x > 12).ToArray();

Console.WriteLine(string.Join(", ", mayoresQue12));   // 233, 658, 45555
```

- **`x => x > 12`** es una **expresión lambda**: "para cada elemento `x`, ¿es `x` mayor que 12?". Las lambdas se explicarán más adelante; por ahora basta con saber que lo que hay después de la flecha `=>` es la condición.
- `Where` devuelve una **secuencia**, no un array. **`ToArray()`** la convierte de nuevo en un array. Sin él, `string.Join` o asignarlo a un `int[]` no funcionaría.
- El 12 no aparece: la condición es `>`, no `>=`.

## Copiar un array

### Asignar no copia

```csharp
int[] alias = numeros;
alias[0] = 999;

Console.WriteLine(numeros[0]);   // 999 ← ¡también cambió!
```

Un array es un **objeto**. `alias = numeros` no crea otro array: hace que `alias` apunte **al mismo**. Cambiar uno cambia el otro.

### `Clone`: una copia independiente

```csharp
int[] cantidades = (int[])numeros.Clone();
cantidades[0] = 1;

Console.WriteLine(string.Join(", ", numeros));      // 12, 233, 5, 658, 45555
Console.WriteLine(string.Join(", ", cantidades));   // 1, 233, 5, 658, 45555
```

`Clone` devuelve un `object`, por eso se escribe delante `(int[])`: es un casting explícito (lección 9) que le indica el tipo al compilador. Después, cambiar `cantidades` no afecta a `numeros`.

## Operar dos arrays elemento a elemento

Con dos arrays de la misma longitud:

```csharp
int[] array1 = { 10, 20, 30, 40 };
int[] array2 = { 1, 2, 3, 4 };
```

C# **no permite** `array1 + array2`: no suma posición a posición. Hay que recorrerlos con un `for` y guardar cada resultado en un array nuevo:

```csharp
int[] suma = new int[array1.Length];

for (int i = 0; i < array1.Length; i++)
{
    suma[i] = array1[i] + array2[i];
}

Console.WriteLine(string.Join(", ", suma));   // 11, 22, 33, 44
```

```text
posición:     0    1    2    3
array1:       10   20   30   40
array2:       1    2    3    4
suma:         11   22   33   44
```

El resultado de la posición `i` sale de la posición `i` de cada array.

| Operación | Resultado con los arrays de arriba |
|---|---|
| `array1[i] + array2[i]` | `11, 22, 33, 44` |
| `array1[i] - array2[i]` | `9, 18, 27, 36` |
| `array1[i] * array2[i]` | `10, 40, 90, 160` |
| `array1[i] / array2[i]` | `10, 10, 10, 10` |

- El array de resultado se crea con la **misma longitud** que los originales: `new int[array1.Length]`.
- Con **longitudes distintas** el `for` recorrería posiciones que no existen en el más corto y fallaría con `IndexOutOfRangeException`.
- En la división entre enteros se descartan los decimales.

## Ejemplo

```csharp
int[] numeros = { 12, 233, 5, 658, 45555 };

Console.WriteLine("Suma: " + numeros.Sum());
Console.WriteLine("Mayores que 12: " + string.Join(", ", numeros.Where(x => x > 12)));

int[] copia = (int[])numeros.Clone();
Console.WriteLine("Copia: " + string.Join(", ", copia));

Console.ReadKey();
```

## Comandos

```bash
cd csharp/04-data-structures
dotnet new console -n ArrayOperations -o 25-array-operations
cd 25-array-operations
dotnet run
```

Fuente: [curso de C# de INFORMATICONFIG](https://www.youtube.com/watch?v=bo6NbdeYJRY&list=PL2Z95CSZ1N4F7mym8Ca16RoTDVAlIOPlT&index=25), vídeo 25.
