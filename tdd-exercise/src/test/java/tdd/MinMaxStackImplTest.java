package tdd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Stack;

import static org.junit.jupiter.api.Assertions.*;

class MinMaxStackImplTest {
    private MinMaxStackImpl stack;
    private static final int PUSH_VALUE = 1;
    private static final int ITERATIONS = 5;

    private void pushMultipleValues(){
        for (int i = 1; i < ITERATIONS + 1; i++) {
            stack.push(i);
        }
    }
    @BeforeEach
    public void beforeEach(){
        stack = new MinMaxStackImpl();
    }
    @Test
    public void push(){
        stack.push(PUSH_VALUE);
        assertFalse(stack.isEmpty());
    }
    @Test
    public void popNotEmptyStack(){
        stack.push(PUSH_VALUE);
        assertEquals(PUSH_VALUE,stack.pop());
        assertTrue(stack.isEmpty());
    }
    @Test
    public void popEmptyStack(){
        assertThrows(IllegalStateException.class,()->stack.pop());
    }
    @Test
    public void peekStack(){
        stack.push(PUSH_VALUE);
        assertEquals(PUSH_VALUE,stack.peek());
    }
    @Test
    public void peekEmptyStack(){
        assertThrows(IllegalStateException.class,()->stack.peek());
    }
    @Test
    public void popMinStack(){
        pushMultipleValues();
        assertEquals(PUSH_VALUE,stack.getMin());
    }
    @Test
    public void popMaxStack(){
        pushMultipleValues();
        assertEquals(ITERATIONS,stack.getMax());
    }

    @Test
    public void TestCurrentStackSize() {
        pushMultipleValues();
        assertEquals(ITERATIONS,stack.size());
    }

}