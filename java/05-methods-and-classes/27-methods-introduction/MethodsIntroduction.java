// Un método es un bloque de código con nombre, definido dentro de una clase.
// Sirve para no repetir código, dividir tareas en partes pequeñas y poder reutilizarlas.
// Puede recibir datos (parámetros, lección siguiente) y puede devolver un resultado.
//
// La clase con main va primero; la clase Prueba (sección 1) está al final del archivo.

public class MethodsIntroduction {

    // Un método static se puede llamar sin crear ningún objeto.
    // main es un método static: por eso es el punto de entrada del programa.
    static void separador() {
        System.out.println("----------");
    }

    public static void main(String[] args) {

        // ===== 2. Invocar (llamar) un método =====

        // Primero hay que instanciar la clase; después objeto.metodo()
        Prueba saludo1 = new Prueba();
        saludo1.saludar();   // Hola a todos

        Prueba despedida = new Prueba();
        despedida.despedir();   // Adiós a todos

        // Un método se puede llamar tantas veces como se quiera
        saludo1.saludar();


        // ===== 3. Métodos con retorno =====

        separador();

        // El valor devuelto se puede guardar en una variable...
        int resultado = saludo1.sumar();
        System.out.println("Total de la suma: " + resultado);   // 68

        // ...o usar directamente
        System.out.println("Mensaje: " + despedida.despedida());   // Adiós a todos
        System.out.println("sumar() + 2 = " + (saludo1.sumar() + 2));   // 70


        // ===== 4. Un método que llama a otros =====

        separador();
        saludo1.presentar();   // Hola a todos / Adiós a todos


        // ===== 5. Sin return no hay resultado =====

        // Un método void no se puede guardar en una variable:
        // int x = saludo1.saludar();   // error: saludar no devuelve nada
    }
}


// ===== 1. Definir métodos en una clase =====

class Prueba {

    // Método SIN retorno: void significa "no devuelve nada"
    public void saludar() {
        System.out.println("Hola a todos");
    }

    public void despedir() {
        System.out.println("Adiós a todos");
    }

    // Método CON retorno: en lugar de void se escribe el tipo del valor que devuelve,
    // y termina con return
    public int sumar() {
        int valor1 = 23, valor2 = 45;
        int total = valor1 + valor2;

        return total;   // el valor que recibe quien llame al método
    }

    public String despedida() {
        String mensaje = "Adiós a todos";

        return mensaje;
    }

    // Un método puede llamar a otros métodos de su misma clase
    public void presentar() {
        saludar();
        despedir();
    }
}
