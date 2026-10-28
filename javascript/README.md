# JavaScript

Lenguaje complementario. Replica los ejercicios de C# para comparar ambos lenguajes.

## Herramientas

- Node.js
- npm
- Visual Studio Code

## Organización

Un proyecto npm por módulo, con un archivo independiente por ejercicio.

```text
javascript/
└── 02-fundamentals/
    ├── package.json
    └── 05-variables/
        └── index.js
```

## Nombres y equivalencias

La numeración coincide con la de los vídeos del curso de C#. Los nombres de las carpetas usan la tecnología equivalente en JavaScript:

| Curso (C#) | Carpeta en JavaScript |
|---|---|
| 01 Introducción a C# | `01-introduction-javascript` |
| 02 Visual Studio | `02-installation-nodejs-vscode` |
| 03 Proyecto C# | `03-create-npm-project` |
| 46 LINQ | `46-array-methods` |
| 49–52 WPF | `49`–`52-dom-…` (HTML / CSS / DOM) |
| 53–58 ADO.NET | `53`–`58-database-…` (controladores npm) |

## Comandos básicos

```bash
npm init -y                  # inicializar el módulo
node 05-variables/index.js   # ejecutar un ejercicio
```
