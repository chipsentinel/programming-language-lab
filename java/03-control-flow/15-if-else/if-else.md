# Condicionales `if` y `else` en Java

## Qué es una condicional

Una **estructura de control** que ejecuta un bloque de código u otro según se cumpla o no una condición. Hasta ahora el programa ejecutaba todas las líneas de arriba abajo; con una condicional puede **tomar decisiones**.

```text
         ┌── sí ──> hacer algo ──┐
condición                         ├──> continúa el programa
         └── no ──> (otro camino) ┘
```

## `if`

La condición va entre paréntesis y debe dar un `boolean`. Si es `true`, se ejecuta el bloque entre llaves.

```java
int edad = 20;

if (edad > 18) {
    System.out.println("Es mayor");
}
```

Si la condición es `false` y no hay nada más, **no pasa nada**: el programa sigue después del bloque.

## `else`

Define qué hacer **cuando la condición no se cumple**.

```java
if (edad < 18) {
    System.out.println("Es menor");
} else {
    System.out.println("No es menor");
}
```

Un `else` no lleva condición: recoge todo lo que no cumplió el `if`.

## `else if`

Cuando hay **más de dos caminos** se encadenan condiciones. Se evalúan **en orden** y en cuanto una se cumple, se ejecuta su bloque y **se salta el resto**.

```java
int nota = 80;

if (nota >= 70) {
    System.out.println("Felicidades, pasaste el examen con: " + nota);
} else if (nota < 70 && nota >= 50) {
    System.out.println("Debes repetir el examen, sacaste: " + nota);
} else {
    System.out.println("Has reprobado el examen, sacaste: " + nota);
}
```

| `nota` | Resultado |
|---|---|
| `80` | Felicidades, pasaste el examen |
| `65` | Debes repetir el examen |
| `40` | Has reprobado el examen |

- Se pueden poner **tantos `else if` como haga falta**.
- Solo se ejecuta **un** bloque de la cadena.
- El `else` final es opcional y va siempre al final.
- Se combinan con los operadores lógicos: `nota < 70 && nota >= 50` es un rango.

## Cuidado con los límites

`>` y `>=` no son lo mismo. Con `nota > 70` y `nota < 70`, el valor **exacto 70 no cumple ninguna condición** y cae en el `else`.

```java
int nota = 70;

if (nota > 70)  { /* no entra: 70 no es mayor que 70 */ }
if (nota >= 70) { /* entra */ }
```

Al escribir rangos conviene decidir a qué lado pertenece cada límite.

## Aprovechar el orden

Como las condiciones se evalúan en orden, no hace falta repetir el límite superior. Si el primer `if` ya descartó `nota >= 70`, en el `else if` la nota ya es menor que 70:

```java
if (nota >= 70) {
    System.out.println("Aprobado");
} else if (nota >= 50) {         // equivale a nota < 70 && nota >= 50
    System.out.println("Recuperación");
} else {
    System.out.println("Suspenso");
}
```

## Reglas

- La condición **debe ser `boolean`**. `if (nota)` es un error, y `if (nota = 70)` también: `=` asigna y `==` compara.
- Con **una sola instrucción** se pueden omitir las llaves, pero conviene ponerlas siempre.
- Los **textos se comparan con `equals`**, no con `==`:

```java
String nombre = "Sol";

if (nombre.equals("Sol")) {
    System.out.println("Hola, Sol");
}
```

## `if/else` frente al operador ternario

El ternario (lección anterior) sirve para **elegir un valor**; `if/else` para **ejecutar instrucciones**.

```java
String mensaje = (edad > 18) ? "Es mayor" : "No es mayor";   // elige un valor

if (edad > 18) { System.out.println("Es mayor"); }           // ejecuta código
```

## Ejemplo

```java
public class IfElse {
    public static void main(String[] args) {
        int nota = 80;

        System.out.println("Nota final del alumno");

        if (nota >= 70) {
            System.out.println("Felicidades, pasaste el examen con: " + nota);
        } else if (nota >= 50) {
            System.out.println("Debes repetir el examen, sacaste: " + nota);
        } else {
            System.out.println("Has reprobado el examen, sacaste: " + nota);
        }
    }
}
```

## Comandos

```bash
cd java/03-control-flow/15-if-else
java IfElse.java
```

Fuente: [curso de C# de INFORMATICONFIG](https://www.youtube.com/watch?v=3ASlc4lUvik&list=PL2Z95CSZ1N4F7mym8Ca16RoTDVAlIOPlT&index=15), vídeo 15.
