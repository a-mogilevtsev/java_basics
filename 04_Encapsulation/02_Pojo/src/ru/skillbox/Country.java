package ru.skillbox;

/**
 * Created by a.sosnina on 10/14/2021.
 */
public class Country {
    private String name;
    private int population;
    private double square;
    private String capitalName;
    private boolean hasSeaExit;

    public Country(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPopulation() {
        return population;
    }

    public void setPopulation(int population) {
        this.population = population;
    }

    public double getSquare() {
        return square;
    }

    public void setSquare(double square) {
        this.square = square;
    }

    public String getCapitalName() {
        return capitalName;
    }

    public void setCapitalName(String capitalName) {
        this.capitalName = capitalName;
    }

    public boolean isHasSeaExit() {
        return hasSeaExit;
    }

    public void setHasSeaExit(boolean hasSeaExit) {
        this.hasSeaExit = hasSeaExit;
    }
}
