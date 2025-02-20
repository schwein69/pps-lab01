package tdd;

/**
 *  Task 3 - TDD for Circular Queue
 *  A simple CircularQueue that stores integers with a fixed capacity.
 *  When full, new elements overwrite the oldest ones.
 *  E.g., given a capacity of 3, the queue [1, 2, 3] will become [4, 2, 3] after adding 4 and will become [4, 5, 3] after adding 5.
 *
 *  When removing elements, the oldest ones are removed first.
 *  Therefore, giving [4, 5, 3], the first element to be removed is 4, then 5, and finally 3.
 */
public interface CircularQueue {

    /**
     * Adds an element to the queue.
     * If the queue is full, the oldest element is removed to make space.
     *
     * @param value The integer value to add.
     */
    void enqueue(int value);

    /**
     * Removes and returns the oldest (namely the first) element from the queue.
     *
     * @return The removed element.
     * @throws IllegalStateException if the queue is empty.
     */
    int dequeue();

    /**
     * Checks if the queue is empty.
     *
     * @return true if the queue is empty, false otherwise.
     */
    boolean isEmpty();

    /**
     * Checks if the queue is full.
     *
     * @return true if the queue is full, false otherwise.
     */
    boolean isFull();

    /**
     * Gets the current number of elements in the queue.
     *
     * @return The size of the queue.
     */
    int size();

    /**
     * Gets the maximum capacity of the queue.
     *
     * @return The maximum number of elements the queue can hold.
     */
    int capacity();
}