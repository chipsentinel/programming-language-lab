public class ComparisonOperators {

    public static void main(String[] args) {

        // Los operadores de comparación comparan dos valores y dan un resultado boolean: true o false.
        // Con el operador ternario (condición ? siSeCumple : siNoSeCumple) se convierte en un texto.

        int a = 10;
        int b = 20;
        String resultado;


        // ===== 1. Mayor que (>) =====

        resultado = (a > b) ? "Sí" : "No";
        System.out.println("¿a es mayor que b? " + resultado);   // No

        b = 2;
        resultado = (a > b) ? "Sí" : "No";
        System.out.println("¿a es mayor que b? " + resultado);   // Sí


        // ===== 2. Menor que (<) =====

        resultado = (a < b) ? "Sí" : "No";
        System.out.println("¿a es menor que b? " + resultado);   // No


        // ===== 3. Igual que (==) =====

        // Dos signos de igual comparan; uno solo (=) asigna
        resultado = (a == b) ? "Sí" : "No";
        System.out.println("¿a es igual a b? " + resultado);   // No

        b = 10;
        resultado = (a == b) ? "Sí" : "No";
        System.out.println("¿a es igual a b? " + resultado);   // Sí


        // ===== 4. Mayor o igual que (>=) =====

        resultado = (a >= b) ? "Sí" : "No";
        System.out.println("¿a es mayor o igual que b? " + resultado);   // Sí

        a = 1;
        resultado = (a >= b) ? "Sí" : "No";
        System.out.println("¿a es mayor o igual que b? " + resultado);   // No


        // ===== 5. Menor o igual que (<=) =====

        resultado = (a <= b) ? "Sí" : "No";
        System.out.println("¿a es menor o igual que b? " + resultado);   // Sí


        // ===== 6. Distinto de (!=) =====

        resultado = (a != b) ? "Sí" : "No";
        System.out.println("¿a es distinto de b? " + resultado);   // Sí

        a = 10;
        resultado = (a != b) ? "Sí" : "No";
        System.out.println("¿a es distinto de b? " + resultado);   // No


        // ===== 7. El resultado es un boolean =====

        boolean esMayor = a > b;
        System.out.println(esMayor);          // false
        System.out.println(a >= b);           // true


        // ===== 8. Comparar textos =====

        // En Java == compara referencias, no el contenido: para textos se usa equals
        String nombre = "Sol";
        System.out.println(nombre.equals("Sol"));   // true
        System.out.println(nombre.equals("sol"));   // false: distingue mayúsculas y minúsculas
    }
}
