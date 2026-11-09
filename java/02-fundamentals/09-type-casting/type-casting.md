# Casting en Java

## Qué es el casting

**Convertir un valor de un tipo de dato a otro**: de entero a decimal, de decimal a entero, de número a texto, de texto a número.

Hay tres formas de hacerlo:

- **Implícito**: lo hace el compilador solo (en Java se llama *widening*, ensanchamiento).
- **Explícito**: se pide con `(tipo)` (*narrowing*, estrechamiento).
- **Con métodos**: `Integer.parseInt`, `Double.parseDouble` y `String.valueOf`.

## Casting implícito

Se hace solo cuando **no se pierde información**: el tipo destino siempre puede guardar el valor.

```java
int valor = 200;
double total = valor;               // int -> double: implícito
System.out.println(total);          // 200.0
```

```text
byte -> short -> int -> long -> float -> double
                  ^
                char
```

Cualquier conversión hacia la derecha es implícita. `char` también pasa a `int`, y guarda su código ASCII:

```java
char letra = 'A';
int codigoAscii = letra;            // 65
```

## Casting explícito

Cuando se **puede perder información** el compilador no lo hace solo. Hay que pedirlo escribiendo el tipo entre paréntesis delante del valor.

```java
double precio = 500.23;

int descuento = precio;             // error: no se convierte double a int solo
int descuento = (int) precio;       // válido: 500
```

- Los decimales **se descartan**, no se redondean: `(int) 500.99` es `500`.
- Se trunca hacia cero: `(int) -7.9` es `-7`.
- El programador asume la pérdida: por eso el compilador la exige.

Para volver de un código a su letra:

```java
int codigoAscii = 65;
char letra = (char) codigoAscii;    // 'A'
```

## De texto a número

Un `String` nunca se convierte solo a un número, ni siquiera con un cast.

```java
String palabra = "123456";

int numero = palabra;                      // error
int numero = (int) palabra;                // error
int numero = Integer.parseInt(palabra);    // válido: 123456
```

| Método | Sirve para | Ejemplo |
|---|---|---|
| `Integer.parseInt(texto)` | Texto a `int` | `Integer.parseInt("123")` |
| `Double.parseDouble(texto)` | Texto a `double` | `Double.parseDouble("150.60")` |
| `Long.parseLong(texto)` | Texto a `long` | `Long.parseLong("123")` |
| `Boolean.parseBoolean(texto)` | Texto a `boolean` | `Boolean.parseBoolean("true")` |

Si el texto **no es un número válido**, lanzan un error en ejecución (`NumberFormatException`):

```java
Integer.parseInt("hola");   // NumberFormatException
```

### Java no tiene `TryParse`

En C# `int.TryParse` devuelve `true` o `false`. Java no tiene equivalente: se captura el error con `try/catch`, que se verá con las excepciones.

```java
String entrada = "hola";

try {
    int resultado = Integer.parseInt(entrada);
    System.out.println("Convertido: " + resultado);
} catch (NumberFormatException e) {
    System.out.println("\"" + entrada + "\" no es un número entero");
}
```

## El separador decimal

A diferencia de C#, `Double.parseDouble` **siempre usa el punto**, sin depender del idioma del equipo.

```java
Double.parseDouble("150.60");   // 150.6 en cualquier equipo
```

Con coma falla: `Double.parseDouble("150,60")` lanza `NumberFormatException`.

## De número a texto

```java
int edad = 30;
String texto = String.valueOf(edad);   // "30"
String texto2 = Integer.toString(edad);

String saludo = "Edad: " + edad;       // la concatenación lo hace sola
```

## Resumen

| Caso | Se pierde información | Qué se usa |
|---|---|---|
| `int` a `double` | No | Nada: implícito |
| `char` a `int` | No | Nada: implícito |
| `double` a `int` | Sí (decimales) | `(int) valor` |
| `int` a `char` | Posible | `(char) valor` |
| Texto a número | Puede fallar | `Integer.parseInt`, `Double.parseDouble` |
| Número a texto | No | `String.valueOf()` |

## Ejemplo

```java
public class TypeCasting {
    public static void main(String[] args) {
        int valor = 200;
        double total = valor;                       // implícito

        double precio = 500.23;
        int descuento = (int) precio;               // explícito: 500

        char letra = 'A';
        int codigoAscii = letra;                    // 65

        String palabra = "123456";
        int numero = Integer.parseInt(palabra);     // 123456

        System.out.println(total + " " + descuento + " " + codigoAscii + " " + numero);
    }
}
```

## Comandos

```bash
cd java/02-fundamentals/09-type-casting
java TypeCasting.java
```

Fuente: [curso de C# de INFORMATICONFIG](https://www.youtube.com/watch?v=ii2nqtVd0Aw&list=PL2Z95CSZ1N4F7mym8Ca16RoTDVAlIOPlT&index=9), vídeo 09.
