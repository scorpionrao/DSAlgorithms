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
            if(name.length() > person.name.length()) {
                return 1;
            } else if(name.length() < person.name.length()) {
                return -1;
            } else {
                // String comparison over weak Length comparison.
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

        Set<Person> unsortedSet = new LinkedHashSet<>();
        SortedSet<Person> sortedSet = new TreeSet<>();

        addElements(unsortedSet);
        addElements(sortedSet);

        showElements(unsortedSet);
        System.out.println("*******************");
        showElements(sortedSet);
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
