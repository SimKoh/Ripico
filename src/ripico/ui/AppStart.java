package ripico.ui;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import sun.applet.Main;

import java.io.IOException;
import java.util.Properties;

import static com.sun.org.apache.xerces.internal.utils.SecuritySupport.getResourceAsStream;


public class AppStart extends Application {

    public static Properties properties;
    private Stage primaryStage;
    private Parent rootLayout; // AnchorPane

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.getIcons().add(new Image(AppStart.class.getResourceAsStream( "icon.png" )));

        setupIdleView();
    }

    private void setupIdleView() {
        try {
            // ruft initialize auf
            primaryStage.setTitle("Ripico Sportwetten");

            // load IdleView
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("IdleView.fxml"));
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
        properties.setProperty("dummy","true");
        launch(args);
    }


    public void setPrimaryStage(Stage stage) {
        this.primaryStage = stage;

        System.out.println("New Stage loaded!");

        // Main View laden FEHLER
        FXMLLoader loader = new FXMLLoader(getClass().getResource("MainView.fxml"));

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