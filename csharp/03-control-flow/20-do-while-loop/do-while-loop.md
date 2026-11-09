# Bucle `do-while` en C#

## Qué es

Un bucle que **ejecuta el bloque de código al menos una vez** y después lo repite **mientras la condición sea `true`**. La diferencia con `while` y `for` está en **cuándo** se comprueba la condición.

| | `while` | `do-while` |
|---|---|---|
| Comprueba la condición | **Antes** de cada vuelta | **Después** de cada vuelta |
| Vueltas mínimas | 0 | **1** |

```csharp
int i = 1;

do
{
    Console.WriteLine("Vuelta " + i);
    i++;
}
while (i <= 3);

Console.WriteLine("Fin del bucle");
```

```text
Vuelta 1
Vuelta 2
Vuelta 3
Fin del bucle
```

```text
do
{
    código que se repite
}
while (condición);     ← termina en punto y coma
```

`do` significa "haz": haz esto, y luego mira si hay que repetir. El `;` tras el `while` es obligatorio, a diferencia del `while` normal.

## Se ejecuta al menos una vez

Aunque la condición sea falsa desde el principio, el bloque corre **una vez**:

```csharp
int contador = 10;

do
{
    Console.WriteLine("Se ejecuta una vez");
    contador++;
}
while (contador < 3);     // 11 < 3 es false: no se repite
```

Con `while (contador < 3)` el mismo bloque no se ejecutaría **ninguna** vez.

## Caso típico: pedir datos hasta que sean válidos

No se sabe cuántas veces escribirá el usuario, y hay que pedirle un dato **como mínimo una vez**. Es el uso ideal.

```csharp
int numero;

do
{
    Console.WriteLine("Introduce 0 para salir");
    numero = Convert.ToInt32(Console.ReadLine());
    Console.WriteLine("Número colocado: " + numero);
}
while (numero != 0);

Console.WriteLine("Fin del programa");
```

```text
Introduce 0 para salir
5
Número colocado: 5
Introduce 0 para salir
0
Número colocado: 0
Fin del programa
```

- No hay un número fijo de vueltas: termina cuando el usuario escribe `0`.
- Si se escribe un texto que no es un número, `Convert.ToInt32` lanza una excepción (`FormatException`).

## Declarar la variable antes del bucle

La condición está **fuera** del bloque `do { }`. Una variable declarada dentro no sería visible en el `while`:

```csharp
do
{
    int numero = Convert.ToInt32(Console.ReadLine());   // existe solo aquí dentro
}
while (numero != 0);    // error: numero no existe aquí
```

Por eso se declara antes: `int numero;`. No hace falta darle valor, porque el bloque se ejecuta antes de comprobarla.

## Lo mismo con `while`

Con `while` hay que **dar un valor inicial** que permita entrar en el bucle:

```csharp
int numero = 0;

while (numero != 3)
{
    Console.WriteLine("Introduce el número 3 para salir");
    numero = Convert.ToInt32(Console.ReadLine());
}
```

Sin el `= 0` el compilador da error: la variable se usaría sin valor. Con `do-while` no hace falta ese valor ficticio.

## Qué bucle usar

| Bucle | Cuándo |
|---|---|
| `for` | Se sabe **cuántas veces** repetir |
| `while` | Se repite **mientras** se cumpla algo, y puede no ejecutarse |
| `do-while` | Se repite mientras se cumpla algo, pero hace falta ejecutarlo **al menos una vez** |

## Ejemplo

```csharp
int numero;

do
{
    Console.WriteLine("Introduce 0 para salir");
    numero = Convert.ToInt32(Console.ReadLine());
}
while (numero != 0);

Console.WriteLine("Fin del programa");
Console.ReadKey();
```

## Comandos

```bash
cd csharp/03-control-flow
dotnet new console -n DoWhileLoop -o 20-do-while-loop
cd 20-do-while-loop
dotnet run
```

Fuente: [curso de C# de INFORMATICONFIG](https://www.youtube.com/watch?v=9RMg9z62GLU&list=PL2Z95CSZ1N4F7mym8Ca16RoTDVAlIOPlT&index=20), vídeo 20.
