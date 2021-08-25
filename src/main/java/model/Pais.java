
package model;

public class Pais {
private int id; 
private String idPais; 
private String nombre;
private String Continente; 
private String descripcion; 

    public Pais(int id, String idPais, String nombre, String Continente, String descripcion) {
        this.id = id;
        this.idPais = idPais;
        this.nombre = nombre;
        this.Continente = Continente;
        this.descripcion = descripcion;
    }

    public Pais(String idPais, String nombre, String Continente, String descripcion) {
        this.idPais = idPais;
        this.nombre = nombre;
        this.Continente = Continente;
        this.descripcion = descripcion;
    }

    public Pais(String nombre, String Continente, String descripcion) {
        this.nombre = nombre;
        this.Continente = Continente;
        this.descripcion = descripcion;
    }

    public Pais() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getIdPais() {
        return idPais;
    }

    public void setIdPais(String idPais) {
        this.idPais = idPais;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getContinente() {
        return Continente;
    }

    public void setContinente(String Continente) {
        this.Continente = Continente;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "Pais{" + "id=" + id + ", idPais=" + idPais + ", nombre=" + nombre + ", Continente=" + Continente + ", descripcion=" + descripcion + '}';
    }


}
