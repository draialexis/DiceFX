package view.uc;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import model.UnmoddablePlayer;

import java.io.IOException;

public class UCScoreCell extends BorderPane {
    @FXML
    private ImageView image;

    @FXML
    private Label label;

    public UCScoreCell(UnmoddablePlayer player, int value) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/uc/UCScoreCell.fxml"));
        loader.setRoot(this);
        loader.setController(this);
        loader.load();

        image.setImage(new Image(String.format("/img/dice/%d.png",value)));
        label.textProperty().bind(player.nameProperty());
    }
}
