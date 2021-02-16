import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertThrows;


public class CurrentAccountTest {

    private CurrentAccount underTest;

    @Before
    public void setUp() {
        underTest = new CurrentAccount();
    }

    @Test
    public void initialBalanceIsZero() {
        assertEquals(0, underTest.getBalance(),0);
    }

    @Test
    public void depositIncreasesBalance() {
        underTest.deposit(1000);
        assertThat(underTest.getBalance()).isEqualTo(1000);
    }

    @Test
    public void decimalDepositIsHandles() {
        underTest.deposit(10.50);
        assertThat(underTest.getBalance()).isEqualTo(10.50);
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
    public void throwExceptionForANegativeWithdrawal() {
        IllegalArgumentException thrown = assertThrows(
                IllegalArgumentException.class,
                () ->
                        underTest.withdraw(-1000));
        assertTrue(thrown.getMessage().equals("Invalid amount"));
    }

    @Test
    public void withdrawalDecreasesBalance() {
        underTest.deposit(1000);
        underTest.withdraw(500);
        assertEquals(500, underTest.getBalance(),0);
    }
}
