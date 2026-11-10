# Etiquetas y botones en el DOM

## Qué se aprende

Los dos elementos más básicos de una interfaz:

- Un **texto** (la "etiqueta" de WPF).
- Un **botón** `<button>` que el usuario pulsa.

Y cómo darles **apariencia** (colores, fuentes con CSS), **un nombre** (`id`) y **funcionalidad** (eventos con JavaScript). Es el equivalente de la lección 50 de WPF.

## La etiqueta: un elemento de texto

```html
<p id="labelMensaje">Mensaje</p>
```

En HTML no hay una "etiqueta" única como el `Label` de WPF. Un texto se escribe con el elemento que corresponda: `<p>` (párrafo), `<span>` (texto en línea), `<h1>`...

> Existe un elemento **`<label>`**, pero **no es una etiqueta de texto cualquiera**: sirve para **acompañar a un campo de formulario** (lección siguiente).

### Apariencia con CSS

```css
#labelMensaje {
    font-family: Verdana, sans-serif;
    font-size: 22px;
    font-weight: bold;
    color: darkblue;
    background-color: white;
}
```

| Propiedad CSS | Qué controla | En WPF |
|---|---|---|
| `color` | El **color del texto** | `Foreground` |
| `background-color` | El **color de fondo** | `Background` |
| `font-family` | El **tipo de letra** | `FontFamily` |
| `font-size` | El **tamaño** de la letra | `FontSize` |
| `font-weight` | El **grosor** (`bold` = negrita) | `FontWeight` |

- **`#labelMensaje`** es un **selector por `id`**: aplica el estilo solo al elemento con ese id. (`.botones` selecciona por **clase**; `button`, por **etiqueta**.)
- Los colores pueden ser **nombres** (`darkblue`), códigos **hexadecimales** (`#00008b`) o `rgb(0, 0, 139)`.

### Posición

| Propiedad CSS | Qué hace | En WPF |
|---|---|---|
| `width` / `height` | Ancho y alto | `Width`, `Height` |
| `margin` | Espacio **exterior** | `Margin` |
| `text-align: center` | Centra el texto | `HorizontalAlignment` |
| `display: flex` + `justify-content` / `align-items` | Alinea los hijos de un contenedor | `StackPanel` + alineación |
| `gap` | Espacio entre los hijos | |

## El identificador: `id`

```html
<p id="labelMensaje">Mensaje</p>
```

El **`id`** da un nombre al elemento. Sirve para:

- **Encontrarlo desde JavaScript**: `document.getElementById("labelMensaje")`.
- **Darle estilo** desde CSS: `#labelMensaje { ... }`.

Es el equivalente de `x:Name`. Debe ser **único** en la página.

Convención: `camelCase` y un prefijo con el tipo (`labelMensaje`, `botonSaludar`).

## El botón: `<button>`

```html
<button id="botonSaludar" type="button">Saludar</button>
```

- El **texto** va entre las etiquetas de apertura y cierre (en WPF, `Content`).
- **`type="button"`** indica que es un botón normal. Sin él, dentro de un formulario actúa como botón de **enviar**.
- Al pasar el ratón por encima puede cambiar de color con `button:hover { ... }`.
- **Al pulsarlo no ocurre nada** hasta que se le da funcionalidad.

## Eventos: darle funcionalidad

```javascript
const botonSaludar = document.getElementById("botonSaludar");

botonSaludar.addEventListener("click", () => {
    labelMensaje.textContent = "Saludos a todos";
});
```

- **`document.getElementById`** obtiene el elemento por su `id`.
- **`addEventListener("click", función)`** registra el código que se ejecuta **al pulsar**.
- **`() => { ... }`** es una función flecha (lección 25): el código que se ejecuta.
- **`textContent`** es el **texto** del elemento (en WPF, `Content`).

En WPF el enlace se escribe en el diseño (`Click="..."`); en el DOM, **en JavaScript**.

## Activar y desactivar: `disabled`

Los botones y campos tienen la propiedad **`disabled`**:

```html
<button id="botonDespedir" type="button" disabled>Despedir</button>
```

```javascript
botonDespedir.disabled = false;      // activado
botonDespedir.disabled = true;       // desactivado: se ve atenuado y no responde
```

| | DOM | WPF |
|---|---|---|
| Desactivar | `disabled = true` | `IsEnabled = false` |
| Activar | `disabled = false` | `IsEnabled = true` |

Como en JavaFX, es la lógica **al revés** que en WPF: `disabled = true` **desactiva**.

En el HTML basta con escribir el atributo `disabled` para que **empiece desactivado**. En CSS, `button:disabled { ... }` le da su aspecto.

## El ejemplo: saludar y despedir

La página tiene una etiqueta y dos botones, con esta **lógica**:

```text
Estado inicial:   [Saludar] activo      [Despedir] desactivado     "Mensaje"

Pulsar Saludar →   texto "Saludos a todos"   [Saludar] desactivado   [Despedir] activo
Pulsar Despedir →  texto "Adiós a todos"     [Saludar] activo        [Despedir] desactivado
```

```javascript
botonSaludar.addEventListener("click", () => {
    labelMensaje.textContent = "Saludos a todos";

    botonDespedir.disabled = false;      // ya se saludó: ahora se puede despedir
    botonSaludar.disabled = true;        // no hace falta saludar otra vez
});

botonDespedir.addEventListener("click", () => {
    labelMensaje.textContent = "Adiós a todos";

    botonSaludar.disabled = false;       // se puede volver a saludar
    botonDespedir.disabled = true;
});
```

Es **lógica de programa** aplicada a una interfaz: el estado de los botones **guía** al usuario, que no puede despedirse sin haber saludado antes.

> Es fácil olvidar uno de los dos cambios (activar uno y desactivar el otro). Hay que escribir **siempre los dos** en cada manejador.

Un botón desactivado **no dispara** el evento `click`: no hace falta comprobar nada dentro del manejador.

## Cambiar la apariencia desde JavaScript

Además del CSS, los estilos se pueden cambiar desde el código:

```javascript
labelMensaje.style.color = "red";                 // un estilo directo
labelMensaje.classList.add("destacado");          // mejor: añadir una clase definida en el CSS
labelMensaje.classList.remove("destacado");
```

Mejor **cambiar de clase** que escribir estilos sueltos: el aspecto se queda en el CSS y el código solo decide **cuál** se aplica.

## Ejecutar

Abrir `index.html` en un navegador (doble clic, o con **Live Server** en VS Code). Al pulsar **Saludar** el mensaje cambia y los botones intercambian su estado.

## Comandos

```bash
cd javascript/07-ui-and-databases/50-dom-buttons-labels
# abrir index.html en el navegador (o con Live Server en VS Code)
```

Fuente: [curso de C# de INFORMATICONFIG](https://www.youtube.com/watch?v=lGXTKkC-5nE&list=PL2Z95CSZ1N4F7mym8Ca16RoTDVAlIOPlT&index=50), vídeo 50.
