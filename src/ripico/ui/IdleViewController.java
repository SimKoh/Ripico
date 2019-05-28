package ripico.ui;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class IdleViewController {

    private static final Logger logger = Logger.getLogger(IdleViewController.class.getName());

    private AppStart mainApp;

    @FXML
    private Hyperlink aEinloggen;

    @FXML
    private Hyperlink aWettscheinPruefen;

    @FXML
    private Button btn_agreeTos;

    @FXML
    private void initialize() {
        logger.info("Idle View Initialized");
    }

    // LOAD MAINAPP DIREKT
//    public void onClick_agreeTos(ActionEvent event) {
//        try {
//            FXMLLoader loader = new FXMLLoader(getClass().getResource("../../resources/MainView.fxml"));
//            // Get MainView RootElement
//            Parent root = loader.load();
//
//            MainViewController controller = loader.getController();
//            controller.setMainApp(mainApp);
//
//            Stage stage = new Stage(); // Neues Fenster
//            stage.setTitle("Ripico Sportwetten");
//            stage.setScene(new Scene(root));
//            stage.show();
//
//
//            // Set Icon
//            stage.getIcons().add(new Image(AppStart.class.getResourceAsStream("../../resources/imgs/icon.png")));
//            stage.setResizable(false);
//            // Hide/Close TOS-Window
//            ((Node) (event.getSource())).getScene().getWindow().hide();
//        } catch (IOException e) {
//            logger.log(Level.SEVERE, "Fehler 46:", e);
//        }
//    }

    // LOAD EINZAHLEN DIREKT
    public void onClick_agreeTos(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../../resources/AddCurrencyView.fxml"));
            // Get MainView RootElement
            Parent root = loader.load();

            AddCurrencyViewController controller = loader.getController();

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

    @FXML
    void login(ActionEvent event){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../../resources/LoginView.fxml"));
            // Get MainView RootElement
            Parent root = loader.load();

            LoginViewController controller = loader.getController();

            Stage stage = new Stage(); // Neues Fenster
            stage.setTitle("Ripico");
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();


            // Set Icon
            stage.getIcons().add(new Image(AppStart.class.getResourceAsStream("../../resources/imgs/icon.png")));
            stage.setResizable(false);

        } catch (IOException e) {
            logger.log(Level.SEVERE, "Fehler 46:", e);
        }
    }

    @FXML
    void pruefeWettschein(ActionEvent event){
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../../resources/AddResultView.fxml"));
            // Get MainView RootElement
            Parent root = loader.load();

            AddResultViewController controller = loader.getController();

            Stage stage = new Stage(); // Neues Fenster
            stage.setTitle("Ripico Sportwetten");
            stage.setScene(new Scene(root));
            stage.show();

            controller.init();

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
