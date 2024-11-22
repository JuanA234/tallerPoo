/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package edu.poo.persistencia;

import edu.poo.controlador.ControladorImagen;
import edu.poo.interfaz.DAO;
import edu.poo.modelo.Departamento;
import edu.poo.modelo.Pais;
import edu.poo.persistencia.DAOPais;
import edu.poo.recurso.dominio.Configuracion;
import edu.poo.recurso.dominio.Ruta;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import unimag.poo.persistencia.ArchivoPlanoNIO;

/**
 *
 * @author ASUS
 */
public class DAODepartamento implements DAO<Departamento> {

    private ArchivoPlanoNIO miArchivo;
    private String nombrePersistencia;
    private DAOPais miPais = new DAOPais();

    public DAODepartamento() {
        try {
            nombrePersistencia = Ruta.RUTA_PERSISTENCIA + "\\" + Configuracion.PERSISTENCIA_NOMBRE_DEPARTAMENTO;
            miArchivo = new ArchivoPlanoNIO(nombrePersistencia);
        } catch (IOException ex) {
            Logger.getLogger(DAODepartamento.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public boolean insertInto(Departamento miObject, String laRuta, String rutaDest) {
        String cadena;
        boolean correcto = false;
        try {

            String nocu = ControladorImagen.grabarImagen(laRuta);

            cadena = miObject.getCodDepartamento()
                    + Configuracion.SEPARADOR_COLUMNA
                    + miObject.getNombreDepartamento()
                    + Configuracion.SEPARADOR_COLUMNA
                    + miObject.getNombreImagenDepartamento()
                    + Configuracion.SEPARADOR_COLUMNA
                    + miObject.getObjPais().getCodPais()
                    + Configuracion.SEPARADOR_COLUMNA
                    + nocu;

            miArchivo.agregarRegistro(cadena);
            correcto = true;

        } catch (IOException ex) {
            Logger.getLogger(DAODepartamento.class.getName()).log(Level.SEVERE, null, ex);
        }
        return correcto;
    }

    @Override
    public List<Departamento> selectFrom() {
        int i, desde, cuente, limite, codigo;
        String cadena, nombre, imag, ocul;
        Pais pais;

        List<String> arregloDatos;
        List<Departamento> arregloDepartamentos = new ArrayList<>();

        try {
            arregloDatos = miArchivo.obtenerDatos();
            limite = arregloDatos.size();
            for (i = 0; i < limite; i++) {
                cadena = arregloDatos.get(i);

                // Obtener el código
                desde = 0;
                cuente = cadena.indexOf(Configuracion.SEPARADOR_COLUMNA, desde);
                codigo = Integer.parseInt(cadena.substring(desde, cuente).trim()); // El código es numérico

                // Obtener el nombre
                desde = cuente + 1;
                cuente = cadena.indexOf(Configuracion.SEPARADOR_COLUMNA, desde);
                nombre = cadena.substring(desde, cuente).trim();

                // Obtener el nombre de la imagen
                desde = cuente + 1;
                cuente = cadena.indexOf(Configuracion.SEPARADOR_COLUMNA, desde);
                imag = cadena.substring(desde, cuente).trim();

                // Obtener el pais
                desde = cuente + 1;
                cuente = cadena.indexOf(Configuracion.SEPARADOR_COLUMNA, desde);
                pais = miPais.getOne(Integer.parseInt(cadena.substring(desde, cuente).trim()));
                
                
                // Obtener el nombre oculto de la imagen
                desde = cuente + 1;
                cuente = cadena.length() - 1;
                ocul = cadena.substring(desde, cuente).trim();

                Departamento objDepartamento = new Departamento(codigo, nombre, imag, ocul, pais);
                arregloDepartamentos.add(objDepartamento);

            }
        } catch (IOException ex) {
            Logger.getLogger(DAODepartamento.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arregloDepartamentos;
    }

    @Override
    public int getSerial() {
        int id = 0;
        try {
            id = miArchivo.ultimoCodigo() + 1;
        } catch (IOException ex) {
            Logger.getLogger(DAODepartamento.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }

    @Override
    public int numRows() {
        int cantidad = 0;
        try {
            cantidad = miArchivo.cantidadFilas();
        } catch (IOException ex) {
            Logger.getLogger(DAODepartamento.class.getName()).log(Level.SEVERE, null, ex);
        }
        return cantidad;
    }

    @Override
    public boolean deleteFrom(int principalKey) {
        boolean correcto = false;
        List<String> arregloDatos;
        try {

            arregloDatos = miArchivo.borrarFilaPosicion(principalKey);
            if (!arregloDatos.isEmpty()) {
                int pos = arregloDatos.size() - 1;
                String nomOculto = arregloDatos.get(pos);

                String nombreBorrar = Ruta.RUTA_PERSISTENCIA_FOTOS + "\\" + nomOculto;
                Path rutaBorrar = Paths.get(nombreBorrar);
                Files.deleteIfExists(rutaBorrar);
                correcto = true;
            }
        } catch (IOException ex) {
            Logger.getLogger(DAODepartamento.class.getName()).log(Level.SEVERE, null, ex);
        }
        return correcto;
    }

    @Override
    public boolean updateSet(int principalKey, Departamento miObject, String laRuta) {
        boolean correcto = false;
        try {
            String cadena, nomOcu;
            List<String> arregloDatos;

            cadena = miObject.getCodDepartamento()+ Configuracion.SEPARADOR_COLUMNA
                    + miObject.getNombreDepartamento()+ Configuracion.SEPARADOR_COLUMNA
                    + miObject.getNombreImagenDepartamento()+ Configuracion.SEPARADOR_COLUMNA
                    +miObject.getObjPais().getCodPais() + Configuracion.SEPARADOR_COLUMNA;
                   
            if (laRuta.isBlank()) {
                cadena = cadena + miObject.getNombreImagenOcultaDepartamento();
            } else {
                nomOcu = ControladorImagen.grabarImagen(laRuta);
                cadena = cadena + nomOcu;
                arregloDatos = miArchivo.borrarFilaPosicion(principalKey);
                if (!arregloDatos.isEmpty()) {
                    String nomOculto = arregloDatos.get(arregloDatos.size() - 1);
                    String nomBorrar = Ruta.RUTA_PERSISTENCIA_FOTOS + "\\" + nomOculto;
                    Path rutaBorrar = Paths.get(nomBorrar);
                    Files.deleteIfExists(rutaBorrar);
                }
            }
            if (miArchivo.actualizaFilaPosicion(principalKey, cadena)) {
                correcto = true;
            }
        } catch (IOException ex) {
            Logger.getLogger(DAODepartamento.class.getName()).log(Level.SEVERE, null, ex);
        }

        return correcto;
    }

    @Override
    public Departamento getOne(int principalKey) {
        Departamento objDepartamento = new Departamento();

        try {
            List<String> arrDatos = miArchivo.obtenerFila(principalKey - 1);
            objDepartamento = new Departamento(Integer.valueOf(arrDatos.get(0)),
                    arrDatos.get(1),
                    arrDatos.get(2),
                    arrDatos.get(4),
                   miPais.getOne(Integer.parseInt(arrDatos.get(3))));

        } catch (IOException ex) {
            Logger.getLogger(DAODepartamento.class.getName()).log(Level.SEVERE, null, ex);
        }
        return objDepartamento;
    }

}
