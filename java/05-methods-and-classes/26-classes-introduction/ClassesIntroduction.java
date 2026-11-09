// En Java todo el código va dentro de clases, y el programa empieza en el método main.


// ===== 1. Definir una clase (al final del archivo: Persona y Venta) =====

// Una clase es una plantilla (un molde) para crear objetos.
// Sus atributos son variables que pertenecen a la clase: describen cómo es cada objeto.
// (public significa que se pueden usar desde fuera de la clase; se explicará con el encapsulamiento)

// ===== 2. El método principal: main =====

// Todo programa empieza en main: es el primer método que se ejecuta.
// (public, static, void y String[] args se explicarán en la lección de métodos)
// Al ejecutar un solo archivo con "java Archivo.java", se usa la PRIMERA clase del archivo:
// por eso la clase con main va arriba y Persona y Venta (secciones 1) van al final.

public class ClassesIntroduction {

    public static void main(String[] args) {

        // ===== 3. Instanciar: crear un objeto de la clase =====

        // Clase  nombreDelObjeto = new Clase();
        Persona persona1 = new Persona();

        // ===== 4. Leer los atributos: objeto.atributo =====

        System.out.println(persona1.nombre);   // Sol

        System.out.println("Nombre: " + persona1.nombre);
        System.out.println("Edad: " + persona1.edad);
        System.out.println("Dirección: " + persona1.direccion);
        System.out.println("Salario: " + persona1.salario);
        System.out.println("Activo: " + persona1.activo);


        // ===== 5. Cambiar los atributos de un objeto =====

        persona1.edad = 35;
        System.out.println("Edad tras el cambio: " + persona1.edad);   // 35


        // ===== 6. Varios objetos de la misma clase =====

        // Cada objeto tiene sus propios valores
        Persona persona2 = new Persona();
        persona2.nombre = "Marcos";
        persona2.edad = 28;

        System.out.println(persona1.nombre + " tiene " + persona1.edad + " años");   // Sol tiene 35 años
        System.out.println(persona2.nombre + " tiene " + persona2.edad + " años");   // Marcos tiene 28 años


        // ===== 7. Otra clase =====

        Venta venta1 = new Venta();
        System.out.println("Cantidades por defecto: " + venta1.cantidades);   // 0

        venta1.vendedor = "Larry";
        venta1.cantidades = 500;
        System.out.println("Vendedor: " + venta1.vendedor + ", cantidades: " + venta1.cantidades);
    }
}


// ===== Clases auxiliares (van después de la clase con main) =====

class Persona {
    public String nombre = "Sol";
    public int edad = 34;
    public String direccion = "Calle Mayor 12";
    public double salario = 2500;
    public boolean activo = true;
}

// Otra clase, con atributos sin valor inicial
class Venta {
    public String vendedor = "";
    public int cantidades;
}
