# Hilos (threads) en Java, segunda parte

## Qué se aprende

Trabajar con **dos hilos**, cada uno con **una tarea distinta**. El ejemplo simula **analizar un programa** (buscar amenazas) **antes de instalarlo**:

| Hilo | Método | Qué hace | Tarda |
|---|---|---|---|
| `hiloAnalisis` | `analizarArchivo` | "Analizando archivo..." → "Archivo analizado, no hay amenazas" | 5 s |
| `hiloInstalador` | `instalacion` | "Instalando programa..." → "Programa instalado correctamente" | 7 s |

Se usa lo de la lección anterior: `Thread`, `start()`, `Thread.sleep` y `join()`.

## Definir los dos hilos

```java
class Instalador {
    public Thread hiloAnalisis = new Thread(Instalador::analizarArchivo);
    public Thread hiloInstalador = new Thread(Instalador::instalacion);

    static void analizarArchivo() {
        System.out.println("Analizando archivo...");
        dormir(5000);
        System.out.println("Archivo analizado, no hay amenazas");
    }

    static void instalacion() {
        System.out.println("Instalando programa...");
        dormir(7000);
        System.out.println("Programa instalado correctamente");
    }
}
```

- Cada hilo se **crea con el método que va a ejecutar**, como **referencia a método** (`Instalador::analizarArchivo`).
- Son **campos `public`** del objeto para poder usarlos desde `main`.
- `dormir` es un método auxiliar que **captura la `InterruptedException`** de `Thread.sleep` en un único sitio:

```java
static void dormir(long milisegundos) {
    try {
        Thread.sleep(milisegundos);
    } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
    }
}
```

## Para qué sirve cada cosa

### `Thread.sleep`: cuánto TARDA la tarea

El vídeo lo explica con cuidado: **`sleep(5000)`** no hace esperar "al programa". Dice **cuánto tarda esta tarea** (aquí 5 segundos): es el tiempo que el hilo **está parado** antes de pasar a la siguiente acción. Se usa para **simular** trabajo real (analizar, instalar, descargar...).

### `join()`: hace ESPERAR al programa principal

Lo que hace que el **programa principal** espere a que un hilo termine es **`join()`**. Como lanza `InterruptedException`, `main` la **declara** con `throws InterruptedException`.

## A) Un hilo detrás de otro (como en el vídeo)

```java
ejecucion.hiloAnalisis.start();
ejecucion.hiloAnalisis.join();      // espera a que termine el análisis (5 s)

ejecucion.hiloInstalador.start();
ejecucion.hiloInstalador.join();    // espera a que termine la instalación (7 s)
```

Salida:

```text
Analizando archivo...
Archivo analizado, no hay amenazas      ← a los 5 s
Instalando programa...
Programa instalado correctamente        ← a los 7 s más
Tiempo total: 12 s
```

Es **lo que se quiere** en este ejemplo: **primero se analiza** y **solo después** se instala. El orden lo marca el `join()` colocado **inmediatamente después** de cada `start()`.

> Importante: el vídeo dice que así "los dos hilos trabajan en paralelo", pero con un `join()` justo después de cada `start()` **no hay paralelismo**: el segundo hilo **ni siquiera arranca** hasta que el primero termina. Tardan **5 + 7 = 12 segundos**.

## B) Los dos hilos a la vez: paralelismo de verdad

Para que corran **a la vez** hay que **arrancar los dos primero** y **esperar después**:

```java
ejecucionParalela.hiloAnalisis.start();
ejecucionParalela.hiloInstalador.start();      // arranca sin esperar a que acabe el análisis

ejecucionParalela.hiloAnalisis.join();
ejecucionParalela.hiloInstalador.join();
```

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
| **A)** | `start` + `join`, `start` + `join` | **12 s** (5 + 7) |
| **B)** | `start`, `start`, `join`, `join` | **7 s** (el mayor) |

> Esa es la idea del paralelismo, pero **en este ejemplo concreto no tiene sentido instalar sin haber terminado de analizar**: sirve para ver la **diferencia**. En un caso real, dos tareas **independientes** (descargar dos archivos) sí se harían como en B).

El orden de las dos primeras líneas de B) (`Analizando...` e `Instalando...`) **puede variar** de una ejecución a otra: los hilos arrancan casi a la vez y el sistema decide quién escribe antes.

### Un hilo solo se inicia una vez

En B) se crea **un objeto `Instalador` nuevo**: un `Thread` **no se puede volver a iniciar** una vez terminado (daría `IllegalThreadStateException`). Para repetir la tarea hay que crear otro `Thread`.

### Medir el tiempo: `System.nanoTime`

```java
long inicio = System.nanoTime();
...
System.out.println("Tiempo total: " + Math.round((System.nanoTime() - inicio) / 1e9) + " s");
```

`nanoTime` devuelve nanosegundos: dividido entre `1e9`, segundos. Así se comprueba la diferencia entre A) y B).

## Resumen

- Se pueden crear **tantos hilos como se necesiten** en un programa, para hacer varios procesos.
- **`start()`** inicia; **`join()`** espera; **`sleep()`** marca cuánto tarda cada tarea.
- Con `start` + `join` seguidos, los hilos van **uno detrás de otro**; con todos los `start` primero y los `join` después, van **a la vez**.

## Ejecutar

```bash
java ThreadsPart2.java
```

Tarda unos 19 segundos (12 s + 7 s).

## Comandos

```bash
cd java/08-concurrency/60-threads-part-2
java ThreadsPart2.java
```

Fuente: [curso de C# de INFORMATICONFIG](https://www.youtube.com/watch?v=Vg-b7IySPvc&list=PL2Z95CSZ1N4F7mym8Ca16RoTDVAlIOPlT&index=60), vídeo 60.
