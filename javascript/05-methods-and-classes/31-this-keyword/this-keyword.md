# La palabra clave `this` en JavaScript

## Qué es

`this` es una **referencia al objeto actual**: el objeto sobre el que se está ejecutando el código de la clase. Dentro de un método o constructor, `this` significa "yo, este objeto".

```javascript
class Persona {
    nombre = "";
    edad = 0;
}

const persona1 = new Persona();   // cada objeto tiene su propio nombre y su propia edad
const persona2 = new Persona();
```

Cuando se ejecuta un método de `persona1`, `this` es `persona1`; cuando se ejecuta uno de `persona2`, `this` es `persona2`.

## Diferencia clave con C# y Java

En C# y Java, `this` solo es **necesario** cuando un parámetro se llama igual que un atributo; en el resto de casos es opcional.

En JavaScript es **obligatorio siempre** para llegar a los atributos y métodos del objeto:

```javascript
mostrar() {
    console.log(this.nombre);   // correcto
    console.log(nombre);        // ReferenceError: nombre no está definido
}
```

Sin `this.`, JavaScript busca una **variable suelta** llamada `nombre`, no el atributo del objeto.

## Uso 1: distinguir atributo de parámetro

Si un **parámetro** se llama igual que un **atributo**, dentro del método el nombre se refiere al **parámetro** (lo "oculta"). Para llegar al atributo hay que escribir `this.`:

```javascript
class Persona {
    nombre = "";
    edad = 0;

    constructor(nombre, edad) {
        this.nombre = nombre;    // atributo = parámetro
        this.edad = edad;
    }
}
```

```text
this.nombre = nombre;
└─ atributo    └─ parámetro
```

Al crear `new Persona("Bart", 10)`, los valores entran por los parámetros y `this.nombre = nombre` los **copia a los atributos** del objeto.

### Qué pasa sin `this`

Sin él, el parámetro se asigna **a sí mismo** y el atributo no cambia:

```javascript
constructor(nombre, edad) {
    nombre = nombre;    // parámetro = parámetro: no hace nada
    edad = edad;
}
```

El objeto queda con `nombre = ""` y `edad = 0`: los valores iniciales.

## Uso 2: pasar o comparar el propio objeto

`this` es un valor: se puede comparar, pasar a otro método o devolver.

```javascript
esMayorQue(otra) {
    return this.edad > otra.edad;    // this: quien recibe la llamada; otra: el argumento
}
```

```javascript
const persona1 = new Persona("Bart", 10);
const persona2 = new Persona("Lisa", 8);

console.log(persona1.esMayorQue(persona2));   // true: this = persona1
console.log(persona2.esMayorQue(persona1));   // false: this = persona2
```

El mismo método da resultados distintos según **sobre qué objeto** se llama.

## Uso 3: devolver `this` para encadenar

Si un método devuelve `this`, se pueden **encadenar llamadas** sobre el mismo objeto:

```javascript
cumplirAnios() {
    this.edad++;
    return this;
}

persona1.cumplirAnios().cumplirAnios();    // la edad sube 2
```

Es el mismo patrón que ya se usó con las funciones de `string` (`texto.trim().toUpperCase()`).

## Constructores encadenados: no existen

En C# y Java un constructor puede llamar a otro con `this(...)`. En JavaScript **solo hay un constructor** por clase, así que no hace falta: los **valores por defecto** de los parámetros cubren el mismo caso.

```javascript
constructor(nombre = "Sol", edad = 34) {
    this.nombre = nombre;
    this.edad = edad;
}

const persona3 = new Persona();    // Sol, 34
```

## `this` depende de cómo se llama la función

Esta es una particularidad de JavaScript: el valor de `this` depende de **cómo se llama** al método, no de dónde está escrito.

```javascript
persona1.mostrar();              // this = persona1: funciona

const mostrarSuelto = persona1.mostrar;
mostrarSuelto();                 // TypeError: this es undefined
```

Al separar el método del objeto **se pierde su `this`**. Se arregla con **`bind`**, que lo fija:

```javascript
const mostrarFijo = persona1.mostrar.bind(persona1);
mostrarFijo();                   // Nombre: Bart, edad: 12
```

Esto pasa, por ejemplo, al pasar un método como argumento a otra función. Se retomará con las funciones flecha y los eventos.

## Resumen

| Uso | Ejemplo |
|---|---|
| Acceder a un atributo (siempre) | `this.nombre` |
| Llamar a otro método de la clase | `this.mostrar()` |
| Referirse al propio objeto | `return this.edad > otra.edad;` |
| Encadenar llamadas | `return this;` |

## Ejemplo

```javascript
class Persona {
    nombre = "";
    edad = 0;

    constructor(nombre, edad) {
        this.nombre = nombre;
        this.edad = edad;
    }

    mostrar() {
        console.log(`Nombre: ${this.nombre}, edad: ${this.edad}`);
    }
}

const persona1 = new Persona("Bart", 10);
persona1.mostrar();
```

## Comandos

```bash
cd javascript/05-methods-and-classes
node 31-this-keyword/index.js
```

Fuente: [curso de C# de INFORMATICONFIG](https://www.youtube.com/watch?v=jyUaUF79uWc&list=PL2Z95CSZ1N4F7mym8Ca16RoTDVAlIOPlT&index=31), vídeo 31.
