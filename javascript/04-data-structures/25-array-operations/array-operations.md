# Operaciones con arrays en JavaScript

## Qué se ve en esta lección

Operaciones habituales con arrays: **sumar** sus valores, **filtrar** los que cumplen una condición, **copiar** un array y **operar dos arrays** posición a posición.

```javascript
const numeros = [12, 233, 5, 658, 45555];
```

En JavaScript estas operaciones son **funciones del propio array** (`reduce`, `filter`, `map`...), disponibles sin importar nada. Es el equivalente de LINQ en C# y de los streams en Java.

## Sumar: `reduce`

No existe un `sum`. Se usa `reduce`, que **recorre el array acumulando un resultado**:

```javascript
const total = numeros.reduce((acumulado, x) => acumulado + x, 0);
console.log("Total de la suma de valores: " + total);   // 46463
```

- **`(acumulado, x) => acumulado + x`** es una **expresión lambda** (función flecha): recibe lo acumulado hasta ahora y el elemento actual, y devuelve el nuevo acumulado.
- **`0`** es el valor inicial del acumulado.
- Las funciones flecha se explicarán más adelante.

## Máximo, mínimo y media

```javascript
console.log(Math.max(...numeros));    // 45555
console.log(Math.min(...numeros));    // 5
console.log(total / numeros.length);  // 9292.6
```

`Math.max` y `Math.min` aceptan **valores sueltos**, no un array. Los **tres puntos `...`** (operador de propagación) "esparcen" el array en valores: `Math.max(...[1, 2, 3])` equivale a `Math.max(1, 2, 3)`. La media se calcula dividiendo la suma entre `length`.

## Filtrar: `filter`

Se queda con los elementos que **cumplen una condición**:

```javascript
const mayoresQue12 = numeros.filter(x => x > 12);

console.log(mayoresQue12.join(", "));   // 233, 658, 45555
```

- **`x => x > 12`** es una función flecha: "para cada elemento `x`, ¿es `x` mayor que 12?". Lo que hay después de `=>` es la condición.
- `filter` ya devuelve un **array nuevo**; no hace falta convertirlo, como sí ocurre en C# (`ToArray`) y Java.
- El 12 no aparece: la condición es `>`, no `>=`.

## Copiar un array

### Asignar no copia

```javascript
const alias = numeros;
alias[0] = 999;

console.log(numeros[0]);   // 999 ← ¡también cambió!
```

Un array es un **objeto**. `alias = numeros` no crea otro array: hace que `alias` apunte **al mismo**. Cambiar uno cambia el otro. Que sea `const` no lo evita: `const` impide reasignar, no modificar el contenido.

### Una copia independiente

```javascript
const cantidades = [...numeros];   // propagación: copia los elementos
cantidades[0] = 1;

console.log(numeros.join(", "));      // 12, 233, 5, 658, 45555
console.log(cantidades.join(", "));   // 1, 233, 5, 658, 45555
```

`[...numeros]` crea un array nuevo con los mismos elementos. Equivale a `numeros.slice()`. Después, cambiar `cantidades` no afecta a `numeros`.

La copia es **superficial**: si los elementos fueran arrays u objetos, seguirían compartidos.

## Operar dos arrays elemento a elemento

Con dos arrays de la misma longitud:

```javascript
const array1 = [10, 20, 30, 40];
const array2 = [1, 2, 3, 4];
```

`array1 + array2` **no suma posición a posición**: convierte los arrays en texto y los concatena:

```javascript
console.log(array1 + array2);   // "10,20,30,401,2,3,4"
```

Hay que recorrerlos con un `for`:

```javascript
const suma = [];

for (let i = 0; i < array1.length; i++) {
    suma[i] = array1[i] + array2[i];
}

console.log(suma.join(", "));   // 11, 22, 33, 44
```

```text
posición:     0    1    2    3
array1:       10   20   30   40
array2:       1    2    3    4
suma:         11   22   33   44
```

El resultado de la posición `i` sale de la posición `i` de cada array.

| Operación | Resultado con los arrays de arriba |
|---|---|
| `array1[i] + array2[i]` | `11, 22, 33, 44` |
| `array1[i] - array2[i]` | `9, 18, 27, 36` |
| `array1[i] * array2[i]` | `10, 40, 90, 160` |
| `array1[i] / array2[i]` | `10, 10, 10, 10` |

### Con `map`

`map` aplica una operación a **cada elemento** y devuelve un array nuevo. El segundo parámetro es la posición:

```javascript
const sumaConMap = array1.map((valor, i) => valor + array2[i]);
console.log(sumaConMap.join(", "));   // 11, 22, 33, 44
```

Es la forma habitual en JavaScript: más corta que el `for`.

- El array de resultado puede empezar vacío (`[]`): en JavaScript crece solo.
- Con **longitudes distintas** no hay error: las posiciones inexistentes valen `undefined`, y `10 + undefined` da `NaN`.
- La división **no es entera**: `5 / 2` da `2.5`.

## Ejemplo

```javascript
const numeros = [12, 233, 5, 658, 45555];

console.log("Suma: " + numeros.reduce((acumulado, x) => acumulado + x, 0));
console.log("Mayores que 12: " + numeros.filter(x => x > 12).join(", "));

const copia = [...numeros];
console.log("Copia: " + copia.join(", "));
```

## Comandos

```bash
cd javascript/04-data-structures
node 25-array-operations/index.js
```

Fuente: [curso de C# de INFORMATICONFIG](https://www.youtube.com/watch?v=bo6NbdeYJRY&list=PL2Z95CSZ1N4F7mym8Ca16RoTDVAlIOPlT&index=25), vídeo 25.
