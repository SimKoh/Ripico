package ripico.ui;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ripico.api.ServiceFactory;
import ripico.api.domain.Mannschaft;
import ripico.api.domain.Spiel;
import ripico.api.domain.SpielBuilder;
import ripico.api.domain.enums.QuotenArt;
import ripico.api.domain.enums.Sportart;
import ripico.api.service.MannschaftService;
import ripico.api.service.SpielService;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AddGameController {
    private static final Logger logger = Logger.getLogger(AddGameController.class.getName());

    @FXML
    public TextField tfHeimQuote;
    @FXML
    public TextField tfUnentschiedenQuote;
    @FXML
    public TextField tfAuswaertsQuote;

    @FXML
    public DatePicker dpSpielDatum;
    @FXML
    public TextField tfSpielZeit;

    @FXML
    public Label label_StatusMessage;
    @FXML
    ComboBox cbSportart;
    @FXML
    ComboBox cbHeimMannschaft;
    @FXML
    ComboBox cbAuswaertsMannschaft;

    List<Control> controlList = new ArrayList<>();

    private final MannschaftService mannschaftService;
    private final SpielService ss;

    public AddGameController() {
        mannschaftService = ServiceFactory.createService(MannschaftService.class);
        ss = ServiceFactory.createService(SpielService.class);
    }

    @FXML
    void initialize() {
        cbSportart.setItems(FXCollections.observableArrayList(Sportart.values()));

        // TODO mannschaften basierend auf gewählter Sportart filtern

        cbAuswaertsMannschaft.setItems(FXCollections.observableArrayList(mannschaftService.alleMannschaften()));
        cbHeimMannschaft.setItems(FXCollections.observableArrayList(mannschaftService.alleMannschaften()));

        addQuotenValidierung(tfHeimQuote, tfUnentschiedenQuote, tfAuswaertsQuote);
        addComboBoxValidierung(cbHeimMannschaft, cbAuswaertsMannschaft, cbSportart);
        addSpielzeitValidierung(tfSpielZeit);
        addSpielDatumValidierung(dpSpielDatum);


        controlList.add(tfHeimQuote);
        controlList.add(tfUnentschiedenQuote);
        controlList.add(tfAuswaertsQuote);
        controlList.add(cbHeimMannschaft);
        controlList.add(cbAuswaertsMannschaft);
        controlList.add(cbSportart);
        controlList.add(dpSpielDatum);
        controlList.add(tfSpielZeit);

        // Alle Controls als Rotborder markieren weil PFLICHT
        for (Control control : controlList) {
            control.getStyleClass().add("border");
        }

    }

    private void addSpielDatumValidierung(DatePicker dpSpielDatum) {
        dpSpielDatum.valueProperty().addListener((arg0, oldValue, newValue) -> {
            if (dpSpielDatum.getValue() == null) {
                dpSpielDatum.getStyleClass().add("border");
            } else {
                dpSpielDatum.getStyleClass().removeAll("border");
            }
        });
    }

    private void addSpielzeitValidierung(TextField tfSpielZeit) {
        tfSpielZeit.textProperty().addListener((arg0, oldValue, newValue) -> {
            if (!tfSpielZeit.getText().matches("[0-1][0-9]:[0-5][0-9]|[2][0-3]:[0-5][0-9]")) {
                tfSpielZeit.getStyleClass().add("border");
            } else {
                tfSpielZeit.getStyleClass().removeAll("border");
            }
        });
    }

    private void addQuotenValidierung(TextField... textFields) {
        for (TextField tf : textFields) {
            tf.textProperty().addListener((arg0, oldValue, newValue) -> {
                if (!tf.getText().matches("[0-9]+\\.[0-9]+")) {
                    tf.getStyleClass().add("border");
                } else {
                    tf.getStyleClass().removeAll("border");
                }
            });
        }
    }

    private void addComboBoxValidierung(ComboBox... comboBoxes) {
        for (ComboBox cb : comboBoxes) {
            cb.getSelectionModel().selectedItemProperty().addListener((arg0, oldValue, newValue) -> {
                if (cb.getSelectionModel().isEmpty()) {
                    cb.getStyleClass().add("border");
                } else {
                    cb.getStyleClass().removeAll("border");
                }
            });
        }
    }


    private void clearControls() {
        tfHeimQuote.clear();
        tfUnentschiedenQuote.clear();
        tfAuswaertsQuote.clear();
        cbHeimMannschaft.getSelectionModel().clearSelection();
        cbAuswaertsMannschaft.getSelectionModel().clearSelection();
        cbSportart.getSelectionModel().clearSelection();
        cbHeimMannschaft.getSelectionModel().clearSelection();
        dpSpielDatum.setValue(null);
        tfSpielZeit.clear();
    }

    @FXML
    public void addGame() {
        // Wenn irgend ein Control FehlerBorder hat..
        for (Control control : controlList) {
            if (control.getStyleClass().stream().anyMatch(styleClass -> styleClass == "border")) {
                label_StatusMessage.setText("Pflichtfelder nicht ausgefüllt!");
                label_StatusMessage.getStyleClass().removeAll("successMessage");
                label_StatusMessage.getStyleClass().add("errorMessage");
                label_StatusMessage.setVisible(true);
                return;
            }
        }
        label_StatusMessage.setVisible(false);
        try {
            float heimQuote = Float.parseFloat(tfHeimQuote.getText());
            float auswaertsQuote = Float.parseFloat(tfAuswaertsQuote.getText());
            float unentschiedenQuote = Float.parseFloat(tfUnentschiedenQuote.getText());

            Mannschaft heimMannschaft = (Mannschaft) cbHeimMannschaft.getSelectionModel().getSelectedItem();
            Mannschaft auswaertsMannschaft = (Mannschaft) cbAuswaertsMannschaft.getSelectionModel().getSelectedItem();

            Sportart sportart = (Sportart) cbSportart.getSelectionModel().getSelectedItem();

            String datum = dpSpielDatum.getValue().format(DateTimeFormatter.ofPattern("dd.MM.yyyy")) + " " + tfSpielZeit.getText();
            Date date = new SimpleDateFormat("dd.MM.yyyy HH:mm").parse(datum);
            logger.info(datum);

            Map<QuotenArt, Float> quotenMap = new EnumMap<>(QuotenArt.class);
            quotenMap.put(QuotenArt.HEIM, heimQuote);
            quotenMap.put(QuotenArt.UNENTSCHIEDEN, unentschiedenQuote);
            quotenMap.put(QuotenArt.AUSWAERTS, auswaertsQuote);


            Spiel spiel = SpielBuilder.newSpiel()
                    .withDatum(date)
                    .withMannschaftHeim(heimMannschaft)
                    .withMannschaftAuswaerts(auswaertsMannschaft)
                    .withSportart(sportart)
                    .withQuoten(quotenMap)
                    .build();

            ss.erstelleSpiel(spiel);
            label_StatusMessage.setText("Spiel wurde mit der ID:" + spiel.getSpielId() + " hinzugefügt!");
            label_StatusMessage.getStyleClass().add("successMessage");
            label_StatusMessage.setVisible(true);
            clearControls();


        } catch (ParseException e) {
            e.printStackTrace();
        }


    }

    public void ergebnisViewOeffnen(ActionEvent actionEvent) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../../resources/AddResultView.fxml"));
            // Get MainView RootElement
            Parent root = loader.load();

            AddResultViewController controller = loader.getController();

            Stage stage = new Stage(); // Neues Fenster
            stage.setTitle("Ripico Ergebnis eintragen");
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);
            stage.show();

            controller.init();
            // Set Icon
            stage.getIcons().add(new Image(AppStart.class.getResourceAsStream("../../resources/imgs/icon.png")));
            stage.setResizable(false);

        } catch (IOException e) {
            logger.log(Level.SEVERE, "Fehler 46:", e);
        }
    }
}
