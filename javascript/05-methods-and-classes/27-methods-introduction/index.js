// Un método es una función definida dentro de una clase.
// Sirve para no repetir código, dividir tareas en partes pequeñas y poder reutilizarlas.
// Puede recibir datos (parámetros, lección siguiente) y puede devolver un resultado.


// ===== 1. Definir métodos en una clase =====

class Prueba {

    // No se escribe void ni el tipo de retorno: un método sin return devuelve undefined
    saludar() {
        console.log("Hola a todos");
    }

    despedir() {
        console.log("Adiós a todos");
    }

    // Método CON retorno: termina con return
    sumar() {
        const valor1 = 23, valor2 = 45;
        const total = valor1 + valor2;

        return total;   // el valor que recibe quien llame al método
    }

    despedida() {
        const mensaje = "Adiós a todos";

        return mensaje;
    }

    // Un método puede llamar a otros métodos de su misma clase: con this.
    presentar() {
        this.saludar();
        this.despedir();
    }
}


// ===== 2. Funciones sueltas =====

// En JavaScript no hace falta una clase: una función puede existir por sí sola.
// Es el equivalente a un método static de C# y Java.
function separador() {
    console.log("----------");
}


// ===== 3. Invocar (llamar) un método =====

// Primero hay que instanciar la clase; después objeto.metodo()
const saludo1 = new Prueba();
saludo1.saludar();   // Hola a todos

const despedida = new Prueba();
despedida.despedir();   // Adiós a todos

// Un método se puede llamar tantas veces como se quiera
saludo1.saludar();


// ===== 4. Métodos con retorno =====

separador();

// El valor devuelto se puede guardar en una variable...
const resultado = saludo1.sumar();
console.log("Total de la suma: " + resultado);   // 68

// ...o usar directamente
console.log("Mensaje: " + despedida.despedida());   // Adiós a todos
console.log("sumar() + 2 = " + (saludo1.sumar() + 2));   // 70


// ===== 5. Un método que llama a otros =====

separador();
saludo1.presentar();   // Hola a todos / Adiós a todos


// ===== 6. Sin return se obtiene undefined =====

// JavaScript no da error al guardar el resultado de un método sin return
const nada = saludo1.saludar();   // escribe "Hola a todos"
console.log(nada);                // undefined
