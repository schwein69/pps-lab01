package tdd;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static java.util.stream.Stream.*;
import static org.junit.jupiter.api.Assertions.*;

/**
 * The test suite for testing the CircularList implementation
 */
public class CircularListTest {
    private CircularQueue circularQueue;
    private static final int MAX_SIZE = 3;
    private static final int N_ITERATIONS = 3;
    private static final int ENQUEUE_VALUE = 1;
    private static final int REPLACE_ENQUEUE_VALUE = 10;

    private void multipleEnqueue(){
        for (int i = 1; i <= N_ITERATIONS; i++) {
            circularQueue.enqueue(i);
        }
    }


    @BeforeEach
    public void beforeEach(){
        circularQueue = new CircularQueueImpl(MAX_SIZE);
    }
    @Test
    public void testQueueCapacity(){
        assertEquals(MAX_SIZE,circularQueue.getMaxCapacity());
    }
    @Test
    public void testEnqueue(){
        circularQueue.enqueue(ENQUEUE_VALUE);
        assertEquals(ENQUEUE_VALUE,circularQueue.getFront());
    }
    @Test
    public void testGetRear(){
        multipleEnqueue();
        assertEquals(N_ITERATIONS,circularQueue.getRear());
    }
    @Test
    public void testCurrentQueueSize(){
        circularQueue.enqueue(ENQUEUE_VALUE);
        circularQueue.enqueue(ENQUEUE_VALUE);
        assertEquals(2,circularQueue.getSize());
    }
    @Test
    public void testReplacement(){
        multipleEnqueue();
        circularQueue.enqueue(REPLACE_ENQUEUE_VALUE);
        assertEquals(REPLACE_ENQUEUE_VALUE,circularQueue.getFront());
    }
    @Test
    public void testMultipleReplacement(){
        multipleEnqueue();
        circularQueue.enqueue(REPLACE_ENQUEUE_VALUE);
        circularQueue.enqueue(REPLACE_ENQUEUE_VALUE);
        circularQueue.enqueue(REPLACE_ENQUEUE_VALUE);
        assertEquals(generate(() -> REPLACE_ENQUEUE_VALUE)
                .limit(N_ITERATIONS)
                .toList(),circularQueue.getQueueList());
    }

    @Test
    public void testRemoveNotEmptyQueue(){
        circularQueue.enqueue(ENQUEUE_VALUE);
        assertDoesNotThrow(()->circularQueue.dequeue());
    }

    @Test
    public void testRemoveEmptyQueue(){
        assertThrows(IllegalAccessException.class,()->circularQueue.dequeue());
    }

}
