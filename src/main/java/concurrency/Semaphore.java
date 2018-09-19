package concurrency;

public class Semaphore {

    public static class SimpleSemaphore {

        private boolean sendSignal = false;

        public synchronized void takeORlockORsendsignal() {
            this.sendSignal = true;
            this.notify();
        }

        public synchronized void releaseORunlockORwaitforsignal()
                throws InterruptedException {
            while (!this.sendSignal) {
                wait();
            }
            this.sendSignal = false;
        }
    }

    public static class CountingSemaphore {

        private int signalCount = 0;

        public synchronized void takeORlockORsendsignal() {
            this.signalCount++;
            this.notify();
        }

        public synchronized void releaseORunlockORwaitforsignal()
                throws InterruptedException {
            while (this.signalCount == 0) {
                wait();
            }
            this.signalCount--;
        }
    }

    public static class BoundedSemaphore {

        private int signals = 0;
        private int bound = 0;

        public BoundedSemaphore(int bound) {
            this.bound = bound;
        }

        public synchronized void take() throws InterruptedException {
            if(this.signals >= bound) {
                wait();
            }
            this.signals++;
            this.notify();
        }

        public synchronized void release() throws InterruptedException {
            while(this.signals == 0) {
                wait();
            }
            this.signals--;
            this.notify();
        }
    }

    public static class SendingThread implements Runnable {

        SimpleSemaphore semaphore = null;

        private SendingThread(SimpleSemaphore semaphore) {
            this.semaphore = semaphore;
        }

        @Override
        public void run() {
            while(true) {
                try {
                    this.semaphore.releaseORunlockORwaitforsignal();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                // do something
                this.semaphore.takeORlockORsendsignal();
            }
        }
    }

    public static class ReceivingThread implements Runnable {

        SimpleSemaphore semaphore = null;

        private ReceivingThread(SimpleSemaphore semaphore) {
            this.semaphore = semaphore;
        }

        @Override
        public void run() {
            while(true) {
                try {
                    this.semaphore.releaseORunlockORwaitforsignal();
                    // receive signal, then do something...
                    this.semaphore.takeORlockORsendsignal();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws InterruptedException {
        SimpleSemaphore semaphore = new SimpleSemaphore();

        // take() and release() called by DIFFERENT thread
        Thread sendingThread = new Thread(new SendingThread(semaphore));
        Thread receivingThread = new Thread(new ReceivingThread(semaphore));
        sendingThread.start();
        receivingThread.start();

        // take() and release() called by SAME thread
        BoundedSemaphore boundedSemaphoreAsLock = new BoundedSemaphore(1);
        semaphore.takeORlockORsendsignal();
        try {
            // critical section
        } finally {
            boundedSemaphoreAsLock.release();
        }
    }
}
