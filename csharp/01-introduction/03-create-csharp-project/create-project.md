# Crear un proyecto en C#

Guía rápida con los comandos para crear y ejecutar un ejercicio. Un proyecto .NET independiente por ejercicio.

## Crear el proyecto

Desde la carpeta del módulo (la carpeta del ejercicio ya existe en el repositorio):

```bash
cd csharp/02-fundamentals
dotnet new console -n Exercise -o 05-variables
```
-n => nombre del proyecto (genera `Exercise.csproj`)
-o => carpeta de salida donde se crea el proyecto

Esto genera dentro de `05-variables/`:

```text
05-variables/
├── Exercise.csproj
└── Program.cs
```

Si la carpeta del ejercicio no existe, `dotnet new` la crea. Si contiene un `.gitkeep`, se puede borrar.

## Ejecutar y compilar

```bash
cd 05-variables
dotnet run
dotnet build
```

## Otros comandos útiles

```bash
dotnet new list            # plantillas disponibles
dotnet new classlib -n Exercise -o 26-classes-introduction   # biblioteca de clases
dotnet --version           # versión del SDK
```

## Resultado de `dotnet new console`

El `Program.cs` generado usa *top-level statements* (sin `Main` explícito) en las versiones recientes de .NET:

```csharp
Console.WriteLine("Hello, World!");
```

Los archivos `bin/` y `obj/` se generan al compilar y están en `.gitignore`.

Fuente: [curso de C# de INFORMATICONFIG](https://www.youtube.com/watch?v=SgV0SG-0LFo&list=PL2Z95CSZ1N4F7mym8Ca16RoTDVAlIOPlT&index=3), vídeo 03.
