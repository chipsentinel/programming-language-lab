# Operadores aritméticos en Java

## Qué son

Símbolos que realizan **operaciones matemáticas** con variables, constantes o valores numéricos.

| Operador | Operación | Ejemplo | Resultado |
|---|---|---|---|
| `+` | Suma | `90 + 70` | `160` |
| `-` | Resta | `90 - 70` | `20` |
| `*` | Multiplicación | `10 * 70` | `700` |
| `/` | División | `90 / 10` | `9` |
| `%` | Residuo (módulo) | `90 % 20` | `10` |

```java
int valor1 = 90, valor2 = 70, valor3 = 10;

int total = valor1 + valor2;        // 160
int diferencia = valor1 - valor2;   // 20
int producto = valor3 * valor2;     // 700
int resultado = valor1 / valor3;    // 9
int residuo = valor2 % valor3;      // 0
```

## Declarar varias variables en una línea

Varias variables **del mismo tipo** se declaran juntas, separadas por comas. Todas reciben ese tipo.

```java
int valor1 = 90, valor2 = 70, valor3 = 10;
```

Equivale a escribir una línea por variable y ahorra espacio.

## `+` suma o concatena

Con **dos números** suma. Si hay un **texto** a un lado, concatena.

```java
System.out.println(valor1 + valor2);                // 160
System.out.println("Suma: " + valor1 + valor2);     // Suma: 9070
System.out.println("Suma: " + (valor1 + valor2));   // Suma: 160
```

En el segundo caso se evalúa de izquierda a derecha: `"Suma: " + 90` da un texto, y a ese texto se le concatena `70`. Los **paréntesis** fuerzan primero la suma.

## División entera y decimal

Entre dos `int` el resultado es un `int`: **se descartan los decimales**.

```java
System.out.println(90 / 20);     // 4, no 4.5
System.out.println(90 / 20.0);   // 4.5
```

Si uno de los dos es decimal, el resultado es decimal. Con `double` el residuo y la división también dan decimales:

```java
var decimalValor = 2.3;
System.out.println(70 / decimalValor);   // 30.434782608695656
System.out.println(70 % decimalValor);   // 1.0000000000000053
```

Esos decimales de más son el resultado real de la operación en coma flotante. Más adelante se verá cómo redondearlos.

## División entre cero

| | Resultado |
|---|---|
| `int` entre `0` | Error en ejecución: `ArithmeticException` |
| `double` entre `0` | No falla: da `Infinity` |

```java
int cero = 0;
System.out.println(90 / cero);    // ArithmeticException: / by zero
System.out.println(90.0 / 0);     // Infinity
```

Una excepción es un error que detiene el programa. Más adelante se verá cómo capturarlas y manejarlas.

## Potencia y otras operaciones

Java **no tiene operador de potencia**. Potencias, raíces y otras operaciones se hacen con la clase `Math`, que se verá más adelante.

```java
System.out.println(Math.pow(2, 3));   // 8.0
```

`Math.pow` siempre devuelve un `double`.

## Orden de las operaciones

Igual que en matemáticas: primero `*`, `/` y `%`, luego `+` y `-`. Los paréntesis cambian el orden.

```java
System.out.println(2 + 3 * 4);     // 14
System.out.println((2 + 3) * 4);   // 20
```

## Ejemplo

```java
public class ArithmeticOperators {
    public static void main(String[] args) {
        int valor1 = 90, valor2 = 70, valor3 = 10;

        System.out.println("Total de la suma: " + (valor1 + valor2));
        System.out.println("Total de la resta: " + (valor1 - valor2));
        System.out.println("Producto: " + valor3 * valor2);
        System.out.println("Resultado: " + valor1 / valor3);
        System.out.println("Residuo: " + valor2 % valor3);
    }
}
```

## Comandos

```bash
cd java/02-fundamentals/10-arithmetic-operators
java ArithmeticOperators.java
```

Fuente: [curso de C# de INFORMATICONFIG](https://www.youtube.com/watch?v=8Z2pkmVGUK4&list=PL2Z95CSZ1N4F7mym8Ca16RoTDVAlIOPlT&index=10), vídeo 10.
