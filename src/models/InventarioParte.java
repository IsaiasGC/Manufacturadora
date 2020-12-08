package models;

import java.sql.Date;

public class InventarioParte {

    private String cveInv;
    private Date fecha;
    private String cveParte;
    private int cantidad;

    public InventarioParte(String cveInv, Date fecha, String cveParte, int cantidad) {
        this.cveInv = cveInv;
        this.fecha = fecha;
        this.cveParte = cveParte;
        this.cantidad = cantidad;
    }

    public String getCveInv() {
        return cveInv;
    }

    public Date getFecha() {
        return fecha;
    }

    public String getCveParte() {
        return cveParte;
    }

    public int getCantidad() {
        return cantidad;
    }
}