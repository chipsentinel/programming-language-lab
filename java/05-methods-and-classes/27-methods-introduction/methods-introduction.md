# Introducción a los métodos en Java

## Qué es un método

Un **bloque de código con nombre**, definido dentro de una clase, que realiza una tarea concreta. Se escribe una vez y se **invoca** (se llama) siempre que haga falta.

Sirven para:

- **Evitar repetir código**: se escribe una vez y se usa muchas.
- **Dividir tareas complejas** en partes pequeñas y fáciles de entender: al ver `sumar()` ya se sabe qué hace sin leer su código.
- **Reutilizar** lo ya hecho, en distintos lugares del programa.
- **Depurar** más fácil: si algo falla, se revisa el método responsable.

Un método puede **recibir datos** (parámetros, lección siguiente) y puede **devolver un resultado**.

## Método sin retorno: `void`

`void` significa "vacío": el método **hace algo pero no devuelve nada**.

```java
class Prueba {
    public void saludar() {
        System.out.println("Hola a todos");
    }
}
```

```text
public   void   saludar   ( )   { ... }
acceso   retorno  nombre  parámetros  cuerpo
```

- **`public`**: se puede usar desde fuera de la clase (se verá con el encapsulamiento).
- **`void`**: no devuelve nada.
- **`saludar`**: el nombre. En Java los métodos van en `camelCase` (empiezan en minúscula) y suelen ser **verbos**.
- **`()`**: los paréntesis, siempre. Aquí vacíos: no recibe datos.
- **`{ }`**: el cuerpo, con las instrucciones.

## Invocar un método

Primero se **instancia** la clase (lección anterior) y después se llama con `objeto.metodo()`:

```java
Prueba saludo1 = new Prueba();
saludo1.saludar();    // Hola a todos
```

- Los **paréntesis son obligatorios** al llamarlo, incluso sin datos.
- Un método se puede llamar **tantas veces** como se quiera: cada llamada ejecuta su código de nuevo.
- Al escribir el punto, el IDE lista los métodos disponibles.

## Método con retorno

En lugar de `void` se escribe el **tipo del valor que devuelve**, y el cuerpo termina con **`return`**:

```java
public int sumar() {
    int valor1 = 23, valor2 = 45;
    int total = valor1 + valor2;

    return total;
}
```

- **`int`** antes del nombre: el método devuelve un entero.
- **`return total;`** entrega ese valor a quien llamó al método y **termina** el método.
- El compilador **exige** el `return` en un método que no es `void`, y que el valor devuelto sea del tipo indicado.

Un método puede devolver cualquier tipo: `int`, `String`, `boolean`, `double`...

```java
public String despedida() {
    String mensaje = "Adiós a todos";
    return mensaje;
}
```

### Usar el valor devuelto

Se puede **guardar** en una variable o **usar directamente** donde haga falta:

```java
int resultado = saludo1.sumar();
System.out.println("Total de la suma: " + resultado);       // 68

System.out.println("Mensaje: " + despedida.despedida());    // Adiós a todos
System.out.println(saludo1.sumar() + 2);                    // 70
```

Un método `void` **no se puede guardar** en una variable: no devuelve nada.

```java
int x = saludo1.saludar();   // error: saludar no devuelve nada
```

## Comparación

| | Sin retorno | Con retorno |
|---|---|---|
| Se declara con | `void` | El tipo: `int`, `String`... |
| Lleva `return` | No | **Sí**, obligatorio |
| Sirve para | **Hacer** algo (mostrar, guardar) | **Calcular** un valor |
| Ejemplo | `saludar()` | `sumar()` |

> A veces se oye "métodos *set*" y "métodos *get*" para estos dos tipos. No es el sentido habitual: *getter* y *setter* son métodos especiales para leer y modificar atributos, y se verán con el encapsulamiento.

## Un método puede llamar a otros

Dentro de una clase, un método llama a otro de la misma clase solo con su nombre:

```java
public void presentar() {
    saludar();
    despedir();
}
```

Así se construyen tareas grandes a partir de otras pequeñas.

## Métodos `static`

Un método `static` pertenece a la **clase**, no a un objeto: se puede llamar **sin instanciar**. `main` es un método `static`, por eso el programa puede arrancar sin crear ningún objeto antes.

```java
public class MethodsIntroduction {
    static void separador() {
        System.out.println("----------");
    }

    public static void main(String[] args) {
        separador();    // sin objeto
    }
}
```

Desde un método `static` no se puede llamar a un método normal (de objeto) directamente: hace falta un objeto. `static` se verá en la lección 47.

## Ejemplo

```java
public class MethodsIntroduction {
    public static void main(String[] args) {
        Prueba prueba1 = new Prueba();

        prueba1.saludar();
        System.out.println("Total de la suma: " + prueba1.sumar());
    }
}

class Prueba {
    public void saludar() {
        System.out.println("Hola a todos");
    }

    public int sumar() {
        int total = 23 + 45;
        return total;
    }
}
```

## Comandos

```bash
cd java/05-methods-and-classes/27-methods-introduction
java MethodsIntroduction.java
```

Fuente: [curso de C# de INFORMATICONFIG](https://www.youtube.com/watch?v=lDyzFYnibdE&list=PL2Z95CSZ1N4F7mym8Ca16RoTDVAlIOPlT&index=27), vídeo 27.
