import java.util.Scanner;

// Un parámetro es un dato que un método recibe al ser llamado.
// Así el mismo método puede trabajar con valores distintos sin cambiar su código.
// Los valores concretos que se pasan al llamarlo se llaman argumentos.
//
// La clase con main va primero; la clase Prueba (secciones 1 a 5) está al final del archivo.

public class MethodsWithParameters {

    public static void main(String[] args) {

        Prueba objeto1 = new Prueba();

        objeto1.saludoConAtributo();           // Hola, Sol

        // Se pasa un argumento por cada parámetro, en el mismo orden y con el mismo tipo
        objeto1.saludar("Sol");                // Hola, Sol
        objeto1.saludar("Marcos");             // Hola, Marcos

        objeto1.presentar("Sol", 34);          // Nombre: Sol, edad: 34

        // Si faltan argumentos, o son de otro tipo, no compila:
        // objeto1.presentar("Sol");           // error: falta la edad
        // objeto1.presentar(34, "Sol");       // error: el orden y los tipos importan


        // ===== 6. Usar el valor devuelto =====

        int resultado = objeto1.sumar(34, 22);
        System.out.println("Suma: " + resultado);                      // 56

        // Los argumentos pueden ser variables o expresiones
        System.out.println("Suma: " + objeto1.sumar(resultado, 4));    // 60
        System.out.println("Mayor de edad (20): " + objeto1.esMayorDeEdad(20));   // true
        System.out.println("Mayor de edad (15): " + objeto1.esMayorDeEdad(15));   // false


        // ===== 7. Parámetro opcional: en Java, sobrecarga =====

        objeto1.despedir("Sol");                    // Adiós, Sol
        objeto1.despedir("Sol", "Hasta pronto");    // Hasta pronto, Sol


        // ===== 8. Un parámetro solo existe dentro de su método =====

        // El parámetro nombre de saludar no se puede usar desde main:
        // System.out.println(nombre);   // error: nombre no existe aquí


        // ===== 9. Argumentos que escribe el usuario =====

        Scanner scanner = new Scanner(System.in);

        System.out.println("Ingrese su nombre...");
        String nombreUsuario = scanner.nextLine();

        System.out.println("Ingrese su edad...");
        int edadUsuario = Integer.parseInt(scanner.nextLine());

        objeto1.presentar(nombreUsuario, edadUsuario);
        System.out.println("Suma de la edad y 10: " + objeto1.sumar(edadUsuario, 10));

        scanner.close();
    }
}


class Prueba {

    // ===== 1. Un atributo de la clase se puede usar en todos sus métodos =====

    public String nombre = "Sol";

    public void saludoConAtributo() {
        System.out.println("Hola, " + nombre);   // usa el atributo de la clase
    }

    // Problema: el saludo siempre es para "Sol". Con un parámetro cada llamada puede usar otro nombre.


    // ===== 2. Método con un parámetro =====

    // Entre paréntesis se declara cada parámetro: tipo y nombre
    public void saludar(String nombre) {
        System.out.println("Hola, " + nombre);
    }


    // ===== 3. Método con varios parámetros =====

    // Se separan con comas, cada uno con su tipo
    public void presentar(String nombre, int edad) {
        System.out.println("Nombre: " + nombre + ", edad: " + edad);
    }


    // ===== 4. Parámetros y retorno =====

    public int sumar(int a, int b) {
        return a + b;
    }

    public boolean esMayorDeEdad(int edad) {
        return edad >= 18;
    }


    // ===== 5. Java no tiene parámetros opcionales: se escriben dos versiones =====

    // Dos métodos con el mismo nombre y distintos parámetros (sobrecarga)
    public void despedir(String nombre) {
        despedir(nombre, "Adiós");
    }

    public void despedir(String nombre, String mensaje) {
        System.out.println(mensaje + ", " + nombre);
    }
}
