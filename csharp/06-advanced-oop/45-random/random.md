# Números aleatorios con `Random` en C#

## Qué es `Random`

Una clase que genera **números pseudoaleatorios**: valores que parecen impredecibles, pero que en realidad salen de una **fórmula matemática**. Por eso se llaman *pseudo*-aleatorios.

Sirve para:

- **Juegos**: tirar un dado, repartir cartas, hacer aparecer enemigos.
- **Simulaciones** y pruebas con datos de ejemplo.
- **Repartir o elegir** elementos al azar (un ganador, una pregunta).
- Datos **temporales** poco sensibles (un identificador de ejemplo).

> **No sirve para contraseñas ni claves de seguridad**: ver el apartado final.

## Crear un objeto `Random`

```csharp
Random aleatorio = new Random();
```

Se crea **una vez** y se reutiliza para pedir todos los números que hagan falta. Se explica en la lección de clases (26): `Random` es una clase y `aleatorio` es un objeto.

## Los métodos principales

| Método | Devuelve |
|---|---|
| `Next()` | Un entero entre `0` y `int.MaxValue - 1` |
| `Next(max)` | Un entero entre `0` y `max - 1` |
| `Next(min, max)` | Un entero entre `min` y `max - 1` |
| `NextDouble()` | Un decimal entre `0.0` y `0.999...` |

## `Next()`: sin límites

```csharp
int numero1 = aleatorio.Next();
Console.WriteLine($"Número aleatorio: {numero1}");     // 1026083190
```

Da un entero entre **0 y 2 147 483 646**. Un valor enorme que, casi siempre, hay que acotar.

## `Next(max)`: de 0 a un máximo

```csharp
int numero2 = aleatorio.Next(50);        // 0, 1, 2, ... 49
```

**El máximo no se incluye.** `Next(50)` nunca devuelve `50`: devuelve de **0 a 49** (50 posibilidades). Es como las posiciones de un array de 50 elementos.

Con `Next(500)` los valores llegan hasta `499`; con `Next(10)`, hasta `9`.

## `Next(min, max)`: un rango

```csharp
int numero3 = aleatorio.Next(20, 25);    // 20, 21, 22, 23 o 24
```

| Límite | ¿Se incluye? |
|---|---|
| El **mínimo** (`20`) | **Sí** |
| El **máximo** (`25`) | **No** |

Es la regla general. Para un **dado**, del 1 al 6, se escribe `Next(1, 7)`:

```csharp
int dado = aleatorio.Next(1, 7);         // 1, 2, 3, 4, 5 o 6
```

Un error muy habitual es escribir `Next(1, 6)` y no sacar nunca el 6.

### Cómo comprobar el rango

Un solo valor no demuestra nada. Pidiendo **muchos** y comprobando que todos caen dentro:

```csharp
bool todosEnRango = true;
for (int i = 0; i < 1000; i++)
{
    int valor = aleatorio.Next(20, 25);
    if (valor < 20 || valor > 24) todosEnRango = false;
}
```

## Decimales: `NextDouble`

`Next` solo devuelve **enteros**. Para decimales:

```csharp
double d1 = aleatorio.NextDouble();           // entre 0.0 y 1.0 (sin llegar a 1)
double d2 = aleatorio.NextDouble() * 10.0;    // entre 0.0 y 10.0 (sin llegar a 10)
double d3 = aleatorio.NextDouble() * 5 + 2;   // entre 2.0 y 7.0
```

Para un rango, se **multiplica** por la amplitud y se **suma** el mínimo. Como con los enteros, el máximo no se alcanza (se verá `9,99...` pero nunca `10`).

Si se asigna `Next` a un `double` no da error, pero seguirá siendo un entero: hay que usar `NextDouble`.

## Semilla (*seed*)

Una fórmula necesita un **punto de partida**: la **semilla**.

```csharp
Random a = new Random(42);
Random b = new Random(42);

Console.WriteLine(a.Next(100) == b.Next(100));     // True: misma semilla, mismos números
```

- **Sin semilla**, `Random` usa una que depende del reloj: por eso cambia en cada ejecución.
- **Con semilla fija**, la secuencia es **siempre la misma**. Útil para **pruebas reproducibles** o para que un juego genere el mismo mapa.

## Usos habituales

### Cara o cruz

```csharp
string moneda = aleatorio.Next(2) == 0 ? "cara" : "cruz";
```

### Elegir un elemento de un array

Se elige una **posición** al azar: de `0` a `Length - 1`, que es justo lo que da `Next(Length)`:

```csharp
string[] colores = { "rojo", "verde", "azul", "amarillo" };
string elegido = colores[aleatorio.Next(colores.Length)];
```

Es una de las razones de que el máximo no se incluya.

### Mezclar

Desde .NET 8 hay un método para barajar un array:

```csharp
Random.Shared.Shuffle(colores);
```

## `Random.Shared`

Desde .NET 6 hay un objeto compartido, listo para usar sin crearlo:

```csharp
int numero = Random.Shared.Next(1, 101);     // del 1 al 100
```

Es la forma más cómoda cuando no se necesita una semilla.

## `Random` no es seguro

Los números de `Random` se pueden **predecir**: quien conozca la semilla o algunos valores puede calcular los siguientes. Por eso **no debe usarse** para contraseñas, claves, tokens ni nada de seguridad.

Para eso existe **`RandomNumberGenerator`**, que usa el generador criptográfico del sistema:

```csharp
using System.Security.Cryptography;

int seguro = RandomNumberGenerator.GetInt32(1, 101);     // del 1 al 100
```

Es algo más lento, pero impredecible.

## Resumen

| Quiero... | Escribo... |
|---|---|
| Un entero de 0 a 9 | `Next(10)` |
| Un entero de 1 a 100 | `Next(1, 101)` |
| Un dado | `Next(1, 7)` |
| Un decimal de 0 a 1 | `NextDouble()` |
| Un decimal de 0 a 10 | `NextDouble() * 10` |
| Repetir la secuencia | `new Random(semilla)` |
| Algo seguro | `RandomNumberGenerator` |

## Ejemplo

```csharp
Random aleatorio = new Random();

int dado = aleatorio.Next(1, 7);
Console.WriteLine("Has sacado un " + dado);

string[] premios = { "libro", "entrada", "camiseta" };
Console.WriteLine("Premio: " + premios[aleatorio.Next(premios.Length)]);

Console.ReadKey();
```

## Comandos

```bash
cd csharp/06-advanced-oop
dotnet new console -n RandomLesson -o 45-random
cd 45-random
dotnet run
```

Fuente: [curso de C# de INFORMATICONFIG](https://www.youtube.com/watch?v=j4WszlGEUF0&list=PL2Z95CSZ1N4F7mym8Ca16RoTDVAlIOPlT&index=45), vídeo 45.
