# Depuración (*debugging*) en C#

## Qué es depurar

El proceso de **identificar, analizar y corregir errores** en un programa. Se hace con el **depurador**: una herramienta del editor que ejecuta el programa **paso a paso**, lo **detiene** donde se le indica y permite **ver el valor de las variables** en cada momento.

Sirve para:

- **Encontrar la causa** de un fallo, no solo ver que existe.
- **Entender el flujo** real del programa (por dónde pasa, qué valen las variables).
- Mejorar la calidad del código y **evitar problemas** cuando ya está en uso.

## Tipos de errores

| Tipo | Qué pasa | Quién lo detecta |
|---|---|---|
| **De sintaxis** | El código está mal escrito (falta un `;`, un nombre mal puesto) | El **compilador**: no deja ejecutar |
| **En tiempo de ejecución** | El programa falla mientras corre (dividir entre cero, archivo inexistente) | Una **excepción** (lección 41) |
| **Lógicos** | El programa funciona, pero **no hace lo que se quería** | **Nadie avisa**: hay que depurar |

Los errores **lógicos** son los más difíciles: no hay mensaje de error, solo un resultado equivocado.

## Un ejemplo con error lógico

```csharp
int a = 30;
int b = 20;

int suma = a + b;
int resta = a - b;
int multiplicacion = a * b;

Console.WriteLine("Suma: " + suma);
Console.WriteLine("Resta: " + resta);
Console.WriteLine("Multiplicación: " + suma);       // ← error: debería ser multiplicacion
```

```text
Suma: 50
Resta: 10
Multiplicación: 50      ← debería ser 600
```

El programa compila y no lanza excepciones. Solo el resultado, `50`, es **ilógico**: `30 × 20` no es `50`.

## Puntos de interrupción (*breakpoints*)

Un punto de interrupción indica **dónde detener** la ejecución.

- Se pone con **un clic en el margen izquierdo** del editor (junto al número de línea), o con **`F9`** sobre la línea.
- Aparece un **círculo rojo**.
- La línea marcada **todavía no se ha ejecutado** cuando el programa se detiene en ella.
- Se pueden poner **varios**.
- Para quitarlo: otro clic o `F9`.

## Iniciar la depuración

| Acción | Atajo |
|---|---|
| **Iniciar depuración** | `F5` |
| Ejecutar **sin** depurar (lo normal) | `Ctrl + F5` |
| **Detener** la depuración | `Shift + F5` |
| **Reiniciar** | `Ctrl + Shift + F5` |

Al ejecutar con `F5`, el programa corre normalmente hasta el **primer punto de interrupción**, donde se **pausa**. La línea en la que se detiene se marca en **amarillo** con una flecha.

## Moverse por el programa

| Botón | Atajo | Qué hace |
|---|---|---|
| **Continuar** (*Continue*) | `F5` | Sigue hasta el **siguiente** punto de interrupción |
| **Paso por encima** (*Step Over*) | `F10` | Ejecuta la línea **sin entrar** en los métodos que llame |
| **Paso a paso por instrucciones** (*Step Into*) | `F11` | Ejecuta la línea y, si llama a un método, **entra** en él |
| **Paso a paso para salir** (*Step Out*) | `Shift + F11` | **Sale** del método actual y vuelve a quien lo llamó |

Diferencias:

- **Step Over**: si la línea llama a un método que ya se sabe que funciona, se salta sin recorrerlo.
- **Step Into**: si se sospecha del método, se entra a revisarlo línea a línea.
- **Step Out**: si ya se ha entrado y se ve que no es ahí, se sale de golpe.
- **Continue**: salta directamente entre puntos de interrupción, sin ir línea a línea.

## Ver los valores

Con el programa **pausado** se examinan las variables:

| Dónde | Qué muestra |
|---|---|
| **Pasar el ratón** sobre una variable | Su valor actual |
| **Locales / Variables** | Todas las variables del método actual y su valor |
| **Inspección** (*Watch*) | Las expresiones que se quieran vigilar (`a * b`, `total > 10`) |
| **Pila de llamadas** (*Call Stack*) | La cadena de métodos que llevó hasta aquí |
| **Consola de depuración** | Evaluar expresiones al momento |

