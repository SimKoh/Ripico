package ripico.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.LoadException;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AddCurrencyViewController {
    private static final Logger logger = Logger.getLogger(AddCurrencyViewController.class.getName());
    private StatusLabelManager statusManager;

    @FXML
    private Label label_errorMessage;
    @FXML
    private TextField tfBetrag;

    @FXML
    public void initialize() {
        statusManager = new StatusLabelManager(label_errorMessage);
    }

    public void betragEinzahlen(ActionEvent actionEvent) {
        if (tfBetrag.getText().isEmpty() || tfBetrag.getText() == null || Float.parseFloat(tfBetrag.getText()) < 1) {
            tfBetrag.setText("1000.0");
            statusManager.setFailureMessage("Bruder, was ist denn das!? Komm, ein Taui geht doch noch");
            return;
        }
        if(Float.parseFloat(tfBetrag.getText())>99999){
            tfBetrag.setText("1000.0");
            statusManager.setFailureMessage("Bruder, wir wollen dich nicht arm machen.. Mach nicht über 99.999€");
            return;
        }

        // Load MainApp on Success
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../../resources/MainView.fxml"));
            // Get MainView RootElement
            Parent root = loader.load();

            // Set Variables
            MainViewController controller = loader.getController();
            controller.setGuthaben(Float.parseFloat(tfBetrag.getText()));
            controller.init();
            Stage stage = new Stage(); // Neues Fenster
            stage.setTitle("Ripico Sportwetten");
            stage.setScene(new Scene(root));
            stage.show();
            stage.setResizable(false);

            // Set Icon
            stage.getIcons().add(new Image(AppStart.class.getResourceAsStream("../../resources/imgs/icon.png")));
            stage.setResizable(false);
            // Hide/Close TOS-Window
            ((Node) (actionEvent.getSource())).getScene().getWindow().hide();

        } catch (LoadException le) {
            logger.log(Level.SEVERE, "Fehler 47:", le);
            statusManager.setFailureMessage("Laden der Applikation nicht möglich, vielleicht läuft der SQL-Server nicht?");
        } catch (IOException e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
            statusManager.setFailureMessage("Laden der Applikation nicht möglich");
        }
    }

    public void btnAdd(ActionEvent actionEvent) {
        Button sourceBtn = (Button) actionEvent.getSource();
        switch (sourceBtn.getId()) {
            case "btnAdd1":
                tfBetrag.setText("100");
                break;
            case "btnAdd2":
                tfBetrag.setText("250");
                break;
            case "btnAdd3":
                tfBetrag.setText("1000");
                break;
            case "btnAdd4":
                tfBetrag.setText("10000");
                break;
            default:
                break;
        }
    }
}
