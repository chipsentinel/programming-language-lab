// static: el miembro (método, variable) pertenece a la CLASE en sí, no a un objeto concreto.
// Por eso se usa con el nombre de la clase, SIN crear ningún objeto: Clase.miembro
//
// Sirve para:
//   - Llamar a algo sin instanciar la clase (utilidades como Math).
//   - Compartir un dato entre TODOS los objetos de una clase (un contador).
//   - Crear herramientas que no dependen del estado de un objeto.
//
// La clase con main va primero; las demás clases están al final del archivo.

public class StaticLesson {

    // main es static: por eso el programa puede arrancar sin crear ningún objeto
    public static void main(String[] args) {

        // ===== 1. Llamar a un miembro static: Clase.miembro =====

        Utilidad.saludar();
        System.out.println("Total: " + Utilidad.total);

        // Java permite llamar a un miembro static desde un objeto (u.saludar()), pero está desaconsejado:
        // confunde porque parece de instancia. Los de instancia sí necesitan el objeto:
        new Utilidad().saludarDesdeObjeto();

        Utilidad.total = 8000;    // una variable static se puede modificar
        System.out.println("Total tras cambiarlo: " + Utilidad.total);


        // ===== 2. Clase de utilidades =====

        // Java no tiene "clases static" como C#. Se escribe una clase con constructor private y métodos static
        // Matematica m = new Matematica();   // error: el constructor es private
        System.out.println("Suma: " + Matematica.sumar(50, 20));
        System.out.println("Doble de 4: " + Matematica.doble(4));

        // Math (de Java) es de este tipo: Math.sqrt(16), Math.PI...
        System.out.println("Raíz de 16: " + Math.sqrt(16));


        // ===== 3. Dato compartido =====

        Persona.mostrarTotal();

        Persona sol = new Persona("Sol");
        Persona marcos = new Persona("Marcos");
        Persona lucia = new Persona("Lucía");

        sol.presentar();
        marcos.presentar();
        lucia.presentar();

        Persona.mostrarTotal();     // el contador es de la clase: lo ven y lo cambian todos los objetos


        // ===== 4. Bloque static =====

        System.out.println("Antes de usar Configuracion");
        System.out.println("Idioma: " + Configuracion.idioma);     // aquí se ejecuta el bloque static
        System.out.println("Idioma: " + Configuracion.idioma);     // la segunda vez ya no
    }
}


// ===== 1. Miembros static de una clase normal =====

class Utilidad {
    // Método static: se llama con Utilidad.saludar(), sin new
    public static void saludar() {
        System.out.println("Hola a todos");
    }

    // Variable static: un único valor, perteneciente a la clase
    public static int total = 5000;

    // Método normal (de instancia): necesita un objeto
    public void saludarDesdeObjeto() {
        System.out.println("Hola desde un objeto");
    }
}


// ===== 2. Clase de utilidades =====

// Convención: final (no se puede heredar) + constructor private (no se puede instanciar) + todo static
final class Matematica {
    private Matematica() { }

    public static int sumar(int a, int b) {
        return a + b;
    }

    public static int doble(int numero) {
        return numero * 2;
    }
}


// ===== 3. Un dato compartido entre todos los objetos =====

class Persona {
    // static: UNA sola copia para toda la clase
    public static int creadas = 0;

    // Sin static: cada objeto tiene la suya
    public String nombre;
    public int numero;

    public Persona(String nombre) {
        creadas++;               // cada objeto nuevo suma 1 al contador compartido
        this.nombre = nombre;
        this.numero = creadas;
    }

    public void presentar() {
        System.out.println("Soy " + nombre + " (n.º " + numero + ")");
    }

    // Un método static solo puede usar miembros static
    public static void mostrarTotal() {
        System.out.println("Personas creadas: " + creadas);
        // System.out.println(nombre);        // error: un método static no tiene "this" ni acceso a miembros de instancia
        // System.out.println(this.numero);   // error: this no existe en un contexto static
    }
}


// ===== 4. Bloque static =====

class Configuracion {
    public static String idioma;

    // Se ejecuta UNA sola vez, automáticamente, cuando la clase se usa por primera vez
    static {
        System.out.println("Inicializando configuración (solo una vez)");
        idioma = "es";
    }
}
