# Variables en Java

Equivalente de la lección 05 del curso de C#. En Java, cada lección es un archivo `.java` en su carpeta, como el `Program.cs` de C#: este concepto se implementa en `Variables.java`.

## Qué es una variable

Una variable es un **espacio con nombre** en la memoria donde el programa guarda un valor. El valor puede cambiar mientras el programa se ejecuta; el tipo, no (Java es de tipado estático, igual que C#).

```java
int edad = 25;
```

```text
int      edad     =    25;
tipo     nombre   =    valor
```

## Declarar, asignar e inicializar

```java
int edad;           // declarar: se reserva el espacio con su tipo
edad = 25;          // asignar: se guarda un valor
int anio = 2025;    // inicializar: declarar y asignar a la vez
```

Igual que en C#, no se puede usar una variable local sin valor asignado:

```java
int edad;
System.out.println(edad);   // error: variable edad might not have been initialized
```

## Cambiar el valor

Se puede reasignar con `=`, siempre con un valor del **mismo tipo**.

```java
int edad = 25;
edad = 26;           // válido
edad = "veintiséis"; // error: String no se puede convertir a int
```

## Declarar varias a la vez

```java
int x = 1, y = 2, z = 3;
```

## `var`: tipo inferido

Desde Java 10, `var` deja que el compilador deduzca el tipo del valor inicial. Solo funciona en variables locales, obliga a inicializar y el tipo queda fijo.

```java
var nombre = "Sol";   // String
var edad = 25;        // int
var nota = 8.5;       // double
```

## Reglas para los nombres

- Pueden contener letras, números, guion bajo `_` y el símbolo `$`.
- **No pueden empezar por un número**: `1edad` no es válido.
- No pueden contener espacios ni otros símbolos (`-`, `@`…).
- **Distinguen mayúsculas de minúsculas**: `edad` y `Edad` son variables distintas.
- No pueden ser palabras reservadas (`int`, `class`, `new`, `void`…).

| Válido | No válido |
|---|---|
| `edad` | `1edad` |
| `_total` | `mi edad` |
| `nombreCompleto` | `nombre-completo` |
| `precio2` | `int` |

## Convenciones

Variables locales en `camelCase`, con nombres descriptivos: `nombreCompleto`, `descuento2` (sin guion bajo). Es la misma convención que en C#.

## Mostrar variables

Java no tiene interpolación con `$` como C#: se concatena con `+`.

```java
String nombre = "Sol";
int edad = 25;

System.out.println("Me llamo " + nombre + " y tengo " + edad + " años.");
System.out.println("Me llamo %s y tengo %d años.".formatted(nombre, edad));   // alternativa
```

## Ejemplo del ejercicio

Una variable de cada tipo habitual, inicializada y mostrada por consola:

```java
String nombre = "informaticonfig";
int edad = 30;
boolean activo = true;
LocalDateTime fecha = LocalDateTime.now();
float precio = 30.5f;
BigDecimal descuento = new BigDecimal("4.6");
double descuento2 = 10.50;

System.out.println("nombre = " + nombre);
System.out.println("edad = " + edad);
System.out.println("activo = " + activo);
System.out.println("fecha = " + fecha);
System.out.println("precio = " + precio);
System.out.println("descuento = " + descuento);
System.out.println("descuento2 = " + descuento2);

// Recuerda para ver el tipo de dato usa getClass().getName()
System.out.println("\nTipo de nombre: " + nombre.getClass().getName());
```

| Variable | Tipo | Qué guarda |
|---|---|---|
| `nombre` | `String` | Texto |
| `edad` | `int` | Número entero |
| `activo` | `boolean` | `true` o `false` |
| `fecha` | `LocalDateTime` | Fecha y hora; `LocalDateTime.now()` es el momento actual |
| `precio` | `float` | Decimal de 32 bits (sufijo `f`) |
| `descuento` | `BigDecimal` | Decimal exacto (equivale a `decimal` de C#) |
| `descuento2` | `double` | Decimal de 64 bits, el decimal por defecto |

- `LocalDateTime` y `BigDecimal` son clases y hay que importarlas (`java.time.LocalDateTime`, `java.math.BigDecimal`). `String` no hace falta importarla.
- `BigDecimal` se crea con `new BigDecimal("4.6")` y el valor entre comillas; no existe un sufijo como el `m` de C#.
- `getClass().getName()` equivale al `GetType()` de C#; devuelve el nombre completo (`java.lang.String`). Solo funciona con objetos, no con tipos primitivos (`int`, `float`…).
- No hay equivalente a `Console.ReadKey()`: al ejecutar desde IntelliJ IDEA la consola no se cierra sola.

## Ámbito

Una variable solo existe dentro del bloque `{ }` donde se declara.

```java
if (true) {
    int contador = 1;
}
System.out.println(contador);   // error: cannot find symbol
```

## Diferencias con C#

| C# | Java |
|---|---|
| `string` | `String` (clase, con mayúscula) |
| `bool` | `boolean` |
| `decimal` | `BigDecimal` |
| `DateTime` | `LocalDateTime` |
| `$"{nombre}"` | `"" + nombre` o `.formatted()` |
| `GetType()` | `getClass().getName()` |
| `Console.ReadKey()` | No existe |

## Comandos

```bash
cd java/02-fundamentals/05-variables
java Variables.java
```
