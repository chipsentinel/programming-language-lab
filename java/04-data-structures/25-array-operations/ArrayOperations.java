import java.util.Arrays;

public class ArrayOperations {

    public static void main(String[] args) {

        // Operaciones habituales con arrays: sumar, filtrar, copiar y combinar dos arrays.
        // Arrays.stream(...) convierte el array en un flujo (stream) sobre el que se pueden aplicar operaciones.


        int[] numeros = { 12, 233, 5, 658, 45555 };


        // ===== 1. Sumar todos los valores: sum =====

        int total = Arrays.stream(numeros).sum();
        System.out.println("Total de la suma de valores: " + total);   // 46463


        // ===== 2. Máximo, mínimo y media =====

        System.out.println("Máximo: " + Arrays.stream(numeros).max().getAsInt());           // 45555
        System.out.println("Mínimo: " + Arrays.stream(numeros).min().getAsInt());           // 5
        System.out.println("Media: " + Arrays.stream(numeros).average().getAsDouble());     // 9292.6


        // ===== 3. Filtrar: filter =====

        // filter recibe una condición y se queda con los elementos que la cumplen.
        // x -> x > 12 es una expresión lambda ("para cada x, ¿es x mayor que 12?"): se verá más adelante.
        int[] mayoresQue12 = Arrays.stream(numeros).filter(x -> x > 12).toArray();

        System.out.println("Mayores que 12: " + Arrays.toString(mayoresQue12));   // [233, 658, 45555]

        // filter devuelve un flujo: toArray() lo convierte de nuevo en un array


        // ===== 4. Copiar un array =====

        // Asignar un array a otra variable NO lo copia: las dos apuntan al mismo array
        int[] alias = numeros;
        alias[0] = 999;
        System.out.println("numeros[0] tras cambiar alias[0]: " + numeros[0]);   // 999

        numeros[0] = 12;   // se restaura el valor original

        // clone crea una copia independiente
        int[] cantidades = numeros.clone();
        cantidades[0] = 1;

        System.out.println("Array numeros: " + Arrays.toString(numeros));         // [12, 233, 5, 658, 45555]
        System.out.println("Array cantidades: " + Arrays.toString(cantidades));   // [1, 233, 5, 658, 45555]


        // ===== 5. Operar dos arrays elemento a elemento =====

        int[] array1 = { 10, 20, 30, 40 };
        int[] array2 = { 1, 2, 3, 4 };

        // Los dos arrays deben tener la misma cantidad de elementos
        int[] suma = new int[array1.length];
        int[] resta = new int[array1.length];
        int[] producto = new int[array1.length];
        int[] division = new int[array1.length];

        for (int i = 0; i < array1.length; i++) {
            suma[i] = array1[i] + array2[i];          // 10 + 1, 20 + 2, ...
            resta[i] = array1[i] - array2[i];
            producto[i] = array1[i] * array2[i];
            division[i] = array1[i] / array2[i];      // división entera
        }

        System.out.println("Suma: " + Arrays.toString(suma));             // [11, 22, 33, 44]
        System.out.println("Resta: " + Arrays.toString(resta));           // [9, 18, 27, 36]
        System.out.println("Producto: " + Arrays.toString(producto));     // [10, 40, 90, 160]
        System.out.println("División: " + Arrays.toString(division));     // [10, 10, 10, 10]


        // ===== 6. Un array no se puede sumar con + =====

        // array1 + array2 no suma los elementos: no compila.
        // Hay que recorrer las posiciones con un for, como en el apartado anterior.
    }
}
