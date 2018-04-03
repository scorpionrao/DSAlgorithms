package live;

import java.util.HashSet;
import java.util.Set;

public class jit {

    public static class Another {
        static String anotherVariable = "a";
    }

    public static class Employee {
        static String employeeVariable = "a";
        private String firstName;
        private String lastName;

        Employee(String firstName, String lastName) {
            this.firstName = firstName;
            this.lastName = lastName;
        }

        public String getFirstName() {
            return this.firstName;
        }

        public String getLastName() {
            return  this.lastName;
        }

        @Override
        public boolean equals(Object object) {
            if(! (object instanceof Employee)) {
                return false;
            }
            Employee employee = (Employee) object;
            return this.getFirstName().equals(employee.getFirstName())
                    && this.getLastName().equals(employee.getLastName());
        }

        @Override
        public int hashCode() {
            int result = 17;
            result = (31 * result) + (this.getFirstName() != null ? this.getFirstName().hashCode() : 0);
            result = (31 * result) + (this.getLastName() != null ? this.getLastName().hashCode() : 0);
            return result;
        }

    }

    public static void main(String[] args) {
        String s1 = "abc", s2 = "abc";

        System.out.println("literals + ==       : " + (s1 == s2));
        System.out.println("literals + equals   : " + s1.equals(s2));

        String s3 = new String("abc");
        String s4 = new String("abc");

        System.out.println("objects + ==      : " + (s3 == s4));
        System.out.println("objects + equals  : " + s3.equals(s4));

        Employee employee1 = new Employee("R", "G");
        Employee employee2 = new Employee("R", "G");

        System.out.println("employee + ==      : " + (employee1 == employee2));
        System.out.println("employee + equals  : " + employee1.equals(employee2));

        Set<Employee> employees = new HashSet<>();
        employees.add(employee1);

        // False if equals(Object) is NOT defined for Employee
        // True if equals(Object) is defined for Employee
        System.out.println(employees.contains(employee2));

        // Different value if hashcode is not defined
        // Same value if hashcode is defined
        System.out.println("employee1.hashCode(): " + employee1.hashCode() + "  employee2.hashCode(): " + employee2.hashCode());

        System.out.println("literals + ==       : " + (Another.anotherVariable == Employee.employeeVariable));
    }
}
