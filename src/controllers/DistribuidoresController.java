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
import models.Distribuidor;

public class DistribuidoresController implements Initializable {

    @FXML
    TableView tblDistribuidores;
    @FXML
    Button btnNuevo, btnModificar, btnEliminar;

    ObservableList<Distribuidor> distribudores;
    public static Distribuidor distribuidor;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        inicializar();
        desactivar();
        cargarDistribuidores();
        tblDistribuidores.setOnMouseClicked(selection);
        btnNuevo.setOnAction(nuevo);
        btnModificar.setOnAction(editar);
        btnEliminar.setOnAction(eliminar);
    }

    private void inicializar() {
        TableColumn colCveDist = new TableColumn("Clave Distribuidor");
        colCveDist.setCellValueFactory(new PropertyValueFactory<>("cveDist"));
        colCveDist.setPrefWidth(150);
        TableColumn colNombre = new TableColumn("Nombre");
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colNombre.setPrefWidth(350);
        TableColumn colDireccion = new TableColumn("Direccion");
        colDireccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));
        colDireccion.setPrefWidth(300);

        tblDistribuidores.getColumns().addAll(colCveDist, colNombre, colDireccion);
    }

    public void desactivar() {
        btnModificar.setDisable(true);
        btnEliminar.setDisable(true);
    }

    public void cargarDistribuidores() {
        distribudores = Main.manager.fetchAllDistribuidores();
        tblDistribuidores.setItems(distribudores);
    }

    private void viewNuevo() {
        try {
            NuevoDistribuidorController.control=this;
            Stage window = new Stage();
            NuevoDistribuidorController.stage=window;
            window.setTitle("Nuevo Distribuidor");
            Parent nuevo = FXMLLoader.load(getClass().getResource("/views/nuevoDistribuidor.fxml"));
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
            ModificarDistribuidorController.control=this;
            Stage window = new Stage();
            ModificarDistribuidorController.stage=window;
            window.setTitle("Modificar Distribuidor");
            Parent modificar = FXMLLoader.load(getClass().getResource("/views/modificarDistribuidor.fxml"));
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
            distribuidor = distribudores.get(tblDistribuidores.getSelectionModel().getSelectedIndex());
            viewModificar();
        }
    };
    
    EventHandler<ActionEvent> eliminar = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            desactivar();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Distribuidor");
            alert.setContentText("Esta seguro que desea eliminarlo");
            Optional<ButtonType> salir = alert.showAndWait();
            if (salir.get() == ButtonType.OK) {
                alert.setAlertType(Alert.AlertType.INFORMATION);
                if (Main.manager.delete(distribudores.get(tblDistribuidores.getSelectionModel().getSelectedIndex()))) {
                    alert.setContentText("El Distribuidor se Elimino Correctamente");
                    alert.show();
                    cargarDistribuidores();
                } else {
                    alert.setContentText("El Distribuidor No se Pudo eliminar");
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