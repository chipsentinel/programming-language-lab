# Conexión a SQL Server con ADO.NET

## Qué se aprende

La misma operación de las lecciones 53 (MySQL) y 55 (Oracle), ahora con **SQL Server**, el gestor de Microsoft. El esquema de ADO.NET es el mismo; cambian la **biblioteca**, las **clases** y la **cadena de conexión**:

| Paso | MySQL | Oracle | SQL Server |
|---|---|---|---|
| Biblioteca (NuGet) | `MySql.Data` | `Oracle.ManagedDataAccess.Core` | **`Microsoft.Data.SqlClient`** |
| Espacio de nombres | `MySql.Data.MySqlClient` | `Oracle.ManagedDataAccess.Client` | **`Microsoft.Data.SqlClient`** |
| Conexión | `MySqlConnection` | `OracleConnection` | **`SqlConnection`** |
| Comando | `MySqlCommand` | `OracleCommand` | `SqlCommand` |
| Lector | `MySqlDataReader` | `OracleDataReader` | `SqlDataReader` |

## Preparar SQL Server

Quien ya tenga una instancia de SQL Server solo necesita conocer los **parámetros**.

### El motor: SQL Server 2022 (Express)

1. En la página de **SQL Server 2022** de Microsoft → **Descargas** → elegir la edición **Express** (gratuita, la que se instala en el propio equipo) → *Descargar ahora*. Microsoft pide rellenar un formulario: es un requisito, no es relevante.
2. Ejecutar el instalador descargado y elegir el tipo **Básica**.
3. Aceptar la licencia y la carpeta de instalación.
4. Al terminar muestra el **nombre de la instancia** (`SQLEXPRESS` en Express), las carpetas y la versión.

### La herramienta: SQL Server Management Studio (SSMS)

El equivalente de MySQL Workbench y de SQL Developer. En la misma pantalla final: **Install SSMS** → descargar **SSMS 21** (un instalador pequeño) → instalarlo **sin ninguna carga de trabajo adicional**. No hace falta reiniciar.

### Conectarse con SSMS

1. Abrirlo (menú Inicio → SSMS). Si pide iniciar sesión con una cuenta: **omitir**.
2. En la ventana de conexión:
   - **Server name**: el que muestra el sistema (aquí `localhost`; en Express suele ser `localhost\SQLEXPRESS`).
   - **Authentication**: **Windows Authentication**. Entra el usuario de Windows, **sin usuario ni clave**, porque no se ha creado ningún otro.
   - **Encryption**: poner **Optional** (o marcar *Trust server certificate*). Si se deja en *Mandatory*, da un error y no se puede entrar.
3. **Connect**. En el **Explorador de objetos** (*Object Explorer*) aparecen los objetos del servidor.

Si **SQL Server Agent** aparece con una cruz roja: clic derecho → **Start**.

### Crear la base de datos `farmacia`

1. En el Explorador: clic derecho en **Databases** → **New Database**.
2. Nombre: **`farmacia`** → **OK**.
3. Clic derecho sobre `farmacia` → **New Query**.
4. El script **[farmacia.sql](farmacia.sql)** (en esta carpeta) tiene **dos partes**:
   - **Parte 1**: crea tres tablas. Se pega y se ejecuta (**Execute**). Debe decir *Commands completed successfully*.
   - **Parte 2**: las llena. Se pega y se ejecuta: 20 clientes, 30 productos y 30 ventas.
5. Comprobar: `SELECT * FROM clientes;`.

| Tabla | Campos principales |
|---|---|
| `clientes` | `id_cliente`, `nombre`, `direccion`, `telefono` |
| `productos` | `id_producto`, `nombre`, `descripcion`, `precio`, `stock` |
| `ventas` | `id_venta`, `id_cliente`, `id_producto`, `cantidad`, `fecha` |

## La biblioteca de SQL Server: NuGet

`SqlConnection` también viene en una biblioteca moderna de Microsoft:

- En Visual Studio: *Proyecto → Administrar paquetes NuGet* → **Examinar** → **`Microsoft.Data.SqlClient`** → **Instalar**. (Ojo: no confundir con el antiguo `System.Data.SqlClient`.)
- Por línea de comandos:

```bash
dotnet add package Microsoft.Data.SqlClient
```

```csharp
using Microsoft.Data.SqlClient;
```

## La clase `Conexion`

```csharp
class Conexion
{
    private readonly string cadenaConexion;
    private SqlConnection conectar;

    public Conexion()
    {
        cadenaConexion = "Server=localhost;" +
                         "Database=farmacia;" +
                         "Trusted_Connection=True;" +
                         "TrustServerCertificate=True;";

        conectar = new SqlConnection(cadenaConexion);
    }
}
```

