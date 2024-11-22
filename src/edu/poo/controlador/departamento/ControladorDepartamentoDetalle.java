package edu.poo.controlador.departamento;

import java.util.logging.Level;
import java.util.logging.Logger;
import edu.poo.modelo.Departamento;
import edu.poo.modelo.Pais;
import edu.poo.persistencia.DAODepartamento;
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

public class ControladorDepartamentoDetalle {

    private static Integer cambiarIndice(String opcion, Integer indice, int numDepartamentos) {
        Integer nuevoIndice, limite;

        nuevoIndice = indice;
        limite = numDepartamentos - 1;
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
            Label lblContador, Label lblPais, Label lblNombreImagenPais, ArrayList<ImageView> imagenMostrar, double anchoPanel, double altoPanel) {
        //inicio metood

        DAODepartamento miDAODepartamento = new DAODepartamento();
        List<Departamento> arregloDepartamentos = miDAODepartamento.selectFrom();
        int numDepartamentos = arregloDepartamentos.size();

        if (numDepartamentos > 0) {
            indice = cambiarIndice(sentido, indice, numDepartamentos);
            Pais pais = arregloDepartamentos.get(indice).getObjPais();
            lblNombre.setText(arregloDepartamentos.get(indice).getNombreDepartamento());
            lblNombreImagen.setText(arregloDepartamentos.get(indice).getNombreImagenDepartamento());
            lblContador.setText((indice + 1) + "/" + numDepartamentos);
            lblPais.setText(pais.getNombrePais());
            lblNombreImagenPais.setText(pais.getNombreImagenPais());

            try {
                String rutaPersistencia = Ruta.RUTA_PERSISTENCIA_FOTOS + "\\";
                String rutaImagenDepartamento = rutaPersistencia + arregloDepartamentos.get(indice).getNombreImagenOcultaDepartamento();
                String rutaImagenPais = rutaPersistencia + pais.getNombreImagenOcultaPais();

                Path imgDepartamento = Paths.get(rutaImagenDepartamento);
                InputStream streamImagen = Files.newInputStream(imgDepartamento);
                Image imgTemporal = new Image(streamImagen);

                double ajusteAncho = anchoPanel - (anchoPanel * 0.6);
                double ajusteAlto = altoPanel - (altoPanel * 0.5);

                imagenMostrar.get(0).setFitWidth(ajusteAncho);
                imagenMostrar.get(0).setFitHeight(ajusteAlto);
                imagenMostrar.get(0).setPreserveRatio(true);
                imagenMostrar.get(0).setImage(imgTemporal);

                Path imgPais = Paths.get(rutaImagenPais);
                streamImagen = Files.newInputStream(imgPais);
                imgTemporal = new Image(streamImagen);

                imagenMostrar.get(1).setFitWidth(ajusteAncho);
                imagenMostrar.get(1).setFitHeight(ajusteAlto);
                imagenMostrar.get(1).setPreserveRatio(true);
                imagenMostrar.get(1).setImage(imgTemporal);

            } catch (IOException ex) {
                Logger.getLogger(ControladorDepartamentoDetalle.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            lblNombre.setText("No hay registro");
            lblNombreImagen.setText("");
            lblContador.setText("0/0");
            lblPais.setText("no hay registro");
            lblNombreImagenPais.setText("");

            imagenMostrar.get(0).setImage(null);
            imagenMostrar.get(1).setImage(null);
        }
        return indice;
    }
}
