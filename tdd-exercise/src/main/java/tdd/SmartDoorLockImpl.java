package tdd;

import java.util.Enumeration;
import java.util.Map;


public class SmartDoorLockImpl implements SmartDoorLock{


    private static final int MAX_ATTEMPTS = 3;
    private int attempts;
    private int pin;
    private boolean locked;
    private boolean blocked;
    private boolean reset;

    public SmartDoorLockImpl() {
        this.pin = 0;
        this.attempts = 0;
        this.locked = false;
        this.blocked = false;
    }

    @Override
    public void setPin(int pin) {
        if (!locked && !blocked) {
            this.pin = pin;
        }
    }

    @Override
    public void unlock(int pin) {
        if (blocked) return;
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
        return MAX_ATTEMPTS;
    }

    @Override
    public int getFailedAttempts() {
        return this.attempts;
    }

    @Override
    public void reset() {
        this.pin = 0;
        this.locked = false;
        this.blocked = false;
        this.attempts = 0;
    }

}
