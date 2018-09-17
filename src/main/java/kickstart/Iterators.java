package kickstart;

import java.util.Iterator;
import java.util.LinkedList;

public class Iterators {

    public static void main(String[] args) {

        /*
            Preferred to add or remove elements in the middle of the list.

            Use ArrayList - when the goal is to add or remove elements at the end of the list
         */
        LinkedList<String> linkedList = new LinkedList<>();
        linkedList.add("fox");
        linkedList.add("cat");
        linkedList.add("dog");
        linkedList.add("rabbit");

        /*
            Internal code for a simple for SinglyLinkedListLoop.

            Modifying a collection in the middle of iterating the same collection.
         */
        Iterator<String> it = linkedList.iterator();
        while (it.hasNext()) {
            String value = it.next();
            if(value.equals("cat")) {
                // REMOVES LAST ELEMENT RETURNED BY THE ITERATOR
                it.remove();
            } else {
                System.out.println(value);
            }
        }
    }
}
