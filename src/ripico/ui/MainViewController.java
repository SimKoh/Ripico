package ripico.ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.LoadException;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Modality;
import javafx.stage.Stage;
import ripico.api.ServiceFactory;
import ripico.api.domain.Spiel;
import ripico.api.domain.Wette;
import ripico.api.domain.Wettschein;
import ripico.api.domain.enums.QuotenArt;
import ripico.api.service.SpielService;
import ripico.api.service.WettscheinService;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public class MainViewController {
    private static final Logger logger = Logger.getLogger(MainViewController.class.getName());
    private final WettscheinService wettscheinService;
    private final SpielService spielService;
    private static StatusLabelManager statusManager;

    private List<Spiel> verfuergbareSpieleList;
    private List<Wette> meineWettenListe;
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy HH:MM");
    private Wettschein wettschein;
    private float guthaben = 0.f;
    private float gesamtQuote = 0.f;
    private float gesamtGewinn = 0.f;
    private float einsatz = 0.f;

    @FXML
    private Label labelStatusMessage;
    @FXML
    private Label label_Guthaben;
    @FXML
    private VBox vBox_availableBets;
    @FXML
    private VBox vBox_meineWetten;
    @FXML
    private ScrollPane scrollPane_availableBets;
    @FXML
    private Label labelWettscheinId;
    @FXML
    private Label labelDatumSchein;
    @FXML
    private Label labelGesamtquote;
    @FXML
    private TextField tfBetEinsatz;
    @FXML
    private Label label_gesamtGewinn;

    public MainViewController() {
        spielService = ServiceFactory.createService(SpielService.class);
        wettscheinService = ServiceFactory.createService(WettscheinService.class);
        meineWettenListe = new ArrayList<>();

        // TODO assert erklären
        assert spielService != null;
        assert wettscheinService != null;

        verfuergbareSpieleList = new ArrayList<>(spielService.ladeSpiele());
        wettschein = wettscheinService.erstelleLeerenWettschein();
    }
    public void init(){
        label_Guthaben.setText(String.format("%.2f €", guthaben));
        statusManager = new StatusLabelManager(labelStatusMessage);
        tfBetEinsatz.setOnKeyReleased(event -> {
            if (tfBetEinsatz.getText().isEmpty()) {
                tfBetEinsatz.setText("0");
            }
            aktualisiereGesamtGewinn(Float.parseFloat(tfBetEinsatz.getText()));
            // CLear Error after new manual edit
            statusManager.clearStatus();
        });

        // Setup Controls
        labelDatumSchein.setText(simpleDateFormat.format(new Date()));
        labelWettscheinId.setText(String.valueOf(wettschein.getWettscheinId()));

        aktualisiereAvailableBets();

        scrollPane_availableBets.setContent(vBox_availableBets);
    }

    public void aktualisiereAvailableBets() {
        if (isAvailableBetsEmpty()) {
            return;
        }
        vBox_availableBets.getChildren().clear();
        for (Spiel spiel : verfuergbareSpieleList) {
            Pane paneBet = createBetRow(1, spiel);
            paneBet.setOnMouseClicked(event -> openBetView(spiel));
            vBox_availableBets.getChildren().add(paneBet);
        }

    }

    public void aktualisiereGesamtquote() {
        gesamtQuote = wettscheinService.berechneGesamtQuote(meineWettenListe);
        labelGesamtquote.setText(String.format(Locale.ROOT, "%.2f", gesamtQuote));
        aktualisiereGesamtGewinn();
    }

    private void aktualisiereGesamtGewinn() {
        gesamtGewinn = gesamtQuote * einsatz;
        label_gesamtGewinn.setText(String.format(Locale.ROOT, "%.2f €", gesamtGewinn));
    }

    private void aktualisiereGesamtGewinn(float parseFloat) {
        einsatz = parseFloat;
        gesamtGewinn = gesamtQuote * einsatz;
        label_gesamtGewinn.setText(String.format(Locale.ROOT, "%.2f €", gesamtGewinn));
    }

    public void addWetteToMyList(Wette wette) {


        Spiel spiel = wette.getSpiel();
        if (wette == null || spiel == null) {
            statusManager.setFailureMessage("Fehler, Wette oder Spiel sind null");
            return;
        }
        statusManager.clearStatus();

        // Add Wette to meineWetten
        meineWettenListe.add(wette);
        aktualisiereGesamtquote();

        // Remove Spiel from available Bets and GUI
        int i = verfuergbareSpieleList.indexOf(spiel);
        verfuergbareSpieleList.remove(i);
        vBox_availableBets.getChildren().remove(i);

        // Lade ganze Liste neu..
        aktualisiereAvailableBets();

        Pane myBetRow = createMyBetRow(1, wette);
        myBetRow.setOnMouseClicked(event -> {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Wette entfernen");
            alert.setHeaderText("Bist du sicher dass du die Wette entfernen willst? Überleg nochmal!");
            alert.setContentText(spiel.getMannschaftHeim().getMannschaftsName() + " : " + spiel.getMannschaftAuswaerts().getMannschaftsName());
            // option != null.
            Optional<ButtonType> option = alert.showAndWait();

            if (option.isPresent() && option.get() == ButtonType.OK) {
                verfuergbareSpieleList.add(spiel);
                aktualisiereAvailableBets();

                meineWettenListe.remove(wette);
                aktualisiereGesamtquote();

                vBox_meineWetten.getChildren().remove(myBetRow);
            }
        });

        vBox_meineWetten.getChildren().add(myBetRow);
    }

    private boolean isAvailableBetsEmpty() {
        if (verfuergbareSpieleList.isEmpty()) {
            Label noBetsLabel = new Label("Keine Wetten verfügbar!");
            noBetsLabel.setPadding(new Insets(25, 25, 25, 25));
            vBox_availableBets.getChildren().add(noBetsLabel);
            return true;
        }
        return false;
    }

    // TODO anpassen in eine Methode ..
    private Pane createBetRow(int counter, Spiel spiel) {
        Pane paneBet = new Pane();

        Label label_sportArtDesc = new Label("Sportart:");
        label_sportArtDesc.setLayoutX(20);
        label_sportArtDesc.setLayoutY(3);
        paneBet.getChildren().add(label_sportArtDesc);

        Label label_sportArt = new Label(spiel.getSportart().getBezeichnung());
        label_sportArt.setLayoutX(70);
        label_sportArt.setLayoutY(3);
        paneBet.getChildren().add(label_sportArt);

        Label label_zeitpunkt = new Label(simpleDateFormat.format(spiel.getDatum()));
        label_zeitpunkt.setLayoutX(295);
        label_zeitpunkt.setLayoutY(3);
        paneBet.getChildren().add(label_zeitpunkt);

        Label label_mannschaft1 = new Label(spiel.getMannschaftHeim().getMannschaftsName());
        label_mannschaft1.setLayoutX(20);
        label_mannschaft1.setLayoutY(20);
        label_mannschaft1.setFont(new Font("Arial", 19));
        paneBet.getChildren().add(label_mannschaft1);

        Label label_seperator = new Label(":");
        label_seperator.setLayoutX(220);
        label_seperator.setLayoutY(20);
        label_seperator.setFont(new Font("Arial", 19));
        paneBet.getChildren().add(label_seperator);

        Label label_mannschaft2 = new Label(spiel.getMannschaftAuswaerts().getMannschaftsName());
        label_mannschaft2.setLayoutX(295);
        label_mannschaft2.setLayoutY(20);
        label_mannschaft2.setFont(new Font("Arial", 19));
        paneBet.getChildren().add(label_mannschaft2);

        Label label_quoteMannschaft1 = new Label(String.valueOf(spiel.getQuoten().get(QuotenArt.HEIM)));
        label_quoteMannschaft1.setLayoutX(75);
        label_quoteMannschaft1.setLayoutY(45);
        label_quoteMannschaft1.setFont(new Font("Arial", 19));
        paneBet.getChildren().add(label_quoteMannschaft1);

        Label label_quoteUnentschieden = new Label(String.valueOf(spiel.getQuoten().get(QuotenArt.UNENTSCHIEDEN)));
        label_quoteUnentschieden.setLayoutX(210);
        label_quoteUnentschieden.setLayoutY(45);
        label_quoteUnentschieden.setFont(new Font("Arial", 19));
        paneBet.getChildren().add(label_quoteUnentschieden);

        Label label_quoteMannschaft2 = new Label(String.valueOf(spiel.getQuoten().get(QuotenArt.AUSWAERTS)));
        label_quoteMannschaft2.setLayoutX(340);
        label_quoteMannschaft2.setLayoutY(45);
        label_quoteMannschaft2.setFont(new Font("Arial", 19));
        paneBet.getChildren().add(label_quoteMannschaft2);

        paneBet.setMinHeight(75);
        paneBet.setMaxHeight(75);
        paneBet.setPrefWidth(475);
        paneBet.getStyleClass().add("betEntry");
        return paneBet;
    }

    private Pane createMyBetRow(int counter, Wette wette) {
        Pane paneBet = new Pane();
        Spiel spiel = wette.getSpiel();

        Label label_sportArtDesc = new Label("Sportart:");
        label_sportArtDesc.setLayoutX(20);
        label_sportArtDesc.setLayoutY(3);
        paneBet.getChildren().add(label_sportArtDesc);

        Label label_sportArt = new Label(spiel.getSportart().getBezeichnung());
        label_sportArt.setLayoutX(70);
        label_sportArt.setLayoutY(3);
        paneBet.getChildren().add(label_sportArt);

        Label label_zeitpunkt = new Label(simpleDateFormat.format(spiel.getDatum()));
        label_zeitpunkt.setLayoutX(295);
        label_zeitpunkt.setLayoutY(3);
        paneBet.getChildren().add(label_zeitpunkt);

        Label label_mannschaft1 = new Label(spiel.getMannschaftHeim().getMannschaftsName());
        label_mannschaft1.setLayoutX(20);
        label_mannschaft1.setLayoutY(20);
        label_mannschaft1.setFont(new Font("Arial", 19));
        paneBet.getChildren().add(label_mannschaft1);

        Label label_seperator = new Label(":");
        label_seperator.setLayoutX(220);
        label_seperator.setLayoutY(20);
        label_seperator.setFont(new Font("Arial", 19));
        paneBet.getChildren().add(label_seperator);

        Label label_mannschaft2 = new Label(spiel.getMannschaftAuswaerts().getMannschaftsName());
        label_mannschaft2.setLayoutX(295);
        label_mannschaft2.setLayoutY(20);
        label_mannschaft2.setFont(new Font("Arial", 19));
        paneBet.getChildren().add(label_mannschaft2);


        Label label_quoteMannschaft1 = new Label(String.valueOf(spiel.getQuoten().get(QuotenArt.HEIM)));
        label_quoteMannschaft1.setLayoutX(75);
        label_quoteMannschaft1.setLayoutY(45);
        label_quoteMannschaft1.setFont(new Font("Arial", 19));
        paneBet.getChildren().add(label_quoteMannschaft1);

        Label label_quoteUnentschieden = new Label(String.valueOf(spiel.getQuoten().get(QuotenArt.UNENTSCHIEDEN)));
        label_quoteUnentschieden.setLayoutX(210);
        label_quoteUnentschieden.setLayoutY(45);
        label_quoteUnentschieden.setFont(new Font("Arial", 19));
        paneBet.getChildren().add(label_quoteUnentschieden);

        Label label_quoteMannschaft2 = new Label(String.valueOf(spiel.getQuoten().get(QuotenArt.AUSWAERTS)));
        label_quoteMannschaft2.setLayoutX(340);
        label_quoteMannschaft2.setLayoutY(45);
        label_quoteMannschaft2.setFont(new Font("Arial", 19));
        paneBet.getChildren().add(label_quoteMannschaft2);


        if (wette.getGesetzteWette() == QuotenArt.HEIM) {
            label_quoteMannschaft1.setStyle("-fx-text-fill: green;");
        }

        if (wette.getGesetzteWette() == QuotenArt.UNENTSCHIEDEN) {
            label_quoteUnentschieden.setStyle("-fx-text-fill: green;");
        }
        if (wette.getGesetzteWette() == QuotenArt.AUSWAERTS) {
            label_quoteMannschaft2.setStyle("-fx-text-fill: green;");
        }

        paneBet.setMinHeight(75);
        paneBet.setMaxHeight(75);
        paneBet.setPrefWidth(475);

        paneBet.getStyleClass().add("betEntry");

        return paneBet;
    }

    public void submitWettschein(ActionEvent event) {
        if (meineWettenListe.isEmpty()) {
            statusManager.setFailureMessage("Du musst erst Wetten zu deinem Wettschein hinzufügen!");
            return;
        }

        String strEinsatz = tfBetEinsatz.getText();
        float einsatz = 0;

        try {
            einsatz = Float.parseFloat(strEinsatz);
        } catch (NumberFormatException e) {
            statusManager.setFailureMessage("Einsätze nur in Form mit dem Dezimalzeichen: 5,87!");
            tfBetEinsatz.setText(guthaben + "");
            aktualisiereGesamtGewinn(Float.parseFloat(tfBetEinsatz.getText()));
            //TODO nach allen stackTracePrints suchen
            e.printStackTrace();
            return;
        }
        if (einsatz < 1) {
            statusManager.setFailureMessage("Einsatz muss größer als 0 sein!");
            tfBetEinsatz.setText(guthaben + "");
            aktualisiereGesamtGewinn(Float.parseFloat(tfBetEinsatz.getText()));
            return;
        }
        if ((guthaben - einsatz) < 0) {
            statusManager.setFailureMessage("Du hast zu wenig Guthaben für diesen Einsatz!");
            tfBetEinsatz.setText(guthaben + "");
            aktualisiereGesamtGewinn(Float.parseFloat(tfBetEinsatz.getText()));
            return;
        }
        statusManager.clearStatus();

        wettschein.setEinsatz(einsatz);
        wettschein.setWetten(meineWettenListe);
        wettscheinService.speichereWettschein(wettschein);
        setGuthaben(guthaben-einsatz);

        // Bestätige Wettscheinnummer
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Wettschein erstellt");
        alert.setHeaderText("Bald bist du reich, versprochen!");
        alert.setContentText("Verlier bloß nicht deine Wettschein-Id: " + wettschein.getWettscheinId());
        Optional<ButtonType> option = alert.showAndWait();

        if (guthaben > 0) {
            // Load MainApp on Success
            openMainView(event);
        } else {
            // Load Auflade - Screen
            openAddCurrencyView(event);
        }

    }

    private void openMainView(ActionEvent actionEvent) {
        // Load MainApp on Success
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../../resources/MainView.fxml"));
            // Get MainView RootElement
            Parent root = loader.load();

            // Set Variables
            MainViewController controller = loader.getController();
            controller.setGuthaben(guthaben);
            controller.init();
            Stage stage = new Stage(); // Neues Fenster
            stage.setTitle("Ripico Sportwetten");
            stage.setScene(new Scene(root));
            stage.show();
            stage.setResizable(false);

            // Set Icon
            stage.getIcons().add(new Image(AppStart.class.getResourceAsStream("../../resources/imgs/icon.png")));
            stage.setResizable(false);
            // Hide/Close TOS-Window
            ((Node) (actionEvent.getSource())).getScene().getWindow().hide();

        } catch (LoadException le) {
            logger.log(Level.SEVERE, "Fehler 47:", le);
            statusManager.setFailureMessage("Laden der Applikation nicht möglich, vielleicht läuft der SQL-Server nicht?");
        } catch (IOException e) {
            logger.log(Level.SEVERE, e.getMessage(), e);
            statusManager.setFailureMessage("Laden der Applikation nicht möglich");
        }

    }

    private void openAddCurrencyView(ActionEvent event) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../../resources/AddCurrencyView.fxml"));
            // Get MainView RootElement
            Parent root = loader.load();

            AddCurrencyViewController controller = loader.getController();

            Stage stage = new Stage(); // Neues Fenster
            stage.setTitle("Ripico Sportwetten - Einzahlung");
            stage.setScene(new Scene(root));
            stage.show();


            // Set Icon
            stage.getIcons().add(new Image(AppStart.class.getResourceAsStream("../../resources/imgs/icon.png")));
            stage.setResizable(false);
            // Hide/Close TOS-Window
            ((Node) (event.getSource())).getScene().getWindow().hide();
        } catch (IOException e) {
            statusManager.setFailureMessage("Fehler beim Laden der Hauptapplikation: IOException");
            logger.log(Level.SEVERE, e.getMessage(), e);
        }
    }

    private void openBetView(Spiel spiel) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("../../resources/BetView.fxml"));
            // Get MainView RootElement
            Parent root = loader.load();

            Stage stage = new Stage(); // Neues Fenster
            stage.setTitle("Neue Wette hinzufügen");
            stage.setScene(new Scene(root));
            stage.initModality(Modality.APPLICATION_MODAL);

            BetViewController controller = loader.getController();
            controller.setSpiel(spiel);
            controller.setMainView(this);

            stage.show();
            // Set Icon
            stage.getIcons().add(new Image(AppStart.class.getResourceAsStream("../../resources/imgs/icon.png")));
            stage.setResizable(false);
            // Hide/Close TOS-Window

        } catch (IOException e) {
            statusManager.setFailureMessage("Fehler beim Laden der Hauptapplikation: IOException");
            logger.log(Level.SEVERE, e.getMessage(), e);
        }
    }

    public void setGuthaben(float parseFloat) {
        guthaben = parseFloat;
    }
}
