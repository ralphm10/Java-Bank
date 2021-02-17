import org.junit.Before;
import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class CurrentAccountTest {

    private CurrentAccount underTest;

    @Before
    public void setUp() {
        underTest = new CurrentAccount(-500);
    }

    @Test
    public void initialBalanceIsZero() {
        assertEquals(0, underTest.getBalance(),0);
    }

    @Test
    public void depositIncreasesBalance() throws Exception {
        underTest.deposit(1000);
        assertThat(underTest.getBalance()).isEqualTo(1000);
    }

    @Test
    public void decimalDepositIsHandled() throws Exception {
        underTest.deposit(10.50);
        assertThat(underTest.getBalance()).isEqualTo(10.50);
    }

    @Test
    public void throwsExceptionForANegativeDeposit() {
        Exception thrown = assertThrows(
                Exception.class,
                () ->
                        underTest.deposit(-1000));
        assertEquals("Invalid amount", thrown.getMessage());
    }

    @Test
    public void throwsExceptionForANegativeWithdrawal() {
        Exception thrown = assertThrows(
                Exception.class,
                () ->
                        underTest.withdraw(-1000));
        assertEquals("Invalid amount", thrown.getMessage());
    }

    @Test
    public void throwsExceptionForExceedingOverdraft() {
        Exception thrown = assertThrows(
                Exception.class,
                () ->
                        underTest.withdraw(501));
        assertEquals("Insufficient funds", thrown.getMessage());
    }

    @Test
    public void throwExceptionForAmountMoreThanTwoDecimals() {
        Exception thrown = assertThrows(
                Exception.class,
                () ->
                        underTest.deposit(10.599));
        assertEquals("Invalid amount", thrown.getMessage());
    }

    @Test
    public void withdrawalDecreasesBalance() throws Exception {
        underTest.deposit(1000);
        underTest.withdraw(500);
        assertEquals(500, underTest.getBalance(),0);
    }

    @Test
    public void returnsTrueForNegativeAmount() {
        assertTrue(underTest.invalidAmount(-10));
    }

    @Test
    public void returnsTrueForAmountMoreThanTwoDecimals() {
        assertTrue(underTest.invalidAmount(10.501));
    }
}
