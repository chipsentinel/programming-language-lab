# `break` y `continue` en C#

## Qué son

Dos instrucciones para **controlar un bucle desde dentro**, cuando no se quiere esperar a que su condición termine por sí sola.

| Instrucción | Qué hace |
|---|---|
| `break` | **Sale del bucle** por completo: el programa sigue después de él |
| `continue` | **Salta el resto de la vuelta actual** y pasa a la siguiente |

Funcionan en `for`, `while` y `do-while`. Casi siempre van dentro de un `if`.

## `break`: romper el bucle

```csharp
for (int i = 0; i < 5; i++)
{
    if (i == 3)
    {
        break;
    }

    Console.WriteLine("Vuelta: " + i);
}

Console.WriteLine("Fuera del bucle");
```

```text
Vuelta: 0
Vuelta: 1
Vuelta: 2
Fuera del bucle
```

En la vuelta con `i == 3` se ejecuta `break` y el bucle **termina**, aunque quedaran vueltas. Las vueltas 3 y 4 no se hacen. Es el mismo `break` que cierra cada `case` de un `switch`.

## El `break` solo actúa si la condición se cumple

Con un paso de 2 la variable vale `0, 2, 4, 6...` y **nunca vale 3**: ese `break` no se ejecuta y el bucle llega hasta el final.

```csharp
for (int i = 0; i <= 10; i += 2)
{
    if (i == 3)
    {
        break;               // nunca se cumple
    }

    Console.WriteLine(i);    // 0, 2, 4, 6, 8, 10
}
```

Con `i == 6` sí se cumple, y el bucle corta ahí: escribe `0, 2, 4`.

## `continue`: saltar una vuelta

`continue` se salta lo que **queda de la vuelta actual** y el bucle sigue con la siguiente.

```csharp
for (int i = 0; i < 5; i++)
{
    if (i == 3)
    {
        continue;
    }

    Console.WriteLine("Con continue: " + i);
}
```

```text
Con continue: 0
Con continue: 1
Con continue: 2
Con continue: 4
```

Ya no está el 3: en esa vuelta se saltó el `WriteLine`, pero el bucle **no se detuvo** y llegó hasta el 4.

## La diferencia

```text
i:         0  1  2  3  4
break:     0  1  2  ■               ← termina en el 3
continue:  0  1  2  -  4            ← solo se salta el 3
```

## En un `while`

`break` funciona igual:

```csharp
int contador = 0;

while (contador < 10)
{
    Console.WriteLine("While: " + contador);
    contador++;

    if (contador == 4)
    {
        break;
    }
}
```

Escribe `0, 1, 2, 3` y sale sin llegar a 10.

### Cuidado con `continue` en un `while`

En un `for` la actualización (`i++`) se ejecuta siempre. En un `while`, si `continue` salta **antes** del incremento, la variable no cambia y el bucle **no termina nunca**:

```csharp
int n = 0;

while (n < 5)
{
    if (n == 3)
    {
        continue;    // n sigue valiendo 3: bucle infinito
    }

    n++;
}
```

La solución es incrementar **antes** del `continue`:

```csharp
while (n < 5)
{
    n++;

    if (n == 3)
    {
        continue;
    }

    Console.WriteLine(n);   // 1, 2, 4, 5
}
```

## Uso práctico: buscar y parar

Cuando ya se encontró lo que se buscaba, no hace falta seguir dando vueltas:

```csharp
int encontrado = -1;

for (int i = 20; i <= 50; i++)
{
    if (i % 7 == 0)
    {
        encontrado = i;
        break;
    }
}

Console.WriteLine("Primer múltiplo de 7: " + encontrado);   // 21
```

## Bucles anidados

Cuando hay un bucle dentro de otro, `break` y `continue` actúan **solo sobre el bucle más interno** en el que están escritos.

## Ejemplo

```csharp
for (int i = 0; i < 5; i++)
{
    if (i == 3)
    {
        continue;
    }

    Console.WriteLine("Vuelta: " + i);
}

Console.ReadKey();
```

## Comandos

```bash
cd csharp/03-control-flow
dotnet new console -n BreakContinue -o 21-break-continue
cd 21-break-continue
dotnet run
```

Fuente: [curso de C# de INFORMATICONFIG](https://www.youtube.com/watch?v=TXJu968w5-w&list=PL2Z95CSZ1N4F7mym8Ca16RoTDVAlIOPlT&index=21), vídeo 21.
