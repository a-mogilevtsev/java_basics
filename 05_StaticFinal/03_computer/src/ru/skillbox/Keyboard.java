package ru.skillbox;

/**
 * Created by a.sosnina on 10/18/2021.
 */
public class Keyboard {
    private final String type;
    private final boolean hasBacklight;
    private final short weight;

    public Keyboard(String type, boolean hasBacklight, short weight) {
        this.type = type;
        this.hasBacklight = hasBacklight;
        this.weight = weight;
    }

    public String getType() {
        return type;
    }

    public boolean isHasBacklight() {
        return hasBacklight;
    }

    public short getWeight() {
        return weight;
    }

    public String toString() {
        return "Keyboard {" +
                "type = '" + type + '\'' +
                ", hasBacklight = " + hasBacklight +
                ", weight = " + weight +
                '}';
    }
}
