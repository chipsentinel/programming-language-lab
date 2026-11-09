# Incremento y decremento en Java

## Qué son

Operadores que **aumentan o reducen el valor de una variable** sin escribir la suma o la resta completa.

| Operador | Efecto | Equivale a |
|---|---|---|
| `numero++` | Suma 1 | `numero = numero + 1` |
| `numero--` | Resta 1 | `numero = numero - 1` |

```java
int numero = 5;
System.out.println("Valor original: " + numero);       // 5

numero++;
System.out.println("Valor incrementado: " + numero);   // 6

numero--;
System.out.println("Valor decrementado: " + numero);   // 5
```

Cada `++` o `--` cambia el valor de uno en uno, tantas veces como se escriba.

## Postfijo y prefijo

El operador puede ir **después** (`numero++`) o **antes** (`++numero`) de la variable.

| | Escribe | Qué hace |
|---|---|---|
| Postincremento | `numero++` | Primero **usa** el valor y después lo incrementa |
| Preincremento | `++numero` | Primero **incrementa** y después usa el valor |

Escritos solos en una línea dan el mismo resultado. La diferencia aparece cuando el valor se **usa** en la misma instrucción:

```java
int valor = 7;
int valor2 = valor++;   // valor2 = 7, después valor = 8

int valor3 = 7;
int valor4 = ++valor3;  // valor3 = 8, después valor4 = 8
```

El decremento funciona igual con `--`.

## Operadores compuestos de asignación

Cuando hay que cambiar el valor en **más de una unidad**, se escribe el operador antes del `=`.

| Operador | Equivale a | Con `numero = 5` |
|---|---|---|
| `numero += 3` | `numero = numero + 3` | `8` |
| `numero -= 3` | `numero = numero - 3` | `2` |
| `numero *= 3` | `numero = numero * 3` | `15` |
| `numero /= 3` | `numero = numero / 3` | `1` |
| `numero %= 3` | `numero = numero % 3` | `2` |

```java
int numero = 5;
numero += 3;   // 8
numero *= 2;   // 16
```

- Funciona con cualquier operador aritmético: `+=`, `-=`, `*=`, `/=` y `%=`.
- El operador va **antes** del `=`. Escrito al revés, `numero =+ 3`, no suma: asigna `+3`.
- Con enteros, `/=` descarta los decimales: `5 /= 3` da `1`, porque `5 / 3` es una división entera.
- `++` y `--` son el caso especial de sumar o restar 1.

## Ejemplo

```java
public class IncrementDecrement {
    public static void main(String[] args) {
        int numero = 5;
        System.out.println("Valor original: " + numero);

        numero++;
        System.out.println("Valor incrementado: " + numero);

        numero += 3;
        System.out.println("Después de += 3: " + numero);

        int valor = 7;
        int valor2 = valor++;
        System.out.println("valor2 = " + valor2 + ", valor = " + valor);
    }
}
```

## Comandos

```bash
cd java/02-fundamentals/12-increment-decrement
java IncrementDecrement.java
```

Fuente: [curso de C# de INFORMATICONFIG](https://www.youtube.com/watch?v=o9C6euoH-zU&list=PL2Z95CSZ1N4F7mym8Ca16RoTDVAlIOPlT&index=12), vídeo 12.
