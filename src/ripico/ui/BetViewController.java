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
        String radioButtonId = selectedRadioButton.getId();
        Wette wette;
        switch (radioButtonId) {
            case "radioHeim":
                wette = WetteBuilder.newWette().withSpiel(spiel).withGesetzteWette(QuotenArt.HEIM).build();
                break;
            case "radioUnentschieden":
                wette = WetteBuilder.newWette().withSpiel(spiel).withGesetzteWette(QuotenArt.HEIM).build();
                break;
            case "radioAuswaerts":
                wette = WetteBuilder.newWette().withSpiel(spiel).withGesetzteWette(QuotenArt.HEIM).build();
                break;
            default:
                logger.severe("Wette kann nicht erstellt werden, RadioButton nicht erkannt!");
                return;
        }
        MainViewController.getMeineWettenListe().add(wette);
        mainView.aktualisiereGesamtquote();
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
