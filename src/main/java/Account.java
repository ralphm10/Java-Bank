public class Account {

    private double balance;
    Statement statement = new Statement();

    public Account(){
        int openingBalance = 0;
        this.balance = openingBalance;
    }

    public double getBalance() {
        return this.balance;
    }

    public void deposit(double amount) throws IllegalArgumentException {
        if (amount <= 0) {
            throw new IllegalArgumentException("Invalid amount");
        }
        this.balance += amount;
        statement.recordDeposit(amount,getBalance());
    }

    public void withdraw(double amount) throws IllegalArgumentException {
        if (amount <= 0) {
            throw new IllegalArgumentException("Invalid amount");
        }
        this.balance -= amount;
        statement.recordWithdrawal(amount,getBalance());
    }
}
