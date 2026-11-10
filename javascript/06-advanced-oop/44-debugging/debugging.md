# Depuración (*debugging*) en JavaScript

## Qué es depurar

El proceso de **identificar, analizar y corregir errores** en un programa. Se hace con el **depurador**: una herramienta que ejecuta el programa **paso a paso**, lo **detiene** donde se le indica y permite **ver el valor de las variables** en cada momento.

Sirve para:

- **Encontrar la causa** de un fallo, no solo ver que existe.
- **Entender el flujo** real del programa (por dónde pasa, qué valen las variables).
- Mejorar la calidad del código y **evitar problemas** cuando ya está en uso.

## Tipos de errores

| Tipo | Qué pasa | Quién lo detecta |
|---|---|---|
| **De sintaxis** | El código está mal escrito (`SyntaxError`) | Node, **antes** de ejecutar nada |
| **En tiempo de ejecución** | El programa falla mientras corre (`TypeError`, `ReferenceError`) | Una **excepción** (lección 41) |
| **Lógicos** | El programa funciona, pero **no hace lo que se quería** | **Nadie avisa**: hay que depurar |

Los errores **lógicos** son los más difíciles: no hay mensaje de error, solo un resultado equivocado. En JavaScript hay **más**, porque el lenguaje deja pasar cosas que C# y Java rechazarían (`NaN`, `undefined`, `"5" + 3`).

## Un ejemplo con error lógico

```javascript
const a = 30;
const b = 20;

const suma = a + b;
const resta = a - b;
const multiplicacion = a * b;

console.log("Suma: " + suma);
console.log("Resta: " + resta);
console.log("Multiplicación: " + suma);       // ← error: debería ser multiplicacion
```

```text
Suma: 50
Resta: 10
Multiplicación: 50      ← debería ser 600
```

El programa no lanza excepciones. Solo el resultado, `50`, es **ilógico**: `30 × 20` no es `50`.

## Puntos de interrupción (*breakpoints*)

Un punto de interrupción indica **dónde detener** la ejecución.

- En **VS Code** se pone con **un clic en el margen izquierdo** (junto al número de línea) o con **`F9`**.
- Aparece un **círculo rojo**.
- La línea marcada **todavía no se ha ejecutado** cuando el programa se detiene en ella.
- Se pueden poner **varios**.

### La instrucción `debugger`

En JavaScript se puede escribir el punto de interrupción **en el propio código**:

```javascript
debugger;
```

Solo actúa **si hay un depurador conectado**; sin él, no hace nada. Hay que quitarlo antes de publicar el programa.

## Iniciar la depuración

### En VS Code

Se abre el archivo y se pulsa **`F5`** (Node.js ya viene integrado): se ejecuta con el depurador y se detiene en los puntos de interrupción.

| Acción | Atajo |
|---|---|
| **Iniciar depuración** | `F5` |
| Ejecutar **sin** depurar | `Ctrl + F5` |
| **Detener** | `Shift + F5` |
| **Reiniciar** | `Ctrl + Shift + F5` |

### Desde la terminal

```bash
node --inspect-brk index.js
```

Arranca Node **esperando a un depurador** y se detiene en la primera línea. Se conecta desde `chrome://inspect` (Chrome o Edge) o desde VS Code.

### En el navegador

Con `F12` se abren las **herramientas de desarrollo**; en la pestaña **Sources** (*Depuración*) se ponen puntos de interrupción en el código de una página web.

## Moverse por el programa

| Botón | Atajo | Qué hace |
|---|---|---|
| **Continuar** (*Continue*) | `F5` | Sigue hasta el **siguiente** punto de interrupción |
| **Paso por encima** (*Step Over*) | `F10` | Ejecuta la línea **sin entrar** en las funciones que llame |
| **Paso a paso por instrucciones** (*Step Into*) | `F11` | Ejecuta la línea y, si llama a una función, **entra** en ella |
| **Paso a paso para salir** (*Step Out*) | `Shift + F11` | **Sale** de la función actual |

Diferencias:

- **Step Over**: si la línea llama a una función que ya se sabe que funciona, se salta sin recorrerla.
- **Step Into**: si se sospecha de la función, se entra a revisarla línea a línea.
- **Step Out**: si ya se ha entrado y se ve que no es ahí, se sale de golpe.
- **Continue**: salta directamente entre puntos de interrupción.

