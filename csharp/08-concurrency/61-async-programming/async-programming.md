# Programación asíncrona en C# (`async` / `await`)

## Qué es

**Asíncrono** significa que **no se espera a que una tarea termine para seguir con la siguiente**.

Hasta ahora la programación era **lineal**: se invoca un proceso, se espera a que acabe, se invoca el siguiente. Con la programación asíncrona se pueden **lanzar varios procesos a la vez**: las tareas avanzan **en segundo plano** y el programa continúa. Cuando una tarea termina, el programa sigue desde donde corresponde mediante `await` (en otros lenguajes y épocas, con *callbacks*).

## Para qué sirve

| Utilidad | Explicación |
|---|---|
| **Mejor rendimiento** | El sistema no se queda **esperando** a una tarea lenta: aprovecha ese tiempo para hacer otras cosas |
| **Interfaces fluidas** | En aplicaciones de ventana (WPF), mientras se hace una tarea larga la interfaz **no se congela** (lo mismo que con los hilos, lección 59) |

### Una aclaración importante

> **La programación asíncrona no hace que el código se ejecute más rápido**: cada tarea tarda **lo mismo**. Lo que hace es que el programa **no se detenga esperando**, y pueda hacer **otras cosas mientras tanto** (por ejemplo, conectarse a una base de datos mientras se lee un archivo).

## Dos formas de escribirlo

### Con una continuación (la forma antigua)

El vídeo menciona la idea de **encadenar** lo que se hace al terminar:

```csharp
LeerArchivoAsync().ContinueWith(resultado => Console.WriteLine("Archivo leído"));
```

`ContinueWith` dice "cuando termine, haz esto" (un *callback*, con una función flecha). Funciona, pero encadenar varios pasos se vuelve confuso.

### Con `async` / `await` (la forma actual)

Es lo que se estudia en esta lección: el código **se escribe en orden, como si fuera lineal**, pero se ejecuta de forma asíncrona.

## Un método asíncrono

```csharp
static async Task Conteo(int tiempo, string mensaje)
{
    await Task.Delay(tiempo);

    Console.WriteLine(mensaje);
}
```

| Pieza | Qué es |
|---|---|
| **`async`** | Marca que el método es asíncrono y **puede usar `await`** |
| **`Task`** | Lo que **devuelve**: un objeto que representa **una tarea que terminará en el futuro**. Con `Task` a secas no devuelve valor (es el `void` de lo asíncrono); con `Task<int>` devolvería un `int` |
| **`await`** | **Espera** a que la tarea que tiene delante termine, **sin bloquear el hilo**: el programa puede hacer otras cosas |
| **`Task.Delay(ms)`** | Una tarea que se completa **pasado ese tiempo** |

> Si se quita `Task` (y se deja `void`), el compilador avisa: los métodos `async` **deben devolver `Task`** para poder esperarlos desde fuera.

### `Task.Delay` frente a `Thread.Sleep`

| | `Thread.Sleep(ms)` | `await Task.Delay(ms)` |
|---|---|---|
| ¿Bloquea el hilo? | **Sí**: el hilo se queda parado | **No**: el hilo queda libre |
| Para qué | Simular una tarea en un hilo propio (lección 59) | Esperar dentro de código asíncrono |

`Task.Delay` **programa el tiempo de espera**; **`await`** es lo que le dice al programa "espera el resultado y, mientras, sigue con otras cosas".

## Lanzar las tareas a la vez

```csharp
Task proceso1 = Conteo(2000, "Han pasado 2 segundos");
Task proceso2 = Conteo(5000, "Han pasado 5 segundos");

await Task.WhenAll(proceso1, proceso2);
```

- **Llamar al método no espera**: arranca la tarea y devuelve su `Task` enseguida. Las **dos cuentas empiezan en el mismo momento**.
- **`Task.WhenAll(...)`** recibe **todas las tareas que se quiera** (aquí dos; podrían ser 50) y devuelve **una tarea nueva que se completa cuando todas han terminado**.
- **`await`** delante de `WhenAll` hace que el programa **espere** hasta entonces.
- Si **alguna falla**, `WhenAll` lo hace saber (la gestión de errores en tareas asíncronas es otro tema).

## `Main` asíncrono

Para poder usar `await` en `Main`, este se declara **`static async Task Main()`**.

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

```csharp
await Conteo(2000, "Han pasado 2 segundos");
await Conteo(5000, "Han pasado 5 segundos");
```

```text
=== B) Una tarea detrás de otra ===
Han pasado 2 segundos
Han pasado 5 segundos
Tiempo total: 7 s
```

Tarda **2 + 5 = 7 segundos**: es el comportamiento "lineal" de siempre, pero **sin bloquear el hilo** mientras espera.

| | Forma | Tiempo total |
|---|---|---|
| **A)** | Lanzar las dos, `await Task.WhenAll(...)` | **5 s** (la más larga) |
| **B)** | `await` + `await` | **7 s** (la suma) |

## `async` no es lo mismo que un hilo nuevo

Con `Thread` (lecciones 59 y 60) se crea **un hilo** para cada tarea. Con `async`/`await` **no se crea necesariamente ningún hilo**: mientras `Task.Delay` espera, **no hay ningún hilo ocupado**; el sistema retoma el código cuando llega el momento. Por eso se usa para tareas que **esperan** (red, archivos, bases de datos, temporizadores), y los hilos para trabajo que **calcula** de verdad.

## Resumen

| Concepto | En C# |
|---|---|
| Método asíncrono | `static async Task Metodo()` |
| Esperar sin bloquear | `await` |
| Pausa asíncrona | `await Task.Delay(ms)` |
| Esperar a varias tareas | `await Task.WhenAll(t1, t2, ...)` |
| `Main` asíncrono | `static async Task Main()` |

## Ejecutar

```bash
dotnet run
```

Tarda unos 12 segundos (5 s + 7 s).

## Comandos

```bash
cd csharp/08-concurrency
dotnet new console -n AsyncProgramming -o 61-async-programming
cd 61-async-programming
dotnet run
```

Fuente: [curso de C# de INFORMATICONFIG](https://www.youtube.com/watch?v=TRy41lyM6cU&list=PL2Z95CSZ1N4F7mym8Ca16RoTDVAlIOPlT&index=61), vídeo 61.
