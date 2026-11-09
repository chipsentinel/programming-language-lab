public class WhileLoop {

    public static void main(String[] args) {

        // ===== 1. Bucle while =====

        // Un bucle repite un bloque de código mientras se cumpla una condición.
        // while (condición) { ... }: se comprueba la condición antes de cada vuelta.

        int valor = 0;

        while (valor < 3) {
            System.out.println("Valor es: " + valor);
            valor++;   // sin esta línea valor nunca llegaría a 3 y el bucle sería infinito
        }

        System.out.println("Fin del bucle");


        // ===== 2. Bucle infinito =====

        // Si la condición siempre se cumple, el bucle no termina nunca (se corta con Ctrl+C).
        /*
        int contador = 0;
        while (contador < 3) {
            System.out.println("Contador es: " + contador);   // falta contador++
        }
        */


        // ===== 3. Incluir el límite y empezar en 1 =====

        int numero = 1;

        while (numero <= 3) {   // <= incluye el 3
            System.out.println("Vuelta " + numero);
            numero++;
        }


        // ===== 4. Decisiones dentro del bucle =====

        numero = 1;

        while (numero <= 5) {
            if (numero == 2) {
                System.out.println("Soy la vuelta dos");
            } else {
                System.out.println("Vuelta " + numero);
            }

            numero++;
        }


        // ===== 5. Tabla de multiplicar =====

        final int TABLA = 8;
        numero = 1;

        System.out.println("Tabla del " + TABLA);

        while (numero <= 10) {
            System.out.println(TABLA + " x " + numero + " = " + TABLA * numero);
            numero++;
        }

        System.out.println("Fin de la tabla");


        // ===== 6. while puede no ejecutarse ninguna vez =====

        // La condición se comprueba primero: si ya es falsa, el bloque se salta.
        int intentos = 5;

        while (intentos < 3) {
            System.out.println("Esto no se imprime nunca");
            intentos++;
        }

        System.out.println("Intentos: " + intentos);   // 5


        // ===== 7. Acumular un resultado =====

        int suma = 0;
        numero = 1;

        while (numero <= 5) {
            suma += numero;   // suma = suma + numero
            numero++;
        }

        System.out.println("Suma de 1 a 5: " + suma);   // 15
    }
}
