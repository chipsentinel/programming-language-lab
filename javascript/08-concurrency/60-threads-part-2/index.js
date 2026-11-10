// Hilos (threads), segunda parte: dos hilos con tareas distintas.
// Se simula analizar un programa (buscar amenazas) antes de instalarlo.
// En Node.js los hilos son los Workers (módulo worker_threads). Para ejecutarlo:  node index.js

const { Worker } = require("worker_threads");
const { once } = require("events");


// ===== 1. La clase Instalador: dos hilos, cada uno con su archivo =====

class Instalador {
    // Un Worker se INICIA AL CREARSE (no hay Start() aparte): por eso aquí se crean con un método,
    // justo en el momento en que se quiere arrancar cada hilo.
    crearHiloAnalisis() {
        return new Worker(__dirname + "/analizar-archivo.js");
    }

    crearHiloInstalador() {
        return new Worker(__dirname + "/instalacion.js");
    }
}


// ===== 2. El programa principal =====

async function main() {
    const ejecucion = new Instalador();

    // ----- A) Un hilo detrás de otro (como en el vídeo): primero el análisis y después la instalación -----

    console.log("=== A) Un hilo detrás de otro ===");
    let inicio = performance.now();        // para medir el tiempo total

    const hiloAnalisis = ejecucion.crearHiloAnalisis();      // arranca el hilo de análisis
    await once(hiloAnalisis, "exit");                        // el programa ESPERA a que termine (5 s) antes de seguir

    const hiloInstalador = ejecucion.crearHiloInstalador();  // solo entonces arranca el de instalación
    await once(hiloInstalador, "exit");                      // y se espera a que termine (7 s)

    console.log("Tiempo total: " + Math.round((performance.now() - inicio) / 1000) + " s");   // 5 + 7 = 12 s


    // ----- B) Los dos hilos A LA VEZ: se arrancan los dos y después se espera a los dos -----

    console.log();
    console.log("=== B) Los dos hilos a la vez ===");
    inicio = performance.now();

    const hiloAnalisis2 = ejecucion.crearHiloAnalisis();
    const hiloInstalador2 = ejecucion.crearHiloInstalador();    // arranca sin esperar a que acabe el análisis

    // Promise.all espera a que terminen los dos
    await Promise.all([once(hiloAnalisis2, "exit"), once(hiloInstalador2, "exit")]);

    console.log("Tiempo total: " + Math.round((performance.now() - inicio) / 1000) + " s");   // solo 7 s: el más largo
}

main();
