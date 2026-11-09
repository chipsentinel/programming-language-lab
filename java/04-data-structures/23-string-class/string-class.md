# La clase `String` en Java

## Qué es

`String` es el tipo de dato para **texto**: una secuencia de caracteres. Es una **clase**, lo que significa que además de guardar el texto trae un conjunto de **métodos** para trabajar con él: ver su longitud, pasarlo a mayúsculas, buscar dentro, cortarlo...

```java
String texto = "Hola a todos";
```

Para usar un método se escribe un punto después de la variable: `texto.toUpperCase()`. El IDE muestra al escribir el punto la lista de todo lo disponible.

## Texto en varias líneas

Un texto largo se sale de la pantalla y es incómodo de leer. Hay dos formas de repartirlo en líneas.

**1. Concatenar con `+`**, y el salto de línea con `\n`:

```java
String lineas = "Esta es la primera línea\n" +
                "Esta es la segunda línea\n" +
                "Esta es la tercera línea";
```

- El `+` une las partes **en el código**; es el `\n` el que hace el salto **al mostrarlo**.

**2. Bloque de texto con `"""`** (desde Java 15): se escribe tal cual, con saltos de línea reales, sin `+` ni `\n`:

```java
String lineas = """
        Esta es la primera línea
        Esta es la segunda línea
        Esta es la tercera línea""";
```

- Después de las primeras `"""` hay que **saltar de línea**.
- Java **quita la sangría común** de todas las líneas, así que el margen izquierdo del código no aparece en el texto.
- Si las `"""` de cierre van en una línea aparte, el texto termina con un salto de línea.

## Caracteres especiales

Algunos caracteres no se pueden escribir directamente dentro de un texto. Se usan **secuencias de escape** con la barra invertida `\`:

| Secuencia | Significa |
|---|---|
| `\n` | Salto de línea |
| `\t` | Tabulador |
| `\"` | Comillas dobles |
| `\\` | Una barra invertida |

```java
System.out.println("Comillas: \"mundo\"");     // Comillas: "mundo"
System.out.println("Ruta: C:\\Datos\\foto");   // Ruta: C:\Datos\foto
```

Las comillas dentro de un texto necesitan `\"`: si no, el compilador cree que el texto termina ahí.

## Métodos principales

Con `texto = "Hola a todos"`:

| Método | Qué hace | Resultado |
|---|---|---|
| `texto.length()` | Número de caracteres | `12` |
| `texto.toUpperCase()` | Pasa a mayúsculas | `HOLA A TODOS` |
| `texto.toLowerCase()` | Pasa a minúsculas | `hola a todos` |
| `texto.contains("todos")` | ¿Contiene ese texto? | `true` |
| `texto.startsWith("Hola")` | ¿Empieza por…? | `true` |
| `texto.endsWith("todos")` | ¿Termina en…? | `true` |
| `texto.indexOf("do")` | Posición donde aparece | `9` |
| `texto.substring(2, 6)` | Extrae una parte | `la a` |
| `texto.replace("a todos", "mundo")` | Sustituye texto | `Hola mundo` |
| `"  hola  ".trim()` | Quita espacios de los extremos | `hola` |

A diferencia de C#, `length()` **sí lleva paréntesis**: en Java es un método.

### Longitud

Cuenta **todos** los caracteres: letras, signos de puntuación y **espacios**. Un texto de cuatro espacios tiene longitud 4.

### Mayúsculas y minúsculas

```java
System.out.println(texto.toUpperCase());   // HOLA A TODOS
System.out.println(texto.toLowerCase());   // hola a todos
```

### `contains`, `startsWith`, `endsWith`

Devuelven `true` o `false` y **distinguen mayúsculas de minúsculas**:

```java
System.out.println(texto.startsWith("Hola"));   // true
System.out.println(texto.startsWith("hola"));   // false: la h es minúscula
System.out.println(texto.contains("mundo"));    // false
```

## Índices: la posición empieza en 0

Cada carácter tiene una posición, y **la primera es la 0**, no la 1:

```text
H  o  l  a     a     t  o  d  o  s
0  1  2  3  4  5  6  7  8  9  10 11
```

```java
System.out.println(texto.charAt(0));        // H
System.out.println(texto.indexOf("do"));    // 9
System.out.println(texto.indexOf("tr"));    // -1
```

- En Java no se puede escribir `texto[0]`; se usa `charAt(0)`.
- `indexOf` devuelve la posición de la **primera coincidencia**.
- Si el texto **no existe** devuelve **`-1`**. Un `-1` siempre significa "no encontrado".

## `substring`

Extrae una parte del texto: `substring(inicio, fin)`.

```java
texto.substring(2, 6);   // "la a": desde la posición 2 hasta la 6, sin incluir la 6
texto.substring(1, 5);   // "ola "
texto.substring(5);      // "a todos": desde la 5 hasta el final
```

**Cuidado:** en C# `Substring(2, 4)` significa "desde la 2, **cuatro** caracteres". En Java el segundo número es la **posición final**, que no se incluye: `substring(2, 6)`. La cantidad de caracteres es `fin - inicio`.

## `replace`

Sustituye **todas** las apariciones de un texto por otro:

```java
System.out.println(texto.replace("a todos", "mundo"));   // Hola mundo
```

## `trim`

Quita los espacios del **principio y del final** (no los de en medio):

```java
String conEspacios = "   Hola a todos   ";

System.out.println(conEspacios.length());         // 18
System.out.println(conEspacios.trim().length());  // 12
```

Desde Java 11 existe también `strip()`, que además reconoce los espacios Unicode.

## Los `String` no se modifican

Los métodos **no cambian el texto original**: devuelven uno nuevo. Si no se guarda, el resultado se pierde. Se dice que los `String` son **inmutables**.

```java
texto.toUpperCase();                    // no hace nada visible
System.out.println(texto);              // Hola a todos: sigue igual

String mayusculas = texto.toUpperCase();   // así se conserva
```

## Encadenar métodos

Se pueden unir varios, y se aplican de **izquierda a derecha**:

```java
System.out.println(conEspacios.trim().toUpperCase());   // HOLA A TODOS
System.out.println(conEspacios.trim().length());        // 12
```

## Ejemplo

```java
public class StringClass {
    public static void main(String[] args) {
        String texto = "Hola a todos";

        System.out.println("Texto: " + texto);
        System.out.println("Caracteres: " + texto.length());
        System.out.println("Mayúsculas: " + texto.toUpperCase());
        System.out.println("Contiene \"todos\": " + texto.contains("todos"));
        System.out.println("Reemplazado: " + texto.replace("a todos", "mundo"));
    }
}
```

## Comandos

```bash
cd java/04-data-structures/23-string-class
java StringClass.java
```

Fuente: [curso de C# de INFORMATICONFIG](https://www.youtube.com/watch?v=v1f3dJsNreU&list=PL2Z95CSZ1N4F7mym8Ca16RoTDVAlIOPlT&index=23), vídeo 23.
