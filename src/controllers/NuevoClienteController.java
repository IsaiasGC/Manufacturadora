package controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Cliente;

public class NuevoClienteController implements Initializable {

    @FXML
    TextField no, nom, dir;
    
    static ClientesController control;
    static Stage stage;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void Guardar(ActionEvent event) {
        if(Main.manager.insert(new Cliente(no.getText(), nom.getText(), dir.getText()))){
            control.cargarClientes();
            System.out.println("Guardado Correctamente");
            stage.close();
        }else
            System.out.println("Error al Guardar");
    }
}
