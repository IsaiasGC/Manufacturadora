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
import models.Cliente;

public class ClientesController implements Initializable {

    @FXML
    TableView tblClientes;

    @FXML
    Button btnNuevo, btnModificar, btnEliminar;

    ObservableList<Cliente> clientes;
    public static Cliente cliente;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        inicializar();
        desactivar();
        cargarClientes();
        tblClientes.setOnMouseClicked(selection);
        btnNuevo.setOnAction(nuevo);
        btnModificar.setOnAction(editar);
        btnEliminar.setOnAction(eliminar);
    }

    private void inicializar() {
        TableColumn colNoCte = new TableColumn("No. Cliente");
        colNoCte.setCellValueFactory(new PropertyValueFactory<>("noCte"));
        colNoCte.setPrefWidth(100);
        TableColumn colNombre = new TableColumn("Nombre");
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colNombre.setPrefWidth(400);
        TableColumn colDireccion = new TableColumn("Direccion");
        colDireccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));
        colDireccion.setPrefWidth(300);

        tblClientes.getColumns().addAll(colNoCte, colNombre, colDireccion);
    }

    public void cargarClientes() {
        clientes = Main.manager.fetchAllClientes();
        tblClientes.setItems(clientes);
    }

    public void desactivar() {
        btnModificar.setDisable(true);
        btnEliminar.setDisable(true);
    }

    private void viewNuevo() {
        try {
            NuevoClienteController.control=this;
            Stage window = new Stage();
            NuevoClienteController.stage=window;
            window.setTitle("Nuevo Cliente");
            Parent nuevo = FXMLLoader.load(getClass().getResource("/views/nuevoCliente.fxml"));
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
            ModificarClienteController.control=this;
            Stage window = new Stage();
            ModificarClienteController.stage=window;
            window.setTitle("Modificar Cliente");
            Parent modificar = FXMLLoader.load(getClass().getResource("/views/modificarCliente.fxml"));
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
            cliente = clientes.get(tblClientes.getSelectionModel().getSelectedIndex());
            viewModificar();
        }
    };
    
    EventHandler<ActionEvent> eliminar = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            desactivar();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Cliente");
            alert.setContentText("Esta seguro que desea eliminarlo");
            Optional<ButtonType> salir = alert.showAndWait();
            if (salir.get() == ButtonType.OK) {
                alert.setAlertType(Alert.AlertType.INFORMATION);
                if (Main.manager.delete(clientes.get(tblClientes.getSelectionModel().getSelectedIndex()))) {
                    alert.setContentText("El Cliente se Elimino Correctamente");
                    alert.show();
                    cargarClientes();
                } else {
                    alert.setContentText("El Cliente No se Pudo eliminar");
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