public interface Account {

    double getBalance();

    void deposit(double amount) throws IllegalArgumentException;

    void withdraw(double amount) throws IllegalArgumentException;
}
