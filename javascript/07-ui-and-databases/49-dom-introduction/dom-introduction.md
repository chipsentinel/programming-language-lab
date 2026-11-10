# Introducción al DOM en JavaScript

## El equivalente de WPF

En C# se crean ventanas con **WPF** y en Java con **JavaFX**. En JavaScript las "ventanas" son **páginas web**, y se construyen con tres lenguajes que trabajan juntos:

| Lenguaje | Para qué | En WPF |
|---|---|---|
| **HTML** | La **estructura**: qué elementos hay | XAML |
| **CSS** | La **apariencia**: colores, tamaños, posición | Estilos y plantillas |
| **JavaScript** | El **comportamiento**: qué pasa al pulsar | Código C# (*code-behind*) |

Es la **misma separación** que en WPF: diseño por un lado, lógica por otro.

> Hasta ahora JavaScript se ejecutaba con **Node.js**, en la consola. Aquí se ejecuta en el **navegador**, que es el que dibuja la página. No se usa `node`.

## Qué es el DOM

El **DOM** (*Document Object Model*, "modelo de objetos del documento") es la representación que hace el navegador de una página HTML: un **árbol de objetos** que JavaScript puede **leer y modificar**.

```text
document
└── html
    ├── head
    │   └── title
    └── body
        └── main
            ├── h1  (id="mensaje")
            └── button  (id="aceptar")
```

Cada **etiqueta** HTML se convierte en un **objeto** con propiedades y métodos. Cambiar ese árbol desde JavaScript **cambia lo que se ve** en la pantalla al instante.

## Los tres archivos

```text
49-dom-introduction/
├── index.html     estructura
├── style.css      apariencia
└── index.js       comportamiento
```

### HTML: la estructura

```html
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <title>Ventana principal</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
    <main>
        <h1 id="mensaje">Bienvenido al DOM</h1>
        <button id="aceptar" type="button">Aceptar</button>
    </main>

    <script src="index.js"></script>
</body>
</html>
```

Es un lenguaje de **etiquetas** (se abren y se cierran) con **atributos**, igual que XAML.

| Parte | Significa | En WPF |
|---|---|---|
| `<title>` | El **título** de la pestaña | `Title` de la ventana |
| `<main>` | Un **contenedor** del contenido | `Grid`, `StackPanel` |
| `<h1>` | Un **título** (nivel 1) | `TextBlock` |
| `<button>` | Un **botón** | `Button` |
| `id="aceptar"` | Un **nombre** para usarlo desde JavaScript | `x:Name` |
| `<link rel="stylesheet">` | Carga el **CSS** | |
| `<script src="...">` | Carga el **JavaScript** | |

El `<script>` va **al final del `body`** para que, cuando se ejecute, los elementos **ya existan**. (La alternativa es `<script src="index.js" defer></script>` en el `head`.)

### CSS: la apariencia

```css
button {
    width: 120px;
    height: 32px;
}

button:hover {
    background-color: #dbeafe;
}
```

- Un **selector** (`button`) indica a **qué elementos** se aplica; dentro de las llaves van las **propiedades**.
- `:hover` es un **estado**: cuando el ratón está encima.
- `width` y `height` son el **ancho** y el **alto**, como en WPF.

### JavaScript: el comportamiento

```javascript
const mensaje = document.getElementById("mensaje");
const aceptar = document.getElementById("aceptar");

aceptar.addEventListener("click", () => {
    mensaje.textContent = "Has pulsado Aceptar";
    aceptar.disabled = true;
});
```

## Buscar elementos

| Método | Qué hace |
|---|---|
| `document.getElementById("id")` | El elemento con ese **id** (el equivalente de usar un `x:Name`) |
| `document.querySelector("main")` | El **primer** elemento que cumple un **selector CSS** |
| `document.querySelectorAll("button")` | **Todos** los que lo cumplen |

Devuelven un **objeto** con el que se trabaja: si no encuentran nada, devuelven `null`.

## Leer y cambiar elementos

| Propiedad | Qué es |
|---|---|
| `elemento.textContent` | El **texto** del elemento |
| `elemento.disabled` | Si un botón o campo está **desactivado** |
| `elemento.style.color` | Un estilo directo |
| `elemento.classList.add("activo")` | Añadir una **clase** CSS |

```javascript
mensaje.textContent = "Nuevo texto";       // cambia lo que se ve
```

## Eventos

Un **evento** es algo que ocurre en la página: un clic, una tecla, el ratón que entra...

```javascript
aceptar.addEventListener("click", () => {
    // código que se ejecuta al hacer clic
});
```

- **`addEventListener("click", función)`** asocia una función a un evento (en WPF: `Click="Aceptar_Click"`).
- La función se llama una vez **por cada clic**.
- Hasta que no se asocia un evento, un botón **no hace nada** al pulsarlo.

### Programación dirigida por eventos

En un programa de consola, el código se ejecuta de arriba abajo. Aquí el programa **espera** y **reacciona** a lo que hace el usuario. Es un cambio de mentalidad que comparten WPF, JavaFX y el navegador.

## Crear elementos desde JavaScript

El DOM también permite **crear** elementos nuevos:

```javascript
const nota = document.createElement("p");
nota.textContent = "Esta línea se ha creado con JavaScript";
document.querySelector("main").appendChild(nota);
```

1. `createElement("p")` crea un párrafo **todavía invisible**.
2. Se le da contenido.
3. `appendChild` lo **añade** al árbol, y entonces aparece.

## Ejecutar

No se ejecuta con `node`: hay que **abrir `index.html` en un navegador** (doble clic sobre el archivo, o arrastrarlo al navegador). Se carga el CSS y el JavaScript y aparece la página.

En **Visual Studio Code**, la extensión **Live Server** (clic derecho en `index.html` → *Open with Live Server*) abre la página y la **recarga sola** al guardar.

Para ver errores y probar código, las **herramientas de desarrollo** del navegador (`F12`): consola, inspector del DOM y depurador (lección 44).

## Comparación con la consola

| | Programa de consola | Página web |
|---|---|---|
| Interfaz | Texto | **Elementos** HTML |
| Se ejecuta con | `node index.js` | Abriendo `index.html` |
| Entrada del usuario | `readline` | Cajas de texto, botones... |
| Salida | `console.log()` | Cambiar el DOM |
| El programa avanza | De arriba abajo | **Por eventos** |

## Resumen

| Quiero... | Escribo... |
|---|---|
| Un elemento por su id | `document.getElementById("id")` |
| Cambiar su texto | `elemento.textContent = "..."` |
| Reaccionar a un clic | `elemento.addEventListener("click", ...)` |
| Desactivar un botón | `boton.disabled = true` |
| Crear un elemento | `document.createElement("p")` + `appendChild` |

## Comandos

```bash
cd javascript/07-ui-and-databases/49-dom-introduction
# abrir index.html en el navegador (o con Live Server en VS Code)
```

Fuente: [curso de C# de INFORMATICONFIG](https://www.youtube.com/watch?v=uwishEicQMs&list=PL2Z95CSZ1N4F7mym8Ca16RoTDVAlIOPlT&index=49), vídeo 49.
