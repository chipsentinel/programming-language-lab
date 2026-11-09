# Los `string` en JavaScript

## Qué es

`string` es el tipo de dato para **texto**: una secuencia de caracteres. Aunque es un tipo primitivo, JavaScript le da acceso a un conjunto de **propiedades y funciones** para trabajar con él: ver su longitud, pasarlo a mayúsculas, buscar dentro, cortarlo...

```javascript
const texto = "Hola a todos";
```

Para usar una función se escribe un punto después de la variable: `texto.toUpperCase()`.

## Texto en varias líneas

Un texto largo se sale de la pantalla y es incómodo de leer. Hay dos formas de repartirlo en líneas.

**1. Concatenar con `+`**, y el salto de línea con `\n`:

```javascript
const lineas = "Esta es la primera línea\n" +
               "Esta es la segunda línea\n" +
               "Esta es la tercera línea";
```

- El `+` une las partes **en el código**; es el `\n` el que hace el salto **al mostrarlo**.

**2. Plantilla con comillas invertidas** (`` ` ``): se escribe tal cual, con saltos de línea reales, sin `+` ni `\n`:

```javascript
const lineas = `Esta es la primera línea
Esta es la segunda línea
Esta es la tercera línea`;
```

Todo lo que hay entre las comillas invertidas se guarda como está, **incluidos los espacios** del margen izquierdo. Estas mismas plantillas son las que permiten `${variable}`.

## Caracteres especiales

Algunos caracteres no se pueden escribir directamente dentro de un texto. Se usan **secuencias de escape** con la barra invertida `\`:

| Secuencia | Significa |
|---|---|
| `\n` | Salto de línea |
| `\t` | Tabulador |
| `\"` | Comillas dobles |
| `\\` | Una barra invertida |

```javascript
console.log("Comillas: \"mundo\"");     // Comillas: "mundo"
console.log("Ruta: C:\\Datos\\foto");   // Ruta: C:\Datos\foto
```

También se pueden usar comillas simples (`'...'`) para evitar el escape: `'Comillas: "mundo"'`.

## Funciones principales

Con `texto = "Hola a todos"`:

| Función | Qué hace | Resultado |
|---|---|---|
| `texto.length` | Número de caracteres | `12` |
| `texto.toUpperCase()` | Pasa a mayúsculas | `HOLA A TODOS` |
| `texto.toLowerCase()` | Pasa a minúsculas | `hola a todos` |
| `texto.includes("todos")` | ¿Contiene ese texto? | `true` |
| `texto.startsWith("Hola")` | ¿Empieza por…? | `true` |
| `texto.endsWith("todos")` | ¿Termina en…? | `true` |
| `texto.indexOf("do")` | Posición donde aparece | `9` |
| `texto.substring(2, 6)` | Extrae una parte | `la a` |
| `texto.replace("a todos", "mundo")` | Sustituye texto | `Hola mundo` |
| `"  hola  ".trim()` | Quita espacios de los extremos | `hola` |

Como en C#, `length` **no lleva paréntesis**: es una propiedad. Y lo que en C# es `Contains`, aquí se llama **`includes`**.

### Longitud

Cuenta **todos** los caracteres: letras, signos de puntuación y **espacios**. Un texto de cuatro espacios tiene longitud 4.

### Mayúsculas y minúsculas

```javascript
console.log(texto.toUpperCase());   // HOLA A TODOS
console.log(texto.toLowerCase());   // hola a todos
```

### `includes`, `startsWith`, `endsWith`

Devuelven `true` o `false` y **distinguen mayúsculas de minúsculas**:

```javascript
console.log(texto.startsWith("Hola"));   // true
console.log(texto.startsWith("hola"));   // false: la h es minúscula
console.log(texto.includes("mundo"));    // false
```

## Índices: la posición empieza en 0

Cada carácter tiene una posición, y **la primera es la 0**, no la 1:

```text
H  o  l  a     a     t  o  d  o  s
0  1  2  3  4  5  6  7  8  9  10 11
```

```javascript
console.log(texto[0]);               // H
console.log(texto.indexOf("do"));    // 9
console.log(texto.indexOf("tr"));    // -1
```

- `indexOf` devuelve la posición de la **primera coincidencia**.
- Si el texto **no existe** devuelve **`-1`**. Un `-1` siempre significa "no encontrado".

## `substring` y `slice`

Extraen una parte del texto: `substring(inicio, fin)`.

```javascript
texto.substring(2, 6);   // "la a": desde la posición 2 hasta la 6, sin incluir la 6
texto.substring(1, 5);   // "ola "
texto.substring(5);      // "a todos": desde la 5 hasta el final
```

**Cuidado:** en C# `Substring(2, 4)` significa "desde la 2, **cuatro** caracteres". En JavaScript (y en Java) el segundo número es la **posición final**, que no se incluye. La cantidad de caracteres es `fin - inicio`.

`slice` hace lo mismo, pero además acepta **números negativos**, que cuentan desde el final:

```javascript
texto.slice(-5);   // "todos": los últimos cinco caracteres
```

## `replace`

Sustituye **solo la primera** aparición. Para cambiar todas existe `replaceAll`:

```javascript
console.log(texto.replace("a todos", "mundo"));   // Hola mundo

"a-b-c".replace("-", "+");      // "a+b-c": solo el primero
"a-b-c".replaceAll("-", "+");   // "a+b+c"
```

En C# y Java, `Replace` y `replace` cambian todas.

## `trim`

Quita los espacios del **principio y del final** (no los de en medio):

```javascript
const conEspacios = "   Hola a todos   ";

console.log(conEspacios.length);          // 18
console.log(conEspacios.trim().length);   // 12
```

## Los `string` no se modifican

Las funciones **no cambian el texto original**: devuelven uno nuevo. Si no se guarda, el resultado se pierde. Se dice que los `string` son **inmutables**.

```javascript
texto.toUpperCase();                    // no hace nada visible
console.log(texto);                     // Hola a todos: sigue igual

const mayusculas = texto.toUpperCase(); // así se conserva
```

## Encadenar funciones

Se pueden unir varias, y se aplican de **izquierda a derecha**:

```javascript
console.log(conEspacios.trim().toUpperCase());   // HOLA A TODOS
console.log(conEspacios.trim().length);          // 12
```

## Ejemplo

```javascript
const texto = "Hola a todos";

console.log(`Texto: ${texto}`);
console.log(`Caracteres: ${texto.length}`);
console.log(`Mayúsculas: ${texto.toUpperCase()}`);
console.log(`Contiene "todos": ${texto.includes("todos")}`);
console.log(`Reemplazado: ${texto.replace("a todos", "mundo")}`);
```

## Comandos

```bash
cd javascript/04-data-structures
node 23-string-class/index.js
```

Fuente: [curso de C# de INFORMATICONFIG](https://www.youtube.com/watch?v=v1f3dJsNreU&list=PL2Z95CSZ1N4F7mym8Ca16RoTDVAlIOPlT&index=23), vídeo 23.
