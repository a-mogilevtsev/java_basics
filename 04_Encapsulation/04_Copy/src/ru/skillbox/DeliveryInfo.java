package ru.skillbox;

/**
 * Created by a.sosnina on 10/15/2021.
 */
public class DeliveryInfo {
    public final Dimensions dimensions;
    public final int weight;
    public final String deliveryAdress;
    public final boolean canBeFlipped;
    public final String regNumber;
    public final boolean isFragile;


    public DeliveryInfo(Dimensions dimensions, int weight, String deliveryAdress, boolean canBeFlipped, String regNumber, boolean isFragile) {
        this.dimensions = dimensions;
        this.weight = weight;
        this.deliveryAdress = deliveryAdress;
        this.canBeFlipped = canBeFlipped;
        this.regNumber = regNumber;
        this.isFragile = isFragile;
    }

    public DeliveryInfo setDimensions(Dimensions dimensions) {
        return new DeliveryInfo(dimensions, weight, deliveryAdress, canBeFlipped, regNumber, isFragile);
    }

    public DeliveryInfo setWeight(int weight) {
        return new DeliveryInfo(dimensions, weight, deliveryAdress, canBeFlipped, regNumber, isFragile);
    }

    public DeliveryInfo setDeliveryAdress(String deliveryAdress) {
        return new DeliveryInfo(dimensions, weight, deliveryAdress, canBeFlipped,regNumber, isFragile);
    }
}
