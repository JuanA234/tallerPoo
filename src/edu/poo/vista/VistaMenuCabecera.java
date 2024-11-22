package edu.poo.vista;

import edu.poo.controlador.ControladorSalida;
import edu.poo.controlador.ciudad.ControladorCiudadVentana;
import edu.poo.controlador.departamento.ControladorDepartamentoVentana;
import edu.poo.controlador.pais.ControladorPaisVentana;
//import edu.poo.controlador.Ruta.ControladorRutaVentana;
//import edu.poo.controlador.Tipo.ControladorTipoVentana;
import edu.poo.recurso.dominio.Configuracion;
import edu.poo.recurso.dominio.Contenedor;
import edu.poo.recurso.dominio.Ruta;
import edu.poo.vista.acerca.VistaBotonAcerca;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.SubScene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class VistaMenuCabecera extends SubScene {

    private final int btnTamanioX = 120;
    private final int btnTamanioY = 20;
    private final int btnEspacioX = 20;

    private final VBox panelCabecera;
    private final HBox seccionArriba;
    private final HBox seccionIntermedia;
    private final HBox seccionAbajo;
    private Pane panelCuerpo;
    private final Stage miEscenario;
    private final BorderPane panelPrincipal;

    public VistaMenuCabecera(Stage esce, BorderPane princ, Pane pane,
            double anchoPanel, double altoPanel) {
        super(new VBox(), anchoPanel, altoPanel);
        panelCabecera = (VBox) this.getRoot();

        miEscenario = esce;
        panelPrincipal = princ;
        panelCuerpo = pane;
        seccionArriba = new HBox(panelCabecera.getHeight() / 3);
        seccionIntermedia = new HBox(panelCabecera.getHeight() / 3);
        seccionAbajo = new HBox(panelCabecera.getHeight() / 3);

        panelCabecera.setSpacing(btnEspacioX);
        panelCabecera.setPadding(new Insets(15, 10, 15, 10));
        panelCabecera.setPrefHeight(Contenedor.ALTO_CABECERA.getValor());
        panelCabecera.setStyle(Configuracion.CABECERA_COLOR_FONDO);
        panelCabecera.getStylesheets().add(getClass().getResource(Ruta.RUTA_ESTILO_BOTON).toExternalForm());
        crearBotones();
        panelCabecera.getChildren().addAll(seccionArriba, seccionIntermedia, seccionAbajo);
    }

    private void crearBotones() {
        btnPaisCrear("Crear Paises");
        btnPaisListar("Ver Paises");
        btnPaisDetalle("Detalles paises");
        btnDepartamentoCrear("Crear Departamento");
        btnDepartamentoListar("Listar Departamento");
        btnDepartamentoDetalle("Detalles departamentos");
        btnCiudadCrear("Crear Ciudad");
        btnCiudadListar("Ver Ciudades");
        btnCiudadDetalle("Detalles ciudades");
        btnSalir("Salir");
        btnAcerca(Configuracion.ACERCA_ANCHO, Configuracion.ACERCA_ALTO);
    }

    private void agregarBtnCabeceraTop(Button boton) {
        boton.setCursor(Cursor.HAND);
        boton.setPrefSize(btnTamanioX, btnTamanioY);
        seccionArriba.getChildren().add(boton);

    }

    private void agregarBtnCabeceraCenter(Button boton) {
        boton.setCursor(Cursor.HAND);
        boton.setPrefSize(btnTamanioX, btnTamanioY);
        seccionIntermedia.getChildren().add(boton);

    }

    private void agregarBtnCabeceraBottom(Button boton) {
        boton.setCursor(Cursor.HAND);
        boton.setPrefSize(btnTamanioX, btnTamanioY);
        seccionAbajo.getChildren().add(boton);

    }

    private void btnPaisCrear(String titulo) {
        Button btnCrear = new Button(titulo);
        btnCrear.setOnAction((t) -> {
            panelCuerpo = ControladorPaisVentana.paisCrear(
                    Configuracion.ANCHO_APP, Contenedor.ALTO_CUERPO.getValor());
            panelPrincipal.setCenter(null);
            panelPrincipal.setCenter(panelCuerpo);
        });

        agregarBtnCabeceraTop(btnCrear);
    }

    private void btnPaisListar(String titulo) {
        Button btnListar = new Button(titulo);
        btnListar.setOnAction((t) -> {
            panelCuerpo = ControladorPaisVentana.paisListar(
                    Configuracion.ANCHO_APP, Contenedor.ALTO_CUERPO.getValor());
            panelPrincipal.setCenter(null);
            panelPrincipal.setCenter(panelCuerpo);
        });
        agregarBtnCabeceraTop(btnListar);
    }

    private void btnDepartamentoCrear(String titulo) {
        Button btnCrear = new Button(titulo);
        btnCrear.setOnAction((t) -> {
            panelCuerpo = ControladorDepartamentoVentana.departamentoCrear(
                    Configuracion.ANCHO_APP, Contenedor.ALTO_CUERPO.getValor());
            panelPrincipal.setCenter(null);
            panelPrincipal.setCenter(panelCuerpo);
        });

        agregarBtnCabeceraCenter(btnCrear);
    }

    private void btnDepartamentoListar(String titulo) {
        Button btnListar = new Button(titulo);
        btnListar.setOnAction((t) -> {
            panelCuerpo = ControladorDepartamentoVentana.departamentoListar(
                    Configuracion.ANCHO_APP, Contenedor.ALTO_CUERPO.getValor());
            panelPrincipal.setCenter(null);
            panelPrincipal.setCenter(panelCuerpo);
        });
        agregarBtnCabeceraCenter(btnListar);
    }

    private void btnCiudadCrear(String titulo) {
        Button btnCrear = new Button(titulo);
        btnCrear.setOnAction((t) -> {
            panelCuerpo = ControladorCiudadVentana.ciudadCrear(
                    Configuracion.ANCHO_APP, Contenedor.ALTO_CUERPO.getValor());
            panelPrincipal.setCenter(null);
            panelPrincipal.setCenter(panelCuerpo);
        });

        agregarBtnCabeceraBottom(btnCrear);
    }

    private void btnCiudadListar(String titulo) {
        Button btnListar = new Button(titulo);
        btnListar.setOnAction((t) -> {
            panelCuerpo = ControladorCiudadVentana.ciudadListar(
                    Configuracion.ANCHO_APP, Contenedor.ALTO_CUERPO.getValor());
            panelPrincipal.setCenter(null);
            panelPrincipal.setCenter(panelCuerpo);
        });
        agregarBtnCabeceraBottom(btnListar);
    }

    private void btnPaisDetalle(String texto) {
        int indice = 0;
        Button btnDetalle = new Button(texto);
        btnDetalle.setOnAction((ActionEvent t) -> {
            panelCuerpo = ControladorPaisVentana.paisDetalle(panelPrincipal, panelCuerpo, 
                    Configuracion.ANCHO_APP, Contenedor.ALTO_CUERPO.getValor(), indice);
            panelPrincipal.setCenter(null);
            panelPrincipal.setCenter(panelCuerpo);
        });
        agregarBtnCabeceraTop(btnDetalle);
    }
    private void btnDepartamentoDetalle(String texto) {
        int indice = 0;
        Button btnDetalle = new Button(texto);
        btnDetalle.setOnAction((ActionEvent t) -> {
            panelCuerpo = ControladorDepartamentoVentana.departamentoDetalle(panelPrincipal, panelCuerpo, 
                    Configuracion.ANCHO_APP, Contenedor.ALTO_CUERPO.getValor(), indice);
            panelPrincipal.setCenter(null);
            panelPrincipal.setCenter(panelCuerpo);
        });
        agregarBtnCabeceraCenter(btnDetalle);
    }
    private void btnCiudadDetalle(String texto) {
        int indice = 0;
        Button btnDetalle = new Button(texto);
        btnDetalle.setOnAction((ActionEvent t) -> {
            panelCuerpo = ControladorCiudadVentana.ciudadDetalle(panelPrincipal, panelCuerpo, 
                    Configuracion.ANCHO_APP, Contenedor.ALTO_CUERPO.getValor(), indice);
            panelPrincipal.setCenter(null);
            panelPrincipal.setCenter(panelCuerpo);
        });
        agregarBtnCabeceraBottom(btnDetalle);
    }

    private void btnAcerca(Double anchito, Double altito) {
        VistaBotonAcerca btnAcerca = new VistaBotonAcerca(anchito, altito);
        StackPane panelIcono = btnAcerca.obtener();
        seccionAbajo.getChildren().add(panelIcono);
        VBox.setVgrow(panelIcono, Priority.ALWAYS);

    }

    private void btnSalir(String titulo) {
        Button btnSalir = new Button(titulo);
        btnSalir.setOnAction((t) -> {
            ControladorSalida.verificar(miEscenario);
        });
        agregarBtnCabeceraBottom(btnSalir);
    }

    public VBox getPanelCabecera() {
        return panelCabecera;
    }
}
