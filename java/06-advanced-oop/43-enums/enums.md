# Enumeraciones (`enum`) en Java

## Qué es una enumeración

Un **tipo de dato formado por un conjunto fijo de constantes con nombre**: valores relacionados entre sí que se escriben con palabras en lugar de números o textos "mágicos".

En vez de decir "estado `2`" (¿qué significa?) o "estado `"enviado"`" (¿y si se escribe `"enviadoo"`?), se dice `EstadoPedido.ENVIADO`.

Ventajas:

- **Legibilidad**: el código se explica solo.
- **Seguridad**: solo se pueden usar los valores definidos; el compilador rechaza los inventados.
- **Mantenimiento**: los valores están en un solo sitio.

Ejemplos típicos: días de la semana, meses, estados de un pedido, niveles de prioridad, tipos de usuario, colores, direcciones (norte, sur, este, oeste).

## Definir una enumeración

```java
enum EstadoPedido {
    PENDIENTE,
    PROCESADO,
    ENVIADO,
    ENTREGADO
}
```

- Se escribe `enum`. El nombre del tipo (`EstadoPedido`) va en `PascalCase` y en **singular**.
- Las constantes van entre llaves, **separadas por comas**, y por **convención en MAYÚSCULAS** (son constantes).
- Si el `enum` solo tiene constantes, **no hace falta** punto y coma al final.

### Cada constante tiene una posición

Cada constante tiene un número, su **posición** (`ordinal()`), empezando en `0`:

| Constante | `ordinal()` |
|---|---|
| `PENDIENTE` | `0` |
| `PROCESADO` | `1` |
| `ENVIADO` | `2` |
| `ENTREGADO` | `3` |

## Un `enum` es una clase

A diferencia de C#, en Java un `enum` es una **clase completa**: cada constante es un **objeto**, y puede tener **atributos, constructor y métodos**. Así se dan "valores propios":

```java
enum Prioridad {
    BAJA(1),
    MEDIA(5),
    ALTA(10);                       // aquí sí hace falta punto y coma: hay más código

    private final int valor;

    Prioridad(int valor) {          // el constructor de un enum es siempre private
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }
}
```

```java
Prioridad prioridad = Prioridad.ALTA;
System.out.println(prioridad.getValor());       // 10
```

Y se pueden asociar textos u otros datos a cada constante:

```java
enum EstadoPedido {
    PENDIENTE("El pedido está esperando"),
    ENVIADO("El pedido va de camino");

    private final String descripcion;

    EstadoPedido(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }
}
```

## Dónde se declara

Una enumeración se declara **fuera de los métodos**:

| Dónde | Quién puede usarla |
|---|---|
| En **su propio archivo** (`EstadoPedido.java`) | Cualquier clase (si es `public`) |
| Fuera de la clase, en el mismo archivo | Las clases del mismo paquete |
| Dentro de una **clase** | Con `Clase.Enum` |
| Dentro de un **método** | Sí (desde Java 16), pero es poco habitual |

## Usar una enumeración

```java
EstadoPedido status = EstadoPedido.PENDIENTE;

System.out.println("Estado del pedido: " + status);      // PENDIENTE
```

- Se escribe `Tipo.CONSTANTE`.
- Al **mostrarla** se ve el **nombre**, no el número.
- Una variable de tipo `EstadoPedido` solo admite sus constantes (o `null`): `EstadoPedido.PERDIDO` no compila.

### Ver el número: `ordinal()`

```java
System.out.println(status.ordinal());       // 0
```

Java **no permite** el casting `(int)` de un enum, como sí hace C#. Y no conviene depender del `ordinal()` para guardar datos: cambia si se reordenan las constantes.

## Comprobar si un valor existe

Java no tiene un `IsDefined`. Se comprueba contra el rango de posiciones de `values()`:

```java
static boolean existePosicion(int posicion) {
    return posicion >= 0 && posicion < EstadoPedido.values().length;
}
```

## Comparar

Las constantes se comparan con **`==`** (aquí es correcto, porque cada constante es un objeto único), y por su posición con `compareTo`:

```java
status == EstadoPedido.ENVIADO                          // true
status.compareTo(EstadoPedido.PENDIENTE) > 0            // true: 2 > 0
```

Los operadores `<` y `>` **no** funcionan con enum.

## `switch` con enumeraciones

Es su pareja natural. Dentro del `switch` **no se escribe el nombre del tipo**:

```java
switch (status) {
    case PENDIENTE:
        System.out.println("El pedido está esperando");
        break;
    case ENVIADO:
        System.out.println("El pedido va de camino");
        break;
    // ...
}
```

## Recorrer todas las constantes: `values()`

```java
for (EstadoPedido estado : EstadoPedido.values()) {
    System.out.println(estado.ordinal() + " = " + estado);
}
```

```text
0 = PENDIENTE
1 = PROCESADO
2 = ENVIADO
3 = ENTREGADO
```

## Texto a enumeración: `valueOf`

```java
EstadoPedido leido = EstadoPedido.valueOf("ENVIADO");

try {
    EstadoPedido.valueOf("PERDIDO");
} catch (IllegalArgumentException error) {
    System.out.println("No existe el estado Perdido");
}
```

- `valueOf` exige el **nombre exacto**, mayúsculas incluidas (`"enviado".toUpperCase()` si hace falta).
- Si no existe **lanza `IllegalArgumentException`** (no hay un `TryParse`).

## Resumen

| Quiero... | Escribo... |
|---|---|
| Declarar | `enum Nombre { A, B, C }` |
| Usar | `Nombre.A` |
| La posición | `valor.ordinal()` |
| Todos los valores | `Nombre.values()` |
| Texto a enum | `Nombre.valueOf("A")` |
| Datos propios | Atributos y constructor dentro del `enum` |

## Ejemplo

```java
public class Enums {
    public static void main(String[] args) {
        EstadoPedido status = EstadoPedido.ENVIADO;

        System.out.println("Estado del pedido: " + status);
        System.out.println("ID del estado: " + status.ordinal());
    }
}

enum EstadoPedido {
    PENDIENTE,
    PROCESADO,
    ENVIADO,
    ENTREGADO
}
```

## Comandos

```bash
cd java/06-advanced-oop/43-enums
java Enums.java
```

Fuente: [curso de C# de INFORMATICONFIG](https://www.youtube.com/watch?v=BtWC39X0WKQ&list=PL2Z95CSZ1N4F7mym8Ca16RoTDVAlIOPlT&index=43), vídeo 43.
