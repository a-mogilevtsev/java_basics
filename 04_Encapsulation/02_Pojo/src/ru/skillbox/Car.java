package ru.skillbox;

/**
 * Created by a.sosnina on 10/14/2021.
 */
public class Car {
    private String modelName;
    private byte passengerSeats;
    private short length;
    private short width;
    private short maxSpeed;

    public Car(String modelName) {
        this.modelName = modelName;
    }

    public Car(String modelName, byte passengerSeats) {
        this.modelName = modelName;
        this.passengerSeats = passengerSeats;
    }

    public String getModelName() {
        return modelName;
    }

    public void setModelName(String modelName) {
        this.modelName = modelName;
    }

    public byte getPassengerSeats() {
        return passengerSeats;
    }

    public void setPassengerSeats(byte passengerSeats) {
        this.passengerSeats = passengerSeats;
    }

    public short getLength() {
        return length;
    }

    public void setLength(short length) {
        this.length = length;
    }

    public short getWidth() {
        return width;
    }

    public void setWidth(short width) {
        this.width = width;
    }

    public short getMaxSpeed() {
        return maxSpeed;
    }

    public void setMaxSpeed(short maxSpeed) {
        this.maxSpeed = maxSpeed;
    }
}
