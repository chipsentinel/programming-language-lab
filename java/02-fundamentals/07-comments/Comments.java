public class Comments {
    public static void main(String[] args) {
        String nombre = "Sol";
        int edad = 30;

        // Salida
        System.out.println("Nombre de usuario: " + nombre + ", edad: " + edad);
        System.out.println("Nombre de usuario: %s, edad: %d".formatted(nombre, edad));
        System.out.println("Nombre de usuario: " + nombre + " \nEdad: " + edad);

        System.out.print("Nombre usuario: " + nombre);
        System.out.println(", edad: " + edad);


        // ===== 1. Una línea: // =====

        // En su propia línea
        int anio = 2025;   // al final de una línea

        // Varias líneas
        // con // en cada una
        // como esta


        // ===== 2. Varias líneas: /* */ =====

        /*
           Este comentario ocupa
           varias líneas.
        */

        int total = 10 /* valor inicial */ + 5;   // en medio de una línea


        // ===== 3. Documentación Javadoc: /** */ =====

        // El método sumar está debajo de main, con su comentario Javadoc
        System.out.println("Suma: " + sumar(2, 3));


        // ===== 4. Desactivar código =====

        // Con //
        // System.out.println("Nombre de usuario: " + nombre + ", edad: " + (edad + 5));

        // Con /* */
        /*
        System.out.println("Nombre de usuario: " + nombre + ", edad: " + edad);
        System.out.println("Nombre de usuario: %s, edad: %d".formatted(nombre, edad));
        */

        // Con if (false): el código se compila pero no se ejecuta (admite /* */ dentro)
        if (false) {
            System.out.println("Esto no se ejecuta");
            /* incluso con comentarios de bloque dentro */
        }


        // ===== 5. Organizar =====

        // Etiquetas para dejar avisos
        // TODO: pedir estos datos por teclado
        // FIXME: falla si el nombre está vacío
        // NOTE: este valor es provisional
        // HACK: solución temporal

        // Plegar un bloque en IntelliJ IDEA
        // region Datos
        String ciudad = "Madrid";
        int espera = 30;   // segundos
        // endregion

        System.out.println(anio + " " + total + " " + ciudad + " " + espera);


        // ===== 6. Un // dentro de un texto no es un comentario =====

        System.out.println("Visita https://ejemplo.com");
        System.out.println("// esto no es un comentario");
    }

    /**
     * Suma dos números enteros.
     *
     * @param a primer número
     * @param b segundo número
     * @return la suma de a y b
     */
    static int sumar(int a, int b) {
        return a + b;
    }
}
