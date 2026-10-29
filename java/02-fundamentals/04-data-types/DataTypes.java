import java.math.BigDecimal;

public class DataTypes {
    public static void main(String[] args) {
        // Tipos de datos básicos

        // Enteros
        byte nivel = 127;
        short anio = 2025;
        int edad = 25;
        long poblacion = 8000000000L;

        // Decimales
        float temperatura = 36.6f;
        double pi = 3.14159;
        BigDecimal precio = new BigDecimal("19.99");

        // Lógico
        boolean esMayorDeEdad = true;

        // Texto
        char letra = 'S';
        String nombre = "Sol";

        // Mostrar valores
        System.out.println("\nMostrar valores".toUpperCase());
        System.out.println("byte nivel = " + nivel);
        System.out.println("short anio = " + anio);
        System.out.println("int edad = " + edad);
        System.out.println("long poblacion = " + poblacion);
        System.out.println("float temperatura = " + temperatura);
        System.out.println("double pi = " + pi);
        System.out.println("BigDecimal precio = " + precio);
        System.out.println("boolean esMayorDeEdad = " + esMayorDeEdad);
        System.out.println("char letra = " + letra);
        System.out.println("String nombre = " + nombre);

        // Mostrar el tipo y los límites
        System.out.println("\nMostrar el tipo y los límites".toUpperCase());
        System.out.println("Tipo de nivel: " + ((Object) nivel).getClass().getName());
        System.out.println("Tipo de anio: " + ((Object) anio).getClass().getName());
        System.out.println("Tipo de edad: " + ((Object) edad).getClass().getName());
        System.out.println("Tipo de poblacion: " + ((Object) poblacion).getClass().getName());
        System.out.println("Tipo de temperatura: " + ((Object) temperatura).getClass().getName());
        System.out.println("Tipo de pi: " + ((Object) pi).getClass().getName());
        System.out.println("Tipo de precio: " + precio.getClass().getName());
        System.out.println("Tipo de esMayorDeEdad: " + ((Object) esMayorDeEdad).getClass().getName());
        System.out.println("Tipo de letra: " + ((Object) letra).getClass().getName());
        System.out.println("Tipo de nombre: " + nombre.getClass().getName());

        System.out.println("\nValor mínimo a valor máximo".toUpperCase());
        System.out.println("byte:    " + Byte.MIN_VALUE + " a " + Byte.MAX_VALUE);
        System.out.println("short:   " + Short.MIN_VALUE + " a " + Short.MAX_VALUE);
        System.out.println("int:     " + Integer.MIN_VALUE + " a " + Integer.MAX_VALUE);
        System.out.println("long:    " + Long.MIN_VALUE + " a " + Long.MAX_VALUE);
    }
}
