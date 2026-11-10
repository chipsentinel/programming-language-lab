# Manejo de excepciones en JavaScript

## Qué es una excepción

Un **error que ocurre mientras el programa se ejecuta** y que interrumpe su flujo normal. Si no se controla, **el programa se detiene** con un mensaje como:

```text
Uncaught Error: sin controlar
```

El **manejo de excepciones** permite **capturar** el error, saber qué ha pasado y decidir cómo seguir. Las excepciones son objetos de la clase `Error` (o de sus clases hijas, lección 38), que guardan su **tipo** (`name`), su **mensaje** (`message`) y la **pila de llamadas** (`stack`).

## `try` y `catch`

```javascript
try {
    const resultado = valor1 / valor2;
    console.log("Resultado: " + resultado);
} catch (error) {
    console.log("Error: " + error.message);
}
```

```text
try    { código que puede fallar }
catch  { qué hacer si falla }
```

- **`try`**: se **intenta** ejecutar el bloque. Si todo va bien, `catch` se ignora.
- **`catch`**: si ocurre una excepción, el programa **salta** aquí y no ejecuta lo que quedaba en el `try`.
- **`error`** es una variable (el nombre es libre) con la información del error.
- Solo hay **un** `catch` (no se filtra por tipo, como en C# y Java).

## La gran diferencia: JavaScript lanza pocas excepciones

Lo que en C# y Java **falla con una excepción**, en JavaScript **sigue funcionando** y da un valor especial:

| Operación | C# y Java | JavaScript |
|---|---|---|
| `20 / 0` | Excepción (con enteros) | `Infinity` |
| Convertir `"T"` a número | `FormatException` / `NumberFormatException` | `Number("T")` da `NaN` |
| `numeros[5]` (no existe) | Excepción de índice | `undefined` |
| Atributo inexistente (`objeto.x`) | No compila | `undefined` |

Por eso **un `try / catch` sobre estas operaciones no atrapa nada**:

```javascript
try {
    console.log(20 / 0);    // Infinity: no hay excepción
} catch (error) {
    console.log("nunca llega aquí");
}
```

La solución es **comprobar el caso** y **lanzar la excepción uno mismo** con `throw`.

## Lanzar una excepción: `throw`

```javascript
dividir(valor1, valor2) {
    try {
        if (valor2 === 0) {
            throw new RangeError("división entre cero");
        }

        console.log("Resultado: " + valor1 / valor2);
    } catch (error) {
        console.log("Error: no se puede dividir entre cero");
    }
}
```

Y para el formato:

```javascript
const numero = Number(texto);

if (Number.isNaN(numero)) {
    throw new TypeError("formato incorrecto");
}
```

Se puede lanzar cualquier cosa, pero lo correcto es **un objeto `Error`**.

## Tipos de error

| Tipo | Se usa para |
|---|---|
| `Error` | Error genérico |
| `TypeError` | Un valor no es del tipo esperado (o se llama a algo que no es una función) |
| `RangeError` | Un valor fuera del rango permitido |
| `SyntaxError` | Texto con formato incorrecto (al analizar JSON, por ejemplo) |
| `ReferenceError` | Usar una variable que no existe |

JavaScript **sí lanza** algunos por sí mismo:

```javascript
objeto.metodoInexistente();     // TypeError: objeto.metodoInexistente is not a function
variableQueNoExiste;            // ReferenceError: variableQueNoExiste is not defined
JSON.parse("esto no es json");  // SyntaxError
null.nombre;                    // TypeError: Cannot read properties of null
```

## Distinguir el tipo dentro del `catch`

Como solo hay un `catch`, el tipo se comprueba con **`instanceof`**:

```javascript
try {
    // ...
} catch (error) {
    if (error instanceof TypeError) {
        console.log("Error de formato");
    } else if (error instanceof RangeError) {
        console.log("Error: división entre cero");
    } else {
        console.log("Error inesperado: " + error.message);
    }
}
```

En C# y Java esto se resuelve con varios `catch`.

## El mensaje del error

```javascript
console.log(error.message);    // división entre cero
console.log(error.name);       // RangeError
console.log(error.stack);      // tipo + mensaje + pila de llamadas
```

- **`message`**: el mensaje corto, útil para mostrar o registrar.
- **`stack`**: todo el detalle, incluida la pila de llamadas. Es lo que conviene guardar para el desarrollador.
- Al usuario final conviene mostrarle **un mensaje comprensible**, no el técnico.

## `finally`

Un bloque que se ejecuta **siempre**, haya error o no, incluso con `return` dentro del `try`:

```javascript
try {
    // intentar algo
} catch (error) {
    // tratar el error
} finally {
    console.log("Fin de la operación");
}
```

Sirve para **liberar recursos**: cerrar un archivo, una conexión... (lección 42).

## Ejemplo

```javascript
function validarEdad(edad) {
    if (edad < 0) {
        throw new RangeError("La edad no puede ser negativa");
    }

    console.log("Edad válida: " + edad);
}

try {
    validarEdad(30);
    validarEdad(-5);
} catch (error) {
    console.log("Error: " + error.message);
} finally {
    console.log("Fin del programa");
}
```

## Comandos

```bash
cd javascript/06-advanced-oop
node 41-exception-handling/index.js
```

Fuente: [curso de C# de INFORMATICONFIG](https://www.youtube.com/watch?v=1Zf9Pkxv8zM&list=PL2Z95CSZ1N4F7mym8Ca16RoTDVAlIOPlT&index=41), vídeo 41.
