// Tipos de datos básicos

// Números (JavaScript solo tiene number y bigint)
let nivel = 255;
let anio = 2025;
let edad = 25;
let poblacion = 8000000000n;

// Decimales
let temperatura = 36.6;
let pi = 3.14159;
let precio = 19.99;

// Lógico
let esMayorDeEdad = true;

// Texto
let letra = "S";
let nombre = "Sol";

// Mostrar valores
console.log("\nMostrar valores".toUpperCase());
console.log(`number nivel = ${nivel}`);
console.log(`number anio = ${anio}`);
console.log(`number edad = ${edad}`);
console.log(`bigint poblacion = ${poblacion}`);
console.log(`number temperatura = ${temperatura}`);
console.log(`number pi = ${pi}`);
console.log(`number precio = ${precio}`);
console.log(`boolean esMayorDeEdad = ${esMayorDeEdad}`);
console.log(`string letra = ${letra}`);
console.log(`string nombre = ${nombre}`);

// Mostrar el tipo y los límites
console.log("\nMostrar el tipo y los límites".toUpperCase());
console.log(`Tipo de nivel: ${typeof nivel}`);
console.log(`Tipo de anio: ${typeof anio}`);
console.log(`Tipo de edad: ${typeof edad}`);
console.log(`Tipo de poblacion: ${typeof poblacion}`);
console.log(`Tipo de temperatura: ${typeof temperatura}`);
console.log(`Tipo de pi: ${typeof pi}`);
console.log(`Tipo de precio: ${typeof precio}`);
console.log(`Tipo de esMayorDeEdad: ${typeof esMayorDeEdad}`);
console.log(`Tipo de letra: ${typeof letra}`);
console.log(`Tipo de nombre: ${typeof nombre}`);

console.log("\nValor mínimo a valor máximo".toUpperCase());
console.log(`entero seguro: ${Number.MIN_SAFE_INTEGER} a ${Number.MAX_SAFE_INTEGER}`);
console.log(`number:        ${-Number.MAX_VALUE} a ${Number.MAX_VALUE}`);
