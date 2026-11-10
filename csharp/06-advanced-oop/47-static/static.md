# La palabra clave `static` en C#

## Qué significa `static`

Un miembro `static` **pertenece a la clase en sí, no a un objeto** creado a partir de ella. Por eso **no hace falta instanciar la clase** (`new`) para usarlo: se usa con el **nombre de la clase**.

| | Miembro normal (de instancia) | Miembro `static` |
|---|---|---|
| Pertenece a | **Cada objeto** | **La clase** |
| Se usa con | `objeto.Miembro` | `Clase.Miembro` |
| Copias | Una **por objeto** | **Una sola**, compartida |
| Necesita `new` | **Sí** | **No** |

Se puede aplicar a **métodos, variables, propiedades, constructores y clases**.

## Para qué sirve

- **Llamar a algo sin crear un objeto**: es su función principal.
- **Compartir datos** entre todos los objetos de la clase (un contador).
- Crear **utilidades** que no dependen del estado de ningún objeto (`Math`, `Console`).

## Método `static`

```csharp
class Utilidad
{
    public static void Saludar()
    {
        Console.WriteLine("Hola a todos");
    }
}

Utilidad.Saludar();           // sin new
```

Sin `static`, habría que crear un objeto:

```csharp
class Utilidad
{
    public void Saludar() { /* ... */ }
}

Utilidad saludo = new Utilidad();
saludo.Saludar();
```

Un método `static` **no se puede llamar sobre un objeto**: `saludo.Saludar()` da error. Se accede a través del tipo.

### `Main` es `static`

Ahora se entiende por qué `static void Main()` lleva `static`: el programa **arranca sin crear ningún objeto**, así que el punto de entrada debe poder llamarse sin instanciar nada. Si se le quita el `static`, deja de ser el punto de entrada.

Y por qué ya se llamaba así a `Math.Sqrt(16)` o `Console.WriteLine(...)`: `Math` y `Console` son **clases estáticas**.

## Variable `static`

```csharp
class Utilidad
{
    public static int Total = 5000;
}

Console.WriteLine(Utilidad.Total);      // 5000
Utilidad.Total = 8000;                  // se puede modificar
```

Tiene **un único valor** para toda la clase, y existe desde que se usa la clase, sin necesidad de ningún objeto.

## Un dato compartido entre todos los objetos

El uso más típico: un **contador** que suman todos los objetos.

```csharp
class Persona
{
    public static int Creadas = 0;       // una sola copia
    public string Nombre;                // una por objeto
    public int Numero;

    public Persona(string nombre)
    {
        Creadas++;
        Nombre = nombre;
        Numero = Creadas;
    }
}

Persona sol = new Persona("Sol");        // Creadas = 1, Numero = 1
Persona marcos = new Persona("Marcos");  // Creadas = 2, Numero = 2
Persona lucia = new Persona("Lucía");    // Creadas = 3, Numero = 3

Console.WriteLine(Persona.Creadas);      // 3
```

| Miembro | Cuántas copias | `Sol` | `Marcos` | `Lucía` |
|---|---|---|---|---|
| `Nombre` | Una por objeto | `"Sol"` | `"Marcos"` | `"Lucía"` |
| `Numero` | Una por objeto | `1` | `2` | `3` |
| `Creadas` | **Una para todos** | `3` | `3` | `3` |

## Clases `static`

Una clase marcada como `static` **no se puede instanciar** y **todo lo que contiene debe ser `static`**:

```csharp
static class Matematica
{
    public static int Sumar(int a, int b)
    {
        return a + b;
    }
}

int resultado = Matematica.Sumar(50, 20);       // 70
Matematica m = new Matematica();                // error: no se puede crear una instancia
```

Reglas:

- **Todos** los miembros deben ser `static`: métodos, variables, propiedades. Si falta alguno, el compilador da error ("no se puede declarar un miembro de instancia en una clase estática").
- La clase **no puede tener atributos normales** ni constructor de instancia.
- Lo contrario sí vale: una **clase normal** puede tener miembros `static` (como `Persona.Creadas`), y se usan sin instanciar.

Se usan para **agrupar utilidades**: `Math`, `Console`, `File`, `Convert`...

## Reglas importantes

### 1. `this` no existe en un contexto `static`

`this` es "el objeto actual" (lección 31), y un miembro `static` **no tiene objeto**:

```csharp
public static void MostrarTotal()
{
    Console.WriteLine(Nombre);         // error: Nombre es de instancia
    Console.WriteLine(this.Numero);    // error: this no existe aquí
}
```

Un método `static` solo puede usar **otros miembros `static`** (o los que reciba como parámetro).

### 2. Lo `static` sí se puede usar desde un método normal

```csharp
public void Presentar()
{
    Console.WriteLine($"Soy {Nombre}, hay {Creadas} personas");    // permitido
}
```

La dirección contraria (desde `static` hacia instancia) es la prohibida.

### 3. Se comparte entre todas las instancias

Un cambio en un miembro `static` lo ven **todos**. Es útil, pero también **peligroso**: un valor global modificable desde cualquier parte es difícil de controlar. Conviene usar `static` solo cuando de verdad tiene sentido.

## Constructor `static`

Se ejecuta **una sola vez**, automáticamente, **antes del primer uso** de la clase. No lleva `public` ni parámetros:

```csharp
class Configuracion
{
    public static string Idioma;

    static Configuracion()
    {
        Console.WriteLine("Inicializando configuración (solo una vez)");
        Idioma = "es";
    }
}

Console.WriteLine(Configuracion.Idioma);    // aquí se ejecuta el constructor static
Console.WriteLine(Configuracion.Idioma);    // la segunda vez ya no
```

Sirve para preparar los datos `static` de la clase.

## Cuándo usar `static`

| Usar `static` | No usar `static` |
|---|---|
| Funciones auxiliares sin estado (`Sumar`, `Convertir`) | Cuando el método depende de los **datos de un objeto** |
| Constantes y datos compartidos de la clase | Cuando cada objeto necesita **su propia copia** |
| Contadores de instancias | Cuando se necesitará **sustituirlo en pruebas** o redefinirlo (herencia) |

## Ejemplo

```csharp
static class Matematica
{
    public static int Sumar(int a, int b)
    {
        return a + b;
    }
}

class Program
{
    static void Main()
    {
        int resultado = Matematica.Sumar(50, 20);
        Console.WriteLine("Suma: " + resultado);

        Console.ReadKey();
    }
}
```

## Comandos

```bash
cd csharp/06-advanced-oop
dotnet new console -n StaticLesson -o 47-static
cd 47-static
dotnet run
```

Fuente: [curso de C# de INFORMATICONFIG](https://www.youtube.com/watch?v=HTFcs3xJVrQ&list=PL2Z95CSZ1N4F7mym8Ca16RoTDVAlIOPlT&index=47), vídeo 47.
