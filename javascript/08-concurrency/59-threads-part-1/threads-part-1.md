# Hilos (threads) en JavaScript, primera parte

## Qué es un hilo

Un **hilo** (*thread*) es una **unidad de ejecución** dentro de un proceso (un programa). Permite que el programa haga **varias tareas a la vez**: ejecutar código **en paralelo**.

Ejemplos de la vida diaria:

- Se **descarga un archivo** mientras se sigue navegando: la descarga la lleva **un hilo** que trabaja **en segundo plano**.
- En el **Administrador de tareas**, la sección *Procesos en segundo plano* muestra muchos programas y servicios que trabajan sin que el usuario lo note: todo eso lo manejan hilos.

## Para qué sirven

| Utilidad | Explicación |
|---|---|
| **Tareas en segundo plano** | Una operación larga no impide hacer otras cosas |
| **Mejor rendimiento** | Los recursos del equipo no se concentran en **una sola tarea**: se reparten en varias que corren en paralelo |
| **Interfaces que no se congelan** | En una página, un cálculo largo en el hilo principal la **congela**. Pasándolo a **otro hilo**, la página sigue respondiendo |

## JavaScript tiene un solo hilo (por defecto)

Esto es una **diferencia importante** con C# y Java: el código de JavaScript corre en **un único hilo** (el principal), con un **bucle de eventos** (*event loop*) que atiende las tareas de una en una. Las operaciones lentas de entrada/salida (red, archivos, temporizadores) se hacen **sin bloquear** con funciones asíncronas (lección 61), no con hilos.

Para ejecutar código **en paralelo de verdad** (por ejemplo, un cálculo pesado) existen los **hilos de trabajo**:

| Entorno | Hilos de trabajo |
|---|---|
| **Node.js** | `Worker` del módulo `worker_threads` (el de esta lección) |
| **Navegador** | `Web Worker` (`new Worker("archivo.js")`) |

Cada hilo de trabajo tiene **su propio entorno**: **no comparte variables** con el principal, se comunican **enviándose mensajes**.

## Crear un hilo: la clase `Worker`

```javascript
const { Worker } = require("worker_threads");

const hilo1 = new Worker(__dirname + "/hilo.js");
```

Al crear el `Worker` se le entrega **el archivo con el código que va a ejecutar** (`hilo.js`), que equivale al método `EjecucionHilo` de C#. (`__dirname` es la carpeta del archivo actual.)

```javascript
// hilo.js
function dormir(milisegundos) {
    Atomics.wait(new Int32Array(new SharedArrayBuffer(4)), 0, 0, milisegundos);
}

function ejecucionHilo() {
    dormir(3000);

    console.log("Pasaron 3 segundos");
}

ejecucionHilo();
```

Ese archivo es el **comportamiento del hilo**: lo que hace cuando corre.

### Dormir un hilo

JavaScript **no tiene** `Thread.Sleep`. Un hilo de trabajo se duerme de forma **bloqueante** con `Atomics.wait` (con un `SharedArrayBuffer` que nadie va a despertar), indicando los milisegundos como último argumento:

| Milisegundos | Tiempo |
|---|---|
| `1000` | 1 segundo |
| `3000` | 3 segundos |
| `5000` | 5 segundos |

> **No se puede usar en el hilo principal** (ahí se usa `setTimeout` o `await`, que **no bloquean**). Solo se puede dentro de un `Worker`.

## Iniciar un hilo

Un `Worker` **se inicia al crearse**: **no hay** un `Start()` aparte como en C#. El arranque es **instantáneo**: `new Worker(...)` no espera a que el hilo termine, devuelve el control enseguida.

## Esperar al hilo: el "Join"

```javascript
const { once } = require("events");

const hilo1 = new Worker(__dirname + "/hilo.js");
console.log("Hilo iniciado");

await once(hilo1, "exit");

console.log("Fin del programa");
```

No hay un `Join()` que **bloquee** (el hilo principal de JavaScript no se detiene nunca). En su lugar, se **espera con `await`** al evento **`"exit"`** (el hilo terminó) y el programa continúa cuando llega. Eso requiere estar dentro de una función **`async`**.

### Con y sin la espera

| | Salida |
|---|---|
| **Con** `await once(hilo1, "exit")` | `Hilo iniciado` → *(3 s)* → `Pasaron 3 segundos` → `Fin del programa` |
| **Sin** la espera | `Hilo iniciado` → `Fin del programa` → *(3 s)* → `Pasaron 3 segundos` |

Sin la espera, el programa principal **sigue sin esperar**: dice "Fin del programa" **antes** de que el hilo termine. Así se ve que **son dos ejecuciones paralelas** y no una detrás de otra.

> El vídeo interpreta la salida sin `Join` (en C#) como que "el sistema finalizó el hilo instantáneamente": no es así. El hilo **sigue vivo** y escribe "Pasaron 3 segundos" 3 segundos después (Node.js no termina mientras quede un hilo de trabajo en marcha); simplemente el principal **no lo espera**.

## El programa completo

```text
Hilo iniciado
(esta línea sale antes que 'Pasaron 3 segundos': el programa principal no espera al hilo)
Pasaron 3 segundos          ← a los 3 segundos
Fin del programa
```

## Resumen

| Concepto | JavaScript (Node.js) | C# |
|---|---|---|
| Crear (e iniciar) un hilo | `new Worker(archivo)` | `new Thread(método)` + `Start()` |
| Dormirlo | `Atomics.wait(...)` (solo en el Worker) | `Thread.Sleep(ms)` |
| Esperar a que termine | `await once(hilo, "exit")` | `hilo.Join()` |
| ¿Comparten variables? | **No**: se envían mensajes | Sí |

En el siguiente vídeo se verán ejemplos con **varios hilos** a la vez.

## Ejecutar

```bash
node index.js
```

## Comandos

```bash
cd javascript/08-concurrency/59-threads-part-1
node index.js
```

Fuente: [curso de C# de INFORMATICONFIG](https://www.youtube.com/watch?v=wW8WV433M80&list=PL2Z95CSZ1N4F7mym8Ca16RoTDVAlIOPlT&index=59), vídeo 59.
