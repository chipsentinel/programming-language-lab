// Depurar (debug) es el proceso de encontrar, analizar y corregir errores en un programa.
// Se hace con el DEPURADOR: se ejecuta el programa paso a paso, se detiene en puntos
// concretos (puntos de interrupción) y se examina el valor de las variables.
//
// Este archivo es un programa PARA DEPURAR: pon puntos de interrupción (clic en el margen izquierdo
// de VS Code) en las líneas marcadas con "// <-- punto de interrupción" y ejecútalo con F5 (ver el .md).
//
// Tipos de errores:
//   - De sintaxis: el código está mal escrito (SyntaxError) y no llega a ejecutarse.
//   - En tiempo de ejecución: ocurren al ejecutar (excepciones, lección 41).
//   - Lógicos: el programa funciona, pero NO hace lo que se quería. Son los más difíciles de encontrar.


// ===== 1. Un error lógico =====

// El programa funciona sin errores, pero uno de los resultados está mal.
function operaciones() {
    const a = 30;
    const b = 20;

    const suma = a + b;                    // <-- punto de interrupción
    const resta = a - b;
    const multiplicacion = a * b;

    console.log("Suma: " + suma);
    console.log("Resta: " + resta);

    // Versión con el error lógico: se muestra la variable equivocada.
    // console.log("Multiplicación: " + suma);          // muestra 50 en lugar de 600
    console.log("Multiplicación: " + multiplicacion);   // corregido: 600
}


// ===== 2. Un error clásico: uno de menos =====

// Debería sumar de 1 a n, pero el bucle termina antes: < n en lugar de <= n
function sumaHastaConError(n) {
    let total = 0;

    for (let i = 1; i < n; i++) {          // <-- punto de interrupción: ¿cuánto vale i en la última vuelta?
        total += i;
    }

    return total;
}

function sumaHasta(n) {
    let total = 0;

    for (let i = 1; i <= n; i++) {         // corregido: <=
        total += i;
    }

    return total;
}


// ===== 3. Depurar con trazas: mostrar valores mientras corre =====

function sumaHastaConTraza(n) {
    let total = 0;

    for (let i = 1; i <= n; i++) {
        total += i;
        console.log(`  i=${i}, total=${total}`);   // la "traza": ver cómo evolucionan las variables
    }

    return total;
}


operaciones();

console.log(`SumaHastaConError(5) = ${sumaHastaConError(5)} (esperado 15)`);
console.log(`SumaHasta(5) = ${sumaHasta(5)}`);

console.log("Traza de SumaHastaConTraza(3):");
const resultado = sumaHastaConTraza(3);
console.log("Resultado: " + resultado);


// ===== 4. Herramientas de JavaScript para depurar =====

// La instrucción debugger actúa como un punto de interrupción... pero solo si hay un depurador conectado.
// Sin depurador no hace nada.
debugger;

// console.assert escribe un aviso SOLO si la condición es falsa (no detiene el programa)
console.assert(resultado === 6, "El resultado debería ser 6");

// console.error / console.warn salen por la salida de errores
console.error("[traza] esto sale por la salida de errores");

// console.table muestra un array u objeto como tabla
console.table([{ n: 1, total: 1 }, { n: 2, total: 3 }, { n: 3, total: 6 }]);

// console.trace muestra la pila de llamadas hasta ese punto
// console.trace("¿cómo llegué hasta aquí?");
