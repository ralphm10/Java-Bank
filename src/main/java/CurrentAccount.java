import java.time.LocalDateTime;

public class CurrentAccount implements Account {

    private double balance;
    private final Statement statement;
    private final double overdraftLimit;

    public CurrentAccount(double overdraftLimit){
        this.balance = 0;
        this.overdraftLimit = -overdraftLimit;
        this.statement = new Statement();
    }

    @Override
    public double getBalance() {
        return this.balance;
    }

    @Override
    public void deposit(double amount) throws Exception {
        checkAmount(amount);
        this.balance += amount;
        this.statement.recordDeposit(amount, getBalance(), LocalDateTime.now());
    }

    @Override
    public void withdraw(double amount) throws Exception {
        checkAmount(amount);
        if (this.balance - amount < this.overdraftLimit) {
            throw new Exception("Insufficient funds");
        }
        this.balance -= amount;
        this.statement.recordWithdrawal(amount, getBalance(), LocalDateTime.now());
    }

    public boolean invalidAmount(double amount) {
        return amount <= 0 || amount * 1000 % 10 != 0;
    }

    public void checkAmount(double amount) throws Exception {
        if (invalidAmount(amount)) {
            throw new Exception("Invalid amount");
        }
    }

    public static void main(String[] args) throws Exception {
        CurrentAccount ralph = new CurrentAccount(0);
        ralph.deposit(999.49);
        ralph.withdraw(500.50);
        System.out.println(ralph.statement.printStatement());

    }
}
