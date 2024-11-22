package edu.poo.modelo;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Ciudad {

    private IntegerProperty codCiudad;
    private StringProperty nombreCiudad;
    private StringProperty nombreImagenCiudad;
    private StringProperty nombreImagenOcultaCiudad;
    private Departamento objDepartamento;

    public Ciudad() {
    }

    public Ciudad(Integer cod, String nombre, String imag, String ocul ,Departamento obj) {
        armar(cod, nombre, obj, imag, ocul);
    }

    private void armar(Integer cod, String nombre, Departamento obj, String img, String ocul) {
        setCodCiudad(cod);
        setNombreCiudad(nombre);
        setNombreImagenCiudad(img);
        setNombreImagenOcultaCiudad(ocul);
        setObjDepartamento(obj);
    }

    public IntegerProperty propiedadCodigo() {
        if (this.codCiudad == null) {
            this.codCiudad = new SimpleIntegerProperty(this, "codCiudad");
        }
        return this.codCiudad;
    }

    public Integer getCodCiudad() {
        return propiedadCodigo().get();
    }

    public void setCodCiudad(Integer codCiudad) {
        this.propiedadCodigo().set(codCiudad);
    }

    //nombre
    public StringProperty propiedadNombre() {
        if (this.nombreCiudad == null) {
            this.nombreCiudad = new SimpleStringProperty(this, "nombreCiudad");
        }
        return this.nombreCiudad;
    }

    public String getNombreCiudad() {
        return propiedadNombre().get();
    }

    public void setNombreCiudad(String nombreCiudad) {
        this.propiedadNombre().set(nombreCiudad);
    }

       //nombreImagen
    public StringProperty propiedadNombreImagenCiudad(){
        if (this.nombreImagenCiudad  == null) {
            this.nombreImagenCiudad = new SimpleStringProperty(this, "nombreImagenCiudad");
        }
        return this.nombreImagenCiudad;
    }
    
    public String getNombreImagenCiudad() {
        return propiedadNombreImagenCiudad().get();
    }

    public void setNombreImagenCiudad(String nombreImagenCiudad) {
        this.propiedadNombreImagenCiudad().set(nombreImagenCiudad);
    }
    
    //nombreImagenOculta
    public StringProperty propiedadNombreImagenOcultaCiudad(){
        if (this.nombreImagenOcultaCiudad  == null) {
            this.nombreImagenOcultaCiudad = new SimpleStringProperty(this, "nombreImagenOcultaCiudad");
        }
        return this.nombreImagenOcultaCiudad;
    }

    public String getNombreImagenOcultaCiudad() {
        return propiedadNombreImagenOcultaCiudad().get();
    }
    
    

    public void setNombreImagenOcultaCiudad(String nombreImagenOcultaCiudad) {
        this.propiedadNombreImagenOcultaCiudad().set(nombreImagenOcultaCiudad);
    }
    
    public Departamento getObjDepartamento() {
        return objDepartamento;
    }

    public void setObjDepartamento(Departamento objDepartamento) {
        this.objDepartamento = objDepartamento;
    }

}
