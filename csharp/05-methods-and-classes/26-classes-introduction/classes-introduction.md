# Introducción a las clases en C#

## Un cambio de enfoque

Hasta ahora el código se escribía suelto, directamente en `Program.cs` (*instrucciones de nivel superior*). Para proyectos reales eso no basta: el código se **organiza en clases**. A partir de esta lección `Program.cs` tendrá clases y un método `Main`.

## Qué es una clase

Una **plantilla** (un molde) para crear objetos. Define **qué datos** tiene algo y, más adelante, **qué puede hacer**.

- Tipos como `int`, `string` o `bool` ya son clases de .NET (`Int32`, `String`, `Boolean`). Una variable de ese tipo es un **objeto** de esa clase.
- Con `class` se pueden crear **clases propias**: `Persona`, `Empleado`, `Venta`...

Sirven para:

- **Agrupar datos relacionados**: nombre, edad y salario de una persona van juntos.
- **Organizar el código** de forma estructurada y reutilizable.

## Definir una clase y sus atributos

```csharp
class Persona
{
    public string nombre = "Sol";
    public int edad = 34;
    public string direccion = "Calle Mayor 12";
    public double salario = 2500;
    public bool activo = true;
}
```

- **`class Persona`**: el nombre de la clase, en `PascalCase` y en singular.
- **Las llaves `{ }`** delimitan el **cuerpo** de la clase.
- **Atributos** (o campos): variables declaradas dentro de la clase. Describen cómo es cada objeto de esa clase.
- **`public`** permite usarlos desde fuera de la clase. Sin él serían privados; se verá con el encapsulamiento.

Un atributo sin valor inicial recibe el **valor por defecto** de su tipo (`0`, `false`, `null`...):

```csharp
class Venta
{
    public string vendedor = "";
    public int cantidades;    // vale 0
}
```

Una clase, por sí sola, **no hace nada**: es solo la plantilla.

## El método `Main`

Todo programa de consola empieza en **`Main`**: es el primer método que se ejecuta.

```csharp
class Program
{
    static void Main()
    {
        // aquí empieza el programa
    }
}
```

`static`, `void` y los paréntesis se explicarán en la lección de métodos. Por ahora basta con memorizar esta estructura. Una clase puede tener tantos métodos como se quiera, pero el programa necesita un `Main`.

## Instanciar: crear un objeto

Para usar una clase hay que **crear un objeto** de ella. Se llama **instanciar**:

```csharp
Persona persona1 = new Persona();
```

```text
Persona   persona1   =   new   Persona();
clase     objeto          instancia
```

- Se escribe primero la **clase** (el tipo) y después el **nombre del objeto**.
- **`new Persona()`** construye el objeto a partir de la plantilla. Los paréntesis se explicarán con los constructores.
- De una misma clase se pueden crear **tantos objetos como se quiera**.

## Acceder a los atributos: `objeto.atributo`

Con un punto entre el objeto y el atributo:

```csharp
Console.WriteLine(persona1.nombre);                  // Sol
Console.WriteLine($"Edad: {persona1.edad}");         // Edad: 34
```

Al escribir el punto, el editor lista todos los atributos disponibles. También se pueden **cambiar**:

```csharp
persona1.edad = 35;
```

## Cada objeto tiene sus propios valores

La clase es el molde; los objetos son las copias. Cambiar uno **no afecta** a los demás:

```csharp
Persona persona2 = new Persona();
persona2.nombre = "Marcos";
persona2.edad = 28;

Console.WriteLine($"{persona1.nombre} tiene {persona1.edad} años");   // Sol tiene 35 años
Console.WriteLine($"{persona2.nombre} tiene {persona2.edad} años");   // Marcos tiene 28 años
```

## Varias clases

Un programa puede tener tantas clases como necesite (`Persona`, `Venta`...). La que contiene `Main` es por la que arranca; desde ahí se crean objetos de las demás.

```csharp
Venta venta1 = new Venta();
venta1.vendedor = "Larry";
venta1.cantidades = 500;
```

## Ejemplo

```csharp
class Persona
{
    public string nombre = "Sol";
    public int edad = 34;
}

class Program
{
    static void Main()
    {
        Persona persona1 = new Persona();

        Console.WriteLine($"Nombre: {persona1.nombre}");
        Console.WriteLine($"Edad: {persona1.edad}");

        Console.ReadKey();
    }
}
```

## Comandos

```bash
cd csharp/05-methods-and-classes
dotnet new console -n ClassesIntroduction -o 26-classes-introduction
cd 26-classes-introduction
dotnet run
```

Fuente: [curso de C# de INFORMATICONFIG](https://www.youtube.com/watch?v=7Kn9S2Sy1Hc&list=PL2Z95CSZ1N4F7mym8Ca16RoTDVAlIOPlT&index=26), vídeo 26.
