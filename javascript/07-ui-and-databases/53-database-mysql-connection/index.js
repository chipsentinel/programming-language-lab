// Conexión a una base de datos MySQL con Node.js: el equivalente de la lección 53 (ADO.NET).
// Antes de ejecutar: instalar MySQL, ejecutar colegio_amanecer.sql y, en la carpeta 07-ui-and-databases:
//   npm install          (instala mysql2, el controlador de MySQL)
// Para ejecutarlo:  node index.js
// Si la clave de root no es abc123, definir la variable de entorno MYSQL_PASSWORD.

const mysql = require("mysql2/promise");     // la versión con promesas: se usa con async/await


// ===== 1. La clase Conexion: guarda los parámetros y abre la conexión =====

class Conexion {
    constructor() {
        // La clave no se escribe en el código: se lee de una variable de entorno (por defecto, abc123 como en el vídeo)
        const password = process.env.MYSQL_PASSWORD ?? "abc123";

        // Los parámetros de conexión: toda la información necesaria para llegar a la base
        this.parametros = {
            host: "localhost",              // servidor: el propio equipo
            port: 3306,                     // puerto por defecto de MySQL
            database: "colegio_amanecer",   // nombre de la base de datos
            user: "root",                   // usuario
            password: password,             // contraseña
        };

        this.conectar = null;               // la conexión activa (null mientras no se ha abierto)
    }

    // Abre la conexión y devuelve el objeto de conexión (abierto, o null si hubo un error)
    async abrirConexion() {
        try {
            // Solo se abre si no hay una conexión abierta
            if (this.conectar === null) {
                this.conectar = await mysql.createConnection(this.parametros);
                console.log("Conexión abierta");
            }
        } catch (e) {
            // Con varios intentos fallidos (IPv4 e IPv6) el error llega sin mensaje: se usa su código (ECONNREFUSED...)
            console.log("Error al abrir la conexión a la base: " + (e.message || e.code));
        }

        return this.conectar;
    }

    // Cierra la conexión: siempre hay que cerrarla para liberar recursos
    async cerrarConexion() {
        if (this.conectar !== null) {
            await this.conectar.end();
            this.conectar = null;
            console.log("Conexión cerrada");
        }
    }
}


// ===== 2. El programa principal =====

async function main() {
    console.log("Iniciando conexión...");

    const conectarBD = new Conexion();

    // Abrir la conexión (tiene que ser un paso aparte de crear el objeto)
    const conectar = await conectarBD.abrirConexion();

    if (conectar !== null) {
        console.log("CONEXIÓN EXITOSA");
    } else {
        console.log("ERROR CONECTÁNDOSE A LA BASE");
    }

    // Al terminar, cerrar la conexión
    await conectarBD.cerrarConexion();
}

main();
