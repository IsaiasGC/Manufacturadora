package controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Proveedor;

public class ModificarProveedorController implements Initializable {

    @FXML
    TextField cve, nom, dir, tel;
    static ProveedoresController control;
    static Stage stage;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cve.setText(ProveedoresController.proveedor.getCveProv());
        nom.setText(ProveedoresController.proveedor.getNombre());
        dir.setText(ProveedoresController.proveedor.getDireccion());
        tel.setText(ProveedoresController.proveedor.getTelefono());
        cve.setDisable(true);
    }

    @FXML
    private void Modificar(ActionEvent event) {
        if(Main.manager.update(new Proveedor(cve.getText(), nom.getText(), dir.getText(), tel.getText()))){
            System.out.println("Guardado Correctamente");
            control.cargarProveedores();
            stage.close();
        }else
            System.out.println("Error al Guardar");
    }
}