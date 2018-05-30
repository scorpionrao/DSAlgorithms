package concurrency;

import java.util.ArrayList;
import java.util.List;

public class Synchronizer {

    public synchronized void doSynchronized1() {
        // do a lot of work
    }

    Lock lock = new Lock();

    public void doSynchronized2() throws InterruptedException {
        this.lock.lock();
        // do a lot of work
        this.lock.unlock();
    }

    public class Lock {
        private boolean isLocked = false;
        private Thread lockingThread = null;

        public synchronized void lock() throws InterruptedException {
            while (isLocked) {
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

    public class QueueObject {
        private boolean isNotified = false;

        public synchronized void doWait() throws InterruptedException {
            while (!isNotified) {
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

    public class FairLock {
        private boolean isLocked = false;
        private Thread lockingThread = null;
        private List<QueueObject> waitingThreads = new ArrayList<>();

        public void lock() throws InterruptedException {
            QueueObject queueObject = new QueueObject();
            boolean isLockedForThisThread = true;
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
