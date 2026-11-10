import java.util.Scanner;

// Proyecto: cajero automático.
// Pone en práctica lo visto hasta ahora: clases, atributos, métodos con y sin retorno,
// entrada de datos, conversión de tipos, if/else, switch y bucles do-while.
//
// La clase con main va primero; la clase CajeroAutomatico está al final del archivo.

public class AtmProject {

    public static void main(String[] args) {
        CajeroAutomatico cajero = new CajeroAutomatico();
        cajero.menu();
    }
}


class CajeroAutomatico {

    // Atributos del cajero
    private double saldo = 10000;           // saldo inicial de la cuenta
    private int operaciones = 0;            // operaciones realizadas en esta sesión
    private static final int LIMITE_OPERACIONES = 3;
    private final Scanner scanner = new Scanner(System.in);


    // ===== Menú principal =====

    public void menu() {
        String opcion;

        // do-while: el menú se muestra al menos una vez y se repite hasta elegir "4"
        do {
            limpiarPantalla();

            System.out.println("""
                    ==== SELECCIONE LA OPERACIÓN ====
                    1. Consultar saldo
                    2. Depositar
                    3. Retirar
                    4. Salir
                    =================================""");
            System.out.println("Seleccione una opción:");

            // nextLine devuelve texto, y la opción se compara con textos ("1", "2"...)
            opcion = scanner.nextLine();

            switch (opcion) {
                case "1":
                    consultarSaldo();
                    break;
                case "2":
                    depositar();
                    break;
                case "3":
                    retirar();
                    break;
                case "4":
                    salir();
                    break;
                default:
                    System.out.println("OPCIÓN NO VÁLIDA. Presione Enter para continuar...");
                    scanner.nextLine();
                    break;
            }
        } while (!opcion.equals("4"));
    }


    // ===== Operaciones =====

    private void consultarSaldo() {
        limpiarPantalla();
        System.out.println("Saldo actual: " + String.format("%.2f", saldo));

        finalizarOperacion();
    }

    private void depositar() {
        double monto;

        // Se vuelve a pedir el monto mientras no sea válido
        do {
            System.out.println("Ingrese el monto a depositar:");
            monto = leerNumero();

            if (monto <= 0) {
                System.out.println("Monto inválido: debe ser mayor que cero. Intente de nuevo.");
            }
        } while (monto <= 0);

        saldo += monto;
        System.out.println("Depósito exitoso. Nuevo saldo: " + String.format("%.2f", saldo));

        finalizarOperacion();
    }

    private void retirar() {
        double retiro;
        boolean valido;

        do {
            System.out.println("Ingrese el monto a retirar:");
            retiro = leerNumero();
            valido = false;

            if (retiro <= 0) {
                System.out.println("Monto inválido: debe ser mayor que cero. Intente de nuevo.");
            } else if (retiro > saldo) {
                System.out.println("Fondos insuficientes. Intente de nuevo.");
            } else {
                valido = true;
            }
        } while (!valido);

        saldo -= retiro;
        System.out.println("Retiro completo. Nuevo saldo: " + String.format("%.2f", saldo));

        finalizarOperacion();
    }

    private void salir() {
        System.out.println("==============================");
        System.out.println("Gracias por usar nuestros servicios");
        System.out.println("==============================");

        System.exit(0);   // cierra el programa
    }


    // ===== Métodos auxiliares =====

    // Cuenta la operación y pregunta si se quiere hacer otra
    private void finalizarOperacion() {
        operaciones++;

        // Ejercicio propuesto: el cliente solo puede hacer un número limitado de operaciones
        if (operaciones >= LIMITE_OPERACIONES) {
            System.out.println("LÍMITE DE OPERACIONES PERMITIDAS ALCANZADO");
            salir();
        }

        System.out.println("¿Desea realizar otra operación? (1 = Sí / 2 = No)");
        String respuesta = scanner.nextLine();

        if (!respuesta.equals("1")) {
            salir();
        }
        // Si responde 1, el método termina y el menú vuelve a mostrarse
    }

    // Convierte lo que escribe el usuario en número; si no es un número, devuelve 0 (monto inválido).
    // try/catch captura el error de la conversión: se verá con las excepciones (lección 41).
    private double leerNumero() {
        try {
            return Double.parseDouble(scanner.nextLine());
        } catch (NumberFormatException e) {
            return 0;
        }
    }

    private void limpiarPantalla() {
        // Secuencia ANSI que borra la consola (Java no tiene un método para ello)
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }
}
