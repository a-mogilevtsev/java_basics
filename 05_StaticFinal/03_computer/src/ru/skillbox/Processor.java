package ru.skillbox;

/**
 * Created by a.sosnina on 10/18/2021.
 */
public class Processor {
    private final short frequency;
    private final byte coreCount;
    private final String manufacturer;
    private final short weight;

    public Processor(short frequency, byte coreCount, String manufacturer, short weight) {
        this.frequency = frequency;
        this.coreCount = coreCount;
        this.manufacturer = manufacturer;
        this.weight = weight;
    }

    public short getFrequency() {
        return frequency;
    }

    public byte getCoreCount() {
        return coreCount;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public short getWeight() {
        return weight;
    }


    public String toString() {
        return "Processor {" +
                "frequency = " + frequency +
                ", coreCount = " + coreCount +
                ", manufacturer = '" + manufacturer + '\'' +
                ", weight = " + weight +
                '}';
    }
}
