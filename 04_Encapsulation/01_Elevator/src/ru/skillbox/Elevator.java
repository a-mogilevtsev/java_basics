package ru.skillbox;

/**
 * Created by a.sosnina on 10/14/2021.
 */
public class Elevator {
    private int currentFloor;
    private int maxFloor;
    private int minFloor;

    public Elevator() {
        currentFloor = 1;
    }

    public Elevator(int minFloor, int maxFloor) {
        this.minFloor = minFloor;
        this.maxFloor = maxFloor;
    }

    public int getCurrentFloor() {
        return currentFloor;
    }

    public void moveUp() {
        if(currentFloor < maxFloor) currentFloor = currentFloor + 1;
        if (currentFloor == 0) currentFloor = currentFloor + 1;
        System.out.println("Лифт на " + currentFloor);
    }

    public void moveDown() {
        if(currentFloor > minFloor) currentFloor = currentFloor - 1;
        if (currentFloor == 0) currentFloor = currentFloor - 1;
        System.out.println("Лифт на " + currentFloor);
    }

    public void move(int floor) {
        if(minFloor < floor && floor < maxFloor && floor!=0){
            while(getCurrentFloor() != floor) {
                if (getCurrentFloor() > floor) moveDown();
                else moveUp();
            }
        } else System.out.println("Введен некорректный номер этажа");
    }
}

