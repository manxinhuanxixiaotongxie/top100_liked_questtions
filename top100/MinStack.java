package top100;

import java.util.Stack;

public class MinStack {
    Stack<Integer> dataStack;
    Stack<Integer> minStack;

    public MinStack() {
        dataStack = new Stack<>();
        minStack = new Stack<>();
    }

    public void push(int val) {
        if (minStack.isEmpty()) {
            dataStack.push(val);
            minStack.push(val);
        } else {
            minStack.push(Math.min(minStack.peek(), val));
            dataStack.push(val);
        }
    }

    public void pop() {
        if (!dataStack.isEmpty()) {
            dataStack.pop();
            minStack.pop();
        }
    }

    public int top() {
        if (!dataStack.isEmpty()) {
            return dataStack.peek();
        }
        return -1;
    }

    public int getMin() {
        if (!minStack.isEmpty()) {
            return minStack.peek();
        }
        return -1;
    }
}
