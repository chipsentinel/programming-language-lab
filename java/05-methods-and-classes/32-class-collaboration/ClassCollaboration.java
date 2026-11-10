// Un programa real tiene varias clases, y colaboran entre sí:
// una clase crea objetos de otra, los guarda como atributos y llama a sus métodos.
//
// La clase con main va primero; Cliente y Orden están al final del archivo.

public class ClassCollaboration {

    public static void main(String[] args) {

        // ===== 3. Crear y usar un objeto de otra clase desde main =====

        // main está en la clase ClassCollaboration, pero crea un Cliente y llama a su método
        Cliente cliente1 = new Cliente("Sol", "sol@correo.com");
        cliente1.datos();


        // ===== 4. Colaboración: una clase recibe un objeto de otra =====

        Orden orden1 = new Orden(1, cliente1, 120.5);
        orden1.resumen();

        System.out.println("Cliente de la orden: " + orden1.nombreDelCliente());


        // ===== 5. Los objetos se pasan por referencia =====

        // La orden no guarda una copia del cliente, sino el mismo objeto
        Orden orden2 = new Orden(2, cliente1, 80);

        cliente1.email = "nuevo@correo.com";   // se cambia el cliente...

        orden1.resumen();   // ...y las dos órdenes lo ven
        orden2.resumen();
    }
}


// ===== 1. Una clase con sus datos y su método =====

class Cliente {
    public String nombre;
    public String email;

    public Cliente(String nombre, String email) {
        this.nombre = nombre;
        this.email = email;
    }

    // public: sin él, clases de otros paquetes no podrían llamar al método
    public void datos() {
        System.out.println("Nombre: " + nombre);
        System.out.println("Email: " + email);
    }
}


// ===== 2. Una clase que necesita a otra =====

// Una orden de compra pertenece a un cliente: Orden tiene un atributo de tipo Cliente
class Orden {
    private int numero;
    private Cliente cliente;      // un objeto de otra clase como atributo
    private double total;

    public Orden(int numero, Cliente cliente, double total) {
        this.numero = numero;
        this.cliente = cliente;
        this.total = total;
    }

    public void resumen() {
        System.out.println("Orden #" + numero);
        cliente.datos();           // Orden usa un método de Cliente
        System.out.println("Total: " + String.format("%.2f", total));
    }

    public String nombreDelCliente() {
        return cliente.nombre;     // y también sus atributos
    }
}
