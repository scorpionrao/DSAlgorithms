package leetcode;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class MinStack {

    public interface MinStackInterface {
        void push(int x);
        void pop();
        int top();
        int getMin();
    }

    /* Space : O(2*N) */
    private static class MinStack1 implements MinStackInterface {
        Stack<Integer> stack;
        Stack<Integer> minStack;

        public MinStack1() {
            stack = new Stack<>();
            minStack = new Stack<>();
        }

        public void push(int x) {
            stack.push(x);
            if(minStack.isEmpty()) {
                minStack.push(x);
            } else {
                int y = minStack.peek();
                if(y < x) {
                    minStack.push(y);
                } else {
                    minStack.push(x);
                }
            }
        }

        public void pop() {
            stack.pop();
            minStack.pop();
        }

        public int top() {
            return stack.peek();
        }

        public int getMin() {
            if(!minStack.isEmpty()) {
                return minStack.peek();
            } else {
                return -1;
            }
        }
    }

    /* Space : O(N) */
    private static class MinStack2 implements MinStackInterface {
        Stack<Integer> stack;
        int min;

        public MinStack2() {
            stack = new Stack<>();
            min = Integer.MAX_VALUE;
        }

        public void push(int x) {
            if(x < min) {
                stack.push(min);
                min = x;
            }
            stack.push(x);
        }

        public void pop() {
            if(stack.pop() == min) {
                min = stack.pop();
            }
        }

        public int top() {
            return stack.peek();
        }

        public int getMin() {
            return min;
        }
    }

    private static void evaluate(String name, MinStackInterface minStack) {

        List<Integer> result = new ArrayList<>();
        minStack.push(-2);
        minStack.push(0);
        minStack.push(-3);
        result.add(minStack.getMin());
        minStack.pop();
        result.add(minStack.top());
        result.add(minStack.getMin());
        System.out.println(name + " : " + result.toString());
    }

    public static void main(String[] args) {
        System.out.println("Expected : [-3, 0, -2]");
        evaluate("MinStack 1", new MinStack1());
        evaluate("MinStack 2", new MinStack2());
    }
}
