// Lo que hace el hilo de análisis (equivale al método AnalizarArchivo de C#): tarda 5 segundos.
// Este archivo se ejecuta dentro de un Worker.

const { dormir } = require("./dormir");

console.log("Analizando archivo...");

// dormir indica cuánto TARDA esta tarea (aquí, 5 segundos). Es el tiempo que el sistema
// espera antes de pasar a la siguiente acción. NO es lo que hace esperar al programa principal:
// eso lo hace la espera con await, en index.js.
dormir(5000);

console.log("Archivo analizado, no hay amenazas");
