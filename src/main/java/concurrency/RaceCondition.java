package concurrency;

public class RaceCondition {

    private int counter = 0;

    public void add(int value) {
        counter = counter + value;
        System.out.println(this.counter);
    }

    public static void main(String[] args) {
        RaceCondition raceCondition = new RaceCondition();

        Thread thread1 = new Thread(new Runnable(){
            @Override
            public void run() {
                raceCondition.add(5);
            }
        }, "Thread1");

        Thread thread2 = new Thread(new Runnable(){
            @Override
            public void run() {
                raceCondition.add(2);
            }
        }, "Thread2");

        Thread thread3 = new Thread(new Runnable(){
            @Override
            public void run() {
                raceCondition.add(4);
            }
        }, "Thread3");
        thread1.start();
        thread2.start();
        thread3.start();
    }
}
