package edu.poo.controlador.pais;

import edu.poo.modelo.Pais;
import edu.poo.persistencia.DAOPais;
import edu.poo.recurso.utilidad.Efecto;
import edu.poo.vista.pais.VistaPaisCrear;
import edu.poo.vista.pais.VistaPaisDetalle;
import edu.poo.vista.pais.VistaPaisEditar;
import edu.poo.vista.pais.VistaPaisListar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class ControladorPaisVentana {

    public static StackPane paisCrear(double ancho, double alto) {
        VistaPaisCrear paisCrearSub = new VistaPaisCrear(ancho, alto);
        StackPane contenedor = paisCrearSub.getMiStackPane();
        Efecto.transicionX(contenedor, 1.5);
        return contenedor;
    }

    public static VBox paisListar(double ancho, double alto) {
        VistaPaisListar paisListarSub = new VistaPaisListar(ancho, alto);
        VBox contenedor = paisListarSub.getMiVbox();

        Efecto.transicionX(contenedor, 1.5);
        return contenedor;
    }

    public static BorderPane paisDetalle(BorderPane princ, Pane pan, double anchoPanel,
            double altoPanel, int ind) {
        VistaPaisDetalle paisDetallesub = new VistaPaisDetalle(princ, pan, anchoPanel, altoPanel, ind);
        BorderPane contenedor = paisDetallesub.getMiBorderPane();

        Efecto.transicionY(contenedor, 1.5);
        return contenedor;

    }

    public static StackPane paisEditar(BorderPane princ, Pane pane, double anchoPanel, double altoPanel, int indice) {
        DAOPais miDao = new DAOPais();
        Pais objPais = miDao.getOne(indice+1);

        VistaPaisEditar ventani = new VistaPaisEditar(princ, pane, anchoPanel, altoPanel, indice, objPais);

        StackPane contenedor = ventani.getMiStackPane();
        Efecto.transicionX(contenedor, 1.5);
        return contenedor;
    }
}
