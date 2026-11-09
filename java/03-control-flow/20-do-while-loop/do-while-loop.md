# Bucle `do-while` en Java

## Qué es

Un bucle que **ejecuta el bloque de código al menos una vez** y después lo repite **mientras la condición sea `true`**. La diferencia con `while` y `for` está en **cuándo** se comprueba la condición.

| | `while` | `do-while` |
|---|---|---|
| Comprueba la condición | **Antes** de cada vuelta | **Después** de cada vuelta |
| Vueltas mínimas | 0 | **1** |

```java
int i = 1;

do {
    System.out.println("Vuelta " + i);
    i++;
} while (i <= 3);

System.out.println("Fin del bucle");
```

```text
Vuelta 1
Vuelta 2
Vuelta 3
Fin del bucle
```

```text
do {
    código que se repite
} while (condición);     ← termina en punto y coma
```

`do` significa "haz": haz esto, y luego mira si hay que repetir. El `;` tras el `while` es obligatorio, a diferencia del `while` normal.

## Se ejecuta al menos una vez

Aunque la condición sea falsa desde el principio, el bloque corre **una vez**:

```java
int contador = 10;

do {
    System.out.println("Se ejecuta una vez");
    contador++;
} while (contador < 3);     // 11 < 3 es false: no se repite
```

Con `while (contador < 3)` el mismo bloque no se ejecutaría **ninguna** vez.

## Caso típico: pedir datos hasta que sean válidos

No se sabe cuántas veces escribirá el usuario, y hay que pedirle un dato **como mínimo una vez**. Es el uso ideal.

```java
Scanner scanner = new Scanner(System.in);
int numero;

do {
    System.out.println("Introduce 0 para salir");
    numero = Integer.parseInt(scanner.nextLine());
    System.out.println("Número colocado: " + numero);
} while (numero != 0);

System.out.println("Fin del programa");
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
- Si se escribe un texto que no es un número, `parseInt` lanza una excepción (`NumberFormatException`).

## Declarar la variable antes del bucle

La condición está **fuera** del bloque `do { }`. Una variable declarada dentro no sería visible en el `while`:

```java
do {
    int numero = Integer.parseInt(scanner.nextLine());   // existe solo aquí dentro
} while (numero != 0);    // error: numero no existe aquí
```

Por eso se declara antes: `int numero;`. No hace falta darle valor, porque el bloque se ejecuta antes de comprobarla.

## Lo mismo con `while`

Con `while` hay que **dar un valor inicial** que permita entrar en el bucle:

```java
int numero = 0;

while (numero != 3) {
    System.out.println("Introduce el número 3 para salir");
    numero = Integer.parseInt(scanner.nextLine());
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

```java
import java.util.Scanner;

public class DoWhileLoop {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int numero;

        do {
            System.out.println("Introduce 0 para salir");
            numero = Integer.parseInt(scanner.nextLine());
        } while (numero != 0);

        System.out.println("Fin del programa");
        scanner.close();
    }
}
```

## Comandos

```bash
cd java/03-control-flow/20-do-while-loop
java DoWhileLoop.java
```

Fuente: [curso de C# de INFORMATICONFIG](https://www.youtube.com/watch?v=9RMg9z62GLU&list=PL2Z95CSZ1N4F7mym8Ca16RoTDVAlIOPlT&index=20), vídeo 20.
