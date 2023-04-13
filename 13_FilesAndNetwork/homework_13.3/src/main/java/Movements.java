import java.io.BufferedReader;
import java.io.FileReader;
import java.time.LocalDate;
import java.util.*;
import java.util.stream.Collectors;

public class Movements {
    private final String pathMovementsCsv;
    private List<Operation> operations;
    public Movements(String pathMovementsCsv) {
        this.pathMovementsCsv = pathMovementsCsv;
        parseCsv();
    }

    public double getExpenseSum() {
        double totalExpense = 0;
        for(Operation operation : operations) {
            totalExpense += operation.getExpense();
        }
        return totalExpense;
    }

    public double getIncomeSum() {
        double totalIncome = 0;
        for(Operation operation : operations) {
            totalIncome += operation.getIncome();
        }
        return totalIncome;
    }


    public List<Operation> parseCsv() {
        List<Operation> operations = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(pathMovementsCsv));
            String line = br.readLine();
            while(br.ready()){
                line = br.readLine();
                String[] elements = line.split(",");
                Operation operation;
                if(elements.length == 8) {
                    operation = createOperation(elements);
                } else {
                    operation = createOperationWithCommas(elements);
                }
                operations.add(operation);
            }
            this.setOperations(operations);

        }catch (Exception ex) {
            ex.printStackTrace();
        }
        return operations;
    }

    public void setOperations(List<Operation> operations) {
        this.operations = operations;
    }

    public List<Operation> getOperations() {
        return operations;
    }

    public Operation createOperation(String[] elements) {
        String accountType = elements[0];
        String accountNumber = elements[1];
        String currency = elements[2];
        String[] dateParts = elements[3].split("\\.");
        short year = Short.parseShort("20" + dateParts[2]);
        short month = Short.parseShort(dateParts[1]);
        short day = Short.parseShort(dateParts[0]);
        LocalDate date = LocalDate.of(year, month,day);
        String reference = elements[4];
        String description = elements[5];
        String orgName = parseDescription(description);
        double income = Double.parseDouble(elements[6]);
        double expense = Double.parseDouble(elements[7]);
        Operation operation = new Operation.Builder()
                .withAccountType(accountType)
                .withAccountNumber(accountNumber)
                .withCurrency(currency)
                .withDate(date)
                .withReference(reference)
                .withOrgName(orgName)
                .withDescription(description)
                .withIncome(income)
                .withExpense(expense)
                .build();
        return operation;
    }

    public Operation createOperationWithCommas(String[] elements) {
        List<String> elementsList = new ArrayList<>();
        for (int i = 0; i < elements.length; i++) {
            if (!elements[i].startsWith("\"")) {
                elementsList.add(elements[i]);
            } else {
                String firstPart = elements[i].substring(1) + ".";
                while (!elements[i].endsWith("\"")) {
                    i++;
                }
                int trimSize = elements[i].length() - 1;
                String secondPart = elements[i].substring(0, trimSize);
                elementsList.add(firstPart + secondPart);
            }
        }
            String[] elementsEdited = new String[elementsList.size()];
            elementsEdited = elementsList.toArray(elementsEdited);
            return createOperation(elementsEdited);
    }

    public Map<String, DoubleSummaryStatistics> calculateExpByOrgs() {
        Map<String, DoubleSummaryStatistics> expByOrgs = operations.stream()
                .collect(Collectors.groupingBy(Operation::getOrgName,Collectors.summarizingDouble(Operation::getExpense)));
        return expByOrgs;
    }

    public String parseDescription(String description) {
        String[] descParts = description.split("[\\s]{2,}");
        int index = descParts[1].lastIndexOf("\\");
        String organisation = "unknown";
        if(index < 0) {
            index = descParts[1].lastIndexOf("/");
        }
        organisation = descParts[1].substring(index + 1).trim();
        return organisation;
    }

    public void printExpenses(Map<String, DoubleSummaryStatistics> expByOrgs) {
        for(String organisation : expByOrgs.keySet()) {
            System.out.println(organisation + " - " + expByOrgs.get(organisation).getSum());
        }
    }

}
