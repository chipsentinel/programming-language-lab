# Streams en Java

## Qué son los streams

Los **streams** (flujos) de Java permiten hacer **consultas sobre colecciones de datos**: filtrar, ordenar, transformar, agrupar y calcular totales, **encadenando operaciones**. Son el equivalente de **LINQ** en C#.

Todo eso existe también en **SQL** (el lenguaje de las bases de datos, que se verá más adelante), y los streams se le parecen, pero escritos dentro de Java.

Están en `java.util.stream` (desde Java 8).

> Un `Stream` **no es una colección**: no guarda datos, solo **describe un proceso** por el que pasan los datos de una colección.

## Los datos de partida

```java
List<String> nombres = new ArrayList<>(List.of("Ana", "Carlos", "Pedro", "Clara", "Andrés"));
```

## La estructura de un stream

```text
colección ──► .stream() ──► operaciones intermedias ──► operación final
              (abre)        filter, map, sorted...      collect, count, sum...
```

```java
nombres.stream()                              // 1. se abre el flujo
       .filter(n -> n.startsWith("C"))        // 2. operaciones intermedias (devuelven otro stream)
       .sorted()
       .toList();                             // 3. operación final (devuelve el resultado)
```

- **`stream()`** abre el flujo.
- Las **operaciones intermedias** (`filter`, `map`, `sorted`...) devuelven otro stream y se **encadenan**.
- La **operación final** (`toList`, `count`, `sum`, `forEach`...) produce el resultado y **es la que ejecuta todo**.
- Sin operación final, **no pasa nada**.

`n -> n.startsWith("C")` es una **expresión lambda**: "dado `n`, devuelve si empieza por C". La flecha `->` separa el parámetro de la expresión.

## Operaciones más usadas

Con `numeros = List.of(10, 20, 30, 40, 50, 60, 1)`:

| Operación | Código | Resultado |
|---|---|---|
| Filtrar | `.filter(n -> n > 25)` | `30, 40, 50, 60` |
| Transformar | `.map(n -> n * 2)` | `20, 40, 60, 80, 100, 120, 2` |
| Ordenar | `.sorted()` | de menor a mayor |
| Ordenar descendente | `.sorted(Comparator.reverseOrder())` | `60, 50, ..., 1` |
| Contar | `.count()` | `7` |
| ¿Alguno? | `.anyMatch(n -> n > 50)` | `true` |
| ¿Todos? | `.allMatch(n -> n > 0)` | `true` |
| Primero | `.filter(...).findFirst()` | `Optional(30)` |
| N primeros | `.limit(3)` | `10, 20, 30` |
| Saltar N | `.skip(5)` | `60, 1` |
| Sin repetidos | `.distinct()` | |

### Transformar: `map`

`map` **cambia cada elemento** por otra cosa:

```java
numeros.stream().map(n -> n * 2);                // el doble de cada número
personas.stream().map(Persona::nombre);          // solo los nombres
```

`Persona::nombre` es una **referencia a método**: equivale a `p -> p.nombre()`.

### Cálculos con `IntStream`

`sum`, `average`, `max` y `min` están en los flujos **de primitivos** (`IntStream`). Se convierte con `mapToInt`:

```java
int suma = numeros.stream().mapToInt(Integer::intValue).sum();                          // 211
double media = numeros.stream().mapToInt(Integer::intValue).average().getAsDouble();    // 30.14...
```

`average()` y `max()` devuelven un valor **opcional** (`OptionalDouble`), porque podría no haber elementos: se termina con `getAsDouble()` o `getAsInt()`.

### Sacar el resultado: `toList` y `collect`

```java
List<String> conC = nombres.stream().filter(n -> n.startsWith("C")).sorted().toList();     // Java 16+
```

Antes de Java 16 se escribía `.collect(Collectors.toList())`. Un stream no se puede recorrer con `for` directamente: hay que terminar con una operación final.

## Agrupar: `groupingBy`

```java
TreeMap<Character, List<String>> grupos =
        nombres.stream().collect(Collectors.groupingBy(n -> n.charAt(0), TreeMap::new, Collectors.toList()));
```

```text
A: Ana, Andrés
C: Carlos, Clara
P: Pedro
```

`groupingBy` reparte los elementos según un criterio (aquí, la primera letra) y devuelve un **mapa** (lección 34): clave → lista. Con `TreeMap::new` las claves salen **ordenadas**. Es el `GROUP BY` de SQL.

## Con objetos

Streams brillan con **listas de objetos**:

```java
record Persona(String nombre, int edad) { }

List<Persona> personas = List.of(
        new Persona("Sol", 34),
        new Persona("Marcos", 28),
        new Persona("Lucía", 8),
        new Persona("Pedro", 17),
        new Persona("Marta", 45));

personas.stream()
        .filter(p -> p.edad() >= 18)
        .sorted(Comparator.comparingInt(Persona::edad))
        .map(Persona::nombre)
        .toList();                                           // [Marcos, Sol, Marta]
```

En SQL sería: `SELECT nombre FROM personas WHERE edad >= 18 ORDER BY edad`.

> Un `record` (Java 16+) es una clase sencilla e inmutable para guardar datos: genera solo el constructor y los métodos `nombre()` y `edad()`.

## Un stream solo se usa una vez

```java
Stream<String> flujo = nombres.stream().filter(n -> n.startsWith("C"));

flujo.toList();      // correcto
flujo.toList();      // IllegalStateException: stream has already been operated upon or closed
```

Una vez ejecutada la operación final, el stream **se consume**. Para repetir la consulta hay que **crear un stream nuevo** (`nombres.stream()...`).

A diferencia de LINQ en C#, no se puede guardar una consulta en una variable para ejecutarla más tarde con los datos que haya entonces: cada `stream()` ve los datos del **momento en que se crea**.

## Resumen

| Quiero... | Escribo... |
|---|---|
| Abrir el flujo | `lista.stream()` |
| Quedarme con unos | `.filter(x -> condición)` |
| Cambiar cada elemento | `.map(x -> ...)` |
| Ordenar | `.sorted()` / `.sorted(Comparator...)` |
| Agrupar | `.collect(Collectors.groupingBy(...))` |
| Calcular | `.count()`, `.mapToInt(...).sum()`, `.average()` |
| Preguntar | `.anyMatch(...)`, `.allMatch(...)` |
| Guardar el resultado | `.toList()` |

## Ejemplo

```java
import java.util.List;

public class Streams {
    public static void main(String[] args) {
        List<Integer> numeros = List.of(10, 20, 30, 40, 50, 60, 1);

        List<Integer> pares = numeros.stream()
                .filter(n -> n % 2 == 0)
                .toList();

        System.out.println("Pares: " + pares);
        System.out.println("Suma de los pares: " + pares.stream().mapToInt(Integer::intValue).sum());
    }
}
```

## Comandos

```bash
cd java/06-advanced-oop/46-streams
java Streams.java
```

Fuente: [curso de C# de INFORMATICONFIG](https://www.youtube.com/watch?v=lvat7qWZ6tw&list=PL2Z95CSZ1N4F7mym8Ca16RoTDVAlIOPlT&index=46), vídeo 46.
