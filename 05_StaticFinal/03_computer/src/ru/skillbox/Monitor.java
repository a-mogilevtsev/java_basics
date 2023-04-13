package ru.skillbox;

/**
 * Created by a.sosnina on 10/18/2021.
 */
public class Monitor {
    private final byte size;
    private final PANEL_TYPE type;
    private final short weight;

    public Monitor(byte size, PANEL_TYPE type, short weight) {
        this.size = size;
        this.type = type;
        this.weight = weight;
    }

    public byte getSize() {
        return size;
    }

    public PANEL_TYPE getType() {
        return type;
    }

    public short getWeight() {
        return weight;
    }


    public String toString() {
        return "Monitor {" +
                "size = " + size +
                ", type = '" + type + '\'' +
                ", weight = " + weight +
                '}';
    }
}
