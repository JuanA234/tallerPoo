package edu.poo.persistencia;

import edu.poo.controlador.ControladorImagen;
import edu.poo.interfaz.DAO;
import edu.poo.modelo.Ciudad;
import edu.poo.modelo.Departamento;
import edu.poo.modelo.Pais;
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

public class DAOCiudad implements DAO<Ciudad> {

    private ArchivoPlanoNIO miArchivo;
    private String nombrePersistencia;
    private DAODepartamento miDepartamento = new DAODepartamento();

    public DAOCiudad() {
        try {
            nombrePersistencia = Ruta.RUTA_PERSISTENCIA + "\\" + Configuracion.PERSISTENCIA_NOMBRE_CIUDAD;
            miArchivo = new ArchivoPlanoNIO(nombrePersistencia);
        } catch (IOException ex) {
            Logger.getLogger(DAOCiudad.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public boolean insertInto(Ciudad miObject, String laRuta, String rutaDest) {
        String cadena;
        boolean correcto = false;
        try {

            String nocu = ControladorImagen.grabarImagen(laRuta);
            cadena = miObject.getCodCiudad()
                    + Configuracion.SEPARADOR_COLUMNA
                    + miObject.getNombreCiudad()
                    + Configuracion.SEPARADOR_COLUMNA
                    + miObject.getNombreImagenCiudad()
                    + Configuracion.SEPARADOR_COLUMNA
                    + miObject.getObjDepartamento().getCodDepartamento()
                    + Configuracion.SEPARADOR_COLUMNA
                    + nocu;

            miArchivo.agregarRegistro(cadena);
            correcto = true;

        } catch (IOException ex) {
            Logger.getLogger(DAOCiudad.class.getName()).log(Level.SEVERE, null, ex);
        }
        return correcto;
    }

    @Override
    public List<Ciudad> selectFrom() {
        int i, desde, cuente, limite, codigo;
        String cadena, nombre, imag, ocul;
        Departamento departamento;

        List<String> arregloDatos;
        List<Ciudad> arregloCiudades = new ArrayList<>();

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
                departamento = miDepartamento.getOne(Integer.parseInt(cadena.substring(desde, cuente).trim()));

                // Obtener el nombre oculto de la imagen
                desde = cuente + 1;
                cuente =  cadena.length() - 1;
                ocul = cadena.substring(desde, cuente).trim();
                
                Ciudad objCiudad = new Ciudad(codigo, nombre, imag, ocul, departamento);
                arregloCiudades.add(objCiudad);

            }
        } catch (IOException ex) {
            Logger.getLogger(DAOCiudad.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arregloCiudades;
    }

    @Override
    public int getSerial() {
        int id = 0;
        try {
            id = miArchivo.ultimoCodigo() + 1;
        } catch (IOException ex) {
            Logger.getLogger(DAOCiudad.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }

    @Override
    public int numRows() {
        int cantidad = 0;
        try {
            cantidad = miArchivo.cantidadFilas();
        } catch (IOException ex) {
            Logger.getLogger(DAOCiudad.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(DAOCiudad.class.getName()).log(Level.SEVERE, null, ex);
        }
        return correcto;
    }

    @Override
    public boolean updateSet(int principalKey, Ciudad miObject, String laRuta) {
        boolean correcto = false;
        try {
            String cadena, nomOcu;
            List<String> arregloDatos;

            cadena = miObject.getCodCiudad()+ Configuracion.SEPARADOR_COLUMNA
                    + miObject.getNombreCiudad()+ Configuracion.SEPARADOR_COLUMNA
                    + miObject.getNombreImagenCiudad() + Configuracion.SEPARADOR_COLUMNA
                    + miObject.getObjDepartamento().getCodDepartamento() + Configuracion.SEPARADOR_COLUMNA;

            if (laRuta.isBlank()) {
                cadena = cadena + miObject.getNombreImagenOcultaCiudad();
            } else {
                nomOcu = ControladorImagen.grabarImagen(laRuta);
                cadena = cadena + nomOcu ;
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
            Logger.getLogger(DAOCiudad.class.getName()).log(Level.SEVERE, null, ex);
        }

        return correcto;
    }

    @Override
    public Ciudad getOne(int principalKey) {
        Ciudad objCiudad = new Ciudad();

        try {
            List<String> arrDatos = miArchivo.obtenerFila(principalKey-1);
            objCiudad = new Ciudad(Integer.valueOf(arrDatos.get(0)),
                    arrDatos.get(1),
                    arrDatos.get(2),
                    arrDatos.get(4),
                    miDepartamento.getOne(Integer.parseInt(arrDatos.get(3))));

        } catch (IOException ex) {
            Logger.getLogger(DAOCiudad.class.getName()).log(Level.SEVERE, null, ex);
        }
        return objCiudad;
    }

}
