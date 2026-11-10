// JavaScript NO tiene la palabra clave abstract. Una clase abstracta se imita a mano:
//   - Para impedir instanciarla: se comprueba new.target en el constructor.
//   - Para obligar a las hijas a implementar un método: la versión del padre lanza un error.
// A diferencia de C# y Java, el compilador no avisa: los errores aparecen al EJECUTAR.


// ===== 1. La "clase abstracta" =====

class Animal {
    nombre;

    constructor(nombre) {
        // new.target es la clase con la que se hizo new: si es Animal, se está instanciando directamente
        if (new.target === Animal) {
            throw new TypeError("No se puede instanciar la clase abstracta Animal");
        }

        this.nombre = nombre;
    }

    // Método normal: lo heredan las hijas tal cual
    comer() {
        console.log(`${this.nombre} está comiendo`);
    }

    // Método "abstracto": las hijas deben redefinirlo; si no, falla al llamarlo
    hacerSonido() {
        throw new Error(`${this.constructor.name} debe implementar hacerSonido()`);
    }

    // Método con una versión por defecto que las hijas pueden redefinir
    dormir() {
        console.log(`${this.nombre} está durmiendo`);
    }
}


// ===== 2. Las hijas implementan lo abstracto =====

class Perro extends Animal {
    // Sin constructor propio: usa el de Animal

    // Redefinir un método del padre: se escribe con el mismo nombre (no hay override)
    hacerSonido() {
        console.log(`${this.nombre} está ladrando`);
    }
}

class Gato extends Animal {
    hacerSonido() {
        console.log(`${this.nombre} está maullando`);
    }

    // Opcional: cambia la versión por defecto
    dormir() {
        console.log(`${this.nombre} duerme 16 horas`);
    }
}

class Vaca extends Animal {
    hacerSonido() {
        console.log(`${this.nombre} está mugiendo`);
    }
    // No redefine dormir: usa el de Animal
}


// ===== 3. No se puede instanciar la clase abstracta =====

// const animal = new Animal("Genérico");   // TypeError (lo lanza el constructor)

const miPerro = new Perro("Snoopy");
const miGato = new Gato("Garfield");


// ===== 4. Usar las clases hijas =====

miPerro.comer();          // heredado de Animal
miPerro.hacerSonido();    // implementado en Perro

miGato.comer();
miGato.hacerSonido();     // implementado en Gato: es distinto


// ===== 5. Polimorfismo =====

// Todos son Animal. Al llamar a hacerSonido, se ejecuta la versión de la clase REAL de cada objeto.
// (En JavaScript el polimorfismo no necesita una clase común: basta con que tengan el mismo método)
const animales = [miPerro, miGato, new Vaca("Lola")];

console.log("Animales: " + animales.length);

for (const animal of animales) {
    animal.hacerSonido();
}

for (const animal of animales) {
    animal.dormir();      // Gato lo redefine, los demás usan el de Animal
}


// ===== 6. Lo que en C# y Java es un error de compilación, aquí falla al ejecutar =====

try {
    new Animal("Genérico");
} catch (error) {
    console.log("Error: " + error.message);
}

class Pez extends Animal { }      // se olvidó implementar hacerSonido

try {
    new Pez("Nemo").hacerSonido();
} catch (error) {
    console.log("Error: " + error.message);
}
