public class SwitchExpressions {

    public static void main(String[] args) {

        // ===== 1. Repaso: el switch clásico es una instrucción =====

        // switch (dia) { case 1: ... break; } ejecuta código, pero ocupa mucho espacio:
        // un case y un break por cada valor.
        // La expresión switch hace lo mismo y además devuelve un valor, en mucho menos código.


        // ===== 2. Expresión switch con valores =====

        // Una expresión switch devuelve un valor: se guarda directamente en una variable.
        // Cada rama es: valor -> resultado; no necesita break.

        int dia = 3;

        String nombreDia = switch (dia) {
            case 1 -> "Lunes";
            case 2 -> "Martes";
            case 3 -> "Miércoles";
            case 4 -> "Jueves";
            case 5 -> "Viernes";
            case 6 -> "Sábado";
            case 7 -> "Domingo";
            default -> "Día no válido";   // equivale al else final
        };

        System.out.println(nombreDia);   // Miércoles


        // ===== 3. Rangos =====

        // Java 17 no admite rangos como ">= 70" dentro de un case.
        // Se agrupan valores con una división: nota / 10 da la decena (80 -> 8, 65 -> 6).
        int nota = 80;

        String resultado = switch (nota / 10) {
            case 10, 9, 8, 7 -> "Pasaste el examen";          // 70 a 109
            case 6, 5 -> "Debes repetir el examen";           // 50 a 69
            default -> "Has reprobado el examen";
        };

        System.out.println("Nota " + nota + ": " + resultado);   // Pasaste el examen

        nota = 65;
        resultado = switch (nota / 10) {
            case 10, 9, 8, 7 -> "Pasaste el examen";
            case 6, 5 -> "Debes repetir el examen";
            default -> "Has reprobado el examen";
        };
        System.out.println("Nota " + nota + ": " + resultado);   // Debes repetir el examen

        nota = 40;
        resultado = switch (nota / 10) {
            case 10, 9, 8, 7 -> "Pasaste el examen";
            case 6, 5 -> "Debes repetir el examen";
            default -> "Has reprobado el examen";
        };
        System.out.println("Nota " + nota + ": " + resultado);   // Has reprobado el examen


        // ===== 4. Rango cerrado =====

        nota = 64;

        resultado = switch (nota / 10) {
            case 10, 9, 8 -> "Pasaste el examen";
            case 6 -> "Repetir examen";            // 60 a 69
            default -> "Reprobaste el examen";
        };

        System.out.println("Nota " + nota + ": " + resultado);   // Repetir examen


        // ===== 5. Varios valores en un mismo case =====

        dia = 6;

        String tipoDia = switch (dia) {
            case 6, 7 -> "Fin de semana";
            case 1, 2, 3, 4, 5 -> "Día laborable";
            default -> "Día no válido";
        };

        System.out.println(tipoDia);   // Fin de semana


        // ===== 6. Una rama puede tener varias instrucciones: yield =====

        dia = 7;

        String mensaje = switch (dia) {
            case 6, 7 -> {
                String texto = "Hoy descansas";
                yield texto.toUpperCase();     // yield devuelve el valor del bloque
            }
            default -> "A trabajar";
        };

        System.out.println(mensaje);   // HOY DESCANSAS
    }
}
