# Bucle `for` en JavaScript

## Qué es

Un bucle que **repite un bloque de código un número determinado de veces**. Reúne en **una sola línea** las tres partes de un bucle con contador: inicio, condición y actualización.

```javascript
for (let i = 0; i < 4; i++) {
    console.log("Número de vuelta: " + i);
}

console.log("Fin del bucle");
```

```text
Número de vuelta: 0
Número de vuelta: 1
Número de vuelta: 2
Número de vuelta: 3
Fin del bucle
```

## Las tres partes

```text
for (  let i = 0  ;  i < 4  ;  i++  )
       └ inicio      └ condición  └ actualización
```

| Parte | Cuándo se ejecuta | En el ejemplo |
|---|---|---|
| **Inicialización** | Una sola vez, antes de empezar | `let i = 0` |
| **Condición** | Antes de cada vuelta; si es falsa el bucle termina | `i < 4` |
| **Actualización** | Al final de cada vuelta | `i++` |

- `i` es la **variable de control**; por costumbre se llama `i`, `j`, `k`.
- Se declara con **`let`**, no con `const`, porque su valor cambia en cada vuelta.
- La condición marca el límite: `i < 4` empezando en 0 da **4 vueltas**.
- Con `while` estas tres partes iban en sitios distintos; en `for` están juntas y es más difícil olvidar la actualización.

## Empezar en 1 e incluir el límite

Para contar de 1 a 5:

```javascript
for (let i = 1; i <= 5; i++) {
    console.log("Vuelta " + i);
}
```

- `i < 5` llega hasta 4; `i <= 5` **incluye** el 5.
- Empezando en `0` hay una vuelta más que empezando en `1` con el mismo límite.

## Cambiar el paso

La actualización no tiene por qué ser `i++`. Con `i += 2` el contador salta de dos en dos:

```javascript
for (let i = 1; i <= 5; i += 2) {
    console.log("Impar: " + i);   // 1, 3, 5
}
```

Solo da **3 vueltas**: 1, 3 y 5.

## Cuenta atrás

Se empieza en el valor más alto, se usa `>=` y la actualización **resta**:

```javascript
for (let i = 3; i >= 1; i--) {
    console.log("Faltan " + i);
}

console.log("¡Despegue!");
```

## Decisiones dentro del bucle

```javascript
for (let i = 1; i <= 5; i++) {
    if (i === 4) {
        console.log("Soy la vuelta cuatro");
    } else {
        console.log("Vuelta " + i);
    }
}
```

## Tabla de multiplicar

```javascript
const TABLA = 8;

for (let i = 1; i <= 10; i++) {
    console.log(`${TABLA} x ${i} = ${TABLA * i}`);
}
```

Es el mismo ejemplo que con `while`, pero más corto.

## Alcance de la variable

Con `let`, la variable declarada en el `for` **solo existe dentro del bucle**:

```javascript
for (let i = 0; i < 3; i++) { }

console.log(i);   // ReferenceError: i no está definida
```

Con `var` (la forma antigua) la variable **sí quedaría visible fuera**, por eso hoy se usa `let`.

## El usuario decide las vueltas

El límite puede ser una variable, por ejemplo un valor que escribe el usuario. Leer del teclado es asíncrono, así que va dentro de una función `async` (lección 11):

```javascript
const readline = require("readline/promises");

async function main() {
    const rl = readline.createInterface({ input: process.stdin, output: process.stdout });

    const vueltas = Number(await rl.question("Indique la cantidad de vueltas...\n"));

    for (let i = 1; i <= vueltas; i++) {
        console.log("Número de vuelta: " + i);
    }

    rl.close();
}

main();
```

Con `5` da cinco vueltas; con `20`, veinte.

## Otras formas de recorrer en JavaScript

JavaScript tiene además `for...of` y `for...in`, pensados para recorrer arrays y objetos. Se verán con los arrays; el `for` clásico es el que se usa para contar.

## `for` frente a `while`

| | `for` | `while` |
|---|---|---|
| Se usa cuando | Se **sabe cuántas veces** repetir | **No se sabe** cuántas: depende de una condición |
| Partes del contador | Juntas en la cabecera | Repartidas por el código |
| Ejemplo | Recorrer del 1 al 10 | Repetir hasta que el usuario acierte |

## Ejemplo

```javascript
for (let i = 1; i <= 5; i++) {
    console.log("Vuelta " + i);
}

console.log("Fin del bucle");
```

## Comandos

```bash
cd javascript/03-control-flow
node 19-for-loop/index.js
```

Fuente: [curso de C# de INFORMATICONFIG](https://www.youtube.com/watch?v=_9hZ9izNJkE&list=PL2Z95CSZ1N4F7mym8Ca16RoTDVAlIOPlT&index=19), vídeo 19.