- **`readonly`**: la variable solo puede recibir valor **en el constructor**; después no se puede cambiar.
- Crear el `SqlConnection` **no conecta todavía**: solo guarda los datos.

### La cadena de conexión

Pares `clave=valor` separados por **punto y coma**:

| Clave | Valor | Significado |
|---|---|---|
| `Server` | `localhost` | El servidor: el propio equipo (con instancia con nombre: `localhost\SQLEXPRESS`) |
| `Database` | `farmacia` | La base de datos |
| `Trusted_Connection` | `True` | **Autenticación de Windows**: sin usuario ni clave |
| `TrustServerCertificate` | `True` | **Confiar en el certificado** del servidor |

Con **usuario y clave de SQL Server** en lugar de la autenticación de Windows, se cambia `Trusted_Connection=True;` por:

```text
User Id=usuario;Password=clave;
```

### Errores típicos de la cadena (los del vídeo)

Con cada ejecución falla algo distinto, y los mensajes dicen **dónde** mirar:

| Error | Causa | Arreglo |
|---|---|---|
| *Keyword not supported: 'trust connection'* | La **clave está mal escrita** | Es **`Trusted_Connection`**, con la `d` |
| *No se encontró el servidor o no estaba accesible* | Un **error en la cadena**: por ejemplo una **coma** en vez de un **punto y coma** tras `localhost` | Revisar los `;` |
| *The certificate chain was issued by an authority that is not trusted* | Con autenticación de Windows la conexión viaja **cifrada** y el certificado del servidor no es de confianza | Añadir **`TrustServerCertificate=True;`** (en local no hay problema) |

> Como el `catch` muestra `error.Message`, el mensaje dice cuál de los tres es. Y si el programa **conecta pero no dice nada**, es porque falta el `Console.WriteLine` de éxito: hay que avisar **siempre** al usuario del resultado.

### Variables de entorno opcionales

En el ejemplo, para no escribir datos de la máquina en el código:

| Variable | Para qué |
|---|---|
| `SQLSERVER_SERVER` | El servidor, por ejemplo `localhost\SQLEXPRESS` |
| `SQLSERVER_USER` + `SQLSERVER_PASSWORD` | Si se definen, se usa **autenticación de SQL Server** en vez de la de Windows |

```powershell
$env:SQLSERVER_SERVER = "localhost\SQLEXPRESS"      # PowerShell
dotnet run
```

## Abrir y cerrar la conexión

```csharp
public SqlConnection AbrirConexion()
{
    try
    {
        if (conectar.State == ConnectionState.Closed)
        {
            conectar.Open();
            Console.WriteLine("Sistema conectado a SQL Server");
        }
    }
    catch (Exception error)
    {
        Console.WriteLine("Error conectando a la base: " + error.Message);
    }

    return conectar;
}

public void CerrarConexion()
{
    try
    {
        if (conectar.State == ConnectionState.Open)
        {
            conectar.Close();
            Console.WriteLine("Sistema desconectado de la base");
        }
    }
    catch (Exception error)
    {
        Console.WriteLine("Error al desconectar de la base: " + error.Message);
    }
}
```

- **`conectar.State`** es el **estado** de la conexión (`ConnectionState.Closed`, `Open`...). Se abre solo si está **cerrada** y se cierra solo si está **abierta**.
- **`Open()`** es lo que **realmente se conecta**.
- Todo va en un **`try/catch`** (lección 41).
- `AbrirConexion` **devuelve** la conexión (en el vídeo "de tipo `get`"): quien la llame podrá usarla para consultar (lección siguiente).

## El programa principal

```csharp
Conexion conexion = new Conexion();

conexion.AbrirConexion();
conexion.CerrarConexion();
```

### Salida esperada

Con SQL Server funcionando y la base creada:

```text
Iniciando conexión a la base...
Sistema conectado a SQL Server
Sistema desconectado de la base
```

Sin SQL Server (o con el servidor mal escrito):

```text
Iniciando conexión a la base...
Error conectando a la base: Error relacionado con la red o específico de la instancia ...
```

## Ejecutar

```bash
dotnet run
```

## Comandos

```bash
cd csharp/07-ui-and-databases
dotnet new console -n AdoSqlserverConnection -o 57-ado-sqlserver-connection
cd 57-ado-sqlserver-connection
dotnet add package Microsoft.Data.SqlClient
dotnet run
```

Fuente: [curso de C# de INFORMATICONFIG](https://www.youtube.com/watch?v=y0OPYm63iZ0&list=PL2Z95CSZ1N4F7mym8Ca16RoTDVAlIOPlT&index=57), vídeo 57.
