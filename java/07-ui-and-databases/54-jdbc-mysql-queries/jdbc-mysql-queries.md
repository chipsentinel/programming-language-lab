# Consultas a MySQL con JDBC

## Qué se aprende

Con la conexión de la lección anterior, **leer datos** de una tabla con una consulta `SELECT` (equivalente a la lección 54 de ADO.NET):

```sql
SELECT * FROM alumnos;
```

Lo mismo que se hace en MySQL Workbench, pero **desde Java**. Requiere la base `colegio_amanecer` creada con el script de la lección 53.

## Cerrar la conexión: `cerrarConexion`

En la lección 53 solo se **abría**. Cerrarla es **importantísimo**: una conexión abierta consume **memoria, procesador y una de las conexiones limitadas del servidor**.

```java
public void cerrarConexion() {
    try {
        if (conectar != null && !conectar.isClosed()) {
            conectar.close();
            System.out.println("Conexión cerrada");
        }
    } catch (SQLException e) {
        System.out.println("Error al cerrar la conexión: " + e.getMessage());
    }
}
```

Si la conexión está **abierta**, se cierra con **`close()`** y se avisa al usuario.

## La clase `Consulta`

La clase `Conexion` se queda con **una sola tarea**: conectarse (parámetros, abrir, cerrar). Las consultas van en **otra clase**, `Consulta`, que usa a la primera. Así el programa queda más ordenado y profesional.

```java
class Consulta {
    public void consultaAlumnos() {
        Conexion conexionConsulta = new Conexion();        // un objeto de la clase Conexion
        String consulta = "SELECT * FROM alumnos";         // la consulta SQL, en un String
        ...
    }
}
```

## Las clases para consultar

| Clase JDBC | Para qué | En ADO.NET |
|---|---|---|
| **`Statement`** | **Lleva la consulta SQL** por la conexión | `MySqlCommand` |
| **`ResultSet`** | **Las filas** del resultado, que se leen de una en una | `MySqlDataReader` |

```java
try (Statement consultar = conexionConsulta.conectar.createStatement();
     ResultSet datos = consultar.executeQuery(consulta)) {

    while (datos.next()) {
        System.out.println(datos.getString("nombre"));
    }
}
```

- **`conectar.createStatement()`** crea el objeto que envía consultas por esa conexión.
- **`executeQuery(consulta)`** **ejecuta** el `SELECT` y devuelve el `ResultSet` con las filas.
- **`datos.next()`** avanza a la **siguiente fila** y devuelve `false` cuando ya no quedan: por eso se usa como condición de un `while` (lección 18).
- **`getString("nombre")`** / **`getInt("edad")`** devuelven el valor del campo de la fila actual. Se elige el método según el **tipo** del campo (en C# `datos["campo"]` valía para todos).

> Los nombres de los campos se escriben **exactamente como están en la tabla**: si hay un error, la consulta falla.

### `try-with-resources`

Lo que se declara **entre paréntesis** del `try` (`Statement` y `ResultSet`) se **cierra automáticamente** al salir del bloque, aunque haya un error. Es el equivalente del `using` de C#.

## Estructura: `try / catch / finally`

```java
try {
    conexionConsulta.abrirConexion();
    // ... consultar y mostrar ...
} catch (SQLException e) {
    System.out.println("Error consultando datos: " + e.getMessage());
} finally {
    conexionConsulta.cerrarConexion();
}
```

- **`try`**: lo que puede fallar (el servidor, la red, un nombre de campo mal escrito).
- **`catch (SQLException e)`**: captura el error y lo muestra. Todo lo de JDBC lanza `SQLException`.
- **`finally`**: **se ejecuta siempre**, haya error o no. Es el sitio correcto para **cerrar la conexión**: así nunca queda abierta.

`abrirConexion` **devuelve `null`** si no pudo abrir (la lección 53 solo mostraba el error). Antes de consultar se comprueba, y si no hay conexión se lanza una excepción propia, para no llegar a un `NullPointerException`:

```java
if (conexionConsulta.abrirConexion() == null) {
    throw new SQLException("La conexión no está abierta");
}
```

Sin servidor MySQL el resultado es:

```text
Error al abrir la conexión a la base: Communications link failure ...
Error consultando datos: La conexión no está abierta
```

## Mostrar los datos

La tabla `alumnos` tiene muchos campos: `id_alumno`, `nombre`, `apellido`, `edad`, `direccion`, `telefono`, `grado` e `id_profesor`. El vídeo va probando formas de mostrarlos: primero **desordenado**, después **todo pegado**, y finalmente **separado por espacios**.

En el ejemplo se usa **`printf`** para **alinear en columnas**:

```java
System.out.printf("%-4d%-9s%-10s%-5d%-19s%-11s%-9s%d%n",
                  datos.getInt("id_alumno"), datos.getString("nombre"),
                  datos.getString("apellido"), datos.getInt("edad"),
                  datos.getString("direccion"), datos.getString("telefono"),
                  datos.getString("grado"), datos.getInt("id_profesor"));
```

`%-9s` pone el texto **alineado a la izquierda** en un ancho de 9 caracteres; `%-4d`, lo mismo para un entero; `%n` es el salto de línea. Así los datos salen como una tabla:

```text
ID  Nombre   Apellido  Edad Dirección          Teléfono   Grado    Prof.
1   Sol      Vargas    6    Calle Luna 12      600111222  Primero  1
2   Mateo    Ortiz     6    Avenida Sur 5      600222333  Primero  1
...
```

> La consola **no es el editor de la base de datos**: no tiene el formato de tabla de Workbench, y hay que construirlo a mano. En las aplicaciones con ventana (JavaFX) se usan controles de tabla (`TableView`).

Si la consola no muestra bien los acentos en Windows: `java -Dfile.encoding=UTF-8 ...`.

## Pedir solo algunos campos

No siempre hacen falta todos. Pedir **solo `nombre` y `grado`** es más rápido y más claro:

```java
String consulta = "SELECT nombre, grado FROM alumnos";
```

```java
System.out.printf("%-9s%s%n", datos.getString("nombre"), datos.getString("grado"));
```

```text
Nombre   Grado
Sol      Primero
Mateo    Primero
...
```

Se pueden practicar otras consultas, como a la tabla `materias`, o cualquier `SELECT` que se conozca (`WHERE`, `ORDER BY`...).

## El programa principal

```java
Consulta consulta = new Consulta();
consulta.consultaAlumnos();
consulta.consultaNombreGrado();
```

La conexión se **abre dentro de cada consulta** (en el vídeo se llama a `abrirConexion` también en `main` y el mensaje "Conexión abierta" sale dos veces; sobra una).

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
java -cp mysql-connector-j-8.4.0.jar JdbcMysqlQueries.java
```

Requiere MySQL en marcha, la base `colegio_amanecer` (lección 53) y el conector `.jar` en esta carpeta.

## Comandos

```bash
cd java/07-ui-and-databases/54-jdbc-mysql-queries
# descargar mysql-connector-j-8.4.0.jar en esta carpeta y:
java -cp mysql-connector-j-8.4.0.jar JdbcMysqlQueries.java
```

Fuente: [curso de C# de INFORMATICONFIG](https://www.youtube.com/watch?v=YfN6rKFC0hk&list=PL2Z95CSZ1N4F7mym8Ca16RoTDVAlIOPlT&index=54), vídeo 54.
