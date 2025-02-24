package tdd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SmartDoorLockTest {
    private SmartDoorLock smartDoorLock;
    private static final int PIN  = 1234;

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
    void testCorrectPin(){
        assertAll(
                this::testSetPin
        );
        assertEquals(smartDoorLock.unlock(PIN));
    }

    @Test
    public void todo() {
        assertTrue(true);
    }
}
