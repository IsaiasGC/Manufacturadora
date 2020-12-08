package models;

public class Departamento {

    private String cveDepto;
    private String cveSuc;
    private String nombre;
    private String encargado;

    public Departamento(String cveDepto, String cveSuc, String nombre, String encargado) {
        this.cveDepto = cveDepto;
        this.cveSuc = cveSuc;
        this.nombre = nombre;
        this.encargado = encargado;
    }

    public String getCveDepto() {
        return cveDepto;
    }

    public String getCveSuc() {
        return cveSuc;
    }

    public String getNombre() {
        return nombre;
    }

    public String getEncargado() {
        return encargado;
    }
}