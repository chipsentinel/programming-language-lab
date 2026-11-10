// JavaScript no tiene un "LINQ" como lenguaje de consultas: sus arrays traen métodos para
// filtrar, ordenar, transformar y calcular, que se encadenan. Es el equivalente de LINQ y de los streams.
// Las expresiones (n) => ... se llaman funciones flecha (lambdas): "dado n, devuelve ...".
//
// Los más importantes: filter (filtrar), map (transformar), sort (ordenar), reduce (calcular un único valor),
// find / some / every (preguntar), slice (cortar). Casi todos DEVUELVEN un array nuevo y se pueden encadenar.

const nombres = ["Ana", "Carlos", "Pedro", "Clara", "Andrés"];


// ===== 1. Filtrar y ordenar: filter, sort =====

// sort() cambia el array original: se ordena una COPIA (el filter ya devuelve un array nuevo)
console.log("Empiezan por C: " + nombres.filter((n) => n.startsWith("C")).sort().join(", "));   // Carlos, Clara
console.log("Empiezan por A: " + nombres.filter((n) => n.startsWith("A")).sort().join(", "));   // Ana, Andrés


// ===== 2. Encadenar =====

const conC = nombres.filter((n) => n.startsWith("C")).sort();
console.log("Con métodos: " + conC.join(", "));


// ===== 3. Con números =====

const numeros = [10, 20, 30, 40, 50, 60, 1];

console.log("Pares: " + numeros.filter((n) => n % 2 === 0).join(", "));        // 10, 20, 30, 40, 50, 60
console.log("Mayores que 25: " + numeros.filter((n) => n > 25).join(", "));    // 30, 40, 50, 60


// ===== 4. Transformar (proyectar): map =====

console.log("Doble de cada uno: " + numeros.map((n) => n * 2).join(", "));     // 20, 40, ..., 2


// ===== 5. Ordenar: sort =====

// Cuidado: sort() sin argumentos ordena como TEXTO. Para números hace falta una función de comparación.
console.log("De mayor a menor: " + [...numeros].sort((a, b) => b - a).join(", "));   // 60, 50, ..., 1


// ===== 6. Cálculos: length, reduce, Math.max, Math.min =====

const suma = numeros.reduce((acumulado, n) => acumulado + n, 0);

console.log("Cantidad: " + numeros.length);                                    // 7
console.log("Suma: " + suma);                                                  // 211
console.log("Media: " + (suma / numeros.length).toFixed(2));                   // 30.14
console.log("Máximo: " + Math.max(...numeros));                                // 60
console.log("Mínimo: " + Math.min(...numeros));                                // 1


// ===== 7. Preguntas: some, every, find =====

console.log("¿Alguno mayor que 50? " + numeros.some((n) => n > 50));           // true
console.log("¿Todos mayores que 0? " + numeros.every((n) => n > 0));           // true
console.log("Primero mayor que 25: " + numeros.find((n) => n > 25));           // 30
console.log("¿Alguno mayor que 100? " + numeros.some((n) => n > 100));         // false


// ===== 8. Seleccionar partes: slice, Set =====

console.log("Los 3 primeros: " + numeros.slice(0, 3).join(", "));              // 10, 20, 30
console.log("Sin los 5 primeros: " + numeros.slice(5).join(", "));             // 60, 1

const repetidos = [1, 2, 2, 3, 3, 3];
console.log("Sin repetidos: " + [...new Set(repetidos)].join(", "));           // 1, 2, 3


// ===== 9. Agrupar: Object.groupBy =====

// Disponible en Node.js 21 o superior. Devuelve un objeto: clave -> array de elementos
const grupos = Object.groupBy(nombres, (n) => n[0]);

for (const [letra, lista] of Object.entries(grupos)) {
    console.log(`${letra}: ${lista.join(", ")}`);
}


// ===== 10. Con objetos =====

const personas = [
    { nombre: "Sol", edad: 34 },
    { nombre: "Marcos", edad: 28 },
    { nombre: "Lucía", edad: 8 },
    { nombre: "Pedro", edad: 17 },
    { nombre: "Marta", edad: 45 },
];

const mayoresDeEdad = personas
    .filter((p) => p.edad >= 18)
    .sort((a, b) => a.edad - b.edad)
    .map((p) => p.nombre);

console.log("Mayores de edad (por edad): " + mayoresDeEdad.join(", "));        // Marcos, Sol, Marta
console.log("Edad media: " + (personas.reduce((total, p) => total + p.edad, 0) / personas.length).toFixed(1));   // 26.4


// ===== 11. No hay ejecución diferida =====

// A diferencia de LINQ, filter se ejecuta AL LLAMARLO y devuelve un array ya calculado
const empiezanPorC = nombres.filter((n) => n.startsWith("C"));
nombres.push("Cecilia");                                  // se añade DESPUÉS...
console.log("No diferida: " + empiezanPorC.join(", "));   // ...y NO aparece: Carlos, Clara


// ===== 12. forEach: recorrer sin devolver nada =====

numeros.slice(0, 2).forEach((n, i) => console.log(`Posición ${i}: ${n}`));
