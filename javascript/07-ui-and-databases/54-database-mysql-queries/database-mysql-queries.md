# Consultas a MySQL con Node.js

## Qué se aprende

Con la conexión de la lección anterior, **leer datos** de una tabla con una consulta `SELECT` (equivalente a la lección 54 de ADO.NET):

```sql
SELECT * FROM alumnos;
```

Lo mismo que se hace en MySQL Workbench, pero **desde JavaScript (Node.js)**. Requiere la base `colegio_amanecer` creada con el script de la lección 53 y `npm install` hecho en la carpeta `07-ui-and-databases`.

## Cerrar la conexión: `cerrarConexion`

En la lección 53 solo se **abría**. Cerrarla es **importantísimo**: una conexión abierta consume **memoria, procesador y una de las conexiones limitadas del servidor**, y además **Node.js no termina** mientras haya una abierta.

```javascript
async cerrarConexion() {
    if (this.conectar !== null) {
        await this.conectar.end();
        this.conectar = null;
        console.log("Conexión cerrada");
    }
}
```

Si hay una conexión abierta, se cierra con **`end()`** y se avisa al usuario.

## La clase `Consulta`

La clase `Conexion` se queda con **una sola tarea**: conectarse (parámetros, abrir, cerrar). Las consultas van en **otra clase**, `Consulta`, que usa a la primera. Así el programa queda más ordenado y profesional.

```javascript
class Consulta {
    async consultaAlumnos() {
        const conexionConsulta = new Conexion();        // un objeto de la clase Conexion
        const consulta = "SELECT * FROM alumnos";       // la consulta SQL, en un string
        ...
    }
}
```

## Consultar: `query`

```javascript
const [filas] = await conexionConsulta.conectar.query(consulta);

for (const datos of filas) {
    console.log(datos.nombre);
}
```

- **`conectar.query(consulta)`** **envía** el `SELECT` por la conexión y devuelve (con `await`) un array de dos elementos: **`[filas, campos]`**. Se queda con las filas con la **desestructuración** `const [filas] = ...`.
- **`filas`** es un **array con un objeto por fila**: `{ id_alumno: 1, nombre: "Sol", apellido: "Vargas", ... }`. Es lo que en C# hacía el `MySqlDataReader` fila a fila y en Java el `ResultSet`: aquí ya **llegan todas juntas** y se recorren con un `for...of` (lección 19).
- **`datos.nombre`** es el valor del campo `nombre` de esa fila.

| | Node.js (`mysql2`) | JDBC | ADO.NET |
|---|---|---|---|
| Enviar la consulta | `conectar.query(sql)` | `Statement.executeQuery(sql)` | `MySqlCommand.ExecuteReader()` |
| Las filas | array de objetos | `ResultSet` + `next()` | `MySqlDataReader` + `Read()` |
| Un campo | `datos.nombre` | `datos.getString("nombre")` | `datos["nombre"]` |

> Los nombres de los campos se escriben **exactamente como están en la tabla**: si hay un error, el campo sale `undefined`.

## Estructura: `try / catch / finally`

```javascript
try {
    if (await conexionConsulta.abrirConexion() === null) {
        throw new Error("La conexión no está abierta");
    }
    // ... consultar y mostrar ...
} catch (e) {
    console.log("Error consultando datos: " + (e.message || e.code));
} finally {
    await conexionConsulta.cerrarConexion();
}
```

- **`try`**: lo que puede fallar (el servidor, la red, un nombre de tabla mal escrito).
- **`catch`**: captura el error y lo muestra.
- **`finally`**: **se ejecuta siempre**, haya error o no. Es el sitio correcto para **cerrar la conexión**: así nunca queda abierta.

`abrirConexion` **devuelve `null`** si no pudo abrir (la lección 53 solo mostraba el error). Antes de consultar se comprueba, y si no hay conexión se lanza un error propio, para no llegar a un `TypeError` ("Cannot read properties of null").

Sin servidor MySQL el resultado es:

```text
Error al abrir la conexión a la base: ECONNREFUSED
Error consultando datos: La conexión no está abierta
```

## Mostrar los datos

La tabla `alumnos` tiene muchos campos: `id_alumno`, `nombre`, `apellido`, `edad`, `direccion`, `telefono`, `grado` e `id_profesor`. El vídeo va probando formas de mostrarlos: primero **desordenado**, después **todo pegado**, y finalmente **separado por espacios**.

En el ejemplo se usa **`padEnd`** para **alinear en columnas**:

```javascript
console.log(String(datos.id_alumno).padEnd(4) + datos.nombre.padEnd(9) + datos.apellido.padEnd(10) +
            String(datos.edad).padEnd(5) + datos.direccion.padEnd(19) + datos.telefono.padEnd(11) +
            datos.grado.padEnd(9) + datos.id_profesor);
```

`padEnd(9)` rellena con espacios **a la derecha** hasta tener 9 caracteres. Solo existe en `string`: los números hay que convertirlos con `String(...)`. Así los datos salen como una tabla:

```text
ID  Nombre   Apellido  Edad Dirección          Teléfono   Grado    Prof.
1   Sol      Vargas    6    Calle Luna 12      600111222  Primero  1
2   Mateo    Ortiz     6    Avenida Sur 5      600222333  Primero  1
...
```

> La consola **no es el editor de la base de datos**: no tiene el formato de tabla de Workbench, y hay que construirlo a mano. (Node.js también tiene `console.table(filas)`, que dibuja una tabla con las filas de una vez.)

## Pedir solo algunos campos

No siempre hacen falta todos. Pedir **solo `nombre` y `grado`** es más rápido y más claro:

```javascript
const consulta = "SELECT nombre, grado FROM alumnos";
```

```javascript
console.log(datos.nombre.padEnd(9) + datos.grado);
```

```text
Nombre   Grado
Sol      Primero
Mateo    Primero
...
```

Se pueden practicar otras consultas, como a la tabla `materias`, o cualquier `SELECT` que se conozca (`WHERE`, `ORDER BY`...).

## El programa principal

```javascript
const consulta = new Consulta();
await consulta.consultaAlumnos();
await consulta.consultaNombreGrado();
```

El `await` hace que la segunda consulta **espere** a que termine la primera. La conexión se **abre dentro de cada consulta** (en el vídeo se llama a `abrirConexion` también en `main` y el mensaje "Conexión abierta" sale dos veces; sobra una).

Con MySQL funcionando:

```text
Iniciando conexión...
Conexión abierta

ID  Nombre   Apellido  ...
...
Conexión cerrada
Conexión abierta

Nombre   Grado
...
Conexión cerrada
```

## Ejecutar

```bash
node index.js
```

Requiere MySQL en marcha, la base `colegio_amanecer` (lección 53) y `npm install` hecho en `07-ui-and-databases`.

## Comandos

```bash
cd javascript/07-ui-and-databases
npm install
cd 54-database-mysql-queries
node index.js
```

Fuente: [curso de C# de INFORMATICONFIG](https://www.youtube.com/watch?v=YfN6rKFC0hk&list=PL2Z95CSZ1N4F7mym8Ca16RoTDVAlIOPlT&index=54), vídeo 54.
