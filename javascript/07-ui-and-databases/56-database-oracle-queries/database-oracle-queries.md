# Consultas a Oracle con Node.js

## Qué se aprende

Con la conexión de la lección 55, **consultar tablas** de una base Oracle desde Node.js: el equivalente a la lección 56 de ADO.NET.

| Paso | MySQL (lección 54) | Oracle (esta lección) |
|---|---|---|
| Enviar la consulta | `conectar.query(sql)` | **`conexionOracle.execute(sql)`** |
| Las filas | array de **objetos** | por defecto, array de **arrays** (`resultado.rows`) |
| Campo de una fila | `datos.nombre` (por nombre) | **`lectura[1]`** (por posición) |

## Esquemas y usuarios en Oracle

En SQL Developer, el **explorador de objetos** muestra la conexión con el usuario `system`. En bases de datos, a ese usuario con todo lo suyo (tablas, vistas, índices...) se le llama **esquema**: el esquema `SYSTEM`. En Oracle **cada usuario tiene su propio esquema**, y las tablas pertenecen al esquema donde se crean.

Dentro del esquema, la sección **Other Users** muestra los usuarios que vienen creados por defecto. Se va a crear uno nuevo para la librería: el usuario **`libreria`**.

## Preparar el entorno

El script **[libreria.sql](libreria.sql)** (en esta carpeta) tiene **dos partes**:

### Parte 1: crear el usuario (conectado como `system`)

```sql
CREATE USER libreria IDENTIFIED BY oracle;
GRANT CREATE SESSION TO libreria;
GRANT CREATE TABLE, CREATE VIEW, CREATE SEQUENCE TO libreria;
```

- La **clave** (`oracle`) se puede cambiar por la que se prefiera.
- `CREATE SESSION` permite **conectarse**; los demás permisos permiten **crear objetos** en su esquema.

Se pega en el editor de SQL Developer y se ejecuta con **Run Script** (F5).

### Conectarse con el nuevo usuario

Se crea una **conexión nueva** (la cruz verde): nombre (por ejemplo, `base libreria`, solo una etiqueta), usuario **`libreria`** y su clave → **Test** (**Success**) → **Connect**.

> El **nombre de la conexión** (`base libreria`) **no es** el nombre del usuario/esquema (`libreria`). Para el programa, lo que vale es el **usuario**.

### Parte 2: crear las tablas y los datos (conectado como `libreria`)

| Tabla | Campos |
|---|---|
| `autores` | `id_autor`, `nombre`, `apellido`, `nacionalidad`, `libros_publicados` |
| `libros` | `id_libro`, `titulo`, **`id_autor`**, `fecha_publicacion`, `genero` |

Cada libro se **relaciona con su autor** por el campo `id_autor`. Se comprueba con `SELECT * FROM libros;`.

> Con **Oracle 18c/21c XE** hay que conectarse al servicio **`XEPDB1`** (no `XE`) para crear usuarios y tablas, y definir `ORACLE_SERVICE=XEPDB1` al ejecutar el programa.

## La clase `Conexion`: un cambio

```javascript
user: "libreria",      // antes system
```

**El usuario es `libreria`**, no `system`: las tablas están en **su** esquema.

