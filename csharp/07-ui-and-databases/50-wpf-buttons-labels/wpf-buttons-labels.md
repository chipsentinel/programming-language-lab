# Etiquetas y botones en WPF

## Qué se aprende

Los dos elementos más básicos de una ventana:

- **`Label`**: una **etiqueta**, un texto en pantalla.
- **`Button`**: un **botón** que el usuario pulsa.

Y cómo darles **apariencia** (colores, fuentes), **un nombre** y **funcionalidad** (eventos).

## El elemento `Label`

```xml
<Label x:Name="labelMensaje"
       Content="Mensaje"
       FontFamily="Verdana" FontSize="22" FontWeight="Bold"
       Foreground="DarkBlue" Background="White" />
```

Un elemento en XAML se escribe con una **etiqueta** y **propiedades** como atributos:

| Propiedad | Qué controla |
|---|---|
| **`Content`** | El **texto** de la etiqueta |
| `Foreground` | El **color del texto** |
| `Background` | El **color de fondo** |
| `FontFamily` | El **tipo de letra** (`Verdana`, `Arial`...) |
| `FontSize` | El **tamaño** de la letra, en píxeles |
| `FontWeight` | El **grosor** (`Bold` es negrita) |
| `x:Name` | El **nombre** del elemento para usarlo desde C# |

### Toolbox y Propiedades

En Visual Studio, el XAML se puede escribir a mano o con el diseñador:

1. Arrastrar un **`Label`** de la **caja de herramientas** a la ventana.
2. Seleccionar el elemento: el panel **Propiedades** muestra todas sus propiedades agrupadas.
   - **Pincel** (*Brush*): `Background` y `Foreground`, con selector de color.
   - **Texto**: fuente, tamaño y grosor.
   - **Diseño** (*Layout*): ancho, alto, alineación y márgenes.
   - **Nombre**: cada elemento puede recibir un nombre.
3. Cualquier cambio se **escribe en el XAML**.

Al mover un elemento por la ventana, unas **líneas rojas guía** indican cuándo queda **centrado**.

### Posición: alineación y márgenes

| Propiedad | Qué hace |
|---|---|
| `HorizontalAlignment` | Alinea en horizontal: `Left`, `Center`, `Right`, `Stretch` |
| `VerticalAlignment` | Alinea en vertical: `Top`, `Center`, `Bottom`, `Stretch` |
| `Margin="0,0,0,20"` | Margen: izquierda, arriba, derecha, abajo |
| `Width` / `Height` | Ancho y alto |

Dentro de un contenedor (`StackPanel`, `Grid`) la alineación se mide **respecto a ese contenedor**, no a la ventana.

## Poner un nombre: `x:Name`

Para usar un elemento desde C#, hay que **darle un nombre** con `x:Name`:

```xml
<Label x:Name="labelMensaje" Content="Mensaje" />
```

Desde el código, ese nombre **funciona como una variable**:

```csharp
labelMensaje.Content = "Saludos a todos";
```

Sin `x:Name`, el elemento se ve en pantalla pero **no se puede manipular** desde el código.

Convención: `camelCase` y un prefijo con el tipo (`labelMensaje`, `botonSaludar`) para saber qué es.

## El elemento `Button`

```xml
<Button x:Name="botonSaludar" Content="Saludar" Width="120" Height="36"
        Background="LightGreen" Foreground="Black" Click="BotonSaludar_Click" />
```

Tiene las **mismas propiedades** de apariencia y posición que `Label` (`Content`, `Background`, `Foreground`, `Width`...), más una importante: **`Click`**.

Al pasar el ratón por encima el botón **cambia de color** (efecto *hover*) sin hacer nada. Pero **al pulsarlo, no ocurre nada** hasta que se le da funcionalidad.

## Eventos: darle funcionalidad

Un **evento** es algo que ocurre en la interfaz (un clic, una tecla). Se le asocia un **método** de C# que se ejecuta cuando sucede:

```xml
<Button ... Click="BotonSaludar_Click" />
```

