package models;

public class Empleado {

    private int noEmp;
    private String nombre;
    private String sexo;
    private int edad;
    private String direccion;
    private String cveDep;
    private String cveTipo;

    public Empleado(int noEmp, String nombre, String sexo, int edad, String direccion, String cveDep, String cveTipo) {
        this.noEmp = noEmp;
        this.nombre = nombre;
        this.sexo = sexo;
        this.edad = edad;
        this.direccion = direccion;
        this.cveDep = cveDep;
        this.cveTipo = cveTipo;
    }

    public int getNoEmp() {
        return noEmp;
    }

    public String getNombre() {
        return nombre;
    }

    public String getSexo() {
        return sexo;
    }

    public int getEdad() {
        return edad;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getCveDep() {
        return cveDep;
    }

    public String getCveTipo() {
        return cveTipo;
    }
}