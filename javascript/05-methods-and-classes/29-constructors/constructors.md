# Constructores en JavaScript

## Qué es un constructor

Un **método especial que se ejecuta automáticamente al crear un objeto** (`new`). Su función principal es **dar valores iniciales** a los atributos y preparar el objeto para usarse.

Hasta ahora, al escribir `new Persona()`, ya se estaba llamando a un constructor: el que JavaScript crea **por defecto**.

## Constructor por defecto

Si una clase **no define ninguno**, JavaScript crea uno vacío. Por eso esto funciona sin escribir nada más:

```javascript
class Contador {
    valor = 10;
}

const contador1 = new Contador();    // llama al constructor por defecto
```

Los atributos con valor inicial (`valor = 10`) se asignan al crear el objeto, antes del código del constructor.

## Definir un constructor propio

```javascript
class Persona {
    nombre = "";
    edad;

    constructor(elNombre, laEdad) {
        this.nombre = elNombre;
        this.edad = laEdad;
        console.log(`Creando persona: ${this.nombre}, ${this.edad}`);
    }
}
```

```javascript
const persona2 = new Persona("Marcos", 28);    // Creando persona: Marcos, 28
```

## Diferencias con C# y Java

| | C# y Java | JavaScript |
|---|---|---|
| Nombre | El **de la clase** (`Persona`) | Siempre **`constructor`** |
| Cuántos | **Varios** (sobrecarga) | **Solo uno** por clase |
| Acceso a atributos | Por su nombre | Con **`this.`** |
| Sin constructor | Uno por defecto vacío | Uno por defecto vacío |

## Reglas

1. **Se llama `constructor`**.
2. **No tiene tipo de retorno** (no devuelve nada).
3. **Se ejecuta una sola vez por objeto**, en el `new`.
4. **Solo puede haber uno** en cada clase; definir dos es un `SyntaxError`.

```javascript
const persona1 = new Persona("Sol", 34);   // ejecuta el constructor
persona1.constructor("Ana", 1);            // TypeError: solo se ejecuta con new
```

- **No se puede llamar sin `new`**: una clase no es una función normal.

```javascript
const persona5 = Persona();   // TypeError: Class constructor Persona cannot be invoked without 'new'
```

## Parámetros y valores por defecto

Como en cualquier método, el constructor recibe parámetros. Al ser **uno solo**, para ofrecer varias formas de crear el objeto se usan **parámetros con valor por defecto**, en lugar de varios constructores:

```javascript
class Persona {
    nombre = "";
    edad;

    constructor(elNombre = "Sol", laEdad = 34) {
        this.nombre = elNombre;
        this.edad = laEdad;
        console.log(`Creando persona: ${this.nombre}, ${this.edad}`);
    }
}

const persona1 = new Persona();                // Creando persona: Sol, 34
const persona2 = new Persona("Marcos", 28);    // Creando persona: Marcos, 28
```

- Si falta un argumento se usa el valor por defecto.
- Sin valores por defecto, un argumento ausente vale `undefined` y **no da error**:

```javascript
new Persona("Marcos");    // laEdad vale undefined si no tiene valor por defecto
```

- Aquí los parámetros se llaman `elNombre` y `laEdad` para no confundirlos con los atributos. En la lección sobre `this` se verá cómo usar el mismo nombre.

## `this.` es obligatorio

Dentro de la clase, para llegar a un atributo hay que escribir **`this.`**. Sin él, `nombre = elNombre` crearía una variable suelta en lugar de asignar el atributo (o fallaría):

```javascript
constructor(elNombre) {
    nombre = elNombre;          // error: nombre no está definido
    this.nombre = elNombre;     // correcto
}
```

En C# y Java basta con el nombre del atributo.

## Un constructor no es el sitio para otros métodos

Se pueden escribir instrucciones dentro del constructor, pero los **métodos van en la clase**, fuera del constructor.

## Para qué sirve

- **Garantizar** que el objeto nace con valores válidos.
- **Evitar** asignar atributo por atributo después del `new`.
- **Preparar** lo que el objeto necesite al empezar.

## Ejemplo

```javascript
class Persona {
    nombre = "";
    edad;

    constructor(elNombre, laEdad) {
        this.nombre = elNombre;
        this.edad = laEdad;
    }
}

const persona1 = new Persona("Sol", 34);
const persona2 = new Persona("Marcos", 28);

console.log(`${persona1.nombre}: ${persona1.edad}`);
console.log(`${persona2.nombre}: ${persona2.edad}`);
```

## Comandos

```bash
cd javascript/05-methods-and-classes
node 29-constructors/index.js
```

Fuente: [curso de C# de INFORMATICONFIG](https://www.youtube.com/watch?v=AIESIKJzq5g&list=PL2Z95CSZ1N4F7mym8Ca16RoTDVAlIOPlT&index=29), vídeo 29.
