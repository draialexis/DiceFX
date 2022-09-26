package view;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import model.Game;
import model.UnmoddablePlayer;

public class MainWindow {
    @FXML
    private  Rectangle p1Rect;
    @FXML
    private  StackPane p1Pane;
    @FXML
    private  StackPane p2Pane;
    @FXML
    private  Rectangle p2Rect;
    @FXML
    private Circle    p1d1;
    @FXML
    private Circle    p1d2;
    @FXML
    private Circle    p1d3;
    @FXML
    private Circle    p1d4;
    @FXML
    private Circle    p1d5;
    @FXML
    private Circle    p1d6;
    @FXML
    private Circle    p1d7;
    @FXML
    private Circle    p2d1;
    @FXML
    private Circle    p2d2;
    @FXML
    private Circle    p2d3;
    @FXML
    private Circle    p2d4;
    @FXML
    private Circle    p2d5;
    @FXML
    private Circle    p2d6;
    @FXML
    private Circle    p2d7;

    @FXML
    private Label labelPlayer1;
    @FXML
    private Label labelPlayer2;

    @FXML
    private Button player1Button;
    @FXML
    private Button player2Button;

    private final DoubleProperty scaleXd1 = new SimpleDoubleProperty();
    private final DoubleProperty scaleYd1 = new SimpleDoubleProperty();
    private final DoubleProperty scaleXd2 = new SimpleDoubleProperty();
    private final DoubleProperty scaleYd2 = new SimpleDoubleProperty();

    private final Game             game = new Game();
    private final UnmoddablePlayer p1   = game.getPlayer1();
    private final UnmoddablePlayer p2   = game.getPlayer2();

    @FXML
    private void clickThrowDie(ActionEvent evt) {
        game.throwDie((UnmoddablePlayer) ((Button) evt.getSource()).getUserData());
        player1Button.setDisable(!player1Button.isDisable());
    }

    private void checkGameOver() {
        if (!game.isRunning()) {
            Alert msg = new Alert(Alert.AlertType.INFORMATION,
                                  String.format("Game over, %s won", game.getWinner().getName()),
                                  ButtonType.OK);
            msg.setHeaderText(null);
            msg.showAndWait();
            game.reinit();
        }
    }

    private void computeDiePos() {
        prepareDie(p1, p1d1, p1d2, p1d3, p1d4, p1d5, p1d6, p1d7);
        prepareDie(p2, p2d1, p2d2, p2d3, p2d4, p2d5, p2d6, p2d7);
        scaleDie(scaleXd1, scaleYd1, p1d1, p1d2, p1d3, p1d4, p1d5, p1d6, p1d7);
        scaleDie(scaleXd2, scaleYd2, p2d1, p2d2, p2d3, p2d4, p2d5, p2d6, p2d7);
    }

    private void prepareDie(UnmoddablePlayer player,
                            Circle d1,
                            Circle d2,
                            Circle d3,
                            Circle d4,
                            Circle d5,
                            Circle d6,
                            Circle d7)
    {
        d1.visibleProperty().bind(
                player.getDieVal().isEqualTo(2)
                      .or(player.getDieVal().isEqualTo(3)
                                .or(player.getDieVal().isEqualTo(4)
                                          .or(player.getDieVal().isEqualTo(5)
                                                    .or(player.getDieVal().isEqualTo(6))))));
        d1.visibleProperty().bind(
                player.getDieVal().isEqualTo(4)
                      .or(player.getDieVal().isEqualTo(5)
                                .or(player.getDieVal().isEqualTo(6))));
        d3.visibleProperty().bind(player.getDieVal().isEqualTo(6));
        d4.visibleProperty().bind(d3.visibleProperty());
        d5.visibleProperty().bind(d2.visibleProperty());
        d6.visibleProperty().bind(d1.visibleProperty());
        d7.visibleProperty().bind(
                player.getDieVal().isEqualTo(1)
                      .or(player.getDieVal().isEqualTo(3).
                                or(player.getDieVal().isEqualTo(5))));
    }

    public void initialize() {
        labelPlayer1.textProperty()
                    .bind((p1.nameProperty().concat(" : ").concat(p1.scoreProperty()).concat(" points")));
        labelPlayer2.textProperty()
                    .bind((p2.nameProperty().concat(" : ").concat(p2.scoreProperty()).concat(" points")));

        game.runningProperty().addListener((__, ___, ____) -> checkGameOver());

        player1Button.setUserData(p1);
        player2Button.setUserData(p2);
        computeDiePos();
        initScaleDie(p1Pane, p1Rect, scaleXd1, scaleYd1);
        initScaleDie(p2Pane, p2Rect, scaleXd2, scaleYd2);
    }

    private void initScaleDie(StackPane pane, Rectangle rect, DoubleProperty scaleX, DoubleProperty scaleY) {
        scaleX.bind(pane.widthProperty().divide(200));
        scaleY.bind(pane.heightProperty().divide(200));
        rect.scaleXProperty().bind(scaleX);
        rect.scaleYProperty().bind(scaleY);
    }

    private void scaleDie(DoubleProperty scaleX,
                          DoubleProperty scaleY,
                          Circle... circles)
    {
        for (Circle circle : circles) {
            circle.scaleXProperty().bind(scaleX);
            circle.scaleYProperty().bind(scaleY);
        }
    }
}
