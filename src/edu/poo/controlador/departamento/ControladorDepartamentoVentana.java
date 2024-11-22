package edu.poo.controlador.departamento;

import edu.poo.modelo.Departamento;
import edu.poo.persistencia.DAODepartamento;
import edu.poo.recurso.utilidad.Efecto;
import edu.poo.vista.departamento.VistaDepartamentoCrear;
import edu.poo.vista.departamento.VistaDepartamentoDetalle;
import edu.poo.vista.departamento.VistaDepartamentoEditar;
import edu.poo.vista.departamento.VistaDepartamentoListar;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;

public class ControladorDepartamentoVentana {

    public static StackPane departamentoCrear(double ancho, double alto) {
        VistaDepartamentoCrear departamentoCrearSub = new VistaDepartamentoCrear(ancho, alto);
        StackPane contenedor = departamentoCrearSub.getMiStackPane();
        Efecto.transicionX(contenedor, 1.5);
        return contenedor;
    }

    public static VBox departamentoListar(double ancho, double alto) {
        VistaDepartamentoListar departamentoListarSub = new VistaDepartamentoListar(ancho, alto);
        VBox contenedor = departamentoListarSub.getMiVbox();

        Efecto.transicionX(contenedor, 1.5);
        return contenedor;
    }

    public static BorderPane departamentoDetalle(BorderPane princ, Pane pan, double anchoPanel,
            double altoPanel, int ind) {
        VistaDepartamentoDetalle departamentoDetallesub = new VistaDepartamentoDetalle(princ, pan, anchoPanel, altoPanel, ind);
        BorderPane contenedor = departamentoDetallesub.getMiBorderPane();

        Efecto.transicionY(contenedor, 1.5);
        return contenedor;

    }

    public static StackPane departamentoEditar(BorderPane princ, Pane pane, double anchoPanel, double altoPanel, int indice) {
        DAODepartamento miDao = new DAODepartamento();
        Departamento objDepartamento = miDao.getOne(indice+1);

        VistaDepartamentoEditar ventani = new VistaDepartamentoEditar(princ, pane, anchoPanel, altoPanel, indice, objDepartamento);

        StackPane contenedor = ventani.getMiStackPane();
        Efecto.transicionX(contenedor, 1.5);
        return contenedor;
    }
}
