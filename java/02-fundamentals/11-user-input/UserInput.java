import java.util.Scanner;

public class UserInput {

    public static void main(String[] args) {

        // ===== 1. Pedir un texto =====

        // Entrada de datos: el programa recibe información externa, normalmente del usuario.
        // System.out.println solo muestra un mensaje; para recoger lo que se escribe hace falta Scanner.
        Scanner scanner = new Scanner(System.in);

        System.out.println("==============================");
        System.out.println("Introduzca sus datos");
        System.out.println("==============================");

        System.out.println("Introduzca su nombre...");
        String nombre = scanner.nextLine();   // espera a que se pulse Enter


        // ===== 2. Mostrar lo que se ha escrito =====

        System.out.println("Nombre colocado: " + nombre);


        // ===== 3. Pedir un número entero =====

        // nextLine siempre devuelve texto: hay que convertirlo (casting, lección anterior)
        System.out.println("Introduzca su edad...");
        int edad = Integer.parseInt(scanner.nextLine());
        System.out.println("Edad colocada: " + edad);


        // ===== 4. Pedir un número decimal =====

        // Double.parseDouble exige punto como separador decimal
        System.out.println("Indique su salario...");
        double salario = Double.parseDouble(scanner.nextLine());
        System.out.println("Salario colocado: " + salario);


        // ===== 5. Usar los datos =====

        System.out.println("==============================");
        System.out.println(nombre + " tiene " + edad + " años y cobra " + salario);
        System.out.println("En 10 años tendrá " + (edad + 10));
        System.out.println("==============================");

        // Si el texto no es un número válido, parseInt lanza NumberFormatException
        // (con try/catch se evita; se verá con las excepciones)

        scanner.close();
    }
}
