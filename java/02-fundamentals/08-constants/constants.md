# Constantes en Java

## Qué es una constante

Una variable que **no se puede reasignar** después de recibir su valor. Java no tiene `const`: se usa la palabra `final`.

```java
final double PI = 3.14159;
```

```text
final    double   PI    =    3.14159;
palabra  tipo     nombre =   valor
```

```java
int edad = 30;
edad = 31;          // válido

final int DIAS = 7;
DIAS = 8;           // error: no se puede asignar a una variable final
```

## Para qué sirven

- **Evitar números mágicos**: el nombre explica qué es el valor.
- **Cambiarlo en un solo sitio**: si el IVA cambia, se modifica la constante y se actualiza en todo el programa.
- **Protegerlo**: el compilador avisa si alguien intenta modificarlo.

```java
double total = precio * 1.21;          // ¿qué es 1.21?

final double IVA = 0.21;
double total = precio * (1 + IVA);     // el nombre lo explica
```

## Reglas

- **Se le da valor una sola vez**. Normalmente al declararla, pero también se puede dejar para después: `final int meses;` y más tarde `meses = 12;`. Una segunda asignación es un error.
- **Vale cualquier tipo**: números, `boolean`, `char`, `String` y también objetos.
- **El valor puede calcularse al ejecutar**: `final int n = Integer.parseInt("5");` es válido.
- **Admite `var`**: `final var x = 5;`.

```java
final int DIAS = 7;
final int SEMANAS = DIAS * 4;     // válido
final String SALUDO = "Hola " + "Sol";

final int MESES;                  // válido: falta darle valor
System.out.println(MESES);        // error: variable no inicializada
```

## `final` no hace inmutable el contenido

`final` impide **reasignar** la variable, pero un objeto o un array guardado en ella sí se puede modificar.

```java
final int[] numeros = {1, 2, 3};

numeros[0] = 10;            // válido: se modifica el contenido
numeros = new int[3];       // error: se intenta reasignar
```

## Constantes de clase: `static final`

Si la constante se usa en varios métodos, se declara fuera de `main`, dentro de la clase, con `static final`.

```java
public class Constants {
    static final double PI = 3.1416;

    public static void main(String[] args) {
        System.out.println(PI);
    }
}
```

## Convención de nombre

- Una constante de clase (`static final`) usa `MAYUSCULAS_CON_GUION_BAJO`: `PI`, `IVA`, `DIAS_SEMANA`.
- Una variable local `final` usa `camelCase`, como cualquier variable: `final int numero2 = 44;`.

## Constantes que ya trae Java

```java
System.out.println(Math.PI);                // 3.141592653589793
System.out.println(Integer.MAX_VALUE);      // 2147483647
System.out.println(Integer.MIN_VALUE);      // -2147483648
```

`MIN_VALUE` y `MAX_VALUE`, de la lección de tipos de datos, son constantes.

## Repaso: `var` fija el tipo

`var` no es dinámico: el compilador deduce el tipo del **primer valor** y ya no cambia.

```java
var numero = 10.20;   // double

numero = 4000;        // válido: int -> double es implícito
numero = "hola";      // error: String no se convierte a double
numero = 's';         // válido: char -> double, guarda 115.0
```

- `System.out.println(numero)` muestra `115.0` y no `s`: en la variable ya solo queda el número.
- Para conservar la letra la variable debe ser `char`; para volver de `115.0` a letra, `(char) numero`.
- Una variable no se puede declarar dos veces en el mismo bloque.

## Ejemplo

```java
public class Constants {
    static final double PI = 3.14159;
    static final double IVA = 0.21;

    public static void main(String[] args) {
        double radio = 5;
        double area = PI * radio * radio;

        double precio = 100;
        double total = precio * (1 + IVA);

        System.out.println("Área del círculo de radio " + radio + ": " + area);
        System.out.println("Precio con IVA: " + total);
    }
}
```

## Comandos

```bash
cd java/02-fundamentals/08-constants
java Constants.java
```

Fuente: [curso de C# de INFORMATICONFIG](https://www.youtube.com/watch?v=O2cAgrpLLcQ&list=PL2Z95CSZ1N4F7mym8Ca16RoTDVAlIOPlT&index=8), vídeo 08.
