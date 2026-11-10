// Consultas (SELECT) a una base de datos Oracle con Node.js: el equivalente de la lección 56 (ADO.NET).
// Requiere el usuario LIBRERIA y sus tablas (script libreria.sql, en esta carpeta)
// y haber hecho  npm install  en la carpeta 07-ui-and-databases.
// Para ejecutarlo:  node index.js
// Si la clave de libreria no es "oracle", definir la variable de entorno ORACLE_PASSWORD;
// en Oracle 18c/21c XE, definir ORACLE_SERVICE=XEPDB1.

const oracledb = require("oracledb");


// ===== 1. La clase Conexion (la de la lección 55, con otro usuario) =====

class Conexion {
    constructor() {
        const password = process.env.ORACLE_PASSWORD ?? "oracle";
        const servicio = process.env.ORACLE_SERVICE ?? "XE";

        this.parametros = {
            // El usuario es LIBRERIA (el esquema donde están las tablas), no SYSTEM:
            // con SYSTEM la consulta falla con "ORA-00942: la tabla o vista no existe", porque las tablas no están en su esquema
            user: "libreria",
            password: password,
            connectString: "localhost:1521/" + servicio,
        };

        this.conexionOracle = null;     // (la clase Consultas la usa desde fuera)
    }

    async abrirConexion() {
        try {
            this.conexionOracle = await oracledb.getConnection(this.parametros);
            console.log("Sistema conectado a Oracle Database");
        } catch (e) {
            console.log("Error conectando a la base: " + (e.message || e.code));
        }
    }

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


// ===== 2. La clase Consultas: las consultas a la base =====

class Consultas {

    async consultaLibros() {
        // Un objeto de la clase Conexion: ya sabe cómo conectarse
        const conectar = new Conexion();

        try {
            await conectar.abrirConexion();

            // Si no se pudo abrir (queda null), no se puede consultar: se avisa con una excepción
            if (conectar.conexionOracle === null) {
                throw new Error("La conexión no está abierta");
            }

            const consulta = "SELECT * FROM libros";

            // execute() envía la consulta y devuelve un objeto con las filas en result.rows.
            // Por defecto cada fila es un ARRAY con los campos en el orden de la tabla (posición 0, 1, 2...)
            const resultado = await conectar.conexionOracle.execute(consulta);

            console.log();
            console.log("ID".padEnd(4) + "Título".padEnd(50) + "Autor".padEnd(7) + "Fecha".padEnd(12) + "Género");
            console.log("-".repeat(80));

            // Se recorren las filas una a una
            for (const lectura of resultado.rows) {
                // Los campos se piden por su POSICIÓN en la tabla, empezando en 0:
                // 0 id_libro, 1 titulo, 2 id_autor, 3 fecha_publicacion, 4 genero
                const id = lectura[0];
                const titulo = lectura[1];
                const autor = lectura[2];
                const fecha = lectura[3];      // un objeto Date de JavaScript
                const genero = lectura[4];

                // Fecha formateada (año-mes-día): getMonth() empieza en 0, por eso el +1
                const fechaTexto = fecha.getFullYear() + "-" +
                                   String(fecha.getMonth() + 1).padStart(2, "0") + "-" +
                                   String(fecha.getDate()).padStart(2, "0");

                console.log(String(id).padEnd(4) + titulo.padEnd(50) + String(autor).padEnd(7) +
                            fechaTexto.padEnd(12) + genero);
            }
        } catch (error) {
            console.log("Error consultando datos: " + (error.message || error.code));
        } finally {
            // finally se ejecuta SIEMPRE (haya error o no): la conexión se cierra sí o sí
            await conectar.cerrarConexion();
        }
    }
}


// ===== 3. El programa principal =====

async function main() {
    console.log("Iniciando conexión a la base...");

    // La consulta abre y cierra la conexión por su cuenta
    const consultar = new Consultas();
    await consultar.consultaLibros();
}

main();
