// private: el miembro solo es accesible DENTRO de la clase donde se declara.
// Ninguna otra clase (ni siquiera una derivada) puede usarlo directamente.
// Es lo habitual para los atributos: se protegen y se ofrece acceso controlado mediante métodos.
//
// La clase con main va primero; Camion, Furgoneta y Utilidades están al final del archivo.

public class EncapsulationPrivate {

    public static void main(String[] args) {

        Camion camion1 = new Camion("Mack", "MK12", "rojo", 18000);

        // Los miembros privados no se ven desde otra clase:
        // System.out.println(camion1.marca);        // error: marca has private access in Camion
        // camion1.cargaMaxima = -500;               // error: ya no se puede dejar un valor absurdo
        // System.out.println(camion1.descripcion()); // error: el método es private

        System.out.println("Marca: " + camion1.obtenerMarca());

        camion1.cambiarCargaMaxima(-500);     // rechazado por la validación
        camion1.cambiarCargaMaxima(20000);    // aceptado

        camion1.mostrar();


        // ===== Getters y setters estándar =====

        Furgoneta furgoneta1 = new Furgoneta();
        furgoneta1.setCarga(-5);        // rechazado
        furgoneta1.setCarga(1000);      // aceptado
        System.out.println("Carga de la furgoneta: " + furgoneta1.getCarga());


        // ===== Constructor privado =====

        // Utilidades u = new Utilidades();   // error: Utilidades() has private access
    }
}


// ===== 1. Atributos privados con métodos de acceso =====

class Camion {

    // private: solo se pueden usar dentro de la clase Camion
    private String marca;
    private String modelo;
    private String color;
    private int cargaMaxima;

    public Camion(String marca, String modelo, String color, int cargaMaxima) {
        this.marca = marca;
        this.modelo = modelo;
        this.color = color;
        this.cargaMaxima = cargaMaxima;
    }

    // Método "getter": permite LEER el atributo sin dejar modificarlo
    public String obtenerMarca() {
        return marca;
    }

    public int obtenerCargaMaxima() {
        return cargaMaxima;
    }

    // Método "setter": permite MODIFICAR el atributo, pero validando el valor
    public void cambiarCargaMaxima(int nuevaCarga) {
        if (nuevaCarga <= 0) {
            System.out.println("Carga inválida: debe ser mayor que cero");
            return;
        }

        cargaMaxima = nuevaCarga;
        System.out.println("Carga máxima cambiada a " + cargaMaxima);
    }

    // ===== 2. Métodos privados: ayudantes internos =====

    // Un método private solo se puede llamar desde otros métodos de la misma clase
    private String descripcion() {
        return marca + " " + modelo + " (" + color + ")";
    }

    public void mostrar() {
        System.out.println(descripcion() + ": carga máxima " + cargaMaxima + " kg");
    }
}


// ===== 3. Getters y setters: la forma habitual en Java =====

class Furgoneta {
    private int carga = 500;

    // Convención: getNombre() para leer y setNombre(valor) para modificar.
    // Los IDE (IntelliJ) los generan automáticamente.
    public int getCarga() {
        return carga;
    }

    public void setCarga(int carga) {
        if (carga <= 0) {
            System.out.println("Carga inválida: debe ser mayor que cero");
            return;
        }

        this.carga = carga;
    }
}


// ===== 4. Constructor privado =====

class Utilidades {
    // Con un constructor private nadie puede crear objetos con new desde fuera
    private Utilidades() { }
}
