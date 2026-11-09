# La clase `Math` en C#

## Qué es

Una colección de **funciones matemáticas ya hechas**. Se usan escribiendo `Math.`, el nombre de la función y los valores entre paréntesis. Complementa a los operadores aritméticos (`+ - * / %`), que no cubren potencias, raíces ni redondeos.

> Qué es exactamente una clase y una función se verá más adelante. Por ahora basta con saber usarlas: `Math.Funcion(valor)` devuelve un resultado que se guarda o se muestra.

```csharp
double resultado = Math.Pow(4, 2);   // 16
```

## Funciones principales

| Función | Qué hace | Ejemplo | Resultado |
|---|---|---|---|
| `Math.Pow(x, y)` | Eleva `x` a la `y` | `Math.Pow(4, 2)` | `16` |
| `Math.Sqrt(x)` | Raíz cuadrada | `Math.Sqrt(4)` | `2` |
| `Math.Abs(x)` | Valor absoluto | `Math.Abs(-4)` | `4` |
| `Math.Ceiling(x)` | Redondea **hacia arriba** | `Math.Ceiling(50.23)` | `51` |
| `Math.Floor(x)` | Redondea **hacia abajo** | `Math.Floor(50.23)` | `50` |
| `Math.Round(x)` | Redondea al **más cercano** | `Math.Round(50.53)` | `51` |
| `Math.Max(a, b)` | El **mayor** | `Math.Max(4, 50.53)` | `50,53` |
| `Math.Min(a, b)` | El **menor** | `Math.Min(4, 50.53)` | `4` |
| `Math.PI` | El número pi | | `3,141592653589793` |

`Math.PI` no lleva paréntesis porque es un **valor**, no una función. Las funciones que devuelven decimales (`Pow`, `Sqrt`, `Ceiling`...) devuelven `double`.

## Potencia y raíz

```csharp
int numero = 4;

Console.WriteLine(Math.Pow(numero, 2));   // 16: 4 al cuadrado
Console.WriteLine(Math.Pow(numero, 3));   // 64: 4 al cubo
Console.WriteLine(Math.Sqrt(numero));     // 2: raíz cuadrada
```

El operador `**` no existe en C#, por eso se usa `Math.Pow`.

## Valor absoluto

El valor de un número **sin su signo**:

```csharp
Console.WriteLine(Math.Abs(-4));   // 4
Console.WriteLine(Math.Abs(4));    // 4
```

## Redondeo

| `x` | `Ceiling` | `Floor` | `Round` |
|---|---|---|---|
| `50.23` | `51` | `50` | `50` |
| `50.53` | `51` | `50` | `51` |

- **`Ceiling`**: siempre sube al siguiente entero (techo).
- **`Floor`**: siempre baja (suelo).
- **`Round`**: va al entero más cercano.

### Cuidado con el punto medio

En C#, `Math.Round` cuando el decimal es justo `.5` redondea al **número par** más cercano:

```csharp
Console.WriteLine(Math.Round(2.5));   // 2
Console.WriteLine(Math.Round(3.5));   // 4
```

Es el llamado redondeo bancario: reduce el error al sumar muchos valores. Para el redondeo "de toda la vida", en el que `.5` sube, se indica:

```csharp
Console.WriteLine(Math.Round(2.5, MidpointRounding.AwayFromZero));   // 3
```

### Redondear a unos decimales

El segundo argumento es el número de decimales:

```csharp
Console.WriteLine(Math.Round(3.14159, 2));   // 3,14
```

Útil para quitar los decimales sobrantes de un cálculo.

## Máximo y mínimo

```csharp
Console.WriteLine(Math.Max(4, 50.53));   // 50,53
Console.WriteLine(Math.Min(4, 50.53));   // 4
```

Comparan dos valores a la vez. Se pueden anidar para comparar más: `Math.Max(a, Math.Max(b, c))`.

## Trigonometría

```csharp
Console.WriteLine(Math.Sin(Math.PI / 2));   // 1
Console.WriteLine(Math.Cos(0));             // 1
```

`Sin`, `Cos` y `Tan` trabajan con **radianes**, no con grados. 90 grados son `Math.PI / 2` radianes.

## Ejercicio: área de un círculo

El área es `π · radio²`:

```csharp
Console.WriteLine("Indique el radio del círculo...");
double radio = Convert.ToDouble(Console.ReadLine());

double area = Math.PI * Math.Pow(radio, 2);

Console.WriteLine("Área del círculo: " + area);
Console.WriteLine("Área redondeada: " + Math.Round(area));
Console.WriteLine("Área con 2 decimales: " + Math.Round(area, 2));
```

Con radio `5`:

```text
Área del círculo: 78,53981633974483
Área redondeada: 79
Área con 2 decimales: 78,54
```

`Convert.ToDouble` usa la configuración regional: en español el decimal se escribe con coma.

## Ejemplo

```csharp
double precio = 50.53;

Console.WriteLine("Hacia arriba: " + Math.Ceiling(precio));
Console.WriteLine("Hacia abajo: " + Math.Floor(precio));
Console.WriteLine("Redondeado: " + Math.Round(precio));
Console.WriteLine("Elevado al cuadrado: " + Math.Pow(4, 2));

Console.ReadKey();
```

## Comandos

```bash
cd csharp/04-data-structures
dotnet new console -n MathClass -o 22-math-class
cd 22-math-class
dotnet run
```

Fuente: [curso de C# de INFORMATICONFIG](https://www.youtube.com/watch?v=scmOWO6FM1c&list=PL2Z95CSZ1N4F7mym8Ca16RoTDVAlIOPlT&index=22), vídeo 22.
