// Programación orientada a objetos (POO): organizar el programa en torno a OBJETOS
// que combinan datos (atributos) y comportamientos (métodos), igual que los objetos del mundo real.
//
// Ejemplo: un auto tiene marca, modelo, color... (atributos) y puede encenderse, acelerar, frenar... (métodos).


// ===== 1. La clase: la plantilla del objeto =====

class Auto {

    // Atributos: las características del auto
    marca;
    modelo;
    color;
    anio;                 // sin ñ en los nombres: anio
    velocidadMaxima;

    // Atributos de estado: cambian mientras el auto se usa
    encendido = false;
    velocidadActual = 0;

    // ===== 2. El constructor: inicializa el objeto =====

    constructor(marca, modelo, color, anio, velocidadMaxima) {
        this.marca = marca;
        this.modelo = modelo;
        this.color = color;
        this.anio = anio;
        this.velocidadMaxima = velocidadMaxima;
    }

    // ===== 3. Métodos: lo que el auto puede hacer =====

    // Método sin retorno que muestra la información del objeto
    mostrarInfo() {
        console.log("=== Información del auto ===");
        console.log(`Marca: ${this.marca}`);
        console.log(`Modelo: ${this.modelo}`);
        console.log(`Color: ${this.color}`);
        console.log(`Año: ${this.anio}`);
        console.log(`Velocidad máxima: ${this.velocidadMaxima} km/h`);
    }

    // Métodos que cambian el estado del objeto
    encender() {
        this.encendido = true;
        console.log("Auto encendido");
    }

    apagar() {
        this.encendido = false;
        this.velocidadActual = 0;
        console.log("Auto apagado");
    }

    acelerar() {
        // El comportamiento depende del estado actual del objeto
        if (!this.encendido) {
            console.log("Auto apagado, no se puede acelerar");
            return;
        }

        this.velocidadActual = Math.min(this.velocidadActual + 10, this.velocidadMaxima);
        console.log(`Auto acelerando. Velocidad actual: ${this.velocidadActual} km/h`);
    }

    frenar() {
        this.velocidadActual = 0;
        console.log("Auto detenido");
    }

    // Métodos con retorno: devuelven información sobre el objeto
    estaEncendido() {
        return this.encendido;
    }

    describir() {
        return `${this.marca} ${this.modelo} (${this.color}, ${this.anio})`;
    }
}


// ===== 4. Crear objetos: instanciar la clase =====

const auto1 = new Auto("Toyota", "RAV4", "negro", 2025, 250);
auto1.mostrarInfo();


// ===== 5. Usar los métodos del objeto =====

auto1.acelerar();    // apagado: no se puede
auto1.encender();
auto1.acelerar();
auto1.acelerar();
console.log("Velocidad: " + auto1.velocidadActual + " km/h");   // 20

auto1.frenar();
auto1.apagar();
console.log(auto1.describir());


// ===== 6. Cada objeto tiene su propio estado =====

const auto2 = new Auto("Ford", "Fiesta", "rojo", 2018, 180);
auto2.encender();

console.log(auto2.describir());
console.log("¿Auto 1 encendido? " + auto1.estaEncendido());   // false
console.log("¿Auto 2 encendido? " + auto2.estaEncendido());   // true
