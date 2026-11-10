# Hilos (threads) en Java, primera parte

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
| **Interfaces que no se congelan** | En una aplicación con ventana (JavaFX), una tarea larga en el hilo de la interfaz la **congela**. Pasándola a **otro hilo**, la ventana sigue respondiendo |

## Crear un hilo: la clase `Thread`

Está en `java.lang`, así que **no hace falta importarla**.

```java
Thread hilo1 = new Thread(Hilos::ejecucionHilo);
```

Al crear el `Thread` se le entrega **el código que va a ejecutar**. Aquí es una **referencia a un método** (`Hilos::ejecucionHilo`): no se **llama** al método, se le **entrega** al hilo para que lo llame él. (En C# se pasaba el nombre del método a secas.)

```java
static void ejecucionHilo() {
    try {
        Thread.sleep(3000);
    } catch (InterruptedException e) {
        return;
    }
    System.out.println("Pasaron 3 segundos");
}
```

Ese método es el **comportamiento del hilo**: lo que hace cuando corre. El constructor recibe un **`Runnable`** (una interfaz con un solo método `run()`), por eso vale también una lambda: `new Thread(() -> { ... })`.

### Dormir un hilo: `Thread.sleep`

**`Thread.sleep(milisegundos)`** **suspende el hilo actual** ("lo duerme") durante ese tiempo:

| Milisegundos | Tiempo |
|---|---|
| `1000` | 1 segundo |
| `3000` | 3 segundos |
| `5000` | 5 segundos |

Lanza una **`InterruptedException`** (excepción comprobada: hay que capturarla) por si otro hilo **interrumpe** el sueño.

## Iniciar un hilo: `start()`

Crear el objeto **no** lo pone en marcha. Hay que **iniciarlo**:

```java
hilo1.start();
```

Desde ese momento, `ejecucionHilo` corre **en paralelo** al resto del programa. El arranque es **instantáneo**: `start()` no espera a que el hilo termine, devuelve el control enseguida.

> **No** hay que llamar a `run()` directamente: se ejecutaría en el hilo actual, como una llamada normal, **sin crear ningún hilo**.

## Esperar al hilo: `join()`

```java
hilo1.start();
System.out.println("Hilo iniciado");

try {
    hilo1.join();
} catch (InterruptedException e) {
    System.out.println("Espera interrumpida");
}

System.out.println("Fin del programa");
```

**`join()`** **detiene el hilo que lo llama** (aquí, el principal) hasta que **el hilo termine**. Cuando termina, el programa continúa. También lanza `InterruptedException`.

### Con y sin `join`

| | Salida |
|---|---|
| **Con** `join()` | `Hilo iniciado` → *(3 s)* → `Pasaron 3 segundos` → `Fin del programa` |
| **Sin** `join()` | `Hilo iniciado` → `Fin del programa` → *(3 s)* → `Pasaron 3 segundos` |

Sin `join`, el programa principal **sigue sin esperar**: dice "Fin del programa" **antes** de que el hilo termine. Así se ve que **son dos ejecuciones paralelas** y no una detrás de otra.

> El vídeo interpreta la salida sin `Join` (en C#) como que "el sistema finalizó el hilo instantáneamente": no es así. El hilo **sigue vivo** y escribe "Pasaron 3 segundos" 3 segundos después (la JVM no termina mientras quede un hilo normal en marcha); simplemente el principal **no lo espera**.

## El programa completo

```text
Hilo iniciado
(esta línea sale antes que 'Pasaron 3 segundos': el programa principal no espera al hilo)
Pasaron 3 segundos          ← a los 3 segundos
Fin del programa
```

## Resumen

| Concepto | Java | C# |
|---|---|---|
| Crear un hilo | `new Thread(Clase::método)` | `new Thread(método)` |
| Iniciarlo | `hilo.start()` | `hilo.Start()` |
| Dormirlo | `Thread.sleep(ms)` | `Thread.Sleep(ms)` |
| Esperar a que termine | `hilo.join()` | `hilo.Join()` |
| Excepción al dormir/esperar | `InterruptedException` (hay que capturarla) | no hay |

En el siguiente vídeo se verán ejemplos con **varios hilos** a la vez.

## Ejecutar

```bash
java ThreadsPart1.java
```

## Comandos

```bash
cd java/08-concurrency/59-threads-part-1
java ThreadsPart1.java
```

Fuente: [curso de C# de INFORMATICONFIG](https://www.youtube.com/watch?v=wW8WV433M80&list=PL2Z95CSZ1N4F7mym8Ca16RoTDVAlIOPlT&index=59), vídeo 59.
