import java.util.Scanner;

public class DoWhileLoop {

    public static void main(String[] args) {

        // ===== 1. Bucle do-while =====

        // do { ... } while (condición);
        // Ejecuta el bloque UNA VEZ como mínimo y después repite mientras la condición sea true.
        // La condición se comprueba al FINAL de cada vuelta.

        int i = 1;

        do {
            System.out.println("Vuelta " + i);
            i++;
        } while (i <= 3);

        System.out.println("Fin del bucle");


        // ===== 2. Se ejecuta al menos una vez =====

        // Aunque la condición sea falsa desde el principio, el bloque corre una vez
        int contador = 10;

        do {
            System.out.println("do-while: se ejecuta una vez con contador = " + contador);
            contador++;
        } while (contador < 3);

        // Con while, en cambio, el bloque no se ejecutaría ninguna vez
        while (contador < 3) {
            System.out.println("while: esto no se imprime");
        }


        // ===== 3. Pedir datos hasta que el usuario escriba 0 =====

        Scanner scanner = new Scanner(System.in);

        // La variable se declara ANTES del bucle: la condición está fuera del bloque do { }
        // y no vería una variable declarada dentro.
        int numero;

        do {
            System.out.println("Introduce 0 para salir");
            numero = Integer.parseInt(scanner.nextLine());
            System.out.println("Número colocado: " + numero);
        } while (numero != 0);

        System.out.println("Fin del programa");


        // ===== 4. Mismo resultado con while =====

        // Con while hay que dar un valor inicial a la variable para poder entrar al bucle
        // (aquí el valor de salida es 3 en lugar de 0)
        numero = 0;

        while (numero != 3) {
            System.out.println("Introduce el número 3 para salir");
            numero = Integer.parseInt(scanner.nextLine());
            System.out.println("Número colocado: " + numero);
        }

        System.out.println("Fin del programa");


        // Si se escribe un texto que no es un número, parseInt lanza una excepción (NumberFormatException)

        scanner.close();
    }
}
