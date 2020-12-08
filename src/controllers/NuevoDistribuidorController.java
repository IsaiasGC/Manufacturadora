package controllers;

import static controllers.ModificarDistribuidorController.control;
import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.Distribuidor;

public class NuevoDistribuidorController implements Initializable {

    @FXML
    TextField cve, nom, dir;
    static DistribuidoresController control;
    static Stage stage;
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void Guardar(ActionEvent event) {
        if(Main.manager.insert(new Distribuidor(cve.getText(), nom.getText(), dir.getText()))){
            System.out.println("Guardado Correctamente");
            control.cargarDistribuidores();
            stage.close();
        }else
            System.out.println("Error al Guardar");
    }
}