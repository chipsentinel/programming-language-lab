# CheckBox y RadioButton en JavaFX

## Qué se aprende

Dos controles para **elegir** entre opciones (equivalente a la lección 52 de WPF):

| Control | Para qué | Cuántas se pueden marcar |
|---|---|---|
| **`CheckBox`** (casilla) | Elegir **varias** opciones a la vez | **Las que se quiera**, en el orden que se quiera |
| **`RadioButton`** (botón de opción) | Elegir **una sola** opción de un grupo | **Solo una**: al marcar otra, se desmarca la anterior |

El ejemplo es una pequeña herramienta de pedidos: **pizzas** (casillas, se pueden pedir varias) y **bebida** (botones de opción, solo una), con un total que se calcula al pulsar **Ordenar**.

## El `CheckBox`

```java
CheckBox ck01 = new CheckBox("Pizza de queso - $150.00");
ck01.setId("ck01");
ck01.setStyle("-fx-font-family: Verdana; -fx-font-size: 14px; -fx-text-fill: darkgreen;");
```

- El **texto** es el argumento del constructor (en WPF, `Content`).
- La apariencia, con métodos o con CSS (`-fx-...`).
- Cada casilla es **independiente** de las demás.

## El `RadioButton` y el `ToggleGroup`

```java
RadioButton rb01 = new RadioButton("Pepsi - $30.00");
RadioButton rb02 = new RadioButton("Coca-Cola - $30.00");
RadioButton rb03 = new RadioButton("Sprite - $25.00");

ToggleGroup grupoBebidas = new ToggleGroup();
rb01.setToggleGroup(grupoBebidas);
rb02.setToggleGroup(grupoBebidas);
rb03.setToggleGroup(grupoBebidas);
```

Los botones de opción necesitan **un grupo explícito**: el **`ToggleGroup`**. Los que tengan **el mismo grupo** se excluyen entre sí.

Esta es una **diferencia con WPF**, donde basta con que estén en el mismo contenedor. En JavaFX **sin `ToggleGroup`**, cada `RadioButton` es independiente y se podrían marcar los tres.

| | JavaFX | WPF |
|---|---|---|
| Agrupar | `ToggleGroup` + `setToggleGroup` | Mismo contenedor o `GroupName` |

> Un grupo de `RadioButton` **no se puede desmarcar con un clic**: una vez elegida una opción, el usuario solo puede cambiar a otra. Para dejar el grupo sin selección hay que hacerlo desde el código: `grupoBebidas.selectToggle(null)`.

## Nombres con prefijo

Por convención, el nombre indica el tipo de control:

| Prefijo | Control | Ejemplos |
|---|---|---|
| `ck` | `CheckBox` | `ck01`, `ck02`, `ck03` |
| `rb` | `RadioButton` | `rb01`, `rb02`, `rb03` |
| `b` | `Button` | `bOrdenar`, `bCancelar` |
| `l` | `Label` | `lTotal` |

Aquí ese nombre es **el de la variable** y también el **`id`** (`setId`).

## Saber si está marcado: `isSelected()`

```java
if (ck01.isSelected())
```

- **`isSelected()`** devuelve `true` si está marcado y `false` si no. Funciona igual para `CheckBox` y `RadioButton`.
- En WPF era `IsChecked`, que además podía ser `null` (un `bool?`); en JavaFX el `CheckBox` normal es solo `true`/`false`.

Para **marcar o desmarcar** desde el código:

```java
ck01.setSelected(false);
```

## La lógica del pedido

### Regla de negocio: sin pizza no hay pedido

Una **regla de negocio** es una condición propia del programa, no del lenguaje. Aquí: **no se puede pedir solo una bebida**.

```java
if (!ck01.isSelected() && !ck02.isSelected() && !ck03.isSelected()) {
    lTotal.setText("Debe seleccionar una pizza con la orden");
}
```

Las tres casillas **sin marcar** (`&&`) significa que **no hay ninguna pizza**.

### Sumar lo marcado

En el `else` hay al menos una pizza. Se parte de un total de **cero** y se **suma** lo que corresponda:

```java
double total = 0.0;

// Pizzas: cada una marcada se suma (una, dos o las tres)
if (ck01.isSelected()) total += 150.00;
if (ck02.isSelected()) total += 250.00;
if (ck03.isSelected()) total += 200.00;

// Bebida: solo una puede estar marcada
if (rb01.isSelected()) total += 30.00;
if (rb02.isSelected()) total += 30.00;
if (rb03.isSelected()) total += 25.00;
```

Son **`if` separados**, no `else if`: las casillas son independientes y todas las marcadas deben sumarse. Con `else if` solo se sumaría la primera.

### Mostrar el total con dos decimales

```java
lTotal.setText("$" + String.format(Locale.ROOT, "%.2f", total));
```

- **`%.2f`**: formato con **dos decimales** (`180.00`).
- **`Locale.ROOT`**: usa siempre el **punto** decimal. Sin él, en un sistema en español saldría `180,00`. Hay que importar `java.util.Locale`.

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

```java
bCancelar.setOnAction(e -> {
    ck01.setSelected(false);
    ck02.setSelected(false);
    ck03.setSelected(false);

    grupoBebidas.selectToggle(null);

    lTotal.setText("");
});
```

Desmarca **todo** y vacía el total. Para los botones de opción basta con deseleccionar **el grupo**.

## Etiqueta de total "oculta"

La etiqueta del total (`lTotal`) **empieza vacía**: no se ve, pero está ahí. A su izquierda hay otra etiqueta fija, "Total de la orden:", que da el contexto. Así el usuario sabe qué es el valor cuando aparece.

## Colocar los elementos

```java
HBox columnas = new HBox(columnaPizzas, columnaBebidas);                  // dos columnas lado a lado
VBox contenedor = new VBox(24, columnas, filaBotones, filaTotal);         // todo en vertical
```

Cada columna es un `VBox` (etiqueta + casillas) y se juntan en un `HBox`.

## Ejercicio propuesto

Tal como está, tras pulsar **Ordenar** el usuario puede seguir cambiando su elección y volver a ordenar; y **Cancelar** funciona siempre. El vídeo propone mejorarlo: **cuando se pulse Ordenar, el botón Cancelar debe quedar desactivado** (`bCancelar.setDisable(true)`), porque la orden ya es definitiva.

## Ejecutar

```bash
java --module-path "C:\javafx-sdk-21\lib" --add-modules javafx.controls JavaFxCheckboxRadiobuttons.java
```

Se prueba: sin nada marcado, solo bebida, una pizza, varias pizzas, cambiar de bebida y Cancelar.

## Comandos

```bash
cd java/07-ui-and-databases/52-javafx-checkbox-radiobuttons
java --module-path "C:\javafx-sdk-21\lib" --add-modules javafx.controls JavaFxCheckboxRadiobuttons.java
```

Cambiar `C:\javafx-sdk-21\lib` por la ruta real del SDK de JavaFX.

Fuente: [curso de C# de INFORMATICONFIG](https://www.youtube.com/watch?v=3oK4MoUEfCo&list=PL2Z95CSZ1N4F7mym8Ca16RoTDVAlIOPlT&index=52), vídeo 52.
