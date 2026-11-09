# Bucle `for` en C#

## Qué es

Un bucle que **repite un bloque de código un número determinado de veces**. Reúne en **una sola línea** las tres partes de un bucle con contador: inicio, condición y actualización.

```csharp
for (int i = 0; i < 4; i++)
{
    Console.WriteLine("Número de vuelta: " + i);
}

Console.WriteLine("Fin del bucle");
```

```text
Número de vuelta: 0
Número de vuelta: 1
Número de vuelta: 2
Número de vuelta: 3
Fin del bucle
```

## Las tres partes

```text
for (  int i = 0  ;  i < 4  ;  i++  )
       └ inicio      └ condición  └ actualización
```

| Parte | Cuándo se ejecuta | En el ejemplo |
|---|---|---|
| **Inicialización** | Una sola vez, antes de empezar | `int i = 0` |
| **Condición** | Antes de cada vuelta; si es `false` el bucle termina | `i < 4` |
| **Actualización** | Al final de cada vuelta | `i++` |

- `i` es la **variable de control**; por costumbre se llama `i`, `j`, `k`.
- La condición marca el límite: `i < 4` empezando en 0 da **4 vueltas**.
- Con `while` estas tres partes iban en sitios distintos; en `for` están juntas y es más difícil olvidar la actualización.

## Empezar en 1 e incluir el límite

Para contar de 1 a 5:

```csharp
for (int i = 1; i <= 5; i++)
{
    Console.WriteLine("Vuelta " + i);
}
```

- `i < 5` llega hasta 4; `i <= 5` **incluye** el 5.
- Empezando en `0` hay una vuelta más que empezando en `1` con el mismo límite.

## Cambiar el paso

La actualización no tiene por qué ser `i++`. Con `i += 2` el contador salta de dos en dos:

```csharp
for (int i = 1; i <= 5; i += 2)
{
    Console.WriteLine("Impar: " + i);   // 1, 3, 5
}
```

Solo da **3 vueltas**: 1, 3 y 5.

## Cuenta atrás

Se empieza en el valor más alto, se usa `>=` y la actualización **resta**:

```csharp
for (int i = 3; i >= 1; i--)
{
    Console.WriteLine("Faltan " + i);
}

Console.WriteLine("¡Despegue!");
```

## Decisiones dentro del bucle

```csharp
for (int i = 1; i <= 5; i++)
{
    if (i == 4)
    {
        Console.WriteLine("Soy la vuelta cuatro");
    }
    else
    {
        Console.WriteLine("Vuelta " + i);
    }
}
```

## Tabla de multiplicar

```csharp
const int Tabla = 8;

for (int i = 1; i <= 10; i++)
{
    Console.WriteLine($"{Tabla} x {i} = {Tabla * i}");
}
```

Es el mismo ejemplo que con `while`, pero más corto.

## Alcance de la variable

La variable declarada en el `for` **solo existe dentro del bucle**. Fuera de él, `i` no está definida:

```csharp
for (int i = 0; i < 3; i++) { }

Console.WriteLine(i);   // error: i no existe aquí
```

Por eso se puede volver a usar el nombre `i` en otro `for` seguido.

## El usuario decide las vueltas

El límite puede ser una variable, por ejemplo un valor que escribe el usuario:

```csharp
Console.WriteLine("Indique la cantidad de vueltas...");
int vueltas = Convert.ToInt32(Console.ReadLine());

for (int i = 1; i <= vueltas; i++)
{
    Console.WriteLine("Número de vuelta: " + i);
}
```

Con `5` da cinco vueltas; con `20`, veinte. Aquí se usa `ReadLine` y la conversión a entero de las lecciones anteriores.

## `for` frente a `while`

| | `for` | `while` |
|---|---|---|
| Se usa cuando | Se **sabe cuántas veces** repetir | **No se sabe** cuántas: depende de una condición |
| Partes del contador | Juntas en la cabecera | Repartidas por el código |
| Ejemplo | Recorrer del 1 al 10 | Repetir hasta que el usuario acierte |

## Ejemplo

```csharp
for (int i = 1; i <= 5; i++)
{
    Console.WriteLine("Vuelta " + i);
}

Console.WriteLine("Fin del bucle");
Console.ReadKey();
```

## Comandos

```bash
cd csharp/03-control-flow
dotnet new console -n ForLoop -o 19-for-loop
cd 19-for-loop
dotnet run
```

Fuente: [curso de C# de INFORMATICONFIG](https://www.youtube.com/watch?v=_9hZ9izNJkE&list=PL2Z95CSZ1N4F7mym8Ca16RoTDVAlIOPlT&index=19), vídeo 19.
