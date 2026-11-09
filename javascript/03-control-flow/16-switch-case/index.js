// ===== 1. switch: comparar una variable con varios casos =====

// switch compara el valor de una variable con cada case; ejecuta el que coincide.
// Es una alternativa más clara que una cadena larga de else if con la misma variable.

console.log("Días de la semana");

// En los nombres de variables no se usan tildes ni la ñ (dia, no día)
let dia = 3;

switch (dia) {
    case 1:
        console.log("Lunes");
        break;
    case 2:
        console.log("Martes");
        break;
    case 3:
        console.log("Miércoles");
        break;
    case 4:
        console.log("Jueves");
        break;
    case 5:
        console.log("Viernes");
        break;
    case 6:
        console.log("Sábado");
        break;
    case 7:
        console.log("Domingo");
        break;
}

// break termina el switch: sin él, el programa no pararía en este case


// ===== 2. Cambiar el valor =====

dia = 7;

switch (dia) {
    case 1:
        console.log("Lunes");
        break;
    case 7:
        console.log("Domingo");
        break;
}


// ===== 3. default: ningún case coincide =====

dia = 9;

switch (dia) {
    case 1:
        console.log("Lunes");
        break;
    case 2:
        console.log("Martes");
        break;
    default:
        console.log("Día no válido");   // equivale al else final
        break;
}


// ===== 4. Varios case con el mismo código =====

dia = 6;

switch (dia) {
    case 6:
    case 7:
        console.log("Fin de semana");
        break;
    case 1:
    case 2:
    case 3:
    case 4:
    case 5:
        console.log("Día laborable");
        break;
    default:
        console.log("Día no válido");
        break;
}


// ===== 5. switch con textos =====

const opcion = "guardar";

switch (opcion) {
    case "abrir":
        console.log("Abriendo el archivo");
        break;
    case "guardar":
        console.log("Guardando el archivo");
        break;
    default:
        console.log("Opción desconocida");
        break;
}


// ===== 6. JavaScript sí permite pasar de un case al siguiente =====

// Sin break, el programa sigue ejecutando los case siguientes (fall-through).
// Es un error frecuente; C# no lo permite.
dia = 1;

switch (dia) {
    case 1:
        console.log("Lunes");      // se ejecuta
    case 2:
        console.log("Martes");     // también, aunque dia no vale 2
        break;
}


// ===== 7. switch compara con === =====

// No convierte tipos: el texto "3" no coincide con el número 3
const valor = "3";

switch (valor) {
    case 3:
        console.log("Es el número 3");
        break;
    default:
        console.log('"3" es un texto, no coincide con el número 3');
        break;
}
