// ===== 1. Incremento (++) =====

// ++ suma 1 a la variable, sin escribir numero = numero + 1
// Las variables que cambian de valor se declaran con let
let numero1 = 5;
console.log("Valor original: " + numero1);   // 5

numero1++;
console.log("Valor incrementado: " + numero1);   // 6

numero1++;
console.log("Valor incrementado: " + numero1);   // 7


// ===== 2. Decremento (--) =====

// -- resta 1 a la variable
numero1--;
console.log("Valor decrementado: " + numero1);   // 6

numero1--;
console.log("Valor decrementado: " + numero1);   // 5


// ===== 3. Postincremento y preincremento =====

// numero++ (post): primero usa el valor y después lo incrementa
// ++numero (pre): primero incrementa y después usa el valor
// Solas, en una línea, dan el mismo resultado; la diferencia aparece al asignar:

let valor = 7;
const valor2 = valor++;   // valor2 recibe 7 y después valor pasa a 8
console.log(`Post: valor2 = ${valor2}, valor = ${valor}`);   // 7 y 8

let valor3 = 7;
const valor4 = ++valor3;  // valor3 pasa a 8 y valor4 recibe 8
console.log(`Pre: valor4 = ${valor4}, valor3 = ${valor3}`);   // 8 y 8


// ===== 4. Operadores compuestos de asignación =====

// variable op= valor equivale a variable = variable op valor
let numero2 = 5;

numero2 += 3;   // numero2 = numero2 + 3
console.log("+= 3: " + numero2);   // 8

numero2 -= 3;   // numero2 = numero2 - 3
console.log("-= 3: " + numero2);   // 5

numero2 *= 3;   // numero2 = numero2 * 3
console.log("*= 3: " + numero2);   // 15

numero2 /= 3;   // numero2 = numero2 / 3 (aquí no hay división entera)
console.log("/= 3: " + numero2);   // 5

numero2 %= 3;   // numero2 = numero2 % 3 (residuo)
console.log("%= 3: " + numero2);   // 2

// El operador siempre va antes del igual: =+ o =- no son operadores compuestos
// numero2 =+ 3;   // asigna +3, no suma

// Con otros valores sí se ve que /= no es entera: 5 / 3 conserva los decimales
let numero3 = 5;
numero3 /= 3;
console.log("5 /= 3: " + numero3);   // 1.6666666666666667
