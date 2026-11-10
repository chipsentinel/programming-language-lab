// this es una referencia al objeto actual: el objeto sobre el que se está ejecutando el método.
// Se usa para distinguir atributos de parámetros con el mismo nombre,
// para pasar el propio objeto como valor y para encadenar constructores.
//
// La clase con main va primero; las clases SinThis y Persona están al final del archivo.

public class ThisKeyword {

    public static void main(String[] args) {

        // Sin this no se asigna nada
        SinThis malo = new SinThis("Bart", 10);
        System.out.println("Sin this: nombre = '" + malo.nombre + "', edad = " + malo.edad);

        // Con this sí
        Persona persona1 = new Persona("Bart", 10);
        persona1.mostrar();

        // this como valor
        Persona persona2 = new Persona("Lisa", 8);
        System.out.println("¿Bart es mayor que Lisa? " + persona1.esMayorQue(persona2));   // true
        System.out.println("¿Lisa es mayor que Bart? " + persona2.esMayorQue(persona1));   // false

        // Constructor encadenado
        Persona persona3 = new Persona();
        persona3.mostrar();

        // Llamadas encadenadas
        persona1.cumplirAnios().cumplirAnios();
        persona1.mostrar();   // edad 12

        // Cuando los nombres son distintos, this no hace falta:
        // public Persona(String elNombre, int laEdad) { nombre = elNombre; edad = laEdad; }
    }
}


// ===== 1. El problema: parámetro y atributo con el mismo nombre =====

class SinThis {
    public String nombre = "";
    public int edad = 0;

    public SinThis(String nombre, int edad) {
        // Dentro del constructor, "nombre" es el PARÁMETRO (oculta al atributo):
        // se asigna el parámetro a sí mismo y el atributo no cambia
        nombre = nombre;
        edad = edad;
    }
}


// ===== 2. La solución: this =====

class Persona {
    public String nombre = "";
    public int edad = 0;

    // this.nombre es el atributo del objeto; nombre es el parámetro
    public Persona(String nombre, int edad) {
        this.nombre = nombre;
        this.edad = edad;
    }

    // ===== 5. Encadenar constructores: this(...) =====

    // Este constructor reutiliza el de dos parámetros en lugar de repetir su código.
    // La llamada this(...) debe ser la primera instrucción.
    public Persona() {
        this("Sol", 34);
    }

    public void mostrar() {
        // En un método sin parámetros homónimos this es opcional, pero deja claro que es un atributo
        System.out.println("Nombre: " + this.nombre + ", edad: " + this.edad);
    }

    // ===== 3. this como valor: el propio objeto =====

    // this es el objeto que recibe la llamada; otra es el que se pasa como argumento
    public boolean esMayorQue(Persona otra) {
        return this.edad > otra.edad;
    }

    // ===== 4. Devolver this para encadenar llamadas =====

    public Persona cumplirAnios() {
        this.edad++;
        return this;   // devuelve el mismo objeto, y se puede llamar a otro método sobre él
    }
}
