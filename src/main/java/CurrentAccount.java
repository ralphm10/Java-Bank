import java.time.LocalDateTime;

public class CurrentAccount implements Account {

    private double balance;
    private final Statement statement;

    public CurrentAccount(){
        this.balance = 0;
        this.statement = new Statement();
    }

    @Override
    public double getBalance() {
        return this.balance;
    }

    @Override
    public void deposit(double amount) throws IllegalArgumentException {
        if (invalidAmount(amount)) {
            throw new IllegalArgumentException("Invalid amount");
        }
        this.balance += amount;
        this.statement.recordDeposit(amount, getBalance(), LocalDateTime.now());
    }

    @Override
    public void withdraw(double amount) throws IllegalArgumentException {
        if (invalidAmount(amount)) {
            throw new IllegalArgumentException("Invalid amount");
        }
        this.balance -= amount;
        this.statement.recordWithdrawal(amount, getBalance(), LocalDateTime.now());
    }

    public boolean invalidAmount(double amount) {
        return amount <= 0 || amount * 1000 % 10 != 0;
    }

    public static void main(String[] args) {
        CurrentAccount ralph = new CurrentAccount();
        ralph.deposit(999.49);
        ralph.withdraw(500.50);
        System.out.println(ralph.statement.printStatement());

    }
}
