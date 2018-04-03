package live;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedQueue;

public class InMemoryCollection {

    /*
        Requirement: Add, Remove and Get

        Array:
            Add - requires tracking index, does not prevent duplicates.
            Remove - requires moving all elements when element is removed in the middle.
            Get - Scanning through O(N)

        Dynamic Array:
            Add - O(1), but no way to prevent duplicates.
            Remove - O(N) uses EQUALS if defined.
            Contains - O(N) uses EQUALS if defined.

        HashSet:
            Add - O(1), prevents duplicates when hashcode is defined, otherwise allows duplicates.
            Remove - O(1), uses HASHCODE.
            Contains - O(N) uses HASHCODE and EQUALS (if required).

         */

    public static void main(String[] args) {
        // new DatastoreDynamicList().execute();
        new DatastoreHashSet().execute();
        // new DatastoreHashMap().execute();
    }

    public static class DatastoreHashMap {

        private Queue<User> queue = new ConcurrentLinkedQueue<>();
        private Map<Integer, User> hashMap = new ConcurrentHashMap();

        public void add(User user) {
            hashMap.put(user.getId(), user);
        }

        public void remove(User user) {
            hashMap.remove(user);
        }

        public Collection<User> getUsers() {
            return hashMap.values();
        }

        public void execute() {
            DatastoreHashMap store = new DatastoreHashMap();
            User user1 = new User(1, "R", "G");
            store.add(user1);
            User user2 = new User(2, "R", "G");
            store.add(user2);
            User duplicateUser = new User(2, "R", "G");
            store.add(duplicateUser);
            System.out.println("After Add 1 and 2 :" + store.getUsers());

            User user4 = new User(2, "A", "M");
            store.remove(user4);
            System.out.println("After Remove 4 :" + store.getUsers());

            User user5 = new User(1, "M", "G");
            System.out.println("Contains user5:" + store.getUsers().contains(user5));
        }
    }

    public static class DatastoreHashSet {

        private Set<User> hashSet = new HashSet<>();

        public void add(User user) {
            hashSet.add(user);
        }

        public void remove(User user) {
            hashSet.remove(user);
        }

        public Set<User> getUsers() {
            return hashSet;
        }

        public void execute() {
            DatastoreHashSet store = new DatastoreHashSet();
            User user1 = new User(1, "R", "G");
            store.add(user1);
            User user2 = new User(2, "R", "G");
            store.add(user2);
            User duplicateUser = new User(2, "R", "G");
            // duplicate will be added without equals and hashcode
            // duplicate will be added with equals and no hashcode
            // duplicate will NOT be added with hashcode
            store.add(duplicateUser);
            System.out.println("After Add 1 and 2 and duplicate :" + store.getUsers());

            // not a duplicate without equals and hashcode. nothing will be removed
            // not a duplicate with equals and no hashcode. nothing will be removed
            // with hashcode - a duplicate, will be removed
            User user4 = new User(2, "A", "M");
            store.remove(user4);
            System.out.println("After Remove 4 :" + store.getUsers());

            // false - with and without equals
            // true - with hash
            User user5 = new User(1, "M", "G");
            System.out.println("Contains user5:" + store.getUsers().contains(user5));
        }
    }

    public static class DatastoreDynamicList {

        private List<User> arrayList = new ArrayList<>();

        public void add(User user) {
            arrayList.add(user);
        }

        public void remove(User user) {
            arrayList.remove(user);
        }

        public List<User> getUsers() {
            return arrayList;
        }

        public void execute() {
            DatastoreDynamicList store = new DatastoreDynamicList();
            User user1 = new User(1, "R", "G");
            store.add(user1);
            User user2 = new User(2, "R", "G");
            store.add(user2);
            System.out.println("After Add 1 and 2 :" + store.getUsers());

            User user3 = new User(2, "A", "M");
            store.remove(user3);
            System.out.println("After Remove 3 :" + store.getUsers());

            User user4 = new User(1, "M", "G");
            System.out.println("Contains user2:" + store.getUsers().contains(user4));
        }
    }

    public static class User {

        private final int id;
        private final String firstName;
        private final String lastName;

        public User(final int id, final String firstName, final String lastName) {
            this.id = id;
            this.firstName = firstName;
            this.lastName = lastName;
        }

        public int getId() {
            return this.id;
        }

        public String getFirstName() {
            return this.firstName;
        }

        public String getLastName() {
            return this.lastName;
        }

        @Override
        public String toString() {
            return this.getId() + " " + this.getFirstName() + " " + this.getLastName();
        }

        @Override
        public boolean equals(Object o) {
            User user = (User) o;
            return this.getId() == user.getId();
        }

        @Override
        public int hashCode() {
            return this.getId();
        }

    }
}