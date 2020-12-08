package models;

public class Proveedor {

    private String cveProv;
    private String nombre;
    private String telefono;
    private String direccion;

    public Proveedor(String cveProv, String nombre, String telefono, String direccion) {
        this.cveProv = cveProv;
        this.nombre = nombre;
        this.telefono = telefono;
        this.direccion = direccion;
    }

    public String getCveProv() {
        return cveProv;
    }

    public String getNombre() {
        return nombre;
    }

    public String getTelefono() {
        return telefono;
    }

    public String getDireccion() {
        return direccion;
    }
}