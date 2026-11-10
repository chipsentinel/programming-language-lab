# CheckBox y RadioButton en WPF

## Qué se aprende

Dos controles para **elegir** entre opciones:

| Control | Para qué | Cuántas se pueden marcar |
|---|---|---|
| **`CheckBox`** (casilla) | Elegir **varias** opciones a la vez | **Las que se quiera**, en el orden que se quiera |
| **`RadioButton`** (botón de opción) | Elegir **una sola** opción de un grupo | **Solo una**: al marcar otra, se desmarca la anterior |

El ejemplo es una pequeña herramienta de pedidos: **pizzas** (casillas, se pueden pedir varias) y **bebida** (botones de opción, solo una), con un total que se calcula al pulsar **Ordenar**.

## El `CheckBox`

```xml
<CheckBox x:Name="ck01" Content="Pizza de queso - $150.00"
          FontFamily="Verdana" FontSize="14" Foreground="DarkGreen" />
```

- **`Content`** es el texto que se ve junto a la casilla.
- Las propiedades de apariencia son las de siempre (`FontFamily`, `FontSize`, `Foreground`...).
- Cada casilla es **independiente** de las demás.

## El `RadioButton`

```xml
<RadioButton x:Name="rb01" Content="Pepsi - $30.00" />
```

Tiene las **mismas propiedades** que el `CheckBox`. La diferencia es de comportamiento: **los botones de un mismo grupo se excluyen entre sí**.

### ¿Qué es "el mismo grupo"?

- Por defecto, **todos los `RadioButton` que están dentro del mismo contenedor** (`StackPanel`, `Grid`...).
- O los que comparten la propiedad **`GroupName`**: `GroupName="bebida"`.

Si se pusieran las pizzas y las bebidas en el mismo contenedor con `RadioButton`, solo se podría elegir **una opción entre todas**. Por eso cada grupo va en su propio panel.

> Un grupo de `RadioButton` **no se puede desmarcar con un clic**: una vez elegida una opción, el usuario solo puede cambiar a otra. Para dejar el grupo sin selección hay que hacerlo desde el código (`IsChecked = false`), como en el botón Cancelar.

## Nombres con prefijo

Por convención, el nombre indica el tipo de control:

| Prefijo | Control | Ejemplos |
|---|---|---|
| `ck` | `CheckBox` | `ck01`, `ck02`, `ck03` |
| `rb` | `RadioButton` | `rb01`, `rb02`, `rb03` |
| `b` | `Button` | `bOrdenar`, `bCancelar` |
| `l` | `Label` | `lTotal` |

Siempre con **`x:Name`**; sin nombre no se pueden leer desde C#.

## Saber si está marcado: `IsChecked`

```csharp
if (ck01.IsChecked == true)
```

- **`IsChecked`** vale `true` si está marcado y `false` si no.
- En realidad es un **`bool?`** (un booleano que además puede ser `null`, para casillas de tres estados), por eso se **compara** con `== true` o `== false` en lugar de usarla directamente como condición.

Para **marcar o desmarcar** desde el código se **asigna**:

```csharp
ck01.IsChecked = false;
```

## La lógica del pedido

### Regla de negocio: sin pizza no hay pedido

Una **regla de negocio** es una condición propia del programa, no del lenguaje. Aquí: **no se puede pedir solo una bebida**.

```csharp
if (ck01.IsChecked == false && ck02.IsChecked == false && ck03.IsChecked == false)
{
    lTotal.Content = "Debe seleccionar una pizza con la orden";
}
```

Las tres casillas vacías (`&&`) significa que **no hay ninguna pizza**.

### Sumar lo marcado

En el `else` hay al menos una pizza. Se parte de un total de **cero** y se **suma** lo que corresponda:

```csharp
double total = 0.0;

// Pizzas: cada una marcada se suma (una, dos o las tres)
if (ck01.IsChecked == true) total += 150.00;
if (ck02.IsChecked == true) total += 250.00;
if (ck03.IsChecked == true) total += 200.00;

// Bebida: solo una puede estar marcada
if (rb01.IsChecked == true) total += 30.00;
if (rb02.IsChecked == true) total += 30.00;
if (rb03.IsChecked == true) total += 25.00;
```

Son **`if` separados**, no `else if`: las casillas son independientes y todas las marcadas deben sumarse. Con `else if` solo se sumaría la primera.

### Mostrar el total con dos decimales

```csharp
lTotal.Content = "$" + total.ToString("F2", CultureInfo.InvariantCulture);
```

- **`"F2"`**: formato con **dos decimales** (`180.00`).
- **`CultureInfo.InvariantCulture`**: usa siempre el **punto** decimal. Sin ella, en un sistema en español saldría `180,00`. Hay que añadir `using System.Globalization;`.

En el vídeo se escribe `$"${total}"` y luego se añade `.00` a mano; con `ToString("F2")` el formato sale bien en todos los casos (por ejemplo, con `152.5` saldría `152.50`).

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

```csharp
ck01.IsChecked = false;
ck02.IsChecked = false;
ck03.IsChecked = false;

rb01.IsChecked = false;
rb02.IsChecked = false;
rb03.IsChecked = false;

lTotal.Content = string.Empty;
```

Desmarca **todo** y vacía el total. **`string.Empty`** es la forma clara de escribir el texto vacío `""`.

## Etiqueta de total "oculta"

La etiqueta del total (`lTotal`) **empieza vacía**: no se ve, pero está ahí. A su izquierda hay otra etiqueta fija, "Total de la orden:", que da el contexto. Así el usuario sabe qué es el valor cuando aparece.

## Ejercicio propuesto

Tal como está, tras pulsar **Ordenar** el usuario puede seguir cambiando su elección y volver a ordenar; y **Cancelar** funciona siempre. El vídeo propone mejorarlo: **cuando se pulse Ordenar, el botón Cancelar debe quedar desactivado** (`IsEnabled = false`), porque la orden ya es definitiva. Se puede resolver combinando esta lección con la anterior.

## Ejecutar

```bash
dotnet run
```

Se prueba: sin nada marcado, solo bebida, una pizza, varias pizzas, cambiar de bebida y Cancelar.

## Comandos

```bash
cd csharp/07-ui-and-databases
dotnet new wpf -n WpfCheckboxRadiobuttons -o 52-wpf-checkbox-radiobuttons
cd 52-wpf-checkbox-radiobuttons
dotnet run
```

Fuente: [curso de C# de INFORMATICONFIG](https://www.youtube.com/watch?v=3oK4MoUEfCo&list=PL2Z95CSZ1N4F7mym8Ca16RoTDVAlIOPlT&index=52), vídeo 52.
