# Manejo de excepciones en Java

## Qué es una excepción

Un **error que ocurre mientras el programa se ejecuta** y que interrumpe su flujo normal: dividir entre cero, convertir a número un texto que no lo es, usar una posición que no existe en un array, abrir un archivo que no está...

No son errores de sintaxis (esos los detecta el compilador antes), sino de **situaciones que solo se dan al ejecutar**. Si no se controlan, **el programa se detiene** con un mensaje como:

```text
Exception in thread "main" java.lang.ArithmeticException: / by zero
```

El **manejo de excepciones** permite **capturar** el error, saber qué ha pasado y decidir cómo seguir. En Java todas las excepciones son objetos de clases que heredan de **`Throwable`** (lección 38), y guardan su **tipo**, su **mensaje** y la **pila de llamadas** (qué métodos llevaron hasta el error).

## `try` y `catch`

```java
try {
    int resultado = valor1 / valor2;
    System.out.println("Resultado: " + resultado);
} catch (ArithmeticException error) {
    System.out.println("Error: no se puede dividir entre cero");
}
```

```text
try    { código que puede fallar }
catch  { qué hacer si falla }
```

- **`try`**: se **intenta** ejecutar el bloque. Si todo va bien, `catch` se ignora.
- **`catch`**: si ocurre una excepción **del tipo indicado**, el programa **salta** aquí y no ejecuta lo que quedaba en el `try`.
- **`error`** es una variable (el nombre es libre: `e`, `ex`, `error`) con la información del error.
- En el `try` solo cuentan los errores **en ejecución**; los de sintaxis o de lógica no se capturan.

Lo que hay **después** del error dentro del `try` **no se ejecuta**: si falla la división, el `println("Resultado...")` se salta.

## Excepciones concretas

Cada error tiene **su tipo de excepción**. Algunas comunes:

| Excepción | Cuándo ocurre |
|---|---|
| `ArithmeticException` | División entera entre cero |
| `NumberFormatException` | Texto con formato incorrecto (`Integer.parseInt("T")`) |
| `ArrayIndexOutOfBoundsException` | Posición inexistente en un array |
| `IndexOutOfBoundsException` | Posición inexistente en una lista |
| `IllegalArgumentException` | Argumento inválido para un método |
| `NullPointerException` | Usar un objeto que vale `null` |
| `FileNotFoundException` | Archivo que no existe |

### División entre cero

```java
int resultado = 20 / 0;      // ArithmeticException: / by zero
```

Solo ocurre con **enteros**: `20.0 / 0` da `Infinity`, sin excepción (lección 10).

### Formato

```java
try {
    int numero = Integer.parseInt("T");
} catch (NumberFormatException error) {
    System.out.println("Eso no es un número entero");
}
```

`Integer.parseInt` lanza `NumberFormatException` si el texto no es un número. Java no tiene `TryParse`: **este es el modo de controlarlo** (lección 11).

### Array fuera de rango

```java
int[] numeros = { 10, 20, 30, 40 };    // posiciones 0 a 3
System.out.println(numeros[5]);        // ArrayIndexOutOfBoundsException
```

## El mensaje del error

El objeto `error` da información técnica:

```java
System.out.println(error.getMessage());                    // / by zero
System.out.println(error.getClass().getSimpleName());      // ArithmeticException
error.printStackTrace();                                   // tipo + mensaje + pila de llamadas
```

- **`getMessage()`**: el mensaje corto, útil para mostrar o registrar.
- **`printStackTrace()`**: todo el detalle, incluida la pila de llamadas, por la salida de errores. Es lo que conviene guardar en un registro (*log*) para el desarrollador.
- Al usuario final conviene mostrarle **un mensaje comprensible**, no el técnico.

## Varios `catch`

Se pueden poner varios, para tratar de forma distinta cada tipo de error:

```java
try {
    int numero = Integer.parseInt(texto);
    System.out.println(numero / divisor);
} catch (NumberFormatException error) {
    System.out.println("Error de formato");
} catch (ArithmeticException error) {
    System.out.println("Error: división entre cero");
} catch (Exception error) {
    System.out.println("Error inesperado: " + error.getMessage());
}
```

- Se evalúan **en orden** y se ejecuta **solo el primero** que coincide.
- Lo **más específico va primero** y `Exception` (que atrapa casi cualquier error) **al final**. Si `Exception` fuera la primera, el compilador da error.
- Un solo `catch` puede cubrir varios tipos con `|`: `catch (NumberFormatException | ArithmeticException error)`.

## `finally`

Un bloque que se ejecuta **siempre**, haya error o no, incluso con `return` dentro del `try`:

```java
try {
    // intentar algo
} catch (Exception error) {
    // tratar el error
} finally {
    System.out.println("Fin de la operación");
}
```

Sirve para **liberar recursos**: cerrar un archivo, una conexión a una base de datos... (lecciones 42 y 53). Java además tiene **`try-with-resources`**, que cierra los recursos solo.

## Lanzar una excepción: `throw`

Un método puede **lanzar** su propia excepción cuando detecta algo incorrecto:

```java
public void validarEdad(int edad) {
    if (edad < 0) {
        throw new IllegalArgumentException("La edad no puede ser negativa");
    }

    System.out.println("Edad válida: " + edad);
}
```

Quien llame al método decide qué hacer con el error:

```java
try {
    validarEdad(-5);
} catch (IllegalArgumentException error) {
    System.out.println("Error: " + error.getMessage());
}
```

La excepción **sube** por la cadena de llamadas hasta que algún `catch` la captura; si ninguno lo hace, el programa se detiene.

## Excepciones comprobadas y no comprobadas

Característica propia de Java:

| Tipo | Ejemplos | ¿Obliga el compilador a tratarlas? |
|---|---|---|
| **No comprobadas** (*unchecked*) | `ArithmeticException`, `NumberFormatException`, `NullPointerException` | **No** |
| **Comprobadas** (*checked*) | `IOException`, `FileNotFoundException`, `SQLException` | **Sí**: o `try / catch`, o declarar `throws` |

Las comprobadas (típicas de archivos y bases de datos) **no compilan** si no se tratan:

```java
public void leer() throws IOException {      // "este método puede lanzar IOException"
    // ...
}
```

Se verán en la lección de archivos. C# no tiene esta distinción.

## Ejemplo

```java
import java.util.Scanner;

public class ExceptionHandling {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        try {
            System.out.println("Ingrese un número:");
            int numero = Integer.parseInt(scanner.nextLine());
            System.out.println("Número ingresado: " + numero);
        } catch (NumberFormatException error) {
            System.out.println("Eso no es un número entero");
        } finally {
            System.out.println("Fin del programa");
        }
    }
}
```

## Comandos

```bash
cd java/06-advanced-oop/41-exception-handling
java ExceptionHandling.java
```

Fuente: [curso de C# de INFORMATICONFIG](https://www.youtube.com/watch?v=1Zf9Pkxv8zM&list=PL2Z95CSZ1N4F7mym8Ca16RoTDVAlIOPlT&index=41), vídeo 41.
