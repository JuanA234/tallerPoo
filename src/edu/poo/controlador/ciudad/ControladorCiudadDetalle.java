
package edu.poo.controlador.ciudad;
import java.util.logging.Level;
import java.util.logging.Logger;
import edu.poo.modelo.Ciudad;
import edu.poo.modelo.Departamento;
import edu.poo.persistencia.DAOCiudad;
import edu.poo.recurso.dominio.Ruta;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;


public class ControladorCiudadDetalle {
     private static Integer cambiarIndice(String opcion, Integer indice, int numCiudades) {
        Integer nuevoIndice, limite;

        nuevoIndice = indice;
        limite = numCiudades - 1;
        switch (opcion) {
            case "anterior" -> {
                if (indice.equals(0)) {
                    nuevoIndice = limite;
                } else {
                    nuevoIndice = indice - 1;
                }
            }
            case "siguiente" -> {
                if (indice.equals(limite)) {
                    nuevoIndice = 0;
                } else {
                    nuevoIndice = indice + 1;
                }
            }

        }
        return nuevoIndice;
    }

    public static Integer rotar(String sentido, Integer indice, Label lblNombre, Label lblNombreImagen,
            Label lblContador, Label lblDepartamento, Label lblNombreImagenDepartamento, ArrayList<ImageView> imagenMostrar, double anchoPanel, double altoPanel) {
        //inicio metood

        DAOCiudad miDAOCiudad = new DAOCiudad();
        List<Ciudad> arregloCiudades = miDAOCiudad.selectFrom();
        int numDeCiudades = arregloCiudades.size();

        if (numDeCiudades > 0) {
            indice = cambiarIndice(sentido, indice, numDeCiudades);
            Departamento departamento = arregloCiudades.get(indice).getObjDepartamento();
            lblNombre.setText(arregloCiudades.get(indice).getNombreCiudad());
            lblNombreImagen.setText(arregloCiudades.get(indice).getNombreImagenCiudad());
            lblContador.setText((indice + 1) + "/" + numDeCiudades);
            lblDepartamento.setText(departamento.getNombreDepartamento());
            lblNombreImagenDepartamento.setText(departamento.getNombreImagenDepartamento());

            try {
                String rutaPersistencia = Ruta.RUTA_PERSISTENCIA_FOTOS + "\\";
                String rutaImagenCiudad = rutaPersistencia + arregloCiudades.get(indice).getNombreImagenOcultaCiudad();
                String rutaImagenDepartamento = rutaPersistencia + departamento.getNombreImagenOcultaDepartamento();

                Path imgCiudad = Paths.get(rutaImagenCiudad);
                InputStream streamImagen = Files.newInputStream(imgCiudad);
                Image imgTemporal = new Image(streamImagen);

                double ajusteAncho = anchoPanel - (anchoPanel * 0.6);
                double ajusteAlto = altoPanel - (altoPanel * 0.5);

                imagenMostrar.get(0).setFitWidth(ajusteAncho);
                imagenMostrar.get(0).setFitHeight(ajusteAlto);
                imagenMostrar.get(0).setPreserveRatio(true);
                imagenMostrar.get(0).setImage(imgTemporal);

                Path imgDepartamento = Paths.get(rutaImagenDepartamento);
                streamImagen = Files.newInputStream(imgDepartamento);
                imgTemporal = new Image(streamImagen);

                imagenMostrar.get(1).setFitWidth(ajusteAncho);
                imagenMostrar.get(1).setFitHeight(ajusteAlto);
                imagenMostrar.get(1).setPreserveRatio(true);
                imagenMostrar.get(1).setImage(imgTemporal);

            } catch (IOException ex) {
                Logger.getLogger(ControladorCiudadDetalle.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            lblNombre.setText("No hay registro");
            lblNombreImagen.setText("");
            lblContador.setText("0/0");
            lblDepartamento.setText("No hay registros");
            lblNombreImagenDepartamento.setText("");

            imagenMostrar.get(0).setImage(null);
            imagenMostrar.get(1).setImage(null);
        }
        return indice;
    }
}
