package model;

import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

import java.util.Random;

public class Die {
    private final IntegerProperty value = new SimpleIntegerProperty();
        public IntegerProperty valueProperty() {return value;}
        public void setValue(int value) {this.value.set(value);}
        public int getValue() {return value.get();}

    private final Random random = new Random();

    public void computeValue() {value.set(random.nextInt(6) + 1);}
}
