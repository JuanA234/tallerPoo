package edu.poo.modelo;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Pais {

    private IntegerProperty codPais;
    private StringProperty nombrePais;
    private StringProperty nombreImagenPais;
    private StringProperty nombreImagenOcultaPais;

    public Pais() {
    }

    public Pais(Integer cod, String nombre, String img, String ocul) {
        armar(cod, nombre, img, ocul);
    }

    private void armar(Integer cod, String nombre, String img, String ocul) {
        setCodPais(cod);
        setNombrePais(nombre);
        setNombreImagenPais(img);
        setNombreImagenOcultaPais(ocul);

    }

    //Codigo
    public IntegerProperty propiedadCodigo() {
        if (this.codPais == null) {
            this.codPais = new SimpleIntegerProperty(this, "codPais");
        }
        return this.codPais;
    }

    public Integer getCodPais() {
        return propiedadCodigo().get();
    }

    public void setCodPais(Integer codPais) {
        this.propiedadCodigo().set(codPais);
    }

    //nombre
    public StringProperty propiedadNombre() {
        if (this.nombrePais == null) {
            this.nombrePais = new SimpleStringProperty(this, "nombrePais");
        }
        return this.nombrePais;
    }

    public String getNombrePais() {
        return propiedadNombre().get();
    }

    public void setNombrePais(String nombrePais) {
        this.propiedadNombre().set(nombrePais);
    }

    //nombreImagen
    public StringProperty propiedadNombreImagenCiudad() {
        if (this.nombreImagenPais == null) {
            this.nombreImagenPais = new SimpleStringProperty(this, "nombreImagenPais");
        }
        return this.nombreImagenPais;
    }

    public String getNombreImagenPais() {
        return propiedadNombreImagenCiudad().get();
    }

    public void setNombreImagenPais(String nombreImagenPais) {
        this.propiedadNombreImagenCiudad().set(nombreImagenPais);
    }

    //nombreImagenOculta
    public StringProperty propiedadNombreImagenOcultaCiudad() {
        if (this.nombreImagenOcultaPais == null) {
            this.nombreImagenOcultaPais = new SimpleStringProperty(this, "nombreImagenOcultaPais");
        }
        return this.nombreImagenOcultaPais;
    }

    public String getNombreImagenOcultaPais() {
        return propiedadNombreImagenOcultaCiudad().get();
    }

    public void setNombreImagenOcultaPais(String nombreImagenOcultaPais) {
        this.propiedadNombreImagenOcultaCiudad().set(nombreImagenOcultaPais);
    }

}
