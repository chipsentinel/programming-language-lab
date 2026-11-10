# Clases abstractas en JavaScript

## Qué es una clase abstracta

Una clase que **no se puede instanciar**: no se pueden crear objetos directamente de ella. Su propósito es **servir de base** para otras clases (herencia, lección 38): define **lo que tienen en común** y **obliga a las clases hijas** a completar ciertas partes.

Es como una **plantilla incompleta**: dice "todo animal puede hacer un sonido", pero no dice cuál. Cada animal concreto lo decide.

## JavaScript no tiene `abstract`

C# y Java tienen la palabra clave `abstract`. **JavaScript no.** Hay que **imitarla a mano**, y la gran diferencia es que **el lenguaje no avisa antes de ejecutar**: los errores aparecen **al ejecutar**, no al compilar.

| | C# y Java | JavaScript |
|---|---|---|
| Palabra clave | `abstract` | **No existe** |
| Instanciar la abstracta | **Error de compilación** | Hay que **impedirlo a mano** |
| Hija sin implementar | **Error de compilación** | Falla **al llamar** al método |
| Redefinir | `override` / `@Override` | Mismo nombre, sin palabra clave |

## Impedir que se instancie: `new.target`

Dentro de un constructor, **`new.target`** es la clase con la que se hizo `new`. Si es la propia clase abstracta, se lanza un error:

```javascript
class Animal {
    nombre;

    constructor(nombre) {
        if (new.target === Animal) {
            throw new TypeError("No se puede instanciar la clase abstracta Animal");
        }

        this.nombre = nombre;
    }
}

new Animal("Genérico");       // TypeError: No se puede instanciar la clase abstracta Animal
```

Con una hija, `new.target` es la hija (`Perro`), y el constructor sigue adelante.

## Método "abstracto": que falle si no se implementa

Se escribe en la clase base un método cuyo **único contenido es lanzar un error**:

```javascript
hacerSonido() {
    throw new Error(`${this.constructor.name} debe implementar hacerSonido()`);
}
```

- Si la hija lo redefine, se ejecuta **el de la hija** y el error nunca se lanza.
- Si la hija **lo olvida**, el error salta **al llamarlo**:

```javascript
class Pez extends Animal { }              // se olvidó implementar hacerSonido

new Pez("Nemo").hacerSonido();            // Error: Pez debe implementar hacerSonido()
```

`this.constructor.name` da el nombre de la clase real del objeto.

## Implementarlo en las hijas

Se **redefine** el método con el mismo nombre. No hay `override`:

```javascript
class Perro extends Animal {
    hacerSonido() {
        console.log(`${this.nombre} está ladrando`);
    }
}

class Gato extends Animal {
    hacerSonido() {
        console.log(`${this.nombre} está maullando`);
    }
}
```

- Si la hija no define constructor, **usa el del padre** (como `Perro` y `Gato`).
- A diferencia de Java con `@Override`, **no hay nada que compruebe** que el nombre está bien escrito: `hacerSonid()` crearía un método nuevo sin avisar, y el original seguiría lanzando el error.

```javascript
const miPerro = new Perro("Snoopy");

miPerro.comer();         // Snoopy está comiendo   (heredado de Animal)
miPerro.hacerSonido();   // Snoopy está ladrando   (redefinido en Perro)
```

## Métodos con código por defecto

En JavaScript **todos los métodos se pueden redefinir**: no existe `virtual`.

```javascript
dormir() {
    console.log(`${this.nombre} está durmiendo`);     // por defecto
}
```

`Gato` puede redefinirlo; `Vaca` lo deja como está. Para llamar a la versión del padre desde la hija se usa `super.dormir()`.

## Polimorfismo

El cuarto pilar de la POO (lección 35): **objetos de clases distintas se tratan como uno común**.

```javascript
const animales = [new Perro("Snoopy"), new Gato("Garfield"), new Vaca("Lola")];

for (const animal of animales) {
    animal.hacerSonido();
}
```

```text
Snoopy está ladrando
Garfield está maullando
Lola está mugiendo
```

- Al llamar a `hacerSonido()`, se ejecuta la versión de **la clase real** del objeto.
- Quien recorre el array **no necesita saber** qué animal es cada uno.
- En JavaScript el polimorfismo es todavía más flexible: **no hace falta una clase común**. Cualquier objeto con un método `hacerSonido()` sirve (*duck typing*: "si camina como un pato y suena como un pato, es un pato"). Por eso en este lenguaje la clase abstracta es menos necesaria que en C# o Java.

## Cuándo usar una clase abstracta

| Usar | Cuándo |
|---|---|
| **Clase "abstracta"** | Hay un **concepto general** que no tiene sentido como objeto concreto y las hijas comparten código |
| **Clase normal** | El concepto se puede instanciar tal cual |
| **Funciones o composición** | Es lo más habitual en JavaScript cuando solo hace falta compartir comportamiento |

## Ejemplo

```javascript
class Figura {
    constructor() {
        if (new.target === Figura) {
            throw new TypeError("Figura es abstracta");
        }
    }

    area() {
        throw new Error("Debe implementarse en la clase hija");
    }
}

class Cuadrado extends Figura {
    constructor(lado) {
        super();
        this.lado = lado;
    }

    area() {
        return this.lado * this.lado;
    }
}

const figura = new Cuadrado(4);
console.log("Área: " + figura.area());      // 16
```

## Comandos

```bash
cd javascript/06-advanced-oop
node 48-abstract-classes/index.js
```

Fuente: [curso de C# de INFORMATICONFIG](https://www.youtube.com/watch?v=t7ZhwYmIIH4&list=PL2Z95CSZ1N4F7mym8Ca16RoTDVAlIOPlT&index=48), vídeo 48.
