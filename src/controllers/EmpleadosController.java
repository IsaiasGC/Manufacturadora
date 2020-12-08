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
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.stage.Modality;
import javafx.stage.Stage;
import models.Empleado;

public class EmpleadosController implements Initializable {

    @FXML
    TableView tblEmpleados;
    @FXML
    ComboBox cmbDeparta;
    @FXML
    Button btnNuevo, btnModificar, btnEliminar;

    ObservableList<Empleado> empleados;
    public static Empleado empleado;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        inicializar();
        desactivar();
        cargarEmpleados();
        tblEmpleados.setOnMouseClicked(selection);
        btnNuevo.setOnAction(nuevo);
        btnModificar.setOnAction(editar);
        btnEliminar.setOnAction(eliminar);
    }

    private void inicializar() {
        TableColumn colNoEmp = new TableColumn("No. Empleado");
        colNoEmp.setCellValueFactory(new PropertyValueFactory<>("noEmp"));
        colNoEmp.setPrefWidth(120);
        TableColumn colNombre = new TableColumn("Nombre");
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colNombre.setPrefWidth(300);
        TableColumn colSexo = new TableColumn("Sexo");
        colSexo.setCellValueFactory(new PropertyValueFactory<>("sexo"));
        colSexo.setPrefWidth(120);
        TableColumn colEdad = new TableColumn("Edad");
        colEdad.setCellValueFactory(new PropertyValueFactory<>("edad"));
        colEdad.setPrefWidth(120);
        TableColumn colDep = new TableColumn("Departamento");
        colDep.setCellValueFactory(new PropertyValueFactory<>("cveDep"));
        colDep.setPrefWidth(120);
        TableColumn colPuesto = new TableColumn("Puesto");
        colPuesto.setCellValueFactory(new PropertyValueFactory<>("cveTipo"));
        colPuesto.setPrefWidth(120);

        cmbDeparta.getItems().clear();
        cmbDeparta.getItems().addAll("Pintura", "Ensamblado", "Motor");

        tblEmpleados.getColumns().addAll(colNoEmp, colNombre, colSexo, colEdad, colDep, colPuesto);
    }

    public void cargarEmpleados() {
        empleados = Main.manager.fetchAllEmpleados();
        tblEmpleados.setItems(empleados);
    }

    private void cargarEmpleados(String cveDep) {

    }

    private void viewNuevo() {
        try {
            NuevoEmpleadoController.control = this;
            Stage window = new Stage();
            NuevoEmpleadoController.stage = window;
            window.setTitle("Nuevo Empleado");
            Parent nuevo = FXMLLoader.load(getClass().getResource("/views/nuevoEmpleado.fxml"));
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
            ModificarEmpleadoController.control=this;
            Stage window = new Stage();
            NuevoEmpleadoController.stage = window;
            window.setTitle("Modificar Empleado");
            Parent modificar = FXMLLoader.load(getClass().getResource("/views/modificarEmpleado.fxml"));
            Scene scene = new Scene(modificar);
            window.setScene(scene);
            window.initOwner(Main.primary);
            window.initModality(Modality.APPLICATION_MODAL);
            window.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void desactivar() {
        btnModificar.setDisable(true);
        btnEliminar.setDisable(true);
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
            empleado = empleados.get(tblEmpleados.getSelectionModel().getSelectedIndex());
            viewModificar();
        }
    };

    EventHandler<ActionEvent> eliminar = new EventHandler<ActionEvent>() {
        @Override
        public void handle(ActionEvent event) {
            desactivar();
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Empleado");
            alert.setContentText("Esta seguro que desea eliminarlo");
            Optional<ButtonType> salir = alert.showAndWait();
            if (salir.get() == ButtonType.OK) {
                alert.setAlertType(Alert.AlertType.INFORMATION);
                if (Main.manager.delete(empleados.get(tblEmpleados.getSelectionModel().getSelectedIndex()))) {
                    alert.setContentText("El Empleado se Elimino Correctamente");
                    alert.show();
                    cargarEmpleados();
                } else {
                    alert.setContentText("El Empleado No se Pudo eliminar");
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
