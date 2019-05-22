package ripico.ui;


import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;
import ripico.database.DatabaseWettscheinAdapterImpl;

import java.io.IOException;
import java.sql.SQLException;


public class AppStart extends Application {

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
            System.out.println("FEHLER 45:");
            ex.printStackTrace();
        }
    }


    public static void main(String[] args) throws SQLException {
        DatabaseWettscheinAdapterImpl databaseWettscheinAdapter = new DatabaseWettscheinAdapterImpl();
        databaseWettscheinAdapter.readWettschein(1);
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