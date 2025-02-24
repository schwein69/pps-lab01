package tdd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SmartDoorLockTest {
    private SmartDoorLock smartDoorLock;
    private static final int PIN  = 1234;
    private static final int ATTEMPTS = 5;
    @BeforeEach
    void beforeEach(){
        smartDoorLock = new SmartDoorLockImpl();
    }

    @Test
    void testInitialState(){
        assertAll(
                () -> assertFalse(smartDoorLock.isLocked()),
                () -> assertFalse(smartDoorLock.isBlocked())
        );
    }

    @Test
    void testSetPin(){
        testInitialState();
        smartDoorLock.setPin(PIN);
    }

    @Test
    void testCanBeLocked() throws Exception {
        testSetPin();
        smartDoorLock.lock();
        assertTrue(smartDoorLock.isLocked());
    }

    @Test
    void testCannotBeLocked() {
        assertThrows(Exception.class,()->smartDoorLock.lock());
    }

    @Test
    void testCorrectPin(){
        assertAll(
                this::testSetPin
        );
        smartDoorLock.unlock(PIN);
        assertFalse(smartDoorLock.isLocked());
    }

    @Test
    void testWrongPin(){
        assertAll(
                this::testSetPin
        );
        smartDoorLock.unlock(5555);
        assertTrue(smartDoorLock.isLocked());
    }

    private void multipleAttempts() {
        for (int i = 0; i < ATTEMPTS; i++) {
            smartDoorLock.unlock((int)(Math.random() * 9999));
        }
    }
    @Test
    void testMultipleWrongPin(){
        assertAll(
                this::testSetPin
        );
        multipleAttempts();
        assertTrue(smartDoorLock.isBlocked());
    }

    @Test
    public void todo() {
        assertTrue(true);
    }
}
