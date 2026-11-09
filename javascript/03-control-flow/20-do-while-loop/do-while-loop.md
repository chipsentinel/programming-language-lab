# Bucle `do-while` en JavaScript

## Qué es

Un bucle que **ejecuta el bloque de código al menos una vez** y después lo repite **mientras la condición sea verdadera**. La diferencia con `while` y `for` está en **cuándo** se comprueba la condición.

| | `while` | `do-while` |
|---|---|---|
| Comprueba la condición | **Antes** de cada vuelta | **Después** de cada vuelta |
| Vueltas mínimas | 0 | **1** |

```javascript
let i = 1;

do {
    console.log("Vuelta " + i);
    i++;
} while (i <= 3);

console.log("Fin del bucle");
```

```text
Vuelta 1
Vuelta 2
Vuelta 3
Fin del bucle
```

```text
do {
    código que se repite
} while (condición);     ← termina en punto y coma
```

`do` significa "haz": haz esto, y luego mira si hay que repetir. El `;` tras el `while` es recomendable, a diferencia del `while` normal.

## Se ejecuta al menos una vez

Aunque la condición sea falsa desde el principio, el bloque corre **una vez**:

```javascript
let contador = 10;

do {
    console.log("Se ejecuta una vez");
    contador++;
} while (contador < 3);     // 11 < 3 es false: no se repite
```

Con `while (contador < 3)` el mismo bloque no se ejecutaría **ninguna** vez.

## Caso típico: pedir datos hasta que sean válidos

No se sabe cuántas veces escribirá el usuario, y hay que pedirle un dato **como mínimo una vez**. Es el uso ideal. Leer del teclado es asíncrono, así que va dentro de una función `async` (lección 11).

```javascript
const readline = require("readline/promises");

async function main() {
    const rl = readline.createInterface({ input: process.stdin, output: process.stdout });
    let numero;

    do {
        console.log("Introduce 0 para salir");
        numero = Number(await rl.question(""));
        console.log("Número colocado: " + numero);
    } while (numero !== 0);

    console.log("Fin del programa");
    rl.close();
}

main();
```

```text
Introduce 0 para salir
5
Número colocado: 5
Introduce 0 para salir
0
Número colocado: 0
Fin del programa
```

- No hay un número fijo de vueltas: termina cuando el usuario escribe `0`.
- Si se escribe un texto que no es un número, `Number` devuelve `NaN`; como `NaN !== 0` es verdadero, el bucle **sigue pidiendo** en lugar de dar error.

## Declarar la variable antes del bucle

La condición está **fuera** del bloque `do { }`. Una variable declarada dentro no sería visible en el `while`:

```javascript
do {
    let numero = Number(await rl.question(""));   // existe solo aquí dentro
} while (numero !== 0);    // ReferenceError: numero no está definida
```

Por eso se declara antes: `let numero;`. No hace falta darle valor, porque el bloque se ejecuta antes de comprobarla.

## Lo mismo con `while`

Con `while` hay que **dar un valor inicial** que permita entrar en el bucle:

```javascript
let numero = 0;

while (numero !== 3) {
    console.log("Introduce el número 3 para salir");
    numero = Number(await rl.question(""));
}
```

Con `do-while` no hace falta ese valor ficticio.

## Qué bucle usar

| Bucle | Cuándo |
|---|---|
| `for` | Se sabe **cuántas veces** repetir |
| `while` | Se repite **mientras** se cumpla algo, y puede no ejecutarse |
| `do-while` | Se repite mientras se cumpla algo, pero hace falta ejecutarlo **al menos una vez** |

## Ejemplo

```javascript
let i = 1;

do {
    console.log("Vuelta " + i);
    i++;
} while (i <= 3);

console.log("Fin del bucle");
```

## Comandos

```bash
cd javascript/03-control-flow
node 20-do-while-loop/index.js
```

Fuente: [curso de C# de INFORMATICONFIG](https://www.youtube.com/watch?v=9RMg9z62GLU&list=PL2Z95CSZ1N4F7mym8Ca16RoTDVAlIOPlT&index=20), vídeo 20.
