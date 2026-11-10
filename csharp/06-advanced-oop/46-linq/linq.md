# LINQ en C#

## Qué es LINQ

**LINQ** (*Language Integrated Query*, "consulta integrada en el lenguaje") es una característica de C# que permite hacer **consultas sobre colecciones de datos** escribiéndolas en el propio lenguaje.

Funciona con listas, arrays, bases de datos, archivos XML... Con LINQ se puede:

- **Filtrar** (quedarse con los que cumplen algo).
- **Ordenar**.
- **Transformar** (proyectar) los datos.
- **Agrupar**.
- **Calcular** totales, medias, máximos...

Todo eso existe también en **SQL** (el lenguaje de las bases de datos, que se verá más adelante), y LINQ se le parece mucho, pero escrito dentro de C#.

Está en el espacio de nombres `System.Linq`, que el proyecto ya incluye (`ImplicitUsings`).

## Los datos de partida

```csharp
List<string> nombres = new List<string> { "Ana", "Carlos", "Pedro", "Clara", "Andrés" };
```

## Sintaxis de consulta: `from … where … select`

```csharp
var resultado =
    from nombre in nombres
    where nombre.StartsWith("C")
    orderby nombre
    select nombre;

foreach (var f in resultado)
{
    Console.WriteLine(f);        // Carlos, Clara
}
```

Se lee casi como una frase: *"desde cada `nombre` de `nombres`, donde empiece por C, ordenado por nombre, selecciona el nombre"*.

| Palabra | Qué hace |
|---|---|
| `from x in colección` | Recorre la colección: `x` es **cada elemento** (un nombre temporal; puede llamarse `n`, `f`, `x`...) |
| `where condición` | **Filtra**: se queda con los que cumplen la condición |
| `orderby x` | **Ordena** de forma ascendente (`orderby x descending` para descendente) |
| `select x` | Qué se **devuelve** (puede ser el elemento o una transformación) |

- El resultado se guarda en una variable de tipo **`var`**: el compilador deduce el tipo (lección 5).
- Se recorre con un `foreach` (lección 33).
- La condición de `where` es cualquier expresión `bool`: `n % 2 == 0` para los pares.

```csharp
var pares = from n in numeros where n % 2 == 0 select n;
```

## Sintaxis de métodos

Lo mismo se puede escribir **encadenando métodos**, y es la forma que más se usa:

```csharp
var resultado = nombres.Where(n => n.StartsWith("C")).OrderBy(n => n);
```

`n => n.StartsWith("C")` es una **expresión lambda**: "dado `n`, devuelve si empieza por C". La flecha `=>` separa el parámetro de la expresión. En la sintaxis de consulta se escribe `where` y en la de métodos, `Where`.

| Consulta | Método |
|---|---|
| `where` | `Where(...)` |
| `orderby` | `OrderBy(...)` / `OrderByDescending(...)` |
| `select` | `Select(...)` |

## Operaciones más usadas

Con `numeros = { 10, 20, 30, 40, 50, 60, 1 }`:

| Operación | Método | Resultado |
|---|---|---|
| Filtrar | `numeros.Where(n => n > 25)` | `30, 40, 50, 60` |
| Transformar | `numeros.Select(n => n * 2)` | `20, 40, 60, 80, 100, 120, 2` |
| Ordenar descendente | `numeros.OrderByDescending(n => n)` | `60, 50, ..., 1` |
| Contar | `numeros.Count()` | `7` |
| Sumar | `numeros.Sum()` | `211` |
| Media | `numeros.Average()` | `30,142857...` |
| Máximo | `numeros.Max()` | `60` |
| Mínimo | `numeros.Min()` | `1` |
| ¿Alguno? | `numeros.Any(n => n > 50)` | `True` |
| ¿Todos? | `numeros.All(n => n > 0)` | `True` |
| Primero | `numeros.First(n => n > 25)` | `30` |
| N primeros | `numeros.Take(3)` | `10, 20, 30` |
| Saltar N | `numeros.Skip(5)` | `60, 1` |
| Sin repetidos | `Distinct()` | |

### Transformar: `Select`

