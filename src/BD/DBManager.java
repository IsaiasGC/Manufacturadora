package BD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import models.Cliente;
import models.Departamento;
import models.Distribuidor;
import models.Empleado;
import models.InventarioMateria;
import models.InventarioParte;
import models.Proveedor;
import models.Sucursal;

public class DBManager {

    Connection conn;

    public DBManager(Connection conn) {
        this.conn = conn;
    }

    public ObservableList<Cliente> fetchAllClientes() {
        ResultSet rs = null;
        ObservableList<Cliente> clientes = FXCollections.observableArrayList();
        try {
            String query = "select * from cliente";
            Statement st = conn.createStatement();
            rs = st.executeQuery(query);
            Cliente c = null;
            while (rs.next()) {
                c = new Cliente(rs.getString("noCte"), rs.getString("nombre"), rs.getString("direccion"));
                clientes.add(c);
            }
            rs.close();
            st.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error al recuperar información...");
        }
        return clientes;
    }

    public ObservableList<Empleado> fetchAllEmpleados() {
        ResultSet rs = null;
        ObservableList<Empleado> empleados = FXCollections.observableArrayList();
        try {
            String query = "select * from empleado";
            Statement st = conn.createStatement();
            rs = st.executeQuery(query);
            Empleado e = null;
            while (rs.next()) {
                e = new Empleado(rs.getInt("noEmp"), rs.getString("nombre"), rs.getString("sexo"),
                        rs.getInt("edad"), rs.getString("direccion"), rs.getString("cveDep"),
                        rs.getString("cveTipo"));
                empleados.add(e);
            }
            rs.close();
            st.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error al recuperar información...");
        }
        return empleados;
    }

    public ObservableList<Departamento> fetchAllDepartamentos() {
        ResultSet rs = null;
        ObservableList<Departamento> departamentos = FXCollections.observableArrayList();
        try {
            String query = "select * from departamento";
            Statement st = conn.createStatement();
            rs = st.executeQuery(query);
            Departamento d = null;
            while (rs.next()) {
                d = new Departamento(rs.getString("cveDep"), rs.getString("cveSuc"),
                         rs.getString("nombre"), rs.getString("encargado"));
                departamentos.add(d);
            }
            rs.close();
            st.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error al recuperar información...");
        }
        return departamentos;
    }

    public ObservableList<Sucursal> fetchAllSucursales() {
        ResultSet rs = null;
        ObservableList<Sucursal> sucursales = FXCollections.observableArrayList();
        try {
            String query = "select * from sucursal";
            Statement st = conn.createStatement();
            rs = st.executeQuery(query);
            Sucursal s = null;
            while (rs.next()) {
                s = new Sucursal(rs.getString("cveSuc"), rs.getString("nombre"),
                         rs.getString("direccion"), rs.getString("encargado"));
                sucursales.add(s);
            }
            rs.close();
            st.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error al recuperar información...");
        }
        return sucursales;
    }

    public ObservableList<Distribuidor> fetchAllDistribuidores() {
        ResultSet rs = null;
        ObservableList<Distribuidor> distribuidores = FXCollections.observableArrayList();
        try {
            String query = "select * from distribuidor";
            Statement st = conn.createStatement();
            rs = st.executeQuery(query);
            Distribuidor d = null;
            while (rs.next()) {
                d = new Distribuidor(rs.getString("cveDist"), rs.getString("nombre"), rs.getString("direccion"));
                distribuidores.add(d);
            }
            rs.close();
            st.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error al recuperar información...");
        }
        return distribuidores;
    }

    public ObservableList<Proveedor> fetchAllProveedores() {
        ResultSet rs = null;
        ObservableList<Proveedor> proveedores = FXCollections.observableArrayList();
        try {
            String query = "select * from proveedor";
            Statement st = conn.createStatement();
            rs = st.executeQuery(query);
            Proveedor p = null;
            while (rs.next()) {
                p = new Proveedor(rs.getString("cveProv"), rs.getString("nombre"),
                        rs.getString("telefono"), rs.getString("direccion"));
                proveedores.add(p);
            }
            rs.close();
            st.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error al recuperar información...");
        }
        return proveedores;
    }

    public ObservableList<InventarioMateria> fetchAllInventariosMat() {
        ResultSet rs = null;
        ObservableList<InventarioMateria> inventarios = FXCollections.observableArrayList();
        try {
            String query = "select * from inventarioMateria";
            Statement st = conn.createStatement();
            rs = st.executeQuery(query);
            InventarioMateria i = null;
            while (rs.next()) {
                i = new InventarioMateria(rs.getString("cveInv"), rs.getDate("fecha"),
                        rs.getString("cveMat"), rs.getInt("cantidad"));
                inventarios.add(i);
            }
            rs.close();
            st.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error al recuperar información...");
        }
        return inventarios;
    }

