# Operadores de comparación en JavaScript

## Qué son

Operadores que **comparan dos valores** y dan como resultado un `boolean`: `true` o `false`. Sirven para tomar decisiones en el flujo del programa.

| Operador | Significa | Ejemplo | Resultado |
|---|---|---|---|
| `>` | Mayor que | `10 > 20` | `false` |
| `<` | Menor que | `10 < 20` | `true` |
| `===` | Igual que | `10 === 10` | `true` |
| `>=` | Mayor o igual que | `10 >= 10` | `true` |
| `<=` | Menor o igual que | `10 <= 5` | `false` |
| `!==` | Distinto de | `10 !== 20` | `true` |

```javascript
let a = 10;
let b = 20;

console.log(a > b);     // false
console.log(a !== b);   // true
```

`console.log` muestra `true` y `false` en minúsculas.

## `=` frente a `===`

- `=` **asigna**: `a = 10` guarda un 10 en `a`.
- `===` **compara**: `a === 10` pregunta si `a` vale 10.

```javascript
let a = 5;
const igual = (a === 10);   // false: solo pregunta
```

## `==` frente a `===`

JavaScript tiene dos operadores de igualdad:

| | Compara | `10` y `"10"` |
|---|---|---|
| `==` | El valor, **convirtiendo los tipos** antes | `true` |
| `===` | El valor **y el tipo** | `false` |

```javascript
console.log(10 == "10");    // true: el texto se convierte a número
console.log(10 === "10");   // false: número y texto son tipos distintos
```

Se usa **siempre `===` y `!==`**: `==` produce resultados sorprendentes, como `0 == ""` o `null == undefined`, que son `true`. En C# y Java no existe este problema, porque los tipos distintos no se comparan.

## El operador ternario `? :`

Convierte el resultado de una comparación en un valor. Es una pregunta con dos respuestas.

```text
(condición) ? valorSiTrue : valorSiFalse
```

```javascript
const a = 10, b = 20;
const resultado = (a > b) ? "Sí" : "No";

console.log("¿a es mayor que b? " + resultado);   // No
```

- Si `a > b` es `true`, `resultado` recibe `"Sí"`.
- Si es `false`, recibe `"No"`.
- Es una forma corta de decidir. En la lección de condicionales se verá `if/else`, que sirve para lo mismo y para decisiones más largas.

## Mayor o igual y menor o igual

`>=` es verdadero si se cumple **cualquiera** de las dos condiciones: que sea mayor o que sea igual.

```javascript
const b = 10;

console.log(10 >= b);   // true: es igual
console.log(11 >= b);   // true: es mayor
console.log(1 >= b);    // false: no es ni mayor ni igual
```

El `=` va siempre **después** de `>` o `<`: `=>` y `=<` no son operadores de comparación (`=>` es otra cosa, una función flecha).

## Comparar textos

En JavaScript `===` compara el **contenido** de los `string`, y distingue mayúsculas de minúsculas.

```javascript
const nombre = "Sol";

console.log(nombre === "Sol");   // true
console.log(nombre === "sol");   // false
```

## Ejemplo

```javascript
const a = 10;
const b = 20;

console.log("¿a es mayor que b? " + ((a > b) ? "Sí" : "No"));
console.log("¿a es menor que b? " + ((a < b) ? "Sí" : "No"));
console.log("¿a es igual a b? " + ((a === b) ? "Sí" : "No"));
console.log("¿a es distinto de b? " + ((a !== b) ? "Sí" : "No"));
```

## Comandos

```bash
cd javascript/02-fundamentals
node 13-comparison-operators/index.js
```

Fuente: [curso de C# de INFORMATICONFIG](https://www.youtube.com/watch?v=XKRWB9LluAA&list=PL2Z95CSZ1N4F7mym8Ca16RoTDVAlIOPlT&index=13), vídeo 13.
