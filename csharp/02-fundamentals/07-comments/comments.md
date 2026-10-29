# Comentarios en C#

## Qué es un comentario

Un comentario es texto que **el compilador ignora**. Sirve para tres cosas:

- **Explicar** el código a quien lo lee, incluido tú dentro de unos meses.
- **Desactivar** líneas sin borrarlas.
- **Organizar** el archivo en secciones o dejar avisos pendientes.

Los comentarios **no afectan al programa**: no se ejecutan, no ocupan memoria al ejecutar y no cambian la velocidad.

## Formas de escribir comentarios

| Forma | Sintaxis | Uso |
|---|---|---|
| Una línea | `// texto` | Comentarios cortos |
| Varias líneas | `/* texto */` | Bloques largos o desactivar varias líneas |
| Documentación XML | `/// texto` | Documentar métodos, clases y propiedades |

### Una línea: `//`

Todo lo que hay a la derecha de `//` se ignora. Puede ir **encima** de la línea que explica, que es lo más habitual, o **al final** de una instrucción.

```csharp
// En su propia línea
int anio = 2025;   // al final de una línea
```

Un comentario al final sirve para aclarar un dato concreto, como la unidad de un número:

```csharp
int espera = 30;   // segundos
```

Para un comentario de varias líneas con `//` se repite en cada una:

```csharp
// Varias líneas
// con // en cada una
// como esta
```

### Varias líneas: `/* ... */`

Todo lo que hay entre `/*` y `*/` se ignora, ocupe las líneas que ocupe.

```csharp
/*
   Este comentario ocupa
   varias líneas.
*/
```

También puede ir en medio de una línea:

```csharp
int total = 10 /* valor inicial */ + 5;
```

Los comentarios de bloque **no se pueden anidar**: el primer `*/` cierra el comentario, aunque hubiera otro `/*` dentro.

```csharp
/* exterior /* interior */ esto ya no es comentario */   // error
```

### Documentación XML: `///`

Se escribe encima de una clase, método o propiedad. Los editores muestran ese texto al pasar el ratón por encima o al escribir la llamada. Se generan solos al escribir `///` sobre un método.

```csharp
/// <summary>
/// Suma dos números enteros.
/// </summary>
/// <param name="a">Primer número.</param>
/// <param name="b">Segundo número.</param>
/// <returns>La suma de a y b.</returns>
static int Sumar(int a, int b)
{
    return a + b;
}
```

Las etiquetas más habituales:

| Etiqueta | Para qué |
|---|---|
| `<summary>` | Qué hace (la más importante) |
| `<param name="x">` | Qué es cada parámetro |
| `<returns>` | Qué devuelve |
| `<remarks>` | Notas adicionales |
| `<example>` | Un ejemplo de uso |
| `<exception cref="…">` | Qué excepción puede lanzar |
| `<see cref="…"/>` | Enlace a otro elemento |

Los métodos y las excepciones se ven en lecciones posteriores; aquí basta con saber que existen.

C# también admite `/** ... */` para documentación XML, pero casi no se usa: se prefiere `///`.

## Desactivar código

Un uso muy habitual es **desactivar código** sin borrarlo, por ejemplo para probar algo. Hay tres formas:

| Forma | Cuándo |
|---|---|
| `//` | Una línea o pocas |
| `/* */` | Un bloque, si no contiene otro `/* */` |
| `#if false` | Un bloque que **sí** puede contener `/* */` |

```csharp
// Con //
// Console.WriteLine("Esta línea no se ejecuta");

// Con /* */
/*
Console.WriteLine("Primera");
Console.WriteLine("Segunda");
*/

// Con #if false (admite /* */ dentro)
#if false
Console.WriteLine("Esto no se compila");
/* incluso con comentarios de bloque dentro */
#endif
```

`#if false` es una directiva del compilador, no un comentario, pero se usa para lo mismo. Se verá con más detalle en lecciones avanzadas.

No conviene dejar mucho código comentado: confunde. Para guardar versiones antiguas está Git.

## Organizar: etiquetas, separadores y regiones

### Etiquetas

Muchos editores resaltan ciertas palabras en los comentarios. Sirven para dejar avisos a uno mismo:

```csharp
// TODO: validar que la edad no sea negativa
// FIXME: falla si el nombre está vacío
// NOTE: este valor es provisional
// HACK: solución temporal hasta cambiar la librería
```

| Etiqueta | Significado |
|---|---|
| `TODO` | Algo pendiente de hacer |
| `FIXME` | Algo que falla y hay que arreglar |
| `NOTE` | Una aclaración importante |
| `HACK` | Una solución provisional |

Visual Studio las lista en la ventana *Task List*; en Visual Studio Code se resaltan con extensiones.

### Separadores

Se pueden usar para dividir un archivo en secciones:

```csharp
// ----- Datos -----
string nombre = "Sol";
int edad = 30;

// ----- Salida -----
Console.WriteLine($"{nombre}, {edad}");
```

### Regiones

`#region` permite **plegar** un bloque en el editor:

```csharp
#region Datos
string ciudad = "Madrid";
int espera = 30;
#endregion
```

## Comodidad: atajos de teclado

Los editores comentan y descomentan varias líneas de una vez: se seleccionan las líneas y se pulsa el atajo.

| Acción | Visual Studio Code | Visual Studio |
|---|---|---|
| Comentar / descomentar línea (`//`) | `Ctrl + /` | `Ctrl + K`, `Ctrl + C` / `Ctrl + K`, `Ctrl + U` |
| Comentar / descomentar bloque (`/* */`) | `Shift + Alt + A` | — |

