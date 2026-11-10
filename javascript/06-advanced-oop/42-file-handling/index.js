// Manejo de archivos: crear, leer, modificar, copiar y borrar archivos del sistema.
// Node.js incluye el módulo fs (File System) y path: no hace falta instalar nada.
// (En el navegador no hay acceso directo a los archivos del equipo: esto es solo de Node.js.)
//
// Cada función de fs tiene tres versiones:
//   fs.readFileSync(...)          síncrona: espera a terminar (la que se usa aquí)
//   fs.readFile(..., callback)    con callback
//   fs.promises.readFile(...)     con promesas (async / await): lección 61
//
// Para no ensuciar el equipo, esta lección trabaja dentro de una carpeta temporal
// que se crea al empezar y se borra al terminar.

const fs = require("fs");
const os = require("os");
const path = require("path");


// ===== 0. La ruta del archivo =====

// Una ruta puede ser relativa (archivo1.txt: junto a donde se ejecuta) o absoluta (C:\Carpeta\archivo1.txt).
// path.join une las partes con el separador correcto del sistema operativo.
const carpeta = fs.mkdtempSync(path.join(os.tmpdir(), "leccion42-js-"));
const ruta = path.join(carpeta, "archivo1.txt");

// En una ruta escrita a mano, la barra invertida \ es especial en los textos: se escribe doble (\\)
//   const rutaManual = "C:\\Datos\\archivo1.txt";   // o con barras normales: "C:/Datos/archivo1.txt"


// ===== 1. Crear un archivo y escribir en él: writeFileSync =====

// Si el archivo no existe lo crea; si existe, LO SOBRESCRIBE
fs.writeFileSync(ruta, "Hola a todos", "utf8");
console.log("Archivo creado");


// ===== 2. Leer todo el contenido: readFileSync =====

// Sin indicar "utf8" devuelve bytes (un Buffer) en lugar de texto
const contenido = fs.readFileSync(ruta, "utf8");
console.log("Contenido: " + contenido);


// ===== 3. Añadir texto al final: appendFileSync =====

fs.appendFileSync(ruta, "\nEsta es una nueva línea de texto", "utf8");

console.log("Tras añadir una línea:");
console.log(fs.readFileSync(ruta, "utf8"));


// ===== 4. Leer línea a línea =====

// No hay un readAllLines: se lee todo el texto y se parte por los saltos de línea
const lineas = fs.readFileSync(ruta, "utf8").split("\n");

console.log("Número de líneas: " + lineas.length);
for (let i = 0; i < lineas.length; i++) {
    console.log(`Línea ${i + 1}: ${lineas[i]}`);
}


// ===== 5. Escribir varias líneas =====

fs.writeFileSync(ruta, ["primera", "segunda", "tercera"].join("\n"), "utf8");
console.log("Reescrito con 3 líneas");
console.log("Número de líneas: " + fs.readFileSync(ruta, "utf8").split("\n").length);


// ===== 6. Comprobar si existe y copiar =====

console.log("¿Existe el archivo? " + fs.existsSync(ruta));

const copia = path.join(carpeta, "copia.txt");
fs.copyFileSync(ruta, copia);   // sobrescribe si la copia ya existe
console.log("Copia creada: contenido igual = " + (fs.readFileSync(ruta, "utf8") === fs.readFileSync(copia, "utf8")));


// ===== 7. Borrar un archivo: unlinkSync =====

if (fs.existsSync(ruta)) {
    try {
        fs.unlinkSync(ruta);
        console.log("Archivo borrado correctamente");
    } catch (error) {
        console.log("No se pudo borrar: " + error.message);
    }
} else {
    console.log("El archivo no existe");
}

console.log("¿Existe el archivo? " + fs.existsSync(ruta));

// unlinkSync SÍ lanza un error (código ENOENT) si el archivo no existe


// ===== 8. Leer un archivo que no existe: excepción =====

try {
    fs.readFileSync(ruta, "utf8");
} catch (error) {
    if (error.code === "ENOENT") {   // ENOENT: "no such file or directory"
        console.log("Error: el archivo no existe");
    }
}


// ===== 9. Escribir y leer con flujos (streams) =====

// Para archivos muy grandes no conviene cargarlos enteros en memoria: se usan flujos.
// writeSync / readSync trabajan con un descriptor de archivo que hay que cerrar.
const descriptor = fs.openSync(ruta, "w");
try {
    fs.writeSync(descriptor, "línea escrita con un descriptor\n");
    fs.writeSync(descriptor, "segunda línea\n");
} finally {
    fs.closeSync(descriptor);   // finally garantiza que se cierre aunque haya un error
}

for (const linea of fs.readFileSync(ruta, "utf8").split("\n")) {
    if (linea !== "") {
        console.log("Leída: " + linea);
    }
}


// ===== Limpieza: se borra la carpeta temporal con todo su contenido =====

fs.rmSync(carpeta, { recursive: true, force: true });
console.log("¿Existe la carpeta? " + fs.existsSync(carpeta));
