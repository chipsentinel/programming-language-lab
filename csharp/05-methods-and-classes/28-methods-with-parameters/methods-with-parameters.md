# Métodos con parámetros en C#

## Qué es un parámetro

Un **dato de entrada** que un método recibe cuando se le llama. Gracias a los parámetros, el mismo método sirve para **valores distintos** sin tocar su código.

| Término | Qué es | Dónde está |
|---|---|---|
| **Parámetro** | La variable que el método declara para recibir un dato | En la **definición**: `Saludar(string nombre)` |
| **Argumento** | El valor concreto que se envía | En la **llamada**: `Saludar("Sol")` |

## El problema que resuelven

Con un atributo de la clase, un método siempre usa el mismo valor:

```csharp
class Prueba
{
    public string nombre = "Sol";

    public void SaludoConAtributo()
    {
        Console.WriteLine("Hola, " + nombre);   // siempre "Sol"
    }
}
```

Funciona, pero para saludar a otra persona habría que cambiar el atributo. Con un parámetro, cada llamada decide el valor.

## Definir un parámetro

Entre los paréntesis del método, con **tipo y nombre**:

```csharp
public void Saludar(string nombre)
{
    Console.WriteLine("Hola, " + nombre);
}
```

Y se llama pasando un **argumento**:

```csharp
objeto1.Saludar("Sol");       // Hola, Sol
objeto1.Saludar("Marcos");    // Hola, Marcos
```

El parámetro `nombre` se comporta como una variable que **vale el argumento** durante esa llamada.

## Varios parámetros

Se separan con comas, cada uno con su tipo:

```csharp
public void Presentar(string nombre, int edad)
{
    Console.WriteLine($"Nombre: {nombre}, edad: {edad}");
}

objeto1.Presentar("Sol", 34);    // Nombre: Sol, edad: 34
```

Pueden mezclarse tipos y no hay límite en la cantidad.

## Reglas de la llamada

Al llamar al método hay que respetar su definición:

- **La misma cantidad** de argumentos que de parámetros.
- **El mismo orden**.
- **Tipos compatibles** con los de los parámetros.

```csharp
objeto1.Presentar("Sol");         // error: falta la edad
objeto1.Presentar(34, "Sol");     // error: el orden y los tipos importan
```

El compilador lo detecta antes de ejecutar.

## Parámetros y retorno

Un método puede recibir datos **y** devolver un resultado:

```csharp
public int Sumar(int a, int b)
{
    return a + b;
}

public bool EsMayorDeEdad(int edad)
{
    return edad >= 18;
}
```

```csharp
int resultado = objeto1.Sumar(34, 22);                  // 56
Console.WriteLine(objeto1.Sumar(resultado, 4));         // 60
Console.WriteLine(objeto1.EsMayorDeEdad(15));           // False
```

- `return a + b;` calcula y devuelve en un paso.
- El argumento puede ser un **literal** (`34`), una **variable** (`resultado`) o una **expresión**.
- Para ver el resultado hay que **guardarlo** o mostrarlo: llamar a `Sumar(34, 22)` sin hacer nada con el valor lo pierde.

## Alcance: dónde existe cada variable

| Variable | Dónde existe |
|---|---|
| **Atributo** (declarado en la clase) | En **todos** los métodos de la clase |
| **Parámetro** | Solo **dentro de su método** |
| **Variable local** (declarada en un método) | Solo dentro de ese método |

> El vídeo llama "variable global" al atributo. En rigor no es global del programa: pertenece a la clase.

```csharp
public void Saludar(string nombre) { /* nombre existe aquí */ }

public void Despedir()
{
    Console.WriteLine(nombre);   // error: nombre es un parámetro de Saludar, no existe aquí
}
```

Dos métodos pueden tener un parámetro con el **mismo nombre** sin que se mezclen.

## Parámetros opcionales

Con un **valor por defecto**, el argumento deja de ser obligatorio:

```csharp
public void Despedir(string nombre, string mensaje = "Adiós")
{
    Console.WriteLine($"{mensaje}, {nombre}");
}

objeto1.Despedir("Sol");                    // Adiós, Sol
objeto1.Despedir("Sol", "Hasta pronto");    // Hasta pronto, Sol
```

Los opcionales van **siempre al final** de la lista.

## Argumentos con nombre

Se pueden indicar los argumentos **por nombre**, y así el orden da igual:

```csharp
objeto1.Presentar(edad: 34, nombre: "Sol");
```

Mejora la legibilidad cuando hay varios argumentos del mismo tipo.

## Argumentos que escribe el usuario

Los argumentos pueden venir de la entrada de datos:

```csharp
Console.WriteLine("Ingrese su nombre...");
string nombreUsuario = Console.ReadLine() ?? "";

Console.WriteLine("Ingrese su edad...");
int edadUsuario = Convert.ToInt32(Console.ReadLine());

objeto1.Presentar(nombreUsuario, edadUsuario);
```

Las variables `nombreUsuario` y `edadUsuario` son del método `Main`; se **pasan** al otro método como argumentos. Los nombres no tienen por qué coincidir con los de los parámetros.

## Ejemplo

```csharp
class Prueba
{
    public int Sumar(int a, int b)
    {
        return a + b;
    }

    public void Presentar(string nombre, int edad)
    {
        Console.WriteLine($"Nombre: {nombre}, edad: {edad}");
    }
}

class Program
{
    static void Main()
    {
        Prueba objeto1 = new Prueba();

        objeto1.Presentar("Sol", 34);
        Console.WriteLine("Suma: " + objeto1.Sumar(34, 22));

        Console.ReadKey();
    }
}
```

## Comandos

```bash
cd csharp/05-methods-and-classes
dotnet new console -n MethodsWithParameters -o 28-methods-with-parameters
cd 28-methods-with-parameters
dotnet run
```

Fuente: [curso de C# de INFORMATICONFIG](https://www.youtube.com/watch?v=6PJSvxFLt9k&list=PL2Z95CSZ1N4F7mym8Ca16RoTDVAlIOPlT&index=28), vídeo 28.
