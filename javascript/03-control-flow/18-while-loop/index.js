// ===== 1. Bucle while =====

// Un bucle repite un bloque de código mientras se cumpla una condición.
// while (condición) { ... }: se comprueba la condición antes de cada vuelta.

let valor = 0;

while (valor < 3) {
    console.log("Valor es: " + valor);
    valor++;   // sin esta línea valor nunca llegaría a 3 y el bucle sería infinito
}

console.log("Fin del bucle");


// ===== 2. Bucle infinito =====

// Si la condición siempre se cumple, el bucle no termina nunca (se corta con Ctrl+C).
/*
let contador = 0;
while (contador < 3) {
    console.log("Contador es: " + contador);   // falta contador++
}
*/


// ===== 3. Incluir el límite y empezar en 1 =====

let numero = 1;

while (numero <= 3) {   // <= incluye el 3
    console.log("Vuelta " + numero);
    numero++;
}


// ===== 4. Decisiones dentro del bucle =====

numero = 1;

while (numero <= 5) {
    if (numero === 2) {
        console.log("Soy la vuelta dos");
    } else {
        console.log("Vuelta " + numero);
    }

    numero++;
}


// ===== 5. Tabla de multiplicar =====

const TABLA = 8;
numero = 1;

console.log(`Tabla del ${TABLA}`);

while (numero <= 10) {
    console.log(`${TABLA} x ${numero} = ${TABLA * numero}`);
    numero++;
}

console.log("Fin de la tabla");


// ===== 6. while puede no ejecutarse ninguna vez =====

// La condición se comprueba primero: si ya es falsa, el bloque se salta.
let intentos = 5;

while (intentos < 3) {
    console.log("Esto no se imprime nunca");
    intentos++;
}

console.log("Intentos: " + intentos);   // 5


// ===== 7. Acumular un resultado =====

let suma = 0;
numero = 1;

while (numero <= 5) {
    suma += numero;   // suma = suma + numero
    numero++;
}

console.log("Suma de 1 a 5: " + suma);   // 15
