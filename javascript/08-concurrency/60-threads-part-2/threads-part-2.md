# Hilos (threads) en JavaScript, segunda parte

## Qué se aprende

Trabajar con **dos hilos**, cada uno con **una tarea distinta**. El ejemplo simula **analizar un programa** (buscar amenazas) **antes de instalarlo**:

| Hilo | Archivo | Qué hace | Tarda |
|---|---|---|---|
| análisis | `analizar-archivo.js` | "Analizando archivo..." → "Archivo analizado, no hay amenazas" | 5 s |
| instalador | `instalacion.js` | "Instalando programa..." → "Programa instalado correctamente" | 7 s |

Se usa lo de la lección anterior: `Worker` (el hilo de Node.js), dormir un hilo y esperar a que termine.

## Definir los dos hilos

Cada hilo es un **archivo** distinto (lo que en C# era un método):

```javascript
// analizar-archivo.js
const { dormir } = require("./dormir");

console.log("Analizando archivo...");
dormir(5000);
console.log("Archivo analizado, no hay amenazas");
```

```javascript
// instalacion.js
const { dormir } = require("./dormir");

console.log("Instalando programa...");
dormir(7000);
console.log("Programa instalado correctamente");
```

`dormir.js` tiene la función que **duerme** un hilo de trabajo (`Atomics.wait`, lección anterior) y se **comparte** con `require` / `module.exports`.

En `index.js`:

```javascript
class Instalador {
    crearHiloAnalisis() {
        return new Worker(__dirname + "/analizar-archivo.js");
    }

    crearHiloInstalador() {
        return new Worker(__dirname + "/instalacion.js");
    }
}
```

> Un `Worker` **se inicia al crearse** (no hay `Start()` aparte como en C#). Por eso aquí se **crean con un método**, justo en el momento en que se quiere **arrancar** cada hilo.

## Para qué sirve cada cosa

### `dormir`: cuánto TARDA la tarea

El vídeo lo explica con cuidado: **`Sleep(5000)`** no hace esperar "al programa". Dice **cuánto tarda esta tarea** (aquí 5 segundos): es el tiempo que el hilo **está parado** antes de pasar a la siguiente acción. Se usa para **simular** trabajo real (analizar, instalar, descargar...).

### `await once(hilo, "exit")`: hace ESPERAR al programa principal

Lo que hace que el **programa principal** espere a que un hilo termine es **esperar** (con `await`) al evento **`"exit"`** del `Worker`. (En C# era `Join()`.) Requiere estar dentro de una función **`async`**.

## A) Un hilo detrás de otro (como en el vídeo)

```javascript
const hiloAnalisis = ejecucion.crearHiloAnalisis();
await once(hiloAnalisis, "exit");           // espera a que termine el análisis (5 s)

const hiloInstalador = ejecucion.crearHiloInstalador();
await once(hiloInstalador, "exit");         // espera a que termine la instalación (7 s)
```

Salida:

```text
Analizando archivo...
Archivo analizado, no hay amenazas      ← a los 5 s
Instalando programa...
Programa instalado correctamente        ← a los 7 s más
Tiempo total: 12 s
```

Es **lo que se quiere** en este ejemplo: **primero se analiza** y **solo después** se instala. El orden lo marca la espera **inmediatamente después** de crear cada hilo.

> Importante: el vídeo dice que así "los dos hilos trabajan en paralelo", pero con una espera justo después de arrancar cada hilo **no hay paralelismo**: el segundo hilo **ni siquiera se crea** hasta que el primero termina. Tardan **5 + 7 = 12 segundos**.

## B) Los dos hilos a la vez: paralelismo de verdad

Para que corran **a la vez** hay que **crear los dos primero** y **esperar después**:

```javascript
const hiloAnalisis2 = ejecucion.crearHiloAnalisis();
const hiloInstalador2 = ejecucion.crearHiloInstalador();    // arranca sin esperar a que acabe el análisis

await Promise.all([once(hiloAnalisis2, "exit"), once(hiloInstalador2, "exit")]);
```

**`Promise.all([...])`** espera a que terminen **todas** las esperas del array (lección 61, promesas).

Salida:

```text
Analizando archivo...
Instalando programa...                  ← casi a la vez
Archivo analizado, no hay amenazas      ← a los 5 s
Programa instalado correctamente        ← a los 7 s (contados desde el principio)
Tiempo total: 7 s
```

Los dos corren **en paralelo** y el total es lo que tarda **el más largo** (7 s), no la suma.

| | Forma | Tiempo total |
|---|---|---|
| **A)** | crear + esperar, crear + esperar | **12 s** (5 + 7) |
| **B)** | crear, crear, esperar a los dos | **7 s** (el mayor) |

> Esa es la idea del paralelismo, pero **en este ejemplo concreto no tiene sentido instalar sin haber terminado de analizar**: sirve para ver la **diferencia**. En un caso real, dos tareas **independientes** (descargar dos archivos) sí se harían como en B).

El orden de las dos primeras líneas de B) (`Analizando...` e `Instalando...`) **puede variar** de una ejecución a otra: los hilos arrancan casi a la vez y el sistema decide quién escribe antes.

### Un hilo solo se usa una vez

Un `Worker` **termina** al acabar su archivo y **no se puede reutilizar**. Para repetir la tarea hay que crear **otro** `Worker` (por eso se crean con un método).

### Medir el tiempo: `performance.now()`

```javascript
let inicio = performance.now();
...
console.log("Tiempo total: " + Math.round((performance.now() - inicio) / 1000) + " s");
```

`performance.now()` devuelve **milisegundos**: dividido entre `1000`, segundos. Así se comprueba la diferencia entre A) y B).

## Resumen

- Se pueden crear **tantos hilos como se necesiten** en un programa, para hacer varios procesos.
- Crear un `Worker` **inicia**; **`await once(hilo, "exit")`** espera; **dormir** marca cuánto tarda cada tarea.
- Si se espera a cada hilo **justo después de crearlo**, van **uno detrás de otro**; si se crean todos primero y se espera después, van **a la vez**.

## Ejecutar

```bash
node index.js
```

Tarda unos 19 segundos (12 s + 7 s).

## Comandos

```bash
cd javascript/08-concurrency/60-threads-part-2
node index.js
```

Fuente: [curso de C# de INFORMATICONFIG](https://www.youtube.com/watch?v=Vg-b7IySPvc&list=PL2Z95CSZ1N4F7mym8Ca16RoTDVAlIOPlT&index=60), vídeo 60.
