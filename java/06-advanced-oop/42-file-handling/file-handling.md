# Manejo de archivos en Java

## Qué se aprende

Crear, leer, modificar, copiar y borrar **archivos** del sistema desde un programa. Se usa el paquete **`java.nio.file`**, con la clase **`Files`** (operaciones) y la interfaz **`Path`** (rutas).

Se empieza con el formato más sencillo: el **archivo de texto** (`.txt`).

> Existe también la clase antigua `java.io.File`. `Files` y `Path` son la forma moderna (desde Java 7) y la que se usa aquí.

## `IOException`: una excepción comprobada

Casi todas las operaciones con archivos pueden lanzar **`IOException`** (el archivo no existe, no hay permisos...). Es una excepción **comprobada** (lección 41): el compilador **obliga** a tratarla, de una de dos formas:

```java
// 1. Capturarla
try {
    Files.readString(ruta);
} catch (IOException error) {
    System.out.println("Error: " + error.getMessage());
}

// 2. Declarar que el método puede lanzarla
public static void main(String[] args) throws IOException {
    Files.readString(ruta);
}
```

En esta lección `main` declara **`throws IOException`** para no repetir un `try / catch` en cada línea.

## La ruta del archivo

```java
Path ruta = Path.of("archivo1.txt");
```

| Tipo de ruta | Ejemplo | Dónde |
|---|---|---|
| **Relativa** | `archivo1.txt` | En la carpeta desde donde se ejecuta el programa |
| **Absoluta** | `C:\Datos\archivo1.txt` | Un lugar exacto del disco |

### La barra invertida en los textos

En Windows las carpetas se separan con `\`, que en un texto de Java es un carácter **especial** (`\n`, `\t`...). Se duplica, o se usan barras normales, que Java acepta en cualquier sistema:

```java
Path ruta1 = Path.of("C:\\Datos\\archivo1.txt");
Path ruta2 = Path.of("C:/Datos/archivo1.txt");
```

### Construir rutas: `resolve`

```java
Path carpeta = Files.createTempDirectory("mis-archivos");
Path ruta = carpeta.resolve("archivo1.txt");     // une con el separador correcto
```

## Crear y escribir: `Files.writeString`

```java
Files.writeString(ruta, "Hola a todos");
```

- Si el archivo **no existe, lo crea**.
- Si **ya existe, lo sobrescribe**: el contenido anterior se pierde.
- Desde Java 11. En versiones anteriores: `Files.write(ruta, texto.getBytes())`.

## Leer: `Files.readString`

```java
String contenido = Files.readString(ruta);
System.out.println(contenido);
```

Devuelve **todo el archivo** como un único `String`.

## Añadir al final: `APPEND`

Se indica una **opción** al escribir:

```java
Files.writeString(ruta, "\nEsta es una nueva línea de texto", StandardOpenOption.APPEND);
```

Se pone un `\n` al principio para que el texto nuevo empiece en una **línea nueva**.

| Opción | Efecto |
|---|---|
| *(ninguna)* | Crea o **reemplaza** el contenido |
| `StandardOpenOption.APPEND` | **Añade** al final (el archivo debe existir) |

## Leer y escribir por líneas

```java
List<String> lineas = Files.readAllLines(ruta);

for (int i = 0; i < lineas.size(); i++) {
    System.out.println("Línea " + (i + 1) + ": " + lineas.get(i));
}

Files.write(ruta, List.of("primera", "segunda", "tercera"));
```

`readAllLines` devuelve una **lista** con un elemento por línea (listas, lección 33).

## Comprobar si existe: `Files.exists`

```java
if (Files.exists(ruta)) {
    System.out.println("El archivo existe");
}
```

Devuelve `true` o `false`. Conviene usarlo **antes** de leer o borrar.

## Copiar y mover

```java
Files.copy(ruta, copia, StandardCopyOption.REPLACE_EXISTING);
Files.move(ruta, nuevaRuta);        // mueve o renombra
```

Sin `REPLACE_EXISTING`, `copy` **falla** (`FileAlreadyExistsException`) si el destino ya existe.

## Borrar: `Files.delete`

```java
if (Files.exists(ruta)) {
    try {
        Files.delete(ruta);
        System.out.println("Archivo borrado correctamente");
    } catch (IOException error) {
        System.out.println("No se pudo borrar: " + error.getMessage());
    }
} else {
    System.out.println("El archivo no existe");
}
```

- **Borra el archivo para siempre**: no va a la papelera.
- `Files.delete` **lanza `NoSuchFileException`** si el archivo no existe. (En C# `File.Delete` no da error en ese caso.)
- **`Files.deleteIfExists(ruta)`** no falla si no existe: devuelve `false`.

## Errores al leer

Leer un archivo que no existe lanza `NoSuchFileException`, que es una `IOException`:

```java
try {
    Files.readString(Path.of("no-existe.txt"));
} catch (NoSuchFileException error) {
    System.out.println("Error: el archivo no existe");
}
```

| Excepción | Cuándo |
|---|---|
| `NoSuchFileException` | El archivo o la carpeta no existen |
| `AccessDeniedException` | No hay permisos |
| `FileAlreadyExistsException` | El destino ya existe |
| `IOException` | Error de lectura o escritura en general (clase padre de las anteriores) |

## Cerrar los archivos: `try-with-resources`

Para trabajar **línea a línea** o con archivos grandes se usan `BufferedWriter` y `BufferedReader`, que **hay que cerrar**. Se declaran entre los **paréntesis del `try`** y Java los cierra solo, haya error o no (equivale a un `finally`):

```java
try (BufferedWriter escritor = Files.newBufferedWriter(ruta)) {
    escritor.write("primera línea");
    escritor.newLine();
}   // aquí se cierra solo

try (BufferedReader lector = Files.newBufferedReader(ruta)) {
    String linea;
    while ((linea = lector.readLine()) != null) {
        System.out.println(linea);
    }
}
```

`readLine` devuelve `null` cuando se acaba el archivo, lo que termina el `while`.

## Carpetas

```java
Files.createDirectory(carpeta);        // crea la carpeta
Files.createDirectories(carpeta);      // crea la carpeta y las intermedias
Files.exists(carpeta);                 // ¿existe?
Files.delete(carpeta);                 // borra la carpeta (debe estar vacía)
```

A diferencia de C#, `Files.delete` **no borra una carpeta con contenido**: hay que vaciarla antes.

## Ejemplo

```java
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardOpenOption;

public class FileHandling {
    public static void main(String[] args) throws IOException {
        Path ruta = Path.of("archivo1.txt");

        Files.writeString(ruta, "Hola a todos");
        Files.writeString(ruta, "\nSegunda línea", StandardOpenOption.APPEND);

        System.out.println(Files.readString(ruta));

        Files.delete(ruta);
    }
}
```

## Comandos

```bash
cd java/06-advanced-oop/42-file-handling
java FileHandling.java
```

Fuente: [curso de C# de INFORMATICONFIG](https://www.youtube.com/watch?v=qJ_lYQBTRzU&list=PL2Z95CSZ1N4F7mym8Ca16RoTDVAlIOPlT&index=42), vídeo 42.
