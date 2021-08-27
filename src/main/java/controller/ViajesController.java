
package controller;

import gestion.ViajeGestion;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
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
            return "listarViajes.xhtml";
        } else {
            FacesMessage mensaje
                    = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Error", "Intente de nuevo");
            FacesContext.getCurrentInstance().addMessage(
                    "editaViajeForm:idTurista", mensaje);
            return "listarViajes.xhtml";
        }
    }

    public String modifica() {
        if (ViajeGestion.modificar(this)) {
            return "listarViajes.xhtml";
        } else {
            FacesMessage mensaje
                    = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Error", "Posible cédula duplicada");
            FacesContext.getCurrentInstance().addMessage(
                    "editaViajeForm:idViaje", mensaje);
            return "editaViaje.xhtml";
        }
    }

    public String elimina(String idViaje) {
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

    public String edita(String idViaje) {
        Viaje viaje = (Viaje) ViajeGestion.getViaje(idViaje);
    //    Turista turista = TuristaGestion.getTurista(idTurista);
        if (viaje != null) {  //Si lo encuantra actualizo la info
            
            this.setIdViaje(viaje.getIdViaje());
            this.setNombre(viaje.getNombre());
            this.setDescripcion(viaje.getDescripcion());
            this.setTelefono(viaje.getTelefono());
            this.setCorreoElectronico(viaje.getCorreoElectronico());
            this.setActivo(viaje.isActivo());
            this.setCosto(viaje.getCosto());
            return "editaViaje.xhtml";
        } else {  //Si no encuentra la información del viaje...
            return "listarViajes.xhtml";
        }
    }

    public List<Viaje> getViajes() {
        return ViajeGestion.getViajes();
    }
    
      public List<Viaje> getViajesDisponibles() {
        
        List<Viaje> viajesDisponibles = new ArrayList<>();
        List<Viaje> viajes = ViajeGestion.getViajes(); //Llamo a todos los viajes que tengo en la base de datos por medio del metodo getViajes
                
        for (int i = 0; i < viajes.size(); i++) {
            if (viajes.get(i).isActivo() == true) {
                viajesDisponibles.add(viajes.get(i));
            }
        }

        return viajesDisponibles;
    }

}
