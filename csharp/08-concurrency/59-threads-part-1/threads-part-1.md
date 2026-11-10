# Hilos (threads) en C#, primera parte

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
| **Interfaces que no se congelan** | En una aplicación con ventana, una tarea larga en el hilo de la interfaz la **congela** ("se frisó el programa"). Pasándola a **otro hilo**, la ventana sigue respondiendo |

## Crear un hilo: la clase `Thread`

Está en `System.Threading` (con `ImplicitUsings` ya viene incluido).

```csharp
Thread hilo1 = new Thread(EjecucionHilo);
```

Al crear el `Thread` se le entrega **el método que va a ejecutar** (aquí `EjecucionHilo`), pasando **solo su nombre, sin paréntesis**: no se **llama** al método, se le **entrega** al hilo para que lo llame él.

```csharp
static void EjecucionHilo()
{
    Thread.Sleep(3000);
    Console.WriteLine("Pasaron 3 segundos");
}
```

Ese método es el **comportamiento del hilo**: lo que hace cuando corre.

### Dormir un hilo: `Thread.Sleep`

**`Thread.Sleep(milisegundos)`** **suspende el hilo actual** ("lo duerme") durante ese tiempo:

| Milisegundos | Tiempo |
|---|---|
| `1000` | 1 segundo |
| `3000` | 3 segundos |
| `5000` | 5 segundos |

Se usa para simular una tarea que tarda, o para esperar entre pasos.

## Iniciar un hilo: `Start()`

Crear el objeto **no** lo pone en marcha. Hay que **iniciarlo**:

```csharp
hilo1.Start();
```

Desde ese momento, `EjecucionHilo` corre **en paralelo** al resto del programa. El arranque es **instantáneo**: `Start()` no espera a que el hilo termine, devuelve el control enseguida.

## Esperar al hilo: `Join()`

```csharp
hilo1.Start();
Console.WriteLine("Hilo iniciado");

hilo1.Join();

Console.WriteLine("Fin del programa");
```

**`Join()`** **detiene el hilo que lo llama** (aquí, el principal) hasta que **el hilo termine**. Cuando termina, el programa continúa.

### Con y sin `Join`

| | Salida |
|---|---|
| **Con** `Join()` | `Hilo iniciado` → *(3 s)* → `Pasaron 3 segundos` → `Fin del programa` |
| **Sin** `Join()` | `Hilo iniciado` → `Fin del programa` → *(3 s)* → `Pasaron 3 segundos` |

Sin `Join`, el programa principal **sigue sin esperar**: dice "Fin del programa" **antes** de que el hilo termine. Así se ve que **son dos ejecuciones paralelas** y no una detrás de otra.

> El vídeo interpreta la salida sin `Join` como que "el sistema finalizó el hilo instantáneamente": no es así. El hilo **sigue vivo** y escribe "Pasaron 3 segundos" 3 segundos después (el proceso no termina mientras quede un hilo normal en marcha); simplemente el principal **no lo espera**.

Si se cambia a `Thread.Sleep(5000)`, el programa tarda 5 segundos en continuar (y habrá que cambiar también el mensaje, que seguiría diciendo "3 segundos").

## El programa completo

```text
Hilo iniciado
(esta línea sale antes que 'Pasaron 3 segundos': el programa principal no espera al hilo)
Pasaron 3 segundos          ← a los 3 segundos
Fin del programa
```

## Resumen

| Concepto | En C# |
|---|---|
| Crear un hilo | `new Thread(método)` |
| Iniciarlo | `hilo.Start()` |
| Dormirlo | `Thread.Sleep(milisegundos)` |
| Esperar a que termine | `hilo.Join()` |

En el siguiente vídeo se verán ejemplos con **varios hilos** a la vez.

## Ejecutar

```bash
dotnet run
```

## Comandos

```bash
cd csharp/08-concurrency
dotnet new console -n ThreadsPart1 -o 59-threads-part-1
cd 59-threads-part-1
dotnet run
```

Fuente: [curso de C# de INFORMATICONFIG](https://www.youtube.com/watch?v=wW8WV433M80&list=PL2Z95CSZ1N4F7mym8Ca16RoTDVAlIOPlT&index=59), vídeo 59.
