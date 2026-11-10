# Conexión a MySQL con Node.js

## Qué se aprende

Conectarse a una **base de datos** para **guardar**, **recuperar**, **actualizar** y **eliminar** datos. En C# se hacía con **ADO.NET** (lección 53); en JavaScript con **Node.js** se usa un **controlador** (*driver*) instalado con **npm**: para MySQL, el paquete **`mysql2`**.

> Ojo: el **navegador no puede** conectarse directamente a una base de datos (se expondría la clave a cualquiera que abra la página). Esto se hace en **Node.js**, en el servidor. Por eso el ejemplo es un programa de Node.js y no una página.
>
> No es un curso de bases de datos: solo se prepara un entorno para aprender a **conectarse y trabajar desde JavaScript**.

## El flujo típico

```text
1. Preparar los parámetros       →  servidor, puerto, base de datos, usuario y contraseña
2. Abrir la conexión             →  mysql.createConnection(parametros)
3. Ejecutar comandos SQL         →  conexion.query / conexion.execute: SELECT, INSERT, UPDATE, DELETE
4. Leer los resultados           →  el resultado de la consulta (filas)
5. Cerrar la conexión            →  siempre, para liberar recursos: conexion.end()
```

| Paso | `mysql2` | ADO.NET (MySQL) |
|---|---|---|
| Conexión | `mysql.createConnection(...)` | `MySqlConnection` |
| Comando | `conexion.query(sql)` | `MySqlCommand` |
| Lector | el array de filas devuelto | `MySqlDataReader` |

## Preparar MySQL

Si ya hay un MySQL instalado solo hace falta conocer los **parámetros de conexión**.

### Instalarlo (Windows)

1. Descargar **MySQL Installer for Windows** (la versión grande, de unos 350 MB) desde la sección *Downloads → MySQL Community (GPL) Downloads* de mysql.com.
2. Ejecutarlo y elegir el tipo **Custom**.
3. Instalar **MySQL Server 8.0** (el motor de bases de datos, donde se guardan los datos) y **MySQL Workbench 8.0** (el editor con el que se administran).
4. En la configuración, dejar todo por defecto: *Development Computer* y el puerto **TCP/IP 3306**.
5. Fijar la **clave del administrador** (`root`). El instalador avisa de que es *weak* si es sencilla: en un entorno de pruebas no importa, pero en **producción** hay que usar una clave **fuerte**.
6. No crear más usuarios; terminar.

### MySQL Workbench y la base del ejemplo

Workbench se abre con la conexión **instancia local**: usuario **`root`** y dirección **`localhost:3306`**.

Con el script **[colegio_amanecer.sql](colegio_amanecer.sql)** (en esta carpeta) se crea la base **`colegio_amanecer`**:

1. Copiar todo su contenido en el escritorio de Workbench y ejecutarlo (el rayo). **Todas las líneas deben salir en verde**.
2. Comprobar con `SELECT * FROM alumnos;`.

| Tabla | Campos principales |
|---|---|
| `alumnos` | `id_alumno`, `nombre`, `apellido`, `edad`, `direccion`, `telefono`, `grado`, `id_profesor` |
| `profesores` | `id_profesor`, `nombre`, `grado` |
| `materias` | `id_materia`, `materia`, `nivel`, `horario`, `id_profesor` |
| `alumno_materia` | `id_alumno`, `id_materia` |

> El nombre es `colegio_amanecer` (minúscula y guion bajo): **hay que escribirlo exactamente igual** en la conexión.

## El controlador: `mysql2` con npm

