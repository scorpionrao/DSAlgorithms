package kickstart;

import java.util.*;

public class ComparableSorting {

    public static class Person implements Comparable<Person> {
        private String name;
        public Person(String name) {
            this.name = name;
        }
        public String getName() {
            return this.name;
        }
        public String toString() {
            return this.name;
        }

        public int compareTo(Person person) {
            Integer length1 = this.name.length();
            Integer length2 = person.name.length();
            if(length1 > length2) {
                return 1;
            } else if(length1 < length2) {
                return -1;
            } else {
                // KEY ELEMENT - ensures Equals and Comparable arrive at same result for equal length results.
                return this.getName().compareTo(person.getName());
            }
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Person person = (Person) o;

            return name != null ? name.equals(person.name) : person.name == null;

        }

        @Override
        public int hashCode() {
            return name != null ? name.hashCode() : 0;
        }
    }

    public static void main(String[] args) {

        List<Person> list = new ArrayList<>();
        SortedSet<Person> set = new TreeSet<>();

        addElements(list);
        addElements(set);

        showElements(list);
        System.out.println("*******************");
        showElements(set);
    }

    private static void addElements(Collection<Person> collection) {
        collection.add(new Person("Joe"));
        collection.add(new Person("Sue"));
        collection.add(new Person("Juliet"));
        collection.add(new Person("Clare"));
        collection.add(new Person("Mike"));
    }

    private static void showElements(Collection<Person> collection) {
        for(Person person : collection) {
            System.out.println(person);
        }
    }
}
