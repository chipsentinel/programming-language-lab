// Los operadores lógicos combinan o modifican expresiones boolean (verdadero o falso)

let numero1 = 40;
const numero2 = 90;
let numero3 = 5;
let resultado;


// ===== 1. AND (&&): se cumplen las dos condiciones =====

resultado = numero1 < numero2 && numero1 < numero3;   // true && false
console.log("AND (40 < 90 y 40 < 5): " + resultado);   // false

numero3 = 50;
resultado = numero1 < numero2 && numero1 < numero3;   // true && true
console.log("AND (40 < 90 y 40 < 50): " + resultado);  // true


// ===== 2. OR (||): se cumple al menos una condición =====

resultado = numero1 < numero2 || numero1 < numero3;   // true || true
console.log("OR (40 < 90 o 40 < 50): " + resultado);    // true

numero1 = 100;
resultado = numero1 < numero2 || numero1 < numero3;   // false || false
console.log("OR (100 < 90 o 100 < 50): " + resultado);  // false


// ===== 3. NOT (!): invierte el valor =====

numero1 = 40;
numero3 = 5;
resultado = numero1 < numero2 && numero1 < numero3;   // false
console.log("Sin negar: " + resultado);               // false

resultado = !(numero1 < numero2 && numero1 < numero3);
console.log("Negado con !: " + resultado);            // true


// ===== 4. Tablas de verdad =====

console.log("true && true   = " + (true && true));     // true
console.log("true && false  = " + (true && false));    // false
console.log("false || true  = " + (false || true));    // true
console.log("false || false = " + (false || false));   // false
console.log("!true          = " + !true);              // false


// ===== 5. Evaluación en cortocircuito =====

// && y || dejan de evaluar en cuanto saben el resultado
const divisor = 0;
const seguro = divisor !== 0 && 10 / divisor > 1;   // 10 / 0 nunca se calcula
console.log("Sin error gracias al cortocircuito: " + seguro);   // false


// ===== 6. Orden de evaluación =====

// ! primero, después &&, y al final ||
console.log(true || false && false);     // true: se evalúa false && false primero
console.log((true || false) && false);   // false: los paréntesis cambian el orden


// ===== 7. && y || con valores que no son boolean =====

// En JavaScript devuelven uno de los dos valores, no siempre true o false
console.log("Sol" || "invitado");   // Sol: el primero es verdadero, se queda con él
console.log("" || "invitado");      // invitado: "" cuenta como falso
console.log(0 && "hola");           // 0: el primero es falso, se queda con él

// ?? solo sustituye null o undefined, no 0 ni ""
console.log(0 ?? 10);               // 0
console.log(null ?? 10);            // 10
