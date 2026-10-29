# Comentarios en Java

Equivalente de la lección 07 del curso de C#. En Java, cada lección es un archivo `.java` en su carpeta, como el `Program.cs` de C#: este concepto se implementa en `Comments.java`.

## Qué es un comentario

Un comentario es texto que **el compilador ignora**. Sirve para tres cosas:

- **Explicar** el código a quien lo lee, incluido tú dentro de unos meses.
- **Desactivar** líneas sin borrarlas.
- **Organizar** el archivo en secciones o dejar avisos pendientes.

Los comentarios **no afectan al programa**: no se ejecutan y no cambian la velocidad.

## Formas de escribir comentarios

| Forma | Sintaxis | Uso |
|---|---|---|
| Una línea | `// texto` | Comentarios cortos |
| Varias líneas | `/* texto */` | Bloques largos o desactivar varias líneas |
| Documentación Javadoc | `/** texto */` | Documentar clases, métodos y atributos |

Son las mismas formas que en C#, con una diferencia: la documentación usa `/** */` en lugar de `///`.

### Una línea: `//`

Todo lo que hay a la derecha de `//` se ignora. Puede ir **encima** de la línea que explica o **al final** de una instrucción.

```java
// En su propia línea
int anio = 2025;   // al final de una línea
```

Para varias líneas con `//` se repite en cada una:

```java
// Varias líneas
// con // en cada una
// como esta
```

### Varias líneas: `/* ... */`

Todo lo que hay entre `/*` y `*/` se ignora.

```java
/*
   Este comentario ocupa
   varias líneas.
*/
```

También puede ir en medio de una línea:

```java
int total = 10 /* valor inicial */ + 5;
```

Los comentarios de bloque **no se pueden anidar**: el primer `*/` cierra el comentario, aunque hubiera otro `/*` dentro.

### Documentación Javadoc: `/** ... */`

Es un comentario de bloque que empieza con **dos asteriscos**. Se escribe encima de una clase, método o atributo. Los IDE muestran ese texto al pasar el ratón por encima, y la herramienta `javadoc` genera documentación en HTML a partir de él.

```java
/**
 * Suma dos números enteros.
 *
 * @param a primer número
 * @param b segundo número
 * @return la suma de a y b
 */
static int sumar(int a, int b) {
    return a + b;
}
```

Las etiquetas más habituales:

| Etiqueta | Para qué |
|---|---|
| (primera frase) | Qué hace (la más importante) |
| `@param nombre` | Qué es cada parámetro |
| `@return` | Qué devuelve |
| `@throws Excepcion` | Qué excepción puede lanzar |
| `@see` | Enlace a otro elemento |
| `@author` / `@version` / `@since` | Autoría y versión |
| `@deprecated` | Marca algo que ya no se debe usar |
| `{@code x}` | Muestra `x` como código dentro del texto |

Los métodos y las excepciones se ven en lecciones posteriores; aquí basta con saber que existen. En IntelliJ IDEA se genera el esqueleto al escribir `/**` y pulsar `Enter` sobre un método.

## Desactivar código

Un uso muy habitual es **desactivar código** sin borrarlo, por ejemplo para probar algo. Hay tres formas:

| Forma | Cuándo |
|---|---|
| `//` | Una línea o pocas |
| `/* */` | Un bloque, si no contiene otro `/* */` |
| `if (false) { }` | Un bloque que **sí** puede contener `/* */` |

```java
// Con //
// System.out.println("Esta línea no se ejecuta");

// Con /* */
/*
System.out.println("Primera");
System.out.println("Segunda");
*/

// Con if (false): el código se compila pero no se ejecuta
if (false) {
    System.out.println("Esto no se ejecuta");
    /* incluso con comentarios de bloque dentro */
}
```

Java **no tiene** `#if false` como C#. Con `if (false)` el código **se compila** (tiene que ser válido) pero no se ejecuta; con un comentario ni siquiera se compila.

No conviene dejar mucho código comentado: confunde. Para guardar versiones antiguas está Git.

## Organizar: etiquetas, separadores y regiones

### Etiquetas

Muchos editores resaltan ciertas palabras en los comentarios. Sirven para dejar avisos a uno mismo:

