// En JavaScript un miembro es privado si su nombre empieza por # (campos y métodos privados).
// Solo es accesible DENTRO de la clase: fuera de ella da un error de sintaxis.
// Es lo habitual para los atributos: se protegen y se ofrece acceso controlado mediante métodos.


// ===== 1. Atributos privados con métodos de acceso =====

class Camion {

    // #: privado. Hay que declararlos en la clase (no se pueden crear "al vuelo")
    #marca;
    #modelo;
    #color;
    #cargaMaxima;

    constructor(marca, modelo, color, cargaMaxima) {
        this.#marca = marca;
        this.#modelo = modelo;
        this.#color = color;
        this.#cargaMaxima = cargaMaxima;
    }

    // Método "getter": permite LEER el atributo sin dejar modificarlo
    obtenerMarca() {
        return this.#marca;
    }

    obtenerCargaMaxima() {
        return this.#cargaMaxima;
    }

    // Método "setter": permite MODIFICAR el atributo, pero validando el valor
    cambiarCargaMaxima(nuevaCarga) {
        if (nuevaCarga <= 0) {
            console.log("Carga inválida: debe ser mayor que cero");
            return;
        }

        this.#cargaMaxima = nuevaCarga;
        console.log(`Carga máxima cambiada a ${this.#cargaMaxima}`);
    }

    // ===== 2. Métodos privados: ayudantes internos =====

    // Un método con # solo se puede llamar desde otros métodos de la misma clase
    #descripcion() {
        return `${this.#marca} ${this.#modelo} (${this.#color})`;
    }

    mostrar() {
        console.log(`${this.#descripcion()}: carga máxima ${this.#cargaMaxima} kg`);
    }
}


// ===== 3. get y set: propiedades =====

class Furgoneta {
    #carga = 500;

    // get y set se usan como un atributo, pero ejecutan código al leer y al escribir
    get carga() {
        return this.#carga;
    }

    set carga(valor) {
        if (valor <= 0) {
            console.log("Carga inválida: debe ser mayor que cero");
            return;
        }

        this.#carga = valor;
    }
}


const camion1 = new Camion("Mack", "MK12", "rojo", 18000);

// Los miembros privados no se ven desde fuera:
// console.log(camion1.#marca);          // SyntaxError: campo privado fuera de la clase
// camion1.#cargaMaxima = -500;          // SyntaxError

console.log("Marca: " + camion1.obtenerMarca());

camion1.cambiarCargaMaxima(-500);     // rechazado por la validación
camion1.cambiarCargaMaxima(20000);    // aceptado

camion1.mostrar();


// ===== Propiedad get / set =====

const furgoneta1 = new Furgoneta();
furgoneta1.carga = -5;        // rechazado
furgoneta1.carga = 1000;      // aceptado
console.log("Carga de la furgoneta: " + furgoneta1.carga);


// ===== 4. El guion bajo no protege =====

// Antes de #, se usaba _nombre por convención: es solo una señal, el atributo sigue siendo accesible
class Antigua {
    _saldo = 100;
}
const antigua = new Antigua();
antigua._saldo = -999;
console.log("Saldo con _ (sin protección): " + antigua._saldo);
