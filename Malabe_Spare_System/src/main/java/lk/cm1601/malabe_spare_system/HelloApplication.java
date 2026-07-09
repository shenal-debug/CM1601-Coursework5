package lk.cm1601.malabe_spare_system;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import lk.cm1601.malabe_spare_system.filehandler.InventoryFileHandler;

import java.io.IOException;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {

        // Read inventory file when the application starts
        InventoryFileHandler inventoryFileHandler = new InventoryFileHandler();
        inventoryFileHandler.readInventoryFile();

        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("hello-view.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 320, 240);

        stage.setTitle("Malabe Spare System");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}