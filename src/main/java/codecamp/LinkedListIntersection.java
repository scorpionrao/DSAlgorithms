package codecamp;

public class LinkedListIntersection {

    private static class ListNode {
        int val;
        ListNode next;
        ListNode(int val) {
            this.val = val;
            this.next = null;
        }
    }

    private static ListNode intersection(ListNode root1, ListNode root2) {
        ListNode currentA = root1;
        ListNode currentB = root2;
        while(currentA != currentB) {
            if(currentA == null) {
                currentA = root2;
            } else {
                currentA = currentA.next;
            }

            if(currentB == null) {
                currentB = root1;
            } else {
                currentB = currentB.next;
            }
        }
        return currentA;
    }

    public static void main(String[] args) {
        ListNode root1 = new ListNode(3);
        root1.next = new ListNode(7);
        root1.next.next = new ListNode(8);
        root1.next.next.next = new ListNode(10);

        ListNode root2 = new ListNode(99);
        root2.next = new ListNode(1);
        root2.next.next = root1.next.next;

        ListNode node = intersection(root1, root2);
        if(node == null) {
            System.out.println("Test Failed");
        } else {
            System.out.println(node.val);
        }
    }
}
