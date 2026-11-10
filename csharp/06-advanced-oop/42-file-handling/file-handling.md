# Manejo de archivos en C#

## Qué se aprende

Crear, leer, modificar, copiar y borrar **archivos** del sistema desde un programa. Se usa la clase estática **`File`** del espacio de nombres `System.IO`, que el proyecto ya incluye (`ImplicitUsings`).

Se empieza con el formato más sencillo: el **archivo de texto** (`.txt`).

## La ruta del archivo

Todas las funciones necesitan **dónde está** el archivo:

```csharp
string ruta = "archivo1.txt";
```

| Tipo de ruta | Ejemplo | Dónde |
|---|---|---|
| **Relativa** | `archivo1.txt` | Junto al programa. Al ejecutar con `dotnet run`, en la carpeta actual; en Visual Studio, en `bin\Debug\net8.0` |
| **Absoluta** | `C:\Datos\archivo1.txt` | Un lugar exacto del disco |

### La barra invertida en los textos

En Windows las carpetas se separan con `\`, que en un texto de C# es un carácter **especial** (`\n`, `\t`...). Hay dos soluciones:

```csharp
string ruta1 = "C:\\Datos\\archivo1.txt";    // se duplica: \\
string ruta2 = @"C:\Datos\archivo1.txt";     // con @ el texto es literal
```

### Construir rutas: `Path.Combine`

Para unir partes con el separador correcto del sistema operativo (y funcionar también en Linux y macOS):

```csharp
string carpeta = Path.Combine(Path.GetTempPath(), "mis-archivos");
string ruta = Path.Combine(carpeta, "archivo1.txt");
```

## Crear y escribir: `WriteAllText`

```csharp
File.WriteAllText(ruta, "Hola a todos");
```

- Si el archivo **no existe, lo crea**.
- Si **ya existe, lo sobrescribe**: el contenido anterior se pierde.
- No muestra nada: el efecto está en el disco.

Para escribir un texto largo con saltos de línea se usan `\n` o un texto literal (lección 23).

## Leer: `ReadAllText`

```csharp
string contenido = File.ReadAllText(ruta);
Console.WriteLine(contenido);
```

Devuelve **todo el archivo** como un único `string`.

## Añadir al final: `AppendAllText`

Sin borrar lo que ya hay:

```csharp
File.AppendAllText(ruta, "\nEsta es una nueva línea de texto");
```

Se pone un `\n` al principio para que el texto nuevo empiece en una **línea nueva**.

| Método | Si el archivo existe |
|---|---|
| `WriteAllText` | **Reemplaza** el contenido |
| `AppendAllText` | **Añade** al final |

## Leer y escribir por líneas

```csharp
string[] lineas = File.ReadAllLines(ruta);

for (int i = 0; i < lineas.Length; i++)
{
    Console.WriteLine($"Línea {i + 1}: {lineas[i]}");
}

File.WriteAllLines(ruta, new string[] { "primera", "segunda", "tercera" });
```

`ReadAllLines` devuelve un **array** con una posición por línea, lo que facilita procesarlas (arrays, lección 24).

## Comprobar si existe: `File.Exists`

```csharp
if (File.Exists(ruta))
{
    Console.WriteLine("El archivo existe");
}
```

Devuelve `true` o `false`. Conviene usarlo **antes** de leer o borrar.

## Copiar y mover

```csharp
File.Copy(ruta, copia, true);     // true: sobrescribe la copia si ya existe
File.Move(ruta, nuevaRuta);       // mueve o renombra el archivo
```

Sin el tercer argumento, `Copy` **falla** si el destino ya existe.

## Borrar: `File.Delete`

```csharp
if (File.Exists(ruta))
{
    try
    {
        File.Delete(ruta);
        Console.WriteLine("Archivo borrado correctamente");
    }
    catch (IOException error)
    {
        Console.WriteLine("No se pudo borrar: " + error.Message);
    }
}
else
{
    Console.WriteLine("El archivo no existe");
}
```

- **Borra el archivo para siempre**: no va a la papelera.
- `File.Delete` **no da error si el archivo no existe**. Por eso, sin el `if`, no habría ningún aviso: hay que comprobarlo uno mismo con `File.Exists`.
- Sí puede lanzar `IOException` si el archivo está **en uso** por otro programa, y `UnauthorizedAccessException` si **no hay permisos**.
- El `else` informa de que no había nada que borrar (sin él, el programa no diría nada).

## Errores al leer

Leer un archivo que no existe **lanza una excepción** (lección 41):

```csharp
try
{
    File.ReadAllText("no-existe.txt");
}
catch (FileNotFoundException)
{
    Console.WriteLine("Error: el archivo no existe");
}
```

| Excepción | Cuándo |
|---|---|
| `FileNotFoundException` | El archivo no existe |
| `DirectoryNotFoundException` | La carpeta de la ruta no existe |
| `UnauthorizedAccessException` | No hay permisos |
| `IOException` | Error de lectura o escritura en general |

`IOException` es la **clase padre** de las dos primeras (herencia, lección 38): `catch (IOException)` las captura todas.

## Cerrar los archivos: `using`

Los métodos `File.ReadAllText` y compañía abren y cierran el archivo solos. Para trabajar **línea a línea** o con archivos grandes se usan `StreamWriter` y `StreamReader`, que **hay que cerrar**. El bloque **`using`** lo hace automáticamente, igual que un `finally`:

```csharp
using (StreamWriter escritor = new StreamWriter(ruta))
{
    escritor.WriteLine("primera línea");
    escritor.WriteLine("segunda línea");
}   // aquí se cierra solo

using (StreamReader lector = new StreamReader(ruta))
{
    string? linea;
    while ((linea = lector.ReadLine()) != null)
    {
        Console.WriteLine(linea);
    }
}
```

`ReadLine` devuelve `null` cuando se acaba el archivo, lo que termina el `while`.

## Carpetas: `Directory`

```csharp
Directory.CreateDirectory(carpeta);    // crea la carpeta (y las intermedias)
Directory.Exists(carpeta);             // ¿existe?
Directory.Delete(carpeta, true);       // borra la carpeta; true: con todo lo que contiene
```

## Ejemplo

```csharp
string ruta = "archivo1.txt";

File.WriteAllText(ruta, "Hola a todos");
File.AppendAllText(ruta, "\nSegunda línea");

Console.WriteLine(File.ReadAllText(ruta));

File.Delete(ruta);

Console.ReadKey();
```

## Comandos

```bash
cd csharp/06-advanced-oop
dotnet new console -n FileHandling -o 42-file-handling
cd 42-file-handling
dotnet run
```

Fuente: [curso de C# de INFORMATICONFIG](https://www.youtube.com/watch?v=qJ_lYQBTRzU&list=PL2Z95CSZ1N4F7mym8Ca16RoTDVAlIOPlT&index=42), vídeo 42.
