package ripico.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
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
import ripico.api.domain.QuotenArt;
import ripico.api.domain.Spiel;
import ripico.api.service.SpielService;
import ripico.service.DefaultSpielServiceImpl;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.format.DateTimeFormatter;
import java.util.Locale;


public class MainViewController {

    private AppStart mainApp;
    private SpielService spielService = ServiceFactory.createService(SpielService.class);


    @FXML
    private VBox vBox_availableBets;

    @FXML
    private ScrollPane scrollPane_availableBets;

    @FXML
    private void initialize() {
        System.out.println("MainViewController initialized");

        ObservableList<Spiel> wettList = FXCollections.observableArrayList(spielService.ladeSpiele());


        //ScrollPane
        // VBOX - Alle Wetten
        // PANE - Height: 230, width: 400
        // LABEL SportartDesc - X:20, Y:3
        // LABEL SportArt - X:70, Y:3

        // LABEL Zeitpunkt - X:215, Y:3

        // LABEL Mannschaft1 - X:20, Y:20
        // LABEL : - X:170, Y:20
        // LABEL Mannschaft2 - X:200, Y:20

        // LABEL: Quote M1 - X:65, Y:45
        // LABEL: Quote UN - X:155, Y:45
        // LABEL: Quote M2 - X:245, Y:45


        int counter = 0;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd.MM.yyyy HH:MM");
        for (Spiel spiel : wettList) {

            Pane paneBet = new Pane();


            Label label_sportArtDesc = new Label("Sportart:");
            label_sportArtDesc.setLayoutX(20);
            label_sportArtDesc.setLayoutY(3);
            paneBet.getChildren().add(label_sportArtDesc);

            Label label_sportArt = new Label(spiel.getSportart());
            label_sportArt.setLayoutX(70);
            label_sportArt.setLayoutY(3);
            paneBet.getChildren().add(label_sportArt);

            Label label_zeitpunkt = new Label(simpleDateFormat.format(spiel.getDatum()));
            label_zeitpunkt.setLayoutX(240);
            label_zeitpunkt.setLayoutY(3);
            paneBet.getChildren().add(label_zeitpunkt);

            Label label_mannschaft1 = new Label(spiel.getMannschaftHeim());
            label_mannschaft1.setLayoutX(20);
            label_mannschaft1.setLayoutY(20);
            label_mannschaft1.setFont(new Font("Arial", 19));
            paneBet.getChildren().add(label_mannschaft1);

            Label label_seperator = new Label(":");
            label_seperator.setLayoutX(190);
            label_seperator.setLayoutY(20);
            label_seperator.setFont(new Font("Arial", 19));
            paneBet.getChildren().add(label_seperator);

            Label label_mannschaft2 = new Label(spiel.getMannschaftAuswaerts());
            label_mannschaft2.setLayoutX(240);
            label_mannschaft2.setLayoutY(20);
            label_mannschaft2.setFont(new Font("Arial", 19));
            paneBet.getChildren().add(label_mannschaft2);


            Label label_quoteMannschaft1 = new Label(String.valueOf(spiel.getQuoten().get(QuotenArt.HEIM)));
            label_quoteMannschaft1.setLayoutX(65);
            label_quoteMannschaft1.setLayoutY(45);
            label_quoteMannschaft1.setFont(new Font("Arial", 19));
            paneBet.getChildren().add(label_quoteMannschaft1);

            Label label_quoteUnentschieden = new Label(String.valueOf(spiel.getQuoten().get(QuotenArt.UNENTSCHIEDEN)));
            label_quoteUnentschieden.setLayoutX(180);
            label_quoteUnentschieden.setLayoutY(45);
            label_quoteUnentschieden.setFont(new Font("Arial", 19));
            paneBet.getChildren().add(label_quoteUnentschieden);

            Label label_quoteMannschaft2 = new Label(String.valueOf(spiel.getQuoten().get(QuotenArt.AUSWAERTS)));
            label_quoteMannschaft2.setLayoutX(275);
            label_quoteMannschaft2.setLayoutY(45);
            label_quoteMannschaft2.setFont(new Font("Arial", 19));
            paneBet.getChildren().add(label_quoteMannschaft2);


            paneBet.setMinHeight(75);
            paneBet.setMaxHeight(75);
            paneBet.setPrefWidth(460);


            if (counter % 2 == 0) {
                paneBet.getStyleClass().add("betEntryEven");

            } else {
                paneBet.getStyleClass().add("betEntryOdd");
            }




            paneBet.setOnMouseClicked(event ->
            {
                try {
                    FXMLLoader loader = new FXMLLoader(getClass().getResource("../../resources/BetView.fxml"));
                    // Get MainView RootElement
                    Parent root = loader.load();

                    Stage stage = new Stage(); // Neues Fenster
                    stage.setTitle("Ripico Sportwetten");
                    stage.setScene(new Scene(root));
                    stage.initModality(Modality.APPLICATION_MODAL);

                    BetViewController controller = loader.getController();
                    controller.setSpiel(spiel);

                    stage.show();
                    // Set Icon
                    stage.getIcons().add(new Image(AppStart.class.getResourceAsStream( "../../resources/imgs/icon.png" )));
                    stage.setResizable(false);
                    // Hide/Close TOS-Window

                } catch (IOException e) {
                    System.out.println("Fehler 46:");
                    System.out.println(e.getMessage());
                }


            });
            vBox_availableBets.getChildren().add(paneBet);

            counter++;
        }
        scrollPane_availableBets.setContent(vBox_availableBets);

    }

    public void setMainApp(AppStart app) {
        mainApp = app;
        System.out.println("MainViewController: App loaded!");
    }


}
