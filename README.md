# 💻 Programming Language Lab

### Laboratorio de programación multilenguaje

**C# · Java · JavaScript**

Repositorio personal dedicado al aprendizaje y práctica de programación mediante la resolución de ejercicios equivalentes en distintos lenguajes.

El proyecto comienza con **C# como lenguaje principal**, utilizando Java y JavaScript para reforzar conocimientos, comparar implementaciones y comprender las particularidades de cada ecosistema.

Forma parte de mi formación en **Desarrollo de Aplicaciones Web (DAW)** y está diseñado para ampliarse en el futuro con otros lenguajes, como Python o PHP.

## 🎯 Objetivos

- Aprender C# y el ecosistema .NET.
- Consolidar conocimientos de Java y JavaScript.
- Mejorar la lógica de programación y la resolución de problemas.
- Practicar algoritmos, estructuras de datos y programación orientada a objetos.
- Familiarizarse con herramientas profesionales de desarrollo.
- Comparar diferentes soluciones a un mismo problema.

> El objetivo es comprender los conceptos de programación, no traducir código literalmente entre lenguajes.

---

## 🛠️ Tecnologías y herramientas

| Lenguaje | Entorno | Herramientas | IDE |
|---|---|---|---|
| C# | .NET | .NET CLI / NuGet | Visual Studio Code |
| Java | JDK / JVM | Maven | IntelliJ IDEA |
| JavaScript | Node.js | npm | Visual Studio Code |

**Control de versiones:** Git y GitHub.

Cada lenguaje utiliza sus propias herramientas de creación, compilación, ejecución y gestión de dependencias.

---

## 📂 Estructura del repositorio

El proyecto está organizado como un **monorepositorio**, con un directorio independiente para cada lenguaje.

```text
programming-language-lab/
│
├── README.md
├── LICENSE
├── .gitignore
│
├── csharp/
│   ├── README.md
│   ├── 01-introduction/
│   │   ├── 01-introduction-csharp/
│   │   ├── 02-installation-visual-studio/
│   │   └── 03-create-csharp-project/
│   ├── 02-fundamentals/
│   ├── 03-control-flow/
│   ├── 04-data-structures/
│   ├── 05-methods-and-classes/
│   ├── 06-advanced-oop/
│   ├── 07-ui-and-databases/
│   └── 08-concurrency/
│
├── java/
│   ├── README.md
│   └── 01-introduction/ … 08-concurrency/   (un proyecto Maven por módulo)
│
├── javascript/      (misma organización que csharp/)
│
├── x-lenguaje/      (plantilla para futuros lenguajes)
│   ├── README.md
│   └── 01-introduction/ … 08-concurrency/
```

Los módulos agrupan los ejercicios por temática y mantienen una numeración común para facilitar su localización y comparación. En C#, JavaScript y `x-lenguaje/` cada ejercicio tiene una carpeta con el número de su vídeo. En Java no hay carpetas por ejercicio: cada módulo es un proyecto Maven y cada ejercicio, una clase (salvo `01-introduction/`, que solo contiene documentación).

Los nombres de las lecciones que dependen de la tecnología (introducción, instalación, creación de proyecto, LINQ, WPF y ADO.NET) usan el equivalente de cada lenguaje; el detalle está en el README de cada uno.

`x-lenguaje/` es una plantilla para futuros lenguajes; no es un lenguaje activo.

Cada lenguaje dispone de una organización interna adaptada a sus herramientas de desarrollo.

---

## 📚 Curso de referencia

El itinerario inicial está basado en el curso de programación en C# de **INFORMATICONFIG**, compuesto por 62 vídeos.

