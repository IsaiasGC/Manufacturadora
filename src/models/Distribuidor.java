package models;

public class Distribuidor {

    private String cveDist;
    private String nombre;
    private String direccion;

    public Distribuidor(String cveDist, String nombre, String direccion) {
        this.cveDist = cveDist;
        this.nombre = nombre;
        this.direccion = direccion;
    }

    public String getCveDist() {
        return cveDist;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDireccion() {
        return direccion;
    }
}