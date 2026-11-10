// Programación orientada a objetos (POO): organizar el programa en torno a OBJETOS
// que combinan datos (atributos) y comportamientos (métodos), igual que los objetos del mundo real.
//
// Ejemplo: un auto tiene marca, modelo, color... (atributos) y puede encenderse, acelerar, frenar... (métodos).
//
// La clase con main va primero; la clase Auto (secciones 1 a 3) está al final del archivo.

public class OopIntroduction {

    public static void main(String[] args) {

        // ===== 4. Crear objetos: instanciar la clase =====

        Auto auto1 = new Auto("Toyota", "RAV4", "negro", 2025, 250);
        auto1.mostrarInfo();


        // ===== 5. Usar los métodos del objeto =====

        auto1.acelerar();    // apagado: no se puede
        auto1.encender();
        auto1.acelerar();
        auto1.acelerar();
        System.out.println("Velocidad: " + auto1.velocidadActual + " km/h");   // 20

        auto1.frenar();
        auto1.apagar();
        System.out.println(auto1.describir());


        // ===== 6. Cada objeto tiene su propio estado =====

        Auto auto2 = new Auto("Ford", "Fiesta", "rojo", 2018, 180);
        auto2.encender();

        System.out.println(auto2.describir());
        System.out.println("¿Auto 1 encendido? " + auto1.estaEncendido());   // false
        System.out.println("¿Auto 2 encendido? " + auto2.estaEncendido());   // true
    }
}


// ===== 1. La clase: la plantilla del objeto =====

class Auto {

    // Atributos: las características del auto
    public String marca;
    public String modelo;
    public String color;
    public int anio;                 // sin ñ en los nombres: anio
    public int velocidadMaxima;

    // Atributos de estado: cambian mientras el auto se usa
    public boolean encendido = false;
    public int velocidadActual = 0;

    // ===== 2. El constructor: inicializa el objeto =====

    public Auto(String marca, String modelo, String color, int anio, int velocidadMaxima) {
        this.marca = marca;
        this.modelo = modelo;
        this.color = color;
        this.anio = anio;
        this.velocidadMaxima = velocidadMaxima;
    }

    // ===== 3. Métodos: lo que el auto puede hacer =====

    // Método sin retorno que muestra la información del objeto
    public void mostrarInfo() {
        System.out.println("=== Información del auto ===");
        System.out.println("Marca: " + marca);
        System.out.println("Modelo: " + modelo);
        System.out.println("Color: " + color);
        System.out.println("Año: " + anio);
        System.out.println("Velocidad máxima: " + velocidadMaxima + " km/h");
    }

    // Métodos que cambian el estado del objeto
    public void encender() {
        encendido = true;
        System.out.println("Auto encendido");
    }

    public void apagar() {
        encendido = false;
        velocidadActual = 0;
        System.out.println("Auto apagado");
    }

    public void acelerar() {
        // El comportamiento depende del estado actual del objeto
        if (!encendido) {
            System.out.println("Auto apagado, no se puede acelerar");
            return;
        }

        velocidadActual = Math.min(velocidadActual + 10, velocidadMaxima);
        System.out.println("Auto acelerando. Velocidad actual: " + velocidadActual + " km/h");
    }

    public void frenar() {
        velocidadActual = 0;
        System.out.println("Auto detenido");
    }

    // Métodos con retorno: devuelven información sobre el objeto
    public boolean estaEncendido() {
        return encendido;
    }

    public String describir() {
        return marca + " " + modelo + " (" + color + ", " + anio + ")";
    }
}
