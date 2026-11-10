# Conexión a MySQL con ADO.NET

## Qué se aprende

Una de las funcionalidades más importantes de cualquier lenguaje: **conectarse a una base de datos**. Con esa conexión un programa puede **guardar**, **recuperar**, **actualizar** y **eliminar** datos de un sistema gestor de bases de datos (**SQL Server, MySQL, PostgreSQL, Oracle**...).

En C# se hace con **ADO.NET** (*ActiveX Data Objects para .NET*): una colección de clases para trabajar con datos de una base.

> No es un curso de bases de datos: solo se prepara un entorno para aprender a **conectarse y trabajar desde C#**.

## El flujo típico

```text
1. Crear la cadena de conexión   →  servidor, puerto, base de datos, usuario y contraseña
2. Abrir la conexión             →  MySqlConnection (SqlConnection en SQL Server)
3. Ejecutar comandos SQL         →  MySqlCommand: SELECT, INSERT, UPDATE, DELETE
4. Leer los resultados           →  MySqlDataReader
5. Cerrar la conexión            →  siempre, para liberar recursos
```

Cada gestor tiene **sus propias clases con el mismo esquema**:

| Paso | MySQL | SQL Server |
|---|---|---|
| Conexión | `MySqlConnection` | `SqlConnection` |
| Comando | `MySqlCommand` | `SqlCommand` |
| Lector | `MySqlDataReader` | `SqlDataReader` |

## Preparar MySQL

Si ya hay un MySQL instalado solo hace falta conocer los **parámetros de conexión**.

### Instalarlo (Windows)

1. Descargar **MySQL Installer for Windows** (la versión grande, de unos 350 MB) desde la sección *Downloads → MySQL Community (GPL) Downloads* de mysql.com.
2. Ejecutarlo y elegir el tipo **Custom**.
3. Instalar **MySQL Server 8.0** (el motor de bases de datos, donde se guardan los datos) y **MySQL Workbench 8.0** (el editor con el que se administran).
4. En la configuración, dejar todo por defecto: *Development Computer* y el puerto **TCP/IP 3306**.
5. Fijar la **clave del administrador** (`root`). El instalador avisa de que es *weak* si es sencilla: en un entorno de pruebas no importa, pero en **producción** hay que usar una clave **fuerte** (mayúsculas, minúsculas, números, símbolos).
6. No crear más usuarios; terminar (*Execute* → *Finish*).

### MySQL Workbench

Se abre con una conexión llamada **instancia local** (*Local instance*): usuario **`root`** y dirección **`localhost:3306`**. "Local" significa que la base está en **este mismo equipo**. Al abrirla pide la clave.

### Crear la base de datos del ejemplo

En el script **[colegio_amanecer.sql](colegio_amanecer.sql)** (en esta carpeta):

1. Copiar todo su contenido en el escritorio de Workbench.
2. Ejecutarlo (botón del rayo): **todas las líneas deben salir en verde**; si hay alguna roja, hay que leer el error.
3. Comprobar con `SELECT * FROM alumnos;`.

El script crea la base **`colegio_amanecer`** (un colegio) con cuatro tablas:

| Tabla | Campos principales |
|---|---|
| `alumnos` | `id_alumno`, `nombre`, `apellido`, `edad`, `direccion`, `telefono`, `grado`, `id_profesor` |
| `profesores` | `id_profesor`, `nombre`, `grado` |
| `materias` | `id_materia`, `materia`, `nivel`, `horario`, `id_profesor` |
| `alumno_materia` | `id_alumno`, `id_materia` (qué alumno cursa qué materia) |

Primero hay que **crear las tablas** y luego **insertar los datos**.

> El nombre de la base es `colegio_amanecer` (todo en minúscula y con guion bajo). Se puede ver con `SHOW DATABASES;`. **Hay que escribirlo exactamente igual** en la conexión.

## La biblioteca de MySQL: NuGet

`MySqlConnection` **no viene con .NET**: está en una **biblioteca externa**. **NuGet** es el gestor de paquetes oficial de .NET, para añadir bibliotecas externas.

- En Visual Studio: *Proyecto → Administrar paquetes NuGet* → pestaña **Examinar** → buscar **`MySql.Data`** → **Instalar**.
- Por línea de comandos:

```bash
dotnet add package MySql.Data
```

Se añade una línea al `.csproj`:

```xml
<PackageReference Include="MySql.Data" Version="..." />
```

Y en el código:

```csharp
using MySql.Data.MySqlClient;
```

## La clase `Conexion`

Se separa en una clase la parte de **conectarse**, para que el programa sea más ordenado y se pueda reutilizar.

