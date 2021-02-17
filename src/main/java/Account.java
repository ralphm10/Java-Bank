public interface Account {

    double getBalance();

    void deposit(double amount) throws Exception;

    void withdraw(double amount) throws Exception;
}
