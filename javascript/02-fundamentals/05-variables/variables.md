# Variables en JavaScript

Equivalente de la lección 05 del curso de C#.

## Qué es una variable

Una variable es un **espacio con nombre** donde el programa guarda un valor. En JavaScript el tipo no se declara: lo tiene el **valor** y puede cambiar (tipado dinámico).

```javascript
let edad = 25;
```

```text
let      edad     =    25;
palabra  nombre   =    valor
```

## `let`, `const` y `var`

| Palabra | Reasignable | Ámbito | Uso |
|---|---|---|---|
| `let` | Sí | Bloque `{ }` | Variables normales |
| `const` | No | Bloque `{ }` | Valores que no cambian (lección 08) |
| `var` | Sí | Función | Antiguo; se evita |

Se usa `let` por defecto y `const` cuando el valor no se reasigna. `var` solo se ve en código antiguo.

## Declarar, asignar e inicializar

```javascript
let edad;           // declarar: vale undefined
edad = 25;          // asignar
let anio = 2025;    // inicializar: declarar y asignar a la vez
```

A diferencia de C# y Java, usar una variable sin valor **no da error**: vale `undefined`.

```javascript
let edad;
console.log(edad);   // undefined
```

## Cambiar el valor

```javascript
let edad = 25;
edad = 26;            // válido
edad = "veintiséis";  // también válido: ahora es string
```

En C# y Java la última línea sería un error de compilación.

## Declarar varias a la vez

```javascript
let x = 1, y = 2, z = 3;
```

## Reglas para los nombres

- Pueden contener letras, números, guion bajo `_` y el símbolo `$`.
- **No pueden empezar por un número**: `1edad` no es válido.
- No pueden contener espacios ni otros símbolos (`-`, `@`…).
- **Distinguen mayúsculas de minúsculas**: `edad` y `Edad` son variables distintas.
- No pueden ser palabras reservadas (`let`, `class`, `function`…).

| Válido | No válido |
|---|---|
| `edad` | `1edad` |
| `_total` | `mi edad` |
| `nombreCompleto` | `nombre-completo` |
| `precio2` | `let` |

## Convenciones

Variables en `camelCase`, con nombres descriptivos: `nombreCompleto`, `descuento2` (sin guion bajo). Es la misma convención que en C#.

## Mostrar variables

Las comillas invertidas `` ` `` permiten escribir variables dentro del texto con `${ }` (plantillas de texto). Es el equivalente a la interpolación `$"{nombre}"` de C#.

```javascript
let nombre = "Sol";
let edad = 25;

console.log(`Me llamo ${nombre} y tengo ${edad} años.`);
```

## Ejemplo del ejercicio

Una variable de cada tipo habitual, inicializada y mostrada por consola:

```javascript
let nombre = "informaticonfig";
let edad = 30;
let activo = true;
let fecha = new Date();
let precio = 30.5;
let descuento = 4.6;
let descuento2 = 10.50;

console.log(`nombre = ${nombre}`);
console.log(`edad = ${edad}`);
console.log(`activo = ${activo}`);
console.log(`fecha = ${fecha}`);
console.log(`precio = ${precio}`);
console.log(`descuento = ${descuento}`);
console.log(`descuento2 = ${descuento2}`);

// Recuerda para ver el tipo de dato usa typeof
console.log(`\nTipo de nombre: ${typeof nombre}`);
```

| Variable | Tipo | Qué guarda |
|---|---|---|
| `nombre` | `string` | Texto |
| `edad` | `number` | Número |
| `activo` | `boolean` | `true` o `false` |
| `fecha` | `object` (`Date`) | Fecha y hora; `new Date()` es el momento actual |
| `precio` | `number` | Número |
| `descuento` | `number` | Número |
| `descuento2` | `number` | Número |

- `edad`, `precio`, `descuento` y `descuento2` son del mismo tipo: JavaScript solo tiene `number` para enteros y decimales. No existen `int`, `float`, `decimal` ni `double`.
- `Date` es un objeto, así que `typeof fecha` devuelve `"object"`.
- `typeof` equivale al `GetType()` de C#, pero devuelve el tipo de JavaScript (`"string"`), no el nombre completo de una clase.
- No hay equivalente a `Console.ReadKey()`: el programa termina y la terminal sigue abierta.

## Ámbito

Con `let` y `const`, una variable solo existe dentro del bloque `{ }` donde se declara.

```javascript
if (true) {
    let contador = 1;
}
console.log(contador);   // error: contador is not defined
```

## Diferencias con C#

| C# | JavaScript |
|---|---|
| `string nombre = "Sol";` | `let nombre = "Sol";` |
| Tipo fijo al declarar | El tipo lo tiene el valor |
| `int`, `double`, `decimal` | Solo `number` |
| `DateTime` | `Date` |
| Variable sin asignar: error | Vale `undefined` |
| `GetType()` | `typeof` |
| `Console.ReadKey()` | No existe |

## Comandos

```bash
cd javascript/02-fundamentals
node 05-variables/index.js
```
