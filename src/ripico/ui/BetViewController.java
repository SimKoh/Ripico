package ripico.ui;

import javafx.fxml.FXML;
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
    @FXML
    public Label label_errorMessage;
    private Spiel spiel;
    Stage betViewStage;

    @FXML
    AnchorPane anchorPaneRoot;

    @FXML
    ImageView aLogo;

    @FXML
    ImageView hLogo;

    @FXML
    Label mannschaftHeimLabel;

    @FXML
    Label mannschaftAuswaertsLabel;

    @FXML
    Label hQuote;

    @FXML
    Label aQuote;

    @FXML
    Label uQuote;

    @FXML
    RadioButton radioHeim;

    @FXML
    RadioButton radioAuswaerts;

    @FXML
    RadioButton radioUnentschieden;

    @FXML
    TextField tfBetEinsatz;

    @FXML
    Button btnCancelBet;

    @FXML
    Button btnAddBet;

    ToggleGroup toggleGroup;
    private MainViewController mainView;

    @FXML
    void initialize() {
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

    public void addBetToWettschein() {
        RadioButton selectedRadioButton = (RadioButton) toggleGroup.getSelectedToggle();
        if(selectedRadioButton==null) {
            label_errorMessage.setText("JUNGE WÄHL NEN TEAM AUS!!!");
            label_errorMessage.getStyleClass().add("errorMessage");
            label_errorMessage.setVisible(true);
            return;
        }

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

        // AFTER ADD
        betViewStage.close();
    }

    public void cancelBet() {
        betViewStage.close();
    }

    public Stage getBetViewStage() {
        return betViewStage;
    }

    public void setBetViewStage(Stage betViewStage) {
        this.betViewStage = betViewStage;
    }


    public void setMainView(MainViewController mainViewController) {
        this.mainView = mainViewController;
    }
}
