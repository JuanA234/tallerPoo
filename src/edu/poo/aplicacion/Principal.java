package edu.poo.aplicacion;

import edu.poo.controlador.ControladorSalida;
import edu.poo.recurso.dominio.Configuracion;
import edu.poo.recurso.dominio.Ruta;
import edu.poo.vista.VistaAdmin;
import javafx.application.Application;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class Principal extends Application {

    private VistaAdmin adminVista;

    public Principal() {
    }

    public static void main(String[] args) {
        launch();
        
        // Y esa linea anterior para qué?
    }

    @Override
    public void start(Stage stage) throws Exception {
        String iconoApp = Ruta.RUTA_IMAGENES + Configuracion.ICONO_APP;
        Image iconito = new Image(getClass().getResourceAsStream(iconoApp));

        adminVista = new VistaAdmin();
        stage = adminVista.getMiEscenario();
        stage.setTitle("Aplicacion");
        stage.getIcons().add(iconito);
        stage.show();
        habilitaLaX(stage);
        //si eres pulido le mete lo de la X cerrar
    }

    private void habilitaLaX(Stage miEsce) {
        miEsce.setOnCloseRequest((event) -> {
            event.consume();
            ControladorSalida.verificar(miEsce);
        });
    }

}
