# Cuadros de texto en WPF (TextBox)

## Qué se aprende

El **`TextBox`** es el cuadro donde el usuario **escribe** (y también donde se puede **mostrar** un dato). Es el elemento de cualquier formulario: nombre, edad, correo...

En esta lección se construye un pequeño formulario: dos `TextBox` (nombre y edad), un botón **Ingresar** que valida y muestra los datos, y un botón **Borrar** que limpia todo.

## El elemento `TextBox`

```xml
<TextBox x:Name="txtNombre" FontFamily="Arial" FontSize="16"
         Foreground="DarkBlue" Background="White" />
```

| Propiedad | Qué controla |
|---|---|
| **`Text`** | El **texto** del cuadro: lo que el usuario escribe |
| `FontFamily` / `FontSize` | La **letra** con la que escribe el usuario |
| `Foreground` | El **color de la letra** |
| `Background` | El **color de fondo** del cuadro |
| `x:Name` | El **nombre** para usarlo desde C# |

Todas las propiedades se pueden tocar en el panel **Propiedades** del diseñador (secciones *Texto* y *Pincel*): conviene probarlas sin miedo.

### Texto inicial del cuadro

Al arrastrar un `TextBox`, el diseñador le pone `Text="TextBox"`. Hay que **quitar esa propiedad** o dejarla vacía, y para el texto de ayuda usar una **etiqueta** encima (`Label`: "Ingrese su nombre").

Se puede dejar un texto de ayuda dentro del propio cuadro, pero entonces el usuario tiene que borrarlo. Los formularios actuales usan un **texto de sugerencia** (*placeholder*) que **desaparece al empezar a escribir**; el `TextBox` de WPF no lo trae de serie (se consigue con una plantilla o un control propio, más adelante).

## El nombre: `x:Name` y su relación con el evento

Para leer o cambiar el contenido hay que **darle nombre** al cuadro, y **debe ser distinto** del de cualquier método:

```xml
<TextBox x:Name="txtNombre" ... />
```

Un error típico del vídeo: **doble clic** en el `TextBox` crea un manejador de `TextChanged="TextBox_TextChanged"`, y si no se le ha dado nombre, el diseñador usa nombres genéricos que **chocan o se confunden**. Si el nombre del elemento y el del método coinciden, el compilador dice que el objeto "es un método, no está en un contexto válido".

Recomendaciones:

- Dar a **todos** los elementos un `x:Name` explícito.
- Usar un **prefijo por tipo**: `txtNombre`, `txtEdad` (cuadros de texto), `btnIngresar`, `btnBorrar` (botones), `lResultado` (etiqueta).
- **No** crear manejadores que no se van a usar (el `TextChanged` de cada cuadro): si se crean por doble clic y no hacen falta, **se borran del XAML y del código**, o el compilador se queja de que falta la definición.

> Un error que sale en el vídeo: "no contiene una definición para `txtEdad`". Ocurre cuando el XAML y el código no están de acuerdo (se escribió el nombre en un sitio y no en el otro). Se arregla dando el `x:Name` en el XAML.

## Leer y limpiar el texto: `Text` y `Trim()`

```csharp
string nombre = txtNombre.Text.Trim();
```

- **`txtNombre.Text`** es lo que hay escrito.
- **`Trim()`** quita los **espacios sobrantes** de los extremos (`"  Sol  "` → `"Sol"`).

Para **escribir** en el cuadro se asigna `Text`:

```csharp
txtNombre.Text = "";
```

| Control | Propiedad del texto |
|---|---|
| `TextBox` (cuadro de texto) | **`Text`** |
| `Label` (etiqueta) | **`Content`** |

Es un fallo muy común usar `Content` en un `TextBox` o `Text` en un `Label`.

## Validar: `string.IsNullOrEmpty`

El usuario puede pulsar **Ingresar** **sin escribir nada**. Hay que comprobarlo:

