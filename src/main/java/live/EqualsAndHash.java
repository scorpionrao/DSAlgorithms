package live;

import java.util.HashSet;
import java.util.Set;
import static java.lang.System.out;

public class EqualsAndHash {

    public static class Person {

        private final String lastName;
        private String firstName;

        public Person(final String newLastName, final String newFirstName) {
            this.lastName = newLastName;
            this.firstName = newFirstName;
        }

        public void setFirstName(final String newFirstName) {
            this.firstName = newFirstName;
        }

        @Override
        public String toString()
        {
            return this.firstName + " " + this.lastName;
        }

        @Override
        public boolean equals(Object obj)
        {
            if (obj == null)
            {
                return false;
            }
            if (this == obj)
            {
                return true;
            }
            if (this.getClass() != obj.getClass())
            {
                return false;
            }
            final Person other = (Person) obj;
            if (this.lastName == null ? other.lastName != null : !this.lastName.equals(other.lastName))
            {
                return false;
            }
            if (this.firstName == null ? other.firstName != null : !this.firstName.equals(other.firstName))
            {
                return false;
            }
            return true;
        }
/*
        @Override
        public int hashCode()
        {
            return lastName.hashCode() + firstName.hashCode();
        }
*/
    }


    public static class HashAndEquals {

        private static final String HEADER_SEPARATOR = "======================================================================";

        private static final int HEADER_SEPARATOR_LENGTH = HEADER_SEPARATOR.length();

        private static final String NEW_LINE = System.getProperty("line.separator");

        private final Person person1 = new Person("Flintstone", "Fred");
        private final Person person2 = new Person("Rubble", "Barney");
        private final Person person3 = new Person("Flintstone", "Fred");
        private final Person person4 = new Person("Rubble", "Barney");

        public void displayContents() {
            printHeader("THE CONTENTS OF THE OBJECTS");
            out.println("Person 1: " + person1);
            out.println("Person 2: " + person2);
            out.println("Person 3: " + person3);
            out.println("Person 4: " + person4);
        }

        public void compareEquality() {
            printHeader("EQUALITY COMPARISONS");
            out.println("Person1.equals(Person2): " + person1.equals(person2));
            out.println("Person1.equals(Person3): " + person1.equals(person3));
            out.println("Person2.equals(Person4): " + person2.equals(person4));
        }

        public void compareHashCodes() {
            printHeader("COMPARE HASH CODES");
            out.println("Person1.hashCode(): " + person1.hashCode());
            out.println("Person2.hashCode(): " + person2.hashCode());
            out.println("Person3.hashCode(): " + person3.hashCode());
            out.println("Person4.hashCode(): " + person4.hashCode());
        }

        public Set<Person> addToHashSet() {
            printHeader("ADD ELEMENTS TO SET - ARE THEY ADDED OR THE SAME?");
            final Set<Person> set = new HashSet<Person>();
            out.println("Set.notSynchronizedMethod(Person1): " + set.add(person1));
            out.println("Set.notSynchronizedMethod(Person2): " + set.add(person2));
            out.println("Set.notSynchronizedMethod(Person3): " + set.add(person3));
            out.println("Set.notSynchronizedMethod(Person4): " + set.add(person4));
            return set;
        }

        public void removeFromHashSet(final Set<Person> sourceSet) {
            printHeader("REMOVE ELEMENTS FROM SET - CAN THEY BE FOUND TO BE REMOVED?");
            out.println("Set.remove(Person1): " + sourceSet.remove(person1));
            out.println("Set.remove(Person2): " + sourceSet.remove(person2));
            out.println("Set.remove(Person3): " + sourceSet.remove(person3));
            out.println("Set.remove(Person4): " + sourceSet.remove(person4));
        }

        public static void printHeader(final String headerText) {
            out.println(NEW_LINE);
            out.println(HEADER_SEPARATOR);
            out.println("= " + headerText);
            out.println(HEADER_SEPARATOR);
        }
    }

    public static void main(final String[] arguments) {
        final HashAndEquals instance = new HashAndEquals();
        instance.displayContents();
        instance.compareEquality();
        instance.compareHashCodes();
        final Set<Person> set = instance.addToHashSet();
        out.println("Set Before Removals: " + set);
        instance.person1.setFirstName("Bam Bam");
        instance.removeFromHashSet(set);
        out.println("Set After Removals: " + set);
    }
}
