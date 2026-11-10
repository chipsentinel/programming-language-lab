# Conexión a SQL Server con Node.js

## Qué se aprende

La misma operación de las lecciones 53 (MySQL) y 55 (Oracle), ahora con **SQL Server**, el gestor de Microsoft. Con **Node.js** se usa el paquete **`mssql`**, que se instala con **npm**.

| | MySQL | Oracle | SQL Server |
|---|---|---|---|
| Paquete npm | `mysql2` | `oracledb` | **`mssql`** |
| Conectar | `mysql.createConnection(...)` | `oracledb.getConnection(...)` | **`sql.connect(...)`** |

> Ojo: el **navegador no puede** conectarse directamente a una base de datos (se expondría la clave a cualquiera que abra la página). Esto se hace en **Node.js**, en el servidor.

## Preparar SQL Server

Quien ya tenga una instancia de SQL Server solo necesita conocer los **parámetros**.

### El motor: SQL Server 2022 (Express)

1. En la página de **SQL Server 2022** de Microsoft → **Descargas** → edición **Express** (gratuita, para instalar en el propio equipo). Microsoft pide rellenar un formulario: es un requisito, no es relevante.
2. Ejecutar el instalador y elegir el tipo **Básica**.
3. Aceptar la licencia y la carpeta de instalación.
4. Al terminar muestra el **nombre de la instancia** (`SQLEXPRESS` en Express), las carpetas y la versión.

### La herramienta: SQL Server Management Studio (SSMS)

El equivalente de MySQL Workbench y de SQL Developer. En la misma pantalla final: **Install SSMS** → descargar **SSMS 21** → instalarlo **sin cargas de trabajo adicionales**.

### Conectarse con SSMS

1. Abrirlo desde el menú Inicio. Si pide iniciar sesión con una cuenta: **omitir**.
2. En la ventana de conexión: **Server name** (`localhost`, o `localhost\SQLEXPRESS`), **Authentication: Windows Authentication** y **Encryption: Optional** (si se deja *Mandatory* da un error y no se puede entrar).
3. **Connect**. Si **SQL Server Agent** aparece con una cruz roja: clic derecho → **Start**.

### Crear la base de datos `farmacia`

1. Clic derecho en **Databases** → **New Database** → nombre **`farmacia`** → **OK**.
2. Clic derecho sobre `farmacia` → **New Query**.
3. El script **[farmacia.sql](farmacia.sql)** (en esta carpeta) tiene **dos partes**: la **1** crea tres tablas (debe decir *Commands completed successfully*) y la **2** las llena (20 clientes, 30 productos y 30 ventas). Se pegan y ejecutan por separado.
4. Comprobar: `SELECT * FROM clientes;`.

| Tabla | Campos principales |
|---|---|
| `clientes` | `id_cliente`, `nombre`, `direccion`, `telefono` |
| `productos` | `id_producto`, `nombre`, `descripcion`, `precio`, `stock` |
| `ventas` | `id_venta`, `id_cliente`, `id_producto`, `cantidad`, `fecha` |

### Preparar el servidor para Node.js

En C# se usaba la **autenticación de Windows** (sin usuario ni clave). El paquete `mssql` **no puede** usarla directamente: hay que entrar con **usuario y clave de SQL Server**. Además, se conecta por **TCP/IP** (puerto 1433), que en SQL Server Express suele estar desactivado. Hay que hacer tres cosas:

1. **Autenticación mixta**: en SSMS, clic derecho en el servidor → *Properties* → *Security* → **SQL Server and Windows Authentication mode**.
2. **Un usuario** (*login*): *Security* → *Logins* → *New Login* → autenticación de SQL Server, con su clave; y en *User Mapping*, darle acceso a la base `farmacia` (por ejemplo, el rol `db_datareader`).
3. **Habilitar TCP/IP**: **SQL Server Configuration Manager** → *Configuración de red de SQL Server* → *Protocolos de SQLEXPRESS* → **TCP/IP** → **Habilitar**; en *Direcciones IP* → **IPAll**: **Puerto TCP = 1433**, y **Puertos dinámicos** en blanco.

