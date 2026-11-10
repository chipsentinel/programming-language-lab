# Etiquetas y botones en JavaFX

## Qué se aprende

Los dos elementos más básicos de una ventana:

- **`Label`**: una **etiqueta**, un texto en pantalla.
- **`Button`**: un **botón** que el usuario pulsa.

Y cómo darles **apariencia** (colores, fuentes), **un identificador** y **funcionalidad** (eventos). Es el equivalente de la lección 50 de WPF.

## La etiqueta: `Label`

```java
Label labelMensaje = new Label("Mensaje");
labelMensaje.setId("labelMensaje");
labelMensaje.setFont(Font.font("Verdana", FontWeight.BOLD, 22));
labelMensaje.setTextFill(Color.DARKBLUE);
labelMensaje.setStyle("-fx-background-color: white;");
```

En JavaFX los elementos se **crean como objetos** y su apariencia se ajusta con **métodos `set...`**:

| Qué | JavaFX | WPF |
|---|---|---|
| El **texto** | `new Label("texto")` / `setText(...)` | `Content` |
| **Color del texto** | `setTextFill(Color.DARKBLUE)` | `Foreground` |
| **Color de fondo** | `setStyle("-fx-background-color: white;")` | `Background` |
| **Fuente** (tipo, grosor, tamaño) | `setFont(Font.font("Verdana", FontWeight.BOLD, 22))` | `FontFamily`, `FontWeight`, `FontSize` |
| **Identificador** | `setId("labelMensaje")` | `x:Name` |

### Apariencia con CSS

Todo lo que se puede cambiar con métodos también se puede con **CSS**, con propiedades que **empiezan por `-fx-`**:

```java
boton.setStyle("-fx-background-color: lightgreen; -fx-font-size: 16px;");
```

Se pueden escribir en un archivo `.css` aparte y cargarlo en la escena.

### Alineación y tamaño

| Qué | JavaFX | WPF |
|---|---|---|
| Ancho y alto preferidos | `setPrefSize(120, 36)` | `Width`, `Height` |
| Alineación en un contenedor | `contenedor.setAlignment(Pos.CENTER)` | `HorizontalAlignment` |
| Márgenes internos | `setPadding(new Insets(20))` | `Margin` |
| Espacio entre elementos | `new VBox(20, ...)` (el 20) | `Margin` en cada hijo |

En JavaFX es el **contenedor** el que alinea y separa a sus hijos.

## El identificador: `setId`

```java
labelMensaje.setId("labelMensaje");
```

En esta lección **las variables ya dan nombre** a los elementos (`labelMensaje`, `botonSaludar`), así que el `id` no es imprescindible: sirve para **buscar un elemento** desde fuera (`escena.lookup("#labelMensaje")`) y para aplicarle **estilos CSS** (`#labelMensaje { ... }`). Es el equivalente de `x:Name`.

Por convención: `camelCase` y un prefijo con el tipo (`labelMensaje`, `botonSaludar`).

## El botón: `Button`

```java
Button botonSaludar = new Button("Saludar");
botonSaludar.setPrefSize(120, 36);
botonSaludar.setStyle("-fx-background-color: lightgreen;");
```

Tiene las **mismas propiedades** de apariencia y tamaño que `Label`. Al pasar el ratón por encima cambia de color (efecto *hover*), pero **al pulsarlo no ocurre nada** hasta que se le da funcionalidad.

## Eventos: darle funcionalidad

Un **evento** es algo que ocurre en la interfaz (un clic, una tecla). Se asocia con un **manejador**:

```java
botonSaludar.setOnAction(e -> {
    labelMensaje.setText("Saludos a todos");
});
```

- **`setOnAction`** registra el código que se ejecuta **al pulsar** el botón.
- **`e -> { ... }`** es una **expresión lambda** (lección 46): `e` es el evento y el bloque, lo que se hace.
- Dentro se usa cualquier elemento a través de su variable.

Para que la lambda pueda usar las variables `labelMensaje` y `botonDespedir`, deben ser **locales del método `start`** y no cambiar de valor (*efectivamente finales*).

En WPF el enlace se escribe en el diseño (`Click="..."`); en JavaFX, **en el código**.

## Activar y desactivar: `setDisable`

Todos los controles tienen `setDisable(true/false)`:

```java
botonDespedir.setDisable(true);       // desactivado: se ve atenuado y no responde
botonDespedir.setDisable(false);      // activado
```

¡Cuidado!: es la **lógica al revés** que en WPF: `setDisable(true)` **desactiva**, mientras que `IsEnabled = false` desactiva.

| | JavaFX | WPF |
|---|---|---|
| Desactivar | `setDisable(true)` | `IsEnabled = false` |
| Activar | `setDisable(false)` | `IsEnabled = true` |
| Consultar | `isDisabled()` | `IsEnabled` |

## El ejemplo: saludar y despedir

La ventana tiene una etiqueta y dos botones, con esta **lógica**:

```text
Estado inicial:   [Saludar] activo      [Despedir] desactivado     "Mensaje"

Pulsar Saludar →   etiqueta "Saludos a todos"   [Saludar] desactivado   [Despedir] activo
Pulsar Despedir →  etiqueta "Adiós a todos"     [Saludar] activo        [Despedir] desactivado
```

```java
botonSaludar.setOnAction(e -> {
    labelMensaje.setText("Saludos a todos");

    botonDespedir.setDisable(false);      // ya se saludó: ahora se puede despedir
    botonSaludar.setDisable(true);        // no hace falta saludar otra vez
});

botonDespedir.setOnAction(e -> {
    labelMensaje.setText("Adiós a todos");

    botonSaludar.setDisable(false);       // se puede volver a saludar
    botonDespedir.setDisable(true);
});
```

Es **lógica de programa** aplicada a una interfaz: el estado de los botones **guía** al usuario, que no puede despedirse sin haber saludado antes.

> Es fácil olvidar uno de los dos cambios (activar uno y desactivar el otro). Hay que escribir **siempre los dos** en cada manejador.

## Colocar los elementos

```java
HBox filaBotones = new HBox(12, botonSaludar, botonDespedir);     // en fila, 12 px de separación
filaBotones.setAlignment(Pos.CENTER);

VBox contenedor = new VBox(20, labelMensaje, filaBotones);        // en columna
contenedor.setAlignment(Pos.CENTER);
contenedor.setPadding(new Insets(20));
```

`HBox` coloca los elementos **en fila** y `VBox` **en columna**. Se pueden anidar: aquí una fila de botones dentro de una columna.

## Ejecutar

```bash
java --module-path "C:\javafx-sdk-21\lib" --add-modules javafx.controls JavaFxButtonsLabels.java
```

Al pulsar **Saludar** el mensaje cambia y los botones intercambian su estado.

## Comandos

```bash
cd java/07-ui-and-databases/50-javafx-buttons-labels
java --module-path "C:\javafx-sdk-21\lib" --add-modules javafx.controls JavaFxButtonsLabels.java
```

Cambiar `C:\javafx-sdk-21\lib` por la ruta real del SDK de JavaFX.

Fuente: [curso de C# de INFORMATICONFIG](https://www.youtube.com/watch?v=lGXTKkC-5nE&list=PL2Z95CSZ1N4F7mym8Ca16RoTDVAlIOPlT&index=50), vídeo 50.
