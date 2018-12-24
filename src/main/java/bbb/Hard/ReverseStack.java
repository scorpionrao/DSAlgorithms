package bbb.Hard;

import java.util.Stack;

public class ReverseStack {

    /* Pop, Reverse remaining, insert popped at bottom */
    private static void reverseStack(Stack<Integer> stack) {
        if(stack.isEmpty()) {
            return;
        }
        int lastPop = stack.pop();
        reverseStack(stack);
        insertAtBottom(stack, lastPop);
    }

    /* Empty stack and push Target to bottom */
    private static void insertAtBottom(Stack<Integer> stack, int bottomTarget) {
        if(stack.isEmpty()) {
            stack.push(bottomTarget);
            return;
        }
        int temp = stack.pop();
        insertAtBottom(stack, bottomTarget);
        stack.push(temp);
    }

    public static void main(String[] args) {
        Stack<Integer> stack = new Stack();
        for(int i = 1000; i >= 0; i--) {
            stack.push(i);
        }
        reverseStack(stack);
        while(!stack.isEmpty()) {
            System.out.println(stack.pop());
        }
    }
}
