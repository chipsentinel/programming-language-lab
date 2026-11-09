# Bucle `while` en JavaScript

## Qué es un bucle

Una estructura de control que **repite un bloque de código** varias veces: un número determinado, o mientras se cumpla una condición. Sin bucles habría que escribir la misma instrucción una y otra vez.

## El bucle `while`

Repite el bloque **mientras la condición sea verdadera**. La condición se comprueba **antes de cada vuelta**.

```javascript
let valor = 0;

while (valor < 3) {
    console.log("Valor es: " + valor);
    valor++;
}

console.log("Fin del bucle");
```

```text
Valor es: 0
Valor es: 1
Valor es: 2
Fin del bucle
```

```text
while (condición) {
    código que se repite
}
```

## Cómo se ejecuta

| Vuelta | `valor` al comprobar | ¿`valor < 3`? | Qué pasa |
|---|---|---|---|
| 1 | `0` | Sí | Escribe y `valor` pasa a `1` |
| 2 | `1` | Sí | Escribe y `valor` pasa a `2` |
| 3 | `2` | Sí | Escribe y `valor` pasa a `3` |
| 4 | `3` | **No** | El bucle termina |

## Las tres partes de un bucle con contador

1. **Inicialización**: `let valor = 0;`, antes del bucle.
2. **Condición**: `valor < 3`, en el `while`.
3. **Actualización**: `valor++;`, dentro del bucle.

El contador se declara con `let`, no con `const`, porque su valor cambia.

## Bucle infinito

Si la variable **nunca cambia**, la condición siempre se cumple y el bucle no termina. El programa se queda repitiendo sin parar.

```javascript
let contador = 0;
while (contador < 3) {
    console.log("Contador es: " + contador);   // falta contador++
}
```

Para detenerlo hay que cerrar la consola o pulsar `Ctrl+C`. Casi siempre es un olvido de la **actualización**.

## Incluir el límite y empezar en 1

- `valor < 3` repite hasta el 2; `valor <= 3` **incluye** el 3.
- Empezando en `0` hay una vuelta más que empezando en `1`: `0, 1, 2, 3` son cuatro valores, `1, 2, 3` son tres.

```javascript
let numero = 1;

while (numero <= 3) {
    console.log("Vuelta " + numero);
    numero++;
}
```

## Decisiones dentro del bucle

Dentro del bucle se pueden usar condicionales (`if`, `switch`) para que ciertas vueltas hagan algo distinto:

```javascript
numero = 1;

while (numero <= 5) {
    if (numero === 2) {
        console.log("Soy la vuelta dos");
    } else {
        console.log("Vuelta " + numero);
    }

    numero++;
}
```

## Ejemplo clásico: tabla de multiplicar

```javascript
const TABLA = 8;
let numero = 1;

console.log(`Tabla del ${TABLA}`);

while (numero <= 10) {
    console.log(`${TABLA} x ${numero} = ${TABLA * numero}`);
    numero++;
}

console.log("Fin de la tabla");
```

```text
Tabla del 8
8 x 1 = 8
8 x 2 = 16
...
8 x 10 = 80
Fin de la tabla
```

- `TABLA` es **fija** (`const`), y `numero` es el **contador** que cambia en cada vuelta.
- Las plantillas con `` `...${}...` `` evitan concatenar con `+`.
- Para otra tabla basta con cambiar el valor de `TABLA`.

## `while` puede no ejecutarse nunca

Como la condición se comprueba **antes**, si ya es falsa desde el principio el bloque no se ejecuta ni una vez.

```javascript
let intentos = 5;

while (intentos < 3) {
    console.log("Esto no se imprime nunca");
}
```

Si hace falta que el bloque se ejecute **al menos una vez**, se usa `do-while` (lección 20).

## Acumular un resultado

Un bucle sirve para ir sumando valores en una variable:

```javascript
let suma = 0;
let numero = 1;

while (numero <= 5) {
    suma += numero;
    numero++;
}

console.log("Suma de 1 a 5: " + suma);   // 15
```

## Ejemplo

```javascript
let numero = 1;

while (numero <= 3) {
    console.log("Vuelta " + numero);
    numero++;
}

console.log("Fin del bucle");
```

## Comandos

```bash
cd javascript/03-control-flow
node 18-while-loop/index.js
```

Fuente: [curso de C# de INFORMATICONFIG](https://www.youtube.com/watch?v=tq9tWc8P0Rs&list=PL2Z95CSZ1N4F7mym8Ca16RoTDVAlIOPlT&index=18), vídeo 18.
