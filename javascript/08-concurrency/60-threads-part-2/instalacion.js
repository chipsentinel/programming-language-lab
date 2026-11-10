// Lo que hace el hilo de instalación (equivale al método Instalacion de C#): tarda 7 segundos.
// Este archivo se ejecuta dentro de un Worker.

const { dormir } = require("./dormir");

console.log("Instalando programa...");

dormir(7000);

console.log("Programa instalado correctamente");
