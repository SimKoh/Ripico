package ripico.ui;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
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
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../../resources/MainView.fxml"));
            // Get MainView RootElement
            Parent root = loader.load();

            MainViewController controller = loader.getController();
            controller.setMainApp(mainApp);

            Stage stage = new Stage(); // Neues Fenster
            stage.setTitle("Ripico Sportwetten");
            stage.setScene(new Scene(root));
            stage.show();


            // Set Icon
            stage.getIcons().add(new Image(AppStart.class.getResourceAsStream( "../../resources/imgs/icon.png" )));
            stage.setResizable(false);
            // Hide/Close TOS-Window
            ((Node)(event.getSource())).getScene().getWindow().hide();
        } catch (IOException e) {
            System.out.println("Fehler 46:");
            e.printStackTrace();
        }
    }



    public void setMainApp(AppStart app){
        this.mainApp = app;
        System.out.println("IdleViewController: App loaded!");
    }

}
