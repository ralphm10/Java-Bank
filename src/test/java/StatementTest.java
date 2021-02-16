import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StatementTest {

    private Statement statementUnderTest;
    private Account accountUnderTest;

    @Before
    public void setUp() {
        statementUnderTest = new Statement();
        accountUnderTest = new Account();
    }

    @Test
    public void printsHeadersOnlyIfNoTransactions() {
        assertEquals("date || credit || debit || balance", statementUnderTest.printStatement());
    }

    @Test
    public void printsTheDate(){
        assertEquals("16/02/2021", statementUnderTest.printDate());
    }

    @Test
    public void formatsTheAmount() {
        assertEquals(statementUnderTest.formatAmount(10.5),"10.50");
    }


    @Test
    public void recordsADepositAndTheDate() {
        accountUnderTest.deposit(1000);
        assertEquals("date || credit || debit || balance\n" +
                "16/02/2021 || 1000.00 || || 1000.00",
                accountUnderTest.statement.printStatement());
    }

    @Test
    public void recordsMultipleDeposits() {
        accountUnderTest.deposit(1000);
        accountUnderTest.deposit(500);
        assertEquals("date || credit || debit || balance\n" +
                        "16/02/2021 || 1000.00 || || 1000.00\n" +
                        "16/02/2021 || 500.00 || || 1500.00",
                accountUnderTest.statement.printStatement());
    }

    @Test
    public void recordsAWithdrawalAndTheDate() {
        accountUnderTest.withdraw(500);
        assertEquals("date || credit || debit || balance\n" +
                        "16/02/2021 || || 500.00 || -500.00",
                accountUnderTest.statement.printStatement());
    }

    @Test
    public void recordsADepositAndWithdrawal() {
        accountUnderTest.deposit(1000.50);
        accountUnderTest.withdraw(200.51);
        assertEquals("date || credit || debit || balance\n" +
                        "16/02/2021 || 1000.50 || || 1000.50\n" +
                        "16/02/2021 || || 200.51 || 799.99",
                accountUnderTest.statement.printStatement());
    }
}