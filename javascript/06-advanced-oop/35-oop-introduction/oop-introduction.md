# Programación orientada a objetos en JavaScript

## Qué es la POO

Un modo de programar que **organiza el software en torno a objetos**, que combinan **datos** y **comportamientos**. Su idea es **modelar las entidades del mundo real** (un auto, un libro, una persona, un cliente) dentro del programa, para que sea más fácil de **diseñar, mantener y ampliar**.

JavaScript es un lenguaje **multiparadigma**: permite programar con objetos, pero no obliga. Aun así, casi todo en JavaScript **es un objeto** (arrays, funciones, `Map`...), y la sintaxis `class` permite organizar el código igual que en C# y Java.

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
| **Polimorfismo** | Objetos de clases distintas se tratan como **uno común** | Lección 48 |

## Ejemplo práctico: la clase `Auto`

### Atributos

```javascript
class Auto {
    marca;
    modelo;
    color;
    anio;
    velocidadMaxima;

    encendido = false;      // estado
    velocidadActual = 0;    // estado
}
```

- Son los **datos** del objeto: unos describen cómo es (`marca`, `color`) y otros **su estado actual** (`encendido`, `velocidadActual`), que cambian al usarlo.
- No se declara el tipo de cada atributo.
- Se escribe `anio` y no `año`: **sin ñ** en los nombres, como se vio con las variables.

### Constructor

Da valores iniciales a los atributos con los parámetros (lecciones 29 y 31):

```javascript
constructor(marca, modelo, color, anio, velocidadMaxima) {
    this.marca = marca;
    this.modelo = modelo;
    this.color = color;
    this.anio = anio;
    this.velocidadMaxima = velocidadMaxima;
}
```

```javascript
const auto1 = new Auto("Toyota", "RAV4", "negro", 2025, 250);
```

### Métodos que muestran: `mostrarInfo`

```javascript
mostrarInfo() {
    console.log("=== Información del auto ===");
    console.log(`Marca: ${this.marca}`);
    console.log(`Modelo: ${this.modelo}`);
    // ...
}
```

Solo **consulta** los atributos del objeto. Para verlo hay que llamarlo: `auto1.mostrarInfo();`.

### Métodos que cambian el estado

```javascript
encender() {
    this.encendido = true;
    console.log("Auto encendido");
}

acelerar() {
    if (!this.encendido) {
        console.log("Auto apagado, no se puede acelerar");
        return;
    }

    this.velocidadActual = Math.min(this.velocidadActual + 10, this.velocidadMaxima);
    console.log(`Auto acelerando. Velocidad actual: ${this.velocidadActual} km/h`);
}
```

- Un método **modifica atributos** del objeto: `encender` pone `encendido` a `true`. Siempre con `this.`.
- Su comportamiento **depende del estado**: acelerar con el auto apagado no funciona.
- `Math.min(...)` impide superar la velocidad máxima.
- `return;` **sale del método** sin hacer el resto.

### Métodos que devuelven un valor

```javascript
estaEncendido() {
    return this.encendido;
}

describir() {
    return `${this.marca} ${this.modelo} (${this.color}, ${this.anio})`;
}
```

### Cada objeto, su estado

```javascript
const auto1 = new Auto("Toyota", "RAV4", "negro", 2025, 250);
const auto2 = new Auto("Ford", "Fiesta", "rojo", 2018, 180);

auto2.encender();

console.log(auto1.estaEncendido());    // false
console.log(auto2.estaEncendido());    // true
```

Encender `auto2` **no afecta** a `auto1`: tienen valores independientes.

## Una aclaración sobre `get` y `set`

A veces se dice "métodos *set*" a los que no devuelven nada y "*get*" a los que devuelven un valor. En rigor, **getter y setter** son métodos concretos para **leer** y **modificar un atributo**. JavaScript tiene incluso una sintaxis propia (`get marca() { ... }` y `set marca(valor) { ... }`) que se verá con el encapsulamiento.

Lo que aquí se llama **métodos de consulta** (`describir`, `estaEncendido`) y **métodos que cambian el estado** (`encender`, `acelerar`) es la forma habitual de dar funcionalidad al objeto.

## Ejemplo

```javascript
class Auto {
    encendido = false;

    constructor(marca) {
        this.marca = marca;
    }

    encender() {
        this.encendido = true;
        console.log(this.marca + " encendido");
    }
}

const auto1 = new Auto("Toyota");
auto1.encender();
```

## Comandos

```bash
cd javascript/06-advanced-oop
node 35-oop-introduction/index.js
```

Fuente: [curso de C# de INFORMATICONFIG](https://www.youtube.com/watch?v=vHuT4uNPRtQ&list=PL2Z95CSZ1N4F7mym8Ca16RoTDVAlIOPlT&index=35), vídeo 35.
