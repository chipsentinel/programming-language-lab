# Consultas a SQL Server con ADO.NET

## Qué se aprende

Con la conexión de la lección 57, **leer datos** de una tabla de SQL Server con un `SELECT`:

```sql
SELECT nombre, direccion, telefono FROM clientes;
```

Lo mismo que se ve en SQL Server Management Studio, pero **desde C#**. Requiere la base `farmacia` creada con el script de la lección 57.

| Paso | MySQL (54) | Oracle (56) | SQL Server (58) |
|---|---|---|---|
| Comando | `MySqlCommand` | `OracleCommand` | **`SqlCommand`** |
| Lector | `MySqlDataReader` | `OracleDataReader` | **`SqlDataReader`** |
| Campo de una fila | `datos["nombre"]` | `lectura.GetString(1)` | **`lectura["nombre"]`** |

## La clase `Consultas`

La clase `Conexion` se queda con **una sola tarea**: conectarse (parámetros, abrir, cerrar). Las consultas van en **otra clase**, `Consultas`, que la usa. Así no hay que repetir el proceso de conexión cada vez.

Crear la clase: en Visual Studio, clic derecho en el proyecto → *Agregar → Clase*.

```csharp
class Consultas
{
    public void ConsultaClientes()
    {
        Conexion conectar = new Conexion();      // un objeto de la clase Conexion
        ...
    }
}
```

> En el vídeo la consulta se hace **dentro del constructor** de `Consultas`: al crear el objeto, ya consulta. Funciona, pero un constructor es para **preparar** el objeto; aquí se usa un **método** con nombre (`ConsultaClientes`), que se llama cuando se necesita.

## Abrir, consultar y leer

```csharp
SqlConnection con = conectar.AbrirConexion();

string consulta = "SELECT nombre, direccion, telefono FROM clientes";

using (SqlCommand comando = new SqlCommand(consulta, con))
using (SqlDataReader lectura = comando.ExecuteReader())
{
    while (lectura.Read())
    {
        string nombre = lectura["nombre"].ToString() ?? "";
        ...
    }
}
```

- **`conectar.AbrirConexion()`** devuelve la `SqlConnection` ya abierta (por eso se hizo que **devolviera** la conexión en la lección 57). El error del vídeo: pasar al comando el **objeto `Conexion`** (`conectar`) en vez de la **conexión** (`con`): el compilador avisa de que no puede convertir un tipo en el otro.
- **`SqlCommand(consulta, con)`** lleva la consulta y la conexión por la que se envía.
- **`ExecuteReader()`** ejecuta la consulta y devuelve el lector con las filas.
- **`lectura.Read()`** avanza a la siguiente fila y devuelve `false` cuando no quedan (lección 18, `while`).
- **`lectura["nombre"]`** es el valor del campo `nombre`, como un `object`; **`ToString()`** lo pasa a **texto**, imprescindible para poder mostrarlo.

> Los nombres de los campos se escriben **exactamente como están en la tabla**: si hay un error, la consulta falla (lanza una excepción).

### Pedir solo los campos necesarios

El vídeo cambia el `SELECT *` por **`nombre, direccion, telefono`**: el **ID** casi nunca se muestra en las aplicaciones, y pedir menos columnas es más rápido y más claro.

### `using`

`using (...)` **cierra y libera** el comando y el lector automáticamente al salir del bloque (el vídeo cierra el lector a mano con `lectura.Close()`).

## Dar formato de tabla a la consola

La consola no tiene el formato de tabla de SSMS: hay que construirlo.

```csharp
Console.WriteLine("{0,-20} | {1,-30} | {2,-15}", "Nombre", "Dirección", "Teléfono");
Console.WriteLine(new string('-', 70));
```

Cada `{índice,-ancho}` indica **qué valor** va ahí y **cuántos caracteres ocupa**, alineado a la izquierda (el `-`):

| Marcador | Valor | Ancho |
|---|---|---|
| `{0,-20}` | el **primer** argumento (nombre) | 20 |
| `{1,-30}` | el **segundo** (dirección) | 30 |
| `{2,-15}` | el **tercero** (teléfono) | 15 |

- Las **barras verticales** `|` separan las columnas.
- **`new string('-', 70)`** crea un texto de 70 guiones: la **línea** bajo los encabezados (lección sobre strings).
- Con **el mismo formato** se imprime cada fila, para que las columnas queden alineadas con los encabezados.

```text
Nombre               | Dirección                      | Teléfono
----------------------------------------------------------------------
Sol Vargas           | Calle Luna 34                  | 610000000
Mateo Ortiz          | Avenida Sur 12                 | 611111111
...
```

## `try / catch / finally`

```csharp
catch (Exception error)
{
    Console.WriteLine("Error consultando datos: " + error.Message);
}
finally
{
    conectar.CerrarConexion();
}
```

- **`catch`** muestra el error (un nombre de campo mal escrito, una tabla inexistente...).
- **`finally`** se ejecuta **siempre**: la conexión se cierra pase lo que pase.

Por eso en `Main` **no se llama a `AbrirConexion` ni a `CerrarConexion`**: la consulta lo hace por su cuenta.

Sin SQL Server el resultado es:

```text
Error conectando a la base: Error relacionado con la red o específico de la instancia ...
Error consultando datos: ExecuteReader requiere una Connection abierta y disponible ...
```

## El programa principal

```csharp
Consultas consultar = new Consultas();
consultar.ConsultaClientes();
```

## Ejercicios propuestos

- Consultar la tabla **`productos`** (nombre, precio y stock).
- Consultar las **`ventas`** y mostrar el **precio con dos decimales** (`{3,10:F2}`).
- Probar un `WHERE`: `SELECT ... FROM productos WHERE precio > 5`.

## Ejecutar

```bash
dotnet run
```

Requiere SQL Server en marcha y la base `farmacia` (lección 57).

## Comandos

```bash
cd csharp/07-ui-and-databases
dotnet new console -n AdoSqlserverQueries -o 58-ado-sqlserver-queries
cd 58-ado-sqlserver-queries
dotnet add package Microsoft.Data.SqlClient
dotnet run
```

Fuente: [curso de C# de INFORMATICONFIG](https://www.youtube.com/watch?v=10cKRN4kTAk&list=PL2Z95CSZ1N4F7mym8Ca16RoTDVAlIOPlT&index=58), vídeo 58.
