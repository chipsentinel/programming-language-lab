import java.security.SecureRandom;
import java.util.Random;

// La clase Random genera números PSEUDOALEATORIOS: parecen impredecibles, pero salen de una fórmula.
// Sirve para juegos, simulaciones, repartir datos al azar, elegir elementos...
// NO sirve para contraseñas ni claves de seguridad (ver el apartado final).
//
// Las líneas que empiezan por "Ejemplo:" cambian en cada ejecución.

public class RandomLesson {

    public static void main(String[] args) {

        // ===== 1. Crear un objeto Random =====

        Random aleatorio = new Random();

        // nextInt() sin argumentos: un entero cualquiera, positivo o NEGATIVO
        int numero1 = aleatorio.nextInt();
        System.out.println("Ejemplo: número aleatorio 1: " + numero1);
        // Para solo positivos se indica un máximo (apartado siguiente) o se usa Math.abs con cuidado
        System.out.println("nextInt() es un int válido: " + (numero1 >= Integer.MIN_VALUE));


        // ===== 2. Entre 0 y un máximo: nextInt(max) =====

        // El máximo NO se incluye: nextInt(50) da de 0 a 49
        int numero2 = aleatorio.nextInt(50);
        System.out.println("Ejemplo: número aleatorio entre 0 y 49: " + numero2);

        // Para comprobarlo se piden muchos valores y se mira si TODOS caen en el rango
        boolean todosEnRango = true;
        for (int i = 0; i < 1000; i++) {
            int valor = aleatorio.nextInt(50);
            if (valor < 0 || valor >= 50) todosEnRango = false;
        }
        System.out.println("1000 valores de nextInt(50) están entre 0 y 49: " + todosEnRango);


        // ===== 3. Entre un mínimo y un máximo: nextInt(min, max) =====

        // El mínimo SÍ se incluye y el máximo NO: nextInt(20, 25) da 20, 21, 22, 23 o 24
        // (nextInt con dos argumentos existe desde Java 17)
        int numero3 = aleatorio.nextInt(20, 25);
        System.out.println("Ejemplo: número aleatorio entre 20 y 24: " + numero3);

        boolean todosEn20a24 = true;
        boolean salio20 = false;
        boolean salio25 = false;
        for (int i = 0; i < 1000; i++) {
            int valor = aleatorio.nextInt(20, 25);
            if (valor < 20 || valor > 24) todosEn20a24 = false;
            if (valor == 20) salio20 = true;
            if (valor == 25) salio25 = true;
        }
        System.out.println("1000 valores de nextInt(20, 25) están entre 20 y 24: " + todosEn20a24);
        System.out.println("El mínimo (20) puede salir: " + salio20);
        System.out.println("El máximo (25) nunca sale: " + !salio25);


        // ===== 4. Decimales: nextDouble =====

        // nextDouble() da un decimal entre 0.0 (incluido) y 1.0 (excluido)
        double decimal1 = aleatorio.nextDouble();
        System.out.println("nextDouble() está entre 0 y 1: " + (decimal1 >= 0 && decimal1 < 1));

        // Para otro rango se multiplica: entre 0.0 y 10.0
        double decimal2 = aleatorio.nextDouble() * 10.0;
        System.out.println("Ejemplo: decimal entre 0 y 10: " + decimal2);
        System.out.println("Está entre 0 y 10: " + (decimal2 >= 0 && decimal2 < 10));


        // ===== 5. Semilla: la misma semilla repite la secuencia =====

        // Un Random es una fórmula que parte de una "semilla". Con la misma semilla da SIEMPRE los mismos números.
        // Sin semilla, usa una que depende del reloj y por eso cambia en cada ejecución.
        Random a = new Random(42);
        Random b = new Random(42);

        boolean iguales = true;
        for (int i = 0; i < 5; i++) {
            if (a.nextInt(100) != b.nextInt(100)) iguales = false;
        }
        System.out.println("Con la misma semilla se repite la secuencia: " + iguales);   // útil para pruebas reproducibles


        // ===== 6. Usos habituales =====

        // Tirar un dado: del 1 al 6 (el máximo 7 no se incluye)
        int dado = aleatorio.nextInt(1, 7);
        System.out.println("Ejemplo: dado: " + dado);
        System.out.println("Dado válido (1-6): " + (dado >= 1 && dado <= 6));

        // Cara o cruz
        String moneda = aleatorio.nextInt(2) == 0 ? "cara" : "cruz";
        System.out.println("Ejemplo: moneda: " + moneda);

        // Elegir un elemento al azar de un array: una posición aleatoria
        String[] colores = { "rojo", "verde", "azul", "amarillo" };
        String elegido = colores[aleatorio.nextInt(colores.length)];
        System.out.println("Ejemplo: color elegido: " + elegido);
        System.out.println("El color existe en el array: " + java.util.Arrays.asList(colores).contains(elegido));


        // ===== 7. Math.random() y ThreadLocalRandom =====

        // Math.random() da un decimal entre 0.0 y 1.0 sin crear objetos
        int conMath = (int) (Math.random() * 100) + 1;   // del 1 al 100
        System.out.println("Math.random() escalado está entre 1 y 100: " + (conMath >= 1 && conMath <= 100));

        // ThreadLocalRandom es un Random compartido, listo para usar (Java 7+)
        int compartido = java.util.concurrent.ThreadLocalRandom.current().nextInt(1, 101);
        System.out.println("ThreadLocalRandom.nextInt(1, 101) está entre 1 y 100: " + (compartido >= 1 && compartido <= 100));


        // ===== 8. Random NO es seguro para contraseñas =====

        // Con la semilla se pueden predecir los números: para seguridad se usa SecureRandom
        int seguro = new SecureRandom().nextInt(1, 101);
        System.out.println("SecureRandom.nextInt(1, 101) está entre 1 y 100: " + (seguro >= 1 && seguro <= 100));
    }
}
