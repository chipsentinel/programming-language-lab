import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

// Etiquetas (Label) y botones (Button) en JavaFX: el equivalente de la lección 50 de WPF.
// Para ejecutarlo (ver el .md):
//   java --module-path "RUTA\javafx-sdk\lib" --add-modules javafx.controls JavaFxButtonsLabels.java

public class JavaFxButtonsLabels extends Application {

    @Override
    public void start(Stage ventana) {

        // ===== 1. Una etiqueta: Label =====

        // El texto es el argumento del constructor (en WPF, la propiedad Content)
        Label labelMensaje = new Label("Mensaje");
        labelMensaje.setId("labelMensaje");          // el id identifica al elemento (en WPF, x:Name)

        // Apariencia con métodos de JavaFX...
        labelMensaje.setFont(Font.font("Verdana", FontWeight.BOLD, 22));   // tipo de letra, grosor y tamaño
        labelMensaje.setTextFill(Color.DARKBLUE);                          // color del texto (Foreground en WPF)

        // ...o con CSS, usando propiedades que empiezan por -fx-
        labelMensaje.setStyle("-fx-background-color: white;");             // color de fondo (Background en WPF)


        // ===== 2. Dos botones: Button =====

        Button botonSaludar = new Button("Saludar");
        botonSaludar.setId("botonSaludar");
        botonSaludar.setPrefSize(120, 36);
        botonSaludar.setStyle("-fx-background-color: lightgreen;");

        Button botonDespedir = new Button("Despedir");
        botonDespedir.setId("botonDespedir");
        botonDespedir.setPrefSize(120, 36);
        botonDespedir.setStyle("-fx-background-color: lightsalmon;");
        botonDespedir.setDisable(true);              // desactivado al empezar (en WPF, IsEnabled = false)


        // ===== 3. Eventos: setOnAction =====

        // Se ejecuta al pulsar el botón. (e) -> {...} es una expresión lambda (lección 46)
        botonSaludar.setOnAction(e -> {
            labelMensaje.setText("Saludos a todos");     // cambiar el texto de la etiqueta

            botonDespedir.setDisable(false);             // ya se saludó: ahora se puede despedir
            botonSaludar.setDisable(true);               // no hace falta saludar otra vez
        });

        botonDespedir.setOnAction(e -> {
            labelMensaje.setText("Adiós a todos");

            botonSaludar.setDisable(false);              // se puede volver a saludar
            botonDespedir.setDisable(true);
        });


        // ===== 4. Colocar los elementos =====

        // HBox: fila de botones. VBox: columna con la etiqueta y la fila.
        HBox filaBotones = new HBox(12, botonSaludar, botonDespedir);
        filaBotones.setAlignment(Pos.CENTER);

        VBox contenedor = new VBox(20, labelMensaje, filaBotones);
        contenedor.setAlignment(Pos.CENTER);
        contenedor.setPadding(new Insets(20));

        ventana.setTitle("Etiquetas y botones");
        ventana.setScene(new Scene(contenedor, 420, 260));
        ventana.show();
    }

    public static void main(String[] args) {
        Application.launch(JavaFxButtonsLabels.class, args);
    }
}
