# x-lenguaje

Plantilla para incorporar nuevos lenguajes al laboratorio. No es un lenguaje activo y no contiene código ni proyectos.

Incluye los ocho módulos y las carpetas de ejercicios del itinerario inicial. Los nombres son neutros (por ejemplo, `02-environment-setup`, `49-ui-introduction` o `53-database-mysql-connection`), sin referencias a tecnologías de C#. Se pueden renombrar al adaptar la plantilla (por ejemplo, `ui` por `tkinter`).

## Cómo crear un nuevo lenguaje

1. Copiar la carpeta `x-lenguaje/` en la raíz del repositorio con el nombre del lenguaje, en minúsculas (por ejemplo, `python/`).
2. Sustituir este README por uno propio con el objetivo, las herramientas, la organización de los ejercicios y los comandos básicos.
3. Añadir el lenguaje al README principal y a las columnas de `docs/progress.md`.

## Adaptar herramientas y configuración

Cada lenguaje usa las herramientas de su ecosistema: gestor de dependencias, sistema de compilación o ejecución e IDE. Los proyectos y archivos de configuración (`.csproj`, `pom.xml`, `package.json`, etc.) se crean dentro de las carpetas, según convenga a ese lenguaje.

Si hay configuraciones locales o artefactos de compilación nuevos, se añaden a `.gitignore`.

## Flexibilidad

Los nuevos lenguajes pueden añadir módulos específicos, omitir los que no apliquen o renombrarlos. No están obligados a seguir exactamente el itinerario inicial.
