import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.util.Locale;

// CheckBox y RadioButton en JavaFX: el equivalente de la lección 52 de WPF.
// Para ejecutarlo (ver el .md):
//   java --module-path "RUTA\javafx-sdk\lib" --add-modules javafx.controls JavaFxCheckboxRadiobuttons.java

public class JavaFxCheckboxRadiobuttons extends Application {

    @Override
    public void start(Stage ventana) {

        // ===== 1. CheckBox: se pueden marcar varias a la vez =====

        Label etiquetaPizzas = new Label("Indique su sabor de pizza");
        etiquetaPizzas.setStyle("-fx-font-family: Verdana; -fx-font-size: 15px; -fx-text-fill: darkblue;");

        CheckBox ck01 = new CheckBox("Pizza de queso - $150.00");
        CheckBox ck02 = new CheckBox("Pizza de jamón y queso - $250.00");
        CheckBox ck03 = new CheckBox("Pizza de queso y pepperoni - $200.00");
        ck01.setId("ck01");
        ck02.setId("ck02");
        ck03.setId("ck03");
        for (CheckBox ck : new CheckBox[] { ck01, ck02, ck03 }) {
            ck.setStyle("-fx-font-family: Verdana; -fx-font-size: 14px; -fx-text-fill: darkgreen;");
        }

        VBox columnaPizzas = new VBox(8, etiquetaPizzas, ck01, ck02, ck03);
        columnaPizzas.setPrefWidth(280);


        // ===== 2. RadioButton: solo uno del grupo =====

        Label etiquetaBebidas = new Label("Indique su bebida");
        etiquetaBebidas.setStyle("-fx-font-family: Verdana; -fx-font-size: 15px; -fx-text-fill: darkblue;");

        RadioButton rb01 = new RadioButton("Pepsi - $30.00");
        RadioButton rb02 = new RadioButton("Coca-Cola - $30.00");
        RadioButton rb03 = new RadioButton("Sprite - $25.00");
        rb01.setId("rb01");
        rb02.setId("rb02");
        rb03.setId("rb03");

        // En JavaFX los RadioButton se agrupan con un ToggleGroup (en WPF basta con que estén en el mismo contenedor)
        ToggleGroup grupoBebidas = new ToggleGroup();
        for (RadioButton rb : new RadioButton[] { rb01, rb02, rb03 }) {
            rb.setToggleGroup(grupoBebidas);
            rb.setStyle("-fx-font-family: Verdana; -fx-font-size: 14px; -fx-text-fill: darkolivegreen;");
        }

        VBox columnaBebidas = new VBox(8, etiquetaBebidas, rb01, rb02, rb03);
        columnaBebidas.setPrefWidth(220);


        // ===== 3. Botones y total =====

        Button bOrdenar = new Button("Ordenar");
        bOrdenar.setId("bOrdenar");
        bOrdenar.setPrefSize(120, 34);
        bOrdenar.setStyle("-fx-font-family: Arial; -fx-font-weight: bold; -fx-font-size: 16px; -fx-background-color: lightgreen;");

        Button bCancelar = new Button("Cancelar");
        bCancelar.setId("bCancelar");
        bCancelar.setPrefSize(120, 34);
        bCancelar.setStyle("-fx-font-family: Arial; -fx-font-weight: bold; -fx-font-size: 16px; -fx-background-color: lightsalmon;");

        Label etiquetaTotal = new Label("Total de la orden:");
        etiquetaTotal.setStyle("-fx-font-size: 16px;");

        // Etiqueta del total: empieza vacía y se rellena desde el código
        Label lTotal = new Label("");
        lTotal.setId("lTotal");
        lTotal.setStyle("-fx-font-size: 16px; -fx-font-weight: bold; -fx-text-fill: darkred;");


        // ===== 4. Botón Ordenar: calcular el total =====

        bOrdenar.setOnAction(e -> {
            // isSelected(): true si el CheckBox/RadioButton está marcado
            // Regla de negocio: no se puede ordenar sin pizza (una bebida sola no vale)
            if (!ck01.isSelected() && !ck02.isSelected() && !ck03.isSelected()) {
                lTotal.setText("Debe seleccionar una pizza con la orden");
            } else {
                double total = 0.0;

                // Selección de pizzas: se suman todas las marcadas (pueden ser una, dos o las tres)
                if (ck01.isSelected()) total += 150.00;
                if (ck02.isSelected()) total += 250.00;
                if (ck03.isSelected()) total += 200.00;

                // Selección de bebidas: solo una puede estar marcada (RadioButton)
                if (rb01.isSelected()) total += 30.00;
                if (rb02.isSelected()) total += 30.00;
                if (rb03.isSelected()) total += 25.00;

                // %.2f: dos decimales. Locale.ROOT: siempre con punto, sea cual sea el idioma del sistema
                lTotal.setText("$" + String.format(Locale.ROOT, "%.2f", total));
            }
        });


        // ===== 5. Botón Cancelar: desmarcar todo =====

        bCancelar.setOnAction(e -> {
            ck01.setSelected(false);
            ck02.setSelected(false);
            ck03.setSelected(false);

            grupoBebidas.selectToggle(null);     // desmarca el RadioButton elegido del grupo

            lTotal.setText("");
        });


        // ===== 6. Colocar los elementos =====

        HBox columnas = new HBox(columnaPizzas, columnaBebidas);
        columnas.setAlignment(Pos.CENTER);

        HBox filaBotones = new HBox(12, bOrdenar, bCancelar);
        filaBotones.setAlignment(Pos.CENTER);

        HBox filaTotal = new HBox(6, etiquetaTotal, lTotal);
        filaTotal.setAlignment(Pos.CENTER);

        VBox contenedor = new VBox(24, columnas, filaBotones, filaTotal);
        contenedor.setAlignment(Pos.CENTER);
        contenedor.setPadding(new Insets(24));

        ventana.setTitle("Pizzas y bebidas");
        ventana.setScene(new Scene(contenedor, 560, 380));
        ventana.centerOnScreen();
        ventana.show();
    }

    public static void main(String[] args) {
        Application.launch(JavaFxCheckboxRadiobuttons.class, args);
    }
}
