# Operadores de comparación en C#

## Qué son

Operadores que **comparan dos valores** y dan como resultado un `bool`: `true` o `false`. Sirven para tomar decisiones en el flujo del programa.

| Operador | Significa | Ejemplo | Resultado |
|---|---|---|---|
| `>` | Mayor que | `10 > 20` | `false` |
| `<` | Menor que | `10 < 20` | `true` |
| `==` | Igual que | `10 == 10` | `true` |
| `>=` | Mayor o igual que | `10 >= 10` | `true` |
| `<=` | Menor o igual que | `10 <= 5` | `false` |
| `!=` | Distinto de | `10 != 20` | `true` |

```csharp
int a = 10;
int b = 20;

Console.WriteLine(a > b);    // False
Console.WriteLine(a != b);   // True
```

`WriteLine` muestra `True` y `False` con la inicial en mayúscula.

## `=` frente a `==`

- `=` **asigna**: `a = 10` guarda un 10 en `a`.
- `==` **compara**: `a == 10` pregunta si `a` vale 10.

```csharp
int a = 5;
bool igual = (a == 10);   // false: solo pregunta
```

Distinto se escribe `!=`: el signo `!` significa "no".

## El operador ternario `? :`

Convierte el resultado de una comparación en un valor. Es una pregunta con dos respuestas.

```text
(condición) ? valorSiTrue : valorSiFalse
```

```csharp
int a = 10, b = 20;
string resultado = (a > b) ? "Sí" : "No";

Console.WriteLine("¿a es mayor que b? " + resultado);   // No
```

- Si `a > b` es `true`, `resultado` recibe `"Sí"`.
- Si es `false`, recibe `"No"`.
- Es una forma corta de decidir. En la lección de condicionales se verá `if/else`, que sirve para lo mismo y para decisiones más largas.

## Mayor o igual y menor o igual

`>=` es verdadero si se cumple **cualquiera** de las dos condiciones: que sea mayor o que sea igual.

```csharp
int b = 10;

Console.WriteLine(10 >= b);   // True: es igual
Console.WriteLine(11 >= b);   // True: es mayor
Console.WriteLine(1 >= b);    // False: no es ni mayor ni igual
```

El `=` va siempre **después** de `>` o `<`: `=>` y `=<` no son operadores de comparación.

## Comparar textos

En C# `==` compara el **contenido** de los `string`, y distingue mayúsculas de minúsculas.

```csharp
string nombre = "Sol";

Console.WriteLine(nombre == "Sol");   // True
Console.WriteLine(nombre == "sol");   // False
```

## Ejemplo

```csharp
int a = 10;
int b = 20;

Console.WriteLine("¿a es mayor que b? " + ((a > b) ? "Sí" : "No"));
Console.WriteLine("¿a es menor que b? " + ((a < b) ? "Sí" : "No"));
Console.WriteLine("¿a es igual a b? " + ((a == b) ? "Sí" : "No"));
Console.WriteLine("¿a es distinto de b? " + ((a != b) ? "Sí" : "No"));

Console.ReadKey();
```

## Comandos

```bash
cd csharp/02-fundamentals
dotnet new console -n ComparisonOperators -o 13-comparison-operators
cd 13-comparison-operators
dotnet run
```

Fuente: [curso de C# de INFORMATICONFIG](https://www.youtube.com/watch?v=XKRWB9LluAA&list=PL2Z95CSZ1N4F7mym8Ca16RoTDVAlIOPlT&index=13), vídeo 13.