    public ObservableList<InventarioParte> fetchAllInventariosPart() {
        ResultSet rs = null;
        ObservableList<InventarioParte> inventarios = FXCollections.observableArrayList();
        try {
            String query = "select * from inventarioParte";
            Statement st = conn.createStatement();
            rs = st.executeQuery(query);
            InventarioParte i = null;
            while (rs.next()) {
                i = new InventarioParte(rs.getString("cveInv"), rs.getDate("fecha"),
                        rs.getString("cveParte"), rs.getInt("cantidad"));
                inventarios.add(i);
            }
            rs.close();
            st.close();

        } catch (SQLException ex) {
            ex.printStackTrace();
            System.out.println("Error al recuperar información...");
        }
        return inventarios;
    }

    public boolean insert(Cliente client) {
        try {
            String query = "INSERT INTO cliente"
                    + "(nocte, nombre, direccion)"
                    + "VALUES(?, ?, ?)";
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, client.getNoCte());
            st.setString(2, client.getNombre());
            st.setString(3, client.getDireccion());
            st.execute();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public boolean insert(Empleado emp) {
        try {
            String query = "INSERT INTO empleado"
                    + "(noEmp, nombre, sexo, edad, direccion, cveDep, cveTipo)"
                    + "VALUES(?, ?, ?, ?, ?, ?, ?)";
            PreparedStatement st = conn.prepareStatement(query);
            st.setInt(1, emp.getNoEmp());
            st.setString(2, emp.getNombre());
            st.setString(3, emp.getSexo());
            st.setInt(4, emp.getEdad());
            st.setString(5, emp.getDireccion());
            st.setString(6, emp.getCveDep());
            st.setString(7, emp.getCveTipo());
            st.execute();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public boolean insert(Departamento depto) {
        try {
            String query = "INSERT INTO departamento"
                    + "(cveDep, cveSuc, nombre, encargado)"
                    + "VALUES(?, ?, ?, ?)";
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, depto.getCveDepto());
            st.setString(2, depto.getCveSuc());
            st.setString(3, depto.getNombre());
            st.setString(4, depto.getEncargado());
            st.execute();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public boolean insert(Sucursal sucursal) {
        try {
            String query = "INSERT INTO sucursal"
                    + "(cveSuc, nombre, direccion, encargado)"
                    + "VALUES(?, ?, ?, ?)";
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, sucursal.getCveSuc());
            st.setString(2, sucursal.getNombre());
            st.setString(3, sucursal.getDireccion());
            st.setString(4, sucursal.getEncargado());
            st.execute();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public boolean insert(Distribuidor distribuidor) {
        try {
            String query = "INSERT INTO distribuidor"
                    + "(cveDist, nombre, direccion)"
                    + "VALUES(?, ?, ?)";
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, distribuidor.getCveDist());
            st.setString(2, distribuidor.getNombre());
            st.setString(3, distribuidor.getDireccion());
            st.execute();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public boolean insert(Proveedor proveedor) {
        try {
            String query = "INSERT INTO proveedor"
                    + "(cveProv, nombre, telefono, direccion)"
                    + "VALUES(?, ?, ?, ?)";
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, proveedor.getCveProv());
            st.setString(2, proveedor.getNombre());
            st.setString(3, proveedor.getTelefono());
            st.setString(4, proveedor.getDireccion());
            st.execute();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public boolean insert(InventarioMateria inventario) {
        try {
            String query = "INSERT INTO inventarioMateria"
                    + "(cveInv, fecha, cveMat, cantidad)"
                    + "VALUES(?, ?, ?, ?)";
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, inventario.getCveInv());
            st.setDate(2, inventario.getFecha());
            st.setString(3, inventario.getCveMat());
            st.setInt(4, inventario.getCantidad());
            st.execute();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public boolean insert(InventarioParte inventario) {
        try {
            String query = "INSERT INTO inventarioParte"
                    + "(cveInv, fecha, cveParte, cantidad)"
                    + "VALUES(?, ?, ?, ?)";
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, inventario.getCveInv());
            st.setDate(2, inventario.getFecha());
            st.setString(3, inventario.getCveParte());
            st.setInt(4, inventario.getCantidad());
            st.execute();
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public boolean update(Cliente client) {
        try {
            String query = "UPDATE cliente SET nombre=?, direccion=?"
                    + " WHERE noCte='" + client.getNoCte() + "'";
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, client.getNombre());
            st.setString(2, client.getDireccion());
            st.execute();
            return true;
        } catch (Exception e) {
            System.out.println("Update Cliente: " + e.getMessage());
        }
        return false;
    }

    public boolean update(Sucursal sucursal) {
        try {
            String query = "UPDATE sucursal SET nombre=?, direccion=?, encargado=?"
                    + " WHERE cveSuc='" + sucursal.getCveSuc() + "'";
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, sucursal.getNombre());
            st.setString(2, sucursal.getDireccion());
            st.setString(3, sucursal.getEncargado());
            st.execute();
            return true;
        } catch (Exception e) {
            System.out.println("Update Sucursal: " + e.getMessage());
        }
        return false;
    }

    public boolean update(Departamento departamento) {
        try {
            String query = "UPDATE departamento SET cveSuc=?, nombre=?, encargado=?"
                    + " WHERE cveDep='" + departamento.getCveDepto() + "'";
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, departamento.getCveSuc());
            st.setString(2, departamento.getNombre());
            st.setString(3, departamento.getEncargado());
            st.execute();
            return true;
        } catch (Exception e) {
            System.out.println("Update Departamento: " + e.getMessage());
        }
        return false;
    }

    public boolean update(Distribuidor distribuidor) {
        try {
            String query = "UPDATE distribuidor SET nombre=?, direccion=?"
                    + " WHERE cveDist='" + distribuidor.getCveDist() + "'";
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, distribuidor.getNombre());
            st.setString(2, distribuidor.getDireccion());
            st.execute();
            return true;
        } catch (Exception e) {
            System.out.println("Update Distribuidor: " + e.getMessage());
        }
        return false;
    }

    public boolean update(Empleado empleado) {
        try {
            String query = "UPDATE empleado SET nombre=?, sexo=?, edad=?, direccion=?, cveDep=?, cveTipo=?"
                    + " WHERE noEmp='" + empleado.getNoEmp() + "'";
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, empleado.getNombre());
            st.setString(2, empleado.getSexo());
            st.setInt(3, empleado.getEdad());
            st.setString(4, empleado.getDireccion());
            st.setString(5, empleado.getCveDep());
            st.setString(6, empleado.getCveTipo());
            st.execute();
            return true;
        } catch (Exception e) {
            System.out.println("Update Empleado: " + e.getMessage());
        }
        return false;
    }

    public boolean update(InventarioMateria inventarioM) {
        try {
            String query = "UPDATE inventariomateria SET fecha=?, cveMat=?, cantidad=?"
                    + " WHERE cveInv='" + inventarioM.getCveInv() + "'";
            PreparedStatement st = conn.prepareStatement(query);
            st.setDate(1, inventarioM.getFecha());
            st.setString(2, inventarioM.getCveMat());
            st.setInt(3, inventarioM.getCantidad());
            st.execute();
            return true;
        } catch (Exception e) {
            System.out.println("Update Inventario Materia: " + e.getMessage());
        }
        return false;
    }

    public boolean update(InventarioParte inventarioP) {
        try {
            String query = "UPDATE inventarioparte SET fecha=?, cveParte=?, cantidad=?"
                    + " WHERE cveInv='" + inventarioP.getCveInv() + "'";
            PreparedStatement st = conn.prepareStatement(query);
            st.setDate(1, inventarioP.getFecha());
            st.setString(2, inventarioP.getCveParte());
            st.setInt(3, inventarioP.getCantidad());
            st.execute();
            return true;
        } catch (Exception e) {
            System.out.println("Update Inventario Parte: " + e.getMessage());
        }
        return false;
    }

    public boolean update(Proveedor proveedor) {
        try {
            String query = "UPDATE proveedor SET nombre=?, telefono=?, direccion=?"
                    + " WHERE cveProv='" + proveedor.getCveProv() + "'";
            PreparedStatement st = conn.prepareStatement(query);
            st.setString(1, proveedor.getNombre());
            st.setString(2, proveedor.getTelefono());
            st.setString(3, proveedor.getDireccion());
            st.execute();
            return true;
        } catch (Exception e) {
            System.out.println("Update Proveedor: " + e.getMessage());
        }
        return false;
    }

    public boolean delete(Cliente client) {
        try {
            String query = "delete from Cliente where noCte = '" + client.getNoCte() + "'";
            Statement st = conn.createStatement();
            st.execute(query);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public boolean delete(Empleado emp) {
        try {
            String query = "delete from empleado where noEmp = '" + emp.getNoEmp() + "'";
            Statement st = conn.createStatement();
            st.execute(query);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public boolean delete(Departamento depto) {
        try {
            String query = "delete from departamento where cveDep = '" + depto.getCveDepto() + "'";
            Statement st = conn.createStatement();
            st.execute(query);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public boolean delete(Sucursal sucursal) {
        try {
            String query = "delete from sucursal where cveSuc = '" + sucursal.getCveSuc() + "'";
            Statement st = conn.createStatement();
            st.execute(query);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public boolean delete(Distribuidor distribuidor) {
        try {
            String query = "delete from distribuidor where cveDist = '" + distribuidor.getCveDist() + "'";
            Statement st = conn.createStatement();
            st.execute(query);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public boolean delete(Proveedor proveedor) {
        try {
            String query = "delete from proveedor where cveProv = '" + proveedor.getCveProv() + "'";
            Statement st = conn.createStatement();
            st.execute(query);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public boolean delete(InventarioMateria inventario) {
        try {
            String query = "delete from inventarioMateria where cveInv = '" + inventario.getCveInv() + "'";
            Statement st = conn.createStatement();
            st.execute(query);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }

    public boolean delete(InventarioParte inventario) {
        try {
            String query = "delete from inventarioParte where cveInv = '" + inventario.getCveInv() + "'";
            Statement st = conn.createStatement();
            st.execute(query);
            return true;
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return false;
    }
}