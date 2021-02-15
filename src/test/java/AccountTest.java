import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;


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
        assertThat(underTest.getBalance()).isEqualTo(1000);
    }

    @Test
    public void throwExceptionForANegativeDeposit() {
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () ->
                        underTest.deposit(-1000));
        assertTrue(thrown.getMessage().equals("Invalid amount"));
    }

    @Test
    public void withdrawalDecreasesBalance() {
        underTest.deposit(1000);
        underTest.withdraw(500);
        assertEquals(500, underTest.getBalance());
    }
}
