import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

// Cuadros de texto (TextField) en JavaFX: el equivalente de la lección 51 de WPF.
// Para ejecutarlo (ver el .md):
//   java --module-path "RUTA\javafx-sdk\lib" --add-modules javafx.controls JavaFxTextField.java

public class JavaFxTextField extends Application {

    @Override
    public void start(Stage ventana) {

        // ===== 1. Cuadros de texto: TextField =====

        Label etiquetaNombre = new Label("Ingrese su nombre");
        etiquetaNombre.setStyle("-fx-font-size: 14px;");

        // TextField: un cuadro de texto de una línea (en WPF, TextBox). Lo escrito se lee con getText()
        TextField txtNombre = new TextField();
        txtNombre.setId("txtNombre");
        txtNombre.setStyle("-fx-font-family: Arial; -fx-font-size: 16px; -fx-text-fill: darkblue; -fx-control-inner-background: white;");

        Label etiquetaEdad = new Label("Ingrese su edad");
        etiquetaEdad.setStyle("-fx-font-size: 14px;");

        TextField txtEdad = new TextField();
        txtEdad.setId("txtEdad");
        txtEdad.setStyle("-fx-font-family: Arial; -fx-font-size: 16px; -fx-text-fill: darkblue; -fx-control-inner-background: white;");
        txtEdad.setMaxWidth(80);

        // setPromptText: texto de ayuda que se ve mientras el cuadro está vacío y desaparece al escribir
        // (es la configuración de la que habla el vídeo: "se borra cuando el usuario empieza a escribir")
        // txtNombre.setPromptText("Nombre");


        // ===== 2. Botones y etiqueta de resultado =====

        Button btnIngresar = new Button("Ingresar");
        btnIngresar.setId("btnIngresar");
        btnIngresar.setPrefSize(100, 32);

        Button btnBorrar = new Button("Borrar");
        btnBorrar.setId("btnBorrar");
        btnBorrar.setPrefSize(100, 32);

        Label lResultado = new Label("Resultado");
        lResultado.setId("lResultado");
        lResultado.setStyle("-fx-font-size: 18px; -fx-text-fill: darkgreen;");


        // ===== 3. Botón Ingresar: leer, validar y mostrar =====

        btnIngresar.setOnAction(e -> {
            // getText() es lo que hay escrito; trim() quita los espacios sobrantes de los extremos
            String nombre = txtNombre.getText().trim();
            String edad = txtEdad.getText().trim();

            // isEmpty(): true si el texto está vacío ("")
            if (nombre.isEmpty() && edad.isEmpty()) {
                lResultado.setText("No colocaste ningún dato");
            } else if (nombre.isEmpty()) {
                lResultado.setText("No colocaste nombre");
            } else if (edad.isEmpty()) {
                lResultado.setText("No colocaste edad");
            } else {
                // Todos los datos están: se muestran
                lResultado.setText("Nombre: " + nombre + ", Edad: " + edad);
            }
        });


        // ===== 4. Botón Borrar: limpiar =====

        btnBorrar.setOnAction(e -> {
            txtNombre.setText("");               // en un TextField es setText...
            txtEdad.setText("");
            lResultado.setText("Resultado");     // ...igual que en un Label. No se vacía: orienta al usuario
        });


        // ===== 5. Colocar los elementos =====

        HBox filaBotones = new HBox(12, btnIngresar, btnBorrar);
        filaBotones.setAlignment(Pos.CENTER);

        lResultado.setMaxWidth(Double.MAX_VALUE);
        lResultado.setAlignment(Pos.CENTER);

        VBox contenedor = new VBox(6, etiquetaNombre, txtNombre, etiquetaEdad, txtEdad, filaBotones, lResultado);
        contenedor.setAlignment(Pos.CENTER_LEFT);
        contenedor.setPadding(new Insets(20, 50, 20, 50));
        VBox.setMargin(filaBotones, new Insets(10, 0, 10, 0));

        ventana.setTitle("Cuadros de texto");
        ventana.setScene(new Scene(contenedor, 420, 360));
        ventana.centerOnScreen();      // la ventana aparece en el centro de la pantalla (en WPF, CenterScreen)
        ventana.show();
    }

    public static void main(String[] args) {
        Application.launch(JavaFxTextField.class, args);
    }
}
