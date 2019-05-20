package ripico.ui;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import ripico.api.domain.Spiel;


public class BetViewController {

    @FXML
    AnchorPane anchorPaneRoot;

    @FXML
    ImageView aLogo;

    @FXML
    ImageView hLogo;

    @FXML
    Label mannschaftHeimLabel;

    @FXML
    Label mamnnschaftAuswaertsLabel;


    public void setSpiel(Spiel spiel) {
        this.spiel = spiel;
        this.mamnnschaftAuswaertsLabel.setText(spiel.getMannschaftAuswaerts().getMannschaftsName());
        this.mannschaftHeimLabel.setText(spiel.getMannschaftHeim().getMannschaftsName());
        this.hLogo.setImage(new Image(getClass().getResource(spiel.getMannschaftHeim().getMannschaftLogo()).toString(), true));
        this.aLogo.setImage(new Image(getClass().getResource(spiel.getMannschaftAuswaerts().getMannschaftLogo()).toString(), true));
    }

    private Spiel spiel;


}
