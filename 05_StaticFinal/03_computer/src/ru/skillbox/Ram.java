package ru.skillbox;

/**
 * Created by a.sosnina on 10/18/2021.
 */
public class Ram {
    private final RAM_TYPE type;
    private final short capacity;
    private final short weight;

    public Ram(RAM_TYPE type, short capacity, short weight) {
        this.type = type;
        this.capacity = capacity;
        this.weight = weight;
    }

    public RAM_TYPE getType() {
        return type;
    }

    public short getCapacity() {
        return capacity;
    }

    public short getWeight() {
        return weight;
    }


    public String toString() {
        return "Ram {" +
                "type = '" + type + '\'' +
                ", capacity = " + capacity +
                ", weight = " + weight +
                '}';
    }
}
