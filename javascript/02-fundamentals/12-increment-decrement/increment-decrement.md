# Incremento y decremento en JavaScript

## Qué son

Operadores que **aumentan o reducen el valor de una variable** sin escribir la suma o la resta completa.

| Operador | Efecto | Equivale a |
|---|---|---|
| `numero++` | Suma 1 | `numero = numero + 1` |
| `numero--` | Resta 1 | `numero = numero - 1` |

```javascript
let numero = 5;
console.log("Valor original: " + numero);       // 5

numero++;
console.log("Valor incrementado: " + numero);   // 6

numero--;
console.log("Valor decrementado: " + numero);   // 5
```

Cada `++` o `--` cambia el valor de uno en uno, tantas veces como se escriba. La variable debe declararse con `let`, no con `const`, porque su valor cambia.

## Postfijo y prefijo

El operador puede ir **después** (`numero++`) o **antes** (`++numero`) de la variable.

| | Escribe | Qué hace |
|---|---|---|
| Postincremento | `numero++` | Primero **usa** el valor y después lo incrementa |
| Preincremento | `++numero` | Primero **incrementa** y después usa el valor |

Escritos solos en una línea dan el mismo resultado. La diferencia aparece cuando el valor se **usa** en la misma instrucción:

```javascript
let valor = 7;
const valor2 = valor++;   // valor2 = 7, después valor = 8

let valor3 = 7;
const valor4 = ++valor3;  // valor3 = 8, después valor4 = 8
```

El decremento funciona igual con `--`.

## Operadores compuestos de asignación

Cuando hay que cambiar el valor en **más de una unidad**, se escribe el operador antes del `=`.

| Operador | Equivale a | Con `numero = 5` |
|---|---|---|
| `numero += 3` | `numero = numero + 3` | `8` |
| `numero -= 3` | `numero = numero - 3` | `2` |
| `numero *= 3` | `numero = numero * 3` | `15` |
| `numero /= 3` | `numero = numero / 3` | `1.6666666666666667` |
| `numero %= 3` | `numero = numero % 3` | `2` |

```javascript
let numero = 5;
numero += 3;   // 8
numero *= 2;   // 16
```

- Funciona con cualquier operador aritmético: `+=`, `-=`, `*=`, `/=` y `%=`.
- El operador va **antes** del `=`. Escrito al revés, `numero =+ 3`, no suma: asigna `+3`.
- A diferencia de C# y Java, `/=` **conserva los decimales**: `5 /= 3` da `1.6666666666666667`, porque no hay división entera.
- `+=` también concatena textos: `let texto = "Hola"; texto += " Sol";`.
- `++` y `--` son el caso especial de sumar o restar 1.

## Ejemplo

```javascript
let numero = 5;
console.log("Valor original: " + numero);

numero++;
console.log("Valor incrementado: " + numero);

numero += 3;
console.log("Después de += 3: " + numero);

let valor = 7;
const valor2 = valor++;
console.log(`valor2 = ${valor2}, valor = ${valor}`);
```

## Comandos

```bash
cd javascript/02-fundamentals
node 12-increment-decrement/index.js
```

Fuente: [curso de C# de INFORMATICONFIG](https://www.youtube.com/watch?v=o9C6euoH-zU&list=PL2Z95CSZ1N4F7mym8Ca16RoTDVAlIOPlT&index=12), vídeo 12.
