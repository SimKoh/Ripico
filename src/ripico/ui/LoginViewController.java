package ripico.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ripico.api.ServiceFactory;
import ripico.api.domain.Mitarbeiter;
import ripico.api.service.MitarbeiterService;
import ripico.api.exception.InvalidCredentialsException;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class LoginViewController {
    private static final Logger logger = Logger.getLogger(LoginViewController.class.getName());
    private StatusLabelManager statusManager;
    @FXML
    private Label label_errorMessage;
    @FXML
    private TextField tfUsername;
    @FXML
    private TextField tfPassword;
    @FXML
    void initialize() {
        statusManager = new StatusLabelManager(label_errorMessage);
    }

    @FXML
    void login(ActionEvent event) {
        MitarbeiterService ms = ServiceFactory.createService(MitarbeiterService.class);
        String username = tfUsername.getText();
        String password = tfPassword.getText();

        try {
            Mitarbeiter mitarbeiter = ms.login(username, password);
            if (mitarbeiter == null) throw new InvalidCredentialsException();
        } catch (InvalidCredentialsException e) {
            statusManager.setFailureMessage("Login nicht möglich! Benutzer/Passwort fehlerhaft?");
            logger.log(Level.SEVERE, e.getMessage(), e);
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../../resources/AddGameView.fxml"));
            // Get MainView RootElement
            Parent root = loader.load();

            AddGameController controller = loader.getController();

            Stage stage = new Stage(); // Neues Fenster
            stage.setTitle("Ripico Spiel hinzufügen");
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();


            // Set Icon
            stage.getIcons().add(new Image(AppStart.class.getResourceAsStream("../../resources/imgs/icon.png")));
            stage.setResizable(false);
            ((Node) (event.getSource())).getScene().getWindow().hide();


        } catch (IOException e) {
            statusManager.setFailureMessage("IOException aufgetreten!");
            logger.log(Level.SEVERE, e.getMessage(), e);
            return;
        }
    }
}
