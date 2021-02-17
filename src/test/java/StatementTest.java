import org.junit.Before;
import org.junit.Test;

import java.time.Clock;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;

import static org.junit.Assert.assertEquals;

public class StatementTest {

    private Statement statementUnderTest;
    private LocalDateTime fakeDate;
    private LocalDateTime fakeDate2;

    @Before
    public void setUp() {
        statementUnderTest = new Statement();
        Clock clock = Clock.fixed(Instant.parse("2014-12-22T10:15:30.00Z"), ZoneId.of("UTC"));
        Clock clock2 = Clock.fixed(Instant.parse("2020-09-25T10:15:30.00Z"), ZoneId.of("UTC"));
        fakeDate = LocalDateTime.now(clock);
        fakeDate2 = LocalDateTime.now(clock2);
    }

    @Test
    public void printsHeadersOnlyIfNoTransactions() {
        assertEquals("date || credit || debit || balance", statementUnderTest.printStatement());
    }

    @Test
    public void printsTheDate(){
        assertEquals("22/12/2014", statementUnderTest.formatDate(fakeDate));
    }

    @Test
    public void formatsTheAmount() {
        assertEquals(statementUnderTest.formatAmount(10.5),"10.50");
    }

    @Test
    public void recordsADepositAndTheDate() {
        statementUnderTest.recordDeposit(1000,1000, fakeDate);
        assertEquals("date || credit || debit || balance\n" +
                "22/12/2014 || 1000.00 || || 1000.00",
                statementUnderTest.printStatement());
    }

    @Test
    public void recordsMultipleDeposits() {
        statementUnderTest.recordDeposit(1000,1000, fakeDate);
        statementUnderTest.recordDeposit(500,1500, fakeDate2);
        assertEquals("date || credit || debit || balance\n" +
                        "25/09/2020 || 500.00 || || 1500.00\n" +
                        "22/12/2014 || 1000.00 || || 1000.00",
                statementUnderTest.printStatement());
    }

    @Test
    public void recordsAWithdrawalAndTheDate() {
        statementUnderTest.recordWithdrawal(500,-500,fakeDate);
        assertEquals("date || credit || debit || balance\n" +
                        "22/12/2014 || || 500.00 || -500.00",
                statementUnderTest.printStatement());
    }

    @Test
    public void recordsADepositAndWithdrawal() {
        statementUnderTest.recordDeposit(1000.50, 1000.50, fakeDate);
        statementUnderTest.recordWithdrawal(200.51, 799.99, fakeDate2);
        assertEquals("date || credit || debit || balance\n" +
                        "22/12/2014 || 1000.50 || || 1000.50\n" +
                        "25/09/2020 || || 200.51 || 799.99",
                statementUnderTest.printStatement());
    }
}