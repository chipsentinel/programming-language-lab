# Manejo de excepciones en C#

## Qué es una excepción

Un **error que ocurre mientras el programa se ejecuta** y que interrumpe su flujo normal: dividir entre cero, convertir a número un texto que no lo es, usar una posición que no existe en un array, abrir un archivo que no está...

No son errores de sintaxis (esos los detecta el compilador antes), sino de **situaciones que solo se dan al ejecutar**. Si no se controlan, **el programa se detiene** con un mensaje como:

```text
Unhandled exception. System.DivideByZeroException: Attempted to divide by zero.
```

El **manejo de excepciones** permite **capturar** el error, saber qué ha pasado y decidir cómo seguir. Se basa en la clase `Exception` del espacio de nombres `System`, que guarda información sobre el error: su **tipo**, su **mensaje** y la **pila de llamadas** (qué métodos llevaron hasta el error).

## `try` y `catch`

```csharp
try
{
    int resultado = valor1 / valor2;
    Console.WriteLine("Resultado: " + resultado);
}
catch (DivideByZeroException error)
{
    Console.WriteLine("Error: no se puede dividir entre cero");
}
```

```text
try    { código que puede fallar }
catch  { qué hacer si falla }
```

- **`try`**: se **intenta** ejecutar el bloque. Si todo va bien, `catch` se ignora.
- **`catch`**: si ocurre una excepción **del tipo indicado**, el programa **salta** aquí y no ejecuta lo que quedaba en el `try`.
- **`error`** es una variable (el nombre es libre: `e`, `ex`, `error`) con la información del error.
- En el `try` solo cuentan los errores **en ejecución**; los de sintaxis o de lógica no se capturan.

Lo que hay **después** del error dentro del `try` **no se ejecuta**: si falla la división, el `WriteLine("Resultado...")` se salta.

## Excepciones concretas

Cada error tiene **su tipo de excepción**. Algunas comunes:

| Excepción | Cuándo ocurre |
|---|---|
| `DivideByZeroException` | División entera entre cero |
| `FormatException` | Texto con formato incorrecto (`int.Parse("T")`) |
| `IndexOutOfRangeException` | Posición inexistente en un array |
| `ArgumentException` | Argumento inválido para un método |
| `NullReferenceException` | Usar un objeto que vale `null` |
| `FileNotFoundException` | Archivo que no existe |

### División entre cero

```csharp
int resultado = 20 / 0;      // DivideByZeroException
```

Solo ocurre con **enteros**: `20.0 / 0` da infinito, sin excepción (lección 10).

### Formato

```csharp
try
{
    int numero = int.Parse("T");
}
catch (FormatException error)
{
    Console.WriteLine("Eso no es un número entero");
}
```

`int.Parse` lanza `FormatException` si el texto no es un número. Es lo que pasaba con `Convert.ToInt32` cuando el usuario escribía una letra (lección 11).

### Array fuera de rango

```csharp
int[] numeros = { 10, 20, 30, 40 };    // posiciones 0 a 3
Console.WriteLine(numeros[5]);         // IndexOutOfRangeException
```

## El mensaje del error

El objeto `error` da información técnica:

```csharp
Console.WriteLine(error.Message);          // Attempted to divide by zero.
Console.WriteLine(error.GetType().Name);   // DivideByZeroException
Console.WriteLine(error.ToString());       // tipo + mensaje + pila de llamadas
```

- **`Message`**: el mensaje corto, útil para mostrar o registrar.
- **`ToString()`**: todo el detalle, incluida la pila de llamadas. Es lo que conviene guardar en un registro (*log*) para el desarrollador.
- Al usuario final conviene mostrarle **un mensaje comprensible**, no el técnico.

## Varios `catch`

Se pueden poner varios, para tratar de forma distinta cada tipo de error:

```csharp
try
{
    int numero = int.Parse(texto);
    Console.WriteLine(numero / divisor);
}
catch (FormatException)
{
    Console.WriteLine("Error de formato");
}
catch (DivideByZeroException)
{
    Console.WriteLine("Error: división entre cero");
}
catch (Exception error)
{
    Console.WriteLine("Error inesperado: " + error.Message);
}
```

- Se evalúan **en orden** y se ejecuta **solo el primero** que coincide.
- Lo **más específico va primero** y `Exception` (que atrapa cualquier error) **al final**. Si `Exception` fuera la primera, ocultaría a las demás, y el compilador da error.
- Si no hace falta usar el objeto, se puede omitir el nombre: `catch (FormatException)`.

## `finally`

Un bloque que se ejecuta **siempre**, haya error o no, incluso con `return` dentro del `try`:

```csharp
try
{
    // intentar algo
}
catch (Exception error)
{
    // tratar el error
}
finally
{
    Console.WriteLine("Fin de la operación");
}
```

Sirve para **liberar recursos**: cerrar un archivo, una conexión a una base de datos... (lecciones 42 y 53).

## Lanzar una excepción: `throw`

Un método puede **lanzar** su propia excepción cuando detecta algo incorrecto:

```csharp
public void ValidarEdad(int edad)
{
    if (edad < 0)
    {
        throw new ArgumentException("La edad no puede ser negativa");
    }

    Console.WriteLine("Edad válida: " + edad);
}
```

Quien llame al método decide qué hacer con el error:

```csharp
try
{
    ValidarEdad(-5);
}
catch (ArgumentException error)
{
    Console.WriteLine("Error: " + error.Message);
}
```

La excepción **sube** por la cadena de llamadas hasta que algún `catch` la captura; si ninguno lo hace, el programa se detiene.

## `TryParse` frente a `try / catch`

Para convertir texto a número hay dos caminos:

| | `TryParse` | `try / catch` |
|---|---|---|
| Qué hace | Devuelve `true` / `false` | Captura la excepción |
| Coste | **Bajo** | Mayor (lanzar excepciones es lento) |
| Cuándo | El error es **previsible y frecuente** (entrada del usuario) | El error es **excepcional** |

Las excepciones son para situaciones **excepcionales**, no para el flujo normal: si se puede comprobar de antemano (`if`, `TryParse`), es mejor.

## Ejemplo

```csharp
try
{
    Console.WriteLine("Ingrese un número:");
    int numero = int.Parse(Console.ReadLine() ?? "");
    Console.WriteLine("Número ingresado: " + numero);
}
catch (FormatException)
{
    Console.WriteLine("Eso no es un número entero");
}
finally
{
    Console.WriteLine("Fin del programa");
}

Console.ReadKey();
```

## Comandos

```bash
cd csharp/06-advanced-oop
dotnet new console -n ExceptionHandling -o 41-exception-handling
cd 41-exception-handling
dotnet run
```

Fuente: [curso de C# de INFORMATICONFIG](https://www.youtube.com/watch?v=1Zf9Pkxv8zM&list=PL2Z95CSZ1N4F7mym8Ca16RoTDVAlIOPlT&index=41), vídeo 41.
