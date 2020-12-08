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
import models.InventarioParte;

public class InventariosPartController implements Initializable {

    @FXML
    TableView tblInventarios;
    @FXML
    Button btnNuevo, btnModificar, btnEliminar;

    ObservableList<InventarioParte> inventarios;
    public static InventarioParte inventario;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        inicializar();
        desactivar();
        cargarInventarios();
        tblInventarios.setOnMouseClicked(selection);
        btnNuevo.setOnAction(nuevo);
        btnModificar.setOnAction(editar);
        btnEliminar.setOnAction(eliminar);
    }

    private void inicializar() {
        TableColumn colCveInv = new TableColumn("Clave Inventario");
        colCveInv.setCellValueFactory(new PropertyValueFactory<>("cveInv"));
        colCveInv.setPrefWidth(200);
        TableColumn colCvePart = new TableColumn("Clave Parte");
        colCvePart.setCellValueFactory(new PropertyValueFactory<>("cvePart"));
        colCvePart.setPrefWidth(200);
        TableColumn colFecha = new TableColumn("Fecha");
        colFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        colFecha.setPrefWidth(200);
        TableColumn colCantidad = new TableColumn("Cantidad");
        colCantidad.setCellValueFactory(new PropertyValueFactory<>("cantidad"));
        colCantidad.setPrefWidth(200);

        tblInventarios.getColumns().addAll(colCveInv, colCvePart, colFecha, colCantidad);
    }

    public void desactivar() {
        btnModificar.setDisable(true);
        btnEliminar.setDisable(true);
    }

    public void cargarInventarios() {
        inventarios = Main.manager.fetchAllInventariosPart();
        tblInventarios.setItems(inventarios);
    }

    private void viewNuevo() {
        try {
            NuevoInventarioPartController.control=this;
            Stage window = new Stage();
            NuevoInventarioPartController.stage=window;
            window.setTitle("Nuevo Inventario Parte");
            Parent nuevo = FXMLLoader.load(getClass().getResource("/views/nuevoInventarioPart.fxml"));
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
            ModificarInventarioPartController.control=this;
            Stage window = new Stage();
            ModificarInventarioPartController.stage=window;
            window.setTitle("Modificar Inventario Parte");
            Parent modificar = FXMLLoader.load(getClass().getResource("/views/modificarInventarioPart.fxml"));
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
            inventario = inventarios.get(tblInventarios.getSelectionModel().getSelectedIndex());
            viewModificar();
        }
    };
    
    EventHandler<ActionEvent> eliminar = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            desactivar();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Inventario Parte");
            alert.setContentText("Esta seguro que desea eliminarlo");
            Optional<ButtonType> salir = alert.showAndWait();
            if (salir.get() == ButtonType.OK) {
                alert.setAlertType(Alert.AlertType.INFORMATION);
                if (Main.manager.delete(inventarios.get(tblInventarios.getSelectionModel().getSelectedIndex()))) {
                    alert.setContentText("El Inventario se Elimino Correctamente");
                    alert.show();
                    cargarInventarios();
                } else {
                    alert.setContentText("El Inventario No se Pudo eliminar");
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