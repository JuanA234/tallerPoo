package edu.poo.recurso.dominio;

public class Ruta {
    //rutas app
    public final static String RUTA_APP = System.getProperty("user.dir");
    public final static String RUTA_USER = System.getProperty("user.home");
    public final static String RUTA_IMAGENES = "/edu/poo/recurso/imagen/";
    
    //rutas para el estilo
    public final static String RUTA_ESTILO_BTN_ACERCA 
            = "/edu/poo/recurso/estilo/btnAcerca.css";
    
    public final static String RUTA_ESTILO_TEXTO 
            = "/edu/poo/recurso/estilo/CarroCrear.css";
    
      public final static String RUTA_ESTILO_BOTON  = "/edu/poo/recurso/estilo/boton.css";
      public final static String RUTA_ESTILO_TABLA  = "/edu/poo/recurso/estilo/tabla_1.css";
    
    public final static String RUTA_PERSISTENCIA = 
            RUTA_APP + "\\" + "baseDatos";
    public final static String RUTA_PERSISTENCIA_FOTOS = 
            RUTA_PERSISTENCIA + "\\" + "meteFotos";
    
    
    
}
