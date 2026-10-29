# Crear un proyecto Maven en Java

Guía rápida con los comandos para crear un proyecto Maven. Es la lección equivalente a `dotnet new console` en C#.

A partir de la lección 04, los ejercicios son un único archivo `.java` que se ejecuta con `java Archivo.java`, sin Maven. Un proyecto Maven completo hace falta cuando se necesitan dependencias, pruebas o empaquetar el programa.

## Estructura del proyecto

Desde la carpeta de la lección:

```bash
cd java/01-introduction/03-create-maven-project
mkdir -p src/main/java/lab/introduction
touch pom.xml
```
mkdir -p => crea la carpeta y las intermedias que falten
touch => crea un archivo vacío

En PowerShell, en lugar de `mkdir -p` y `touch`:

```powershell
cd java/01-introduction/03-create-maven-project
New-Item -ItemType Directory -Force src/main/java/lab/introduction
New-Item pom.xml
```

Resultado:

```text
03-create-maven-project/
├── pom.xml
└── src/main/java/lab/introduction/
```

## pom.xml mínimo

```xml
<project xmlns="http://maven.apache.org/POM/4.0.0"
         xmlns:xsi="http://www.w3.org/2001/XMLSchema-instance"
         xsi:schemaLocation="http://maven.apache.org/POM/4.0.0 http://maven.apache.org/xsd/maven-4.0.0.xsd">
  <modelVersion>4.0.0</modelVersion>

  <groupId>lab</groupId>
  <artifactId>hello-world</artifactId>
  <version>1.0-SNAPSHOT</version>

  <properties>
    <maven.compiler.release>17</maven.compiler.release>
    <project.build.sourceEncoding>UTF-8</project.build.sourceEncoding>
  </properties>
</project>
```

Ajustar `maven.compiler.release` a la versión del JDK instalado.

## Crear una clase

```bash
touch src/main/java/lab/introduction/HelloWorld.java
```

Contenido mínimo de la clase (equivalente al `Program.cs` generado por `dotnet new console`):

```java
package lab.introduction;

public class HelloWorld {
    public static void main(String[] args) {
        System.out.println("Hello, World!");
    }
}
```

## Compilar y ejecutar

```bash
mvn compile
java -cp target/classes lab.introduction.HelloWorld
```
mvn compile => compila el código a `target/classes`
-cp => classpath: carpeta donde están las clases compiladas

Desde IntelliJ IDEA, abrir el `pom.xml` con *File → Open* y ejecutar la clase con el botón de ejecución junto a `main`.

La carpeta `target/` se genera al compilar y está en `.gitignore`.
