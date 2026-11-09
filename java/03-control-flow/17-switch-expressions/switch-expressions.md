# Expresiones `switch` en Java

## Qué es

Una forma **más corta** de escribir un `switch` cuando el objetivo es **obtener un valor**. En lugar de ejecutar instrucciones y `break` en cada `case`, la expresión `switch` **devuelve un resultado** que se guarda directamente en una variable. Existe desde **Java 14**.

| | `switch` clásico | Expresión `switch` |
|---|---|---|
| Qué es | Una instrucción: ejecuta código | Una expresión: devuelve un valor |
| Cada caso | `case 1: ...; break;` | `case 1 -> valor;` |
| `break` | Necesario | No hace falta |
| Fall-through | Sí (por olvidar `break`) | No |

## Con valores

```java
int dia = 3;

String nombreDia = switch (dia) {
    case 1 -> "Lunes";
    case 2 -> "Martes";
    case 3 -> "Miércoles";
    case 4 -> "Jueves";
    case 5 -> "Viernes";
    case 6 -> "Sábado";
    case 7 -> "Domingo";
    default -> "Día no válido";
};

System.out.println(nombreDia);   // Miércoles
```

- La variable se asigna **antes** de `switch`, y el conjunto termina en `;` porque es una asignación.
- Cada rama es `case valor -> resultado;`. La flecha `->` significa "entonces devuelve".
- **Debe ser exhaustiva**: si no se cubren todos los valores posibles hace falta `default`, o no compila.

## Varios valores en un `case`

Se separan con comas:

```java
String tipoDia = switch (dia) {
    case 6, 7 -> "Fin de semana";
    case 1, 2, 3, 4, 5 -> "Día laborable";
    default -> "Día no válido";
};
```

## Rangos

Aquí Java se diferencia de C#. En **Java 17** un `case` solo admite **valores concretos**, no condiciones como `>= 70`. Una técnica habitual es dividir para agrupar por decenas:

```java
int nota = 80;

String resultado = switch (nota / 10) {       // 80 / 10 = 8
    case 10, 9, 8, 7 -> "Pasaste el examen";          // 70 a 109
    case 6, 5 -> "Debes repetir el examen";           // 50 a 69
    default -> "Has reprobado el examen";
};
```

| `nota` | `nota / 10` | `resultado` |
|---|---|---|
| `80` | `8` | Pasaste el examen |
| `65` | `6` | Debes repetir el examen |
| `40` | `4` | Has reprobado el examen |

Funciona porque la división entre enteros descarta los decimales. Es una solución limitada: solo sirve para rangos que coinciden con decenas. Para rangos arbitrarios se usa `if / else if`.

> Desde **Java 21** existen patrones con condiciones: `case Integer n when n >= 70 -> ...`. Este proyecto trabaja con Java 17, así que no se usa.

## Bloques con `yield`

Si una rama necesita **varias instrucciones**, se escribe un bloque y se devuelve el valor con `yield`:

```java
String mensaje = switch (dia) {
    case 6, 7 -> {
        String texto = "Hoy descansas";
        yield texto.toUpperCase();
    }
    default -> "A trabajar";
};
```

## Cuándo usar cada uno

| Usar | Cuándo |
|---|---|
| Expresión `switch` | Hay que **obtener un valor** según una variable |
| `switch` clásico | Cada caso ejecuta **varias instrucciones** sin devolver nada |
| `if / else if` | Las condiciones son **rangos** o comparan variables distintas |

## Ejemplo

```java
public class SwitchExpressions {
    public static void main(String[] args) {
        int nota = 64;

        String resultado = switch (nota / 10) {
            case 10, 9, 8 -> "Pasaste el examen";
            case 6 -> "Repetir examen";
            default -> "Reprobaste el examen";
        };

        System.out.println("Nota " + nota + ": " + resultado);
    }
}
```

## Comandos

```bash
cd java/03-control-flow/17-switch-expressions
java SwitchExpressions.java
```

Fuente: [curso de C# de INFORMATICONFIG](https://www.youtube.com/watch?v=ydP8job-2a8&list=PL2Z95CSZ1N4F7mym8Ca16RoTDVAlIOPlT&index=17), vídeo 17.
