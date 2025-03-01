import example.model.AccountHolder;
import example.model.BankAccount;
import example.model.SimpleBankAccount;

import org.junit.jupiter.api.*;
import org.junit.jupiter.api.condition.DisabledIf;

import static org.junit.jupiter.api.Assertions.*;

/**
 * The test suite for testing the SimpleBankAccount implementation
 */
class SimpleBankAccountTest {
    private static final int INITIAL_DEPOSIT = 100;
    private static final int DRAW_AMOUNT = 50;
    private static final int N_OF_ATTEMPTS = 2;
    private AccountHolder accountHolder;
    private BankAccount bankAccount;

    @BeforeEach
    void beforeEach(){
        accountHolder = new AccountHolder("Mario", "Rossi", 1);
        bankAccount = new SimpleBankAccount(accountHolder, 0);
    }

    private void multipleWithDraw(){
        for (int i = 0; i < N_OF_ATTEMPTS; i++) {
            bankAccount.withdraw(accountHolder.getId(), DRAW_AMOUNT);
        }
    }

    @Test
    void testInitialBalance() {
        assertAll(
                () -> assertEquals(0, bankAccount.getBalance()),
                () -> assertEquals(bankAccount.getHolder(),accountHolder)
        );
    }

    @Test
    void testDeposit() {
        bankAccount.deposit(accountHolder.getId(), INITIAL_DEPOSIT);
        assertEquals(INITIAL_DEPOSIT, bankAccount.getBalance());
    }

    @Test
    void testWrongDeposit() {
        bankAccount.deposit(2, 50);
        assertEquals(0, bankAccount.getBalance());
    }

    @Test
    void testWithdraw() {
        bankAccount.deposit(accountHolder.getId(), INITIAL_DEPOSIT);
        assertDoesNotThrow(()->bankAccount.withdraw(accountHolder.getId(), DRAW_AMOUNT));
    }

    @Test
    void testWrongWithdraw() {
        bankAccount.deposit(accountHolder.getId(), INITIAL_DEPOSIT);
        assertThrows(IllegalStateException.class,()->bankAccount.withdraw(2, DRAW_AMOUNT * 2));
    }

    @Test
    void tesMultipleWithdraw() {
        bankAccount.deposit(accountHolder.getId(), INITIAL_DEPOSIT);
        assertThrows(IllegalStateException.class, this::multipleWithDraw);
    }
}
