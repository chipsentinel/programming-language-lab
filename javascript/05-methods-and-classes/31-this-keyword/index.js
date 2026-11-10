// this es una referencia al objeto actual: el objeto sobre el que se está ejecutando el método.
// En JavaScript es OBLIGATORIO para llegar a los atributos y métodos del objeto
// (en C# y Java solo hace falta cuando un parámetro tiene el mismo nombre que un atributo).


// ===== 1. El problema: parámetro y atributo con el mismo nombre =====

class SinThis {
    nombre = "";
    edad = 0;

    constructor(nombre, edad) {
        // Dentro del constructor, "nombre" es el PARÁMETRO (oculta al atributo):
        // se asigna el parámetro a sí mismo y el atributo no cambia
        nombre = nombre;
        edad = edad;
    }
}


// ===== 2. La solución: this =====

class Persona {
    nombre = "";
    edad = 0;

    // this.nombre es el atributo del objeto; nombre es el parámetro
    // (JavaScript solo permite un constructor: los valores por defecto hacen de segundo constructor)
    constructor(nombre = "Sol", edad = 34) {
        this.nombre = nombre;
        this.edad = edad;
    }

    mostrar() {
        // Sin this no se encuentra el atributo: aquí es obligatorio, no opcional
        console.log(`Nombre: ${this.nombre}, edad: ${this.edad}`);
    }

    // ===== 3. this como valor: el propio objeto =====

    // this es el objeto que recibe la llamada; otra es el que se pasa como argumento
    esMayorQue(otra) {
        return this.edad > otra.edad;
    }

    // ===== 4. Devolver this para encadenar llamadas =====

    cumplirAnios() {
        this.edad++;
        return this;   // devuelve el mismo objeto, y se puede llamar a otro método sobre él
    }
}


// Sin this no se asigna nada
const malo = new SinThis("Bart", 10);
console.log(`Sin this: nombre = '${malo.nombre}', edad = ${malo.edad}`);

// Con this sí
const persona1 = new Persona("Bart", 10);
persona1.mostrar();

// this como valor
const persona2 = new Persona("Lisa", 8);
console.log(`¿Bart es mayor que Lisa? ${persona1.esMayorQue(persona2)}`);   // true
console.log(`¿Lisa es mayor que Bart? ${persona2.esMayorQue(persona1)}`);   // false

// Sin argumentos: valores por defecto (en C# y Java se encadenaría un constructor con this(...))
const persona3 = new Persona();
persona3.mostrar();

// Llamadas encadenadas
persona1.cumplirAnios().cumplirAnios();
persona1.mostrar();   // edad 12


// ===== 5. this cambia según cómo se llama la función =====

// Un método separado del objeto pierde su this
const mostrarSuelto = persona1.mostrar;
// mostrarSuelto();   // TypeError: this es undefined dentro de la clase

// bind fija el this
const mostrarFijo = persona1.mostrar.bind(persona1);
mostrarFijo();        // Nombre: Bart, edad: 12
