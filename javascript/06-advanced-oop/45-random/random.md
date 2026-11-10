# Números aleatorios en JavaScript

## `Math.random()`

JavaScript tiene **una sola función** para generar números aleatorios: **`Math.random()`**. No hay clase `Random` como en C# y Java.

```javascript
const numero = Math.random();
console.log(numero);     // 0.8429371029...
```

Devuelve un **decimal entre 0 (incluido) y 1 (excluido)**: puede salir `0`, pero nunca `1`. Todo lo demás (enteros, rangos, dados, elegir...) se **calcula** a partir de ella.

Los números son **pseudoaleatorios**: parecen impredecibles, pero salen de una **fórmula**. Sirven para:

- **Juegos**: tirar un dado, repartir cartas, hacer aparecer enemigos.
- **Simulaciones** y pruebas con datos de ejemplo.
- **Repartir o elegir** elementos al azar.

> **No sirven para contraseñas ni claves de seguridad**: ver el apartado final.

## Entero entre 0 y un máximo

Se **multiplica** por el tamaño del rango y se **redondea hacia abajo** con `Math.floor`:

```javascript
const numero = Math.floor(Math.random() * 50);       // 0, 1, 2, ... 49
```

Paso a paso:

| Paso | Valores posibles |
|---|---|
| `Math.random()` | `0` a `0.999...` |
| `* 50` | `0` a `49.99...` |
| `Math.floor(...)` | `0` a `49` |

**El máximo no se incluye**: nunca sale `50`. (Es lo mismo que `Next(50)` de C# y `nextInt(50)` de Java.)

Se usa `Math.floor`, no `Math.round`: con `round` los extremos saldrían la mitad de veces y el máximo sí podría aparecer.

## Entero entre un mínimo y un máximo

Se multiplica por la **amplitud** (`max - min`) y se **suma el mínimo**:

```javascript
const aleatorioEntre = (min, max) => Math.floor(Math.random() * (max - min)) + min;

aleatorioEntre(20, 25);      // 20, 21, 22, 23 o 24
```

| Límite | ¿Se incluye? |
|---|---|
| El **mínimo** (`20`) | **Sí** |
| El **máximo** (`25`) | **No** |

Es la misma regla que en C# y Java. Para un **dado**, del 1 al 6:

```javascript
const dado = aleatorioEntre(1, 7);      // 1, 2, 3, 4, 5 o 6
```

Un error muy habitual es escribir `aleatorioEntre(1, 6)` y no sacar nunca el 6. Conviene guardar esta función y reutilizarla.

### Cómo comprobar el rango

Un solo valor no demuestra nada. Pidiendo **muchos** y comprobando que todos caen dentro:

```javascript
let todosEnRango = true;
for (let i = 0; i < 1000; i++) {
    const valor = aleatorioEntre(20, 25);
    if (valor < 20 || valor > 24) todosEnRango = false;
}
```

## Decimales

`Math.random()` ya es un decimal. Para otro rango, se **multiplica** y se **suma**:

```javascript
Math.random() * 10;          // entre 0.0 y 10.0 (sin llegar a 10)
Math.random() * 5 + 2;       // entre 2.0 y 7.0
```

## Semilla (*seed*)

Una fórmula necesita un **punto de partida**: la **semilla**. Con la misma semilla, la secuencia es **siempre la misma**, lo que sirve para pruebas reproducibles.

**`Math.random()` no permite fijar la semilla**: cada ejecución es distinta y no hay forma de repetirla. Para secuencias reproducibles hay que usar un **generador propio** (existen algoritmos cortos, como *mulberry32*, o librerías como `seedrandom`).

En C# es `new Random(42)` y en Java `new Random(42)`.

## Usos habituales

### Cara o cruz

```javascript
const moneda = Math.random() < 0.5 ? "cara" : "cruz";
```

### Elegir un elemento de un array

Se elige una **posición** al azar: de `0` a `length - 1`:

```javascript
const colores = ["rojo", "verde", "azul", "amarillo"];
const elegido = colores[Math.floor(Math.random() * colores.length)];
```

### Mezclar un array

JavaScript **no tiene** un método para barajar. El algoritmo correcto es **Fisher-Yates**:

```javascript
const baraja = [1, 2, 3, 4, 5];

for (let i = baraja.length - 1; i > 0; i--) {
    const j = Math.floor(Math.random() * (i + 1));
    [baraja[i], baraja[j]] = [baraja[j], baraja[i]];     // intercambia dos posiciones
}
```

> **No uses** `array.sort(() => Math.random() - 0.5)`: parece que mezcla, pero el resultado **no es uniforme**.

## `Math.random()` no es seguro

Los números de `Math.random()` se pueden **predecir**. Por eso **no deben usarse** para contraseñas, claves, tokens ni nada de seguridad.

Para eso existe el módulo **`crypto`**, que usa el generador criptográfico del sistema:

```javascript
const { randomInt } = require("crypto");

const seguro = randomInt(1, 101);      // del 1 al 100 (el máximo no se incluye)
```

En el **navegador**: `crypto.getRandomValues(...)`.

## Resumen

| Quiero... | Escribo... |
|---|---|
| Un decimal de 0 a 1 | `Math.random()` |
| Un entero de 0 a 9 | `Math.floor(Math.random() * 10)` |
| Un entero de 1 a 100 | `Math.floor(Math.random() * 100) + 1` |
| Un dado | `Math.floor(Math.random() * 6) + 1` |
| Repetir la secuencia | Un generador propio con semilla |
| Algo seguro | `crypto.randomInt` |

## Ejemplo

```javascript
const aleatorioEntre = (min, max) => Math.floor(Math.random() * (max - min)) + min;

const dado = aleatorioEntre(1, 7);
console.log("Has sacado un " + dado);

const premios = ["libro", "entrada", "camiseta"];
console.log("Premio: " + premios[Math.floor(Math.random() * premios.length)]);
```

## Comandos

```bash
cd javascript/06-advanced-oop
node 45-random/index.js
```

Fuente: [curso de C# de INFORMATICONFIG](https://www.youtube.com/watch?v=j4WszlGEUF0&list=PL2Z95CSZ1N4F7mym8Ca16RoTDVAlIOPlT&index=45), vídeo 45.
