// JavaScript no tiene el modificador protected.
// Hay tres opciones para un miembro que "solo deberían usar la clase y sus hijas":
//
//   1. Público sin más (nombre):  lo ve todo el mundo.
//   2. Convención con guion bajo (_nombre): es una señal, pero sigue siendo accesible desde fuera.
//   3. Privado con # (#nombre):  solo lo ve la propia clase; las hijas tampoco.
//
// Lo habitual para imitar protected es la convención _nombre.


// ===== 1. La clase padre con miembros "protected" por convención =====

class Animal {
    nombre;

    // El guion bajo avisa: "uso interno de la clase y sus hijas"
    _energia = 100;

    constructor(nombre) {
        this.nombre = nombre;
    }

    _respirar() {
        console.log(`${this.nombre} está respirando`);
    }

    dormir() {
        console.log(`${this.nombre} está durmiendo`);
        this._respirar();
    }

    descansar() {
        this._energia = 100;
        console.log(`${this.nombre} descansa. Energía: ${this._energia}`);
    }
}


// ===== 2. Las hijas usan los miembros del padre =====

class Perro extends Animal {

    ladrar() {
        this._respirar();                                   // usado por convención desde la hija
        console.log(`${this.nombre} está ladrando`);
    }

    correr() {
        this._energia -= 30;
        console.log(`${this.nombre} corre. Energía: ${this._energia}`);
    }
}

class Gato extends Animal {

    maullar() {
        this._respirar();
        console.log(`${this.nombre} está maullando`);
    }
}


const miPerro = new Perro("Rex");

miPerro.dormir();       // el método público usa _respirar() internamente
miPerro.ladrar();       // la hija usa _respirar()
miPerro.correr();       // la hija usa _energia
miPerro.descansar();

const miGato = new Gato("Michi");
miGato.maullar();


// ===== 3. El guion bajo no impide el acceso desde fuera =====

miPerro._respirar();                   // funciona: solo es una convención
console.log(miPerro._energia);         // funciona


// ===== 4. Con # ni siquiera la hija puede usarlo =====

class Base {
    #secreto = 42;

    leerSecreto() {
        return this.#secreto;
    }
}

class Derivada extends Base {
    intentarLeer() {
        // return this.#secreto;   // SyntaxError: el campo privado no existe en Derivada
        return this.leerSecreto();  // se accede a través de un método público del padre
    }
}

console.log("Secreto a través del padre: " + new Derivada().intentarLeer());   // 42