**[Ver curso completo en YouTube](https://www.youtube.com/playlist?list=PL2Z95CSZ1N4F7mym8Ca16RoTDVAlIOPlT)**

| Módulo | Contenido | Vídeos |
|---|---|---|
| 01 | Introducción y configuración | 01–03 |
| 02 | Fundamentos de programación | 04–14 |
| 03 | Estructuras de control | 15–21 |
| 04 | Estructuras de datos | 22–25 |
| 05 | Métodos y clases | 26–34 |
| 06 | Programación orientada a objetos avanzada | 35–48 |
| 07 | Interfaces gráficas y bases de datos | 49–58 |
| 08 | Concurrencia y programación asíncrona | 59–62 |
| **Total** | **8 módulos** | **62 vídeos** |

Los ejercicios se desarrollan inicialmente en C# y posteriormente se implementan en Java y JavaScript, utilizando las alternativas apropiadas de cada lenguaje.

---

## ⚙️ Organización técnica

### 🔷 C# — .NET

Se utiliza **un proyecto .NET independiente por ejercicio práctico**, generado mediante .NET CLI.

```text
csharp/
└── 02-fundamentals/
    └── 05-variables/
        ├── Program.cs
        └── Exercise.csproj
```

**Crear un proyecto:**

```bash
dotnet new console -n Exercise -o 05-variables
```

**Ejecutar:**

```bash
cd 05-variables
dotnet run
```

**Compilar:**

```bash
dotnet build
```

Los ejercicios avanzados podrán utilizar otras plantillas de .NET y dependencias NuGet.

### ☕ Java — Maven

Se utiliza **un proyecto Maven por módulo**, con clases independientes para cada ejercicio.

```text
java/
└── 02-fundamentals/
    ├── pom.xml
    └── src/
        ├── main/
        │   └── java/
        │       └── lab/
        │           └── fundamentals/
        │               ├── DataTypes.java
        │               └── Variables.java
        └── test/
            └── java/
```

Los proyectos se generan mediante Maven o los asistentes de IntelliJ IDEA.

**Compilar:**

```bash
mvn compile
```

**Ejecutar pruebas:**

```bash
mvn test
```

Los ejercicios se ejecutan principalmente desde IntelliJ IDEA.

### 🟨 JavaScript — Node.js

Se utiliza **un proyecto npm por módulo**, con archivos independientes para cada ejercicio.

```text
javascript/
└── 02-fundamentals/
    ├── package.json
    ├── 04-data-types/
    │   └── index.js
    └── 05-variables/
        └── index.js
```

**Inicializar un proyecto:**

```bash
npm init -y
```

**Ejecutar un ejercicio:**

```bash
node 05-variables/index.js
```

Las dependencias se administran mediante npm cuando son necesarias.

---

## 🧠 Metodología de aprendizaje

Cada concepto se trabaja siguiendo el mismo proceso:

1. **C#:** estudiar el concepto y desarrollar el ejercicio.
2. **Java:** implementar una solución equivalente.
3. **JavaScript:** resolver el mismo problema utilizando Node.js.
4. **Comparación:** identificar diferencias de sintaxis, tipado, estructuras y funcionamiento.

No todos los conceptos tienen una equivalencia directa entre lenguajes.

Por ejemplo:

| C# | Java | JavaScript |
|---|---|---|
| LINQ | Streams | `map()`, `filter()`, `reduce()` |
| WPF | JavaFX | HTML / CSS / DOM |
| ADO.NET | JDBC | Controladores de bases de datos |
| Task | CompletableFuture | Promise |
| Dictionary | HashMap | Map |

Las alternativas se seleccionan según las características de cada ecosistema.

---

## 🌍 Ampliación a nuevos lenguajes

Se podrán incorporar nuevos lenguajes (Python, PHP, TypeScript, Go, Rust u otros) copiando la plantilla [`x-lenguaje/`](x-lenguaje/README.md) con el nombre del lenguaje.

Cada nuevo lenguaje tendrá su propio directorio y utilizará las herramientas de su ecosistema. Podrá añadir módulos específicos y no está obligado a seguir exactamente el itinerario inicial.

---

## 👨‍💻 Autor

**Cristian Gil González**

Estudiante de Desarrollo de Aplicaciones Web (DAW).

**GitHub:** [@chipsentinel](https://github.com/chipsentinel)

