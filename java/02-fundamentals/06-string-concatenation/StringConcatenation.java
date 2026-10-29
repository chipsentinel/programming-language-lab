public class StringConcatenation {
    public static void main(String[] args) {
        String nombre = "Sol";
        int edad = 30;

        System.out.println("Nombre de usuario: " + nombre + ", edad: " + edad);

        System.out.println("Nombre de usuario: %s, edad: %d".formatted(nombre, edad));

        System.out.println("Nombre de usuario: " + nombre + " \nEdad: " + edad);

        System.out.print("Nombre usuario: " + nombre);
        System.out.println(", edad: " + edad);

        System.out.println("Nombre de usuario: " + nombre + ", edad: " + (edad + 5));
    }
}
