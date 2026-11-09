# Operadores lógicos en JavaScript

## Qué son

Operadores que **combinan o modifican expresiones booleanas** (verdadero o falso). Se usan para evaluar varias condiciones a la vez.

| Operador | Nombre | Significa | Ejemplo |
|---|---|---|---|
| `&&` | AND (y) | Se cumplen **las dos** condiciones | `a < b && a < c` |
| `\|\|` | OR (o) | Se cumple **al menos una** | `a < b \|\| a < c` |
| `!` | NOT (no) | **Invierte** el valor | `!(a < b)` |

La barra vertical `|` suele estar en la tecla a la izquierda del `1`; `||` son dos.

## AND (`&&`)

Da `true` solo si **ambas** condiciones son `true`.

| `A` | `B` | `A && B` |
|---|---|---|
| true | true | **true** |
| true | false | false |
| false | true | false |
| false | false | false |

```javascript
let numero1 = 40;
const numero2 = 90;
let numero3 = 5;

let resultado = numero1 < numero2 && numero1 < numero3;   // true && false
console.log(resultado);   // false

numero3 = 50;
resultado = numero1 < numero2 && numero1 < numero3;       // true && true
console.log(resultado);   // true
```

## OR (`||`)

Da `true` si **al menos una** condición es `true`. Solo es `false` si ninguna se cumple.

| `A` | `B` | `A \|\| B` |
|---|---|---|
| true | true | **true** |
| true | false | **true** |
| false | true | **true** |
| false | false | false |

```javascript
let numero1 = 40;
const numero2 = 90, numero3 = 50;

console.log(numero1 < numero2 || numero1 < numero3);   // true

numero1 = 100;
console.log(numero1 < numero2 || numero1 < numero3);   // false
```

## NOT (`!`)

**Invierte** el valor: `true` pasa a `false` y `false` a `true`. Se escribe delante de la expresión, y si la expresión tiene varias partes se pone entre paréntesis.

```javascript
const resultado = numero1 < numero2 && numero1 < numero3;   // false
console.log(resultado);    // false
console.log(!resultado);   // true

console.log(!(numero1 < numero2 && numero1 < numero3));   // true
```

Sirve para ahorrar código al validar datos. Se verá con los condicionales.

## Evaluación en cortocircuito

`&&` y `||` **dejan de evaluar en cuanto conocen el resultado**.

- En `A && B`, si `A` es `false`, ya no se evalúa `B`.
- En `A || B`, si `A` es `true`, ya no se evalúa `B`.

```javascript
const divisor = 0;
const seguro = divisor !== 0 && 10 / divisor > 1;   // 10 / 0 nunca se calcula
```

En JavaScript dividir entre cero no da error (da `Infinity`), pero el cortocircuito se usa igual para evitar acceder a propiedades de valores que no existen.

## `&&` y `||` no siempre devuelven `true` o `false`

A diferencia de C# y Java, en JavaScript **devuelven uno de los dos operandos**. Esto permite usarlos para dar valores por defecto.

```javascript
console.log("Sol" || "invitado");   // Sol: el primero es verdadero, se queda con él
console.log("" || "invitado");      // invitado: "" cuenta como falso
console.log(0 && "hola");           // 0: el primero es falso, se queda con él
```

Son **falsos** estos valores: `false`, `0`, `""`, `null`, `undefined` y `NaN`. El resto son verdaderos.

Para dar un valor por defecto solo cuando falta (`null` o `undefined`), existe `??`, que no se confunde con `0` ni `""`:

```javascript
console.log(0 || 10);      // 10: 0 cuenta como falso
console.log(0 ?? 10);      // 0
console.log(null ?? 10);   // 10
```

## Orden de evaluación

Primero `!`, después `&&` y al final `||`. Los paréntesis cambian el orden.

```javascript
console.log(true || false && false);     // true: primero false && false
console.log((true || false) && false);   // false
```

## Ejemplo

```javascript
const numero1 = 40, numero2 = 90, numero3 = 50;

console.log("AND: " + (numero1 < numero2 && numero1 < numero3));
console.log("OR: " + (numero1 > numero2 || numero1 < numero3));
console.log("NOT: " + !(numero1 < numero2));
```

## Comandos

```bash
cd javascript/02-fundamentals
node 14-logical-operators/index.js
```

Fuente: [curso de C# de INFORMATICONFIG](https://www.youtube.com/watch?v=QBCcwzUFjpQ&list=PL2Z95CSZ1N4F7mym8Ca16RoTDVAlIOPlT&index=14), vídeo 14.
