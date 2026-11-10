# Consultas a MySQL con ADO.NET

## Qué se aprende

Con la conexión de la lección anterior, **leer datos** de una tabla con una consulta `SELECT`:

```sql
SELECT * FROM alumnos;
```

Lo mismo que se hace en MySQL Workbench, pero **desde C#**. Requiere la base `colegio_amanecer` creada con el script de la lección 53.

## Cerrar la conexión: `CerrarConexion`

En la lección 53 solo se **abría**. Cerrarla es **importantísimo**: una conexión abierta consume **memoria, procesador y una de las conexiones limitadas del servidor**.

```csharp
public void CerrarConexion()
{
    if (conectar.State == ConnectionState.Open)
    {
        conectar.Close();
        Console.WriteLine("Conexión cerrada");
    }
}
```

Si la conexión está **abierta** (`ConnectionState.Open`), se cierra con **`Close()`** y se avisa al usuario.

## La clase `Consulta`

La clase `Conexion` se queda con **una sola tarea**: conectarse (parámetros, abrir, cerrar). Las consultas van en **otra clase**, `Consulta`, que usa a la primera. Así el programa queda más ordenado y profesional.

```csharp
class Consulta
{
    public void ConsultaAlumnos()
    {
        Conexion conexionConsulta = new Conexion();     // un objeto de la clase Conexion
        string consulta = "SELECT * FROM alumnos";      // la consulta SQL, en un string
        ...
    }
}
```

Como `AbrirConexion` pertenece a otra clase, se crea **un objeto de `Conexion`** y se invoca con él: `conexionConsulta.AbrirConexion()`.

## Las clases para consultar

| Clase | Para qué |
|---|---|
| **`MySqlCommand`** | **Lleva la consulta SQL** y la conexión por la que se envía |
| **`MySqlDataReader`** | **Lee las filas** del resultado, de una en una |

```csharp
using (MySqlCommand consultar = new MySqlCommand(consulta, conexionConsulta.conectar))
using (MySqlDataReader datos = consultar.ExecuteReader())
{
    while (datos.Read())
    {
        Console.WriteLine(datos["nombre"]);
    }
}
```

- **`new MySqlCommand(consulta, conexion)`**: la consulta y la conexión (`conectar`, la variable de la clase `Conexion`, accesible con `conexionConsulta.conectar`).
- **`ExecuteReader()`** **ejecuta** la consulta y devuelve el lector con las filas, convertidas en objetos de C#.
- **`datos.Read()`** avanza a la **siguiente fila** y devuelve `false` cuando ya no quedan: por eso se usa como condición de un `while` (lección 18).
- **`datos["nombre"]`** devuelve el valor del campo `nombre` de la fila actual.

> Los nombres de los campos se escriben **exactamente como están en la tabla**: si hay un error, la consulta falla.

### `using`

`using (...)` **cierra y libera** el comando y el lector automáticamente al salir del bloque, aunque haya un error. Equivale a llamar a `Dispose()` a mano.

## Estructura: `try / catch / finally`

```csharp
try
{
    conexionConsulta.AbrirConexion();
    // ... consultar y mostrar ...
}
catch (Exception e)
{
    Console.WriteLine("Error consultando datos: " + e.Message);
}
finally
{
    conexionConsulta.CerrarConexion();
}
```

- **`try`**: lo que puede fallar (el servidor, la red, un nombre de campo mal escrito).
- **`catch`**: captura el error y lo muestra.
- **`finally`**: **se ejecuta siempre**, haya error o no. Es el sitio correcto para **cerrar la conexión**: así nunca queda abierta.

Sin servidor MySQL el resultado es:

```text
Error al abrir la conexión a la base: Unable to connect to any of the specified MySQL hosts
Error consultando datos: Connection must be valid and open.
```

(El segundo mensaje es porque, al no estar abierta, el comando no puede ejecutarse.)

## Mostrar los datos

La tabla `alumnos` tiene muchos campos: `id_alumno`, `nombre`, `apellido`, `edad`, `direccion`, `telefono`, `grado` e `id_profesor`. El vídeo va probando formas de mostrarlos:

1. Un `Console.WriteLine` con saltos de línea: **desordenado**.
2. Todo en una línea, con los campos pegados: **no se distingue** dónde acaba uno y empieza otro.
3. Separándolos con espacios: ya **se entiende**, aunque no es una tabla.

En el ejemplo se usa una **alineación en columnas** con el formato `{indice,-ancho}`:

```csharp
Console.WriteLine("{0,-4}{1,-9}{2,-10}{3,-5}{4,-19}{5,-11}{6,-9}{7}",
                  datos["id_alumno"], datos["nombre"], datos["apellido"], datos["edad"],
                  datos["direccion"], datos["telefono"], datos["grado"], datos["id_profesor"]);
```

`{1,-9}` pone el segundo valor **alineado a la izquierda** en un ancho de 9 caracteres. Así los datos salen como una tabla:

```text
ID  Nombre   Apellido  Edad Dirección          Teléfono   Grado    Prof.
1   Sol      Vargas    6    Calle Luna 12      600111222  Primero  1
2   Mateo    Ortiz     6    Avenida Sur 5      600222333  Primero  1
...
```

> La consola **no es el editor de la base de datos**: no tiene el formato de tabla de Workbench, y hay que construirlo a mano. En las aplicaciones con ventana (WPF) se usan controles de tabla (`DataGrid`).

## Pedir solo algunos campos

No siempre hacen falta todos. Pedir **solo `nombre` y `grado`** es más rápido y más claro:

```csharp
string consulta = "SELECT nombre, grado FROM alumnos";
```

```csharp
Console.WriteLine("{0,-9}{1}", datos["nombre"], datos["grado"]);
```

```text
Nombre   Grado
Sol      Primero
Mateo    Primero
...
```

Se pueden practicar otras consultas, como a la tabla `materias`, o cualquier `SELECT` que se conozca (`WHERE`, `ORDER BY`...).

## El programa principal

```csharp
Consulta consulta = new Consulta();
consulta.ConsultaAlumnos();
consulta.ConsultaNombreGrado();
```

La conexión se **abre dentro de cada consulta** (en el vídeo se llama a `AbrirConexion` también en `Main` y el mensaje "Conexión abierta" sale dos veces; sobra una).

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
dotnet run
```

Requiere MySQL en marcha y la base `colegio_amanecer` (lección 53).

## Comandos

```bash
cd csharp/07-ui-and-databases
dotnet new console -n AdoMysqlQueries -o 54-ado-mysql-queries
cd 54-ado-mysql-queries
dotnet add package MySql.Data
dotnet run
```

Fuente: [curso de C# de INFORMATICONFIG](https://www.youtube.com/watch?v=YfN6rKFC0hk&list=PL2Z95CSZ1N4F7mym8Ca16RoTDVAlIOPlT&index=54), vídeo 54.
