package launcher;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.Objects;

public class Launcher extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        Parent root  = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("/FXML/MainWindow.fxml")));
        Scene  scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("DiceFX");
        primaryStage.show();
    }
}
