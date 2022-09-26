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
import model.UnmoddablePlayer;

import java.io.IOException;

public class UC_Die_BorderPane extends BorderPane {

    @FXML
    private ListView<FinalScore> playerScores;
    @FXML
    private Rectangle rect;
    @FXML
    private StackPane pane;
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
    private Label playerLbl;
    @FXML
    private Button playerBtn;

    private final DoubleProperty scaleX = new SimpleDoubleProperty();
    private final DoubleProperty scaleY = new SimpleDoubleProperty();

    @FXML
    private void clickThrowDie(ActionEvent evt) {
        mainWindow.game.throwDie((UnmoddablePlayer) ((Button) evt.getSource()).getUserData());
        playerBtn.setDisable(!playerBtn.isDisable());
    }

    private final UnmoddablePlayer player;
    private final MainWindow mainWindow;

    public UC_Die_BorderPane() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setController(this);
        loader.setRoot(this);
        loader.load();
    }

    public UC_Die_BorderPane(UnmoddablePlayer player, MainWindow mainWindow) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setController(this);
        loader.setRoot(this);
        loader.load();

        this.player = player;
        this.mainWindow = mainWindow;
    }

    private void computeDiePos() {
        prepareDie(player, d1, d2, d3, d4, d5, d6, d7);
        scaleDie(scaleX, scaleY, d1, d2, d3, d4, d5, d6, d7);
    }


    private void initScaleDie(StackPane pane, Rectangle rect, DoubleProperty scaleX, DoubleProperty scaleY) {
        scaleX.bind(pane.widthProperty().divide(200));
        scaleY.bind(pane.heightProperty().divide(200));
        rect.scaleXProperty().bind(scaleX);
        rect.scaleYProperty().bind(scaleY);
    }

    private void scaleDie(DoubleProperty scaleX,
                          DoubleProperty scaleY,
                          Circle... circles) {
        for (Circle circle : circles) {
            circle.scaleXProperty().bind(scaleX);
            circle.scaleYProperty().bind(scaleY);
        }
    }

    private void prepareDie(UnmoddablePlayer player,
                            Circle d1,
                            Circle d2,
                            Circle d3,
                            Circle d4,
                            Circle d5,
                            Circle d6,
                            Circle d7) {
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
        playerLbl.textProperty()
                .bind((player.nameProperty().concat(" : ").concat(player.scoreProperty()).concat(" points")));

        playerScores.itemsProperty().bind(player.scoresProperty());
        playerScores.setFocusTraversable(false);

        playerBtn.setUserData(player);
        computeDiePos();
        initScaleDie(pane, rect, scaleX, scaleY);
    }
}
