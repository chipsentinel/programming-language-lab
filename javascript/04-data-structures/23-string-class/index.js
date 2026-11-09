// string es un tipo de dato con propiedades y funciones para trabajar con texto.


// ===== 1. Texto en varias líneas =====

// Opción 1: concatenar con + y \n para el salto de línea
const lineasConcatenadas = "Esta es la primera línea\n" +
                           "Esta es la segunda línea\n" +
                           "Esta es la tercera línea";
console.log(lineasConcatenadas);

// Opción 2: plantilla con comillas invertidas (`): respeta los saltos de línea
const lineasLiterales = `Esta es la primera línea
Esta es la segunda línea
Esta es la tercera línea`;
console.log(lineasLiterales);

// Caracteres especiales dentro de un texto (secuencias de escape)
console.log("Comillas: \"mundo\"");     // \"  comillas
console.log("Ruta: C:\\Datos\\foto");   // \\  barra invertida
console.log("Col1\tCol2");              // \t  tabulador


// ===== 2. Longitud: length =====

const texto = "Hola a todos";

console.log("Texto: " + texto);
console.log(`Cantidad de caracteres: ${texto.length}`);   // 12 (los espacios también cuentan); sin paréntesis

// Un texto de solo espacios también tiene longitud
console.log("    ".length);   // 4


// ===== 3. Mayúsculas y minúsculas =====

console.log(`En mayúsculas: ${texto.toUpperCase()}`);   // HOLA A TODOS
console.log(`En minúsculas: ${texto.toLowerCase()}`);   // hola a todos


// ===== 4. Buscar: includes, startsWith, endsWith =====

// Devuelven true o false, y distinguen mayúsculas de minúsculas
console.log(`Contiene "mundo": ${texto.includes("mundo")}`);       // false (aquí se llama includes)
console.log(`Contiene "todos": ${texto.includes("todos")}`);       // true
console.log(`Inicia con "hola": ${texto.startsWith("hola")}`);     // false (minúscula)
console.log(`Inicia con "Hola": ${texto.startsWith("Hola")}`);     // true
console.log(`Finaliza con "todos": ${texto.endsWith("todos")}`);   // true


// ===== 5. Posición: indexOf =====

// Cada carácter tiene un índice, y el primero es el 0:
// H o l a _ a _ t o d o s
// 0 1 2 3 4 5 6 7 8 9 10 11
console.log(`Posición de "do": ${texto.indexOf("do")}`);   // 9
console.log(`Posición de "la": ${texto.indexOf("la")}`);   // 2
console.log(`Posición de "tr": ${texto.indexOf("tr")}`);   // -1: no está

// Un solo carácter, por su índice
console.log(`Primer carácter: ${texto[0]}`);   // H


// ===== 6. Extraer una parte: substring =====

// substring(inicio, fin): desde la posición 2 hasta la 6 (sin incluir la 6)
// OJO: en C# el segundo número es la cantidad; en JavaScript es la posición final
console.log(`substring(2, 6): "${texto.substring(2, 6)}"`);   // "la a"
console.log(`substring(1, 5): "${texto.substring(1, 5)}"`);   // "ola "

// Con un solo número, hasta el final
console.log(`substring(5): "${texto.substring(5)}"`);         // "a todos"

// slice hace lo mismo y además admite negativos (contar desde el final)
console.log(`slice(-5): "${texto.slice(-5)}"`);               // "todos"


// ===== 7. Reemplazar: replace =====

console.log(`Replace: ${texto.replace("a todos", "mundo")}`);   // Hola mundo


// ===== 8. Quitar espacios: trim =====

const conEspacios = "   Hola a todos   ";

console.log(`Con espacios: ${conEspacios.length} caracteres`);          // 18
console.log(`Con trim: ${conEspacios.trim().length} caracteres`);       // 12
console.log(`Entre corchetes: [${conEspacios.trim()}]`);                // [Hola a todos]


// ===== 9. Los strings no cambian =====

// Estas funciones devuelven un texto nuevo; el original sigue igual
texto.toUpperCase();   // el resultado se pierde si no se guarda
console.log(texto);    // Hola a todos

const mayusculas = texto.toUpperCase();   // así sí se conserva
console.log(mayusculas);                  // HOLA A TODOS


// ===== 10. Encadenar funciones =====

// Se aplican de izquierda a derecha, y el resultado final es el de la última
console.log(conEspacios.trim().toUpperCase());   // HOLA A TODOS
