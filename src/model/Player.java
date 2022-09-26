package model;

public class Player extends UnmoddablePlayer {

    public Player(String name) {
        super(name);
        throwDie();
    }

    public void setScore(int value) {
        score.setValue(value);
    }

    public void throwDie() {
        die.computeValue();
    }
}
