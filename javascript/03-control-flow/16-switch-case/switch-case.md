# Condicional `switch` en JavaScript

## Qué es

Una condicional que **compara el valor de una variable con varios casos posibles** y ejecuta el que coincide. Es una alternativa más clara a una cadena larga de `else if` que comparan siempre la misma variable.

```javascript
let dia = 3;

switch (dia) {
    case 1:
        console.log("Lunes");
        break;
    case 2:
        console.log("Martes");
        break;
    case 3:
        console.log("Miércoles");
        break;
}
```

```text
Miércoles
```

## Cómo funciona

```text
switch (variable) {
    case valor1:
        instrucciones;
        break;
    case valor2:
        instrucciones;
        break;
    default:
        instrucciones;
}
```

- **`switch (dia)`**: indica la variable que se evalúa.
- **`case 3:`**: "si `dia` vale 3". Acaba en **dos puntos**, no en punto y coma.
- **`break;`**: termina el `switch`. El programa sale y ya no mira los demás casos.
- El sistema recorre los `case` en orden hasta encontrar el que coincide.

## `default`

Se ejecuta cuando **ningún `case` coincide**. Es el `else` final del `switch`.

```javascript
let dia = 9;

switch (dia) {
    case 1:
        console.log("Lunes");
        break;
    default:
        console.log("Día no válido");
        break;
}
```

Sin `default`, si ningún caso coincide no pasa nada. Es opcional, pero conviene ponerlo para controlar valores inesperados.

## Varios `case` con el mismo código

Se escriben seguidos, sin código entre ellos:

```javascript
switch (dia) {
    case 6:
    case 7:
        console.log("Fin de semana");
        break;
    case 1:
    case 2:
    case 3:
    case 4:
    case 5:
        console.log("Día laborable");
        break;
}
```

## Con textos

`switch` funciona con cualquier valor, no solo con números.

```javascript
const opcion = "guardar";

switch (opcion) {
    case "abrir":
        console.log("Abriendo el archivo");
        break;
    case "guardar":
        console.log("Guardando el archivo");
        break;
}
```

## `switch` compara con `===`

No convierte tipos: el texto `"3"` **no coincide** con `case 3`.

```javascript
const valor = "3";

switch (valor) {
    case 3:
        console.log("Es el número 3");
        break;
    default:
        console.log('"3" es un texto, no coincide');   // se ejecuta este
        break;
}
```

## JavaScript sí deja pasar de un `case` al siguiente

Si falta el `break`, el programa **sigue ejecutando los casos siguientes** aunque no coincidan. Se llama *fall-through* y es un error muy frecuente.

```javascript
let dia = 1;

switch (dia) {
    case 1:
        console.log("Lunes");     // se ejecuta
    case 2:
        console.log("Martes");    // también, aunque dia no vale 2
        break;
}
```

```text
Lunes
Martes
```

En C# este código **no compila**: obliga a escribir `break` en cada `case` con código. En JavaScript hay que acordarse de ponerlo.

## `switch` frente a `if / else if`

| Usar `switch` | Usar `if / else if` |
|---|---|
| Se compara **una variable** con valores concretos | Las condiciones son rangos (`nota >= 70`) |
| Muchos casos, como días o meses | Se combinan variables distintas con `&&` y `||` |

## Un consejo sobre nombres

En los nombres de variables **no se usan tildes ni la `ñ`**: `dia`, no `día`; `anio`, no `año`. Aunque JavaScript los acepta, pueden dar problemas con editores y control de versiones.

## Ejemplo

```javascript
const dia = 3;

console.log("Días de la semana");

switch (dia) {
    case 1:
        console.log("Lunes");
        break;
    case 2:
        console.log("Martes");
        break;
    case 3:
        console.log("Miércoles");
        break;
    default:
        console.log("Otro día");
        break;
}
```

## Comandos

```bash
cd javascript/03-control-flow
node 16-switch-case/index.js
```

Fuente: [curso de C# de INFORMATICONFIG](https://www.youtube.com/watch?v=xP7MIqlBTSU&list=PL2Z95CSZ1N4F7mym8Ca16RoTDVAlIOPlT&index=16), vídeo 16.
