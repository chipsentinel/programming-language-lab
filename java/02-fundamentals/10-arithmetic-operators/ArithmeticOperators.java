public class ArithmeticOperators {

    public static void main(String[] args) {

        // ===== 1. Declarar varias variables del mismo tipo en una línea =====

        // Todas reciben el mismo tipo de dato
        int valor1 = 90, valor2 = 70, valor3 = 10;


        // ===== 2. Suma (+) =====

        int total = valor1 + valor2;
        System.out.println("Total de la suma: " + total);   // 160

        // Con un texto a un lado, + concatena; con dos números, suma
        System.out.println(valor1 + valor2);                // 160
        System.out.println("Suma: " + valor1 + valor2);     // Suma: 9070 (concatena)
        System.out.println("Suma: " + (valor1 + valor2));   // Suma: 160 (los paréntesis fuerzan la suma)


        // ===== 3. Resta (-) =====

        int diferencia = valor1 - valor2;
        System.out.println("Total de la resta: " + diferencia);   // 20


        // ===== 4. Multiplicación (*) =====

        int producto = valor3 * valor2;
        System.out.println("Producto: " + producto);   // 700


        // ===== 5. División (/) =====

        int resultado = valor1 / valor3;
        System.out.println("Resultado: " + resultado);   // 9

        // Entre dos enteros la división es entera: se descartan los decimales
        System.out.println(valor1 / 20);        // 4, no 4.5
        System.out.println(valor1 / 20.0);      // 4.5: basta con que uno sea decimal


        // ===== 6. Residuo o módulo (%) =====

        var modulo = valor2 % valor3;
        System.out.println("Residuo: " + modulo);   // 0

        System.out.println(valor1 % 20);        // 10: 90 entre 20 sobra 10


        // ===== 7. División entre cero =====

        // Con enteros lanza una excepción: ArithmeticException
        // int cero = 0;
        // System.out.println(valor1 / cero);

        // Con decimales no falla: da infinito
        System.out.println(90.0 / 0);           // Infinity


        // ===== 8. Con decimales =====

        // Si las variables son double, el resultado conserva los decimales
        var decimalValor = 2.3;
        System.out.println("División: " + valor2 / decimalValor);   // muchos decimales
        System.out.println("Residuo: " + valor2 % decimalValor);     // también decimales


        // ===== 9. Potencia =====

        // Java no tiene operador de potencia: se usa Math.pow (devuelve double)
        System.out.println(Math.pow(2, 3));   // 8.0
    }
}
