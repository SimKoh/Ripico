package ripico.ui;

import javafx.event.ActionEvent;
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
    public TextField tfBetrag;
    public Label label_errorMessage;

    public void betragEinzahlen(ActionEvent actionEvent) {
        if (tfBetrag.getText().isEmpty() || tfBetrag.getText() == null || Float.parseFloat(tfBetrag.getText()) < 1) {
            label_errorMessage.setText("Bruder, was ist denn das!? Komm, ein Taui geht doch noch");
            tfBetrag.setText("1000.0");
            label_errorMessage.getStyleClass().add("errorMessage");
            label_errorMessage.setVisible(true);
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

            // Set Icon
            stage.getIcons().add(new Image(AppStart.class.getResourceAsStream("../../resources/imgs/icon.png")));
//            stage.setResizable(false);
            // Hide/Close TOS-Window
            ((Node) (actionEvent.getSource())).getScene().getWindow().hide();

        } catch (LoadException le) {
            logger.log(Level.SEVERE, "Fehler 47:", le);
            label_errorMessage.setText("Laden der Applikation nicht möglich, vielleicht läuft der SQL-Server nicht?");
            label_errorMessage.setVisible(true);
            label_errorMessage.getStyleClass().add("errorMessage");
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Fehler 46:", e);

            e.printStackTrace();
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
