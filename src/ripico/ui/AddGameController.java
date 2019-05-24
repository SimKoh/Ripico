package ripico.ui;

import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import ripico.api.ServiceFactory;
import ripico.api.domain.enums.Sportart;
import ripico.api.service.SpielService;

import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.stream.Collectors;

public class AddGameController {
    @FXML
    public TextField tfHeimQuote;
    @FXML
    public TextField tfUnentschiedenQuote;
    @FXML
    public TextField tfAuswaertsQuote;
    @FXML
    public TextField tfHeimMannschaft;
    @FXML
    public TextField tfAuswaertsMannschaft;
    @FXML
    public DatePicker dpSpielDatum;
    @FXML
    public TextField tfSpielZeit;
    @FXML
    public Label label_errorMessage;
    @FXML
    ComboBox cbSportart;
    @FXML
    ComboBox cbHeimMannschaft;
    @FXML
    ComboBox cbAuswaertsMannschaft;

    @FXML
    void initialize(){
        //TODO debug-entfernen
        System.out.println("Bin Login geladen");
        cbSportart.setItems(FXCollections.observableArrayList(Arrays.stream(Sportart.values()).map(sportart -> sportart.getBezeichnung()).collect(Collectors.toList())));

        cbAuswaertsMannschaft.setItems(FXCollections.observableArrayList(Arrays.stream(Sportart.values()).map(sportart -> sportart.getBezeichnung()).collect(Collectors.toList())));
        cbHeimMannschaft.setItems(FXCollections.observableArrayList(Arrays.stream(Sportart.values()).map(sportart -> sportart.getBezeichnung()).collect(Collectors.toList())));

        cbSportart.getSelectionModel().select(0);
    }

    public void init() {

    }

    @FXML
    public void addGame(){
        float heimQuote = Float.parseFloat(tfHeimQuote.getText());
        float auswaertsQuote = Float.parseFloat(tfAuswaertsQuote.getText());
        float unentschiedenQuote = Float.parseFloat(tfUnentschiedenQuote.getText());

        String heimMannschaft = tfHeimMannschaft.getText();
        String auswaertsMannschaft = tfAuswaertsMannschaft.getText();

        String datum = dpSpielDatum.getValue().format(DateTimeFormatter.ofPattern("dd.MM.yyyy"))+ " " + tfSpielZeit;

        SpielService ss = ServiceFactory.createService(SpielService.class);

    }
}
