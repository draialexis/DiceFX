package model;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class Game {

    private Player winner = null;
    private final Player player1 = new Player("Player 1");
    private final Player player2 = new Player("Player 2");

    private final BooleanProperty running = new SimpleBooleanProperty(true);
        public BooleanProperty runningProperty() {return running;}
        public boolean isRunning() {return running.get();}

    private final ObjectProperty<Player> currentPlayer = new SimpleObjectProperty<>();
        public ObjectProperty<Player> currentPlayerProperty() {return currentPlayer;}
        public Player getCurrentPlayer() {return currentPlayer.get();}

    private final ObservableList<Throw> throwiesObs = FXCollections.observableArrayList();
    private final ListProperty<Throw> throwies = new SimpleListProperty<>(throwiesObs);
        public ObservableList<Throw> getThrowies() {return throwies.get();}
        public ListProperty<Throw> throwiesProperty() {return throwies;}

    public Game() {currentPlayer.set(player1);}

    public UnmoddablePlayer getWinner() {return winner;}

    public UnmoddablePlayer getPlayer1() {return player1;}

    public UnmoddablePlayer getPlayer2() {return player2;}

    public void throwDie(UnmoddablePlayer player) {
        Player current = (Player) player;
        current.throwDie();
        throwiesObs.add(new Throw(current, current.getDieVal().get()));
        if (current.getDieVal().get() != 1) {
            currentPlayer.set(currentPlayer.get() == player1 ? player2 : player1);
            current.setScore(current.getScore() + current.getDieVal().get());
        }
        else {
            winner = player1.getScore() > player2.getScore() ? player1 : player2;
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
