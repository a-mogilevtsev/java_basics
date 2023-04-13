package ru.skillbox;

public class Main {

    public static void main(String[] args) {
        ArithmeticCalculator ac = new ArithmeticCalculator(8,4);
        System.out.println(ac.calculate(Operation.ADD));
        System.out.println(ac.calculate(Operation.SUBTRACT));
        System.out.println(ac.calculate(Operation.MULTIPLY));
    }
}
