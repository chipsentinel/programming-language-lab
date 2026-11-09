# Entrada de datos en JavaScript

## Qué es la entrada de datos

El proceso por el que un programa **recibe información externa**, normalmente de un usuario, para procesarla, guardarla o usarla. Rellenar un formulario o escribir usuario y clave son entradas de datos.

Hasta ahora el programa solo **mostraba** datos con `console.log`. Para **recoger** lo que el usuario escribe en la consola se usa el módulo `readline`, que viene con Node.js: no hace falta instalar nada.

> En el navegador la entrada se hace con formularios y `prompt()`. Aquí se trabaja con Node.js en la consola.

## `readline`

Se crea una interfaz que lee del teclado (`process.stdin`) y escribe en la consola (`process.stdout`).

```javascript
const readline = require("readline/promises");

const rl = readline.createInterface({
    input: process.stdin,
    output: process.stdout,
});
```

`rl.question("pregunta")` muestra la pregunta, detiene el programa hasta que el usuario pulsa `Enter` y devuelve **lo escrito como texto**.

```javascript
const nombre = await rl.question("Introduzca su nombre... ");
console.log("Nombre colocado: " + nombre);
```

```text
Introduzca su nombre... Sol
Nombre colocado: Sol
```

- La **pregunta va dentro** de `question`; en C# y Java son una instrucción aparte.
- Al terminar se cierra con `rl.close()`.

## Por qué `async` y `await`

Leer del teclado es una operación **asíncrona**: el programa tiene que esperar al usuario. Por eso `question` devuelve una promesa, y para esperarla se escribe `await`. `await` solo puede usarse dentro de una función `async`:

```javascript
async function main() {
    const nombre = await rl.question("Introduzca su nombre... ");
    console.log(nombre);
}

main();
```

Las promesas se verán con detalle en la lección de programación asíncrona. De momento basta con copiar esta estructura.

## Pedir números

`question` **siempre devuelve texto**. Para usarlo como número hay que convertirlo, con lo que se vio en la lección de casting.

```javascript
const edad = Number(await rl.question("Introduzca su edad... "));
const salario = Number(await rl.question("Indique su salario... "));
```

- Si el usuario escribe algo que no es un número, `Number` devuelve `NaN` y no lanza ningún error.
- `Number` exige **punto** como separador decimal: se escribe `1500.50`, no `1500,50`.
- Sin `Number`, `edad + 10` concatenaría: `"34" + 10` da `"3410"`.

## Presentar mejor la entrada

Una línea de signos `=` separa visualmente las partes del programa:

```javascript
console.log("==============================");
console.log("Introduzca sus datos");
console.log("==============================");
```

## Ejemplo

```javascript
const readline = require("readline/promises");

async function main() {
    const rl = readline.createInterface({
        input: process.stdin,
        output: process.stdout,
    });

    const nombre = await rl.question("Introduzca su nombre... ");
    const edad = Number(await rl.question("Introduzca su edad... "));
    const salario = Number(await rl.question("Indique su salario... "));

    console.log(`${nombre} tiene ${edad} años y cobra ${salario}`);
    console.log(`En 10 años tendrá ${edad + 10}`);

    rl.close();
}

main();
```

## Comandos

```bash
cd javascript/02-fundamentals
node 11-user-input/index.js
```

Fuente: [curso de C# de INFORMATICONFIG](https://www.youtube.com/watch?v=Ic8VCjLitjE&list=PL2Z95CSZ1N4F7mym8Ca16RoTDVAlIOPlT&index=11), vídeo 11.
