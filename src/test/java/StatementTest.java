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
        assertEquals("15/02/2021", statementUnderTest.printDate());
    }


    @Test
    public void recordsADepositAndTheDate() {
        accountUnderTest.deposit(1000);
        assertEquals("date || credit || debit || balance\n" +
                "15/02/2021 || 1000 || || 1000",
                accountUnderTest.statement.printStatement());
    }

    @Test
    public void recordsMultipleDeposits() {
        accountUnderTest.deposit(1000);
        accountUnderTest.deposit(500);
        assertEquals("date || credit || debit || balance\n" +
                        "15/02/2021 || 1000 || || 1000\n" +
                        "15/02/2021 || 500 || || 1500",
                accountUnderTest.statement.printStatement());
    }

    @Test
    public void recordsAWithdrawalAndTheDate() {
        accountUnderTest.withdraw(500);
        assertEquals("date || credit || debit || balance\n" +
                        "15/02/2021 || || 500 || -500",
                accountUnderTest.statement.printStatement());
    }
}