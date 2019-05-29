package ripico.ui;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.util.logging.Level;
import java.util.logging.Logger;


public class AppStart extends Application {
    private static final Logger logger = Logger.getLogger(AppStart.class.getName());
    private Stage primaryStage;

    public static void main(String[] args) throws ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        primaryStage.getIcons().add(new Image(AppStart.class.getResourceAsStream("../../resources/imgs/icon.png")));

        openIdleView();
    }

    private void openIdleView() {
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

            primaryStage.show();

        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }
    }
}