package controller;

import gestion.TuristaGestion;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import model.Turista;

@Named(value = "turistaController")
@SessionScoped
public class TuristaController extends Turista implements Serializable {

    public String valida() {
        Turista usuario = gestion.TuristaGestion.valida(this.getIdTurista(),
                this.getPwTurista());
        if (usuario != null) {   //Se autenticó
            this.setIdTurista(usuario.getIdTurista());
            this.setNombreUsuario(usuario.getNombreUsuario());
            this.setCorreoTurista(usuario.getCorreoTurista());
            return "principal2.xhtml";
        } else {
            return "index.xhtml";
        }
    }

    public String inserta() {
        if (TuristaGestion.insertar(this)) {
            return "listarTurista.xhtml";
        } else {
            FacesMessage mensaje
                    = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Error", "Posible cédula duplicada");
            FacesContext.getCurrentInstance().addMessage(
                    "editaTuristaform:idTurista", mensaje);
            return "editaTurista.xhtml";
        }
    }

    public String modifica() {
        if (TuristaGestion.modificar(this)) {
            return "listarTurista.xhtml";
        } else {
            FacesMessage mensaje
                    = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Error", "Posible cédula duplicada");
            FacesContext.getCurrentInstance().addMessage(
                    "editaTuristaForm:idTurista", mensaje);
            return "editaTurista.xhtml";
        }
    }

    public String elimina() {
        if (TuristaGestion.eliminar(this)) {
            return "listarTuristas.xhtml";
        } else {
            FacesMessage mensaje
                    = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Error", "Es posible que tenga notas");
            FacesContext.getCurrentInstance().addMessage(
                    "editaTuristaForm:idTurista", mensaje);
            return "editaTurista.xhtml";
        }
    }

    public String edita(String idTurista) {
        Turista turista = TuristaGestion.getTurista(idTurista);
        if (turista != null) {  //Si lo encuantra actualizo la info
            this.setIdTurista(turista.getIdTurista());
            this.setPwTurista(turista.getPwTurista());
            this.setNombreUsuario(turista.getNombreUsuario());
            this.setCorreoTurista(turista.getCorreoTurista());
            this.setActivo(turista.isActivo());
            this.setIdRol(turista.getIdRol());           
            return "editaTurista.xhtml";
        } else {  //Por alguna razón no esta el turista...
            return "listarTurista.xhtml";
        }
    }

    public List<Turista> getTuristas() {
        return TuristaGestion.getTuristas();
    }

    public String logout() {
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        return "index.xhtml";
    }
}
