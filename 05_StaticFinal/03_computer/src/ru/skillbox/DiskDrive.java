package ru.skillbox;

/**
 * Created by a.sosnina on 10/18/2021.
 */
public class DiskDrive {
    private final DISK_TYPE type;
    private final short capacity;
    private final short weight;

    public DiskDrive(DISK_TYPE type, short capacity, short weight) {
        this.type = type;
        this.capacity = capacity;
        this.weight = weight;
    }

    public DISK_TYPE getType() {
        return type;
    }

    public short getCapacity() {
        return capacity;
    }

    public short getWeight() {
        return weight;
    }

    public String toString() {
        return "DiskDrive {" +
                "type = '" + type + '\'' +
                ", capacity = " + capacity +
                ", weight = " + weight +
                '}';
    }
}
