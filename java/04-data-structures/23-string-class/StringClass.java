public class StringClass {

    public static void main(String[] args) {

        // String es una clase: representa una secuencia de caracteres y trae métodos para trabajar con texto.


        // ===== 1. Texto en varias líneas =====

        // Opción 1: concatenar con + y \n para el salto de línea
        String lineasConcatenadas = "Esta es la primera línea\n" +
                                    "Esta es la segunda línea\n" +
                                    "Esta es la tercera línea";
        System.out.println(lineasConcatenadas);

        // Opción 2: bloque de texto con """ (Java 15 o superior): respeta los saltos de línea
        String lineasLiterales = """
                Esta es la primera línea
                Esta es la segunda línea
                Esta es la tercera línea""";
        System.out.println(lineasLiterales);

        // Caracteres especiales dentro de un texto (secuencias de escape)
        System.out.println("Comillas: \"mundo\"");     // \"  comillas
        System.out.println("Ruta: C:\\Datos\\foto");   // \\  barra invertida
        System.out.println("Col1\tCol2");              // \t  tabulador


        // ===== 2. Longitud: length() =====

        String texto = "Hola a todos";

        System.out.println("Texto: " + texto);
        System.out.println("Cantidad de caracteres: " + texto.length());   // 12 (los espacios también cuentan)

        // Un texto de solo espacios también tiene longitud
        System.out.println("    ".length());   // 4


        // ===== 3. Mayúsculas y minúsculas =====

        System.out.println("En mayúsculas: " + texto.toUpperCase());   // HOLA A TODOS
        System.out.println("En minúsculas: " + texto.toLowerCase());   // hola a todos


        // ===== 4. Buscar: contains, startsWith, endsWith =====

        // Devuelven true o false, y distinguen mayúsculas de minúsculas
        System.out.println("Contiene \"mundo\": " + texto.contains("mundo"));       // false
        System.out.println("Contiene \"todos\": " + texto.contains("todos"));       // true
        System.out.println("Inicia con \"hola\": " + texto.startsWith("hola"));     // false (minúscula)
        System.out.println("Inicia con \"Hola\": " + texto.startsWith("Hola"));     // true
        System.out.println("Finaliza con \"todos\": " + texto.endsWith("todos"));   // true


        // ===== 5. Posición: indexOf =====

        // Cada carácter tiene un índice, y el primero es el 0:
        // H o l a _ a _ t o d o s
        // 0 1 2 3 4 5 6 7 8 9 10 11
        System.out.println("Posición de \"do\": " + texto.indexOf("do"));   // 9
        System.out.println("Posición de \"la\": " + texto.indexOf("la"));   // 2
        System.out.println("Posición de \"tr\": " + texto.indexOf("tr"));   // -1: no está

        // Un solo carácter, por su índice
        System.out.println("Primer carácter: " + texto.charAt(0));   // H


        // ===== 6. Extraer una parte: substring =====

        // substring(inicio, fin): desde la posición 2 hasta la 6 (sin incluir la 6)
        // OJO: en C# el segundo número es la cantidad; en Java es la posición final
        System.out.println("substring(2, 6): \"" + texto.substring(2, 6) + "\"");   // "la a"
        System.out.println("substring(1, 5): \"" + texto.substring(1, 5) + "\"");   // "ola "

        // Con un solo número, hasta el final
        System.out.println("substring(5): \"" + texto.substring(5) + "\"");         // "a todos"


        // ===== 7. Reemplazar: replace =====

        System.out.println("Replace: " + texto.replace("a todos", "mundo"));   // Hola mundo


        // ===== 8. Quitar espacios: trim =====

        String conEspacios = "   Hola a todos   ";

        System.out.println("Con espacios: " + conEspacios.length() + " caracteres");          // 18
        System.out.println("Con trim: " + conEspacios.trim().length() + " caracteres");       // 12
        System.out.println("Entre corchetes: [" + conEspacios.trim() + "]");                  // [Hola a todos]


        // ===== 9. Los strings no cambian =====

        // Estos métodos devuelven un texto nuevo; el original sigue igual
        texto.toUpperCase();   // el resultado se pierde si no se guarda
        System.out.println(texto);   // Hola a todos

        String mayusculas = texto.toUpperCase();   // así sí se conserva
        System.out.println(mayusculas);            // HOLA A TODOS


        // ===== 10. Encadenar métodos =====

        // Se aplican de izquierda a derecha, y el resultado final es el de la última
        System.out.println(conEspacios.trim().toUpperCase());   // HOLA A TODOS
    }
}
