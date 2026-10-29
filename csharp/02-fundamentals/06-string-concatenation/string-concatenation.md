# Concatenación de cadenas en C#

## Qué es concatenar

Concatenar es **unir varios textos en uno solo**. En C# se puede hacer de tres formas habituales: con el operador `+`, con interpolación `$"..."` y con `string.Format`.

```csharp
string nombre = "Sol";
int edad = 30;
```

## Con el operador `+`

Une textos con `+`. Si uno de los operandos es un número, C# lo convierte a texto automáticamente.

```csharp
Console.WriteLine("Nombre de usuario: " + nombre + ", edad: " + edad);
// Nombre de usuario: Sol, edad: 30
```

Hay que cuidar los espacios y los signos: `"Nombre:" + nombre` da `Nombre:Sol`, sin espacio.

## Con interpolación `$"..."`

El `$` delante de las comillas permite escribir variables entre llaves `{}` dentro del texto. Es la forma más legible y la que se usa por defecto.

```csharp
Console.WriteLine($"Nombre de usuario: {nombre}, edad: {edad}");
// Nombre de usuario: Sol, edad: 30
```

## Salto de línea con `\n`

`\n` dentro del texto es un salto de línea. Con él se escribe en varias líneas con un solo `WriteLine`.

```csharp
Console.WriteLine($"Nombre de usuario: {nombre} \nEdad: {edad}");
// Nombre de usuario: Sol
// Edad: 30
```

Si hay un espacio antes de `\n` (`{nombre} \n`), ese espacio queda al final de la primera línea. No se ve, pero está.

## `Write` y `WriteLine`

| Método | Qué hace |
|---|---|
| `Console.WriteLine(...)` | Escribe el texto y salta de línea |
| `Console.Write(...)` | Escribe el texto **sin** saltar de línea |

Dos llamadas seguidas pueden formar una sola línea:

```csharp
Console.Write($"Nombre usuario: {nombre}");
Console.WriteLine($", edad: {edad}");
// Nombre usuario: Sol, edad: 30
```

## Expresiones dentro de las llaves

Dentro de `{ }` se puede escribir una expresión, no solo una variable. Se calcula al construir el texto y **la variable no cambia**.

```csharp
Console.WriteLine($"Nombre de usuario: {nombre}, edad: {edad + 5}");
// Nombre de usuario: Sol, edad: 35
```

Después de esa línea, `edad` sigue valiendo `30`.

## Con `string.Format`

Los huecos se indican con `{0}`, `{1}`… y los valores se pasan después, en orden. Es la forma más antigua; hoy se prefiere la interpolación.

```csharp
Console.WriteLine(string.Format("Nombre de usuario: {0}, edad: {1}", nombre, edad));
// Nombre de usuario: Sol, edad: 30
```

## Cambiar el valor de una variable y volver a mostrarla

Una variable se puede reasignar, y cada `Console.WriteLine` muestra el valor que tiene **en ese momento**.

```csharp
int edad = 64;
Console.WriteLine(edad);   // 64

edad = 32;
Console.WriteLine(edad);   // 32
```

Mismo caso con texto:

```csharp
string nombre = "Sol";
Console.WriteLine($"Nombre: {nombre}");   // Nombre: Sol

nombre = "Luna";
Console.WriteLine($"Nombre: {nombre}");   // Nombre: Luna
```

El tipo no cambia: `edad` sigue siendo `int` aunque cambie su valor. Asignar un valor de otro tipo es un error de compilación:

```csharp
edad = "treinta";   // error: no se puede convertir string a int
```

## El texto ya construido no se actualiza

Al concatenar o interpolar, el resultado es **un texto nuevo con los valores de ese momento**. Si después cambia la variable, el texto ya construido **no cambia**.

```csharp
int edad = 64;
string mensaje = $"Edad: {edad}";

edad = 32;

Console.WriteLine(mensaje);          // Edad: 64   (guardó el valor de antes)
Console.WriteLine($"Edad: {edad}");  // Edad: 32   (se construye de nuevo)
```

Para ver el valor nuevo hay que volver a construir el texto después del cambio.

## Orden al sumar y concatenar

Con `+`, C# evalúa de izquierda a derecha. Mientras ambos lados sean números, **suma**; en cuanto aparece un texto, **concatena**.

```csharp
Console.WriteLine(1 + 2 + "a");   // 3a   (primero suma 1 + 2, luego concatena)
Console.WriteLine("a" + 1 + 2);   // a12  (concatena desde el principio)
Console.WriteLine("a" + (1 + 2)); // a3   (los paréntesis fuerzan la suma)
```

Con interpolación no hay esta ambigüedad: `{1 + 2}` se calcula dentro de las llaves.

## Los textos son inmutables

Un `string` no se modifica: concatenar crea una cadena nueva y la original queda igual.

```csharp
string saludo = "Hola";
string otro = saludo + " Sol";   // crea un texto nuevo; saludo sigue valiendo "Hola"
saludo = saludo + " Sol";        // ahora saludo vale "Hola Sol"
saludo += "!";                   // forma corta de saludo = saludo + "!"
```

## Ejemplo del ejercicio

```csharp
string nombre = "Sol";
int edad = 30;

Console.WriteLine("Nombre de usuario: " + nombre + ", edad: " + edad);
Console.WriteLine($"Nombre de usuario: {nombre}, edad: {edad}");
Console.WriteLine($"Nombre de usuario: {nombre} \nEdad: {edad}");

Console.Write($"Nombre usuario: {nombre}");
Console.WriteLine($", edad: {edad}");

Console.WriteLine($"Nombre de usuario: {nombre}, edad: {edad + 5}");

// Espera a que se pulse una tecla antes de cerrar la consola
Console.ReadKey();
```

Salida:

```text
Nombre de usuario: Sol, edad: 30
Nombre de usuario: Sol, edad: 30
Nombre de usuario: Sol
Edad: 30
Nombre usuario: Sol, edad: 30
Nombre de usuario: Sol, edad: 35
```

## Resumen

| Forma | Ejemplo | Cuándo |
|---|---|---|
| Operador `+` | `"Edad: " + edad` | Uniones simples |
| Interpolación | `$"Edad: {edad}"` | **La opción por defecto** |
| `string.Format` | `string.Format("Edad: {0}", edad)` | Código antiguo |

| Caso | Resultado |
|---|---|
| `\n` dentro del texto | Salto de línea |
| `Write` + `WriteLine` | Una sola línea |
| `{edad + 5}` | Calcula 35; `edad` sigue siendo 30 |
| Reasignar y volver a mostrar | Muestra el valor actual |
| Cambiar la variable después de construir el texto | El texto ya construido no cambia |
| `1 + 2 + "a"` | `3a` |
| `"a" + 1 + 2` | `a12` |

## Comandos

```bash
cd csharp/02-fundamentals
dotnet new console -n StringConcatenation -o 06-string-concatenation
cd 06-string-concatenation
dotnet run
```

Fuente: [curso de C# de INFORMATICONFIG](https://www.youtube.com/watch?v=FguZes6FTu8&list=PL2Z95CSZ1N4F7mym8Ca16RoTDVAlIOPlT&index=6), vídeo 06.
