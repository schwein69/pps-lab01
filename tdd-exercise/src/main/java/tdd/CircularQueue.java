package tdd;

import java.util.List;

/**
 *  Task 3 - TDD for Circular Queue
 *  A simple CircularQueue that stores integers with a **fixed** capacity.
 *  When full, new elements overwrite the oldest ones.
 *  
 *  When removing elements, the oldest ones are removed first.
 *  Therefore, giving [4, 5, 3], the first element to be removed is 4, then 5, and finally 3.
 *  
 *  For the exercise: 
 *   - Think about the test cases you need to write.
 *   - Introduce methods in the interface in order to make the tests pass.
 *   - Refactor
 *
 */

public interface CircularQueue {

    /**
     * Adds an element to the queue.
     * If the queue is full, the new element overwrites the oldest element.
     *
     * @param value The integer value to enqueue.
     */
    void enqueue(int value);

    /**
     * Removes the oldest element from the queue.
     *
     * @throws IllegalAccessException If the queue is empty.
     */
    void dequeue() throws IllegalAccessException;

    /**
     * Returns the maximum capacity of the queue.
     *
     * @return The maximum number of elements the queue can hold.
     */
    int getMaxCapacity();

    /**
     * Retrieves the front element of the queue (the oldest element).
     *
     * @return The front element of the queue.
     */
    int getFront();

    /**
     * Retrieves the rear element of the queue (the most recently added element).
     *
     * @return The rear element of the queue.
     */
    int getRear();

    /**
     * Returns the current number of elements in the queue.
     *
     * @return The current size of the queue.
     */
    int getSize();

    /**
     * Checks if the queue is empty.
     *
     * @return {@code true} if the queue is empty, otherwise {@code false}.
     */
    boolean isEmpty();

    /**
     * Returns the list representation of the queue, showing elements.
     *
     * @return A list containing the elements of the queue.
     */
    List<Integer> getQueueList();
}