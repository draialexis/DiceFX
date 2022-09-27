package model;

public class Throw {

    private final Player player;
    private final int value;

    public Throw(Player player, int value) {
        this.player = player;
        this.value = value;
    }

    public Player getPlayer() {
        return player;
    }

    public int getValue() {
        return value;
    }
}
