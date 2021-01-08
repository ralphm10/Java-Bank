import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AccountTest {

    @Test
    public void initialBalanceIsZero() {
        Account account = new Account();
        assertEquals(0, account.getBalance());
    }
}
