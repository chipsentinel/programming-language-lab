# Constantes en C#

## Qué es una constante

Un valor que **se fija al escribir el código y no puede cambiar** mientras el programa se ejecuta. Se declara como una variable, con `const` delante.

```csharp
const double Pi = 3.14159;
```

```text
const    double   Pi    =    3.14159;
palabra  tipo     nombre =   valor
```

```csharp
int edad = 30;
edad = 31;          // válido

const int Dias = 7;
Dias = 8;           // error: no se puede asignar a una constante
```

## Para qué sirven

- **Evitar números mágicos**: el nombre explica qué es el valor.
- **Cambiarlo en un solo sitio**: si el IVA cambia, se modifica la constante y se actualiza en todo el programa.
- **Protegerlo**: el compilador avisa si alguien intenta modificarlo.

```csharp
decimal total = precio * 1.21m;          // ¿qué es 1.21?

const decimal Iva = 0.21m;
decimal total = precio * (1 + Iva);      // el nombre lo explica
```

## Reglas

- **Se inicializa al declararla**: `const int Meses;` es un error.
- **El valor se conoce al compilar**: vale un literal o una operación entre constantes; no vale un método ni `DateTime.Now`.
- **Solo ciertos tipos**: números, `bool`, `char` y `string`. No `DateTime`, arrays ni la mayoría de objetos.
- **No admite `var`**: `const var X = 5;` es un error; se escribe `const int X = 5;`.

```csharp
const int Dias = 7;
const int Semanas = Dias * 4;             // válido
const string Nombre = "Sol";
const string Saludo = $"Hola {Nombre}";   // válido: todo es constante

const int N = int.Parse("5");             // error: no se conoce al compilar
```

## Convención de nombre

Las constantes usan **`PascalCase`** (`Pi`, `Iva`, `DiasSemana`) y las variables **`camelCase`** (`edad`, `nombreCompleto`). `DIAS_SEMANA` también es válido, pero no es lo habitual en C#.

## Constantes que ya trae .NET

```csharp
Console.WriteLine(Math.PI);       // 3,141592653589793
Console.WriteLine(int.MaxValue);  // 2147483647
```

`MinValue` y `MaxValue`, de la lección de tipos de datos, son constantes.

## `const` frente a `readonly`

`readonly` también impide cambiar un valor, pero **se fija al ejecutar** y solo existe como campo de una clase (se verá con las clases).

| | `const` | `readonly` |
|---|---|---|
| Se fija | Al compilar | Al ejecutar |
| Admite `DateTime.Now` y métodos | No | Sí |

Si el valor se conoce de antemano, `const`; si depende de algo que solo se sabe al ejecutar, `readonly`.

## Repaso: `var` fija el tipo

`var` no es dinámico: el compilador deduce el tipo del **primer valor** y ya no cambia.

```csharp
var numero = 10.20;   // double

numero = 4000;        // válido: int -> double es implícito
numero = "hola";      // error: string no se convierte a double
numero = 's';         // válido: char -> double, guarda 115
```

- `WriteLine(numero)` muestra `115` y no `s`: en la variable ya solo queda el número.
- Para conservar la letra la variable debe ser `char`; para volver de `115` a letra, `(char)numero`.
- Una variable no se puede declarar dos veces en el mismo bloque.

## Ejemplo

```csharp
const double Pi = 3.14159;
const decimal Iva = 0.21m;

double radio = 5;
double area = Pi * radio * radio;

decimal precio = 100m;
decimal total = precio * (1 + Iva);

Console.WriteLine($"Área del círculo de radio {radio}: {area}");
Console.WriteLine($"Precio con IVA: {total}");

Console.ReadKey();
```

## Comandos

```bash
cd csharp/02-fundamentals
dotnet new console -n Constants -o 08-constants
cd 08-constants
dotnet run
```

Fuente: [curso de C# de INFORMATICONFIG](https://www.youtube.com/watch?v=O2cAgrpLLcQ&list=PL2Z95CSZ1N4F7mym8Ca16RoTDVAlIOPlT&index=8), vídeo 08.
