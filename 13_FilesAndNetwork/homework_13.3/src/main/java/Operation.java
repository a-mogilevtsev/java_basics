import java.time.LocalDate;

/**
 * Created by a.sosnina on 12/8/2021.
 */
public class Operation {
    private String accountType;
    private String accountNumber;
    private String currency;
    private LocalDate date;
    private String reference;
    private String description;
    private String orgName;
    private double income;
    private double expense;

    public static class Builder {
        private Operation newOperation;

        public Builder() {
            newOperation = new Operation();
        }

        public Builder withAccountType(String accountType) {
            newOperation.accountType = accountType;
            return this;
        }

        public Builder withAccountNumber(String accountNumber) {
            newOperation.accountNumber = accountNumber;
            return this;
        }

        public Builder withCurrency(String currency) {
            newOperation.currency = currency;
            return this;
        }

        public Builder withOrgName(String orgName) {
            newOperation.orgName = orgName;
            return this;
        }

        public Builder withDate(LocalDate date) {
            newOperation.date = date;
            return this;
        }

        public Builder withReference(String reference) {
            newOperation.reference = reference;
            return this;
        }

        public Builder withDescription(String description) {
            newOperation.description = description;
            return this;
        }

        public Builder withIncome(Double income) {
            newOperation.income = income;
            return this;
        }

        public Builder withExpense(Double expense) {
            newOperation.expense = expense;
            return this;
        }

        public Operation build(){
            return newOperation;
        }
    }

    public String getAccountType() {
        return accountType;
    }


    public String getAccountNumber() {
        return accountNumber;
    }


    public String getCurrency() {
        return currency;
    }


    public LocalDate getDate() {
        return date;
    }


    public String getReference() {
        return reference;
    }

    public String getOrgName() {
        return orgName;
    }

    public String getDescription() {
        return description;
    }


    public double getIncome() {
        return income;
    }


    public double getExpense() {
        return expense;
    }


    @Override
    public String toString() {
        return "Operation{" +
                "accountType='" + accountType + '\'' +
                ", accountNumber='" + accountNumber + '\'' +
                ", currency='" + currency + '\'' +
                ", date=" + date +
                ", reference='" + reference + '\'' +
                ", description='" + description + '\'' +
                ", income=" + income +
                ", expense=" + expense +
                '}';
    }
}
