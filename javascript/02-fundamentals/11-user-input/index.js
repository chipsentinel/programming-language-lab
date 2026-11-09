const readline = require("readline/promises");

// Leer de la consola es una operación asíncrona en Node.js (se verá en la lección de asincronía).
// Por ahora basta con copiar esta estructura: el código va dentro de una función async
// y delante de cada rl.question se escribe await.
async function main() {

    // ===== 1. Pedir un texto =====

    // Entrada de datos: el programa recibe información externa, normalmente del usuario.
    // console.log solo muestra un mensaje; para recoger lo que se escribe hace falta readline.
    const rl = readline.createInterface({
        input: process.stdin,
        output: process.stdout,
    });

    console.log("==============================");
    console.log("Introduzca sus datos");
    console.log("==============================");

    const nombre = await rl.question("Introduzca su nombre... ");   // espera a que se pulse Enter


    // ===== 2. Mostrar lo que se ha escrito =====

    console.log("Nombre colocado: " + nombre);


    // ===== 3. Pedir un número entero =====

    // question siempre devuelve texto: hay que convertirlo (casting, lección anterior)
    const edad = Number(await rl.question("Introduzca su edad... "));
    console.log("Edad colocada: " + edad);


    // ===== 4. Pedir un número decimal =====

    // Number exige punto como separador decimal
    const salario = Number(await rl.question("Indique su salario... "));
    console.log("Salario colocado: " + salario);


    // ===== 5. Usar los datos =====

    console.log("==============================");
    console.log(`${nombre} tiene ${edad} años y cobra ${salario}`);
    console.log(`En 10 años tendrá ${edad + 10}`);
    console.log("==============================");

    // Si el texto no es un número válido, Number devuelve NaN (no lanza error)

    rl.close();
}

main();
