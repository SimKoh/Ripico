package ripico.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;


public class MainViewController {

    private AppStart mainApp;


    @FXML
    private VBox vBox_availableBets;

    @FXML
    private ScrollPane scrollPane_availableBets;

    @FXML
    private void initialize() {
        System.out.println("MainViewController initialized");

        ObservableList<Wett> wettList = FXCollections.<Wett>observableArrayList(
                new Wett("Fussball", "BVB Dortmund", "Bayern München", 2.f, "08.05.2019 - 22:00"),
                new Wett("Fussball", "Bayern München", "TE Leverkusen", 2.f, "08.05.2019 - 20:00"),
                new Wett("Badminton", "Vfl Schwerte", "PUS Köln", 2.f, "09.05.2019 - 21:00"),
                new Wett("Fussball", "Kgg Düsseldorf", "Arsenal London", 2.f, "10.05.2019 - 23:00"),
                new Wett("Schwanzerei", "Kgg Schwerte", "TE Dortmund", 2.f, "10.05.2019 - 23:00"),
                new Wett("Fussball", "Bayern München", "Bayern London", 2.f, "10.05.2019 - 23:00"),
                new Wett("Schwanzerei", "Kgg Schwerte", "TE Dortmund", 2.f, "10.05.2019 - 23:00"),
                new Wett("Schwanzerei", "Kgg Schwerte", "TE Dortmund", 2.f, "10.05.2019 - 23:00")

                );




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
        for (Wett wette: wettList) {

            Pane paneBet = new Pane();



            Label label_sportArtDesc = new Label("Sportart:");
            label_sportArtDesc.setLayoutX(20);
            label_sportArtDesc.setLayoutY(3);
            paneBet.getChildren().add(label_sportArtDesc);

            Label label_sportArt = new Label(wette.getSportart());
            label_sportArt.setLayoutX(70);
            label_sportArt.setLayoutY(3);
            paneBet.getChildren().add(label_sportArt);

            Label label_zeitpunkt = new Label(wette.getZeit());
            label_zeitpunkt.setLayoutX(240);
            label_zeitpunkt.setLayoutY(3);
            paneBet.getChildren().add(label_zeitpunkt);

            Label label_mannschaft1 = new Label(wette.getMannschaft1());
            label_mannschaft1.setLayoutX(20);
            label_mannschaft1.setLayoutY(20);
            label_mannschaft1.setFont(new Font("Arial", 19));
            paneBet.getChildren().add(label_mannschaft1);

            Label label_seperator = new Label(":");
            label_seperator.setLayoutX(190);
            label_seperator.setLayoutY(20);
            label_seperator.setFont(new Font("Arial", 19));
            paneBet.getChildren().add(label_seperator);

            Label label_mannschaft2 = new Label(wette.getMannschaft2());
            label_mannschaft2.setLayoutX(240);
            label_mannschaft2.setLayoutY(20);
            label_mannschaft2.setFont(new Font("Arial", 19));
            paneBet.getChildren().add(label_mannschaft2);


            Label label_quoteMannschaft1 = new Label(String.valueOf(wette.getQuote()));
            label_quoteMannschaft1.setLayoutX(65);
            label_quoteMannschaft1.setLayoutY(45);
            label_quoteMannschaft1.setFont(new Font("Arial", 19));
            paneBet.getChildren().add(label_quoteMannschaft1);

            Label label_quoteUnentschieden = new Label(String.valueOf(wette.getQuote()));
            label_quoteUnentschieden.setLayoutX(180);
            label_quoteUnentschieden.setLayoutY(45);
            label_quoteUnentschieden.setFont(new Font("Arial", 19));
            paneBet.getChildren().add(label_quoteUnentschieden);

            Label label_quoteMannschaft2 = new Label(String.valueOf(wette.getQuote()));
            label_quoteMannschaft2.setLayoutX(275);
            label_quoteMannschaft2.setLayoutY(45);
            label_quoteMannschaft2.setFont(new Font("Arial", 19));
            paneBet.getChildren().add(label_quoteMannschaft2);


            paneBet.setMinHeight(75);
            paneBet.setMaxHeight(75);
            paneBet.setPrefWidth(460);


            if(counter % 2 == 0){
                paneBet.getStyleClass().add("betEntryEven");

            } else {
                paneBet.getStyleClass().add("betEntryOdd");
            }


            vBox_availableBets.getChildren().add(paneBet);

            counter++;
        }
        scrollPane_availableBets.setContent(vBox_availableBets);

    }

    public void setMainApp(AppStart app){
        mainApp = app;
        System.out.println("MainViewController: App loaded!");
    }



}
