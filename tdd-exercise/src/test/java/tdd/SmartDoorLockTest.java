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
        assertAll(() -> assertFalse(smartDoorLock.isLocked()), () -> assertFalse(smartDoorLock.isBlocked()));
        smartDoorLock.setPin(PIN);
    }

    @Test
    void testLockAfterSetPin() {
        smartDoorLock.setPin(PIN);
        assertDoesNotThrow(() -> smartDoorLock.lock());
        assertTrue(smartDoorLock.isLocked());
    }

    @Test
    void testLockWithoutSettingPin() {
        assertThrows(Exception.class, () -> smartDoorLock.lock());
    }

    @Test
    void testIsBlocked() {
        smartDoorLock.setPin(PIN);
        assertDoesNotThrow(() -> smartDoorLock.lock());
        multipleAttempts();
        assertTrue(smartDoorLock.isBlocked());
    }

    @Test
    void testIsNotBlocked() {
        smartDoorLock.setPin(PIN);
        assertDoesNotThrow(() -> smartDoorLock.lock());
        smartDoorLock.unlock(PIN);
        assertFalse(smartDoorLock.isBlocked());
    }

    @Test
    void testCorrectPin() {
        smartDoorLock.setPin(PIN);
        assertDoesNotThrow(() -> smartDoorLock.lock());
        smartDoorLock.unlock(PIN);
        assertFalse(smartDoorLock.isLocked());
    }

    @Test
    void testWrongPinFirstTime() {
        smartDoorLock.setPin(PIN);
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
        smartDoorLock.setPin(PIN);
        assertDoesNotThrow(() -> smartDoorLock.lock());
        smartDoorLock.unlock(WRONG_PIN);
        assertEquals(1, smartDoorLock.getFailedAttempts());
    }

    @Test
    public void testReset() {
        smartDoorLock.setPin(PIN);
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
        smartDoorLock.setPin(PIN);
        assertDoesNotThrow(() -> smartDoorLock.lock());
        smartDoorLock.unlock(PIN);
        assertAll(
                () -> assertFalse(smartDoorLock.isLocked()),
                () -> assertFalse(smartDoorLock.isBlocked()));
        smartDoorLock.setPin(NEW_PIN);
        assertDoesNotThrow(() -> smartDoorLock.lock());
        smartDoorLock.unlock(NEW_PIN);
        assertFalse(smartDoorLock.isLocked());
    }

    @Test
    void testSetPinAgainWhenBlockedLocked() {
        smartDoorLock.setPin(PIN);
        assertDoesNotThrow(() -> smartDoorLock.lock());
        multipleAttempts();
        assertAll(
                () -> assertTrue(smartDoorLock.isLocked()),
                () -> assertTrue(smartDoorLock.isBlocked()));
        smartDoorLock.setPin(NEW_PIN);
        smartDoorLock.unlock(NEW_PIN);
        assertTrue(smartDoorLock.isLocked());
    }
}
