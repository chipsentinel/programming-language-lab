# Crear un proyecto Maven en Java

Guía rápida con los comandos para crear un módulo. Un proyecto Maven por módulo y una clase por ejercicio.

## Estructura del módulo

Desde la carpeta del módulo (ya existe en el repositorio):

```bash
cd java/02-fundamentals
mkdir -p src/main/java/lab/fundamentals
touch pom.xml
```
mkdir -p => crea la carpeta y las intermedias que falten
touch => crea un archivo vacío

En PowerShell, en lugar de `mkdir -p` y `touch`:

```powershell
New-Item -ItemType Directory -Force src/main/java/lab/fundamentals
New-Item pom.xml
```

Resultado:

```text
02-fundamentals/
├── pom.xml
└── src/main/java/lab/fundamentals/
```

## pom.xml mínimo

```xml
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>

  <groupId>lab</groupId>
  <artifactId>fundamentals</artifactId>
  <version>1.0-SNAPSHOT</version>

  <properties>
    <maven.compiler.release>17</maven.compiler.release>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
  </properties>
</project>
```

Ajustar `maven.compiler.release` a la versión del JDK instalado.

## Crear una clase de ejercicio

```bash
touch src/main/java/lab/fundamentals/Variables.java
```

Contenido mínimo de la clase (equivalente al `Program.cs` generado por `dotnet new console`):

```java
package lab.fundamentals;

public class Variables {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
    }
}
```

## Compilar y ejecutar

```bash
mvn compile
java -cp target/classes lab.fundamentals.Variables
```
mvn compile => compila el código a `target/classes`
-cp => classpath: carpeta donde están las clases compiladas

Desde IntelliJ IDEA, abrir el `pom.xml` con *File → Open* y ejecutar la clase con el botón de ejecución junto a `main`.

La carpeta `target/` se genera al compilar y está en `.gitignore`.
