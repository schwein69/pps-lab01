package tdd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SmartDoorLockTest {
    private SmartDoorLock smartDoorLock;
    private static final int MAX_ATTEMPTS = 3;
    private static final int PIN = 1234;
    private static final int NEW_PIN = 6666;
    private static final int WRONG_PIN = 9999;
    private static final int ATTEMPTS = 5;

    private void multipleAttempts() {
        for (int i = 0; i < ATTEMPTS; i++) {
            smartDoorLock.unlock(WRONG_PIN);
        }
    }

    @BeforeEach
    void beforeEach() {
        smartDoorLock = new SmartDoorLockImpl();
    }

    @Test
    void testInitialState() {
        assertFalse(smartDoorLock.isLocked());
        assertFalse(smartDoorLock.isBlocked());
    }

    @Test
    void testSetPin() {
        assertDoesNotThrow(() -> smartDoorLock.setPin(PIN));
    }

    @Test
    void testLockAfterSetPin() {
        assertDoesNotThrow(() -> smartDoorLock.setPin(PIN));
        assertDoesNotThrow(() -> smartDoorLock.lock());
        assertTrue(smartDoorLock.isLocked());
    }

    @Test
    void testLockWithoutSettingPin() {
        assertThrows(Exception.class, () -> smartDoorLock.lock());
    }

    @Test
    void testIsBlocked() {
        assertDoesNotThrow(() -> smartDoorLock.setPin(PIN));
        assertDoesNotThrow(() -> smartDoorLock.lock());
        multipleAttempts();
        assertTrue(smartDoorLock.isBlocked());
    }

    @Test
    void testIsNotBlocked() {
        assertDoesNotThrow(() -> smartDoorLock.setPin(PIN));
        assertDoesNotThrow(() -> smartDoorLock.lock());
        smartDoorLock.unlock(PIN);
        assertFalse(smartDoorLock.isBlocked());
    }

    @Test
    void testCorrectPin() {
        assertDoesNotThrow(() -> smartDoorLock.setPin(PIN));
        assertDoesNotThrow(() -> smartDoorLock.lock());
        smartDoorLock.unlock(PIN);
        assertFalse(smartDoorLock.isLocked());
    }

    @Test
    void testWrongPinFirstTime() {
        assertDoesNotThrow(() -> smartDoorLock.setPin(PIN));
        assertDoesNotThrow(() -> smartDoorLock.lock());
        smartDoorLock.unlock(WRONG_PIN);
        assertFalse(smartDoorLock.isBlocked());
    }

    @Test
    void testMaxAttempts() {
        assertEquals(MAX_ATTEMPTS, smartDoorLock.getMaxAttempts());
    }

    @Test
    void testFailedAttempts() {
        assertDoesNotThrow(() -> smartDoorLock.setPin(PIN));
        assertDoesNotThrow(() -> smartDoorLock.lock());
        smartDoorLock.unlock(WRONG_PIN);
        assertEquals(1, smartDoorLock.getFailedAttempts());
    }

    @Test
    public void testReset() {
        assertDoesNotThrow(() -> smartDoorLock.setPin(PIN));
        assertDoesNotThrow(() -> smartDoorLock.lock());
        multipleAttempts();
        smartDoorLock.reset();
        assertAll(
                () -> assertFalse(smartDoorLock.isLocked()),
                () -> assertFalse(smartDoorLock.isBlocked()),
                () -> assertEquals(0, smartDoorLock.getFailedAttempts()));
    }

    @Test
    void testSetPinAgainWhenNotBlockedLocked() {
        assertDoesNotThrow(() -> smartDoorLock.setPin(PIN));
        assertDoesNotThrow(() -> smartDoorLock.lock());
        smartDoorLock.unlock(PIN);
        assertAll(
                () -> assertFalse(smartDoorLock.isLocked()),
                () -> assertFalse(smartDoorLock.isBlocked()),
                () -> assertDoesNotThrow(() -> smartDoorLock.setPin(NEW_PIN)),
                () -> assertDoesNotThrow(() -> smartDoorLock.lock())
        );
        smartDoorLock.unlock(NEW_PIN);
        assertFalse(smartDoorLock.isLocked());
    }

    @Test
    void testSetPinAgainWhenBlockedLocked() {
        assertDoesNotThrow(() -> smartDoorLock.setPin(PIN));
        assertDoesNotThrow(() -> smartDoorLock.lock());
        multipleAttempts();
        assertAll(
                () -> assertTrue(smartDoorLock.isLocked()),
                () -> assertTrue(smartDoorLock.isBlocked()),
                () -> assertThrows(IllegalStateException.class, () -> smartDoorLock.setPin(NEW_PIN)));
    }
}
