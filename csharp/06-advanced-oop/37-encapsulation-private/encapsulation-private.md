# Encapsulamiento `private` en C#

## Qué es `private`

El nivel de acceso **más restrictivo**: un miembro `private` **solo se puede usar dentro de la clase donde se declara**. Ninguna otra clase puede acceder a él directamente, ni siquiera una clase derivada.

| | `public` | `private` |
|---|---|---|
| Desde la propia clase | Sí | Sí |
| Desde otra clase | **Sí** | **No** |
| Uso habitual | Métodos que forman la "cara visible" | **Atributos** y métodos internos |

Es la forma más común de **proteger por completo los datos internos** de una clase.

## Atributos `private`

```csharp
public class Camion
{
    private string marca;
    private string modelo;
    private string color;
    private int cargaMaxima;

    public Camion(string marca, string modelo, string color, int cargaMaxima)
    {
        this.marca = marca;
        this.modelo = modelo;
        this.color = color;
        this.cargaMaxima = cargaMaxima;
    }
}
```

Desde otra clase ya no se pueden leer ni cambiar:

```csharp
Camion camion1 = new Camion("Mack", "MK12", "rojo", 18000);

Console.WriteLine(camion1.marca);       // error: 'Camion.marca' es inaccesible por su nivel de protección
camion1.cargaMaxima = -500;             // error
```

Dentro de **su propia clase** sí se usan con normalidad: el constructor y los métodos de `Camion` pueden acceder a todos sus atributos privados.

> Esto resuelve el problema de la lección anterior: con `public`, cualquiera podía dejar `cargaMaxima = -500`.

## Acceso controlado: métodos *getter* y *setter*

Si los atributos son privados, ¿cómo se leen o se cambian desde fuera? A través de **métodos públicos** que la clase decide ofrecer:

```csharp
// Getter: permite LEER el atributo
public string ObtenerMarca()
{
    return marca;
}

// Setter: permite MODIFICARLO, pero validando
public void CambiarCargaMaxima(int nuevaCarga)
{
    if (nuevaCarga <= 0)
    {
        Console.WriteLine("Carga inválida: debe ser mayor que cero");
        return;
    }

    cargaMaxima = nuevaCarga;
}
```

```csharp
Console.WriteLine(camion1.ObtenerMarca());    // Mack
camion1.CambiarCargaMaxima(-500);             // rechazado
camion1.CambiarCargaMaxima(20000);            // aceptado
```

Esta es la gran ventaja: **la clase controla qué valores acepta**. Y se puede decidir qué se permite:

| Se quiere... | Se ofrece... |
|---|---|
| Solo lectura | Solo el *getter* |
| Lectura y escritura | *Getter* y *setter* |
| Escritura validada | *Setter* con comprobaciones |
| Ninguno de los dos | Nada: dato interno |

## Propiedades: la forma habitual en C#

C# tiene una sintaxis propia que reúne *getter* y *setter* en un solo miembro: la **propiedad**.

```csharp
public class Furgoneta
{
    private int carga = 500;    // atributo privado

    public int Carga
    {
        get { return carga; }
        set
        {
            if (value <= 0)
            {
                Console.WriteLine("Carga inválida: debe ser mayor que cero");
                return;
            }

            carga = value;
        }
    }
}
```

```csharp
Furgoneta furgoneta1 = new Furgoneta();
furgoneta1.Carga = -5;        // ejecuta el set: rechazado
furgoneta1.Carga = 1000;      // ejecuta el set: aceptado
Console.WriteLine(furgoneta1.Carga);    // ejecuta el get: 1000
```

- Se **usa como un atributo** (`furgoneta1.Carga`), pero ejecuta código al leer (`get`) y al escribir (`set`).
- **`value`** es el valor que se está asignando.
- Por convención, la propiedad va en `PascalCase` y el atributo privado en `camelCase`.
- Si no hace falta validar, basta una **propiedad automática**: `public int Carga { get; set; }` (C# crea el atributo oculto).

## Métodos `private`

Un método también puede ser privado: un **ayudante interno** que solo usan otros métodos de la misma clase.

```csharp
private string Descripcion()
{
    return $"{marca} {modelo} ({color})";
}

public void Mostrar()
{
    Console.WriteLine($"{Descripcion()}: carga máxima {cargaMaxima} kg");
}
```

Desde fuera solo se ve `Mostrar()`. `Descripcion()` es un detalle de implementación que se puede cambiar sin afectar a nadie.

```csharp
camion1.Descripcion();    // error: es private
```

Si un método `public` usa atributos `private` de otra clase, tampoco funciona: el acceso depende de **dónde está escrito el código**, no de quién lo llama.

## Constructor `private`

Si el constructor es `private`, **nadie fuera de la clase puede crear objetos** con `new`:

```csharp
public class Utilidades
{
    private Utilidades() { }
}

Utilidades u = new Utilidades();    // error: el constructor es inaccesible
```

Se usa en clases que no deben instanciarse desde fuera, como las que ofrecen un único objeto (*singleton*) o solo métodos estáticos (lección 47).

## Resumen

| Elemento | Recomendación |
|---|---|
| **Atributos** | `private` |
| Acceso a los atributos | Propiedades (`get` / `set`) o métodos |
| Métodos internos de apoyo | `private` |
| Métodos que otras clases deben usar | `public` |

## Ejemplo

```csharp
public class Cuenta
{
    private double saldo;

    public double Saldo
    {
        get { return saldo; }
    }

    public void Depositar(double monto)
    {
        if (monto > 0)
        {
            saldo += monto;
        }
    }
}

public class Program
{
    public static void Main()
    {
        Cuenta cuenta1 = new Cuenta();
        cuenta1.Depositar(100);
        Console.WriteLine(cuenta1.Saldo);    // 100

        Console.ReadKey();
    }
}
```

## Comandos

```bash
cd csharp/06-advanced-oop
dotnet new console -n EncapsulationPrivate -o 37-encapsulation-private
cd 37-encapsulation-private
dotnet run
```

Fuente: [curso de C# de INFORMATICONFIG](https://www.youtube.com/watch?v=sWpp73OUmCQ&list=PL2Z95CSZ1N4F7mym8Ca16RoTDVAlIOPlT&index=37), vídeo 37.
