# Encapsulamiento `protected` en C#

## Qué es `protected`

Un nivel de acceso intermedio entre `public` y `private`: un miembro `protected` es accesible **dentro de su propia clase y en las clases que heredan de ella**, pero **no desde el resto del programa**.

| | Propia clase | Clase hija | Resto del programa |
|---|---|---|---|
| `public` | Sí | Sí | **Sí** |
| `protected` | Sí | **Sí** | No |
| `private` | Sí | No | No |

Por eso hay que conocer **la herencia** (lección 38) antes de entender `protected`. Sin herencia, `protected` se comportaría igual que `private`.

## Para qué sirve

Para miembros que son **detalles internos** del padre, que las hijas pueden reutilizar, pero que **no deben formar parte de lo que ve el exterior**.

| Quién lo necesita | Modificador |
|---|---|
| Todo el mundo | `public` |
| **La clase y sus hijas** | **`protected`** |
| Solo la clase | `private` |

## Un método `protected`

```csharp
public class Animal
{
    public string Nombre { get; set; }

    protected void Respirar()
    {
        Console.WriteLine($"{Nombre} está respirando");
    }

    public void Dormir()
    {
        Console.WriteLine($"{Nombre} está durmiendo");
        Respirar();                       // la propia clase lo usa
    }
}
```

- **`Respirar()`** es un detalle interno de cualquier animal.
- **`Dormir()`**, público, **lo usa** internamente: la propia clase siempre ve sus miembros.

## Las hijas lo usan

```csharp
public class Perro : Animal
{
    public Perro(string nombre) : base(nombre) { }

    public void Ladrar()
    {
        Respirar();                                    // permitido: Perro hereda de Animal
        Console.WriteLine($"{Nombre} está ladrando");
    }
}
```

`Perro` y `Gato` pueden llamar a `Respirar()` porque heredan de `Animal`.

## Desde fuera no se ve

```csharp
Perro miPerro = new Perro("Rex");

miPerro.Ladrar();       // correcto: es public
miPerro.Respirar();     // error: 'Animal.Respirar()' es inaccesible por su nivel de protección
```

`Main` no hereda de `Animal`, así que **no puede llamar** a `Respirar()`. Tampoco aparece en la lista del editor al escribir el punto. Esto ocurre incluso con un objeto de la propia clase `Animal` creado desde fuera:

```csharp
Animal conejo = new Animal("Boxy");
conejo.Respirar();      // error
```

> **Una aclaración sobre el vídeo.** El vídeo da a entender que `protected` impide el acceso "incluso dentro de la misma clase" y que las hijas no lo heredan. No es así: la propia clase y las hijas **sí** lo usan siempre; lo que queda excluido es el **código externo** que accede mediante un objeto. Es lo que muestra `Respirar()` dentro de `Dormir()` y de `Ladrar()`.

## Atributos `protected`

```csharp
public class Animal
{
    protected int energia = 100;

    public void Descansar()
    {
        energia = 100;
        Console.WriteLine($"{Nombre} descansa. Energía: {energia}");
    }
}

public class Perro : Animal
{
    public void Correr()
    {
        energia -= 30;                      // permitido: la hija modifica el atributo heredado
        Console.WriteLine($"{Nombre} corre. Energía: {energia}");
    }
}
```

```csharp
Console.WriteLine(miPerro.energia);    // error desde fuera
```

Con `private`, `Perro` **no podría** tocar `energia`. Con `protected`, sí, y el exterior sigue sin verlo.

## Riesgo de `protected` en atributos

Un atributo `protected` está **abierto a todas las hijas**, incluso las que se escriban en el futuro. Si se modifica sin cuidado desde las hijas, se pueden romper reglas del padre. Por eso, para **atributos** suele preferirse `private` con acceso por métodos o propiedades, y reservar `protected` para **métodos** pensados para ser reutilizados o **redefinidos** por las hijas (lección 48).

## Otros modificadores relacionados

| Modificador | Quién puede acceder |
|---|---|
| `protected internal` | Clases del mismo ensamblado **o** clases hijas |
| `private protected` | Clases hijas **dentro del mismo ensamblado** |

## Resumen de los tres niveles

```text
public     ──►  todo el programa
protected  ──►  la clase + sus hijas
private     ──►  solo la clase
```

## Ejemplo

```csharp
public class Animal
{
    public string Nombre { get; set; }

    public Animal(string nombre)
    {
        Nombre = nombre;
    }

    protected void Respirar()
    {
        Console.WriteLine($"{Nombre} está respirando");
    }
}

public class Perro : Animal
{
    public Perro(string nombre) : base(nombre) { }

    public void Ladrar()
    {
        Respirar();
        Console.WriteLine($"{Nombre} está ladrando");
    }
}

public class Program
{
    public static void Main()
    {
        Perro miPerro = new Perro("Rex");
        miPerro.Ladrar();

        Console.ReadKey();
    }
}
```

## Comandos

```bash
cd csharp/06-advanced-oop
dotnet new console -n EncapsulationProtected -o 39-encapsulation-protected
cd 39-encapsulation-protected
dotnet run
```

Fuente: [curso de C# de INFORMATICONFIG](https://www.youtube.com/watch?v=TrVo7IxkH44&list=PL2Z95CSZ1N4F7mym8Ca16RoTDVAlIOPlT&index=39), vídeo 39.
