# Mapas en JavaScript

## Qué es un mapa

Una colección que guarda **pares clave-valor**. Cada **valor** se asocia a una **clave única**, y para obtenerlo se indica la clave. En JavaScript la clase es **`Map`**.

Es como un diccionario de papel: se busca por la **palabra** (la clave) y se obtiene su **definición** (el valor).

```text
clave   →  valor
  1     →  "manzanas"
  2     →  "peras"
  3     →  "bananas"
```

## Para qué sirve

- **Buscar datos rápido** por una clave, sin recorrer todo.
- **Sustituir** arrays cuando se necesita acceder por algo más significativo que una posición (un código, un nombre...).
- **Agrupar información relacionada**: producto → precio, país → capital, DNI → persona.

| | Array | Mapa |
|---|---|---|
| Se accede por | **Posición** (0, 1, 2...) | **Clave** (la que se elija) |
| Elementos | Valores | Pares clave-valor |
| Duplicados | Sí | Valores sí, **claves no** |

## Crear un mapa

```javascript
const frutas = new Map();
```

A diferencia de C# y Java, **no se indican los tipos**: las claves y los valores pueden ser de **cualquier tipo**, incluso mezclados. Un `Map` **recuerda el orden de inserción**.

También se puede crear con valores iniciales, como una lista de pares `[clave, valor]`:

```javascript
const stock = new Map([
    ["pan", 20],
    ["leche", 12],
    ["huevos", 30],
]);
```

## Añadir pares: `set`

```javascript
frutas.set(1, "manzanas");
frutas.set(2, "peras");
frutas.set(3, "bananas");

console.log(frutas.size);    // 3
```

`size` es una **propiedad**, sin paréntesis (como `length` en un array).

## Leer por clave: `get`

```javascript
console.log(frutas.get(2));    // peras
```

Si la clave **no existe**, `get` devuelve **`undefined`**: no lanza ningún error.

```javascript
console.log(frutas.get(9));    // undefined
```

Hay que comprobarlo o dar un valor por defecto con `??`: `stock.get("sal") ?? 0`.

## Recorrer: `for...of`

Cada elemento es un **par** `[clave, valor]`, que se puede **desestructurar** directamente en dos variables:

```javascript
for (const [clave, valor] of frutas) {
    console.log(`Clave: ${clave}, Valor: ${valor}`);
}
```

```text
Clave: 1, Valor: manzanas
Clave: 2, Valor: peras
...
```

- `const [clave, valor]` toma el par y lo reparte en dos variables.
- También existen `keys()` (solo las claves) y `values()` (solo los valores).

## Las claves son únicas

`set` con una clave que **ya existe** **reemplaza** el valor, sin error. Si no existe, crea el par:

```javascript
frutas.set(2, "peras verdes");    // reemplaza el valor de la clave 2
frutas.set(6, "kiwis");           // crea un par nuevo
```

## Métodos principales

| Método | Qué hace |
|---|---|
| `set(clave, valor)` | Añade un par, o reemplaza si la clave existe |
| `get(clave)` | Valor de la clave, o `undefined` |
| `has(clave)` | ¿Existe la **clave**? |
| `delete(clave)` | Quita el par de esa clave |
| `keys()` / `values()` | Todas las claves / todos los valores |
| `size` | Número de pares |
| `clear()` | Quita todo |

### `has`

```javascript
console.log(frutas.has(3));    // true
console.log(frutas.has(9));    // false
```

`has` busca la **clave**. No existe un `containsValue`: para buscar un **valor** se pasan los valores a un array con `...` y se usa `includes`:

```javascript
console.log([...frutas.values()].includes("uvas"));    // true
```

### Mostrar claves y valores

`keys()` y `values()` no devuelven arrays, sino **iteradores**. Con `...` se convierten en un array:

```javascript
console.log([...frutas.keys()].join(", "));      // 2, 3, 4, 5
console.log([...frutas.values()].join(", "));    // peras, bananas, uvas, fresas
```

### `delete` y `clear`

```javascript
frutas.delete(1);    // quita el par de la clave 1
frutas.clear();      // quita todos los pares
```

`delete` recibe la **clave**, no el valor ni la posición.

## Claves de texto

Lo más habitual es usar **texto** como clave:

```javascript
console.log(stock.get("leche"));                        // 12
stock.set("leche", stock.get("leche") - 2);             // modifica el valor de esa clave
console.log(stock.get("sal") ?? 0);                     // 0: la clave no existe
```

Las claves de texto distinguen mayúsculas de minúsculas: `"Leche"` y `"leche"` son claves distintas.

## Un objeto `{ }` también es un diccionario

En JavaScript es muy común usar un **objeto literal** como diccionario simple de claves de texto:

```javascript
const precios = { pan: 1.2, leche: 0.95 };

console.log(precios.leche);       // 0.95
console.log(precios["leche"]);    // 0.95
precios.huevos = 2.5;             // añade un par
console.log(Object.keys(precios).join(", "));    // pan, leche, huevos
```

| | `Map` | Objeto `{ }` |
|---|---|---|
| Claves | **De cualquier tipo** | Solo texto (los números se convierten) |
| Tamaño | `size` | `Object.keys(o).length` |
| Orden | El de inserción | Casi siempre el de inserción |
| Uso | Datos que se añaden y quitan con frecuencia | Estructuras fijas |

## Ejemplo

```javascript
const capitales = new Map();

capitales.set("España", "Madrid");
capitales.set("Francia", "París");

console.log(capitales.get("España"));

for (const [pais, capital] of capitales) {
    console.log(`${pais}: ${capital}`);
}
```

## Comandos

```bash
cd javascript/05-methods-and-classes
node 34-maps/index.js
```

Fuente: [curso de C# de INFORMATICONFIG](https://www.youtube.com/watch?v=SJk2uRIEqV0&list=PL2Z95CSZ1N4F7mym8Ca16RoTDVAlIOPlT&index=34), vídeo 34.
