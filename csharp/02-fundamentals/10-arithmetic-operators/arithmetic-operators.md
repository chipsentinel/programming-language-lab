# Operadores aritméticos en C#

## Qué son

Símbolos que realizan **operaciones matemáticas** con variables, constantes o valores numéricos.

| Operador | Operación | Ejemplo | Resultado |
|---|---|---|---|
| `+` | Suma | `90 + 70` | `160` |
| `-` | Resta | `90 - 70` | `20` |
| `*` | Multiplicación | `10 * 70` | `700` |
| `/` | División | `90 / 10` | `9` |
| `%` | Residuo (módulo) | `90 % 20` | `10` |

```csharp
int valor1 = 90, valor2 = 70, valor3 = 10;

int total = valor1 + valor2;        // 160
int diferencia = valor1 - valor2;   // 20
int producto = valor3 * valor2;     // 700
int resultado = valor1 / valor3;    // 9
int residuo = valor2 % valor3;      // 0
```

## Declarar varias variables en una línea

Varias variables **del mismo tipo** se declaran juntas, separadas por comas. Todas reciben ese tipo.

```csharp
int valor1 = 90, valor2 = 70, valor3 = 10;
```

Equivale a escribir una línea por variable y ahorra espacio.

## `+` suma o concatena

Con **dos números** suma. Si hay un **texto** a un lado, concatena.

```csharp
Console.WriteLine(valor1 + valor2);                // 160
Console.WriteLine("Suma: " + valor1 + valor2);     // Suma: 9070
Console.WriteLine("Suma: " + (valor1 + valor2));   // Suma: 160
```

En el segundo caso se evalúa de izquierda a derecha: `"Suma: " + 90` da un texto, y a ese texto se le concatena `70`. Los **paréntesis** fuerzan primero la suma.

## División entera y decimal

Entre dos `int` el resultado es un `int`: **se descartan los decimales**.

```csharp
Console.WriteLine(90 / 20);     // 4, no 4,5
Console.WriteLine(90 / 20.0);   // 4,5
```

Si uno de los dos es decimal, el resultado es decimal. Con `double` el residuo y la división también dan decimales:

```csharp
var decimalValor = 2.3;
Console.WriteLine(70 / decimalValor);   // 30,434782608695656
Console.WriteLine(70 % decimalValor);   // 1,0000000000000053
```

Esos decimales de más son el resultado real de la operación en coma flotante. Más adelante se verá cómo redondearlos.

## División entre cero

| | Resultado |
|---|---|
| `int` entre `0` | Error en ejecución: `DivideByZeroException` |
| `double` entre `0` | No falla: da `∞` (`Infinity`) |

```csharp
int cero = 0;
Console.WriteLine(90 / cero);    // DivideByZeroException
Console.WriteLine(90.0 / 0);     // ∞
```

Una excepción es un error que detiene el programa. Más adelante se verá cómo capturarlas y manejarlas.

## Potencia y otras operaciones

C# **no tiene operador de potencia**. Potencias, raíces y otras operaciones se hacen con la clase `Math`, que se verá más adelante.

```csharp
Console.WriteLine(Math.Pow(2, 3));   // 8
```

## Orden de las operaciones

Igual que en matemáticas: primero `*`, `/` y `%`, luego `+` y `-`. Los paréntesis cambian el orden.

```csharp
Console.WriteLine(2 + 3 * 4);     // 14
Console.WriteLine((2 + 3) * 4);   // 20
```

## Ejemplo

```csharp
int valor1 = 90, valor2 = 70, valor3 = 10;

Console.WriteLine("Total de la suma: " + (valor1 + valor2));
Console.WriteLine("Total de la resta: " + (valor1 - valor2));
Console.WriteLine("Producto: " + valor3 * valor2);
Console.WriteLine("Resultado: " + valor1 / valor3);
Console.WriteLine("Residuo: " + valor2 % valor3);

Console.ReadKey();
```

## Comandos

```bash
cd csharp/02-fundamentals
dotnet new console -n ArithmeticOperators -o 10-arithmetic-operators
cd 10-arithmetic-operators
dotnet run
```

Fuente: [curso de C# de INFORMATICONFIG](https://www.youtube.com/watch?v=8Z2pkmVGUK4&list=PL2Z95CSZ1N4F7mym8Ca16RoTDVAlIOPlT&index=10), vídeo 10.