```java
// TODO: validar que la edad no sea negativa
// FIXME: falla si el nombre está vacío
// NOTE: este valor es provisional
// HACK: solución temporal hasta cambiar la librería
```

| Etiqueta | Significado |
|---|---|
| `TODO` | Algo pendiente de hacer |
| `FIXME` | Algo que falla y hay que arreglar |
| `NOTE` | Una aclaración importante |
| `HACK` | Una solución provisional |

IntelliJ IDEA lista los `TODO` y `FIXME` en la ventana *TODO*.

### Separadores

Se pueden usar para dividir un archivo en secciones:

```java
// ----- Datos -----
String nombre = "Sol";
int edad = 30;

// ----- Salida -----
System.out.println(nombre + ", " + edad);
```

### Regiones

Java no tiene `#region`, pero IntelliJ IDEA permite **plegar** un bloque con comentarios especiales:

```java
// region Datos
String ciudad = "Madrid";
int espera = 30;
// endregion
```

Son solo comentarios: el compilador los ignora, y solo los entiende el IDE.

## Comodidad: atajos de teclado

Los editores comentan y descomentan varias líneas de una vez: se seleccionan las líneas y se pulsa el atajo.

| Acción | IntelliJ IDEA | Visual Studio Code |
|---|---|---|
| Comentar / descomentar línea (`//`) | `Ctrl + /` | `Ctrl + /` |
| Comentar / descomentar bloque (`/* */`) | `Ctrl + Shift + /` | `Shift + Alt + A` |

- `Ctrl + /` **alterna**: si la línea ya está comentada, la descomenta.
- Sin selección, afecta a la línea donde está el cursor.
- Con varias líneas seleccionadas, afecta a todas.

## Casos curiosos

### Un `//` dentro de un texto no es un comentario

Si `//` o `/*` están dentro de comillas, son parte del texto:

```java
System.out.println("Visita https://ejemplo.com");   // se muestra la URL completa
System.out.println("// esto no es un comentario");  // se muestra tal cual
```

## Qué comentar y qué no

Un buen comentario explica **por qué**, no **qué**: el código ya dice lo que hace.

```java
// Mal: repite lo que ya dice el código
edad = edad + 1;   // suma 1 a edad

// Bien: explica el motivo
edad = edad + 1;   // el usuario acaba de cumplir años
```

| Conviene | Evitar |
|---|---|
| Explicar el motivo de una decisión | Repetir lo que hace cada línea |
| Avisar de casos raros | Dejar mucho código comentado sin uso |
| Documentar métodos y clases con Javadoc | Comentarios que ya no coinciden con el código |
| Dejar `TODO` con una descripción clara | Comentarios vagos como `// arreglar esto` |

Si el código necesita muchos comentarios para entenderse, suele ser mejor dar nombres más claros a las variables.

## Diferencias con C#

| C# | Java |
|---|---|
| `/// <summary>` | `/** … */` con etiquetas `@param`, `@return` |
| `#region` / `#endregion` | `// region` / `// endregion` (solo IntelliJ) |
| `#if false` | `if (false) { }` (el código se compila) |
| `Console.ReadKey()` | No existe |
| Documentación con etiquetas XML | Documentación con etiquetas `@` |

## Resumen

| Quiero… | Uso |
|---|---|
| Un comentario corto | `// texto` |
| Un comentario largo | `/* texto */` |
| Documentar un método | `/** … */` con `@param` y `@return` |
| Desactivar una línea | `// código` |
| Desactivar un bloque | `/* código */` o `if (false) { }` |
| Dejar un aviso | `// TODO: …` |
| Plegar un bloque | `// region` / `// endregion` en IntelliJ |
| Comentar rápido | `Ctrl + /` |

## Ejemplo del ejercicio

Reúne todos los casos de esta lección en un solo programa, en el mismo orden que este documento. Consulta `Comments.java` para verlo completo.

Salida (las líneas comentadas y el bloque `if (false)` no se ejecutan):

```text
Nombre de usuario: Sol, edad: 30
Nombre de usuario: Sol, edad: 30
Nombre de usuario: Sol 
Edad: 30
Nombre usuario: Sol, edad: 30
Suma: 5
2025 15 Madrid 30
Visita https://ejemplo.com
// esto no es un comentario
```

## Comandos

```bash
cd java/02-fundamentals/07-comments
java Comments.java
```
