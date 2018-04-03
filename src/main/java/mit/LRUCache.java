package mit;
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
    private HashMap<Integer, Node> cache;
    
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
            return -1;
        } else {
            moveToHead(node);
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
    
    public static void main(String[] args) {
		LRUCache cache = new LRUCache(2);

		cache.put(2, 1);
		cache.put(1, 1);
		cache.put(2, 3);
		cache.put(4, 1);
		
		System.out.println(cache.get(1));
		System.out.println(cache.get(2));
	}

}