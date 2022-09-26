package model;

import javafx.beans.property.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableIntegerArray;
import javafx.collections.ObservableList;

public abstract class UnmoddablePlayer {

    private final StringProperty name = new SimpleStringProperty();
        public StringProperty nameProperty() {return name;}
        public String getName() {return name.get();}

    protected IntegerProperty score = new SimpleIntegerProperty();
        public IntegerProperty scoreProperty() {return score;}
        public int getScore() {return score.get();}

    private final ObservableList<FinalScore> scoresObs = FXCollections.observableArrayList();
    protected ListProperty<FinalScore> scores = new SimpleListProperty<>(scoresObs);
        public ListProperty<FinalScore> scoresProperty() {return scores;}
        public ObservableList<FinalScore> getScores() {return scores.get();}

    protected Die die = new Die();

    public UnmoddablePlayer(String name) {this.name.set(name);}

    public IntegerProperty getDieVal() {return die.valueProperty();}

    public void logScore(boolean winner) {getScores().add(new FinalScore(getScore(), winner));} // auto-unboxing..?
}
