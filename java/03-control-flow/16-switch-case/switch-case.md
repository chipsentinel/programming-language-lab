# Condicional `switch` en Java

## Qué es

Una condicional que **compara el valor de una variable con varios casos posibles** y ejecuta el que coincide. Es una alternativa más clara a una cadena larga de `else if` que comparan siempre la misma variable.

```java
int dia = 3;

switch (dia) {
    case 1:
        System.out.println("Lunes");
        break;
    case 2:
        System.out.println("Martes");
        break;
    case 3:
        System.out.println("Miércoles");
        break;
}
```

```text
Miércoles
```

## Cómo funciona

```text
switch (variable) {
    case valor1:
        instrucciones;
        break;
    case valor2:
        instrucciones;
        break;
    default:
        instrucciones;
}
```

- **`switch (dia)`**: indica la variable que se evalúa.
- **`case 3:`**: "si `dia` vale 3". Acaba en **dos puntos**, no en punto y coma.
- **`break;`**: termina el `switch`. El programa sale y ya no mira los demás casos.
- El sistema recorre los `case` en orden hasta encontrar el que coincide.

## `default`

Se ejecuta cuando **ningún `case` coincide**. Es el `else` final del `switch`.

```java
int dia = 9;

switch (dia) {
    case 1:
        System.out.println("Lunes");
        break;
    default:
        System.out.println("Día no válido");
        break;
}
```

Sin `default`, si ningún caso coincide no pasa nada. Es opcional, pero conviene ponerlo para controlar valores inesperados.

## Varios `case` con el mismo código

Se escriben seguidos, sin código entre ellos:

```java
switch (dia) {
    case 6:
    case 7:
        System.out.println("Fin de semana");
        break;
    case 1:
    case 2:
    case 3:
    case 4:
    case 5:
        System.out.println("Día laborable");
        break;
}
```

## Con textos

`switch` también funciona con `String`, `char` y `enum`, no solo con números.

```java
String opcion = "guardar";

switch (opcion) {
    case "abrir":
        System.out.println("Abriendo el archivo");
        break;
    case "guardar":
        System.out.println("Guardando el archivo");
        break;
}
```

## Java sí deja pasar de un `case` al siguiente

Si falta el `break`, el programa **sigue ejecutando los casos siguientes** aunque no coincidan. Se llama *fall-through* y es un error muy frecuente.

```java
int dia = 1;

switch (dia) {
    case 1:
        System.out.println("Lunes");     // se ejecuta
    case 2:
        System.out.println("Martes");    // también, aunque dia no vale 2
        break;
}
```

```text
Lunes
Martes
```

En C# este código **no compila**: obliga a escribir `break` en cada `case` con código. En Java hay que acordarse de ponerlo.

## `switch` frente a `if / else if`

| Usar `switch` | Usar `if / else if` |
|---|---|
| Se compara **una variable** con valores concretos | Las condiciones son rangos (`nota >= 70`) |
| Muchos casos, como días o meses | Se combinan variables distintas con `&&` y `||` |

## Un consejo sobre nombres

En los nombres de variables **no se usan tildes ni la `ñ`**: `dia`, no `día`; `anio`, no `año`. Aunque Java los acepta, pueden dar problemas con editores, compiladores y control de versiones.

## Ejemplo

```java
public class SwitchCase {
    public static void main(String[] args) {
        int dia = 3;

        System.out.println("Días de la semana");

        switch (dia) {
            case 1:
                System.out.println("Lunes");
                break;
            case 2:
                System.out.println("Martes");
                break;
            case 3:
                System.out.println("Miércoles");
                break;
            default:
                System.out.println("Otro día");
                break;
        }
    }
}
```

## Comandos

```bash
cd java/03-control-flow/16-switch-case
java SwitchCase.java
```

Fuente: [curso de C# de INFORMATICONFIG](https://www.youtube.com/watch?v=xP7MIqlBTSU&list=PL2Z95CSZ1N4F7mym8Ca16RoTDVAlIOPlT&index=16), vídeo 16.
