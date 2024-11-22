package edu.poo.vista.departamento;

import edu.poo.controlador.departamento.ControladorDepartamento;
import edu.poo.controlador.departamento.ControladorDepartamentoDetalle;
import edu.poo.controlador.departamento.ControladorDepartamentoVentana;
import edu.poo.controlador.pais.ControladorPaisDetalle;
import edu.poo.recurso.dominio.Configuracion;
import edu.poo.recurso.dominio.Contenedor;
import edu.poo.recurso.dominio.Ruta;
import edu.poo.recurso.utilidad.Mensaje;
import java.util.ArrayList;
import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.SubScene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;

public class VistaDepartamentoDetalle extends SubScene {

    private final VBox miVBox;
    private final BorderPane miBorderPane;

    private Label lblNombre;
    private Label lblNombreImagen;
    private Label lblNombrePais;
    private Label lblNombreImagenPais;
    private ArrayList<ImageView> imagenMostrar;
    private Label lblContador;

    private Integer indice;

    private final Stage escenarioActual;

    private Pane panelCuerpo;
    private final BorderPane panelPrincipal;

    public VistaDepartamentoDetalle(BorderPane princ, Pane pan, double anchoPanel, double altoPanel, int ind) {
        super(new BorderPane(), anchoPanel, altoPanel);
        this.indice = ind;
//        Background fondo = Fondo.asignar("", anchoPanel, altoPanel);{
        Background background = new Background(new BackgroundFill(Color.TRANSPARENT, null, null));

        this.miBorderPane = (BorderPane) this.getRoot();
        this.miBorderPane.setBackground(background);
//        miBorderPane.getStylesheets().add(getClass().getResource(Ruta.RUTA_ESTILO_BOTON).toExternalForm());
        this.miVBox = new VBox();

        this.miBorderPane.setTranslateY(altoPanel);
        this.escenarioActual = null;
        this.panelPrincipal = princ;
        this.panelCuerpo = pan;

        crearObjetos(anchoPanel, altoPanel);
    }

    private void crearObjetos(double anchoPanel, double altoPanel) {
        String colorBorde = "#b3d9ff";
//        Stop[] arrColores = new Stop[]{
//            new Stop(0, Color.web("#336699")), new Stop(0.5, Color.web("#6c99c6")), new Stop(1, Color.web("#97bfe8"))};

        double nuevoAncho = anchoPanel - (anchoPanel * 0.25);
        double nuevoAlto = altoPanel - (altoPanel * 0.1);
//        Rectangle marco = Marco.crear(nuevoAncho, nuevoAlto, arrColores, colorBorde);
        Rectangle marco = new Rectangle(nuevoAncho, nuevoAlto);
        marco.setArcWidth(30);
        marco.setArcHeight(30);
        marco.setStroke(Color.web(colorBorde));
        marco.setStrokeWidth(5);
        marco.setOpacity(0);
        miBorderPane.getChildren().add(marco);
        miBorderPane.setTranslateY(nuevoAlto);
        marco.setTranslateX((anchoPanel - nuevoAncho) / 2);
        marco.setTranslateY((altoPanel - nuevoAlto) / 2);

        construirPanelIzquierdo(anchoPanel, altoPanel);
        construirPanelDerecho(anchoPanel, altoPanel);
        construirPanelCentral(anchoPanel, altoPanel);
    }

    private void construirPanelIzquierdo(double anchoPanel, double altoPanel) {
        Button btnAnterior = new Button("<--");
        btnAnterior.setCursor(Cursor.HAND);

        btnAnterior.setOnAction((t) -> {
            indice = ControladorDepartamentoDetalle.rotar("anterior", indice, lblNombre,
                    lblNombreImagen, lblContador, lblNombrePais, lblNombreImagenPais, imagenMostrar, anchoPanel, altoPanel);
        });

        AnchorPane panelIzquierdo = new AnchorPane();
        AnchorPane.setTopAnchor(btnAnterior, (altoPanel / 2));
        AnchorPane.setLeftAnchor(btnAnterior, ((anchoPanel * 0.15) / 2));
        panelIzquierdo.setPrefWidth(anchoPanel * 0.15);

        panelIzquierdo.getChildren().addAll(btnAnterior);
        miBorderPane.setLeft(panelIzquierdo);
    }

    private void construirPanelDerecho(double anchoPanel, double altoPanel) {
        Button btnSiguiente = new Button("-->");
        btnSiguiente.setCursor(Cursor.HAND);
        btnSiguiente.setOnAction((ActionEvent t) -> {
            indice = ControladorDepartamentoDetalle.rotar("siguiente", indice,
                    lblNombre, lblNombreImagen, lblContador, lblNombrePais, lblNombreImagenPais, imagenMostrar, anchoPanel, altoPanel);
        });
        AnchorPane panelDerecho = new AnchorPane();
        AnchorPane.setTopAnchor(btnSiguiente, (altoPanel / 2));
        AnchorPane.setLeftAnchor(btnSiguiente, ((anchoPanel * 0.15) / 2));
        panelDerecho.setPrefWidth(anchoPanel * 0.15);
        panelDerecho.getChildren().addAll(btnSiguiente);
        miBorderPane.setRight(panelDerecho);
    }

