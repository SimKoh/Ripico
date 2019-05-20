package ripico.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import ripico.api.domain.Spiel;
import ripico.api.domain.Wette;
import ripico.api.domain.enums.QuotenArt;


public class BetViewController {
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

    public void addBetToWettschein(){
    }

    public void cancelBet(){
        betViewStage.close();
    }

    public Stage getBetViewStage() {
        return betViewStage;
    }

    public void setBetViewStage(Stage betViewStage) {
        this.betViewStage = betViewStage;
    }


}
