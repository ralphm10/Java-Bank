import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class StatementTest {

    private Statement statementUnderTest;
    private CurrentAccount currentAccountUnderTest;

    @Before
    public void setUp() {
        statementUnderTest = new Statement();
        currentAccountUnderTest = new CurrentAccount();
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
        currentAccountUnderTest.deposit(1000);
        assertEquals("date || credit || debit || balance\n" +
                "16/02/2021 || 1000.00 || || 1000.00",
                currentAccountUnderTest.statement.printStatement());
    }

    @Test
    public void recordsMultipleDeposits() {
        currentAccountUnderTest.deposit(1000);
        currentAccountUnderTest.deposit(500);
        assertEquals("date || credit || debit || balance\n" +
                        "16/02/2021 || 1000.00 || || 1000.00\n" +
                        "16/02/2021 || 500.00 || || 1500.00",
                currentAccountUnderTest.statement.printStatement());
    }

    @Test
    public void recordsAWithdrawalAndTheDate() {
        currentAccountUnderTest.withdraw(500);
        assertEquals("date || credit || debit || balance\n" +
                        "16/02/2021 || || 500.00 || -500.00",
                currentAccountUnderTest.statement.printStatement());
    }

    @Test
    public void recordsADepositAndWithdrawal() {
        currentAccountUnderTest.deposit(1000.50);
        currentAccountUnderTest.withdraw(200.51);
        assertEquals("date || credit || debit || balance\n" +
                        "16/02/2021 || 1000.50 || || 1000.50\n" +
                        "16/02/2021 || || 200.51 || 799.99",
                currentAccountUnderTest.statement.printStatement());
    }
}