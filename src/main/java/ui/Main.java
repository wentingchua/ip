package ui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * This is them main class
 */
public class Main extends Application {

    private Dusty dusty = new Dusty("data/tasks.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/MainWindow.fxml"));
            assert Main.class.getResource("/MainWindow.fxml") != null : "FXML resource not found!";

            AnchorPane ap = fxmlLoader.load();
            assert ap != null : "Failed to load AnchorPane from FXML!";

            Scene scene = new Scene(ap);
            stage.setScene(scene);

            assert fxmlLoader.getController() != null : "FXML Controller is null!";
            fxmlLoader.<MainWindow>getController().setDuke(dusty);

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}

