package jcip.introduction;

public class SequenceGenerator {
    public class Unsafe {

        private int value;
        private Object mutex = new Object();
        public int getNext() {
            synchronized (this) {
                return value++;
            }

        }
    }

    public class Safe {
        private int value;

        public synchronized int getNext() {
            return value++;
        }
    }
}
