const readline = require("readline/promises");

// ===== 1. Bucle for =====

// for reúne en una sola línea las tres partes de un bucle con contador:
// for (inicialización; condición; actualización)

for (let i = 0; i < 4; i++) {
    console.log("Número de vuelta: " + i);
}

console.log("Fin del bucle");

// i es la variable de control; por costumbre se llama i, j, k
// Se lee: "para i empezando en 0, mientras i sea menor que 4, sumando 1 en cada vuelta"
// El número que aparece en la condición (4) indica cuántas vueltas da el bucle


// ===== 2. Empezar en 1 e incluir el límite =====

for (let i = 1; i <= 5; i++) {
    console.log("Vuelta " + i);
}


// ===== 3. Cambiar el paso =====

// i += 2 salta de dos en dos: da menos vueltas
for (let i = 1; i <= 5; i += 2) {
    console.log("Impar: " + i);   // 1, 3, 5
}


// ===== 4. Cuenta atrás =====

for (let i = 3; i >= 1; i--) {
    console.log("Faltan " + i);
}

console.log("¡Despegue!");


// ===== 5. Decisiones dentro del bucle =====

for (let i = 1; i <= 5; i++) {
    if (i === 4) {
        console.log("Soy la vuelta cuatro");
    } else {
        console.log("Vuelta " + i);
    }
}


// ===== 6. Tabla de multiplicar =====

const TABLA = 8;

console.log(`Tabla del ${TABLA}`);

for (let i = 1; i <= 10; i++) {
    console.log(`${TABLA} x ${i} = ${TABLA * i}`);
}


// ===== 7. La variable del bucle solo existe dentro de él =====

// console.log(i);   // ReferenceError: i no existe fuera del for (con let)


// ===== 8. El usuario decide cuántas vueltas =====

// Leer del teclado es asíncrono: se hace dentro de una función async (lección 11)
async function main() {
    const rl = readline.createInterface({
        input: process.stdin,
        output: process.stdout,
    });

    const vueltas = Number(await rl.question("Indique la cantidad de vueltas...\n"));

    for (let i = 1; i <= vueltas; i++) {
        console.log("Número de vuelta: " + i);
    }

    rl.close();
}

main();
