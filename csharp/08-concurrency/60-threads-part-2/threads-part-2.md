# Hilos (threads) en C#, segunda parte

## Qué se aprende

Trabajar con **dos hilos**, cada uno con **una tarea distinta**. El ejemplo simula **analizar un programa** (buscar amenazas) **antes de instalarlo**:

| Hilo | Método | Qué hace | Tarda |
|---|---|---|---|
| `hiloAnalisis` | `AnalizarArchivo` | "Analizando archivo..." → "Archivo analizado, no hay amenazas" | 5 s |
| `hiloInstalador` | `Instalacion` | "Instalando programa..." → "Programa instalado correctamente" | 7 s |

Se usa lo de la lección anterior: `Thread`, `Start()`, `Thread.Sleep` y `Join()`.

## Definir los dos hilos

```csharp
class Instalador
{
    public Thread hiloAnalisis = new Thread(AnalizarArchivo);
    public Thread hiloInstalador = new Thread(Instalacion);

    static void AnalizarArchivo()
    {
        Console.WriteLine("Analizando archivo...");
        Thread.Sleep(5000);
        Console.WriteLine("Archivo analizado, no hay amenazas");
    }

    static void Instalacion()
    {
        Console.WriteLine("Instalando programa...");
        Thread.Sleep(7000);
        Console.WriteLine("Programa instalado correctamente");
    }
}
```

- Cada hilo se **crea con el método que va a ejecutar** (solo su nombre, sin paréntesis).
- Son **campos `public`** del objeto para poder usarlos desde `Main`.
- Un hilo definido pero **sin iniciar** no hace nada (Visual Studio muestra su método atenuado mientras nadie lo usa).

## Para qué sirve cada cosa

### `Thread.Sleep`: cuánto TARDA la tarea

El vídeo lo explica con cuidado: **`Thread.Sleep(5000)`** no hace esperar "al programa". Dice **cuánto tarda esta tarea** (aquí 5 segundos): es el tiempo que el hilo **está parado** antes de pasar a la siguiente acción. Se usa para **simular** trabajo real (analizar, instalar, descargar...).

### `Join()`: hace ESPERAR al programa principal

Lo que hace que el **programa principal** espere a que un hilo termine es **`Join()`**.

## A) Un hilo detrás de otro (como en el vídeo)

```csharp
ejecucion.hiloAnalisis.Start();
ejecucion.hiloAnalisis.Join();      // espera a que termine el análisis (5 s)

ejecucion.hiloInstalador.Start();
ejecucion.hiloInstalador.Join();    // espera a que termine la instalación (7 s)
```

Salida:

```text
Analizando archivo...
Archivo analizado, no hay amenazas      ← a los 5 s
Instalando programa...
Programa instalado correctamente        ← a los 7 s más
Tiempo total: 12 s
```

Es **lo que se quiere** en este ejemplo: **primero se analiza** y **solo después** se instala. El orden lo marca el `Join()` colocado **inmediatamente después** de cada `Start()`.

> Importante: el vídeo dice que así "los dos hilos trabajan en paralelo", pero con un `Join()` justo después de cada `Start()` **no hay paralelismo**: el segundo hilo **ni siquiera arranca** hasta que el primero termina. Tardan **5 + 7 = 12 segundos**.

## B) Los dos hilos a la vez: paralelismo de verdad

Para que corran **a la vez** hay que **arrancar los dos primero** y **esperar después**:

```csharp
ejecucionParalela.hiloAnalisis.Start();
ejecucionParalela.hiloInstalador.Start();      // arranca sin esperar a que acabe el análisis

ejecucionParalela.hiloAnalisis.Join();
ejecucionParalela.hiloInstalador.Join();
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
| **A)** | `Start` + `Join`, `Start` + `Join` | **12 s** (5 + 7) |
| **B)** | `Start`, `Start`, `Join`, `Join` | **7 s** (el mayor) |

> Esa es la idea del paralelismo, pero **en este ejemplo concreto no tiene sentido instalar sin haber terminado de analizar**: sirve para ver la **diferencia**. En un caso real, dos tareas **independientes** (descargar dos archivos) sí se harían como en B).

El orden de las dos primeras líneas de B) (`Analizando...` e `Instalando...`) **puede variar** de una ejecución a otra: los hilos arrancan casi a la vez y el sistema decide quién escribe antes.

### Un hilo solo se inicia una vez

En B) se crea **un objeto `Instalador` nuevo**: un `Thread` **no se puede volver a iniciar** una vez terminado (daría `ThreadStateException`). Para repetir la tarea hay que crear otro `Thread`.

### Medir el tiempo: `Stopwatch`

```csharp
Stopwatch reloj = Stopwatch.StartNew();
...
Console.WriteLine("Tiempo total: " + Math.Round(reloj.Elapsed.TotalSeconds) + " s");
```

`Stopwatch` (de `System.Diagnostics`) es un cronómetro: así se comprueba la diferencia entre A) y B).

## Resumen

- Se pueden crear **tantos hilos como se necesiten** en un programa, para hacer varios procesos.
- **`Start()`** inicia; **`Join()`** espera; **`Sleep()`** marca cuánto tarda cada tarea.
- Con `Start` + `Join` seguidos, los hilos van **uno detrás de otro**; con todos los `Start` primero y los `Join` después, van **a la vez**.

## Ejecutar

```bash
dotnet run
```

Tarda unos 19 segundos (12 s + 7 s).

## Comandos

```bash
cd csharp/08-concurrency
dotnet new console -n ThreadsPart2 -o 60-threads-part-2
cd 60-threads-part-2
dotnet run
```

Fuente: [curso de C# de INFORMATICONFIG](https://www.youtube.com/watch?v=Vg-b7IySPvc&list=PL2Z95CSZ1N4F7mym8Ca16RoTDVAlIOPlT&index=60), vídeo 60.
