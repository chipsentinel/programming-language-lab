public class TypeCasting {

    public static void main(String[] args) {

        // ===== 1. Casting implícito =====

        // Casting: convertir un valor de un tipo de dato a otro.
        // Implícito: el compilador lo hace solo cuando no se pierde información.

        int valor = 200;
        int total = valor;                 // mismo tipo: no hay conversión
        System.out.println("Casting implícito: " + total);

        double totalDecimal = valor;       // int -> double: implícito
        System.out.println("Casting implícito: " + totalDecimal);   // 200.0


        // ===== 2. Casting explícito =====

        // Explícito: hay que pedirlo con (tipo) porque se puede perder información.
        double precio = 500.23;

        // int descuento = precio;         // error: no se convierte double a int solo
        int descuento = (int) precio;      // se descartan los decimales, no se redondea
        System.out.println("Casting explícito: " + descuento);   // 500

        // Trunca hacia cero, también con negativos
        double temperatura = -7.9;
        System.out.println((int) temperatura);   // -7, no -8


        // ===== 3. De char a int y de int a char =====

        char letra = 'A';
        int codigoAscii = letra;           // char -> int: implícito
        System.out.println("Código ASCII de " + letra + ": " + codigoAscii);   // 65

        char letraDeVuelta = (char) codigoAscii;   // int -> char: explícito
        System.out.println("Letra del código " + codigoAscii + ": " + letraDeVuelta);   // A


        // ===== 4. De texto a número: Integer.parseInt =====

        String palabra = "123456";

        // int numero = palabra;           // error: un String no se convierte a int solo
        int numero = Integer.parseInt(palabra);
        System.out.println("Texto a entero: " + numero);   // 123456

        // Si el texto no es un número válido, falla en ejecución
        // Integer.parseInt("hola");       // NumberFormatException


        // ===== 5. De texto a decimal: Double.parseDouble =====

        String textoDecimal = "150.60";

        // Java siempre usa el punto como separador decimal, sin depender del idioma del equipo
        double valor3 = Double.parseDouble(textoDecimal);
        System.out.println("Texto a double: " + valor3);   // 150.6


        // ===== 6. De número a texto =====

        int edad = 30;
        String edadTexto = String.valueOf(edad);
        System.out.println("Edad: " + edadTexto);

        double pi = 3.1416;
        System.out.println(Double.toString(pi));   // 3.1416


        // ===== 7. Texto que no es un número =====

        // Java no tiene TryParse: parseInt lanza NumberFormatException.
        // Las excepciones se capturan con try/catch (se verán más adelante).
        String entrada = "hola";

        try {
            int resultado = Integer.parseInt(entrada);
            System.out.println("Convertido: " + resultado);
        } catch (NumberFormatException e) {
            System.out.println("\"" + entrada + "\" no es un número entero");
        }
    }
}
