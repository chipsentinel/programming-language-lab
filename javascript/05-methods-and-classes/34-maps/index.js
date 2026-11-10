// Un mapa guarda pares clave-valor: cada valor se asocia a una clave ÚNICA,
// y se accede al valor indicando su clave.
// En JavaScript la clase es Map (los objetos { } también sirven, pero Map está pensado para esto).


// ===== 1. Crear un mapa =====

// No se indica el tipo de las claves ni de los valores: pueden ser de cualquier tipo
const frutas = new Map();


// ===== 2. Añadir pares: set(clave, valor) =====

frutas.set(1, "manzanas");
frutas.set(2, "peras");
frutas.set(3, "bananas");
frutas.set(4, "uvas");
frutas.set(5, "fresas");

console.log("Cantidad: " + frutas.size);   // 5 (size es una propiedad, sin paréntesis)


// ===== 3. Leer por clave: get(clave) =====

console.log("Clave 2: " + frutas.get(2));   // peras

// Pedir una clave que no existe NO da error: devuelve undefined
// console.log(frutas.get(9));   // undefined


// ===== 4. Recorrer: for...of con pares [clave, valor] =====

for (const [clave, valor] of frutas) {
    console.log(`Clave: ${clave}, Valor: ${valor}`);
}


// ===== 5. Comprobar si existe: has =====

console.log("¿Existe la clave 3? " + frutas.has(3));            // true
console.log("¿Existe la clave 9? " + frutas.has(9));            // false
// No hay containsValue: se buscan los valores con includes
console.log("¿Existe el valor uvas? " + [...frutas.values()].includes("uvas"));  // true


// ===== 6. Las claves son únicas =====

// set con una clave que ya existe REEMPLAZA el valor (no da error)...
frutas.set(2, "peras verdes");
console.log("Clave 2 tras cambiarla: " + frutas.get(2));

// ...y crea el par si no existe
frutas.set(6, "kiwis");
console.log("Cantidad: " + frutas.size);   // 6


// ===== 7. Leer sin riesgo =====

const fruta = frutas.get(9);
if (fruta !== undefined) {
    console.log("Clave 9: " + fruta);
} else {
    console.log("La clave 9 no existe");
}


// ===== 8. Quitar un par: delete(clave) =====

frutas.delete(1);
console.log("Cantidad tras delete(1): " + frutas.size);   // 5

// Las claves y los valores por separado
console.log("Claves: " + [...frutas.keys()].join(", "));
console.log("Valores: " + [...frutas.values()].join(", "));


// ===== 9. Vaciar: clear =====

frutas.clear();
console.log("Mapa limpio. Cantidad: " + frutas.size);   // 0


// ===== 10. Claves de texto: el caso más habitual =====

const stock = new Map([
    ["pan", 20],
    ["leche", 12],
    ["huevos", 30],
]);

console.log("Stock de leche: " + stock.get("leche"));   // 12

stock.set("leche", stock.get("leche") - 2);   // se puede modificar el valor de una clave
console.log("Stock de leche tras vender 2: " + stock.get("leche"));   // 10

// Un valor por defecto cuando la clave no existe
console.log("Stock de sal: " + (stock.get("sal") ?? 0));   // 0


// ===== 11. Un objeto literal como diccionario =====

// Para claves de texto, un objeto { } también funciona (las claves son siempre texto)
const precios = { pan: 1.2, leche: 0.95 };
console.log("Precio de la leche: " + precios.leche);      // 0.95
console.log("Precio de la leche: " + precios["leche"]);   // 0.95
precios.huevos = 2.5;                                     // añade un par
console.log(Object.keys(precios).join(", "));             // pan, leche, huevos
