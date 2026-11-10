// Encapsulamiento: ocultar los detalles internos de una clase y controlar quién puede acceder a ella.
//
// JavaScript no tiene los modificadores public / protected / internal de C# y Java.
// Solo hay dos situaciones:
//   - Sin nada: el miembro es PÚBLICO (accesible desde cualquier parte). Es el valor por defecto.
//   - Con # delante del nombre (#saldo): el miembro es PRIVADO (lección siguiente).
// No hace falta escribir "public": todo lo que se declara normalmente ya lo es.


// ===== 1. Una clase con miembros públicos =====

class Camion {

    // Públicos: se pueden leer y cambiar desde cualquier otra parte del programa
    marca;
    modelo;
    color;
    cargaMaxima;

    constructor(marca, modelo, color, cargaMaxima) {
        this.marca = marca;
        this.modelo = modelo;
        this.color = color;
        this.cargaMaxima = cargaMaxima;
    }

    // Un método público se puede llamar desde fuera de la clase
    datosCamion(velocidad, acelera) {
        console.log(`Marca: ${this.marca}`);
        console.log(`Modelo: ${this.modelo}`);
        console.log(`Color: ${this.color}`);
        console.log(`Velocidad: ${velocidad} km/h`);
        console.log(`Aceleración: ${acelera} m/s²`);
    }
}


// ===== 2. Usar una clase desde otra parte del programa =====

const camion1 = new Camion("Mack", "MK12", "rojo", 18000);
camion1.datosCamion(230, 40);


// ===== 3. Cambiar un atributo público directamente =====

camion1.color = "azul";
console.log("Color tras cambiarlo: " + camion1.color);


// ===== 4. El inconveniente: nadie controla los valores =====

// Al ser público, cualquiera puede dejar el objeto en un estado absurdo
camion1.cargaMaxima = -500;
console.log("Carga máxima: " + camion1.cargaMaxima + " (nadie lo impide)");

// En JavaScript ni siquiera hay que declarar el atributo: se puede inventar uno nuevo
camion1.ruedas = 6;
console.log("Ruedas (atributo inventado): " + camion1.ruedas);

// Para poder validar los datos hay que ocultar los atributos y dar acceso controlado:
// eso es lo que permite private (#), en la lección siguiente.
