package ripico.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import ripico.api.domain.Spiel;
import ripico.api.domain.Wette;
import ripico.api.domain.WetteBuilder;
import ripico.api.domain.enums.QuotenArt;

import java.util.logging.Logger;


public class BetViewController {
    private static final Logger logger = Logger.getLogger(BetViewController.class.getName());
    private static StatusLabelManager statusManager;
    private MainViewController mainView;
    private Spiel spiel;
    private ToggleGroup toggleGroup;

    @FXML
    private Label label_errorMessage;
    @FXML
    private ImageView aLogo;
    @FXML
    private ImageView hLogo;
    @FXML
    private Label mannschaftHeimLabel;
    @FXML
    private Label mannschaftAuswaertsLabel;
    @FXML
    private Label hQuote;
    @FXML
    private Label aQuote;
    @FXML
    private Label uQuote;
    @FXML
    private RadioButton radioHeim;
    @FXML
    private RadioButton radioAuswaerts;
    @FXML
    private RadioButton radioUnentschieden;

    @FXML
    void initialize() {
        statusManager = new StatusLabelManager(label_errorMessage);
        toggleGroup = new ToggleGroup();
        radioHeim.setToggleGroup(toggleGroup);
        radioAuswaerts.setToggleGroup(toggleGroup);
        radioUnentschieden.setToggleGroup(toggleGroup);
    }

    public void setSpiel(Spiel spiel) {
        this.spiel = spiel;
        this.mannschaftAuswaertsLabel.setText(spiel.getMannschaftAuswaerts().getMannschaftsName());
        this.mannschaftHeimLabel.setText(spiel.getMannschaftHeim().getMannschaftsName());
        this.hLogo.setImage(new Image(getClass().getResource(spiel.getMannschaftHeim().getMannschaftLogo()).toString(), true));
        this.aLogo.setImage(new Image(getClass().getResource(spiel.getMannschaftAuswaerts().getMannschaftLogo()).toString(), true));
        this.hQuote.setText(spiel.getQuoten().get(QuotenArt.HEIM).toString());
        this.aQuote.setText(spiel.getQuoten().get(QuotenArt.AUSWAERTS).toString());
        this.uQuote.setText(spiel.getQuoten().get(QuotenArt.UNENTSCHIEDEN).toString());

    }

    @FXML
    public void addBetToWettschein(ActionEvent actionEvent) {
        RadioButton selectedRadioButton = (RadioButton) toggleGroup.getSelectedToggle();
        if(selectedRadioButton==null) {
            statusManager.setFailureMessage("JUNGE WÄHL NEN TEAM AUS!!!");
            return;
        }
        statusManager.clearStatus();

        String radioButtonId = selectedRadioButton.getId();
        Wette wette;
        switch (radioButtonId) {
            case "radioHeim":
                wette = WetteBuilder.newWette().withSpiel(spiel).withGesetzteWette(QuotenArt.HEIM).build();
                break;
            case "radioUnentschieden":
                wette = WetteBuilder.newWette().withSpiel(spiel).withGesetzteWette(QuotenArt.UNENTSCHIEDEN).build();
                break;
            case "radioAuswaerts":
                wette = WetteBuilder.newWette().withSpiel(spiel).withGesetzteWette(QuotenArt.AUSWAERTS).build();
                break;
            default:
                logger.severe("Wette kann nicht erstellt werden, RadioButton nicht erkannt!");
                return;
        }
        mainView.addWetteToMyList(wette);
        // Close Window after Click
        ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
    }

    @FXML
    public void cancelBet(ActionEvent actionEvent) {
        ((Node)(actionEvent.getSource())).getScene().getWindow().hide();
    }

    public void setMainView(MainViewController mainViewController) {
        this.mainView = mainViewController;
    }
}
