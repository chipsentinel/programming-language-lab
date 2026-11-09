# Arrays en Java

## Qué es un array

Una estructura de datos que **guarda varios valores del mismo tipo en una sola variable**. En lugar de crear `precio1`, `precio2`, `precio3`..., se crea un único array `precios` con todos.

- Todos los elementos son del **mismo tipo**: `int[]` solo guarda enteros.
- Cada elemento tiene una **posición** (índice), y **la primera es la 0**.
- El **tamaño es fijo**: se decide al crearlo y no cambia.

## Declarar un array

```java
int[] enteros = new int[5];          // 5 enteros
String[] nombres = new String[5];    // 5 textos
double[] precios = new double[5];    // 5 decimales
```

```text
tipo[]  nombre  =  new  tipo[cantidad];
```

- Los **corchetes `[]` van junto al tipo**: `int[]` se lee "array de enteros".
- `new` reserva la memoria; se explicará con las clases.
- El número entre corchetes es la **cantidad de elementos**.

Un array recién creado se rellena con el **valor por defecto** del tipo:

| Tipo | Valor por defecto |
|---|---|
| `int` | `0` |
| `double` | `0.0` |
| `boolean` | `false` |
| `String` | `null` (vacío) |

## Guardar y leer por posición

```java
enteros[0] = 30;
enteros[1] = 6;
enteros[2] = 112;
enteros[3] = 20000;
enteros[4] = 60;

System.out.println(enteros[2]);   // 112
```

```text
posición:  0     1    2     3       4
valor:     30    6    112   20000   60
```

- Sin la posición, `System.out.println(enteros)` no muestra los valores sino una referencia como `[I@6d06d69c`: hay que decir **cuál** se quiere ver.
- Con 5 elementos, las posiciones válidas son **0 a 4**. La 5 **no existe**.

### Fuera de rango

Usar una posición que no existe da un error en ejecución:

```java
enteros[5] = 1;   // ArrayIndexOutOfBoundsException
```

La causa más habitual es contar desde 1. Un array de `n` elementos llega hasta la posición `n - 1`.

### Un solo tipo

```java
enteros[0] = 4.8;      // error: un decimal no cabe en un int
enteros[0] = "hola";   // error: un texto no cabe en un int
```

## Crear un array con sus valores

Si ya se conocen los valores, se escriben entre llaves. El tamaño se calcula solo y las posiciones empiezan en 0:

```java
int[] numeros = { 20, 15, 18, 93, 36, 50, 1, 2, 7, 4 };
String[] personas = { "Juan", "Ana", "Antonio", "Samuel", "Elizabeth" };

System.out.println(numeros[0]);   // 20
System.out.println(numeros[3]);   // 93
```

Ya no hay que escribir `new int[...]`, ni las posiciones una a una. Todos los valores deben ser del tipo del array.

## Longitud: `length`

El número de elementos:

```java
System.out.println(numeros.length);   // 10
```

En un array **no lleva paréntesis** (es un campo). En un `String` sí: `texto.length()`. La última posición es siempre `length - 1`.

## Recorrer un array

Con un `for`, usando `length` como límite:

```java
for (int i = 0; i < numeros.length; i++) {
    System.out.println("numeros[" + i + "] = " + numeros[i]);
}
```

- `i` empieza en 0 y llega a `length - 1`: `i < numeros.length`.
- Es la mejor forma de **hacer algo con cada elemento**.

## Mostrar todo en una línea

Java tiene la clase `Arrays` (con `import java.util.Arrays;`):

```java
System.out.println(Arrays.toString(numeros));
// [20, 15, 18, 93, 36, 50, 1, 2, 7, 4]
```

Para un array de **textos** también sirve `String.join`, que no añade corchetes:

```java
System.out.println(String.join(", ", personas));
// Juan, Ana, Antonio, Samuel, Elizabeth
```

Solo sirven para mostrar. Si hay que operar con cada elemento, se usa el `for`.

> En este proyecto la clase se llama `Arrays24` para no chocar con `java.util.Arrays`.

## Métodos de la clase `Arrays`

| Método | Qué hace |
|---|---|
| `Arrays.sort(a)` | Ordena de menor a mayor (alfabético en textos) |
| `Arrays.fill(a, valor)` | Rellena todo con un valor |
| `Arrays.toString(a)` | Texto con todos los elementos |

```java
Arrays.sort(numeros);
System.out.println(Arrays.toString(numeros));   // [1, 2, 4, 7, 15, 18, 20, 36, 50, 93]
```

- **Cambian el propio array**: después de `sort`, el orden original se pierde.
- Con textos, `sort` ordena alfabéticamente: `Ana, Antonio, Elizabeth, Juan, Samuel`.

### Invertir

Java no tiene un método para invertir un `int[]`. Se intercambian el primero con el último, el segundo con el penúltimo, y así hasta la mitad:

```java
for (int i = 0; i < numeros.length / 2; i++) {
    int temporal = numeros[i];
    numeros[i] = numeros[numeros.length - 1 - i];
    numeros[numeros.length - 1 - i] = temporal;
}
```

Para tener de mayor a menor: `sort` y luego invertir. Invertir solo da la vuelta a lo que haya, **no ordena**.

### Buscar

`Arrays` no tiene `indexOf`. Un array de objetos (como `String[]`) se convierte en lista con `Arrays.asList`, que sí lo tiene:

```java
System.out.println(Arrays.asList(personas).indexOf("Ana"));    // posición donde está
System.out.println(Arrays.asList(personas).indexOf("Luis"));   // -1: no existe
```

El `-1` significa "no encontrado", igual que en `String.indexOf`.

### Rellenar y vaciar

```java
Arrays.fill(numeros, 99);      // 99, 99, 99, ... en todas las posiciones
Arrays.fill(personas, "");     // todos vacíos
```

No existe un `clear`: se rellena con el valor vacío. El array **conserva su tamaño**: no se pueden quitar posiciones.

## Tamaño fijo

Un array no puede crecer ni encogerse. Para colecciones a las que se añaden o quitan elementos existen las **listas** (lección 33).

## Ejemplo

```java
import java.util.Arrays;

public class Arrays24 {
    public static void main(String[] args) {
        int[] numeros = { 20, 15, 18, 93, 36 };

        System.out.println("Elementos: " + numeros.length);

        Arrays.sort(numeros);
        System.out.println("Ordenado: " + Arrays.toString(numeros));

        for (int i = 0; i < numeros.length; i++) {
            System.out.println("Posición " + i + ": " + numeros[i]);
        }
    }
}
```

## Comandos

```bash
cd java/04-data-structures/24-arrays
java Arrays24.java
```

Fuente: [curso de C# de INFORMATICONFIG](https://www.youtube.com/watch?v=MiOWZ0s3Wx8&list=PL2Z95CSZ1N4F7mym8Ca16RoTDVAlIOPlT&index=24), vídeo 24.
