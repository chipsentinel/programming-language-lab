# Bucle `while` en C#

## Qué es un bucle

Una estructura de control que **repite un bloque de código** varias veces: un número determinado, o mientras se cumpla una condición. Sin bucles habría que escribir la misma instrucción una y otra vez.

## El bucle `while`

Repite el bloque **mientras la condición sea `true`**. La condición se comprueba **antes de cada vuelta**.

```csharp
int valor = 0;

while (valor < 3)
{
    Console.WriteLine("Valor es: " + valor);
    valor++;
}

Console.WriteLine("Fin del bucle");
```

```text
Valor es: 0
Valor es: 1
Valor es: 2
Fin del bucle
```

```text
while (condición)
{
    código que se repite
}
```

## Cómo se ejecuta

| Vuelta | `valor` al comprobar | ¿`valor < 3`? | Qué pasa |
|---|---|---|---|
| 1 | `0` | Sí | Escribe y `valor` pasa a `1` |
| 2 | `1` | Sí | Escribe y `valor` pasa a `2` |
| 3 | `2` | Sí | Escribe y `valor` pasa a `3` |
| 4 | `3` | **No** | El bucle termina |

## Las tres partes de un bucle con contador

1. **Inicialización**: `int valor = 0;`, antes del bucle.
2. **Condición**: `valor < 3`, en el `while`.
3. **Actualización**: `valor++;`, dentro del bucle.

## Bucle infinito

Si la variable **nunca cambia**, la condición siempre se cumple y el bucle no termina. El programa se queda repitiendo sin parar.

```csharp
int contador = 0;
while (contador < 3)
{
    Console.WriteLine("Contador es: " + contador);   // falta contador++
}
```

Para detenerlo hay que cerrar la consola o pulsar `Ctrl+C`. Casi siempre es un olvido de la **actualización**.

## Incluir el límite y empezar en 1

- `valor < 3` repite hasta el 2; `valor <= 3` **incluye** el 3.
- Empezando en `0` hay una vuelta más que empezando en `1`: `0, 1, 2, 3` son cuatro valores, `1, 2, 3` son tres.

```csharp
int numero = 1;

while (numero <= 3)
{
    Console.WriteLine("Vuelta " + numero);
    numero++;
}
```

## Decisiones dentro del bucle

Dentro del bucle se pueden usar condicionales (`if`, `switch`) para que ciertas vueltas hagan algo distinto:

```csharp
numero = 1;

while (numero <= 5)
{
    if (numero == 2)
    {
        Console.WriteLine("Soy la vuelta dos");
    }
    else
    {
        Console.WriteLine("Vuelta " + numero);
    }

    numero++;
}
```

## Ejemplo clásico: tabla de multiplicar

```csharp
const int Tabla = 8;
int numero = 1;

Console.WriteLine($"Tabla del {Tabla}");

while (numero <= 10)
{
    Console.WriteLine($"{Tabla} x {numero} = {Tabla * numero}");
    numero++;
}

Console.WriteLine("Fin de la tabla");
```

```text
Tabla del 8
8 x 1 = 8
8 x 2 = 16
...
8 x 10 = 80
Fin de la tabla
```

- `Tabla` es **fija** (constante), y `numero` es el **contador** que cambia en cada vuelta.
- La cadena con `$"..."` y `{ }` evita concatenar con `+`.
- Para otra tabla basta con cambiar el valor de `Tabla`.

## `while` puede no ejecutarse nunca

Como la condición se comprueba **antes**, si ya es `false` desde el principio el bloque no se ejecuta ni una vez.

```csharp
int intentos = 5;

while (intentos < 3)
{
    Console.WriteLine("Esto no se imprime nunca");
}
```

Si hace falta que el bloque se ejecute **al menos una vez**, se usa `do-while` (lección 20).

## Acumular un resultado

Un bucle sirve para ir sumando valores en una variable:

```csharp
int suma = 0;
int numero = 1;

while (numero <= 5)
{
    suma += numero;
    numero++;
}

Console.WriteLine("Suma de 1 a 5: " + suma);   // 15
```

## Ejemplo

```csharp
int numero = 1;

while (numero <= 3)
{
    Console.WriteLine("Vuelta " + numero);
    numero++;
}

Console.WriteLine("Fin del bucle");
Console.ReadKey();
```

## Comandos

```bash
cd csharp/03-control-flow
dotnet new console -n WhileLoop -o 18-while-loop
cd 18-while-loop
dotnet run
```

Fuente: [curso de C# de INFORMATICONFIG](https://www.youtube.com/watch?v=tq9tWc8P0Rs&list=PL2Z95CSZ1N4F7mym8Ca16RoTDVAlIOPlT&index=18), vídeo 18.
