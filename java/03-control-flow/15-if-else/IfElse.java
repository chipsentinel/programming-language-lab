public class IfElse {

    public static void main(String[] args) {

        // ===== 1. if: ejecutar código solo si se cumple una condición =====

        // Una condicional ejecuta un bloque de código u otro según se cumpla o no una condición.
        // La condición va entre paréntesis y debe dar un boolean; el bloque va entre llaves.

        int edad = 20;
        if (edad > 18) {
            System.out.println("Es mayor");
        }


        // ===== 2. if y else =====

        // Si la condición no se cumple y no hay else, no pasa nada.
        // else define qué hacer en ese caso.

        if (edad < 18) {
            System.out.println("Es menor");
        } else {
            System.out.println("No es menor");
        }


        // ===== 3. else if: varias condiciones =====

        System.out.println("Nota final del alumno");

        int nota = 80;

        // Se evalúan en orden; en cuanto una se cumple, el resto se salta
        if (nota >= 70) {
            System.out.println("Felicidades, pasaste el examen con: " + nota);
        } else if (nota < 70 && nota >= 50) {
            System.out.println("Debes repetir el examen, sacaste: " + nota);
        } else {
            System.out.println("Has reprobado el examen, sacaste: " + nota);
        }


        // ===== 4. El mismo código con otros valores =====

        nota = 65;

        if (nota >= 70) {
            System.out.println("Felicidades, pasaste el examen con: " + nota);
        } else if (nota < 70 && nota >= 50) {
            System.out.println("Debes repetir el examen, sacaste: " + nota);
        } else {
            System.out.println("Has reprobado el examen, sacaste: " + nota);
        }

        nota = 40;

        if (nota >= 70) {
            System.out.println("Felicidades, pasaste el examen con: " + nota);
        } else if (nota < 70 && nota >= 50) {
            System.out.println("Debes repetir el examen, sacaste: " + nota);
        } else {
            System.out.println("Has reprobado el examen, sacaste: " + nota);
        }


        // ===== 5. Cuidado con los límites =====

        // Con nota > 70 y nota < 70, el valor 70 no entra en ninguna condición y cae en el else.
        // Por eso se usa >= en uno de los dos lados.
        nota = 70;

        if (nota > 70) {
            System.out.println("Con >: pasaste");
        } else {
            System.out.println("Con >: 70 no pasa, cae en el else");
        }

        if (nota >= 70) {
            System.out.println("Con >=: pasaste");
        }


        // ===== 6. Un else if no necesita repetir el límite superior =====

        // Como el primer if ya descartó nota >= 70, aquí nota ya es menor que 70
        nota = 65;

        if (nota >= 70) {
            System.out.println("Aprobado");
        } else if (nota >= 50) {
            System.out.println("Recuperación");
        } else {
            System.out.println("Suspenso");
        }


        // ===== 7. Con textos =====

        // Los textos se comparan con equals, no con ==
        String nombre = "Sol";

        if (nombre.equals("Sol")) {
            System.out.println("Hola, Sol");
        } else {
            System.out.println("Hola, desconocido");
        }
    }
}
