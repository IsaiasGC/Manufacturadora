package controllers;

import static controllers.ModificarProveedorController.control;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Proveedor;

public class NuevoProveedorController implements Initializable {

    @FXML
    TextField cve, nom, dir, tel;
    static ProveedoresController control;
    static Stage stage;
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void Guardar(ActionEvent event) {
        if(Main.manager.insert(new Proveedor(cve.getText(), nom.getText(), dir.getText(), tel.getText()))){
            System.out.println("Guardado Correctamente");
            control.cargarProveedores();
            stage.close();
        }else
            System.out.println("Error al Guardar");
    }
}