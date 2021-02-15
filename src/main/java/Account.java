public class Account {

    private int balance;
    Statement statement = new Statement();

    public Account(){
        int openingBalance = 0;
        this.balance = openingBalance;
    }

    public int getBalance() {
        return this.balance;
    }

    public void deposit(int amount) {
        this.balance += amount;
        statement.recordDeposit(amount,getBalance());
    }

    public void withdraw(int amount) {
        this.balance -= amount;
    }
}
