# Introducción a las clases en JavaScript

## Qué es una clase

Una **plantilla** (un molde) para crear objetos. Define **qué datos** tiene algo y, más adelante, **qué puede hacer**.

- Con `class` se pueden crear **clases propias**: `Persona`, `Empleado`, `Venta`...
- En JavaScript un objeto también se puede crear sin clase (con llaves `{ }`); las clases son una forma más ordenada de hacer muchos objetos iguales.

Sirven para:

- **Agrupar datos relacionados**: nombre, edad y salario de una persona van juntos.
- **Organizar el código** de forma estructurada y reutilizable.

## Diferencias con C# y Java

| | C# y Java | JavaScript |
|---|---|---|
| Tipo de cada atributo | Se declara (`string nombre`) | **No se declara** |
| Método principal | `Main` / `main` obligatorio | **No existe**: el archivo se ejecuta de arriba abajo |
| `public` | Se escribe en los atributos | **No hace falta**: son públicos |
| Atributo sin valor | `0`, `false`, `null` | `undefined` |

## Definir una clase y sus atributos

```javascript
class Persona {
    nombre = "Sol";
    edad = 34;
    direccion = "Calle Mayor 12";
    salario = 2500;
    activo = true;
}
```

- **`class Persona`**: el nombre de la clase, en `PascalCase` y en singular.
- **Las llaves `{ }`** delimitan el **cuerpo** de la clase.
- **Atributos** (o campos): variables declaradas dentro de la clase. Describen cómo es cada objeto. Se escriben **sin `let` ni `const`**.

Un atributo sin valor inicial vale `undefined`:

```javascript
class Venta {
    vendedor = "";
    cantidades;    // undefined
}
```

Una clase, por sí sola, **no hace nada**: es solo la plantilla.

## Instanciar: crear un objeto

Para usar una clase hay que **crear un objeto** de ella. Se llama **instanciar**:

```javascript
const persona1 = new Persona();
```

```text
const   persona1   =   new   Persona();
        objeto              instancia
```

- **`new Persona()`** construye el objeto a partir de la plantilla. Los paréntesis se explicarán con los constructores.
- De una misma clase se pueden crear **tantos objetos como se quiera**.

## Acceder a los atributos: `objeto.atributo`

Con un punto entre el objeto y el atributo:

```javascript
console.log(persona1.nombre);                    // Sol
console.log(`Edad: ${persona1.edad}`);           // Edad: 34
```

También se pueden **cambiar**, aunque el objeto esté en una variable `const`:

```javascript
persona1.edad = 35;
```

`const` impide **reasignar la variable** (`persona1 = otraCosa`), pero no modificar los atributos del objeto.

## Cada objeto tiene sus propios valores

La clase es el molde; los objetos son las copias. Cambiar uno **no afecta** a los demás:

```javascript
const persona2 = new Persona();
persona2.nombre = "Marcos";
persona2.edad = 28;

console.log(`${persona1.nombre} tiene ${persona1.edad} años`);   // Sol tiene 35 años
console.log(`${persona2.nombre} tiene ${persona2.edad} años`);   // Marcos tiene 28 años
```

## Ejemplo

```javascript
class Persona {
    nombre = "Sol";
    edad = 34;
}

const persona1 = new Persona();

console.log(`Nombre: ${persona1.nombre}`);
console.log(`Edad: ${persona1.edad}`);
```

## Comandos

```bash
cd javascript/05-methods-and-classes
node 26-classes-introduction/index.js
```

Fuente: [curso de C# de INFORMATICONFIG](https://www.youtube.com/watch?v=7Kn9S2Sy1Hc&list=PL2Z95CSZ1N4F7mym8Ca16RoTDVAlIOPlT&index=26), vídeo 26.
