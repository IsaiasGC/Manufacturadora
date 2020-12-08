package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.MenuItem;
import javafx.stage.Modality;
import javafx.stage.Stage;

public class MenuController implements Initializable {

    @FXML
    MenuItem mnSalir, mnEmpleados, mnDepartamentos, mnInventariosMat, mnInventariosPart, mnClientes, mnSucursales, mnDistribuidores, mnProveedores, menuItemHelpAbout;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        mnSalir.setOnAction(EventSalir);
        mnEmpleados.setOnAction(EventMenu);
        mnDepartamentos.setOnAction(EventMenu);
        mnInventariosMat.setOnAction(EventMenu);
        mnInventariosPart.setOnAction(EventMenu);
        mnClientes.setOnAction(EventMenu);
        mnSucursales.setOnAction(EventMenu);
        mnDistribuidores.setOnAction(EventMenu);
        mnProveedores.setOnAction(EventMenu);
        menuItemHelpAbout.setOnAction(EventMenu);
    }

    private Parent viewEmpleados() throws IOException {
        Parent content = FXMLLoader.load(getClass().getResource("/views/empleados.fxml"));
        return content;
    }

    private Parent viewDepartamentos() throws IOException {
        Parent content = FXMLLoader.load(getClass().getResource("/views/departamentos.fxml"));
        return content;
    }

    private Parent viewInventariosMat() throws IOException {
        Parent content = FXMLLoader.load(getClass().getResource("/views/inventarios_mat.fxml"));
        return content;
    }

    private Parent viewInventariosPart() throws IOException {
        Parent content = FXMLLoader.load(getClass().getResource("/views/inventarios_part.fxml"));
        return content;
    }

    private Parent viewClientes() throws IOException {
        Parent content = FXMLLoader.load(getClass().getResource("/views/clientes.fxml"));
        return content;
    }

    private Parent viewSucursales() throws IOException {
        Parent content = FXMLLoader.load(getClass().getResource("/views/sucursales.fxml"));
        return content;
    }

    private Parent viewDistribuidores() throws IOException {
        Parent content = FXMLLoader.load(getClass().getResource("/views/distribuidores.fxml"));
        return content;
    }

    private Parent viewProveedores() throws IOException {
        Parent content = FXMLLoader.load(getClass().getResource("/views/proveedores.fxml"));
        return content;
    }

    EventHandler<ActionEvent> EventSalir = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Salir");
            alert.setContentText("Esta Seguro que desea Salir?");
            Optional<ButtonType> op = alert.showAndWait();
            if (op.get() == ButtonType.OK) {
                alert.setAlertType(Alert.AlertType.INFORMATION);
                alert.setContentText("Hasta Luego...");
                alert.showAndWait();
                System.exit(0);
            }
        }
    };

    EventHandler<ActionEvent> EventMenu = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            System.out.println(event.getSource());
            try {
                if (event.getSource() == mnEmpleados) {
                    Main.rootPane.setCenter(viewEmpleados());
                } else if (event.getSource() == mnDepartamentos) {
                    Main.rootPane.setCenter(viewDepartamentos());
                } else if (event.getSource() == mnInventariosMat) {
                    Main.rootPane.setCenter(viewInventariosMat());
                } else if (event.getSource() == mnInventariosPart) {
                    Main.rootPane.setCenter(viewInventariosPart());
                } else if (event.getSource() == mnClientes) {
                    Main.rootPane.setCenter(viewClientes());
                } else if (event.getSource() == mnSucursales) {
                    Main.rootPane.setCenter(viewSucursales());
                } else if (event.getSource() == mnDistribuidores) {
                    Main.rootPane.setCenter(viewDistribuidores());
                } else if (event.getSource() == mnProveedores) {
                    Main.rootPane.setCenter(viewProveedores());
                } else if (event.getSource() == menuItemHelpAbout) {
                    Stage window = new Stage();
                    window.setTitle("Acerca de...");
                    Parent nuevo = FXMLLoader.load(getClass().getResource("/views/acercaDe.fxml"));
                    Scene scene = new Scene(nuevo);
                    window.setScene(scene);
                    window.initOwner(Main.primary);
                    window.initModality(Modality.APPLICATION_MODAL);
                    window.show();
                }
            } catch (IOException ex) {
                Logger.getLogger(MenuController.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    };
}
