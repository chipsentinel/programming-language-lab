# Clases abstractas en C#

## Qué es una clase abstracta

Una clase que **no se puede instanciar**: no se pueden crear objetos directamente de ella. Su propósito es **servir de base** para otras clases (herencia, lección 38): define **lo que tienen en común** y **obliga a las clases hijas** a completar ciertas partes.

Es como una **plantilla incompleta**: dice "todo animal puede hacer un sonido", pero no dice cuál. Cada animal concreto lo decide.

Se parece a una **interfaz** (que se verá más adelante), pero con más flexibilidad: una clase abstracta puede tener **atributos y código**, no solo la lista de lo que hay que implementar.

## Declararla: `abstract`

```csharp
public abstract class Animal
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

    public abstract void HacerSonido();
}
```

Y no se puede crear un objeto de ella:

```csharp
Animal animal = new Animal("Genérico");    // error: no se puede crear una instancia de una clase abstracta
```

### Debe ser `public`

Una clase abstracta tiene que ser accesible para que otras hereden. El compilador rechaza `private abstract class` en el espacio de nombres (error CS1527: los elementos del espacio de nombres no pueden ser privados ni protegidos). Si no se escribe nada, la clase es `internal`, válido pero solo dentro del mismo proyecto.

## Qué puede contener

| Miembro | ¿Permitido? |
|---|---|
| Atributos y propiedades **normales** | Sí |
| Constructores | Sí (los usan las hijas con `: base(...)`) |
| Métodos **con código** | Sí |
| Métodos **abstractos** | Sí (**solo** en clases abstractas) |
| Métodos **virtuales** | Sí |

Y no todos los miembros tienen que ser abstractos: `Nombre` y `Comer()` son normales. Es una diferencia con una clase `static` (lección 47), donde **todo** debe serlo.

## Métodos abstractos

```csharp
public abstract void HacerSonido();
```

- Llevan **`abstract`**.
- **No tienen cuerpo**: acaban en `;`, sin llaves. Poner llaves da error.
- **Solo pueden existir en una clase abstracta.**
- Indican **qué** debe hacer toda hija, no **cómo**.

## Implementarlos en las hijas: `override`

Una clase que hereda de una abstracta **está obligada a implementar todos sus métodos abstractos**. Si no, el compilador da error ("no implementa el miembro abstracto heredado"). Se hace con **`override`**:

```csharp
public class Perro : Animal
{
    public Perro(string nombre) : base(nombre) { }

    public override void HacerSonido()
    {
        Console.WriteLine($"{Nombre} está ladrando");
    }
}

public class Gato : Animal
{
    public Gato(string nombre) : base(nombre) { }

    public override void HacerSonido()
    {
        Console.WriteLine($"{Nombre} está maullando");
    }
}
```

- **`override`** significa "sustituyo el método del padre por el mío". Sin él, no compila.
- Misma firma que la original: mismo nombre, parámetros y tipo de retorno.
- Cada hija da **su propia versión**: ahí está la utilidad.

```csharp
Perro miPerro = new Perro("Snoopy");

miPerro.Comer();         // Snoopy está comiendo   (heredado de Animal)
miPerro.HacerSonido();   // Snoopy está ladrando   (implementado en Perro)
```

## `abstract` frente a `virtual`

| | `abstract` | `virtual` |
|---|---|---|
| ¿Tiene código? | **No** | **Sí** (una versión por defecto) |
| ¿Las hijas deben redefinirlo? | **Sí, obligatorio** | No, **opcional** |
| ¿Dónde puede estar? | Solo en clases abstractas | En cualquier clase |
| Se redefine con | `override` | `override` |

```csharp
public virtual void Dormir()
{
    Console.WriteLine($"{Nombre} está durmiendo");     // por defecto
}
```

`Gato` puede redefinirlo (`public override void Dormir() { ... }`); `Perro` lo deja como está. Un método **sin** `virtual` ni `abstract` **no se puede** redefinir con `override`.

## Polimorfismo

El cuarto pilar de la POO (lección 35): **objetos de clases distintas se tratan como uno común**.

```csharp
List<Animal> animales = new List<Animal>
{
    new Perro("Snoopy"),
    new Gato("Garfield"),
    new Vaca("Lola")
};

foreach (Animal animal in animales)
{
    animal.HacerSonido();
}
```

```text
Snoopy está ladrando
Garfield está maullando
Lola está mugiendo
```

- La lista guarda **`Animal`**, pero cada elemento es **un `Perro`, un `Gato`, una `Vaca`**.
- Al llamar a `HacerSonido()`, C# ejecuta la versión de **la clase real** del objeto, no la del tipo de la variable.
- Quien recorre la lista **no necesita saber** qué animal es cada uno.
- Añadir una clase nueva (`Pato`) **no obliga a cambiar** el código que usa la lista.

Esta es la razón de ser de las clases abstractas: **escribir código para el concepto general** (`Animal`) que funciona con **cualquier clase concreta**.

## Cuándo usar una clase abstracta

| Usar | Cuándo |
|---|---|
| **Clase abstracta** | Hay un **concepto general** que no tiene sentido como objeto concreto (un `Animal`, una `Figura`, un `Empleado`) y las hijas comparten código |
| **Clase normal** | El concepto se puede instanciar tal cual |
| **Interfaz** (más adelante) | Solo se quiere fijar **qué métodos** debe tener algo, sin compartir código, o una clase necesita varios "contratos" |

## Ejemplo

```csharp
public abstract class Figura
{
    public abstract double Area();
}

public class Cuadrado : Figura
{
    public double Lado { get; set; }

    public Cuadrado(double lado) { Lado = lado; }

    public override double Area()
    {
        return Lado * Lado;
    }
}

public class Program
{
    public static void Main()
    {
        Figura figura = new Cuadrado(4);
        Console.WriteLine("Área: " + figura.Area());      // 16

        Console.ReadKey();
    }
}
```

## Comandos

```bash
cd csharp/06-advanced-oop
dotnet new console -n AbstractClasses -o 48-abstract-classes
cd 48-abstract-classes
dotnet run
```

Fuente: [curso de C# de INFORMATICONFIG](https://www.youtube.com/watch?v=t7ZhwYmIIH4&list=PL2Z95CSZ1N4F7mym8Ca16RoTDVAlIOPlT&index=48), vídeo 48.
