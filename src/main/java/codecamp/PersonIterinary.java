package codecamp;

import java.util.*;

public class PersonIterinary {

    private static List<String> constructIterinary1(String[][] flights, String start) {
        Map<String, PriorityQueue<String>> map = new HashMap<>();
        for(String[] flight : flights) {
            map.computeIfAbsent(flight[0], k -> new PriorityQueue<>()).add(flight[1]);
        }

        List<String> result = new LinkedList<>();

        Stack<String> stack = new Stack<>();
        stack.push(start);
        while(!stack.isEmpty()) {
            while(map.containsKey(stack.peek()) && !map.get(stack.peek()).isEmpty()) {
                stack.push(map.get(stack.peek()).poll());
            }
            result.add(0, stack.pop());
        }
        return result.size() == flights.length + 1 ? result : null;
    }

    private static List<String> constructIterinary2(String[][] flights, String start) {
        List<String> result = new LinkedList<>();
        Map<String, PriorityQueue<String>> map = new HashMap<>();
        for(String[] flight : flights) {
            map.computeIfAbsent(flight[0], k -> new PriorityQueue<>()).add(flight[1]);
        }
        constructIterinary2(map, start, result);
        return result.size() == flights.length + 1 ? result : null;
    }

    private static void constructIterinary2(Map<String, PriorityQueue<String>> map,
                                                    String start, List<String> result) {
        while(map.containsKey(start) && !map.get(start).isEmpty()) {
            constructIterinary2(map, map.get(start).poll(), result);
        }
        result.add(0, start);
    }

    private static void print(List<String> actual) {
        if(actual != null) {
            System.out.println("Actual : " + actual.toString());
        }
    }

    public static void evaluate(String[][] flights, String start, List<String> expected) {
        System.out.println("INPUT : ");
        for(String[] flight : flights) {
            System.out.println(Arrays.toString(flight));
        }
        if(expected != null) {
            System.out.println("Expected : " + expected.toString());
        }
        List<String> actual1 = constructIterinary1(flights, start);
        print(actual1);
        List<String> actual2 = constructIterinary2(flights, start);
        print(actual2);
    }

    public static void main(String[] args) {
        String[][] flights1 = { {"SFO", "HKO"}, {"YYZ", "SFO"}, {"YUL", "YYZ"}, {"HKO", "ORD"} };
        List<String> expected1 = Arrays.asList("YUL", "YYZ", "SFO", "HKO", "ORD");
        evaluate(flights1, "YUL", expected1);
        String[][] flights2 = { {"SFO", "COM"}, {"COM", "YYZ"} };
        evaluate(flights2, "COM", null);
        String[][] flights3 = { {"A", "B"}, {"A", "C"}, {"B", "C"}, {"C", "A"} };
        List<String> expected3 = Arrays.asList("A", "B", "C", "A", "C");
        evaluate(flights3, "A", expected3);
        String[][] flights4 = { {"MUC", "LHR"}, {"JFK", "MUC"}, {"SFO", "SJC"}, {"LHR", "SFO"} };
        List<String> expected4 = Arrays.asList("JFK", "MUC", "LHR", "SFO", "SJC");
        evaluate(flights4, "JFK", expected4);
    }
}
