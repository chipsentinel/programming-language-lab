# Operadores de comparación en Java

## Qué son

Operadores que **comparan dos valores** y dan como resultado un `boolean`: `true` o `false`. Sirven para tomar decisiones en el flujo del programa.

| Operador | Significa | Ejemplo | Resultado |
|---|---|---|---|
| `>` | Mayor que | `10 > 20` | `false` |
| `<` | Menor que | `10 < 20` | `true` |
| `==` | Igual que | `10 == 10` | `true` |
| `>=` | Mayor o igual que | `10 >= 10` | `true` |
| `<=` | Menor o igual que | `10 <= 5` | `false` |
| `!=` | Distinto de | `10 != 20` | `true` |

```java
int a = 10;
int b = 20;

System.out.println(a > b);    // false
System.out.println(a != b);   // true
```

`println` muestra `true` y `false` en minúsculas (en C# salen con la inicial en mayúscula).

## `=` frente a `==`

- `=` **asigna**: `a = 10` guarda un 10 en `a`.
- `==` **compara**: `a == 10` pregunta si `a` vale 10.

```java
int a = 5;
boolean igual = (a == 10);   // false: solo pregunta
```

Distinto se escribe `!=`: el signo `!` significa "no".

## El operador ternario `? :`

Convierte el resultado de una comparación en un valor. Es una pregunta con dos respuestas.

```text
(condición) ? valorSiTrue : valorSiFalse
```

```java
int a = 10, b = 20;
String resultado = (a > b) ? "Sí" : "No";

System.out.println("¿a es mayor que b? " + resultado);   // No
```

- Si `a > b` es `true`, `resultado` recibe `"Sí"`.
- Si es `false`, recibe `"No"`.
- Es una forma corta de decidir. En la lección de condicionales se verá `if/else`, que sirve para lo mismo y para decisiones más largas.

## Mayor o igual y menor o igual

`>=` es verdadero si se cumple **cualquiera** de las dos condiciones: que sea mayor o que sea igual.

```java
int b = 10;

System.out.println(10 >= b);   // true: es igual
System.out.println(11 >= b);   // true: es mayor
System.out.println(1 >= b);    // false: no es ni mayor ni igual
```

El `=` va siempre **después** de `>` o `<`: `=>` y `=<` no son operadores de comparación.

## Comparar textos: `equals`

En Java `==` entre `String` **no compara el contenido**, sino si son el mismo objeto en memoria. Para comparar textos se usa el método `equals`, que distingue mayúsculas de minúsculas.

```java
String nombre = "Sol";

System.out.println(nombre.equals("Sol"));              // true
System.out.println(nombre.equals("sol"));              // false
System.out.println(nombre.equalsIgnoreCase("sol"));    // true
```

Con los tipos primitivos (`int`, `double`, `char`, `boolean`) `==` sí compara el valor, como en C#.

## Ejemplo

```java
public class ComparisonOperators {
    public static void main(String[] args) {
        int a = 10;
        int b = 20;

        System.out.println("¿a es mayor que b? " + ((a > b) ? "Sí" : "No"));
        System.out.println("¿a es menor que b? " + ((a < b) ? "Sí" : "No"));
        System.out.println("¿a es igual a b? " + ((a == b) ? "Sí" : "No"));
        System.out.println("¿a es distinto de b? " + ((a != b) ? "Sí" : "No"));
    }
}
```

## Comandos

```bash
cd java/02-fundamentals/13-comparison-operators
java ComparisonOperators.java
```

Fuente: [curso de C# de INFORMATICONFIG](https://www.youtube.com/watch?v=XKRWB9LluAA&list=PL2Z95CSZ1N4F7mym8Ca16RoTDVAlIOPlT&index=13), vídeo 13.
