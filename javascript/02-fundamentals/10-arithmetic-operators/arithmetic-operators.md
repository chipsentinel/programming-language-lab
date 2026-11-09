# Operadores aritméticos en JavaScript

## Qué son

Símbolos que realizan **operaciones matemáticas** con variables, constantes o valores numéricos.

| Operador | Operación | Ejemplo | Resultado |
|---|---|---|---|
| `+` | Suma | `90 + 70` | `160` |
| `-` | Resta | `90 - 70` | `20` |
| `*` | Multiplicación | `10 * 70` | `700` |
| `/` | División | `90 / 10` | `9` |
| `%` | Residuo (módulo) | `90 % 20` | `10` |
| `**` | Potencia | `2 ** 3` | `8` |

```javascript
const valor1 = 90, valor2 = 70, valor3 = 10;

const total = valor1 + valor2;        // 160
const diferencia = valor1 - valor2;   // 20
const producto = valor3 * valor2;     // 700
const resultado = valor1 / valor3;    // 9
const residuo = valor2 % valor3;      // 0
```

## Declarar varias variables en una línea

Varias variables se declaran juntas con una sola palabra clave, separadas por comas. Cada una lleva su propio valor.

```javascript
const valor1 = 90, valor2 = 70, valor3 = 10;
```

Equivale a escribir una línea por variable y ahorra espacio.

## `+` suma o concatena

Con **dos números** suma. Si hay un **texto** a un lado, concatena.

```javascript
console.log(valor1 + valor2);                // 160
console.log("Suma: " + valor1 + valor2);     // Suma: 9070
console.log("Suma: " + (valor1 + valor2));   // Suma: 160
```

En el segundo caso se evalúa de izquierda a derecha: `"Suma: " + 90` da un texto, y a ese texto se le concatena `70`. Los **paréntesis** fuerzan primero la suma.

## División: no hay división entera

A diferencia de C# y Java, aquí no existe la división entera: todos los números son `number` y el resultado conserva los decimales.

```javascript
console.log(90 / 20);                // 4.5
console.log(Math.trunc(90 / 20));    // 4: para quedarse con la parte entera
```

Con decimales el residuo y la división dan resultados largos:

```javascript
const decimalValor = 2.3;
console.log(70 / decimalValor);   // 30.434782608695656
console.log(70 % decimalValor);   // 1.0000000000000053
```

Esos decimales de más son el resultado real de la operación en coma flotante. Más adelante se verá cómo redondearlos.

## División entre cero

JavaScript **no lanza ninguna excepción**, con enteros ni con decimales:

```javascript
console.log(90 / 0);   // Infinity
console.log(0 / 0);    // NaN
```

En C# y Java, `90 / 0` con enteros detiene el programa con una excepción.

## Potencia

JavaScript sí tiene operador de potencia. C# y Java usan `Math.Pow` y `Math.pow`.

```javascript
console.log(2 ** 3);            // 8
console.log(Math.pow(2, 3));    // 8: también existe
```

## Orden de las operaciones

Igual que en matemáticas: primero `**`, luego `*`, `/` y `%`, y por último `+` y `-`. Los paréntesis cambian el orden.

```javascript
console.log(2 + 3 * 4);     // 14
console.log((2 + 3) * 4);   // 20
```

## Ejemplo

```javascript
const valor1 = 90, valor2 = 70, valor3 = 10;

console.log("Total de la suma: " + (valor1 + valor2));
console.log("Total de la resta: " + (valor1 - valor2));
console.log("Producto: " + valor3 * valor2);
console.log("Resultado: " + valor1 / valor3);
console.log("Residuo: " + valor2 % valor3);
```

## Comandos

```bash
cd javascript/02-fundamentals
node 10-arithmetic-operators/index.js
```

Fuente: [curso de C# de INFORMATICONFIG](https://www.youtube.com/watch?v=8Z2pkmVGUK4&list=PL2Z95CSZ1N4F7mym8Ca16RoTDVAlIOPlT&index=10), vídeo 10.
