# Programación asíncrona en JavaScript (`async` / `await` y `Promise`)

## Qué es

**Asíncrono** significa que **no se espera a que una tarea termine para seguir con la siguiente**.

Hasta ahora la programación era **lineal**: se invoca un proceso, se espera a que acabe, se invoca el siguiente. Con la programación asíncrona se pueden **lanzar varios procesos a la vez**: las tareas avanzan **en segundo plano** y el programa continúa. Cuando una tarea termina, el programa sigue desde donde corresponde.

En JavaScript la programación asíncrona es **parte central del lenguaje** (lección 59: JavaScript tiene un solo hilo, y las esperas largas se hacen siempre de forma asíncrona). La sintaxis `async`/`await` es **casi idéntica a la de C#**; en vez de `Task`, se usa **`Promise`**.

## Para qué sirve

| Utilidad | Explicación |
|---|---|
| **Mejor rendimiento** | El programa no se queda **esperando** a una tarea lenta: aprovecha ese tiempo para hacer otras cosas |
| **Páginas fluidas** | En el navegador, mientras se hace una tarea larga (una petición de red) la página **no se congela** |

### Una aclaración importante

> **La programación asíncrona no hace que el código se ejecute más rápido**: cada tarea tarda **lo mismo**. Lo que hace es que el programa **no se detenga esperando**, y pueda hacer **otras cosas mientras tanto** (por ejemplo, conectarse a una base de datos mientras se lee un archivo).

## La `Promise`

Una **`Promise`** (promesa) representa **un resultado que llegará en el futuro**. Es lo que en C# es un `Task`. Puede estar **pendiente**, **cumplida** (terminó bien) o **rechazada** (falló).

```javascript
function espera(milisegundos) {
    return new Promise((resolve) => setTimeout(resolve, milisegundos));
}
```

- **`new Promise((resolve) => { ... })`** crea la promesa. La función recibe `resolve`: **llamarla** avisa de que la tarea **terminó**.
- **`setTimeout(función, ms)`** ejecuta algo **pasado ese tiempo**, **sin bloquear** el programa.
- Juntos, `espera(ms)` es el equivalente de **`Task.Delay(ms)`**: una promesa que se cumple pasado ese tiempo.

## Una función asíncrona

```javascript
async function conteo(tiempo, mensaje) {
    await espera(tiempo);

    console.log(mensaje);
}
```

| Pieza | Qué es |
|---|---|
| **`async`** | Marca que la función es asíncrona y **puede usar `await`**. Una función `async` **siempre devuelve una `Promise`** (aquí, sin valor) |
| **`await`** | **Espera** a que la promesa que tiene delante termine, **sin bloquear el programa**: mientras, se pueden hacer otras cosas |

## Lanzar las tareas a la vez

```javascript
const proceso1 = conteo(2000, "Han pasado 2 segundos");
const proceso2 = conteo(5000, "Han pasado 5 segundos");

await Promise.all([proceso1, proceso2]);
```

- **Llamar a la función no espera**: arranca la tarea y devuelve su `Promise` enseguida. Las **dos cuentas empiezan en el mismo momento**.
- **`Promise.all([...])`** recibe un **array con todas las promesas que se quiera** (aquí dos; podrían ser 50) y devuelve **una promesa nueva que se cumple cuando todas han terminado**. Equivale a `Task.WhenAll`.
- **`await`** delante de `Promise.all` hace que el programa **espere** hasta entonces.
- Si **alguna falla**, `Promise.all` se rechaza (y `await` lanza el error, que se puede capturar con `try/catch`).

## `main` asíncrono

Para poder usar `await` hay que estar **dentro de una función `async`**: por eso el programa se escribe en `async function main()` y se llama al final con `main();`.

## A) Las dos tareas a la vez

```text
=== A) Las dos tareas a la vez ===
Iniciando conteo...
Han pasado 2 segundos           ← a los 2 s
Han pasado 5 segundos           ← a los 5 s (3 s después del anterior, NO 5)
Tiempo total: 5 s
```

Lo importante: el mensaje de 5 segundos sale **3 segundos después** del primero y no 5, **porque las dos tareas corrían a la vez**. El tiempo total es el de **la más larga** (5 s).

El vídeo lo comprueba cambiando el 5 por un 3: entonces el segundo mensaje sale **1 segundo** después del primero (3 − 2).

## B) Una tarea detrás de otra

Si se **espera a cada tarea antes de lanzar la siguiente**:

```javascript
await conteo(2000, "Han pasado 2 segundos");
await conteo(5000, "Han pasado 5 segundos");
```

```text
=== B) Una tarea detrás de otra ===
Han pasado 2 segundos
Han pasado 5 segundos
Tiempo total: 7 s
```

Tarda **2 + 5 = 7 segundos**: es el comportamiento "lineal" de siempre, pero **sin bloquear** mientras espera.

| | Forma | Tiempo total |
|---|---|---|
| **A)** | Lanzar las dos, `await Promise.all([...])` | **5 s** (la más larga) |
| **B)** | `await` + `await` | **7 s** (la suma) |

## Encadenar lo que ocurre al terminar: `then`

Antes de `async`/`await`, y como en el `ContinueWith` de C# del vídeo, se encadenaba con **`then`**:

```javascript
leerArchivo().then((resultado) => console.log("Archivo leído"));
```

`then` dice "cuando termine, haz esto con el resultado" (un *callback*, con una función flecha). Encadenar varios pasos se vuelve confuso; `await` permite escribir el código **en orden**.

## Resumen

| Concepto | JavaScript | C# |
|---|---|---|
| Una tarea futura | `Promise` | `Task` |
| Función asíncrona | `async function f()` | `static async Task F()` |
| Esperar sin bloquear | `await` | `await` |
| Pausa asíncrona | `await new Promise(r => setTimeout(r, ms))` | `await Task.Delay(ms)` |
| Esperar a varias | `await Promise.all([p1, p2])` | `await Task.WhenAll(t1, t2)` |

## Ejecutar

```bash
node index.js
```

Tarda unos 12 segundos (5 s + 7 s).

## Comandos

```bash
cd javascript/08-concurrency/61-async-programming
node index.js
```

Fuente: [curso de C# de INFORMATICONFIG](https://www.youtube.com/watch?v=TRy41lyM6cU&list=PL2Z95CSZ1N4F7mym8Ca16RoTDVAlIOPlT&index=61), vídeo 61.
