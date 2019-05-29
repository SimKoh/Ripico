package ripico.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import ripico.api.ServiceFactory;
import ripico.api.exception.ResourceNotFoundException;
import ripico.api.service.WettscheinService;

public class CheckWettscheinViewController {
    public Label labelResult;
    public TextField tfWettscheinId;
    private WettscheinService ws;

    @FXML
    public void initialize() {
        ws = ServiceFactory.createService(WettscheinService.class);
    }

    public void pruefeWettschein(ActionEvent actionEvent) {
        int userWettscheinId = 0;
        try {
            userWettscheinId = Integer.parseInt(tfWettscheinId.getText());
            if(userWettscheinId<0) {
                showErrorMessage("Wettschein-ID kann nicht kleiner als 0 sein");
                return;
            }
            if (ws.pruefeWettschein(userWettscheinId)) {
                showWinMessage();
            } else{
                showLoseMessage();
            }
        } catch (NumberFormatException e) {
            showErrorMessage("Falsches ID-Format");
            return;
        } catch (ResourceNotFoundException e) {
            showErrorMessage("ID nicht gefunden! Tja, Geld ist wohl weg!");
            e.printStackTrace();
            return;
        }
    }

    private void showWinMessage(){
        labelResult.setText("Du hast gewonnen! Auszahlung dauert i.d.R. 24 Wochen");
        labelResult.setVisible(true);
        labelResult.getStyleClass().add("successMessage");
    }

    private void showLoseMessage(){
        labelResult.setText("Du hast verloren! Spast");
        labelResult.setVisible(true);
        labelResult.getStyleClass().add("errorMessage");
    }
    private void showErrorMessage(String msg){
        labelResult.setText(msg);
        labelResult.setVisible(true);
        labelResult.getStyleClass().add("errorMessage");
    }
}
