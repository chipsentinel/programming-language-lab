# Consultas a Oracle con JDBC

## Qué se aprende

Con la conexión de la lección 55, **consultar tablas** de una base Oracle desde Java: el equivalente a la lección 56 de ADO.NET. Con JDBC las clases son **las mismas que con MySQL** (`Statement`, `ResultSet`): solo cambian la URL y el controlador.

| Paso | MySQL (lección 54) | Oracle (esta lección) |
|---|---|---|
| Comando | `Statement` | `Statement` (igual) |
| Lector | `ResultSet` | `ResultSet` (igual) |
| Campo de una fila | `getString("nombre")` (por nombre) | **`getString(2)`** (por posición y tipo) |

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

## La clase `Conexion`: dos cambios

```java
public Connection conexionOracle;          // antes private

private final String usuario = "libreria"; // antes system
```

1. **`public`**: la variable se va a usar **desde otra clase** (`Consultas`). Cuando es `private` el compilador da error de **inaccesible**: es la **encapsulación** (lección 37).
2. **El usuario es `libreria`**, no `system`: las tablas están en **su** esquema.

### El error `ORA-00942`

Si se conecta con `system` (como en la lección 55), la conexión **funciona**, pero la consulta falla:

```text
Error consultando datos: ORA-00942: la tabla o vista no existe
```

La tabla `libros` **no existe en el esquema `SYSTEM`**: está en `LIBRERIA`. Es el error más común al empezar con Oracle: se **conecta** al usuario equivocado.

## La clase `Consultas`

Una clase para las consultas, separada de `Conexion` (que solo se ocupa de conectar):

```java
class Consultas {
    public void consultaLibros() {
        Conexion conectar = new Conexion();     // un objeto de la clase Conexion
        ...
    }
}
```

## Consultar y leer

```java
String consulta = "SELECT * FROM libros";

try (Statement comando = conectar.conexionOracle.createStatement();
     ResultSet lectura = comando.executeQuery(consulta)) {

    while (lectura.next()) {
        int id = lectura.getInt(1);
        String titulo = lectura.getString(2);
        int autor = lectura.getInt(3);
        LocalDate fecha = lectura.getDate(4).toLocalDate();
        String genero = lectura.getString(5);
        ...
    }
}
```

- **`createStatement()`** crea el objeto que envía la consulta; **`executeQuery(consulta)`** la ejecuta y devuelve el `ResultSet`.
- **`lectura.next()`** avanza a la siguiente fila y devuelve `false` cuando no quedan (lección 18, `while`).
- El **`try-with-resources`** cierra el `Statement` y el `ResultSet` automáticamente.

### Leer por posición y tipo

Cada campo se pide por su **posición** en la tabla y con el **método que corresponde a su tipo**. **En JDBC la primera posición es la 1**, no la 0 (en ADO.NET empezaba en 0):

| Posición | Campo | Tipo | Método |
|---|---|---|---|
| 1 | `id_libro` | número entero | `getInt(1)` |
| 2 | `titulo` | texto | `getString(2)` |
| 3 | `id_autor` | número entero | `getInt(3)` |
| 4 | `fecha_publicacion` | fecha | `getDate(4)` |
| 5 | `genero` | texto | `getString(5)` |

También se puede leer **por nombre** (`getString("titulo")`), como en MySQL. En Oracle los nombres de las columnas están **en mayúsculas** internamente, pero JDBC no distingue mayúsculas al buscarlos.

### Formatear la fecha

`getDate` devuelve un **`java.sql.Date`** (la clase antigua). Con **`toLocalDate()`** se pasa a **`LocalDate`** (lección 40), que al imprimirse sale **año-mes-día** (`1967-06-05`).

### Mostrar como tabla

```java
System.out.printf("%-4d%-50s%-7d%-12s%s%n", id, titulo, autor, fecha, genero);
```

`%-50s` alinea a la izquierda en 50 caracteres. (El vídeo usa tabuladores, que no siempre quedan alineados porque los títulos tienen longitudes distintas.)

```text
ID  Título                                            Autor  Fecha       Género
--------------------------------------------------------------------------------
1   Cien años de soledad                              1      1967-06-05  Novela
2   El amor en los tiempos del cólera                 1      1985-12-05  Novela
...
```

Si la consola no muestra bien los acentos en Windows: `java -Dfile.encoding=UTF-8 ...`.

## `try / catch / finally`

```java
} catch (SQLException error) {
    System.out.println("Error consultando datos: " + error.getMessage());
} finally {
    conectar.cerrarConexion();
}
```

- **`catch`** muestra el error.
- **`finally`** se ejecuta **siempre**: la conexión se cierra pase lo que pase.

Por eso en `main` **no se llama a `cerrarConexion`** ni a `abrirConexion`: la consulta abre y cierra la conexión por su cuenta.

Si `abrirConexion` no pudo conectar, la variable queda en `null`: se comprueba antes de consultar para no llegar a un `NullPointerException`.

## El programa principal

```java
Consultas consultar = new Consultas();
consultar.consultaLibros();
```

## Ejecutar

```bash
java -cp ojdbc11-23.26.4.0.0.jar JdbcOracleQueries.java
```

Requiere Oracle en marcha, el usuario `libreria` y sus tablas, y el controlador `.jar`.

## Comandos

```bash
cd java/07-ui-and-databases/56-jdbc-oracle-queries
# descargar ojdbc11-23.26.4.0.0.jar en esta carpeta y:
java -cp ojdbc11-23.26.4.0.0.jar JdbcOracleQueries.java
```

Fuente: [curso de C# de INFORMATICONFIG](https://www.youtube.com/watch?v=syTRJU5tvLY&list=PL2Z95CSZ1N4F7mym8Ca16RoTDVAlIOPlT&index=56), vídeo 56.
