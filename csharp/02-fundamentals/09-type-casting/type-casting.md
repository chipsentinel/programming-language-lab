# Casting en C#

## Qué es el casting

**Convertir un valor de un tipo de dato a otro**: de entero a decimal, de decimal a entero, de número a texto, de texto a número.

Hay tres formas de hacerlo:

- **Implícito**: lo hace el compilador solo.
- **Explícito**: se pide con `(tipo)`.
- **Con métodos**: `Convert`, `Parse` y `TryParse`.

## Casting implícito

Se hace solo cuando **no se pierde información**: el tipo destino siempre puede guardar el valor.

```csharp
int valor = 200;
double total = valor;          // int -> double: implícito
Console.WriteLine(total);      // 200
```

```text
byte -> short -> int -> long -> float -> double
                  ^
                char
```

Cualquier conversión hacia la derecha es implícita. `char` también pasa a `int`, y guarda su código ASCII:

```csharp
char letra = 'A';
int codigoAscii = letra;       // 65
```

## Casting explícito

Cuando se **puede perder información** el compilador no lo hace solo. Hay que pedirlo escribiendo el tipo entre paréntesis delante del valor.

```csharp
double precio = 500.23;

int descuento = precio;        // error: no se convierte double a int solo
int descuento = (int)precio;   // válido: 500
```

- Los decimales **se descartan**, no se redondean: `(int)500.99` es `500`.
- Se trunca hacia cero: `(int)-7.9` es `-7`.
- El programador asume la pérdida: por eso el compilador la exige.

Para volver de un código a su letra:

```csharp
int codigoAscii = 65;
char letra = (char)codigoAscii;   // 'A'
```

## De texto a número

Un `string` nunca se convierte solo a un número, ni siquiera con un cast.

```csharp
string palabra = "123456";

int numero = palabra;                  // error
int numero = (int)palabra;             // error
int numero = Convert.ToInt32(palabra); // válido: 123456
```

| Método | Sirve para | Ejemplo |
|---|---|---|
| `Convert.ToInt32(texto)` | Texto a `int` | `Convert.ToInt32("123")` |
| `int.Parse(texto)` | Texto a `int` | `int.Parse("123")` |
| `double.Parse(texto)` | Texto a `double` | `double.Parse("150.60")` |
| `int.TryParse(texto, out n)` | Texto a `int` sin fallar | `int.TryParse("hola", out n)` |

`Convert.ToInt32` y `int.Parse` hacen lo mismo con texto. La diferencia es que `Convert` también admite otros tipos.

Si el texto **no es un número válido**, lanzan un error en ejecución (`FormatException`):

```csharp
Convert.ToInt32("hola");   // FormatException
```

### `TryParse`: convertir sin que el programa falle

Devuelve `true` o `false`, y el número sale por el parámetro `out`.

```csharp
string entrada = "hola";

if (int.TryParse(entrada, out int resultado))
{
    Console.WriteLine($"Convertido: {resultado}");
}
else
{
    Console.WriteLine($"\"{entrada}\" no es un número entero");
}
```

Es lo recomendable cuando el texto viene del usuario, que puede escribir cualquier cosa.

## El separador decimal depende del idioma

`double.Parse` usa la configuración regional del equipo. En un equipo en español el separador decimal es la coma, y el punto se toma como separador de miles.

```csharp
double.Parse("150.60");   // en español: 15060, no 150,6
```

Para leer siempre con punto se indica `CultureInfo.InvariantCulture`:

```csharp
using System.Globalization;

double valor = double.Parse("150.60", CultureInfo.InvariantCulture);   // 150,6
```

Por eso `Console.WriteLine(150.6)` muestra `150,6` en un equipo en español y `150.6` en uno en inglés.

## De número a texto

```csharp
int edad = 30;
string texto = edad.ToString();       // "30"
string texto2 = Convert.ToString(edad);

string saludo = "Edad: " + edad;      // la concatenación lo hace sola
```

## Resumen

| Caso | Se pierde información | Qué se usa |
|---|---|---|
| `int` a `double` | No | Nada: implícito |
| `char` a `int` | No | Nada: implícito |
| `double` a `int` | Sí (decimales) | `(int)valor` |
| `int` a `char` | Posible | `(char)valor` |
| Texto a número | Puede fallar | `Convert`, `Parse` o `TryParse` |
| Número a texto | No | `ToString()` |

## Ejemplo

```csharp
int valor = 200;
double total = valor;                       // implícito

double precio = 500.23;
int descuento = (int)precio;                // explícito: 500

char letra = 'A';
int codigoAscii = letra;                    // 65

string palabra = "123456";
int numero = Convert.ToInt32(palabra);      // 123456

Console.WriteLine($"{total} {descuento} {codigoAscii} {numero}");
Console.ReadKey();
```

## Comandos

```bash
cd csharp/02-fundamentals
dotnet new console -n TypeCasting -o 09-type-casting
cd 09-type-casting
dotnet run
```

Fuente: [curso de C# de INFORMATICONFIG](https://www.youtube.com/watch?v=ii2nqtVd0Aw&list=PL2Z95CSZ1N4F7mym8Ca16RoTDVAlIOPlT&index=9), vídeo 09.
