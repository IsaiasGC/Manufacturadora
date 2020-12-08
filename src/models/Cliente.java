package models;

public class Cliente {

    private String noCte;
    private String nombre;
    private String direccion;

    public Cliente(String noCte, String nombre, String direccion) {
        this.noCte = noCte;
        this.nombre = nombre;
        this.direccion = direccion;
    }

    public String getNoCte() {
        return noCte;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDireccion() {
        return direccion;
    }
}