// Conexión a una base de datos SQL Server con Node.js: el equivalente de la lección 57 (ADO.NET).
// Antes de ejecutar: tener SQL Server en marcha, la base "farmacia" creada (script farmacia.sql, en esta carpeta)
// y, en la carpeta 07-ui-and-databases:
//   npm install          (instala mssql, el controlador de SQL Server)
// Para ejecutarlo:  node index.js
// Variables de entorno:
//   SQLSERVER_USER y SQLSERVER_PASSWORD   autenticación de SQL Server (OBLIGATORIAS: ver el .md)
//   SQLSERVER_SERVER                      el servidor (por defecto localhost)
//   SQLSERVER_INSTANCE                    la instancia con nombre, si la hay (por ejemplo SQLEXPRESS)

const sql = require("mssql");


// ===== 1. La clase Conexion: guarda los parámetros y abre / cierra la conexión =====

class Conexion {
    constructor() {
        // Los parámetros de conexión: toda la información necesaria para llegar a la base
        this.parametros = {
            server: process.env.SQLSERVER_SERVER ?? "localhost",     // el servidor: localhost, el propio equipo
            database: "farmacia",                                    // nombre de la base de datos
            user: process.env.SQLSERVER_USER,                        // usuario de SQL Server
            password: process.env.SQLSERVER_PASSWORD,                // contraseña
            options: {
                trustServerCertificate: true,                        // confiar en el certificado del servidor (si no, falla en local)
                encrypt: true,
                instanceName: process.env.SQLSERVER_INSTANCE,        // undefined si no se define
            },
        };

        this.conectar = null;       // el "pool" de conexiones activo (null mientras no se ha abierto)
    }

    // Abre la conexión y devuelve el objeto de conexión (abierto, o null si hubo un error)
    async abrirConexion() {
        try {
            // Solo se abre si no hay una conexión abierta
            if (this.conectar === null) {
                // A diferencia de C#, aquí no hay autenticación de Windows: hay que dar usuario y clave de SQL Server
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

    // Cierra la conexión
    async cerrarConexion() {
        try {
            // Solo si está abierta
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


// ===== 2. El programa principal =====

async function main() {
    console.log("Iniciando conexión a la base...");

    const conexion = new Conexion();

    await conexion.abrirConexion();
    await conexion.cerrarConexion();
}

main();
