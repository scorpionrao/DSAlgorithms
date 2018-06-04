package concurrency;

import java.util.concurrent.atomic.AtomicBoolean;

public class CompareAndSwap {

    public class MyLockConcurrentSupport1 {
        private boolean locked = false;

        public synchronized boolean flip() {
            locked = !locked;
            return locked;
        }
    }

    public class MyLockConcurrentSupport2 {
        private AtomicBoolean locked = new AtomicBoolean(false);

        public boolean flip() {
            return locked.compareAndSet(false, true);
        }
    }
}
