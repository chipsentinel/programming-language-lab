# Encapsulamiento y `public` en C#

## Qué es el encapsulamiento

El proceso de **ocultar los detalles internos de una clase** a quienes la usan, y de **controlar qué partes se pueden ver y modificar**. Es uno de los pilares de la POO (lección 35).

Con él se puede **restringir el acceso** a:

- los **atributos** de una clase,
- sus **métodos**,
- e incluso la **clase entera**.

Para qué sirve:

- **Proteger los datos** y evitar cambios no autorizados.
- **Controlar el acceso**: solo se permite lo necesario.
- Un diseño **más limpio, seguro y fácil de mantener**: el exterior solo conoce lo que la clase decide mostrar.

Se aplica con los **modificadores de acceso**.

## Modificadores de acceso

| Modificador | Quién puede acceder |
|---|---|
| `public` | **Cualquier parte** del programa |
| `private` | **Solo la propia clase** |
| `protected` | La propia clase y sus **clases derivadas** (herencia) |
| `internal` | Cualquier clase del **mismo ensamblado** (el mismo proyecto) |
| `protected internal` | Mismo ensamblado **o** clases derivadas de otros ensamblados |
| `private protected` | La clase y sus derivadas, **solo dentro del mismo ensamblado** |

- Un **ensamblado** es el resultado de compilar un proyecto (`.dll` o `.exe`). De momento cada lección es un ensamblado.
- Las **clases derivadas** se verán con la herencia (lección 38).
- Hoy se trabaja con `public`; `private`, en la lección 37; `protected`, en la 39.

### Si no se escribe ninguno

| Elemento | Acceso por defecto |
|---|---|
| Miembro de una clase (atributo, método, constructor) | `private` |
| Clase | `internal` |

Por eso, hasta ahora, los atributos que se querían usar desde `Main` se escribían con `public`.

## `public`

Un miembro `public` es **accesible desde cualquier parte** del programa: desde su propia clase y desde cualquier otra.

```csharp
public class Camion
{
    public string marca;
    public string modelo;
    public string color;

    public Camion(string marca, string modelo, string color, int cargaMaxima) { /* ... */ }

    public void DatosCamion(int velocidad, int acelera) { /* ... */ }
}
```

Y se usa desde **otra clase** distinta:

```csharp
public class Program
{
    public static void Main()
    {
        Camion camion1 = new Camion("Mack", "MK12", "rojo", 18000);
        camion1.DatosCamion(230, 40);
    }
}
```

`Program` y `Camion` son clases distintas. `Program` puede crear un `Camion` y usar sus miembros porque son `public`.

### Qué pasa sin `public`

Sin `public`, los miembros son `private` y **otra clase no los ve**:

```csharp
class Camion
{
    string marca;                       // private por defecto
    void DatosCamion() { /* ... */ }    // private por defecto
}

camion1.marca = "Volvo";     // error: 'marca' es inaccesible por su nivel de protección
camion1.DatosCamion();       // error
```

Al escribir el punto, el editor solo muestra los miembros accesibles: si falta `public`, el atributo o el método **no aparece** en la lista.

### También el constructor

Si el **constructor** no es `public`, otras clases **no pueden crear objetos** de la clase:

```csharp
Camion camion1 = new Camion(...);     // error si el constructor no es public
```

## Leer y cambiar un atributo público

```csharp
camion1.color = "azul";
Console.WriteLine(camion1.color);    // azul
```

Con un atributo `public` cualquier clase puede **leerlo y modificarlo** directamente.

## El inconveniente de `public`

Ese acceso directo no tiene ningún control:

```csharp
camion1.cargaMaxima = -500;    // nadie lo impide
```

Una carga máxima negativa no tiene sentido, pero `public` **no puede evitarlo**: la clase no se entera de que alguien cambió el dato.

Por eso, no conviene dejar los **atributos** como `public`. La solución es **ocultarlos con `private`** y dar un acceso controlado mediante métodos o propiedades, que permiten validar. Se verá en la lección siguiente.

## Qué conviene hacer público

| Elemento | Normalmente |
|---|---|
| Clases que usan otros | `public` |
| Constructores | `public` |
| Métodos que forman la "cara visible" de la clase | `public` |
| **Atributos** | `private` (lección 37) |

La regla general: **mostrar lo mínimo necesario**.

## Ejemplo

```csharp
public class Camion
{
    public string marca;

    public Camion(string marca)
    {
        this.marca = marca;
    }

    public void Mostrar()
    {
        Console.WriteLine("Marca: " + marca);
    }
}

public class Program
{
    public static void Main()
    {
        Camion camion1 = new Camion("Mack");
        camion1.Mostrar();

        Console.ReadKey();
    }
}
```

## Comandos

```bash
cd csharp/06-advanced-oop
dotnet new console -n EncapsulationPublic -o 36-encapsulation-public
cd 36-encapsulation-public
dotnet run
```

Fuente: [curso de C# de INFORMATICONFIG](https://www.youtube.com/watch?v=vem7AznM0Gk&list=PL2Z95CSZ1N4F7mym8Ca16RoTDVAlIOPlT&index=36), vídeo 36.
