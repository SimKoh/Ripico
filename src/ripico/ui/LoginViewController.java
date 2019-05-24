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
import ripico.service.exception.InvalidCredentialsException;

import java.io.IOException;

public class LoginViewController {


    @FXML
    public Label label_errorMessage;
    @FXML
    TextField tfUsername;
    @FXML
    TextField tfPassword;


    @FXML
    void initialize() {

    }

    @FXML
    void login(ActionEvent event) {
        MitarbeiterService ms = ServiceFactory.createService(MitarbeiterService.class);

        String username = tfUsername.getText();
        String password = tfPassword.getText();

        try {
            Mitarbeiter mitarbeiter = ms.login(username, password);
            if (mitarbeiter == null) throw new InvalidCredentialsException("Mitarbeiter ist null, Error!");

        } catch (InvalidCredentialsException e) {
            label_errorMessage.setText("Login nicht möglich! Benutzer/Passwort fehlerhaft?");
            label_errorMessage.setVisible(true);
            e.printStackTrace();
            return;
        }

        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../../resources/AddGameView.fxml"));
            // Get MainView RootElement
            Parent root = loader.load();

            AddGameController controller = loader.getController();

            Stage stage = new Stage(); // Neues Fenster
            stage.setTitle("Ripico");
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();


            // Set Icon
            stage.getIcons().add(new Image(AppStart.class.getResourceAsStream("../../resources/imgs/icon.png")));
            stage.setResizable(false);
            ((Node) (event.getSource())).getScene().getWindow().hide();


        } catch (IOException e) {
            System.out.println("ERORR HJUNGE");
            e.printStackTrace();
        }
    }
}
