package ru.skillbox;

/**
 * Created by a.sosnina on 10/15/2021.
 */
public class Dimensions {
    public final int length;
    public final int width;
    public final int height;

    public Dimensions(int length, int width, int height) {
        this.length = length;
        this.width = width;
        this.height = height;
    }

    public int calculateVolume() {
       return length * width * height;
    }
}
