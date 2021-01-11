public class Account {

    private static int openingBalance = 0;

    private int balance;

    public Account(){
        this.balance = openingBalance;
    }

    public int getBalance() {
        return this.balance;
    }

    public void deposit(int amount) {
        this.balance += amount;
    }

    public void withdraw(int amount) {
        this.balance -= amount;
    }
}
