# La clase `string` en C#

## Qué es

`string` es el tipo de dato para **texto**: una secuencia de caracteres. Es una **clase**, lo que significa que además de guardar el texto trae un conjunto de **funciones y propiedades** para trabajar con él: ver su longitud, pasarlo a mayúsculas, buscar dentro, cortarlo...

```csharp
string texto = "Hola a todos";
```

Para usar una función se escribe un punto después de la variable: `texto.ToUpper()`. Visual Studio Code muestra al escribir el punto la lista de todo lo disponible.

## Texto en varias líneas

Un texto largo se sale de la pantalla y es incómodo de leer. Hay dos formas de repartirlo en líneas.

**1. Concatenar con `+`**, y el salto de línea con `\n`:

```csharp
string lineas = "Esta es la primera línea\n" +
                "Esta es la segunda línea\n" +
                "Esta es la tercera línea";
```

- El `+` une las partes **en el código**; es el `\n` el que hace el salto **al mostrarlo**.

**2. Texto literal con `@`**: se escribe tal cual, con saltos de línea reales, sin `+` ni `\n`:

```csharp
string lineas = @"Esta es la primera línea
Esta es la segunda línea
Esta es la tercera línea";
```

Todo lo que hay entre las comillas se guarda como está, incluidos los espacios del margen izquierdo.

## Caracteres especiales

Algunos caracteres no se pueden escribir directamente dentro de un texto. Se usan **secuencias de escape** con la barra invertida `\`:

| Secuencia | Significa |
|---|---|
| `\n` | Salto de línea |
| `\t` | Tabulador |
| `\"` | Comillas dobles |
| `\\` | Una barra invertida |

```csharp
Console.WriteLine("Comillas: \"mundo\"");     // Comillas: "mundo"
Console.WriteLine("Ruta: C:\\Datos\\foto");   // Ruta: C:\Datos\foto
```

Las comillas dentro de un texto necesitan `\"`: si no, el compilador cree que el texto termina ahí.

## Funciones principales

Con `texto = "Hola a todos"`:

| Función | Qué hace | Resultado |
|---|---|---|
| `texto.Length` | Número de caracteres | `12` |
| `texto.ToUpper()` | Pasa a mayúsculas | `HOLA A TODOS` |
| `texto.ToLower()` | Pasa a minúsculas | `hola a todos` |
| `texto.Contains("todos")` | ¿Contiene ese texto? | `True` |
| `texto.StartsWith("Hola")` | ¿Empieza por…? | `True` |
| `texto.EndsWith("todos")` | ¿Termina en…? | `True` |
| `texto.IndexOf("do")` | Posición donde aparece | `9` |
| `texto.Substring(2, 4)` | Extrae una parte | `la a` |
| `texto.Replace("a todos", "mundo")` | Sustituye texto | `Hola mundo` |
| `"  hola  ".Trim()` | Quita espacios de los extremos | `hola` |

`Length` **no lleva paréntesis** porque es una propiedad (un dato); el resto son funciones y sí los llevan.

### Longitud

Cuenta **todos** los caracteres: letras, signos de puntuación y **espacios**. Un texto de cuatro espacios tiene longitud 4.

### Mayúsculas y minúsculas

```csharp
Console.WriteLine(texto.ToUpper());   // HOLA A TODOS
Console.WriteLine(texto.ToLower());   // hola a todos
```

### `Contains`, `StartsWith`, `EndsWith`

Devuelven `true` o `false` y **distinguen mayúsculas de minúsculas**:

```csharp
Console.WriteLine(texto.StartsWith("Hola"));   // True
Console.WriteLine(texto.StartsWith("hola"));   // False: la h es minúscula
Console.WriteLine(texto.Contains("mundo"));    // False
```

## Índices: la posición empieza en 0

Cada carácter tiene una posición, y **la primera es la 0**, no la 1:

```text
H  o  l  a     a     t  o  d  o  s
0  1  2  3  4  5  6  7  8  9  10 11
```

```csharp
Console.WriteLine(texto[0]);             // H
Console.WriteLine(texto.IndexOf("do"));  // 9
Console.WriteLine(texto.IndexOf("tr"));  // -1
```

- `IndexOf` devuelve la posición de la **primera coincidencia**.
- Si el texto **no existe** devuelve **`-1`**. Un `-1` siempre significa "no encontrado".

## `Substring`

Extrae una parte del texto: `Substring(inicio, cantidad)`.

```csharp
texto.Substring(2, 4);   // "la a": desde la posición 2, cuatro caracteres
texto.Substring(1, 4);   // "ola ": desde la 1, cuatro caracteres
texto.Substring(5);      // "a todos": desde la 5 hasta el final
```

Los espacios también cuentan como caracteres: en `"la a"` hay un espacio.

## `Replace`

Sustituye **todas** las apariciones de un texto por otro:

```csharp
Console.WriteLine(texto.Replace("a todos", "mundo"));   // Hola mundo
```

## `Trim`

Quita los espacios del **principio y del final** (no los de en medio):

```csharp
string conEspacios = "   Hola a todos   ";

Console.WriteLine(conEspacios.Length);         // 18
Console.WriteLine(conEspacios.Trim().Length);  // 12
```

## Los `string` no se modifican

Las funciones **no cambian el texto original**: devuelven uno nuevo. Si no se guarda, el resultado se pierde.

```csharp
texto.ToUpper();                       // no hace nada visible
Console.WriteLine(texto);              // Hola a todos: sigue igual

string mayusculas = texto.ToUpper();   // así se conserva
```

## Encadenar funciones

Se pueden unir varias, y se aplican de **izquierda a derecha**:

```csharp
Console.WriteLine(conEspacios.Trim().ToUpper());   // HOLA A TODOS
Console.WriteLine(conEspacios.Trim().Length);      // 12
```

En el último caso se muestra el resultado de `Length`, que es lo que devuelve la última del encadenamiento; `Trim` actúa antes, pero no se ve.

## Ejemplo

```csharp
string texto = "Hola a todos";

Console.WriteLine($"Texto: {texto}");
Console.WriteLine($"Caracteres: {texto.Length}");
Console.WriteLine($"Mayúsculas: {texto.ToUpper()}");
Console.WriteLine($"Contiene \"todos\": {texto.Contains("todos")}");
Console.WriteLine($"Reemplazado: {texto.Replace("a todos", "mundo")}");

Console.ReadKey();
```

## Comandos

```bash
cd csharp/04-data-structures
dotnet new console -n StringClass -o 23-string-class
cd 23-string-class
dotnet run
```

Fuente: [curso de C# de INFORMATICONFIG](https://www.youtube.com/watch?v=v1f3dJsNreU&list=PL2Z95CSZ1N4F7mym8Ca16RoTDVAlIOPlT&index=23), vídeo 23.
