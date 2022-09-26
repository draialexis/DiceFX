package model;

import javafx.beans.property.*;

public class FinalScore {

    private final BooleanProperty winner = new SimpleBooleanProperty();
        public BooleanProperty winnerProperty() {return winner;}
        public boolean isWinner() {return winner.get();}

    private final IntegerProperty total = new SimpleIntegerProperty();
        public IntegerProperty totalProperty() {return total;}
        public int getTotal() {return total.get();}

    public FinalScore(int total, boolean winner) {
        this.total.set(total);
        this.winner.set(winner);
    }

    @Override
    public String toString() {
        return String.format("%d -- %s", getTotal(), isWinner()? "WON" : "LOST");
    }
}
