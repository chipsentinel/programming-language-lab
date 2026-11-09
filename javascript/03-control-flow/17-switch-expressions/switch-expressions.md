# Alternativas a las expresiones `switch` en JavaScript

## JavaScript no tiene expresión `switch`

En C# y Java, una expresión `switch` **devuelve un valor** y se guarda en una variable. En JavaScript `switch` es **solo una instrucción**: ejecuta código, pero no devuelve nada.

```javascript
// No existe en JavaScript:
// const nombreDia = dia switch { 1 => "Lunes", ... };
```

Se consigue algo equivalente con tres técnicas.

## 1. Asignar dentro de cada `case`

Se declara la variable con `let` antes y se le da valor en cada caso.

```javascript
const dia = 3;
let nombreDia;

switch (dia) {
    case 1: nombreDia = "Lunes"; break;
    case 2: nombreDia = "Martes"; break;
    case 3: nombreDia = "Miércoles"; break;
    default: nombreDia = "Día no válido";
}

console.log(nombreDia);   // Miércoles
```

## 2. Rangos con `switch (true)`

Un `case` compara con `===`, así que no admite `>= 70` directamente. El truco es hacer `switch (true)` y escribir **condiciones** en los `case`: gana la primera que da `true`.

```javascript
const nota = 80;
let resultado;

switch (true) {
    case nota >= 70:
        resultado = "Pasaste el examen";
        break;
    case nota >= 50:
        resultado = "Debes repetir el examen";   // ya es menor que 70
        break;
    default:
        resultado = "Has reprobado el examen";
}
```

| `nota` | `resultado` |
|---|---|
| `80` | Pasaste el examen |
| `65` | Debes repetir el examen |
| `40` | Has reprobado el examen |

Para un rango cerrado se combinan condiciones con `&&`:

```javascript
case nota >= 60 && nota <= 69:
    resultado = "Repetir examen";
    break;
```

Como en C#, **el orden importa**: se toma el primer `case` que coincide. Aquí JavaScript no avisa si se tapa una condición.

Es un truco muy usado, pero para dos o tres rangos un `if / else if` se lee igual de bien o mejor.

## 3. Operador ternario encadenado

Devuelve un valor directamente, como una expresión:

```javascript
const nota = 64;

const resultado = nota >= 80 ? "Pasaste el examen"
    : nota >= 60 ? "Repetir examen"
    : "Reprobaste el examen";

console.log(`Nota ${nota}: ${resultado}`);   // Repetir examen
```

Se lee de arriba abajo: "si `nota >= 80`, ese texto; si no, si `nota >= 60`, este otro; si no, el último". Con más de tres ramas deja de ser legible.

## Alternativa para valores exactos: un objeto

Para convertir un valor en otro (días, meses, códigos), un objeto funciona como tabla de clave → valor:

```javascript
const nombres = { 1: "Lunes", 2: "Martes", 3: "Miércoles" };

console.log(nombres[3] ?? "Día no válido");   // Miércoles
console.log(nombres[9] ?? "Día no válido");   // Día no válido
```

`??` aplica el valor por defecto solo cuando la clave no existe (`undefined`). Los objetos se verán más adelante.

## Varios valores en un `case`

Igual que en el `switch` normal, se apilan sin código entre ellos:

```javascript
switch (dia) {
    case 6:
    case 7:
        tipoDia = "Fin de semana";
        break;
}
```

## Comparación con C# y Java

| | C# | Java | JavaScript |
|---|---|---|---|
| Expresión `switch` | `x switch { 1 => "a" }` | `switch (x) { case 1 -> "a"; }` | No existe |
| Rangos en el caso | `>= 70` | No (Java 17) | `switch (true)` con `case x >= 70` |
| Valor por defecto | `_ =>` | `default ->` | `default:` |

## Comandos

```bash
cd javascript/03-control-flow
node 17-switch-expressions/index.js
```

Fuente: [curso de C# de INFORMATICONFIG](https://www.youtube.com/watch?v=ydP8job-2a8&list=PL2Z95CSZ1N4F7mym8Ca16RoTDVAlIOPlT&index=17), vídeo 17.
