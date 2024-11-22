package edu.poo.vista.departamento;

import edu.poo.controlador.ControladorImagen;
import edu.poo.controlador.departamento.ControladorDepartamento;
import edu.poo.controlador.departamento.ControladorDepartamentoVentana;
import edu.poo.controlador.pais.ControladorPais;
import edu.poo.controlador.pais.ControladorPaisVentana;
import edu.poo.modelo.Departamento;
import edu.poo.modelo.Pais;
import edu.poo.recurso.dominio.Configuracion;
import edu.poo.recurso.dominio.Contenedor;
import edu.poo.recurso.dominio.Ruta;
import edu.poo.recurso.utilidad.Marco;
import edu.poo.recurso.utilidad.Mensaje;
import java.io.File;
import java.util.List;
import javafx.geometry.HPos;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.SubScene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Pane;
import javafx.scene.layout.Priority;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.paint.Stop;
import javafx.scene.shape.Line;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class VistaDepartamentoEditar extends SubScene {

    private final GridPane miGrid;
    private final StackPane miStackPane;

    private TextField cajaNombre;
    private TextField cajaImagenDepartamento;
    private ComboBox<String> paises;

    private String rutaImagenSeleccionada = "";
    private double nuevoAnchoMarco;
    private double nuevoAltoMarco;

    private Pane panelCuerpo;
    private BorderPane panelPrincipal;
    private Stage escenarioActual;
    private int indice;
    private Departamento objDepartamento;

    public VistaDepartamentoEditar(BorderPane princ,
            Pane pane,
            double ancho,
            double alto,
            int indi,
            Departamento objExterno) {

        super(new StackPane(), ancho, alto);
        miStackPane = (StackPane) this.getRoot();
        miStackPane.getStylesheets().add(getClass().getResource(Ruta.RUTA_ESTILO_TEXTO).
                toExternalForm());
        miStackPane.getStylesheets().add(getClass().getResource(Ruta.RUTA_ESTILO_BOTON).toExternalForm());
//        Background fondo = Fondo.asignar("fondoborroso.jpg", ancho, alto);
//        miStackPane.setBackground(fondo);
//        miStackPane.setStyle(Configuracion.CUERPO_COLOR_FONDO);

        Background background = new Background(new BackgroundFill(Color.TRANSPARENT, null, null));

        miStackPane.setBackground(background);
        nuevoAnchoMarco = ancho - (ancho * 0.25);
        nuevoAltoMarco = alto - (alto * 0.4);

        miGrid = new GridPane(nuevoAnchoMarco, nuevoAltoMarco);

        miStackPane.setTranslateX(ancho - (ancho * 0.2));
//        miGrid.setStyle("-fx-grid-lines-visible: true;");

        escenarioActual = null;
        panelPrincipal = princ;
        panelCuerpo = pane;
        indice = indi;
        objDepartamento = objExterno;
        organizarObjetos();
    }

    private void organizarObjetos() {
        String colorBorde = "#f0a64e";
        Stop[] arrColores = new Stop[]{ //            
            new Stop(0, Color.web("#272121")),};
        Rectangle marco = Marco.crear(nuevoAnchoMarco, nuevoAltoMarco, arrColores,
                colorBorde);
        miStackPane.getChildren().add(marco);
        miStackPane.getChildren().add(miGrid);

        miStackPane.setAlignment(Pos.TOP_CENTER);
        marco.setTranslateX(0);
        marco.setTranslateY(30);

        contruirFormulario();
    }

    private void contruirFormulario() {
        Stage centrado = null;

        miGrid.setAlignment(Pos.TOP_CENTER);
        miGrid.setPadding(new Insets(40, 0, 10, 0));
        miGrid.setHgap(10);
        miGrid.setVgap(10);

        double anchoGrilla = nuevoAnchoMarco - (nuevoAnchoMarco * 0.15);
        double columna1 = anchoGrilla * 0.25;
        double columna2 = anchoGrilla * 0.75;

        miGrid.getColumnConstraints().add(new ColumnConstraints(columna1));
        miGrid.getColumnConstraints().add(new ColumnConstraints(columna2));

        Text titulo = new Text("Actualizacion de departamentos");
        titulo.setFill(Color.web("#ffffff"));
        titulo.setFont(Font.font("Tahoma", FontWeight.BOLD, 20));
        //nodo, columna, fila, colSpan, rowSpan

        Line underline = new Line();
        underline.setEndX(100);
        underline.setStroke(Color.GREENYELLOW); // Color de la línea
        underline.setStrokeWidth(3);

        miGrid.add(titulo, 0, 0, 2, 1);
        miGrid.add(underline, 0, 1, 2, 1);

        // Ajustar el ancho de la línea al ancho del texto
        titulo.layoutBoundsProperty().addListener((obs, oldBounds, newBounds) -> {
            underline.setEndX(newBounds.getWidth());
        });

        GridPane.setHalignment(titulo, HPos.CENTER);
        GridPane.setHalignment(underline, HPos.CENTER);

        Label lblDepartamentos = new Label("Departamentos: ");
        lblDepartamentos.setFont(new Font("Tahoma", 15));
        lblDepartamentos.setTextFill(Color.WHITESMOKE);

        miGrid.add(lblDepartamentos, 0, 2);

        cajaNombre = new TextField(objDepartamento.getNombreDepartamento());
        miGrid.add(cajaNombre, 1, 2);

        Label lblImagenDepartamentos = new Label("Foto: ");
        lblImagenDepartamentos.setFont(new Font("Tahoma", 15));
        lblImagenDepartamentos.setTextFill(Color.WHITESMOKE);
        miGrid.add(lblImagenDepartamentos, 0, 3);

        Button btnSeleccionar = new Button("+");
        cajaImagenDepartamento = new TextField(objDepartamento.getNombreImagenDepartamento());
        cajaImagenDepartamento.setDisable(true);

        FileChooser objSeleccionar = crearSelectorImagen();
        btnSeleccionar.setOnAction((t) -> {
            rutaImagenSeleccionada = ControladorImagen.seleccionar(centrado, cajaImagenDepartamento, objSeleccionar);
            if (rutaImagenSeleccionada.isEmpty()) {
                Mensaje.modal(Alert.AlertType.WARNING, centrado, "Ey", "Agarra la foto");
            }
        });
        HBox.setHgrow(cajaImagenDepartamento, Priority.ALWAYS);
        HBox panelHorizontalImagen = new HBox(5);
        panelHorizontalImagen.setAlignment(Pos.BOTTOM_RIGHT);
        panelHorizontalImagen.getChildren().addAll(cajaImagenDepartamento, btnSeleccionar);

        miGrid.add(panelHorizontalImagen, 1, 3);

        Label lblPaises = new Label("Elija el país");
        lblPaises.setFont(new Font("Tahoma", 15));
        lblPaises.setTextFill(Color.WHITESMOKE);
        miGrid.add(lblPaises, 0, 4);

        List<Pais> misPaises = ControladorPais.cargar();

        paises = new ComboBox<>();
        paises.getItems().add("Selecciona el pais");
        for (int i = 0; i < misPaises.size(); i++) {
            paises.getItems().add(misPaises.get(i).getNombrePais());
        }
        paises.setValue(objDepartamento.getObjPais().getNombrePais());
        miGrid.add(paises, 1, 4);

        Button btnActualizar = new Button("Actualizar");
        Button btnRegresar = new Button("Regresar");
        btnActualizar.getStyleClass().add("button");
        btnRegresar.getStyleClass().add("button");

        btnActualizar.setOnAction((t) -> {
            if (cajasLlenas(cajaNombre, paises)) {
                objDepartamento.setNombreDepartamento(cajaNombre.getText());
                for (int i = 0; i < misPaises.size(); i++) {
                    Pais pais = misPaises.get(i);
                    if (pais.getNombrePais().equals(paises.getValue())) {
                        objDepartamento.setObjPais(pais);
                        break;
                    }
                }
                if (!rutaImagenSeleccionada.isEmpty()) {
                    objDepartamento.setNombreImagenDepartamento(cajaImagenDepartamento.getText());
                }
                if (ControladorDepartamento.actualizar(indice, objDepartamento, rutaImagenSeleccionada)) {
                    cajaNombre.setText("");
                    cajaNombre.requestFocus();
                    Mensaje.modal(Alert.AlertType.INFORMATION, null, "Actualizado Exitosamente",
                            "La información se actualizado correctamente");
                } else {
                    Mensaje.modal(Alert.AlertType.ERROR, null, "Error",
                            "No se pudo actualizar la información");
                }
            }

        }
        );

        btnRegresar.setOnAction((t) -> {
            panelCuerpo = ControladorDepartamentoVentana.departamentoDetalle(panelPrincipal, panelCuerpo, Configuracion.ANCHO_APP,
                    Contenedor.ALTO_CUERPO.getValor(), indice);
            panelPrincipal.setCenter(null);
            panelPrincipal.setCenter(panelCuerpo);
        });

        HBox panelHachito = new HBox(20);
        panelHachito.setAlignment(Pos.BASELINE_RIGHT);
        panelHachito.getChildren().addAll(btnActualizar, btnRegresar);

        miGrid.add(panelHachito, 1, 8);

    }

    private boolean cajasLlenas(TextField caja1, ComboBox<String> combo) {
        boolean siga = false;
        if (caja1.getText().isBlank()) {
            caja1.requestFocus();
            Mensaje.modal(Alert.AlertType.WARNING, null, "PAILAS", "Debe llenar todas las casillas");
        } else if (combo.getSelectionModel().isSelected(0)) {
            Mensaje.modal(Alert.AlertType.WARNING, null, "PAILAS", "Debes elegir un país");
        } else {
            siga = true;

        }
        return siga;
    }

    private FileChooser crearSelectorImagen() {
        File rutaInicial = new File(Ruta.RUTA_USER);
        if (!rutaInicial.exists()) {
            rutaInicial = new File(Ruta.RUTA_APP);
        }
        FileChooser objSeleccionar = new FileChooser();
        objSeleccionar.setTitle("agarra la imagen");
        objSeleccionar.setInitialDirectory(rutaInicial);

        objSeleccionar.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("imagenes", "*.png"),
                new FileChooser.ExtensionFilter("fotos", "*.jpg", "*.jpeg"));
        return objSeleccionar;
    }

    public StackPane getMiStackPane() {
        return miStackPane;
    }

}
