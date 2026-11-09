// Los operadores de comparación comparan dos valores y dan un resultado boolean: true o false.
// Con el operador ternario (condición ? siSeCumple : siNoSeCumple) se convierte en un texto.

let a = 10;
let b = 20;
let resultado;


// ===== 1. Mayor que (>) =====

resultado = (a > b) ? "Sí" : "No";
console.log("¿a es mayor que b? " + resultado);   // No

b = 2;
resultado = (a > b) ? "Sí" : "No";
console.log("¿a es mayor que b? " + resultado);   // Sí


// ===== 2. Menor que (<) =====

resultado = (a < b) ? "Sí" : "No";
console.log("¿a es menor que b? " + resultado);   // No


// ===== 3. Igual que (===) =====

// Tres signos de igual comparan valor y tipo; uno solo (=) asigna
resultado = (a === b) ? "Sí" : "No";
console.log("¿a es igual a b? " + resultado);   // No

b = 10;
resultado = (a === b) ? "Sí" : "No";
console.log("¿a es igual a b? " + resultado);   // Sí


// ===== 4. Mayor o igual que (>=) =====

resultado = (a >= b) ? "Sí" : "No";
console.log("¿a es mayor o igual que b? " + resultado);   // Sí

a = 1;
resultado = (a >= b) ? "Sí" : "No";
console.log("¿a es mayor o igual que b? " + resultado);   // No


// ===== 5. Menor o igual que (<=) =====

resultado = (a <= b) ? "Sí" : "No";
console.log("¿a es menor o igual que b? " + resultado);   // Sí


// ===== 6. Distinto de (!==) =====

resultado = (a !== b) ? "Sí" : "No";
console.log("¿a es distinto de b? " + resultado);   // Sí

a = 10;
resultado = (a !== b) ? "Sí" : "No";
console.log("¿a es distinto de b? " + resultado);   // No


// ===== 7. El resultado es un boolean =====

const esMayor = a > b;
console.log(esMayor);          // false
console.log(a >= b);           // true


// ===== 8. Comparar textos =====

// En JavaScript === compara el contenido de los strings
const nombre = "Sol";
console.log(nombre === "Sol");   // true
console.log(nombre === "sol");   // false: distingue mayúsculas y minúsculas


// ===== 9. == frente a === =====

// == convierte los tipos antes de comparar (coerción); === no
console.log(10 == "10");    // true: el texto se convierte a número
console.log(10 === "10");   // false: tipos distintos