(En C# y Java la variable de la conexión pasaba de `private` a `public` para usarla desde la clase `Consultas`. En JavaScript **no hace falta**: las propiedades de un objeto ya son accesibles desde fuera.)

### El error `ORA-00942`

Si se conecta con `system` (como en la lección 55), la conexión **funciona**, pero la consulta falla:

```text
Error consultando datos: ORA-00942: table or view does not exist
```

La tabla `libros` **no existe en el esquema `SYSTEM`**: está en `LIBRERIA`. Es el error más común al empezar con Oracle: se **conecta** al usuario equivocado.

## La clase `Consultas`

Una clase para las consultas, separada de `Conexion` (que solo se ocupa de conectar):

```javascript
class Consultas {
    async consultaLibros() {
        const conectar = new Conexion();     // un objeto de la clase Conexion
        ...
    }
}
```

## Consultar y leer

```javascript
const consulta = "SELECT * FROM libros";

const resultado = await conectar.conexionOracle.execute(consulta);

for (const lectura of resultado.rows) {
    const id = lectura[0];
    const titulo = lectura[1];
    const autor = lectura[2];
    const fecha = lectura[3];
    const genero = lectura[4];
    ...
}
```

- **`execute(consulta)`** **envía** el `SELECT` y devuelve (con `await`) un objeto cuyo **`rows`** es un **array de filas**.
- Por defecto **cada fila es otro array**, con los campos en el **orden de la tabla**.
- Se recorren con un `for...of` (lección 19).

### Leer por posición

Cada campo se pide por su **posición** en la tabla, **empezando en 0** (como en ADO.NET):

| Posición | Campo | Tipo en JavaScript |
|---|---|---|
| 0 | `id_libro` | `number` |
| 1 | `titulo` | `string` |
| 2 | `id_autor` | `number` |
| 3 | `fecha_publicacion` | **`Date`** |
| 4 | `genero` | `string` |

Aquí **no hay un método por tipo**: el controlador convierte cada campo al tipo de JavaScript que corresponde (los `NUMBER` a `number`, los `DATE` a objetos `Date`).

> Se puede pedir que las filas lleguen como **objetos** (`{ ID_LIBRO: 1, TITULO: "...", ... }`, con los nombres **en mayúsculas**) con la opción `{ outFormat: oracledb.OUT_FORMAT_OBJECT }`.

### Formatear la fecha

La fecha llega como un objeto `Date`. Para mostrarla **año-mes-día**:

```javascript
const fechaTexto = fecha.getFullYear() + "-" +
                   String(fecha.getMonth() + 1).padStart(2, "0") + "-" +
                   String(fecha.getDate()).padStart(2, "0");
```

- `getMonth()` empieza en **0** (enero = 0), por eso el `+ 1`.
- `padStart(2, "0")` rellena con ceros a la izquierda (`6` → `"06"`).

### Mostrar como tabla

```javascript
console.log(String(id).padEnd(4) + titulo.padEnd(50) + String(autor).padEnd(7) +
            fechaTexto.padEnd(12) + genero);
```

`padEnd(50)` rellena con espacios a la derecha hasta 50 caracteres. (El vídeo usa tabuladores, que no siempre quedan alineados porque los títulos tienen longitudes distintas.)

```text
ID  Título                                            Autor  Fecha       Género
--------------------------------------------------------------------------------
1   Cien años de soledad                              1      1967-06-05  Novela
2   El amor en los tiempos del cólera                 1      1985-12-05  Novela
...
```

## `try / catch / finally`

```javascript
} catch (error) {
    console.log("Error consultando datos: " + (error.message || error.code));
} finally {
    await conectar.cerrarConexion();
}
```

- **`catch`** muestra el error.
- **`finally`** se ejecuta **siempre**: la conexión se cierra pase lo que pase.

Por eso en `main` **no se llama a `cerrarConexion`** ni a `abrirConexion`: la consulta abre y cierra la conexión por su cuenta.

Si `abrirConexion` no pudo conectar, `conexionOracle` queda en `null`: se comprueba antes de consultar para no llegar a un `TypeError`.

## El programa principal

```javascript
const consultar = new Consultas();
await consultar.consultaLibros();
```

## Ejecutar

```bash
node index.js
```

Requiere Oracle en marcha, el usuario `libreria` y sus tablas, y `npm install` hecho en `07-ui-and-databases`.

## Comandos

```bash
cd javascript/07-ui-and-databases
npm install
cd 56-database-oracle-queries
node index.js
```

Fuente: [curso de C# de INFORMATICONFIG](https://www.youtube.com/watch?v=syTRJU5tvLY&list=PL2Z95CSZ1N4F7mym8Ca16RoTDVAlIOPlT&index=56), vídeo 56.
