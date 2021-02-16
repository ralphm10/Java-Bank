public class CurrentAccount implements Account {

    private double balance;
    Statement statement = new Statement();

    public CurrentAccount(){
        double openingBalance = 0;
        this.balance = openingBalance;
    }

    @Override
    public double getBalance() {
        return this.balance;
    }

    @Override
    public void deposit(double amount) throws IllegalArgumentException {
        if (amount <= 0) {
            throw new IllegalArgumentException("Invalid amount");
        }
        this.balance += amount;
        statement.recordDeposit(amount,getBalance());
    }

    @Override
    public void withdraw(double amount) throws IllegalArgumentException {
        if (amount <= 0) {
            throw new IllegalArgumentException("Invalid amount");
        }
        this.balance -= amount;
        statement.recordWithdrawal(amount,getBalance());
    }
}
