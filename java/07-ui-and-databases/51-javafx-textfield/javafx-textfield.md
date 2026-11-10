# Cuadros de texto en JavaFX (TextField)

## Qué se aprende

El **`TextField`** es el cuadro donde el usuario **escribe** una línea de texto. Es el equivalente del `TextBox` de WPF (lección 51).

Se construye un pequeño formulario: dos cuadros (nombre y edad), un botón **Ingresar** que valida y muestra los datos, y un botón **Borrar** que limpia todo.

## El control `TextField`

```java
TextField txtNombre = new TextField();
txtNombre.setId("txtNombre");
txtNombre.setStyle("-fx-font-family: Arial; -fx-font-size: 16px; -fx-text-fill: darkblue; -fx-control-inner-background: white;");
```

| Qué | JavaFX | WPF |
|---|---|---|
| El **texto** escrito | `getText()` / `setText("...")` | `Text` |
| **Letra** (tipo y tamaño) | `-fx-font-family`, `-fx-font-size` | `FontFamily`, `FontSize` |
| **Color de la letra** | `-fx-text-fill` | `Foreground` |
| **Color de fondo** del cuadro | `-fx-control-inner-background` | `Background` |
| **Identificador** | `setId("txtNombre")` | `x:Name` |
| Ancho máximo | `setMaxWidth(80)` | `Width` |

> El fondo de un cuadro de texto **no** es `-fx-background-color` (ese es el borde exterior), sino **`-fx-control-inner-background`**.

### Texto de sugerencia: `setPromptText`

```java
txtNombre.setPromptText("Nombre");
```

Muestra un **texto de ayuda gris** mientras el cuadro está vacío y **desaparece al empezar a escribir**. Es lo que el vídeo describe como "se usa mucho en los formularios actuales": en WPF hay que construirlo a mano, en JavaFX viene incluido.

Con él se puede **prescindir de la etiqueta** que acompaña al cuadro, aunque aquí se conserva para parecerse al ejercicio de WPF.

## Leer y limpiar el texto: `getText()` y `trim()`

```java
String nombre = txtNombre.getText().trim();
```

- **`getText()`** devuelve lo escrito.
- **`trim()`** quita los **espacios sobrantes** de los extremos (`"  Sol  "` → `"Sol"`).

Para **escribir** en el cuadro se usa `setText`:

```java
txtNombre.setText("");
```

En JavaFX el texto de un `TextField` **y** el de un `Label` se manejan con la misma pareja `getText` / `setText` (en WPF eran distintos: `Text` y `Content`).

## Validar: `isEmpty()`

El usuario puede pulsar **Ingresar** **sin escribir nada**. `getText()` **nunca devuelve `null`** en un `TextField` (devuelve `""` si está vacío), así que basta con **`isEmpty()`**:

| | JavaFX | C# |
|---|---|---|
| ¿Texto vacío? | `texto.isEmpty()` | `string.IsNullOrEmpty(texto)` |

> Como se hace `trim()` antes, un cuadro con solo espacios cuenta como **vacío**. (Java 11 trae también `isBlank()`, que hace esa comprobación directamente.)

### Las cuatro situaciones

El primer planteamiento del vídeo falla, y es instructivo:

1. Con `vacío(nombre) || vacío(edad)` el mensaje "no colocaste ningún dato" **también salía** si faltaba **solo uno** de los dos.
2. Con `&&` solo se detecta cuando faltan **los dos**, y la edad vacía queda sin tratar.

La solución es **encadenar `else if`**, de lo más específico a lo menos:

```java
btnIngresar.setOnAction(e -> {
    String nombre = txtNombre.getText().trim();
    String edad = txtEdad.getText().trim();

    if (nombre.isEmpty() && edad.isEmpty()) {
        lResultado.setText("No colocaste ningún dato");        // faltan los dos
    } else if (nombre.isEmpty()) {
        lResultado.setText("No colocaste nombre");             // falta solo el nombre
    } else if (edad.isEmpty()) {
        lResultado.setText("No colocaste edad");               // falta solo la edad
    } else {
        lResultado.setText("Nombre: " + nombre + ", Edad: " + edad);   // están los dos
    }
});
```

El `else` final solo se ejecuta si **todo lo anterior falló**, es decir, si **hay datos**. Es un ejemplo de **control de flujo** (lección 15) aplicado a una ventana.

## El botón Borrar

```java
btnBorrar.setOnAction(e -> {
    txtNombre.setText("");
    txtEdad.setText("");
    lResultado.setText("Resultado");
});
```

- Los cuadros se **vacían** con `setText("")`.
- La etiqueta **no** se deja vacía: vuelve a decir **`Resultado`**, que **orienta** al usuario sobre dónde aparecerá la respuesta.

## Centrar la ventana: `centerOnScreen`

Por defecto la ventana se abre en una posición que decide el sistema. Para **centrarla** (en WPF, `WindowStartupLocation="CenterScreen"`):

```java
ventana.centerOnScreen();
ventana.show();
```

## Colocar los elementos

```java
VBox contenedor = new VBox(6, etiquetaNombre, txtNombre, etiquetaEdad, txtEdad, filaBotones, lResultado);
contenedor.setPadding(new Insets(20, 50, 20, 50));   // arriba, derecha, abajo, izquierda
```

Una columna (`VBox`) con una fila de botones (`HBox`) dentro. El `Insets` de cuatro valores va en el orden **arriba, derecha, abajo, izquierda** (en WPF, `Margin` va izquierda, arriba, derecha, abajo).

## Mejoras posibles

El vídeo termina proponiendo ejercicios para repasar lo aprendido:

- Que el **nombre no lleve números**.
- Que la **edad sea un número** (con `Integer.parseInt` y `try/catch`) y no un texto cualquiera.
- Limitar la edad: **no mayor de 100** y, por ejemplo, **mínimo 18**.

## Ejecutar

```bash
java --module-path "C:\javafx-sdk-21\lib" --add-modules javafx.controls JavaFxTextField.java
```

Se prueba: sin datos, solo el nombre, solo la edad, ambos, y **Borrar**.

## Comandos

```bash
cd java/07-ui-and-databases/51-javafx-textfield
java --module-path "C:\javafx-sdk-21\lib" --add-modules javafx.controls JavaFxTextField.java
```

Cambiar `C:\javafx-sdk-21\lib` por la ruta real del SDK de JavaFX.

Fuente: [curso de C# de INFORMATICONFIG](https://www.youtube.com/watch?v=KtmS5I1HA6Y&list=PL2Z95CSZ1N4F7mym8Ca16RoTDVAlIOPlT&index=51), vídeo 51.
