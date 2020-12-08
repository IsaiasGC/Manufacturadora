package controllers;

import BD.DBManager;
import BD.MySQL;
import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Main extends Application {

    public static Stage primary;
    public static BorderPane rootPane;
    public static DBManager manager;

    @Override
    public void start(Stage primaryStage) throws Exception {
        primary = primaryStage;
        rootPane = new BorderPane();
        manager = new DBManager(MySQL.getConnection());
        primary.setResizable(false);
        viewPantallaInicial();
    }

    public void viewPantallaInicial() {
        try {
            Parent menuContent = FXMLLoader.load(getClass().getResource("/views/menu.fxml"));
            Parent principalContent = FXMLLoader.load(getClass().getResource("/views/pantallaInicial.fxml"));

            rootPane.setTop(menuContent);
            rootPane.setCenter(principalContent);
            primary.setScene(new Scene(rootPane, 1000, 500));
            primary.setTitle("Manufacturadora Automotriz");
            primary.show();
        } catch (IOException ex) {
            System.err.println(ex.getMessage());
            System.exit(0);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}