package algorithms.cracking;

public class Threads {

    public static class ThreadExample extends Thread {

        int count = 0;

        @Override
        public void run() {
            System.out.println("Thread starting...");
            try {
                while(count < 5) {
                    Thread.currentThread().sleep(2000);
                    System.out.println("In Thread, count is " + this.count);
                    this.count++;
                }
            } catch(InterruptedException e) {
                System.out.println("Thread interrupted");
            }
            System.out.println("Thread Terminating");
        }
    }

    public static class RunnableExample implements Runnable {

        int count = 0;

        @Override
        public void run() {
            System.out.println("Runnable starting...");
            try {
                while (count < 5) {
                    Thread.currentThread().sleep(2000);
                    System.out.println("In Runnable, count is " + this.count);
                    this.count++;
                }
            } catch(InterruptedException e) {
                System.out.println("Runnable interrupted");
            }
            System.out.println("Runnable Terminating");
        }
    }

    public static void main(String[] args) {
        // new ThreadExample().start();
        RunnableExample runnableExample = new RunnableExample();
        new Thread(runnableExample).start();
        while (runnableExample.count != 5) {
            try {
                Thread.currentThread().sleep(500);
                System.out.println("In main thread sleep");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
