package model;

import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;

public class Game {

    private       Player winner  = null;
    private final Player player1 = new Player("Player 1");
    private final Player player2 = new Player("Player 2");

    private final BooleanProperty running = new SimpleBooleanProperty(true);
        public BooleanProperty runningProperty() {return running;}
        public boolean isRunning() {return running.get();}

    public UnmoddablePlayer getWinner() {return winner;}

    public UnmoddablePlayer getPlayer1() {return player1;}

    public UnmoddablePlayer getPlayer2() {return player2;}

    public void throwDie(UnmoddablePlayer player) {
        Player current = (Player) player;
        current.throwDie();
        if (current.getDieVal().get() != 1) {
            current.setScore(current.getScore() + current.getDieVal().get());
        }
        else {
            winner = (player1.getScore() > player2.getScore()) ? player1 : player2;
            running.set(false);
        }
    }

    public void reinit() {
        winner = null;
        player1.setScore(0);
        player2.setScore(0);
        running.set(true);
    }
}
