package ripico.ui;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class AppStart extends Application {

    private static final Logger logger = Logger.getLogger(AppStart.class.getName());

    private Stage primaryStage;
    private Parent rootLayout; // AnchorPane

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.getIcons().add(new Image(AppStart.class.getResourceAsStream("../../resources/imgs/icon.png")));

        setupIdleView();
    }

    private void setupIdleView() {
        try {
            // ruft initialize auf
            primaryStage.setTitle("Ripico");

            // load IdleView
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("../../resources/IdleView.fxml"));
            Parent root = (Parent) loader.load(); // !IMPORTANT! Needed to get Controller
            Scene scene = new Scene(root);
            primaryStage.setScene(scene);
            primaryStage.setResizable(false);

            IdleViewController idleViewController = loader.getController();
            idleViewController.setMainApp(this);

            primaryStage.show();

        } catch (Exception ex) {
            logger.log(Level.SEVERE, "FEHLER 45:", ex);
        }
    }


    public static void main(String[] args) throws SQLException {
        System.setProperty("java.util.logging.SimpleFormatter.format",
                "[%1$tF %1$tT] [%4$-7s] %5$s %n");
        launch(args);
    }


    public void setPrimaryStage(Stage stage) {
        this.primaryStage = stage;

        logger.info("New Stage loaded!");

        // Main View laden FEHLER
        FXMLLoader loader = new FXMLLoader(getClass().getResource("../../resources/MainView.fxml"));

        try {
            Parent root = (Parent) loader.load(); // !IMPORTANT! Needed to get Controller
        } catch (IOException e) {
            logger.log(Level.SEVERE, "Fehler beim setzten der primaryStage", e);
        }

        MainViewController mainViewController = loader.getController();
        mainViewController.setMainApp(this);

        this.primaryStage.show();
    }

    public void setRootLayout(Parent rootLayout) {
        this.rootLayout = rootLayout;
    }
}