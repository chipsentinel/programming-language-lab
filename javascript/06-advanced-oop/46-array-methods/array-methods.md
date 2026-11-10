# Métodos de arrays en JavaScript

## El equivalente de LINQ

JavaScript no tiene un lenguaje de consultas como el **LINQ** de C# ni una API de streams como Java. Lo que tiene es que **los arrays traen métodos** para filtrar, ordenar, transformar y calcular, y se **encadenan**:

```javascript
const nombres = ["Ana", "Carlos", "Pedro", "Clara", "Andrés"];

const resultado = nombres
    .filter((n) => n.startsWith("C"))
    .sort();                                  // ["Carlos", "Clara"]
```

Todo esto existe también en **SQL** (el lenguaje de las bases de datos, que se verá más adelante), y los métodos de arrays se le parecen.

`(n) => n.startsWith("C")` es una **función flecha** (lambda): "dado `n`, devuelve si empieza por C". La flecha `=>` separa el parámetro de la expresión.

## Los métodos principales

| Quiero... | Método | En C# | En Java |
|---|---|---|---|
| **Filtrar** | `filter` | `Where` | `filter` |
| **Transformar** | `map` | `Select` | `map` |
| **Ordenar** | `sort` | `OrderBy` | `sorted` |
| **Calcular un valor** | `reduce` | `Sum`, `Aggregate` | `reduce` |
| ¿Algún elemento cumple? | `some` | `Any` | `anyMatch` |
| ¿Todos cumplen? | `every` | `All` | `allMatch` |
| El primero que cumple | `find` | `First` | `findFirst` |
| Cortar | `slice` | `Take`, `Skip` | `limit`, `skip` |

La mayoría **devuelven un array nuevo**, así que se pueden encadenar.

## `filter`: quedarse con unos

```javascript
const numeros = [10, 20, 30, 40, 50, 60, 1];

numeros.filter((n) => n % 2 === 0);      // [10, 20, 30, 40, 50, 60]
numeros.filter((n) => n > 25);           // [30, 40, 50, 60]
```

La función recibe cada elemento y devuelve `true` (se queda) o `false` (se descarta).

## `map`: transformar cada elemento

```javascript
numeros.map((n) => n * 2);               // [20, 40, 60, 80, 100, 120, 2]
personas.map((p) => p.nombre);           // solo los nombres
```

`map` devuelve **un array con la misma cantidad de elementos**, cada uno transformado.

## `sort`: ordenar

```javascript
[...numeros].sort((a, b) => b - a);      // [60, 50, 40, 30, 20, 10, 1]
```

Dos trampas importantes (lección 24):

1. **`sort()` cambia el array original.** Se ordena una **copia** con `[...numeros]` para no modificarlo.
2. **`sort()` sin argumentos ordena como texto**, así que `[10, 9, 1].sort()` da `[1, 10, 9]`. Para números hay que pasar la función de comparación `(a, b) => a - b` (ascendente) o `(a, b) => b - a` (descendente).

Para textos, `sort()` ya ordena alfabéticamente.

## `reduce`: calcular un único valor

`reduce` recorre el array **acumulando** un resultado:

```javascript
const suma = numeros.reduce((acumulado, n) => acumulado + n, 0);      // 211
```

- El primer parámetro es el **acumulado** hasta ahora; el segundo, el elemento actual.
- El `0` final es el **valor inicial**.
- Con `reduce` se calcula cualquier cosa: suma, producto, máximo...

Los demás cálculos:

```javascript
numeros.length;                          // 7: cantidad
suma / numeros.length;                   // media
Math.max(...numeros);                    // 60: máximo
Math.min(...numeros);                    // 1: mínimo
```

(Los tres puntos `...` "esparcen" el array en valores sueltos, lección 25.)

## Preguntas: `some`, `every`, `find`

```javascript
numeros.some((n) => n > 50);             // true: ¿alguno?
numeros.every((n) => n > 0);             // true: ¿todos?
numeros.find((n) => n > 25);             // 30: el primero que cumple (o undefined si no hay)
```

