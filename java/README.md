# Java

Lenguaje complementario. Replica los ejercicios de C# para comparar ambos lenguajes.

## Herramientas

- JDK
- Maven
- IntelliJ IDEA

## Organización

Un proyecto Maven por módulo, con una clase independiente por ejercicio en la estructura estándar `src/main/java`. No hay carpetas por ejercicio: cada ejercicio es una clase del módulo. La excepción es `01-introduction/`, que solo contiene documentación y mantiene una carpeta por lección.

```text
java/
└── 02-fundamentals/
    ├── pom.xml
    └── src/main/java/lab/fundamentals/
        ├── DataTypes.java
        └── Variables.java
```

Los nombres de clase usan PascalCase en inglés, equivalente al concepto de la lección (`04-data-types` → `DataTypes`). El número de la lección se indica en un comentario de la clase.

## Equivalencias con el curso de C#

Los módulos y su numeración coinciden con los del curso. Algunas lecciones usan la tecnología equivalente en Java:

| Curso (C#) | Java |
|---|---|
| 02 Visual Studio / 03 Proyecto C# | IntelliJ IDEA / proyecto Maven |
| 46 LINQ | Streams |
| 49–52 WPF | JavaFX |
| 53–58 ADO.NET | JDBC |
| 61 Task | CompletableFuture |

## Comandos básicos

```bash
mvn compile   # compilar
mvn test      # ejecutar pruebas
```

Los ejercicios se ejecutan principalmente desde IntelliJ IDEA.