```csharp
private void BotonSaludar_Click(object sender, RoutedEventArgs e)
{
    labelMensaje.Content = "Saludos a todos";
}
```

- **`Click="BotonSaludar_Click"`** en el XAML es el **enlace** entre el botón y el método.
- El método se llama **manejador de evento** (*event handler*) y tiene siempre los parámetros `object sender, RoutedEventArgs e`.
- Dentro se usa el nombre de cualquier elemento: `labelMensaje.Content = ...`.

### Crear el manejador

- **Doble clic** en el botón del diseñador: Visual Studio crea el método vacío en el código (llamado `Button_Click`) y escribe el `Click="..."` en el XAML.
- Al cambiar el nombre del método en el XAML hay que **renombrarlo también** en el código; si no coinciden, no compila. Un nombre descriptivo (`BotonSaludar_Click`) es mucho más claro que `Button_Click`.

## Activar y desactivar: `IsEnabled`

Todos los controles tienen `IsEnabled`: `true` si se pueden usar y `false` si están **desactivados** (se ven atenuados y no responden).

```xml
<Button x:Name="botonDespedir" Content="Despedir" IsEnabled="False" ... />
```

```csharp
botonDespedir.IsEnabled = true;
```

Se puede fijar en el XAML o en el constructor (después de `InitializeComponent()`).

## El ejemplo: saludar y despedir

La ventana tiene una etiqueta y dos botones, con esta **lógica**:

```text
Estado inicial:   [Saludar] activo      [Despedir] desactivado     "Mensaje"

Pulsar Saludar →   etiqueta "Saludos a todos"   [Saludar] desactivado   [Despedir] activo
Pulsar Despedir →  etiqueta "Adiós a todos"     [Saludar] activo        [Despedir] desactivado
```

```csharp
private void BotonSaludar_Click(object sender, RoutedEventArgs e)
{
    labelMensaje.Content = "Saludos a todos";

    botonDespedir.IsEnabled = true;      // ya se saludó: ahora se puede despedir
    botonSaludar.IsEnabled = false;      // no hace falta saludar otra vez
}

private void BotonDespedir_Click(object sender, RoutedEventArgs e)
{
    labelMensaje.Content = "Adiós a todos";

    botonSaludar.IsEnabled = true;       // se puede volver a saludar
    botonDespedir.IsEnabled = false;
}
```

Es **lógica de programa** aplicada a una interfaz: el estado de los botones **guía** al usuario, que no puede despedirse sin haber saludado antes.

> Es fácil olvidar uno de los dos cambios (activar uno y desactivar el otro). Hay que escribir **siempre los dos** en cada manejador.

## Copiar un elemento

`Ctrl + C` y `Ctrl + V` sobre un botón en el diseñador crea una **copia** con el nombre terminado en `_Copy`. Hay que **cambiarle el `x:Name`**, el `Content` y el `Click` para que no se mezcle con el original.

## `Label` o `TextBlock`

| | `Label` | `TextBlock` |
|---|---|---|
| Texto en la propiedad | `Content` | `Text` |
| Para | Etiquetas de formularios, con soporte de teclas de acceso | **Texto en general**, con saltos de línea y ajuste |
| Peso | Algo más pesado | Más ligero |

Para un texto que solo se muestra, `TextBlock` es lo más habitual; `Label` es el que se usa en el vídeo.

## Ejecutar

```bash
dotnet run
```

O `F5` en Visual Studio. Al pulsar **Saludar** el mensaje cambia y los botones intercambian su estado.

## Comandos

```bash
cd csharp/07-ui-and-databases
dotnet new wpf -n WpfButtonsLabels -o 50-wpf-buttons-labels
cd 50-wpf-buttons-labels
dotnet run
```

Fuente: [curso de C# de INFORMATICONFIG](https://www.youtube.com/watch?v=lGXTKkC-5nE&list=PL2Z95CSZ1N4F7mym8Ca16RoTDVAlIOPlT&index=50), vídeo 50.
