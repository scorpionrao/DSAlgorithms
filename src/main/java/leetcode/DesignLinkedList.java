package leetcode;

public class DesignLinkedList {

    private static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) {
            this.val = val;
            this.next = null;
        }
    }

    ListNode head;
    private int size;
    /** Initialize your data structure here. */
    public DesignLinkedList() {
        this.head = null;
    }

    /** Get the value of the index-th node in the linked list. If the index is invalid, return -1. */
    public int get(int index) {
        if(index >= size) {
            return -1;
        }
        ListNode current = head;
        for(int i = 0; i < index; i++) {
            current = current.next;
        }
        return current.val;
    }

    /** Add a node of value val before the first element of the linked list. After the insertion, the new node will be the first node of the linked list. */
    public void addAtHead(int val) {
        ListNode temp = head;
        head = new ListNode(val);
        head.next = temp;
        size++;
    }

    /** Append a node of value val to the last element of the linked list. */
    public void addAtTail(int val) {
        ListNode newNode = new ListNode(val);
        size++;
        if(head == null) {
            head = newNode;
        } else {
            ListNode current = head;
            while(current.next != null) {
                current = current.next;
            }
            current.next = newNode;
        }
    }

    /** Add a node of value val before the index-th node in the linked list. If index equals to the length of linked list, the node will be appended to the end of linked list. If index is greater than the length, the node will not be inserted. */
    public void addAtIndex(int index, int val) {
        if(index > size) {
            return;
        } else if(index == 0) {
            addAtHead(val);
        } else {
            ListNode current = head;
            for(int i = 0; i < index - 1; i++) {
                current = current.next;
            }
            ListNode newNode = new ListNode(val);
            size++;
            newNode.next = current.next;
            current.next = newNode;
        }
        print();
    }

    /** Delete the index-th node in the linked list, if the index is valid. */
    public void deleteAtIndex(int index) {
        if(index >= size) {
            return;
        }
        ListNode current = head;
        for(int i = 0; i < index - 1; i++) {
            current = current.next;
        }
        current.next = current.next.next;
        size--;
        print();
    }

    private void print() {
        ListNode current = head;
        System.out.print("Actual : ");
        while(current != null) {
            System.out.print(current.val + " -> ");
            current = current.next;
        }
        System.out.print("NULL");
        System.out.println();
    }

    public static void main(String[] args) {
        DesignLinkedList linkedList = new DesignLinkedList();

        linkedList.addAtHead(1);
        // linkedList.addAtIndex(0, 1);
        int val0 = linkedList.get(0);
        System.out.println("Expected : 1, Actual : " + val0);
        linkedList.print();
        linkedList.addAtTail(3);
        linkedList.print();
        linkedList.addAtIndex(1, 2);
        System.out.println("Expected : 1 -> 2 -> 3 -> NULL");
        linkedList.print();
        int val1 = linkedList.get(1);
        System.out.println("Expected : 2, Actual : " + val1);
        linkedList.deleteAtIndex(1);
        System.out.println("Expected : 1 -> 3 -> NULL");
        linkedList.print();
        int val2 = linkedList.get(1);
        System.out.println("Expected : 3, Actual : " + val2);

        /*
        linkedList.addAtHead(7);
        linkedList.addAtHead(2);
        linkedList.addAtHead(1);
        linkedList.addAtIndex(3, 0);
        linkedList.deleteAtIndex(2);
        linkedList.addAtHead(6);
        linkedList.addAtTail(4);
        linkedList.get(4);
        linkedList.addAtHead(4);
        linkedList.addAtIndex(5, 0);
        linkedList.addAtHead(6);
        */
    }
}
