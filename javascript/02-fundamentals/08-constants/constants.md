# Constantes en JavaScript

## Qué es una constante

Un valor que **no se puede reasignar** después de declararlo. Se declara con `const` en lugar de `let`.

```javascript
const PI = 3.14159;
```

```text
const    PI    =    3.14159;
palabra  nombre =   valor
```

```javascript
let edad = 30;
edad = 31;          // válido

const DIAS = 7;
DIAS = 8;           // TypeError: Assignment to constant variable
```

## Para qué sirven

- **Evitar números mágicos**: el nombre explica qué es el valor.
- **Cambiarlo en un solo sitio**: si el IVA cambia, se modifica la constante y se actualiza en todo el programa.
- **Protegerlo**: el programa falla si alguien intenta reasignarlo.

```javascript
const total = precio * 1.21;          // ¿qué es 1.21?

const IVA = 0.21;
const total = precio * (1 + IVA);     // el nombre lo explica
```

## Reglas

- **Se inicializa al declararla**: `const meses;` es un `SyntaxError`.
- **Puede tomar cualquier valor**, incluso el resultado de una función o de `Date.now()`. El valor se calcula al ejecutar, no hace falta conocerlo antes.
- **Tiene ámbito de bloque**, igual que `let`: solo existe dentro de las `{ }` donde se declara.
- **No se puede redeclarar** en el mismo bloque.

```javascript
const DIAS = 7;
const SEMANAS = DIAS * 4;             // válido
const AHORA = Date.now();             // válido: se calcula al ejecutar
const N = Number("5");                // válido

const MESES;                          // error: falta el valor
```

## `const` no hace inmutable el contenido

`const` impide **reasignar** la variable, pero un objeto o un array guardado en ella sí se puede modificar.

```javascript
const DIAS = ["lunes", "martes"];

DIAS.push("miércoles");   // válido: se modifica el contenido
DIAS = [];                // error: se intenta reasignar
```

Para impedir también los cambios internos se usa `Object.freeze(DIAS)`.

## Convención de nombre

- Un valor fijo y conocido de antemano (`PI`, `IVA`, `DIAS`) usa `MAYUSCULAS_CON_GUION_BAJO`.
- El resto de `const` (`radio`, `total`) usa `camelCase`, como cualquier variable.

Por eso conviene usar `const` por defecto y `let` solo si el valor va a cambiar.

## Constantes que ya trae JavaScript

```javascript
console.log(Math.PI);                  // 3.141592653589793
console.log(Number.MAX_SAFE_INTEGER);  // 9007199254740991
console.log(Number.MIN_SAFE_INTEGER);  // -9007199254740991
```

## Repaso: `let` admite cualquier tipo

A diferencia de C# y Java, en JavaScript una variable no fija su tipo: puede cambiar de valor y de tipo.

```javascript
let numero = 10.20;

numero = 4000;        // válido
numero = "hola";      // válido: JavaScript es de tipado dinámico
numero = 's';         // válido: guarda el texto "s"
```

- `console.log(numero)` muestra `s`: no hay conversión a código Unicode como en C# o Java.
- Una variable no se puede declarar dos veces en el mismo bloque con `let` o `const`.

## Ejemplo

```javascript
const PI = 3.14159;
const IVA = 0.21;

const radio = 5;
const area = PI * radio * radio;

const precio = 100;
const total = precio * (1 + IVA);

console.log(`Área del círculo de radio ${radio}: ${area}`);
console.log(`Precio con IVA: ${total}`);
```

## Comandos

```bash
cd javascript/02-fundamentals
node 08-constants/index.js
```

Fuente: [curso de C# de INFORMATICONFIG](https://www.youtube.com/watch?v=O2cAgrpLLcQ&list=PL2Z95CSZ1N4F7mym8Ca16RoTDVAlIOPlT&index=8), vídeo 08.
