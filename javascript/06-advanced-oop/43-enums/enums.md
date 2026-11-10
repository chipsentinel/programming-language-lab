# Enumeraciones en JavaScript

## Qué es una enumeración

Un **conjunto fijo de constantes con nombre**: valores relacionados entre sí que se escriben con palabras en lugar de números o textos "mágicos".

En vez de decir "estado `2`" (¿qué significa?) o "estado `"enviado"`" (¿y si se escribe `"enviadoo"`?), se dice `EstadoPedido.ENVIADO`.

Ventajas:

- **Legibilidad**: el código se explica solo.
- **Mantenimiento**: los valores están en un solo sitio.
- Evita errores tipográficos con textos o números sueltos.

Ejemplos típicos: días de la semana, estados de un pedido, niveles de prioridad, tipos de usuario, colores.

## JavaScript no tiene `enum`

C# y Java tienen la palabra clave `enum`. **JavaScript no.** Se **imita** con un **objeto cuyas propiedades son las constantes**, congelado con `Object.freeze` para que nadie lo modifique:

```javascript
const EstadoPedido = Object.freeze({
    PENDIENTE: 0,
    PROCESADO: 1,
    ENVIADO: 2,
    ENTREGADO: 3,
});
```

- Las constantes se escriben por convención en **MAYÚSCULAS**.
- Los **números se asignan a mano**: no se numeran solos desde 0.
- **`Object.freeze`** congela el objeto: no se pueden añadir, cambiar ni quitar propiedades.

> **TypeScript**, el JavaScript con tipos, sí tiene `enum`. Es otro lenguaje que se compila a JavaScript.

### Valores propios

Se escriben directamente:

```javascript
const Prioridad = Object.freeze({
    BAJA: 1,
    MEDIA: 5,
    ALTA: 10,
});
```

## Usar la enumeración

```javascript
let status = EstadoPedido.PENDIENTE;

console.log(status);        // 0
```

Aquí hay una diferencia importante: la variable guarda **el número**, no el nombre. Mostrarla da `0`, no `PENDIENTE`. Para obtener el nombre hay que buscarlo:

```javascript
const nombreDe = (enumeracion, valor) =>
    Object.keys(enumeracion).find((nombre) => enumeracion[nombre] === valor);

console.log(nombreDe(EstadoPedido, status));      // PENDIENTE
```

## Qué protege `Object.freeze` y qué no

```javascript
EstadoPedido.PENDIENTE = 99;
console.log(EstadoPedido.PENDIENTE);      // 0: el cambio se ignora
```

- Con el objeto congelado el cambio **se ignora en silencio** (en modo estricto da un `TypeError`).
- **Lo que no impide** es guardar un valor inventado en una variable:

```javascript
status = 42;          // válido, aunque ningún estado vale 42
```

En C# y Java el compilador lo rechazaría. En JavaScript la seguridad depende de la disciplina del programador.

## Comprobar si un valor existe

```javascript
Object.values(EstadoPedido).includes(3);      // true
Object.values(EstadoPedido).includes(8);      // false
```

`Object.values` devuelve un array con todos los valores.

## Comparar

Se usa `===` (y `<` / `>` si son números):

```javascript
status === EstadoPedido.ENVIADO               // true
status > EstadoPedido.PENDIENTE               // true
```

## `switch` con constantes

```javascript
switch (status) {
    case EstadoPedido.PENDIENTE:
        console.log("El pedido está esperando");
        break;
    case EstadoPedido.ENVIADO:
        console.log("El pedido va de camino");
        break;
    // ...
}
```

## Recorrer todas las constantes

```javascript
for (const [nombre, valor] of Object.entries(EstadoPedido)) {
    console.log(`${valor} = ${nombre}`);
}
```

```text
0 = PENDIENTE
1 = PROCESADO
2 = ENVIADO
3 = ENTREGADO
```

`Object.entries` da los pares `[nombre, valor]`; `Object.keys`, solo los nombres.

## Texto a enumeración

Como es un objeto, se busca por **nombre**:

```javascript
const leido = EstadoPedido["ENVIADO"];          // 2

if (EstadoPedido["PERDIDO"] === undefined) {
    console.log("No existe el estado Perdido");
}
```

- Una clave que no existe da `undefined`, **sin error**.
- Para ignorar mayúsculas: `EstadoPedido[texto.toUpperCase()]`.

## Alternativa más segura: `Symbol`

Un `Symbol` es un valor **único**: dos símbolos nunca son iguales, aunque tengan la misma descripción. Evita confundir una constante con un número cualquiera:

```javascript
const Color = Object.freeze({
    ROJO: Symbol("rojo"),
    VERDE: Symbol("verde"),
});

console.log(Color.ROJO === Color.VERDE);       // false
console.log(Color.ROJO.toString());            // Symbol(rojo)
```

La contrapartida es que **no tienen número** ni se pueden guardar directamente en JSON.

## Resumen

| Quiero... | Escribo... |
|---|---|
| Declarar | `const Nombre = Object.freeze({ A: 0, B: 1 })` |
| Usar | `Nombre.A` |
| ¿Existe ese valor? | `Object.values(Nombre).includes(3)` |
| Todos los valores | `Object.entries(Nombre)` |
| Texto a enum | `Nombre["A"]` |

## Ejemplo

```javascript
const EstadoPedido = Object.freeze({
    PENDIENTE: 0,
    PROCESADO: 1,
    ENVIADO: 2,
    ENTREGADO: 3,
});

const status = EstadoPedido.ENVIADO;

console.log("ID del estado: " + status);

if (status === EstadoPedido.ENVIADO) {
    console.log("El pedido va de camino");
}
```

## Comandos

```bash
cd javascript/06-advanced-oop
node 43-enums/index.js
```

Fuente: [curso de C# de INFORMATICONFIG](https://www.youtube.com/watch?v=BtWC39X0WKQ&list=PL2Z95CSZ1N4F7mym8Ca16RoTDVAlIOPlT&index=43), vídeo 43.
