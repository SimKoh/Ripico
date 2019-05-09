package ripico.ui;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.io.IOException;


public class IdleViewController {

    private AppStart mainApp;

    @FXML
    private Button btn_agreeTos;

    @FXML
    private void initialize() {
        System.out.println("Idle View Initialized");

    }

    public void onClick_agreeTos(ActionEvent event){
        try {
            FXMLLoader loader = new FXMLLoader();
            // Get MainView RootElement
            loader.setLocation(getClass().getResource("MainView.fxml"));
            Parent root = loader.load();

            Stage stage = new Stage(); // Neues Fenster
            stage.setScene(new Scene(root));
            mainApp.setPrimaryStage(stage);

            /*stage.setTitle("Ripico Sportwetten");
            stage.setScene(scene);
            stage.show();*/



            // Hide/Close TOS-Window
            ((Node)(event.getSource())).getScene().getWindow().hide();
        } catch (IOException e) {
            System.out.println("Fehler 46:");
            System.out.println(e.getMessage());
        }
    }



    public void setMainApp(AppStart app){
        this.mainApp = app;
        System.out.println("IdleViewController: App loaded!");


    }

}
