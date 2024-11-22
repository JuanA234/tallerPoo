
package edu.poo.controlador.departamento;

import edu.poo.modelo.Departamento;
import edu.poo.modelo.Pais;
import edu.poo.persistencia.DAODepartamento;
import edu.poo.persistencia.DAOPais;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;


public class ControladorDepartamento {
    public static boolean grabar(String nombre, String imagDepartamento, String rutaDepartamento, Pais pais){
        boolean correcto = false;
        
         DAODepartamento miDao = new DAODepartamento();
        int codigo = miDao.getSerial();
         Departamento miDepartamento = new Departamento(codigo, nombre, imagDepartamento, rutaDepartamento, pais);
        if(miDao.insertInto(miDepartamento, rutaDepartamento, "dkasljas")){
            correcto = true;
        }
        return correcto;
    }
    
    public static ObservableList<Departamento> cargar(){
        DAODepartamento miDao = new DAODepartamento();
        
        List<Departamento> arreglo = miDao.selectFrom();
        
        ObservableList<Departamento> datosTabla = FXCollections.observableList(arreglo);
        
        return datosTabla;
    }
    
    
     
    public static boolean eliminar(int indice){
        boolean correcto;
        DAODepartamento miDao = new DAODepartamento();
        correcto = miDao.deleteFrom(indice);
        return correcto;
    }
    
    public static int obtenerCantidadDeDepartamentos(){
        DAODepartamento miDao = new DAODepartamento();
        return miDao.numRows();
    }
    
        public static boolean actualizar(int indice, Departamento elDepartamento, String laRuta){
        DAODepartamento miDao = new DAODepartamento();
        return miDao.updateSet(indice, elDepartamento, laRuta);
    }
}
