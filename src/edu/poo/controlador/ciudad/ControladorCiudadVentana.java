
package edu.poo.controlador.ciudad;

import edu.poo.modelo.Ciudad;
import edu.poo.persistencia.DAOCiudad;
import edu.poo.recurso.utilidad.Efecto;
import edu.poo.vista.ciudad.VistaCiudadCrear;
import edu.poo.vista.ciudad.VistaCiudadDetalle;
import edu.poo.vista.ciudad.VistaCiudadEditar;
import edu.poo.vista.ciudad.VistaCiudadListar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class ControladorCiudadVentana {
    public static StackPane ciudadCrear(double ancho, double alto) {
        VistaCiudadCrear ciudadCrearSub = new VistaCiudadCrear(ancho, alto);
        StackPane contenedor = ciudadCrearSub.getMiStackPane();
        Efecto.transicionX(contenedor, 1.5);
        return contenedor;
    }

    public static VBox ciudadListar(double ancho, double alto) {
        VistaCiudadListar ciudadListarSub = new VistaCiudadListar(ancho, alto);
        VBox contenedor = ciudadListarSub.getMiVbox();

        Efecto.transicionX(contenedor, 1.5);
        return contenedor;
    }
    
    
        public static BorderPane ciudadDetalle(BorderPane princ, Pane pan, double anchoPanel,
            double altoPanel, int ind) {
            VistaCiudadDetalle departamentoDetallesub = new VistaCiudadDetalle(princ, pan, anchoPanel, altoPanel, ind);
        BorderPane contenedor = departamentoDetallesub.getMiBorderPane();

        Efecto.transicionY(contenedor, 1.5);
        return contenedor;

    }

    public static StackPane ciudadEditar(BorderPane princ, Pane pane, double anchoPanel, double altoPanel, int indice) {
        DAOCiudad miDao = new DAOCiudad();
        Ciudad objCiudad = miDao.getOne(indice+1);

        VistaCiudadEditar ventani = new VistaCiudadEditar(princ, pane, anchoPanel, altoPanel, indice, objCiudad);

        StackPane contenedor = ventani.getMiStackPane();
        Efecto.transicionX(contenedor, 1.5);
        return contenedor;
    }
}
