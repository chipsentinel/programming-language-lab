// Consultas (SELECT) a una base de datos MySQL con Node.js: el equivalente de la lección 54 (ADO.NET).
// Requiere la base colegio_amanecer de la lección 53 (script colegio_amanecer.sql en esa carpeta)
// y haber hecho  npm install  en la carpeta 07-ui-and-databases.
// Para ejecutarlo:  node index.js
// Si la clave de root no es abc123, definir la variable de entorno MYSQL_PASSWORD.

const mysql = require("mysql2/promise");


// ===== 1. La clase Conexion (la de la lección 53, con el método para cerrar) =====

class Conexion {
    constructor() {
        const password = process.env.MYSQL_PASSWORD ?? "abc123";

        this.parametros = {
            host: "localhost",
            port: 3306,
            database: "colegio_amanecer",
            user: "root",
            password: password,
        };

        this.conectar = null;
    }

    // Abre la conexión (el propio método comprueba el estado, así que no hace falta comprobarlo fuera)
    async abrirConexion() {
        try {
            if (this.conectar === null) {
                this.conectar = await mysql.createConnection(this.parametros);
                console.log("Conexión abierta");
            }
        } catch (e) {
            console.log("Error al abrir la conexión a la base: " + (e.message || e.code));
        }

        return this.conectar;
    }

    // Cierra la conexión si está abierta: liberar recursos (memoria, procesador, conexiones del servidor)
    async cerrarConexion() {
        if (this.conectar !== null) {
            await this.conectar.end();
            this.conectar = null;
            console.log("Conexión cerrada");
        }
    }
}


// ===== 2. La clase Consulta: las consultas a la base =====

class Consulta {

    // Consulta 1: todos los campos de la tabla alumnos
    async consultaAlumnos() {
        // Se crea un objeto de la clase Conexion: es la que sabe cómo conectarse
        const conexionConsulta = new Conexion();

        // La consulta SQL, en un string
        const consulta = "SELECT * FROM alumnos";

        try {
            // Si no se pudo abrir (devuelve null), no se puede consultar: se avisa con una excepción
            if (await conexionConsulta.abrirConexion() === null) {
                throw new Error("La conexión no está abierta");
            }

            // query() envía la consulta por la conexión y devuelve [filas, campos].
            // filas es un array con un objeto por fila: { id_alumno: 1, nombre: "Sol", ... }
            const [filas] = await conexionConsulta.conectar.query(consulta);

            console.log();
            console.log("ID".padEnd(4) + "Nombre".padEnd(9) + "Apellido".padEnd(10) + "Edad".padEnd(5) +
                        "Dirección".padEnd(19) + "Teléfono".padEnd(11) + "Grado".padEnd(9) + "Prof.");

            // Se recorren las filas una a una. Cada campo se pide por su nombre EXACTO en la tabla.
            // padEnd(9): alinea el valor a la izquierda en 9 caracteres (para que se vea como una tabla)
            for (const datos of filas) {
                console.log(String(datos.id_alumno).padEnd(4) + datos.nombre.padEnd(9) + datos.apellido.padEnd(10) +
                            String(datos.edad).padEnd(5) + datos.direccion.padEnd(19) + datos.telefono.padEnd(11) +
                            datos.grado.padEnd(9) + datos.id_profesor);
            }
        } catch (e) {
            console.log("Error consultando datos: " + (e.message || e.code));
        } finally {
            // finally se ejecuta SIEMPRE (haya error o no): la conexión se cierra sí o sí
            await conexionConsulta.cerrarConexion();
        }
    }

    // Consulta 2: solo el nombre y el grado (se piden solo los campos necesarios)
    async consultaNombreGrado() {
        const conexionConsulta = new Conexion();
        const consulta = "SELECT nombre, grado FROM alumnos";

        try {
            // Si no se pudo abrir (devuelve null), no se puede consultar: se avisa con una excepción
            if (await conexionConsulta.abrirConexion() === null) {
                throw new Error("La conexión no está abierta");
            }

            const [filas] = await conexionConsulta.conectar.query(consulta);

            console.log();
            console.log("Nombre".padEnd(9) + "Grado");

            for (const datos of filas) {
                console.log(datos.nombre.padEnd(9) + datos.grado);
            }
        } catch (e) {
            console.log("Error consultando datos: " + (e.message || e.code));
        } finally {
            await conexionConsulta.cerrarConexion();
        }
    }
}


// ===== 3. El programa principal =====

async function main() {
    console.log("Iniciando conexión...");

    // La conexión se abre dentro de cada consulta: aquí solo se invocan
    const consulta = new Consulta();
    await consulta.consultaAlumnos();
    await consulta.consultaNombreGrado();
}

main();
