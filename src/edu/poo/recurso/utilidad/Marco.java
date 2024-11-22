package edu.poo.recurso.utilidad;

import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Rectangle;

public class Marco {
    public static Rectangle crear(double ancho, double alto,
            Stop[] arregloColores, String colorBorde){
        Rectangle marco = new Rectangle(ancho, alto);
        marco.setArcWidth(30);
        marco.setArcHeight(30);
        marco.setFill(new LinearGradient(0,0,0,1, true, 
                CycleMethod.NO_CYCLE, arregloColores));
        marco.setStroke(Color.web(colorBorde));
        marco.setStrokeWidth(5);
        return marco;
    }
    
}
