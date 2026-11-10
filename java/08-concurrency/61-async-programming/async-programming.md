# Programación asíncrona en Java (`CompletableFuture`)

## Qué es

**Asíncrono** significa que **no se espera a que una tarea termine para seguir con la siguiente**.

Hasta ahora la programación era **lineal**: se invoca un proceso, se espera a que acabe, se invoca el siguiente. Con la programación asíncrona se pueden **lanzar varios procesos a la vez**: las tareas avanzan **en segundo plano** y el programa continúa. Cuando una tarea termina, el programa sigue desde donde corresponde.

En C# se hace con `async`/`await` y `Task`. En Java **no existen `async`/`await`**: lo equivalente es **`CompletableFuture`**, que representa **una tarea que terminará en el futuro**.

## Para qué sirve

| Utilidad | Explicación |
|---|---|
| **Mejor rendimiento** | El sistema no se queda **esperando** a una tarea lenta: aprovecha ese tiempo para hacer otras cosas |
| **Interfaces fluidas** | En aplicaciones de ventana (JavaFX), mientras se hace una tarea larga la interfaz **no se congela** (lo mismo que con los hilos, lección 59) |

### Una aclaración importante

> **La programación asíncrona no hace que el código se ejecute más rápido**: cada tarea tarda **lo mismo**. Lo que hace es que el programa **no se detenga esperando**, y pueda hacer **otras cosas mientras tanto** (por ejemplo, conectarse a una base de datos mientras se lee un archivo).

## Un método asíncrono

```java
static CompletableFuture<Void> conteo(int tiempo, String mensaje) {
    return CompletableFuture.runAsync(
            () -> System.out.println(mensaje),
            CompletableFuture.delayedExecutor(tiempo, TimeUnit.MILLISECONDS));
}
```

| Pieza | Qué es | En C# |
|---|---|---|
| **`CompletableFuture<Void>`** | Lo que **devuelve**: un objeto que representa **una tarea que terminará en el futuro**, sin valor (con `CompletableFuture<Integer>` devolvería un entero) | `Task` |
| **`runAsync(código, ejecutor)`** | **Lanza** la tarea de forma asíncrona y devuelve su `CompletableFuture` | llamar a un método `async` |
| **`delayedExecutor(ms, unidad)`** | Un ejecutor que lanza la tarea **pasado ese tiempo**, **sin bloquear ningún hilo** mientras espera | `Task.Delay` |
| **`join()`** | **Espera** a que la tarea termine | `await` |

### `delayedExecutor` frente a `Thread.sleep`

| | `Thread.sleep(ms)` | `delayedExecutor(ms, ...)` |
|---|---|---|
| ¿Bloquea un hilo? | **Sí**: el hilo se queda parado | **No**: no hay ningún hilo esperando |
| Para qué | Simular una tarea en un hilo propio (lección 59) | Programar algo para dentro de un tiempo |

## Lanzar las tareas a la vez

```java
CompletableFuture<Void> proceso1 = conteo(2000, "Han pasado 2 segundos");
CompletableFuture<Void> proceso2 = conteo(5000, "Han pasado 5 segundos");

CompletableFuture.allOf(proceso1, proceso2).join();
```

- **Llamar al método no espera**: arranca la tarea y devuelve su `CompletableFuture` enseguida. Las **dos cuentas empiezan en el mismo momento**.
- **`CompletableFuture.allOf(...)`** recibe **todas las tareas que se quiera** (aquí dos; podrían ser 50) y devuelve **una tarea nueva que se completa cuando todas han terminado**. Equivale a `Task.WhenAll`.
- **`join()`** **espera** hasta entonces.
- Si **alguna falla**, la nueva tarea también (la gestión de errores en tareas asíncronas, con `exceptionally` o `handle`, es otro tema).

> Hay un **`join()` bloqueante** al final: el hilo `main` **sí** se queda esperando. Para un programa de consola no importa; en una aplicación con ventana se encadenaría el resultado con `thenRun`/`thenAccept` en lugar de bloquear.

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

```java
conteo(2000, "Han pasado 2 segundos").join();
conteo(5000, "Han pasado 5 segundos").join();
```

```text
=== B) Una tarea detrás de otra ===
Han pasado 2 segundos
Han pasado 5 segundos
Tiempo total: 7 s
```

Tarda **2 + 5 = 7 segundos**: es el comportamiento "lineal" de siempre.

| | Forma | Tiempo total |
|---|---|---|
| **A)** | Lanzar las dos, `allOf(...).join()` | **5 s** (la más larga) |
| **B)** | `join()` + `join()` | **7 s** (la suma) |

## Encadenar lo que ocurre al terminar

El equivalente del `ContinueWith` de C# (y de los *callbacks*) son los métodos `then...`:

```java
CompletableFuture.supplyAsync(() -> leerArchivo())
                 .thenAccept(datos -> System.out.println("Archivo leído"));
```

`thenAccept` dice "cuando termine, haz esto con el resultado".

## Resumen

| Concepto | Java | C# |
|---|---|---|
| Una tarea futura | `CompletableFuture<T>` | `Task<T>` |
| Lanzar una tarea | `CompletableFuture.runAsync(...)` | llamar a un método `async` |
| Pausa asíncrona | `delayedExecutor(ms, ...)` | `await Task.Delay(ms)` |
| Esperar a una tarea | `tarea.join()` | `await tarea` |
| Esperar a varias | `CompletableFuture.allOf(t1, t2).join()` | `await Task.WhenAll(t1, t2)` |

## Ejecutar

```bash
java AsyncProgramming.java
```

Tarda unos 12 segundos (5 s + 7 s).

## Comandos

```bash
cd java/08-concurrency/61-async-programming
java AsyncProgramming.java
```

Fuente: [curso de C# de INFORMATICONFIG](https://www.youtube.com/watch?v=TRy41lyM6cU&list=PL2Z95CSZ1N4F7mym8Ca16RoTDVAlIOPlT&index=61), vídeo 61.
