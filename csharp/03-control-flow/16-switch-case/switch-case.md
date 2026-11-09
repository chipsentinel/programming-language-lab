# Condicional `switch` en C#

## Qué es

Una condicional que **compara el valor de una variable con varios casos posibles** y ejecuta el que coincide. Es una alternativa más clara a una cadena larga de `else if` que comparan siempre la misma variable.

```csharp
int dia = 3;

switch (dia)
{
    case 1:
        Console.WriteLine("Lunes");
        break;
    case 2:
        Console.WriteLine("Martes");
        break;
    case 3:
        Console.WriteLine("Miércoles");
        break;
}
```

```text
Miércoles
```

## Cómo funciona

```text
switch (variable)
{
    case valor1:
        instrucciones;
        break;
    case valor2:
        instrucciones;
        break;
    default:
        instrucciones;
        break;
}
```

- **`switch (dia)`**: indica la variable que se evalúa.
- **`case 3:`**: "si `dia` vale 3". Acaba en **dos puntos**, no en punto y coma.
- **`break;`**: termina el `switch`. El programa sale y ya no mira los demás casos.
- El sistema recorre los `case` en orden hasta encontrar el que coincide.

## `default`

Se ejecuta cuando **ningún `case` coincide**. Es el `else` final del `switch`.

```csharp
int dia = 9;

switch (dia)
{
    case 1:
        Console.WriteLine("Lunes");
        break;
    default:
        Console.WriteLine("Día no válido");
        break;
}
```

Sin `default`, si ningún caso coincide no pasa nada. Es opcional, pero conviene ponerlo para controlar valores inesperados.

## Varios `case` con el mismo código

Se escriben seguidos, sin código entre ellos:

```csharp
switch (dia)
{
    case 6:
    case 7:
        Console.WriteLine("Fin de semana");
        break;
    case 1:
    case 2:
    case 3:
    case 4:
    case 5:
        Console.WriteLine("Día laborable");
        break;
}
```

## Con textos

`switch` también funciona con `string`, `char` y otros tipos, no solo con números.

```csharp
string opcion = "guardar";

switch (opcion)
{
    case "abrir":
        Console.WriteLine("Abriendo el archivo");
        break;
    case "guardar":
        Console.WriteLine("Guardando el archivo");
        break;
}
```

## C# no deja pasar de un `case` al siguiente

Cada `case` con código **debe terminar** en `break` (o `return`, o `throw`). Si falta, el programa **no compila**:

```csharp
switch (dia)
{
    case 1:
        Console.WriteLine("Lunes");   // error CS0163: falta el break
    case 2:
        Console.WriteLine("Martes");
        break;
}
```

Esto evita un error típico de otros lenguajes, en los que olvidar el `break` ejecuta también los casos siguientes. La excepción son los `case` apilados sin código, como el ejemplo de `case 6: case 7:`.

## `switch` frente a `if / else if`

| Usar `switch` | Usar `if / else if` |
|---|---|
| Se compara **una variable** con valores concretos | Las condiciones son rangos (`nota >= 70`) |
| Muchos casos, como días o meses | Se combinan variables distintas con `&&` y `||` |

## Un consejo sobre nombres

En los nombres de variables **no se usan tildes ni la `ñ`**: `dia`, no `día`; `anio`, no `año`. Aunque C# los acepta, pueden dar problemas con editores, compiladores y control de versiones.

## Ejemplo

```csharp
int dia = 3;

Console.WriteLine("Días de la semana");

switch (dia)
{
    case 1:
        Console.WriteLine("Lunes");
        break;
    case 2:
        Console.WriteLine("Martes");
        break;
    case 3:
        Console.WriteLine("Miércoles");
        break;
    default:
        Console.WriteLine("Otro día");
        break;
}

Console.ReadKey();
```

## Comandos

```bash
cd csharp/03-control-flow
dotnet new console -n SwitchCase -o 16-switch-case
cd 16-switch-case
dotnet run
```

Fuente: [curso de C# de INFORMATICONFIG](https://www.youtube.com/watch?v=xP7MIqlBTSU&list=PL2Z95CSZ1N4F7mym8Ca16RoTDVAlIOPlT&index=16), vídeo 16.
