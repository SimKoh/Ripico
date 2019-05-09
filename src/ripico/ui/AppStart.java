package ripico.ui;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import sun.applet.Main;

import java.io.IOException;


public class AppStart extends Application {

    private Stage primaryStage;
    private Parent rootLayout; // AnchorPane

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        setupIdleView();
    }

    private void setupIdleView(){
        try {
            // Calls Initialize usw.
            primaryStage.setTitle("Ripico - Idle");

            // IdleView
            FXMLLoader loader = new FXMLLoader();
            loader.setLocation(getClass().getResource("IdleView.fxml"));
            Parent root = (Parent) loader.load(); // !IMPORTANT! Needed to get Controller
            primaryStage.setScene(new Scene(root));

            IdleViewController idleViewController = loader.getController();
            idleViewController.setMainApp(this);

            primaryStage.show();

        } catch (Exception ex) {
            System.out.println("FEHLER 45:");
            ex.printStackTrace();
        }
    }







    public static void main(String[] args) {
        launch(args);
    }


    public void setPrimaryStage(Stage stage) {
        this.primaryStage = stage;

        System.out.println("New Stage loaded!");

        // Main View laden FEHLER
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("MainView.fxml"));
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