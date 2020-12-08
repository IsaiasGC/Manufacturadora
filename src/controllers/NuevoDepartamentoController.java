package controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Departamento;

public class NuevoDepartamentoController implements Initializable {

    @FXML
    TextField cvedep, cvesuc, nom, encar;
    static DepartamentosController control;
    static Stage stage;
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void Guardar(ActionEvent event) {
        if(Main.manager.insert(new Departamento(cvedep.getText(), cvesuc.getText(), nom.getText(), encar.getText()))){
            System.out.println("Guardado Correctamente");
            control.cargarDepartamentos();
            stage.close();
        }else
            System.out.println("Error al Guardar");
    }
}