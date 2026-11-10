import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

// JavaFX: la biblioteca de interfaces gráficas de Java (el equivalente de WPF en C#).
// Una aplicación JavaFX es una clase que HEREDA de Application (lección 38) y redefine el método start.
//
// Estructura de una ventana JavaFX:
//   Stage   (escenario)  la ventana
//   Scene   (escena)     el contenido de la ventana
//   Nodos                los elementos: contenedores (VBox, HBox, GridPane...) y controles (Label, Button...)
//
// JavaFX no viene con el JDK: hay que descargarlo (https://openjfx.io) o usar Maven.
// Para ejecutar este archivo hay que indicar dónde están sus módulos (ver el .md).

public class JavaFxIntroduction extends Application {

    // start es el equivalente del método Main de una aplicación de escritorio: aquí se construye la ventana
    @Override
    public void start(Stage ventana) {

        // ===== 1. Los elementos de la interfaz (nodos) =====

        Label mensaje = new Label("Bienvenido a JavaFX");
        mensaje.setStyle("-fx-font-size: 20px;");                   // estilo con CSS

        Button aceptar = new Button("Aceptar");
        aceptar.setPrefWidth(120);
        aceptar.setPrefHeight(32);


        // ===== 2. Dar funcionalidad: eventos =====

        // setOnAction asocia un código al clic del botón. (e) -> {...} es una expresión lambda (lección 46)
        aceptar.setOnAction(e -> {
            mensaje.setText("Has pulsado Aceptar");
            aceptar.setDisable(true);                                // el botón se desactiva
        });


        // ===== 3. Organizar los elementos en un contenedor =====

        // VBox apila sus hijos uno debajo de otro (como el StackPanel de WPF)
        VBox contenedor = new VBox(16, mensaje, aceptar);            // 16 = espacio entre elementos
        contenedor.setAlignment(Pos.CENTER);
        contenedor.setPadding(new Insets(20));


        // ===== 4. La escena y la ventana =====

        Scene escena = new Scene(contenedor, 400, 260);              // ancho y alto

        ventana.setTitle("Ventana principal");
        ventana.setScene(escena);
        ventana.show();
    }

    // El método main solo lanza la aplicación: launch crea la ventana y llama a start.
    // Se le pasa la clase (JavaFxIntroduction.class) para que funcione al ejecutar el archivo directamente
    // con "java JavaFxIntroduction.java"; en un proyecto Maven basta con launch(args).
    public static void main(String[] args) {
        Application.launch(JavaFxIntroduction.class, args);
    }
}
