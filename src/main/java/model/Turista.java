package model;

import java.util.Date;

//La clase POJO Turista...
public class Turista {

    private int id;
    private String idTurista;
    private String pwTurista;
    private String nombreUsuario;
    private String correoTurista;
    private boolean activo;
    private String idRol;

    public Turista() {
    }

    
    public Turista(int id, String idTurista, String pwTurista, String nombreUsuario, String correoTurista, boolean activo, String idRol) {
        this.id = id;
        this.idTurista = idTurista;
        this.pwTurista = pwTurista;
        this.nombreUsuario = nombreUsuario;
        this.correoTurista = correoTurista;
        this.activo = activo;
        this.idRol = idRol;
    }

    public Turista(String idTurista, String pwTurista, String nombreUsuario, String correoTurista, boolean activo, String idRol) {
        this.idTurista = idTurista;
        this.pwTurista = pwTurista;
        this.nombreUsuario = nombreUsuario;
        this.correoTurista = correoTurista;
        this.activo = activo;
        this.idRol = idRol;
    }

    
    
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdTurista() {
        return idTurista;
    }

    public void setIdTurista(String idTurista) {
        this.idTurista = idTurista;
    }

    public String getPwTurista() {
        return pwTurista;
    }

    public void setPwTurista(String pwTurista) {
        this.pwTurista = pwTurista;
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getCorreoTurista() {
        return correoTurista;
    }

    public void setCorreoTurista(String correoTurista) {
        this.correoTurista = correoTurista;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public String getIdRol() {
        return idRol;
    }

    public void setIdRol(String idRol) {
        this.idRol = idRol;
    }
    
    
    
}