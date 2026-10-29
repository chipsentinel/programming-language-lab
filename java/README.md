# Java

Lenguaje complementario. Replica los ejercicios de C# para comparar ambos lenguajes.

## Herramientas

- JDK
- Maven (solo para proyectos con dependencias; ver lección 03)
- IntelliJ IDEA

## Organización

Una carpeta por lección igual que en C# y JavaScript, con un único archivo `.java` y el `.md` de la lección. Se ejecuta directamente con `java Archivo.java`, sin proyecto Maven. Maven solo se usa en la lección 03 (`03-create-maven-project/`), que explica cómo es un proyecto Maven completo, y se retomará cuando haya dependencias.

```text
java/
└── 02-fundamentals/
    └── 04-data-types/
        ├── DataTypes.java
        └── data-types.md
```

Los nombres de clase usan PascalCase en inglés, equivalente al concepto de la lección (`04-data-types` → `DataTypes`).

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
java Archivo.java   # compilar y ejecutar un archivo
```

También se pueden ejecutar desde IntelliJ IDEA con el botón junto a `main`.

Para un proyecto Maven (lección 03): `mvn compile` y `mvn test`.
