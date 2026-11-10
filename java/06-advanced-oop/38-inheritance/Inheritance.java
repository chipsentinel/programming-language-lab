// Herencia: una clase (hija) puede derivar de otra (padre) y reutilizar sus miembros.
// La hija recibe los atributos y métodos del padre, y puede añadir los suyos.
// Se escribe con extends: class Hija extends Padre
//
// Reglas en Java:
//  - Una clase solo puede heredar de UNA clase (herencia simple).
//  - Todas las clases derivan, directa o indirectamente, de la clase Object (se hace solo).
//
// La clase con main va primero; Animal, Perro y Gato están al final del archivo.

public class Inheritance {

    public static void main(String[] args) {

        // ===== 3. La hija usa los miembros del padre =====

        Perro miPerro = new Perro("Snoopy");

        miPerro.dormir();    // heredado de Animal
        miPerro.comer();     // heredado de Animal
        miPerro.ladrar();    // propio de Perro

        // También los métodos de acceso heredados
        miPerro.setNombre("Rex");
        miPerro.comer();     // Rex está comiendo


        // ===== 4. Cada hija tiene lo suyo =====

        Gato miGato = new Gato("Michi");

        miGato.dormir();
        miGato.comer();
        miGato.maullar();

        // Lo propio de una hija no existe en la otra:
        // miGato.ladrar();   // error: Gato no tiene el método ladrar


        // ===== 5. Relación "es un" =====

        // Un Perro ES UN Animal: se puede guardar en una variable de tipo Animal
        Animal animal = miPerro;
        animal.comer();      // Rex está comiendo
        System.out.println("¿Perro es un Animal? " + (miPerro instanceof Animal));   // true
        System.out.println("¿Perro es un Gato? " + (animal instanceof Gato));        // false

        // Pero a través de la variable Animal solo se ven los miembros de Animal:
        // animal.ladrar();   // error: Animal no tiene ladrar


        // ===== 6. Un miembro private del padre no se hereda para uso directo =====

        // nombre es private en Animal: Perro no puede usarlo directamente y debe pasar por getNombre().
        // Por eso se necesita protected (lección 39).
    }
}


// ===== 1. La clase padre: lo que tienen en común todos los animales =====

class Animal {
    private String nombre;

    public Animal(String nombre) {
        this.nombre = nombre;
    }

    // Métodos de acceso (lección 37)
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    // Métodos comunes a cualquier animal
    public void comer() {
        System.out.println(nombre + " está comiendo");
    }

    public void dormir() {
        System.out.println(nombre + " está durmiendo");
    }
}


// ===== 2. Las clases hijas: heredan de Animal y añaden lo suyo =====

class Perro extends Animal {

    // super(nombre) llama al constructor del padre, que es quien guarda el nombre
    public Perro(String nombre) {
        super(nombre);
    }

    // Método propio de los perros
    public void ladrar() {
        System.out.println(getNombre() + " está ladrando");
    }
}

class Gato extends Animal {

    public Gato(String nombre) {
        super(nombre);
    }

    public void maullar() {
        System.out.println(getNombre() + " está maullando");
    }
}
