package sr.unasat.country.rest.financehelper.entities;

public class Transaction {
    private long id;
    private double amount;
    private String type;

    public Transaction(long id, double amount, String type) {
        this.id = id;
        this.amount = amount;
        this.type = type;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
