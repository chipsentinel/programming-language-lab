# Tipos de datos en C#

## Qué es un tipo de dato

Un tipo de dato indica **qué clase de valor** puede guardar una variable, **cuánta memoria** ocupa y **qué operaciones** se pueden hacer con ella. C# es un lenguaje de tipado estático: el tipo se decide al escribir el código y el compilador comprueba que se use correctamente.

```csharp
int edad = 25;          // número entero
string nombre = "Sol";  // texto
```

## Tipos de datos integrados

C# incluye de serie estos tipos, agrupados así:

```text
Tipos de datos integrados
├── Simples
│   ├── Enteros con signo:  sbyte, short, int, long
│   ├── Enteros sin signo:  byte, ushort, uint, ulong
│   ├── Decimales:          float, double, decimal
│   ├── Carácter:           char
│   └── Lógico:             bool
├── object
├── string
└── dynamic
```

Los tipos **simples** guardan el valor directamente. `object`, `string` y `dynamic` son tipos por referencia.

## Tipos simples

### Enteros

Guardan números sin decimales. Los tipos con signo admiten negativos; los que empiezan por `u` (*unsigned*) solo positivos y el cero, y por eso llegan al doble de máximo. `byte` es la excepción: no tiene signo, y su versión con signo es `sbyte`.

| Tipo | Tamaño | Rango |
|---|---|---|
| `sbyte` | 8 bits | −128 a 127 |
| `byte` | 8 bits | 0 a 255 |
| `short` | 16 bits | −32.768 a 32.767 |
| `ushort` | 16 bits | 0 a 65.535 |
| `int` | 32 bits | −2.147.483.648 a 2.147.483.647 |
| `uint` | 32 bits | 0 a 4.294.967.295 |
| `long` | 64 bits | −9.223.372.036.854.775.808 a 9.223.372.036.854.775.807 |
| `ulong` | 64 bits | 0 a 18.446.744.073.709.551.615 |

`int` es el entero por defecto.

```csharp
byte nivel = 255;
short anio = 2025;
int edad = 25;
long poblacion = 8000000000L;   // la L indica que es long
```

### Decimales

| Tipo | Tamaño | Precisión | Sufijo | Uso habitual |
|---|---|---|---|---|
| `float` | 32 bits | ~6–9 dígitos | `f` | Gráficos, juegos |
| `double` | 64 bits | ~15–17 dígitos | (ninguno) | **El decimal por defecto** |
| `decimal` | 128 bits | 28–29 dígitos | `m` | **Dinero y cálculos financieros** |

```csharp
float temperatura = 36.6f;
double pi = 3.14159;
decimal precio = 19.99m;
```

- El separador decimal es el **punto** (`3.14`), no la coma.
- El sufijo es obligatorio en `float` y `decimal`: un número como `19.99` se interpreta como `double`, y el compilador no lo convierte solo.
- `decimal` es más lento, pero evita los errores de redondeo que tienen `float` y `double` al sumar decimales, por eso se usa con dinero.

### Carácter: `char`

Guarda **un solo** carácter, entre comillas **simples**.

```csharp
char letra = 'S';
```

### Lógico: `bool`

Solo admite `true` o `false`. Se usa en condiciones y comparaciones.

```csharp
bool esMayorDeEdad = true;
```

## `string`: texto

Guarda una cadena de texto, entre comillas **dobles**. Es un tipo por referencia e inmutable: al modificarlo se crea una cadena nueva, no se cambia la original.

```csharp
string nombre = "Sol";
```

| Tipo | Comillas | Ejemplo |
|---|---|---|
| `char` | Simples `' '` | `'S'` |
| `string` | Dobles `" "` | `"Sol"` |

## `object`

Es el tipo base del que derivan todos los demás: una variable `object` puede guardar cualquier valor.

```csharp
object dato = 25;
dato = "Sol";       // válido
```

Para usar el valor con sus operaciones propias hay que convertirlo de nuevo a su tipo (lo verás en la lección de casting).

## `dynamic`

Con `dynamic` el compilador **no comprueba el tipo**: se resuelve al ejecutar el programa. Un error de tipo no aparece al compilar, sino al ejecutar.

```csharp
dynamic dato = 25;
dato = "Sol";       // válido
```

Se usa en casos concretos, como interoperabilidad con otros lenguajes. En general se prefiere un tipo concreto.

## Alias de .NET

Las palabras de C# (`int`, `string`…) son alias de tipos de .NET (`System`):

| C# | .NET |
|---|---|
| `int` | `System.Int32` |
| `long` | `System.Int64` |
| `double` | `System.Double` |
| `bool` | `System.Boolean` |
| `string` | `System.String` |

Se escribe siempre la palabra de C#, que es lo habitual.

## Mostrar valores, tipos y límites

`GetType()` devuelve el tipo de una variable. `MinValue` y `MaxValue` son los límites de un tipo; se piden **al tipo**, no a la variable (`int.MaxValue`, no `edad.MaxValue`).

```csharp
Console.WriteLine($"int edad = {edad}");
Console.WriteLine($"Tipo de edad: {edad.GetType()}");   // System.Int32
Console.WriteLine($"int:     {int.MinValue} a {int.MaxValue}");
```

El `$` delante de las comillas permite escribir variables entre llaves `{}` dentro del texto (interpolación; se ve en la lección 06).

Si una variable no se muestra ni se usa, el compilador avisa de que su valor nunca se utiliza.

## `var`: tipo inferido

Con `var` el compilador deduce el tipo a partir del valor. El tipo **sigue siendo fijo**; no cambia después (a diferencia de `dynamic`).

```csharp
var edad = 25;        // int
var precio = 19.99;   // double
var nombre = "Sol";   // string
```

## Valores por defecto y `null`

- Los tipos numéricos valen `0` por defecto, `bool` vale `false`.
- `string` y los demás tipos por referencia pueden valer `null` (sin valor).
- Para que un tipo por valor admita `null` se añade `?`: `int? edad = null;`.

## Resumen

| Necesito guardar… | Uso |
|---|---|
| Un número entero | `int` (o `long` si es muy grande) |
| Un número con decimales | `double` (o `decimal` si es dinero) |
| Verdadero o falso | `bool` |
| Una letra | `char` |
| Un texto | `string` |
| Cualquier valor | `object` |

## Comandos

```bash
cd csharp/02-fundamentals
dotnet new console -n DataTypes -o 04-data-types
cd 04-data-types
dotnet run
```

Fuente: [curso de C# de INFORMATICONFIG](https://www.youtube.com/watch?v=bSpDgfa07D4&list=PL2Z95CSZ1N4F7mym8Ca16RoTDVAlIOPlT&index=4), vídeo 04.
