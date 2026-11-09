# Condicionales `if` y `else` en C#

## Qué es una condicional

Una **estructura de control** que ejecuta un bloque de código u otro según se cumpla o no una condición. Hasta ahora el programa ejecutaba todas las líneas de arriba abajo; con una condicional puede **tomar decisiones**.

```text
         ┌── sí ──> hacer algo ──┐
condición                         ├──> continúa el programa
         └── no ──> (otro camino) ┘
```

## `if`

La condición va entre paréntesis y debe dar un `bool`. Si es `true`, se ejecuta el bloque entre llaves.

```csharp
int edad = 20;

if (edad > 18)
{
    Console.WriteLine("Es mayor");
}
```

Si la condición es `false` y no hay nada más, **no pasa nada**: el programa sigue después del bloque.

## `else`

Define qué hacer **cuando la condición no se cumple**.

```csharp
if (edad < 18)
{
    Console.WriteLine("Es menor");
}
else
{
    Console.WriteLine("No es menor");
}
```

Un `else` no lleva condición: recoge todo lo que no cumplió el `if`.

## `else if`

Cuando hay **más de dos caminos** se encadenan condiciones. Se evalúan **en orden** y en cuanto una se cumple, se ejecuta su bloque y **se salta el resto**.

```csharp
int nota = 80;

if (nota >= 70)
{
    Console.WriteLine("Felicidades, pasaste el examen con: " + nota);
}
else if (nota < 70 && nota >= 50)
{
    Console.WriteLine("Debes repetir el examen, sacaste: " + nota);
}
else
{
    Console.WriteLine("Has reprobado el examen, sacaste: " + nota);
}
```

| `nota` | Resultado |
|---|---|
| `80` | Felicidades, pasaste el examen |
| `65` | Debes repetir el examen |
| `40` | Has reprobado el examen |

- Se pueden poner **tantos `else if` como haga falta**.
- Solo se ejecuta **un** bloque de la cadena.
- El `else` final es opcional y va siempre al final.
- Se combinan con los operadores lógicos: `nota < 70 && nota >= 50` es un rango.

## Cuidado con los límites

`>` y `>=` no son lo mismo. Con `nota > 70` y `nota < 70`, el valor **exacto 70 no cumple ninguna condición** y cae en el `else`.

```csharp
int nota = 70;

if (nota > 70) { /* no entra: 70 no es mayor que 70 */ }
if (nota >= 70) { /* entra */ }
```

Al escribir rangos conviene decidir a qué lado pertenece cada límite.

## Aprovechar el orden

Como las condiciones se evalúan en orden, no hace falta repetir el límite superior. Si el primer `if` ya descartó `nota >= 70`, en el `else if` la nota ya es menor que 70:

```csharp
if (nota >= 70)
{
    Console.WriteLine("Aprobado");
}
else if (nota >= 50)         // equivale a nota < 70 && nota >= 50
{
    Console.WriteLine("Recuperación");
}
else
{
    Console.WriteLine("Suspenso");
}
```

## Reglas

- La condición **debe ser `bool`**. `if (nota)` es un error, y `if (nota = 70)` también: `=` asigna y `==` compara.
- Con **una sola instrucción** se pueden omitir las llaves, pero conviene ponerlas siempre.
- Funciona igual con otros tipos: `if (nombre == "Sol")` compara textos.

```csharp
if (edad > 18) Console.WriteLine("Es mayor");   // válido, pero poco claro
```

## `if/else` frente al operador ternario

El ternario (lección anterior) sirve para **elegir un valor**; `if/else` para **ejecutar instrucciones**.

```csharp
string mensaje = (edad > 18) ? "Es mayor" : "No es mayor";   // elige un valor

if (edad > 18) { Console.WriteLine("Es mayor"); }            // ejecuta código
```

## Ejemplo

```csharp
int nota = 80;

Console.WriteLine("Nota final del alumno");

if (nota >= 70)
{
    Console.WriteLine("Felicidades, pasaste el examen con: " + nota);
}
else if (nota >= 50)
{
    Console.WriteLine("Debes repetir el examen, sacaste: " + nota);
}
else
{
    Console.WriteLine("Has reprobado el examen, sacaste: " + nota);
}

Console.ReadKey();
```

## Comandos

```bash
cd csharp/03-control-flow
dotnet new console -n IfElse -o 15-if-else
cd 15-if-else
dotnet run
```

Fuente: [curso de C# de INFORMATICONFIG](https://www.youtube.com/watch?v=3ASlc4lUvik&list=PL2Z95CSZ1N4F7mym8Ca16RoTDVAlIOPlT&index=15), vídeo 15.
