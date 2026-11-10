// Hilos (threads), primera parte: crear un hilo, iniciarlo y esperar a que termine.
// En Node.js los hilos son los Workers (módulo worker_threads). Para ejecutarlo:  node index.js

const { Worker } = require("worker_threads");
const { once } = require("events");


// ===== 1. La clase Hilos =====

class Hilos {

    // conteo: el método que crea, inicia y espera al hilo
    async conteo() {
        // ===== 2. Crear un hilo =====

        // Worker es la clase de los hilos. Al crearlo se le pasa el ARCHIVO con el código que va a ejecutar
        // (hilo.js, que equivale al método EjecucionHilo). Un Worker se inicia al crearse:
        // no hay un Start() aparte, como en C#.
        const hilo1 = new Worker(__dirname + "/hilo.js");

        // ===== 3. Iniciar el hilo =====

        console.log("Hilo iniciado");
        console.log("(esta línea sale antes que 'Pasaron 3 segundos': el programa principal no espera al hilo)");

        // ===== 4. Esperar al hilo: el "Join" =====

        // No hay Join() bloqueante: el hilo principal de JavaScript no se detiene.
        // Se ESPERA al evento "exit" (el hilo terminó) con await, y el programa continúa cuando llega.
        // Sin esta línea, "Fin del programa" saldría enseguida, sin esperar los 3 segundos del hilo.
        await once(hilo1, "exit");

        console.log("Fin del programa");
    }
}


// ===== 5. El programa principal =====

async function main() {
    const ejecuta = new Hilos();
    await ejecuta.conteo();
}

main();
