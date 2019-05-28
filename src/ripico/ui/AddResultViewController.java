package ripico.ui;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import ripico.api.ServiceFactory;
import ripico.api.domain.Spiel;
import ripico.api.domain.enums.QuotenArt;
import ripico.api.service.SpielService;

public class AddResultViewController {
    public Label lSportart;
    public Label lSportartValue;
    public Label lDatum;
    public Label lDatumValue;
    public Label labelDivider;
    public Label lHeimValue;
    public Label lSpiel;
    public Label lAuswaertsValue;
    public Label lErgebnis;
    public ComboBox cbErgebnis;
    public Button btnAbbrechen;
    public Button btnErgebnisBestaetigen;
    public ComboBox cbSpiel;

    public void init() {
        SpielService ss = ServiceFactory.createService(SpielService.class);
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
