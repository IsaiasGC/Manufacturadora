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
import models.Sucursal;

public class SucursalesController implements Initializable {

    @FXML
    TableView tblSucursales;
    @FXML
    Button btnNuevo, btnModificar, btnEliminar;

    ObservableList<Sucursal> sucursales;
    public static Sucursal sucursal;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        inicializar();
        desactivar();
        cargarSucursales();
        tblSucursales.setOnMouseClicked(selection);
        btnNuevo.setOnAction(nuevo);
        btnModificar.setOnAction(editar);
        btnEliminar.setOnAction(eliminar);
    }

    private void inicializar() {
        TableColumn colCveSuc = new TableColumn("Clave Sucursal");
        colCveSuc.setCellValueFactory(new PropertyValueFactory<>("cveSuc"));
        colCveSuc.setPrefWidth(120);
        TableColumn colNombre = new TableColumn("Nombre");
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colNombre.setPrefWidth(300);
        TableColumn colDireccion = new TableColumn("Direccion");
        colDireccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));
        colDireccion.setPrefWidth(300);
        TableColumn colEncarg = new TableColumn("Encargado");
        colEncarg.setCellValueFactory(new PropertyValueFactory<>("encargado"));
        colEncarg.setPrefWidth(80);

        tblSucursales.getColumns().addAll(colCveSuc, colNombre, colDireccion, colEncarg);
    }

    public void desactivar() {
        btnModificar.setDisable(true);
        btnEliminar.setDisable(true);
    }

    public void cargarSucursales() {
        sucursales = Main.manager.fetchAllSucursales();
        tblSucursales.setItems(sucursales);
    }

    private void viewNuevo() {
        try {
            NuevaSucursalController.control=this;
            Stage window = new Stage();
            NuevaSucursalController.stage=window;
            window.setTitle("Nuevo Sucursal");
            Parent nuevo = FXMLLoader.load(getClass().getResource("/views/nuevaSucursal.fxml"));
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
            ModificarSucursalController.control=this;
            Stage window = new Stage();
            ModificarSucursalController.stage=window;
            window.setTitle("Modificar Sucursal");
            Parent modificar = FXMLLoader.load(getClass().getResource("/views/modificarSucursal.fxml"));
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
            sucursal = sucursales.get(tblSucursales.getSelectionModel().getSelectedIndex());
            viewModificar();
        }
    };
    
    EventHandler<ActionEvent> eliminar = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            desactivar();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Sucursal");
            alert.setContentText("Esta seguro que desea eliminarlo");
            Optional<ButtonType> salir = alert.showAndWait();
            if (salir.get() == ButtonType.OK) {
                alert.setAlertType(Alert.AlertType.INFORMATION);
                if (Main.manager.delete(sucursales.get(tblSucursales.getSelectionModel().getSelectedIndex()))) {
                    alert.setContentText("La Sucursal se Elimino Correctamente");
                    alert.show();
                    cargarSucursales();
                } else {
                    alert.setContentText("La Sucursal No se Pudo eliminar");
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