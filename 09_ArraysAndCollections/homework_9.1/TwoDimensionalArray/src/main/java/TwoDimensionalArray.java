public class TwoDimensionalArray {
    public static char symbol = 'X';

    public static char[][] getTwoDimensionalArray(int size) {

        //TODO: Написать метод, который создаст двумерный массив char заданного размера.
        // массив должен содержать символ symbol по диагоналям, пример для size = 3
        // [X,  , X]
        // [ , X,  ]
        // [X,  , X]
        char[][] array = getBlankArray(size);
        int j = size - 1;
        for (int i = 0; i < size; i++ )
        {
            array[i][i] = symbol;
            array[j][i] = symbol;
            j--;
        }

        return array;
    }

    public static char[][] getBlankArray(int size) {
        char[][] array = new char[size][size];
        for (int i = 0; i < array.length; i++) {
            for(int j = 0; j < array.length; j++)
            {
                array[i][j] = ' ';
            }
        }
        return array;
    }

}
