package leetcode;

import java.util.*;

public class MergeTwoLinkedLists {

    public static class Node implements Comparable<Node> {
        int key;
        Node next;
        public Node(int key) {
            this.key = key;
            this.next = null;
        }

        @Override
        public int compareTo(Node o) {
            return this.key - o.key;
        }
    }

    public static void print(Node mergeHead) {
        Node current = mergeHead;
        while (current != null) {
            System.out.print(current.key + " --> ");
            current = current.next;
        }
        System.out.println("NULL");
    }

    /* Time : O(M*N log(M*N)), Space : O(M+N) */
    public static Node mergeTwoNaive(Node node1, Node node2) {
        List<Node> list = new ArrayList<>();
        while(node1 != null) {
            list.add(node1);
            node1 = node1.next;
        }
        while(node2 != null) {
            list.add(node2);
            node2 = node2.next;
        }
        Collections.sort(list);

        Node head = list.get(0);
        for(int i = 0; i < list.size() - 1; i++) {
            list.get(i).next = list.get(i+1);
        }
        return head;
    }

    private static Node mergeTwoListsRecurse(Node node1, Node node2) {
        if(node1 == null) {
            return node2;
        } else if(node2 == null) {
            return node1;
        } else if(node1.key < node2.key) {
            node1.next = mergeTwoListsRecurse(node1.next, node2);
            return node1;
        } else {
            node2.next = mergeTwoListsRecurse(node1, node2.next);
            return node2;
        }
    }

    private static Node mergeTwoListsIterative(Node node1, Node node2) {

        Node preHead = new Node(0);
        Node prev = preHead;
        while(node1 != null && node2 != null) {
            if(node1.key < node2.key) {
                prev.next = node1;
                node1 = node1.next;
            } else {
                prev.next = node2;
                node2 = node2.next;
            }
            prev = prev.next;
        }
        while(node1 != null) {
            prev.next = node1;
            node1 = node1.next;
            prev = prev.next;
        }
        while(node2 != null) {
            prev.next = node2;
            node2 = node2.next;
            prev = prev.next;
        }
        return preHead.next;
    }

    public Node mergeTwoListsPQ(Node l1, Node l2) {
        if(l1 == null) {
            return l2;
        }
        if(l2 == null) {
            return l1;
        }

        PriorityQueue<Node> pq = new PriorityQueue<>(2, new Comparator<Node>(){
            @Override
            public int compare(Node node1, Node node2) {
                return node1.key - node2.key;
            }
        });
        pq.add(l1);
        pq.add(l2);

        Node head = null;
        Node tail = null;
        while (!pq.isEmpty()) {
            Node node = pq.poll();
            if(head == null) {
                head = node;
                tail = head;
            } else {
                tail.next = node;
                tail = tail.next;
            }
            if(node.next != null) {
                pq.add(node.next);
            }
        }
        return head;
    }

    public static void main(String[] args) {
        Node root1 = new Node(1);
        root1.next = new Node(2);
        root1.next.next = new Node(4);

        Node root2 = new Node(1);
        root2.next = new Node(3);
        root2.next.next = new Node(4);

        //TrieNode mergedHeadRecurse = mergeTwoListsRecurse(root1, root2);
        //print(mergedHeadRecurse);
        //TrieNode mergedHeadIterative = mergeTwoListsIterative(root1, root2);
        //print(mergedHeadIterative);

        Node mergedHeadNaive = mergeTwoListsIterative(root1, root2);
        print(mergedHeadNaive);
    }
}
