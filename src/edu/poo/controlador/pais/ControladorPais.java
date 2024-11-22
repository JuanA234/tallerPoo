package edu.poo.controlador.pais;

import edu.poo.modelo.Pais;
import edu.poo.persistencia.DAOPais;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class ControladorPais {

    public static boolean grabar(String nombre, String imagPais, String rutaPais) {
        boolean correcto = false;

        DAOPais miDao = new DAOPais();
        int codigo = miDao.getSerial();
        Pais miPais = new Pais(codigo, nombre, imagPais, rutaPais);
        if (miDao.insertInto(miPais, rutaPais, "dkasljas")) {
            correcto = true;
        }
        return correcto;
    }

    public static ObservableList<Pais> cargar() {
        DAOPais miDao = new DAOPais();

        List<Pais> arreglo = miDao.selectFrom();

        ObservableList<Pais> datosTabla = FXCollections.observableList(arreglo);

        return datosTabla;
    }

    public static boolean eliminar(int indice) {
        boolean correcto;
        DAOPais miDao = new DAOPais();
        correcto = miDao.deleteFrom(indice);
        return correcto;
    }

    public static int obtenerCantidadDePaises() {
        DAOPais miDao = new DAOPais();
        return miDao.numRows();
    }

    public static boolean actualizar(int indice, Pais elPais, String laRuta) {
        DAOPais miDao = new DAOPais();
        return miDao.updateSet(indice, elPais, laRuta);
    }
}
