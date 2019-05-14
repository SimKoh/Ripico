package ripico.ui;

import javafx.fxml.FXML;
import javafx.scene.layout.AnchorPane;

public class BetViewController {

    @FXML
    AnchorPane anchorPaneRoot;

    public void setWette(Wett wette) {
        this.wette = wette;
    }

    private Wett wette;



}
