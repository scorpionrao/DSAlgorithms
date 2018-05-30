package concurrency;

public class ThreadSafety {

    StringBuilder sb = new StringBuilder();

    public void update(String text) {
        sb.append(text);
        System.out.println(sb.toString());
    }

    public static void main(String[] args) {

        ThreadSafety threadSafety = new ThreadSafety();

        new Thread(new Runnable(){
            @Override
            public void run() {
                threadSafety.update("Thread1");
            }
        }).start();

        new Thread(new Runnable(){
            @Override
            public void run() {
                threadSafety.update("Thread2");
            }
        }).start();
    }
}
