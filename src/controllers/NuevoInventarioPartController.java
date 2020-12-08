package controllers;

import static controllers.ModificarInventarioPartController.control;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.InventarioParte;

public class NuevoInventarioPartController implements Initializable {

    @FXML
    TextField cve, fech, part, cant;
    static InventariosPartController control;
    static Stage stage;
    @Override
    public void initialize(URL url, ResourceBundle rb) {

    }

    @FXML
    private void Guardar(ActionEvent event) {
        try {
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            java.util.Date fecha = formato.parse(fech.getText());

            if(Main.manager.insert(new InventarioParte(cve.getText(), new java.sql.Date(fecha.getTime()), part.getText(), Integer.parseInt(cant.getText())))){
                System.out.println("Guardado Correctamente");
                control.cargarInventarios();
                stage.close();
            }
            else
                System.out.println("Error al Guardar");
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}