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
import java.util.logging.Level;
import java.util.logging.Logger;


public class IdleViewController {

    private static final Logger logger = Logger.getLogger(IdleViewController.class.getName());

    private AppStart mainApp;

    @FXML
    private Button btn_agreeTos;

    @FXML
    private void initialize() {
        logger.info("Idle View Initialized");
    }

    public void onClick_agreeTos(ActionEvent event) {
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
            stage.getIcons().add(new Image(AppStart.class.getResourceAsStream("../../resources/imgs/icon.png")));
            stage.setResizable(false);
            // Hide/Close TOS-Window
            ((Node) (event.getSource())).getScene().getWindow().hide();
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Fehler 46:", e);
        }
    }


    public void setMainApp(AppStart app) {
        this.mainApp = app;
        logger.info("IdleViewController: App loaded!");
    }

}
