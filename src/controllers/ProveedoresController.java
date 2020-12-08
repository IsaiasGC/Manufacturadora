package controllers;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import models.Proveedor;

public class ProveedoresController implements Initializable {

    @FXML
    TableView tblProveedores;
    @FXML
    Button btnNuevo, btnModificar, btnEliminar;

    ObservableList<Proveedor> proveedores;
    public static Proveedor proveedor;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        inicializar();
        desactivar();
        cargarProveedores();
        tblProveedores.setOnMouseClicked(selection);
        btnNuevo.setOnAction(nuevo);
        btnModificar.setOnAction(editar);
        btnEliminar.setOnAction(eliminar);
    }

    private void inicializar() {
        TableColumn colCveProv = new TableColumn("Clave Proveedor");
        colCveProv.setCellValueFactory(new PropertyValueFactory<>("cveProv"));
        colCveProv.setPrefWidth(120);
        TableColumn colNombre = new TableColumn("Nombre");
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colNombre.setPrefWidth(300);
        TableColumn colTel = new TableColumn("Telefono");
        colTel.setCellValueFactory(new PropertyValueFactory<>("telefono"));
        colTel.setPrefWidth(100);
        TableColumn colDireccion = new TableColumn("Direccion");
        colDireccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));
        colDireccion.setPrefWidth(280);

        tblProveedores.getColumns().addAll(colCveProv, colNombre, colTel, colDireccion);
    }

    public void desactivar() {
        btnModificar.setDisable(true);
        btnEliminar.setDisable(true);
    }

    public void cargarProveedores() {
        proveedores = Main.manager.fetchAllProveedores();
        tblProveedores.setItems(proveedores);
    }

    private void viewNuevo() {
        try {
            NuevoProveedorController.control=this;
            Stage window = new Stage();
            NuevoProveedorController.stage=window;
            window.setTitle("Nuevo Proveedor");
            Parent nuevo = FXMLLoader.load(getClass().getResource("/views/nuevoProveedor.fxml"));
            Scene scene = new Scene(nuevo);
            window.setScene(scene);
            window.initOwner(Main.primary);
            window.initModality(Modality.APPLICATION_MODAL);
            window.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void viewModificar() {
        try {
            ModificarProveedorController.control=this;
            Stage window = new Stage();
            ModificarProveedorController.stage=window;
            window.setTitle("Modificar Proveedor");
            Parent modificar = FXMLLoader.load(getClass().getResource("/views/modificarProveedor.fxml"));
            Scene scene = new Scene(modificar);
            window.setScene(scene);
            window.initOwner(Main.primary);
            window.initModality(Modality.APPLICATION_MODAL);
            window.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    
    EventHandler<ActionEvent> nuevo = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            desactivar();
            viewNuevo();
        }
    };
    
    EventHandler<ActionEvent> editar = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            desactivar();
            proveedor = proveedores.get(tblProveedores.getSelectionModel().getSelectedIndex());
            viewModificar();
        }
    };
    
    EventHandler<ActionEvent> eliminar = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            desactivar();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Proveedor");
            alert.setContentText("Esta seguro que desea eliminarlo");
            Optional<ButtonType> salir = alert.showAndWait();
            if (salir.get() == ButtonType.OK) {
                alert.setAlertType(Alert.AlertType.INFORMATION);
                if (Main.manager.delete(proveedores.get(tblProveedores.getSelectionModel().getSelectedIndex()))) {
                    alert.setContentText("El Proveedor se Elimino Correctamente");
                    alert.show();
                    cargarProveedores();
                } else {
                    alert.setContentText("El Proveedor No se Pudo eliminar");
                    alert.show();
                }
            }
        }
    };
    
    EventHandler<MouseEvent> selection = new EventHandler<MouseEvent>() {
        @Override
        public void handle(MouseEvent event) {
            btnModificar.setDisable(false);
            btnEliminar.setDisable(false);
        }
    };
}