public interface Account {

    Statement statement = new Statement();
    double getBalance();

    void deposit(double amount) throws IllegalArgumentException;

    void withdraw(double amount) throws IllegalArgumentException;
}
