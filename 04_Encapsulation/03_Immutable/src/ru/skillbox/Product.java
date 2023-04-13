package ru.skillbox;

/**
 * Created by a.sosnina on 10/14/2021.
 */
public class Product {
    private final String name;
    private  int price;
    private final int barcode;

    public Product(int barcode, String name) {
        this.barcode = barcode;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getBarcode() {
        return barcode;
    }
}
