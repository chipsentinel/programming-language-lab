// ===== 1. Casting implícito (coerción) =====

// Casting: convertir un valor de un tipo de dato a otro.
// En JavaScript solo existe el tipo number para enteros y decimales,
// así que no hay conversión int -> double.

const valor = 200;
const total = valor;
console.log(`Casting implícito: ${total}`);

// JavaScript convierte tipos solo cuando se mezclan en una operación (coerción)
console.log("5" + 3);   // "53": el número pasa a texto
console.log("5" - 3);   // 2: el texto pasa a número
console.log("5" * "2"); // 10


// ===== 2. Casting explícito =====

// Para quitar los decimales no hay (int): se usa Math.trunc
const precio = 500.23;

const descuento = Math.trunc(precio);   // se descartan los decimales, no se redondea
console.log(`Casting explícito: ${descuento}`);   // 500

// Trunca hacia cero, también con negativos
const temperatura = -7.9;
console.log(Math.trunc(temperatura));   // -7, no -8


// ===== 3. De carácter a número y de número a carácter =====

// No existe el tipo char: una letra es un string de longitud 1
const letra = "A";
const codigoAscii = letra.charCodeAt(0);   // texto -> número: método
console.log(`Código ASCII de ${letra}: ${codigoAscii}`);   // 65

const letraDeVuelta = String.fromCharCode(codigoAscii);   // número -> texto
console.log(`Letra del código ${codigoAscii}: ${letraDeVuelta}`);   // A


// ===== 4. De texto a número: Number y parseInt =====

const palabra = "123456";

const numero = Number(palabra);
console.log(`Texto a entero: ${numero}`);   // 123456

// Si el texto no es un número válido, no falla: devuelve NaN
console.log(Number("hola"));   // NaN


// ===== 5. De texto a decimal: Number y parseFloat =====

const textoDecimal = "150.60";

// JavaScript siempre usa el punto como separador decimal
const valor3 = parseFloat(textoDecimal);
console.log(`Texto a decimal: ${valor3}`);   // 150.6

// parseFloat lee hasta donde encuentra un número; Number exige el texto completo
console.log(parseFloat("150.60abc"));   // 150.6
console.log(Number("150.60abc"));       // NaN


// ===== 6. De número a texto =====

const edad = 30;
const edadTexto = String(edad);
console.log("Edad: " + edadTexto);

const pi = 3.1416;
console.log(pi.toString());   // 3.1416


// ===== 7. Texto que no es un número =====

// JavaScript no lanza error: hay que comprobar si el resultado es NaN
const entrada = "hola";
const resultado = Number(entrada);

if (Number.isNaN(resultado)) {
    console.log(`"${entrada}" no es un número`);
} else {
    console.log(`Convertido: ${resultado}`);
}
