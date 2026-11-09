import java.util.Scanner;

public class MathClass {

    public static void main(String[] args) {

        // La clase Math reúne funciones matemáticas ya hechas: Math.funcion(valores).
        // (Qué son una clase y un método se verá más adelante: por ahora solo se usan.)


        // ===== 1. Potencia: Math.pow =====

        int numero = 4;

        System.out.println("Potenciación (4 al cuadrado): " + Math.pow(numero, 2));   // 16.0
        System.out.println("Potenciación (4 al cubo): " + Math.pow(numero, 3));       // 64.0


        // ===== 2. Raíz cuadrada: Math.sqrt =====

        System.out.println("Raíz cuadrada de 4: " + Math.sqrt(numero));   // 2.0


        // ===== 3. Valor absoluto: Math.abs =====

        numero = -4;
        System.out.println("Valor absoluto de -4: " + Math.abs(numero));   // 4


        // ===== 4. Redondeo: ceil, floor y round =====

        double numero2 = 50.23;

        System.out.println("Hacia arriba (ceil): " + Math.ceil(numero2));      // 51.0
        System.out.println("Hacia abajo (floor): " + Math.floor(numero2));      // 50.0
        System.out.println("Normal (round) 50.23: " + Math.round(numero2));     // 50

        numero2 = 50.53;
        System.out.println("Normal (round) 50.53: " + Math.round(numero2));     // 51


        // ===== 5. Redondear el punto medio (.5) =====

        // En Java, Math.round sube cuando está justo en la mitad (redondeo "de toda la vida")
        System.out.println(Math.round(2.5));   // 3
        System.out.println(Math.round(3.5));   // 4

        // Math.round devuelve un entero (long). Para redondear a 2 decimales:
        System.out.println(Math.round(3.14159 * 100) / 100.0);   // 3.14


        // ===== 6. Máximo y mínimo: Math.max y Math.min =====

        numero = 4;

        System.out.println("Máximo: " + Math.max(numero, numero2));   // 50.53
        System.out.println("Mínimo: " + Math.min(numero, numero2));   // 4.0


        // ===== 7. Pi y funciones trigonométricas =====

        System.out.println("Valor de pi: " + Math.PI);               // 3.141592653589793
        System.out.println("Seno de 90 grados: " + Math.sin(Math.PI / 2));   // 1.0 (los ángulos van en radianes)
        System.out.println("Coseno de 0: " + Math.cos(0));                    // 1.0


        // ===== 8. Ejercicio: área de un círculo =====

        Scanner scanner = new Scanner(System.in);

        System.out.println("Indique el radio del círculo...");
        double radio = Double.parseDouble(scanner.nextLine());

        double area = Math.PI * Math.pow(radio, 2);   // pi * radio al cuadrado

        System.out.println("Área del círculo: " + area);
        System.out.println("Área redondeada: " + Math.round(area));
        System.out.println("Área con 2 decimales: " + Math.round(area * 100) / 100.0);

        scanner.close();
    }
}
