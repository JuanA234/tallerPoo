package edu.poo.controlador.ciudad;

import edu.poo.modelo.Ciudad;
import edu.poo.modelo.Departamento;
import edu.poo.persistencia.DAOCiudad;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ControladorCiudad {

    public static boolean grabar(String nombre, String imagCiudad, String rutaCiudad, Departamento departamento) {
        boolean correcto = false;

        DAOCiudad miDao = new DAOCiudad();
        int codigo = miDao.getSerial();
        Ciudad miCiudad = new Ciudad(codigo, nombre, imagCiudad, rutaCiudad, departamento);
        if (miDao.insertInto(miCiudad, rutaCiudad, "dkasljas")) {
            correcto = true;
        }
        return correcto;
    }

    public static ObservableList<Ciudad> cargar() {
        DAOCiudad miDao = new DAOCiudad();

        List<Ciudad> arreglo = miDao.selectFrom();

        ObservableList<Ciudad> datosTabla = FXCollections.observableList(arreglo);

        return datosTabla;
    }

    public static boolean eliminar(int indice) {
        boolean correcto;
        DAOCiudad miDao = new DAOCiudad();
        correcto = miDao.deleteFrom(indice);
        return correcto;
    }

    public static int obtenerCantidadDeCiudades() {
        DAOCiudad miDao = new DAOCiudad();
        return miDao.numRows();
    }
    
        public static boolean actualizar(int indice, Ciudad laCiudad, String laRuta){
        DAOCiudad miDao = new DAOCiudad();
        return miDao.updateSet(indice, laCiudad, laRuta);
    }
}
