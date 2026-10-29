# Concatenación de cadenas en Java

Equivalente de la lección 06 del curso de C#. En Java, cada lección es un archivo `.java` en su carpeta, como el `Program.cs` de C#: este concepto se implementa en `StringConcatenation.java`.

## Qué es concatenar

Concatenar es **unir varios textos en uno solo**. En Java se puede hacer con el operador `+`, con `formatted` (o `String.format`) y con `StringBuilder`. **No existe la interpolación `$"..."` de C#.**

```java
String nombre = "Sol";
int edad = 30;
```

## Con el operador `+`

Une textos con `+`. Si uno de los operandos es un número, Java lo convierte a texto automáticamente.

```java
System.out.println("Nombre de usuario: " + nombre + ", edad: " + edad);
// Nombre de usuario: Sol, edad: 30
```

Hay que cuidar los espacios y los signos: `"Nombre:" + nombre` da `Nombre:Sol`, sin espacio.

## Con `formatted`

Es lo más parecido a la interpolación de C#. Los huecos se marcan con `%s` (texto) y `%d` (entero) y los valores se pasan después, en orden.

```java
System.out.println("Nombre de usuario: %s, edad: %d".formatted(nombre, edad));
// Nombre de usuario: Sol, edad: 30
```

`formatted` existe desde Java 15. `String.format("…", nombre, edad)` hace lo mismo y funciona en versiones anteriores.

| Marcador | Tipo |
|---|---|
| `%s` | Texto (cualquier valor) |
| `%d` | Entero |
| `%f` | Decimal |
| `%n` | Salto de línea |

## Salto de línea con `\n`

`\n` dentro del texto es un salto de línea. Con él se escribe en varias líneas con un solo `println`.

```java
System.out.println("Nombre de usuario: " + nombre + " \nEdad: " + edad);
// Nombre de usuario: Sol
// Edad: 30
```

Si hay un espacio antes de `\n`, ese espacio queda al final de la primera línea. No se ve, pero está.

## `print` y `println`

| Método | Qué hace |
|---|---|
| `System.out.println(...)` | Escribe el texto y salta de línea (como `Console.WriteLine`) |
| `System.out.print(...)` | Escribe el texto **sin** saltar de línea (como `Console.Write`) |

Dos llamadas seguidas pueden formar una sola línea:

```java
System.out.print("Nombre usuario: " + nombre);
System.out.println(", edad: " + edad);
// Nombre usuario: Sol, edad: 30
```

## Expresiones: usar paréntesis

Como no hay llaves `{ }`, para calcular dentro de una concatenación hay que usar **paréntesis**. Sin ellos, Java concatena en lugar de sumar.

```java
System.out.println("Nombre de usuario: " + nombre + ", edad: " + (edad + 5));
// Nombre de usuario: Sol, edad: 35

System.out.println("Nombre de usuario: " + nombre + ", edad: " + edad + 5);
// Nombre de usuario: Sol, edad: 305   (concatena "30" y "5")
```

Después de esa línea, `edad` sigue valiendo `30`.

## Cambiar el valor de una variable y volver a mostrarla

Una variable se puede reasignar, y cada `println` muestra el valor que tiene **en ese momento**.

```java
int edad = 64;
System.out.println(edad);   // 64

edad = 32;
System.out.println(edad);   // 32
```

Mismo caso con texto:

```java
String nombre = "Sol";
System.out.println("Nombre: " + nombre);   // Nombre: Sol

nombre = "Luna";
System.out.println("Nombre: " + nombre);   // Nombre: Luna
```

El tipo no cambia: `edad` sigue siendo `int`. Asignar un valor de otro tipo es un error de compilación:

```java
edad = "treinta";   // error: String no se puede convertir a int
```

## El texto ya construido no se actualiza

Al concatenar, el resultado es **un texto nuevo con los valores de ese momento**. Si después cambia la variable, el texto ya construido **no cambia**.

```java
int edad = 64;
String mensaje = "Edad: " + edad;

edad = 32;

System.out.println(mensaje);            // Edad: 64   (guardó el valor de antes)
System.out.println("Edad: " + edad);    // Edad: 32   (se construye de nuevo)
```

## Orden al sumar y concatenar

Con `+`, Java evalúa de izquierda a derecha. Mientras ambos lados sean números, **suma**; en cuanto aparece un texto, **concatena**. Es igual que en C#.

```java
System.out.println(1 + 2 + "a");     // 3a
System.out.println("a" + 1 + 2);     // a12
System.out.println("a" + (1 + 2));   // a3
```

## Los textos son inmutables

Un `String` no se modifica: concatenar crea una cadena nueva y la original queda igual.

```java
String saludo = "Hola";
String otro = saludo + " Sol";   // crea un texto nuevo; saludo sigue valiendo "Hola"
saludo = saludo + " Sol";        // ahora saludo vale "Hola Sol"
saludo += "!";                   // forma corta de saludo = saludo + "!"
```

Para unir muchos textos en un bucle se usa `StringBuilder`, que modifica el texto sin crear uno nuevo cada vez (se verá más adelante).

## Ejemplo del ejercicio

```java
String nombre = "Sol";
int edad = 30;

System.out.println("Nombre de usuario: " + nombre + ", edad: " + edad);
System.out.println("Nombre de usuario: %s, edad: %d".formatted(nombre, edad));
System.out.println("Nombre de usuario: " + nombre + " \nEdad: " + edad);

System.out.print("Nombre usuario: " + nombre);
System.out.println(", edad: " + edad);

System.out.println("Nombre de usuario: " + nombre + ", edad: " + (edad + 5));
```

Salida:

```text
Nombre de usuario: Sol, edad: 30
Nombre de usuario: Sol, edad: 30
Nombre de usuario: Sol
Edad: 30
Nombre usuario: Sol, edad: 30
Nombre de usuario: Sol, edad: 35
```

## Diferencias con C#

| C# | Java |
|---|---|
| `$"Edad: {edad}"` | `"Edad: " + edad` o `"Edad: %d".formatted(edad)` |
| `$"{edad + 5}"` | `"" + (edad + 5)` (con paréntesis) |
| `Console.WriteLine` | `System.out.println` |
| `Console.Write` | `System.out.print` |
| `string.Format("{0}", x)` | `String.format("%s", x)` |
| `Console.ReadKey()` | No existe |

## Comandos

```bash
cd java/02-fundamentals/06-string-concatenation
java StringConcatenation.java
```
