# Programación orientada a objetos en Java

## Qué es la POO

Un modo de programar que **organiza el software en torno a objetos**, que combinan **datos** y **comportamientos**. Su idea es **modelar las entidades del mundo real** (un auto, un libro, una persona, un cliente) dentro del programa, para que sea más fácil de **diseñar, mantener y ampliar**.

Java es un lenguaje **orientado a objetos desde su base**: todo el código vive dentro de clases. Hasta ahora ya se ha trabajado con objetos sin llamarlo así: cada vez que se hace `new` se crea uno.

## Para qué sirve

- **Reutilizar código**, sobre todo con la herencia.
- **Modularizar**: cada clase es una pieza independiente.
- **Proteger los datos** con el encapsulamiento.
- Crear programas **más robustos y fáciles de mantener** a medida que crecen.

## Clase y objeto

| Concepto | Qué es | Ejemplo |
|---|---|---|
| **Clase** | La **plantilla** (el molde): describe cómo son y qué hacen | `class Auto` |
| **Objeto** | Una **instancia**: un ejemplar concreto creado con `new` | `auto1`, `auto2` |
| **Atributo** | Un **dato** del objeto (su estado) | `marca`, `color`, `velocidadActual` |
| **Método** | Un **comportamiento** (lo que puede hacer) | `encender()`, `acelerar()` |

```text
Clase Auto  ─── new ───►  auto1:  Toyota, RAV4, negro, 2025
(plantilla)     new ───►  auto2:  Ford, Fiesta, rojo, 2018
```

Todos los objetos de una clase tienen **los mismos atributos y métodos**, pero **cada uno con sus propios valores**.

## Objetos del mundo real

| Objeto | Atributos | Métodos |
|---|---|---|
| **Auto** | Marca, modelo, color, año, velocidad | Encender, acelerar, frenar, girar |
| **Libro** | Título, autor, editorial, año | Abrir, cerrar, leer |
| **Persona** | Nombre, apellido, edad | Comer, dormir, pensar, trabajar |

## Los cuatro pilares

| Pilar | Idea | Se verá en |
|---|---|---|
| **Abstracción** | Quedarse con lo **esencial** de una entidad e ignorar los detalles irrelevantes | Al diseñar cada clase |
| **Encapsulamiento** | **Ocultar** los detalles internos y exponer solo lo necesario | Lecciones 36, 37 y 39 |
| **Herencia** | Una clase **hereda** atributos y métodos de otra | Lección 38 |
| **Polimorfismo** | Objetos de clases distintas se tratan como **uno común** | Clases abstractas (lección 48) |

## Ejemplo práctico: la clase `Auto`

### Atributos

```java
class Auto {
    public String marca;
    public String modelo;
    public String color;
    public int anio;
    public int velocidadMaxima;

    public boolean encendido = false;      // estado
    public int velocidadActual = 0;        // estado
}
```

- Son los **datos** del objeto: unos describen cómo es (`marca`, `color`) y otros **su estado actual** (`encendido`, `velocidadActual`), que cambian al usarlo.
- Se escribe `anio` y no `año`: **sin ñ** en los nombres, como se vio con las variables.
- El año podría ser una fecha (`LocalDate`), pero se verá en su propia lección.

### Constructor

Da valores iniciales a los atributos con los parámetros (lecciones 29 y 31):

```java
public Auto(String marca, String modelo, String color, int anio, int velocidadMaxima) {
    this.marca = marca;
    this.modelo = modelo;
    this.color = color;
    this.anio = anio;
    this.velocidadMaxima = velocidadMaxima;
}
```

```java
Auto auto1 = new Auto("Toyota", "RAV4", "negro", 2025, 250);
```

### Métodos que muestran: `mostrarInfo`

```java
public void mostrarInfo() {
    System.out.println("=== Información del auto ===");
    System.out.println("Marca: " + marca);
    System.out.println("Modelo: " + modelo);
    // ...
}
```

Solo **consulta** los atributos del objeto. Para verlo hay que llamarlo: `auto1.mostrarInfo();`.

### Métodos que cambian el estado

```java
public void encender() {
    encendido = true;
    System.out.println("Auto encendido");
}

public void acelerar() {
    if (!encendido) {
        System.out.println("Auto apagado, no se puede acelerar");
        return;
    }

    velocidadActual = Math.min(velocidadActual + 10, velocidadMaxima);
    System.out.println("Auto acelerando. Velocidad actual: " + velocidadActual + " km/h");
}
```

- Un método **modifica atributos** del objeto: `encender` pone `encendido` a `true`.
- Su comportamiento **depende del estado**: acelerar con el auto apagado no funciona.
- `Math.min(...)` impide superar la velocidad máxima.
- `return;` dentro de un método `void` **sale del método** sin hacer el resto.

### Métodos que devuelven un valor

```java
public boolean estaEncendido() {
    return encendido;
}

public String describir() {
    return marca + " " + modelo + " (" + color + ", " + anio + ")";
}
```

### Cada objeto, su estado

```java
Auto auto1 = new Auto("Toyota", "RAV4", "negro", 2025, 250);
Auto auto2 = new Auto("Ford", "Fiesta", "rojo", 2018, 180);

auto2.encender();

System.out.println(auto1.estaEncendido());    // false
System.out.println(auto2.estaEncendido());    // true
```

Encender `auto2` **no afecta** a `auto1`: tienen valores independientes.

## Una aclaración sobre `get` y `set`

A veces se dice "métodos *set*" a los que no devuelven nada (`void`) y "*get*" a los que devuelven un valor. En rigor, **getter y setter** son métodos concretos para **leer** y **modificar un atributo** (por ejemplo, `getMarca()` y `setMarca(...)`), muy usados en Java (los generan los IDE), y se verán con el encapsulamiento.

Lo que aquí se llama **métodos de consulta** (`describir`, `estaEncendido`) y **métodos que cambian el estado** (`encender`, `acelerar`) es la forma habitual de dar funcionalidad al objeto.

## Ejemplo

```java
public class OopIntroduction {
    public static void main(String[] args) {
        Auto auto1 = new Auto("Toyota");
        auto1.encender();
    }
}

class Auto {
    public String marca;
    public boolean encendido = false;

    public Auto(String marca) {
        this.marca = marca;
    }

    public void encender() {
        encendido = true;
        System.out.println(marca + " encendido");
    }
}
```

## Comandos

```bash
cd java/06-advanced-oop/35-oop-introduction
java OopIntroduction.java
```

Fuente: [curso de C# de INFORMATICONFIG](https://www.youtube.com/watch?v=vHuT4uNPRtQ&list=PL2Z95CSZ1N4F7mym8Ca16RoTDVAlIOPlT&index=35), vídeo 35.
