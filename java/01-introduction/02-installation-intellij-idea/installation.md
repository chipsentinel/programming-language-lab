# Instalación del entorno para Java

Equivalente de la lección 02 del curso de C# (instalación de Visual Studio): preparar las herramientas para programar en Java.

## Herramientas

| Herramienta | Para qué sirve |
|---|---|
| **JDK** | Compilador y herramientas de desarrollo de Java |
| **Maven** | Compilación y gestión de dependencias |
| **IntelliJ IDEA** | Entorno de desarrollo (IDE) |

## Pasos

1. Instalar un **JDK** (por ejemplo, Temurin u OpenJDK) y comprobar la versión:

```bash
java -version
```

2. Instalar [Apache Maven](https://maven.apache.org/download.cgi) y añadirlo al `PATH`:

```bash
mvn -version
```

3. Instalar [IntelliJ IDEA](https://www.jetbrains.com/idea/download/) (la edición Community es suficiente).
4. En IntelliJ, seleccionar el JDK instalado en *File → Project Structure → SDKs*.

IntelliJ incluye una versión de Maven, pero conviene tener también la instalación del sistema para ejecutar `mvn` desde la terminal.
