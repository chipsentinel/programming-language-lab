// Una enumeración (enum) es un tipo de dato formado por un conjunto FIJO de constantes con nombre.
// Hace el código más legible y seguro: en lugar de usar números o textos "mágicos" (0, 1, "enviado"),
// se usan nombres con significado (EstadoPedido.ENVIADO) y el compilador impide valores inventados.
//
// En Java un enum es una CLASE completa: puede tener atributos, constructor y métodos.
//
// La clase con main va primero; los enum están al final del archivo.

public class Enums {

    public static void main(String[] args) {

        // ===== 2. Usar una enumeración =====

        // Se declara una variable del tipo enum y se le da una de sus constantes: Tipo.VALOR
        EstadoPedido status = EstadoPedido.PENDIENTE;
        System.out.println("Estado del pedido: " + status);              // PENDIENTE (muestra el nombre)
        System.out.println("ID del estado: " + status.ordinal());        // 0 (ordinal(): la posición)

        status = EstadoPedido.ENVIADO;
        System.out.println("Estado del pedido: " + status);              // ENVIADO
        System.out.println("ID del estado: " + status.ordinal());        // 2

        // Una variable enum no admite valores inventados:
        // status = EstadoPedido.PERDIDO;   // error: no existe esa constante


        // ===== 3. Comprobar si un valor existe =====

        // Java no tiene un IsDefined: se comprueba el rango de posiciones
        System.out.println("¿Existe el valor 3? " + existePosicion(3));   // true
        System.out.println("¿Existe el valor 8? " + existePosicion(8));   // false


        // ===== 4. Comparar =====

        System.out.println("¿Está enviado? " + (status == EstadoPedido.ENVIADO));   // true: == funciona bien con enum
        System.out.println("¿Va más allá de pendiente? " + (status.compareTo(EstadoPedido.PENDIENTE) > 0));   // true: se compara por posición


        // ===== 5. Enum con switch =====

        switch (status) {
            case PENDIENTE:      // dentro del switch no se escribe EstadoPedido.
                System.out.println("El pedido está esperando");
                break;
            case PROCESADO:
                System.out.println("El pedido se está preparando");
                break;
            case ENVIADO:
                System.out.println("El pedido va de camino");
                break;
            case ENTREGADO:
                System.out.println("El pedido ya llegó");
                break;
        }


        // ===== 6. Recorrer todos los valores: values() =====

        for (EstadoPedido estado : EstadoPedido.values()) {
            System.out.println(estado.ordinal() + " = " + estado);
        }


        // ===== 7. Texto a enum: valueOf =====

        // valueOf exige el nombre exacto: lanza IllegalArgumentException si no existe
        System.out.println("Texto convertido: " + EstadoPedido.valueOf("enviado".toUpperCase()));

        try {
            EstadoPedido.valueOf("PERDIDO");
        } catch (IllegalArgumentException error) {
            System.out.println("No existe el estado Perdido");
        }


        // ===== 8. Valores propios: atributos del enum =====

        Prioridad prioridad = Prioridad.ALTA;
        System.out.println("Prioridad " + prioridad + " = " + prioridad.getValor());   // ALTA = 10


        // ===== 9. Un enum puede tener métodos =====

        System.out.println(EstadoPedido.ENTREGADO.getDescripcion());   // El pedido ya llegó
    }

    // IsDefined no existe en Java: se busca entre las posiciones de values()
    static boolean existePosicion(int posicion) {
        return posicion >= 0 && posicion < EstadoPedido.values().length;
    }
}


// ===== 1. Definir una enumeración =====

// Las constantes se escriben por convención en MAYÚSCULAS, separadas por comas.
// Se numeran automáticamente desde 0 (ordinal()): PENDIENTE = 0, PROCESADO = 1, ENVIADO = 2, ENTREGADO = 3
enum EstadoPedido {
    PENDIENTE("El pedido está esperando"),
    PROCESADO("El pedido se está preparando"),
    ENVIADO("El pedido va de camino"),
    ENTREGADO("El pedido ya llegó");        // aquí sí hace falta punto y coma: hay más código

    private final String descripcion;

    EstadoPedido(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getDescripcion() {
        return descripcion;
    }
}

// En Java los valores propios se guardan en un atributo (no hay "ALTA = 10" directo)
enum Prioridad {
    BAJA(1),
    MEDIA(5),
    ALTA(10);

    private final int valor;

    Prioridad(int valor) {
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }
}
