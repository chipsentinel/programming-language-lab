# Tipos de datos en JavaScript

Equivalente de la lección 04 del curso de C#.

## Qué es un tipo de dato

Un tipo de dato indica qué clase de valor guarda una variable. JavaScript es de **tipado dinámico**: no se declara el tipo de la variable; el tipo lo tiene el **valor** y puede cambiar durante la ejecución.

```javascript
let dato = 25;       // number
dato = "veinticinco"; // ahora es string: es válido
```

En C# y Java esto daría error de compilación.

## Los tipos

JavaScript tiene **7 tipos primitivos** y un tipo por referencia (`object`).

| Tipo | Qué guarda | Ejemplo |
|---|---|---|
| `number` | Números (enteros y decimales) | `25`, `19.99` |
| `bigint` | Enteros de tamaño arbitrario | `9007199254740993n` |
| `string` | Texto | `"Hola"` |
| `boolean` | `true` o `false` | `true` |
| `undefined` | Variable declarada sin valor | `undefined` |
| `null` | Ausencia intencionada de valor | `null` |
| `symbol` | Identificador único | `Symbol("id")` |
| `object` | Objetos, arrays, funciones | `{ nombre: "Ana" }`, `[1, 2]` |

## `number`: un único tipo numérico

No hay `int`, `long`, `float` ni `double`: **todos los números son `number`**, un decimal de 64 bits (como `double`).

```javascript
let edad = 25;
let precio = 19.99;
```

Consecuencias:

- Los enteros son exactos hasta `Number.MAX_SAFE_INTEGER` (9.007.199.254.740.991). A partir de ahí pierden precisión.
- Los decimales tienen errores de redondeo: `0.1 + 0.2` da `0.30000000000000004`.
- Existen valores especiales: `NaN` (no es un número) e `Infinity`.

Para dinero se trabaja con enteros (por ejemplo, céntimos), ya que no hay equivalente a `decimal`.

## `bigint`

Para enteros más grandes que el límite seguro. Se escribe con el sufijo `n` y no se mezcla directamente con `number`.

```javascript
let enorme = 9007199254740993n;
```

## `string`

Texto entre comillas simples, dobles o acentos graves (*template literals*). No existe el tipo `char`: un carácter es un `string` de longitud 1.

```javascript
let a = 'Hola';
let b = "Hola";
let c = `Hola ${a}`;   // admite expresiones con ${}
```

## `boolean`

```javascript
let esMayorDeEdad = true;
```

## `undefined` y `null`

- `undefined`: la variable existe, pero no se le ha dado valor.
- `null`: se asigna a propósito para indicar «sin valor».

```javascript
let x;           // undefined
let y = null;    // null
```

## Declarar variables

| Palabra | Uso |
|---|---|
| `const` | Valor que no se reasigna (primera opción) |
| `let` | Valor que cambia |
| `var` | Forma antigua; se evita |

## Cómo comprobar el tipo

```javascript
console.log(typeof 25);          // "number"
console.log(typeof "Hola");      // "string"
console.log(typeof true);        // "boolean"
console.log(typeof undefined);   // "undefined"
console.log(typeof null);        // "object"  (error histórico del lenguaje)
console.log(Number.MAX_SAFE_INTEGER);
```

## Comparación con C#

| C# | JavaScript |
|---|---|
| `int`, `long`, `float`, `double` | `number` |
| `decimal` | No existe |
| `bool` | `boolean` |
| `string` | `string` |
| `char` | No existe (`string` de un carácter) |
| `null` | `null` y `undefined` |
| Tipo fijo al declarar | El tipo lo tiene el valor |

## Comandos

```bash
cd javascript/02-fundamentals
touch 04-data-types/index.js
node 04-data-types/index.js
```