Después, **reiniciar** el servicio *SQL Server (SQLEXPRESS)*.

## El controlador: `mssql` con npm

```bash
npm install mssql
```

Esto deja una entrada en `package.json`:

```json
"dependencies": { "mssql": "^12.7.14" }
```

Y se importa en el código:

```javascript
const sql = require("mssql");
```

Cuando alguien descargue el proyecto, con **`npm install`** se instalan las dependencias que figuran en `package.json`.

## La clase `Conexion`

```javascript
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

        this.conectar = null;       // null mientras no se ha abierto
    }
}
```

### Los parámetros de conexión

| Clave | Valor | Significado |
|---|---|---|
| `server` | `localhost` | El servidor: el propio equipo |
| `database` | `farmacia` | La base de datos |
| `user` / `password` | *(desde variables de entorno)* | El usuario y la clave de SQL Server |
| `options.trustServerCertificate` | `true` | **Confiar en el certificado** del servidor (si no, falla con un error de certificado; en local no hay problema) |
| `options.encrypt` | `true` | Cifrar la comunicación |
| `options.instanceName` | `SQLEXPRESS` | Solo si el servidor es una **instancia con nombre** |

### Las credenciales no van en el código

Una clave escrita en el código acaba en el repositorio y la ve cualquiera. Por eso se leen de **variables de entorno**:

| Variable | Para qué |
|---|---|
| `SQLSERVER_USER` + `SQLSERVER_PASSWORD` | El usuario y la clave de SQL Server (**obligatorias**) |
| `SQLSERVER_SERVER` | El servidor (por defecto `localhost`) |
| `SQLSERVER_INSTANCE` | La instancia con nombre, si la hay (por ejemplo `SQLEXPRESS`) |

```powershell
$env:SQLSERVER_USER = "mi_usuario"       # PowerShell
$env:SQLSERVER_PASSWORD = "mi_clave"
node index.js
```

## Abrir y cerrar la conexión

```javascript
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
```

- **`sql.connect(parametros)`** es lo que **realmente se conecta**. Devuelve una **promesa**, de ahí el **`await`** y que el método sea **`async`**.
- Se abre solo si **no hay conexión** y se cierra solo si **la hay**. **Siempre hay que cerrarla** (y mientras haya una abierta, Node.js no termina).
- Todo va en un **`try/catch`**. Si falla, el `catch` muestra el motivo en vez de cerrar el programa.
- `abrirConexion` **devuelve** la conexión (o `null` si falló): quien la llame podrá usarla para consultar (lección siguiente).

## Errores típicos (los del vídeo)

Con cada ejecución falla algo distinto, y el `catch` muestra el mensaje:

| Error | Causa |
|---|---|
| *Failed to connect to localhost:1433 - Could not connect* | No hay SQL Server en marcha, TCP/IP está deshabilitado o el **servidor está mal escrito** (como la coma en vez de punto y coma del vídeo) |
| Certificado no confiable | Falta **`trustServerCertificate: true`** |
| *Login failed for user ...* | Usuario o clave incorrectos, o el servidor no está en modo de autenticación mixta |

> Y si el programa **conecta pero no dice nada**, es porque falta el `console.log` de éxito: hay que avisar **siempre** al usuario del resultado.

## El programa principal

```javascript
async function main() {
    const conexion = new Conexion();

    await conexion.abrirConexion();
    await conexion.cerrarConexion();
}

main();
```

### Salida esperada

Con SQL Server funcionando y la base creada:

```text
Iniciando conexión a la base...
Sistema conectado a SQL Server
Sistema desconectado de la base
```

## Ejecutar

```bash
node index.js
```

Requiere SQL Server en marcha, la base `farmacia`, las variables de entorno y `npm install` hecho en `07-ui-and-databases`.

## Comandos

```bash
cd javascript/07-ui-and-databases
npm install
cd 57-database-sqlserver-connection
node index.js
```

Fuente: [curso de C# de INFORMATICONFIG](https://www.youtube.com/watch?v=y0OPYm63iZ0&list=PL2Z95CSZ1N4F7mym8Ca16RoTDVAlIOPlT&index=57), vídeo 57.
