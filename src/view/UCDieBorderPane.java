package view;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import model.FinalScore;
import model.Game;
import model.UnmoddablePlayer;

import java.io.IOException;

public class UCDieBorderPane extends BorderPane {

    @FXML
    private Circle d1;
    @FXML
    private Circle d2;
    @FXML
    private Circle d3;
    @FXML
    private Circle d4;
    @FXML
    private Circle d5;
    @FXML
    private Circle d6;
    @FXML
    private Circle d7;

    @FXML
    private Button playerBtn;
    @FXML
    private StackPane pane;
    @FXML
    private Rectangle rect;
    @FXML
    private Label playerLbl;
    @FXML
    private ListView<FinalScore> playerLvScores;

    private final DoubleProperty scaleX = new SimpleDoubleProperty();
    private final DoubleProperty scaleY = new SimpleDoubleProperty();

    private UnmoddablePlayer player;
    private Game game;
    private Button otherPlayerBtn = null;

    @FXML
    private void clickThrowDie(ActionEvent evt) {
        game.throwDie((UnmoddablePlayer) ((Button) evt.getSource()).getUserData());
        playerBtn.setDisable(!playerBtn.isDisable());
        if (otherPlayerBtn.isDisable()) {
            otherPlayerBtn.setDisable(false);
        }
    }

    public void setOtherPlayerButton(Button button) {
        otherPlayerBtn = button;
    }

    public void setPlayer(UnmoddablePlayer player) {
        this.player = player;
        playerBtn.setUserData(player);
        // bind the label
        playerLbl.textProperty()
                 .bind((player.nameProperty().concat(" : ").concat(player.scoreProperty()).concat(" points")));
        // bind the scoreboard
        playerLvScores.itemsProperty().bind(player.scoresProperty());
        computeDiePos();
        playerLvScores.setFocusTraversable(false);
    }

    public void setGame(Game game) {
        this.game = game;
        initScaleDie(pane, rect, scaleX, scaleY);
    }

    public UCDieBorderPane() throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/UC/UCDieBorderPane.fxml"));
        loader.setController(this);
        loader.setRoot(this);
        loader.load();
    }

    private void initScaleDie(StackPane pane, Rectangle rect, DoubleProperty scaleX, DoubleProperty scaleY) {
        scaleX.bind(pane.widthProperty().divide(200));
        scaleY.bind(pane.heightProperty().divide(200));
        rect.scaleXProperty().bind(scaleX);
        rect.scaleYProperty().bind(scaleY);
    }

    private void computeDiePos() {
        prepareDie(player, d1, d2, d3, d4, d5, d6, d7);
        scaleDie(scaleX, scaleY, d1, d2, d3, d4, d5, d6, d7);
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
        d1.visibleProperty()
          .bind(player.getDieVal()
                      .isEqualTo(2)
                      .or(player.getDieVal()
                                .isEqualTo(3)
                                .or(player.getDieVal()
                                          .isEqualTo(4)
                                          .or(player.getDieVal().isEqualTo(5).or(player.getDieVal().isEqualTo(6))))));
        d1.visibleProperty()
          .bind(player.getDieVal()
                      .isEqualTo(4)
                      .or(player.getDieVal().isEqualTo(5).or(player.getDieVal().isEqualTo(6))));
        d3.visibleProperty().bind(player.getDieVal().isEqualTo(6));
        d4.visibleProperty().bind(d3.visibleProperty());
        d5.visibleProperty().bind(d2.visibleProperty());
        d6.visibleProperty().bind(d1.visibleProperty());
        d7.visibleProperty()
          .bind(player.getDieVal()
                      .isEqualTo(1)
                      .or(player.getDieVal().isEqualTo(3).or(player.getDieVal().isEqualTo(5))));
    }

    private void scaleDie(DoubleProperty scaleX, DoubleProperty scaleY, Circle... circles) {
        for (Circle circle : circles) {
            circle.scaleXProperty().bind(scaleX);
            circle.scaleYProperty().bind(scaleY);
        }
    }
}
