// Un constructor es un método especial que se ejecuta automáticamente al crear un objeto (new).
// Sirve para dar valores iniciales a los atributos y preparar el objeto.
//
// La clase con main va primero; las clases Contador y Persona están al final del archivo.

public class Constructors {

    public static void main(String[] args) {

        // ===== 1. Constructor por defecto =====

        Contador contador1 = new Contador();
        System.out.println("Valor: " + contador1.valor);   // 10


        // ===== 2. Constructor propio =====

        // new llama al constructor: el mensaje sale en ese momento
        Persona persona1 = new Persona();
        System.out.println("Nombre: " + persona1.nombre + ", edad: " + persona1.edad);

        Persona persona2 = new Persona("Marcos", 28);
        System.out.println("Nombre: " + persona2.nombre + ", edad: " + persona2.edad);


        // ===== 3. Se ejecuta una vez por cada objeto =====

        // Cada new ejecuta el constructor de nuevo
        Persona persona3 = new Persona("Lucía", 8);
        Persona persona4 = new Persona();


        // ===== 4. El constructor no se puede volver a llamar =====

        // persona1.Persona();   // error: un constructor solo se ejecuta con new


        // ===== 5. Si hay un constructor con parámetros, el vacío desaparece =====

        // Si Persona solo tuviera el constructor con parámetros, new Persona() no compilaría:
        // el constructor por defecto solo se crea cuando la clase no define ninguno.
    }
}


// ===== 1. Constructor por defecto =====

// Si una clase no define ningún constructor, Java crea uno vacío por su cuenta.
// Por eso hasta ahora new Persona() funcionaba sin que escribiéramos nada.
class Contador {
    public int valor = 10;
}


// ===== 2. Constructor propio =====

class Persona {
    public String nombre = "";
    public int edad;

    // Reglas del constructor:
    // 1. Se llama igual que la clase.
    // 2. No tiene tipo de retorno (ni siquiera void).
    // 3. Se ejecuta una sola vez por objeto, al hacer new.

    // Constructor sin parámetros: da valores iniciales fijos
    public Persona() {
        nombre = "Sol";
        edad = 34;
        System.out.println("Creando persona: " + nombre + ", " + edad);
    }

    // Constructor con parámetros: cada objeto recibe sus valores al crearse
    public Persona(String elNombre, int laEdad) {
        nombre = elNombre;
        edad = laEdad;
        System.out.println("Creando persona: " + nombre + ", " + edad);
    }

    // Una clase puede tener varios constructores con distintos parámetros (sobrecarga).
    // new elige el que coincide con los argumentos que se pasan.
}


// ===== 3. Un constructor con el tipo de retorno deja de serlo =====

class Falso {
    // Con void ya no es un constructor, sino un método normal que se llama igual que la clase:
    // public void Falso() { }    // compila, pero es un método, no un constructor (error frecuente)
}
