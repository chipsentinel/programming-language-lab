public class Constants {

    // Las constantes de clase van fuera de main: static final
    static final double PI = 3.1416;
    static final String MENSAJE_BIENVENIDA = "¡Bienvenido!";
    static final int DIAS = 7;
    static final String NOMBRE = "Sol";
    static final double IVA = 0.21;

    public static void main(String[] args) {

        // ===== 1. Repaso: var fija el tipo con el primer valor =====

        // Una variable solo se declara una vez; no se puede repetir el nombre
        /*
        var numero = 50;
        var numero = "hola";
        var numero = 5.2;
        */

        var numero = 10.20;   // el compilador deduce double
        System.out.println(numero);

        numero = 4000;        // válido: int -> double es implícito
        System.out.println(numero);

        // No compila: numero es double y un String no se convierte a double
        // numero = "hola";

        // Curiosidad: un char sí se convierte a double, y guarda su código Unicode
        numero = 's';
        System.out.println(numero);   // 115.0, no "s"


        // ===== 2. Constantes =====

        final int numero2 = 44;
        System.out.println("Valor de constante: " + numero2);

        // numero2 = 80;   // error: no se puede asignar a una variable final

        // Se usan para valores inalterables, como Pi
        System.out.println(PI);
        System.out.println(MENSAJE_BIENVENIDA);


        // ===== 3. Ejemplo con constantes =====

        double radio = 5;
        double area = PI * radio * radio;

        double precio = 100;
        double total = precio * (1 + IVA);

        System.out.println(NOMBRE + ": una semana tiene " + DIAS + " días");
        System.out.println("Área del círculo de radio " + radio + ": " + area);
        System.out.println("Precio con IVA: " + total);
    }
}
