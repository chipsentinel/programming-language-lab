# Comentarios en JavaScript

Equivalente de la lección 07 del curso de C#.

## Qué es un comentario

Un comentario es texto que **el intérprete ignora**. Sirve para tres cosas:

- **Explicar** el código a quien lo lee, incluido tú dentro de unos meses.
- **Desactivar** líneas sin borrarlas.
- **Organizar** el archivo en secciones o dejar avisos pendientes.

Los comentarios **no afectan al programa**: no se ejecutan y no cambian la velocidad.

## Formas de escribir comentarios

| Forma | Sintaxis | Uso |
|---|---|---|
| Una línea | `// texto` | Comentarios cortos |
| Varias líneas | `/* texto */` | Bloques largos o desactivar varias líneas |
| Documentación JSDoc | `/** texto */` | Documentar funciones, clases y variables |

Son las mismas formas que en C# y Java, con una diferencia: la documentación usa `/** */` en lugar de `///`.

### Una línea: `//`

Todo lo que hay a la derecha de `//` se ignora. Puede ir **encima** de la línea que explica o **al final** de una instrucción.

```javascript
// En su propia línea
let anio = 2025;   // al final de una línea
```

Para varias líneas con `//` se repite en cada una:

```javascript
// Varias líneas
// con // en cada una
// como esta
```

### Varias líneas: `/* ... */`

Todo lo que hay entre `/*` y `*/` se ignora.

```javascript
/*
   Este comentario ocupa
   varias líneas.
*/
```

También puede ir en medio de una línea:

```javascript
let total = 10 /* valor inicial */ + 5;
```

Los comentarios de bloque **no se pueden anidar**: el primer `*/` cierra el comentario, aunque hubiera otro `/*` dentro.

### Documentación JSDoc: `/** ... */`

Es un comentario de bloque que empieza con **dos asteriscos**. Se escribe encima de una función, clase o variable. Los editores como Visual Studio Code muestran ese texto al pasar el ratón por encima o al escribir la llamada.

```javascript
/**
 * Suma dos números.
 *
 * @param {number} a - Primer número.
 * @param {number} b - Segundo número.
 * @returns {number} La suma de a y b.
 */
function sumar(a, b) {
    return a + b;
}
```

Las etiquetas más habituales:

| Etiqueta | Para qué |
|---|---|
| (primera frase) | Qué hace (la más importante) |
| `@param {tipo} nombre` | Qué es cada parámetro y de qué tipo |
| `@returns {tipo}` | Qué devuelve |
| `@throws {tipo}` | Qué error puede lanzar |
| `@example` | Un ejemplo de uso |
| `@deprecated` | Marca algo que ya no se debe usar |

Como JavaScript no declara tipos, JSDoc es la forma de **indicarlos** sin cambiar el código. Las funciones y los errores se ven en lecciones posteriores; aquí basta con saber que existen. En Visual Studio Code se genera el esqueleto al escribir `/**` y pulsar `Enter` sobre una función.

## Desactivar código

Un uso muy habitual es **desactivar código** sin borrarlo, por ejemplo para probar algo. Hay tres formas:

| Forma | Cuándo |
|---|---|
| `//` | Una línea o pocas |
| `/* */` | Un bloque, si no contiene otro `/* */` |
| `if (false) { }` | Un bloque que **sí** puede contener `/* */` |

```javascript
// Con //
// console.log("Esta línea no se ejecuta");

// Con /* */
/*
console.log("Primera");
console.log("Segunda");
*/

// Con if (false): el código no se ejecuta
if (false) {
    console.log("Esto no se ejecuta");
    /* incluso con comentarios de bloque dentro */
}
```

JavaScript **no tiene** `#if false` como C#. Con `if (false)` el código **sigue en el programa** (tiene que ser válido) pero no se ejecuta; con un comentario el intérprete ni lo lee.

No conviene dejar mucho código comentado: confunde. Para guardar versiones antiguas está Git.

