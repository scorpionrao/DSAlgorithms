package live;
import java.util.HashMap;
public class LRUCache {
	
    class Node {
        int key;
        int value;
        Node previous;
        Node next;
        public Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
        public Node() {}
    }
    
    private Node head, tail;
    private int count = 0;
    private int capacity = 0;
    public HashMap<Integer, Node> cache;
    
    public LRUCache(int capacity) {
        this.capacity = capacity;
        head = new Node();
        head.previous = null;
        tail = new Node();
        tail.next = null;
        head.next = tail;
        tail.previous = head;
        cache = new HashMap<>();
    }
    
    public int get(int key) {
    	Node node = cache.get(key);
        if (node == null) {
            print();
            return -1;
        } else {
            moveToHead(node);
            print();
            return node.value;
        }
    }
    
    public void put(int key, int value) {
    	Node node = cache.get(key);
        if (node == null) {
        	Node cur = new Node(key, value);
            cache.put(key, cur);
            addToHead(cur);
            count++;
            if (count > capacity) {
            	Node last = popTail();
                cache.remove(last.key);
                count--;
            }
        } else {
            node.value = value;
            moveToHead(node);
        }
        print();
    }
    
    private void addToHead(Node node) {
        node.previous = head;
        node.next = head.next;
        head.next.previous = node;
        head.next = node;
    }
    private void removeNode(Node node) {
    	Node post = node.next;
    	Node pre = node.previous;
        post.previous = pre;
        pre.next = post;
    }
    private void moveToHead(Node node) {
        removeNode(node);
        addToHead(node);
    }
    private Node popTail() {
    	Node pre = tail.previous;
        removeNode(pre);
        return pre;
    }

    public void print() {
        Node current = head;
        while (current != null) {
            System.out.print(current.key + "-->");
            current = current.next;
        }
        System.out.println();
    }

    /*
        0-->2-->0-->
        0-->1-->2-->0-->
        0-->2-->1-->0-->
        0-->4-->2-->0-->
        0-->4-->2-->0-->
        0-->2-->4-->0-->
     */
    public static void main(String[] args) {
		LRUCache cache = new LRUCache(2);
        // cache - 2
		cache.put(2, 1);
        // cache - 1, 2
		cache.put(1, 1);
        // cache - 2, 1
        cache.put(2, 3);
        // cache - 4, 2
        cache.put(4, 1);

        // cache - 4, 2
		cache.get(1);
        // cache - 2, 4
		cache.get(2);


	}

}