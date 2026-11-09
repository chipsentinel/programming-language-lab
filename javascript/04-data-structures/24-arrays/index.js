// Un array es una estructura que guarda varios valores en una sola variable.
// Cada valor (elemento) tiene una posición, y la primera posición es la 0.
// En JavaScript un array puede mezclar tipos y cambiar de tamaño.


// ===== 1. Declarar un array =====

// new Array(cantidad) crea una lista de huecos vacíos; lo habitual es usar corchetes []
const enteros = new Array(5);   // 5 huecos sin valor
const nombres = new Array(5);
const precios = new Array(5);

// No hay valor por defecto como 0: un hueco vale undefined
console.log("Posición 2 de enteros: " + enteros[2]);   // undefined
console.log("Posición 2 de nombres está vacía: " + (nombres[2] === undefined));   // true


// ===== 2. Guardar y leer valores por posición =====

enteros[0] = 30;
enteros[1] = 6;
enteros[2] = 112;
enteros[3] = 20000;
enteros[4] = 60;

console.log("enteros[2] = " + enteros[2]);   // 112
console.log("enteros[4] = " + enteros[4]);   // 60

// Leer una posición que no existe no da error: devuelve undefined
console.log("enteros[5] = " + enteros[5]);   // undefined

// Y guardar en una posición nueva hace crecer el array (en C# y Java sería un error)
enteros[5] = 1;
console.log("Elementos tras enteros[5] = 1: " + enteros.length);   // 6

// Un array puede mezclar tipos
enteros[0] = "hola";
console.log("enteros[0] = " + enteros[0]);   // hola


// ===== 3. Crear un array con sus valores: corchetes =====

// Sin indicar el tamaño ni las posiciones: se calculan solas, empezando en 0
const numeros = [20, 15, 18, 93, 36, 50, 1, 2, 7, 4];

console.log("numeros[0] = " + numeros[0]);   // 20
console.log("numeros[3] = " + numeros[3]);   // 93

const personas = ["Juan", "Ana", "Antonio", "Samuel", "Elizabeth"];

// const impide reasignar la variable, pero el contenido del array sí se puede cambiar


// ===== 4. Longitud: length =====

console.log("Cantidad de elementos: " + numeros.length);   // 10


// ===== 5. Recorrer un array con for =====

for (let i = 0; i < numeros.length; i++) {
    console.log(`numeros[${i}] = ${numeros[i]}`);
}


// ===== 6. Mostrar todo el array: join =====

console.log("Array original: " + numeros.join(", "));


// ===== 7. Ordenar: sort =====

// Cuidado: sin argumentos, sort ordena como TEXTO ("15" va antes que "2")
const copia = numeros.slice();   // una copia, para no cambiar el original
console.log("sort() sin más, como texto: " + copia.sort().join(", "));

// Para ordenar números hace falta una función de comparación (se verá más adelante)
numeros.sort((a, b) => a - b);   // cambia el propio array, de menor a mayor
console.log("Array organizado: " + numeros.join(", "));

personas.sort();                 // con textos, el orden por defecto ya es el alfabético
console.log("Nombres organizados: " + personas.join(", "));


// ===== 8. Invertir: reverse =====

numeros.reverse();   // da la vuelta al orden actual
console.log("Array al revés: " + numeros.join(", "));


// ===== 9. Buscar: indexOf =====

// Devuelve la posición, o -1 si no está (igual que en string)
console.log("Posición de Luis: " + personas.indexOf("Luis"));       // -1
console.log("Posición de Ana: " + personas.indexOf("Ana"));         // 0
console.log("Posición de Samuel: " + personas.indexOf("Samuel"));   // 4


// ===== 10. Rellenar con un valor: fill =====

numeros.fill(99);
console.log("Array llenado con 99: " + numeros.join(", "));


// ===== 11. Vaciar =====

// Se rellena con el valor "vacío" (aquí, un texto vacío)
personas.fill("");
console.log("Array limpio: " + personas.join(", "));   // solo quedan las comas

// O se quita todo, dejando el array sin elementos
personas.length = 0;
console.log("Elementos tras length = 0: " + personas.length);   // 0


// ===== 12. El tamaño sí cambia =====

// En JavaScript se pueden añadir elementos al final con push
const frutas = ["manzana", "pera"];
frutas.push("uva");
console.log(frutas.join(", "));   // manzana, pera, uva
