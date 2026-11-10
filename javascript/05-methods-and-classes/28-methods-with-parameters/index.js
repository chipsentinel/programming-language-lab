const readline = require("readline/promises");

// Un parámetro es un dato que un método recibe al ser llamado.
// Así el mismo método puede trabajar con valores distintos sin cambiar su código.
// Los valores concretos que se pasan al llamarlo se llaman argumentos.


class Prueba {

    // ===== 1. Un atributo de la clase se puede usar en todos sus métodos =====

    nombre = "Sol";

    saludoConAtributo() {
        console.log("Hola, " + this.nombre);   // usa el atributo de la clase: con this.
    }

    // Problema: el saludo siempre es para "Sol". Con un parámetro cada llamada puede usar otro nombre.


    // ===== 2. Método con un parámetro =====

    // Entre paréntesis se declara cada parámetro: solo el nombre, sin tipo
    saludar(nombre) {
        console.log("Hola, " + nombre);
    }


    // ===== 3. Método con varios parámetros =====

    // Se separan con comas
    presentar(nombre, edad) {
        console.log(`Nombre: ${nombre}, edad: ${edad}`);
    }


    // ===== 4. Parámetros y retorno =====

    sumar(a, b) {
        return a + b;
    }

    esMayorDeEdad(edad) {
        return edad >= 18;
    }


    // ===== 5. Parámetro opcional (valor por defecto) =====

    // Si al llamar no se pasa el argumento, se usa el valor por defecto
    despedir(nombre, mensaje = "Adiós") {
        console.log(`${mensaje}, ${nombre}`);
    }
}


const objeto1 = new Prueba();

objeto1.saludoConAtributo();           // Hola, Sol

// Se pasa un argumento por cada parámetro, en el mismo orden
objeto1.saludar("Sol");                // Hola, Sol
objeto1.saludar("Marcos");             // Hola, Marcos

objeto1.presentar("Sol", 34);          // Nombre: Sol, edad: 34

// JavaScript no comprueba ni la cantidad ni el tipo de los argumentos:
objeto1.presentar("Sol");              // Nombre: Sol, edad: undefined (falta un argumento)
objeto1.presentar(34, "Sol");          // Nombre: 34, edad: Sol (no hay error, pero está mal)


// ===== 6. Usar el valor devuelto =====

const resultado = objeto1.sumar(34, 22);
console.log("Suma: " + resultado);                      // 56

// Los argumentos pueden ser variables o expresiones
console.log("Suma: " + objeto1.sumar(resultado, 4));    // 60
console.log("Mayor de edad (20): " + objeto1.esMayorDeEdad(20));   // true
console.log("Mayor de edad (15): " + objeto1.esMayorDeEdad(15));   // false

// Cuidado: sin tipos, sumar("34", 22) concatena en lugar de sumar
console.log("sumar('34', 22): " + objeto1.sumar("34", 22));   // 3422


// ===== 7. Parámetro opcional =====

objeto1.despedir("Sol");                    // Adiós, Sol
objeto1.despedir("Sol", "Hasta pronto");    // Hasta pronto, Sol


// ===== 8. Un parámetro solo existe dentro de su método =====

// El parámetro nombre de saludar no existe fuera de él:
// console.log(nombre);   // ReferenceError: nombre no está definido


// ===== 9. Argumentos que escribe el usuario =====

// Leer del teclado es asíncrono: se hace dentro de una función async (lección 11)
async function main() {
    const rl = readline.createInterface({
        input: process.stdin,
        output: process.stdout,
    });

    const nombreUsuario = await rl.question("Ingrese su nombre...\n");
    const edadUsuario = Number(await rl.question("Ingrese su edad...\n"));

    objeto1.presentar(nombreUsuario, edadUsuario);
    console.log("Suma de la edad y 10: " + objeto1.sumar(edadUsuario, 10));

    rl.close();
}

main();
