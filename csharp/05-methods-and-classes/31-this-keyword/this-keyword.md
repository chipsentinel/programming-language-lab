# La palabra clave `this` en C#

## Qué es

`this` es una **referencia al objeto actual**: el objeto sobre el que se está ejecutando el código de la clase. Dentro de un método o constructor, `this` significa "yo, este objeto".

```csharp
class Persona
{
    public string nombre = "";
    public int edad = 0;
}

Persona persona1 = new Persona();   // cada objeto tiene su propio nombre y su propia edad
Persona persona2 = new Persona();
```

Cuando se ejecuta un método de `persona1`, `this` es `persona1`; cuando se ejecuta uno de `persona2`, `this` es `persona2`.

## Uso 1: distinguir atributo de parámetro

Es el motivo principal. Si un **parámetro** se llama igual que un **atributo**, dentro del método el nombre se refiere al **parámetro** (lo "oculta"). Para llegar al atributo hay que escribir `this.`:

```csharp
class Persona
{
    public string nombre = "";
    public int edad = 0;

    public Persona(string nombre, int edad)
    {
        this.nombre = nombre;    // atributo = parámetro
        this.edad = edad;
    }
}
```

```text
this.nombre = nombre;
└─ atributo    └─ parámetro
```

Al crear `new Persona("Bart", 10)`, los valores entran por los parámetros y `this.nombre = nombre` los **copia a los atributos** del objeto.

### Qué pasa sin `this`

Sin él, el parámetro se asigna **a sí mismo** y el atributo no cambia:

```csharp
public Persona(string nombre, int edad)
{
    nombre = nombre;    // parámetro = parámetro: no hace nada (aviso CS1717)
    edad = edad;
}
```

El objeto queda con `nombre = ""` y `edad = 0`: los valores por defecto. El compilador avisa de que se asigna una variable a sí misma.

### Si los nombres son distintos, no hace falta

```csharp
public Persona(string elNombre, int laEdad)
{
    nombre = elNombre;     // sin ambigüedad: this es opcional
    edad = laEdad;
}
```

Con nombres diferentes no hay conflicto. Pero usar el **mismo nombre con `this`** es la convención habitual: el parámetro se llama `nombre` porque es el nombre de la persona.

Visual Studio Code sugiere automáticamente `this.nombre = nombre;` al escribir un constructor.

## Uso 2: pasar o comparar el propio objeto

`this` es un valor: se puede comparar, pasar a otro método o devolver.

```csharp
public bool EsMayorQue(Persona otra)
{
    return this.edad > otra.edad;    // this: quien recibe la llamada; otra: el argumento
}
```

```csharp
Persona persona1 = new Persona("Bart", 10);
Persona persona2 = new Persona("Lisa", 8);

Console.WriteLine(persona1.EsMayorQue(persona2));   // True: this = persona1
Console.WriteLine(persona2.EsMayorQue(persona1));   // False: this = persona2
```

El mismo método da resultados distintos según **sobre qué objeto** se llama.

## Uso 3: devolver `this` para encadenar

Si un método devuelve `this`, se pueden **encadenar llamadas** sobre el mismo objeto:

```csharp
public Persona CumplirAnios()
{
    this.edad++;
    return this;
}

persona1.CumplirAnios().CumplirAnios();    // la edad sube 2
```

Es el mismo patrón que ya se usó con las funciones de `string` (`texto.Trim().ToUpper()`).

## Uso 4: encadenar constructores: `: this(...)`

En la lección de constructores, el constructor sin parámetros **repetía** el código del otro. Con `: this(...)` un constructor llama a otro de la misma clase:

```csharp
public Persona(string nombre, int edad)
{
    this.nombre = nombre;
    this.edad = edad;
}

public Persona() : this("Sol", 34)    // reutiliza el de arriba
{
}
```

`new Persona()` ejecuta primero el constructor de dos parámetros con `"Sol"` y `34`. Si hay que cambiar cómo se inicializa un objeto, se cambia en un solo sitio.

## Dónde no se puede usar

`this` solo existe **dentro de un objeto**. En un método `static` (como `Main`) no hay objeto actual, así que no se puede usar:

```csharp
static void Main()
{
    Console.WriteLine(this.nombre);   // error: this no existe en un método static
}
```

## Resumen

| Uso | Ejemplo |
|---|---|
| Atributo con el mismo nombre que un parámetro | `this.nombre = nombre;` |
| Referirse al propio objeto | `return this.edad > otra.edad;` |
| Encadenar llamadas | `return this;` |
| Encadenar constructores | `public Persona() : this("Sol", 34)` |

## Ejemplo

```csharp
class Persona
{
    public string nombre = "";
    public int edad = 0;

    public Persona(string nombre, int edad)
    {
        this.nombre = nombre;
        this.edad = edad;
    }

    public void Mostrar()
    {
        Console.WriteLine($"Nombre: {this.nombre}, edad: {this.edad}");
    }
}

class Program
{
    static void Main()
    {
        Persona persona1 = new Persona("Bart", 10);
        persona1.Mostrar();

        Console.ReadKey();
    }
}
```

## Comandos

```bash
cd csharp/05-methods-and-classes
dotnet new console -n ThisKeyword -o 31-this-keyword
cd 31-this-keyword
dotnet run
```

Fuente: [curso de C# de INFORMATICONFIG](https://www.youtube.com/watch?v=jyUaUF79uWc&list=PL2Z95CSZ1N4F7mym8Ca16RoTDVAlIOPlT&index=31), vídeo 31.
