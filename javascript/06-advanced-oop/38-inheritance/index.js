// Herencia: una clase (hija) puede derivar de otra (padre) y reutilizar sus miembros.
// La hija recibe los atributos y métodos del padre, y puede añadir los suyos.
// Se escribe con extends: class Hija extends Padre
//
// Reglas en JavaScript:
//  - Una clase solo puede extender UNA clase (herencia simple).
//  - Todas las clases derivan, directa o indirectamente, de Object (se hace solo).


// ===== 1. La clase padre: lo que tienen en común todos los animales =====

class Animal {
    nombre;

    constructor(nombre) {
        this.nombre = nombre;
    }

    // Métodos comunes a cualquier animal
    comer() {
        console.log(`${this.nombre} está comiendo`);
    }

    dormir() {
        console.log(`${this.nombre} está durmiendo`);
    }
}


// ===== 2. Las clases hijas: heredan de Animal y añaden lo suyo =====

class Perro extends Animal {

    // super(nombre) llama al constructor del padre, que es quien guarda el nombre.
    // En una clase hija es OBLIGATORIO llamar a super() antes de usar this.
    constructor(nombre) {
        super(nombre);
    }

    // Método propio de los perros
    ladrar() {
        console.log(`${this.nombre} está ladrando`);
    }
}

class Gato extends Animal {

    // Si la hija no necesita un constructor propio, se puede omitir: usa el del padre

    maullar() {
        console.log(`${this.nombre} está maullando`);
    }
}


// ===== 3. La hija usa los miembros del padre =====

const miPerro = new Perro("Snoopy");

miPerro.dormir();    // heredado de Animal
miPerro.comer();     // heredado de Animal
miPerro.ladrar();    // propio de Perro

// También el atributo heredado
miPerro.nombre = "Rex";
miPerro.comer();     // Rex está comiendo


// ===== 4. Cada hija tiene lo suyo =====

const miGato = new Gato("Michi");

miGato.dormir();
miGato.comer();
miGato.maullar();

// Lo propio de una hija no existe en la otra: no da error al escribirlo, falla al ejecutarlo
// miGato.ladrar();   // TypeError: miGato.ladrar is not a function


// ===== 5. Relación "es un" =====

// Un Perro ES UN Animal
const animal = miPerro;
animal.comer();      // Rex está comiendo
console.log("¿Perro es un Animal? " + (miPerro instanceof Animal));   // true
console.log("¿Perro es un Gato? " + (animal instanceof Gato));        // false

// En JavaScript la variable no tiene tipo, así que sí se puede llamar a lo propio de la hija:
animal.ladrar();     // Rex está ladrando (el objeto sigue siendo un Perro)


// ===== 6. La cadena de herencia =====

// Perro -> Animal -> Object
console.log(Object.getPrototypeOf(Perro.prototype) === Animal.prototype);   // true
console.log(Object.getPrototypeOf(Animal.prototype) === Object.prototype);  // true
