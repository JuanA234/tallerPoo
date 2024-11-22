package edu.poo.recurso.utilidad;

import javafx.scene.control.Alert;
import javafx.stage.Window;

public class Mensaje {

    public static void modal(Alert.AlertType tipo,
            Window ventanaPadre, String titulo, String texto) {
        Alert mensaje = new Alert(tipo);
        mensaje.setTitle(titulo);
        mensaje.setHeaderText(null);
        mensaje.setContentText(texto);
        mensaje.initOwner(ventanaPadre);
        mensaje.show();
    }
}
