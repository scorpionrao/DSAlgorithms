package codecamp;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MinimumClassRooms {

    private static class Interval {
        int start;
        int end;
        Interval(int start, int end) {
            this.start = start;
            this.end = end;
        }

        @Override
        public String toString() {
            return "[" + this.start + ", " + this.end + "]";
        }
    }

    private enum Type {
        START, END
    }

    private static class Node {
        int time;
        Type type;
        Node(int time, Type type) {
            this.type = type;
            this.time = time;
        }
    }

    /* Time : O(2N log 2N), Space : O(2*N) */
    private static int minimumClassRooms(Interval[] input) {
        List<Node> list = new ArrayList<>();
        for(Interval interval : input) {
            list.add(new Node(interval.start, Type.START));
            list.add(new Node(interval.end, Type.END));
        }

        Collections.sort(list, new Comparator<Node>() {
            public int compare(Node node1, Node node2) {
                return new Integer(node1.time).compareTo(new Integer(node2.time));
            }
        });

        int result = 0;
        int minClassRooms = 0;
        for(Node node : list) {
            if(node.type == Type.START) {
                minClassRooms++;
            } else {
                minClassRooms--;
            }
            result = Math.max(result, minClassRooms);
        }
        return result;
    }

    private static void evaluate(Interval[] input, int expected) {
        System.out.println("Input : " + input.toString());
        int result = minimumClassRooms(input);
        System.out.println("Expected : " + expected + ", Actual : " + result);
    }

    public static void main(String[] args) {
        Interval interval1 = new Interval(30, 75);
        Interval interval2 = new Interval(0, 50);
        Interval interval3 = new Interval(60, 150);
        Interval[] input = {interval1, interval2, interval3};

        evaluate(input, 2);
    }
}
