package controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Sucursal;

public class NuevaSucursalController implements Initializable {

    @FXML
    TextField cve, nom, dir, encar;
    static SucursalesController control;
    static Stage stage;
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void Guardar(ActionEvent event) {
        if(Main.manager.insert(new Sucursal(cve.getText(), nom.getText(), dir.getText(), encar.getText()))){
            System.out.println("Guardado Correctamente");
            control.cargarSucursales();
            stage.close();
        }else
            System.out.println("Error al Guardar");
    }
}
