# CheckBox y RadioButton en el DOM

## Qué se aprende

Dos controles para **elegir** entre opciones (equivalente a la lección 52 de WPF):

| Control | En HTML | Para qué | Cuántas se pueden marcar |
|---|---|---|---|
| **Casilla** | `<input type="checkbox">` | Elegir **varias** opciones a la vez | **Las que se quiera** |
| **Botón de opción** | `<input type="radio">` | Elegir **una sola** opción de un grupo | **Solo una**: al marcar otra, se desmarca la anterior |

El ejemplo es una pequeña herramienta de pedidos: **pizzas** (casillas, se pueden pedir varias) y **bebida** (botones de opción, solo una), con un total que se calcula al pulsar **Ordenar**.

## La casilla: `type="checkbox"`

```html
<label><input type="checkbox" id="ck01"> Pizza de queso - $150.00</label>
```

- El **texto** va **fuera** del `<input>` (en WPF era `Content`). Lo habitual es envolver el `<input>` y el texto en un **`<label>`**: así **hacer clic en el texto también marca la casilla**.
- Cada casilla es **independiente** de las demás.
- Se estiliza con CSS (`color`, `font-family`...) en el `<label>` que la acompaña.

## El botón de opción: `type="radio"` y `name`

```html
<label><input type="radio" name="bebida" id="rb01"> Pepsi - $30.00</label>
<label><input type="radio" name="bebida" id="rb02"> Coca-Cola - $30.00</label>
<label><input type="radio" name="bebida" id="rb03"> Sprite - $25.00</label>
```

Lo que forma el grupo es el atributo **`name`**: **todos los `radio` con el mismo `name` se excluyen entre sí**.

| | DOM | JavaFX | WPF |
|---|---|---|---|
| Agrupar | Mismo `name` | `ToggleGroup` | Mismo contenedor o `GroupName` |

Sin `name` (o con `name` distintos), cada botón sería independiente y se podrían marcar los tres.

> Un grupo de `radio` **no se puede desmarcar con un clic**: una vez elegida una opción, el usuario solo puede cambiar a otra. Para dejar el grupo sin selección hay que hacerlo desde el código: `rb01.checked = false`.

### Agrupar con `<fieldset>` y `<legend>`

```html
<fieldset>
    <legend>Indique su bebida</legend>
    ...
</fieldset>
```

`<fieldset>` agrupa visualmente y semánticamente campos relacionados y `<legend>` es el título del grupo (en WPF, la `Label` que hay encima de cada columna).

## Nombres con prefijo

Por convención, el `id` indica el tipo de control:

| Prefijo | Control | Ejemplos |
|---|---|---|
| `ck` | Casilla | `ck01`, `ck02`, `ck03` |
| `rb` | Botón de opción | `rb01`, `rb02`, `rb03` |
| `b` | Botón | `bOrdenar`, `bCancelar` |
| `l` | Texto de resultado | `lTotal` |

## Saber si está marcado: `checked`

```javascript
if (ck01.checked)
```

- **`checked`** es `true` si está marcado y `false` si no. Funciona igual para casillas y botones de opción.
- En WPF era `IsChecked`.

Para **marcar o desmarcar** desde el código:

```javascript
ck01.checked = false;
```

## La lógica del pedido

### Regla de negocio: sin pizza no hay pedido

Una **regla de negocio** es una condición propia del programa, no del lenguaje. Aquí: **no se puede pedir solo una bebida**.

```javascript
if (!ck01.checked && !ck02.checked && !ck03.checked) {
    lTotal.textContent = "Debe seleccionar una pizza con la orden";
}
```

Las tres casillas **sin marcar** (`&&`) significa que **no hay ninguna pizza**.

### Sumar lo marcado

En el `else` hay al menos una pizza. Se parte de un total de **cero** y se **suma** lo que corresponda:

```javascript
let total = 0.0;

// Pizzas: cada una marcada se suma (una, dos o las tres)
if (ck01.checked) total += 150.00;
if (ck02.checked) total += 250.00;
if (ck03.checked) total += 200.00;

// Bebida: solo una puede estar marcada
if (rb01.checked) total += 30.00;
if (rb02.checked) total += 30.00;
if (rb03.checked) total += 25.00;
```

Son **`if` separados**, no `else if`: las casillas son independientes y todas las marcadas deben sumarse. Con `else if` solo se sumaría la primera.

### Mostrar el total con dos decimales

```javascript
lTotal.textContent = "$" + total.toFixed(2);
```

**`toFixed(2)`** devuelve un texto con **dos decimales** (`"180.00"`) y siempre con **punto**.

### Ejemplos de totales

| Selección | Total |
|---|---|
| Nada | *Debe seleccionar una pizza con la orden* |
| Solo Pepsi | *Debe seleccionar una pizza con la orden* |
| Queso + Pepsi | `$180.00` |
| Queso + jamón y queso + Sprite | `$425.00` |
| Queso + jamón y queso + Coca-Cola | `$430.00` |
| Las tres pizzas + Coca-Cola | `$630.00` |

## El botón Cancelar

```javascript
bCancelar.addEventListener("click", () => {
    ck01.checked = false;
    ck02.checked = false;
    ck03.checked = false;

    rb01.checked = false;
    rb02.checked = false;
    rb03.checked = false;

    lTotal.textContent = "";
});
```

Desmarca **todo** y vacía el total.

## Texto de total "oculto"

El `<span id="lTotal">` **empieza vacío**: no se ve, pero está ahí. Delante, el texto fijo "Total de la orden:" da el contexto. Así el usuario sabe qué es el valor cuando aparece.

## Ejercicio propuesto

Tal como está, tras pulsar **Ordenar** el usuario puede seguir cambiando su elección y volver a ordenar; y **Cancelar** funciona siempre. El vídeo propone mejorarlo: **cuando se pulse Ordenar, el botón Cancelar debe quedar desactivado** (`bCancelar.disabled = true`), porque la orden ya es definitiva.

## Ejecutar

Abrir `index.html` en un navegador (doble clic, o con **Live Server** en VS Code). Se prueba: sin nada marcado, solo bebida, una pizza, varias pizzas, cambiar de bebida y Cancelar.

## Comandos

```bash
cd javascript/07-ui-and-databases/52-dom-checkbox-radiobuttons
# abrir index.html en el navegador (o con Live Server en VS Code)
```

Fuente: [curso de C# de INFORMATICONFIG](https://www.youtube.com/watch?v=3oK4MoUEfCo&list=PL2Z95CSZ1N4F7mym8Ca16RoTDVAlIOPlT&index=52), vídeo 52.
