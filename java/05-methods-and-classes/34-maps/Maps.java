import java.util.LinkedHashMap;
import java.util.Map;

// Un mapa guarda pares clave-valor: cada valor se asocia a una clave ÚNICA,
// y se accede al valor indicando su clave.
// En Java es la interfaz Map<K, V>; la implementación habitual es HashMap.

public class Maps {

    public static void main(String[] args) {

        // ===== 1. Crear un mapa =====

        // Map<tipoClave, tipoValor>: aquí claves Integer y valores String (clases, no primitivos).
        // LinkedHashMap recuerda el orden de inserción; HashMap no garantiza ningún orden.
        Map<Integer, String> frutas = new LinkedHashMap<>();


        // ===== 2. Añadir pares: put(clave, valor) =====

        frutas.put(1, "manzanas");
        frutas.put(2, "peras");
        frutas.put(3, "bananas");
        frutas.put(4, "uvas");
        frutas.put(5, "fresas");

        System.out.println("Cantidad: " + frutas.size());   // 5


        // ===== 3. Leer por clave: get(clave) =====

        System.out.println("Clave 2: " + frutas.get(2));   // peras

        // Pedir una clave que no existe NO da error: devuelve null
        // System.out.println(frutas.get(9));   // null


        // ===== 4. Recorrer: for each con pares clave-valor =====

        for (Map.Entry<Integer, String> par : frutas.entrySet()) {
            System.out.println("Clave: " + par.getKey() + ", Valor: " + par.getValue());
        }


        // ===== 5. Comprobar si existe: containsKey y containsValue =====

        System.out.println("¿Existe la clave 3? " + frutas.containsKey(3));            // true
        System.out.println("¿Existe la clave 9? " + frutas.containsKey(9));            // false
        System.out.println("¿Existe el valor uvas? " + frutas.containsValue("uvas"));  // true


        // ===== 6. Las claves son únicas =====

        // put con una clave que ya existe REEMPLAZA el valor (no da error)...
        frutas.put(2, "peras verdes");
        System.out.println("Clave 2 tras cambiarla: " + frutas.get(2));

        // ...y crea el par si no existe
        frutas.put(6, "kiwis");
        System.out.println("Cantidad: " + frutas.size());   // 6


        // ===== 7. Leer sin riesgo: getOrDefault =====

        String fruta = frutas.get(9);
        if (fruta != null) {
            System.out.println("Clave 9: " + fruta);
        } else {
            System.out.println("La clave 9 no existe");
        }


        // ===== 8. Quitar un par: remove(clave) =====

        frutas.remove(1);
        System.out.println("Cantidad tras remove(1): " + frutas.size());   // 5

        // Las claves y los valores por separado
        System.out.println("Claves: " + String.join(", ", frutas.keySet().stream().map(String::valueOf).toList()));
        System.out.println("Valores: " + String.join(", ", frutas.values()));


        // ===== 9. Vaciar: clear =====

        frutas.clear();
        System.out.println("Mapa limpio. Cantidad: " + frutas.size());   // 0


        // ===== 10. Claves de texto: el caso más habitual =====

        Map<String, Integer> stock = new LinkedHashMap<>();
        stock.put("pan", 20);
        stock.put("leche", 12);
        stock.put("huevos", 30);

        System.out.println("Stock de leche: " + stock.get("leche"));   // 12

        stock.put("leche", stock.get("leche") - 2);   // se puede modificar el valor de una clave
        System.out.println("Stock de leche tras vender 2: " + stock.get("leche"));   // 10

        // Un valor por defecto cuando la clave no existe
        System.out.println("Stock de sal: " + stock.getOrDefault("sal", 0));   // 0
    }
}
