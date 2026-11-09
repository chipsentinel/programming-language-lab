# Operadores lógicos en C#

## Qué son

Operadores que **combinan o modifican expresiones `bool`** (verdadero o falso) y devuelven otro `bool`. Se usan para evaluar varias condiciones a la vez.

| Operador | Nombre | Significa | Ejemplo |
|---|---|---|---|
| `&&` | AND (y) | Se cumplen **las dos** condiciones | `a < b && a < c` |
| `\|\|` | OR (o) | Se cumple **al menos una** | `a < b \|\| a < c` |
| `!` | NOT (no) | **Invierte** el valor | `!(a < b)` |

La barra vertical `|` suele estar en la tecla a la izquierda del `1`; `||` son dos.

## AND (`&&`)

Da `true` solo si **ambas** condiciones son `true`.

| `A` | `B` | `A && B` |
|---|---|---|
| true | true | **true** |
| true | false | false |
| false | true | false |
| false | false | false |

```csharp
int numero1 = 40, numero2 = 90, numero3 = 5;

bool resultado = numero1 < numero2 && numero1 < numero3;   // true && false
Console.WriteLine(resultado);   // False

numero3 = 50;
resultado = numero1 < numero2 && numero1 < numero3;        // true && true
Console.WriteLine(resultado);   // True
```

## OR (`||`)

Da `true` si **al menos una** condición es `true`. Solo es `false` si ninguna se cumple.

| `A` | `B` | `A \|\| B` |
|---|---|---|
| true | true | **true** |
| true | false | **true** |
| false | true | **true** |
| false | false | false |

```csharp
int numero1 = 40, numero2 = 90, numero3 = 50;

Console.WriteLine(numero1 < numero2 || numero1 < numero3);   // True

numero1 = 100;
Console.WriteLine(numero1 < numero2 || numero1 < numero3);   // False
```

## NOT (`!`)

**Invierte** el valor: `true` pasa a `false` y `false` a `true`. Se escribe delante de la expresión, y si la expresión tiene varias partes se pone entre paréntesis.

```csharp
bool resultado = numero1 < numero2 && numero1 < numero3;   // false
Console.WriteLine(resultado);    // False
Console.WriteLine(!resultado);   // True

Console.WriteLine(!(numero1 < numero2 && numero1 < numero3));   // True
```

Sirve para ahorrar código al validar datos. Se verá con los condicionales.

## Evaluación en cortocircuito

`&&` y `||` **dejan de evaluar en cuanto conocen el resultado**.

- En `A && B`, si `A` es `false`, ya no se evalúa `B`: el resultado es `false`.
- En `A || B`, si `A` es `true`, ya no se evalúa `B`: el resultado es `true`.

Sirve para evitar errores:

```csharp
int divisor = 0;
bool seguro = divisor != 0 && 10 / divisor > 1;   // 10 / 0 nunca se calcula
```

Si se escribiera `10 / divisor > 1 && divisor != 0`, lanzaría `DivideByZeroException`.

## Orden de evaluación

Primero `!`, después `&&` y al final `||`. Los paréntesis cambian el orden.

```csharp
Console.WriteLine(true || false && false);     // True: primero false && false
Console.WriteLine((true || false) && false);   // False
```

Ante la duda conviene poner paréntesis: se lee mejor.

## Combinar con operadores de comparación

Los operadores de comparación dan `bool`, así que se combinan con los lógicos:

```csharp
int edad = 20;
bool esMayorDeEdad = edad >= 18;
bool puedeVotar = edad >= 18 && edad < 120;
```

## Ejemplo

```csharp
int numero1 = 40, numero2 = 90, numero3 = 50;

Console.WriteLine("AND: " + (numero1 < numero2 && numero1 < numero3));
Console.WriteLine("OR: " + (numero1 > numero2 || numero1 < numero3));
Console.WriteLine("NOT: " + !(numero1 < numero2));

Console.ReadKey();
```

## Comandos

```bash
cd csharp/02-fundamentals
dotnet new console -n LogicalOperators -o 14-logical-operators
cd 14-logical-operators
dotnet run
```

Fuente: [curso de C# de INFORMATICONFIG](https://www.youtube.com/watch?v=QBCcwzUFjpQ&list=PL2Z95CSZ1N4F7mym8Ca16RoTDVAlIOPlT&index=14), vídeo 14.
