package controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Empleado;

public class ModificarEmpleadoController implements Initializable {

    @FXML
    TextField cve, nom, sex, edad, dir, dep, pues;
    static EmpleadosController control;
    static Stage stage;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cve.setText(EmpleadosController.empleado.getNoEmp()+"");
        nom.setText(EmpleadosController.empleado.getNombre());
        sex.setText(EmpleadosController.empleado.getSexo());
        edad.setText(EmpleadosController.empleado.getEdad()+"");
        dir.setText(EmpleadosController.empleado.getDireccion());
        dep.setText(EmpleadosController.empleado.getCveDep());
        pues.setText(EmpleadosController.empleado.getCveTipo());
        cve.setDisable(true);
    }

    @FXML
    private void Modificar(ActionEvent event) {
        Main.manager.update(new Empleado(Integer.parseInt(cve.getText()), nom.getText(), sex.getText(), Integer.parseInt(edad.getText()), dir.getText(), dep.getText(), pues.getText()));
        System.out.println("Modificado Correctamente");
    }
}