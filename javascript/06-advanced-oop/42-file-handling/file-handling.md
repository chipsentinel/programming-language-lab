# Manejo de archivos en JavaScript

## Qué se aprende

Crear, leer, modificar, copiar y borrar **archivos** del sistema desde un programa. En Node.js se usa el módulo **`fs`** (*File System*), que viene incluido: no hace falta instalar nada. Se importa con `require`:

```javascript
const fs = require("fs");
const path = require("path");
```

Se empieza con el formato más sencillo: el **archivo de texto** (`.txt`).

> Esto es **solo de Node.js**. En el navegador, JavaScript **no** puede leer ni escribir archivos del equipo directamente por seguridad.

## Tres versiones de cada función

| Versión | Ejemplo | Cómo funciona |
|---|---|---|
| **Síncrona** | `fs.readFileSync(ruta)` | Espera a terminar antes de seguir |
| **Con callback** | `fs.readFile(ruta, callback)` | Sigue y avisa al terminar |
| **Con promesas** | `fs.promises.readFile(ruta)` | Con `async` / `await` (lección 61) |

Aquí se usan las **síncronas** (acaban en `Sync`), las más sencillas. En un programa real, con muchos usuarios a la vez, se prefieren las asíncronas para no bloquear.

## La ruta del archivo

```javascript
const ruta = "archivo1.txt";
```

| Tipo de ruta | Ejemplo | Dónde |
|---|---|---|
| **Relativa** | `archivo1.txt` | En la carpeta **desde donde se ejecuta** `node` |
| **Absoluta** | `C:\Datos\archivo1.txt` | Un lugar exacto del disco |

### La barra invertida en los textos

En Windows las carpetas se separan con `\`, que en un texto de JavaScript es un carácter **especial** (`\n`, `\t`...). Se duplica, o se usan barras normales, que Node acepta en cualquier sistema:

```javascript
const ruta1 = "C:\\Datos\\archivo1.txt";
const ruta2 = "C:/Datos/archivo1.txt";
```

### Construir rutas: `path.join`

```javascript
const carpeta = path.join(os.tmpdir(), "mis-archivos");
const ruta = path.join(carpeta, "archivo1.txt");     // separador correcto del sistema
```

## Crear y escribir: `writeFileSync`

```javascript
fs.writeFileSync(ruta, "Hola a todos", "utf8");
```

- Si el archivo **no existe, lo crea**.
- Si **ya existe, lo sobrescribe**: el contenido anterior se pierde.
- `"utf8"` es la **codificación** del texto.

## Leer: `readFileSync`

```javascript
const contenido = fs.readFileSync(ruta, "utf8");
console.log(contenido);
```

Devuelve **todo el archivo**. **Sin `"utf8"`** devuelve bytes (un `Buffer`) en lugar de texto, y `console.log` muestra algo como `<Buffer 48 6f 6c 61>`.

## Añadir al final: `appendFileSync`

```javascript
fs.appendFileSync(ruta, "\nEsta es una nueva línea de texto", "utf8");
```

Se pone un `\n` al principio para que el texto nuevo empiece en una **línea nueva**.

| Función | Si el archivo existe |
|---|---|
| `writeFileSync` | **Reemplaza** el contenido |
| `appendFileSync` | **Añade** al final |

## Leer y escribir por líneas

No existe un `readAllLines`: se lee todo y se **parte por los saltos de línea** con `split`:

```javascript
const lineas = fs.readFileSync(ruta, "utf8").split("\n");

for (let i = 0; i < lineas.length; i++) {
    console.log(`Línea ${i + 1}: ${lineas[i]}`);
}
```

Para escribir varias líneas, se unen con `join`:

```javascript
fs.writeFileSync(ruta, ["primera", "segunda", "tercera"].join("\n"), "utf8");
```

Si el archivo termina en un salto de línea, `split` deja una **última línea vacía**.

## Comprobar si existe: `existsSync`

```javascript
if (fs.existsSync(ruta)) {
    console.log("El archivo existe");
}
```

Devuelve `true` o `false`. Conviene usarlo **antes** de leer o borrar.

## Copiar y mover

```javascript
fs.copyFileSync(ruta, copia);           // copia (sobrescribe si ya existe)
fs.renameSync(ruta, nuevaRuta);         // mueve o renombra
```

## Borrar: `unlinkSync`

```javascript
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
```

- **Borra el archivo para siempre**: no va a la papelera.
- `unlinkSync` **lanza un error** si el archivo no existe (código `ENOENT`).

## Errores al leer

Los errores de `fs` son objetos `Error` con un **código** (`code`) que dice qué pasó:

```javascript
try {
    fs.readFileSync("no-existe.txt", "utf8");
} catch (error) {
    if (error.code === "ENOENT") {
        console.log("Error: el archivo no existe");
    }
}
```

| Código | Significa |
|---|---|
| `ENOENT` | El archivo o la carpeta no existen |
| `EACCES` | No hay permisos |
| `EEXIST` | Ya existe |
| `EISDIR` | Es una carpeta, no un archivo |

En C# y Java hay una excepción distinta por cada caso; en JavaScript es un único `Error` y se distingue por `code`.

## Cerrar los archivos

Con las funciones sencillas (`readFileSync`...) Node abre y cierra el archivo solo. Si se trabaja con un **descriptor** (`openSync`), hay que cerrarlo, y **`finally`** garantiza que se haga aunque haya un error:

```javascript
const descriptor = fs.openSync(ruta, "w");
try {
    fs.writeSync(descriptor, "primera línea\n");
} finally {
    fs.closeSync(descriptor);
}
```

JavaScript no tiene un `using` ni un `try-with-resources`. Para archivos grandes se usan **flujos** (`fs.createReadStream`).

## Carpetas

```javascript
fs.mkdirSync(carpeta, { recursive: true });                  // crea la carpeta (y las intermedias)
fs.existsSync(carpeta);                                      // ¿existe?
fs.rmSync(carpeta, { recursive: true, force: true });        // borra la carpeta con todo su contenido
```

## Ejemplo

```javascript
const fs = require("fs");

const ruta = "archivo1.txt";

fs.writeFileSync(ruta, "Hola a todos", "utf8");
fs.appendFileSync(ruta, "\nSegunda línea", "utf8");

console.log(fs.readFileSync(ruta, "utf8"));

fs.unlinkSync(ruta);
```

## Comandos

```bash
cd javascript/06-advanced-oop
node 42-file-handling/index.js
```

Fuente: [curso de C# de INFORMATICONFIG](https://www.youtube.com/watch?v=qJ_lYQBTRzU&list=PL2Z95CSZ1N4F7mym8Ca16RoTDVAlIOPlT&index=42), vídeo 42.
