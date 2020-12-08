package controllers;

import static controllers.NuevoDepartamentoController.control;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Departamento;

public class ModificarDepartamentoController implements Initializable {

    @FXML 
    TextField cvedep, cvesuc, nom, encar;
    static DepartamentosController control;
    static Stage stage;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cvedep.setText(DepartamentosController.depto.getCveDepto());
        cvesuc.setText(DepartamentosController.depto.getCveSuc());
        nom.setText(DepartamentosController.depto.getNombre());
        encar.setText(DepartamentosController.depto.getEncargado());
        cvedep.setDisable(true);
    }    
    
    @FXML
    private void Modificar(ActionEvent event) {
        if(Main.manager.update(new Departamento(cvedep.getText(), cvesuc.getText(), nom.getText(), encar.getText()))){
            System.out.println("Modificado Correctamente");
            control.cargarDepartamentos();
            stage.close();
        }else
            System.out.println("Error al Modificar");
    } 
}