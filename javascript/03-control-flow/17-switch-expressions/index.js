// ===== 1. JavaScript no tiene expresión switch =====

// switch es solo una instrucción: ejecuta código, pero no devuelve un valor.
// Para obtener algo parecido se asigna el valor dentro de cada case,
// o se usa el operador ternario encadenado.


// ===== 2. switch que asigna un valor =====

const dia = 3;
let nombreDia;

switch (dia) {
    case 1: nombreDia = "Lunes"; break;
    case 2: nombreDia = "Martes"; break;
    case 3: nombreDia = "Miércoles"; break;
    case 4: nombreDia = "Jueves"; break;
    case 5: nombreDia = "Viernes"; break;
    case 6: nombreDia = "Sábado"; break;
    case 7: nombreDia = "Domingo"; break;
    default: nombreDia = "Día no válido";
}

console.log(nombreDia);   // Miércoles


// ===== 3. Rangos con switch (true) =====

// Truco habitual: se compara true con cada condición, y gana la primera que es true
let nota = 80;
let resultado;

switch (true) {
    case nota >= 70:
        resultado = "Pasaste el examen";
        break;
    case nota >= 50:
        resultado = "Debes repetir el examen";   // ya es menor que 70: se evalúa en orden
        break;
    default:
        resultado = "Has reprobado el examen";
}

console.log(`Nota ${nota}: ${resultado}`);   // Pasaste el examen

nota = 65;
switch (true) {
    case nota >= 70:
        resultado = "Pasaste el examen";
        break;
    case nota >= 50:
        resultado = "Debes repetir el examen";
        break;
    default:
        resultado = "Has reprobado el examen";
}
console.log(`Nota ${nota}: ${resultado}`);   // Debes repetir el examen

nota = 40;
switch (true) {
    case nota >= 70:
        resultado = "Pasaste el examen";
        break;
    case nota >= 50:
        resultado = "Debes repetir el examen";
        break;
    default:
        resultado = "Has reprobado el examen";
}
console.log(`Nota ${nota}: ${resultado}`);   // Has reprobado el examen


// ===== 4. Rango cerrado =====

nota = 64;

switch (true) {
    case nota >= 80:
        resultado = "Pasaste el examen";
        break;
    case nota >= 60 && nota <= 69:   // entre 60 y 69, ambos incluidos
        resultado = "Repetir examen";
        break;
    default:
        resultado = "Reprobaste el examen";
}

console.log(`Nota ${nota}: ${resultado}`);   // Repetir examen


// ===== 5. Varios valores en un mismo case =====

const dia2 = 6;
let tipoDia;

switch (dia2) {
    case 6:
    case 7:
        tipoDia = "Fin de semana";
        break;
    case 1:
    case 2:
    case 3:
    case 4:
    case 5:
        tipoDia = "Día laborable";
        break;
    default:
        tipoDia = "Día no válido";
}

console.log(tipoDia);   // Fin de semana


// ===== 6. Alternativa: operador ternario encadenado =====

// Devuelve un valor directamente, como una expresión switch
const nota2 = 64;
const resultado2 = nota2 >= 80 ? "Pasaste el examen"
    : nota2 >= 60 ? "Repetir examen"
    : "Reprobaste el examen";

console.log(`Nota ${nota2}: ${resultado2}`);   // Repetir examen


// ===== 7. Alternativa: objeto como tabla =====

// Para valores exactos, un objeto hace de diccionario clave -> valor
const nombres = { 1: "Lunes", 2: "Martes", 3: "Miércoles" };
console.log(nombres[3] ?? "Día no válido");   // Miércoles
console.log(nombres[9] ?? "Día no válido");   // Día no válido
