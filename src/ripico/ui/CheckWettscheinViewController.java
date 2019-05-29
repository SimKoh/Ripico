package ripico.ui;

import javafx.event.ActionEvent;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import ripico.api.ServiceFactory;
import ripico.api.exception.ResourceNotFoundException;
import ripico.api.service.WettscheinService;

public class CheckWettscheinViewController {
    public Label labelResult;
    public TextField tfWettscheinId;

    public void pruefeWettschein(ActionEvent actionEvent) {
        WettscheinService ws = ServiceFactory.createService(WettscheinService.class);
        try {
            if (ws.pruefeWettschein(Integer.parseInt(tfWettscheinId.getText()))) {
                showWinMessage();
            } else{
                showLoseMessage();
            }
        } catch (ResourceNotFoundException e) {
            showErrorMessage("ID nicht gefunden! Tja, Geld ist wohl weg!");
            e.printStackTrace();
        }

    }

    private void showWinMessage(){
        labelResult.setText("Du hast gewonnen! Auszahlung beträgt i.d.R. 24 Wochen");
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
