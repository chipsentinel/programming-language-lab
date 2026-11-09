# Casting en JavaScript

## Qué es el casting

**Convertir un valor de un tipo de dato a otro**: de número a texto, de texto a número, de decimal a entero.

En JavaScript funciona distinto que en C# y Java:

- **Solo hay un tipo `number`** para enteros y decimales: no existe la conversión `int` a `double`, ni el cast `(int)`.
- **No hay `char`**: una letra es un `string` de longitud 1.
- **El tipo cambia solo** al mezclar valores (coerción implícita).
- **La conversión explícita** se hace con funciones: `Number`, `parseInt`, `parseFloat` y `String`.

## Coerción implícita

JavaScript convierte los tipos por su cuenta cuando se mezclan en una operación.

```javascript
console.log("5" + 3);     // "53": el número pasa a texto y se concatena
console.log("5" - 3);     // 2: el texto pasa a número
console.log("5" * "2");   // 10
```

`+` con un texto **concatena**; `-`, `*` y `/` convierten el texto a número. Por eso conviene convertir de forma explícita en lugar de fiarse de la coerción.

## Quitar los decimales

No hay `(int)`. Para descartar los decimales se usa `Math.trunc`.

```javascript
const precio = 500.23;
const descuento = Math.trunc(precio);   // 500
```

| Función | `500.99` | `-7.9` |
|---|---|---|
| `Math.trunc` | `500` | `-7` |
| `Math.floor` | `500` | `-8` |
| `Math.round` | `501` | `-8` |

`Math.trunc` equivale al `(int)` de C# y Java: descarta los decimales, no redondea, y trunca hacia cero.

## De carácter a número y viceversa

Como no existe `char`, el código de una letra se pide con un método.

```javascript
const letra = "A";
const codigoAscii = letra.charCodeAt(0);              // 65
const letraDeVuelta = String.fromCharCode(65);        // "A"
```

## De texto a número

| Función | Sirve para | Ejemplo |
|---|---|---|
| `Number(texto)` | Texto a número, exige que todo el texto sea válido | `Number("123456")` |
| `parseInt(texto)` | Texto a entero, lee hasta donde puede | `parseInt("42px")` es `42` |
| `parseFloat(texto)` | Texto a decimal, lee hasta donde puede | `parseFloat("150.60")` |

```javascript
Number("123456");        // 123456
parseFloat("150.60");    // 150.6
parseFloat("150.60abc"); // 150.6: lee hasta la 'a'
Number("150.60abc");     // NaN: el texto completo no es un número
```

### `NaN` en lugar de error

Si el texto no es un número, JavaScript **no lanza ningún error**: devuelve `NaN` (*Not a Number*). Hay que comprobarlo.

```javascript
const resultado = Number("hola");   // NaN

if (Number.isNaN(resultado)) {
    console.log("no es un número");
}
```

Es el equivalente a `TryParse` de C# y al `try/catch` de Java.

`NaN` no es igual ni a sí mismo: `NaN === NaN` es `false`. Por eso se usa `Number.isNaN()`.

## El separador decimal

Como en Java, **siempre se usa el punto**, sin depender del idioma del equipo. Con coma solo se lee la parte entera:

```javascript
parseFloat("150,60");   // 150
```

## De número a texto

```javascript
const edad = 30;
const texto = String(edad);     // "30"
const texto2 = edad.toString(); // "30"

const saludo = "Edad: " + edad; // la concatenación lo hace sola
```

## Resumen

| Caso | Qué se usa |
|---|---|
| Quitar decimales | `Math.trunc(valor)` |
| Letra a código | `letra.charCodeAt(0)` |
| Código a letra | `String.fromCharCode(codigo)` |
| Texto a número | `Number()`, `parseInt()`, `parseFloat()` |
| Número a texto | `String()` o `.toString()` |
| Comprobar si falló | `Number.isNaN()` |

## Ejemplo

```javascript
const precio = 500.23;
const descuento = Math.trunc(precio);              // 500

const codigoAscii = "A".charCodeAt(0);             // 65

const palabra = "123456";
const numero = Number(palabra);                    // 123456

console.log(`${descuento} ${codigoAscii} ${numero}`);
```

## Comandos

```bash
cd javascript/02-fundamentals
node 09-type-casting/index.js
```

Fuente: [curso de C# de INFORMATICONFIG](https://www.youtube.com/watch?v=ii2nqtVd0Aw&list=PL2Z95CSZ1N4F7mym8Ca16RoTDVAlIOPlT&index=9), vídeo 09.
