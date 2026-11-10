// En JavaScript no existe una clase "lista": el array (lección 24) ya es dinámico
// y cumple la misma función. Aquí se ven sus funciones para añadir, insertar y quitar elementos.


// ===== 1. Crear una lista =====

// Un array vacío; no se indica el tipo de los elementos
const simpsons = [];

console.log("Elementos al crearla: " + simpsons.length);   // 0: empieza vacía


// ===== 2. Añadir elementos: push =====

simpsons.push("Homero");
simpsons.push("Marge");
simpsons.push("Lisa");
simpsons.push("Bart");
simpsons.push("Maggie");

console.log("Cantidad: " + simpsons.length);   // 5


// ===== 3. Recorrer una lista: for...of =====

// for (const variable of colección) recorre cada elemento sin usar índices
for (const nombre of simpsons) {
    console.log(nombre);
}

// También se puede con for, accediendo por posición
for (let i = 0; i < simpsons.length; i++) {
    console.log(`Posición ${i}: ${simpsons[i]}`);
}


// ===== 4. Las listas admiten duplicados =====

simpsons.push("Homero");
console.log("Con duplicado: " + simpsons.join(", "));


// ===== 5. Quitar por valor =====

// No existe remove: se busca la posición con indexOf y se quita con splice(posición, cuántos)
simpsons.splice(simpsons.indexOf("Homero"), 1);   // quita la PRIMERA aparición
console.log("Tras quitar Homero: " + simpsons.join(", "));


// ===== 6. Insertar en una posición: splice(posición, 0, valor) =====

simpsons.splice(0, 0, "Apu");   // en la posición 0, quita 0 elementos e inserta "Apu"
console.log("Tras insertar en 0 a Apu: " + simpsons.join(", "));


// ===== 7. Quitar por posición: splice(posición, 1) =====

simpsons.splice(5, 1);   // la posición 5 es el segundo Homero
console.log("Tras quitar la posición 5: " + simpsons.join(", "));


// ===== 8. Buscar: includes e indexOf =====

console.log("¿Contiene Lisa? " + simpsons.includes("Lisa"));   // true
console.log("¿Contiene Moe? " + simpsons.includes("Moe"));     // false

console.log("Posición de Bart: " + simpsons.indexOf("Bart"));       // 3
console.log("Posición de Nelson: " + simpsons.indexOf("Nelson"));   // -1: no está


// ===== 9. Ordenar e invertir: sort y reverse =====

simpsons.sort();
console.log("Ordenada: " + simpsons.join(", "));

simpsons.reverse();
console.log("Al revés: " + simpsons.join(", "));


// ===== 10. Vaciar =====

simpsons.length = 0;
console.log("Tras vaciar: " + simpsons.length);   // 0


// ===== 11. Una lista de números =====

const valores = [21, 8, 235, 100, 120];   // se crea ya con valores
console.log("Valores: " + valores.join(", "));

// Quitar el VALOR 235: se busca su posición y se quita
valores.splice(valores.indexOf(235), 1);
console.log("Tras quitar el 235: " + valores.join(", "));

valores.splice(1, 1);     // quita lo que hay en la POSICIÓN 1 (el 8)
console.log("Tras quitar la posición 1: " + valores.join(", "));

// sort() ordena como texto: para números hace falta la función de comparación
valores.sort((a, b) => a - b);
valores.reverse();
console.log("De mayor a menor: " + valores.join(", "));

// Un array admite cualquier tipo, incluso mezclados
valores.push("hola");   // válido


// ===== 12. Otras funciones propias de JavaScript =====

// pop quita el último elemento, shift el primero, unshift añade al principio
const cola = ["a", "b", "c"];
cola.pop();            // a, b
cola.shift();          // b
cola.unshift("z");     // z, b
console.log("Cola: " + cola.join(", "));
