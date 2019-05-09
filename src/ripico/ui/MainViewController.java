package ripico.ui;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;


public class MainViewController {

    private AppStart mainApp;

    @FXML
    private TableView meineWettenList;
    @FXML
    private TableColumn<Wett, String> sportartColumn;
    @FXML
    private TableColumn<Wett, String> mannschaft1Column;
    @FXML
    private TableColumn<Wett, String> mannschaft2Column;
    @FXML
    private TableColumn<Wett, Float> quoteColumn;
    @FXML
    private TableColumn<Wett, String> zeitColumn;

    @FXML
    private void initialize() {
        System.out.println("MainViewController initialized");

        // Initialize the person table with the two columns.
        sportartColumn.setCellValueFactory(cellData -> cellData.getValue().sportartProperty());
        mannschaft1Column.setCellValueFactory(cellData -> cellData.getValue().mannschaft1Property());
        mannschaft2Column.setCellValueFactory(cellData -> cellData.getValue().mannschaft2Property());
        quoteColumn.setCellValueFactory(cellData -> cellData.getValue().quoteProperty().asObject());
        zeitColumn.setCellValueFactory(cellData -> cellData.getValue().zeitProperty());

        ObservableList<Wett> wettList = FXCollections.<Wett>observableArrayList(
                new Wett("Fussball", "BVB", "Bayern M", 2.f, "heute"),
                new Wett("Fussball", "Bayern", "Leverkusen", 2.f, "heute"),
                new Wett("Fussball", "Schwerte", "Köln", 2.f, "heute"),
                new Wett("Fussball", "Düsseldorf", "Arsenal", 2.f, "heute")
        );

        meineWettenList.setItems(wettList);
    }

    public void setMainApp(AppStart app){
        mainApp = app;
        System.out.println("MainViewController: App loaded!");
    }



}
