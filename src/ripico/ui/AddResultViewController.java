package ripico.ui;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import ripico.api.ServiceFactory;
import ripico.api.domain.Spiel;
import ripico.api.domain.enums.QuotenArt;
import ripico.api.service.SpielService;

import java.util.logging.Logger;

public class AddResultViewController {
    private static final Logger logger = Logger.getLogger(AddResultViewController.class.getName());
    private final SpielService ss;

    @FXML
    private Label lSportartValue;
    @FXML
    private Label lDatumValue;
    @FXML
    private Label lHeimValue;
    @FXML
    private Label lAuswaertsValue;
    @FXML
    private Label labelDivider;
    @FXML
    private ComboBox cbErgebnis;
    @FXML
    private ComboBox cbSpiel;

    public AddResultViewController() {
        ss = ServiceFactory.createService(SpielService.class);
    }

    public void initialize() {
        this.cbSpiel.setItems(FXCollections.observableArrayList(ss.ladeSpiele()));
        cbSpiel.getSelectionModel().selectedItemProperty().addListener((arg0, oldValue, newValue) -> {
            if (!cbSpiel.getSelectionModel().isEmpty()) {
                Spiel pickedGame = (Spiel) cbSpiel.getSelectionModel().getSelectedItem();
                lDatumValue.setText(pickedGame.getDatum().toString());
                lSportartValue.setText(pickedGame.getSportart().getBezeichnung());
                lHeimValue.setText(pickedGame.getMannschaftHeim().getMannschaftsName());
                lAuswaertsValue.setText(pickedGame.getMannschaftAuswaerts().getMannschaftsName());
                lDatumValue.setText(pickedGame.getDatum().toString());
                labelDivider.setVisible(true);
            }
        });

        this.cbErgebnis.setItems(FXCollections.observableArrayList(QuotenArt.values()));
    }

    public void ergebnisEintragen(ActionEvent actionEvent) {
        if (cbSpiel.getSelectionModel().isEmpty()) return;
        Spiel pickedGame = (Spiel) cbSpiel.getSelectionModel().getSelectedItem();
        SpielService ss = ServiceFactory.createService(SpielService.class);
        ss.setzeErgebnis(pickedGame, (QuotenArt) cbErgebnis.getSelectionModel().getSelectedItem());

        //Close
        ((Node) (actionEvent.getSource())).getScene().getWindow().hide();

    }

    public void closeErgebnisView(ActionEvent actionEvent) {
        ((Node) (actionEvent.getSource())).getScene().getWindow().hide();
    }
}
