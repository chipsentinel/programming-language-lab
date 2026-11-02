// ===== 1. Repaso: let admite cualquier tipo =====

// Una variable solo se declara una vez; no se puede repetir el nombre
/*
let numero = 50;
let numero = "hola";   // SyntaxError: 'numero' ya está declarada
*/

let numero = 10.20;
console.log(numero);

numero = 4000;
console.log(numero);

numero = "hola";       // válido: JavaScript es de tipado dinámico
console.log(numero);

numero = 's';
console.log(numero);   // s


// ===== 2. Constantes =====

const NUMERO_2 = 44;
console.log(`Valor de constante: ${NUMERO_2}`);

// NUMERO_2 = 80;   // TypeError: Assignment to constant variable

// Se usan para valores inalterables, como Pi
const PI = 3.1416;
const MENSAJE_BIENVENIDA = "¡Bienvenido!";

console.log(PI);
console.log(MENSAJE_BIENVENIDA);

// const no hace inmutable el contenido de un objeto o array
const DIAS_LABORABLES = ["lunes", "martes"];
DIAS_LABORABLES.push("miércoles");   // válido: se modifica el array, no la constante
// DIAS_LABORABLES = [];             // TypeError


// ===== 3. Ejemplo con constantes =====

const DIAS = 7;
const NOMBRE = "Sol";
const IVA = 0.21;

const radio = 5;
const area = PI * radio * radio;

const precio = 100;
const total = precio * (1 + IVA);

console.log(`${NOMBRE}: una semana tiene ${DIAS} días`);
console.log(`Área del círculo de radio ${radio}: ${area}`);
console.log(`Precio con IVA: ${total}`);
