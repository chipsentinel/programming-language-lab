// JavaScript solo tiene UNA función para números aleatorios: Math.random().
// Da un decimal entre 0 (incluido) y 1 (excluido). Todo lo demás (enteros, rangos, elegir...) se calcula a partir de ella.
// Los números son PSEUDOALEATORIOS: parecen impredecibles, pero salen de una fórmula.
// NO sirven para contraseñas ni claves de seguridad (ver el apartado final).
//
// Las líneas que empiezan por "Ejemplo:" cambian en cada ejecución.


// ===== 0. Funciones auxiliares =====

// Entero entre min (incluido) y max (EXCLUIDO), como Next(min, max) de C#
const aleatorioEntre = (min, max) => Math.floor(Math.random() * (max - min)) + min;

// Entero entre 0 y max (excluido), como Next(max)
const aleatorioHasta = (max) => aleatorioEntre(0, max);


// ===== 1. Math.random =====

const numero1 = Math.random();
console.log(`Ejemplo: número aleatorio 1: ${numero1}`);
console.log("Math.random() está entre 0 y 1: " + (numero1 >= 0 && numero1 < 1));


// ===== 2. Entre 0 y un máximo =====

// El máximo NO se incluye: aleatorioHasta(50) da de 0 a 49.
// Math.random() * 50 da un decimal entre 0 y 49.99...; Math.floor lo convierte en entero.
const numero2 = aleatorioHasta(50);
console.log(`Ejemplo: número aleatorio entre 0 y 49: ${numero2}`);

// Para comprobarlo se piden muchos valores y se mira si TODOS caen en el rango
let todosEnRango = true;
for (let i = 0; i < 1000; i++) {
    const valor = aleatorioHasta(50);
    if (valor < 0 || valor >= 50) todosEnRango = false;
}
console.log("1000 valores de aleatorioHasta(50) están entre 0 y 49: " + todosEnRango);


// ===== 3. Entre un mínimo y un máximo =====

// El mínimo SÍ se incluye y el máximo NO: aleatorioEntre(20, 25) da 20, 21, 22, 23 o 24
const numero3 = aleatorioEntre(20, 25);
console.log(`Ejemplo: número aleatorio entre 20 y 24: ${numero3}`);

let todosEn20a24 = true;
let salio20 = false;
let salio25 = false;
for (let i = 0; i < 1000; i++) {
    const valor = aleatorioEntre(20, 25);
    if (valor < 20 || valor > 24) todosEn20a24 = false;
    if (valor === 20) salio20 = true;
    if (valor === 25) salio25 = true;
}
console.log("1000 valores de aleatorioEntre(20, 25) están entre 20 y 24: " + todosEn20a24);
console.log("El mínimo (20) puede salir: " + salio20);
console.log("El máximo (25) nunca sale: " + !salio25);


// ===== 4. Decimales =====

// Math.random() ya es un decimal entre 0 y 1
const decimal1 = Math.random();
console.log("Math.random() está entre 0 y 1: " + (decimal1 >= 0 && decimal1 < 1));

// Para otro rango se multiplica: entre 0.0 y 10.0
const decimal2 = Math.random() * 10;
console.log(`Ejemplo: decimal entre 0 y 10: ${decimal2}`);
console.log("Está entre 0 y 10: " + (decimal2 >= 0 && decimal2 < 10));


// ===== 5. Semilla: la misma semilla repite la secuencia =====

// Math.random() NO permite fijar una semilla. Para secuencias reproducibles hay que escribir
// (o instalar) un generador propio. Este es uno pequeño y conocido (mulberry32):
function crearGenerador(semilla) {
    let estado = semilla;
    return function () {
        estado |= 0;
        estado = (estado + 0x6d2b79f5) | 0;
        let t = Math.imul(estado ^ (estado >>> 15), 1 | estado);
        t = (t + Math.imul(t ^ (t >>> 7), 61 | t)) ^ t;
        return ((t ^ (t >>> 14)) >>> 0) / 4294967296;
    };
}

const a = crearGenerador(42);
const b = crearGenerador(42);

let iguales = true;
for (let i = 0; i < 5; i++) {
    if (Math.floor(a() * 100) !== Math.floor(b() * 100)) iguales = false;
}
console.log("Con la misma semilla se repite la secuencia: " + iguales);


// ===== 6. Usos habituales =====

// Tirar un dado: del 1 al 6 (el máximo 7 no se incluye)
const dado = aleatorioEntre(1, 7);
console.log(`Ejemplo: dado: ${dado}`);
console.log("Dado válido (1-6): " + (dado >= 1 && dado <= 6));

// Cara o cruz
const moneda = aleatorioHasta(2) === 0 ? "cara" : "cruz";
console.log(`Ejemplo: moneda: ${moneda}`);

// Elegir un elemento al azar de un array: una posición aleatoria
const colores = ["rojo", "verde", "azul", "amarillo"];
const elegido = colores[aleatorioHasta(colores.length)];
console.log(`Ejemplo: color elegido: ${elegido}`);
console.log("El color existe en el array: " + colores.includes(elegido));


// ===== 7. Mezclar un array (algoritmo de Fisher-Yates) =====

const baraja = [1, 2, 3, 4, 5];
for (let i = baraja.length - 1; i > 0; i--) {
    const j = aleatorioHasta(i + 1);
    [baraja[i], baraja[j]] = [baraja[j], baraja[i]];   // intercambia dos posiciones
}
console.log("Mezclado conserva los mismos elementos: " + (baraja.slice().sort().join() === "1,2,3,4,5"));


// ===== 8. Math.random NO es seguro para contraseñas =====

// Para seguridad se usa el módulo crypto (en Node.js; en el navegador, crypto.getRandomValues)
const { randomInt } = require("crypto");
const seguro = randomInt(1, 101);   // entre 1 y 100 (el máximo no se incluye)
console.log("crypto.randomInt(1, 101) está entre 1 y 100: " + (seguro >= 1 && seguro <= 100));
