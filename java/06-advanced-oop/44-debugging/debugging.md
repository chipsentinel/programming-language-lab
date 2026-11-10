# Depuración (*debugging*) en Java

## Qué es depurar

El proceso de **identificar, analizar y corregir errores** en un programa. Se hace con el **depurador**: una herramienta del IDE que ejecuta el programa **paso a paso**, lo **detiene** donde se le indica y permite **ver el valor de las variables** en cada momento.

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

```java
int a = 30;
int b = 20;

int suma = a + b;
int resta = a - b;
int multiplicacion = a * b;

System.out.println("Suma: " + suma);
System.out.println("Resta: " + resta);
System.out.println("Multiplicación: " + suma);       // ← error: debería ser multiplicacion
```

```text
Suma: 50
Resta: 10
Multiplicación: 50      ← debería ser 600
```

El programa compila y no lanza excepciones. Solo el resultado, `50`, es **ilógico**: `30 × 20` no es `50`.

## Puntos de interrupción (*breakpoints*)

Un punto de interrupción indica **dónde detener** la ejecución.

- Se pone con **un clic en el margen izquierdo** del editor (junto al número de línea).
- En IntelliJ aparece un **círculo rojo**; con `Ctrl + F8` sobre la línea también se pone o quita.
- La línea marcada **todavía no se ha ejecutado** cuando el programa se detiene en ella.
- Se pueden poner **varios**.

## Iniciar la depuración

En **IntelliJ IDEA**, junto al botón verde de ejecutar (▶) hay uno con un **bicho** (🐞): **Debug**.

| Acción | Atajo en IntelliJ |
|---|---|
| **Iniciar depuración** | `Shift + F9` (o el botón del bicho) |
| **Continuar** hasta el siguiente punto | `F9` |
| **Detener** | `Ctrl + F2` |

Al depurar, el programa corre normalmente hasta el **primer punto de interrupción**, donde se **pausa**. La línea en la que se detiene se marca en **azul**.

## Moverse por el programa

| Botón | Atajo (IntelliJ) | Qué hace |
|---|---|---|
| **Step Over** | `F8` | Ejecuta la línea **sin entrar** en los métodos que llame |
| **Step Into** | `F7` | Ejecuta la línea y, si llama a un método, **entra** en él |
| **Step Out** | `Shift + F8` | **Sale** del método actual y vuelve a quien lo llamó |
| **Resume / Continue** | `F9` | Sigue hasta el **siguiente** punto de interrupción |

(En VS Code los atajos son `F10`, `F11`, `Shift + F11` y `F5`.)

Diferencias:

- **Step Over**: si la línea llama a un método que ya se sabe que funciona, se salta sin recorrerlo.
- **Step Into**: si se sospecha del método, se entra a revisarlo línea a línea.
- **Step Out**: si ya se ha entrado y se ve que no es ahí, se sale de golpe.
- **Resume**: salta directamente entre puntos de interrupción, sin ir línea a línea.

## Ver los valores

Con el programa **pausado** se examinan las variables:

| Dónde | Qué muestra |
|---|---|
| **Pasar el ratón** sobre una variable | Su valor actual |
| Los valores **en gris al lado del código** | IntelliJ los escribe en la propia línea |
| Panel **Variables** | Todas las variables del método actual |
| **Watches** | Las expresiones que se quieran vigilar (`a * b`, `total > 10`) |
| **Frames** (pila de llamadas) | La cadena de métodos que llevó hasta aquí |
| **Evaluate Expression** (`Alt + F8`) | Calcular una expresión al momento |

## Depurar el ejemplo

1. Poner un punto de interrupción en `int suma = a + b;`.
2. Iniciar con el botón **Debug**: el programa se detiene allí.
3. Con `F8`, avanzar línea a línea mirando las variables:

| Tras ejecutar... | `suma` | `resta` | `multiplicacion` |
|---|---|---|---|
| `suma = a + b` | `50` | `0` | `0` |
| `resta = a - b` | `50` | `10` | `0` |
| `multiplicacion = a * b` | `50` | `10` | **`600`** |

4. Al llegar a `println`, se ve que `multiplicacion` vale **600** pero se está mostrando **`suma`**: ahí está el error.
5. Detener, **corregir** y volver a ejecutar.

El depurador **no corrige el código**: enseña **dónde** y **por qué** falla, y el programador lo arregla.

## Un error clásico: uno de menos

```java
static int sumaHastaConError(int n) {
    int total = 0;

    for (int i = 1; i < n; i++) {      // debería ser i <= n
        total += i;
    }

    return total;
}
```

`sumaHastaConError(5)` da `10` en lugar de `15`. Con un punto de interrupción en el `for` y `Step Over`, se ve que **`i` nunca llega a 5**: la última vuelta es con `i = 4`. Cambiar `<` por `<=` lo arregla.

## Depurar mostrando valores (trazas)

Cuando no se puede usar el depurador, se **muestran los valores** mientras el programa corre:

```java
System.out.println("  i=" + i + ", total=" + total);
```

Es rápido, pero hay que **escribirlo y luego borrarlo**. El depurador permite ver lo mismo sin tocar el código. `System.err` envía el mensaje a la **salida de errores** (en el IDE sale en rojo).

## Herramientas de Java

```java
assert resultado == 6 : "Debería ser 6";    // comprueba una condición que siempre debería cumplirse
```

- **`assert`** está **desactivado por defecto**: se activa con `java -ea Archivo.java` (o `-ea` en las opciones de la configuración de ejecución del IDE).
- Si la condición es falsa, lanza un `AssertionError`.
- No debe usarse para validar datos del usuario, porque puede estar desactivado.

Java no tiene un equivalente a `Debugger.Break()`: el punto de interrupción se pone siempre en el IDE.

## Puntos de interrupción más potentes

| Tipo | Para qué |
|---|---|
| **Condicional** | Se detiene solo si se cumple algo (`i == 4`). Ideal en bucles largos |
| **Contador de visitas** | Se detiene a la enésima vez que pasa por la línea |
| **Sin detener** (*logging*) | **Escribe un mensaje** al pasar, sin detener el programa ni tocar el código |
| **De excepción** | Se detiene **cuando se lanza** una excepción concreta |

Se crean con **clic derecho** sobre el círculo rojo.

## Herramientas

| Editor | Depurador |
|---|---|
| **IntelliJ IDEA** | Incluido |
| **Visual Studio Code** | Extensión **Debugger for Java** (incluida en *Extension Pack for Java*) |

## Buenas prácticas

- **Reproducir** el error antes de depurar: con los mismos datos.
- **Reducir** el problema: probar con un caso pequeño.
- **Sospechar** del código propio antes que del lenguaje.
- Anotar **qué se esperaba** y **qué se obtiene**: el error está donde se separan.
- **Quitar** los puntos de interrupción y las trazas al terminar.

## Ejemplo

```java
public class Debugging {
    public static void main(String[] args) {
        int a = 30;
        int b = 20;

        int multiplicacion = a * b;      // punto de interrupción aquí, y F8

        System.out.println("Multiplicación: " + multiplicacion);
    }
}
```

## Comandos

```bash
cd java/06-advanced-oop/44-debugging
java Debugging.java
```

Para depurar: abrir el archivo en IntelliJ IDEA o Visual Studio Code y usar **Debug**.

Fuente: [curso de C# de INFORMATICONFIG](https://www.youtube.com/watch?v=ersgkd7ddFA&list=PL2Z95CSZ1N4F7mym8Ca16RoTDVAlIOPlT&index=44), vídeo 44.
