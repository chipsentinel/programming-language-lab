import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

// Una lista es una colección que guarda elementos en orden, como un array,
// pero con tamaño dinámico: se pueden añadir, insertar y quitar elementos.
// List es la interfaz y ArrayList la implementación habitual.

public class Lists {

    public static void main(String[] args) {

        // ===== 1. Crear una lista =====

        // List<tipo> nombre = new ArrayList<>();
        // El tipo de los elementos va entre ángulos < > y debe ser una clase (String, Integer...)
        List<String> simpsons = new ArrayList<>();

        System.out.println("Elementos al crearla: " + simpsons.size());   // 0: empieza vacía


        // ===== 2. Añadir elementos: add =====

        simpsons.add("Homero");
        simpsons.add("Marge");
        simpsons.add("Lisa");
        simpsons.add("Bart");
        simpsons.add("Maggie");

        System.out.println("Cantidad: " + simpsons.size());   // 5


        // ===== 3. Recorrer una lista: for each =====

        // for (tipo variable : colección) recorre cada elemento sin usar índices
        for (String nombre : simpsons) {
            System.out.println(nombre);
        }

        // También se puede con for, accediendo por posición con get
        for (int i = 0; i < simpsons.size(); i++) {
            System.out.println("Posición " + i + ": " + simpsons.get(i));
        }


        // ===== 4. Las listas admiten duplicados =====

        simpsons.add("Homero");
        System.out.println("Con duplicado: " + simpsons);


        // ===== 5. Quitar por valor: remove =====

        // Quita la PRIMERA aparición del valor
        simpsons.remove("Homero");
        System.out.println("Tras remove(Homero): " + simpsons);


        // ===== 6. Insertar en una posición: add(posición, valor) =====

        simpsons.add(0, "Apu");
        System.out.println("Tras add(0, Apu): " + simpsons);


        // ===== 7. Quitar por posición: remove(posición) =====

        simpsons.remove(5);   // la posición 5 es el segundo Homero
        System.out.println("Tras remove(5): " + simpsons);


        // ===== 8. Buscar: contains e indexOf =====

        System.out.println("¿Contiene Lisa? " + simpsons.contains("Lisa"));   // true
        System.out.println("¿Contiene Moe? " + simpsons.contains("Moe"));     // false

        System.out.println("Posición de Bart: " + simpsons.indexOf("Bart"));       // 3
        System.out.println("Posición de Nelson: " + simpsons.indexOf("Nelson"));   // -1: no está


        // ===== 9. Ordenar e invertir: Collections.sort y Collections.reverse =====

        Collections.sort(simpsons);
        System.out.println("Ordenada: " + simpsons);

        Collections.reverse(simpsons);
        System.out.println("Al revés: " + simpsons);


        // ===== 10. Vaciar: clear =====

        simpsons.clear();
        System.out.println("Tras clear: " + simpsons.size());   // 0


        // ===== 11. Una lista de enteros =====

        // Las listas no admiten tipos primitivos (int): se usa su clase, Integer.
        // Arrays.asList crea una lista ya con valores (aquí se copia a un ArrayList para poder modificarla)
        List<Integer> valores = new ArrayList<>(Arrays.asList(21, 8, 235, 100, 120));
        System.out.println("Valores: " + valores);

        // OJO: con Integer, remove(235) se tomaría como POSICIÓN 235 y fallaría.
        // Para quitar el VALOR hay que indicar que es un objeto:
        valores.remove(Integer.valueOf(235));     // quita el VALOR 235
        System.out.println("Tras remove(235): " + valores);

        valores.remove(1);     // quita lo que hay en la POSICIÓN 1 (el 8)
        System.out.println("Tras remove(1): " + valores);

        Collections.sort(valores);
        Collections.reverse(valores);
        System.out.println("De mayor a menor: " + valores);

        // Una lista de Integer solo admite Integer
        // valores.add("hola");   // error: un texto no es un Integer


        // ===== 12. Convertir entre array y lista =====

        Integer[] array = valores.toArray(new Integer[0]);
        List<Integer> deNuevoLista = new ArrayList<>(Arrays.asList(array));
        System.out.println("Array: " + array.length + ", lista: " + deNuevoLista.size());
    }
}
