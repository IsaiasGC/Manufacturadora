package controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Distribuidor;

public class ModificarDistribuidorController implements Initializable {

    @FXML
    TextField cve, nom, dir;
    static DistribuidoresController control;
    static Stage stage;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cve.setText(DistribuidoresController.distribuidor.getCveDist());
        nom.setText(DistribuidoresController.distribuidor.getNombre());
        dir.setText(DistribuidoresController.distribuidor.getDireccion());
        cve.setDisable(true);
    }

    @FXML
    private void Modificar(ActionEvent event) {
        if(Main.manager.update(new Distribuidor(cve.getText(), nom.getText(), dir.getText()))){
            System.out.println("Modificado Correctamente");
            control.cargarDistribuidores();
            stage.close();
        }else
            System.out.println("Error al Modificar");
    }
}