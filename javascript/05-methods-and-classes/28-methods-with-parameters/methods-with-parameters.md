# Métodos con parámetros en JavaScript

## Qué es un parámetro

Un **dato de entrada** que un método recibe cuando se le llama. Gracias a los parámetros, el mismo método sirve para **valores distintos** sin tocar su código.

| Término | Qué es | Dónde está |
|---|---|---|
| **Parámetro** | La variable que el método declara para recibir un dato | En la **definición**: `saludar(nombre)` |
| **Argumento** | El valor concreto que se envía | En la **llamada**: `saludar("Sol")` |

## El problema que resuelven

Con un atributo de la clase, un método siempre usa el mismo valor:

```javascript
class Prueba {
    nombre = "Sol";

    saludoConAtributo() {
        console.log("Hola, " + this.nombre);   // siempre "Sol"
    }
}
```

Funciona, pero para saludar a otra persona habría que cambiar el atributo. Con un parámetro, cada llamada decide el valor. Un atributo se usa con **`this.`** dentro de los métodos.

## Definir un parámetro

Entre los paréntesis del método, **solo con el nombre**: no se declara el tipo.

```javascript
saludar(nombre) {
    console.log("Hola, " + nombre);
}
```

Y se llama pasando un **argumento**:

```javascript
objeto1.saludar("Sol");       // Hola, Sol
objeto1.saludar("Marcos");    // Hola, Marcos
```

El parámetro `nombre` se comporta como una variable que **vale el argumento** durante esa llamada.

## Varios parámetros

Se separan con comas:

```javascript
presentar(nombre, edad) {
    console.log(`Nombre: ${nombre}, edad: ${edad}`);
}

objeto1.presentar("Sol", 34);    // Nombre: Sol, edad: 34
```

No hay límite en la cantidad.

## JavaScript no comprueba los argumentos

Aquí está la mayor diferencia con C# y Java. Como no hay tipos en los parámetros, **ni la cantidad ni el tipo se verifican**:

```javascript
objeto1.presentar("Sol");        // Nombre: Sol, edad: undefined   ← falta un argumento
objeto1.presentar(34, "Sol");    // Nombre: 34, edad: Sol          ← orden equivocado, sin error
```

- Un argumento que **falta** vale `undefined`.
- Uno que **sobra** se ignora.
- Un tipo equivocado **no da error**, pero puede dar resultados inesperados:

```javascript
objeto1.sumar("34", 22);   // 3422: concatena en lugar de sumar
```

Es responsabilidad del programador pasar lo correcto. Se puede uno proteger con comprobaciones (`typeof`, `Number(...)`).

## Parámetros y retorno

Un método puede recibir datos **y** devolver un resultado:

```javascript
sumar(a, b) {
    return a + b;
}

esMayorDeEdad(edad) {
    return edad >= 18;
}
```

```javascript
const resultado = objeto1.sumar(34, 22);                  // 56
console.log(objeto1.sumar(resultado, 4));                 // 60
console.log(objeto1.esMayorDeEdad(15));                   // false
```

- `return a + b;` calcula y devuelve en un paso.
- El argumento puede ser un **literal** (`34`), una **variable** (`resultado`) o una **expresión**.
- Para ver el resultado hay que **guardarlo** o mostrarlo: llamar a `sumar(34, 22)` sin hacer nada con el valor lo pierde.

## Alcance: dónde existe cada variable

| Variable | Dónde existe |
|---|---|
| **Atributo** (declarado en la clase) | En todos los métodos, con `this.` |
| **Parámetro** | Solo **dentro de su método** |
| **Variable local** (`const` / `let` en un método) | Solo dentro de ese método |

> El vídeo llama "variable global" al atributo. En rigor no es global del programa: pertenece a la clase.

```javascript
saludar(nombre) { /* nombre existe aquí */ }

despedir() {
    console.log(nombre);   // ReferenceError: nombre no está definido aquí
}
```

Dos métodos pueden tener un parámetro con el **mismo nombre** sin que se mezclen.

## Parámetros opcionales

Con un **valor por defecto**, se usa cuando no se pasa el argumento:

```javascript
despedir(nombre, mensaje = "Adiós") {
    console.log(`${mensaje}, ${nombre}`);
}

objeto1.despedir("Sol");                    // Adiós, Sol
objeto1.despedir("Sol", "Hasta pronto");    // Hasta pronto, Sol
```

Los opcionales van **al final** de la lista. En Java se resuelve con sobrecarga de métodos; en C# se escribe igual que aquí.

## Argumentos que escribe el usuario

Los argumentos pueden venir de la entrada de datos. Leer del teclado es asíncrono, así que va dentro de una función `async` (lección 11):

```javascript
async function main() {
    const rl = readline.createInterface({ input: process.stdin, output: process.stdout });

    const nombreUsuario = await rl.question("Ingrese su nombre...\n");
    const edadUsuario = Number(await rl.question("Ingrese su edad...\n"));

    objeto1.presentar(nombreUsuario, edadUsuario);
    rl.close();
}

main();
```

Las variables `nombreUsuario` y `edadUsuario` se **pasan** al método como argumentos. Los nombres no tienen por qué coincidir con los de los parámetros. Se convierte la edad con `Number`, porque `question` devuelve texto.

## Ejemplo

```javascript
class Prueba {
    sumar(a, b) {
        return a + b;
    }

    presentar(nombre, edad) {
        console.log(`Nombre: ${nombre}, edad: ${edad}`);
    }
}

const objeto1 = new Prueba();

objeto1.presentar("Sol", 34);
console.log("Suma: " + objeto1.sumar(34, 22));
```

## Comandos

```bash
cd javascript/05-methods-and-classes
node 28-methods-with-parameters/index.js
```

Fuente: [curso de C# de INFORMATICONFIG](https://www.youtube.com/watch?v=6PJSvxFLt9k&list=PL2Z95CSZ1N4F7mym8Ca16RoTDVAlIOPlT&index=28), vídeo 28.
