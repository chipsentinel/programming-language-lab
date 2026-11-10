# Herencia en JavaScript

## Qué es la herencia

Una forma de programar en la que **una clase deriva de otra y reutiliza sus miembros**: atributos y métodos.

| Término | Significa | Ejemplo |
|---|---|---|
| **Clase padre** (o base) | La clase de la que se hereda | `Animal` |
| **Clase hija** (o derivada) | La clase que hereda | `Perro`, `Gato` |

Una `Animal` sabe **comer** y **dormir**. Un `Perro` y un `Gato` también son animales, así que **ya saben comer y dormir** sin escribirlo otra vez, y además cada uno añade lo suyo (ladrar, maullar).

```text
          Animal
       (nombre, comer, dormir)
        /              \
     Perro            Gato
   (+ ladrar)      (+ maullar)
```

## Para qué sirve

- **Reutilizar código**: lo común se escribe **una sola vez**, en el padre.
- **Evitar repetir** lo mismo en varias clases.
- **Extensibilidad**: se pueden añadir clases hijas o nuevas funciones sin tocar la clase base.
- **Polimorfismo**: tratar objetos distintos como uno común (lección 48).

## Reglas en JavaScript

1. Una clase puede extender **una sola clase** (herencia simple).
2. **Todas las clases derivan de `Object`**. Se hace solo: por eso cualquier objeto tiene métodos como `toString()`.
3. JavaScript implementa la herencia con **prototipos**: `class` y `extends` son una sintaxis cómoda por encima.

## La clase padre

```javascript
class Animal {
    nombre;

    constructor(nombre) {
        this.nombre = nombre;
    }

    comer() {
        console.log(`${this.nombre} está comiendo`);
    }

    dormir() {
        console.log(`${this.nombre} está durmiendo`);
    }
}
```

Es una clase normal: no necesita nada especial para ser un padre.

## La clase hija: `extends`

```javascript
class Perro extends Animal {

    constructor(nombre) {
        super(nombre);
    }

    ladrar() {
        console.log(`${this.nombre} está ladrando`);
    }
}

class Gato extends Animal {

    maullar() {
        console.log(`${this.nombre} está maullando`);
    }
}
```

`Perro` **recibe** `nombre`, `comer()` y `dormir()` de `Animal`, y **añade** `ladrar()`.

### El constructor: `super(...)`

**Los constructores no se llaman solos.** Para que el padre inicialice sus datos, la hija llama a su constructor con **`super(...)`**:

```javascript
constructor(nombre) {
    super(nombre);
}
```

- Los argumentos van **al constructor del padre**.
- En una clase hija es **obligatorio** llamar a `super()` **antes** de usar `this`; si no, hay un `ReferenceError`.
- Si la hija **no define constructor** (como `Gato`), JavaScript usa el del padre automáticamente, con los mismos argumentos. Por eso `new Gato("Michi")` funciona.

## Usar las clases

```javascript
const miPerro = new Perro("Snoopy");

miPerro.dormir();     // Snoopy está durmiendo   (heredado)
miPerro.comer();      // Snoopy está comiendo    (heredado)
miPerro.ladrar();     // Snoopy está ladrando    (propio)

miPerro.nombre = "Rex";    // el atributo heredado también se puede cambiar
```

La **herencia solo va hacia abajo**: lo propio de una hija no existe en la otra.

```javascript
const miGato = new Gato("Michi");
miGato.maullar();     // correcto
miGato.ladrar();      // TypeError: miGato.ladrar is not a function
```

A diferencia de C# y Java, el error no aparece al escribirlo, sino **al ejecutarlo**.

## Relación "es un"

La herencia modela una relación **"es un"**: un perro *es un* animal.

```javascript
const animal = miPerro;
animal.comer();                                   // Rex está comiendo
console.log(miPerro instanceof Animal);           // true
console.log(animal instanceof Gato);              // false
```

`instanceof` comprueba si un objeto pertenece a una clase o a alguna de sus clases padre. Como las variables **no tienen tipo**, a diferencia de C# y Java **también se puede llamar a lo propio de la hija** a través de `animal`: `animal.ladrar()` funciona, porque el objeto real es un `Perro`.

> Si la relación es "**tiene un**" (una orden *tiene un* cliente, lección 32), no es herencia sino **colaboración**: se guarda como atributo. Conviene preguntarse "¿es un?" antes de heredar.

## La cadena de herencia

Un `Perro` tiene como padre un `Animal`, y este tiene como padre `Object`:

```javascript
Object.getPrototypeOf(Perro.prototype) === Animal.prototype;    // true
Object.getPrototypeOf(Animal.prototype) === Object.prototype;   // true
```

Cuando se llama a `miPerro.comer()`, JavaScript busca `comer` primero en `Perro`, luego en `Animal`, luego en `Object`. Es la **cadena de prototipos**.

## La herencia y el encapsulamiento

Los campos y métodos **públicos** se heredan sin problema. Los **privados con `#`** pertenecen solo a la clase donde se declaran: **la hija no los ve**, ni siquiera heredando.

| El miembro del padre es... | ¿La hija lo usa directamente? |
|---|---|
| Público | **Sí** |
| Privado (`#campo`) | **No** (solo con métodos públicos del padre) |

JavaScript no tiene `protected`; se verá en la lección siguiente cómo se resuelve.

## Ejemplo

```javascript
class Animal {
    constructor(nombre) {
        this.nombre = nombre;
    }

    comer() {
        console.log(`${this.nombre} está comiendo`);
    }
}

class Perro extends Animal {
    ladrar() {
        console.log(`${this.nombre} está ladrando`);
    }
}

const miPerro = new Perro("Snoopy");
miPerro.comer();
miPerro.ladrar();
```

## Comandos

```bash
cd javascript/06-advanced-oop
node 38-inheritance/index.js
```

Fuente: [curso de C# de INFORMATICONFIG](https://www.youtube.com/watch?v=WD8TZY8gcaU&list=PL2Z95CSZ1N4F7mym8Ca16RoTDVAlIOPlT&index=38), vídeo 38.
