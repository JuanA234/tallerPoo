package edu.poo.recurso.utilidad;

import javafx.animation.TranslateTransition;
import javafx.scene.layout.Pane;
import javafx.util.Duration;

public class Efecto {
    public static void transicionX(Pane contenedor, double segundos){
        TranslateTransition cambio = new TranslateTransition();
        cambio.setDuration(Duration.seconds(segundos));
        cambio.setNode(contenedor);
        cambio.setToX(0);
        cambio.play();
    }
    
    public static void transicionY(Pane contenedor, double segundos){
        TranslateTransition cambio = new TranslateTransition();
        cambio.setDuration(Duration.seconds(segundos));
        cambio.setNode(contenedor);
        cambio.setToY(0);
        cambio.play();    
    }
}
