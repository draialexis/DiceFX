package model;

import javafx.beans.property.*;

public abstract class UnmoddablePlayer {

    private final StringProperty name = new SimpleStringProperty();
        public StringProperty nameProperty() {return name;}
        public String getName() {return name.get();}

    protected IntegerProperty score = new SimpleIntegerProperty();
        public IntegerProperty scoreProperty() {return score;}
        public int getScore() {return score.get();}

    protected Die die = new Die();

    public UnmoddablePlayer(String name) {this.name.set(name);}

    public IntegerProperty getDieVal() {return die.valueProperty();}
}
