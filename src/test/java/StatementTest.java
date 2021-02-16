import org.junit.Before;
import org.junit.Test;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import static org.junit.Assert.assertEquals;

public class StatementTest {

    private Statement statementUnderTest;
    private CurrentAccount fakeAccount;
    private Clock clock;
    private LocalDateTime fakeDate;

    @Before
    public void setUp() {
        statementUnderTest = new Statement();
        fakeAccount = new CurrentAccount();
        clock = Clock.fixed(Instant.parse("2014-12-22T10:15:30.00Z"), ZoneId.of("UTC"));
        fakeDate = LocalDateTime.now(clock);
    }

    @Test
    public void printsHeadersOnlyIfNoTransactions() {
        assertEquals("date || credit || debit || balance", statementUnderTest.printStatement());
    }

    @Test
    public void printsTheDate(){
        assertEquals("22/12/2014", statementUnderTest.printDate(fakeDate));
    }

    @Test
    public void formatsTheAmount() {
        assertEquals(statementUnderTest.formatAmount(10.5),"10.50");
    }


    @Test
    public void recordsADepositAndTheDate() {
        fakeAccount.deposit(1000);
        assertEquals("date || credit || debit || balance\n" +
                "16/02/2021 || 1000.00 || || 1000.00",
                fakeAccount.statement.printStatement());
    }

    @Test
    public void recordsMultipleDeposits() {
        fakeAccount.deposit(1000);
        fakeAccount.deposit(500);
        assertEquals("date || credit || debit || balance\n" +
                        "16/02/2021 || 1000.00 || || 1000.00\n" +
                        "16/02/2021 || 500.00 || || 1500.00",
                fakeAccount.statement.printStatement());
    }

    @Test
    public void recordsAWithdrawalAndTheDate() {
        fakeAccount.withdraw(500);
        assertEquals("date || credit || debit || balance\n" +
                        "16/02/2021 || || 500.00 || -500.00",
                fakeAccount.statement.printStatement());
    }

    @Test
    public void recordsADepositAndWithdrawal() {
        fakeAccount.deposit(1000.50);
        fakeAccount.withdraw(200.51);
        assertEquals("date || credit || debit || balance\n" +
                        "16/02/2021 || 1000.50 || || 1000.50\n" +
                        "16/02/2021 || || 200.51 || 799.99",
                fakeAccount.statement.printStatement());
    }
}