# Conexión a Oracle con ADO.NET

## Qué se aprende

La misma operación de la lección 53 (conectarse a una base de datos), pero con **Oracle**. Es importante saber hacerlo: muchos proyectos reales usan Oracle, y hay que poder conectarse desde C#.

El esquema de ADO.NET es **el mismo**; cambia la **biblioteca**, las **clases** y la **cadena de conexión**:

| Paso | MySQL | Oracle |
|---|---|---|
| Biblioteca (NuGet) | `MySql.Data` | `Oracle.ManagedDataAccess.Core` |
| Espacio de nombres | `MySql.Data.MySqlClient` | `Oracle.ManagedDataAccess.Client` |
| Conexión | `MySqlConnection` | **`OracleConnection`** |
| Comando | `MySqlCommand` | `OracleCommand` |
| Lector | `MySqlDataReader` | `OracleDataReader` |

## Preparar Oracle

Quien ya tenga Oracle instalado se puede saltar esto; solo necesita conocer los **parámetros** (usuario, clave, servidor, puerto y servicio).

### Oracle Database Express Edition (XE)

La versión **gratuita y ligera** de Oracle: más fácil de instalar y configurar que un Oracle profesional. El vídeo usa la **11g XE**, una versión antigua, pero para lo que se hace aquí la tecnología es la misma. Hoy se descarga la versión actual (**21c XE**) de oracle.com.

Pasos del instalador (*wizard*):

1. Ejecutar `setup` y **Next**.
2. Aceptar los términos de la licencia → **Next**.
3. Elegir la carpeta de instalación → **Next**.
4. Escribir la **clave del administrador** (la de los usuarios `SYS` y `SYSTEM`) → **Next**.
5. **Install**, y al terminar, **Finish**.

### SQL Developer

La herramienta para **administrar los datos** de Oracle (el equivalente de MySQL Workbench). Se descarga de oracle.com:

- Si ya hay un **JDK** instalado: la versión *Windows 32/64-bit*.
- Si no: la que **incluye el JDK**.

Se descomprime en cualquier carpeta y se ejecuta `sqldeveloper.exe`.

Para conectarse:

1. Nueva conexión (la flecha verde) → usuario **`system`** y la **clave** del instalador.
2. Pulsar **Test**: debe decir **Success**.
3. **Connect**.

Con clic derecho en la conexión → *Propiedades* se ve el **puerto** (**1521**, el de Oracle por defecto) y el servicio (**XE**).

## La biblioteca de Oracle: NuGet

`OracleConnection` **no viene con .NET**: está en una **biblioteca externa**.

- En Visual Studio: *Proyecto → Administrar paquetes NuGet* → **Examinar** → buscar **Oracle** → **Oracle Managed Data Access** → **Instalar**.
- Por línea de comandos:

```bash
dotnet add package Oracle.ManagedDataAccess.Core
```

(**Managed** significa que está escrita completamente en código de .NET: **no hace falta instalar el cliente de Oracle** en el equipo.)

Y en el código:

```csharp
using Oracle.ManagedDataAccess.Client;
```

## La clase `Conexion`

```csharp
class Conexion
{
    private string cadenaConexion;
    private OracleConnection conexionOracle;

    public Conexion()
    {
        cadenaConexion = "User Id=system;" +
                         "Password=" + password + ";" +
                         "Data Source=localhost:1521/XE;";

        conexionOracle = new OracleConnection(cadenaConexion);
    }
}
```

### La cadena de conexión

Pares `clave=valor` separados por **punto y coma**:

| Clave | Valor | Significado |
|---|---|---|
| `User Id` | `system` | El usuario con el que se entró en SQL Developer |
| `Password` | *(la clave)* | La clave del administrador de la instalación |
| `Data Source` | `localhost:1521/XE` | **Servidor:puerto/servicio** |

El `Data Source` tiene tres partes:

```text
localhost : 1521 / XE
│           │      └── el servicio (XE: Express Edition)
│           └───────── el puerto de Oracle
└───────────────────── el servidor: este mismo equipo
```

A diferencia de MySQL, donde se indicaba **la base de datos** (`database=colegio_amanecer`), en Oracle se indica **el servicio** y el usuario es el que determina **su esquema** (donde están sus tablas). Con 11g XE el servicio es `XE`; con 18c o 21c XE la base "enchufable" donde se trabaja es **`XEPDB1`**: en ese caso se define la variable de entorno `ORACLE_SERVICE=XEPDB1` antes de ejecutar (por defecto se usa `XE`, como en el vídeo).

Crear el `OracleConnection` **no conecta todavía**: solo guarda los datos.

> `system` es el administrador: para pruebas está bien, pero en un proyecto real se crea **un usuario propio con los permisos justos**.

### La contraseña no va en el código

El vídeo la escribe en el código porque es un laboratorio. En el ejemplo se lee de una **variable de entorno**, con `oracle` por defecto:

```csharp
string password = Environment.GetEnvironmentVariable("ORACLE_PASSWORD") ?? "oracle";
```

Para usar otra clave, antes de ejecutar:

```powershell
$env:ORACLE_PASSWORD = "mi_clave"      # PowerShell
dotnet run
```

Una clave escrita en el código acaba en el repositorio y la ve cualquiera.

## Abrir y cerrar la conexión

```csharp
public void AbrirConexion()
{
    try
    {
        conexionOracle.Open();
        Console.WriteLine("Sistema conectado a Oracle Database");
    }
    catch (Exception e)
    {
        Console.WriteLine("Error conectando a la base: " + e.Message);
    }
}

public void CerrarConexion()
{
    try
    {
        if (conexionOracle.State == ConnectionState.Open)
        {
            conexionOracle.Close();
            Console.WriteLine("Sistema desconectado de la base");
        }
    }
    catch (Exception e)
    {
        Console.WriteLine("Error al desconectar de la base: " + e.Message);
    }
}
```

- **`Open()`** es lo que **realmente se conecta**: usa los parámetros para llegar al servidor y autenticarse.
- **`Close()`** cierra el canal. **Siempre hay que cerrar la conexión** para liberar recursos.
- Los dos van en un **`try/catch`** (lección 41): el servidor puede estar apagado, la clave puede ser incorrecta...
- En `CerrarConexion` se comprueba que esté **abierta** (`ConnectionState.Open`) para no avisar de "desconectado" si nunca se llegó a conectar (el vídeo no lo hace).

## El programa principal

```csharp
Conexion conectar = new Conexion();

conectar.AbrirConexion();
conectar.CerrarConexion();
```

### Salida esperada

Con Oracle funcionando y la clave correcta:

```text
Iniciando conexión a la base...
Sistema conectado a Oracle Database
Sistema desconectado de la base
```

Sin Oracle (o con la clave mal), sale el motivo:

```text
Iniciando conexión a la base...
Error conectando a la base: ORA-50201: Oracle Communication: Failed to connect to server or failed to parse connect string
```

(Los errores de Oracle empiezan con su **código `ORA-xxxxx`**, útil para buscarlos.)

## Ejecutar

```bash
dotnet run
```

## Comandos

```bash
cd csharp/07-ui-and-databases
dotnet new console -n AdoOracleConnection -o 55-ado-oracle-connection
cd 55-ado-oracle-connection
dotnet add package Oracle.ManagedDataAccess.Core
dotnet run
```

Fuente: [curso de C# de INFORMATICONFIG](https://www.youtube.com/watch?v=oY1LKRXDVE0&list=PL2Z95CSZ1N4F7mym8Ca16RoTDVAlIOPlT&index=55), vídeo 55.
