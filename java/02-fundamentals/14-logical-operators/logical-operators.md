# Operadores lógicos en Java

## Qué son

Operadores que **combinan o modifican expresiones `boolean`** (verdadero o falso) y devuelven otro `boolean`. Se usan para evaluar varias condiciones a la vez.

| Operador | Nombre | Significa | Ejemplo |
|---|---|---|---|
| `&&` | AND (y) | Se cumplen **las dos** condiciones | `a < b && a < c` |
| `\|\|` | OR (o) | Se cumple **al menos una** | `a < b \|\| a < c` |
| `!` | NOT (no) | **Invierte** el valor | `!(a < b)` |

La barra vertical `|` suele estar en la tecla a la izquierda del `1`; `||` son dos.

## AND (`&&`)

Da `true` solo si **ambas** condiciones son `true`.

| `A` | `B` | `A && B` |
|---|---|---|
| true | true | **true** |
| true | false | false |
| false | true | false |
| false | false | false |

```java
int numero1 = 40, numero2 = 90, numero3 = 5;

boolean resultado = numero1 < numero2 && numero1 < numero3;   // true && false
System.out.println(resultado);   // false

numero3 = 50;
resultado = numero1 < numero2 && numero1 < numero3;           // true && true
System.out.println(resultado);   // true
```

## OR (`||`)

Da `true` si **al menos una** condición es `true`. Solo es `false` si ninguna se cumple.

| `A` | `B` | `A \|\| B` |
|---|---|---|
| true | true | **true** |
| true | false | **true** |
| false | true | **true** |
| false | false | false |

```java
int numero1 = 40, numero2 = 90, numero3 = 50;

System.out.println(numero1 < numero2 || numero1 < numero3);   // true

numero1 = 100;
System.out.println(numero1 < numero2 || numero1 < numero3);   // false
```

## NOT (`!`)

**Invierte** el valor: `true` pasa a `false` y `false` a `true`. Se escribe delante de la expresión, y si la expresión tiene varias partes se pone entre paréntesis.

```java
boolean resultado = numero1 < numero2 && numero1 < numero3;   // false
System.out.println(resultado);    // false
System.out.println(!resultado);   // true

System.out.println(!(numero1 < numero2 && numero1 < numero3));   // true
```

Sirve para ahorrar código al validar datos. Se verá con los condicionales.

## Evaluación en cortocircuito

`&&` y `||` **dejan de evaluar en cuanto conocen el resultado**.

- En `A && B`, si `A` es `false`, ya no se evalúa `B`: el resultado es `false`.
- En `A || B`, si `A` es `true`, ya no se evalúa `B`: el resultado es `true`.

Sirve para evitar errores:

```java
int divisor = 0;
boolean seguro = divisor != 0 && 10 / divisor > 1;   // 10 / 0 nunca se calcula
```

Si se escribiera `10 / divisor > 1 && divisor != 0`, lanzaría `ArithmeticException`.

## Orden de evaluación

Primero `!`, después `&&` y al final `||`. Los paréntesis cambian el orden.

```java
System.out.println(true || false && false);     // true: primero false && false
System.out.println((true || false) && false);   // false
```

Ante la duda conviene poner paréntesis: se lee mejor.

## Combinar con operadores de comparación

Los operadores de comparación dan `boolean`, así que se combinan con los lógicos:

```java
int edad = 20;
boolean esMayorDeEdad = edad >= 18;
boolean puedeVotar = edad >= 18 && edad < 120;
```

## Ejemplo

```java
public class LogicalOperators {
    public static void main(String[] args) {
        int numero1 = 40, numero2 = 90, numero3 = 50;

        System.out.println("AND: " + (numero1 < numero2 && numero1 < numero3));
        System.out.println("OR: " + (numero1 > numero2 || numero1 < numero3));
        System.out.println("NOT: " + !(numero1 < numero2));
    }
}
```

## Comandos

```bash
cd java/02-fundamentals/14-logical-operators
java LogicalOperators.java
```

Fuente: [curso de C# de INFORMATICONFIG](https://www.youtube.com/watch?v=QBCcwzUFjpQ&list=PL2Z95CSZ1N4F7mym8Ca16RoTDVAlIOPlT&index=14), vídeo 14.
