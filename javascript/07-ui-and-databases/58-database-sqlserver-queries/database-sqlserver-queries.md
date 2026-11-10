# Consultas a SQL Server con Node.js

## Qué se aprende

Con la conexión de la lección 57, **leer datos** de una tabla de SQL Server con un `SELECT` (equivalente a la lección 58 de ADO.NET):

```sql
SELECT nombre, direccion, telefono FROM clientes;
```

Lo mismo que se ve en SQL Server Management Studio, pero **desde Node.js**. Requiere la base `farmacia` de la lección 57 y `npm install` hecho en la carpeta `07-ui-and-databases`.

| Paso | MySQL (54) | Oracle (56) | SQL Server (58) |
|---|---|---|---|
| Enviar la consulta | `conectar.query(sql)` | `conexionOracle.execute(sql)` | **`con.request().query(sql)`** |
| Las filas | array de objetos | array de arrays | array de **objetos** (`resultado.recordset`) |
| Campo de una fila | `datos.nombre` | `lectura[1]` | **`lectura.nombre`** |

## La clase `Consultas`

La clase `Conexion` se queda con **una sola tarea**: conectarse (parámetros, abrir, cerrar). Las consultas van en **otra clase**, `Consultas`, que la usa. Así no hay que repetir el proceso de conexión cada vez.

```javascript
class Consultas {
    async consultaClientes() {
        const conectar = new Conexion();      // un objeto de la clase Conexion
        ...
    }
}
```

> En el vídeo la consulta se hace **dentro del constructor** de `Consultas`: al crear el objeto, ya consulta. Funciona, pero un constructor es para **preparar** el objeto (y no puede ser `async`); aquí se usa un **método** con nombre (`consultaClientes`), que se llama cuando se necesita.

## Abrir, consultar y leer

```javascript
const con = await conectar.abrirConexion();
if (con === null) {
    throw new Error("La conexión no está abierta");
}

const consulta = "SELECT nombre, direccion, telefono FROM clientes";

const resultado = await con.request().query(consulta);

for (const lectura of resultado.recordset) {
    const nombre = lectura.nombre;
    ...
}
```

- **`conectar.abrirConexion()`** devuelve la conexión ya abierta (o `null` si falló: se comprueba para no llegar a un `TypeError`).
- **`con.request()`** crea una petición sobre esa conexión; **`.query(consulta)`** la **envía** y devuelve (con `await`) un objeto con los resultados.
- **`resultado.recordset`** es un **array con un objeto por fila**: `{ nombre: "Sol Vargas", direccion: "...", telefono: "..." }`. Aquí las filas **ya llegan todas juntas** (en C# se leían de una en una con `Read()`), y se recorren con un `for...of` (lección 19).
- **`lectura.nombre`** es el valor del campo `nombre`; los campos de texto ya llegan **como `string`**.

> Los nombres de los campos se escriben **exactamente como están en la tabla**: si hay un error, el campo sale `undefined` y falla al formatearlo.

### Pedir solo los campos necesarios

El vídeo cambia el `SELECT *` por **`nombre, direccion, telefono`**: el **ID** casi nunca se muestra en las aplicaciones, y pedir menos columnas es más rápido y más claro.

## Dar formato de tabla a la consola

La consola no tiene el formato de tabla de SSMS: hay que construirlo.

```javascript
console.log("Nombre".padEnd(20) + " | " + "Dirección".padEnd(30) + " | " + "Teléfono".padEnd(15));
console.log("-".repeat(70));
```

**`padEnd(n)`** rellena el texto con espacios **a la derecha** hasta tener `n` caracteres (en C# era `{0,-20}`):

| Campo | Ancho |
|---|---|
| nombre | 20 |
| dirección | 30 |
| teléfono | 15 |

- Las **barras verticales** `|` separan las columnas.
- **`"-".repeat(70)`** crea un texto de 70 guiones: la **línea** bajo los encabezados.
- Con **el mismo formato** se imprime cada fila, para que las columnas queden alineadas con los encabezados.

```text
Nombre               | Dirección                      | Teléfono
----------------------------------------------------------------------
Sol Vargas           | Calle Luna 34                  | 610000000
Mateo Ortiz          | Avenida Sur 12                 | 611111111
...
```

> Node.js también tiene `console.table(resultado.recordset)`, que dibuja una tabla con las filas de una vez.

## `try / catch / finally`

```javascript
} catch (error) {
    console.log("Error consultando datos: " + (error.message || error.code));
} finally {
    await conectar.cerrarConexion();
}
```

- **`catch`** muestra el error (una tabla inexistente, un campo sin valor...).
- **`finally`** se ejecuta **siempre**: la conexión se cierra pase lo que pase.

Por eso en `main` **no se llama a `abrirConexion` ni a `cerrarConexion`**: la consulta lo hace por su cuenta.

Sin SQL Server (o sin las variables de entorno) el resultado es:

```text
Error conectando a la base: Defina las variables de entorno SQLSERVER_USER y SQLSERVER_PASSWORD
Error consultando datos: La conexión no está abierta
```

## El programa principal

```javascript
async function main() {
    const consultar = new Consultas();
    await consultar.consultaClientes();
}

main();
```

## Ejercicios propuestos

- Consultar la tabla **`productos`** (nombre, precio y stock).
- Consultar las **`ventas`** y mostrar el **precio con dos decimales** (`toFixed(2)`).
- Probar un `WHERE`: `SELECT ... FROM productos WHERE precio > 5`.

## Ejecutar

```bash
node index.js
```

Requiere SQL Server en marcha (con TCP/IP habilitado), la base `farmacia`, las variables de entorno `SQLSERVER_USER` y `SQLSERVER_PASSWORD` (lección 57) y `npm install` hecho en `07-ui-and-databases`.

## Comandos

```bash
cd javascript/07-ui-and-databases
npm install
cd 58-database-sqlserver-queries
node index.js
```

Fuente: [curso de C# de INFORMATICONFIG](https://www.youtube.com/watch?v=10cKRN4kTAk&list=PL2Z95CSZ1N4F7mym8Ca16RoTDVAlIOPlT&index=58), vídeo 58.
