// Un constructor es un método especial que se ejecuta automáticamente al crear un objeto (new).
// Sirve para dar valores iniciales a los atributos y preparar el objeto.


// ===== 1. Constructor por defecto =====

// Si una clase no define ningún constructor, JavaScript crea uno vacío por su cuenta.
// Por eso hasta ahora new Persona() funcionaba sin que escribiéramos nada.
class Contador {
    valor = 10;
}

const contador1 = new Contador();
console.log("Valor: " + contador1.valor);   // 10


// ===== 2. Constructor propio =====

class Persona {
    nombre = "";
    edad;

    // Reglas del constructor:
    // 1. Siempre se llama constructor (no lleva el nombre de la clase, a diferencia de C# y Java).
    // 2. No tiene tipo de retorno.
    // 3. Se ejecuta una sola vez por objeto, al hacer new.
    // 4. Solo puede haber UNO por clase: no existe la sobrecarga.

    // Para ofrecer valores por defecto se usan parámetros opcionales
    constructor(elNombre = "Sol", laEdad = 34) {
        this.nombre = elNombre;   // this. distingue el atributo del parámetro (lección 31)
        this.edad = laEdad;
        console.log(`Creando persona: ${this.nombre}, ${this.edad}`);
    }
}


// new llama al constructor: el mensaje sale en ese momento
const persona1 = new Persona();   // usa los valores por defecto
console.log(`Nombre: ${persona1.nombre}, edad: ${persona1.edad}`);

const persona2 = new Persona("Marcos", 28);
console.log(`Nombre: ${persona2.nombre}, edad: ${persona2.edad}`);


// ===== 3. Se ejecuta una vez por cada objeto =====

// Cada new ejecuta el constructor de nuevo
const persona3 = new Persona("Lucía", 8);
const persona4 = new Persona();


// ===== 4. El constructor no se puede volver a llamar =====

// persona1.constructor("Ana", 1);   // TypeError: el constructor solo se ejecuta con new


// ===== 5. Sin new no hay objeto =====

// Una clase no se puede llamar como una función normal:
// const persona5 = Persona();   // TypeError: Class constructor Persona cannot be invoked without 'new'
