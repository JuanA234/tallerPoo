package edu.poo.modelo;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class Departamento {

    private IntegerProperty codDepartamento;
    private StringProperty nombreDepartamento;
    private StringProperty nombreImagenDepartamento;
    private StringProperty nombreImagenOcultaDepartamento;
    private Pais objPais;

    public Departamento() {
    }

    public Departamento(Integer cod, String nombre, String img, String ocul, Pais obj) {
        armar(cod, nombre, img, ocul, obj);
    }

    private void armar(Integer cod, String nombre, String img, String ocul, Pais obj) {
        setCodDepartamento(cod);
        setNombreImagenDepartamento(img);
        setNombreImagenOcultaDepartamento(ocul);
        setNombreDepartamento(nombre);
        setObjPais(obj);
    }

    //Codigo
    public IntegerProperty propiedadCodigo() {
        if (this.codDepartamento == null) {
            this.codDepartamento = new SimpleIntegerProperty(this, "codDepartamento");
        }
        return this.codDepartamento;
    }

    public Integer getCodDepartamento() {
        return propiedadCodigo().get();
    }

    public void setCodDepartamento(Integer codDepartamento) {
        this.propiedadCodigo().set(codDepartamento);
    }

    //nombre
    public StringProperty propiedadNombre() {
        if (this.nombreDepartamento == null) {
            this.nombreDepartamento = new SimpleStringProperty(this, "nombreDepartamento");
        }
        return this.nombreDepartamento;
    }

    public String getNombreDepartamento() {
        return propiedadNombre().get();
    }

    public void setNombreDepartamento(String nombreDepartamento) {
        this.propiedadNombre().set(nombreDepartamento);
    }

    //nombreImagen
    public StringProperty propiedadNombreImagenCiudad() {
        if (this.nombreImagenDepartamento == null) {
            this.nombreImagenDepartamento = new SimpleStringProperty(this, "nombreImagenDepartamento");
        }
        return this.nombreImagenDepartamento;
    }

    public String getNombreImagenDepartamento() {
        return propiedadNombreImagenCiudad().get();
    }

    public void setNombreImagenDepartamento(String nombreImagenDepartamento) {
        this.propiedadNombreImagenCiudad().set(nombreImagenDepartamento);
    }

    //nombreImagenOculta
    public StringProperty propiedadNombreImagenOcultaCiudad() {
        if (this.nombreImagenOcultaDepartamento == null) {
            this.nombreImagenOcultaDepartamento = new SimpleStringProperty(this, "nombreImagenOcultaDepartamento");
        }
        return this.nombreImagenOcultaDepartamento;
    }

    public String getNombreImagenOcultaDepartamento() {
        return propiedadNombreImagenOcultaCiudad().get();
    }

    public void setNombreImagenOcultaDepartamento(String nombreImagenOcultaDepartamento) {
        this.propiedadNombreImagenOcultaCiudad().set(nombreImagenOcultaDepartamento);
    }

    public Pais getObjPais() {
        return objPais;
    }

    public void setObjPais(Pais objPais) {
        this.objPais = objPais;
    }

}
