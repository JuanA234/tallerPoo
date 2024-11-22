
package edu.poo.controlador;

import edu.poo.recurso.dominio.Ruta;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;


public class ControladorImagen {
    public static boolean esImagen(Path path) {
        boolean correcto = false;
        try {
            String tipo = Files.probeContentType(path);
            if (tipo!= null && (tipo.equalsIgnoreCase("image/png") || tipo.equalsIgnoreCase("image/jpeg") || 
                    tipo.equalsIgnoreCase("image/jpg"))) {
                correcto = true;
            }
        } catch (IOException ex) {
            Logger.getLogger(ControladorImagen.class.getName()).log(Level.SEVERE, null, ex);
        }
        return correcto;
    }

    public static String generarId() {
        UUID identificador = UUID.randomUUID();
        String cadena = identificador.toString();
        return cadena;
    }

    public static String seleccionar(Stage escenario, TextField cajaSalida, FileChooser objSeleccionar) {
        String rutaCompleta = "";
        File archivoSeleccionado = objSeleccionar.showOpenDialog(escenario);
        if (archivoSeleccionado != null) {
            rutaCompleta = archivoSeleccionado.getAbsolutePath();
            Path path = Paths.get(rutaCompleta);
            if (esImagen(path)) {
                cajaSalida.setText(path.getFileName().toString());
            } else {
                rutaCompleta = "";
                cajaSalida.setText("Error: Incorrecto");
            }
        }
        return rutaCompleta;
    }

    public static String grabarImagen(String rutaCompleta) {
        String nombreOculto = "";
        try {
            Path archivoOrigen = Paths.get(rutaCompleta);
            nombreOculto = generarId() + "_" + archivoOrigen.getFileName();
            String cadena = Ruta.RUTA_PERSISTENCIA_FOTOS + "\\" + nombreOculto;
            Path archivoDestino = Paths.get(cadena);
            Files.copy(archivoOrigen, archivoDestino);
        } catch (IOException ex) {
            Logger.getLogger(ControladorImagen.class.getName()).log(Level.SEVERE, null, ex);
        }
        return nombreOculto;
    }
}
