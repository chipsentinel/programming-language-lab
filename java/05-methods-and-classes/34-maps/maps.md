# Mapas en Java

## Qué es un mapa

Una colección que guarda **pares clave-valor**. Cada **valor** se asocia a una **clave única**, y para obtenerlo se indica la clave. En Java es la interfaz **`Map<K, V>`**, y la implementación habitual es `HashMap`.

Es como un diccionario de papel: se busca por la **palabra** (la clave) y se obtiene su **definición** (el valor).

```text
clave   →  valor
  1     →  "manzanas"
  2     →  "peras"
  3     →  "bananas"
```

## Para qué sirve

- **Buscar datos rápido** por una clave, sin recorrer todo.
- **Sustituir** arrays y listas cuando se necesita acceder por algo más significativo que una posición (un código, un nombre...).
- **Agrupar información relacionada**: producto → precio, país → capital, DNI → persona.

| | Lista | Mapa |
|---|---|---|
| Se accede por | **Posición** (0, 1, 2...) | **Clave** (la que se elija) |
| Elementos | Valores | Pares clave-valor |
| Duplicados | Sí | Valores sí, **claves no** |

## Crear un mapa

```java
import java.util.LinkedHashMap;
import java.util.Map;

Map<Integer, String> frutas = new LinkedHashMap<>();
```

```text
Map<  Integer  ,  String  >   frutas   =   new LinkedHashMap<>();
      └ tipo       └ tipo
        clave        valor
```

- Se indican **dos tipos** entre `< >`: el de la clave y el del valor. Deben ser **clases** (`Integer`, `String`...), no primitivos (`int`).
- Se declara con la interfaz `Map` y se crea con una implementación:

| Implementación | Orden de los pares |
|---|---|
| `HashMap` | **Ninguno garantizado** (la más rápida) |
| `LinkedHashMap` | El de **inserción** |
| `TreeMap` | Ordenado por **clave** |

Aquí se usa `LinkedHashMap` para que la salida siga el orden en que se añade.

## Añadir pares: `put`

```java
frutas.put(1, "manzanas");
frutas.put(2, "peras");
frutas.put(3, "bananas");

System.out.println(frutas.size());    // 3
```

La clave puede ser cualquier valor del tipo indicado; no tiene por qué empezar en 1 ni ser correlativa.

## Leer por clave: `get`

```java
System.out.println(frutas.get(2));    // peras
```

Si la clave **no existe**, `get` devuelve **`null`**: no lanza ningún error.

```java
System.out.println(frutas.get(9));    // null
```

Es cómodo, pero un `null` inesperado puede provocar un `NullPointerException` más adelante. Conviene comprobarlo (`if (fruta != null)`) o usar `getOrDefault`.

## Recorrer: `entrySet`

Se recorre el conjunto de **pares** (`entries`). Cada uno tiene `getKey()` y `getValue()`:

```java
for (Map.Entry<Integer, String> par : frutas.entrySet()) {
    System.out.println("Clave: " + par.getKey() + ", Valor: " + par.getValue());
}
```

```text
Clave: 1, Valor: manzanas
Clave: 2, Valor: peras
...
```

- **`Map.Entry<K, V>`** representa un par.
- También existen `keySet()` (solo las claves) y `values()` (solo los valores).

## Las claves son únicas

`put` con una clave que **ya existe** **reemplaza** el valor, sin error. Si no existe, crea el par:

```java
frutas.put(2, "peras verdes");    // reemplaza el valor de la clave 2
frutas.put(6, "kiwis");           // crea un par nuevo
```

En C# `Add` falla con una clave repetida; aquí no hay equivalente que avise: si se quiere evitar machacar un valor, se usa `putIfAbsent`.

## Métodos principales

| Método | Qué hace |
|---|---|
| `put(clave, valor)` | Añade un par, o reemplaza si la clave existe |
| `get(clave)` | Valor de la clave, o `null` |
| `getOrDefault(clave, def)` | Valor de la clave, o `def` si no existe |
| `containsKey(clave)` | ¿Existe la **clave**? |
| `containsValue(valor)` | ¿Existe el **valor**? |
| `remove(clave)` | Quita el par de esa clave |
| `keySet()` / `values()` | Todas las claves / todos los valores |
| `size()` | Número de pares |
| `clear()` | Quita todo |

### `containsKey` y `containsValue`

```java
System.out.println(frutas.containsKey(3));          // true
System.out.println(frutas.containsKey(9));          // false
System.out.println(frutas.containsValue("uvas"));   // true
```

Cuidado con confundirlos: `containsKey(3)` busca la **clave** 3, y `containsValue("uvas")` busca el **valor** `"uvas"`. `containsKey` es **muy rápido**; `containsValue` recorre todos los valores.

### `remove` y `clear`

```java
frutas.remove(1);    // quita el par de la clave 1
frutas.clear();      // quita todos los pares
```

`remove` recibe la **clave**, no el valor ni la posición.

## Claves de texto

Lo más habitual es usar **texto** como clave:

```java
Map<String, Integer> stock = new LinkedHashMap<>();
stock.put("pan", 20);
stock.put("leche", 12);
stock.put("huevos", 30);

System.out.println(stock.get("leche"));                    // 12
stock.put("leche", stock.get("leche") - 2);                // modifica el valor de esa clave
System.out.println(stock.getOrDefault("sal", 0));          // 0: la clave no existe
```

Las claves de texto distinguen mayúsculas de minúsculas: `"Leche"` y `"leche"` son claves distintas.

## Mostrar un mapa

```java
System.out.println(frutas);    // {1=manzanas, 2=peras, 3=bananas}
```

## Ejemplo

```java
import java.util.LinkedHashMap;
import java.util.Map;

public class Maps {
    public static void main(String[] args) {
        Map<String, String> capitales = new LinkedHashMap<>();

        capitales.put("España", "Madrid");
        capitales.put("Francia", "París");

        System.out.println(capitales.get("España"));

        for (Map.Entry<String, String> par : capitales.entrySet()) {
            System.out.println(par.getKey() + ": " + par.getValue());
        }
    }
}
```

## Comandos

```bash
cd java/05-methods-and-classes/34-maps
java Maps.java
```

Fuente: [curso de C# de INFORMATICONFIG](https://www.youtube.com/watch?v=SJk2uRIEqV0&list=PL2Z95CSZ1N4F7mym8Ca16RoTDVAlIOPlT&index=34), vídeo 34.
