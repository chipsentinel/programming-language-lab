# Equivalente a `protected` en JavaScript

## `protected` no existe en JavaScript

En C# y Java, `protected` permite que **una clase y sus hijas** usen un miembro sin que lo vea el resto del programa. JavaScript **no tiene** ese modificador.

Hay tres opciones, de menos a más protección:

| Opción | Cómo se escribe | Quién puede usarlo |
|---|---|---|
| Público | `respirar()` | Todo el programa |
| **Convención `_`** | `_respirar()` | Todo el programa, pero **avisa** de que es de uso interno |
| Privado `#` | `#respirar()` | **Solo la propia clase** (las hijas tampoco) |

## Qué es `protected` (concepto)

Para entender qué se intenta imitar: un miembro `protected` es accesible **dentro de su propia clase y de las clases que heredan de ella**, y **no desde el resto del programa**.

| | Propia clase | Clase hija | Resto del programa |
|---|---|---|---|
| Público | Sí | Sí | Sí |
| `protected` (C#, Java) | Sí | **Sí** | No |
| Privado | Sí | No | No |

Sirve para **detalles internos** del padre que las hijas pueden reutilizar pero que no forman parte de lo que ve el exterior.

## La convención del guion bajo

Lo habitual para imitar `protected` es empezar el nombre por **guion bajo**:

```javascript
class Animal {
    nombre;
    _energia = 100;                  // "protected" por convención

    constructor(nombre) {
        this.nombre = nombre;
    }

    _respirar() {                    // "protected" por convención
        console.log(`${this.nombre} está respirando`);
    }

    dormir() {
        console.log(`${this.nombre} está durmiendo`);
        this._respirar();            // la propia clase lo usa
    }
}

class Perro extends Animal {
    ladrar() {
        this._respirar();            // la hija lo usa
        console.log(`${this.nombre} está ladrando`);
    }

    correr() {
        this._energia -= 30;         // la hija modifica el atributo heredado
    }
}
```

- El guion bajo es **solo una señal** para otros programadores: "no uses esto desde fuera".
- Las hijas heredan el miembro y lo usan **con `this.`**, como cualquier otro.

### Pero no lo protege

```javascript
const miPerro = new Perro("Rex");

miPerro._respirar();             // funciona: JavaScript no lo impide
console.log(miPerro._energia);   // funciona
```

Desde fuera **se puede usar igual**. La convención depende de la disciplina del equipo. En C# y Java el compilador lo prohíbe.

## `#`: más protección, pero ni las hijas

Con `#` el miembro es privado de verdad, pero **tampoco lo ve la clase hija**:

```javascript
class Base {
    #secreto = 42;

    leerSecreto() {
        return this.#secreto;
    }
}

class Derivada extends Base {
    intentarLeer() {
        return this.#secreto;        // SyntaxError: el campo privado no existe en Derivada
    }
}
```

La hija debe usar un **método público del padre**:

```javascript
class Derivada extends Base {
    intentarLeer() {
        return this.leerSecreto();   // correcto
    }
}
```

Es el equivalente a `private` en C# y Java.

## Qué elegir

| Se quiere... | Se usa... |
|---|---|
| Que las hijas lo reutilicen y avisar al exterior | **`_nombre`** (convención) |
| Que **nadie fuera de la clase**, ni las hijas, lo toque | **`#nombre`** |
| Que lo use todo el mundo | `nombre` |

## Ejemplo

```javascript
class Animal {
    constructor(nombre) {
        this.nombre = nombre;
    }

    _respirar() {
        console.log(`${this.nombre} está respirando`);
    }
}

class Perro extends Animal {
    ladrar() {
        this._respirar();
        console.log(`${this.nombre} está ladrando`);
    }
}

const miPerro = new Perro("Rex");
miPerro.ladrar();
```

## Comandos

```bash
cd javascript/06-advanced-oop
node 39-encapsulation-protected/index.js
```

Fuente: [curso de C# de INFORMATICONFIG](https://www.youtube.com/watch?v=TrVo7IxkH44&list=PL2Z95CSZ1N4F7mym8Ca16RoTDVAlIOPlT&index=39), vídeo 39.
