# La palabra clave `static` en JavaScript

## Qué significa `static`

Un miembro `static` **pertenece a la clase en sí, no a un objeto** creado a partir de ella. Por eso **no hace falta instanciar la clase** (`new`) para usarlo: se usa con el **nombre de la clase**.

| | Miembro normal (de instancia) | Miembro `static` |
|---|---|---|
| Pertenece a | **Cada objeto** | **La clase** |
| Se usa con | `objeto.miembro` | `Clase.miembro` |
| Copias | Una **por objeto** | **Una sola**, compartida |
| Necesita `new` | **Sí** | **No** |

Se puede aplicar a **métodos, campos y bloques de inicialización**.

## Para qué sirve

- **Llamar a algo sin crear un objeto**.
- **Compartir datos** entre todos los objetos de la clase (un contador).
- Crear **utilidades** que no dependen del estado de ningún objeto (`Math`, `Number.isNaN`, `Object.keys`).

## Método `static`

```javascript
class Utilidad {
    static saludar() {
        console.log("Hola a todos");
    }
}

Utilidad.saludar();           // sin new
```

Sin `static`, habría que crear un objeto:

```javascript
class Utilidad {
    saludar() { /* ... */ }
}

const saludo = new Utilidad();
saludo.saludar();
```

Un método `static` **no se puede llamar sobre un objeto**: `saludo.saludar()` da `TypeError: saludo.saludar is not a function`. (En C# da error de compilación; aquí falla al ejecutar.)

### No hay `main`

JavaScript no necesita un punto de entrada `static`: el archivo se ejecuta de arriba abajo.

Y se llama así a `Math.sqrt(16)`, `Number.isNaN(x)` o `Object.keys(o)`: son **métodos `static`** (o funciones de un objeto) que no necesitan instancia.

## Campo `static`

```javascript
class Utilidad {
    static total = 5000;
}

console.log(Utilidad.total);      // 5000
Utilidad.total = 8000;            // se puede modificar
```

Tiene **un único valor** para toda la clase. Se acepta desde ES2022 (Node.js 12+).

## Un dato compartido entre todos los objetos

El uso más típico: un **contador** que suman todos los objetos.

```javascript
class Persona {
    static creadas = 0;                 // una sola copia
    nombre;                             // una por objeto
    numero;

    constructor(nombre) {
        Persona.creadas++;              // se accede con el nombre de la clase
        this.nombre = nombre;
        this.numero = Persona.creadas;
    }
}

const sol = new Persona("Sol");         // creadas = 1, numero = 1
const marcos = new Persona("Marcos");   // creadas = 2, numero = 2
const lucia = new Persona("Lucía");     // creadas = 3, numero = 3

console.log(Persona.creadas);           // 3
```

| Miembro | Cuántas copias | `sol` | `marcos` | `lucia` |
|---|---|---|---|---|
| `nombre` | Una por objeto | `"Sol"` | `"Marcos"` | `"Lucía"` |
| `numero` | Una por objeto | `1` | `2` | `3` |
| `creadas` | **Una para todos** | `3` | `3` | `3` |

Dentro de la clase hay que escribir **`Persona.creadas`**, no `this.creadas`: `this` es el objeto, y el campo `static` es de la clase.

## No hay clases `static`

C# tiene `static class` y Java usa clases con constructor `private`. En JavaScript **no hace falta**: para agrupar utilidades basta con un **objeto con funciones**:

```javascript
const Matematica = Object.freeze({
    sumar(a, b) {
        return a + b;
    },
    doble(numero) {
        return numero * 2;
    },
});

Matematica.sumar(50, 20);       // 70
```

O simplemente **funciones sueltas**, o las funciones exportadas de un módulo. `Object.freeze` impide que se modifique el objeto.

## Reglas importantes

### 1. Dentro de un método `static`, `this` es la clase

En un método de instancia, `this` es el objeto. En uno `static`, `this` es **la propia clase**:

```javascript
static mostrarTotal() {
    console.log(this.creadas);      // equivale a Persona.creadas
    console.log(this.nombre);       // undefined: "nombre" es de los objetos, no de la clase
}
```

En C# y Java ese segundo caso **no compila**; aquí da `undefined` en silencio.

### 2. Un método normal ve lo `static` solo con el nombre de la clase

```javascript
presentar() {
    console.log(`Soy ${this.nombre}, hay ${Persona.creadas} personas`);
}
```

### 3. Se comparte entre todas las instancias

Un cambio en un miembro `static` lo ven **todos**. Es útil, pero también **peligroso**: un valor global modificable desde cualquier parte es difícil de controlar.

## Bloque `static`

Un bloque que prepara los datos `static`:

```javascript
class Configuracion {
    static idioma;

    static {
        console.log("Inicializando configuración (solo una vez)");
        Configuracion.idioma = "es";
    }
}
```

**Diferencia con C# y Java**: allí el constructor/bloque `static` se ejecuta **cuando la clase se usa por primera vez**. En JavaScript se ejecuta **al definir la clase**, es decir, en el momento en que el motor llega a la declaración `class`. Por eso el mensaje aparece **aunque nunca se use `Configuracion`**.

## Cuándo usar `static`

| Usar `static` | No usar `static` |
|---|---|
| Funciones auxiliares sin estado | Cuando el método depende de los **datos de un objeto** |
| Constantes y datos compartidos de la clase | Cuando cada objeto necesita **su propia copia** |
| Contadores de instancias | Cuando se necesitará **redefinirlo** (herencia, lección 48) |

Muchas veces, en JavaScript, una **función suelta** es mejor que un método `static`.

## Ejemplo

```javascript
const Matematica = Object.freeze({
    sumar(a, b) {
        return a + b;
    },
});

const resultado = Matematica.sumar(50, 20);
console.log("Suma: " + resultado);
```

## Comandos

```bash
cd javascript/06-advanced-oop
node 47-static/index.js
```

Fuente: [curso de C# de INFORMATICONFIG](https://www.youtube.com/watch?v=HTFcs3xJVrQ&list=PL2Z95CSZ1N4F7mym8Ca16RoTDVAlIOPlT&index=47), vídeo 47.
