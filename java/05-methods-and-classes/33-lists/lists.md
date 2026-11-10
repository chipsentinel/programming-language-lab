# Listas en Java

## Qué es una lista

Una **colección que guarda elementos en orden**, como un array, pero con **tamaño dinámico**: se pueden añadir, insertar y quitar elementos en cualquier momento. Se escribe `List<T>`, donde `T` es el tipo de los elementos, y la implementación más habitual es `ArrayList`.

| | Array (`int[]`) | Lista (`List<Integer>`) |
|---|---|---|
| Tamaño | **Fijo** | **Dinámico** |
| Añadir / quitar | No se puede | `add`, `remove`... |
| Tamaño actual | `length` | **`size()`** |
| Acceso por posición | `a[0]` | `l.get(0)` |
| Tipo de los elementos | Primitivos u objetos | **Solo objetos** |

## Crear una lista

```java
import java.util.ArrayList;
import java.util.List;

List<String> simpsons = new ArrayList<>();
```

```text
List<String>   simpsons   =   new ArrayList<>();
interfaz       nombre            implementación
```

- **`List`** es una **interfaz** (el "qué"); **`ArrayList`** es la clase que la implementa (el "cómo"). Se declara con `List` y se crea con `ArrayList`.
- **`<String>`** indica el **tipo de los elementos**. Es característico de las colecciones genéricas.
- **`<>`** (operador diamante) en el `new` deja que Java deduzca el tipo.
- Empieza **vacía**: `simpsons.size()` vale `0`.
- Hay que **importar** `java.util.List` y `java.util.ArrayList`.

### Sin tipos primitivos

Los tipos entre `< >` deben ser **clases**, no primitivos. Para enteros se usa `Integer`, no `int`; para decimales `Double`; para booleanos `Boolean`:

```java
List<Integer> valores = new ArrayList<>(Arrays.asList(21, 8, 235, 100, 120));
```

`Arrays.asList(...)` crea una lista con valores iniciales. Se copia a un `ArrayList` para que se pueda **modificar**: la lista que devuelve `Arrays.asList` tiene tamaño fijo.

## Añadir elementos: `add`

```java
simpsons.add("Homero");
simpsons.add("Marge");
simpsons.add("Lisa");
simpsons.add("Bart");
simpsons.add("Maggie");

System.out.println(simpsons.size());    // 5
```

`add` añade **al final**. La lista crece sola.

- Una `List<String>` solo admite `String`; `simpsons.add(5)` no compila.
- **Admite duplicados**: se puede añadir `"Homero"` dos veces.

## Recorrer: `for` mejorado

Un bucle pensado para recorrer colecciones, sin índice (*for each*):

```java
for (String nombre : simpsons) {
    System.out.println(nombre);
}
```

```text
for (  String nombre  :  simpsons  )
       └ variable        └ colección
```

En cada vuelta, `nombre` toma el valor del **siguiente elemento**. También se puede con `for`, accediendo por posición con `get`:

```java
for (int i = 0; i < simpsons.size(); i++) {
    System.out.println("Posición " + i + ": " + simpsons.get(i));
}
```

Lo que **no** se puede es modificar la lista (añadir o quitar) **mientras** se recorre con el `for` mejorado: lanza `ConcurrentModificationException`.

## Métodos principales

| Método | Qué hace |
|---|---|
| `add(valor)` | Añade al final |
| `add(posición, valor)` | Inserta en una posición |
| `remove(valor)` | Quita la **primera** aparición del valor |
| `remove(posición)` | Quita el elemento de esa posición |
| `get(posición)` | Devuelve el elemento de esa posición |
| `contains(valor)` | ¿Existe? Devuelve `boolean` |
| `indexOf(valor)` | Posición, o `-1` si no está |
| `Collections.sort(l)` | Ordena de menor a mayor |
| `Collections.reverse(l)` | Invierte el orden actual |
| `clear()` | Quita todos los elementos |
| `size()` | Número de elementos |

`Collections` es una clase de ayuda de `java.util` (`import java.util.Collections;`).

### Insertar

```java
simpsons.add(0, "Apu");    // Apu pasa a la posición 0 y los demás se desplazan
```

Con `add(valor)` el elemento va siempre al final; con `add(posición, valor)` se elige dónde.

### Quitar por valor o por posición: cuidado con `Integer`

`remove` está **sobrecargado**: hace dos cosas distintas según el argumento.

```java
simpsons.remove("Homero");    // quita el VALOR "Homero"
simpsons.remove(5);           // quita lo que hay en la POSICIÓN 5
```

Con `String` no hay confusión. Con una lista de `Integer` sí:

```java
List<Integer> valores = new ArrayList<>(Arrays.asList(21, 8, 235, 100, 120));

valores.remove(1);                      // POSICIÓN 1: quita el 8
valores.remove(Integer.valueOf(235));   // VALOR 235: hay que indicar que es un objeto
valores.remove(235);                    // ¡POSICIÓN 235! IndexOutOfBoundsException
```

Java interpreta `remove(235)` como "quita la posición 235". Para quitar el **valor** se escribe `Integer.valueOf(235)`. Es un error clásico.

- `remove(valor)` solo quita la **primera** aparición, y si no existe no hace nada.
- `remove(posición)` lanza `IndexOutOfBoundsException` si la posición no existe.
- Al quitar, los elementos siguientes se **desplazan**.

### Buscar

```java
System.out.println(simpsons.contains("Lisa"));      // true
System.out.println(simpsons.indexOf("Bart"));       // 3
System.out.println(simpsons.indexOf("Nelson"));     // -1
```

**`-1` significa "no está"**, igual que en `String` y en arrays. Distinguen mayúsculas de minúsculas.

### Ordenar e invertir

```java
Collections.sort(simpsons);       // [Apu, Bart, Lisa, Maggie, Marge]
Collections.reverse(simpsons);    // [Marge, Maggie, Lisa, Bart, Apu]
```

**Modifican la propia lista**, y `reverse` no ordena: solo da la vuelta. Para de mayor a menor: `sort` y luego `reverse`.

### Vaciar

```java
simpsons.clear();
System.out.println(simpsons.size());    // 0
```

## Mostrar una lista

Imprimir la lista directamente da un texto con **corchetes**:

```java
System.out.println(simpsons);    // [Apu, Bart, Lisa, Maggie, Marge]
```

Para una lista de textos también sirve `String.join(", ", simpsons)`, sin corchetes.

## Entre array y lista

```java
Integer[] array = valores.toArray(new Integer[0]);            // lista → array
List<Integer> lista = new ArrayList<>(Arrays.asList(array));  // array → lista
```

## Cuándo usar cada una

- **Array**: se sabe cuántos elementos hay y no cambian.
- **Lista**: la cantidad **cambia** durante el programa. Es lo más habitual.

## Ejemplo

```java
import java.util.ArrayList;
import java.util.List;

public class Lists {
    public static void main(String[] args) {
        List<String> nombres = new ArrayList<>();

        nombres.add("Sol");
        nombres.add("Marcos");
        nombres.add(0, "Lucía");

        for (String nombre : nombres) {
            System.out.println(nombre);
        }

        System.out.println("Total: " + nombres.size());
    }
}
```

## Comandos

```bash
cd java/05-methods-and-classes/33-lists
java Lists.java
```

Fuente: [curso de C# de INFORMATICONFIG](https://www.youtube.com/watch?v=Lz4jMlSgM_M&list=PL2Z95CSZ1N4F7mym8Ca16RoTDVAlIOPlT&index=33), vídeo 33.
