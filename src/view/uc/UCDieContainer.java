package view.uc;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import model.Game;
import model.UnmoddablePlayer;

import java.io.IOException;

public class UCDieContainer extends BorderPane {

    @FXML
    private Button throwBtn;
    @FXML
    private Label playerLbl;
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
    private Rectangle rect;
    @FXML
    private StackPane pane;

    private final DoubleProperty scaleX = new SimpleDoubleProperty();
    private final DoubleProperty scaleY = new SimpleDoubleProperty();

    private final UnmoddablePlayer player;
    private final Game game;

    public UCDieContainer(UnmoddablePlayer player, Game game) throws IOException {
        this.player = player;
        this.game = game;

        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/uc/UCDieContainer.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        loader.load();
    }

    @FXML
    private void clickThrowDie(ActionEvent evt) {
        game.throwDie(player);
    }

    public void initialize() {
        playerLbl.textProperty()
                 .bind((player.nameProperty().concat(" : ").concat(player.scoreProperty()).concat(" points")));
        placeDie();
        initScaleDie(pane, rect, scaleX, scaleY);
        throwBtn.disableProperty().bind(game.currentPlayerProperty().isEqualTo(player));
    }

    private void initScaleDie(StackPane pane, Rectangle rect, DoubleProperty scaleX, DoubleProperty scaleY) {
        scaleX.bind(pane.widthProperty().divide(200));
        scaleY.bind(pane.heightProperty().divide(200));
        rect.scaleXProperty().bind(scaleX);
        rect.scaleYProperty().bind(scaleY);
    }

    private void placeDie() {
        prepareDie();
        scaleDie(d1, d2, d3, d4, d5, d6, d7);
    }

    private void prepareDie() {
        d1.visibleProperty()
          .bind(player.getDieVal()
                      .isEqualTo(2)
                      .or(player.getDieVal()
                                .isEqualTo(3)
                                .or(player.getDieVal()
                                          .isEqualTo(4)
                                          .or(player.getDieVal().isEqualTo(5).or(player.getDieVal().isEqualTo(6))))));
        d2.visibleProperty()
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

    private void scaleDie(Circle... circles) {
        for (Circle circle : circles) {
            circle.scaleXProperty().bind(scaleX);
            circle.scaleYProperty().bind(scaleY);
        }
    }
}
