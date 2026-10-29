let nombre = "Sol";
let edad = 30;

// Salida
console.log("Nombre de usuario: " + nombre + ", edad: " + edad);
console.log(`Nombre de usuario: ${nombre}, edad: ${edad}`);
console.log(`Nombre de usuario: ${nombre} \nEdad: ${edad}`);

process.stdout.write(`Nombre usuario: ${nombre}`);
console.log(`, edad: ${edad}`);


// ===== 1. Una línea: // =====

// En su propia línea
let anio = 2025;   // al final de una línea

// Varias líneas
// con // en cada una
// como esta


// ===== 2. Varias líneas: /* */ =====

/*
   Este comentario ocupa
   varias líneas.
*/

let total = 10 /* valor inicial */ + 5;   // en medio de una línea


// ===== 3. Documentación JSDoc: /** */ =====

console.log(`Suma: ${sumar(2, 3)}`);

/**
 * Suma dos números.
 *
 * @param {number} a - Primer número.
 * @param {number} b - Segundo número.
 * @returns {number} La suma de a y b.
 */
function sumar(a, b) {
    return a + b;
}


// ===== 4. Desactivar código =====

// Con //
// console.log(`Nombre de usuario: ${nombre}, edad: ${edad + 5}`);

// Con /* */
/*
console.log("Nombre de usuario: " + nombre + ", edad: " + edad);
console.log(`Nombre de usuario: ${nombre}, edad: ${edad}`);
*/

// Con if (false): el código no se ejecuta (admite /* */ dentro)
if (false) {
    console.log("Esto no se ejecuta");
    /* incluso con comentarios de bloque dentro */
}


// ===== 5. Organizar =====

// Etiquetas para dejar avisos
// TODO: pedir estos datos por teclado
// FIXME: falla si el nombre está vacío
// NOTE: este valor es provisional
// HACK: solución temporal

// Plegar un bloque en Visual Studio Code
//#region Datos
let ciudad = "Madrid";
let espera = 30;   // segundos
//#endregion

console.log(`${anio} ${total} ${ciudad} ${espera}`);


// ===== 6. Un // dentro de un texto no es un comentario =====

console.log("Visita https://ejemplo.com");
console.log("// esto no es un comentario");
