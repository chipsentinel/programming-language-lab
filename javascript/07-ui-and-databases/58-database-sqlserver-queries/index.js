// Consultas (SELECT) a una base de datos SQL Server con Node.js: el equivalente de la lección 58 (ADO.NET).
// Requiere la base "farmacia" de la lección 57 (script farmacia.sql en esa carpeta)
// y haber hecho  npm install  en la carpeta 07-ui-and-databases.
// Para ejecutarlo:  node index.js
// Variables de entorno: SQLSERVER_USER y SQLSERVER_PASSWORD (obligatorias), SQLSERVER_SERVER y SQLSERVER_INSTANCE (ver la lección 57).

const sql = require("mssql");


// ===== 1. La clase Conexion (la de la lección 57) =====

class Conexion {
    constructor() {
        this.parametros = {
            server: process.env.SQLSERVER_SERVER ?? "localhost",
            database: "farmacia",
            user: process.env.SQLSERVER_USER,
            password: process.env.SQLSERVER_PASSWORD,
            options: {
                trustServerCertificate: true,
                encrypt: true,
                instanceName: process.env.SQLSERVER_INSTANCE,
            },
        };

        this.conectar = null;
    }

    async abrirConexion() {
        try {
            if (this.conectar === null) {
                if (!this.parametros.user) {
                    throw new Error("Defina las variables de entorno SQLSERVER_USER y SQLSERVER_PASSWORD");
                }

                this.conectar = await sql.connect(this.parametros);
                console.log("Sistema conectado a SQL Server");
            }
        } catch (error) {
            console.log("Error conectando a la base: " + (error.message || error.code));
        }

        return this.conectar;
    }

    async cerrarConexion() {
        try {
            if (this.conectar !== null) {
                await this.conectar.close();
                this.conectar = null;
                console.log("Sistema desconectado de la base");
            }
        } catch (error) {
            console.log("Error al desconectar de la base: " + (error.message || error.code));
        }
    }
}


// ===== 2. La clase Consultas: las consultas a la base =====

class Consultas {

    async consultaClientes() {
        // Un objeto de la clase Conexion: ya sabe cómo conectarse
        const conectar = new Conexion();

        try {
            // abrirConexion devuelve la conexión abierta (o null, si hubo un error)
            const con = await conectar.abrirConexion();
            if (con === null) {
                throw new Error("La conexión no está abierta");
            }

            // Solo se piden los campos necesarios: el ID casi nunca se muestra en las aplicaciones
            const consulta = "SELECT nombre, direccion, telefono FROM clientes";

            // request().query() envía la consulta y devuelve (con await) un objeto cuyo recordset
            // es un array con un objeto por fila: { nombre: "Sol Vargas", direccion: "...", telefono: "..." }
            const resultado = await con.request().query(consulta);

            // Encabezados con formato de tabla: padEnd(20) rellena con espacios hasta 20 caracteres
            console.log();
            console.log("Nombre".padEnd(20) + " | " + "Dirección".padEnd(30) + " | " + "Teléfono".padEnd(15));
            console.log("-".repeat(70));         // línea de 70 guiones

            // Se recorren las filas una a una. Cada campo se pide por su nombre EXACTO en la tabla
            for (const lectura of resultado.recordset) {
                const nombre = lectura.nombre;
                const direccion = lectura.direccion;
                const telefono = lectura.telefono;

                console.log(nombre.padEnd(20) + " | " + direccion.padEnd(30) + " | " + telefono.padEnd(15));
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
    await consultar.consultaClientes();
}

main();
