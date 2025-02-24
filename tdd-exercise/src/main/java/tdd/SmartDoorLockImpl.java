package tdd;

import java.util.Enumeration;


public class SmartDoorLockImpl implements SmartDoorLock{


    private static final int MAX_ATTEMPS = 3;
    private int attemps;
    private int pin;
    private boolean locked;
    private boolean blocked;
    private boolean reset;

    public SmartDoorLockImpl() {
        this.attemps = 0;
        this.locked = false;
        this.blocked = false;
    }

    @Override
    public void setPin(int pin) {
        this.pin = pin;
        this.locked = true;
    }

    @Override
    public void unlock(int pin) {
        if (this.pin == pin) {
            this.locked = false;
        }
    }

    @Override
    public void lock() {
        this.locked = true;
    }

    @Override
    public boolean isLocked() {
        return this.locked;
    }

    @Override
    public boolean isBlocked() {
        return this.blocked;
    }

    @Override
    public int getMaxAttempts() {
        return 0;
    }

    @Override
    public int getFailedAttempts() {
        return 0;
    }

    @Override
    public void reset() {

    }

    private void setBlocked(){
        this.blocked = true;
    }

    public int getPin() {
        return pin;
    }
}
