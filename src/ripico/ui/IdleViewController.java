package ripico.ui;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class IdleViewController {
    private static final Logger logger = Logger.getLogger(IdleViewController.class.getName());

    @FXML
    public void openCheckWettscheinView(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../../resources/CheckWettscheinView.fxml"));
            // Get MainView RootElement
            Parent root = loader.load();

            CheckWettscheinViewController controller = loader.getController();

            Stage stage = new Stage(); // Neues Fenster
            stage.setTitle("Wettschein prüfen");
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();

            // Set Icon
            stage.getIcons().add(new Image(AppStart.class.getResourceAsStream("../../resources/imgs/icon.png")));
            stage.setResizable(false);
        } catch (IOException e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
        }
    }

    @FXML
    public void openAddCurrencyView(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../../resources/AddCurrencyView.fxml"));
            // Get MainView RootElement
            Parent root = loader.load();

            AddCurrencyViewController controller = loader.getController();

            Stage stage = new Stage(); // Neues Fenster
            stage.setTitle("Ripico Sportwetten - Einzahlung");
            stage.setScene(new Scene(root));
            stage.show();

            // Set Icon
            stage.getIcons().add(new Image(AppStart.class.getResourceAsStream("../../resources/imgs/icon.png")));
            stage.setResizable(false);
            // Hide/Close TOS-Window
            ((Node) (event.getSource())).getScene().getWindow().hide();
        } catch (IOException e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
        }
    }

    @FXML
    void openLoginView(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../../resources/LoginView.fxml"));
            // Get MainView RootElement
            Parent root = loader.load();

            LoginViewController controller = loader.getController();

            Stage stage = new Stage(); // Neues Fenster
            stage.setTitle("Ripico Login");
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();


            // Set Icon
            stage.getIcons().add(new Image(AppStart.class.getResourceAsStream("../../resources/imgs/icon.png")));
            stage.setResizable(false);

        } catch (IOException e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
        }
    }
}
