package ru.skillbox;

/**
 * Created by a.sosnina on 10/18/2021.
 */
public class ArithmeticCalculator {
    private int firstNumber;
    private int secondNumber;

    public ArithmeticCalculator(int firstNumber, int secondNumber) {
        this.firstNumber = firstNumber;
        this.secondNumber = secondNumber;
    }

    public int calculate (Operation operation) {
        int result;
        switch (operation) {
            case ADD : result = firstNumber + secondNumber; break;
            case SUBTRACT : result = firstNumber - secondNumber; break;
            case MULTIPLY : result = firstNumber * secondNumber; break;
            default: result = -1; break;
        }
        return result;
    }
}
