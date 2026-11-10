// Programación asíncrona (async / await y Promise): varias tareas a la vez sin esperar a que cada una termine.
// Es el equivalente de async / await con Task de C#. Para ejecutarlo:  node index.js


// ===== 1. Una espera que no bloquea: el equivalente de Task.Delay =====

// Una Promise es "una promesa" de un resultado futuro: es lo que en C# es un Task.
// setTimeout ejecuta algo pasado ese tiempo SIN BLOQUEAR el programa; cuando llega el momento,
// resolve() avisa de que la tarea terminó.
function espera(milisegundos) {
    return new Promise((resolve) => setTimeout(resolve, milisegundos));
}


// ===== 2. Un método asíncrono =====

// async marca que la función usa await. Una función async SIEMPRE devuelve una Promise.
async function conteo(tiempo, mensaje) {
    // await espera a que la promesa termine, pero mientras tanto el programa puede seguir haciendo otras cosas
    await espera(tiempo);

    console.log(mensaje);
}


// ===== 3. El programa principal =====

async function main() {
    // ----- A) Las dos tareas a la vez -----

    console.log("=== A) Las dos tareas a la vez ===");
    console.log("Iniciando conteo...");
    let inicio = performance.now();       // para medir el tiempo total

    // Llamar a la función NO espera: arranca la tarea y devuelve la Promise enseguida.
    // Las dos cuentas empiezan en el mismo momento
    const proceso1 = conteo(2000, "Han pasado 2 segundos");
    const proceso2 = conteo(5000, "Han pasado 5 segundos");

    // Promise.all recibe las promesas (podrían ser 50) y devuelve una promesa nueva que se cumple
    // cuando TODAS han terminado. Con await se espera a que eso ocurra (equivale a Task.WhenAll)
    await Promise.all([proceso1, proceso2]);

    // El segundo mensaje sale 3 segundos después del primero (no 5): las tareas corrían a la vez
    console.log("Tiempo total: " + Math.round((performance.now() - inicio) / 1000) + " s");   // 5 s


    // ----- B) Una tarea detrás de otra (con await en cada una) -----

    console.log();
    console.log("=== B) Una tarea detrás de otra ===");
    inicio = performance.now();

    // Aquí cada await espera a que su tarea termine ANTES de arrancar la siguiente
    await conteo(2000, "Han pasado 2 segundos");
    await conteo(5000, "Han pasado 5 segundos");

    console.log("Tiempo total: " + Math.round((performance.now() - inicio) / 1000) + " s");   // 2 + 5 = 7 s
}

main();
