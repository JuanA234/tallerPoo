package edu.poo.vista.pais;

import edu.poo.controlador.pais.ControladorPais;
import edu.poo.modelo.Pais;
import edu.poo.recurso.dominio.Ruta;
import edu.poo.recurso.utilidad.Fondo;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.SubScene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Background;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Line;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

public class VistaPaisListar extends SubScene {

    private final VBox miVbox;
    private final String centrar
            = "-fx-alignment: CENTER";
    private final String centrarIzq
            = "-fx-alignment: CENTER-LEFT";
    private TableView<Pais> miTabla;

    public VistaPaisListar(double ancho, double alto) {

        super(new VBox(), ancho, alto);
        miVbox = (VBox) this.getRoot();

        Background fondo = Fondo.asignar("", ancho, alto);
        miVbox.setBackground(fondo);
        miVbox.setTranslateX(-(ancho) - (ancho * 0.2));

        miTabla = new TableView<>();
        miTabla.getStylesheets().add(getClass().getResource(Ruta.RUTA_ESTILO_TABLA).toExternalForm());
        armarTabla(ancho, alto);
    }

    private void armarTabla(double ancho, double alto) {
        Text titulo = new Text("Lista de Paises");
        titulo.setFont(Font.font("Tahoma", FontWeight.BOLD, 25));
        titulo.setFill(Color.web("#ffffff"));

        Line underline = new Line();
        underline.setEndX(100);
        underline.setStroke(Color.GREENYELLOW); // Color de la línea
        underline.setStrokeWidth(3);

        TableColumn<Pais, Integer> colId = new TableColumn<>("Codigo");
        colId.setCellValueFactory(new PropertyValueFactory<>("codPais"));
        colId.prefWidthProperty().bind(miTabla.widthProperty().multiply(0.10));
        colId.setStyle(centrar);

        TableColumn<Pais, String> colNombre = new TableColumn<>("Nombre");
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombrePais"));
        colNombre.prefWidthProperty().bind(miTabla.widthProperty().multiply(0.15));
        colNombre.setStyle(centrarIzq);

        TableColumn<Pais, String> colImagen = new TableColumn<>("Imagen");
        colImagen.setCellValueFactory(new PropertyValueFactory<>("nombreImagenPais"));
        colImagen.prefWidthProperty().bind(miTabla.widthProperty().multiply(0.15));
        colImagen.setStyle(centrarIzq);


        miTabla.getColumns().add(colId);
        miTabla.getColumns().add(colNombre);
        miTabla.getColumns().add(colImagen);
        double nuevoAncho = ancho - (ancho * 0.1);
        double nuevoAlto = alto - (alto * 0.2);

        miTabla.setMaxWidth(nuevoAncho);
        miTabla.setMaxHeight(nuevoAlto);

        ObservableList<Pais> datosCompletos = ControladorPais.cargar();
        miTabla.setItems(datosCompletos);
        miTabla.refresh();

        miVbox.setSpacing(15);
        miVbox.setAlignment(Pos.TOP_CENTER);
        miVbox.setPadding(new Insets(20, 0, 0, 0));
        miVbox.getChildren().addAll(titulo, underline, miTabla);
    }

    public VBox getMiVbox() {
        return miVbox;
    }

}
