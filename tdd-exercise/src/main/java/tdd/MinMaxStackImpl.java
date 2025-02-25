package tdd;

import java.util.Stack;

public class MinMaxStackImpl implements MinMaxStack{

    private Stack<Integer> mainStack;
    private Stack<Integer> maxStack;
    private Stack<Integer> minStack;
    public MinMaxStackImpl() {
        this.mainStack = new Stack<>();
        this.maxStack = new Stack<>();
        this.minStack = new Stack<>();
    }

    @Override
    public void push(int value) {
        this.mainStack.push(value);
        if (this.minStack.isEmpty() || value <= this.minStack.peek()) {
            this.minStack.push(value);
        }
        if (this.maxStack.isEmpty() || value >= this.maxStack.peek()) {
            this.maxStack.push(value);
        }

    }

    @Override
    public int pop() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty. Cannot pop.");
        }
        return this.mainStack.pop();
    }

    @Override
    public int peek() {
        if (isEmpty()) {
            throw new IllegalStateException("Stack is empty. Cannot peek.");
        }
        return this.mainStack.peek();
    }

    @Override
    public int getMin() {
        if (this.minStack.isEmpty()) {
            throw new IllegalStateException("Stack is empty. Cannot peek.");
        }
        return this.minStack.pop();
    }

    @Override
    public int getMax() {
        if (this.maxStack.isEmpty()) {
            throw new IllegalStateException("Stack is empty. Cannot peek.");
        }
        return this.maxStack.pop();
    }

    @Override
    public boolean isEmpty() {
        return this.mainStack.empty();
    }

    @Override
    public int size() {
        return 0;
    }
}
