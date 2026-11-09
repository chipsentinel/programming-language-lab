import java.util.Scanner;

public class ForLoop {

    public static void main(String[] args) {

        // ===== 1. Bucle for =====

        // for reúne en una sola línea las tres partes de un bucle con contador:
        // for (inicialización; condición; actualización)

        for (int i = 0; i < 4; i++) {
            System.out.println("Número de vuelta: " + i);
        }

        System.out.println("Fin del bucle");

        // i es la variable de control; por costumbre se llama i, j, k
        // Se lee: "para i empezando en 0, mientras i sea menor que 4, sumando 1 en cada vuelta"
        // El número que aparece en la condición (4) indica cuántas vueltas da el bucle


        // ===== 2. Empezar en 1 e incluir el límite =====

        for (int i = 1; i <= 5; i++) {
            System.out.println("Vuelta " + i);
        }


        // ===== 3. Cambiar el paso =====

        // i += 2 salta de dos en dos: da menos vueltas
        for (int i = 1; i <= 5; i += 2) {
            System.out.println("Impar: " + i);   // 1, 3, 5
        }


        // ===== 4. Cuenta atrás =====

        for (int i = 3; i >= 1; i--) {
            System.out.println("Faltan " + i);
        }

        System.out.println("¡Despegue!");


        // ===== 5. Decisiones dentro del bucle =====

        for (int i = 1; i <= 5; i++) {
            if (i == 4) {
                System.out.println("Soy la vuelta cuatro");
            } else {
                System.out.println("Vuelta " + i);
            }
        }


        // ===== 6. Tabla de multiplicar =====

        final int TABLA = 8;

        System.out.println("Tabla del " + TABLA);

        for (int i = 1; i <= 10; i++) {
            System.out.println(TABLA + " x " + i + " = " + TABLA * i);
        }


        // ===== 7. La variable del bucle solo existe dentro de él =====

        // System.out.println(i);   // error: i no existe fuera del for


        // ===== 8. El usuario decide cuántas vueltas =====

        Scanner scanner = new Scanner(System.in);

        System.out.println("Indique la cantidad de vueltas...");
        int vueltas = Integer.parseInt(scanner.nextLine());

        for (int i = 1; i <= vueltas; i++) {
            System.out.println("Número de vuelta: " + i);
        }

        scanner.close();
    }
}