Las variables que **todavía no se han asignado** muestran su valor por defecto (`0` en un `int`): por eso `suma` valía `0` antes de ejecutar `a + b`.

## Depurar el ejemplo

1. Poner un punto de interrupción en `int suma = a + b;`.
2. Pulsar `F5`: el programa se detiene allí.
3. Con `F10`, avanzar línea a línea mirando las variables:

| Tras ejecutar... | `suma` | `resta` | `multiplicacion` |
|---|---|---|---|
| `suma = a + b` | `50` | `0` | `0` |
| `resta = a - b` | `50` | `10` | `0` |
| `multiplicacion = a * b` | `50` | `10` | **`600`** |

4. Al llegar a `Console.WriteLine`, se ve que `multiplicacion` vale **600** pero se está mostrando **`suma`**: ahí está el error.
5. Detener (`Shift + F5`), **corregir** y volver a ejecutar.

El depurador **no corrige el código**: enseña **dónde** y **por qué** falla, y el programador lo arregla.

## Un error clásico: uno de menos

```csharp
static int SumaHastaConError(int n)
{
    int total = 0;

    for (int i = 1; i < n; i++)      // debería ser i <= n
    {
        total += i;
    }

    return total;
}
```

`SumaHastaConError(5)` da `10` en lugar de `15`. Con un punto de interrupción en el `for` y `Step Over`, se ve que **`i` nunca llega a 5**: la última vuelta es con `i = 4`. Cambiar `<` por `<=` lo arregla.

## Depurar mostrando valores (trazas)

Cuando no se puede usar el depurador, se **muestran los valores** mientras el programa corre:

```csharp
Console.WriteLine($"  i={i}, total={total}");
```

Es rápido, pero hay que **escribirlo y luego borrarlo**. El depurador permite ver lo mismo sin tocar el código.

## Herramientas de C#

```csharp
using System.Diagnostics;

Debug.WriteLine("Mensaje");                       // a la ventana de Salida del depurador
Debug.Assert(resultado == 6, "Debería ser 6");    // avisa si la condición es falsa (solo en Debug)
Console.WriteLine(Debugger.IsAttached);           // ¿hay un depurador conectado?
Debugger.Break();                                 // punto de interrupción escrito en el código
```

- Las llamadas a `Debug.` **solo existen en compilaciones Debug**: se eliminan en Release, así que no cuestan nada en producción.
- `Debug.WriteLine` **no escribe en la consola**, sino en la ventana de Salida.

## Puntos de interrupción más potentes

| Tipo | Para qué |
|---|---|
| **Condicional** | Se detiene solo si se cumple algo (`i == 4`). Ideal en bucles largos |
| **Contador de visitas** | Se detiene a la enésima vez que pasa por la línea |
| **Punto de registro** (*logpoint*) | **Escribe un mensaje** al pasar, sin detener el programa ni tocar el código |

Se crean con **clic derecho** sobre el círculo rojo.

## Herramientas

| Editor | Depurador |
|---|---|
| **Visual Studio** | Incluido |
| **Visual Studio Code** | Extensión **C# Dev Kit** (o C#), que añade `F5` para depurar |

## Buenas prácticas

- **Reproducir** el error antes de depurar: con los mismos datos.
- **Reducir** el problema: probar con un caso pequeño.
- **Sospechar** del código propio antes que del lenguaje.
- Anotar **qué se esperaba** y **qué se obtiene**: el error está donde se separan.
- **Quitar** los puntos de interrupción y las trazas al terminar.

## Ejemplo

```csharp
int a = 30;
int b = 20;

int multiplicacion = a * b;      // punto de interrupción aquí, y F10

Console.WriteLine("Multiplicación: " + multiplicacion);
Console.ReadKey();
```

## Comandos

```bash
cd csharp/06-advanced-oop
dotnet new console -n Debugging -o 44-debugging
cd 44-debugging
dotnet run
```

Para depurar: abrir el proyecto en Visual Studio o Visual Studio Code y pulsar `F5`.

Fuente: [curso de C# de INFORMATICONFIG](https://www.youtube.com/watch?v=ersgkd7ddFA&list=PL2Z95CSZ1N4F7mym8Ca16RoTDVAlIOPlT&index=44), vídeo 44.
