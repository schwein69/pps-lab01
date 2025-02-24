package tdd;

import java.util.Enumeration;


public class SmartDoorLockImpl implements SmartDoorLock{


    private static final int MAX_ATTEMPTS = 3;
    private int attempts;
    private int pin;
    private boolean locked;
    private boolean blocked;
    private boolean reset;

    public SmartDoorLockImpl() {
        this.attempts = 0;
        this.locked = false;
        this.blocked = false;
    }

    @Override
    public void setPin(int pin) {
        this.pin = pin;
    }

    @Override
    public void unlock(int pin) {
        if (this.pin == pin) {
            this.locked = false;
            this.attempts = 0;
        } else {
            this.attempts++;
            if(attempts >= MAX_ATTEMPTS) this.blocked = true;
        }
    }

    @Override
    public void lock() throws Exception {
        if (this.pin != 0){
            this.locked = true;
        }else{
            throw new Exception();
        }

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

    public int getPin() {
        return pin;
    }
}
