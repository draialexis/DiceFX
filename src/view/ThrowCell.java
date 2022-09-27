package view;

import javafx.scene.control.ListCell;
import model.Throw;
import view.uc.UCScoreCell;

import java.io.IOException;

public class ThrowCell extends ListCell<Throw> {

    @Override
    protected void updateItem(Throw item, boolean empty) {
        super.updateItem(item, empty);
        if (!empty) {
            try {
                UCScoreCell cell = new UCScoreCell(item.getPlayer(), item.getValue());
                setGraphic(cell);
            } catch (IOException e) {
                textProperty().bind(item.getPlayer().nameProperty());
            }
        }
        else {
            textProperty().unbind();
            setText("");
        }
    }
}
