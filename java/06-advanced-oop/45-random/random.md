# Números aleatorios con `Random` en Java

## Qué es `Random`

Una clase de `java.util` que genera **números pseudoaleatorios**: valores que parecen impredecibles, pero que en realidad salen de una **fórmula matemática**. Por eso se llaman *pseudo*-aleatorios.

Sirve para:

- **Juegos**: tirar un dado, repartir cartas, hacer aparecer enemigos.
- **Simulaciones** y pruebas con datos de ejemplo.
- **Repartir o elegir** elementos al azar (un ganador, una pregunta).
- Datos **temporales** poco sensibles (un identificador de ejemplo).

> **No sirve para contraseñas ni claves de seguridad**: ver el apartado final.

## Crear un objeto `Random`

```java
import java.util.Random;

Random aleatorio = new Random();
```

Se crea **una vez** y se reutiliza para pedir todos los números que hagan falta. `Random` es una clase y `aleatorio` es un objeto (lección 26).

## Los métodos principales

| Método | Devuelve |
|---|---|
| `nextInt()` | Un entero cualquiera (**también negativos**) |
| `nextInt(max)` | Un entero entre `0` y `max - 1` |
| `nextInt(min, max)` | Un entero entre `min` y `max - 1` (**Java 17+**) |
| `nextDouble()` | Un decimal entre `0.0` y `0.999...` |
| `nextBoolean()` | `true` o `false` |

## `nextInt()`: sin límites

```java
int numero1 = aleatorio.nextInt();
```

A diferencia de C#, `nextInt()` devuelve **cualquier `int`, positivo o negativo**: de −2 147 483 648 a 2 147 483 647. Casi siempre hay que acotarlo.

Tentación a evitar: `Math.abs(aleatorio.nextInt())`. Falla en un caso raro: `Math.abs(Integer.MIN_VALUE)` sigue siendo negativo. Es mejor usar `nextInt(max)`.

## `nextInt(max)`: de 0 a un máximo

```java
int numero2 = aleatorio.nextInt(50);        // 0, 1, 2, ... 49
```

**El máximo no se incluye.** `nextInt(50)` nunca devuelve `50`: devuelve de **0 a 49** (50 posibilidades). Es como las posiciones de un array de 50 elementos.

## `nextInt(min, max)`: un rango

```java
int numero3 = aleatorio.nextInt(20, 25);    // 20, 21, 22, 23 o 24
```

| Límite | ¿Se incluye? |
|---|---|
| El **mínimo** (`20`) | **Sí** |
| El **máximo** (`25`) | **No** |

Esta versión de dos argumentos existe **desde Java 17**. En versiones anteriores se calcula con la de un argumento:

```java
int numero = 20 + aleatorio.nextInt(5);     // 20 + (0..4) = 20..24
```

Para un **dado**, del 1 al 6, se escribe `nextInt(1, 7)`:

```java
int dado = aleatorio.nextInt(1, 7);         // 1, 2, 3, 4, 5 o 6
```

Un error muy habitual es escribir `nextInt(1, 6)` y no sacar nunca el 6.

### Cómo comprobar el rango

Un solo valor no demuestra nada. Pidiendo **muchos** y comprobando que todos caen dentro:

```java
boolean todosEnRango = true;
for (int i = 0; i < 1000; i++) {
    int valor = aleatorio.nextInt(20, 25);
    if (valor < 20 || valor > 24) todosEnRango = false;
}
```

## Decimales: `nextDouble`

```java
double d1 = aleatorio.nextDouble();           // entre 0.0 y 1.0 (sin llegar a 1)
double d2 = aleatorio.nextDouble() * 10.0;    // entre 0.0 y 10.0 (sin llegar a 10)
double d3 = aleatorio.nextDouble() * 5 + 2;   // entre 2.0 y 7.0
```

Para un rango, se **multiplica** por la amplitud y se **suma** el mínimo. Como con los enteros, el máximo no se alcanza.

## Semilla (*seed*)

Una fórmula necesita un **punto de partida**: la **semilla**.

```java
Random a = new Random(42);
Random b = new Random(42);

System.out.println(a.nextInt(100) == b.nextInt(100));     // true: misma semilla, mismos números
```

- **Sin semilla**, `Random` usa una que cambia en cada ejecución.
- **Con semilla fija**, la secuencia es **siempre la misma**. Útil para **pruebas reproducibles**.

## Usos habituales

### Cara o cruz

```java
String moneda = aleatorio.nextInt(2) == 0 ? "cara" : "cruz";     // o nextBoolean()
```

### Elegir un elemento de un array

Se elige una **posición** al azar: de `0` a `length - 1`, que es justo lo que da `nextInt(length)`:

```java
String[] colores = { "rojo", "verde", "azul", "amarillo" };
String elegido = colores[aleatorio.nextInt(colores.length)];
```

### Mezclar una lista

```java
Collections.shuffle(lista);
```

## `Math.random()` y `ThreadLocalRandom`

Formas de obtener números aleatorios **sin crear** un objeto `Random`:

```java
double d = Math.random();                              // decimal entre 0.0 y 1.0
int c = (int) (Math.random() * 100) + 1;               // entero del 1 al 100

int e = ThreadLocalRandom.current().nextInt(1, 101);   // entero del 1 al 100
```

- **`Math.random()`**: es la forma más simple y funciona en cualquier versión, pero solo da decimales.
- **`ThreadLocalRandom`** (de `java.util.concurrent`): es el más recomendable cuando hay varios hilos (lección 59); no admite semilla.

## `Random` no es seguro

Los números de `Random` se pueden **predecir**: quien conozca algunos valores puede calcular los siguientes. Por eso **no debe usarse** para contraseñas, claves, tokens ni nada de seguridad.

Para eso existe **`SecureRandom`**, que usa el generador criptográfico del sistema:

```java
import java.security.SecureRandom;

int seguro = new SecureRandom().nextInt(1, 101);     // del 1 al 100
```

Es algo más lento, pero impredecible.

## Resumen

| Quiero... | Escribo... |
|---|---|
| Un entero de 0 a 9 | `nextInt(10)` |
| Un entero de 1 a 100 | `nextInt(1, 101)` |
| Un dado | `nextInt(1, 7)` |
| Un decimal de 0 a 1 | `nextDouble()` |
| Un decimal de 0 a 10 | `nextDouble() * 10` |
| Repetir la secuencia | `new Random(semilla)` |
| Algo seguro | `SecureRandom` |

## Ejemplo

```java
import java.util.Random;

public class RandomLesson {
    public static void main(String[] args) {
        Random aleatorio = new Random();

        int dado = aleatorio.nextInt(1, 7);
        System.out.println("Has sacado un " + dado);

        String[] premios = { "libro", "entrada", "camiseta" };
        System.out.println("Premio: " + premios[aleatorio.nextInt(premios.length)]);
    }
}
```

## Comandos

```bash
cd java/06-advanced-oop/45-random
java RandomLesson.java
```

Fuente: [curso de C# de INFORMATICONFIG](https://www.youtube.com/watch?v=j4WszlGEUF0&list=PL2Z95CSZ1N4F7mym8Ca16RoTDVAlIOPlT&index=45), vídeo 45.
