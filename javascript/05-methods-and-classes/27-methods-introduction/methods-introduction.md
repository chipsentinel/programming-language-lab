# Introducción a los métodos en JavaScript

## Qué es un método

Una **función definida dentro de una clase** que realiza una tarea concreta. Se escribe una vez y se **invoca** (se llama) siempre que haga falta.

Sirven para:

- **Evitar repetir código**: se escribe una vez y se usa muchas.
- **Dividir tareas complejas** en partes pequeñas y fáciles de entender: al ver `sumar()` ya se sabe qué hace sin leer su código.
- **Reutilizar** lo ya hecho, en distintos lugares del programa.
- **Depurar** más fácil: si algo falla, se revisa el método responsable.

Un método puede **recibir datos** (parámetros, lección siguiente) y puede **devolver un resultado**.

## Definir un método

```javascript
class Prueba {
    saludar() {
        console.log("Hola a todos");
    }
}
```

```text
saludar   ( )   { ... }
nombre  parámetros  cuerpo
```

A diferencia de C# y Java, **no se escribe** ni `public` ni el tipo de retorno (`void`, `int`...):

- **`saludar`**: el nombre. Los métodos van en `camelCase` (empiezan en minúscula) y suelen ser **verbos**.
- **`()`**: los paréntesis, siempre. Aquí vacíos: no recibe datos.
- **`{ }`**: el cuerpo, con las instrucciones.

## Invocar un método

Primero se **instancia** la clase (lección anterior) y después se llama con `objeto.metodo()`:

```javascript
const saludo1 = new Prueba();
saludo1.saludar();    // Hola a todos
```

- Los **paréntesis son obligatorios** al llamarlo, incluso sin datos: `saludo1.saludar`, sin ellos, no ejecuta nada.
- Un método se puede llamar **tantas veces** como se quiera: cada llamada ejecuta su código de nuevo.

## Método con retorno

El cuerpo termina con **`return`** seguido del valor:

```javascript
sumar() {
    const valor1 = 23, valor2 = 45;
    const total = valor1 + valor2;

    return total;
}
```

- **`return total;`** entrega ese valor a quien llamó al método y **termina** el método.
- No se declara el tipo del valor devuelto: puede ser un número, un texto, un booleano...

```javascript
despedida() {
    const mensaje = "Adiós a todos";
    return mensaje;
}
```

### Usar el valor devuelto

Se puede **guardar** en una variable o **usar directamente** donde haga falta:

```javascript
const resultado = saludo1.sumar();
console.log("Total de la suma: " + resultado);       // 68

console.log("Mensaje: " + despedida.despedida());    // Adiós a todos
console.log(saludo1.sumar() + 2);                    // 70
```

### Sin `return`

Un método sin `return` **devuelve `undefined`**. A diferencia de C# y Java, guardar su resultado no da error:

```javascript
const nada = saludo1.saludar();   // escribe "Hola a todos"
console.log(nada);                // undefined
```

## Con retorno y sin retorno

| | Sin retorno | Con retorno |
|---|---|---|
| Se escribe | Sin `return` | Con `return valor` |
| Sirve para | **Hacer** algo (mostrar, guardar) | **Calcular** un valor |
| Ejemplo | `saludar()` | `sumar()` |

> A veces se oye "métodos *set*" y "métodos *get*" para estos dos tipos. No es el sentido habitual: *getter* y *setter* son métodos especiales para leer y modificar atributos, y se verán más adelante.

## Un método puede llamar a otros

Dentro de una clase, un método llama a otro de la misma clase con **`this.`**:

```javascript
presentar() {
    this.saludar();
    this.despedir();
}
```

En C# y Java basta con el nombre (`Saludar()`); en JavaScript **`this.` es obligatorio**: sin él, `saludar()` buscaría una función suelta y fallaría. `this` se verá en la lección 31.

## Funciones sueltas

JavaScript no necesita una clase para tener funciones:

```javascript
function separador() {
    console.log("----------");
}

separador();    // se llama directamente, sin objeto
```

Es el equivalente a un método `static` de C# y Java, que también se puede llamar sin crear un objeto. Y como no existe `Main`, el archivo se ejecuta de arriba abajo.

## Ejemplo

```javascript
class Prueba {
    saludar() {
        console.log("Hola a todos");
    }

    sumar() {
        const total = 23 + 45;
        return total;
    }
}

const prueba1 = new Prueba();

prueba1.saludar();
console.log("Total de la suma: " + prueba1.sumar());
```

## Comandos

```bash
cd javascript/05-methods-and-classes
node 27-methods-introduction/index.js
```

Fuente: [curso de C# de INFORMATICONFIG](https://www.youtube.com/watch?v=lDyzFYnibdE&list=PL2Z95CSZ1N4F7mym8Ca16RoTDVAlIOPlT&index=27), vídeo 27.
