// Dormir un hilo de trabajo (equivale a Thread.Sleep de C#).
// JavaScript no tiene un "sleep" bloqueante: Atomics.wait espera, como máximo, ese número de milisegundos.
// Solo se puede usar en un Worker, no en el hilo principal.

function dormir(milisegundos) {
    Atomics.wait(new Int32Array(new SharedArrayBuffer(4)), 0, 0, milisegundos);
}

module.exports = { dormir };
