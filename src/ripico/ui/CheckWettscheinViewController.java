package ripico.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import ripico.api.ServiceFactory;
import ripico.api.exception.ResourceNotFoundException;
import ripico.api.service.WettscheinService;

import java.util.logging.Level;
import java.util.logging.Logger;

public class CheckWettscheinViewController {
    private static final Logger logger = Logger.getLogger(CheckWettscheinViewController.class.getName());
    private WettscheinService ws;
    private StatusLabelManager statusManager;

    @FXML
    private Label labelResult;
    @FXML
    private TextField tfWettscheinId;
    @FXML
    public void initialize() { statusManager = new StatusLabelManager(labelResult); }

    public CheckWettscheinViewController() { ws = ServiceFactory.createService(WettscheinService.class); }

    public void pruefeWettschein(ActionEvent actionEvent) {
        int userWettscheinId = 0;
        try {
            userWettscheinId = Integer.parseInt(tfWettscheinId.getText());
            if (userWettscheinId < 0) {
                statusManager.setFailureMessage("Wettschein-ID kann nicht kleiner als 0 sein");
                return;
            }
            // TODO Wettschein zurückgeben anstatt
            if (ws.pruefeWettschein(userWettscheinId)) {
                statusManager.setSuccessMessage("Du hast gewonnen! Auszahlung dauert i.d.R. 24 Wochen");
            } else {
                statusManager.setFailureMessage("Du hast verloren! Spast");
            }
        } catch (NumberFormatException e) {
            statusManager.setFailureMessage("Falsches ID-Format");
            logger.log(Level.SEVERE, e.getMessage(), e);
            return;
        } catch (ResourceNotFoundException e) {
            statusManager.setFailureMessage("ID nicht gefunden! Tja, Geld ist wohl weg!");
            logger.log(Level.SEVERE, e.getMessage(), e);
            return;
        }
    }
}
