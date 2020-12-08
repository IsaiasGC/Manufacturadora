package controllers;

import static controllers.NuevoClienteController.control;
import static controllers.NuevoClienteController.stage;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Cliente;

public class ModificarClienteController implements Initializable {

    @FXML
    TextField no, nom, dir;
    
    static ClientesController control;
    static Stage stage;
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        no.setText(ClientesController.cliente.getNoCte());
        nom.setText(ClientesController.cliente.getNombre());
        dir.setText(ClientesController.cliente.getDireccion());
        no.setDisable(true);
    }

    @FXML
    private void Modificar(ActionEvent event) {
        if(Main.manager.update(new Cliente(no.getText(), nom.getText(), dir.getText()))){
            control.cargarClientes();
            System.out.println("Modificado Correctamente");
            stage.close();
        }else
            System.out.println("Error al Modificar");
    }
}