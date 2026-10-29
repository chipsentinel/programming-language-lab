# Concatenación de cadenas en JavaScript

Equivalente de la lección 06 del curso de C#.

## Qué es concatenar

Concatenar es **unir varios textos en uno solo**. En JavaScript se puede hacer con el operador `+` y con plantillas de texto con comillas invertidas `` `...` `` (equivalentes a la interpolación `$"..."` de C#).

```javascript
let nombre = "Sol";
let edad = 30;
```

## Con el operador `+`

Une textos con `+`. Si uno de los operandos es un número, JavaScript lo convierte a texto automáticamente.

```javascript
console.log("Nombre de usuario: " + nombre + ", edad: " + edad);
// Nombre de usuario: Sol, edad: 30
```

Hay que cuidar los espacios y los signos: `"Nombre:" + nombre` da `Nombre:Sol`, sin espacio.

## Con plantillas de texto `` `...` ``

Las **comillas invertidas** permiten escribir variables dentro del texto con `${ }`. Es la forma más legible y la que se usa por defecto.

```javascript
console.log(`Nombre de usuario: ${nombre}, edad: ${edad}`);
// Nombre de usuario: Sol, edad: 30
```

Con comillas normales (`"` o `'`) no funciona: se escribiría literalmente `${nombre}`.

## Salto de línea con `\n`

`\n` dentro del texto es un salto de línea. Con él se escribe en varias líneas con un solo `console.log`.

```javascript
console.log(`Nombre de usuario: ${nombre} \nEdad: ${edad}`);
// Nombre de usuario: Sol
// Edad: 30
```

Si hay un espacio antes de `\n`, ese espacio queda al final de la primera línea. No se ve, pero está.

Las plantillas de texto también admiten **varias líneas** escribiendo el salto directamente dentro de las comillas invertidas.

## `console.log` y `process.stdout.write`

| Método | Qué hace |
|---|---|
| `console.log(...)` | Escribe el texto y salta de línea (como `Console.WriteLine`) |
| `process.stdout.write(...)` | Escribe el texto **sin** saltar de línea (como `Console.Write`) |

Dos llamadas seguidas pueden formar una sola línea:

```javascript
process.stdout.write(`Nombre usuario: ${nombre}`);
console.log(`, edad: ${edad}`);
// Nombre usuario: Sol, edad: 30
```

`process.stdout.write` solo existe en Node.js, no en el navegador.

## Expresiones dentro de `${ }`

Dentro de `${ }` se puede escribir una expresión, no solo una variable. Se calcula al construir el texto y **la variable no cambia**.

```javascript
console.log(`Nombre de usuario: ${nombre}, edad: ${edad + 5}`);
// Nombre de usuario: Sol, edad: 35
```

Con `+` hay que usar paréntesis para que sume en lugar de concatenar:

```javascript
console.log("edad: " + (edad + 5));   // edad: 35
console.log("edad: " + edad + 5);     // edad: 305
```

Después de esas líneas, `edad` sigue valiendo `30`.

## Cambiar el valor de una variable y volver a mostrarla

Una variable declarada con `let` se puede reasignar, y cada `console.log` muestra el valor que tiene **en ese momento**.

```javascript
let edad = 64;
console.log(edad);   // 64

edad = 32;
console.log(edad);   // 32
```

Mismo caso con texto:

```javascript
let nombre = "Sol";
console.log(`Nombre: ${nombre}`);   // Nombre: Sol

nombre = "Luna";
console.log(`Nombre: ${nombre}`);   // Nombre: Luna
```

A diferencia de C# y Java, el tipo **sí puede cambiar**, porque lo tiene el valor y no la variable:

```javascript
edad = "treinta";   // válido: ahora edad es un string
```

## El texto ya construido no se actualiza

Al concatenar, el resultado es **un texto nuevo con los valores de ese momento**. Si después cambia la variable, el texto ya construido **no cambia**.

```javascript
let edad = 64;
let mensaje = `Edad: ${edad}`;

edad = 32;

console.log(mensaje);          // Edad: 64   (guardó el valor de antes)
console.log(`Edad: ${edad}`);  // Edad: 32   (se construye de nuevo)
```

## Orden al sumar y concatenar

Con `+`, JavaScript evalúa de izquierda a derecha. Mientras ambos lados sean números, **suma**; en cuanto aparece un texto, **concatena**. Es igual que en C# y Java.

```javascript
console.log(1 + 2 + "a");     // 3a
console.log("a" + 1 + 2);     // a12
console.log("a" + (1 + 2));   // a3
```

Cuidado: con otros operadores JavaScript convierte el texto a número, algo que C# y Java no hacen:

```javascript
console.log("5" + 3);   // 53  (concatena)
console.log("5" - 3);   // 2   (resta: convierte "5" a número)
```

## Los textos son inmutables

Un `string` no se modifica: concatenar crea una cadena nueva y la original queda igual.

```javascript
let saludo = "Hola";
let otro = saludo + " Sol";   // crea un texto nuevo; saludo sigue valiendo "Hola"
saludo = saludo + " Sol";     // ahora saludo vale "Hola Sol"
saludo += "!";                // forma corta de saludo = saludo + "!"
```

## Ejemplo del ejercicio

```javascript
let nombre = "Sol";
let edad = 30;

console.log("Nombre de usuario: " + nombre + ", edad: " + edad);
console.log(`Nombre de usuario: ${nombre}, edad: ${edad}`);
console.log(`Nombre de usuario: ${nombre} \nEdad: ${edad}`);

process.stdout.write(`Nombre usuario: ${nombre}`);
console.log(`, edad: ${edad}`);

console.log(`Nombre de usuario: ${nombre}, edad: ${edad + 5}`);
```

Salida:

```text
Nombre de usuario: Sol, edad: 30
Nombre de usuario: Sol, edad: 30
Nombre de usuario: Sol
Edad: 30
Nombre usuario: Sol, edad: 30
Nombre de usuario: Sol, edad: 35
```

## Diferencias con C#

| C# | JavaScript |
|---|---|
| `$"Edad: {edad}"` | `` `Edad: ${edad}` `` |
| `Console.WriteLine` | `console.log` |
| `Console.Write` | `process.stdout.write` |
| `string.Format("{0}", x)` | No existe; se usan plantillas |
| El tipo de la variable no cambia | El tipo lo tiene el valor y puede cambiar |
| `Console.ReadKey()` | No existe |

## Comandos

```bash
cd javascript/02-fundamentals
node 06-string-concatenation/index.js
```
