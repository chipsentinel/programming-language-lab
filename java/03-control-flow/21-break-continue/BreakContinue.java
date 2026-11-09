public class BreakContinue {

    public static void main(String[] args) {

        // ===== 1. break: salir del bucle =====

        // break termina el bucle en el acto: el programa sigue después de él.

        for (int i = 0; i < 5; i++) {
            if (i == 3) {
                break;   // en la vuelta 3 se sale del bucle
            }

            System.out.println("Vuelta: " + i);   // 0, 1, 2
        }

        System.out.println("Fuera del bucle");


        // ===== 2. El break solo actúa si la condición llega a cumplirse =====

        // Con i += 2 la variable vale 0, 2, 4, 6...: nunca vale 3, así que este break no corre
        for (int i = 0; i <= 10; i += 2) {
            if (i == 3) {
                break;
            }

            System.out.println("Paso de 2: " + i);   // 0, 2, 4, 6, 8, 10
        }

        // Con i == 6 sí se cumple, y corta en esa vuelta
        for (int i = 0; i <= 10; i += 2) {
            if (i == 6) {
                break;
            }

            System.out.println("Corta en 6: " + i);   // 0, 2, 4
        }


        // ===== 3. continue: saltar a la siguiente vuelta =====

        // continue se salta lo que queda de la vuelta actual, pero el bucle sigue

        for (int i = 0; i < 5; i++) {
            if (i == 3) {
                continue;   // no se escribe la vuelta 3
            }

            System.out.println("Con continue: " + i);   // 0, 1, 2, 4
        }


        // ===== 4. break en un while =====

        int contador = 0;

        while (contador < 10) {
            System.out.println("While: " + contador);
            contador++;

            if (contador == 4) {
                break;   // se sale con contador = 4; escribe 0, 1, 2, 3
            }
        }


        // ===== 5. continue en un while: cuidado con el contador =====

        // Si continue salta antes del contador++, la variable no cambia y el bucle no termina nunca.
        // Por eso el incremento va ANTES del continue.
        int n = 0;

        while (n < 5) {
            n++;

            if (n == 3) {
                continue;   // no escribe el 3
            }

            System.out.println("While con continue: " + n);   // 1, 2, 4, 5
        }


        // ===== 6. Uso práctico: buscar y parar =====

        // Primer múltiplo de 7 que hay a partir del 20
        int encontrado = -1;

        for (int i = 20; i <= 50; i++) {
            if (i % 7 == 0) {
                encontrado = i;
                break;   // ya no hace falta seguir buscando
            }
        }

        System.out.println("Primer múltiplo de 7: " + encontrado);   // 21
    }
}
