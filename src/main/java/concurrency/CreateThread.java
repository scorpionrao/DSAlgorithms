package concurrency;

public class CreateThread {

    public static void main(String[] args) {

        Thread thread1 = new Thread("Thread1") {
            @Override
            public void run() {
                System.out.println(getName());
            }
        };
        thread1.start();

        Thread thread2 = new Thread(new Runnable(){
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        }, "Thread2");
        thread2.start();

        System.out.println(Thread.currentThread().getName());
    }
}
