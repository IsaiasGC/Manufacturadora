package models;

import java.sql.Date;

public class InventarioMateria {
    private String cveInv;
    private Date fecha;
    private String cveMat;
    private int cantidad;

    public InventarioMateria(String cveInv, Date fecha, String cveMat, int cantidad) {
        this.cveInv = cveInv;
        this.fecha = fecha;
        this.cveMat = cveMat;
        this.cantidad = cantidad;
    }
    
    public String getCveInv() {
        return cveInv;
    }
    
    public Date getFecha() {
        return fecha;
    }
    
    public String getCveMat() {
        return cveMat;
    }
    
    public int getCantidad() {
        return cantidad;
    }
}