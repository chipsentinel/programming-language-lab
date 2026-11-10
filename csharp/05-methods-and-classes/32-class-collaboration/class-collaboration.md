# Colaboración entre clases en C#

## Qué es

Hasta ahora cada ejercicio usaba **una sola clase**. En un programa real hay **varias clases que trabajan juntas**: una usa los atributos o los métodos de otra para cumplir su tarea. A eso se le llama **colaboración entre clases**.

Ejemplo del mundo real: una orden de compra **necesita un cliente**. Hay una clase `Cliente`, otra clase `Orden`, y la orden usa al cliente.

## Qué hace falta

1. Una clase que **ofrezca** algo: atributos y métodos `public`.
2. Otra clase que **cree o reciba** un objeto de la primera.
3. Usar ese objeto con `objeto.atributo` y `objeto.Metodo()`, como ya se hacía.

## La clase que se usa: `Cliente`

```csharp
class Cliente
{
    public string nombre;
    public string email;

    public Cliente(string nombre, string email)
    {
        this.nombre = nombre;
        this.email = email;
    }

    public void Datos()
    {
        Console.WriteLine($"Nombre: {nombre}");
        Console.WriteLine($"Email: {email}");
    }
}
```

No necesita un `Main`: solo una de las clases del programa lo tiene (el punto de entrada).

### `public` es imprescindible

Para que **otra clase** pueda ver un método o un atributo, debe ser **`public`**. Sin él es privado (solo accesible dentro de su propia clase) y el compilador da error al usarlo desde fuera. El editor muestra el miembro en gris cuando no es accesible. El encapsulamiento se verá con detalle más adelante.

## Usar una clase desde otra

`Main` está en la clase `Program`, pero puede crear objetos de cualquier clase del programa:

```csharp
class Program
{
    static void Main()
    {
        Cliente cliente1 = new Cliente("Sol", "sol@correo.com");
        cliente1.Datos();
    }
}
```

`cliente1` es un objeto de la clase `Cliente`, aunque se cree dentro de `Program`. En Visual Studio, `Ctrl + clic` sobre `Cliente` lleva a su definición.

## Una clase que contiene objetos de otra

Una `Orden` tiene un **atributo cuyo tipo es otra clase**:

```csharp
class Orden
{
    private int numero;
    private Cliente cliente;     // un objeto de otra clase como atributo
    private double total;

    public Orden(int numero, Cliente cliente, double total)
    {
        this.numero = numero;
        this.cliente = cliente;
        this.total = total;
    }

    public void Resumen()
    {
        Console.WriteLine($"Orden #{numero}");
        cliente.Datos();          // usa un método de Cliente
        Console.WriteLine($"Total: {total:F2}");
    }
}
```

```csharp
Orden orden1 = new Orden(1, cliente1, 120.5);
orden1.Resumen();
```

```text
Orden #1
Nombre: Sol
Email: sol@correo.com
Total: 120,50
```

- El **constructor recibe un `Cliente`** como parámetro: el tipo del parámetro es una clase, igual que podría ser `int` o `string`.
- `Resumen` **delega**: para mostrar los datos del cliente llama a `cliente.Datos()` en lugar de repetir el código. Cada clase se ocupa de lo suyo.
- Esta relación se llama "**tiene un**" (una orden *tiene un* cliente).

## Los objetos se pasan por referencia

Una variable de tipo clase **no guarda el objeto, sino una referencia** (la dirección donde está). Al pasar `cliente1` a la orden, **la orden y `Main` apuntan al mismo cliente**, no a copias:

```csharp
Orden orden2 = new Orden(2, cliente1, 80);

cliente1.email = "nuevo@correo.com";    // se cambia el cliente

orden1.Resumen();    // Email: nuevo@correo.com
orden2.Resumen();    // Email: nuevo@correo.com
```

Las **dos órdenes** ven el cambio. Es lo mismo que ocurría al asignar un array a otra variable (lección 25). Es una gran ventaja —un cliente, muchas órdenes— pero hay que tener cuidado: modificar el objeto desde un sitio lo modifica para todos.

## Beneficios

- **Cada clase tiene una responsabilidad**: `Cliente` sabe de clientes, `Orden` de órdenes.
- **Se reutiliza código**: el mismo `Cliente` sirve para muchas órdenes.
- **Cambios localizados**: si cambia cómo se muestra un cliente, se modifica solo `Datos()`.

## Ejemplo

```csharp
class Cliente
{
    public string nombre;

    public Cliente(string nombre)
    {
        this.nombre = nombre;
    }
}

class Orden
{
    private Cliente cliente;

    public Orden(Cliente cliente)
    {
        this.cliente = cliente;
    }

    public void Resumen()
    {
        Console.WriteLine("Orden de " + cliente.nombre);
    }
}

class Program
{
    static void Main()
    {
        Cliente cliente1 = new Cliente("Sol");
        Orden orden1 = new Orden(cliente1);
        orden1.Resumen();

        Console.ReadKey();
    }
}
```

## Comandos

```bash
cd csharp/05-methods-and-classes
dotnet new console -n ClassCollaboration -o 32-class-collaboration
cd 32-class-collaboration
dotnet run
```

Fuente: [curso de C# de INFORMATICONFIG](https://www.youtube.com/watch?v=EgzcttBzC_E&list=PL2Z95CSZ1N4F7mym8Ca16RoTDVAlIOPlT&index=32), vídeo 32.
