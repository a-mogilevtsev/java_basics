public class Main {
    public static void main(String[] args) {
        //Распечатайте сгенерированный в классе TwoDimensionalArray.java двумерный массив
        char[][] array = TwoDimensionalArray.getTwoDimensionalArray(5);
        for (int i = 0; i < array.length; i++) {
            for(int j = 0; j < array.length; j++)
            {
                System.out.print(array[i][j]);
            }
            System.out.print("\n");
        }
    }
}
