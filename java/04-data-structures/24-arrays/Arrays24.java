import java.util.Arrays;

public class Arrays24 {

    public static void main(String[] args) {

        // Un array es una estructura que guarda varios valores DEL MISMO TIPO en una sola variable.
        // Cada valor (elemento) tiene una posición, y la primera posición es la 0.


        // ===== 1. Declarar un array =====

        // tipo[] nombre = new tipo[cantidad];
        int[] enteros = new int[5];          // 5 enteros
        String[] nombres = new String[5];    // 5 textos
        double[] precios = new double[5];    // 5 decimales

        // Un array nuevo se rellena con el valor por defecto de su tipo
        System.out.println("Posición 2 de enteros: " + enteros[2]);   // 0
        System.out.println("Posición 2 de precios: " + precios[2]);   // 0.0
        System.out.println("Posición 2 de nombres está vacía: " + (nombres[2] == null));   // true


        // ===== 2. Guardar y leer valores por posición =====

        enteros[0] = 30;
        enteros[1] = 6;
        enteros[2] = 112;
        enteros[3] = 20000;
        enteros[4] = 60;

        System.out.println("enteros[2] = " + enteros[2]);   // 112
        System.out.println("enteros[4] = " + enteros[4]);   // 60

        // Con 5 elementos las posiciones válidas son 0, 1, 2, 3 y 4
        // enteros[5] = 1;   // error en ejecución: ArrayIndexOutOfBoundsException

        // Un array solo guarda el tipo declarado
        // enteros[0] = 4.8;      // error: un decimal no cabe en un int
        // enteros[0] = "hola";   // error: un texto no cabe en un int


        // ===== 3. Crear un array con sus valores: inicializador =====

        // Sin indicar el tamaño ni las posiciones: se calculan solas, empezando en 0
        int[] numeros = { 20, 15, 18, 93, 36, 50, 1, 2, 7, 4 };

        System.out.println("numeros[0] = " + numeros[0]);   // 20
        System.out.println("numeros[3] = " + numeros[3]);   // 93

        String[] personas = { "Juan", "Ana", "Antonio", "Samuel", "Elizabeth" };


        // ===== 4. Longitud: length =====

        // En un array length es un campo, sin paréntesis (en String es un método: length())
        System.out.println("Cantidad de elementos: " + numeros.length);   // 10


        // ===== 5. Recorrer un array con for =====

        for (int i = 0; i < numeros.length; i++) {
            System.out.println("numeros[" + i + "] = " + numeros[i]);
        }


        // ===== 6. Mostrar todo el array: Arrays.toString y String.join =====

        // Para un array de números se usa Arrays.toString (añade corchetes)
        System.out.println("Array original: " + Arrays.toString(numeros));


        // ===== 7. Ordenar: Arrays.sort =====

        Arrays.sort(numeros);   // cambia el propio array, de menor a mayor
        System.out.println("Array organizado: " + Arrays.toString(numeros));

        Arrays.sort(personas);  // con textos, orden alfabético
        // Para un array de textos también sirve String.join, sin corchetes
        System.out.println("Nombres organizados: " + String.join(", ", personas));


        // ===== 8. Invertir =====

        // Java no tiene un método directo para invertir un int[]: se intercambian
        // el primero con el último, el segundo con el penúltimo, etc.
        for (int i = 0; i < numeros.length / 2; i++) {
            int temporal = numeros[i];
            numeros[i] = numeros[numeros.length - 1 - i];
            numeros[numeros.length - 1 - i] = temporal;
        }
        System.out.println("Array al revés: " + Arrays.toString(numeros));


        // ===== 9. Buscar =====

        // Arrays no tiene indexOf: se convierte a lista con Arrays.asList (solo para objetos como String)
        // Devuelve la posición, o -1 si no está (igual que en String)
        System.out.println("Posición de Luis: " + Arrays.asList(personas).indexOf("Luis"));       // -1
        System.out.println("Posición de Ana: " + Arrays.asList(personas).indexOf("Ana"));         // 0
        System.out.println("Posición de Samuel: " + Arrays.asList(personas).indexOf("Samuel"));   // 4


        // ===== 10. Rellenar con un valor: Arrays.fill =====

        Arrays.fill(numeros, 99);
        System.out.println("Array llenado con 99: " + Arrays.toString(numeros));


        // ===== 11. Vaciar =====

        // No hay clear: se rellena con el valor "vacío" (aquí, un texto vacío)
        Arrays.fill(personas, "");
        System.out.println("Array limpio: " + String.join(", ", personas));   // solo quedan las comas


        // ===== 12. El tamaño no cambia =====

        // Un array tiene un tamaño fijo desde que se crea: no se pueden añadir elementos.
        // Para colecciones que crecen se usan las listas (lección 33).
    }
}
