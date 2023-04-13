import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        Movements movements = new Movements("c:\\projects\\skillbox\\java_basics\\13_FilesAndNetwork\\homework_13.3\\src\\test\\resources\\movementList.csv");
        System.out.println("Сумма расходов: " + movements.getExpenseSum());
        System.out.println("Сумма доходов: " + movements.getIncomeSum());
        System.out.println("Суммы расходов по организациям:");
        movements.printExpenses(movements.calculateExpByOrgs());
    }
}
