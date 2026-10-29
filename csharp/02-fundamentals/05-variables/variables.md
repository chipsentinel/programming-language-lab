# Variables en C#

## Qué es una variable

Una variable es un **espacio con nombre** en la memoria donde el programa guarda un valor. El valor puede cambiar mientras el programa se ejecuta; el tipo, no.

```csharp
int edad = 25;
```

```text
int      edad     =    25;
tipo     nombre   =    valor
```

## Declarar, asignar e inicializar

Son tres operaciones distintas:

```csharp
int edad;           // declarar: se reserva el espacio con su tipo
edad = 25;          // asignar: se guarda un valor
int anio = 2025;    // inicializar: declarar y asignar a la vez
```

C# no permite usar una variable que no tenga valor asignado:

```csharp
int edad;
Console.WriteLine(edad);   // error: uso de variable local sin asignar
```

## Cambiar el valor

Se puede reasignar con `=` las veces que haga falta, siempre con un valor del **mismo tipo**.

```csharp
int edad = 25;
edad = 26;          // válido
edad = "veintiséis"; // error: no se puede convertir string a int
```

Una variable puede tomar el valor de otra:

```csharp
int a = 5;
int b = a;          // b vale 5
```

## Declarar varias a la vez

Si son del mismo tipo, se separan por comas:

```csharp
int x = 1, y = 2, z = 3;
```

## `var`: tipo inferido

El compilador deduce el tipo del valor inicial. Obliga a inicializar la variable en la misma línea y el tipo queda fijo.

```csharp
var nombre = "Sol";   // string
var edad = 25;        // int
var nota = 8.5;       // double
```

Ver la lección 04 para la diferencia con `dynamic`.

## Reglas para los nombres

- Pueden contener letras, números y guion bajo `_`.
- **No pueden empezar por un número**: `1edad` no es válido.
- No pueden contener espacios ni símbolos (`-`, `@`, `$`…): `mi-edad` no es válido.
- **Distinguen mayúsculas de minúsculas**: `edad` y `Edad` son variables distintas.
- No pueden ser palabras reservadas (`int`, `class`, `string`…), salvo que se escriba `@` delante (`@class`), algo poco habitual.

| Válido | No válido |
|---|---|
| `edad` | `1edad` |
| `_total` | `mi edad` |
| `nombreCompleto` | `nombre-completo` |
| `precio2` | `int` |

## Convenciones

Las convenciones no las exige el compilador, pero son las que sigue todo el mundo en C#:

| Qué | Estilo | Ejemplo |
|---|---|---|
| Variables locales | `camelCase` | `nombreCompleto` |
| Nombres descriptivos | Mejor claros que cortos | `edad` en lugar de `e` |

## Mostrar variables

El `$` delante de las comillas permite escribir variables entre llaves dentro del texto (interpolación):

```csharp
string nombre = "Sol";
int edad = 25;

Console.WriteLine($"Me llamo {nombre} y tengo {edad} años.");
```

## Ejemplo del ejercicio

Una variable de cada tipo habitual, inicializada y mostrada por consola:

```csharp
string nombre = "informaticonfig";
int edad = 30;
bool activo = true;
DateTime fecha = DateTime.Now;
float precio = 30.5f;
decimal descuento = 4.6m;
double descuento2 = 10.50;

Console.WriteLine($"nombre = {nombre}");
Console.WriteLine($"edad = {edad}");
Console.WriteLine($"activo = {activo}");
Console.WriteLine($"fecha = {fecha}");
Console.WriteLine($"precio = {precio}");
Console.WriteLine($"descuento = {descuento}");
Console.WriteLine($"descuento2 = {descuento2}");

Console.ReadKey();
```

| Variable | Tipo | Qué guarda |
|---|---|---|
| `nombre` | `string` | Texto |
| `edad` | `int` | Número entero |
| `activo` | `bool` | `true` o `false` |
| `fecha` | `DateTime` | Fecha y hora; `DateTime.Now` es el momento actual |
| `precio` | `float` | Decimal de 32 bits (sufijo `f`) |
| `descuento` | `decimal` | Decimal de 128 bits (sufijo `m`) |
| `descuento2` | `double` | Decimal de 64 bits, el decimal por defecto |

- `DateTime` no es una palabra reservada como `int`: es un tipo de .NET (`System.DateTime`) y se escribe con mayúscula inicial.
- Dos variables no pueden llamarse igual en el mismo bloque. Por eso el segundo descuento necesita otro nombre (`descuento2`).
- Con `camelCase` el nombre no lleva guion bajo: `descuento2` y no `descuento_2`.
- `Console.ReadKey()` espera a que se pulse una tecla antes de cerrar la consola.

## Ámbito

Una variable solo existe dentro del bloque `{ }` donde se declara.

```csharp
if (true)
{
    int contador = 1;
}
Console.WriteLine(contador);   // error: contador no existe fuera del bloque
```

## Resumen

| Concepto | Ejemplo |
|---|---|
| Declarar | `int edad;` |
| Asignar | `edad = 25;` |
| Inicializar | `int edad = 25;` |
| Reasignar | `edad = 26;` |
| Varias a la vez | `int x = 1, y = 2;` |
| Tipo inferido | `var edad = 25;` |
| Fecha y hora actual | `DateTime fecha = DateTime.Now;` |

## Comandos

```bash
cd csharp/02-fundamentals
dotnet new console -n Variables -o 05-variables
cd 05-variables
dotnet run
```

Fuente: [curso de C# de INFORMATICONFIG](https://www.youtube.com/watch?v=2Yr4B8S73C8&list=PL2Z95CSZ1N4F7mym8Ca16RoTDVAlIOPlT&index=5), vídeo 05.
