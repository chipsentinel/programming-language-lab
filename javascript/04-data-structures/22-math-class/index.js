const readline = require("readline/promises");

// El objeto Math reúne funciones matemáticas ya hechas: Math.funcion(valores).
// (Qué son un objeto y una función se verá más adelante: por ahora solo se usan.)


// ===== 1. Potencia: Math.pow =====

let numero = 4;

console.log("Potenciación (4 al cuadrado): " + Math.pow(numero, 2));   // 16
console.log("Potenciación (4 al cubo): " + Math.pow(numero, 3));       // 64
console.log("Con el operador **: " + numero ** 2);                     // 16


// ===== 2. Raíz cuadrada: Math.sqrt =====

console.log("Raíz cuadrada de 4: " + Math.sqrt(numero));   // 2


// ===== 3. Valor absoluto: Math.abs =====

numero = -4;
console.log("Valor absoluto de -4: " + Math.abs(numero));   // 4


// ===== 4. Redondeo: ceil, floor y round =====

let numero2 = 50.23;

console.log("Hacia arriba (ceil): " + Math.ceil(numero2));      // 51
console.log("Hacia abajo (floor): " + Math.floor(numero2));      // 50
console.log("Normal (round) 50.23: " + Math.round(numero2));     // 50

numero2 = 50.53;
console.log("Normal (round) 50.53: " + Math.round(numero2));     // 51


// ===== 5. Redondear el punto medio (.5) =====

// En JavaScript, Math.round sube cuando está justo en la mitad...
console.log(Math.round(2.5));    // 3
console.log(Math.round(3.5));    // 4
// ...pero con negativos sube hacia el infinito positivo, no se aleja del cero
console.log(Math.round(-2.5));   // -2

// Redondear a un número de decimales
console.log(Math.round(3.14159 * 100) / 100);   // 3.14
console.log((3.14159).toFixed(2));              // "3.14" (devuelve un texto)


// ===== 6. Máximo y mínimo: Math.max y Math.min =====

numero = 4;

console.log("Máximo: " + Math.max(numero, numero2));   // 50.53
console.log("Mínimo: " + Math.min(numero, numero2));   // 4


// ===== 7. Pi y funciones trigonométricas =====

console.log("Valor de pi: " + Math.PI);               // 3.141592653589793
console.log("Seno de 90 grados: " + Math.sin(Math.PI / 2));   // 1 (los ángulos van en radianes)
console.log("Coseno de 0: " + Math.cos(0));                    // 1


// ===== 8. Ejercicio: área de un círculo =====

// Leer del teclado es asíncrono: se hace dentro de una función async (lección 11)
async function main() {
    const rl = readline.createInterface({
        input: process.stdin,
        output: process.stdout,
    });

    const radio = Number(await rl.question("Indique el radio del círculo...\n"));

    const area = Math.PI * Math.pow(radio, 2);   // pi * radio al cuadrado

    console.log("Área del círculo: " + area);
    console.log("Área redondeada: " + Math.round(area));
    console.log("Área con 2 decimales: " + Math.round(area * 100) / 100);

    rl.close();
}

main();
