# Condicionales `if` y `else` en JavaScript

## Qué es una condicional

Una **estructura de control** que ejecuta un bloque de código u otro según se cumpla o no una condición. Hasta ahora el programa ejecutaba todas las líneas de arriba abajo; con una condicional puede **tomar decisiones**.

```text
         ┌── sí ──> hacer algo ──┐
condición                         ├──> continúa el programa
         └── no ──> (otro camino) ┘
```

## `if`

La condición va entre paréntesis. Si es verdadera, se ejecuta el bloque entre llaves.

```javascript
const edad = 20;

if (edad > 18) {
    console.log("Es mayor");
}
```

Si la condición es falsa y no hay nada más, **no pasa nada**: el programa sigue después del bloque.

## `else`

Define qué hacer **cuando la condición no se cumple**.

```javascript
if (edad < 18) {
    console.log("Es menor");
} else {
    console.log("No es menor");
}
```

Un `else` no lleva condición: recoge todo lo que no cumplió el `if`.

## `else if`

Cuando hay **más de dos caminos** se encadenan condiciones. Se evalúan **en orden** y en cuanto una se cumple, se ejecuta su bloque y **se salta el resto**.

```javascript
let nota = 80;

if (nota >= 70) {
    console.log("Felicidades, pasaste el examen con: " + nota);
} else if (nota < 70 && nota >= 50) {
    console.log("Debes repetir el examen, sacaste: " + nota);
} else {
    console.log("Has reprobado el examen, sacaste: " + nota);
}
```

| `nota` | Resultado |
|---|---|
| `80` | Felicidades, pasaste el examen |
| `65` | Debes repetir el examen |
| `40` | Has reprobado el examen |

- Se pueden poner **tantos `else if` como haga falta**.
- Solo se ejecuta **un** bloque de la cadena.
- El `else` final es opcional y va siempre al final.
- Se combinan con los operadores lógicos: `nota < 70 && nota >= 50` es un rango.
- `nota` se declara con `let` porque cambia de valor; `edad` con `const`.

## Cuidado con los límites

`>` y `>=` no son lo mismo. Con `nota > 70` y `nota < 70`, el valor **exacto 70 no cumple ninguna condición** y cae en el `else`.

```javascript
let nota = 70;

if (nota > 70)  { /* no entra: 70 no es mayor que 70 */ }
if (nota >= 70) { /* entra */ }
```

Al escribir rangos conviene decidir a qué lado pertenece cada límite.

## Aprovechar el orden

Como las condiciones se evalúan en orden, no hace falta repetir el límite superior. Si el primer `if` ya descartó `nota >= 70`, en el `else if` la nota ya es menor que 70:

```javascript
if (nota >= 70) {
    console.log("Aprobado");
} else if (nota >= 50) {         // equivale a nota < 70 && nota >= 50
    console.log("Recuperación");
} else {
    console.log("Suspenso");
}
```

## La condición no tiene por qué ser un `boolean`

A diferencia de C# y Java, JavaScript **convierte cualquier valor** a verdadero o falso. Son falsos (*falsy*): `false`, `0`, `""`, `null`, `undefined` y `NaN`. Todo lo demás es verdadero (*truthy*).

```javascript
const texto = "";

if (texto) {
    console.log("Hay texto");
} else {
    console.log("El texto está vacío");   // "" cuenta como falso
}
```

Es cómodo, pero también fuente de errores: `if (nota)` es falso cuando `nota` vale `0`, aunque `0` sea una nota válida.

## Reglas

- Con **una sola instrucción** se pueden omitir las llaves, pero conviene ponerlas siempre.
- Los textos se comparan con `===`: `if (nombre === "Sol")`.
- `=` asigna y `===` compara: `if (nota = 70)` no da error, pero asigna y siempre es verdadero.

## `if/else` frente al operador ternario

El ternario (lección anterior) sirve para **elegir un valor**; `if/else` para **ejecutar instrucciones**.

```javascript
const mensaje = (edad > 18) ? "Es mayor" : "No es mayor";   // elige un valor

if (edad > 18) { console.log("Es mayor"); }                 // ejecuta código
```

## Ejemplo

```javascript
const nota = 80;

console.log("Nota final del alumno");

if (nota >= 70) {
    console.log("Felicidades, pasaste el examen con: " + nota);
} else if (nota >= 50) {
    console.log("Debes repetir el examen, sacaste: " + nota);
} else {
    console.log("Has reprobado el examen, sacaste: " + nota);
}
```

## Comandos

```bash
cd javascript/03-control-flow
node 15-if-else/index.js
```

Fuente: [curso de C# de INFORMATICONFIG](https://www.youtube.com/watch?v=3ASlc4lUvik&list=PL2Z95CSZ1N4F7mym8Ca16RoTDVAlIOPlT&index=15), vídeo 15.
