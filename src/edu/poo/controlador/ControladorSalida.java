package edu.poo.controlador;

import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.stage.Stage;

public class ControladorSalida {
       public static void verificar(Stage stage){
        Alert mensaje = new Alert(Alert.AlertType.CONFIRMATION);
        mensaje.setTitle("Salir");
        mensaje.setHeaderText(null);
        mensaje.setContentText("¿Seguro que quieres salir?");
        mensaje.initOwner(stage);
        if ( mensaje.showAndWait().get() == ButtonType.OK ) {
            stage.close();
        }
    }
}
