package view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ListView;
import javafx.scene.control.SplitPane;
import model.Game;
import model.Throw;
import model.UnmoddablePlayer;
import view.uc.UCDieContainer;

import java.io.IOException;

public class MainWindow {

    @FXML
    private SplitPane diceContainer;
    @FXML
    private ListView<Throw> throwsLV;

    private final Game game = new Game();
    private final UnmoddablePlayer player1 = game.getPlayer1();
    private final UnmoddablePlayer player2 = game.getPlayer2();

    private void checkGameOver() {
        if (!game.isRunning()) {
            alert(Alert.AlertType.INFORMATION, String.format("Game over, %s won", game.getWinner().getName()));
            game.reinit();
        }
    }

    private void alert(Alert.AlertType alertType, String msgString) {
        Alert msg = new Alert(alertType, msgString, ButtonType.OK);
        msg.setHeaderText(null);
        msg.showAndWait();
    }

    public void initialize() {
        game.runningProperty().addListener((__, ___, ____) -> checkGameOver());
        throwsLV.itemsProperty().bind(game.throwiesProperty());
        throwsLV.setCellFactory(__ -> new ThrowCell());
        try {
            diceContainer.getItems().add(new UCDieContainer(player1, game));
            diceContainer.getItems().add(new UCDieContainer(player2, game));
        } catch (IOException ex) {
            alert(Alert.AlertType.ERROR, "could not load dice: " + ex.getMessage());
            ex.printStackTrace();
        }
        throwsLV.setFocusTraversable(false);
    }
}