```csharp
class Conexion
{
    public MySqlConnection conectar;      // la conexión activa
    private string cadenaConexion;        // los parámetros de conexión

    public Conexion()                     // el constructor prepara todo (todavía sin conectar)
    {
        cadenaConexion = "server=localhost;" +
                         "port=3306;" +
                         "database=colegio_amanecer;" +
                         "user=root;" +
                         "password=" + password + ";";

        conectar = new MySqlConnection(cadenaConexion);
    }
}
```

### La cadena de conexión

Pares `clave=valor` separados por **punto y coma**:

| Clave | Valor | Significado |
|---|---|---|
| `server` | `localhost` | El servidor: la base está en este equipo |
| `port` | `3306` | El puerto por defecto de MySQL |
| `database` | `colegio_amanecer` | Nombre de la base de datos |
| `user` | `root` | Usuario (no se creó ninguno más) |
| `password` | *(la clave)* | La contraseña configurada al instalar |

Crear el `MySqlConnection` **no conecta todavía**: solo guarda esos datos.

### La contraseña no va en el código

El vídeo la escribe en el código (`abc123`) porque es un laboratorio. En el ejemplo se lee de una **variable de entorno**, con `abc123` por defecto:

```csharp
string password = Environment.GetEnvironmentVariable("MYSQL_PASSWORD") ?? "abc123";
```

Para usar otra clave, antes de ejecutar:

```powershell
$env:MYSQL_PASSWORD = "mi_clave"      # PowerShell
dotnet run
```

Una clave escrita en el código acaba en el repositorio y la ve cualquiera.

## Abrir la conexión: `AbrirConexion`

```csharp
public MySqlConnection AbrirConexion()
{
    try
    {
        if (conectar.State == ConnectionState.Closed)
        {
            conectar.Open();
            Console.WriteLine("Conexión abierta");
        }
    }
    catch (Exception e)
    {
        Console.WriteLine("Error al abrir la conexión a la base: " + e.Message);
    }

    return conectar;
}
```

- **`conectar.State`** es el **estado** de la conexión. Es un valor de la enumeración **`ConnectionState`** (lección 43), del espacio de nombres `System.Data`: `Closed` (cerrada), `Open` (abierta), `Connecting`, `Executing`...
- **`conectar.Open()`** es lo que **realmente se conecta** al motor: abre un canal con el servidor y se autentica.
- Si algo falla (servidor apagado, clave incorrecta, base inexistente...) salta una excepción; el **`try/catch`** (lección 41) la captura y muestra el motivo en vez de cerrar el programa.
- Se **devuelve** el objeto de conexión.

> Un `try/catch` es **imprescindible** en todo el trabajo con bases de datos: el servidor puede estar apagado, la red puede fallar, la clave puede ser incorrecta.

## Cerrar la conexión: `CerrarConexion`

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

**Siempre hay que cerrar la conexión** (el vídeo lo deja para el siguiente, aquí ya se incluye). Una conexión abierta ocupa **memoria, procesador y una de las conexiones limitadas del servidor**.

## El programa principal

```csharp
Conexion conectarBD = new Conexion();
MySqlConnection conectar = conectarBD.AbrirConexion();

if (conectar != null && conectar.State == ConnectionState.Open)
    Console.WriteLine("CONEXIÓN EXITOSA");
else
    Console.WriteLine("ERROR CONECTÁNDOSE A LA BASE");

conectarBD.CerrarConexion();
```

El estado que se busca al comprobar es **`Open`**: es lo que indica que se ha conectado. (En el vídeo se escribe primero `Closed` por error, y por eso da error.)

### Salida esperada

Con MySQL funcionando y la base creada:

```text
Iniciando conexión...
Conexión abierta
CONEXIÓN EXITOSA
Conexión cerrada
```

Sin MySQL (o con la clave mal), sale el motivo y el aviso de error:

```text
Iniciando conexión...
Error al abrir la conexión a la base: Unable to connect to any of the specified MySQL hosts
ERROR CONECTÁNDOSE A LA BASE
```

### "Abrir conexión" y "conectarse"

El vídeo distingue dos mensajes: **"conexión abierta"** lo da el método `AbrirConexion` (se ha establecido la comunicación con el servidor) y **"conexión exitosa"** lo da el programa principal (la conexión está en estado `Open` y ya se pueden ejecutar comandos SQL, lo que se hace en la lección siguiente).

## Ejecutar

```bash
dotnet run
```

## Comandos

```bash
cd csharp/07-ui-and-databases
dotnet new console -n AdoMysqlConnection -o 53-ado-mysql-connection
cd 53-ado-mysql-connection
dotnet add package MySql.Data
dotnet run
```

Fuente: [curso de C# de INFORMATICONFIG](https://www.youtube.com/watch?v=7Vy8_RblBAA&list=PL2Z95CSZ1N4F7mym8Ca16RoTDVAlIOPlT&index=53), vídeo 53.
