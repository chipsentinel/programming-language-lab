# Enumeraciones (`enum`) en C#

## Qué es una enumeración

Un **tipo de dato formado por un conjunto fijo de constantes con nombre**: valores relacionados entre sí que se escriben con palabras en lugar de números o textos "mágicos".

En vez de decir "estado `2`" (¿qué significa?) o "estado `"enviado"`" (¿y si se escribe `"enviadoo"`?), se dice `EstadoPedido.Enviado`.

Ventajas:

- **Legibilidad**: el código se explica solo.
- **Seguridad**: solo se pueden usar los valores definidos; el compilador rechaza los inventados.
- **Mantenimiento**: los valores están en un solo sitio.

Ejemplos típicos: días de la semana, meses, estados de un pedido, niveles de prioridad, tipos de usuario, colores, direcciones (norte, sur, este, oeste).

## Definir una enumeración

```csharp
public enum EstadoPedido
{
    Pendiente,
    Procesado,
    Enviado,
    Entregado
}
```

- Se escribe `enum` en **minúscula** (con `Enum` mayúscula es una clase de ayuda, ver más abajo).
- El nombre del tipo (`EstadoPedido`) va en `PascalCase` y en **singular**.
- Las constantes van entre llaves, **separadas por comas**, como los elementos de un array.
- **No** lleva punto y coma al final.

### Los valores se numeran desde 0

Cada constante tiene un **número** asociado, empezando en `0`:

| Constante | Valor |
|---|---|
| `Pendiente` | `0` |
| `Procesado` | `1` |
| `Enviado` | `2` |
| `Entregado` | `3` |

### Valores propios

Se puede dar un número concreto a cada constante:

```csharp
public enum Prioridad
{
    Baja = 1,
    Media = 5,
    Alta = 10
}
```

Si una no lleva número, toma el **siguiente al anterior**.

## Dónde se declara

Una enumeración se declara **fuera de los métodos**:

| Dónde | Quién puede usarla |
|---|---|
| A nivel de **espacio de nombres** (fuera de toda clase) | **Todas las clases** del proyecto |
| Dentro de una **clase** | Solo esa clase (y, con `public`, otras escribiendo `Clase.Enum`) |
| Dentro de un **método** | No se puede (error de sintaxis) |

Lo habitual y cómodo es declararla **fuera de las clases**, como en el ejemplo. Un `enum` dentro de un método sería una "mini clase" dentro del método, algo que C# no permite.

## Usar una enumeración

```csharp
EstadoPedido status = EstadoPedido.Pendiente;

Console.WriteLine($"Estado del pedido: {status}");       // Pendiente
```

- Se escribe `Tipo.Constante`.
- Al **mostrarla** se ve el **nombre**, no el número.
- Una variable de tipo `EstadoPedido` solo admite sus constantes: `EstadoPedido.Perdido` no compila.
- Al escribir el punto, el editor lista las constantes disponibles.

### Ver el número: casting a `int`

```csharp
Console.WriteLine((int)status);       // 0
```

El casting explícito `(int)` (lección 9) da el número de la constante. Para `EstadoPedido.Enviado` sería `2`. Y al revés:

```csharp
EstadoPedido estado = (EstadoPedido)3;    // Entregado
```

## Comprobar si un valor existe: `Enum.IsDefined`

```csharp
Console.WriteLine(Enum.IsDefined(typeof(EstadoPedido), 3));    // True
Console.WriteLine(Enum.IsDefined(typeof(EstadoPedido), 8));    // False
```

- `Enum` (con mayúscula) es una **clase de ayuda** para trabajar con enumeraciones.
- `typeof(EstadoPedido)` obtiene **el tipo** de la enumeración en que se busca.
- Devuelve `true` si ese número corresponde a alguna constante.
- Es necesario porque el casting `(EstadoPedido)8` **no da error** aunque no exista: crea un valor sin nombre.

## Comparar

Las constantes se comparan con `==`, y con `<` / `>` por su **número**:

```csharp
status == EstadoPedido.Enviado           // True
status > EstadoPedido.Pendiente          // True: 2 > 0
```

## `switch` con enumeraciones

Es su pareja natural:

```csharp
switch (status)
{
    case EstadoPedido.Pendiente:
        Console.WriteLine("El pedido está esperando");
        break;
    case EstadoPedido.Enviado:
        Console.WriteLine("El pedido va de camino");
        break;
    // ...
}
```

El compilador avisa si en una **expresión `switch`** falta alguna constante (lección 17).

## Recorrer todas las constantes

```csharp
foreach (EstadoPedido estado in Enum.GetValues(typeof(EstadoPedido)))
{
    Console.WriteLine($"{(int)estado} = {estado}");
}
```

```text
0 = Pendiente
1 = Procesado
2 = Enviado
3 = Entregado
```

## Texto a enumeración: `Enum.TryParse`

```csharp
if (Enum.TryParse("enviado", true, out EstadoPedido leido))
{
    Console.WriteLine(leido);            // Enviado
}
```

- El segundo argumento (`true`) hace que **ignore mayúsculas y minúsculas**.
- `TryParse` devuelve `false` si el texto no corresponde a ninguna constante (sin lanzar error).
- `Enum.Parse` hace lo mismo pero **lanza una excepción** si no existe.

## Resumen

| Quiero... | Escribo... |
|---|---|
| Declarar | `enum Nombre { A, B, C }` |
| Usar | `Nombre.A` |
| El número | `(int)valor` |
| ¿Existe ese número? | `Enum.IsDefined(typeof(Nombre), 3)` |
| Todos los valores | `Enum.GetValues(typeof(Nombre))` |
| Texto a enum | `Enum.TryParse(texto, true, out valor)` |

## Ejemplo

```csharp
public enum EstadoPedido
{
    Pendiente,
    Procesado,
    Enviado,
    Entregado
}

class Program
{
    static void Main()
    {
        EstadoPedido status = EstadoPedido.Enviado;

        Console.WriteLine($"Estado del pedido: {status}");
        Console.WriteLine($"ID del estado: {(int)status}");

        Console.ReadKey();
    }
}
```

## Comandos

```bash
cd csharp/06-advanced-oop
dotnet new console -n Enums -o 43-enums
cd 43-enums
dotnet run
```

Fuente: [curso de C# de INFORMATICONFIG](https://www.youtube.com/watch?v=BtWC39X0WKQ&list=PL2Z95CSZ1N4F7mym8Ca16RoTDVAlIOPlT&index=43), vídeo 43.
