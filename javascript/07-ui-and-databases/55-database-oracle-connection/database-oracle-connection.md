# Conexión a Oracle con Node.js

## Qué se aprende

La misma operación de la lección 53 (conectarse a una base de datos), pero con **Oracle**. Es importante saber hacerlo: muchos proyectos reales usan Oracle.

Con **Node.js** se usa el paquete **`oracledb`** (el controlador oficial de Oracle), que se instala con **npm**. El esquema es el mismo; cambian el **paquete** y los **parámetros**:

| | MySQL | Oracle |
|---|---|---|
| Paquete npm | `mysql2` | **`oracledb`** |
| Conectar | `mysql.createConnection(...)` | **`oracledb.getConnection(...)`** |
| Usuario | `root` | `system` |
| Cadena de conexión | `host`, `port`, `database` | **`connectString`**: `servidor:puerto/servicio` |

> Ojo: el **navegador no puede** conectarse directamente a una base de datos (se expondría la clave a cualquiera que abra la página). Esto se hace en **Node.js**, en el servidor.

## Preparar Oracle

Quien ya tenga Oracle instalado se puede saltar esto; solo necesita conocer los **parámetros** (usuario, clave, servidor, puerto y servicio).

### Oracle Database Express Edition (XE)

La versión **gratuita y ligera** de Oracle: más fácil de instalar y configurar que un Oracle profesional. El vídeo usa la **11g XE**, una versión antigua, pero para lo que se hace aquí la tecnología es la misma. Hoy se descarga la versión actual (**21c XE**) de oracle.com.

Pasos del instalador (*wizard*):

1. Ejecutar `setup` y **Next**.
2. Aceptar los términos de la licencia → **Next**.
3. Elegir la carpeta de instalación → **Next**.
4. Escribir la **clave del administrador** (la de los usuarios `SYS` y `SYSTEM`) → **Next**.
5. **Install**, y al terminar, **Finish**.

### SQL Developer

La herramienta para **administrar los datos** de Oracle (el equivalente de MySQL Workbench). Se descarga de oracle.com, se descomprime y se ejecuta `sqldeveloper.exe` (necesita un JDK, o la versión que lo incluye).

Para conectarse: nueva conexión (flecha verde) → usuario **`system`** y la **clave** del instalador → **Test** (debe decir **Success**) → **Connect**.

Con clic derecho en la conexión → *Propiedades* se ve el **puerto** (**1521**, el de Oracle por defecto) y el servicio (**XE**).

## El controlador: `oracledb` con npm

```bash
npm install oracledb
```

Esto deja una entrada en `package.json`:

```json
"dependencies": { "oracledb": "^26.0.0" }
```

Y se importa en el código:

```javascript
const oracledb = require("oracledb");
```

Por defecto funciona en **modo "Thin"**: está escrito en JavaScript puro y **no necesita instalar el cliente de Oracle**.

Cuando alguien descargue el proyecto, con **`npm install`** se instalan las dependencias que figuran en `package.json`.

## La clase `Conexion`

```javascript
class Conexion {
    constructor() {
        const password = process.env.ORACLE_PASSWORD ?? "oracle";

        this.parametros = {
            user: "system",
            password: password,
            connectString: "localhost:1521/XE",
        };

        this.conexionOracle = null;     // null mientras no se ha abierto
    }
}
```

### Los parámetros de conexión

| Clave | Valor | Significado |
|---|---|---|
| `user` | `system` | El usuario con el que se entró en SQL Developer |
| `password` | *(la clave)* | La clave del administrador de la instalación |
| `connectString` | `localhost:1521/XE` | **Servidor:puerto/servicio** |

El `connectString` tiene tres partes:

```text
localhost : 1521 / XE
│           │      └── el servicio (XE: Express Edition)
│           └───────── el puerto de Oracle
└───────────────────── el servidor: este mismo equipo
```

A diferencia de MySQL, donde se indicaba **la base de datos**, en Oracle se indica **el servicio** y el usuario determina **su esquema** (donde están sus tablas). Con 11g XE el servicio es `XE`; con 18c o 21c XE la base "enchufable" donde se trabaja es **`XEPDB1`**: en ese caso se define la variable de entorno `ORACLE_SERVICE=XEPDB1` antes de ejecutar (por defecto se usa `XE`, como en el vídeo).

> `system` es el administrador: para pruebas está bien, pero en un proyecto real se crea **un usuario propio con los permisos justos**.

### La contraseña no va en el código

El vídeo la escribe en el código porque es un laboratorio. En el ejemplo se lee de una **variable de entorno**, con `oracle` por defecto:

```javascript
const password = process.env.ORACLE_PASSWORD ?? "oracle";
```

Para usar otra clave, antes de ejecutar:

```powershell
$env:ORACLE_PASSWORD = "mi_clave"      # PowerShell
```

Una clave escrita en el código acaba en el repositorio y la ve cualquiera.

## Abrir y cerrar la conexión

```javascript
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
```

- **`oracledb.getConnection(parametros)`** es lo que **realmente se conecta**. Devuelve una **promesa**, de ahí el **`await`** y que el método sea **`async`**.
- **`close()`** cierra el canal. **Siempre hay que cerrar la conexión** para liberar recursos (y mientras haya una abierta, Node.js no termina).
- Los dos van en un **`try/catch`**: el servidor puede estar apagado, la clave puede ser incorrecta...
- En `cerrarConexion` se comprueba que **haya conexión** (`!== null`) para no avisar de "desconectado" si nunca se llegó a conectar (el vídeo no lo hace).

## El programa principal

```javascript
async function main() {
    const conectar = new Conexion();

    await conectar.abrirConexion();
    await conectar.cerrarConexion();
}

main();
```

### Salida esperada

Con Oracle funcionando y la clave correcta:

```text
Iniciando conexión a la base...
Sistema conectado a Oracle Database
Sistema desconectado de la base
```

Sin Oracle (o con la clave mal), sale el motivo:

```text
Iniciando conexión a la base...
Error conectando a la base: NJS-503: connection to host 127.0.0.1 port 1521 could not be established. ...
```

(Los errores de Oracle empiezan con su **código** `ORA-xxxxx` (del servidor) o `NJS-xxx` (del controlador), útil para buscarlos.)

## Ejecutar

```bash
node index.js
```

Requiere Oracle en marcha y `npm install` hecho en `07-ui-and-databases`.

## Comandos

```bash
cd javascript/07-ui-and-databases
npm install
cd 55-database-oracle-connection
node index.js
```

Fuente: [curso de C# de INFORMATICONFIG](https://www.youtube.com/watch?v=oY1LKRXDVE0&list=PL2Z95CSZ1N4F7mym8Ca16RoTDVAlIOPlT&index=55), vídeo 55.