```csharp
if (string.IsNullOrEmpty(nombre) && string.IsNullOrEmpty(edad))
```

`string.IsNullOrEmpty(texto)` vale `true` si el texto es `null` o está vacío (`""`).

> Como se hace `Trim()` antes, un cuadro con solo espacios cuenta como **vacío**. (Existe también `string.IsNullOrWhiteSpace`, que ya hace esa comprobación de espacios.)

### Las cuatro situaciones

El primer planteamiento del vídeo falla, y es instructivo:

1. Con `if (vacío(nombre) || vacío(edad))` el mensaje "no colocaste ningún dato" **también salía** si faltaba **solo uno** de los dos.
2. Con `&&` solo se detecta cuando faltan **los dos**, y la edad vacía queda sin tratar.

La solución es **encadenar `else if`**, de lo más específico a lo menos:

```csharp
if (string.IsNullOrEmpty(nombre) && string.IsNullOrEmpty(edad))
{
    lResultado.Content = "No colocaste ningún dato";      // faltan los dos
}
else if (string.IsNullOrEmpty(nombre))
{
    lResultado.Content = "No colocaste nombre";           // falta solo el nombre
}
else if (string.IsNullOrEmpty(edad))
{
    lResultado.Content = "No colocaste edad";             // falta solo la edad
}
else
{
    lResultado.Content = "Nombre: " + nombre + ", Edad: " + edad;   // están los dos
}
```

El `else` final solo se ejecuta si **todo lo anterior falló**, es decir, si **hay datos**. Es un buen ejemplo de **control de flujo** (lección 15) aplicado a una ventana.

### Alternativa con `return`

En el vídeo se usa en un momento `return;` dentro de un `if`: **termina el método** de inmediato, de modo que el resto no se ejecuta. Es válido para evitar anidar `if`, pero con `else if` queda más legible.

## El botón Borrar

```csharp
private void BtnBorrar_Click(object sender, RoutedEventArgs e)
{
    txtNombre.Text = "";
    txtEdad.Text = "";
    lResultado.Content = "Resultado";
}
```

- Los cuadros se **vacían** con `Text = ""`.
- La etiqueta **no** se deja vacía: vuelve a decir **`Resultado`**, que **orienta** al usuario sobre dónde aparecerá la respuesta. Si se pusiera `""`, la etiqueta desaparecería.

## Centrar la ventana: `WindowStartupLocation`

Por defecto la ventana se abre en la **esquina superior izquierda**. En la etiqueta `<Window>` del XAML:

```xml
<Window ... WindowStartupLocation="CenterScreen">
```

Debe quedar **dentro** de la etiqueta de apertura (antes de `>`), porque es una propiedad de la ventana.

## Mejoras posibles

El vídeo termina proponiendo ejercicios para repasar lo aprendido:

- Que el **nombre no lleve números**.
- Que la **edad sea un número** (con `int.TryParse`) y no un texto cualquiera.
- Limitar la edad: **no mayor de 100** y, por ejemplo, **mínimo 18**.

## Copiar controles

`Ctrl + C` / `Ctrl + V` en el diseñador **duplica** un elemento con todas sus propiedades. Hay que **cambiar su `x:Name`** (y el `Content`) o se mezclarán con el original. Así se hizo la segunda etiqueta/cuadro (la edad) y el botón Borrar.

## Ejecutar

```bash
dotnet run
```

Con `F5` en Visual Studio. Se prueba: sin datos, solo el nombre, solo la edad, ambos, y **Borrar**.

## Comandos

```bash
cd csharp/07-ui-and-databases
dotnet new wpf -n WpfTextBox -o 51-wpf-textbox
cd 51-wpf-textbox
dotnet run
```

Fuente: [curso de C# de INFORMATICONFIG](https://www.youtube.com/watch?v=KtmS5I1HA6Y&list=PL2Z95CSZ1N4F7mym8Ca16RoTDVAlIOPlT&index=51), vídeo 51.
