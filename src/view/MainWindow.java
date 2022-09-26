package view;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import model.FinalScore;
import model.Game;
import model.UnmoddablePlayer;

import java.io.IOException;

public class MainWindow {
    @FXML
    private UC_Die_BorderPane dieBP1;
    @FXML
    private UC_Die_BorderPane dieBP2;

    final Game game = new Game();
    private final UnmoddablePlayer p1 = game.getPlayer1();
    private final UnmoddablePlayer p2 = game.getPlayer2();

    private void checkGameOver() {
        if (!game.isRunning()) {
            Alert msg = new Alert(Alert.AlertType.INFORMATION,
                    String.format("Game over, %s won", game.getWinner().getName()),
                    ButtonType.OK);
            msg.setHeaderText(null);
            msg.showAndWait();
            game.getWinner().logScore(true);
            UnmoddablePlayer loser = game.getWinner().equals(p1) ? p2 : p1;
            loser.logScore(false);

            game.reinit();
        }
    }

    public void initialize() throws IOException {
        dieBP1 = new UC_Die_BorderPane(p1, this);
        dieBP2 = new UC_Die_BorderPane(p2, this);
        game.runningProperty().addListener((__, ___, ____) -> checkGameOver());
    }
}
