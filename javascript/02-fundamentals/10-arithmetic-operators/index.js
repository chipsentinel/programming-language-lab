// ===== 1. Declarar varias variables en una línea =====

// Se separan con comas; cada una lleva su propio valor
const valor1 = 90, valor2 = 70, valor3 = 10;


// ===== 2. Suma (+) =====

const total = valor1 + valor2;
console.log("Total de la suma: " + total);   // 160

// Con un texto a un lado, + concatena; con dos números, suma
console.log(valor1 + valor2);                // 160
console.log("Suma: " + valor1 + valor2);     // Suma: 9070 (concatena)
console.log("Suma: " + (valor1 + valor2));   // Suma: 160 (los paréntesis fuerzan la suma)


// ===== 3. Resta (-) =====

const diferencia = valor1 - valor2;
console.log("Total de la resta: " + diferencia);   // 20


// ===== 4. Multiplicación (*) =====

const producto = valor3 * valor2;
console.log("Producto: " + producto);   // 700


// ===== 5. División (/) =====

const resultado = valor1 / valor3;
console.log("Resultado: " + resultado);   // 9

// No hay división entera: 90 / 20 conserva los decimales
console.log(valor1 / 20);                  // 4.5
console.log(Math.trunc(valor1 / 20));      // 4: para quedarse con la parte entera


// ===== 6. Residuo o módulo (%) =====

const modulo = valor2 % valor3;
console.log("Residuo: " + modulo);   // 0

console.log(valor1 % 20);            // 10: 90 entre 20 sobra 10


// ===== 7. División entre cero =====

// No lanza ninguna excepción: da infinito
console.log(valor1 / 0);             // Infinity
console.log(0 / 0);                  // NaN


// ===== 8. Con decimales =====

const decimalValor = 2.3;
console.log("División: " + valor2 / decimalValor);   // muchos decimales
console.log("Residuo: " + valor2 % decimalValor);     // también decimales


// ===== 9. Potencia (**) =====

// JavaScript sí tiene operador de potencia; C# y Java usan Math.Pow / Math.pow
console.log(2 ** 3);                 // 8
