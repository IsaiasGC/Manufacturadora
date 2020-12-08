package controllers;

import static controllers.NuevoInventarioMatController.control;
import java.net.URL;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import models.InventarioMateria;

public class ModificarInventarioMatController implements Initializable {

    @FXML
    TextField cve, fech, mat, cant;
    static InventariosMatController control;
    static Stage stage;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        cve.setText(InventariosMatController.inventario.getCveInv());
        fech.setText(InventariosMatController.inventario.getFecha().toString());
        mat.setText(InventariosMatController.inventario.getCveMat());
        cant.setText(InventariosMatController.inventario.getCantidad()+"");
        cve.setDisable(true);
    }

    @FXML
    private void Modificar(ActionEvent event) {
        try {
            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            Date fecha = formato.parse(fech.getText());
            
            if(Main.manager.update(new InventarioMateria(cve.getText(), new java.sql.Date(fecha.getTime()), mat.getText(), Integer.parseInt(cant.getText())))){
                System.out.println("Modificado Correctamente");
                control.cargarInventarios();
                stage.close();
            }else
                System.out.println("Error al Modificar");
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}