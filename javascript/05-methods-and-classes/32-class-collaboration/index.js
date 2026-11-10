// Un programa real tiene varias clases, y colaboran entre sí:
// una clase crea objetos de otra, los guarda como atributos y llama a sus métodos.


// ===== 1. Una clase con sus datos y su método =====

class Cliente {
    nombre;
    email;

    constructor(nombre, email) {
        this.nombre = nombre;
        this.email = email;
    }

    // En JavaScript todo es accesible desde otras clases: no hace falta public
    datos() {
        console.log(`Nombre: ${this.nombre}`);
        console.log(`Email: ${this.email}`);
    }
}


// ===== 2. Una clase que necesita a otra =====

// Una orden de compra pertenece a un cliente: Orden tiene un atributo que es un Cliente
class Orden {
    numero;
    cliente;      // un objeto de otra clase como atributo
    total;

    constructor(numero, cliente, total) {
        this.numero = numero;
        this.cliente = cliente;
        this.total = total;
    }

    resumen() {
        console.log(`Orden #${this.numero}`);
        this.cliente.datos();           // Orden usa un método de Cliente
        console.log(`Total: ${this.total.toFixed(2)}`);
    }

    nombreDelCliente() {
        return this.cliente.nombre;     // y también sus atributos
    }
}


// ===== 3. Crear y usar un objeto de otra clase =====

const cliente1 = new Cliente("Sol", "sol@correo.com");
cliente1.datos();


// ===== 4. Colaboración: una clase recibe un objeto de otra =====

const orden1 = new Orden(1, cliente1, 120.5);
orden1.resumen();

console.log("Cliente de la orden: " + orden1.nombreDelCliente());


// ===== 5. Los objetos se pasan por referencia =====

// La orden no guarda una copia del cliente, sino el mismo objeto
const orden2 = new Orden(2, cliente1, 80);

cliente1.email = "nuevo@correo.com";   // se cambia el cliente...

orden1.resumen();   // ...y las dos órdenes lo ven
orden2.resumen();
