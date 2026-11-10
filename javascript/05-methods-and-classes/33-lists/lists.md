# Listas en JavaScript

## Qué es una lista

Una **colección que guarda elementos en orden, con tamaño dinámico**: se pueden añadir, insertar y quitar elementos en cualquier momento.

En JavaScript **no existe una clase `List`**: el **array** (lección 24) ya es dinámico y cumple esa función. Por eso aquí no hay nada nuevo que crear; lo nuevo son las **funciones** para modificar un array.

| | C# (`List<T>`) | Java (`ArrayList`) | JavaScript (array) |
|---|---|---|---|
| Se crea con | `new List<string>()` | `new ArrayList<>()` | `[]` |
| Tipo de los elementos | Uno solo | Uno solo | **Cualquiera, incluso mezclados** |
| Tamaño actual | `Count` | `size()` | `length` |
| Añadir | `Add` | `add` | `push` |

## Crear una lista

```javascript
const simpsons = [];
```

- **`[]`** es un array vacío. No se indica el tipo de los elementos.
- Se puede crear con valores: `const valores = [21, 8, 235, 100, 120];`
- Aunque sea `const`, se puede **modificar su contenido**; `const` solo impide asignarle otro array a `simpsons`.

## Añadir elementos: `push`

```javascript
simpsons.push("Homero");
simpsons.push("Marge");
simpsons.push("Lisa");
simpsons.push("Bart");
simpsons.push("Maggie");

console.log(simpsons.length);    // 5
```

`push` añade **al final**. El array crece solo.

- **Admite duplicados**: se puede añadir `"Homero"` dos veces.
- **Admite cualquier tipo**: `simpsons.push(5)` es válido, aunque lo normal es que todos los elementos sean del mismo tipo.

## Recorrer: `for...of`

Un bucle pensado para recorrer colecciones, sin índice:

```javascript
for (const nombre of simpsons) {
    console.log(nombre);
}
```

```text
for (  const nombre  of  simpsons  )
       └ variable        └ colección
```

En cada vuelta, `nombre` toma el valor del **siguiente elemento**. Se puede declarar con `const` porque en cada vuelta es una variable nueva. También se puede con `for`, accediendo por posición:

```javascript
for (let i = 0; i < simpsons.length; i++) {
    console.log(`Posición ${i}: ${simpsons[i]}`);
}
```

> No confundir `for...of` (recorre los **valores**) con `for...in` (recorre los **índices**, y se usa más con objetos).

## Funciones principales

| Función | Qué hace |
|---|---|
| `push(valor)` | Añade al final |
| `splice(posición, 0, valor)` | **Inserta** en una posición |
| `splice(posición, 1)` | **Quita** el elemento de esa posición |
| `indexOf(valor)` | Posición, o `-1` si no está |
| `includes(valor)` | ¿Existe? Devuelve `boolean` |
| `sort()` | Ordena (**como texto** por defecto) |
| `reverse()` | Invierte el orden actual |
| `length = 0` | Quita todos los elementos |
| `pop()` | Quita el último |
| `shift()` / `unshift(v)` | Quita / añade al principio |

### `splice`: insertar y quitar

No hay `Insert`, `Remove` ni `RemoveAt`: `splice` hace las tres cosas. Su forma es `splice(posición, cuántosQuitar, ...valoresAInsertar)`.

```javascript
simpsons.splice(0, 0, "Apu");    // en la posición 0, quita 0 e inserta "Apu"
simpsons.splice(5, 1);           // en la posición 5, quita 1
```

### Quitar por valor

No existe `remove(valor)`. Se busca la posición con `indexOf` y se quita con `splice`:

```javascript
simpsons.splice(simpsons.indexOf("Homero"), 1);    // quita el PRIMER "Homero"
valores.splice(valores.indexOf(235), 1);           // quita el VALOR 235
valores.splice(1, 1);                              // quita la POSICIÓN 1
```

**Cuidado**: si el valor no existe, `indexOf` da `-1`, y `splice(-1, 1)` **quita el último elemento**. Conviene comprobar antes:

```javascript
const posicion = simpsons.indexOf("Moe");
if (posicion !== -1) {
    simpsons.splice(posicion, 1);
}
```

### Buscar

```javascript
console.log(simpsons.includes("Lisa"));      // true
console.log(simpsons.indexOf("Bart"));       // 3
console.log(simpsons.indexOf("Nelson"));     // -1
```

**`-1` significa "no está"**. Distinguen mayúsculas de minúsculas. `includes` es el equivalente de `Contains` en C# y de `contains` en Java.

### Ordenar e invertir

```javascript
simpsons.sort();       // Apu, Bart, Lisa, Maggie, Marge
simpsons.reverse();    // Marge, Maggie, Lisa, Bart, Apu
```

- **Modifican el propio array**, y `reverse` no ordena: solo da la vuelta.
- `sort()` ordena **como texto**: con números hay que pasarle una función de comparación (lección 24).

```javascript
valores.sort((a, b) => a - b);
valores.reverse();    // de mayor a menor
```

### Vaciar

```javascript
simpsons.length = 0;
```

## Mostrar una lista: `join`

```javascript
console.log(simpsons.join(", "));    // Apu, Bart, Lisa, Maggie, Marge
```

## Extras de JavaScript

`pop`, `shift` y `unshift` permiten usar un array como **pila** o **cola**:

```javascript
const cola = ["a", "b", "c"];
cola.pop();            // quita "c": a, b
cola.shift();          // quita "a": b
cola.unshift("z");     // añade al principio: z, b
```

## Ejemplo

```javascript
const nombres = [];

nombres.push("Sol");
nombres.push("Marcos");
nombres.splice(0, 0, "Lucía");

for (const nombre of nombres) {
    console.log(nombre);
}

console.log("Total: " + nombres.length);
```

## Comandos

```bash
cd javascript/05-methods-and-classes
node 33-lists/index.js
```

Fuente: [curso de C# de INFORMATICONFIG](https://www.youtube.com/watch?v=Lz4jMlSgM_M&list=PL2Z95CSZ1N4F7mym8Ca16RoTDVAlIOPlT&index=33), vídeo 33.
