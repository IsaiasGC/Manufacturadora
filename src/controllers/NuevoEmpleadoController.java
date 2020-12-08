package controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Empleado;

public class NuevoEmpleadoController implements Initializable {

    @FXML
    TextField cve, nom, sex, edad, dir, dep, pues;
    static EmpleadosController control;
    static Stage stage;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }
    @FXML
    private void Guardar(ActionEvent event) {
        if(Main.manager.insert(new Empleado(Integer.parseInt(cve.getText()), nom.getText(), sex.getText(), Integer.parseInt(edad.getText()), dir.getText(), dep.getText(), pues.getText()))){
            System.out.println("Guardado Correctamente");
            control.cargarEmpleados();
            stage.close();
        }else
            System.out.println("Guardado Correctamente");
    }
}