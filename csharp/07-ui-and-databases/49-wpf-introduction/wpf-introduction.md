# Introducción a WPF en C#

## Qué es WPF

**WPF** (*Windows Presentation Foundation*) es el *framework* de Microsoft para crear **interfaces gráficas de aplicaciones de escritorio en Windows**: las ventanas con botones, cajas de texto, listas, imágenes y animaciones con las que se interactúa a diario.

- Existe desde **2006** y forma parte de .NET.
- Es una alternativa **más moderna y potente** a **Windows Forms** (los formularios clásicos).
- **Solo funciona en Windows.** Para aplicaciones multiplataforma existen otras opciones (MAUI, Avalonia).

Hasta ahora todos los programas eran de **consola**. A partir de aquí el programa tiene **ventanas**.

## Qué lo hace especial

| Característica | Qué es |
|---|---|
| **XAML** | Un lenguaje de **etiquetas**, parecido a HTML, para describir el diseño de la ventana |
| **Separación diseño / lógica** | El aspecto se escribe en XAML y el comportamiento, en C# |
| **Enlace de datos** (*data binding*) | Conectar objetos de C# con la interfaz sin repetir código |
| **Estilos y plantillas** | Control total de la apariencia: colores, formas, tamaños |
| **Patrón MVVM** | Una forma de estructurar aplicaciones grandes (Modelo – Vista – VistaModelo) |

## Cuándo usar WPF

- Aplicaciones de **escritorio** modernas, que se adaptan a distintos tamaños de pantalla (*responsivas*).
- Interfaces **avanzadas y muy personalizables**.
- Cuando el cliente va a **pedir cambios** de diseño: separar diseño y lógica permite hacerlos con facilidad.
- Proyectos con **.NET y C#**.

## Requisitos

- **Windows.**
- El SDK de **.NET** con la carga de trabajo de **escritorio** (*Desktop development*). En Visual Studio se marca al instalar; con el SDK, el compilador ya incluye lo necesario para compilar WPF.
- **Visual Studio** es la herramienta ideal porque tiene un **diseñador visual**. En **Visual Studio Code** se puede programar WPF, pero no hay diseñador: el XAML se escribe a mano.

## Crear un proyecto WPF

**Desde la línea de comandos:**

```bash
dotnet new wpf -n WpfIntroduction -o 49-wpf-introduction
```