## Organizar: etiquetas, separadores y regiones

### Etiquetas

Muchos editores resaltan ciertas palabras en los comentarios. Sirven para dejar avisos a uno mismo:

```javascript
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

En Visual Studio Code se resaltan con extensiones, por ejemplo *Todo Tree*.

### Separadores

Se pueden usar para dividir un archivo en secciones:

```javascript
// ----- Datos -----
let nombre = "Sol";
let edad = 30;

// ----- Salida -----
console.log(`${nombre}, ${edad}`);
```

### Regiones

JavaScript no tiene `#region`, pero Visual Studio Code permite **plegar** un bloque con comentarios especiales:

```javascript
//#region Datos
let ciudad = "Madrid";
let espera = 30;
//#endregion
```

Son solo comentarios: el intérprete los ignora, y solo los entiende el editor.

## Comodidad: atajos de teclado

Los editores comentan y descomentan varias líneas de una vez: se seleccionan las líneas y se pulsa el atajo.

| Acción | Visual Studio Code |
|---|---|
| Comentar / descomentar línea (`//`) | `Ctrl + /` |
| Comentar / descomentar bloque (`/* */`) | `Shift + Alt + A` |

- `Ctrl + /` **alterna**: si la línea ya está comentada, la descomenta.
- Sin selección, afecta a la línea donde está el cursor.
- Con varias líneas seleccionadas, afecta a todas.

## Casos curiosos

### Un `//` dentro de un texto no es un comentario

Si `//` o `/*` están dentro de comillas, son parte del texto:

```javascript
console.log("Visita https://ejemplo.com");   // se muestra la URL completa
console.log("// esto no es un comentario");  // se muestra tal cual
```

### La primera línea `#!`

Un archivo de Node.js puede empezar con `#!/usr/bin/env node`. No es un comentario normal, pero Node lo trata como tal. Sirve para ejecutar el archivo directamente en Linux y macOS, y no se usa en estas lecciones.

## Qué comentar y qué no

Un buen comentario explica **por qué**, no **qué**: el código ya dice lo que hace.

```javascript
// Mal: repite lo que ya dice el código
edad = edad + 1;   // suma 1 a edad

// Bien: explica el motivo
edad = edad + 1;   // el usuario acaba de cumplir años
```

| Conviene | Evitar |
|---|---|
| Explicar el motivo de una decisión | Repetir lo que hace cada línea |
| Avisar de casos raros | Dejar mucho código comentado sin uso |
| Documentar funciones con JSDoc | Comentarios que ya no coinciden con el código |
| Dejar `TODO` con una descripción clara | Comentarios vagos como `// arreglar esto` |

Si el código necesita muchos comentarios para entenderse, suele ser mejor dar nombres más claros a las variables.

## Diferencias con C#

| C# | JavaScript |
|---|---|
| `/// <summary>` | `/** … */` con etiquetas `@param`, `@returns` |
| Tipos en el código | Tipos solo en JSDoc, como documentación |
| `#region` / `#endregion` | `//#region` / `//#endregion` (solo el editor) |
| `#if false` | `if (false) { }` |
| `Console.ReadKey()` | No existe |

## Resumen

| Quiero… | Uso |
|---|---|
| Un comentario corto | `// texto` |
| Un comentario largo | `/* texto */` |
| Documentar una función | `/** … */` con `@param` y `@returns` |
| Desactivar una línea | `// código` |
| Desactivar un bloque | `/* código */` o `if (false) { }` |
| Dejar un aviso | `// TODO: …` |
| Plegar un bloque | `//#region` / `//#endregion` en Visual Studio Code |
| Comentar rápido | `Ctrl + /` |

## Ejemplo del ejercicio

Reúne todos los casos de esta lección en un solo programa, en el mismo orden que este documento. Consulta `index.js` para verlo completo.

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
cd javascript/02-fundamentals
node 07-comments/index.js
```
