package controller;

import gestion.ViajeGestion;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import model.Viaje;

@Named(value = "viajesController")
@SessionScoped
public class ViajesController extends Viaje implements Serializable {

    public ViajesController() {
    }

    public String inserta() {
        if (ViajeGestion.insertar(this)) {
            return "list.xhtml";
        } else {
            FacesMessage mensaje
                    = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Error", "Intente de nuevo");
            FacesContext.getCurrentInstance().addMessage(
                    "editaViajeForm:idTurista", mensaje);
            return "edita.xhtml";
        }
    }

    public String modifica() {
        if (ViajeGestion.modificar(this)) {
            return "list.xhtml";
        } else {
            FacesMessage mensaje
                    = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Error", "Posible cédula duplicada");
            FacesContext.getCurrentInstance().addMessage(
                    "editaViajeForm:idViaje", mensaje);
            return "editaViaje.xhtml";
        }
    }

    public String elimina() {
        if (ViajeGestion.eliminar(this)) {
            return "listarViajes.xhtml";
        } else {
            FacesMessage mensaje
                    = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Error", "Es posible que tenga notas");
            FacesContext.getCurrentInstance().addMessage(
                    "editaViajeForm:idViaje", mensaje);
            return "edita.xhtml";
        }
    }

    public String edita(String idViaje, String Nombre, String Descripcion, String Telefono, String CorreoElectronico, boolean activo, double costo ) {
        Viaje viaje = (Viaje) ViajeGestion.getViaje(idViaje);
        if (viaje != null) {  //Si lo encuantra actualizo la info
            
            this.setNombre(Nombre);
            this.setDescripcion(Descripcion);
            this.setTelefono(Telefono);
            this.setCorreoElectronico(CorreoElectronico);
            this.setActivo(true);
            this.setCosto(costo);
            return "editaViaje.xhtml";
        } else {  //Si no encuentra la información del viaje...
            return "listarViajes.xhtml";
        }
    }

    public List<Viaje> getViajes() {
        return ViajeGestion.getViajes();
    }
}
