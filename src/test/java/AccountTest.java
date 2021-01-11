import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AccountTest {

    private Account underTest;

    @Before
    public void setUp() {
        underTest = new Account();
    }

    @Test
    public void initialBalanceIsZero() {

        assertEquals(0, underTest.getBalance());
    }

    @Test
    public void depositIncreasesBalance() {
        underTest.deposit(1000);
        assertEquals(1000, underTest.getBalance());
    }

    @Test
    public void withdrawalDecreasesBalance() {
        underTest.deposit(1000);
        underTest.withdraw(500);
        assertEquals(500, underTest.getBalance());
    }
}
