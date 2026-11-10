# Encapsulamiento privado en JavaScript

## Qué es un miembro privado

Un miembro **privado** solo se puede usar **dentro de la clase donde se declara**. Fuera de ella no se puede leer ni modificar.

En JavaScript no existe la palabra `private`: se marca con una **almohadilla `#`** delante del nombre.

| | Público | Privado |
|---|---|---|
| Se escribe | `marca` | `#marca` |
| Desde la propia clase | Sí | Sí |
| Desde fuera de la clase | **Sí** | **No** |

Es la forma más común de **proteger por completo los datos internos** de una clase.

## Campos privados con `#`

```javascript
class Camion {
    #marca;
    #modelo;
    #color;
    #cargaMaxima;

    constructor(marca, modelo, color, cargaMaxima) {
        this.#marca = marca;
        this.#modelo = modelo;
        this.#color = color;
        this.#cargaMaxima = cargaMaxima;
    }
}
```

- **Hay que declararlos** en el cuerpo de la clase: a diferencia de los atributos públicos, no se pueden crear "al vuelo" dentro del constructor.
- El `#` forma parte del nombre: siempre se escribe `this.#marca`.

Desde fuera no se pueden usar:

```javascript
const camion1 = new Camion("Mack", "MK12", "rojo", 18000);

console.log(camion1.#marca);         // SyntaxError: campo privado fuera de la clase
camion1.#cargaMaxima = -500;         // SyntaxError
```

El error aparece **al cargar el archivo**, antes de ejecutar nada: es una protección real, de las que impiden que el programa funcione.

> Esto resuelve el problema de la lección anterior: con atributos públicos, cualquiera podía dejar `cargaMaxima = -500`.

## Acceso controlado: métodos *getter* y *setter*

Si los campos son privados, ¿cómo se leen o se cambian desde fuera? A través de **métodos públicos** que la clase decide ofrecer:

```javascript
// Getter: permite LEER el campo
obtenerMarca() {
    return this.#marca;
}

// Setter: permite MODIFICARLO, pero validando
cambiarCargaMaxima(nuevaCarga) {
    if (nuevaCarga <= 0) {
        console.log("Carga inválida: debe ser mayor que cero");
        return;
    }

    this.#cargaMaxima = nuevaCarga;
}
```

```javascript
console.log(camion1.obtenerMarca());    // Mack
camion1.cambiarCargaMaxima(-500);       // rechazado
camion1.cambiarCargaMaxima(20000);      // aceptado
```

Esta es la gran ventaja: **la clase controla qué valores acepta**.

## `get` y `set`: propiedades

JavaScript tiene una sintaxis propia que reúne lectura y escritura bajo un mismo nombre: `get` y `set`.

```javascript
class Furgoneta {
    #carga = 500;

    get carga() {
        return this.#carga;
    }

    set carga(valor) {
        if (valor <= 0) {
            console.log("Carga inválida: debe ser mayor que cero");
            return;
        }

        this.#carga = valor;
    }
}
```

```javascript
const furgoneta1 = new Furgoneta();
furgoneta1.carga = -5;        // ejecuta el set: rechazado
furgoneta1.carga = 1000;      // ejecuta el set: aceptado
console.log(furgoneta1.carga);    // ejecuta el get: 1000
```

- Se **usa como un atributo** (`furgoneta1.carga`, **sin paréntesis**), pero ejecuta código al leer y al escribir.
- El campo privado (`#carga`) y la propiedad (`carga`) tienen **nombres distintos**.
- Si solo se define `get`, la propiedad es de **solo lectura**: asignarle un valor no hace nada (o da error en modo estricto).

## Métodos privados

Un método también puede ser privado con `#`: un **ayudante interno** que solo usan otros métodos de la misma clase.

```javascript
#descripcion() {
    return `${this.#marca} ${this.#modelo} (${this.#color})`;
}

mostrar() {
    console.log(`${this.#descripcion()}: carga máxima ${this.#cargaMaxima} kg`);
}
```

Desde fuera solo se ve `mostrar()`. `#descripcion()` es un detalle de implementación que se puede cambiar sin afectar a nadie.

## El guion bajo no protege

Antes de que existiera `#`, se marcaba por **convención** un atributo que "no se debe tocar" con un guion bajo:

```javascript
class Antigua {
    _saldo = 100;
}

const antigua = new Antigua();
antigua._saldo = -999;    // funciona: no hay protección
```

Es solo una señal para otros programadores. `#` **sí es privado de verdad**, y es lo que se usa hoy.

## No hay constructor privado

A diferencia de C# y Java, JavaScript solo permite **un** constructor y no se puede marcar como privado.

## Resumen

| Elemento | Recomendación |
|---|---|
| **Campos** que no deben tocarse libremente | `#campo` |
| Acceso a los campos | `get` / `set` o métodos |
| Métodos internos de apoyo | `#metodo()` |
| Métodos que el resto debe usar | Sin `#` |

## Ejemplo

```javascript
class Cuenta {
    #saldo = 0;

    get saldo() {
        return this.#saldo;
    }

    depositar(monto) {
        if (monto > 0) {
            this.#saldo += monto;
        }
    }
}

const cuenta1 = new Cuenta();
cuenta1.depositar(100);
console.log(cuenta1.saldo);    // 100
```

## Comandos

```bash
cd javascript/06-advanced-oop
node 37-encapsulation-private/index.js
```

Fuente: [curso de C# de INFORMATICONFIG](https://www.youtube.com/watch?v=sWpp73OUmCQ&list=PL2Z95CSZ1N4F7mym8Ca16RoTDVAlIOPlT&index=37), vídeo 37.
