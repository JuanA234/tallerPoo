
package edu.poo.controlador.pais;

import edu.poo.modelo.Pais;
import edu.poo.persistencia.DAOPais;
import edu.poo.recurso.dominio.Ruta;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class ControladorPaisDetalle {
    private static Integer cambiarIndice(String opcion, Integer indice, int numPaises){
        Integer nuevoIndice, limite;
        
        nuevoIndice = indice;
        limite = numPaises -1;
        switch (opcion) {
            case "anterior"->{
                if(indice.equals(0)){
                    nuevoIndice = limite;
                } else{
                    nuevoIndice = indice-1;
                }
            }
            case "siguiente" ->{
                if(indice.equals(limite)){
                    nuevoIndice = 0;
                } else{
                    nuevoIndice = indice +1;
                }
            }
              
        }
        return nuevoIndice;
    }
    
    public static Integer rotar(String sentido, Integer indice, Label lblNombre, Label lblNombreImagen, 
        Label lblContador, ImageView imagenMostrar, double anchoPanel, double altoPanel){
    //inicio metood
    
        DAOPais miDAOPais = new DAOPais();
        List<Pais> arregloPaises = miDAOPais.selectFrom();
        int numPaises = arregloPaises.size();
        if(numPaises > 0){
            indice = cambiarIndice(sentido, indice, numPaises);
            
            lblNombre.setText(arregloPaises.get(indice).getNombrePais());
            lblNombreImagen.setText(arregloPaises.get(indice).getNombreImagenPais());
            lblContador.setText((indice +1)+ "/" +numPaises);
            

            try {
                String rutaPersistencia = Ruta.RUTA_PERSISTENCIA_FOTOS + "\\";
                String rutaImagen = rutaPersistencia + arregloPaises.get(indice).getNombreImagenOcultaPais();
                
                Path imgCarro = Paths.get(rutaImagen);
                InputStream streamImagen;
                streamImagen = Files.newInputStream(imgCarro);
                Image imgTemporal = new Image(streamImagen);
                
                double ajusteAncho = anchoPanel - (anchoPanel * 0.6);
                double ajusteAlto = altoPanel - (altoPanel * 0.5);
                imagenMostrar.setFitWidth(ajusteAncho);
                imagenMostrar.setFitHeight(ajusteAlto);
                imagenMostrar.setPreserveRatio(true);
                imagenMostrar.setImage(imgTemporal);
                
                
            } catch (IOException ex) {
                Logger.getLogger(ControladorPaisDetalle.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            lblNombre.setText("No hay registro");
            lblNombreImagen.setText("");
            lblContador.setText("0/0");
            imagenMostrar.setImage(null);                      
        }
        return indice;
    }  
}
