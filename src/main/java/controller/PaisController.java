/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import gestion.PaisGestion;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import model.Pais;

@Named(value = "paisController")
@SessionScoped
public class PaisController extends Pais implements Serializable {

    public PaisController() {
    }
      public String inserta() {
        if (PaisGestion.insertar(this)) {
            return "listarPaises.xhtml";
        } else {
            FacesMessage mensaje
                    = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Error", "Intente de nuevo");
            FacesContext.getCurrentInstance().addMessage(
                    "editaPaisForm:idPais", mensaje);
            return "edita.xhtml";
        }
    }

    public String modifica() {
        if (PaisGestion.modificar(this)) {
            return "listarPaises.xhtml";
        } else {
            FacesMessage mensaje
                    = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Error", "Posible idPais duplicado");
            FacesContext.getCurrentInstance().addMessage(
                    "editaPaisForm:idPais", mensaje);
            return "editaPais.xhtml";
        }
    }

    public String elimina() {
        if (PaisGestion.eliminar(this)) {
            return "listarPaises.xhtml";
        } else {
            FacesMessage mensaje
                    = new FacesMessage(FacesMessage.SEVERITY_ERROR,
                            "Error", "Es posible que tenga notas");
            FacesContext.getCurrentInstance().addMessage(
                    "editaPaisForm:idPais", mensaje);
            return "edita.xhtml";
        }
    }

    public String edita(String idPais) {
        Pais pais = (Pais) PaisGestion.getPais(idPais);
        if (pais != null) {
            
            this.setIdPais(pais.getIdPais());
            this.setNombre(pais.getNombre());
            this.setContinente(pais.getContinente());
            this.setDescripcion(pais.getDescripcion());
            
            return "editaPais.xhtml";
        } else { 
            return "listarPaises.xhtml";
        }
    }

    public List<Pais> getPaises() {
        return PaisGestion.getPaises();
    }
}
