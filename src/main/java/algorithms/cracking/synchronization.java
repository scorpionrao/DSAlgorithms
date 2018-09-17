package algorithms.cracking;

public class Synchronization
{

    public static class MyObject {
        public synchronized void foo(String name) {
            try {
                System.out.println("Thread " + name + ".foo(): starting");
                synchronized (this) {
                    Thread.currentThread().sleep(3000);
                }
                System.out.println("Thread " + name + ".foo(): ending");
            } catch (InterruptedException e) {
                System.out.println("Thread " + name + ".foo(): interrupted");
            }
        }
    }

    public static class MyClass extends Thread {
        private String name;
        private MyObject myObject;

        public MyClass(String name, MyObject myObject) {
            this.name = name;
            // this could be a singleton - representing a db, file, in-memory object
            this.myObject = myObject;
        }

        @Override
        public void run() {
            myObject.foo(name);
        }
    }

    public static void main(String[] args) {

        // Both Threads have same instance of object
        MyObject myObject = new MyObject();
        MyClass thread1 = new MyClass("Thread1", myObject);
        MyClass thread2 = new MyClass("Thread2", myObject);
        // Both Threads share resource myObject
        thread1.start();
        thread2.start();
    }
}