    private void construirPanelCentral(double anchoPanel, double altoPanel) {
        Font fuente;
        miVBox.setAlignment(Pos.TOP_CENTER);
        miVBox.setSpacing(10);
        miVBox.setPadding(new Insets(altoPanel - (altoPanel * 0.92), 0, 0, 0));

        // Panel horizontal boton borrar
        Button btnEliminar = new Button();
        btnEliminar.setPrefWidth(35);
        btnEliminar.setCursor(Cursor.HAND);
        String iconoBorrar = Ruta.RUTA_IMAGENES + Configuracion.ICONO_BORRAR;
        Image imgBorrar = new Image(getClass().getResourceAsStream(iconoBorrar));
        btnEliminar.setGraphic(new ImageView(imgBorrar));

        btnEliminar.setOnAction((t) -> {
            int cantidad = ControladorDepartamento.obtenerCantidadDeDepartamentos();
            if (cantidad > 0) {
                Alert msg = new Alert(Alert.AlertType.CONFIRMATION);
                msg.setTitle("telo advierto");
                msg.setHeaderText(null);
                msg.setContentText("Mano, ¿Estas seguro de que quieres borrar el departamento?");
                msg.initOwner(escenarioActual);
                if (msg.showAndWait().get() == ButtonType.OK) {
                    ControladorDepartamento.eliminar(indice);
                    indice = ControladorDepartamentoDetalle.rotar("anterior", indice, lblNombre,
                            lblNombreImagen, lblContador, lblNombrePais, lblNombreImagenPais, imagenMostrar,
                            anchoPanel, altoPanel);
                }
            } else {
                Mensaje.modal(Alert.AlertType.WARNING, escenarioActual, "Telo advierto",
                        "No puedes borrar departamentos porque no los hay");
            }
        });

        Button btnActualizar = new Button();
        btnActualizar.setPrefWidth(35);
        btnActualizar.setCursor(Cursor.HAND);
        String iconoEditar = Ruta.RUTA_IMAGENES + Configuracion.ICONO_EDITAR;
        Image imgEditar = new Image(getClass().getResourceAsStream(iconoEditar));
        btnActualizar.setGraphic(new ImageView(imgEditar));

        btnActualizar.setOnAction((t) -> {
            int cantidad = ControladorDepartamento.obtenerCantidadDeDepartamentos();
            if (cantidad > 0) {
                panelCuerpo = ControladorDepartamentoVentana.departamentoEditar(panelPrincipal, panelCuerpo,
                        Configuracion.ANCHO_APP, Contenedor.ALTO_CUERPO.getValor(), indice);
                panelPrincipal.setCenter(null);
                panelPrincipal.setCenter(panelCuerpo);
            } else {
                Mensaje.modal(Alert.AlertType.WARNING, escenarioActual, "Advertencia",
                        "No puedes actualizar registros, ya que no existe ninguno");
            }

        });
        HBox panelHorizontalBotones = new HBox(4);
        panelHorizontalBotones.setAlignment(Pos.CENTER);
        panelHorizontalBotones.getChildren().addAll(btnEliminar, btnActualizar);

        ///*******
        lblNombre = new Label();
        fuente = Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC, 15);
        lblNombre.setFont(fuente);
        lblNombre.setTextFill(Color.WHITESMOKE);

        lblNombrePais = new Label();
        fuente = Font.font("Verdana", FontWeight.BOLD, FontPosture.ITALIC, 15);
        lblNombrePais.setFont(fuente);
        lblNombrePais.setTextFill(Color.WHITESMOKE);

        HBox panelHorizontalNombre = new HBox(4);
        panelHorizontalNombre.setAlignment(Pos.CENTER);
        panelHorizontalNombre.getChildren().addAll(lblNombrePais, lblNombre);

        lblNombreImagen = new Label();
        lblNombreImagen.setTextFill(Color.YELLOWGREEN);
        fuente = Font.font("Verdana", 12);
        lblNombreImagen.setFont(fuente);

        lblNombreImagenPais = new Label();
        lblNombreImagenPais.setTextFill(Color.YELLOWGREEN);
        fuente = Font.font("Verdana", 12);
        lblNombreImagenPais.setFont(fuente);

        HBox panelHorizontalNombreImagen = new HBox(4);
        panelHorizontalNombreImagen.setAlignment(Pos.BOTTOM_CENTER);
        panelHorizontalNombreImagen.getChildren().addAll(lblNombreImagen, lblNombreImagenPais);

        lblContador = new Label();
        lblContador.setTextFill(Color.WHITESMOKE);
        fuente = Font.font("Verdana", 12);
        lblContador.setFont(fuente);

        HBox panelContador = new HBox(4);
        panelContador.setAlignment(Pos.BOTTOM_CENTER);
        panelContador.getChildren().add(lblContador);

        imagenMostrar = new ArrayList<>();
        imagenMostrar.add(new ImageView());
        imagenMostrar.add(new ImageView());

        ControladorDepartamentoDetalle.rotar("primero", indice, lblNombre,
                lblNombreImagen, lblContador, lblNombrePais, lblNombreImagenPais, imagenMostrar, anchoPanel, altoPanel);
        
         HBox panelHorizontaImagenes = new HBox(4);
        panelHorizontaImagenes.setAlignment(Pos.CENTER);
        panelHorizontaImagenes.getChildren().addAll(imagenMostrar.get(0), imagenMostrar.get(1));

     
        miVBox.getChildren().addAll(panelHorizontalBotones, panelHorizontalNombre, panelHorizontaImagenes,
                panelHorizontalNombreImagen, panelContador);
        miVBox.setPrefWidth(anchoPanel * 0.7);
        miBorderPane.setCenter(miVBox);
    }

    public BorderPane getMiBorderPane() {
        return miBorderPane;
    }

}
