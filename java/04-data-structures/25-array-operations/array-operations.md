# Operaciones con arrays en Java

## Qué se ve en esta lección

Operaciones habituales con arrays: **sumar** sus valores, **filtrar** los que cumplen una condición, **copiar** un array y **operar dos arrays** posición a posición.

```java
int[] numeros = { 12, 233, 5, 658, 45555 };
```

Para sumar o filtrar se usan los **streams** (flujos): `Arrays.stream(numeros)` convierte el array en un flujo sobre el que se encadenan operaciones. Hace falta `import java.util.Arrays;`. Los streams son el equivalente de **LINQ** en C# (lección 46).

## Sumar: `sum`

```java
int total = Arrays.stream(numeros).sum();
System.out.println("Total de la suma de valores: " + total);   // 46463
```

Es lo mismo que `12 + 233 + 5 + 658 + 45555`, sin escribir un bucle.

## Máximo, mínimo y media

```java
System.out.println(Arrays.stream(numeros).max().getAsInt());       // 45555
System.out.println(Arrays.stream(numeros).min().getAsInt());       // 5
System.out.println(Arrays.stream(numeros).average().getAsDouble()); // 9292.6
```

`max`, `min` y `average` devuelven un valor **opcional** (podría no haber elementos), y por eso se termina con `getAsInt()` o `getAsDouble()`. `sum` no lo necesita porque la suma de nada es 0.

## Filtrar: `filter`

Se queda con los elementos que **cumplen una condición**:

```java
int[] mayoresQue12 = Arrays.stream(numeros).filter(x -> x > 12).toArray();

System.out.println(Arrays.toString(mayoresQue12));   // [233, 658, 45555]
```

- **`x -> x > 12`** es una **expresión lambda**: "para cada elemento `x`, ¿es `x` mayor que 12?". Las lambdas se explicarán más adelante; por ahora basta con saber que lo que hay después de la flecha `->` es la condición.
- `filter` devuelve un **flujo**, no un array. **`toArray()`** lo convierte de nuevo en un array.
- El 12 no aparece: la condición es `>`, no `>=`.

## Copiar un array

### Asignar no copia

```java
int[] alias = numeros;
alias[0] = 999;

System.out.println(numeros[0]);   // 999 ← ¡también cambió!
```

Un array es un **objeto**. `alias = numeros` no crea otro array: hace que `alias` apunte **al mismo**. Cambiar uno cambia el otro.

### `clone`: una copia independiente

```java
int[] cantidades = numeros.clone();
cantidades[0] = 1;

System.out.println(Arrays.toString(numeros));      // [12, 233, 5, 658, 45555]
System.out.println(Arrays.toString(cantidades));   // [1, 233, 5, 658, 45555]
```

Después, cambiar `cantidades` no afecta a `numeros`. También existe `Arrays.copyOf(numeros, numeros.length)`, que además permite elegir un tamaño distinto para la copia.

## Operar dos arrays elemento a elemento

Con dos arrays de la misma longitud:

```java
int[] array1 = { 10, 20, 30, 40 };
int[] array2 = { 1, 2, 3, 4 };
```

Java **no permite** `array1 + array2`: no suma posición a posición. Hay que recorrerlos con un `for` y guardar cada resultado en un array nuevo:

```java
int[] suma = new int[array1.length];

for (int i = 0; i < array1.length; i++) {
    suma[i] = array1[i] + array2[i];
}

System.out.println(Arrays.toString(suma));   // [11, 22, 33, 44]
```

```text
posición:     0    1    2    3
array1:       10   20   30   40
array2:       1    2    3    4
suma:         11   22   33   44
```

El resultado de la posición `i` sale de la posición `i` de cada array.

| Operación | Resultado con los arrays de arriba |
|---|---|
| `array1[i] + array2[i]` | `[11, 22, 33, 44]` |
| `array1[i] - array2[i]` | `[9, 18, 27, 36]` |
| `array1[i] * array2[i]` | `[10, 40, 90, 160]` |
| `array1[i] / array2[i]` | `[10, 10, 10, 10]` |

- El array de resultado se crea con la **misma longitud** que los originales: `new int[array1.length]`.
- Con **longitudes distintas** el `for` recorrería posiciones que no existen en el más corto y fallaría con `ArrayIndexOutOfBoundsException`.
- En la división entre enteros se descartan los decimales.

## Ejemplo

```java
import java.util.Arrays;

public class ArrayOperations {
    public static void main(String[] args) {
        int[] numeros = { 12, 233, 5, 658, 45555 };

        System.out.println("Suma: " + Arrays.stream(numeros).sum());
        System.out.println("Mayores que 12: " + Arrays.toString(Arrays.stream(numeros).filter(x -> x > 12).toArray()));

        int[] copia = numeros.clone();
        System.out.println("Copia: " + Arrays.toString(copia));
    }
}
```

## Comandos

```bash
cd java/04-data-structures/25-array-operations
java ArrayOperations.java
```

Fuente: [curso de C# de INFORMATICONFIG](https://www.youtube.com/watch?v=bo6NbdeYJRY&list=PL2Z95CSZ1N4F7mym8Ca16RoTDVAlIOPlT&index=25), vídeo 25.
