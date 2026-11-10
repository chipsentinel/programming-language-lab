import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.TreeMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

// Los streams (flujos) de Java permiten hacer CONSULTAS sobre colecciones de datos:
// filtrar, ordenar, transformar, agrupar y calcular totales, encadenando operaciones.
// Es el equivalente de LINQ en C#.
//
// Un stream se obtiene con lista.stream(), se le aplican operaciones intermedias (filter, map, sorted...)
// y se termina con una operación final (collect, count, sum...), que es la que ejecuta todo.
//
// La clase con main va primero; el record Persona está al final del archivo.

public class Streams {

    // Une los elementos de un stream con ", " (en C# es string.Join)
    static String unir(Stream<?> stream) {
        return stream.map(String::valueOf).collect(Collectors.joining(", "));
    }

    public static void main(String[] args) {

        List<String> nombres = new ArrayList<>(List.of("Ana", "Carlos", "Pedro", "Clara", "Andrés"));


        // ===== 1. Filtrar y ordenar: filter, sorted =====

        // Para cada elemento n de la lista, que empiece por C, ordenado
        // (la expresión n -> ... se llama lambda: "n devuelve ...")
        System.out.println("Empiezan por C: " + unir(nombres.stream().filter(n -> n.startsWith("C")).sorted()));   // Carlos, Clara
        System.out.println("Empiezan por A: " + unir(nombres.stream().filter(n -> n.startsWith("A")).sorted()));   // Ana, Andrés


        // ===== 2. Sacar el resultado a una lista: collect / toList =====

        List<String> conC = nombres.stream().filter(n -> n.startsWith("C")).sorted().toList();   // toList(): Java 16+
        System.out.println("Con métodos: " + String.join(", ", conC));


        // ===== 3. Con números =====

        List<Integer> numeros = List.of(10, 20, 30, 40, 50, 60, 1);

        System.out.println("Pares: " + unir(numeros.stream().filter(n -> n % 2 == 0)));              // 10, 20, 30, 40, 50, 60
        System.out.println("Mayores que 25: " + unir(numeros.stream().filter(n -> n > 25)));          // 30, 40, 50, 60


        // ===== 4. Transformar (proyectar): map =====

        System.out.println("Doble de cada uno: " + unir(numeros.stream().map(n -> n * 2)));          // 20, 40, ..., 2


        // ===== 5. Ordenar: sorted =====

        System.out.println("De mayor a menor: " + unir(numeros.stream().sorted(Comparator.reverseOrder())));   // 60, 50, ..., 1


        // ===== 6. Cálculos: count, sum, average, max, min =====

        System.out.println("Cantidad: " + numeros.stream().count());                                  // 7
        System.out.println("Suma: " + numeros.stream().mapToInt(Integer::intValue).sum());            // 211
        System.out.println("Media: " + String.format("%.2f", numeros.stream().mapToInt(Integer::intValue).average().getAsDouble()));   // 30,14
        System.out.println("Máximo: " + numeros.stream().max(Integer::compare).get());                // 60
        System.out.println("Mínimo: " + numeros.stream().min(Integer::compare).get());                // 1

        // sum, average... existen en IntStream (flujo de int): mapToInt convierte el Stream<Integer> en IntStream


        // ===== 7. Preguntas: anyMatch, allMatch, findFirst =====

        System.out.println("¿Alguno mayor que 50? " + numeros.stream().anyMatch(n -> n > 50));       // true
        System.out.println("¿Todos mayores que 0? " + numeros.stream().allMatch(n -> n > 0));        // true
        System.out.println("Primero mayor que 25: " + numeros.stream().filter(n -> n > 25).findFirst().get());   // 30
        System.out.println("¿Alguno mayor que 100? " + numeros.stream().anyMatch(n -> n > 100));     // false


        // ===== 8. Seleccionar partes: limit, skip, distinct =====

        System.out.println("Los 3 primeros: " + unir(numeros.stream().limit(3)));                    // 10, 20, 30
        System.out.println("Sin los 5 primeros: " + unir(numeros.stream().skip(5)));                 // 60, 1

        List<Integer> repetidos = Arrays.asList(1, 2, 2, 3, 3, 3);
        System.out.println("Sin repetidos: " + unir(repetidos.stream().distinct()));                // 1, 2, 3


        // ===== 9. Agrupar: Collectors.groupingBy =====

        // TreeMap mantiene las claves ordenadas
        TreeMap<Character, List<String>> grupos =
                nombres.stream().collect(Collectors.groupingBy(n -> n.charAt(0), TreeMap::new, Collectors.toList()));

        for (var grupo : grupos.entrySet()) {
            System.out.println(grupo.getKey() + ": " + String.join(", ", grupo.getValue()));
        }


        // ===== 10. Con objetos =====

        List<Persona> personas = List.of(
                new Persona("Sol", 34),
                new Persona("Marcos", 28),
                new Persona("Lucía", 8),
                new Persona("Pedro", 17),
                new Persona("Marta", 45));

        System.out.println("Mayores de edad (por edad): " + unir(personas.stream()
                .filter(p -> p.edad() >= 18)
                .sorted(Comparator.comparingInt(Persona::edad))
                .map(Persona::nombre)));                                                              // Marcos, Sol, Marta
        System.out.println("Edad media: " + String.format("%.1f", personas.stream().mapToInt(Persona::edad).average().getAsDouble()));   // 26,4


        // ===== 11. Un stream solo se puede usar una vez =====

        Stream<String> flujo = nombres.stream().filter(n -> n.startsWith("C"));
        System.out.println("Primera vez: " + unir(flujo));
        try {
            unir(flujo);          // segunda vez: error
        } catch (IllegalStateException error) {
            System.out.println("Un stream no se puede reutilizar");
        }

        // A diferencia de LINQ, no hay "ejecución diferida" de una consulta guardada en una variable:
        // para repetirla hay que crear un stream nuevo cada vez (nombres.stream()...)
    }
}


// Un record: una clase sencilla e inmutable para guardar datos (Java 16+).
// Genera solo el constructor, los métodos nombre() y edad(), equals, hashCode y toString.
record Persona(String nombre, int edad) { }
