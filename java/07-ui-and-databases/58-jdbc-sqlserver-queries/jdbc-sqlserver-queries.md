# Consultas a SQL Server con JDBC

## Qué se aprende

Con la conexión de la lección 57, **leer datos** de una tabla de SQL Server con un `SELECT` (equivalente a la lección 58 de ADO.NET):

```sql
SELECT nombre, direccion, telefono FROM clientes;
```

Lo mismo que se ve en SQL Server Management Studio, pero **desde Java**. Requiere la base `farmacia` creada con el script de la lección 57.

Con JDBC las clases son **las mismas para todos los gestores**:

| Paso | JDBC | ADO.NET (SQL Server) |
|---|---|---|
| Comando | `Statement` | `SqlCommand` |
| Lector | `ResultSet` | `SqlDataReader` |
| Campo de una fila | `getString("nombre")` | `lectura["nombre"]` |

## La clase `Consultas`

La clase `Conexion` se queda con **una sola tarea**: conectarse (parámetros, abrir, cerrar). Las consultas van en **otra clase**, `Consultas`, que la usa. Así no hay que repetir el proceso de conexión cada vez.

```java
class Consultas {
    public void consultaClientes() {
        Conexion conectar = new Conexion();      // un objeto de la clase Conexion
        ...
    }
}
```

> En el vídeo la consulta se hace **dentro del constructor** de `Consultas`: al crear el objeto, ya consulta. Funciona, pero un constructor es para **preparar** el objeto; aquí se usa un **método** con nombre (`consultaClientes`), que se llama cuando se necesita.

## Abrir, consultar y leer

```java
Connection con = conectar.abrirConexion();
if (con == null) {
    throw new SQLException("La conexión no está abierta");
}

String consulta = "SELECT nombre, direccion, telefono FROM clientes";

try (Statement comando = con.createStatement();
     ResultSet lectura = comando.executeQuery(consulta)) {

    while (lectura.next()) {
        String nombre = lectura.getString("nombre");
        ...
    }
}
```

- **`conectar.abrirConexion()`** devuelve la `Connection` ya abierta (o `null` si falló: se comprueba para no llegar a un `NullPointerException`). El error del vídeo: pasar al comando el **objeto `Conexion`** en vez de la **conexión**: el compilador avisa de que no puede convertir un tipo en el otro.
- **`createStatement()`** crea el objeto que envía la consulta; **`executeQuery(consulta)`** la ejecuta y devuelve el `ResultSet`.
- **`lectura.next()`** avanza a la siguiente fila y devuelve `false` cuando no quedan (lección 18, `while`).
- **`getString("nombre")`** es el valor del campo `nombre` **como texto** (en C# había que llamar a `ToString()`).

> Los nombres de los campos se escriben **exactamente como están en la tabla**: si hay un error, la consulta falla (lanza una `SQLException`).

### Pedir solo los campos necesarios

El vídeo cambia el `SELECT *` por **`nombre, direccion, telefono`**: el **ID** casi nunca se muestra en las aplicaciones, y pedir menos columnas es más rápido y más claro.

### `try-with-resources`

Lo que se declara **entre paréntesis** del `try` (`Statement` y `ResultSet`) se **cierra automáticamente** al salir del bloque, aunque haya un error. Es el equivalente del `using` de C# (el vídeo cierra el lector a mano con `lectura.Close()`).

## Dar formato de tabla a la consola

La consola no tiene el formato de tabla de SSMS: hay que construirlo.

```java
System.out.printf("%-20s | %-30s | %-15s%n", "Nombre", "Dirección", "Teléfono");
System.out.println("-".repeat(70));
```

Cada `%-ancho s` indica que el valor es **texto** (`s`), **alineado a la izquierda** (`-`) en **ese número de caracteres**:

| Marcador | Valor | Ancho |
|---|---|---|
| `%-20s` | el **primer** argumento (nombre) | 20 |
| `%-30s` | el **segundo** (dirección) | 30 |
| `%-15s` | el **tercero** (teléfono) | 15 |

- Las **barras verticales** `|` separan las columnas, y `%n` es el salto de línea.
- **`"-".repeat(70)`** crea un texto de 70 guiones: la **línea** bajo los encabezados (en C#, `new string('-', 70)`).
- Con **el mismo formato** se imprime cada fila, para que las columnas queden alineadas con los encabezados.

```text
Nombre               | Dirección                      | Teléfono
----------------------------------------------------------------------
Sol Vargas           | Calle Luna 34                  | 610000000
Mateo Ortiz          | Avenida Sur 12                 | 611111111
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

- **`catch`** muestra el error (un nombre de campo mal escrito, una tabla inexistente...).
- **`finally`** se ejecuta **siempre**: la conexión se cierra pase lo que pase.

Por eso en `main` **no se llama a `abrirConexion` ni a `cerrarConexion`**: la consulta lo hace por su cuenta.

Sin SQL Server el resultado es:

```text
Error conectando a la base: The TCP/IP connection to the host localhost, port 1433 has failed. ...
Error consultando datos: La conexión no está abierta
```

## El programa principal

```java
Consultas consultar = new Consultas();
consultar.consultaClientes();
```

## Ejercicios propuestos

- Consultar la tabla **`productos`** (nombre, precio y stock).
- Consultar las **`ventas`** y mostrar el **precio con dos decimales** (`%.2f`).
- Probar un `WHERE`: `SELECT ... FROM productos WHERE precio > 5`.

## Ejecutar

```bash
java -cp mssql-jdbc-12.10.2.jre11.jar JdbcSqlserverQueries.java
```

Requiere SQL Server en marcha (con TCP/IP habilitado), la base `farmacia` (lección 57) y el controlador `.jar` en esta carpeta.

## Comandos

```bash
cd java/07-ui-and-databases/58-jdbc-sqlserver-queries
# descargar mssql-jdbc-12.10.2.jre11.jar en esta carpeta y:
java -cp mssql-jdbc-12.10.2.jre11.jar JdbcSqlserverQueries.java
```

Fuente: [curso de C# de INFORMATICONFIG](https://www.youtube.com/watch?v=10cKRN4kTAk&list=PL2Z95CSZ1N4F7mym8Ca16RoTDVAlIOPlT&index=58), vídeo 58.
