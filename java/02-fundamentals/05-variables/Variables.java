import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Variables {
    public static void main(String[] args) {
        System.out.println("Variables");

        String nombre = "informaticonfig";
        int edad = 30;
        boolean activo = true;
        LocalDateTime fecha = LocalDateTime.now();
        float precio = 30.5f;
        BigDecimal descuento = new BigDecimal("4.6");
        double descuento2 = 10.50;

        System.out.println("nombre = " + nombre);
        System.out.println("edad = " + edad);
        System.out.println("activo = " + activo);
        System.out.println("fecha = " + fecha);
        System.out.println("precio = " + precio);
        System.out.println("descuento = " + descuento);
        System.out.println("descuento2 = " + descuento2);

        // Recuerda para ver el tipo de dato usa getClass().getName()
        System.out.println("\nTipo de nombre: " + nombre.getClass().getName());
    }
}
