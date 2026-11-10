// Una excepción es un error que ocurre MIENTRAS el programa se ejecuta (dividir entre cero,
// convertir un texto que no es un número, usar una posición que no existe...).
// Si no se controla, interrumpe el programa. Con try / catch se captura y se decide qué hacer.
//
// try      { código que puede fallar }
// catch    { qué hacer si falla }
// finally  { código que se ejecuta SIEMPRE, haya error o no }
//
// La clase con main va primero; ManejoExcepciones está al final del archivo.

public class ExceptionHandling {

    public static void main(String[] args) {

        ManejoExcepciones log = new ManejoExcepciones();

        log.dividir(20, 10);
        log.dividir(20, 0);

        log.formato("8");
        log.formato("T");

        log.arrayError();

        log.procesar("100", 5);
        log.procesar("abc", 5);
        log.procesar("100", 0);

        try {
            log.validarEdad(30);
            log.validarEdad(-5);
        } catch (IllegalArgumentException error) {
            System.out.println("Error: " + error.getMessage());
        }

        // Una excepción sin controlar detiene el programa:
        // int cero = 0;
        // System.out.println(10 / cero);   // Exception in thread "main" java.lang.ArithmeticException: / by zero
    }
}


class ManejoExcepciones {

    // ===== 1. try / catch con una excepción concreta =====

    public void dividir(int valor1, int valor2) {
        try {
            int resultado = valor1 / valor2;      // con valor2 = 0 lanza ArithmeticException
            System.out.println("Resultado: " + resultado);
        } catch (ArithmeticException error) {
            System.out.println("Error: no se puede dividir entre cero");
            // error contiene los detalles: tipo, mensaje y pila de llamadas
            System.out.println("[técnico] " + error.getClass().getSimpleName() + ": " + error.getMessage());
        }
    }


    // ===== 2. Error de formato =====

    public void formato(String texto) {
        try {
            int numero = Integer.parseInt(texto);    // con un texto que no es un número lanza NumberFormatException
            System.out.println("Número ingresado: " + numero);
        } catch (NumberFormatException error) {
            System.out.println("Error: '" + texto + "' no es un número entero");
            System.out.println("[técnico] " + error.getClass().getSimpleName());
        }
    }


    // ===== 3. Posición fuera del array =====

    public void arrayError() {
        int[] numeros = { 10, 20, 30, 40 };       // posiciones válidas: 0 a 3

        try {
            System.out.println(java.util.Arrays.toString(numeros).replace("[", "").replace("]", ""));
            System.out.println(numeros[5]);        // no existe: lanza ArrayIndexOutOfBoundsException
        } catch (ArrayIndexOutOfBoundsException error) {
            System.out.println("Error: posición fuera del rango del array");
            System.out.println("[técnico] " + error.getClass().getSimpleName());
        }
    }


    // ===== 4. Varios catch y finally =====

    public void procesar(String texto, int divisor) {
        try {
            int numero = Integer.parseInt(texto);
            System.out.println(numero + " / " + divisor + " = " + (numero / divisor));
        }
        // Se evalúan en orden: el primero que coincide se ejecuta.
        // Lo más específico va primero y Exception (la general) al final.
        catch (NumberFormatException error) {
            System.out.println("Error de formato");
        } catch (ArithmeticException error) {
            System.out.println("Error: división entre cero");
        } catch (Exception error) {
            System.out.println("Error inesperado: " + error.getMessage());
        } finally {
            // Se ejecuta siempre: ideal para cerrar archivos, conexiones...
            System.out.println("Fin de la operación");
        }
    }


    // ===== 5. Lanzar una excepción propia: throw =====

    public void validarEdad(int edad) {
        if (edad < 0) {
            throw new IllegalArgumentException("La edad no puede ser negativa");
        }

        System.out.println("Edad válida: " + edad);
    }
}
