import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AccountTest {

    @Test
    public void initialBalanceIsZero() {
        Account account = new Account();
        assertEquals(0, account.getBalance());
    }

    @Test
    public void depositIncreasesBalance() {
        Account account = new Account();
        account.deposit(1000);
        assertEquals(1000, account.getBalance());
    }
}
