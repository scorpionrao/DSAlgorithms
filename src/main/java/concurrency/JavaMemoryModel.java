package concurrency;

public class JavaMemoryModel {

    public static class Instance implements Runnable {
        @Override
        public void run() {
            methodOne();
        }

        public void methodOne() {
            int localVariable1 = 1;
            MySharedObject localVariable2 = MySharedObject.sharedInstance;
            methodTwo();
        }

        public void methodTwo() {
            Integer localVariable1 = new Integer(99);
        }
    }

    public static class MySharedObject {

        public static final MySharedObject sharedInstance = new MySharedObject();

        public Integer object2 = new Integer(23);
        public Integer object4 = new Integer(28);

        public long member1 = 12349;
        public long member2 = 98761;
    }

}
