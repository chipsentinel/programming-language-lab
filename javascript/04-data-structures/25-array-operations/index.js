// Operaciones habituales con arrays: sumar, filtrar, copiar y combinar dos arrays.
// Las flechas (=>) son expresiones lambda: se verán más adelante.


const numeros = [12, 233, 5, 658, 45555];


// ===== 1. Sumar todos los valores: reduce =====

// reduce recorre el array acumulando un resultado: empieza en 0 y va sumando cada x
const total = numeros.reduce((acumulado, x) => acumulado + x, 0);
console.log("Total de la suma de valores: " + total);   // 46463


// ===== 2. Máximo, mínimo y media =====

// Math.max/min no aceptan un array: se "esparce" con ... para pasar cada elemento
console.log("Máximo: " + Math.max(...numeros));   // 45555
console.log("Mínimo: " + Math.min(...numeros));   // 5
console.log("Media: " + total / numeros.length);  // 9292.6


// ===== 3. Filtrar: filter =====

// filter recibe una condición y se queda con los elementos que la cumplen.
// x => x > 12 es una expresión lambda ("para cada x, ¿es x mayor que 12?").
const mayoresQue12 = numeros.filter(x => x > 12);

console.log("Mayores que 12: " + mayoresQue12.join(", "));   // 233, 658, 45555

// filter ya devuelve un array nuevo: no hace falta convertirlo


// ===== 4. Copiar un array =====

// Asignar un array a otra variable NO lo copia: las dos apuntan al mismo array
const alias = numeros;
alias[0] = 999;
console.log("numeros[0] tras cambiar alias[0]: " + numeros[0]);   // 999

numeros[0] = 12;   // se restaura el valor original

// [...numeros] (o numeros.slice()) crea una copia independiente
const cantidades = [...numeros];
cantidades[0] = 1;

console.log("Array numeros: " + numeros.join(", "));         // 12, 233, 5, 658, 45555
console.log("Array cantidades: " + cantidades.join(", "));   // 1, 233, 5, 658, 45555


// ===== 5. Operar dos arrays elemento a elemento =====

const array1 = [10, 20, 30, 40];
const array2 = [1, 2, 3, 4];

// Los dos arrays deben tener la misma cantidad de elementos
const suma = [];
const resta = [];
const producto = [];
const division = [];

for (let i = 0; i < array1.length; i++) {
    suma[i] = array1[i] + array2[i];          // 10 + 1, 20 + 2, ...
    resta[i] = array1[i] - array2[i];
    producto[i] = array1[i] * array2[i];
    division[i] = array1[i] / array2[i];      // aquí no hay división entera
}

console.log("Suma: " + suma.join(", "));             // 11, 22, 33, 44
console.log("Resta: " + resta.join(", "));           // 9, 18, 27, 36
console.log("Producto: " + producto.join(", "));     // 10, 40, 90, 160
console.log("División: " + division.join(", "));     // 10, 10, 10, 10


// ===== 6. Un array no se puede sumar con + =====

// array1 + array2 no suma los elementos: los convierte en texto y los concatena
console.log(array1 + array2);   // "10,20,30,401,2,3,4"
// Hay que recorrer las posiciones, como en el apartado anterior (o usar map).

// map aplica una operación a cada elemento y devuelve un array nuevo
const sumaConMap = array1.map((valor, i) => valor + array2[i]);
console.log("Suma con map: " + sumaConMap.join(", "));   // 11, 22, 33, 44
