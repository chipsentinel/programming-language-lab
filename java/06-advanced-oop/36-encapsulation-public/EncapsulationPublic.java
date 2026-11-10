// Encapsulamiento: ocultar los detalles internos de una clase y controlar quién puede acceder a ella.
// Los modificadores de acceso indican desde dónde se puede usar una clase o uno de sus miembros.
//
// Modificadores de acceso en Java:
//   public       accesible desde cualquier parte del programa
//   protected    desde el mismo paquete y desde las clases derivadas (lección 39)
//   (ninguno)    solo desde el mismo paquete ("package-private"): es el valor por defecto
//   private      solo desde dentro de la misma clase (lección siguiente)
//
// Esta lección se centra en public.
//
// La clase con main va primero; la clase Camion está al final del archivo.

public class EncapsulationPublic {

    public static void main(String[] args) {

        // ===== 2. Usar una clase pública desde otra =====

        // EncapsulationPublic es una clase distinta a Camion, pero puede crear un Camion
        // y usar sus miembros públicos
        Camion camion1 = new Camion("Mack", "MK12", "rojo", 18000);
        camion1.datosCamion(230, 40);


        // ===== 3. Cambiar un atributo público directamente =====

        camion1.color = "azul";
        System.out.println("Color tras cambiarlo: " + camion1.color);


        // ===== 4. El inconveniente: nadie controla los valores =====

        // Al ser public, cualquier clase puede dejar el objeto en un estado absurdo
        camion1.cargaMaxima = -500;
        System.out.println("Carga máxima: " + camion1.cargaMaxima + " (nadie lo impide)");

        // Para poder validar los datos hay que ocultar los atributos y dar acceso controlado:
        // eso es lo que permite private, en la lección siguiente.
    }
}


// ===== 1. Una clase con miembros públicos =====

// En Java una clase public debe estar en un archivo con su mismo nombre (Camion.java).
// Aquí van varias clases en un archivo, así que Camion no lleva public (acceso de paquete),
// pero sus miembros sí.
class Camion {

    // public: se pueden leer y cambiar desde cualquier otra clase
    public String marca;
    public String modelo;
    public String color;
    public int cargaMaxima;

    // El constructor también es public: si no, otra clase no podría crear camiones
    public Camion(String marca, String modelo, String color, int cargaMaxima) {
        this.marca = marca;
        this.modelo = modelo;
        this.color = color;
        this.cargaMaxima = cargaMaxima;
    }

    // Un método public se puede llamar desde otra clase
    public void datosCamion(int velocidad, int acelera) {
        System.out.println("Marca: " + marca);
        System.out.println("Modelo: " + modelo);
        System.out.println("Color: " + color);
        System.out.println("Velocidad: " + velocidad + " km/h");
        System.out.println("Aceleración: " + acelera + " m/s²");
    }
}
