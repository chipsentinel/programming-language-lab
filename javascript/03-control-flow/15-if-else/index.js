// ===== 1. if: ejecutar código solo si se cumple una condición =====

// Una condicional ejecuta un bloque de código u otro según se cumpla o no una condición.
// La condición va entre paréntesis; el bloque va entre llaves.

const edad = 20;
if (edad > 18) {
    console.log("Es mayor");
}


// ===== 2. if y else =====

// Si la condición no se cumple y no hay else, no pasa nada.
// else define qué hacer en ese caso.

if (edad < 18) {
    console.log("Es menor");
} else {
    console.log("No es menor");
}


// ===== 3. else if: varias condiciones =====

console.log("Nota final del alumno");

let nota = 80;

// Se evalúan en orden; en cuanto una se cumple, el resto se salta
if (nota >= 70) {
    console.log("Felicidades, pasaste el examen con: " + nota);
} else if (nota < 70 && nota >= 50) {
    console.log("Debes repetir el examen, sacaste: " + nota);
} else {
    console.log("Has reprobado el examen, sacaste: " + nota);
}


// ===== 4. El mismo código con otros valores =====

nota = 65;

if (nota >= 70) {
    console.log("Felicidades, pasaste el examen con: " + nota);
} else if (nota < 70 && nota >= 50) {
    console.log("Debes repetir el examen, sacaste: " + nota);
} else {
    console.log("Has reprobado el examen, sacaste: " + nota);
}

nota = 40;

if (nota >= 70) {
    console.log("Felicidades, pasaste el examen con: " + nota);
} else if (nota < 70 && nota >= 50) {
    console.log("Debes repetir el examen, sacaste: " + nota);
} else {
    console.log("Has reprobado el examen, sacaste: " + nota);
}


// ===== 5. Cuidado con los límites =====

// Con nota > 70 y nota < 70, el valor 70 no entra en ninguna condición y cae en el else.
// Por eso se usa >= en uno de los dos lados.
nota = 70;

if (nota > 70) {
    console.log("Con >: pasaste");
} else {
    console.log("Con >: 70 no pasa, cae en el else");
}

if (nota >= 70) {
    console.log("Con >=: pasaste");
}


// ===== 6. Un else if no necesita repetir el límite superior =====

// Como el primer if ya descartó nota >= 70, aquí nota ya es menor que 70
nota = 65;

if (nota >= 70) {
    console.log("Aprobado");
} else if (nota >= 50) {
    console.log("Recuperación");
} else {
    console.log("Suspenso");
}


// ===== 7. Con textos =====

const nombre = "Sol";

if (nombre === "Sol") {
    console.log("Hola, Sol");
} else {
    console.log("Hola, desconocido");
}


// ===== 8. La condición no tiene por qué ser un boolean =====

// JavaScript convierte la condición a verdadero o falso (valores "truthy" y "falsy")
const texto = "";

if (texto) {
    console.log("Hay texto");
} else {
    console.log("El texto está vacío");   // "" cuenta como falso
}
