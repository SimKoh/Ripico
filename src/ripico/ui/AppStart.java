package ripico.ui;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import ripico.api.domain.Spiel;
import ripico.database.DatabaseMitarbeiterAdapterImpl;
import ripico.database.DatabaseSpielAdapterImpl;
import ripico.service.exception.ResourceNotFoundException;
import sun.applet.Main;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Optional;
import java.util.Properties;

import static com.sun.org.apache.xerces.internal.utils.SecuritySupport.getResourceAsStream;


public class AppStart extends Application {

    private Stage primaryStage;
    private Parent rootLayout; // AnchorPane

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.getIcons().add(new Image(AppStart.class.getResourceAsStream( "../../resources/imgs/icon.png" )));

        setupIdleView();
    }

    private void setupIdleView() {
        try {
            // ruft initialize auf
            primaryStage.setTitle("Ripico Sportwetten");

            // load IdleView
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../../resources/IdleView.fxml"));
            Parent root = (Parent) loader.load(); // !IMPORTANT! Needed to get Controller
            primaryStage.setScene(new Scene(root));
            primaryStage.setResizable(false);

            IdleViewController idleViewController = loader.getController();
            idleViewController.setMainApp(this);

            primaryStage.show();

        } catch (Exception ex) {
            System.out.println("FEHLER 45:");
            ex.printStackTrace();
        }
    }


    public static void main(String[] args) {
        try {
            DatabaseSpielAdapterImpl databaseSpielAdapter = new DatabaseSpielAdapterImpl();
            DatabaseMitarbeiterAdapterImpl databaseMitarbeiterAdapter = new DatabaseMitarbeiterAdapterImpl();
            Optional<Spiel> spiel = databaseSpielAdapter.readSpiel(1);
            Optional<Spiel> spiel2 = databaseSpielAdapter.readSpiel(1);
            Optional<Spiel> spiel3 = databaseSpielAdapter.readSpiel(1);
            Optional<Spiel> spiel4 = databaseSpielAdapter.readSpiel(1);

        } catch (SQLException e) {
            e.printStackTrace();
        }
        launch(args);
    }


    public void setPrimaryStage(Stage stage) {
        this.primaryStage = stage;

        System.out.println("New Stage loaded!");

        // Main View laden FEHLER
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../resources/MainView.fxml"));

        try {
            Parent root = (Parent) loader.load(); // !IMPORTANT! Needed to get Controller
        } catch (IOException e) {
            e.printStackTrace();
        }

        MainViewController mainViewController = loader.getController();
        mainViewController.setMainApp(this);

        this.primaryStage.show();
    }

    public void setRootLayout(Parent rootLayout) {
        this.rootLayout = rootLayout;
    }
}