# Tipos de datos en Java

Equivalente de la lección 04 del curso de C#. En Java, los ejercicios son clases del módulo: este concepto se implementaría en `lab.fundamentals.DataTypes`.

## Qué es un tipo de dato

Un tipo de dato indica qué clase de valor puede guardar una variable, cuánta memoria ocupa y qué operaciones admite. Java, como C#, es de **tipado estático**: el tipo se declara y el compilador lo comprueba.

```java
int edad = 25;
String nombre = "Ana";
```

## Tipos primitivos y tipos por referencia

| | Primitivos | Por referencia |
|---|---|---|
| Guardan | El valor directamente | Una referencia al objeto |
| Ejemplos | `int`, `double`, `boolean`, `char` | `String`, arrays, clases, `Integer` |
| Pueden ser `null` | No | Sí |

Java tiene **8 tipos primitivos**. Todo lo demás es un tipo por referencia.

## Enteros

| Tipo | Tamaño | Rango | Uso habitual |
|---|---|---|---|
| `byte` | 8 bits | −128 a 127 | Datos binarios |
| `short` | 16 bits | −32.768 a 32.767 | Enteros pequeños |
| `int` | 32 bits | −2.147.483.648 a 2.147.483.647 | **El entero por defecto** |
| `long` | 64 bits | −9,2 × 10¹⁸ a 9,2 × 10¹⁸ | Números muy grandes |

```java
int edad = 25;
long poblacion = 8000000000L;   // la L indica que es long
```

Java **no tiene tipos enteros sin signo** (`uint`, `ulong`), y `byte` va de −128 a 127, a diferencia de `byte` en C# (0 a 255).

## Decimales

| Tipo | Tamaño | Precisión | Sufijo | Uso habitual |
|---|---|---|---|---|
| `float` | 32 bits | ~6–7 dígitos | `f` | Gráficos |
| `double` | 64 bits | ~15–16 dígitos | (ninguno) | **El decimal por defecto** |

```java
float temperatura = 36.6f;
double pi = 3.14159265358979;
```

No existe un tipo primitivo `decimal` como en C#. Para dinero se usa la clase `BigDecimal` (`java.math`), que evita los errores de redondeo de `double`.

## Lógico y carácter

| Tipo | Qué guarda | Comillas | Ejemplo |
|---|---|---|---|
| `boolean` | `true` o `false` | — | `true` |
| `char` | **Un solo** carácter (16 bits) | Simples `' '` | `'A'` |

```java
boolean esMayorDeEdad = true;
char letra = 'A';
```

En C# el tipo lógico se llama `bool`; en Java, `boolean`.

## Texto: `String`

`String` es una **clase**, no un primitivo (por eso se escribe con mayúscula). Usa comillas dobles y es inmutable: al modificarla se crea una cadena nueva.

```java
String saludo = "Hola, mundo";
```

## Clases envoltorio (*wrappers*)

Cada primitivo tiene una clase equivalente que permite usarlo como objeto y admite `null`:

| Primitivo | Clase |
|---|---|
| `int` | `Integer` |
| `double` | `Double` |
| `boolean` | `Boolean` |
| `char` | `Character` |

```java
Integer edad = null;   // válido; con int no lo sería
```

Son necesarias, por ejemplo, en las colecciones (`List<Integer>`).

## `var`: tipo inferido

Desde Java 10 se puede usar `var` en variables locales. El tipo sigue siendo fijo.

```java
var edad = 25;        // int
var nombre = "Ana";   // String
```

## Valores por defecto

- Los atributos de una clase valen `0`, `false` o `null` si no se inicializan.
- Las **variables locales deben inicializarse** antes de usarse, o el programa no compila.

## Cómo comprobar un tipo y sus límites

```java
System.out.println(Integer.MAX_VALUE);   // 2147483647
System.out.println(Double.MAX_VALUE);
```

## Comparación con C#

| C# | Java |
|---|---|
| `int`, `long`, `double`, `float` | Igual |
| `bool` | `boolean` |
| `string` | `String` |
| `decimal` | `BigDecimal` (clase) |
| `uint`, `ulong`… | No existen |
| `byte` (0 a 255) | `byte` (−128 a 127) |

## Comandos

```bash
cd java/02-fundamentals
mvn compile
java -cp target/classes lab.fundamentals.DataTypes
```
