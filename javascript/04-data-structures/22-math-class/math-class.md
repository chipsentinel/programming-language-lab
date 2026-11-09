# El objeto `Math` en JavaScript

## Qué es

Una colección de **funciones matemáticas ya hechas**. Se usan escribiendo `Math.`, el nombre de la función y los valores entre paréntesis. Complementa a los operadores aritméticos (`+ - * / %`), que no cubren raíces ni redondeos.

> Qué es exactamente un objeto y una función se verá más adelante. Por ahora basta con saber usarlas: `Math.funcion(valor)` devuelve un resultado que se guarda o se muestra.

Está siempre disponible: no hace falta importar nada.

```javascript
const resultado = Math.pow(4, 2);   // 16
```

## Funciones principales

| Función | Qué hace | Ejemplo | Resultado |
|---|---|---|---|
| `Math.pow(x, y)` | Eleva `x` a la `y` | `Math.pow(4, 2)` | `16` |
| `Math.sqrt(x)` | Raíz cuadrada | `Math.sqrt(4)` | `2` |
| `Math.abs(x)` | Valor absoluto | `Math.abs(-4)` | `4` |
| `Math.ceil(x)` | Redondea **hacia arriba** | `Math.ceil(50.23)` | `51` |
| `Math.floor(x)` | Redondea **hacia abajo** | `Math.floor(50.23)` | `50` |
| `Math.round(x)` | Redondea al **más cercano** | `Math.round(50.53)` | `51` |
| `Math.max(a, b)` | El **mayor** | `Math.max(4, 50.53)` | `50.53` |
| `Math.min(a, b)` | El **menor** | `Math.min(4, 50.53)` | `4` |
| `Math.PI` | El número pi | | `3.141592653589793` |

`Math.PI` no lleva paréntesis porque es un **valor**, no una función. Como en JavaScript solo existe `number`, los resultados enteros se muestran sin decimales (`16`, no `16.0`).

## Potencia y raíz

```javascript
const numero = 4;

console.log(Math.pow(numero, 2));   // 16: 4 al cuadrado
console.log(Math.pow(numero, 3));   // 64: 4 al cubo
console.log(Math.sqrt(numero));     // 2: raíz cuadrada
```

JavaScript sí tiene operador de potencia, el `**` visto en la lección de operadores:

```javascript
console.log(numero ** 2);   // 16
```

## Valor absoluto

El valor de un número **sin su signo**:

```javascript
console.log(Math.abs(-4));   // 4
console.log(Math.abs(4));    // 4
```

## Redondeo

| `x` | `ceil` | `floor` | `round` |
|---|---|---|---|
| `50.23` | `51` | `50` | `50` |
| `50.53` | `51` | `50` | `51` |

- **`ceil`**: siempre sube al siguiente entero (techo).
- **`floor`**: siempre baja (suelo).
- **`round`**: va al entero más cercano; en el punto medio (`.5`) **sube**.

```javascript
console.log(Math.round(2.5));    // 3
console.log(Math.round(3.5));    // 4
console.log(Math.round(-2.5));   // -2
```

Con **negativos** el `.5` sube hacia el infinito positivo: `-2.5` da `-2`, no `-3`. En C# `Math.Round` redondea al par (`2.5` da `2`), y en Java `Math.round(-2.5)` también da `-2`.

Para quedarse solo con la parte entera, sin redondear, existe `Math.trunc` (lección de casting).

### Redondear a unos decimales

`Math.round` no tiene argumento de decimales. Se multiplica, se redondea y se divide:

```javascript
console.log(Math.round(3.14159 * 100) / 100);   // 3.14
```

O con `toFixed`, que **devuelve un texto**:

```javascript
console.log((3.14159).toFixed(2));   // "3.14"
```

## Máximo y mínimo

```javascript
console.log(Math.max(4, 50.53));   // 50.53
console.log(Math.min(4, 50.53));   // 4
```

A diferencia de C# y Java, aceptan **cualquier cantidad de valores**: `Math.max(4, 50.53, 7, 99)` da `99`.

## Trigonometría

```javascript
console.log(Math.sin(Math.PI / 2));   // 1
console.log(Math.cos(0));             // 1
```

`sin`, `cos` y `tan` trabajan con **radianes**, no con grados. 90 grados son `Math.PI / 2` radianes.

## Ejercicio: área de un círculo

El área es `π · radio²`. Leer del teclado es asíncrono, así que va dentro de una función `async` (lección 11):

```javascript
const readline = require("readline/promises");

async function main() {
    const rl = readline.createInterface({ input: process.stdin, output: process.stdout });

    const radio = Number(await rl.question("Indique el radio del círculo...\n"));
    const area = Math.PI * Math.pow(radio, 2);

    console.log("Área del círculo: " + area);
    console.log("Área redondeada: " + Math.round(area));
    console.log("Área con 2 decimales: " + Math.round(area * 100) / 100);

    rl.close();
}

main();
```

Con radio `5`:

```text
Área del círculo: 78.53981633974483
Área redondeada: 79
Área con 2 decimales: 78.54
```

## Ejemplo

```javascript
const precio = 50.53;

console.log("Hacia arriba: " + Math.ceil(precio));
console.log("Hacia abajo: " + Math.floor(precio));
console.log("Redondeado: " + Math.round(precio));
console.log("Elevado al cuadrado: " + Math.pow(4, 2));
```

## Comandos

```bash
cd javascript/04-data-structures
node 22-math-class/index.js
```

Fuente: [curso de C# de INFORMATICONFIG](https://www.youtube.com/watch?v=scmOWO6FM1c&list=PL2Z95CSZ1N4F7mym8Ca16RoTDVAlIOPlT&index=22), vídeo 22.
