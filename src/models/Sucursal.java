package models;

public class Sucursal {

    private String cveSuc;
    private String nombre;
    private String direccion;
    private String encargado;

    public Sucursal(String cveSuc, String nombre, String direccion, String encargado) {
        this.cveSuc = cveSuc;
        this.nombre = nombre;
        this.direccion = direccion;
        this.encargado = encargado;
    }

    public String getCveSuc() {
        return cveSuc;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getEncargado() {
        return encargado;
    }
}