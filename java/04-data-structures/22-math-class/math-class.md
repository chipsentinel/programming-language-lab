# La clase `Math` en Java

## Qué es

Una colección de **funciones matemáticas ya hechas**. Se usan escribiendo `Math.`, el nombre del método y los valores entre paréntesis. Complementa a los operadores aritméticos (`+ - * / %`), que no cubren potencias, raíces ni redondeos.

> Qué es exactamente una clase y un método se verá más adelante. Por ahora basta con saber usarlos: `Math.metodo(valor)` devuelve un resultado que se guarda o se muestra.

No hace falta importarla: está en `java.lang`, que se importa sola.

```java
double resultado = Math.pow(4, 2);   // 16.0
```

## Funciones principales

| Método | Qué hace | Ejemplo | Resultado |
|---|---|---|---|
| `Math.pow(x, y)` | Eleva `x` a la `y` | `Math.pow(4, 2)` | `16.0` |
| `Math.sqrt(x)` | Raíz cuadrada | `Math.sqrt(4)` | `2.0` |
| `Math.abs(x)` | Valor absoluto | `Math.abs(-4)` | `4` |
| `Math.ceil(x)` | Redondea **hacia arriba** | `Math.ceil(50.23)` | `51.0` |
| `Math.floor(x)` | Redondea **hacia abajo** | `Math.floor(50.23)` | `50.0` |
| `Math.round(x)` | Redondea al **más cercano** | `Math.round(50.53)` | `51` |
| `Math.max(a, b)` | El **mayor** | `Math.max(4, 50.53)` | `50.53` |
| `Math.min(a, b)` | El **menor** | `Math.min(4, 50.53)` | `4.0` |
| `Math.PI` | El número pi | | `3.141592653589793` |

`Math.PI` no lleva paréntesis porque es un **valor**, no un método. Casi todas devuelven `double`, por eso salen con `.0`. Excepciones: `Math.abs` conserva el tipo recibido (`int` da `int`) y `Math.round` devuelve un entero (`long`).

## Potencia y raíz

```java
int numero = 4;

System.out.println(Math.pow(numero, 2));   // 16.0: 4 al cuadrado
System.out.println(Math.pow(numero, 3));   // 64.0: 4 al cubo
System.out.println(Math.sqrt(numero));     // 2.0: raíz cuadrada
```

El operador `**` no existe en Java, por eso se usa `Math.pow`.

## Valor absoluto

El valor de un número **sin su signo**:

```java
System.out.println(Math.abs(-4));   // 4
System.out.println(Math.abs(4));    // 4
```

## Redondeo

| `x` | `ceil` | `floor` | `round` |
|---|---|---|---|
| `50.23` | `51.0` | `50.0` | `50` |
| `50.53` | `51.0` | `50.0` | `51` |

- **`ceil`**: siempre sube al siguiente entero (techo).
- **`floor`**: siempre baja (suelo).
- **`round`**: va al entero más cercano; en el punto medio (`.5`) **sube**.

```java
System.out.println(Math.round(2.5));   // 3
System.out.println(Math.round(3.5));   // 4
```

A diferencia de C#, aquí no hay redondeo al par: `2.5` da `3`.

### Redondear a unos decimales

`Math.round` no tiene argumento de decimales. Se multiplica, se redondea y se divide:

```java
System.out.println(Math.round(3.14159 * 100) / 100.0);   // 3.14
```

Se divide entre `100.0` (con decimal) para que el resultado sea decimal y no una división entera.

## Máximo y mínimo

```java
System.out.println(Math.max(4, 50.53));   // 50.53
System.out.println(Math.min(4, 50.53));   // 4.0
```

Comparan dos valores a la vez. Se pueden anidar para comparar más: `Math.max(a, Math.max(b, c))`. Como uno es decimal, el otro se convierte y el mínimo sale como `4.0`.

## Trigonometría

```java
System.out.println(Math.sin(Math.PI / 2));   // 1.0
System.out.println(Math.cos(0));             // 1.0
```

`sin`, `cos` y `tan` trabajan con **radianes**, no con grados. 90 grados son `Math.PI / 2` radianes. Existe `Math.toRadians(grados)` para convertir.

## Ejercicio: área de un círculo

El área es `π · radio²`:

```java
Scanner scanner = new Scanner(System.in);

System.out.println("Indique el radio del círculo...");
double radio = Double.parseDouble(scanner.nextLine());

double area = Math.PI * Math.pow(radio, 2);

System.out.println("Área del círculo: " + area);
System.out.println("Área redondeada: " + Math.round(area));
System.out.println("Área con 2 decimales: " + Math.round(area * 100) / 100.0);
```

Con radio `5`:

```text
Área del círculo: 78.53981633974483
Área redondeada: 79
Área con 2 decimales: 78.54
```

## Ejemplo

```java
public class MathClass {
    public static void main(String[] args) {
        double precio = 50.53;

        System.out.println("Hacia arriba: " + Math.ceil(precio));
        System.out.println("Hacia abajo: " + Math.floor(precio));
        System.out.println("Redondeado: " + Math.round(precio));
        System.out.println("Elevado al cuadrado: " + Math.pow(4, 2));
    }
}
```

## Comandos

```bash
cd java/04-data-structures/22-math-class
java MathClass.java
```

Fuente: [curso de C# de INFORMATICONFIG](https://www.youtube.com/watch?v=scmOWO6FM1c&list=PL2Z95CSZ1N4F7mym8Ca16RoTDVAlIOPlT&index=22), vídeo 22.
