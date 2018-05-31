package concurrency;

public class SimpleLock {

    public class CounterWithSynchronizedBlock {
        private int count = 0;
        public int inc() {
            synchronized (this) {
                // critical section
                return ++count;
            }
        }
    }

    public class CounterWithLock {
        private ReentrantLock lock = new ReentrantLock();
        private int count = 0;

        public int inc() throws InterruptedException {
            lock.lock();
            int newCount = ++count;
            try {
                // critical section
            } finally {
                // LOCK FAIRNESS
                lock.unlock();
            }
            return newCount;
        }
    }
    public class ReentrantLock {

        boolean isLocked = false;
        Thread lockedBy = null;
        int lockedCount = 0;

        public synchronized void lock() throws InterruptedException {
            Thread callingThread = Thread.currentThread();
            // spin lock
            // skips check if same thread tries to obtain lock (REENTRANT)
            while (isLocked && lockedBy != callingThread) {
                // lock call is parked
                wait();
            }
            isLocked = true;
            lockedCount++;
            lockedBy = callingThread;
        }

        public synchronized void unlock() {
            if(Thread.currentThread() == this.lockedBy) {
                lockedCount--;
                // Unlock only when all REENTRANTS exited
                if (lockedCount == 0) {
                    isLocked = false;
                    // awakens one of the waiting thread
                    notify();
                }
            }
        }
    }

    public class ReentrantNot {
        public synchronized void outer() {
            inner();
        }
        public synchronized void inner() {
            // do something
        }
    }

    public class ReentrantYes {
        ReentrantLock lock = new ReentrantLock();
        public void outer() throws InterruptedException {
            lock.lock();
            inner();
            lock.unlock();
        }
        public synchronized void inner() throws InterruptedException {
            lock.lock();
            // do something
            lock.unlock();
        }
    }
}
