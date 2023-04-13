public class Basket {
    public static int totalPriceAllBaskets = 0;
    private static int countItemsAllBaskets = 0;
    private static int basketsCount;

    private String items = "";
    private int totalPrice = 0;
    private int limit;
    private double totalWeight;


    public Basket() {
        increaseBasketsCount();
        totalWeight = 0;
        items = "Список товаров:";
        this.limit = 1000000;
    }

    public Basket(int limit) {
        this();
        this.limit = limit;
    }

    public Basket(String items, int totalPrice) {
        this();
        this.items = this.items + items;
        this.totalPrice = totalPrice;
    }

    public int getBasketsCount() {
        return Basket.basketsCount;
    }

    public static void increaseBasketsCount(){
        basketsCount = basketsCount + 1;
    }
    public static void increaseCount(int count) {
        Basket.countItemsAllBaskets = Basket.countItemsAllBaskets + count;
    }

    public static void increasePrice(int price) {
        Basket.totalPriceAllBaskets = Basket.totalPriceAllBaskets + price;
    }

    public static double getAveragePriceAllBaskets() {
        return Basket.totalPriceAllBaskets/Basket.countItemsAllBaskets;
    }

    public static double getAveragePriceOneBasket() {
        if (Basket.basketsCount != 0)
        return Basket.totalPriceAllBaskets/Basket.basketsCount;
        else return -1;
    }

    public void add(String name, int price) {
        add(name, price, 1);
    }

    public void add(String name, int price, int count) {
        boolean error = false;
        if (contains(name)) {
            error = true;
        }

        if (totalPrice + count * price >= limit) {
            error = true;
        }

        if (error) {
            System.out.println("Error occured :(");
            return;
        }

        items = items + "\n" + name + " - " +
            count + " шт. - " + price;
        totalPrice = totalPrice + count * price;
        increaseCount(count);
        increasePrice(count * price);
    }

    public void add(String name, int price, int count, double weight) {
        int priceBefore = totalPrice;
        add(name, price, count);
        if(totalPrice!= priceBefore){
            this.totalWeight = getTotalWeight() + weight;
        }

    }

    public void clear() {
        items = "";
        totalPrice = 0;
        totalWeight = 0;
    }

    public int getTotalPrice() {
        return totalPrice;
    }

    public double getTotalWeight() {
        return totalWeight;
    }
    public boolean contains(String name) {
        return items.contains(name);
    }

    public void print(String title) {
        System.out.println(title);
        if (items.isEmpty()) {
            System.out.println("Корзина пуста");
        } else {
            System.out.println(items);
        }
    }
}
