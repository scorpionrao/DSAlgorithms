package concurrency;

public class RaceCondition {

    private int counter = 0;

    public void add(int value) {
        counter = counter + value;
        System.out.println(this.counter);
    }

    public static void main(String[] args) {
        RaceCondition raceCondition = new RaceCondition();

        Thread[] threads = new Thread[20];
        for(int i = 0; i < threads.length; i++) {
            threads[i] = new Thread(new Runnable() {
               @Override
               public void run() {
                   raceCondition.add(5);
               }
            }, "Thread"+i);
        }

        for(int i = 0; i < threads.length; i++) {
            threads[i].start();
        }
    }
}
