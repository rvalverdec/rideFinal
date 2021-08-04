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
            return "edita.xhtml";
        }
    }

    public String elimina() {
        if (ViajeGestion.eliminar(this)) {
            return "list.xhtml";
        } else {
            FacesMessage mensaje
                    = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Error", "Es posible que tenga notas");
            FacesContext.getCurrentInstance().addMessage(
                    "editaViajeForm:idTurista", mensaje);
            return "edita.xhtml";
        }
    }

    public String edita(String idViaje, String NombreLugar, String Descripcion, String CorreoElectronico, String Oferta, String Requerimiento, String Telefono, String Ubicacion) {
        Viaje viaje = (Viaje) ViajeGestion.getViaje(idViaje);
        if (viaje != null) {  //Si lo encuantra actualizo la info
            this.setCorreoElectronico(CorreoElectronico);
            this.setDescripcion(Descripcion);
            this.setIdViaje(idViaje);
            this.setNombreLugar(NombreLugar);
            this.setOferta(Oferta);
            this.setRequerimiento(Requerimiento);
            this.setTelefono(Telefono);
            this.setUbicacion(Ubicacion);
            return "edita.xhtml";
        } else {  //Si no encuentra la información del viaje...
            return "list.xhtml";
        }
    }

    public List<Viaje> getViaje() {
        return ViajeGestion.getViaje();
    }
}
