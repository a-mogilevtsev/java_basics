public class Main {

    public static void main(String[] args) {
        Basket basket = new Basket();
        basket.add("Milk", 40);
        basket.print("Milk");
        Basket basket2 = new Basket("milk", 80);
        basket2.add("сырок глазированный", 60, 5);
        Basket basket3 = new Basket("coffee", 120);
        basket3.add("яблоки", 120, 2);
        System.out.println(Basket.getAveragePriceAllBaskets() + " - Средняя цена товаров в корзинах");
        System.out.println(Basket.getAveragePriceOneBasket() + " - Средняя цена товаров в одной корзине");

        Printer testPrinter = new Printer();
        testPrinter.append("Testovaia stroka1");
        testPrinter.append("Testovaia stroka2", 5);
        testPrinter.append("Testovaia stroka3", "text.txt", 7);
        testPrinter.print();
        System.out.println(testPrinter.getTotalPrintedCount());
    }
}
