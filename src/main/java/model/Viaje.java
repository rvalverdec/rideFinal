
package model;

public class Viaje {

    private String idViaje;
    private String ubicacion;
    private String nombreLugar;
    private String descripcion;
    private String requerimiento;
    private String oferta;
    private String telefono;
    private String correoElectronico;

    public Viaje() {
    }

    public String getIdViaje() {
        return idViaje;
    }

    public void setIdViaje(String idViaje) {
        this.idViaje = idViaje;
    }

    public String getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(String ubicacion) {
        this.ubicacion = ubicacion;
    }

    public String getNombreLugar() {
        return nombreLugar;
    }

    public void setNombreLugar(String nombreLugar) {
        this.nombreLugar = nombreLugar;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getRequerimiento() {
        return requerimiento;
    }

    public void setRequerimiento(String requerimiento) {
        this.requerimiento = requerimiento;
    }

    public String getOferta() {
        return oferta;
    }

    public void setOferta(String oferta) {
        this.oferta = oferta;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreoElectronico() {
        return correoElectronico;
    }

    public void setCorreoElectronico(String correoElectronico) {
        this.correoElectronico = correoElectronico;
    }

    public Viaje(String ubicacion, String nombreLugar, String descripcion, String requerimiento, String oferta, String telefono, String correoElectronico) {
        this.ubicacion = ubicacion;
        this.nombreLugar = nombreLugar;
        this.descripcion = descripcion;
        this.requerimiento = requerimiento;
        this.oferta = oferta;
        this.telefono = telefono;
        this.correoElectronico = correoElectronico;
    }
}