`Select` **cambia cada elemento** por otra cosa:

```csharp
numeros.Select(n => n * 2);            // el doble de cada número
personas.Select(p => p.Nombre);        // solo los nombres
```

### Cálculos

`Count`, `Sum`, `Average`, `Max` y `Min` devuelven **un único valor**. Con objetos, se indica qué propiedad usar:

```csharp
personas.Average(p => p.Edad);         // 26,4
```

### `First` y las preguntas

`First(condición)` devuelve el primero que cumple, pero **lanza una excepción** si no hay ninguno. Con `FirstOrDefault` devuelve el valor por defecto (`0`, `null`) en ese caso.

## Agrupar: `GroupBy`

```csharp
foreach (var grupo in nombres.GroupBy(n => n[0]))
{
    Console.WriteLine($"{grupo.Key}: {string.Join(", ", grupo)}");
}
```

```text
A: Ana, Andrés
C: Carlos, Clara
P: Pedro
```

`GroupBy` reparte los elementos según un criterio (aquí, la primera letra). Cada grupo tiene una **`Key`** (la clave del grupo) y contiene sus elementos. Es el `GROUP BY` de SQL.

## Con objetos

LINQ brilla con **listas de objetos**:

```csharp
record Persona(string Nombre, int Edad);

List<Persona> personas = new List<Persona>
{
    new Persona("Sol", 34),
    new Persona("Marcos", 28),
    new Persona("Lucía", 8),
    new Persona("Pedro", 17),
    new Persona("Marta", 45)
};

var mayores = personas
    .Where(p => p.Edad >= 18)
    .OrderBy(p => p.Edad)
    .Select(p => p.Nombre);

Console.WriteLine(string.Join(", ", mayores));     // Marcos, Sol, Marta
```

En SQL sería: `SELECT nombre FROM personas WHERE edad >= 18 ORDER BY edad`.

> Un `record` es una clase sencilla pensada para guardar datos; se verá con más detalle más adelante.

## Ejecución diferida

Una consulta **no se ejecuta cuando se escribe**, sino **cuando se recorre**:

```csharp
var empiezanPorC = nombres.Where(n => n.StartsWith("C"));    // aún no se ha ejecutado

nombres.Add("Cecilia");                                       // se añade después...

Console.WriteLine(string.Join(", ", empiezanPorC));           // Carlos, Clara, Cecilia ← incluye a Cecilia
```

La consulta se ejecuta **en ese momento** y ve los datos **actuales**. Para ejecutarla ya y guardar el resultado se usa `ToList()` o `ToArray()`:

```csharp
List<string> fijada = nombres.Where(n => n.StartsWith("C")).ToList();
```

Esto explica por qué hay que tener cuidado si los datos cambian entre que se define la consulta y se usa.

## Resumen

| Quiero... | Escribo... |
|---|---|
| Quedarme con unos | `.Where(x => condición)` |
| Cambiar cada elemento | `.Select(x => ...)` |
| Ordenar | `.OrderBy(x => x)` / `.OrderByDescending(...)` |
| Agrupar | `.GroupBy(x => clave)` |
| Calcular | `.Count()`, `.Sum()`, `.Average()`, `.Max()`, `.Min()` |
| Preguntar | `.Any(...)`, `.All(...)` |
| Guardar el resultado | `.ToList()` / `.ToArray()` |

## Ejemplo

```csharp
List<int> numeros = new List<int> { 10, 20, 30, 40, 50, 60, 1 };

var pares = from n in numeros
            where n % 2 == 0
            select n;

Console.WriteLine("Pares: " + string.Join(", ", pares));
Console.WriteLine("Suma de los pares: " + pares.Sum());

Console.ReadKey();
```

## Comandos

```bash
cd csharp/06-advanced-oop
dotnet new console -n Linq -o 46-linq
cd 46-linq
dotnet run
```

Fuente: [curso de C# de INFORMATICONFIG](https://www.youtube.com/watch?v=lvat7qWZ6tw&list=PL2Z95CSZ1N4F7mym8Ca16RoTDVAlIOPlT&index=46), vídeo 46.
