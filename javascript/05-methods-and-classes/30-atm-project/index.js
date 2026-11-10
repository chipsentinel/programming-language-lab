const readline = require("readline/promises");

// Proyecto: cajero automático.
// Pone en práctica lo visto hasta ahora: clases, atributos, métodos con y sin retorno,
// entrada de datos, conversión de tipos, if/else, switch y bucles do-while.
//
// Leer del teclado es asíncrono (lección 11): los métodos que preguntan al usuario
// son async y delante de cada pregunta se escribe await.

const LIMITE_OPERACIONES = 3;


class CajeroAutomatico {

    // Atributos del cajero
    saldo = 10000;           // saldo inicial de la cuenta
    operaciones = 0;         // operaciones realizadas en esta sesión
    rl = readline.createInterface({ input: process.stdin, output: process.stdout });


    // ===== Menú principal =====

    async menu() {
        let opcion;

        // do-while: el menú se muestra al menos una vez y se repite hasta elegir "4"
        do {
            console.clear();

            console.log(`==== SELECCIONE LA OPERACIÓN ====
1. Consultar saldo
2. Depositar
3. Retirar
4. Salir
=================================`);

            // question devuelve texto, y la opción se compara con textos ("1", "2"...)
            opcion = await this.rl.question("Seleccione una opción:\n");

            switch (opcion) {
                case "1":
                    await this.consultarSaldo();
                    break;
                case "2":
                    await this.depositar();
                    break;
                case "3":
                    await this.retirar();
                    break;
                case "4":
                    this.salir();
                    break;
                default:
                    await this.rl.question("OPCIÓN NO VÁLIDA. Presione Enter para continuar...\n");
                    break;
            }
        } while (opcion !== "4");
    }


    // ===== Operaciones =====

    async consultarSaldo() {
        console.clear();
        console.log(`Saldo actual: ${this.saldo.toFixed(2)}`);

        await this.finalizarOperacion();
    }

    async depositar() {
        let monto;

        // Se vuelve a pedir el monto mientras no sea válido
        do {
            monto = await this.leerNumero("Ingrese el monto a depositar:\n");

            if (monto <= 0) {
                console.log("Monto inválido: debe ser mayor que cero. Intente de nuevo.");
            }
        } while (monto <= 0);

        this.saldo += monto;
        console.log(`Depósito exitoso. Nuevo saldo: ${this.saldo.toFixed(2)}`);

        await this.finalizarOperacion();
    }

    async retirar() {
        let retiro;
        let valido;

        do {
            retiro = await this.leerNumero("Ingrese el monto a retirar:\n");
            valido = false;

            if (retiro <= 0) {
                console.log("Monto inválido: debe ser mayor que cero. Intente de nuevo.");
            } else if (retiro > this.saldo) {
                console.log("Fondos insuficientes. Intente de nuevo.");
            } else {
                valido = true;
            }
        } while (!valido);

        this.saldo -= retiro;
        console.log(`Retiro completo. Nuevo saldo: ${this.saldo.toFixed(2)}`);

        await this.finalizarOperacion();
    }

    salir() {
        console.log("==============================");
        console.log("Gracias por usar nuestros servicios");
        console.log("==============================");

        this.rl.close();
        process.exit(0);   // cierra el programa
    }


    // ===== Métodos auxiliares =====

    // Cuenta la operación y pregunta si se quiere hacer otra
    async finalizarOperacion() {
        this.operaciones++;

        // Ejercicio propuesto: el cliente solo puede hacer un número limitado de operaciones
        if (this.operaciones >= LIMITE_OPERACIONES) {
            console.log("LÍMITE DE OPERACIONES PERMITIDAS ALCANZADO");
            this.salir();
        }

        const respuesta = await this.rl.question("¿Desea realizar otra operación? (1 = Sí / 2 = No)\n");

        if (respuesta !== "1") {
            this.salir();
        }
        // Si responde 1, el método termina y el menú vuelve a mostrarse
    }

    // Convierte lo que escribe el usuario en número; si no es un número, devuelve 0 (monto inválido)
    async leerNumero(pregunta) {
        const numero = Number(await this.rl.question(pregunta));

        // Number("hola") es NaN, y NaN no es un número válido
        return Number.isNaN(numero) ? 0 : numero;
    }
}


const cajero = new CajeroAutomatico();
cajero.menu();
