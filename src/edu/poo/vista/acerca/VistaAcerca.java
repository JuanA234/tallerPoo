package edu.poo.vista.acerca;

import edu.poo.recurso.dominio.Ruta;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class VistaAcerca {
    private final static String LBL_TEXT = "#E8E8E8";
    private final static String ACERCA_FOTO = "fotico.jpeg";
    private final static String ACERCA_CORREO = "jaavendanol@unimagdalena.edu.co";
    private final static String COLOR_FONDO = "-fx-background-color: #4999af";
    
    public static void mostrar(double anchoPanel, double altoPanel){
        Stage nuevoEscenario = new Stage();
        String ruta = Ruta.RUTA_IMAGENES + ACERCA_FOTO;
        
        VBox miPanel = new VBox(6);
        miPanel.setAlignment(Pos.CENTER);
        miPanel.setPadding(new Insets(10, 0, 0, 0));
        miPanel.setStyle(COLOR_FONDO);
        
        Image miImagen = new Image(ruta);
        ImageView foto = new ImageView(miImagen);
        foto.setFitWidth(200);
        foto.setPreserveRatio(true);
        
        Font fuente = Font.font("Verdana", FontWeight.BOLD, 12);
        Label lblCorreo = new Label(ACERCA_CORREO);
        lblCorreo.setTextFill(Color.web(LBL_TEXT));
        lblCorreo.setFont(fuente);
        
        Button btnCerrar = new Button("Aceptar");
        btnCerrar.setPrefWidth(160);
        btnCerrar.setOnAction(event ->nuevoEscenario.close());
        
        miPanel.getChildren().addAll(foto, lblCorreo, btnCerrar);
        Scene nuevaEscena = new Scene(miPanel, anchoPanel, altoPanel);
        
        nuevoEscenario.setScene(nuevaEscena);
        nuevoEscenario.initStyle(StageStyle.UTILITY);
        nuevoEscenario.initModality(Modality.APPLICATION_MODAL);
        
        nuevoEscenario.setTitle("Acerca de mi");
        nuevoEscenario.show();   
    }
}
