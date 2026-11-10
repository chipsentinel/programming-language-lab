# Consultas a Oracle con ADO.NET

## Qué se aprende

Con la conexión de la lección 55, **consultar tablas** de una base Oracle desde C#: el equivalente a lo que se hizo con MySQL en la lección 54, con las clases de Oracle.

| Paso | MySQL (lección 54) | Oracle (esta lección) |
|---|---|---|
| Comando | `MySqlCommand` | **`OracleCommand`** |
| Lector | `MySqlDataReader` | **`OracleDataReader`** |
| Campo de una fila | `datos["nombre"]` (por nombre) | **`lectura.GetString(1)`** (por posición y tipo) |

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

Se pega en el editor de SQL Developer y se ejecuta con **Run Script** (F5). Debe indicar que se creó el usuario y se concedieron los permisos.

Para ver el usuario nuevo: en el explorador, **Other Users** → clic derecho → **Refresh**.

### Conectarse con el nuevo usuario

Se crea una **conexión nueva** (la cruz verde):

- **Nombre** de la conexión: por ejemplo, `base libreria` (es solo una etiqueta).
- **Usuario**: `libreria`, y la **clave** del script.
- **Test** → debe decir **Success** → **Connect**.

> El **nombre de la conexión** (`base libreria`) **no es** el nombre del usuario/esquema (`libreria`). En el vídeo esa confusión causa un error que se ve más abajo.

### Parte 2: crear las tablas y los datos (conectado como `libreria`)

El script crea dos tablas y las llena (cada instrucción debe decir "1 fila insertada"):

| Tabla | Campos |
|---|---|
| `autores` | `id_autor`, `nombre`, `apellido`, `nacionalidad`, `libros_publicados` |
| `libros` | `id_libro`, `titulo`, **`id_autor`**, `fecha_publicacion`, `genero` |

Cada libro se **relaciona con su autor** por el campo `id_autor`.

Se comprueba con `SELECT * FROM autores;` y `SELECT * FROM libros;` (botón *Run Statement*, `Ctrl + Enter`).

> Con **Oracle 18c/21c XE** hay que conectarse al servicio **`XEPDB1`** (no `XE`) para crear usuarios y tablas, y definir `ORACLE_SERVICE=XEPDB1` al ejecutar el programa.

## La clase `Conexion`: dos cambios

La clase de la lección 55 se reutiliza, con dos cambios:

```csharp
public OracleConnection conexionOracle;        // antes private

cadenaConexion = "User Id=libreria;" + ...     // antes system
```

1. **`public`**: la variable se va a usar **desde otra clase** (`Consultas`). Cuando es `private` el compilador da error de **inaccesible**: es la **encapsulación** (lección 37).
2. **El usuario es `libreria`**, no `system`: las tablas están en **su** esquema.

### El error `ORA-00942`

Si se conecta con `system` (como en la lección 55), la conexión **funciona**, pero la consulta falla:

```text
Error consultando datos: ORA-00942: table or view does not exist
```

La tabla `libros` **no existe en el esquema `SYSTEM`**: está en `LIBRERIA`. Es el error más común al empezar con Oracle: se **conecta** al usuario equivocado.

## La clase `Consultas`

Una **nueva clase** para las consultas, separada de `Conexion` (que solo se ocupa de conectar). En Visual Studio: clic derecho en el proyecto → *Agregar → Clase*.

```csharp
class Consultas
{
    public void ConsultaLibros()
    {
        Conexion conectar = new Conexion();     // un objeto de la clase Conexion
        ...
    }
}
```

## Consultar y leer

```csharp
conectar.AbrirConexion();

string consulta = "SELECT * FROM libros";

using (OracleCommand comando = new OracleCommand(consulta, conectar.conexionOracle))
using (OracleDataReader lectura = comando.ExecuteReader())
{
    while (lectura.Read())
    {
        int id = lectura.GetInt32(0);
        string titulo = lectura.GetString(1);
        int autor = lectura.GetInt32(2);
        DateTime fecha = lectura.GetDateTime(3);
        string genero = lectura.GetString(4);
        ...
    }
}
```

- **`OracleCommand(consulta, conexión)`** lleva la consulta y la conexión por la que se envía.
- **`ExecuteReader()`** ejecuta la consulta y devuelve el lector con todas las filas, convertidas en objetos de C#.
- **`lectura.Read()`** avanza a la siguiente fila y devuelve `false` cuando no quedan (lección 18, `while`).

### Leer por posición y tipo

A diferencia de MySQL (donde se usaba `datos["nombre"]`), aquí cada campo se pide por su **posición** en la tabla (**empezando en 0**) y con el **método que corresponde a su tipo**:

| Posición | Campo | Tipo | Método |
|---|---|---|---|
| 0 | `id_libro` | número entero | `GetInt32(0)` |
| 1 | `titulo` | texto | `GetString(1)` |
| 2 | `id_autor` | número entero | `GetInt32(2)` |
| 3 | `fecha_publicacion` | fecha | `GetDateTime(3)` |
| 4 | `genero` | texto | `GetString(4)` |

Si se usa el método equivocado para el tipo (`GetInt32` en un campo de texto) hay una **excepción**. El vídeo tropieza con esto: al principio se miran los campos de la tabla `autores` en vez de la de `libros` y "coincide por suerte" que los tipos son parecidos.

### Formatear la fecha

`GetDateTime` devuelve un `DateTime`. Para mostrarlo **como se quiere** se formatea con **`ToString("yyyy-MM-dd")`** (año-mes-día).

El vídeo usa `ToShortDateString` **sin los paréntesis** y sale `System.Func<string>`: sin `()` no se **llama** al método, solo se **nombra**. Un error clásico.

### Mostrar como tabla

```csharp
Console.WriteLine("{0,-4}{1,-50}{2,-7}{3,-12}{4}", id, titulo, autor, fecha.ToString("yyyy-MM-dd"), genero);
```

`{1,-50}` alinea a la izquierda en 50 caracteres. (El vídeo usa tabuladores `\t`, que no siempre quedan alineados porque los títulos tienen longitudes distintas.)

```text
ID  Título                                            Autor  Fecha       Género
--------------------------------------------------------------------------------
1   Cien años de soledad                              1      1967-06-05  Novela
2   El amor en los tiempos del cólera                 1      1985-12-05  Novela
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

- **`catch`** muestra el error.
- **`finally`** se ejecuta **siempre**: la conexión se cierra pase lo que pase.

Por eso en `Main` **ya no se llama a `CerrarConexion`** ni a `AbrirConexion` (el vídeo los quita): la consulta abre y cierra la conexión por su cuenta.

## El programa principal

```csharp
Consultas consultar = new Consultas();
consultar.ConsultaLibros();
```

## Ejecutar

```bash
dotnet run
```

Requiere Oracle en marcha, el usuario `libreria` y sus tablas.

## Comandos

```bash
cd csharp/07-ui-and-databases
dotnet new console -n AdoOracleQueries -o 56-ado-oracle-queries
cd 56-ado-oracle-queries
dotnet add package Oracle.ManagedDataAccess.Core
dotnet run
```

Fuente: [curso de C# de INFORMATICONFIG](https://www.youtube.com/watch?v=syTRJU5tvLY&list=PL2Z95CSZ1N4F7mym8Ca16RoTDVAlIOPlT&index=56), vídeo 56.
