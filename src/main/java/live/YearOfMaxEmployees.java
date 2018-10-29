package live;

import java.util.Comparator;
import java.util.Map;
import java.util.PriorityQueue;

public class YearOfMaxEmployees {

    public static class Employee {
        int startYear;
        int endYear;

        Employee(int startYear, int endYear) {
            this.startYear = startYear;
            this.endYear = endYear;
        }
    }

    /*
        ClosestXdestinations: Create a histogram of years vs employees and find the tallest BAR.

        1. Traverse all start years and find MIN value
        2. Traverse all end years and find MAX value

        3. Create frequency array of size "MAX - MIN + 1"

        4. For each employee, for (startYear --> endYear) { freq[year - MIN]++ }

        5. Find index of max value

        6. ClosestXdestinations: maxIndex + MIN

        Worst Case: All employees started on Day 1 and ended on Day End.

        Time Complexity: O(max-min+1) * N
        Space Complexity: O(max-min+1) extra space

     */
    public static int getYearOfMaxEmployeesNaive(Employee[] employees) {

        int minYear = Integer.MAX_VALUE;
        int maxYear = Integer.MIN_VALUE;

        // O(N)
        for(int i = 0; i < employees.length; i++) {
            if(employees[i].startYear < minYear) {
                minYear = employees[i].startYear;
            }
            if(employees[i].endYear > maxYear) {
                maxYear = employees[i].endYear;
            }
        }

        int[] freq = new int[maxYear - minYear + 1];
        // O(N)
        for(int i = 0; i < employees.length; i++) {
            // O(max-min+1)
            for(int year = employees[i].startYear; year <= employees[i].endYear; year++) {
                freq[year - minYear]++;
            }
        }

        int maxIndex = Integer.MIN_VALUE;
        int maxValue = Integer.MIN_VALUE;

        // O(max-min+1)
        for(int index = 0; index < freq.length; index++) {
            if(freq[index] > maxValue) {
                maxValue = freq[index];
                maxIndex = index;
            }
        }
        return maxIndex + minYear;
    }

    public enum Type {
        START, END
    }
    public static class Node {
        int year;
        Type type;

        Node(int year, Type type) {
            this.year = year;
            this.type = type;
        }
    }

    /*
        Time Complexity: O(N Log N)
        Space Complexity: O(2 * N) --> O(N)
     */
    public static int getYearOfMaxEmployeesOptimized(Employee[] employees) {

        PriorityQueue<Node> priorityQueue = new PriorityQueue<>(2 * employees.length, new Comparator<Node>(){
            @Override
            public int compare(Node node1, Node node2) {
                if(node1.year > node2.year) {
                    return 1;
                } else if (node1.year < node2.year) {
                    return -1;
                } else {
                    if(node1.type == node2.type) {
                        return 0;
                    } else if(node1.type == Type.END) {
                        return 1;
                    } else {
                        return -1;
                    }
                }
            }
        });

        for(int i = 0; i < employees.length; i++) {
            priorityQueue.add(new Node(employees[i].startYear, Type.START));
            priorityQueue.add(new Node(employees[i].endYear + 1, Type.END));
        }

        int maxEmployeesYear = Integer.MIN_VALUE;
        int maxEmployees = Integer.MIN_VALUE;

        int currentEmployees = 0;

        while (!priorityQueue.isEmpty()) {
            Node node = priorityQueue.poll();
            System.out.println(node.year + " " + node.type.toString() + " " + maxEmployees);
            if(node.type == Type.START) {
                currentEmployees++;
                if(currentEmployees > maxEmployees) {
                    maxEmployees = currentEmployees;
                    maxEmployeesYear = node.year;
                }
            } else if(node.type == Type.END){
                currentEmployees--;
            } else {
                throw new IllegalArgumentException();
            }
        }
        return maxEmployeesYear;
    }

    public static void main(String[] args) {
        /*
        Employee e0 = new Employee(1940, 1945);
        Employee e1 = new Employee(1945, 1945);
        Employee e2 = new Employee(1945, 1950);
        */

        Employee e0 = new Employee(1, 4);
        Employee e1 = new Employee(2, 5);
        Employee e2 = new Employee(10, 12);
        Employee e3 = new Employee(5, 9);
        Employee e4 = new Employee(5, 12);

        Employee[] employees = {e0, e1, e2, e3, e4};

        int yearOfMaxEmployees = getYearOfMaxEmployeesOptimized(employees);
        System.out.println("Max Employees Year = " + yearOfMaxEmployees);
    }
}