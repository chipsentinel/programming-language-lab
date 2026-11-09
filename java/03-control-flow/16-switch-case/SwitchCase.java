public class SwitchCase {

    public static void main(String[] args) {

        // ===== 1. switch: comparar una variable con varios casos =====

        // switch compara el valor de una variable con cada case; ejecuta el que coincide.
        // Es una alternativa más clara que una cadena larga de else if con la misma variable.

        System.out.println("Días de la semana");

        // En los nombres de variables no se usan tildes ni la ñ (dia, no día)
        int dia = 3;

        switch (dia) {
            case 1:
                System.out.println("Lunes");
                break;
            case 2:
                System.out.println("Martes");
                break;
            case 3:
                System.out.println("Miércoles");
                break;
            case 4:
                System.out.println("Jueves");
                break;
            case 5:
                System.out.println("Viernes");
                break;
            case 6:
                System.out.println("Sábado");
                break;
            case 7:
                System.out.println("Domingo");
                break;
        }

        // break termina el switch: sin él, el programa no pararía en este case


        // ===== 2. Cambiar el valor =====

        dia = 7;

        switch (dia) {
            case 1:
                System.out.println("Lunes");
                break;
            case 7:
                System.out.println("Domingo");
                break;
        }


        // ===== 3. default: ningún case coincide =====

        dia = 9;

        switch (dia) {
            case 1:
                System.out.println("Lunes");
                break;
            case 2:
                System.out.println("Martes");
                break;
            default:
                System.out.println("Día no válido");   // equivale al else final
                break;
        }


        // ===== 4. Varios case con el mismo código =====

        dia = 6;

        switch (dia) {
            case 6:
            case 7:
                System.out.println("Fin de semana");
                break;
            case 1:
            case 2:
            case 3:
            case 4:
            case 5:
                System.out.println("Día laborable");
                break;
            default:
                System.out.println("Día no válido");
                break;
        }


        // ===== 5. switch con textos =====

        String opcion = "guardar";

        switch (opcion) {
            case "abrir":
                System.out.println("Abriendo el archivo");
                break;
            case "guardar":
                System.out.println("Guardando el archivo");
                break;
            default:
                System.out.println("Opción desconocida");
                break;
        }


        // ===== 6. Java sí permite pasar de un case al siguiente =====

        // Sin break, el programa sigue ejecutando los case siguientes (fall-through).
        // Es un error frecuente; C# no lo permite.
        dia = 1;

        switch (dia) {
            case 1:
                System.out.println("Lunes");      // se ejecuta
            case 2:
                System.out.println("Martes");     // también, aunque dia no vale 2
                break;
        }
    }
}
