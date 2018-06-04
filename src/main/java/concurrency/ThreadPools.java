package concurrency;

import java.util.ArrayList;
import java.util.List;

public class ThreadPools {

    public interface ThreadPool {
        // enqueue task to Blocking queue
        void execute(Runnable task) throws Exception;
        // shut down Threadpool by stopping all threads
        void stop();
    }

    public interface PoolThread {
        void run();
        void doStop();
    }

    public class MyThreadPool implements ThreadPool {

        // DS for tasks - needs a maximum size
        private BlockingQueue taskQueue = null;
        // DS for Threads - needs dynamic structure
        private List<PoolThread> threads = new ArrayList<>();
        // Check for status of thread pool
        private boolean isStopped = false;

        public MyThreadPool(int numOfThreads, int maxNoTasks) {
            taskQueue = new BlockingQueue(maxNoTasks);
            for(int i = 0; i < numOfThreads; i++) {
                threads.add(new MyPoolThread(taskQueue));
            }
            for(PoolThread thread : threads) {
                ((Thread) thread).start();
            }
        }
        @Override
        public synchronized void execute(Runnable task) throws Exception {
            if(this.isStopped) {
                throw new IllegalStateException("ThreadPool is stopped");
            }
            this.taskQueue.enqueue(task);
        }

        @Override
        public synchronized void stop() {
            this.isStopped = true;
            for(PoolThread thread : threads) {
                ((Thread) thread).stop();
            }
        }
    }

    public class MyPoolThread extends Thread implements PoolThread {

        private BlockingQueue taskQueue = null;
        private boolean isStopped = false;

        public MyPoolThread(BlockingQueue queue) {
            taskQueue = queue;
        }

        public void run() {
            while (!this.isStopped()) {
                try {
                    Runnable runnable = (Runnable) taskQueue.dequeue();
                    runnable.run();
                } catch (Exception e) {
                    // log or otherwise report exception,
                    // keep pool thread alive.
                }
            }
        }

        @Override
        public void doStop() {
            this.isStopped = true;
            this.interrupt();
        }

        public synchronized boolean isStopped() {
            return this.isStopped;
        }
    }
}
