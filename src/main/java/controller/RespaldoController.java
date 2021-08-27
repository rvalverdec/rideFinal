package controller;

import gestion.PaisGestion;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;
import javax.enterprise.context.ApplicationScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;

@Named(value = "respaldoController")
@ApplicationScoped
public class RespaldoController {

    private String[] archivos
            = {"Pais", "Reserva", "Turista", "Viaje"};
    private String[] seleccionados;
    private String archivoRespaldo;

    public String[] getArchivos() {
        return archivos;
    }

    public void setArchivos(String[] archivos) {
        this.archivos = archivos;
    }

    public String[] getSeleccionados() {
        return seleccionados;
    }

    public void setSeleccionados(String[] seleccionados) {
        this.seleccionados = seleccionados;
    }

    public String getArchivoRespaldo() {
        String patron = "yyyyMMdd_HHmmss_SSS";
        SimpleDateFormat formato = new SimpleDateFormat(patron);
        archivoRespaldo = "Respaldo_" + formato.format(new Date());
        return archivoRespaldo;
    }

    public void setArchivoRespaldo(String archivoRespaldo) {
        this.archivoRespaldo = archivoRespaldo;
    }

    public RespaldoController() {
    }

    public void respaldo() {
        //Consultar si hay algo seleccionado para hacer respaldo
        if (seleccionados != null && seleccionados.length > 0) {
            ZipOutputStream salidaZip;  //Donde va a quedar el archivo zip

            File file = new File(FacesContext.getCurrentInstance()
                    .getExternalContext()
                    .getRealPath("/temporales/") + archivoRespaldo + ".zip");

            try {
                salidaZip
                        = new ZipOutputStream(new FileOutputStream(file));
                ZipEntry entradaZip;

                for (String s : seleccionados) {
                    if (s.contains("Paises")) {

                        entradaZip = new ZipEntry("paises.json");
                        salidaZip.putNextEntry(entradaZip);
                        byte data[]
                                = PaisGestion.generaJson().getBytes();
                        salidaZip.write(data, 0, data.length);
                        salidaZip.closeEntry();
                    }
                }
                salidaZip.close();

                // Descargar el archivo zip
                //Se carga el zip en un arreglo de bytes...
                byte[] zip = Files.readAllBytes(file.toPath());

                //Generar la página de respuesta...
                HttpServletResponse respuesta
                        = (HttpServletResponse) FacesContext
                                .getCurrentInstance()
                                .getExternalContext()
                                .getResponse();

                ServletOutputStream salida
                        = respuesta.getOutputStream();

                //Defino los encabezados de la página de respuesta
                respuesta.setContentType("application/zip");
                respuesta.setHeader("Content-Disposition",
                        "attachement; filename=" + archivoRespaldo + ".zip");

                salida.write(zip);
                salida.flush();

                //Todo listo
                FacesContext.getCurrentInstance().responseComplete();
                file.delete();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(RespaldoController.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(RespaldoController.class.getName()).log(Level.SEVERE, null, ex);
            }

        }

    }

}
