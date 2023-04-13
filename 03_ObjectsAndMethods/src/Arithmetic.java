public class Arithmetic {

    private int firstNumber;
    private int secondNumber;

    public Arithmetic(int firstNumber, int secondNumber) {
        this.firstNumber = firstNumber;
        this.secondNumber = secondNumber;
    }

    public int add() {
        return firstNumber + secondNumber;
    }

    public int multiply() {
        return firstNumber * secondNumber;
    }

    public int max() {
        if(firstNumber >= secondNumber) return firstNumber;
        else return secondNumber;
    }

    public int min() {
        if(firstNumber <= secondNumber) return firstNumber;
        else return secondNumber;
    }
}