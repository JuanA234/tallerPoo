package edu.poo.persistencia;

import edu.poo.controlador.ControladorImagen;
import edu.poo.interfaz.DAO;
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

public class DAOPais implements DAO<Pais> {

    private ArchivoPlanoNIO miArchivo;
    private String nombrePersistencia;

    public DAOPais() {

        try {
            nombrePersistencia = Ruta.RUTA_PERSISTENCIA + "\\" + Configuracion.PERSISTENCIA_NOMBRE_PAIS;
            miArchivo = new ArchivoPlanoNIO(nombrePersistencia);
        } catch (IOException ex) {
            Logger.getLogger(DAOPais.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public boolean insertInto(Pais miObject, String ruta, String rutaDest) {
        String cadena;
        boolean correcto = false;
        try {

            String nocu = ControladorImagen.grabarImagen(ruta);
            cadena = miObject.getCodPais()
                    + Configuracion.SEPARADOR_COLUMNA
                    + miObject.getNombrePais()
                    + Configuracion.SEPARADOR_COLUMNA
                    + miObject.getNombreImagenPais()
                    + Configuracion.SEPARADOR_COLUMNA
                    + nocu;

            miArchivo.agregarRegistro(cadena);
            correcto = true;

        } catch (IOException ex) {
            Logger.getLogger(DAOPais.class.getName()).log(Level.SEVERE, null, ex);
        }
        return correcto;
    }

    @Override
    public List<Pais> selectFrom() {
        int i, desde, cuente, limite, codigo;
        String cadena, nombre, imag, ocul;

        List<String> arregloDatos;
        List<Pais> arregloPais = new ArrayList<>();

        try {
            arregloDatos = miArchivo.obtenerDatos();
            limite = arregloDatos.size();
            for (i = 0; i < limite; i++) {
                cadena = arregloDatos.get(i);

                // Obtener el código
                desde = 0;
                cuente = cadena.indexOf(Configuracion.SEPARADOR_COLUMNA, desde);
                codigo = Integer.parseInt(cadena.substring(desde, cuente).trim()); // El código es numérico

                // Obtener la marca
                desde = cuente + 1;
                cuente = cadena.indexOf(Configuracion.SEPARADOR_COLUMNA, desde);
                nombre = cadena.substring(desde, cuente).trim();

                // Obtener el nombre de la imagen
                desde = cuente + 1;
                cuente = cadena.indexOf(Configuracion.SEPARADOR_COLUMNA, desde);
                imag = cadena.substring(desde, cuente).trim();

                // Obtener el nombre oculto de la imagen
                desde = cuente + 1;
                cuente = cadena.length() - 1;
                ocul = cadena.substring(desde, cuente).trim();

                Pais objPais = new Pais(codigo, nombre, imag, ocul);
                arregloPais.add(objPais);
            }
        } catch (IOException ex) {
            Logger.getLogger(DAOPais.class.getName()).log(Level.SEVERE, null, ex);
        }
        return arregloPais;
    }

    @Override
    public int getSerial() {
        int id = 0;
        try {
            id = miArchivo.ultimoCodigo() + 1;
        } catch (IOException ex) {
            Logger.getLogger(DAOPais.class.getName()).log(Level.SEVERE, null, ex);
        }
        return id;
    }

    @Override
    public int numRows() {
        int cantidad = 0;
        try {
            cantidad = miArchivo.cantidadFilas();
        } catch (IOException ex) {
            Logger.getLogger(DAOPais.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(DAOPais.class.getName()).log(Level.SEVERE, null, ex);
        }
        return correcto;
    }

    @Override
    public boolean updateSet(int principalKey, Pais miObject, String laRuta) {
        boolean correcto = false;
        try {
            String cadena, nomOcu;
            List<String> arregloDatos;

            cadena = miObject.getCodPais() + Configuracion.SEPARADOR_COLUMNA
                    + miObject.getNombrePais() + Configuracion.SEPARADOR_COLUMNA
                    + miObject.getNombreImagenPais() + Configuracion.SEPARADOR_COLUMNA;
            if (laRuta.isBlank()) {
                cadena = cadena + miObject.getNombreImagenOcultaPais();
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
            Logger.getLogger(DAOPais.class.getName()).log(Level.SEVERE, null, ex);
        }

        return correcto;
    }

    @Override
    public Pais getOne(int principalKey) {
        Pais objPais = new Pais();
        try {
            List<String> arrDatos = miArchivo.obtenerFila(principalKey - 1);
            objPais = new Pais(Integer.valueOf(arrDatos.get(0)),
                    arrDatos.get(1),
                    arrDatos.get(2),
                    arrDatos.get(3));

        } catch (IOException ex) {
            Logger.getLogger(DAOPais.class.getName()).log(Level.SEVERE, null, ex);
        }
        return objPais;
    }

}