**Desde Visual Studio:** *Crear un proyecto* → buscar `WPF` → **Aplicación WPF** (con el logotipo de **C#**, no la de Visual Basic) → nombre → framework **.NET 8**.

## Estructura del proyecto

```text
49-wpf-introduction/
├── wpf-introduction.csproj    configuración del proyecto
├── App.xaml                   la aplicación (qué ventana se abre)
├── App.xaml.cs                código de la aplicación
├── MainWindow.xaml            DISEÑO de la ventana principal
└── MainWindow.xaml.cs         CÓDIGO de la ventana principal
```

Todo se trabaja en **dos módulos**:

| Módulo | Archivo | Qué contiene |
|---|---|---|
| **Diseño** | `.xaml` | Botones, etiquetas, cajas de texto... (cómo **se ve**) |
| **Código** | `.xaml.cs` | La funcionalidad en C# (qué **hace**) |

### El archivo de proyecto

```xml
<Project Sdk="Microsoft.NET.Sdk">
  <PropertyGroup>
    <OutputType>WinExe</OutputType>
    <TargetFramework>net8.0-windows</TargetFramework>
    <UseWPF>true</UseWPF>
  </PropertyGroup>
</Project>
```

- `WinExe`: una aplicación de **ventanas** (no de consola: no abre ninguna).
- `net8.0-windows`: .NET 8 **para Windows**.
- `UseWPF`: activa WPF.

## XAML: el diseño

```xml
<Window x:Class="WpfIntroduction.MainWindow"
        xmlns="http://schemas.microsoft.com/winfx/2006/xaml/presentation"
        xmlns:x="http://schemas.microsoft.com/winfx/2006/xaml"
        Title="Ventana principal" Height="260" Width="400">

    <Grid>
        <StackPanel VerticalAlignment="Center" HorizontalAlignment="Center">
            <TextBlock x:Name="mensaje" Text="Bienvenido a WPF" FontSize="20" />
            <Button x:Name="aceptar" Content="Aceptar" Width="120" Height="32" Click="Aceptar_Click" />
        </StackPanel>
    </Grid>
</Window>
```

Es **muy parecido a HTML**: **etiquetas** que se abren y se cierran, y **atributos** con sus valores.

| Parte | Significa |
|---|---|
| `<Window>` | La **ventana** |
| `Title="..."` | El **título** de la ventana |
| `Height="260"` `Width="400"` | **Alto** y **ancho** en píxeles |
| `<Grid>` | Un **contenedor** que organiza filas y columnas |
| `<StackPanel>` | Un contenedor que **apila** sus hijos uno debajo de otro |
| `<TextBlock>` | Un **texto** (en otros frameworks, *label*) |
| `<Button>` | Un **botón** |
| `x:Name="aceptar"` | Le da un **nombre** para usarlo desde C# |
| `Click="Aceptar_Click"` | Conecta el botón con un **método** de C# |

Lo demás (`x:Class`, `xmlns`) por ahora se ignora: son declaraciones que WPF necesita para entender el archivo.

Al cambiar `Title`, `Height` o `Width` en el XAML, la ventana cambia. En Visual Studio también se puede **arrastrar el borde** de la ventana en el diseñador y los valores se actualizan solos.

## C#: el código

```csharp
namespace WpfIntroduction
{
    public partial class MainWindow : Window
    {
        public MainWindow()
        {
            InitializeComponent();
        }

        private void Aceptar_Click(object sender, RoutedEventArgs e)
        {
            mensaje.Text = "Has pulsado Aceptar";
            aceptar.IsEnabled = false;
        }
    }
}
```

Es C# normal, con cosas que ya se conocen:

- **`namespace`**: agrupa las clases del proyecto y evita conflictos de nombres.
- **`MainWindow : Window`**: `MainWindow` **hereda** de `Window`, la clase de WPF que representa una ventana (herencia, lección 38).
- **`partial`**: la clase se completa con otra parte **generada automáticamente** a partir del XAML.
- **`InitializeComponent()`**: **carga los elementos del XAML** y los conecta con este código. Es obligatorio y va el primero del constructor.
- **`Aceptar_Click`**: un **manejador de eventos**: se ejecuta cuando ocurre el evento (aquí, el clic).
- **`mensaje.Text`**: los elementos con `x:Name` se usan **por su nombre**, como variables de la clase.

## La caja de herramientas y las propiedades

Con el diseñador de Visual Studio:

- La **caja de herramientas** (*Toolbox*) muestra los controles disponibles: botones, casillas, listas desplegables, cajas de texto... Se **arrastran** a la ventana.
- Al seleccionar un control, el panel **Propiedades** permite cambiar su `Name`, texto, tamaño, fuente, color...
- Se puede **mover**, **redimensionar** e incluso **girar** (`RenderTransform`). Todo se refleja en el XAML.
- **Doble clic en un botón** crea automáticamente su manejador de evento en el código (`Button_Click`).

El diseñador solo **escribe XAML por ti**: lo que se genera es siempre XAML, y se puede editar a mano.

## Ejecutar

```bash
dotnet run
```

O `F5` en Visual Studio. Se abre una **ventana**, con su barra de título y los botones de minimizar, maximizar y cerrar, que se puede cambiar de tamaño. Al pulsar **Aceptar**, el texto cambia y el botón se desactiva.

Hasta que no se escribe código en el manejador, un botón **no hace nada** al pulsarlo.

## Comparación con la consola

| | Aplicación de consola | Aplicación WPF |
|---|---|---|
| Interfaz | Texto | **Ventanas** y controles |
| Punto de entrada | `Main` | `App.xaml` abre la ventana |
| Entrada del usuario | `Console.ReadLine()` | Cajas de texto, botones... |
| Salida | `Console.WriteLine()` | Etiquetas, listas... |
| El programa avanza | De arriba abajo | **Por eventos**: espera a que el usuario haga algo |

Es un cambio de mentalidad: en lugar de ir ejecutando líneas, el programa **espera** y **reacciona** a lo que hace el usuario (**programación dirigida por eventos**).

## Comandos

```bash
cd csharp/07-ui-and-databases
dotnet new wpf -n WpfIntroduction -o 49-wpf-introduction
cd 49-wpf-introduction
dotnet run
```

Fuente: [curso de C# de INFORMATICONFIG](https://www.youtube.com/watch?v=uwishEicQMs&list=PL2Z95CSZ1N4F7mym8Ca16RoTDVAlIOPlT&index=49), vídeo 49.
