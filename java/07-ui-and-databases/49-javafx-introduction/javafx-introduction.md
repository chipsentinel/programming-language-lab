# Introducción a JavaFX en Java

## Qué es JavaFX

**JavaFX** es la biblioteca de Java para crear **interfaces gráficas de aplicaciones de escritorio**: ventanas con botones, cajas de texto, listas, imágenes y animaciones. Es el equivalente de **WPF** en C#.

- Funciona en **Windows, macOS y Linux** (es multiplataforma).
- Sustituye a **Swing** y **AWT**, las bibliotecas antiguas.
- Desde Java 11 **no viene con el JDK**: se descarga aparte (*OpenJFX*) o se incluye como dependencia de Maven.

Hasta ahora todos los programas eran de **consola**. A partir de aquí el programa tiene **ventanas**.

## Qué lo hace especial

| Característica | Qué es |
|---|---|
| **Escenas y nodos** | La interfaz es un **árbol** de elementos (nodos) |
| **FXML** | Un lenguaje de **etiquetas** (como el XAML de WPF) para describir el diseño |
| **CSS** | La apariencia se puede definir con **hojas de estilo** |
| **Enlace de propiedades** | Conectar valores con la interfaz sin repetir código |
| **Scene Builder** | Una herramienta visual para diseñar ventanas (arrastrar y soltar) |

En esta lección el diseño se escribe **directamente en Java**, sin FXML.

## Cuándo usar JavaFX

- Aplicaciones de **escritorio** en Java.
- Interfaces **modernas y personalizables**.
- Cuando se quiere el mismo programa en **varios sistemas operativos**.

## Preparación

Hace falta el SDK de JavaFX (`javafx-sdk-XX`) de [openjfx.io](https://openjfx.io). Contiene una carpeta **`lib`** con los módulos. Se indica dónde está al ejecutar.

En un **proyecto Maven** se añade como dependencia (`org.openjfx:javafx-controls`) y no hace falta nada más. IntelliJ IDEA lo configura al crear un proyecto *JavaFX*.

## La estructura de una ventana

```text
Stage  (escenario)     ← la ventana
└── Scene  (escena)    ← el contenido de la ventana
    └── VBox           ← un contenedor
        ├── Label      ← un texto
        └── Button     ← un botón
```

| Clase | Qué es | En WPF |
|---|---|---|
| `Stage` | La **ventana** | `Window` |
| `Scene` | El **contenido** de la ventana | El contenido de `Window` |
| `VBox` / `HBox` | Contenedor en **columna** / en **fila** | `StackPanel` |
| `GridPane` | Contenedor en **filas y columnas** | `Grid` |
| `Label` | Un **texto** | `TextBlock` |
| `Button` | Un **botón** | `Button` |

## La aplicación

```java
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class JavaFxIntroduction extends Application {

    @Override
    public void start(Stage ventana) {
        Label mensaje = new Label("Bienvenido a JavaFX");
        Button aceptar = new Button("Aceptar");

        aceptar.setOnAction(e -> {
            mensaje.setText("Has pulsado Aceptar");
            aceptar.setDisable(true);
        });

        VBox contenedor = new VBox(16, mensaje, aceptar);

        ventana.setTitle("Ventana principal");
        ventana.setScene(new Scene(contenedor, 400, 260));
        ventana.show();
    }

    public static void main(String[] args) {
        Application.launch(JavaFxIntroduction.class, args);
    }
}
```

### Paso a paso

- **`extends Application`**: toda aplicación JavaFX **hereda** de `Application` (herencia, lección 38).
- **`start(Stage ventana)`**: JavaFX lo llama al arrancar y le entrega **la ventana principal**. Es donde se construye la interfaz.
- **`@Override`**: redefine un método del padre (lección 48).
- **`new Label(...)`, `new Button(...)`**: se crean los **elementos** (nodos) como cualquier objeto.
- **`new VBox(16, mensaje, aceptar)`**: un contenedor que **apila** sus hijos con 16 píxeles de separación.
- **`new Scene(contenedor, 400, 260)`**: la escena, con su **ancho y alto**.
- **`ventana.setTitle(...)`**: el **título** de la ventana.
- **`ventana.show()`**: **muestra** la ventana. Sin esta línea no se ve nada.
- **`launch`**: arranca JavaFX, crea la ventana y llama a `start`.

`launch(args)` es lo habitual en un proyecto; aquí se escribe `Application.launch(JavaFxIntroduction.class, args)` porque, al ejecutar un **archivo suelto**, JavaFX no encuentra la clase por su nombre.

## Dar funcionalidad: eventos

```java
aceptar.setOnAction(e -> {
    mensaje.setText("Has pulsado Aceptar");
});
```

`setOnAction` asocia un **código** al clic del botón. `e -> { ... }` es una **expresión lambda** (lección 46): el código que se ejecuta cuando el usuario pulsa.

Hasta que no se asocia un evento, un botón **no hace nada** al pulsarlo.

## Estilos con CSS

```java
mensaje.setStyle("-fx-font-size: 20px;");
```

Las propiedades CSS de JavaFX empiezan por `-fx-`. Lo normal es un archivo `.css` aparte, cargado en la escena.

## Ejecutar

Con el SDK descargado, hay que indicar **dónde están sus módulos** y **cuáles se usan**:

```bash
java --module-path "C:\javafx-sdk-21\lib" --add-modules javafx.controls JavaFxIntroduction.java
```

- `--module-path`: la carpeta `lib` del SDK.
- `--add-modules javafx.controls`: los módulos que se necesitan (`javafx.controls` incluye los botones, etiquetas, etc.).

Se abre una **ventana**, con su barra de título y los botones de minimizar, maximizar y cerrar, que se puede cambiar de tamaño. Al pulsar **Aceptar**, el texto cambia y el botón se desactiva.

Con **IntelliJ IDEA**, al crear un proyecto *JavaFX* todo esto está ya configurado y basta con pulsar el botón de ejecutar.

## Comparación con la consola

| | Aplicación de consola | Aplicación JavaFX |
|---|---|---|
| Interfaz | Texto | **Ventanas** y controles |
| Punto de entrada | `main` | `main` → `launch` → `start` |
| Entrada del usuario | `Scanner` | Cajas de texto, botones... |
| Salida | `System.out.println()` | Etiquetas, listas... |
| El programa avanza | De arriba abajo | **Por eventos**: espera a que el usuario haga algo |

Es un cambio de mentalidad: en lugar de ir ejecutando líneas, el programa **espera** y **reacciona** a lo que hace el usuario (**programación dirigida por eventos**).

## Comandos

```bash
cd java/07-ui-and-databases/49-javafx-introduction
java --module-path "C:\javafx-sdk-21\lib" --add-modules javafx.controls JavaFxIntroduction.java
```

Cambiar `C:\javafx-sdk-21\lib` por la ruta real del SDK.

Fuente: [curso de C# de INFORMATICONFIG](https://www.youtube.com/watch?v=uwishEicQMs&list=PL2Z95CSZ1N4F7mym8Ca16RoTDVAlIOPlT&index=49), vídeo 49.
