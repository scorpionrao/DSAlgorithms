package concurrency;

import java.util.ArrayList;
import java.util.List;

public class Synchronizer {

    // no guarantees on access
    public synchronized void doSynchronized1() {
        // do a lot of work
    }

    // same lock object for all Threads
    Lock lock = new Lock();
    public void doSynchronized2() throws InterruptedException {
        this.lock.lock();
        // do a lot of work
        this.lock.unlock();
    }

    public class Lock {
        private boolean isLocked = false;
        private Thread lockingThread = null;

        // no guarantees on access
        public synchronized void lock() throws InterruptedException {
            while (isLocked) {
                // lock calls its own wait
                wait();
            }
            isLocked = true;
            lockingThread = Thread.currentThread();
        }

        public synchronized void unlock() {
            if(this.lockingThread != Thread.currentThread()) {
                throw new IllegalMonitorStateException("Calling thread does not have this lock");
            }
            isLocked = false;
            lockingThread = null;
            notify();
        }
    }

    // SEMAPHORE
    public class QueueObject {

        private boolean isNotified = false;

        public synchronized void doWait() throws InterruptedException {

            // prevents missed signals - in case thread was not properly notified
            while (!isNotified) {
                // wait on unique queue object
                this.wait();
            }
            this.isNotified = false;
        }

        public synchronized void doNotify() {
            this.isNotified = true;
            this.notify();
        }

        public boolean equals(Object o) {
            return this == o;
        }
    }

    // lock exactly decides which thread to awaken
    public class FairLock {
        private boolean isLocked = false;
        private Thread lockingThread = null;
        private List<QueueObject> waitingThreads = new ArrayList<>();

        // non-synchronized methods
        public void lock() throws InterruptedException {
            QueueObject queueObject = new QueueObject();
            boolean isLockedForThisThread = true;
            // synchronized block
            synchronized (this) {
                waitingThreads.add(queueObject);
            }
            while(isLockedForThisThread){
                synchronized(this){
                    isLockedForThisThread =
                            isLocked || waitingThreads.get(0) != queueObject;
                    if(!isLockedForThisThread){
                        isLocked = true;
                        waitingThreads.remove(queueObject);
                        lockingThread = Thread.currentThread();
                        return;
                    }
                }
                try{
                    // nested monitor lockout - wait is outside of synchronized block
                    queueObject.doWait();
                }catch(InterruptedException e){
                    synchronized(this) { waitingThreads.remove(queueObject); }
                    throw e;
                }
            }
        }

        public synchronized void unlock() {
            if (this.lockingThread != Thread.currentThread())
            {
                throw new IllegalMonitorStateException(
                        "Calling thread has not locked this lock");
            }
            isLocked = false;
            lockingThread = null;
            if (!waitingThreads.isEmpty())
            {
                waitingThreads.get(0).doNotify();
            }
        }
    }
}
