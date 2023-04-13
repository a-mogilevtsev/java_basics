public class Main {
    public static void main(String[] args) {
        Container container = new Container();
        container.addCount(5672);
        System.out.println(container.getCount());
        // TODO: ниже напишите код для выполнения задания:
        //  С помощью цикла и преобразования чисел в символы найдите все коды
        //  букв русского алфавита — заглавных и строчных, в том числе буквы Ё.


        char a = 'А';
        char ya = 'Я';
        char aLow = 'а';
        char yaLow = 'я';
        char yo = 'Ё';
        char yoLow = 'ё';


        for (int i = (int)a; i <= (int)ya; i++) {
            System.out.println((char)i + " - " + (int) i);
        }
        System.out.println(yo + " - " + (int)yo);

        for (int i = (int)aLow; i <= (int)yaLow; i++) {
            System.out.println((char)i + " - " + (int) i);
        }
        System.out.println(yoLow + " - " + (int)yoLow);

    }
}
