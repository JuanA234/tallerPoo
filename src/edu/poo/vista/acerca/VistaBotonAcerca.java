package edu.poo.vista.acerca;

import edu.poo.recurso.dominio.Ruta;
import javafx.scene.SubScene;
import javafx.scene.control.Button;
import javafx.scene.layout.Background;
import javafx.scene.layout.StackPane;

public class VistaBotonAcerca extends SubScene{
    private final double anchoFlotante;
    private final double altoFlotante;
    
    private final Button botonAcerca;
    private StackPane panelIcono;

    public VistaBotonAcerca() {
        super(new StackPane(), 0, 0);
        this.anchoFlotante = 0;
        this.altoFlotante = 0; 
        botonAcerca = new Button("?");
       
    }

    public VistaBotonAcerca(double anchoFlotante, double altoFlotante) {
        super(new StackPane(),0, 0);
        this.anchoFlotante = anchoFlotante;
        this.altoFlotante = altoFlotante;
        
        panelIcono = (StackPane) this.getRoot();
        panelIcono.setBackground(Background.EMPTY);
        botonAcerca = new Button("?");
        
        botonClic();
    }

    private void botonClic() {
        botonAcerca.setOnAction(event -> {VistaAcerca.mostrar(anchoFlotante, altoFlotante);});
    }
    
    public StackPane obtener(){
        botonAcerca.setPrefWidth(30);
        botonAcerca.setId("btn-ayuda");
        botonAcerca.getStylesheets().add(getClass().
                getResource(Ruta.RUTA_ESTILO_BTN_ACERCA).toExternalForm());
        
        panelIcono.getChildren().add(botonAcerca);
        return panelIcono;
    }
    
    
    
}
