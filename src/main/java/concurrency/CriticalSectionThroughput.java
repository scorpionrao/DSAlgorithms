package concurrency;

public class CriticalSectionThroughput {

    private int sum1 = 0;
    private int sum2 = 0;

    private Integer sum1Lock = new Integer(0);
    private Integer sum2Lock = new Integer(1);

    public void notSynchronizedMethod(int value1, int value2) {
        synchronized (sum1Lock) {
            sum1 = sum1 + value1;
            System.out.println(String.format("Sum1 : %d", sum1));
        }
        synchronized (sum2Lock) {
            sum2 = sum2 + value2;
            System.out.println(String.format("Sum2 : %d", sum2));
        }
    }

    public static void main(String[] args) {

        CriticalSectionThroughput criticalSectionThroughput = new CriticalSectionThroughput();

        new Thread(new Runnable() {
           @Override
           public void run() {
               criticalSectionThroughput.notSynchronizedMethod(1, 2);
           }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                criticalSectionThroughput.notSynchronizedMethod(1, 2);
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                criticalSectionThroughput.notSynchronizedMethod(1, 2);
            }
        }).start();

        new Thread(new Runnable() {
            @Override
            public void run() {
                criticalSectionThroughput.notSynchronizedMethod(1, 2);
            }
        }).start();
    }
}
