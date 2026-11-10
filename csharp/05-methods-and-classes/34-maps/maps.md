# Mapas (diccionarios) en C#

## Qué es un mapa

Una colección que guarda **pares clave-valor**. Cada **valor** se asocia a una **clave única**, y para obtenerlo se indica la clave. En C# se llama **diccionario**: `Dictionary<TClave, TValor>`.

Es como un diccionario de papel: se busca por la **palabra** (la clave) y se obtiene su **definición** (el valor).

```text
clave   →  valor
  1     →  "manzanas"
  2     →  "peras"
  3     →  "bananas"
```

## Para qué sirve

- **Buscar datos rápido** por una clave, sin recorrer todo.
- **Sustituir** arrays y listas cuando se necesita acceder por algo más significativo que una posición (un código, un nombre...).
- **Agrupar información relacionada**: producto → precio, país → capital, DNI → persona.

| | Lista | Diccionario |
|---|---|---|
| Se accede por | **Posición** (0, 1, 2...) | **Clave** (la que se elija) |
| Elementos | Valores | Pares clave-valor |
| Duplicados | Sí | Valores sí, **claves no** |

## Crear un diccionario

```csharp
Dictionary<int, string> frutas = new Dictionary<int, string>();
```

```text
Dictionary<  int  ,  string  >   frutas   =   new Dictionary<int, string>();
             └ tipo   └ tipo                      
               clave    valor
```

Se indican **dos tipos** entre `< >`: el de la clave y el del valor. La clave **no puede ser `null`**.

También se puede crear con valores iniciales:

```csharp
Dictionary<string, int> stock = new Dictionary<string, int>
{
    { "pan", 20 },
    { "leche", 12 },
    { "huevos", 30 }
};
```

## Añadir pares: `Add`

```csharp
frutas.Add(1, "manzanas");
frutas.Add(2, "peras");
frutas.Add(3, "bananas");

Console.WriteLine(frutas.Count);    // 3
```

La clave puede ser cualquier valor del tipo indicado; no tiene por qué empezar en 1 ni ser correlativa (`45` valdría igual).

## Leer por clave

Con **corchetes**, como en un array, pero **dentro va la clave**, no una posición:

```csharp
Console.WriteLine(frutas[2]);    // peras
```

Si la clave **no existe**, lanza `KeyNotFoundException`:

```csharp
Console.WriteLine(frutas[9]);    // KeyNotFoundException
```

## Recorrer: `foreach`

Cada elemento es un **par** con dos propiedades: `Key` y `Value`.

```csharp
foreach (var par in frutas)
{
    Console.WriteLine($"Clave: {par.Key}, Valor: {par.Value}");
}
```

```text
Clave: 1, Valor: manzanas
Clave: 2, Valor: peras
...
```

- **`var`** deja que el compilador deduzca el tipo del par (`KeyValuePair<int, string>`).
- `par.Key` es la clave y `par.Value` el valor.

## Las claves son únicas

| Instrucción | Si la clave **ya existe** | Si **no existe** |
|---|---|---|
| `Add(clave, valor)` | **Error**: `ArgumentException` | Crea el par |
| `diccionario[clave] = valor` | **Reemplaza** el valor | Crea el par |

```csharp
frutas[2] = "peras verdes";    // reemplaza el valor de la clave 2
frutas[6] = "kiwis";           // crea un par nuevo
```

El indexador `[ ]` sirve para las dos cosas; `Add` es más estricto y avisa si se duplica una clave por error.

## Métodos principales

| Método | Qué hace |
|---|---|
| `Add(clave, valor)` | Añade un par (falla si la clave existe) |
| `dic[clave]` | Lee, o asigna, el valor de una clave |
| `ContainsKey(clave)` | ¿Existe la **clave**? |
| `ContainsValue(valor)` | ¿Existe el **valor**? |
| `TryGetValue(clave, out v)` | Lee sin riesgo de error |
| `Remove(clave)` | Quita el par de esa clave |
| `Keys` / `Values` | Todas las claves / todos los valores |
| `Count` | Número de pares |
| `Clear()` | Quita todo |

### `ContainsKey` y `ContainsValue`

```csharp
Console.WriteLine(frutas.ContainsKey(3));          // True
Console.WriteLine(frutas.ContainsKey(9));          // False
Console.WriteLine(frutas.ContainsValue("uvas"));   // True
```

Cuidado con confundirlos: `ContainsKey(3)` busca la **clave** 3, y `ContainsValue("uvas")` busca el **valor** `"uvas"`. `ContainsKey` es **muy rápido**; `ContainsValue` recorre todos los valores.

### `TryGetValue`: leer sin error

Evita la excepción cuando la clave puede no existir. Devuelve `true` o `false`, y el valor sale por el parámetro `out`:

```csharp
if (frutas.TryGetValue(9, out string? fruta))
{
    Console.WriteLine("Clave 9: " + fruta);
}
else
{
    Console.WriteLine("La clave 9 no existe");
}
```

Para un valor por defecto en una sola línea: `stock.GetValueOrDefault("sal", 0)`.

### `Remove` y `Clear`

```csharp
frutas.Remove(1);    // quita el par de la clave 1
frutas.Clear();      // quita todos los pares
```

`Remove` recibe la **clave**, no el valor ni la posición.

## Claves de texto

Lo más habitual es usar **texto** como clave:

```csharp
Dictionary<string, int> stock = new Dictionary<string, int>
{
    { "pan", 20 }, { "leche", 12 }, { "huevos", 30 }
};

Console.WriteLine(stock["leche"]);    // 12
stock["leche"] -= 2;                  // modifica el valor de esa clave
Console.WriteLine(stock["leche"]);    // 10
```

Las claves de texto distinguen mayúsculas de minúsculas: `"Leche"` y `"leche"` son claves distintas.

## Orden

Un `Dictionary` **no garantiza el orden** de los pares. En la práctica suele respetar el de inserción si no se quitan elementos, pero no se debe depender de ello. Si el orden importa, `SortedDictionary` mantiene las claves ordenadas.

## Ejemplo

```csharp
Dictionary<string, string> capitales = new Dictionary<string, string>();

capitales.Add("España", "Madrid");
capitales.Add("Francia", "París");

Console.WriteLine(capitales["España"]);

foreach (var par in capitales)
{
    Console.WriteLine($"{par.Key}: {par.Value}");
}

Console.ReadKey();
```

## Comandos

```bash
cd csharp/05-methods-and-classes
dotnet new console -n Maps -o 34-maps
cd 34-maps
dotnet run
```

Fuente: [curso de C# de INFORMATICONFIG](https://www.youtube.com/watch?v=SJk2uRIEqV0&list=PL2Z95CSZ1N4F7mym8Ca16RoTDVAlIOPlT&index=34), vídeo 34.
