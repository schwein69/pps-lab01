package tdd;


import java.util.LinkedList;
import java.util.List;

public class CircularQueueImpl implements CircularQueue{
    private final List<Integer> queueList;
    private int front;
    private final int maxCapacity;

    public CircularQueueImpl(int maxCapacity) {
        this.queueList =  new LinkedList<>();
        this.maxCapacity = maxCapacity;
        this.front = 0;
    }

    @Override
    public void enqueue(int value) {
        if(this.queueList.size() < this.maxCapacity){
            this.queueList.add(value);
        }
        else{
            this.queueList.remove(this.front);
            this.queueList.add(front,value);
            this.front = (this.front + 1 ) % this.maxCapacity;
        }
    }

    @Override
    public void dequeue() throws IllegalAccessException {
        if (isEmpty()){
            throw new IllegalAccessException();
        }else{
            this.queueList.removeFirst();
        }
    }

    @Override
    public int getMaxCapacity() {
        return this.maxCapacity;
    }

    @Override
    public int getFront() {
        return this.queueList.getFirst();
    }

    @Override
    public int getRear() {
        return this.queueList.getLast();
    }

    @Override
    public int getSize() {
        return this.queueList.size();
    }

    @Override
    public boolean isEmpty() {
        return this.queueList.isEmpty();
    }

    @Override
    public List<Integer> getQueueList() {
        return this.queueList;
    }

}
