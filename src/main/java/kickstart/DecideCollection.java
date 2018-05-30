package kickstart;

public class DecideCollection {

    /*
        1. What you need the collection to do.
        2. are you using the fastest collection for your purposes.

        LISTS

        Objects remain in order.
        Elements indexed via an integer.
        Checking for particular item - slow.
        Looking for an item by index - fast.
        Iterating - fast.
        Sorting - possible.

        ArrayList - If you only add / remove items at the end of list.
        LinkedList - If add / remove items elsewhere in the list.

        SETS

        Only store unique values.
        Great for removing duplicates.
        Not indexed, unlike lists.
        Checking for particular item - fast.
        Objects - must implement hashCode() and equals().

        HashSet - Order is unimportant, OK if it changes.
        TreeSet - Sorted in natural order is important.
        LinkedHashSet - Elements retain insertion order.



        MAPS

        Key Value lookups
        Retrieving item - fast.
        Iterating over maps - slow.
        Keys must implements hashCode() and equals()


        HashMap - Keys not in any particular order, order liable to change.
        TreeMap - Keys sorted in natural order.
        LinkedHashMap - Elements retain insertion order.
     */
}