`mysql2` **no viene con Node.js**: es un **paquete externo**. **npm** es el gestor de paquetes (el equivalente de NuGet en C#).

```bash
npm install mysql2
```

Esto deja una entrada en `package.json`:

```json
"dependencies": { "mysql2": "^3.24.6" }
```

Y se importa en el código. Se usa la versión con **promesas** (`mysql2/promise`), para trabajar con `async/await`:

```javascript
const mysql = require("mysql2/promise");
```

Cuando alguien descargue el proyecto, con **`npm install`** se instalan las dependencias que figuran en `package.json`.

## La clase `Conexion`

Se separa en una clase la parte de **conectarse**, para que el programa sea más ordenado y se pueda reutilizar.

```javascript
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

        this.conectar = null;       // la conexión activa (null mientras no se ha abierto)
    }
}
```

### Los parámetros de conexión

Un **objeto** con los datos para llegar a la base:

| Clave | Valor | Significado |
|---|---|---|
| `host` | `localhost` | El servidor: la base está en este equipo |
| `port` | `3306` | El puerto por defecto de MySQL |
| `database` | `colegio_amanecer` | Nombre de la base de datos |
| `user` | `root` | Usuario (no se creó ninguno más) |
| `password` | *(la clave)* | La contraseña configurada al instalar |

### La contraseña no va en el código

El vídeo la escribe en el código (`abc123`) porque es un laboratorio. En el ejemplo se lee de una **variable de entorno**, con `abc123` por defecto:

```javascript
const password = process.env.MYSQL_PASSWORD ?? "abc123";
```

Para usar otra clave, antes de ejecutar:

```powershell
$env:MYSQL_PASSWORD = "mi_clave"      # PowerShell
```

Una clave escrita en el código acaba en el repositorio y la ve cualquiera.

## Abrir la conexión: `abrirConexion`

```javascript
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
```

- **`mysql.createConnection(parametros)`** **realmente se conecta** al motor: abre un canal con el servidor y se autentica. Devuelve una **promesa**, de ahí el **`await`** y que el método sea **`async`**.
- Si algo falla (servidor apagado, clave incorrecta, base inexistente...) la promesa se **rechaza** y `await` lanza una excepción. El **`try/catch`** muestra el motivo en vez de cerrar el programa.
- Con varios intentos fallidos (IPv4 e IPv6) el error puede llegar **sin mensaje**, por eso se usa también su `code` (`ECONNREFUSED` = conexión rechazada, no hay servidor escuchando).

> Un `try/catch` es **imprescindible** en todo el trabajo con bases de datos: el servidor puede estar apagado, la red puede fallar, la clave puede ser incorrecta.

## Cerrar la conexión: `cerrarConexion`

```javascript
async cerrarConexion() {
    if (this.conectar !== null) {
        await this.conectar.end();
        this.conectar = null;
        console.log("Conexión cerrada");
    }
}
```

**Siempre hay que cerrar la conexión**: una conexión abierta ocupa **memoria, procesador y una de las conexiones limitadas del servidor**. Además, mientras haya una conexión abierta, **Node.js no termina** el programa.

## El programa principal

```javascript
async function main() {
    const conectarBD = new Conexion();
    const conectar = await conectarBD.abrirConexion();

    if (conectar !== null) {
        console.log("CONEXIÓN EXITOSA");
    } else {
        console.log("ERROR CONECTÁNDOSE A LA BASE");
    }

    await conectarBD.cerrarConexion();
}

main();
```

### Salida esperada

Con MySQL funcionando y la base creada:

```text
Iniciando conexión...
Conexión abierta
CONEXIÓN EXITOSA
Conexión cerrada
```

Sin MySQL (o con la clave mal), sale el motivo y el aviso de error:

```text
Iniciando conexión...
Error al abrir la conexión a la base: ECONNREFUSED
ERROR CONECTÁNDOSE A LA BASE
```

### "Abrir conexión" y "conectarse"

El vídeo distingue dos mensajes: **"conexión abierta"** lo da el método `abrirConexion` (se ha establecido la comunicación con el servidor) y **"conexión exitosa"** lo da el programa principal (hay una conexión y ya se pueden ejecutar comandos SQL, lo que se hace en la lección siguiente).

## Ejecutar

```bash
npm install
node index.js
```

(`npm install` se hace una vez, en la carpeta `07-ui-and-databases`.)

## Comandos

```bash
cd javascript/07-ui-and-databases
npm install
cd 53-database-mysql-connection
node index.js
```

Fuente: [curso de C# de INFORMATICONFIG](https://www.youtube.com/watch?v=7Vy8_RblBAA&list=PL2Z95CSZ1N4F7mym8Ca16RoTDVAlIOPlT&index=53), vídeo 53.
