package bbb.Stack;

import java.util.EmptyStackException;
import java.util.Stack;

public class QueueWithStack {

    public void offer(int key) {
        throw new UnsupportedOperationException();
    }
    public int poll() {
        throw new UnsupportedOperationException();
    }

    private static class PollOptimizedQueue extends QueueWithStack {

        public Stack<Integer> primary = new Stack<>();
        public Stack<Integer> secondary = new Stack();

        /* Time: O(N) */
        @Override
        public void offer(int key) {

            while(!primary.isEmpty()) {
                secondary.push(primary.pop());
            }
            primary.push(key);

            while(!secondary.isEmpty()) {
                primary.push(secondary.pop());
            }

            System.out.println("Push : " + key);
        }

        /* Time: O(1) */
        @Override
        public int poll() {
            if(primary.isEmpty()) {
                throw new EmptyStackException();
            }
            int poll = primary.pop();
            System.out.println("Poll : " + poll);
            return poll;
        }
    }

    private static class OfferOptimizedQueue extends QueueWithStack {

        public Stack<Integer> primary = new Stack<>();
        public Stack<Integer> secondary = new Stack();

        /* Time: O(1) */
        @Override
        public void offer(int key) {
            primary.push(key);
            System.out.println("Push : " + key);
        }

        /* Time: O(N) */
        @Override
        public int poll() {
            if(primary.isEmpty()) {
                throw new EmptyStackException();
            }

            while(!primary.isEmpty()) {
                secondary.push(primary.pop());
            }

            int pop = secondary.pop();

            while(!secondary.isEmpty()) {
                primary.push(secondary.pop());
            }

            System.out.println("Pop : " + pop);
            return pop;
        }
    }

    private static void evaluate(int max, QueueWithStack queueWithStack) {
        for(int i = 1; i < max; i++) {
            queueWithStack.offer(i);
        }
        for(int i = 1; i < max; i++) {
            queueWithStack.poll();
        }
    }

    private static void evaluate(int max) {
        evaluate(max, new PollOptimizedQueue());
        evaluate(max, new OfferOptimizedQueue());
    }


    public static void main(String[] args) {
        evaluate(5);

    }
}
