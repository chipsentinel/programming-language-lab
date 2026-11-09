# Arrays en JavaScript

## Qué es un array

Una estructura de datos que **guarda varios valores en una sola variable**. En lugar de crear `precio1`, `precio2`, `precio3`..., se crea un único array `precios` con todos.

- Cada elemento tiene una **posición** (índice), y **la primera es la 0**.

A diferencia de C# y Java:

- **No tiene un tipo fijo**: un mismo array puede mezclar números, textos y más.
- **No tiene un tamaño fijo**: puede crecer y encogerse.
- Se escribe con **corchetes** `[ ]`, sin declarar un tipo.

## Crear un array

Lo habitual es escribir los valores entre corchetes:

```javascript
const numeros = [20, 15, 18, 93, 36, 50, 1, 2, 7, 4];
const personas = ["Juan", "Ana", "Antonio", "Samuel", "Elizabeth"];

console.log(numeros[0]);   // 20
console.log(numeros[3]);   // 93
```

Para un array de un tamaño dado existe `new Array(5)`, pero crea cinco **huecos vacíos**, no ceros:

```javascript
const enteros = new Array(5);
console.log(enteros[2]);   // undefined
```

En C# y Java un array nuevo de enteros se rellena con `0`; en JavaScript no hay valor por defecto: el hueco vale `undefined`.

### `const` y el contenido

Un array declarado con `const` **no se puede reasignar**, pero su contenido **sí se puede modificar**: ordenar, añadir, rellenar... (como se vio en la lección de constantes).

## Guardar y leer por posición

```javascript
enteros[0] = 30;
enteros[1] = 6;
enteros[2] = 112;
enteros[3] = 20000;
enteros[4] = 60;

console.log(enteros[2]);   // 112
```

```text
posición:  0     1    2     3       4
valor:     30    6    112   20000   60
```

### Fuera de rango: sin error

En C# y Java usar una posición que no existe da un error. En JavaScript **no**:

```javascript
console.log(enteros[5]);   // undefined: leer una posición inexistente
enteros[5] = 1;            // válido: el array crece a 6 elementos
```

Es cómodo, pero también oculta errores: una posición mal calculada da `undefined` en lugar de avisar.

## Longitud: `length`

El número de elementos. Sin paréntesis:

```javascript
console.log(numeros.length);   // 10
```

La última posición es siempre `length - 1`.

## Recorrer un array

Con un `for`, usando `length` como límite:

```javascript
for (let i = 0; i < numeros.length; i++) {
    console.log(`numeros[${i}] = ${numeros[i]}`);
}
```

- `i` empieza en 0 y llega a `length - 1`: `i < numeros.length`.
- Es la mejor forma de **hacer algo con cada elemento**.

## Mostrar todo en una línea: `join`

Une los elementos con el separador indicado:

```javascript
console.log(numeros.join(", "));
// 20, 15, 18, 93, 36, 50, 1, 2, 7, 4
```

`join` **devuelve un texto**. Es más corto que un `for`, pero solo sirve para mostrar.

## Funciones de los arrays

| Función | Qué hace |
|---|---|
| `a.sort()` | Ordena (**como texto** por defecto) |
| `a.reverse()` | Invierte el orden actual |
| `a.indexOf(valor)` | Posición del valor, o `-1` |
| `a.fill(valor)` | Rellena todo con un valor |
| `a.push(valor)` | Añade un elemento al final |
| `a.length = 0` | Quita todos los elementos |

### Ordenar: cuidado

`sort()` sin argumentos **convierte los números a texto** y los ordena alfabéticamente:

```javascript
[20, 15, 18, 93, 36, 50, 1, 2, 7, 4].sort();
// 1, 15, 18, 2, 20, 36, 4, 50, 7, 93    ← "15" va antes que "2"
```

Para ordenar números hace falta una **función de comparación** (las funciones se verán más adelante):

```javascript
numeros.sort((a, b) => a - b);
console.log(numeros.join(", "));   // 1, 2, 4, 7, 15, 18, 20, 36, 50, 93
```

Con textos, `sort()` ya ordena alfabéticamente: `Ana, Antonio, Elizabeth, Juan, Samuel`.

`sort` **cambia el propio array**. Para conservar el original se ordena una copia: `numeros.slice().sort(...)`.

### Invertir

```javascript
numeros.reverse();
console.log(numeros.join(", "));   // 93, 50, 36, 20, 18, 15, 7, 4, 2, 1
```

Invertir solo da la vuelta a lo que haya, **no ordena**. Para tener de mayor a menor: `sort` y luego `reverse`.

### Buscar

```javascript
console.log(personas.indexOf("Ana"));    // posición donde está
console.log(personas.indexOf("Luis"));   // -1: no existe
```

El `-1` significa "no encontrado", igual que en `string.indexOf`.

### Rellenar y vaciar

```javascript
numeros.fill(99);       // 99, 99, 99, ... en todas las posiciones
personas.length = 0;    // el array queda sin elementos
```

A diferencia de C# y Java, aquí **sí se puede quitar** posiciones: tras `length = 0` el array mide 0.

### Añadir elementos

```javascript
const frutas = ["manzana", "pera"];
frutas.push("uva");
console.log(frutas.join(", "));   // manzana, pera, uva
```

## Un array puede mezclar tipos

```javascript
enteros[0] = "hola";   // válido: no hay un tipo fijo
```

Es posible, pero rara vez conviene: lo normal es que un array guarde valores del mismo tipo.

## Ejemplo

```javascript
const numeros = [20, 15, 18, 93, 36];

console.log("Elementos: " + numeros.length);

numeros.sort((a, b) => a - b);
console.log("Ordenado: " + numeros.join(", "));

for (let i = 0; i < numeros.length; i++) {
    console.log(`Posición ${i}: ${numeros[i]}`);
}
```

## Comandos

```bash
cd javascript/04-data-structures
node 24-arrays/index.js
```

Fuente: [curso de C# de INFORMATICONFIG](https://www.youtube.com/watch?v=MiOWZ0s3Wx8&list=PL2Z95CSZ1N4F7mym8Ca16RoTDVAlIOPlT&index=24), vídeo 24.
