// Conexión a una base de datos Oracle con Node.js: el equivalente de la lección 55 (ADO.NET).
// Antes de ejecutar: tener Oracle (por ejemplo Oracle Database Express Edition) en marcha y, en la
// carpeta 07-ui-and-databases:
//   npm install          (instala oracledb, el controlador de Oracle)
// Para ejecutarlo:  node index.js
// Si la clave de system no es "oracle", definir la variable de entorno ORACLE_PASSWORD;
// en Oracle 18c/21c XE, definir ORACLE_SERVICE=XEPDB1.

const oracledb = require("oracledb");     // en el modo "Thin" (por defecto) no hace falta instalar el cliente de Oracle


// ===== 1. La clase Conexion: guarda los parámetros y abre / cierra la conexión =====

class Conexion {
    constructor() {
        // La clave no se escribe en el código: se lee de una variable de entorno (por defecto, oracle como en el vídeo)
        const password = process.env.ORACLE_PASSWORD ?? "oracle";

        // El servicio: XE en Oracle 11g XE (como en el vídeo). En 18c/21c XE, la base "enchufable" es XEPDB1
        const servicio = process.env.ORACLE_SERVICE ?? "XE";

        // Los parámetros de conexión: toda la información necesaria para llegar a la base
        this.parametros = {
            user: "system",                         // usuario
            password: password,                     // contraseña
            connectString: "localhost:1521/" + servicio,     // servidor:puerto/servicio
        };

        this.conexionOracle = null;                 // la conexión activa (null mientras no se ha abierto)
    }

    // Abre la conexión
    async abrirConexion() {
        try {
            // aquí es donde realmente se conecta
            this.conexionOracle = await oracledb.getConnection(this.parametros);
            console.log("Sistema conectado a Oracle Database");
        } catch (e) {
            console.log("Error conectando a la base: " + (e.message || e.code));
        }
    }

    // Cierra la conexión
    async cerrarConexion() {
        try {
            if (this.conexionOracle !== null) {
                await this.conexionOracle.close();
                this.conexionOracle = null;
                console.log("Sistema desconectado de la base");
            }
        } catch (e) {
            console.log("Error al desconectar de la base: " + (e.message || e.code));
        }
    }
}


// ===== 2. El programa principal =====

async function main() {
    console.log("Iniciando conexión a la base...");

    const conectar = new Conexion();

    await conectar.abrirConexion();
    await conectar.cerrarConexion();
}

main();
