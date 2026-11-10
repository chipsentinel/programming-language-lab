// protected: el miembro es accesible dentro de su propia clase, en las clases que heredan de ella
// y, en Java, TAMBIÉN en todas las clases del mismo paquete.
//
//                  propia clase   mismo paquete   clase hija (otro paquete)   resto
//   public              sí            sí                  sí                  sí
//   protected           sí            sí                  sí                  NO
//   (sin modificador)   sí            sí                  NO                  NO
//   private             sí            NO                  NO                  NO
//
// La clase con main va primero; Animal, Perro y Gato están al final del archivo.

public class EncapsulationProtected {

    public static void main(String[] args) {

        Perro miPerro = new Perro("Rex");

        miPerro.dormir();       // el método public usa respirar() internamente
        miPerro.ladrar();       // la hija usa respirar()
        miPerro.correr();       // la hija usa energia
        miPerro.descansar();

        Gato miGato = new Gato("Michi");
        miGato.maullar();


        // ===== 3. Desde fuera del paquete, protected no se ve =====

        // OJO: en Java protected incluye el paquete. Como todas las clases de este archivo están
        // en el mismo paquete, aquí SÍ se podría escribir miPerro.respirar() y funcionaría.
        // Desde una clase de OTRO paquete que no sea hija de Animal, daría error:
        // miPerro.respirar();      // error: respirar() has protected access in Animal
    }
}


// ===== 1. La clase padre con miembros protected =====

class Animal {
    private String nombre;

    // protected: lo ven Animal, sus hijas (Perro, Gato) y las clases de su mismo paquete
    protected int energia = 100;

    public Animal(String nombre) {
        this.nombre = nombre;
    }

    // Como nombre es private, las hijas lo leen con un getter
    // (también podría ser protected, como energia)
    public String getNombre() {
        return nombre;
    }

    // Método protected: es un "detalle interno" que las hijas pueden reutilizar
    protected void respirar() {
        System.out.println(nombre + " está respirando");
    }

    // Un método public de la propia clase puede usar sus miembros protected
    public void dormir() {
        System.out.println(nombre + " está durmiendo");
        respirar();
    }

    public void descansar() {
        energia = 100;
        System.out.println(nombre + " descansa. Energía: " + energia);
    }
}


// ===== 2. Las hijas usan los miembros protected del padre =====

class Perro extends Animal {

    public Perro(String nombre) {
        super(nombre);
    }

    public void ladrar() {
        respirar();                                              // protected heredado: permitido
        System.out.println(getNombre() + " está ladrando");
    }

    public void correr() {
        energia -= 30;                                           // atributo protected heredado: permitido
        System.out.println(getNombre() + " corre. Energía: " + energia);
    }
}

class Gato extends Animal {

    public Gato(String nombre) {
        super(nombre);
    }

    public void maullar() {
        respirar();
        System.out.println(getNombre() + " está maullando");
    }
}
