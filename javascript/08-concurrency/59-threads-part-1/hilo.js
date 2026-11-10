// El código que ejecuta el hilo (equivale al método EjecucionHilo de C#).
// Este archivo se ejecuta dentro de un Worker: un hilo de Node.js con su propio entorno.

// En JavaScript no existe "Thread.Sleep": esta es la forma de DORMIR un hilo (de forma bloqueante).
// Atomics.wait espera, como máximo, ese número de milisegundos (3000 ms = 3 segundos).
// Solo se puede usar en un Worker, no en el hilo principal.
function dormir(milisegundos) {
    Atomics.wait(new Int32Array(new SharedArrayBuffer(4)), 0, 0, milisegundos);
}

function ejecucionHilo() {
    dormir(3000);

    console.log("Pasaron 3 segundos");
}

ejecucionHilo();