- En Visual Studio Code, `Ctrl + /` **alterna**: si la línea ya está comentada, la descomenta.
- Sin selección, afecta a la línea donde está el cursor.
- Con varias líneas seleccionadas, afecta a todas.
- Funciona igual en Java y JavaScript.

## Casos curiosos

### Un `//` dentro de un texto no es un comentario

Si `//` o `/*` están dentro de comillas, son parte del texto:

```csharp
Console.WriteLine("Visita https://ejemplo.com");   // se muestra la URL completa
Console.WriteLine("// esto no es un comentario");  // se muestra tal cual
```

### El comentario de la plantilla

Al crear un proyecto con `dotnet new console`, la primera línea de `Program.cs` puede ser un comentario de la plantilla:

```csharp
// See https://aka.ms/new-console-template for more information
```

Se puede borrar sin problema.

## Qué comentar y qué no

Un buen comentario explica **por qué**, no **qué**: el código ya dice lo que hace.

```csharp
// Mal: repite lo que ya dice el código
edad = edad + 1;   // suma 1 a edad

// Bien: explica el motivo
edad = edad + 1;   // el usuario acaba de cumplir años
```

| Conviene | Evitar |
|---|---|
| Explicar el motivo de una decisión | Repetir lo que hace cada línea |
| Avisar de casos raros | Dejar mucho código comentado sin uso |
| Documentar métodos y clases | Comentarios que ya no coinciden con el código |
| Dejar `TODO` con una descripción clara | Comentarios vagos como `// arreglar esto` |

Si el código necesita muchos comentarios para entenderse, suele ser mejor dar nombres más claros a las variables. Un comentario desactualizado es peor que no tener ninguno.

## Resumen

| Quiero… | Uso |
|---|---|
| Un comentario corto | `// texto` |
| Un comentario largo | `/* texto */` |
| Documentar un método | `/// <summary>` |
| Desactivar una línea | `// código` |
| Desactivar un bloque | `/* código */` o `#if false` |
| Dejar un aviso | `// TODO: …` |
| Plegar un bloque | `#region` / `#endregion` |
| Comentar rápido | `Ctrl + /` en Visual Studio Code |

## Ejemplo del ejercicio

Reúne todos los casos de esta lección en un solo programa, en el mismo orden que este documento:

```csharp
string nombre = "Sol";
int edad = 30;

// Salidatos
Console.WriteLine("Nombre de usuario: " + nombre + ", edad: " + edad);
Console.WriteLine($"Nombre de usuario: {nombre}, edad: {edad}");
Console.WriteLine($"Nombre de usuario: {nombre} \nEdad: {edad}");

Console.Write($"Nombre usuario: {nombre}");
Console.WriteLine($", edad: {edad}");


// ===== 1. Una línea: // =====

// En su propia línea
int anio = 2025;   // al final de una línea

// Varias líneas
// con // en cada una
// como esta


// ===== 2. Varias líneas: /* */ =====

/*
   Este comentario ocupa
   varias líneas.
*/

int total = 10 /* valor inicial */ + 5;   // en medio de una línea


// ===== 3. Documentación XML: /// =====

Console.WriteLine($"Suma: {Sumar(2, 3)}");

/// <summary>
/// Suma dos números enteros.
/// </summary>
/// <param name="a">Primer número.</param>
/// <param name="b">Segundo número.</param>
/// <returns>La suma de a y b.</returns>
static int Sumar(int a, int b)
{
    return a + b;
}


// ===== 4. Desactivar código =====

// Con //
// Console.WriteLine($"Nombre de usuario: {nombre}, edad: {edad +5}");

// Con /* */
/*
Console.WriteLine("Nombre de usuario: " + nombre + ", edad: " + edad);
Console.WriteLine($"Nombre de usuario: {nombre}, edad: {edad}");
*/

// Con #if false (admite /* */ dentro)
#if false
Console.WriteLine("Esto no se compila");
/* incluso con comentarios de bloque dentro */
#endif


// ===== 5. Organizar =====

// Etiquetas para dejar avisos
// TODO: pedir estos datos por teclado
// FIXME: falla si el nombre está vacío
// NOTE: este valor es provisional
// HACK: solución temporal

// Plegar un bloque en el editor
#region Datos
string ciudad = "Madrid";
int espera = 30;   // segundos
#endregion

Console.WriteLine($"{anio} {total} {ciudad} {espera}");


// ===== 6. Un // dentro de un texto no es un comentario =====

Console.WriteLine("Visita https://ejemplo.com");
Console.WriteLine("// esto no es un comentario");


// Espera a que se pulse una tecla antes de cerrar la consola
Console.ReadKey();
```

Salida (las líneas comentadas y el bloque `#if false` no se ejecutan):

```text
Nombre de usuario: Sol, edad: 30
Nombre de usuario: Sol, edad: 30
Nombre de usuario: Sol 
Edad: 30
Nombre usuario: Sol, edad: 30
Suma: 5
2025 15 Madrid 30
Visita https://ejemplo.com
// esto no es un comentario
```

## Comandos

```bash
cd csharp/02-fundamentals
dotnet new console -n Comments -o 07-comments
cd 07-comments
dotnet run
```

Fuente: [curso de C# de INFORMATICONFIG](https://www.youtube.com/watch?v=P8URXc2Ealk&list=PL2Z95CSZ1N4F7mym8Ca16RoTDVAlIOPlT&index=7), vídeo 07.
