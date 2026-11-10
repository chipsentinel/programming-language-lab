# Colaboración entre clases en JavaScript

## Qué es

Hasta ahora cada ejercicio usaba **una sola clase**. En un programa real hay **varias clases que trabajan juntas**: una usa los atributos o los métodos de otra para cumplir su tarea. A eso se le llama **colaboración entre clases**.

Ejemplo del mundo real: una orden de compra **necesita un cliente**. Hay una clase `Cliente`, otra clase `Orden`, y la orden usa al cliente.

## Qué hace falta

1. Una clase que **ofrezca** atributos y métodos.
2. Otra clase que **cree o reciba** un objeto de la primera.
3. Usar ese objeto con `objeto.atributo` y `objeto.metodo()`, como ya se hacía.

## La clase que se usa: `Cliente`

```javascript
class Cliente {
    nombre;
    email;

    constructor(nombre, email) {
        this.nombre = nombre;
        this.email = email;
    }

    datos() {
        console.log(`Nombre: ${this.nombre}`);
        console.log(`Email: ${this.email}`);
    }
}
```

### Sin `public`

En C# y Java los miembros deben ser `public` para que otra clase los use. En JavaScript **todo es accesible** desde otras clases por defecto: no hace falta nada. (Existen los campos privados con `#`, que se ven con el encapsulamiento.)

## Usar una clase desde otra

No hay `Main`. Se crean los objetos directamente en el archivo, y pueden ser de cualquier clase declarada:

```javascript
const cliente1 = new Cliente("Sol", "sol@correo.com");
cliente1.datos();
```

`cliente1` es un objeto de la clase `Cliente`. Las clases deben **declararse antes de usarse** en el archivo: `class` no se "eleva" como las funciones, y usarla antes da un `ReferenceError`.

## Una clase que contiene objetos de otra

Una `Orden` tiene un **atributo cuyo valor es otro objeto**:

```javascript
class Orden {
    numero;
    cliente;     // un objeto de otra clase como atributo
    total;

    constructor(numero, cliente, total) {
        this.numero = numero;
        this.cliente = cliente;
        this.total = total;
    }

    resumen() {
        console.log(`Orden #${this.numero}`);
        this.cliente.datos();          // usa un método de Cliente
        console.log(`Total: ${this.total.toFixed(2)}`);
    }
}
```

```javascript
const orden1 = new Orden(1, cliente1, 120.5);
orden1.resumen();
```

```text
Orden #1
Nombre: Sol
Email: sol@correo.com
Total: 120.50
```

- El **constructor recibe un cliente** como parámetro. En JavaScript no se declara el tipo, así que **nada impide** pasar otra cosa (un texto, un número...) y el error aparece después, al llamar a `datos()`.
- `resumen` **delega**: para mostrar los datos del cliente llama a `this.cliente.datos()` en lugar de repetir el código. Cada clase se ocupa de lo suyo.
- Esta relación se llama "**tiene un**" (una orden *tiene un* cliente).

## Los objetos se pasan por referencia

Una variable que contiene un objeto **no guarda el objeto, sino una referencia** (la dirección donde está). Al pasar `cliente1` a la orden, **la orden y el resto del programa apuntan al mismo cliente**, no a copias:

```javascript
const orden2 = new Orden(2, cliente1, 80);

cliente1.email = "nuevo@correo.com";    // se cambia el cliente

orden1.resumen();    // Email: nuevo@correo.com
orden2.resumen();    // Email: nuevo@correo.com
```

Las **dos órdenes** ven el cambio. Es lo mismo que ocurría al asignar un array a otra variable (lección 25). Es una gran ventaja —un cliente, muchas órdenes— pero hay que tener cuidado: modificar el objeto desde un sitio lo modifica para todos.

## Beneficios

- **Cada clase tiene una responsabilidad**: `Cliente` sabe de clientes, `Orden` de órdenes.
- **Se reutiliza código**: el mismo `Cliente` sirve para muchas órdenes.
- **Cambios localizados**: si cambia cómo se muestra un cliente, se modifica solo `datos()`.

## Ejemplo

```javascript
class Cliente {
    constructor(nombre) {
        this.nombre = nombre;
    }
}

class Orden {
    constructor(cliente) {
        this.cliente = cliente;
    }

    resumen() {
        console.log("Orden de " + this.cliente.nombre);
    }
}

const cliente1 = new Cliente("Sol");
const orden1 = new Orden(cliente1);
orden1.resumen();
```

## Comandos

```bash
cd javascript/05-methods-and-classes
node 32-class-collaboration/index.js
```

Fuente: [curso de C# de INFORMATICONFIG](https://www.youtube.com/watch?v=EgzcttBzC_E&list=PL2Z95CSZ1N4F7mym8Ca16RoTDVAlIOPlT&index=32), vídeo 32.
