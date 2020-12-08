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
import models.Departamento;

public class DepartamentosController implements Initializable {

    @FXML
    TableView tblDepartamentos;
    @FXML
    Button btnNuevo, btnModificar, btnEliminar;

    ObservableList<Departamento> deptos;
    public static Departamento depto;

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        inicializar();
        desactivar();
        cargarDepartamentos();
        tblDepartamentos.setOnMouseClicked(selection);
        btnNuevo.setOnAction(nuevo);
        btnModificar.setOnAction(editar);
        btnEliminar.setOnAction(eliminar);
    }

    private void inicializar() {
        TableColumn colCveDep = new TableColumn("Clave Departamento");
        colCveDep.setCellValueFactory(new PropertyValueFactory<>("cveDep"));
        colCveDep.setPrefWidth(150);
        TableColumn colCveSuc = new TableColumn("Clave Sucursal");
        colCveSuc.setCellValueFactory(new PropertyValueFactory<>("cveSuc"));
        colCveSuc.setPrefWidth(100);
        TableColumn colNombre = new TableColumn("Nombre");
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colNombre.setPrefWidth(300);
        TableColumn colDireccion = new TableColumn("Direccion");
        colDireccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));
        colDireccion.setPrefWidth(250);

        tblDepartamentos.getColumns().addAll(colCveDep, colCveSuc, colNombre, colDireccion);
    }

    public void desactivar() {
        btnModificar.setDisable(true);
        btnEliminar.setDisable(true);
    }

    public void cargarDepartamentos() {
        deptos = Main.manager.fetchAllDepartamentos();
        tblDepartamentos.setItems(deptos);
    }

    private void viewNuevo() {
        try {
            NuevoDepartamentoController.control=this;
            Stage window = new Stage();
            NuevoDepartamentoController.stage=window;
            window.setTitle("Nuevo Departamento");
            Parent nuevo = FXMLLoader.load(getClass().getResource("/views/nuevoDepartamento.fxml"));
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
            ModificarDepartamentoController.control=this;
            Stage window = new Stage();
            ModificarDepartamentoController.stage=window;
            window.setTitle("Modificar Departamento");
            Parent modificar = FXMLLoader.load(getClass().getResource("/views/modificarDepartamento.fxml"));
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
            depto = deptos.get(tblDepartamentos.getSelectionModel().getSelectedIndex());
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
                if (Main.manager.delete(deptos.get(tblDepartamentos.getSelectionModel().getSelectedIndex()))) {
                    alert.setContentText("El Cliente se Elimino Correctamente");
                    alert.show();
                    cargarDepartamentos();
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