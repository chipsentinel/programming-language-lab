import java.util.List;

// Una clase abstracta es una clase que NO se puede instanciar: no se pueden crear objetos directamente de ella.
// Sirve como BASE para otras clases (herencia): define lo que tienen en común y OBLIGA a las hijas
// a implementar ciertas partes.
//
// Puede contener:
//   - miembros normales (atributos, métodos con código)
//   - métodos abstractos: solo la firma, sin cuerpo; las hijas DEBEN implementarlos
//   - métodos normales que las hijas PUEDEN redefinir (en Java todos los métodos se pueden redefinir salvo final)
//
// La clase con main va primero; las demás clases están al final del archivo.

public class AbstractClasses {

    public static void main(String[] args) {

        // ===== 3. No se puede instanciar una clase abstracta =====

        // Animal animal = new Animal("Genérico");   // error: Animal is abstract; cannot be instantiated

        Perro miPerro = new Perro("Snoopy");
        Gato miGato = new Gato("Garfield");


        // ===== 4. Usar las clases hijas =====

        miPerro.comer();          // heredado de Animal
        miPerro.hacerSonido();    // implementado en Perro

        miGato.comer();
        miGato.hacerSonido();     // implementado en Gato: es distinto


        // ===== 5. Polimorfismo =====

        // Todos son Animal: se guardan juntos en una lista del tipo base.
        // Al llamar a hacerSonido, se ejecuta la versión de la clase REAL de cada objeto.
        List<Animal> animales = List.of(miPerro, miGato, new Vaca("Lola"));

        System.out.println("Animales: " + animales.size());

        for (Animal animal : animales) {
            animal.hacerSonido();
        }

        for (Animal animal : animales) {
            animal.dormir();      // Gato lo redefine, los demás usan el de Animal
        }
    }
}


// ===== 1. La clase abstracta =====

abstract class Animal {

    // Una clase abstracta puede tener atributos y constructor normales
    private String nombre;

    public Animal(String nombre) {
        this.nombre = nombre;
    }

    public String getNombre() {
        return nombre;
    }

    // Método normal: lo heredan las hijas tal cual
    public void comer() {
        System.out.println(nombre + " está comiendo");
    }

    // Método abstracto: sin cuerpo (solo ;). Cada hija DEBE implementarlo
    public abstract void hacerSonido();

    // Método con una versión por defecto que las hijas pueden redefinir
    public void dormir() {
        System.out.println(nombre + " está durmiendo");
    }
}


// ===== 2. Las hijas implementan lo abstracto =====

class Perro extends Animal {

    public Perro(String nombre) {
        super(nombre);
    }

    // Obligatorio: si no se implementa, el compilador da error.
    // @Override es opcional pero recomendable: el compilador comprueba que realmente redefine algo
    @Override
    public void hacerSonido() {
        System.out.println(getNombre() + " está ladrando");
    }
}

class Gato extends Animal {

    public Gato(String nombre) {
        super(nombre);
    }

    @Override
    public void hacerSonido() {
        System.out.println(getNombre() + " está maullando");
    }

    // Opcional: cambia la versión por defecto
    @Override
    public void dormir() {
        System.out.println(getNombre() + " duerme 16 horas");
    }
}

class Vaca extends Animal {

    public Vaca(String nombre) {
        super(nombre);
    }

    @Override
    public void hacerSonido() {
        System.out.println(getNombre() + " está mugiendo");
    }
    // No redefine dormir: usa el de Animal
}
