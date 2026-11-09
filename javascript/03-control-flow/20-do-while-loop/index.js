const readline = require("readline/promises");

// ===== 1. Bucle do-while =====

// do { ... } while (condición);
// Ejecuta el bloque UNA VEZ como mínimo y después repite mientras la condición sea verdadera.
// La condición se comprueba al FINAL de cada vuelta.

let i = 1;

do {
    console.log("Vuelta " + i);
    i++;
} while (i <= 3);

console.log("Fin del bucle");


// ===== 2. Se ejecuta al menos una vez =====

// Aunque la condición sea falsa desde el principio, el bloque corre una vez
let contador = 10;

do {
    console.log("do-while: se ejecuta una vez con contador = " + contador);
    contador++;
} while (contador < 3);

// Con while, en cambio, el bloque no se ejecutaría ninguna vez
while (contador < 3) {
    console.log("while: esto no se imprime");
}


// ===== 3. Pedir datos hasta que el usuario escriba 0 =====

// Leer del teclado es asíncrono: se hace dentro de una función async (lección 11)
async function main() {
    const rl = readline.createInterface({
        input: process.stdin,
        output: process.stdout,
    });

    // La variable se declara ANTES del bucle: la condición está fuera del bloque do { }
    // y no vería una variable declarada dentro.
    let numero;

    do {
        console.log("Introduce 0 para salir");
        numero = Number(await rl.question(""));
        console.log("Número colocado: " + numero);
    } while (numero !== 0);

    console.log("Fin del programa");


    // ===== 4. Mismo resultado con while =====

    // Con while hay que dar un valor inicial a la variable para poder entrar al bucle
    // (aquí el valor de salida es 3 en lugar de 0)
    numero = 0;

    while (numero !== 3) {
        console.log("Introduce el número 3 para salir");
        numero = Number(await rl.question(""));
        console.log("Número colocado: " + numero);
    }

    console.log("Fin del programa");

    // Si se escribe un texto que no es un número, Number devuelve NaN y el bucle no termina

    rl.close();
}

main();
