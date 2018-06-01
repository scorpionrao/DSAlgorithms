package concurrency;

import java.util.List;
import java.util.LinkedList;

public class BlockingQueue {

    private List queue = new LinkedList();
    private int limit = 0;
    
    public BlockingQueue(int limit) {
        this.limit = limit;
    }
    
    public synchronized void enqueue(Object item) throws InterruptedException {
        // Upper limit - wait for this thread
        if(this.queue.size() >= limit) {
            wait();
        }
        // Lower Limit - inform all the waiting threads to push into
        if(this.queue.isEmpty()) {
            notifyAll();
        }
        this.queue.add(item);
    }
    
    public synchronized Object dequeue() throws InterruptedException {
        // Lower limit - wait for this thread
        while(this.queue.isEmpty()) {
            wait();
        }
        // Upper Limit - inform all the waiting threads to pull out
        if(this.queue.size() == this.limit) {
            notifyAll();
        }
        return this.queue.remove(0);
    }
}
