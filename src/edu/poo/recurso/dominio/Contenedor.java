package edu.poo.recurso.dominio;

public enum Contenedor {
//lo mas parecido a una cadena par valor
    
    ALTO_CABECERA(Configuracion.ALTO_APP * 
            Configuracion.CABECERA_ALTO_PORCENTAJE),
    ALTO_CUERPO(Configuracion.ALTO_APP -(Configuracion.ALTO_APP * 
            Configuracion.CABECERA_ALTO_PORCENTAJE));
    
    private final double valor;

    private Contenedor(double valor) {
        this.valor = valor;
    }

    public double getValor() {
        return valor;
    }
    
}
