# Listas en C#

## Qué es una lista

Una **colección que guarda elementos en orden**, como un array, pero con **tamaño dinámico**: se pueden añadir, insertar y quitar elementos en cualquier momento. Se escribe `List<T>`, donde `T` es el tipo de los elementos.

| | Array (`int[]`) | Lista (`List<int>`) |
|---|---|---|
| Tamaño | **Fijo** | **Dinámico** |
| Añadir / quitar | No se puede | `Add`, `Remove`, `Insert`... |
| Tamaño actual | `Length` | **`Count`** |
| Acceso por posición | `a[0]` | `l[0]` |
| Tipo de los elementos | Uno solo | Uno solo (el de `<T>`) |

## Crear una lista

```csharp
List<string> simpsons = new List<string>();
```

```text
List<string>   simpsons   =   new List<string>();
tipo           nombre            instancia
```

- **`List`** es una clase (por eso lleva `new` y paréntesis).
- **`<string>`** indica el **tipo de los elementos**. Es característico de las colecciones genéricas: la misma clase `List` sirve para `string`, `int`, `double`, `bool`, o clases propias.
- Empieza **vacía**: `simpsons.Count` vale `0`.

También se puede crear **con valores iniciales**:

```csharp
List<int> valores = new List<int> { 21, 8, 235, 100, 120 };
```

## Añadir elementos: `Add`

```csharp
simpsons.Add("Homero");
simpsons.Add("Marge");
simpsons.Add("Lisa");
simpsons.Add("Bart");
simpsons.Add("Maggie");

Console.WriteLine(simpsons.Count);    // 5
```

`Add` añade **al final**. La lista crece sola.

- Una `List<string>` solo admite `string`; `simpsons.Add(5)` no compila.
- **Admite duplicados**: se puede añadir `"Homero"` dos veces.

## Recorrer: `foreach`

Un bucle pensado para recorrer colecciones, sin índice:

```csharp
foreach (string nombre in simpsons)
{
    Console.WriteLine(nombre);
}
```

```text
foreach (  string nombre  in  simpsons  )
           └ variable        └ colección
```

En cada vuelta, `nombre` toma el valor del **siguiente elemento**. Es una versión más cómoda del `for` cuando solo se necesita leer cada elemento.

También se puede con `for`, accediendo por posición:

```csharp
for (int i = 0; i < simpsons.Count; i++)
{
    Console.WriteLine($"Posición {i}: {simpsons[i]}");
}
```

Lo que **no** se puede es modificar la lista (añadir o quitar) **mientras** se recorre con `foreach`: lanza una excepción.

## Métodos principales

| Método | Qué hace |
|---|---|
| `Add(valor)` | Añade al final |
| `Insert(posición, valor)` | Inserta en una posición |
| `Remove(valor)` | Quita la **primera** aparición del valor |
| `RemoveAt(posición)` | Quita el elemento de esa posición |
| `Contains(valor)` | ¿Existe? Devuelve `bool` |
| `IndexOf(valor)` | Posición, o `-1` si no está |
| `Sort()` | Ordena de menor a mayor |
| `Reverse()` | Invierte el orden actual |
| `Clear()` | Quita todos los elementos |
| `Count` | Número de elementos (propiedad, sin paréntesis) |

### Insertar: `Insert`

```csharp
simpsons.Insert(0, "Apu");    // Apu pasa a la posición 0 y los demás se desplazan
```

Con `Add` el elemento va siempre al final; con `Insert` se elige dónde.

### Quitar: `Remove` y `RemoveAt`

Los dos quitan elementos, pero **se les da cosas distintas**:

```csharp
valores.Remove(235);     // quita el VALOR 235, esté donde esté
valores.RemoveAt(1);     // quita lo que haya en la POSICIÓN 1
```

- `Remove` busca el valor. Si hay duplicados, **solo quita el primero**. Si no existe, no hace nada (devuelve `false`).
- `RemoveAt` quita por posición. Si la posición no existe, lanza `ArgumentOutOfRangeException`.
- Al quitar, los elementos siguientes se **desplazan** una posición.

### Buscar: `Contains` e `IndexOf`

```csharp
Console.WriteLine(simpsons.Contains("Lisa"));       // True
Console.WriteLine(simpsons.Contains("Moe"));        // False

Console.WriteLine(simpsons.IndexOf("Bart"));        // 3
Console.WriteLine(simpsons.IndexOf("Nelson"));      // -1
```

- `Contains` responde sí o no.
- `IndexOf` da la posición; **`-1` significa "no está"**, igual que en `string` y en arrays.
- Distinguen mayúsculas de minúsculas.

### Ordenar e invertir

```csharp
simpsons.Sort();       // Apu, Bart, Lisa, Maggie, Marge
simpsons.Reverse();    // Marge, Maggie, Lisa, Bart, Apu
```

Como en los arrays, **modifican la propia lista** y `Reverse` no ordena: solo da la vuelta. Para de mayor a menor: `Sort()` y luego `Reverse()`.

### Vaciar: `Clear`

```csharp
simpsons.Clear();
Console.WriteLine(simpsons.Count);    // 0
```

La lista sigue existiendo, pero vacía.

## Mostrar una lista: `string.Join`

```csharp
Console.WriteLine(string.Join(", ", simpsons));    // Apu, Bart, Lisa, Maggie, Marge
```

## Entre array y lista

```csharp
int[] array = valores.ToArray();                  // lista → array
List<int> lista = new List<int>(array);           // array → lista
```

## Cuándo usar cada una

- **Array**: se sabe cuántos elementos hay y no cambian.
- **Lista**: la cantidad **cambia** durante el programa. Es lo más habitual.

## Ejemplo

```csharp
List<string> nombres = new List<string>();

nombres.Add("Sol");
nombres.Add("Marcos");
nombres.Insert(0, "Lucía");

foreach (string nombre in nombres)
{
    Console.WriteLine(nombre);
}

Console.WriteLine("Total: " + nombres.Count);
Console.ReadKey();
```

## Comandos

```bash
cd csharp/05-methods-and-classes
dotnet new console -n Lists -o 33-lists
cd 33-lists
dotnet run
```

Fuente: [curso de C# de INFORMATICONFIG](https://www.youtube.com/watch?v=Lz4jMlSgM_M&list=PL2Z95CSZ1N4F7mym8Ca16RoTDVAlIOPlT&index=33), vídeo 33.
