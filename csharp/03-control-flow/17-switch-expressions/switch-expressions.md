# Expresiones `switch` en C#

## Qué es

Una forma **más corta** de escribir un `switch` cuando el objetivo es **obtener un valor**. En lugar de ejecutar instrucciones y `break` en cada `case`, la expresión `switch` **devuelve un resultado** que se guarda directamente en una variable.

| | `switch` clásico | Expresión `switch` |
|---|---|---|
| Qué es | Una instrucción: ejecuta código | Una expresión: devuelve un valor |
| Cada caso | `case 1: ...; break;` | `1 => valor,` |
| Sintaxis | `switch (x) { ... }` | `x switch { ... };` |

## Con valores

```csharp
int dia = 3;

string nombreDia = dia switch
{
    1 => "Lunes",
    2 => "Martes",
    3 => "Miércoles",
    4 => "Jueves",
    5 => "Viernes",
    6 => "Sábado",
    7 => "Domingo",
    _ => "Día no válido"
};

Console.WriteLine(nombreDia);   // Miércoles
```

```text
variable switch { patrón => valor, patrón => valor, _ => valorPorDefecto };
```

- La variable va **antes** de `switch`, y el conjunto termina en `;` porque es una asignación.
- Cada rama es `patrón => valor`, y se separan con **comas**, no con `break`.
- La flecha `=>` significa "entonces devuelve".
- `_` recoge **cualquier otro valor**; equivale al `default`.

## Con rangos

La ventaja principal: se pueden usar **operadores de comparación** como patrón.

```csharp
int nota = 80;

string resultado = nota switch
{
    >= 70 => "Pasaste el examen",
    >= 50 => "Debes repetir el examen",
    _ => "Has reprobado el examen"
};
```

| `nota` | `resultado` |
|---|---|
| `80` | Pasaste el examen |
| `65` | Debes repetir el examen |
| `40` | Has reprobado el examen |

Es lo mismo que el `if / else if / else` de la lección 15, pero en menos líneas y en una sola asignación.

## Rangos cerrados: `and`, `or` y `not`

Para combinar patrones se usan las palabras `and`, `or` y `not` (no `&&` ni `||`).

```csharp
nota = 64;

resultado = nota switch
{
    >= 80 => "Pasaste el examen",
    >= 60 and <= 69 => "Repetir examen",    // entre 60 y 69, ambos incluidos
    _ => "Reprobaste el examen"
};
```

```csharp
string tipoDia = dia switch
{
    6 or 7 => "Fin de semana",
    >= 1 and <= 5 => "Día laborable",
    _ => "Día no válido"
};
```

Cuidado con los extremos: `>= 60 and <= 69` incluye el 60 y el 69. Si se olvida el `=`, esos valores quedan fuera.

## El orden importa

Se toma **la primera rama que coincide**. Una condición general antes de una concreta la tapa, y el compilador lo detecta como error (CS8510):

```csharp
resultado = nota switch
{
    >= 50 => "Debes repetir el examen",
    >= 70 => "Pasaste el examen",     // error: ya lo cubre >= 50
    _ => "Has reprobado el examen"
};
```

Se ordenan de lo más concreto o exigente a lo más general.

## Si ninguna rama coincide

Sin `_` el compilador avisa de que la expresión no cubre todos los casos, y si en ejecución ningún patrón coincide se lanza una excepción (`SwitchExpressionException`). Conviene poner siempre `_`.

## Cuándo usar cada uno

| Usar | Cuándo |
|---|---|
| Expresión `switch` | Hay que **obtener un valor** según una variable |
| `switch` clásico | Cada caso ejecuta **varias instrucciones** |
| `if / else if` | Las condiciones comparan **variables distintas** |

## Ejemplo

```csharp
int nota = 64;

string resultado = nota switch
{
    >= 80 => "Pasaste el examen",
    >= 60 and <= 69 => "Repetir examen",
    _ => "Reprobaste el examen"
};

Console.WriteLine($"Nota {nota}: {resultado}");

Console.ReadKey();
```

## Comandos

```bash
cd csharp/03-control-flow
dotnet new console -n SwitchExpressions -o 17-switch-expressions
cd 17-switch-expressions
dotnet run
```

Fuente: [curso de C# de INFORMATICONFIG](https://www.youtube.com/watch?v=ydP8job-2a8&list=PL2Z95CSZ1N4F7mym8Ca16RoTDVAlIOPlT&index=17), vídeo 17.