## Ver los valores

Con el programa **pausado** se examinan las variables:

| Dónde | Qué muestra |
|---|---|
| **Pasar el ratón** sobre una variable | Su valor actual |
| Panel **Variables** | Las variables del ámbito actual (Local, Global...) |
| **Inspección** (*Watch*) | Las expresiones que se quieran vigilar (`a * b`) |
| **Pila de llamadas** (*Call Stack*) | La cadena de funciones que llevó hasta aquí |
| **Consola de depuración** | Evaluar expresiones al momento |

Las variables declaradas con `let` que **todavía no se han asignado** valen `undefined`: por eso `suma` no tiene valor antes de ejecutar `a + b`.

## Depurar el ejemplo

1. Poner un punto de interrupción en `const suma = a + b;`.
2. Pulsar `F5`: el programa se detiene allí.
3. Con `F10`, avanzar línea a línea mirando las variables:

| Tras ejecutar... | `suma` | `resta` | `multiplicacion` |
|---|---|---|---|
| `suma = a + b` | `50` | sin asignar | sin asignar |
| `resta = a - b` | `50` | `10` | sin asignar |
| `multiplicacion = a * b` | `50` | `10` | **`600`** |

4. Al llegar a `console.log`, se ve que `multiplicacion` vale **600** pero se está mostrando **`suma`**: ahí está el error.
5. Detener (`Shift + F5`), **corregir** y volver a ejecutar.

El depurador **no corrige el código**: enseña **dónde** y **por qué** falla, y el programador lo arregla.

## Un error clásico: uno de menos

```javascript
function sumaHastaConError(n) {
    let total = 0;

    for (let i = 1; i < n; i++) {      // debería ser i <= n
        total += i;
    }

    return total;
}
```

`sumaHastaConError(5)` da `10` en lugar de `15`. Con un punto de interrupción en el `for` y `Step Over`, se ve que **`i` nunca llega a 5**: la última vuelta es con `i = 4`. Cambiar `<` por `<=` lo arregla.

## Depurar mostrando valores (trazas)

En JavaScript es **muy habitual** depurar con `console`:

```javascript
console.log(`  i=${i}, total=${total}`);
```

| Función | Para qué |
|---|---|
| `console.log` | Mostrar un valor |
| `console.error` / `console.warn` | Mensajes de error o aviso (salida de errores) |
| `console.table(datos)` | Mostrar un array u objeto como **tabla** |
| `console.assert(cond, msg)` | Avisa **solo si la condición es falsa** |
| `console.trace()` | Muestra la **pila de llamadas** hasta ese punto |
| `console.time` / `timeEnd` | Medir cuánto tarda algo |

Es rápido, pero hay que **escribirlo y luego borrarlo**. El depurador permite ver lo mismo sin tocar el código.

## Puntos de interrupción más potentes

| Tipo | Para qué |
|---|---|
| **Condicional** | Se detiene solo si se cumple algo (`i === 4`). Ideal en bucles largos |
| **Contador de visitas** | Se detiene a la enésima vez que pasa por la línea |
| **Punto de registro** (*logpoint*) | **Escribe un mensaje** al pasar, sin detener el programa ni tocar el código |
| **De excepción** | Se detiene **cuando se lanza** un error |

Se crean con **clic derecho** sobre el círculo rojo.

## Buenas prácticas

- **Reproducir** el error antes de depurar: con los mismos datos.
- **Reducir** el problema: probar con un caso pequeño.
- **Sospechar** del código propio antes que del lenguaje.
- Anotar **qué se esperaba** y **qué se obtiene**: el error está donde se separan.
- **Quitar** los puntos de interrupción, los `debugger` y las trazas al terminar.

## Ejemplo

```javascript
const a = 30;
const b = 20;

const multiplicacion = a * b;      // punto de interrupción aquí, y F10

console.log("Multiplicación: " + multiplicacion);
```

## Comandos

```bash
cd javascript/06-advanced-oop
node 44-debugging/index.js
```

Para depurar: abrir `index.js` en VS Code y pulsar `F5`.

Fuente: [curso de C# de INFORMATICONFIG](https://www.youtube.com/watch?v=ersgkd7ddFA&list=PL2Z95CSZ1N4F7mym8Ca16RoTDVAlIOPlT&index=44), vídeo 44.
