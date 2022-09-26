package view;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import model.Game;

public class MainWindow {
    @FXML
    private UCDieBorderPane dieBP1;
    @FXML
    private UCDieBorderPane dieBP2;

    private final Game game = new Game();

    private void checkGameOver() {
        System.out.println("before");
        System.out.println("after");
        if (!game.isRunning()) {
            game.getWinner().logScore(true);
            game.getLoser().logScore(false);

            Alert msg = new Alert(Alert.AlertType.INFORMATION,
                                  String.format("Game over, %s won", game.getWinner().getName()),
                                  ButtonType.OK);
            msg.setHeaderText(null);
            msg.showAndWait();

            game.reinit();
        }
    }

    public void initialize() {
        game.runningProperty().addListener((__, ___, ____) -> checkGameOver());
        dieBP1.setPlayer(game.getPlayer1());
        dieBP1.setGame(game);
        dieBP2.setPlayer(game.getPlayer2());
        dieBP2.setGame(game);
        // yikes...
        Button button2 = (Button) dieBP2.getChildren().filtered(child -> child instanceof Button).get(0);
        button2.setDisable(true);
        dieBP1.setOtherPlayerButton(button2);
        dieBP2.setOtherPlayerButton((Button) dieBP1.getChildren().filtered(child -> child instanceof Button).get(0));
    }
}
