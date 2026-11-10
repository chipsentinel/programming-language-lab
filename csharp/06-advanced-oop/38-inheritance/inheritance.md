# Herencia en C#

## Qué es la herencia

Una forma de programar en la que **una clase deriva de otra y reutiliza sus miembros**: atributos, propiedades, métodos...

| Término | Significa | Ejemplo |
|---|---|---|
| **Clase padre** (o base) | La clase de la que se hereda | `Animal` |
| **Clase hija** (o derivada) | La clase que hereda | `Perro`, `Gato` |

Una `Animal` sabe **comer** y **dormir**. Un `Perro` y un `Gato` también son animales, así que **ya saben comer y dormir** sin escribirlo otra vez, y además cada uno añade lo suyo (ladrar, maullar).

```text
          Animal
       (nombre, Comer, Dormir)
        /              \
     Perro            Gato
   (+ Ladrar)      (+ Maullar)
```

## Para qué sirve

- **Reutilizar código**: lo común se escribe **una sola vez**, en el padre.
- **Evitar repetir** lo mismo en varias clases.
- **Extensibilidad**: se pueden añadir clases hijas o nuevas funciones sin tocar la clase base.
- **Polimorfismo**: tratar objetos distintos como uno común (lección 48).

## Reglas en C#

1. Una clase puede heredar de **una sola clase** (herencia simple). Lo que sí puede es implementar varias interfaces.
2. **Todas las clases derivan de `object`**. Se hace solo, de forma implícita: por eso cualquier clase tiene métodos como `ToString()`, aunque no los haya escrito. `string`, `Math`, `List`... también.
3. La clase padre debe ser **accesible** (`public`) para que otra la herede desde fuera.

## Propiedades automáticas

La clase padre usa una **propiedad automática** (lección 37):

```csharp
public string Nombre { get; set; }
```

C# crea el atributo oculto y los métodos `get` (leer) y `set` (asignar) sin escribirlos. Es el equivalente corto de un atributo `private` con *getter* y *setter*. Hay que escribirla **por cada atributo**.

## La clase padre

```csharp
public class Animal
{
    public string Nombre { get; set; }

    public Animal(string nombre)
    {
        Nombre = nombre;
    }

    public void Comer()
    {
        Console.WriteLine($"{Nombre} está comiendo");
    }

    public void Dormir()
    {
        Console.WriteLine($"{Nombre} está durmiendo");
    }
}
```

Es una clase normal: no necesita nada especial para ser un padre.

## La clase hija: `:`

Se escribe el nombre de la clase padre tras **dos puntos**:

```csharp
public class Perro : Animal
{
    public Perro(string nombre) : base(nombre)
    {
    }

    public void Ladrar()
    {
        Console.WriteLine($"{Nombre} está ladrando");
    }
}

public class Gato : Animal
{
    public Gato(string nombre) : base(nombre)
    {
    }

    public void Maullar()
    {
        Console.WriteLine($"{Nombre} está maullando");
    }
}
```

`Perro` **recibe** `Nombre`, `Comer()` y `Dormir()` de `Animal`, y **añade** `Ladrar()`.

### El constructor: `: base(...)`

**Los constructores no se heredan.** Para que el padre inicialice sus datos, la hija llama a su constructor con **`: base(...)`**:

```csharp
public Perro(string nombre) : base(nombre)
```

- Los argumentos entre paréntesis van **al constructor del padre**.
- El constructor del padre se ejecuta **antes** que el cuerpo del de la hija.
- Si el padre tiene un constructor **sin parámetros**, `: base()` se llama solo y no hace falta escribirlo.

## Usar las clases

```csharp
Perro miPerro = new Perro("Snoopy");

miPerro.Dormir();     // Snoopy está durmiendo   (heredado)
miPerro.Comer();      // Snoopy está comiendo    (heredado)
miPerro.Ladrar();     // Snoopy está ladrando    (propio)

miPerro.Nombre = "Rex";    // la propiedad heredada también se puede cambiar
```

La **herencia solo va hacia abajo**: lo propio de una hija no existe en la otra.

```csharp
Gato miGato = new Gato("Michi");
miGato.Maullar();     // correcto
miGato.Ladrar();      // error: Gato no tiene Ladrar
```

## Relación "es un"

La herencia modela una relación **"es un"**: un perro *es un* animal. Por eso un objeto de la hija se puede guardar en una variable del tipo padre:

```csharp
Animal animal = miPerro;
animal.Comer();                                    // Rex está comiendo
Console.WriteLine(miPerro is Animal);              // True
Console.WriteLine(miPerro is Gato);                // False
```

Pero a través de la variable `Animal` **solo se ven los miembros de `Animal`**: `animal.Ladrar()` no compila, aunque el objeto sea un `Perro`.

> Si la relación es "**tiene un**" (una orden *tiene un* cliente, lección 32), no es herencia sino **colaboración**: se guarda como atributo. Conviene preguntarse "¿es un?" antes de heredar.

## La herencia y el encapsulamiento

Heredar **no salta** los niveles de acceso:

| El miembro del padre es... | ¿La hija lo usa directamente? |
|---|---|
| `public` | **Sí** |
| `private` | **No** (solo a través de miembros públicos) |
| `protected` | **Sí** (lección 39) |

Si `Nombre` fuera `private`, `Perro` no podría usarlo. Por eso el vídeo recuerda que "al usar herencia hay que poner `public`". La solución más fina es **`protected`**, que es justo lo que se verá a continuación.

## Impedir la herencia: `sealed`

Una clase marcada como `sealed` **no se puede heredar**:

```csharp
public sealed class Perro : Animal { }
```

La clase `string`, por ejemplo, es `sealed`.

## Ejemplo

```csharp
public class Animal
{
    public string Nombre { get; set; }

    public Animal(string nombre)
    {
        Nombre = nombre;
    }

    public void Comer()
    {
        Console.WriteLine($"{Nombre} está comiendo");
    }
}

public class Perro : Animal
{
    public Perro(string nombre) : base(nombre) { }

    public void Ladrar()
    {
        Console.WriteLine($"{Nombre} está ladrando");
    }
}

public class Program
{
    public static void Main()
    {
        Perro miPerro = new Perro("Snoopy");
        miPerro.Comer();
        miPerro.Ladrar();

        Console.ReadKey();
    }
}
```

## Comandos

```bash
cd csharp/06-advanced-oop
dotnet new console -n Inheritance -o 38-inheritance
cd 38-inheritance
dotnet run
```

Fuente: [curso de C# de INFORMATICONFIG](https://www.youtube.com/watch?v=WD8TZY8gcaU&list=PL2Z95CSZ1N4F7mym8Ca16RoTDVAlIOPlT&index=38), vídeo 38.
