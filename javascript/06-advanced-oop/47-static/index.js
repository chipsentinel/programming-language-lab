// static: el miembro (método, campo) pertenece a la CLASE en sí, no a un objeto concreto.
// Por eso se usa con el nombre de la clase, SIN crear ningún objeto: Clase.miembro
//
// Sirve para:
//   - Llamar a algo sin instanciar la clase (utilidades como Math).
//   - Compartir un dato entre TODOS los objetos de una clase (un contador).
//   - Crear herramientas que no dependen del estado de un objeto.
//
// En JavaScript no existen las "clases static" ni hace falta Main: una función suelta o un objeto
// con funciones ya cumplen ese papel.


// ===== 1. Miembros static de una clase normal =====

class Utilidad {
    // Método static: se llama con Utilidad.saludar(), sin new
    static saludar() {
        console.log("Hola a todos");
    }

    // Campo static: un único valor, perteneciente a la clase
    static total = 5000;

    // Método normal (de instancia): necesita un objeto
    saludarDesdeObjeto() {
        console.log("Hola desde un objeto");
    }
}

Utilidad.saludar();
console.log("Total: " + Utilidad.total);

// Sobre un objeto no se pueden llamar los miembros static: da undefined
// const u = new Utilidad();
// u.saludar();    // TypeError: u.saludar is not a function
new Utilidad().saludarDesdeObjeto();

Utilidad.total = 8000;    // un campo static se puede modificar
console.log("Total tras cambiarlo: " + Utilidad.total);


// ===== 2. Funciones sueltas: el equivalente a una clase static =====

// Un objeto con funciones, o simplemente funciones, sirven de "clase de utilidades"
const Matematica = Object.freeze({
    sumar(a, b) {
        return a + b;
    },
    doble(numero) {
        return numero * 2;
    },
});

console.log("Suma: " + Matematica.sumar(50, 20));
console.log("Doble de 4: " + Matematica.doble(4));

// Math (de JavaScript) es un objeto de este tipo: Math.sqrt(16), Math.PI...
console.log("Raíz de 16: " + Math.sqrt(16));


// ===== 3. Dato compartido =====

class Persona {
    // static: UNA sola copia para toda la clase
    static creadas = 0;

    // Sin static: cada objeto tiene la suya
    nombre;
    numero;

    constructor(nombre) {
        Persona.creadas++;               // se accede con el nombre de la clase
        this.nombre = nombre;
        this.numero = Persona.creadas;
    }

    presentar() {
        console.log(`Soy ${this.nombre} (n.º ${this.numero})`);
    }

    // Un método static solo ve miembros static; dentro de él, this es la propia CLASE
    static mostrarTotal() {
        console.log("Personas creadas: " + this.creadas);   // this.creadas == Persona.creadas
        // console.log(this.nombre);    // undefined: la clase no tiene "nombre" (es de los objetos)
    }
}

Persona.mostrarTotal();

const sol = new Persona("Sol");
const marcos = new Persona("Marcos");
const lucia = new Persona("Lucía");

sol.presentar();
marcos.presentar();
lucia.presentar();

Persona.mostrarTotal();     // el contador es de la clase: lo ven y lo cambian todos los objetos


// ===== 4. Bloque static =====

console.log("Antes de usar Configuracion");

class Configuracion {
    static idioma;

    // Se ejecuta UNA sola vez. OJO: en JavaScript se ejecuta al DEFINIR la clase (aquí mismo),
    // no cuando se usa por primera vez, como en C# y Java
    static {
        console.log("Inicializando configuración (solo una vez)");
        Configuracion.idioma = "es";
    }
}

console.log("Idioma: " + Configuracion.idioma);
console.log("Idioma: " + Configuracion.idioma);     // la segunda vez ya no