`find` devuelve `undefined` si ninguno cumple: **no lanza error**, a diferencia de `First` en C#.

## Cortar y quitar repetidos

```javascript
numeros.slice(0, 3);                     // [10, 20, 30]: los 3 primeros
numeros.slice(5);                        // [60, 1]: desde la posición 5
[...new Set([1, 2, 2, 3, 3, 3])];        // [1, 2, 3]: sin repetidos
```

`new Set(...)` es una colección sin duplicados; `[...set]` la convierte de nuevo en array.

## Agrupar: `Object.groupBy`

```javascript
const grupos = Object.groupBy(nombres, (n) => n[0]);

for (const [letra, lista] of Object.entries(grupos)) {
    console.log(`${letra}: ${lista.join(", ")}`);
}
```

```text
A: Ana, Andrés
C: Carlos, Clara
P: Pedro
```

Devuelve un **objeto**: clave → array de elementos. Es el `GROUP BY` de SQL. Existe desde **Node.js 21**; en versiones anteriores se agrupa con `reduce`.

## Con objetos

Los métodos de arrays brillan con **arrays de objetos**:

```javascript
const personas = [
    { nombre: "Sol", edad: 34 },
    { nombre: "Marcos", edad: 28 },
    { nombre: "Lucía", edad: 8 },
    { nombre: "Pedro", edad: 17 },
    { nombre: "Marta", edad: 45 },
];

const mayores = personas
    .filter((p) => p.edad >= 18)
    .sort((a, b) => a.edad - b.edad)
    .map((p) => p.nombre);

console.log(mayores.join(", "));         // Marcos, Sol, Marta
```

En SQL sería: `SELECT nombre FROM personas WHERE edad >= 18 ORDER BY edad`.

## No hay ejecución diferida

En C# una consulta LINQ se ejecuta **al recorrerla**. Aquí cada método se ejecuta **en el momento en que se llama** y devuelve un array ya calculado:

```javascript
const empiezanPorC = nombres.filter((n) => n.startsWith("C"));    // se calcula ahora

nombres.push("Cecilia");

console.log(empiezanPorC.join(", "));    // Carlos, Clara ← Cecilia no aparece
```

El resultado es una **foto** del momento en que se hizo el `filter`. Para volver a consultar con los datos nuevos hay que llamar otra vez a `filter`.

## `forEach`

`forEach` **recorre** el array sin devolver nada. Sirve para hacer algo con cada elemento:

```javascript
numeros.forEach((n, i) => console.log(`Posición ${i}: ${n}`));
```

Con él **no se puede** usar `break` ni `continue` (lección 21): para poder cortar se usa un `for` o `for...of`.

## Resumen

| Quiero... | Escribo... |
|---|---|
| Quedarme con unos | `.filter((x) => condición)` |
| Cambiar cada elemento | `.map((x) => ...)` |
| Ordenar (sin tocar el original) | `[...a].sort(...)` |
| Calcular un valor | `.reduce((acum, x) => ..., inicial)` |
| Preguntar | `.some(...)`, `.every(...)` |
| El primero | `.find(...)` |
| Sin repetidos | `[...new Set(a)]` |
| Agrupar | `Object.groupBy(a, ...)` |

## Ejemplo

```javascript
const numeros = [10, 20, 30, 40, 50, 60, 1];

const pares = numeros.filter((n) => n % 2 === 0);

console.log("Pares: " + pares.join(", "));
console.log("Suma de los pares: " + pares.reduce((total, n) => total + n, 0));
```

## Comandos

```bash
cd javascript/06-advanced-oop
node 46-array-methods/index.js
```

Fuente: [curso de C# de INFORMATICONFIG](https://www.youtube.com/watch?v=lvat7qWZ6tw&list=PL2Z95CSZ1N4F7mym8Ca16RoTDVAlIOPlT&index=46), vídeo 46.
