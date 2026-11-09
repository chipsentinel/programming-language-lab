public class LogicalOperators {

    public static void main(String[] args) {

        // Los operadores lógicos combinan o modifican expresiones boolean (verdadero o falso)

        int numero1 = 40;
        int numero2 = 90;
        int numero3 = 5;
        boolean resultado;


        // ===== 1. AND (&&): se cumplen las dos condiciones =====

        resultado = numero1 < numero2 && numero1 < numero3;   // true && false
        System.out.println("AND (40 < 90 y 40 < 5): " + resultado);   // false

        numero3 = 50;
        resultado = numero1 < numero2 && numero1 < numero3;   // true && true
        System.out.println("AND (40 < 90 y 40 < 50): " + resultado);  // true


        // ===== 2. OR (||): se cumple al menos una condición =====

        resultado = numero1 < numero2 || numero1 < numero3;   // true || true
        System.out.println("OR (40 < 90 o 40 < 50): " + resultado);    // true

        numero1 = 100;
        resultado = numero1 < numero2 || numero1 < numero3;   // false || false
        System.out.println("OR (100 < 90 o 100 < 50): " + resultado);  // false


        // ===== 3. NOT (!): invierte el valor =====

        numero1 = 40;
        numero3 = 5;
        resultado = numero1 < numero2 && numero1 < numero3;   // false
        System.out.println("Sin negar: " + resultado);        // false

        resultado = !(numero1 < numero2 && numero1 < numero3);
        System.out.println("Negado con !: " + resultado);     // true


        // ===== 4. Tablas de verdad =====

        System.out.println("true && true   = " + (true && true));     // true
        System.out.println("true && false  = " + (true && false));    // false
        System.out.println("false || true  = " + (false || true));    // true
        System.out.println("false || false = " + (false || false));   // false
        System.out.println("!true          = " + !true);              // false


        // ===== 5. Evaluación en cortocircuito =====

        // && y || dejan de evaluar en cuanto saben el resultado
        int divisor = 0;
        boolean seguro = divisor != 0 && 10 / divisor > 1;   // 10 / 0 nunca se calcula
        System.out.println("Sin error gracias al cortocircuito: " + seguro);   // false


        // ===== 6. Orden de evaluación =====

        // ! primero, después &&, y al final ||
        System.out.println(true || false && false);     // true: se evalúa false && false primero
        System.out.println((true || false) && false);   // false: los paréntesis cambian el orden
    }
}
