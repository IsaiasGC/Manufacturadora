package controllers;

import static controllers.NuevaSucursalController.control;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Sucursal;

public class ModificarSucursalController implements Initializable {
 
    @FXML
    TextField cve, nom, dir, encar;
    static SucursalesController control;
    static Stage stage;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cve.setText(SucursalesController.sucursal.getCveSuc());
        nom.setText(SucursalesController.sucursal.getNombre());
        dir.setText(SucursalesController.sucursal.getDireccion());
        encar.setText(SucursalesController.sucursal.getEncargado());
        cve.setDisable(true);
    }

    @FXML
    private void Modificar(ActionEvent event) {
        if(Main.manager.update(new Sucursal(cve.getText(), nom.getText(), dir.getText(), encar.getText()))){
            System.out.println("Modificado Correctamente");
            control.cargarSucursales();
            stage.close();
        }else
            System.out.println("Error al Modificar");
    }
}