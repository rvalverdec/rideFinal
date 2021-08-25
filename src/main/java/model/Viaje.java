package model;

public class Viaje {

    private String idViaje;
    private String nombre;
    private String descripcion;
    private String telefono;
    private String correoElectronico;
    private boolean activo;
    private double costo;

    public Viaje() {
    }

    // Constructor sin el idViajes 
    public Viaje(String nombre, String descripcion,  String telefono, String correoElectronico, boolean activo, double costo) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.telefono = telefono;
        this.correoElectronico = correoElectronico;
        this.activo = activo;
        this.costo = costo;
    }

 

    public Viaje(String idViaje, String nombre, String descripcion,  String telefono, String correoElectronico, boolean activo, double costo) {
        this.idViaje = idViaje;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.telefono = telefono;
        this.correoElectronico = correoElectronico;
        this.activo = activo;
        this.costo = costo;
    }

    public String getIdViaje() {
        return idViaje;
    }

    public void setIdViaje(String idViaje) {
        this.idViaje = idViaje;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombreLugar) {
        this.nombre = nombreLugar;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public boolean isActivo() {
        return activo;
    }

    public void setActivo(boolean activo) {
        this.activo = activo;
    }

    public double getCosto() {
        return costo;
    }

    public void setCosto(double costo) {
        this.costo = costo;
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

}
