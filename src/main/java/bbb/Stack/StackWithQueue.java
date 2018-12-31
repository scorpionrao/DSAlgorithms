package bbb.Stack;

import java.util.EmptyStackException;
import java.util.LinkedList;
import java.util.Queue;

public class StackWithQueue {

    public void push(int key) {
        throw new UnsupportedOperationException();
    }
    public int pop() {
        throw new UnsupportedOperationException();
    }

    private static class PopOptimizedStack extends StackWithQueue {

        public Queue<Integer> primary = new LinkedList<>();
        public Queue<Integer> emptyForPush = new LinkedList();

        /* Time: O(N) */
        @Override
        public void push(int key) {
            emptyForPush.offer(key);
            while(!primary.isEmpty()) {
                emptyForPush.offer(primary.poll());
            }
            Queue temp = primary;
            primary = emptyForPush;
            emptyForPush = temp;

            System.out.println("Push : " + primary.peek());
        }

        /* Time: O(1) */
        @Override
        public int pop() {
            if(primary.isEmpty()) {
                throw new EmptyStackException();
            }
            int pop = primary.poll();
            System.out.println("Pop : " + pop);
            return pop;
        }
    }

    private static class PushOptimizedStack extends StackWithQueue {

        public Queue<Integer> primary = new LinkedList<>();
        public Queue<Integer> fullForPop = new LinkedList();

        /* Time: O(1) */
        @Override
        public void push(int key) {
            primary.offer(key);
            System.out.println("Push : " + key);
        }

        /* Time: O(N) */
        @Override
        public int pop() {
            if(primary.isEmpty()) {
                throw new EmptyStackException();
            }

            while(primary.size() > 1) {
                fullForPop.offer(primary.poll());
            }

            int onlyElement = primary.poll();
            System.out.println("Pop : " + onlyElement);

            Queue temp = primary;
            primary = fullForPop;
            fullForPop = temp;

            return onlyElement;
        }
    }

    private static void evaluate(int max, StackWithQueue stackWithQueue) {
        for(int i = 0; i < max; i++) {
            stackWithQueue.push(i);
        }
        for(int i = 0; i < max; i++) {
            stackWithQueue.pop();
        }
    }

    private static void evaluate(int max) {
        evaluate(max, new PopOptimizedStack());
        evaluate(max, new PushOptimizedStack());
    }


    public static void main(String[] args) {
        evaluate(10);


    }
}
