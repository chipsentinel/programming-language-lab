# Cuadros de texto en el DOM (`<input type="text">`)

## Qué se aprende

El cuadro donde el usuario **escribe** una línea de texto es el elemento **`<input type="text">`**. Es el equivalente del `TextBox` de WPF (lección 51).

Se construye un pequeño formulario: dos cuadros (nombre y edad), un botón **Ingresar** que valida y muestra los datos, y un botón **Borrar** que limpia todo.

## El elemento `<input>`

```html
<label for="txtNombre">Ingrese su nombre</label>
<input type="text" id="txtNombre">
```

| Qué | DOM | WPF |
|---|---|---|
| El **texto** escrito | propiedad **`value`** | `Text` |
| **Letra** (tipo y tamaño) | CSS `font-family`, `font-size` | `FontFamily`, `FontSize` |
| **Color de la letra** | CSS `color` | `Foreground` |
| **Color de fondo** del cuadro | CSS `background-color` | `Background` |
| **Identificador** | atributo `id` | `x:Name` |

```css
input {
    font-family: Arial, sans-serif;
    font-size: 16px;
    color: darkblue;
    background-color: white;
}
```

### La etiqueta del campo: `<label for="...">`

Ahora sí se usa **`<label>`** (en la lección 50 no): su atributo **`for`** lo une con el cuadro cuyo `id` coincide. Al **hacer clic en el texto**, el cursor pasa al cuadro, y los lectores de pantalla lo anuncian junto al campo.

### Texto de sugerencia: `placeholder`

```html
<input type="text" id="txtNombre" placeholder="Nombre">
```

Muestra un **texto de ayuda gris** mientras el cuadro está vacío y **desaparece al empezar a escribir**. Es lo que el vídeo describe como "se usa mucho en los formularios actuales": en WPF hay que construirlo a mano, en HTML viene incluido.

Con él se puede **prescindir de la etiqueta**, aunque aquí se conserva para parecerse al ejercicio de WPF (y porque un `placeholder` no sustituye a un `<label>` por accesibilidad).

## Leer y limpiar el texto: `value` y `trim()`

```javascript
const nombre = txtNombre.value.trim();
```

- **`value`** devuelve lo escrito (**siempre un `string`**, aunque sea un número).
- **`trim()`** quita los **espacios sobrantes** de los extremos (`"  Sol  "` → `"Sol"`).

Para **escribir** en el cuadro se asigna `value`:

```javascript
txtNombre.value = "";
```

| Elemento | Propiedad del texto |
|---|---|
| Cuadro de texto (`<input>`) | **`value`** |
| Texto normal (`<p>`, `<span>`...) | **`textContent`** |

Es un fallo común usar `textContent` con un `<input>` (no cambia lo que se ve) o `value` con un párrafo.

## Validar: texto vacío

El usuario puede pulsar **Ingresar** **sin escribir nada**. En JavaScript un texto vacío (`""`) es **falsy**, así que `!nombre` es `true` cuando el cuadro está vacío:

| | DOM | C# |
|---|---|---|
| ¿Texto vacío? | `!texto` (o `texto === ""`) | `string.IsNullOrEmpty(texto)` |

> Como se hace `trim()` antes, un cuadro con solo espacios cuenta como **vacío**.

### Las cuatro situaciones

El primer planteamiento del vídeo falla, y es instructivo:

1. Con `!nombre || !edad` el mensaje "no colocaste ningún dato" **también salía** si faltaba **solo uno** de los dos.
2. Con `&&` solo se detecta cuando faltan **los dos**, y la edad vacía queda sin tratar.

La solución es **encadenar `else if`**, de lo más específico a lo menos:

```javascript
btnIngresar.addEventListener("click", () => {
    const nombre = txtNombre.value.trim();
    const edad = txtEdad.value.trim();

    if (!nombre && !edad) {
        lResultado.textContent = "No colocaste ningún dato";       // faltan los dos
    } else if (!nombre) {
        lResultado.textContent = "No colocaste nombre";            // falta solo el nombre
    } else if (!edad) {
        lResultado.textContent = "No colocaste edad";              // falta solo la edad
    } else {
        lResultado.textContent = "Nombre: " + nombre + ", Edad: " + edad;   // están los dos
    }
});
```

El `else` final solo se ejecuta si **todo lo anterior falló**, es decir, si **hay datos**. Es un ejemplo de **control de flujo** (lección 15) aplicado a una página.

## El botón Borrar

```javascript
btnBorrar.addEventListener("click", () => {
    txtNombre.value = "";
    txtEdad.value = "";
    lResultado.textContent = "Resultado";
});
```

- Los cuadros se **vacían** con `value = ""`.
- El párrafo **no** se deja vacío: vuelve a decir **`Resultado`**, que **orienta** al usuario sobre dónde aparecerá la respuesta.

## Mejoras posibles

El vídeo termina proponiendo ejercicios para repasar lo aprendido:

- Que el **nombre no lleve números**.
- Que la **edad sea un número**: `Number(edad)` y `Number.isNaN(...)`, o `<input type="number">`, que ya solo admite números.
- Limitar la edad: **no mayor de 100** y, por ejemplo, **mínimo 18** (los atributos `min` y `max` de `type="number"` ayudan).

## Ejecutar

Abrir `index.html` en un navegador (doble clic, o con **Live Server** en VS Code). Se prueba: sin datos, solo el nombre, solo la edad, ambos, y **Borrar**.

## Comandos

```bash
cd javascript/07-ui-and-databases/51-dom-text-input
# abrir index.html en el navegador (o con Live Server en VS Code)
```

Fuente: [curso de C# de INFORMATICONFIG](https://www.youtube.com/watch?v=KtmS5I1HA6Y&list=PL2Z95CSZ1N4F7mym8Ca16RoTDVAlIOPlT&index=51), vídeo 51.
