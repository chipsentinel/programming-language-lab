# Introducción a los métodos en C#

## Qué es un método

Un **bloque de código con nombre**, definido dentro de una clase, que realiza una tarea concreta. Se escribe una vez y se **invoca** (se llama) siempre que haga falta.

Sirven para:

- **Evitar repetir código**: se escribe una vez y se usa muchas.
- **Dividir tareas complejas** en partes pequeñas y fáciles de entender: al ver `Sumar()` ya se sabe qué hace sin leer su código.
- **Reutilizar** lo ya hecho, en distintos lugares del programa.
- **Depurar** más fácil: si algo falla, se revisa el método responsable.

Un método puede **recibir datos** (parámetros, lección siguiente) y puede **devolver un resultado**.

## Método sin retorno: `void`

`void` significa "vacío": el método **hace algo pero no devuelve nada**.

```csharp
class Prueba
{
    public void Saludar()
    {
        Console.WriteLine("Hola a todos");
    }
}
```

```text
public   void   Saludar   ( )   { ... }
acceso   retorno  nombre  parámetros  cuerpo
```

- **`public`**: se puede usar desde fuera de la clase (se verá con el encapsulamiento).
- **`void`**: no devuelve nada.
- **`Saludar`**: el nombre. En C# los métodos van en `PascalCase` y suelen ser **verbos**.
- **`()`**: los paréntesis, siempre. Aquí vacíos: no recibe datos.
- **`{ }`**: el cuerpo, con las instrucciones.

## Invocar un método

Primero se **instancia** la clase (lección anterior) y después se llama con `objeto.Metodo()`:

```csharp
Prueba saludo1 = new Prueba();
saludo1.Saludar();    // Hola a todos
```

- Los **paréntesis son obligatorios** al llamarlo, incluso sin datos: `saludo1.Saludar`, sin ellos, no ejecuta nada.
- Un método se puede llamar **tantas veces** como se quiera: cada llamada ejecuta su código de nuevo.
- Al escribir el punto, el editor lista los métodos disponibles.

## Método con retorno

En lugar de `void` se escribe el **tipo del valor que devuelve**, y el cuerpo termina con **`return`**:

```csharp
public int Sumar()
{
    int valor1 = 23, valor2 = 45;
    int total = valor1 + valor2;

    return total;
}
```

- **`int`** antes del nombre: el método devuelve un entero.
- **`return total;`** entrega ese valor a quien llamó al método y **termina** el método.
- El compilador **exige** el `return` en un método que no es `void`, y que el valor devuelto sea del tipo indicado.

Un método puede devolver cualquier tipo: `int`, `string`, `bool`, `double`...

```csharp
public string Despedida()
{
    string mensaje = "Adiós a todos";
    return mensaje;
}
```

### Usar el valor devuelto

Se puede **guardar** en una variable o **usar directamente** donde haga falta:

```csharp
int resultado = saludo1.Sumar();
Console.WriteLine("Total de la suma: " + resultado);       // 68

Console.WriteLine("Mensaje: " + despedida.Despedida());    // Adiós a todos
Console.WriteLine(saludo1.Sumar() + 2);                    // 70
```

Un método `void` **no se puede guardar** en una variable: no devuelve nada.

```csharp
int x = saludo1.Saludar();   // error: Saludar no devuelve nada
```

## Comparación

| | Sin retorno | Con retorno |
|---|---|---|
| Se declara con | `void` | El tipo: `int`, `string`... |
| Lleva `return` | No | **Sí**, obligatorio |
| Sirve para | **Hacer** algo (mostrar, guardar) | **Calcular** un valor |
| Ejemplo | `Saludar()` | `Sumar()` |

> A veces se oye "métodos *set*" y "métodos *get*" para estos dos tipos. No es el sentido habitual: *getter* y *setter* son métodos especiales para leer y modificar atributos, y se verán con el encapsulamiento.

## Un método puede llamar a otros

Dentro de una clase, un método llama a otro de la misma clase solo con su nombre:

```csharp
public void Presentar()
{
    Saludar();
    Despedir();
}
```

Así se construyen tareas grandes a partir de otras pequeñas.

## Métodos `static`

Un método `static` pertenece a la **clase**, no a un objeto: se puede llamar **sin instanciar**. `Main` es un método `static`, por eso el programa puede arrancar sin crear ningún objeto antes.

```csharp
class Program
{
    static void Separador()
    {
        Console.WriteLine("----------");
    }

    static void Main()
    {
        Separador();    // sin objeto
    }
}
```

Desde un método `static` no se puede llamar a un método normal (de objeto) directamente: hace falta un objeto. `static` se verá en la lección 47.

## Ejemplo

```csharp
class Prueba
{
    public void Saludar()
    {
        Console.WriteLine("Hola a todos");
    }

    public int Sumar()
    {
        int total = 23 + 45;
        return total;
    }
}

class Program
{
    static void Main()
    {
        Prueba prueba1 = new Prueba();

        prueba1.Saludar();
        Console.WriteLine("Total de la suma: " + prueba1.Sumar());

        Console.ReadKey();
    }
}
```

## Comandos

```bash
cd csharp/05-methods-and-classes
dotnet new console -n MethodsIntroduction -o 27-methods-introduction
cd 27-methods-introduction
dotnet run
```

Fuente: [curso de C# de INFORMATICONFIG](https://www.youtube.com/watch?v=lDyzFYnibdE&list=PL2Z95CSZ1N4F7mym8Ca16RoTDVAlIOPlT&index=27), vídeo 27.
