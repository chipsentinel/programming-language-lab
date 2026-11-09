# Entrada de datos en Java

## Qué es la entrada de datos

El proceso por el que un programa **recibe información externa**, normalmente de un usuario, para procesarla, guardarla o usarla. Rellenar un formulario o escribir usuario y clave son entradas de datos.

Hasta ahora el programa solo **mostraba** datos con `System.out.println`. Para **recoger** lo que el usuario escribe se usa la clase `Scanner`.

## `Scanner`

Se crea una vez, indicando que lee del teclado (`System.in`), y hay que importarla.

```java
import java.util.Scanner;

Scanner scanner = new Scanner(System.in);
```

`nextLine()` detiene el programa hasta que el usuario escribe algo y pulsa `Enter`. Devuelve **lo escrito como texto**, que se guarda en una variable.

```java
System.out.println("Introduzca su nombre...");
String nombre = scanner.nextLine();

System.out.println("Nombre colocado: " + nombre);
```

```text
Introduzca su nombre...
Sol
Nombre colocado: Sol
```

- El mensaje de `println` es solo la **pregunta**; sin `nextLine` el programa no espera nada.
- La variable donde se guarda se declara antes del `=`: `String nombre = ...`.
- Al terminar se cierra con `scanner.close()`.

## Pedir números

`nextLine` **siempre devuelve texto**. Para guardarlo en un `int` o un `double` hay que convertirlo, con lo que se vio en la lección de casting.

```java
System.out.println("Introduzca su edad...");
int edad = Integer.parseInt(scanner.nextLine());

System.out.println("Indique su salario...");
double salario = Double.parseDouble(scanner.nextLine());
```

```java
int edad = scanner.nextLine();   // error: no se convierte un String a int solo
```

- Si el usuario escribe algo que no es un número, `parseInt` lanza `NumberFormatException`.
- `Double.parseDouble` exige **punto** como separador decimal: se escribe `1500.50`, no `1500,50`. En C# se usa la coma en un equipo en español.
- Con `try/catch` se puede evitar el error; se verá con las excepciones.

## `nextInt` y `nextDouble`

`Scanner` también tiene `nextInt()` y `nextDouble()`, que convierten directamente. Aquí se usa `nextLine` + `parseInt` porque es lo mismo que hace C# con `ReadLine` + `Convert`, y porque `nextInt` deja el `Enter` sin leer: una `nextLine` posterior devuelve un texto vacío.

```java
int edad = scanner.nextInt();         // deja el salto de línea pendiente
String nombre = scanner.nextLine();   // devuelve "" (el salto pendiente)
```

## Presentar mejor la entrada

Una línea de signos `=` separa visualmente las partes del programa:

```java
System.out.println("==============================");
System.out.println("Introduzca sus datos");
System.out.println("==============================");
```

## Ejemplo

```java
import java.util.Scanner;

public class UserInput {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Introduzca su nombre...");
        String nombre = scanner.nextLine();

        System.out.println("Introduzca su edad...");
        int edad = Integer.parseInt(scanner.nextLine());

        System.out.println("Indique su salario...");
        double salario = Double.parseDouble(scanner.nextLine());

        System.out.println(nombre + " tiene " + edad + " años y cobra " + salario);
        System.out.println("En 10 años tendrá " + (edad + 10));

        scanner.close();
    }
}
```

## Comandos

```bash
cd java/02-fundamentals/11-user-input
java UserInput.java
```

Fuente: [curso de C# de INFORMATICONFIG](https://www.youtube.com/watch?v=Ic8VCjLitjE&list=PL2Z95CSZ1N4F7mym8Ca16RoTDVAlIOPlT&index=11), vídeo 11.
