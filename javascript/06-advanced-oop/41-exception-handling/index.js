// Una excepción es un error que ocurre MIENTRAS el programa se ejecuta.
// Si no se controla, interrumpe el programa. Con try / catch se captura y se decide qué hacer.
//
// try      { código que puede fallar }
// catch    { qué hacer si falla }
// finally  { código que se ejecuta SIEMPRE, haya error o no }
//
// DIFERENCIA CLAVE: JavaScript lanza muy pocas excepciones por sí solo.
//   - 20 / 0 da Infinity, no una excepción.
//   - Number("T") da NaN, no una excepción.
//   - numeros[5] da undefined, no una excepción.
// Por eso hay que comprobar los casos problemáticos y lanzar la excepción uno mismo con throw.


class ManejoExcepciones {

    // ===== 1. try / catch =====

    dividir(valor1, valor2) {
        try {
            // JavaScript no falla al dividir entre cero: se comprueba y se lanza el error
            if (valor2 === 0) {
                throw new RangeError("división entre cero");
            }

            const resultado = valor1 / valor2;
            console.log("Resultado: " + resultado);
        } catch (error) {
            console.log("Error: no se puede dividir entre cero");
            // error contiene los detalles: tipo (name), mensaje (message) y pila (stack)
            console.log("[técnico] " + error.name + ": " + error.message);
        }
    }


    // ===== 2. Error de formato =====

    formato(texto) {
        try {
            const numero = Number(texto);          // Number("T") no falla: da NaN

            if (Number.isNaN(numero)) {
                throw new TypeError("formato incorrecto");
            }

            console.log("Número ingresado: " + numero);
        } catch (error) {
            console.log(`Error: '${texto}' no es un número entero`);
            console.log("[técnico] " + error.name);
        }
    }


    // ===== 3. Posición fuera del array =====

    arrayError() {
        const numeros = [10, 20, 30, 40];       // posiciones válidas: 0 a 3

        try {
            console.log(numeros.join(", "));

            // numeros[5] no falla: da undefined
            if (5 >= numeros.length) {
                throw new RangeError("posición fuera del rango");
            }
            console.log(numeros[5]);
        } catch (error) {
            console.log("Error: posición fuera del rango del array");
            console.log("[técnico] " + error.name);
        }
    }


    // ===== 4. Varios tipos de error y finally =====

    procesar(texto, divisor) {
        try {
            const numero = Number(texto);

            if (Number.isNaN(numero)) {
                throw new TypeError("formato incorrecto");
            }
            if (divisor === 0) {
                throw new RangeError("división entre cero");
            }

            console.log(`${numero} / ${divisor} = ${numero / divisor}`);
        } catch (error) {
            // Solo hay UN catch: el tipo se distingue dentro con instanceof
            if (error instanceof TypeError) {
                console.log("Error de formato");
            } else if (error instanceof RangeError) {
                console.log("Error: división entre cero");
            } else {
                console.log("Error inesperado: " + error.message);
            }
        } finally {
            // Se ejecuta siempre: ideal para cerrar archivos, conexiones...
            console.log("Fin de la operación");
        }
    }


    // ===== 5. Lanzar una excepción propia: throw =====

    validarEdad(edad) {
        if (edad < 0) {
            throw new RangeError("La edad no puede ser negativa");
        }

        console.log("Edad válida: " + edad);
    }
}


const log = new ManejoExcepciones();

log.dividir(20, 10);
log.dividir(20, 0);

log.formato("8");
log.formato("T");

log.arrayError();

log.procesar("100", 5);
log.procesar("abc", 5);
log.procesar("100", 0);

try {
    log.validarEdad(30);
    log.validarEdad(-5);
} catch (error) {
    console.log("Error: " + error.message);
}

// Una excepción sin controlar detiene el programa:
// throw new Error("sin controlar");   // Uncaught Error: sin controlar


// ===== 6. Lo que NO falla en JavaScript =====

console.log(20 / 0);            // Infinity
console.log(Number("T"));       // NaN
console.log([1, 2, 3][5]);      // undefined
